package org.techtown.alicorn.data.remote.api

import org.techtown.alicorn.data.remote.request.user.LoginRequstData
import org.techtown.alicorn.data.remote.response.user.data.UserDataResponse
import org.techtown.alicorn.data.remote.response.user.login.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @POST("User/login")
    fun userLogin(@Body data: LoginRequstData) : Call<LoginResponse>

    /**
     * https://alicorn-rest-test.herokuapp.com/api/User/
     */

    @GET("User")
    fun loadUserData(

        @Header("Authorization") token:String) : Call<UserDataResponse>
}