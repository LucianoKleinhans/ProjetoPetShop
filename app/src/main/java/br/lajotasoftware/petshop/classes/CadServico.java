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
    private String IDDono;
    private String NomeDono;
    private String TelefoneDono;
    private String EnderecoDono;
    private String CPFDono;
    private String idpet;
    private String NomePet;
    private String EspeciePet;
    private String RacaPet;
    private String NomeServico1;
    private String NomeServico2;
    private String NomeServico3;
    private String NomeServico4;
    private String PrecoServico1;
    private String PrecoServico2;
    private String PrecoServico3;
    private String PrecoServico4;
    private String Subtotal;
    private String Total;

    public String toString() {
        return NomeDono +" - Pet: "+ NomePet + " - Total: " + Total;
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

    public String getIDDono() {
        return IDDono;
    }

    public void setIDDono(String IDDono) {
        this.IDDono = IDDono;
    }

    public String getNomeDono() {
        return NomeDono;
    }

    public void setNomeDono(String nomeDono) {
        NomeDono = nomeDono;
    }

    public String getTelefoneDono() {
        return TelefoneDono;
    }

    public void setTelefoneDono(String telefoneDono) {
        TelefoneDono = telefoneDono;
    }

    public String getEnderecoDono() {
        return EnderecoDono;
    }

    public void setEnderecoDono(String enderecoDono) {
        EnderecoDono = enderecoDono;
    }

    public String getCPFDono() {
        return CPFDono;
    }

    public void setCPFDono(String CPFDono) {
        this.CPFDono = CPFDono;
    }

    public String getIdpet() {
        return idpet;
    }

    public void setIdpet(String idpet) {
        this.idpet = idpet;
    }

    public String getNomePet() {
        return NomePet;
    }

    public void setNomePet(String nomePet) {
        NomePet = nomePet;
    }

    public String getEspeciePet() {
        return EspeciePet;
    }

    public void setEspeciePet(String especiePet) {
        EspeciePet = especiePet;
    }

    public String getRacaPet() {
        return RacaPet;
    }

    public void setRacaPet(String racaPet) {
        RacaPet = racaPet;
    }

    public String getNomeServico1() {
        return NomeServico1;
    }

    public void setNomeServico1(String nomeServico1) {
        NomeServico1 = nomeServico1;
    }

    public String getNomeServico2() {
        return NomeServico2;
    }

    public void setNomeServico2(String nomeServico2) {
        NomeServico2 = nomeServico2;
    }

    public String getNomeServico3() {
        return NomeServico3;
    }

    public void setNomeServico3(String nomeServico3) {
        NomeServico3 = nomeServico3;
    }

    public String getNomeServico4() {
        return NomeServico4;
    }

    public void setNomeServico4(String nomeServico4) {
        NomeServico4 = nomeServico4;
    }

    public String getPrecoServico1() {
        return PrecoServico1;
    }

    public void setPrecoServico1(String precoServico1) {
        PrecoServico1 = precoServico1;
    }

    public String getPrecoServico2() {
        return PrecoServico2;
    }

    public void setPrecoServico2(String precoServico2) {
        PrecoServico2 = precoServico2;
    }

    public String getPrecoServico3() {
        return PrecoServico3;
    }

    public void setPrecoServico3(String precoServico3) {
        PrecoServico3 = precoServico3;
    }

    public String getPrecoServico4() {
        return PrecoServico4;
    }

    public void setPrecoServico4(String precoServico4) {
        PrecoServico4 = precoServico4;
    }

    public String getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(String subtotal) {
        Subtotal = subtotal;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
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
                        databaseReference.child("CadServico").child(id).child("IDDono").setValue(cs.getIDDono());
                        databaseReference.child("CadServico").child(id).child("NomeDono").setValue(cs.getNomeDono());
                        databaseReference.child("CadServico").child(id).child("TelefoneDono").setValue(cs.getTelefoneDono());
                        databaseReference.child("CadServico").child(id).child("EnderecoDono").setValue(cs.getEnderecoDono());
                        databaseReference.child("CadServico").child(id).child("CPFDono").setValue(cs.getCPFDono());

                        databaseReference.child("CadServico").child(id).child("IDPet").setValue(cs.getIdpet());
                        databaseReference.child("CadServico").child(id).child("NomePet").setValue(cs.getNomePet());
                        databaseReference.child("CadServico").child(id).child("RacaPet").setValue(cs.getRacaPet());
                        databaseReference.child("CadServico").child(id).child("EspeciePet").setValue(cs.getEspeciePet());

                        databaseReference.child("CadServico").child(id).child("NomeServico1").setValue(cs.getNomeServico1());
                        databaseReference.child("CadServico").child(id).child("PrecoServico1").setValue(cs.getPrecoServico1());

                        databaseReference.child("CadServico").child(id).child("NomeServico2").setValue(cs.getNomeServico2());
                        databaseReference.child("CadServico").child(id).child("PrecoServico2").setValue(cs.getPrecoServico2());

                        databaseReference.child("CadServico").child(id).child("NomeServico3").setValue(cs.getNomeServico3());
                        databaseReference.child("CadServico").child(id).child("PrecoServico3").setValue(cs.getPrecoServico3());

                        databaseReference.child("CadServico").child(id).child("NomeServico4").setValue(cs.getNomeServico4());
                        databaseReference.child("CadServico").child(id).child("PrecoServico4").setValue(cs.getPrecoServico4());

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
                        databaseReference.child("CadServico").child(id).child("Dono").child("IDDono").setValue(cs.getIDDono());
                        databaseReference.child("CadServico").child(id).child("Dono").child("NomeDono").setValue(cs.getNomeDono());
                        databaseReference.child("CadServico").child(id).child("Dono").child("TelefoneDono").setValue(cs.getTelefoneDono());
                        databaseReference.child("CadServico").child(id).child("Dono").child("EnderecoDono").setValue(cs.getEnderecoDono());
                        databaseReference.child("CadServico").child(id).child("Dono").child("CPFDono").setValue(cs.getCPFDono());

                        //databaseReference.child("CadServico").child(id).child("Pet").child("IDPet").setValue(cs.getIdpet());
                        databaseReference.child("CadServico").child(id).child("Pet").child("NomePet").setValue(cs.getNomePet());
                        databaseReference.child("CadServico").child(id).child("Pet").child("RacaPet").setValue(cs.getRacaPet());
                        databaseReference.child("CadServico").child(id).child("Pet").child("EspeciePet").setValue(cs.getEspeciePet());

                        databaseReference.child("CadServico").child(id).child("Servico").child("1").child("NomeServico").setValue(cs.getNomeServico1());
                        databaseReference.child("CadServico").child(id).child("Servico").child("1").child("PrecoServico").setValue(cs.getPrecoServico1());

                        databaseReference.child("CadServico").child(id).child("Servico").child("2").child("NomeServico").setValue(cs.getNomeServico2());
                        databaseReference.child("CadServico").child(id).child("Servico").child("2").child("PrecoServico").setValue(cs.getPrecoServico2());

                        databaseReference.child("CadServico").child(id).child("Servico").child("3").child("NomeServico").setValue(cs.getNomeServico3());
                        databaseReference.child("CadServico").child(id).child("Servico").child("3").child("PrecoServico").setValue(cs.getPrecoServico3());

                        databaseReference.child("CadServico").child(id).child("Servico").child("4").child("NomeServico").setValue(cs.getNomeServico4());
                        databaseReference.child("CadServico").child(id).child("Servico").child("4").child("PrecoServico").setValue(cs.getPrecoServico4());

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
