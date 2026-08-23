package com.fcesar.barbershop.repository

import com.fcesar.barbershop.model.BarberItem

class BarberRepository {

    fun getBarberItems(): List<BarberItem> {
        return listOf(
            BarberItem(
                "CLUBE DE CAVALHEIROS",
                "Horário disponível",
                "10am - 10pm",
                com.fcesar.barbershop.R.drawable.img_02
            ),
            BarberItem(
                "CLUBE DE CAVALHEIROS",
                "Horário disponível",
                "10am - 10pm",
                com.fcesar.barbershop.R.drawable.img_03
            )
        )
    }
}