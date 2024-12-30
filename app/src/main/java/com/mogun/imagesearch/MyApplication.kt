package com.mogun.imagesearch

import android.app.Application

class MyApplication: Application() {
    companion object {
        lateinit var kakaoApiKey: String
    }

    override fun onCreate() {
        super.onCreate()

        kakaoApiKey = getString(R.string.kakao_key)
    }
}