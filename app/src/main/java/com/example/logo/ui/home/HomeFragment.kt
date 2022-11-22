package com.example.logo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logo.Navigator
import com.example.logo.R
import com.example.logo.databinding.FragmentHomeBinding
import com.example.logo.navigator
import com.example.logo.ui.home.adapters.NewClothesAdapter
import com.example.logo.ui.home.adapters.SalesAdapter

class HomeFragment : Fragment(), NewClothesAdapter.Listener{

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var viewModelHome: HomeViewModel = HomeViewModel()

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

        val recycleViewNewCollection = binding.recyclerViewNewCollection
        recycleViewNewCollection.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)

        val recycleViewSales = binding.recyclerViewSales
        recycleViewSales.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)


        viewModelHome.productListLiveData.observe(viewLifecycleOwner) {
            recycleViewNewCollection.adapter = NewClothesAdapter(it.data, this)
            recycleViewSales.adapter = SalesAdapter(it.data)
        }

        with(viewModelHome){
            getProductList()
            getCategoryList()
            getProductDetails()
        }
    }

    override fun onClick() {
        Log.d("test", "click")
//        navigator().showGoodFragment()
        findNavController().navigate(R.id.action_homeFragment_to_goodsFragment)
    }
}