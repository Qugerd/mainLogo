package com.example.logo

import android.util.Log

object Constant {
    val TEST = "test"

    fun print(str: String, msg: Any){
        Log.d(TEST, "$str: $msg")
    }

    val BASE_URL = "http://10.0.2.2:80"

    val RUB_SIMBOL = " ₽"

    val EMPTY= ""
//    recyclerview.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
}