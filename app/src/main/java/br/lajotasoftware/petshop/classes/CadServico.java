package br.lajotasoftware.petshop.classes;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadServico implements Serializable {
    private Integer id;
    private Integer iddono;
    private String nomedono;
    private Integer idpet;
    private String nomepet;
    private Servico servico;
    private Float subtotal;
    private Float desconto;
    private Float total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIddono() {
        return iddono;
    }

    public void setIddono(Integer iddono) {
        this.iddono = iddono;
    }

    public String getNomedono() {
        return nomedono;
    }

    public void setNomedono(String nomedono) {
        this.nomedono = nomedono;
    }

    public Integer getIdpet() {
        return idpet;
    }

    public void setIdpet(Integer idpet) {
        this.idpet = idpet;
    }

    public String getNomepet() {
        return nomepet;
    }

    public void setNomepet(String nomepet) {
        this.nomepet = nomepet;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getDesconto() {
        return desconto;
    }

    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
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
   /* public static void salvar(Dono d) {
        if (d.id == null) {
            if (databaseReference == null) {
                inicio();
                List<Dono> donos = new ArrayList();
                List<Servico> servicos = new ArrayList();
                List<Pets> pets = new ArrayList();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DataSnapshot dataSnapshot = snapshot.child("Dono");
                        donos.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Dono dono = postSnapshot.getValue(Dono.class);
                            donos.add(dono);
                        }
                        String id = (Integer.parseInt(donos.get(donos.size() - 1).getId()) + 1) + "";
                        databaseReference.child("Dono").child(id).child("id").setValue(id);
                        databaseReference.child("Dono").child(id).child("nome").setValue(d.getNome());
                        databaseReference.child("Dono").child(id).child("endereco").setValue(d.getEndereco());
                        databaseReference.child("Dono").child(id).child("CPF").setValue(d.getCPF());
                        databaseReference.child("Dono").child(id).child("telefone").setValue(d.getTelefone());
                        for (int i = 0; i < pets.size(); i++) {
                            //String idpet = i+"";
                            databaseReference.child("Dono").child(id).child("Pets").setValue(d.getPets());
                            //databaseReference.child("Dono").child(id).child("Pets").child(idpet).child("id").setValue(idpet);

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
    }*/
}
