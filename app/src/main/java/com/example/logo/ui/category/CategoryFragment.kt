package com.example.logo.ui.category

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.logo.R
import com.example.logo.bottom_sheets.ChooseSize
import com.example.logo.databinding.FragmentCategoryBinding
import com.example.logo.ui.category.adapter.CategoryAdapter
import com.example.logo.ui.goods.GoodsFragment
import com.example.logo.ui.home.adapters.NewClothesAdapter


class CategoryFragment : Fragment(), NewClothesAdapter.Listener {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoryViewModel = CategoryViewModel()

    private val tvCategory by lazy { binding.tvCategory }
    private val toggleButton by lazy { binding.toggleButton }
    private val btnSearch by lazy { binding.btnSearch }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProductDetails()

        val catName = arguments?.get(GoodsFragment.KEY_NAME) as String
        tvCategory.text = catName


        val recycleV = binding.rv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.categoryProductData.observe(viewLifecycleOwner){
            recycleV.adapter = CategoryAdapter(it, this)
        }


        btnSearch.setOnClickListener {
            showSearchFragment()
        }

        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }



        toggleButton.setOnCheckedChangeListener { compoundButton, isCheked ->
            if (isCheked){
                toggleButton.textOn = "Сортировка (Цена)"
                toggleButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sort_up,
                    0, 0, 0)

                viewModel.getProductDetails(0)
                viewModel.categoryProductData.observe(viewLifecycleOwner){
                    recycleV.adapter = CategoryAdapter(it, this)
                }
            }
            else{
                toggleButton.textOff = "Сортировка (Цена)"
                toggleButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sort_down ,
                    0, 0, 0)

                viewModel.getProductDetails(1)
                viewModel.categoryProductData.observe(viewLifecycleOwner){
                    recycleV.adapter = CategoryAdapter(it, this)
                }
            }
        }
    }

    private fun showSearchFragment() {
        findNavController().navigate(R.id.action_categoryFragment_to_searchFragment)
    }

    override fun onItemClick(position: String?) {
        findNavController().navigate(
            R.id.action_categoryFragment_to_goodsFragment,
            bundleOf(GoodsFragment.KEY_NAME to position)
        )
    }

    override fun showSize(name: String) {
        val bundle = Bundle()
        bundle.putString("key", name)

        val bottomSheet = ChooseSize()
        bottomSheet.show(childFragmentManager, "")
        bottomSheet.arguments = bundle
    }
}