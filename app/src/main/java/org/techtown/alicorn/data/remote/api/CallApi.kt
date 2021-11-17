package org.techtown.alicorn.data.remote.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.techtown.alicorn.data.remote.request.user.LoginRequstData
import org.techtown.alicorn.data.remote.response.user.data.UserDataResponse
import org.techtown.alicorn.data.remote.response.user.login.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CallApi {
    private val gson: Gson?= GsonBuilder().setLenient().create()
    private val client: OkHttpClient= OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(10,TimeUnit.SECONDS)
        .writeTimeout(10,TimeUnit.SECONDS)
        .build()

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://alicorn-rest-test.herokuapp.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson!!))
        .client(client)
        .build()

    fun userLogin(mEmail:String, mPassword:String, callback: (Boolean, String, LoginResponse?) -> Unit){
        val body = LoginRequstData().apply{
            email = mEmail
            password = mPassword

        }
        retrofit.create(Api::class.java).userLogin(body).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                callback(true, "response", response.body())
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(false,t.message.toString(),null)
            }


        })

    }
    fun loadUserData(mToken:String, callback: (Boolean, String, UserDataResponse?) -> Unit){
        retrofit.create(Api::class.java).loadUserData(mToken).enqueue(object : Callback<UserDataResponse>{
            override fun onResponse(
                call: Call<UserDataResponse>,
                response: Response<UserDataResponse>
            ) {
                callback(true, "response", response.body())
            }

            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                callback(false,t.message.toString(),null)
            }


        })

    }
}