package com.stephenw.rvnum.main.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.stephenw.rvnum.databinding.ItemLayoutBinding

class NumAdapter(private var list: MutableList<Int>) :
    RecyclerView.Adapter<NumAdapter.ViewHolder>() {

//    var handler: Handler = Handler()
//    var runnable: Runnable? = null
//    var delay = 5000

    inner class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(num: Int) {
            binding.listItemTv.apply {
                // TASK: 5
                changeBackground()
                text = num.toString()
                // TASK: 3
                setOnClickListener {
                    Toast.makeText(
                        it.context,
                        text,
                        Toast.LENGTH_SHORT
                    ).show()
                    // TASK: 5
                    changeBackground()
                }
            }
        }

        // TODO: 7/28/2021 add coroutine for timed delay on color switch?? ->
//        fun onResume() {
//            handler.postDelayed(Runnable {
//                handler.postDelayed(runnable!!, delay.toLong())
//                changeBackground()
//            }.also { runnable = it }, delay.toLong())
//        }

        private fun changeBackground() {
            val numRange = 0..255
            val randomNum1: Int = numRange.random()
            val randomNum2: Int = numRange.random()
            val randomNum3: Int = numRange.random()
            binding.listItemTv.apply {
                setBackgroundColor(Color.rgb(randomNum1, randomNum2, randomNum3))
                setTextColor(Color.rgb(randomNum3, randomNum1, randomNum2))
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}