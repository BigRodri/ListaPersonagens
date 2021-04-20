package com.example.listapersonagens.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaDePersonagemActivity extends AppCompatActivity {
    //Seta o titulo da Appbar
    public static final String TITULO_APPBAR = "Lista de Personagens";
    //Pega as informações da classe PersonagemDAO
    private final PersonagemDAO dao = new PersonagemDAO();
    //Faz o adapter ser reconhecido em todos os metodos
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR);
        configuraFabNovoPersonagem();
        configuraLista();
    }
    //Botão para abrir o formulario de criação de personagem
    private void configuraFabNovoPersonagem() {
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_novo_personagem);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioSalva();
            }
        });
    }
    //Volta pro formulario com as informações setadas
    private void abreFormularioSalva() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

    @Override
    //Não apaga o que está na lista de personagens clicando no botão voltar
    protected void onResume() {
        super.onResume();
        atualizaAdapter();

    }
    //Limpa a lista e recarrega a mesma com todas as informações
    private void atualizaAdapter() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }
    //Metodo criado para remover os personagens
    private void remove (Personagem personagem){
        dao.remove(personagem);
        adapter.remove(personagem);
    }

    @Override
    //Cria o pop up de remover personagem na lista
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_personagens_menu, menu);
    }

    @Override
    //Retorna com a ação do pop up
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        configuraMenu(item);
        return super.onContextItemSelected(item);
    }
    //Ele cria uma pergunta de segurança para verificar se o usuario realmente deseja apagar o personagem
    private void configuraMenu(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_personagem_menu_remover) {
            new AlertDialog.Builder(this)
                    .setTitle("O Personagem sera removido")
                    .setMessage("Tem certeza que deseja remover?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
                            remove(personagemEscolhido);
                        }
                    })
                    .setNegativeButton("Não", null)
                    .show();


        }
    }

    private void configuraLista() {
        //pega o ID da lista de personagens
        ListView ListaDePersonagens = findViewById(R.id.lista_personagem);
        configuraAdapter(ListaDePersonagens);
        configuraItemPorClick(ListaDePersonagens);
        registerForContextMenu(ListaDePersonagens);
    }
    //Função para selecionar algum item da lista
    private void configuraItemPorClick(ListView listaDePersonagens) {

        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditar(personagemEscolhido);

            }
        });
    }
    //Abre o formulario no modo editar e não no criar novo personagem
    private void abreFormularioModoEditar(Personagem personagem) {
        Intent vaiParaFormulario = new Intent(this, FormularioPersonagemActivity.class);
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagem);
        startActivity(vaiParaFormulario);
    }
    //Pega informação e joga na lista
    private void configuraAdapter(ListView listaDePersonagens) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagens.setAdapter(adapter);
    }
}
