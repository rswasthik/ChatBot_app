package com.example.chatbot;

import java.lang.annotation.Target;
import retrofit
public class RetrofitAPI {


    @GET
    Call<MsgModal>  getMessage(@Url String url);


}
