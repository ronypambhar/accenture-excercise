package com.app.accentureexcercise.network;

import com.app.accentureexcercise.BuildConfig
import com.app.accentureexcercise.database.AlbumModel
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit.Builder
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*
import java.util.concurrent.TimeUnit

interface RequestInterface {

    @GET("albums")
    fun getAlbumList(): Observable<ArrayList<AlbumModel>>

    companion object {
        fun create(): RequestInterface {

            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(120L, TimeUnit.SECONDS)
            httpClient.readTimeout(120L, TimeUnit.SECONDS)
            httpClient.writeTimeout(120L, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)

                httpClient.addInterceptor(logging)
                    .addInterceptor { chain ->
                        val original = chain.request()

                        val request = original.newBuilder()
                            .method(original.method(), original.body())
                            .build()
                        val response: Response = chain.proceed(request)
                        response
                    }
            } else {
                httpClient.addInterceptor { chain ->
                    val original = chain.request()

                    val request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build()
                    val response: Response = chain.proceed(request)
                    response
                }
            }

            return Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build().create(RequestInterface::class.java)
        }
    }
}