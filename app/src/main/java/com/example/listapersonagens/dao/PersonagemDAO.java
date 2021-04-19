package com.example.listapersonagens.dao;

import com.example.listapersonagens.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeID = 1;

    public void salvar (Personagem personagemSalvo) {
        personagemSalvo.setId(contadorDeID);
        personagens.add(personagemSalvo);
        contadorDeID++;



    }

    public void editar(Personagem personagem){
        Personagem personagemEscolhido = null;
        for (Personagem p:
             personagens) {
            if(p.getId() == personagem.getId()){
                personagemEscolhido = p;
            }

        }

        if(personagemEscolhido != null){
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
        }
    }

    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }
}
