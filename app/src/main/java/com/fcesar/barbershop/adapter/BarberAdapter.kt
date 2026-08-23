package com.fcesar.barbershop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fcesar.barbershop.databinding.ItemBarberBinding
import com.fcesar.barbershop.model.BarberItem

class BarberAdapter(
    private val items: List<BarberItem>,
    private val onItemClick: (BarberItem) -> Unit
) : RecyclerView.Adapter<BarberAdapter.BarberViewHolder>() {

    inner class BarberViewHolder(
        private val binding: ItemBarberBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition

                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(items[position])
                }
            }
        }

        fun bind(item: BarberItem) {
            binding.titleTextView.text = item.title
            binding.subtitleTextView.text = item.subtitleData
            binding.horario.text = item.horario
            binding.imageView9.setImageResource(item.imagResId)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BarberViewHolder {

        val binding = ItemBarberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return BarberViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BarberViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}