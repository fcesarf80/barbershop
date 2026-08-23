package com.fcesar.barbershop.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.fcesar.barbershop.R
import com.fcesar.barbershop.databinding.ActivityServiceSelectionBinding
import com.fcesar.barbershop.viewmodel.ServiceSelectionViewModel

class ServiceSelection : AppCompatActivity() {

    private lateinit var binding: ActivityServiceSelectionBinding

    private val viewModel: ServiceSelectionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityServiceSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarHorario()
        observarBarbeiros()
        configurarServicos()
        configurarReserva()
    }

    private fun configurarHorario() {

        val horarios = listOf(
            "10:00",
            "11:00",
            "12:00",
            "14:00",
            "15:00",
            "16:00",
            "17:00",
            "18:00",
            "19:00",
            "20:00",
            "21:00"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            horarios
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spinner.adapter = adapter
    }

    private fun observarBarbeiros() {

        viewModel.barberItems.observe(this) { items ->

            if (items.isNotEmpty()) {

                val barber = items[0]

                // Por enquanto, apenas para testar o fluxo.
                Toast.makeText(
                    this,
                    "Barbeiro: ${barber.title}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun configurarServicos() {

        binding.radioButton.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                Toast.makeText(
                    this,
                    "Corte de Cabelo selecionado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.radioButton2.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                Toast.makeText(
                    this,
                    "Barba selecionada",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.radioButton3.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                Toast.makeText(
                    this,
                    "Combo selecionado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.radioButton4.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                Toast.makeText(
                    this,
                    "Barboterapia selecionada",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.radioButton5.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                Toast.makeText(
                    this,
                    "Corte Infantil selecionado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun configurarReserva() {

        binding.button2.setOnClickListener {

            val servico = when {
                binding.radioButton.isChecked ->
                    "Corte de Cabelo"

                binding.radioButton2.isChecked ->
                    "Barba"

                binding.radioButton3.isChecked ->
                    "Combo"

                binding.radioButton4.isChecked ->
                    "Barboterapia"

                binding.radioButton5.isChecked ->
                    "Corte Infantil"

                else -> null
            }

            val horario = binding.spinner.selectedItem.toString()

            if (servico == null) {

                Toast.makeText(
                    this,
                    "Selecione um serviço.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            Toast.makeText(
                this,
                "Reserva: $servico às $horario",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}