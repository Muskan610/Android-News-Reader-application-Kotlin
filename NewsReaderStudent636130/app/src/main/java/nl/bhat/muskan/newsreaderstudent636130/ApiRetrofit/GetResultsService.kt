package nl.bhat.muskan.newsreaderstudent636130.ApiRetrofit

import nl.bhat.muskan.newsreaderstudent636130.LoginRegister.RegisterResponse
import nl.bhat.muskan.newsreaderstudent636130.GetResults.ResultList
import nl.bhat.muskan.newsreaderstudent636130.LoginRegister.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface GetResultsService {
    @GET("Articles")
    fun getAllResults(
        @Query("count") count: Int = 20): Call<ResultList>

    @GET("Articles/liked")
    fun getAllLikedArticles(
        @Header("x-authtoken") auth: String
    ): Call<ResultList>

    @GET("Articles/{id}/")
    fun getAllResultsByNextId(@Path("id") nextId: Int,
                          @Query("count") count: Int = 20): Call<ResultList>

    @FormUrlEncoded
    @POST("Users/register")
    fun register(
        @Field("UserName") UserName: String,
        @Field("Password") Password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("Users/login")
    fun login(
        @Field("UserName") UserName: String,
        @Field("Password") Password: String
    ): Call<LoginResponse>

    //like dislike calls
    @PUT("Articles/{id}/like")
    fun likeArticle(@Path("id") id: Int,
                    @Header("x-authtoken") auth: String) : Call<Void>

    @DELETE("Articles/{id}/like")
    fun dislikeArticle(@Path("id") id: Int,
                       @Header("x-authtoken") auth: String): Call<Void>


}