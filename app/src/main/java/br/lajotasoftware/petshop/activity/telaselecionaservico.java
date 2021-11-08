package br.lajotasoftware.petshop.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

import br.lajotasoftware.petshop.R;
import br.lajotasoftware.petshop.classes.Dono;

import br.lajotasoftware.petshop.classes.Servico;
import br.lajotasoftware.petshop.activity.DataFirebase;

public class telaselecionaservico extends AppCompatActivity {

    DatabaseReference databaseReference;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaselecionaservico);

        databaseReference= DataFirebase.getDatabaseReference();
        listView=findViewById(R.id.listservicos);
        servicos= new LinkedList<>();
        listar();

       /* listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            *//*public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long relacionassexuales) {
                Servico s = servicos.get(position);
                DatabaseReference currentDatabase = FirebaseDatabase.getInstance().getReference("Servico/"+relacionassexuales);
                currentDatabase.removeValue();
                return true;
            }*//*
        });*/
    }

    ArrayAdapter arrayAdapter;
    private void preenche() {
        if (arrayAdapter == null) {
            arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, servicos);
            listView.setAdapter(arrayAdapter);
        } else {
            arrayAdapter.notifyDataSetChanged();
        }
    }
    List<Servico> servicos;
    public void listar(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("Servico");
                servicos.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Servico servico = postSnapshot.getValue(Servico.class);
                    servicos.add(servico);
                }
                preenche();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                doSomeOperations();
            }
        }
    });

    private void doSomeOperations() {
        listar();
    }

    public void bt_cadservico_telaselecionaservico_to_telaadicionaservico (View view){
<<<<<<< Updated upstream
        novo();
    }
    
/*    ValueEventListener postListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            IDs id = dataSnapshot.getValue(IDs.class);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
//mPostReference.addValueEventListener(postListener);*/
    //IDs ids = new IDs();
    //int ServID = ids.getIDServico();
    private void novo() {
        /*ServID = ServID + 1;
        ids.setIDServico(ServID);
        DataFirebase.salvarID(ids);*/
        Intent it = new Intent(this, telaadicionaservico.class);
        String servID = databaseReference.push().toString();
        it.putExtra("Servico",(servID));
=======
        Intent it = new Intent(this, telaadicionaservico.class);
        it.putExtra("Servico",new Servico());
>>>>>>> Stashed changes
        someActivityResultLauncher.launch(it);
    }

    public void bt_confirmar_telaselecionaservico_to_telacadastroservicos (View view){
        Intent it = new Intent(this, telacadastroservicos.class);
        startActivity(it);
    }
}