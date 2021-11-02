package br.lajotasoftware.petshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import br.lajotasoftware.petshop.R;

public class telaservicos extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaservicos);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    public void bt_servico_to_telamain (View view){
        finish();
    //    Intent it = new Intent(this, telamain.class);
    //    startActivity(it);
    }

    public void bt_servico_to_telacadastroservico (View view){
        Intent it = new Intent(this, telacadastroservicos.class);
        startActivity(it);
    }
}