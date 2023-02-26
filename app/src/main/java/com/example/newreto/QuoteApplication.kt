package com.example.newreto

import android.app.Application
import android.provider.SyncStateContract.Constants
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.newreto.QuotesRepo.Repo
import com.example.newreto.api.QuoteServer
import com.example.newreto.api.RetroFitHelper
import com.example.newreto.db.QuotesDataBase
import com.example.newreto.worker.QuoteWorker
import java.util.concurrent.TimeUnit
import kotlin.coroutines.Continuation

class QuoteApplication:Application() {
    lateinit var repo: Repo
    override fun onCreate() {
        super.onCreate()
        initialize()
        setUpWorker()
    }

    private fun setUpWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest =PeriodicWorkRequest.Builder(QuoteWorker::class.java,40,TimeUnit.MINUTES)
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

    private fun initialize() {
        val quoteServer = RetroFitHelper.getInstens().create(QuoteServer::class.java)
        val database =QuotesDataBase.getDataBase(applicationContext)
         repo =Repo(quoteServer,database,applicationContext)
    }
}