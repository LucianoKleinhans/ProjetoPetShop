package br.lajotasoftware.petshop.classes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Servico implements Serializable {
    private String id;
    private String nome;
    private String preco;

    public Servico(String id) {
        this.id = id;
    }

    public Servico() {
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

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String toString() {
        return  nome + " -" + preco;
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
    public static void salvar(Servico s){
        if(databaseReference==null){
            inicio();
            String id = databaseReference.child("Servico").push().getKey();
            databaseReference.child("Servico").child(id).child("nome").setValue(s.getNome());
            databaseReference.child("Servico").child(id).child("preco").setValue(s.getPreco());
        }
    }

    public static void excluir(Servico s){
        databaseReference.child("Servico").child(s.getId()+"").removeValue();
    }
    public static void editar(Servico s) {
        databaseReference.child("Servico").child(s.getId().toString()).child("nome").setValue(s.getNome());
        databaseReference.child("Servico").child(s.getId().toString()).child("preco").setValue(s.getPreco());
    }
}