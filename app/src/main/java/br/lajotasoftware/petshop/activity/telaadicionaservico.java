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
//        if (servico == null){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.telaadicionaservico);
            nomeServ=findViewById(R.id.textnomeservP);
            precoServ=findViewById(R.id.textprecoservP);
            Intent i = getIntent();
            servico= (Servico) i.getSerializableExtra("Servico");
//        }else{
//            nomeServ.setText((CharSequence) databaseReference.child("Servico").child(servico.getId()).child("nome"));
//        }

        //listar();
    }

    public void bt_finish_telaadicionaservico (View view){
        finish();
        //Intent it = new Intent(this, telaselecionaservico.class);
        //startActivity(it);
    }

    public void bt_concluir_telaadicionaservico_to_telaselecionaservico (View view){
        servico.setNome(nomeServ.getText().toString());
        servico.setPreco(precoServ.getText().toString());
        Servico.salvar(servico);
        finish();
    }
}
