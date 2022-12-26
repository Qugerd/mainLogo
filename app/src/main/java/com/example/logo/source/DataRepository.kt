package com.example.logo.source


import android.util.Log
import com.example.logo.api.ApiGetFunction
import com.example.logo.data.DataModel
import com.example.logo.data.post.modification.Modifications
import com.example.logo.data.modelCart.CartList
import com.example.logo.data.modelCart.Product
import com.example.logo.data.modelCategoryList.Categor
import com.example.logo.data.modelDaData.DaData
import com.example.logo.data.modelMainPage.MainPageInfo
import com.example.logo.data.modelProductDetails.ProductDetails
import com.example.logo.data.modelProductList.ProductList
import com.example.logo.data.postCheckSmsCode.CheckSms

class DataRepository(private val apiGetFunction: ApiGetFunction) {

    suspend fun getProductList() : ProductList {
        return apiGetFunction.getProductList()
    }

    suspend fun getProductList(categoryName: String) : ProductList{
        return apiGetFunction.getProductList(categoryName)
    }

    suspend fun getProductList(sortMode: Int) : ProductList {
        return apiGetFunction.getProductList(sortMode)
    }

    suspend fun getProductDetails(slug: String) : ProductDetails {
        return apiGetFunction.getProductDetails(slug)
    }

    suspend fun getCategoryList() : Categor{
        return apiGetFunction.getCategoryList()
    }

    suspend fun getMainPageInfo() : MainPageInfo {
        return apiGetFunction.getMainPageInfo()
    }

    suspend fun getCategoryProductList(categoryName: String): ProductList{
        return apiGetFunction.getCategoryProductList(categoryName)
    }

    suspend fun getSearchQuery(query: String) : ProductList{
        return apiGetFunction.getSearchQuery(query)
    }

    suspend fun postCart(mList : Modifications) : CartList{
        return apiGetFunction.postCart(mList)
    }

    suspend fun postPhoneNumber(number: String, policy: Boolean) {
        return apiGetFunction.postPhoneNumber(number,  policy)
    }

    suspend fun postCheckSmsCode(user_code: String) : CheckSms {
        return apiGetFunction.postCheckSmsCode(user_code)
    }

    suspend fun postAdress(body: DataModel) : DaData{
        return apiGetFunction.postAdress(body)
    }
}