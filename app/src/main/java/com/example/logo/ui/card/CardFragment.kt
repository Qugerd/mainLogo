package com.example.logo.ui.card

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.logo.R
import com.example.logo.bottom_sheets.InpuSmsBottomSheet
import com.example.logo.bottom_sheets.OrderConfirmBottomSheet
import com.example.logo.bottom_sheets.RegistrationBottomSheet
import com.example.logo.databinding.DialogRegistrationBinding
import com.example.logo.databinding.DialogSmsBinding
import com.example.logo.databinding.FragmentCardBinding

class CardFragment:Fragment() {

    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!

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

        binding.btnOrder.setOnClickListener{
            findNavController().navigate(R.id.action_cardFragment_to_ordering)
        }
    }

    private fun showInputNumber(){
        val bottomSheet = RegistrationBottomSheet()
        bottomSheet.show(childFragmentManager, "")
    }
}