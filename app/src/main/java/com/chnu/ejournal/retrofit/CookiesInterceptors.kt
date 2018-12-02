package com.chnu.ejournal.retrofit

import android.content.Context
import android.preference.PreferenceManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

private const val cookiesKey = "appCookies"

class SendCookiesInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val preferences = PreferenceManager
                .getDefaultSharedPreferences(context)
                .getStringSet(cookiesKey, HashSet()) as HashSet<String>

        preferences.forEach { cookie ->
            builder.addHeader("Cookie", cookie)
        }
        return chain.proceed(builder.build())
    }
}

class SaveCookiesInterceptor(private val context: Context) : Interceptor {

    @JvmField
    val setCookieHeader = "Set-Cookie"

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())

        if (!originalResponse.headers(setCookieHeader).isEmpty()) {
            val cookies = PreferenceManager
                    .getDefaultSharedPreferences(context)
                    .getStringSet(cookiesKey, HashSet()) as HashSet<String>

            originalResponse.headers(setCookieHeader).forEach {
                cookies.add(it)
            }

            PreferenceManager
                    .getDefaultSharedPreferences(context)
                    .edit()
                    .putStringSet(cookiesKey, cookies)
                    .apply()
        }

        return originalResponse
    }
}

fun OkHttpClient.Builder.setCookieInterceptors(context: Context) : OkHttpClient.Builder {
    return this
            .addInterceptor(SaveCookiesInterceptor(context))
            .addInterceptor(SendCookiesInterceptor(context))
}