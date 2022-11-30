package com.example.logo.bottom_sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.logo.databinding.DialogRegistrationBinding
import com.example.logo.databinding.FragmentCardBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RegistrationBottomSheet: BottomSheetDialogFragment() {

    private var _binding: DialogRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}