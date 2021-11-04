package br.lajotasoftware.petshop.activity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.lajotasoftware.petshop.classes.Dono;

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

    public static void salvar(Dono dono){
        if(databaseReference==null)
            inicio();
        databaseReference.child("Dono").child(
                dono.getId().toString()
        ).child("Nome").setValue(dono.getNome());
    }

    public void remover(Dono dono){
        databaseReference.child("Dono").child(dono.getId()+"").removeValue();
    }

}
