package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    //для вывода
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN


    public static void main(String[] args) throws IOException {
        //загружаем список слов из файла
        List<String> lines = Files.readAllLines(Paths.get("/home/masha/Univer/TA/RGR/tests/russian.txt"));

        //заполняем дерево стовами
        Trie root = new Trie();
        for (String line : lines) {
            root.insert(line.toLowerCase());
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {


            System.out.print("Введите строку: ");
            String input = scanner.nextLine();
            String[] test = input.split(" ");

            System.out.print("Найденные ошибки: ");
            for (String i : test) {
                //подсветка слов которые не были найдены в дереве
                if (!root.findWord(i.toLowerCase())) {
                    System.out.print(RED_UNDERLINED + i + TEXT_RESET + " ");
                } else System.out.print(i + " ");
            }

            System.out.print("\nПодсказка: ");
            for (String i : test) {
                //если слово не нашлось в дереве
                if (!root.findWord(i.toLowerCase())) {
                    System.out.print("( ");
                    //вызываем метод для поиска слов по задданому префиксу

                    List<String> s = root.suggest(root.findPrefix(i));

                    //выводим только первые 3 найденных
                    for (int j = 0; j < s.size(); ++j) {
                        if (j == 3) break;
                        System.out.print(GREEN_UNDERLINED + s.get(j) + TEXT_RESET + " ");

                    }
                    System.out.print(")");
                } else System.out.print(i + " ");
            }
            System.out.println("\n");

        }
    }


}
