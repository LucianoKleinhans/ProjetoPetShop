package br.lajotasoftware.petshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.lajotasoftware.petshop.R;

public class telaadicionaservico extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaadicionaservico);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    public void bt_voltar_telaadicionaservico_to_telaselecionaservico (View view){
        Intent it = new Intent(this, telaselecionaservico.class);
        startActivity(it);
    }

    public void bt_concluir_telaadicionaservico_to_telaselecionaservico (View view){
        Intent it = new Intent(this, telaselecionaservico.class);
        startActivity(it);
    }
}
