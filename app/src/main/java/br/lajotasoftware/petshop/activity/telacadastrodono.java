package br.lajotasoftware.petshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.lajotasoftware.petshop.R;

public class telacadastrodono extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telacadastrodono);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    public void bt_adicionarpets_telacadastrodono_to_telacadastropet (View view){
        Intent it = new Intent(this, telacadastropet.class);
        startActivity(it);
    }

    public void bt_cancelar_telacadastrodono_to_telacadastros (View view){
        Intent it = new Intent(this, telacadastros.class);
        startActivity(it);
    }

    public void bt_slvcadastro_telacadastrodono_to_telacadastros (View view){
        Intent it = new Intent(this, telacadastros.class);
        startActivity(it);
    }
}
