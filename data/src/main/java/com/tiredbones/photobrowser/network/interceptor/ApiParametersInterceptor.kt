package com.tiredbones.photobrowser.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiParametersInterceptor(private val apiKey: String) : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val url = request.url().newBuilder()
        .addQueryParameter(API_KEY_PARAM, apiKey)
        .addQueryParameter(FORMAT_PARAM, FORMAT_JSON)
        .addQueryParameter(NO_JSON_CALLBACK_PARAM, NO_JSON_CALLBACK_VALUE).build()
    return chain.proceed(request.newBuilder().url(url).build())
  }

  companion object {
    private const val API_KEY_PARAM = "api_key"
    private const val FORMAT_PARAM = "format"
    private const val FORMAT_JSON = "json"
    private const val NO_JSON_CALLBACK_PARAM = "nojsoncallback"
    private const val NO_JSON_CALLBACK_VALUE = "1"
  }
}
