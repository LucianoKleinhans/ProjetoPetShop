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
    private String id;
    private String iddono;
    private String nomedono;
    private String telefonedono;
    private String enderecodono;
    private String cpfdono;
    private String idpet;
    private String nomepet;
    private String especiepet;
    private String raca;
    private String servico1;
    private String servico2;
    private String servico3;
    private String servico4;
    private String precoServico1;
    private String precoServico2;
    private String precoServico3;
    private String precoServico4;
    private String subtotal;
    private String total;

    @Override
    public String toString() {
        return "CadServico{" +
                "id='" + id + '\'' +
                ", iddono='" + iddono + '\'' +
                ", nomedono='" + nomedono + '\'' +
                ", telefonedono='" + telefonedono + '\'' +
                ", enderecodono='" + enderecodono + '\'' +
                ", cpfdono='" + cpfdono + '\'' +
                ", idpet='" + idpet + '\'' +
                ", nomepet='" + nomepet + '\'' +
                ", especiepet='" + especiepet + '\'' +
                ", raca='" + raca + '\'' +
                ", servico1='" + servico1 + '\'' +
                ", servico2='" + servico2 + '\'' +
                ", servico3='" + servico3 + '\'' +
                ", servico4='" + servico4 + '\'' +
                ", precoServico1='" + precoServico1 + '\'' +
                ", precoServico2='" + precoServico2 + '\'' +
                ", precoServico3='" + precoServico3 + '\'' +
                ", precoServico4='" + precoServico4 + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", total='" + total + '\'' +
                '}';
    }

    public CadServico(String id) {
        this.id = id;
    }

    public CadServico() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIddono() {
        return iddono;
    }

    public void setIddono(String iddono) {
        this.iddono = iddono;
    }

    public String getNomedono() {
        return nomedono;
    }

    public void setNomedono(String nomedono) {
        this.nomedono = nomedono;
    }

    public String getTelefonedono() {
        return telefonedono;
    }

    public void setTelefonedono(String telefonedono) {
        this.telefonedono = telefonedono;
    }

    public String getEnderecodono() {
        return enderecodono;
    }

    public void setEnderecodono(String enderecodono) {
        this.enderecodono = enderecodono;
    }

    public String getCpfdono() {
        return cpfdono;
    }

    public void setCpfdono(String cpfdono) {
        this.cpfdono = cpfdono;
    }

    public String getIdpet() {
        return idpet;
    }

    public void setIdpet(String idpet) {
        this.idpet = idpet;
    }

    public String getNomepet() {
        return nomepet;
    }

    public void setNomepet(String nomepet) {
        this.nomepet = nomepet;
    }

    public String getEspeciepet() {
        return especiepet;
    }

    public void setEspeciepet(String especiepet) {
        this.especiepet = especiepet;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getServico1() {
        return servico1;
    }

    public void setServico1(String servico1) {
        this.servico1 = servico1;
    }

    public String getServico2() {
        return servico2;
    }

    public void setServico2(String servico2) {
        this.servico2 = servico2;
    }

    public String getServico3() {
        return servico3;
    }

    public void setServico3(String servico3) {
        this.servico3 = servico3;
    }

    public String getServico4() {
        return servico4;
    }

    public void setServico4(String servico4) {
        this.servico4 = servico4;
    }

    public String getPrecoServico1() {
        return precoServico1;
    }

    public void setPrecoServico1(String precoServico1) {
        this.precoServico1 = precoServico1;
    }

    public String getPrecoServico2() {
        return precoServico2;
    }

    public void setPrecoServico2(String precoServico2) {
        this.precoServico2 = precoServico2;
    }

    public String getPrecoServico3() {
        return precoServico3;
    }

    public void setPrecoServico3(String precoServico3) {
        this.precoServico3 = precoServico3;
    }

    public String getPrecoServico4() {
        return precoServico4;
    }

    public void setPrecoServico4(String precoServico4) {
        this.precoServico4 = precoServico4;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public static FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public static void setFirebaseDatabase(FirebaseDatabase firebaseDatabase) {
        CadServico.firebaseDatabase = firebaseDatabase;
    }

    public static void setDatabaseReference(DatabaseReference databaseReference) {
        CadServico.databaseReference = databaseReference;
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

    public void salvar(CadServico cs){
        if (cs.id == null){
            if (databaseReference == null){
                inicio();
                List<CadServico> cadServicos = new ArrayList();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DataSnapshot dataSnapshot = snapshot.child("CadServico");
                        cadServicos.clear();
                        for (DataSnapshot cadsSnapshot : dataSnapshot.getChildren()){
                            CadServico cadServico = cadsSnapshot.getValue(CadServico.class);
                            cadServicos.add(cadServico);
                        }
                        String id = (Integer.parseInt(cadServicos.get(cadServicos.size() - 1).getId()) + 1) + "";
                        databaseReference.child("CadServico").child(id).child("id").setValue(id);
                        databaseReference.child("CadServico").child(id).child("Dono").child("IDDono").setValue(cs.getIddono());
                        databaseReference.child("CadServico").child(id).child("Dono").child("NomeDono").setValue(cs.getNomedono());
                        databaseReference.child("CadServico").child(id).child("Dono").child("TelefoneDono").setValue(cs.getTelefonedono());
                        databaseReference.child("CadServico").child(id).child("Dono").child("EnderecoDono").setValue(cs.getEnderecodono());
                        databaseReference.child("CadServico").child(id).child("Dono").child("CPFDono").setValue(cs.getCpfdono());

                        databaseReference.child("CadServico").child(id).child("Pet").child("IDPet").setValue(cs.getIdpet());
                        databaseReference.child("CadServico").child(id).child("Pet").child("NomePet").setValue(cs.getNomepet());
                        databaseReference.child("CadServico").child(id).child("Pet").child("RacaPet").setValue(cs.getRaca());
                        databaseReference.child("CadServico").child(id).child("Pet").child("EspeciePet").setValue(cs.getEspeciepet());

                        databaseReference.child("CadServico").child(id).child("Servico").child("1").child("NomeServico").setValue(cs.getServico1());
                        databaseReference.child("CadServico").child(id).child("Servico").child("1").child("NomeServico").setValue(cs.getPrecoServico1());

                        databaseReference.child("CadServico").child(id).child("Servico").child("2").child("NomeServico").setValue(cs.getServico2());
                        databaseReference.child("CadServico").child(id).child("Servico").child("2").child("NomeServico").setValue(cs.getPrecoServico2());

                        databaseReference.child("CadServico").child(id).child("Servico").child("3").child("NomeServico").setValue(cs.getServico3());
                        databaseReference.child("CadServico").child(id).child("Servico").child("3").child("NomeServico").setValue(cs.getPrecoServico3());

                        databaseReference.child("CadServico").child(id).child("Servico").child("4").child("NomeServico").setValue(cs.getServico4());
                        databaseReference.child("CadServico").child(id).child("Servico").child("4").child("NomeServico").setValue(cs.getPrecoServico4());

                        databaseReference.child("CadServico").child(id).child("Subtotal").setValue(cs.getSubtotal());
                        databaseReference.child("CadServico").child(id).child("Total").setValue(cs.getTotal());

                        databaseReference = null;
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        }else {
            if (databaseReference == null){
                inicio();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String id = cs.id;
                        databaseReference.child("CadServico").child(id).child("id").setValue(id);
                        databaseReference.child("CadServico").child(id).child("Dono").child("IDDono").setValue(cs.getIddono());
                        databaseReference.child("CadServico").child(id).child("Dono").child("NomeDono").setValue(cs.getNomedono());
                        databaseReference.child("CadServico").child(id).child("Dono").child("TelefoneDono").setValue(cs.getTelefonedono());
                        databaseReference.child("CadServico").child(id).child("Dono").child("EnderecoDono").setValue(cs.getEnderecodono());
                        databaseReference.child("CadServico").child(id).child("Dono").child("CPFDono").setValue(cs.getCpfdono());

                        databaseReference.child("CadServico").child(id).child("Pet").child("IDPet").setValue(cs.getIdpet());
                        databaseReference.child("CadServico").child(id).child("Pet").child("NomePet").setValue(cs.getNomepet());
                        databaseReference.child("CadServico").child(id).child("Pet").child("RacaPet").setValue(cs.getRaca());
                        databaseReference.child("CadServico").child(id).child("Pet").child("EspeciePet").setValue(cs.getEspeciepet());

                        databaseReference.child("CadServico").child(id).child("Servico").child("1").child("NomeServico").setValue(cs.getServico1());
                        databaseReference.child("CadServico").child(id).child("Servico").child("1").child("NomeServico").setValue(cs.getPrecoServico1());

                        databaseReference.child("CadServico").child(id).child("Servico").child("2").child("NomeServico").setValue(cs.getServico2());
                        databaseReference.child("CadServico").child(id).child("Servico").child("2").child("NomeServico").setValue(cs.getPrecoServico2());

                        databaseReference.child("CadServico").child(id).child("Servico").child("3").child("NomeServico").setValue(cs.getServico3());
                        databaseReference.child("CadServico").child(id).child("Servico").child("3").child("NomeServico").setValue(cs.getPrecoServico3());

                        databaseReference.child("CadServico").child(id).child("Servico").child("4").child("NomeServico").setValue(cs.getServico4());
                        databaseReference.child("CadServico").child(id).child("Servico").child("4").child("NomeServico").setValue(cs.getPrecoServico4());

                        databaseReference.child("CadServico").child(id).child("Subtotal").setValue(cs.getSubtotal());
                        databaseReference.child("CadServico").child(id).child("Total").setValue(cs.getTotal());

                        databaseReference = null;
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        }
    }
}
