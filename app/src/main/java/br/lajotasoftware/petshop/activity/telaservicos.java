package br.lajotasoftware.petshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import br.lajotasoftware.petshop.R;
import br.lajotasoftware.petshop.classes.CadServico;
import br.lajotasoftware.petshop.classes.Dono;
import br.lajotasoftware.petshop.classes.Pets;
import br.lajotasoftware.petshop.classes.Servico;

public class telaservicos extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ListView listView;

    private CadServico cadServico;
    private Dono dono;
    private Pets pets;
    private Servico servico;
    private String nomeDono;
    /*private String id;
    private String iddono;
    private String nomedono;
    private String telefonedono;
    private String cpfdono;
    private String idpet;
    private String nomepet;
    private String especiepet;
    private String raca;
    private String servico;
    private String subtotal;
    private String total;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaservicos);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();

    }

    public void bt_finish_telaservicos (View view){
        finish();
    //    Intent it = new Intent(this, telamain.class);
    //    startActivity(it);
    }

    public void bt_servico_to_telacadastroservico (View view){
        Intent it = new Intent(this, telacadastroservicos.class);
        startActivity(it);
    }
}