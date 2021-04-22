package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_personagem_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

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

        //Cria uma mascara para separar as informações de acordo com o campo Altura, o separando por virgula
        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN");
        MaskTextWatcher mtwAltura = new MaskTextWatcher(campoAltura, smfAltura);
        campoAltura.addTextChangedListener(mtwAltura);

        ////Cria uma mascara para separar as informações de acordo com o campo Altura, o separando-as por barras
        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(campoNascimento, smfNascimento);
        campoNascimento.addTextChangedListener(mtwNascimento);
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