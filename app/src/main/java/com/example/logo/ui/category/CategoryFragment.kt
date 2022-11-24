package com.example.logo.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.logo.databinding.FragmentCategoryBinding
import com.example.logo.ui.category.adapter.CategoryAdapter


class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoryViewModel = CategoryViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycleV = binding.rv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

//        recycleV.addItemDecoration(
//            DividerItemDecoration(
//                context,
//                DividerItemDecoration.VERTICAL,
//            )
//        )

        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.categoryProductData.observe(viewLifecycleOwner){
            recycleV.adapter = CategoryAdapter(it)
        }

        viewModel.getProductDetails()
    }
}