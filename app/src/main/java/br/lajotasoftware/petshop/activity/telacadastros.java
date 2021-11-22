package br.lajotasoftware.petshop.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class telacadastros extends AppCompatActivity{

    DatabaseReference databaseReference;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telacadastros);

        databaseReference= DataFirebase.getDatabaseReference();
        listView=findViewById(R.id.tabelacadastros);
        donos= new LinkedList<>();
        listar();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                createDialog(view,position);
                return true;
            }
        });
    }

    private void createDialog(View view, int position) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("O que deseja fazer?");
        adb.setPositiveButton("Selecionar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Selecionou", Toast.LENGTH_LONG).show();
                seleciona(position);
            } });
        adb.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getApplicationContext(),"Editar", Toast.LENGTH_LONG).show();
                editar(position);
            } });
        adb.setNeutralButton("Excluir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
              Toast.makeText(getApplicationContext(), "Cadastro Excluido", Toast.LENGTH_LONG).show();
              Dono d = donos.get(position);
              databaseReference.child("Dono").child(d.getId()).removeValue();;
            } });
        AlertDialog alertDialog = adb.create();
        alertDialog.show();
    }

    ArrayAdapter arrayAdapter;
    private void preenche() {
        if (arrayAdapter == null) {
            arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, donos);
            listView.setAdapter(arrayAdapter);
        } else {
            arrayAdapter.notifyDataSetChanged();
        }
    }
    List<Dono> donos;
    public void listar(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("Dono");
                donos.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Dono dono =new Dono();
                            dono=postSnapshot.getValue(Dono.class);

                    DataSnapshot dataSnapshotPet =
                            snapshot.child("Dono").child(dono.getId()+"").child("Pets");

                        for (DataSnapshot postSnapshot2: dataSnapshotPet.getChildren()) {
                            dono.setPets( postSnapshot2.getValue(Pets.class));
                        }


                        donos.add(dono);
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

    public void bt_busca_telacadastros(View view) {
    }

    private void editar(int position) {
        Intent it = new Intent(this,telacadastrodono.class);
        it.putExtra("Dono",donos.get(position));
        someActivityResultLauncher.launch(it);
    }

    private void novo(){
        Intent it = new Intent(this, telacadastrodono.class);
        it.putExtra("Dono",new Dono());
        someActivityResultLauncher.launch(it);
    }

    private void seleciona(int position) {
        Intent it = getIntent();
        it.putExtra("Dono",donos.get(position));
        setResult(RESULT_OK,it);

        onBackPressed();

    }

    public void bt_finish_telacadastros (View view){
        finish();
    }

    public void bt_cadastros_to_telacadastrodono (View view){
        novo();
    }
}
