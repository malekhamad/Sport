package com.geniusmind.sport.Model;

import com.geniusmind.sport.ContractUrl;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostRegisterService {
    @FormUrlEncoded
    @POST(ContractUrl.registerEndPoint)
    Call<LoginCallback>registerCall(@Field("image_base64") String image_user, @Field("fname") String fname ,
                                    @Field("lname") String lname, @Field("team_name") String team_name , @Field("email") String email,
                                    @Field("password") String password , @Field("governorate") String governorate, @Field("date_birth") String date_birth,
                                    @Field("phone_number") int phone_number, @Field("sex") String sex);
}
