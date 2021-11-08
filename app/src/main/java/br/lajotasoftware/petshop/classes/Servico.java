package br.lajotasoftware.petshop.classes;

<<<<<<< Updated upstream
import com.google.firebase.database.IgnoreExtraProperties;
=======
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
>>>>>>> Stashed changes

import java.io.Serializable;

@IgnoreExtraProperties
public class Servico {

    public String id;
    public String nome;
    public String preco;

    public Servico() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Servico(String nome, String preco) {
        this.nome = nome;
        this.preco = preco;
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
}

/*
public class Servico implements Serializable {
    private Integer id;
    private String nome;
    private String preco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

//    @Override
//    public String toString() {
//        {
//            return nome+ " - "+"R$"+preco;
//        }
//    }

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
            databaseReference.child("Servico").child(s.getId().toString()).child("nome").setValue(s.getNome());
            databaseReference.child("Servico").child(s.getId().toString()).child("preco").setValue(s.getPreco());
        }
    }
<<<<<<< Updated upstream
}*/
=======
    public static void excluir(Servico s){
        databaseReference.child("Servico").child(s.getId()+"").removeValue();
    }
    public static void editar(Servico s) {
        databaseReference.child("Servico").child(s.getId().toString()).child("nome").setValue(s.getNome());
        databaseReference.child("Servico").child(s.getId().toString()).child("preco").setValue(s.getPreco());
    }
}
>>>>>>> Stashed changes
