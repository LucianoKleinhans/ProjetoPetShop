package br.lajotasoftware.petshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.lajotasoftware.petshop.R;

public class telaselecionaservico extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaselecionaservico);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    public void bt_cadservico_telaselecionaservico_to_telaadicionaservico (View view){
        Intent it = new Intent(this, telaadicionaservico.class);
        startActivity(it);
    }

    public void bt_confirmar_telaselecionaservico_to_telacadastroservicos (View view){
        Intent it = new Intent(this, telacadastroservicos.class);
        startActivity(it);
    }
}
