package com.example.newreto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newreto.QuotesRepo.Repo
import com.example.newreto.View.MainVMF
import com.example.newreto.View.MainViewModel
import com.example.newreto.api.QuoteServer
import com.example.newreto.api.RetroFitHelper

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo =(application as QuoteApplication).repo

        viewModel= ViewModelProvider(this,MainVMF(repo))[MainViewModel::class.java]

        viewModel.quotes.observe(this, Observer {
            Log.d("LiveData",it.results.toString())
        })
    }
}