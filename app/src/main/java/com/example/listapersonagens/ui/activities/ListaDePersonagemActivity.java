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

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaDePersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Personagens";
    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR);
        configuraFabNovoPersonagem();

    }

    private void configuraFabNovoPersonagem() {
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_novo_personagem);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioSalva();
            }
        });
    }

    private void abreFormularioSalva() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView ListaDePersonagens = findViewById(R.id.lista_personagem);
        final List<Personagem> personagem = dao.todos();
        listaDePersonagens(ListaDePersonagens, personagem);
        configuraItemPorClick(ListaDePersonagens);
    }

    private void configuraItemPorClick(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditar(personagemEscolhido);

            }
        });
    }

    private void abreFormularioModoEditar(Personagem personagem) {
        Intent vaiParaFormulario = new Intent(this, FormularioPersonagemActivity.class);
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagem);
        startActivity(vaiParaFormulario);
    }

    private void listaDePersonagens(ListView listaDePersonagens, List<Personagem> personagem) {
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagem));
    }
}
