package com.wiprodemo.model

import android.content.Context
import com.wiprodemo.R
import com.wiprodemo.network.response_been.WiproListResponse
import com.wiprodemo.network.services.BackEndApi
import com.wiprodemo.network.services.WebServiceClient
import com.wiprodemo.presenter.MWiproListModelIntreactor
import com.wiprodemo.utilis.Constants.Companion.SUCCESS_CODE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MWiproListModel : MWiproListModelIntreactor {

    override fun callGetWiproListApi(
        responseListener: MWiproListModelIntreactor.WiproResponseListener,
        context: Context
    ) {
        val callApi: Call<WiproListResponse> =
            WebServiceClient.client1.create(BackEndApi::class.java).getAllListData()
        responseListener.onProgress(true)

        callApi.enqueue(object : Callback<WiproListResponse> {
            override fun onResponse(
                call: Call<WiproListResponse>,
                response: Response<WiproListResponse>
            ) =
                if (response.code() == SUCCESS_CODE) {
                    responseListener.onProgress(false)
                    val responseBean: WiproListResponse = response.body()!!
                    responseListener.onSuccess(responseBean)
                } else {
                    responseListener.onProgress(false)
                    responseListener.onResponseFail(context.getString(R.string.text_response_fail))
                }

            override fun onFailure(call: Call<WiproListResponse>, t: Throwable) {
                responseListener.onProgress(false)
                responseListener.onResponseFail(context.getString(R.string.text_response_fail))

            }
        })

    }


}