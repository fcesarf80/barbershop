package com.fcesar.barbershop.repository

import com.fcesar.barbershop.R
import com.fcesar.barbershop.model.BarberItem

class ServiceSelectionRepository {

    fun getBarberItems(): List<BarberItem> {
        return listOf(
            BarberItem(
                "CLUBE DE CAVALHEIROS",
                "Horário disponível",
                "10am - 10pm",
                R.drawable.img_02
            ),
            BarberItem(
                "CLUBE DE CAVALHEIROS",
                "Horário disponível",
                "10am - 10pm",
                R.drawable.img_03
            ),
            BarberItem(
                "CLUBE DE CAVALHEIROS",
                "Horário disponível",
                "10am - 10pm",
                R.drawable.img_02
            ),
            BarberItem(
                "CLUBE DE CAVALHEIROS",
                "Horário disponível",
                "10am - 10pm",
                R.drawable.img_03
            )
        )
    }
}