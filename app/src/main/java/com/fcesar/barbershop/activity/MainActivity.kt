package com.fcesar.barbershop.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fcesar.barbershop.adapter.BarberAdapter
import com.fcesar.barbershop.databinding.ActivityMainBinding
import com.fcesar.barbershop.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {

        binding.rvBarbeiros.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )

        binding.rvBarbeiros.setHasFixedSize(true)
    }

    private fun observeViewModel() {

        viewModel.barberItems.observe(this) { items ->

            binding.rvBarbeiros.adapter =
                BarberAdapter(items) { clickedItem ->

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
}