package com.example.logo.ui.card

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logo.api.RetrofitInstance
import com.example.logo.bottom_sheets.RegistrationBottomSheet
import com.example.logo.data.post.modification.Modifications
import com.example.logo.data.modelCart.CartList
import com.example.logo.data.postCheckSmsCode.CheckSms
import com.example.logo.source.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel: ViewModel() {

    private val dataRepository = DataRepository(RetrofitInstance.service)

    private val _cartLiveData = MutableLiveData<CartList>()
    val cartLiveData : LiveData<CartList> = _cartLiveData


    private val _token = MutableLiveData<CheckSms>()
    val token : LiveData<CheckSms> = _token

    fun postCart(mList: Modifications){
        try {
            viewModelScope.launch {
                val response = withContext(Dispatchers.IO) {
                    dataRepository.postCart(mList)
                }
                Log.d("test", "Cart: $response")
                _cartLiveData.postValue(response)
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun postPhoneNumber(number: String, policy: Boolean){
        try {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    dataRepository.postPhoneNumber(number, policy)
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }


    fun postCheckSmsCode(user_code: String){
        try {
            viewModelScope.launch {
                val response = withContext(Dispatchers.IO) {
                    dataRepository.postCheckSmsCode(user_code)
                }
                Log.d("test", "_token: $response")
                _token.postValue(response)
            }
        }

        catch (e:Exception){
            e.printStackTrace()
        }
    }
}