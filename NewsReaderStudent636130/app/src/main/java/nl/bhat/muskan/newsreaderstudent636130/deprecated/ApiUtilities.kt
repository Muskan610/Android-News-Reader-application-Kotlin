package nl.bhat.muskan.newsreaderstudent636130.deprecated

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//retrofit client instance
class ApiUtilities {
    var retrofit: Retrofit? = null
    val apiInterface: ApiInterface
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(ApiInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit!!.create(ApiInterface::class.java)
        }
}