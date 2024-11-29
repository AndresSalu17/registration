package com.example.ff;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormLogin4 extends AppCompatActivity {

    private EditText edit_senha1, edit_confSenha1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_login4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edit_senha1 = findViewById(R.id.edit_senha1);
        edit_confSenha1 = findViewById(R.id.edit_confSenha1);

        String email = getIntent().getStringExtra("email");
        String confEmail = getIntent().getStringExtra("confEmail");
        String name = getIntent().getStringExtra("name");
        String dataNascimento = getIntent().getStringExtra("dataNascimento");
        String cpf = getIntent().getStringExtra("cpf");
        String sexo = getIntent().getStringExtra("sexo");
        String cep = getIntent().getStringExtra("cep");
        String num = getIntent().getStringExtra("num");
        String rua = getIntent().getStringExtra("rua");
        String bairro = getIntent().getStringExtra("bairro");
        String estado = getIntent().getStringExtra("estado");
        String cidade = getIntent().getStringExtra("cidade");

        Button btn_Cadastrar = findViewById(R.id.btn_Cadastrar);
        btn_Cadastrar.setOnClickListener(v ->{

            String senha = edit_senha1.getText().toString();
            String confSenha = edit_confSenha1.getText().toString();

            if (!senha.equals(confSenha)) {
                edit_senha1.setError("As senhas não coincidem!");
                edit_confSenha1.setError("As senhas não coincidem!");
                return;
            }

            if (!isSenhaValida(senha)) {
                edit_senha1.setError("A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, um número e um caractere especial!");
                return;
            }
            String dados = String.format("Email: %s\nNome: %s\nCPF: %s\nSexo: %s\n", email, name, cpf, sexo);
            boolean success = FileHelper.saveToFile(this, "usuarios.txt", dados);

            if (success) {
                Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro ao salvar dados.", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(this, Continue.class);
            startActivity(intent);
        });
    }

private boolean isSenhaValida(String senha) {
    return senha.length() >= 8 &&
            senha.matches(".*[A-Z].*") &&  // Pelo menos uma letra maiúscula
            senha.matches(".*\\d.*") &&   // Pelo menos um número
            senha.matches(".*[@#$%^&*+=!].*"); // Pelo menos um caractere especial
    }
    private void limparCampos () {
        edit_senha1.setText("");
        edit_confSenha1.setText("");
    }
}