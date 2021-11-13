package br.lajotasoftware.petshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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
    private ListView listView;
    private CadServico cadServico;
    private Dono dono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telacadastroservicos);
        databaseReference= DataFirebase.getDatabaseReference();
        Intent i = getIntent();
        listView=findViewById(R.id.ListaServicos);
        txtNomeDono=findViewById(R.id.TextNomeDono);
        txtEnderecoDono=findViewById(R.id.TextEnderecoDono);
        txtTelefoneDono=findViewById(R.id.TextTelefone);
        cadServico = (CadServico) i.getSerializableExtra("CadServico");
        /*    txtNomeDono.setText(dono.getNome());
            txtEnderecoDono.setText(dono.getEndereco());
            txtTelefoneDono.setText(dono.getTelefone());*/

    }

    public void bt_selecionardono_telacadastroservicos_to_telacadastros (View view){
        Intent it = new Intent(this, telacadastros.class);
        it.putExtra("Dono",Dono.class);
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

    public void setDatabaseReference(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }
}
