package com.power.suntechpower.Util

import com.power.suntechpower.Models.User
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("track")
    suspend fun Loginuser(
        @Header("Authorization") authorization: String,
        @Field("post_id") post_id: String,
        @Field("driver_id") driver_id: String,
    ): Response<User>
}
