package br.lajotasoftware.petshop.classes;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;

public class Dono implements Serializable {
    private String id;
    private String nome;
    private String telefone;
    private String CPF;
    private String endereco;
    private List<Pets> pets;

    public Dono(String id) {
        this.id = id;
    }

    public Dono() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEndereco() {
        return endereco;
    }

    public List<Pets> getPets() {
        return pets!=null?pets:new LinkedList<>();
    }

    public void setPets(Pets pet) {
        if (pets == null){
            pets = new LinkedList<>();}
        pets.add(pet);
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String toString() {
        return id + " - Nome: " + nome + " - Telefone: " + telefone;
    }

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    private static void inicio() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    public static DatabaseReference getDatabaseReference() {
        if (databaseReference == null)
            inicio();
        return databaseReference;
    }
    public  void salvar(Dono d) {
        if (d.id == null) {
            if (databaseReference == null) {
                inicio();
                List<Dono> donos = new ArrayList();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DataSnapshot dataSnapshot = snapshot.child("Dono");
                        donos.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Dono dono = postSnapshot.getValue(Dono.class);
                            donos.add(dono);
                        }
                        String id;
                        if(donos.size()==0){
                            id = "1";
                        } else {
                            id = (Integer.parseInt(donos.get(donos.size() - 1).getId()) + 1) + "";
                        }
                        databaseReference.child("Dono").child(id).child("id").setValue(id);
                        databaseReference.child("Dono").child(id).child("nome").setValue(d.getNome());
                        databaseReference.child("Dono").child(id).child("endereco").setValue(d.getEndereco());
                        databaseReference.child("Dono").child(id).child("CPF").setValue(d.getCPF());
                        databaseReference.child("Dono").child(id).child("telefone").setValue(d.getTelefone());
                        if (pets!=null) {
                            for (int i = 0; i < pets.size(); i++) {
                                databaseReference.child("Dono").child(id).child("Pets").setValue(d.getPets());
                            }
                        }
                        databaseReference=null;
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        } else {
            if (databaseReference == null) {
                inicio();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String id = d.id;
                        databaseReference.child("Dono").child(id).child("id").setValue(id);
                        databaseReference.child("Dono").child(id).child("nome").setValue(d.getNome());
                        databaseReference.child("Dono").child(id).child("endereco").setValue(d.getEndereco());
                        databaseReference.child("Dono").child(id).child("CPF").setValue(d.getCPF());
                        databaseReference.child("Dono").child(id).child("telefone").setValue(d.getTelefone());
                        int idpet = d.getPets().size()+1;
                        for (int i = idpet; i < pets.size(); i++) {
                            databaseReference.child("Dono").child(id).child("Pets").setValue(d.getPets());
                            databaseReference=null;
                        }
                        databaseReference=null;
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        }
    }
}
