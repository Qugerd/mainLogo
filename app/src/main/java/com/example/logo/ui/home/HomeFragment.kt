package com.example.logo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logo.api.ApiInterface
import com.example.logo.data.model.ProductList
import com.example.logo.databinding.FragmentHomeBinding
import com.example.logo.ui.home.Adapters.CustomAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment:Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.recyclerViewNewCollection){
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        val recycleViewNewCollection = binding.recyclerViewNewCollection


        val apiInterface = ApiInterface.create().getProductList()

        apiInterface.enqueue( object : Callback<ProductList> {
            override fun onResponse(call: Call<ProductList>?, response: Response<ProductList>?) {
                Log.d("test", "Результат ${response?.body()?.category}")

                recycleViewNewCollection.adapter = CustomAdapter(response?.body()?.data)
            }

            override fun onFailure(call: Call<ProductList>?, t: Throwable?) {
                Log.d("test", "Не получилось${t?.message}")
            }
        })
    }
}