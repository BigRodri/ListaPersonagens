package com.example.listapersonagens.ui.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaDePersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);

        List<String> personagem = new ArrayList<>(Arrays.asList("Neo", "Leon", "Chun-Li", "Donatello"));

        ListView ListaDePersonagens = findViewById(R.id.Activity_main_lista_personagem);
        ListaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagem));


        /*TextView primeiroPersonagem = findViewById(R.id.textView);
        primeiroPersonagem.setText(personagem.get(0));

        TextView segundoPersonagem = findViewById(R.id.textView2);
        segundoPersonagem.setText(personagem.get(1));

        TextView terceiroPersonagem = findViewById(R.id.textView3);
        terceiroPersonagem.setText(personagem.get(2));*/



    }
}
