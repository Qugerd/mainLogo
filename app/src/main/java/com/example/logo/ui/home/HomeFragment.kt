package com.example.logo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.logo.R
import com.example.logo.databinding.FragmentHomeBinding
import com.example.logo.ui.goods.GoodsFragment
import com.example.logo.ui.goods.GoodsFragment.Companion.KEY_NAME
import com.example.logo.ui.home.adapters.NewClothesAdapter
import com.example.logo.ui.home.adapters.SalesAdapter

class HomeFragment : Fragment(), NewClothesAdapter.Listener{

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var viewModel: HomeViewModel = HomeViewModel()

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


        viewModel.productListLiveData.observe(viewLifecycleOwner) {
            with(binding){
                textViewCategory.text = it.category[0].name
                // TODO: Нужна картнка для категории  и добавить логику для второй категории
            }
            recycleViewNewCollection.adapter = NewClothesAdapter(it.data, this)
            recycleViewSales.adapter = SalesAdapter(it.data, this)
        }

        with(viewModel){
            getProductList()
            getCategoryList()
        }
        
        binding.cardViewFirst.setOnClickListener{
            Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(position: String?) {
        findNavController().navigate(
            R.id.action_homeFragment_to_goodsFragment,
            bundleOf(KEY_NAME to position)
        )
    }
}