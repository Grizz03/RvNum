package com.stephenw.rvnum.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.stephenw.rvnum.databinding.ItemLayoutBinding

class NumAdapter(private var list: MutableList<Int>) :
    RecyclerView.Adapter<NumAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(num: Int) {
            binding.apply {
                listItemTv.apply {
                    val numRange = 10..255
                    val randomNum1: Int = numRange.random()
                    val randomNum2: Int = numRange.random()
                    val randomNum3: Int = numRange.random()
                    setBackgroundColor(Color.rgb(randomNum1, randomNum2, randomNum3))
                    text = num.toString()
                    // TASK: 3
                    setOnClickListener {
                        Toast.makeText(
                            it.context,
                            num.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}