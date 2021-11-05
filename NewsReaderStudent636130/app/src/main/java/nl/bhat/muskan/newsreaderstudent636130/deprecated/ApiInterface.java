package nl.bhat.muskan.newsreaderstudent636130.deprecated;

import nl.bhat.muskan.newsreaderstudent636130.LoginRegister.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    String BASE_URL="https://inhollandbackend.azurewebsites.net/";

    @GET("api/Articles")
    Call<mainNews> getNews(
            @Query("count") int count
    );

    @FormUrlEncoded
    @POST("something")
    Call<RegisterResponse> reg(
            @Field("UserName") String UserName,
            @Field("Password") String Password
    );


}
