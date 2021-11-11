package br.lajotasoftware.petshop.classes;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import androidx.annotation.NonNull;

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
        if (s.id==null){
            if(databaseReference==null){
                inicio();
                List<Servico> servicos = new ArrayList();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DataSnapshot dataSnapshot = snapshot.child("Servico");
                        servicos.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Servico servico = postSnapshot.getValue(Servico.class);
                            servicos.add(servico);
                        }
                        String id = (Integer.parseInt(servicos.get(servicos.size() - 1).getId()) + 1) + "";
                        databaseReference.child("Servico").child(id).child("id").setValue(id);
                        databaseReference.child("Servico").child(id).child("nome").setValue(s.getNome());
                        databaseReference.child("Servico").child(id).child("preco").setValue(s.getPreco());
                        databaseReference=null;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }else{
            if(databaseReference==null){
                inicio();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String id = s.id;
                        databaseReference.child("Servico").child(id).child("id").setValue(id);
                        databaseReference.child("Servico").child(id).child("nome").setValue(s.getNome());
                        databaseReference.child("Servico").child(id).child("preco").setValue(s.getPreco());
                        databaseReference=null;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
    }

    public static void excluir(Servico s){
        if(databaseReference==null){
            inicio();
            List<Servico> servicos = new ArrayList();
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DataSnapshot dataSnapshot = snapshot.child("Servico");
                    servicos.clear();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Servico servico = postSnapshot.getValue(Servico.class);
                        servicos.add(servico);
                    }
                    String id = (Integer.parseInt(servicos.get(servicos.size() - 1).getId()) + 1) + "";
                    databaseReference.child("Servico").child(id).removeValue();
                    databaseReference=null;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        //databaseReference.child("Servico").child(s.getId()+"").removeValue();
    }
    public static void editar(Servico s) {
        databaseReference.child("Servico").child(s.getId().toString()).child("nome").setValue(s.getNome());
        databaseReference.child("Servico").child(s.getId().toString()).child("preco").setValue(s.getPreco());

       // databaseReference.child("Servico").child("2").child("pet").child("id")setValue(s.getPreco());
    }
}