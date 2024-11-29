package com.example.ff;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class FormLogin3 extends AppCompatActivity {

    private EditText edit_cep, edit_num, edit_rua, edit_bairro, edit_estado, edit_cidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_login3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edit_cep = findViewById(R.id.edit_cep);
        edit_num = findViewById(R.id.edit_rua);
        edit_rua = findViewById(R.id.edit_rua);
        edit_bairro = findViewById(R.id.edit_bairro);
        edit_cidade = findViewById(R.id.edit_cidade);
        edit_estado = findViewById(R.id.edit_estado);

        Button btn_continuar = findViewById(R.id.btn_Continuar);
        btn_continuar.setOnClickListener(v -> {

            String cep = edit_cep.getText().toString();
            String rua = edit_rua.getText().toString();
            String num = edit_num.getText().toString();
            String bairro = edit_bairro.getText().toString();
            String cidade = edit_cidade.getText().toString();
            String estado = edit_estado.getText().toString();

            if(TextUtils.isEmpty(cep) || TextUtils.isEmpty(rua) || TextUtils.isEmpty(num)
            || TextUtils.isEmpty(bairro) || TextUtils.isEmpty(cidade) || TextUtils.isEmpty(estado)){
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, FormLogin4.class);
            intent.putExtra("email", getIntent().getStringExtra("email"));
            intent.putExtra("confEmail", getIntent().getStringExtra("confEmail"));
            intent.putExtra("name", getIntent().getStringExtra("name"));
            intent.putExtra("dataNascimento", getIntent().getStringExtra("dataNascimento"));
            intent.putExtra("cpf", getIntent().getStringExtra("cpf"));
            intent.putExtra("sexo", getIntent().getStringExtra("sexo"));
            intent.putExtra("cep", edit_cep.getText().toString());
            intent.putExtra("num", edit_num.getText().toString());
            intent.putExtra("rua", edit_rua.getText().toString());
            intent.putExtra("bairro", edit_bairro.getText().toString());
            intent.putExtra("estado", edit_estado.getText().toString());
            intent.putExtra("cidade", edit_cidade.getText().toString());
            startActivity(intent);
        });
    }
}