package com.example.logo.ui.card

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.logo.Constant
import com.example.logo.Constant.EMAIL
import com.example.logo.R
import com.example.logo.bottom_sheets.OrderConfirmBottomSheet
import com.example.logo.data.DataModel
import com.example.logo.databinding.FragmentOrderingBinding

class Ordering : Fragment() {

    private var _binding: FragmentOrderingBinding? = null
    private val binding get() = _binding!!

    private val radioGroup by lazy { binding.radioGroup }
    private val vm = CartViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOrderingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }



        radioGroup.setOnCheckedChangeListener { radioGroup, optionId ->
            run {
                when (optionId) {
                    R.id.showroom -> {
                        binding.tvPay.visibility = View.VISIBLE
                        binding.containerShowroom.visibility = View.VISIBLE
                        binding.payShowroom.visibility = View.VISIBLE

                        binding.containerDelivery.visibility = View.GONE
                        binding.payDelivery.visibility = View.GONE
                    }
                    R.id.delivery -> {
                        binding.containerDelivery.visibility = View.VISIBLE
                        binding.payDelivery.visibility = View.VISIBLE

                        binding.containerShowroom.visibility = View.GONE
                        binding.payShowroom.visibility = View.GONE
                    }
                }
            }
        }

        binding.btnOrder.setOnClickListener {
            checkAndOrder()
        }

        binding.inputCity.doOnTextChanged { text, start, before, count ->
            val query = DataModel(text.toString(), 3)
            vm.postDadata(query)

            vm.daData.observe(viewLifecycleOwner){
                val street = arrayListOf<String>()
                it.suggestions.forEach {
                    street.add(it.value)
                }

                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, street)

                binding.inputCity.setAdapter(adapter)
            }
        }
    }

    private fun checkAndOrder() {
        if (binding.inputName.text.isNullOrEmpty()){
            binding.inputName.error = "поле пустое"
        }
        else if (binding.inputEmail.text.isNullOrEmpty()){
            binding.inputEmail.error = "поле пустое"
        }
        else if (radioGroup.checkedRadioButtonId == -1){
            Toast.makeText(requireContext(), "Выберите получкния", Toast.LENGTH_SHORT).show()
        }
        else if (radioGroup.checkedRadioButtonId == R.id.delivery){
            if (binding.inputCity.text.isNullOrEmpty()){
                binding.inputCity.error = "Населенный пункт не указан"
            }
            else if (binding.inputIndex.text.isNullOrEmpty()){
                binding.inputIndex.error = "Почтовый индекс не указан"
            }
            else if (binding.inputStreet.text.isNullOrEmpty()){
                binding.inputStreet.error = "Улица не указана"
            }
            else if (binding.inputHouseNumber.text.isNullOrEmpty()){
                binding.inputHouseNumber.error = "Номер дома не указан"
            }
            else if (binding.inputFlatNumber.text.isNullOrEmpty()){
                binding.inputFlatNumber.error = "Квартира не указана"
            }
            else{
                val bottomSheet = OrderConfirmBottomSheet()
                bottomSheet.show(childFragmentManager, "")
            }
        }
        else{
            val bottomSheet = OrderConfirmBottomSheet()
            bottomSheet.show(childFragmentManager, "")

            Constant.NAME = binding.inputName.text.toString()
            EMAIL = binding.inputEmail.text.toString()
        }
    }
}