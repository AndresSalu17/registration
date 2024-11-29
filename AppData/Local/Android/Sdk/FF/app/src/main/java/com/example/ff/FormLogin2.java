package com.example.ff;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormLogin2 extends AppCompatActivity {

    private EditText edit_email1, edit_confEmail1, edit_name, edit_dataNascimento, edit_cpf1;
    private RadioGroup rgSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_login2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edit_email1 = findViewById(R.id.edit_email1);
        edit_confEmail1 = findViewById(R.id.edit_confEmail1);
        edit_name = findViewById(R.id.edit_name);
        edit_dataNascimento = findViewById(R.id.edit_dataNascimento);
        edit_cpf1 = findViewById(R.id.edit_cpf1);
        rgSexo = findViewById(R.id.rg_sexo);

        edit_dataNascimento.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (isUpdating) {
                    return;
                }

                isUpdating = true;

                // Remove caracteres não numéricos
                String clean = s.toString().replaceAll("[^\\d]", "");

                StringBuilder formatted = new StringBuilder();

                if (clean.length() > 2) {
                    formatted.append(clean.substring(0, 2)).append("/");
                } else if (clean.length() > 0) {
                    formatted.append(clean);
                }

                if (clean.length() > 4) {
                    formatted.append(clean.substring(2, 4)).append("/");
                } else if (clean.length() > 2) {
                    formatted.append(clean.substring(2));
                }

                if (clean.length() > 4) {
                    formatted.append(clean.substring(4, Math.min(clean.length(), 8)));
                }

                edit_dataNascimento.setText(formatted.toString());
                edit_dataNascimento.setSelection(formatted.length());

                isUpdating = false;
            }
        });

        InputFilter cpfFilter = new InputFilter.LengthFilter(11);
        edit_cpf1.setFilters(new InputFilter[]{cpfFilter});

        Button btn_Continuar = findViewById(R.id.btn_Continuar);
        btn_Continuar.setOnClickListener(v ->{

            String email1 = edit_email1.getText().toString();
            String confEmail1 = edit_confEmail1.getText().toString();
            String name = edit_name.getText().toString();
            String dataNascimento = edit_dataNascimento.getText().toString();
            String cpf1 = edit_cpf1.getText().toString();

            int selectedId = rgSexo.getCheckedRadioButtonId();
            String sexo = "";

            if (selectedId == R.id.rb_masculino) {
                sexo = "Masculino";
            } else if (selectedId == R.id.rb_feminino) {
                sexo = "Feminino";
            }
            if (TextUtils.isEmpty(email1) || TextUtils.isEmpty(confEmail1) ||
                    TextUtils.isEmpty(name) || TextUtils.isEmpty(cpf1) ||
                    TextUtils.isEmpty(dataNascimento)){
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!email1.equals(confEmail1)) {
                edit_email1.setError("Os emails não coincidem!");
                edit_confEmail1.setError("Os emails não coincidem!");
                return;
            }
            Intent intent = new Intent(this, FormLogin3.class);
            intent.putExtra("email", edit_email1.getText().toString());
            intent.putExtra("confEmail", edit_confEmail1.getText().toString());
            intent.putExtra("name", edit_name.getText().toString());
            intent.putExtra("dataNascimento", edit_dataNascimento.getText().toString());
            intent.putExtra("cpf", edit_cpf1.getText().toString());
            intent.putExtra("sexo", rgSexo.getCheckedRadioButtonId() == R.id.rb_masculino ? "Masculino" : "Feminino");
            startActivity(intent);

        });
    }
}