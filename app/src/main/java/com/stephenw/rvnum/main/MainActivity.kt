package com.stephenw.rvnum.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.stephenw.rvnum.R
import com.stephenw.rvnum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()  {

    // View Binding
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    // Adapter
    private lateinit var numViewModel: NumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // setting up grid and linear layout variables
        val gridLayoutManager = GridLayoutManager(this, 3)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = gridLayoutManager

        // initialize view model
        numViewModel = ViewModelProvider(this).get(NumViewModel::class.java)

        // delivering data to observer
        numViewModel.data.observe(this){ data ->

            // initializing adapter
            val adapter = NumAdapter(data)
            // hooking up recycler view with the adapter
            binding.rvList.adapter = adapter
            binding.floatingBtn.setOnClickListener {
                numViewModel.addNumToList()
            }
            // switch between layouts with switch
            binding.apply {
                binding.layoutSwitch.setOnClickListener {
                    if (rvList.layoutManager == gridLayoutManager) {
                        rvList.layoutManager = linearLayoutManager
                    } else {
                        rvList.layoutManager = gridLayoutManager
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}