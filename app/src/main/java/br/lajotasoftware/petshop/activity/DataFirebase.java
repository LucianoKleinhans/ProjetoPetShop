package br.lajotasoftware.petshop.activity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import br.lajotasoftware.petshop.classes.Dono;
/*import br.lajotasoftware.petshop.classes.IDs;*/
import br.lajotasoftware.petshop.classes.Pets;
import br.lajotasoftware.petshop.classes.Servico;

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
        databaseReference.child("Dono").child(dono.getId().toString()).child("nome").setValue(dono.getNome());
        databaseReference.child("Dono").child(dono.getId().toString()).child("CPF").setValue(dono.getCPF());
        databaseReference.child("Dono").child(dono.getId().toString()).child("endereco").setValue(dono.getEndereco());
        databaseReference.child("Dono").child(dono.getId().toString()).child("telefone").setValue(dono.getTelefone());
        //databaseReference.child("Dono").child(dono.getId().toString()).child("pets").child(pets.getId().toString()).setValue(pets.getNome());
    }

    public void remover(Dono dono){
        databaseReference.child("Dono").child(dono.getId()+"").removeValue();
    }

<<<<<<< Updated upstream
/*    public static void salvarServ(Servico servico){

=======
    /*public static void salvarServ(Servico servico){
>>>>>>> Stashed changes
        if(databaseReference==null)
            inicio();
        databaseReference.child("Servico").push();
        databaseReference.child("Servico/").("/nome").setValue(servico.getNome());
        databaseReference.child("Servico").child(servico.getId().toString()).child("preco").setValue(servico.getPreco());
    }*/

    public static void writeNewUser(Servico servico) {
        databaseReference.child("Servico").child(servico.getId()).child("nome").setValue(servico.getNome());
        databaseReference.child("Servico").child(servico.getId()).child("preco").setValue(servico.getPreco());
    }


    public void removerServ(Servico servico){
<<<<<<< Updated upstream
       // databaseReference.child("Servico").child(servico.getId()+"").removeValue();
    }

/*
    public static void salvarID(IDs ids){
        databaseReference.child("IDs").child("IDServico").setValue(ids);
    }
*/
=======
        databaseReference.child("Servico").child(servico.getId()+"").removeValue();
    }*/
>>>>>>> Stashed changes

}
