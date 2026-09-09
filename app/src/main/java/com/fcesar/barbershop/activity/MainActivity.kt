package com.fcesar.barbershop.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fcesar.barbershop.R
import com.fcesar.barbershop.adapter.BarberAdapter
import com.fcesar.barbershop.databinding.ActivityMainBinding
import com.fcesar.barbershop.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        loadUserName()
        setupRecyclerView()
        observeViewModel()
        setupBottomNavigation()
    }

    private fun loadUserName() {
        val user = auth.currentUser

        if (user == null) {
            return
        }

        db.collection("users")
            .document(user.uid)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val nome = document.getString("nome")
                    if (!nome.isNullOrEmpty()) {
                        binding.textView4.text = "Welcome, $nome"
                    }
                }
            }
    }

    private fun setupRecyclerView() {
        binding.rvBarbeiros.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.rvBarbeiros.setHasFixedSize(true)
    }

    private fun observeViewModel() {
        viewModel.barberItems.observe(this) { items ->
            binding.rvBarbeiros.adapter = BarberAdapter(items) { clickedItem ->

                val intent = Intent(
                    this,
                    ServiceSelection::class.java
                ).apply {
                    putExtra("BARBER_ITEM", clickedItem)
                }

                startActivity(intent)
            }
        }
    }

    private fun setupBottomNavigation() {

        binding.bottomNavigation.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.nav_profile -> {

                    auth.signOut()

                    startActivity(
                        Intent(this, IntroActivity::class.java)
                    )

                    finish()

                    true
                }

                else -> true
            }
        }
    }
}