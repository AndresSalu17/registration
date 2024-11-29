package com.example.ff;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormLogin extends AppCompatActivity {

    private EditText edit_email, edit_confEmail, edit_nome, edit_cpf, edit_data, edit_senha, edit_confSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        // Ativar modo Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar os campos
        edit_email = findViewById(R.id.edit_email);
        edit_confEmail = findViewById(R.id.edit_confEmail);
        edit_nome = findViewById(R.id.edit_nome);
        edit_cpf = findViewById(R.id.edit_cpf);
        edit_data = findViewById(R.id.edit_data);
        edit_senha = findViewById(R.id.edit_senha);
        edit_confSenha = findViewById(R.id.edit_confSenha);

        // Configurar o botão de cadastro
        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(v -> {
            // Obter os valores dos campos
            String email = edit_email.getText().toString();
            String confirmaEmail = edit_confEmail.getText().toString();
            String nome = edit_nome.getText().toString();
            String cpf = edit_cpf.getText().toString();
            String dataNascimento = edit_data.getText().toString();
            String senha = edit_senha.getText().toString();
            String confirmaSenha = edit_confSenha.getText().toString();

            // Validação dos campos
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(confirmaEmail) ||
                    TextUtils.isEmpty(nome) || TextUtils.isEmpty(cpf) ||
                    TextUtils.isEmpty(dataNascimento) || TextUtils.isEmpty(senha) ||
                    TextUtils.isEmpty(confirmaSenha)) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verificar se os emails são iguais
            if (!email.equals(confirmaEmail)) {
                edit_email.setError("Os emails não coincidem!");
                edit_confEmail.setError("Os emails não coincidem!");
                return;
            }

// Verificar se as senhas são iguais
            if (!senha.equals(confirmaSenha)) {
                edit_senha.setError("As senhas não coincidem!");
                edit_confSenha.setError("As senhas não coincidem!");
                return;
            }

// Verificar se a senha é válida
            if (!isSenhaValida(senha)) {
                edit_senha.setError("A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, um número e um caractere especial!");
                return;
            }


            // Salvar no banco de dados
          //  boolean sucesso = dbHelper.addUser(email, nome, cpf, dataNascimento, senha);
           // if (sucesso) {
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                limparCampos();
          //  } else {
                Toast.makeText(this, "Erro ao salvar no banco de dados.", Toast.LENGTH_SHORT).show();
         //   }
        });
    }

    // Verificar se a senha é válida
    private boolean isSenhaValida(String senha) {
        return senha.length() >= 8 &&
                senha.matches(".*[A-Z].*") &&  // Pelo menos uma letra maiúscula
                senha.matches(".*\\d.*") &&   // Pelo menos um número
                senha.matches(".*[@#$%^&+=!].*"); // Pelo menos um caractere especial
    }

    // Limpar os campos após o cadastro
    private void limparCampos() {
        edit_email.setText("");
        edit_confEmail.setText("");
        edit_nome.setText("");
        edit_cpf.setText("");
        edit_data.setText("");
        edit_senha.setText("");
        edit_confSenha.setText("");
    }

}
