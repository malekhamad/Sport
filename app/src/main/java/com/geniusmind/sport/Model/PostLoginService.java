package com.geniusmind.sport.Model;

import com.geniusmind.sport.ContractUrl;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostLoginService {


    @FormUrlEncoded
    @POST(ContractUrl.loginEndPoint)
    Call<LoginCallback>loginCall(@Field("email") String email , @Field("password") String password);


}
