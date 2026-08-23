package com.fcesar.barbershop.model

import java.io.Serializable

data class BarberItem(
    val title: String,
    val subtitleData: String,
    val horario: String,
    val imagResId: Int
) : Serializable
