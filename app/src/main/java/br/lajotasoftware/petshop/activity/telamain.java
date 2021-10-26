package br.lajotasoftware.petshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import br.lajotasoftware.petshop.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class telamain extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telamain);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    public void bt_main_to_telaservicos(View view){
        Intent it = new Intent(this,telaservicos.class);
        startActivity(it);
    }
}