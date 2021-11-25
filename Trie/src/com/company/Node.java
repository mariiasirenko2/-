package com.company;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private Map<Character, Node> children = new HashMap<>(); //храним ключ и узел к которому он ведет
    private boolean endOfWord;                               // показывает является ли узел - концом ключа

    Map<Character, Node> getChildren() {
        return children;
    }

    boolean isEndOfWord() {
        return endOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }


}
