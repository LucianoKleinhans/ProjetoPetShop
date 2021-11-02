package br.lajotasoftware.petshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.lajotasoftware.petshop.R;

public class telacadastroservicos extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telacadastroservicos);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    public void bt_selecionardono_telacadastroservicos_to_telacadastros (View view){
        Intent it = new Intent(this, telacadastros.class);
        startActivity(it);
    }

    public void bt_addpet_telacadastroservicos_to_telacadastropet (View view){
        Intent it = new Intent(this, telacadastropet.class);
        startActivity(it);
    }

    public void bt_selecionarservico_telacadastroservicos_to_telaselecionaservico (View view){
        Intent it = new Intent(this, telaselecionaservico.class);
        startActivity(it);
    }

    public void bt_finish_telacadastroservicos (View view){
        finish();
        //Intent it = new Intent(this, telaservicos.class);
        //startActivity(it);
    }

    public void bt_concluir_telacadastroservicos_to_telaservicos (View view){
        Intent it = new Intent(this, telaservicos.class);
        startActivity(it);
    }
}
