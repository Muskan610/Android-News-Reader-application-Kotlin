package nl.bhat.muskan.newsreaderstudent636130.ApiRetrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClientInstance {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://inhollandbackend.azurewebsites.net/api/"
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    val setLevel= httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val okHttpClient = OkHttpClient()
     /* val okHttpClient = OkHttpClient.Builder().addInterceptor(object : Interceptor() {
        @Throws(IOException::class)
        fun intercept(chain: Interceptor.Chain): Response? {
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            return chain.proceed(newRequest)
        }
    }).build()*/


    // create a retrofit instance, only if it has not been created yet.
    val retrofitInstance: Retrofit?  //getinstance
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }

            return retrofit //retrofitclient
        }


}