package br.lajotasoftware.petshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import br.lajotasoftware.petshop.R;
import br.lajotasoftware.petshop.classes.CadServico;
import br.lajotasoftware.petshop.classes.Dono;
import br.lajotasoftware.petshop.classes.Pets;
import br.lajotasoftware.petshop.classes.Servico;

public class telacadastroservicos extends AppCompatActivity {

    DatabaseReference databaseReference;
    private TextView txtNomeDono;
    private TextView txtEnderecoDono;
    private TextView txtTelefoneDono;
    private TextView txtNomePet;
    private TextView txtEspeciePet;
    private TextView txtRacaPet;
    private TextView txtServicoNome1;
    private TextView txtServicoNome2;
    private TextView txtServicoNome3;
    private TextView txtServicoNome4;
    private TextView txtServicoPreco1;
    private TextView txtServicoPreco2;
    private TextView txtServicoPreco3;
    private TextView txtServicoPreco4;
    private TextView txtValorSubTotal;
    private EditText txtValorTotal;
    private Spinner spinner;
    private CadServico cadServico;
    private Dono dono;
    private Servico servico;
    private Pets pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telacadastroservicos);
        Intent i = getIntent();
        txtNomeDono=findViewById(R.id.TextNomeDono);
        txtEnderecoDono=findViewById(R.id.TextEnderecoDono);
        txtTelefoneDono=findViewById(R.id.TextTelefone1Dono);
        txtNomePet=findViewById(R.id.textNomePet2);
        txtRacaPet=findViewById(R.id.textRacaPet2);
        txtEspeciePet=findViewById(R.id.txtEspeciePet2);
        txtServicoNome1=findViewById(R.id.txtServicoNome1);
        txtServicoNome2=findViewById(R.id.txtServicoNome2);
        txtServicoNome3=findViewById(R.id.txtServicoNome3);
        txtServicoNome4=findViewById(R.id.txtServicoNome4);
        txtServicoPreco1=findViewById(R.id.txtServicoPreco1);
        txtServicoPreco2=findViewById(R.id.txtServicoPreco2);
        txtServicoPreco3=findViewById(R.id.txtServicoPreco3);
        txtServicoPreco4=findViewById(R.id.txtServicoPreco4);
        txtValorSubTotal=findViewById(R.id.TextValorSubTotal);
        txtValorTotal=findViewById(R.id.TextValorTotal);
        cadServico = (CadServico) i.getSerializableExtra("CadServico");
        spinner= findViewById(R.id.spinner);
        if (txtNomeDono!=null&&txtNomePet!=null&&txtValorTotal!=null){
            txtNomeDono.setText(cadServico.getNomeDono());
            txtEnderecoDono.setText(cadServico.getEnderecoDono());
            txtTelefoneDono.setText(cadServico.getTelefoneDono());
            txtNomePet.setText(cadServico.getNomePet());
            txtRacaPet.setText(cadServico.getRacaPet());
            txtEspeciePet.setText(cadServico.getEspeciePet());
            txtServicoNome1.setText(cadServico.getNomeServico1());
            txtServicoNome2.setText(cadServico.getNomeServico2());
            txtServicoNome3.setText(cadServico.getNomeServico3());
            txtServicoNome4.setText(cadServico.getNomeServico4());
            txtServicoPreco1.setText(cadServico.getPrecoServico1());
            txtServicoPreco2.setText(cadServico.getPrecoServico2());
            txtServicoPreco3.setText(cadServico.getPrecoServico3());
            txtServicoPreco4.setText(cadServico.getPrecoServico4());
            txtValorSubTotal.setText(cadServico.getSubtotal());
            txtValorTotal.setText(cadServico.getTotal());

        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                Object item = parent.getItemAtPosition(position);
                pets = (Pets) item;
                PreencheCampoPet(pets);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    String IDDono;
    String CPFDono;
    String IDPet;
    String especie;
    String observacao;
    Float preco1 = Float.valueOf(0);
    Float preco2 = Float.valueOf(0);
    Float preco3 = Float.valueOf(0);
    Float preco4 = Float.valueOf(0);
    Float SubTotal = Float.valueOf(0);
    Float ValorTotal = Float.valueOf(0);
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        if(data.getSerializableExtra("Dono")!=null) {
                            dono = (Dono) data.getSerializableExtra("Dono");
                            preencheCampoDono(dono);
                        }
                        if(data.getSerializableExtra("Servico")!=null){
                            servico = (Servico) data.getSerializableExtra("Servico");
                            PreencheListaServico(servico);
                        }
                    }
                }
            });
    public void preencheCampoDono(Dono dono) {
        IDDono = dono.getId();
        CPFDono = dono.getCPF();
        txtNomeDono.setText(dono.getNome());
        txtEnderecoDono.setText(dono.getEndereco());
        txtTelefoneDono.setText(dono.getTelefone());
        spinner.setAdapter(new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,dono.getPets()));
    }
    private void PreencheCampoPet(Pets pets) {
        //Date currentTime = Calendar.getInstance().getTime();
        IDPet = pets.getId();
        especie = pets.getEspecie();
        observacao = pets.getObservacao();
        txtNomePet.setText(pets.getNome());
        txtRacaPet.setText(pets.getRaca());
        txtEspeciePet.setText(pets.getEspecie());
    }
    private void PreencheListaServico(Servico servico) {
        if(txtServicoNome1.getText().length() == 0){
                txtServicoNome1.setText(servico.getNome());
                txtServicoPreco1.setText(servico.getPreco());
                AtualizaValor();
        }else if (txtServicoNome1.getText().length() > 0 && txtServicoNome2.getText().length() == 0){
            txtServicoNome2.setText(servico.getNome());
            txtServicoPreco2.setText(servico.getPreco());
            AtualizaValor();
        }else if (txtServicoNome2.getText().length() > 0 && txtServicoNome3.getText().length() == 0){
            txtServicoNome3.setText(servico.getNome());
            txtServicoPreco3.setText(servico.getPreco());
            AtualizaValor();
        }else if (txtServicoNome3.getText().length() > 0 && txtServicoNome4.getText().length() == 0){
            txtServicoNome4.setText(servico.getNome());
            txtServicoPreco4.setText(servico.getPreco());
            AtualizaValor();
        }else{
            Toast.makeText(getApplicationContext(), "Maximo de serviço selecionado!", Toast.LENGTH_LONG).show();
        }
    }
    private void AtualizaValor() {
        if (preco1==0) {
            preco1 = Float.parseFloat((String) txtServicoPreco1.getText());
            SubTotal = preco1;
        }else if (preco1>0 && preco2 == 0){
            preco2 = Float.parseFloat((String) txtServicoPreco2.getText());
            SubTotal = SubTotal + preco2;
        }else if (preco2>0 && preco3 ==0){
            preco3 = Float.parseFloat((String) txtServicoPreco3.getText());
            SubTotal = SubTotal + preco3;
        }else if (preco3>0 && preco4 ==0){
            preco4 = Float.parseFloat((String) txtServicoPreco4.getText());
            SubTotal = SubTotal + preco4;
        }
        txtValorSubTotal.setText(SubTotal+"");
        ValorTotal = SubTotal;
        txtValorTotal.setText(ValorTotal+"");
    }
    public void bt_selecionardono_telacadastroservicos_to_telacadastros (View view){
        Intent it2 = new Intent(this, telacadastros.class);
        someActivityResultLauncher.launch(it2);
    }
    public void bt_selecionarservico_telacadastroservicos_to_telaselecionaservico (View view){
        Intent it3 = new Intent(this, telaselecionaservico.class);
        someActivityResultLauncher.launch(it3);
    }
    public void bt_finish_telacadastroservicos (View view){
        finish();
    }
    public void bt_concluir_telacadastroservicos_to_telaservicos (View view){
        cadServico.setIDDono(IDDono);
        cadServico.setNomeDono(txtNomeDono.getText().toString());
        cadServico.setTelefoneDono(txtTelefoneDono.getText().toString());
        cadServico.setEnderecoDono(txtEnderecoDono.getText().toString());
        cadServico.setCPFDono(CPFDono);
        cadServico.setIdpet(IDPet);
        cadServico.setEspeciePet(especie);
        cadServico.setNomePet(txtNomePet.getText().toString());
        cadServico.setRacaPet(txtRacaPet.getText().toString());
        cadServico.setNomeServico1(txtServicoNome1.getText().toString());
        cadServico.setNomeServico2(txtServicoNome2.getText().toString());
        cadServico.setNomeServico3(txtServicoNome3.getText().toString());
        cadServico.setNomeServico4(txtServicoNome4.getText().toString());
        cadServico.setPrecoServico1(txtServicoPreco1.getText().toString());
        cadServico.setPrecoServico2(txtServicoPreco2.getText().toString());
        cadServico.setPrecoServico3(txtServicoPreco3.getText().toString());
        cadServico.setPrecoServico4(txtServicoPreco4.getText().toString());
        cadServico.setSubtotal(SubTotal.toString());
        cadServico.setTotal(txtValorTotal.getText().toString());
        cadServico.salvar(cadServico);
        finish();
    }
    public void setDatabaseReference(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }
}