package br.lajotasoftware.petshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

import br.lajotasoftware.petshop.R;
import br.lajotasoftware.petshop.classes.Dono;
import br.lajotasoftware.petshop.classes.Pets;
import br.lajotasoftware.petshop.classes.Servico;

public class telacadastrodono extends AppCompatActivity {

    DatabaseReference databaseReference;
    private ListView listView;

    private EditText nomeDono;
    private EditText telefoneDono;
    private EditText enderecoDono;
    private EditText CPFDono;
    private ListView listaPets;
    private Dono dono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telacadastrodono);
        nomeDono=findViewById(R.id.TextNomeDonoCad);
        telefoneDono=findViewById(R.id.textTelDonoCad);
        enderecoDono=findViewById(R.id.textEndDonoCad);
        CPFDono=findViewById(R.id.textCPFDonoCad);
        listaPets= findViewById(R.id.ListPetsCadDono);
        Intent i = getIntent();
        dono= (Dono) i.getSerializableExtra("Dono");
        if (nomeDono!=null&&telefoneDono!=null&&enderecoDono!=null&&CPFDono!=null){
            nomeDono.setText(dono.getNome());
            telefoneDono.setText(dono.getTelefone());
            enderecoDono.setText(dono.getEndereco());
            CPFDono.setText(dono.getCPF());
            listaPets.setAdapter(new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,dono.getPets()));
        }
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        //doSomeOperations();
                    }
                }
            });

    public void bt_adicionarpets_telacadastrodono_to_telacadastropet (View view){
        Intent it = new Intent(this, telacadastropet.class);
        //it.putExtra("Pets",new Pets(pets.size()+1));
        it.putExtra("Pets",new Pets());
        startActivityForResult(it,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(data!=null){
                Pets pets= (Pets) data.getSerializableExtra("Pets");
                dono.setPets(pets);
            }
        }
    }

    public void bt_finish_telacadastrodono (View view){
        finish();
        //Intent it = new Intent(this, telacadastros.class);
        //startActivity(it);
    }

    public void bt_slvcadastro_telacadastrodono_to_telacadastros (View view){
        dono.setNome(nomeDono.getText().toString());
        dono.setTelefone(telefoneDono.getText().toString());
        dono.setEndereco(enderecoDono.getText().toString());
        dono.setCPF(CPFDono.getText().toString());
        dono.salvar(dono);
        finish();
    }
}
