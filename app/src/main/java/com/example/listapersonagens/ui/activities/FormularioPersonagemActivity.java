package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {
    //As duas primeiras strings são para setar o Titulo da Appbar
    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem";
    private static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";
    //Pegar os Edittext das variaveis
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    //Pegar as informações da classe PersonageDAO
    private final PersonagemDAO dao = new PersonagemDAO();
    //Pegar as informações da classe Personagem
    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        inicializaCampos();
        configuraBotaoSalvar();
        carregaPersonagem();
    }
    /*Metodo usado para carregar o Formulario, se ja existir o personagem
    ele carrega o editar personagem*/
    private void carregaPersonagem() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }
    //Metodo usado para setar as informações nas variaveis
    private void preencheCampos() {
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }
    //Quando clicar no botão ele salva o personagem
    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.button_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizaFormulario();
            }
        });
    }
    //Metodo que finaliza o formulario de criação e volta para a lista de personagens
    private void finalizaFormulario() {
        preenchePersonagem();
        if(personagem.IdValido()){
            dao.editar(personagem);
        } else{
        dao.salvar(personagem);
        finish();
        dao.editar(personagem);
        }
        finish();
    }
    //Metodo que pega as ID dos Edittext
    private void inicializaCampos() {
        campoNome = findViewById(R.id.edittext_name);
        campoAltura = findViewById(R.id.edittext_altura);
        campoNascimento = findViewById(R.id.edittext_nascimento);
    }
    //Metodo que preenche os campos(variaveis) dos personagens
    private void preenchePersonagem(){
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();

        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);

    }
}