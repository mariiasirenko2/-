package com.company;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String TEXT_RESET = "\u001B[0m";

    public static void main(String[] args) throws IOException {


        List<String> lines = Files.readAllLines(Paths.get("/home/masha/Univer/TA/tests/numbers.txt")); //вставляем своий путьк файлу со строками

        Trie root = new Trie();
        for (String line : lines) {
            root.insert(line.toLowerCase());
        }

        root.print();


    }
}