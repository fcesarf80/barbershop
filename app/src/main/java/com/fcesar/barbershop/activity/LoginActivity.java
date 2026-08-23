package com.fcesar.barbershop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fcesar.barbershop.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        // Verificar se o usuário já está autenticado
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        // Botão Entrar
        binding.btnEntrar.setOnClickListener(v -> {

            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etSenha.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                        this,
                        "Preencha todos os campos",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {

                        if (task.isSuccessful()) {

                            Toast.makeText(
                                    this,
                                    "Login realizado com sucesso!",
                                    Toast.LENGTH_SHORT).show();

                            startActivity(
                                    new Intent(this, MainActivity.class));

                            finish();

                        } else {

                            Toast.makeText(
                                    this,
                                    "Falha no login: "
                                            + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}