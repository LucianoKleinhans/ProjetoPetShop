package br.lajotasoftware.petshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.lajotasoftware.petshop.R;

public class telacadastropet extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telacadastropet);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    public void bt_cancelar_telacadastropet_to_telacadastroservicos(View view){
        Intent it = new Intent(this, telacadastroservicos.class);
        startActivity(it);
    }

    public void bt_slvcadastro_telacadastropet_to_telacadastroservicos(View view){
        Intent it = new Intent(this, telacadastroservicos.class);
        startActivity(it);
    }

}
