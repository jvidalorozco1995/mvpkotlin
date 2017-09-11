package com.periferiait.contactbusiness.checkkotlinlibraries.adapters


import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.periferiait.contactbusiness.checkkotlinlibraries.R
import com.periferiait.contactbusiness.checkkotlinlibraries.model.NewsItem
import com.periferiait.contactbusiness.checkkotlinlibraries.utils.DateUtil

import kotlinx.android.synthetic.main.view_news_item.view.*

/**
 * Created by admin on 11/09/17.
 */
class NewsAdapter : Adapter<NewsAdapter.NewsViewHolder>() {

    private var mDataSource: List<NewsItem>? = null

    fun setDataSource(dataSource: List<NewsItem>) {
        this.mDataSource = dataSource
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = mDataSource!![position]
        holder.bind(newsItem)
    }


    override fun getItemCount(): Int {
        return mDataSource?.size ?: 0
    }

    class NewsViewHolder internal constructor(itemView: View) : ViewHolder(itemView) {

        var newsItem: NewsItem? = null

        private val mImageView: ImageView? by lazy {
            itemView.image_view
        }
        private val mHeadLineTextView: TextView? by lazy {
            itemView.headLineTextView
        }
        private val mAgencyTextView: TextView?by lazy {
            itemView.agencyTextView
        }
        private val mDateTextView: TextView? by lazy {
            itemView.dateTextView
        }
        private val mCaptionTextView: TextView? by lazy {
            itemView.captionTextView
        }

        fun bind(newsItem: NewsItem) {
            this.newsItem = newsItem
            mHeadLineTextView!!.text = newsItem.headLine

            if (newsItem.agency == null)
                mAgencyTextView!!.visibility = View.GONE
            else
                mAgencyTextView!!.text = itemView.resources.getString(
                        R.string.view_news_item_agency,
                        newsItem.agency)

            mDateTextView!!.text = DateUtil.formatDate(newsItem.dateLine!!)

            mCaptionTextView!!.text = newsItem.caption

            Glide.with(itemView.context)
                    .load(newsItem.image?.Thumb)
                    .asBitmap().centerCrop()
                    .into(object : BitmapImageViewTarget(mImageView!!) {
                        override fun setResource(resource: Bitmap) {
                            val bitmapDrawable = RoundedBitmapDrawableFactory.create(
                                    itemView.resources,
                                    resource)
                            bitmapDrawable.isCircular = true
                            mImageView!!.setImageDrawable(bitmapDrawable)
                        }
                    })
        }
    }

}