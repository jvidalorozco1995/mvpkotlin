package com.periferiait.contactbusiness.checkkotlinlibraries.presenter

import com.periferiait.contactbusiness.checkkotlinlibraries.api.NewsApi
import com.periferiait.contactbusiness.checkkotlinlibraries.model.NewsItem
import com.periferiait.contactbusiness.checkkotlinlibraries.view.activities.MainView
import io.realm.Realm
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers

/**
 * Created by admin on 11/09/17.
 */
open class NewsPresenter {

    private var mNewsApiInterface: NewsApi? = null
    private var mNewsView: MainView? = null

    private var subscription: Subscription? = null

    fun onViewCreated(view: MainView) {
        mNewsView = view
    }

    fun setNewsApiInterface(newsApiInterface: NewsApi) {
        this.mNewsApiInterface = newsApiInterface
    }

    fun loadNews() {
        subscription = mNewsApiInterface!!
                .getNews()
                .map { it.newsItem }
                .flatMap({ items ->
                    Realm.getDefaultInstance().executeTransaction({ realm ->
                        realm.delete(NewsItem::class.java)
                        realm.insert(items)
                    })
                    Observable.just(items)
                })
                .onErrorResumeNext { _ ->
                    val realm = Realm.getDefaultInstance()
                    val items = realm.where(NewsItem::class.java).findAll()
                    Observable.just(realm.copyFromRealm(items))
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate { mNewsView?.hideLoading() }
                .subscribe({ mNewsView?.onNewsItemLoaded(it) }, { mNewsView?.onError(it) })
    }

    fun onDestroy() {
        subscription?.unsubscribe()
    }
}