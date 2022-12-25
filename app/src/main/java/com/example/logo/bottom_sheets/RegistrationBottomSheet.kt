package com.example.logo.bottom_sheets

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.logo.databinding.DialogRegistrationBinding
import com.example.logo.databinding.FragmentCardBinding
import com.example.logo.ui.card.CartViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RegistrationBottomSheet: BottomSheetDialogFragment() {

    companion object{
        lateinit var phoneNumberMask: String
    }

    private var _binding: DialogRegistrationBinding? = null
    private val binding get() = _binding!!

    private val input by lazy { binding.input }
    private val prefix by lazy { binding.textLayout.prefixText }

    private val vm = CartViewModel()

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

        inputNumber()

    }

    private fun inputNumber() {
        input.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (input.unMasked.toList().size == 10){
                    binding.btnGetCode.setOnClickListener {
                        sendPhoneNumber()
                    }
                }
                else  binding.btnGetCode.isClickable = false
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun sendPhoneNumber() {
        val phoneNumber = prefix.toString() + input.unMasked
        phoneNumberMask = prefix.toString() + input.text
        Log.d("test", "phoneNumber ${phoneNumber}")
        Log.d("test", "phoneNumberMask ${phoneNumberMask}")

        showDialogSendCode(phoneNumber)
    }

    private fun showDialogSendCode(phoneNumber: String) {

        val policy = true
        vm.postPhoneNumber(phoneNumber, policy)
//        Log.d("test", "input.unMasked.toList().size ${input.unMasked.toList().size}")
//        Log.d("test", "input.unMasked.toList().size ${input.unMasked}")
        val bottomSheet = InpuSmsBottomSheet()
        bottomSheet.show(parentFragmentManager, "")

        this.dismiss()
    }
}