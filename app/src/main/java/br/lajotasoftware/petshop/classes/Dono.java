package br.lajotasoftware.petshop.classes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Dono implements Serializable {
    private Integer id;
    private String nome;
    private String telefone;
    private String CPF;
    private String endereco;

    public Dono(Integer id) {
        this.id = id;
    }

    public Dono() {
    }

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

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    @Override
    public String toString() {
<<<<<<< Updated upstream
        return id+ " - " +nome;
=======
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
                            //databaseReference.child("Dono").child(id).child("Pets").child(i)
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
    /*public static void excluir(Servico s){
        databaseReference.child("Servico").child(s.getId()+"").removeValue();
>>>>>>> Stashed changes
    }
}
