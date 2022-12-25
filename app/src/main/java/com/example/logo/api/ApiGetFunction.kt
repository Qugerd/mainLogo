package com.example.logo.api

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
import retrofit2.http.Field

interface ApiGetFunction {

    suspend fun getProductList() : ProductList

    suspend fun getProductList(sortMode: Int) : ProductList

    suspend fun getProductDetails(slug: String): ProductDetails

    suspend fun getCategoryList() : Categor

    suspend fun getMainPageInfo() : MainPageInfo

    suspend fun getCategoryProductList(categoryName: String) : ProductList

    suspend fun getSearchQuery(query: String) : ProductList

    suspend fun postCart(body: Modifications) : CartList

    suspend fun postPhoneNumber(number: String, policy: Boolean)

    suspend fun postCheckSmsCode(user_code: String) : CheckSms

    suspend fun postAdress(body: DataModel) : DaData
}