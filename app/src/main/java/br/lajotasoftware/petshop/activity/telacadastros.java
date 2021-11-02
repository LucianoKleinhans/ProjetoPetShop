package br.lajotasoftware.petshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import br.lajotasoftware.petshop.R;

public class telacadastros extends AppCompatActivity{

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telacadastros);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    public void bt_cadastros_to_telamain (View view){
        finish();
        //Intent it = new Intent(this, telamain.class);
        //startActivity(it);
    }

    public void bt_cadastros_to_telacadastrodono (View view){
        Intent it = new Intent(this, telacadastrodono.class);
        startActivity(it);
    }
}
