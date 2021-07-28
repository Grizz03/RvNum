package com.stephenw.rvnum.main

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.stephenw.rvnum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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
        binding.rvList.layoutManager = linearLayoutManager

        // initialize view model
        numViewModel = ViewModelProvider(this).get(NumViewModel::class.java)

        // delivering data to observer
        numViewModel.data.observe(this) { data ->

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