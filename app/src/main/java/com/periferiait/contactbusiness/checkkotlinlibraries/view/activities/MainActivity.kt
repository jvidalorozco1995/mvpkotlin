package com.periferiait.contactbusiness.checkkotlinlibraries.view.activities



import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.periferiait.contactbusiness.checkkotlinlibraries.R



import android.support.v4.widget.SwipeRefreshLayout
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.periferiait.contactbusiness.checkkotlinlibraries.MvpApplication
import com.periferiait.contactbusiness.checkkotlinlibraries.adapters.NewsAdapter
import com.periferiait.contactbusiness.checkkotlinlibraries.api.NewsApi
import com.periferiait.contactbusiness.checkkotlinlibraries.model.NewsItem
import com.periferiait.contactbusiness.checkkotlinlibraries.presenter.NewsPresenter
import java.io.IOException
import javax.inject.Inject

import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity(), MainView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var mNewsPresenter: NewsPresenter
    @Inject
    lateinit var mNewsAPI: NewsApi



    private val mNewsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inject()
        prepareSwipeRefreshLayout()
        prepareRecyclerView()
        recycler_view.adapter = mNewsAdapter
        mNewsPresenter.setNewsApiInterface(mNewsAPI)
        mNewsPresenter.onViewCreated(this)
        mNewsPresenter.loadNews()
    }

    private fun inject() {
        (application as MvpApplication).injector?.inject(this)
    }

    private fun prepareSwipeRefreshLayout() {
        swipe_layout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipe_layout.setOnRefreshListener(this)
    }

    private fun prepareRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        recycler_view.setHasFixedSize(true)
    }

    override fun onNewsItemLoaded(newsItems: List<NewsItem>) {
        swipe_layout.isRefreshing = false
        progress_bar.visibility = View.GONE
        if(newsItems.isEmpty()) {
            status_text_view.setText(R.string.list_is_empty)
            return
        }
        status_text_view.text = null
        mNewsAdapter.setDataSource(newsItems)
    }

    override fun onError(throwable: Throwable?) {
        swipe_layout.isRefreshing = false
        progress_bar.visibility = View.GONE
        if (throwable is IOException) {
            status_text_view.setText(R.string.connection_error)
        } else {
            status_text_view.setText(R.string.list_is_empty)
        }
    }

    override fun hideLoading() {
        swipe_layout.isRefreshing = false
        progress_bar.visibility = View.GONE
    }

    override fun onRefresh() {
        mNewsPresenter.loadNews()
    }

    override fun onStop() {
        super.onStop()
        mNewsPresenter.onDestroy()
    }
}