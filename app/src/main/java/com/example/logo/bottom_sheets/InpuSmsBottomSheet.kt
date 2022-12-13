package com.example.logo.bottom_sheets

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.logo.R
import com.example.logo.bottom_sheets.RegistrationBottomSheet.Companion.phoneNumberMask
import com.example.logo.databinding.DialogRegistrationBinding
import com.example.logo.databinding.DialogSmsBinding
import com.example.logo.ui.card.CartViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.prefs.Preferences

const val APP = "APP"
const val PREF_VALUE = "PREF_VALUE"

class InpuSmsBottomSheet: BottomSheetDialogFragment() {
    private var _binding: DialogSmsBinding? = null
    private val binding get() = _binding!!

    private val input by lazy { binding.input }
    private val button by lazy { binding.btn }

    private val vm = CartViewModel()

    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogSmsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = requireActivity().getSharedPreferences(APP, Context.MODE_PRIVATE)

        val sendSms = requireContext().getString(R.string.send_sms_to_number)
        binding.tv.text = sendSms + phoneNumberMask

        inputCode()
        timerText()



        binding.btnBack.setOnClickListener {
            goBack()
        }
    }

    private fun inputCode() {
        input.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (input.unMasked.toList().size == 6){

                    Log.d("test", "code ${input.unMasked}")
                    vm.postCheckSmsCode(input.unMasked)

                    vm.token.observe(viewLifecycleOwner){
                        preferences.edit()
                            .putString(PREF_VALUE, it.token)
                            .apply()
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun timerText() {

        button.isClickable = false
        val countDownTimer = object : CountDownTimer(60000, 1000) { // starts at 3 seconds


            override fun onTick(secondsUntilDone: Long) {
                val seconds = ((secondsUntilDone / 1000) % 60).toString()
                val minute = ((secondsUntilDone / 1000) / 60).toString()
                button.text = "Отправить еще раз ($minute:$seconds)"
            }

            override fun onFinish() {
                Log.d("test", "timer: DONE")
                button.text = "Отправить еще раз"
                button.setOnClickListener {
                    timerText()
                }
            }

        }.start()}



    private fun goBack() {
        val bottomSheet = RegistrationBottomSheet()
        bottomSheet.show(parentFragmentManager, "")
        this.dismiss()
    }
}