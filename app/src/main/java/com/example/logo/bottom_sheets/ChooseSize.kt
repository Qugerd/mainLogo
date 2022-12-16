package com.example.logo.bottom_sheets

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import com.example.logo.R
import com.example.logo.data.modelProductDetails.Color
import com.example.logo.data.post.modification.Modification
import com.example.logo.databinding.DialogChooseSizeBinding
import com.example.logo.ui.goods.GoodsFragment
import com.example.logo.ui.goods.GoodsFragment.Companion.QUANTITY
import com.example.logo.ui.goods.GoodsFragment.Companion.idProduct
import com.example.logo.ui.goods.GoodsFragment.Companion.mList
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChooseSize : BottomSheetDialogFragment() {

    private var _binding: DialogChooseSizeBinding? = null
    private val binding get() = _binding!!

    private val btnAdd: AppCompatButton by lazy { binding.btnToCart }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("test", "onCreate")
    }

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

        val sizeNames = arguments?.getStringArrayList("key")
        val name = arguments?.getString("key")

        Log.d("test", "name $name")

        if (!sizeNames.isNullOrEmpty()){
            binding.radioGroup.apply {
                sizeNames?.forEach {
                    val radio = RadioButton(requireContext())
                    radio.text = it
                    radio.textSize = 16f
                    radio.typeface = ResourcesCompat.getFont(requireContext(), R.font.montserrat_regular)
                    radio.setTextColor(android.graphics.Color.rgb(81,85,98))

                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )
                    params.setMargins(0, 0, 0, 32)
                    radio.layoutParams = params
                    this.addView(radio)
                }
            }
        }

        btnAdd.setOnClickListener {
            addToCart()
        }

        binding.icClose.setOnClickListener {
            hideDialog()
        }
    }

    private fun addToCart() {
        if (binding.radioGroup.checkedRadioButtonId >= 0){
            mList.add(Modification(idProduct, QUANTITY))
            btnAdd.isClickable = false
            btnAdd.text = "В корзине"
            btnAdd.setBackgroundResource(R.drawable.btn_background)
            btnAdd.setTextColor(android.graphics.Color.rgb(0,0,0))
        }
        else Toast.makeText(requireContext(),"Выберите цвет", Toast.LENGTH_SHORT).show()
    }

    private fun hideDialog(){
        this.dismiss()
    }
}