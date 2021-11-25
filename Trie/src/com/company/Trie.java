
package com.company;

import java.util.*;

public class Trie {
    private Node root;
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[31m";

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

    public boolean findWord(String word) {
        Node current = root;  //ссылка на корневой узел

        // проходим по массиву строк
        for (char i : word.toLowerCase().toCharArray()) {

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

    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    boolean isEmpty() {
        return root == null;
    }

    private boolean delete(Node current, String word, int index) {
        if (index == word.length()) {           //проверка конец ли ключа
            if (!current.isEndOfWord()) {       //обработка случаев когда ключа нет в дереве
                return false;
            }
            current.setEndOfWord(false);       //обработка случая когда ключ есть в дереве
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        Node node = current.getChildren().get(ch);
        if (node == null) {                     //обработка сдучая когда елемента ключа нет в дерве
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord();

        //обработка ключа когда он есть в дереве и является уникальным для определенного префикса
        //удаляем узлы пока не достигнем узла с меткой конца
        if (shouldDeleteCurrentNode) {

            current.getChildren().remove(ch);

            return current.getChildren().isEmpty();
        }
        return false;
    }

    public List<String> prefixWord(String prefix) {
        List<String> words = new ArrayList<>();


        return words;
    }

    public void print() {
        printTrie(root, "root", 0);
    }

    public void printTrie(Node current, String val, int cnt) {
        if (current.isEndOfWord())
            System.out.print("|" + TEXT_RED + val + TEXT_RESET);
        else System.out.print("|" + val);
        if (current.getChildren().isEmpty()) {
            return;
        }

        for (Map.Entry<Character, Node> pair : current.getChildren().entrySet()) {
            System.out.println(WHITE_UNDERLINED + "   " + TEXT_RESET);
            System.out.print("|" + "    ");
            for (int i = 0; i < cnt; ++i)
                System.out.print("|" + "    ");

            printTrie(pair.getValue(), Character.toString(pair.getKey()), cnt + 1);


        }
    }


}

