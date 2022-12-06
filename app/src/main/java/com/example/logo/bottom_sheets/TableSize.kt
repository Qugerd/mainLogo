package com.example.logo.bottom_sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.logo.databinding.DialogConfirmBinding
import com.example.logo.databinding.DialogTableSizeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TableSize : BottomSheetDialogFragment() {

    private var _binding: DialogTableSizeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogTableSizeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.icClose.setOnClickListener {
            hideDialog()
        }
    }

    private fun hideDialog(){
        this.dismiss()
    }
}