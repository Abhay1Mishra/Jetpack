package com.example.newreto.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.newreto.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context,private val param :WorkerParameters):Worker(context,param) {
    override fun doWork(): Result {
        val repo =(context as QuoteApplication).repo
        CoroutineScope(Dispatchers.IO).launch{
            repo.getQuoteRunBackground()
        }
        return Result.success()
    }

}