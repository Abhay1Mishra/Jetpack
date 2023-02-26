package com.example.newreto.View

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newreto.QuotesRepo.Repo
import com.example.newreto.model.QuotesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repo):ViewModel() {

    init {
        viewModelScope.launch ( Dispatchers.IO ){
            repo.getQuotes(1)
        }
    }
    val quotes:LiveData<QuotesList>
    get() = repo.quotes
}