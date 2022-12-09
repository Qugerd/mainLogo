package com.example.logo.ui.goods

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.logo.R
import com.example.logo.databinding.FragmentGalleryBinding
import com.example.logo.ui.goods.GoodsFragment.Companion.imagePaths
import kotlin.properties.Delegates

class Gallery : Fragment(){

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private val linearContainer by lazy { binding.linearContainer }
    private val tvCounter by lazy { binding.tvCounter }
    private val imageView by lazy { binding.imageView }

    private var prevIndex by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding.btnClose.setOnClickListener {
                findNavController().popBackStack()
            }

        val position = arguments?.get(GoodsFragment.KEY_POSITION) as Int
        createPreview()
        prevIndex = position
        onClickImage(position)

    }

    private fun createPreview() {

        imagePaths.forEachIndexed { index, it ->
            val image = ImageView(requireContext())

            val params = LinearLayout.LayoutParams(180,280)
            params.setMargins(0, 0, 10, 0)
            image.layoutParams = params
            image.alpha = 0.6f
            Glide.with(image)
                .load(it)
                .error(R.drawable.jesus)
                .into(image)
            image.setOnClickListener {
                onClickImage(index)
            }
            linearContainer.addView(image)
        }
    }

    private fun onClickImage(index: Int) {
        val position = (index + 1).toString()
        val size = imagePaths.size
        val path = imagePaths[index]

        tvCounter.text = "$position из $size"
        Glide.with(imageView)
            .load(path)
            .error(R.drawable.jesus)
            .into(imageView)

        linearContainer.get(prevIndex).alpha = 0.6f
        linearContainer.get(index).alpha = 1f

        prevIndex = index
    }
}