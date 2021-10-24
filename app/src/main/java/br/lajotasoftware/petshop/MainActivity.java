package br.lajotasoftware.petshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import br.lajotasoftware.petshop.classes.Pessoa;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telamain);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }
    List<Pessoa> pessoas;
    public void listar(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pessoas = (List) snapshot.child("Pessoa").getValue(List.class);
                for (Object o:pessoas){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void listarSomenteUm(){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Pessoa pessoa = (Pessoa) snapshot.child("Pessoa").child("1").getValue(Pessoa.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void salvar(Pessoa pessoa){

        databaseReference.child("Pessoa").child(
                pessoa.getId()>0?pessoa.getId().toString():maxID()
        ).child("nome").setValue(pessoa.getNome());
    }
    public void remover(Pessoa pessoa){

        databaseReference.child("Pessoa").child(pessoa.getId()+"").removeValue();
    }
    public String maxID(){
        return (pessoas.get(pessoas.size()-1).getId()+1)+"";
    }
}