package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaDePersonagemActivity extends AppCompatActivity {

    private final PersonagemDAO dao =new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);



        setTitle("Lista de Personagens");

        dao.salvar(new Personagem("Ken","1,80","10121998"));
        dao.salvar(new Personagem("Ryu","1,80","10121998"));

        FloatingActionButton botaoNovoPersonagem =  findViewById(R.id.fab_novo_personagem);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaDePersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();


        ListView ListaDePersonagens = findViewById(R.id.lista_personagem);
        List<Personagem> personagem = dao.todos();
        ListaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagem));
        ListaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Personagem personagemEscolhido = personagem.get(posicao);
                Intent vaiParaFormulario = new Intent(ListaDePersonagemActivity.this, FormularioPersonagemActivity.class);
                vaiParaFormulario.putExtra("personagem", personagemEscolhido);
                startActivity(vaiParaFormulario);

            }
        });

    }
}
