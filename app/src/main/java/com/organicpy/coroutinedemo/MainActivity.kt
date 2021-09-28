package com.organicpy.coroutinedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.organicpy.coroutinedemo.databinding.ActivityMainBinding
import com.organicpy.coroutinedemo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.gender.observe(this, {
            binding.gender.text = it.gender
            binding.probability.text = it.probability.toString()
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.btnCheckGender.visibility = View.GONE
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.btnCheckGender.visibility = View.VISIBLE
                binding.progressDialog.visibility = View.GONE
            }
        })

        binding.btnCheckGender.setOnClickListener {
            if (binding.inputName.editText?.text!!.isEmpty()) {
                Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.loading.value = true
                viewModel.getGender(binding.inputName.editText?.text.toString())
            }
        }
    }
}