package br.lajotasoftware.petshop.activity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import br.lajotasoftware.petshop.classes.Dono;
import br.lajotasoftware.petshop.classes.Pets;

public class DataFirebase {
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

    public static void salvar(Dono dono, Pets pets){
        if(databaseReference==null)
            inicio();
        databaseReference.child("Dono").child(dono.getId().toString()).child("nome").setValue(dono.getNome());
        databaseReference.child("Dono").child(dono.getId().toString()).child("CPF").setValue(dono.getCPF());
        databaseReference.child("Dono").child(dono.getId().toString()).child("endereco").setValue(dono.getEndereco());
        databaseReference.child("Dono").child(dono.getId().toString()).child("telefone").setValue(dono.getTelefone());
        databaseReference.child("Dono").child(dono.getId().toString()).child("pets").child(pets.getId().toString()).setValue(pets.getNome());
    }

    public void remover(Dono dono){
        databaseReference.child("Dono").child(dono.getId()+"").removeValue();
    }

}
