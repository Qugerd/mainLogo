package com.example.logo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.logo.Constant.TEST
import com.example.logo.R
import com.example.logo.databinding.FragmentHomeBinding
import com.example.logo.ui.goods.GoodsFragment
import com.example.logo.ui.goods.GoodsFragment.Companion.KEY_NAME
import com.example.logo.ui.home.adapters.NewClothesAdapter
import com.example.logo.ui.home.adapters.SalesAdapter
import cz.intik.overflowindicator.SimpleSnapHelper
import com.example.logo.Constant.print

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


//        viewModel.productListSalesData.observe(viewLifecycleOwner){
//            recycleViewSales.adapter = SalesAdapter(it, this)
//            Log.d("test", "Sales = ${it.size}")
//        }

        viewModel.mainPageInfo.observe(viewLifecycleOwner){
            recycleViewNewCollection.adapter = NewClothesAdapter(it.newProducts, this)
            recycleViewSales.adapter = SalesAdapter(it.saleProducts, this)
        }

        val viewOverflowPagerIndicator = binding.viewOverflowPagerIndicator
        viewModel.productListNewClothesData.observe(viewLifecycleOwner) {
            with(binding){
                // textViewCategory.text = it[0].slug
                // TODO: Нужна картнка для категории  и добавить логику для второй категории
            }
//            recycleViewNewCollection.adapter = NewClothesAdapter(it, this)
//            viewOverflowPagerIndicator.attachToRecyclerView(recycleViewNewCollection)
            Log.d("test", "New = ${it.size}")
        }

//        val snapHelper = SimpleSnapHelper(viewOverflowPagerIndicator)
//        snapHelper.attachToRecyclerView(recycleViewNewCollection)

        with(viewModel){
            getMainPageInfo()
//            getProductList()
            getCategoryList()

        }
        
        binding.cardViewFirst.setOnClickListener{
//            Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment_to_categoryFragment)
        }

    }

    override fun onItemClick(position: String?) {
        findNavController().navigate(
            R.id.action_homeFragment_to_goodsFragment,
            bundleOf(KEY_NAME to position)
        )
    }

    // TODO: сделать поиск, кнопки на превью с дабовлением в корзину
}