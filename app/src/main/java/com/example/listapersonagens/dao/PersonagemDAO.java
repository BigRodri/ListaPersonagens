package com.example.listapersonagens.dao;

import com.example.listapersonagens.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
    //Cria uma Lista do tipo array
    private final static List<Personagem> personagens = new ArrayList<>();
    //Ele diz qual a alocação do ID
    private static int contadorDeID = 1;
    //Metodo construtor para salvar o personagem
    public void salvar(Personagem personagemSalvo) {
        personagemSalvo.setId(contadorDeID);
        personagens.add(personagemSalvo);
        contadorDeID++;


    }
    //Metodo construtor para editar o personagem
    public void editar(Personagem personagem) {
        Personagem personagemEscolhido = buscaPersonagemID(personagem);
        if (personagemEscolhido != null) {
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
        }
    }
    //Metodo construtor para buscar o personagem pelo ID
    private Personagem buscaPersonagemID(Personagem personagem) {
        for (Personagem p : personagens) {
            if (p.getId() == personagem.getId()) {
                return p;
            }
        }
        return null;
    }
    //Retornar a lista
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }
    //Metodo Construtor para remover os personagens
    public void remove(Personagem personagem) {
        Personagem personagemDevolvido = buscaPersonagemID(personagem);

        if(personagemDevolvido != null)
        {
        personagens.remove(personagemDevolvido);
        }

    }
}
