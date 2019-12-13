package com.wiprodemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wiprodemo.R
import com.wiprodemo.model.MWiproListModel
import com.wiprodemo.network.response_been.WiproListResponse
import com.wiprodemo.presenter.MWiproListModelIntreactor
import com.wiprodemo.view.adapter.WiproListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MWiproListModelIntreactor.WiproResponseListener {

    lateinit var mWiproListModel: MWiproListModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mWiproListModel = MWiproListModel()
        callApi()
        swipe_refresh.setOnRefreshListener {
            callApi()
            swipe_refresh.isRefreshing =
                false   // reset the SwipeRefreshLayout (stop the loading list data)
        }
    }

    //TODO: call api for getting data
    fun callApi() {
        mWiproListModel.callGetWiproListApi(this, this)
    }


    override fun onNetworkFailed() {

        Toast.makeText(this, getString(R.string.text_response_fail), Toast.LENGTH_SHORT).show()

    }

    override fun onResponseFail(error: String) {
        Toast.makeText(this, getString(R.string.text_response_fail), Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(responseBean: WiproListResponse) {

        //TODO:Set data on adapter on recycler view
        var wiproListAdapter = WiproListAdapter(this, responseBean)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = wiproListAdapter
    }
    override fun onProgress(progress: Boolean) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
