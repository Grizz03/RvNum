package com.stephenw.rvnum.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}