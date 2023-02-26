package com.example.newreto.QuotesRepo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newreto.Utile.NetWorkUtil
import com.example.newreto.api.QuoteServer
import com.example.newreto.db.QuotesDataBase
import com.example.newreto.model.QuotesList

class Repo(
    private val quoteServer: QuoteServer,
    private val quotesDataBase: QuotesDataBase,
    private val applicationContext: Context
) {

    private val quoteLiveData = MutableLiveData<QuotesList>()
    val quotes:LiveData<QuotesList>
        get() = quoteLiveData

    suspend fun getQuotes(page: Int){
        val result =quoteServer.getQuotes(page)

        if(NetWorkUtil.isInternetAvailable(applicationContext)){
            if (result.body()!=null){
                quotesDataBase.getQuoteDao().addQuotes(result.body()!!.results)
                quoteLiveData.postValue(result.body())
            }
        }
        else{
            val quote =quotesDataBase.getQuoteDao().getQuotes()
            val quoteList =QuotesList(1,1,1,quote,1,1)
            val getQuote = quoteLiveData.postValue(quoteList)
        }


    }

suspend fun getQuoteRunBackground(){
    val rand = (Math.random() * 10).toInt()
   val result = quoteServer.getQuotes(rand)
    if (result?.body()!=null){
        quotesDataBase.getQuoteDao().addQuotes(result.body()!!.results)
    }

}
}