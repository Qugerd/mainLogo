package com.example.logo.bottom_sheets

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.logo.databinding.DialogChooseSizeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChooseSize : BottomSheetDialogFragment() {

    private var _binding: DialogChooseSizeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogChooseSizeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.icClose.setOnClickListener {
            hideDialog()
        }

        binding.radioGroup.apply {

            for (i in 0..3){
                val radio = RadioButton(requireContext())
                radio.text = "dmwkd"
                this.addView(radio)
            }

        }
    }

    private fun hideDialog(){
        this.dismiss()
    }
}