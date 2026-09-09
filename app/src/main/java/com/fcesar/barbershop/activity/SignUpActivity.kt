package com.fcesar.barbershop.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.fcesar.barbershop.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        db = FirebaseFirestore.getInstance()

        binding.btnVoltarSign.setOnClickListener {
            finish()
        }

        binding.btnCadastrar.setOnClickListener {

            val nome = binding.etNome.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etSenha.text.toString()
            val confirmarSenha = binding.etConfirmarSenha.text.toString()

            if (nome.isEmpty() ||
                email.isEmpty() ||
                password.isEmpty() ||
                confirmarSenha.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (password != confirmarSenha) {

                Toast.makeText(
                    this,
                    "As senhas não coincidem",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {

                        val user = auth.currentUser

                        if (user != null) {

                            val userData = hashMapOf(
                                "nome" to nome,
                                "email" to email
                            )

                            db.collection("users")
                                .document(user.uid)
                                .set(userData)
                                .addOnSuccessListener {

                                    Toast.makeText(
                                        this,
                                        "Cadastro realizado com sucesso!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                                .addOnFailureListener { e ->

                                    Toast.makeText(
                                        this,
                                        "Usuário criado, mas erro ao salvar dados: ${e.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                        }
                    } else {

                        Toast.makeText(
                            this,
                            "Erro no cadastro: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}
