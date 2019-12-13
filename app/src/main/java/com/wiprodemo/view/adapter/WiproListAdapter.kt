package com.wiprodemo.view.adapter

import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wiprodemo.R
import com.wiprodemo.network.response_been.WiproListResponse
import com.wiprodemo.network.response_been.WiproListRow
import com.wiprodemo.view.MainActivity
import kotlinx.android.synthetic.main.list_raw.view.*
import java.lang.NullPointerException

class WiproListAdapter(
    private var mainActivity: MainActivity,
    private var mwiproData: WiproListResponse
) : RecyclerView.Adapter<WiproListAdapter.VideoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_raw, parent, false)
        return VideoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mwiproData.rows.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(
            mainActivity, mwiproData.rows[position], mwiproData.title
        )
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            videoPlayerActivity: MainActivity,
            wiproList: WiproListRow, title: String
        ) {
            try {
                itemView.title.text = title
                if (wiproList.title != null) {
                    itemView.description.text = wiproList.title + ": " + wiproList.description
                }else{
                    itemView.description.text="N/A"
                }
                if (wiproList.imageHref != null) {
                    Glide.with(videoPlayerActivity).load(wiproList.imageHref).into(itemView.img)
                }
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }
}