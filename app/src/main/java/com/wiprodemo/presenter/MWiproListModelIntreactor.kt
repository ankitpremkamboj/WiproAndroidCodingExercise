package com.wiprodemo.presenter

import android.content.Context
import com.wiprodemo.network.response_been.WiproListResponse

interface MWiproListModelIntreactor {


    interface WiproResponseListener {
        fun onNetworkFailed()
        fun onResponseFail(error: String)
        fun onSuccess(responseBean: WiproListResponse)
        fun onProgress(progress: Boolean)
    }

    fun callGetWiproListApi(responseListener: WiproResponseListener, context: Context)

}