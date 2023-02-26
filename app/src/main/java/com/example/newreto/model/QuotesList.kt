package com.example.newreto.model

import com.example.newreto.db.Result

data class QuotesList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
)