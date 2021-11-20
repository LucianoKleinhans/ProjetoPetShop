package br.lajotasoftware.petshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.lajotasoftware.petshop.R;
import br.lajotasoftware.petshop.classes.CadServico;
import br.lajotasoftware.petshop.classes.Dono;
import br.lajotasoftware.petshop.classes.Pets;
import br.lajotasoftware.petshop.classes.Servico;

public class telacadastroservicos extends AppCompatActivity {

    DatabaseReference databaseReference;
    private TextView txtNomeDono;
    private TextView txtEnderecoDono;
    private TextView txtTelefoneDono;
    private TextView txtNomePet;
    private TextView txtIdadePet;
    private TextView txtRacaPet;
    private ListView listaServico;
    private Spinner spinner;
    private CadServico cadServico;
    private Dono dono;
    private Servico servico;
    private Pets pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telacadastroservicos);
        databaseReference= DataFirebase.getDatabaseReference();
        Intent i = getIntent();
        txtNomeDono=findViewById(R.id.TextNomeDono);
        txtEnderecoDono=findViewById(R.id.TextEnderecoDono);
        txtTelefoneDono=findViewById(R.id.TextTelefone1Dono);
        txtNomePet=findViewById(R.id.textNomePet2);
        txtIdadePet=findViewById(R.id.textIdadePet2);
        txtRacaPet=findViewById(R.id.textRacaPet2);
        cadServico = (CadServico) i.getSerializableExtra("CadServico");
        spinner= findViewById(R.id.spinner);
        listaServico=findViewById(R.id.ListaServicos);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                Object item = parent.getItemAtPosition(position);
                pets = (Pets) item;
                PreencheCampoPet(pets);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        if(data.getSerializableExtra("Dono")!=null) {
                            dono = (Dono) data.getSerializableExtra("Dono");
                            preencheCampoDono(dono);
                        }
                        if(data.getSerializableExtra("Servico")!=null){
                            servico = (Servico) data.getSerializableExtra("Servico");
                            PreencheListaServico(servico);
                        }
                    }
                }
            });
    private void preencheCampoDono(Dono dono) {
        String IDDono = dono.getId();
        String CPFDono = dono.getCPF();
        txtNomeDono.setText(dono.getNome());
        txtEnderecoDono.setText(dono.getEndereco());
        txtTelefoneDono.setText(dono.getTelefone());
        spinner.setAdapter(new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,dono.getPets()));
    }

    private void PreencheCampoPet(Pets pets) {
        Date currentTime = Calendar.getInstance().getTime();
        String IDPet = pets.getId();
        String especie = pets.getEspecie();
        String datanascimento = pets.getDatanascimento();
        String observacao = pets.getObservacao();
        //Integer idade = androi datanascimento
        txtNomePet.setText(pets.getNome());
        txtIdadePet.setText(pets.getDatanascimento());
        txtRacaPet.setText(pets.getRaca());
    }

    private void PreencheListaServico(Servico servico) {


        //listaServico.setAdapter(new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,servico.getServicos()));
    }

   /* public void bt_addpet_telacadastroservicos_to_telacadastropet (View view){
        Intent it = new Intent(this, telacadastropet.class);
        startActivity(it);
    }*/

    public void bt_selecionardono_telacadastroservicos_to_telacadastros (View view){
        Intent it = new Intent(this, telacadastros.class);
        someActivityResultLauncher.launch(it);
    }

    public void bt_selecionarservico_telacadastroservicos_to_telaselecionaservico (View view){
        Intent it = new Intent(this, telaselecionaservico.class);
        someActivityResultLauncher.launch(it);
    }

    public void bt_finish_telacadastroservicos (View view){
        finish();
        //Intent it = new Intent(this, telaservicos.class);
        //startActivity(it);
    }

    public void bt_concluir_telacadastroservicos_to_telaservicos (View view){
        dono.setNome(txtNomeDono.getText().toString());
        dono.setTelefone(txtTelefoneDono.getText().toString());
        dono.setEndereco(txtEnderecoDono.getText().toString());
        dono.setCPF(dono.getCPF());
       // cadServico.salvar(dono,pet,servico);
        onBackPressed();
    }

    public void setDatabaseReference(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }
}
