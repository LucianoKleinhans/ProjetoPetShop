package br.lajotasoftware.petshop.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

import br.lajotasoftware.petshop.R;
import br.lajotasoftware.petshop.classes.CadServico;
import br.lajotasoftware.petshop.classes.Dono;
import br.lajotasoftware.petshop.classes.Pets;
import br.lajotasoftware.petshop.classes.Servico;

public class telaservicos extends AppCompatActivity {


    DatabaseReference databaseReference;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaservicos);
        databaseReference= DataFirebase.getDatabaseReference();
        listView=findViewById(R.id.ListaServicos);
        cadServicos= new LinkedList<>();
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
                //seleciona(position);
            } });
        adb.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Editar", Toast.LENGTH_LONG).show();
                //editar(position);
                finish();
            } });
        adb.setNeutralButton("Excluir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Servico Excluido", Toast.LENGTH_LONG).show();
                CadServico cs = cadServicos.get(position);
                databaseReference.child("CadServico").child(cs.getId()).removeValue();
                finish();
            } });
        AlertDialog alertDialog = adb.create();
        alertDialog.show();
    }

    ArrayAdapter arrayAdapter;
    private void preenche() {
        if (arrayAdapter == null) {
            arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, cadServicos);
            listView.setAdapter(arrayAdapter);
        } else {
            arrayAdapter.notifyDataSetChanged();
        }
    }
    List<CadServico> cadServicos;
    public void listar(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshot = snapshot.child("CadServico");
                cadServicos.clear();
                for (DataSnapshot cadsSnapshot: dataSnapshot.getChildren()) {
                    CadServico cadServico = new CadServico();
                    cadServico = cadsSnapshot.getValue(CadServico.class);
                    cadServicos.add(cadServico);
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
                        listar();
                    }
                }
            });

    public void bt_finish_telaservicos (View view){
        onBackPressed();
    }

    public void bt_servico_to_telacadastroservico (View view){
        novo();
    }

    private void novo() {
        Intent it1 = new Intent(this, telacadastroservicos.class);
        it1.putExtra("CadServico", new CadServico());
        someActivityResultLauncher.launch(it1);
    }
}