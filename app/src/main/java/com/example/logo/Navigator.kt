package com.example.logo

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun showGoodFragment()

}