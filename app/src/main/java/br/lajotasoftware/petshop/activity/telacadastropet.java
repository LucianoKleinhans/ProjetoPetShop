package br.lajotasoftware.petshop.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.lajotasoftware.petshop.R;
import br.lajotasoftware.petshop.classes.Dono;
import br.lajotasoftware.petshop.classes.Pets;

public class telacadastropet extends AppCompatActivity {

    DatabaseReference databaseReference;
<<<<<<< Updated upstream
=======
    private final int CAMERA = 1;
    private final int PETSHOP=2;
    private ImageView fotoPet;
>>>>>>> Stashed changes
    private EditText nomePet;
    private EditText especiePet;
    private EditText racaPet;
    private EditText dataNascimentoPet;
    private EditText observacoes;
    private Pets pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telacadastropet);
<<<<<<< Updated upstream
        nomePet=findViewById(R.id.TextNomePetCad);
        especiePet=findViewById(R.id.TextEspeciePetCad);
        racaPet=findViewById(R.id.TextRacaPetCad);
        dataNascimentoPet=findViewById(R.id.EditDataNascPetCad);
        observacoes=findViewById(R.id.TextObsPetCad);
=======
        nomePet = findViewById(R.id.TextNomePetCad);
        especiePet = findViewById(R.id.TextEspeciePetCad);
        racaPet = findViewById(R.id.TextRacaPetCad);
        observacoes = findViewById(R.id.TextObsPetCad);
        fotoPet = findViewById(R.id.FotoPet);
>>>>>>> Stashed changes
        Intent i = getIntent();
        pets = (Pets) i.getSerializableExtra("Pets");
    }

    Bitmap imagem;

    public void bt_finish_telacadastropet(View view) {
        finish();
        //Intent it = new Intent(this, telacadastroservicos.class);
        //startActivity(it);
    }

<<<<<<< Updated upstream
    public void bt_slvcadastro_telacadastropet_to_telacadastroservicos(View view){
=======
    public void bt_slvcadastro_telacadastropet_to_telacadastroservicos(View view) {
        novo();
    }

    private void novo() {
>>>>>>> Stashed changes
        pets.setNome(nomePet.getText().toString());
        pets.setEspecie(especiePet.getText().toString());
        pets.setRaca(racaPet.getText().toString());
        pets.setDatanascimento(dataNascimentoPet.getText().toString());
        pets.setObservacao(observacoes.getText().toString());
<<<<<<< Updated upstream

        finish();
=======
        pets.setFoto(imagem);
        Intent it = new Intent();
        it.putExtra("Pets", pets);
        setResult(RESULT_OK, it);
        onBackPressed();
>>>>>>> Stashed changes
    }

    public void ChamaCamera(View view) {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, CAMERA);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Captura cancelada", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == CAMERA && resultCode == RESULT_OK) {
            Toast.makeText(this, "Captura confirmada", Toast.LENGTH_SHORT).show();
            Bundle dados = data.getExtras();
            imagem = (Bitmap) dados.get("data");
            fotoPet.setImageBitmap(imagem);
        }
    }
}
