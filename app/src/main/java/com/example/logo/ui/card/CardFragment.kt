package com.example.logo.ui.card

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logo.R
import com.example.logo.bottom_sheets.RegistrationBottomSheet
import com.example.logo.data.modelCart.Item
import com.example.logo.databinding.FragmentCardBinding
import com.example.logo.ui.card.adapters.CartAdapter
import com.example.logo.ui.goods.GoodsFragment.Companion.mList
import com.example.logo.ui.goods.GoodsFragment.Companion.mod

class CardFragment:Fragment() {

    private val vm: CartViewModel = CartViewModel()

    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!

    private val containerEmpty by lazy { binding.containerEmpty }
    private val scrollView by lazy { binding.scrollView }
    private val cardBtn by lazy { binding.cardBtn }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        when {
            mList.isNotEmpty() -> {
                vm.postCart(mod)
                containerEmpty.visibility = View.GONE
                cardBtn.visibility = View.VISIBLE
                scrollView.visibility = View.VISIBLE
            }

            else -> {
                containerEmpty.visibility = View.VISIBLE
                cardBtn.visibility = View.VISIBLE
                scrollView.visibility = View.GONE
            }
        }

        Log.d("test", "onViewCreated")
        val recycleView = binding.rv
        recycleView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)

        vm.cartLiveData.observe(viewLifecycleOwner){
            Log.d("test", "It: $it")
            recycleView.adapter = CartAdapter(it.data.items)
            binding.tvSumOrder.text = it.data.priceTotal.toString()
            binding.tvCount.text = it.data.items.size.toString()
            countSum(it.data.items)
        }

        binding.btnOrder.setOnClickListener{
            ordering()
        }
    }

    private fun ordering() {
        showInputNumber()
    }


    private fun countSum(items: List<Item>) {
        var sum = 0f
        items.forEach {
            sum += it.priceTotal.toFloat()
        }
        binding.tvSum.text = sum.toString()

        var sumSales = 0f

        items.forEach {
            sumSales += it.product.discountPrice.toFloat()
        }

        val sale = sum - sumSales
        binding.tvSumSales.text = sale.toString()
    }

    private fun showInputNumber(){
        val bottomSheet = RegistrationBottomSheet()
        bottomSheet.show(childFragmentManager, "")
    }
}