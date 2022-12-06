package com.example.logo.ui.home

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.print.PrintAttributes.Margins
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.TEXT_ALIGNMENT_TEXT_END
import android.view.View.TEXT_ALIGNMENT_TEXT_START
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.core.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.logo.R
import com.example.logo.databinding.FragmentHomeBinding
import com.example.logo.ui.goods.GoodsFragment.Companion.KEY_NAME
import com.example.logo.ui.home.adapters.NewClothesAdapter
import com.example.logo.ui.home.adapters.SalesAdapter
import cz.intik.overflowindicator.SimpleSnapHelper
import com.example.logo.Constant.print
import com.example.logo.Container
import com.example.logo.databinding.ContainerRvBinding
import com.example.logo.ui.home.adapters.CategoryAdapter

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

        val viewOverflowPagerIndicator = binding.viewOverflowPagerIndicator
        val snapHelper = SimpleSnapHelper(viewOverflowPagerIndicator)
        snapHelper.attachToRecyclerView(recycleViewNewCollection)


        val recycleViewSales = binding.recyclerViewSales
        recycleViewSales.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)

        val viewOverflowPagerIndicator2 = binding.viewOverflowPagerIndicator2
        val snapHelper2 = SimpleSnapHelper(viewOverflowPagerIndicator2)
        snapHelper2.attachToRecyclerView(recycleViewSales)



        viewModel.mainPageInfo.observe(viewLifecycleOwner){
            recycleViewNewCollection.adapter = NewClothesAdapter(it.newProducts, this)
            viewOverflowPagerIndicator.attachToRecyclerView(recycleViewNewCollection)

            recycleViewSales.adapter = SalesAdapter(it.saleProducts, this)
            viewOverflowPagerIndicator2.attachToRecyclerView(recycleViewSales)


            val fr = binding.fr

            if ( fr.isNotEmpty()) fr.removeAllViews()

            val adapter = CategoryAdapter(it.saleProducts, this)
            it.categories.forEach {

                if (it.parentId == "45"){

                    print("categor name", it.name)



                    val linearLayout = LinearLayout(requireContext())

                    val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                    params.setMargins(0, 64, 64, 32)
                    linearLayout.layoutParams = params
                    linearLayout.orientation = LinearLayout.HORIZONTAL


                    val tvSlug = TextView(requireContext()).apply {
                        text = it.name
                        gravity = Gravity.START

                        typeface = ResourcesCompat.getFont(requireContext(), R.font.cormorant_garamond_semibold)
                        textSize = 20f
                        isAllCaps = true

                        setTextColor(Color.rgb(81,85,98))
                        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                    }
                    linearLayout.addView(tvSlug)


                    val tv = TextView(requireContext()).apply {
                        typeface = ResourcesCompat.getFont(requireContext(), R.font.montserrat_medium)
                        text = "Все"
                        gravity = Gravity.END
                        textSize = 16f
                        setTextColor(Color.rgb(81,85,98))
                        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                        setOnClickListener {
                            Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()
                        }
                    }
                    linearLayout.addView(tv)



                    val rv = RecyclerView(requireContext())
                    rv.layoutManager = LinearLayoutManager(requireContext(),
                       LinearLayoutManager.HORIZONTAL, false)
                    rv.adapter  = adapter


                    fr.addView(linearLayout)
                    fr.addView(rv)
                }
            }
        }


        with(viewModel){
            getMainPageInfo()
//            getProductList()
//            getCategoryList()
//            getCategoryProductList("aut")
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