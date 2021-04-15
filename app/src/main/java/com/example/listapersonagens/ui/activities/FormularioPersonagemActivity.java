package com.example.listapersonagens.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listapersonagens.R;
import com.example.listapersonagens.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);


        EditText campoNome = findViewById(R.id.edittext_name);
        EditText campoAltura = findViewById(R.id.edittext_altura);
        EditText campoNascimento = findViewById(R.id.edittext_nascimento);


        Button botaoSalvar = findViewById(R.id.button_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);

                Toast.makeText(FormularioPersonagemActivity.this,
                                 personagemSalvo.getNome() + " - " +
                                      personagemSalvo.getAltura() + " - " +
                                      personagemSalvo.getNascimento(), Toast.LENGTH_SHORT).show();

                new Personagem(nome, altura, nascimento);

                //Toast.makeText(FormularioPersonagemActivity.this, "Personagem Salvo!",Toast.LENGTH_SHORT).show();
            }
        });

    }

}