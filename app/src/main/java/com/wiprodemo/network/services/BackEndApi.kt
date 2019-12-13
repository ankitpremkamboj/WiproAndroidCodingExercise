package com.wiprodemo.network.services

import com.wiprodemo.network.response_been.WiproListResponse
import retrofit2.Call
import retrofit2.http.GET

interface BackEndApi {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getAllListData(): Call<WiproListResponse>

}