package com.company;

import java.util.*;

public class Trie {
    private Node root;

    public Trie() {
        root = new Node();
    }
    public void insert(String word) {
        Node current = root; //ссылка на корневой узел

        // проходим по массиву строк
        for (char i : word.toCharArray()) {

            //переходим на следующий узел
            //проверяя есть ли в текущем ветка с ключем і
            //если нет то добавляем
            current = current.getChildren().computeIfAbsent(i, c -> new Node());
        }

        //ставим на текущем узле пометку что это конец значения
        current.setEndOfWord(true);
    }

    public String findPrefix(String input) {
        Node current = root;  //ссылка на корневой узел
        //префикс слова
        StringBuilder ans = new StringBuilder();

        //проходим по каждому елементу строки
        //поке не наткнемся та несуществующий
        for (char i : input.toCharArray()) {
            Node node = current.getChildren().get(i);

            if (node == null) {
                return ans.toString();
            }

            //добавляем елемент в результирующую строку
            ans.append(i);

            //переходим на следующий узел
            current = node;
        }

        return ans.toString();
    }

    public List<String> suggest(String prefix) {

        List<String> suggest = new ArrayList<>();

        Node current = root;

        //проходим по каждому елементу входящего префикса
        //чтобы найти узел с которого нужно начать поиск
        for (char i : prefix.toCharArray()) {
            current = current.getChildren().get(i);

            //проверка на то что такой префикс может существовать
            if (current == null) return suggest;
        }

        //запускаем рекурсивный поиск
        searchWord(current, suggest, prefix);
        return suggest;
    }

    public void searchWord(Node current, List<String> list, String word) {

        if (current == null) return;

        //ели нашли слово - добавляем в список
        if (current.isEndOfWord()) {
            list.add(word);
        }

        //получаем мап детей текущего узла
        Map<Character, Node> map = current.getChildren();

        //запускаем поиск слов по каждому из детей узла
        //запуская рекурсию на вход которой подан следующий узел
        //в который нужно заходить, спсок найденных слом и новый префикс
        for (Character c : map.keySet()) {
            searchWord(map.get(c), list, word + c);
        }
    }

    public boolean findWord(String word) {
        Node current = root;  //ссылка на корневой узел

        // проходим по массиву строк
        for (char i : word.toCharArray()) {

            //получаем узел по ключу текущего елемента строки
            Node node = current.getChildren().get(i);

            //если узел отсутствует - такого значение в дереве нет
            if (node == null) {
                return false;
            }

            //переходим на следующий уровень
            current = node;
        }

        //возвращаем результат. Если узел помечен как конечьный по true
        // иначе false
        return current.isEndOfWord();
    }


    boolean isEmpty() {
        return root == null;
    }


}

