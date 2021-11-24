package br.lajotasoftware.petshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;

import br.lajotasoftware.petshop.R;
import br.lajotasoftware.petshop.classes.Dono;
import br.lajotasoftware.petshop.classes.Servico;

public class telaadicionaservico extends AppCompatActivity {

    DatabaseReference databaseReference;

    private EditText nomeServ;
    private EditText precoServ;
    private Servico servico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaadicionaservico);
        Intent i = getIntent();
        nomeServ=findViewById(R.id.textnomeservP);
        precoServ=findViewById(R.id.textprecoservP);
        servico= (Servico) i.getSerializableExtra("Servico");
        if (nomeServ!=null&&precoServ!=null){
            nomeServ.setText(servico.getNome());
            precoServ.setText(servico.getPreco());
        }
    }

    public void bt_finish_telaadicionaservico (View view){
        finish();
    }

    public void bt_concluir_telaadicionaservico_to_telaselecionaservico (View view){
        servico.setNome(nomeServ.getText().toString());
        servico.setPreco(precoServ.getText().toString());
        Servico.salvar(servico);
        finish();
    }
}
