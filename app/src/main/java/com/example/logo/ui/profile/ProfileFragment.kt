package com.example.logo.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.logo.Constant.EMAIL
import com.example.logo.Constant.NAME
import com.example.logo.databinding.FragmentProfileBinding

class ProfileFragment: Fragment(){

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!NAME.isNullOrEmpty() or !EMAIL.isNullOrEmpty()){
            binding.container.visibility = View.VISIBLE
            binding.tvName.text = NAME
            binding.tvEmail.text = EMAIL
        }
    }
}