package br.lajotasoftware.petshop.classes;

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
   static List<Pets> pets;

    public Dono(String id) {
        this.id = id;
    }

    public Dono() {
    }

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
        return pets;
    }
    public void setPets(Pets pet) {
        if(pets==null)
            pets= new LinkedList<>();
        pets.add(pet);
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    @Override
    public String toString() {
        return id+ " - " +nome;
    }

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    private static void inicio(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }
    public static DatabaseReference getDatabaseReference() {
        if(databaseReference==null)
            inicio();
        return databaseReference;
    }
    public static void salvar(Dono d){
        if(databaseReference==null){
            inicio();
            List<Dono> donos = new ArrayList();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DataSnapshot dataSnapshot = snapshot.child("Donos");
                    donos.clear();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Dono dono = postSnapshot.getValue(Dono.class);
                        donos.add(dono);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            //String id = databaseReference.child("Dono").push().getKey();
            String id = donos.get(donos.size()-1).getId();
            databaseReference.child("Dono").child(id).child("nome").setValue(d.getNome());
            databaseReference.child("Dono").child(id).child("endereco").setValue(d.getEndereco());
            databaseReference.child("Dono").child(id).child("CPF").setValue(d.getCPF());
            databaseReference.child("Dono").child(id).child("telefone").setValue(d.getTelefone());
            for (int i = 0; i <pets.size() ; i++) {
                databaseReference.child("Dono").child(id).child("Pets").child(i+"").setValue(pets.get(i).getNome());
                databaseReference.child("Dono").child(id).child("Pets").child(i+"").setValue(pets.get(i).getEspecie());
                databaseReference.child("Dono").child(id).child("Pets").child(i+"").setValue(pets.get(i).getRaca());
                databaseReference.child("Dono").child(id).child("Pets").child(i+"").setValue(pets.get(i).getDatanascimento());
                databaseReference.child("Dono").child(id).child("Pets").child(i+"").setValue(pets.get(i).getObservacao());
            }


        }
    }

    /*public static void excluir(Servico s){
        databaseReference.child("Servico").child(s.getId()+"").removeValue();
    }
    public static void editar(Servico s) {
        databaseReference.child("Servico").child(s.getId().toString()).child("nome").setValue(s.getNome());
        databaseReference.child("Servico").child(s.getId().toString()).child("preco").setValue(s.getPreco());
    }*/
}
