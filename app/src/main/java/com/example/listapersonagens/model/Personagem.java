package com.example.listapersonagens.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {

    /*Carregando variáveis*/
    private String nome;
    private String altura;
    private String nascimento;
    private int id = 0;

    public Personagem(String nome, String altura, String nascimento) {
        /*Setando as variáveis carregadas acima*/
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    //Serve para que não ocorra erro quando todos os campo estiverem vazios
    public Personagem() {
    }
    //Todos os Get e Set abaixo pegam e gravam as informações
    public String toString() {
        return nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    //Pega e grava o ID
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    //Verifica se o ID é valido
    public boolean IdValido() {
        return id > 0;
    }
}