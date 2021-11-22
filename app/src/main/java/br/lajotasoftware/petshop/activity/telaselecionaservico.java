package br.lajotasoftware.petshop.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;

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
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                createDialog(view,position);
                return true;
            }
        });
    }

    public void createDialog(View view,int position){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("O que deseja fazer?");
        adb.setPositiveButton("Selecionar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Selecionou", Toast.LENGTH_LONG).show();
                seleciona(position);
            } });
        adb.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Editar", Toast.LENGTH_LONG).show();
                editar(position);
            } });
        adb.setNeutralButton("Excluir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Servico Excluido", Toast.LENGTH_LONG).show();
                Servico s = servicos.get(position);
                databaseReference.child("Servico").child(s.getId()).removeValue();
            } });
        AlertDialog alertDialog = adb.create();
        alertDialog.show();
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
                    Servico servico = new Servico();
                    servico = postSnapshot.getValue(Servico.class);
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
        novo();
    }

    private void editar(int position) {
        Intent it = new Intent(this, telaadicionaservico.class);
        it.putExtra("Servico",servicos.get(position));
        someActivityResultLauncher.launch(it);
    }

    private void novo() {
        Intent it = new Intent(this, telaadicionaservico.class);
        it.putExtra("Servico",new Servico());
        someActivityResultLauncher.launch(it);
    }

    private void seleciona(int position) {
        Intent it = getIntent();
        it.putExtra("Servico",servicos.get(position));
        setResult(RESULT_OK,it);
        onBackPressed();
    }
}
