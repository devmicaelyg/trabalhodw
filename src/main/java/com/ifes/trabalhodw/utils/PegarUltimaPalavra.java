package com.ifes.trabalhodw.utils;

public class PegarUltimaPalavra {
    public static String pegarUltimaPalavra(String texto) {
        String[] palavras = texto.split(" ");
        if (palavras.length > 0) {
            return palavras[palavras.length - 1];
        } else {
            return "";
        }
    }
}
