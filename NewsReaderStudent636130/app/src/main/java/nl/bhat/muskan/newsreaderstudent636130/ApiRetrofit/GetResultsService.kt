package nl.bhat.muskan.newsreaderstudent636130.ApiRetrofit

import nl.bhat.muskan.newsreaderstudent636130.LoginRegister.RegisterResponse
import nl.bhat.muskan.newsreaderstudent636130.GetResults.ResultList
import nl.bhat.muskan.newsreaderstudent636130.LoginRegister.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface GetResultsService {
    @GET("api/Articles")
    fun getAllResults(
        @Query("count") count: Int = 20): Call<ResultList>

    @GET("api/Articles/liked")
    fun getAllLikedArticles(): Call<ResultList>

    @GET("/api/Articles/{id}/")
    fun getAllResultsByNextId(@Path("id") nextId: Int,
                          @Query("count") count: Int = 20): Call<ResultList>

    @FormUrlEncoded
    @POST("api/Users/register")
    fun register(
        @Field("UserName") UserName: String,
        @Field("Password") Password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("api/Users/login")
    fun login(
        @Field("UserName") UserName: String,
        @Field("Password") Password: String
    ): Call<LoginResponse>

    //like dislike calls
    @PUT("api/Articles/{id}/like")
    fun likeArticle(@Path("id") id: Int)

    @DELETE("api/Articles/{id}/like")
    fun dislikeArticle(@Path("id") id: Int)


}