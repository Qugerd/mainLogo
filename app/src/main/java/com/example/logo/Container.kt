package com.example.logo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.logo.databinding.ContainerRvBinding

class Container(context: Context) {

    var view: View = LayoutInflater.from(context).inflate(R.layout.container_rv, null, false)
}