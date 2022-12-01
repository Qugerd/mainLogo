package com.example.logo.bottom_sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.logo.databinding.DialogConfirmBinding
import com.example.logo.databinding.DialogSmsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderConfirmBottomSheet: BottomSheetDialogFragment()  {

    private var _binding: DialogConfirmBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

}