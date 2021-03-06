package br.lajotasoftware.petshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.lajotasoftware.petshop.R;
import br.lajotasoftware.petshop.classes.Dono;
import br.lajotasoftware.petshop.classes.Pets;

public class telacadastropet extends AppCompatActivity {

    DatabaseReference databaseReference;

    private EditText nomePet;
    private EditText especiePet;
    private EditText racaPet;
    private EditText observacoes;
    private Pets pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telacadastropet);
        nomePet=findViewById(R.id.TextNomePetCad);
        especiePet=findViewById(R.id.TextEspeciePetCad);
        racaPet=findViewById(R.id.TextRacaPetCad);
        observacoes=findViewById(R.id.TextObsPetCad);
        Intent i = getIntent();
        pets= (Pets) i.getSerializableExtra("Pets");
    }

    public void bt_finish_telacadastropet(View view){
        finish();
    }

    public void bt_slvcadastro_telacadastropet_to_telacadastroservicos(View view){
        novo();
    }

    private void novo() {
        pets.setNome(nomePet.getText().toString());
        pets.setEspecie(especiePet.getText().toString());
        pets.setRaca(racaPet.getText().toString());
        pets.setObservacao(observacoes.getText().toString());
        Intent it = new Intent();
        it.putExtra("Pets",pets);
        setResult(RESULT_OK,it);
        onBackPressed();
    }

}
