package com.example.quanla.smartschool.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by QuanLA on 3/10/2017.
 */

public class NetContextLogin {
    public static final NetContextLogin instance = new NetContextLogin();

    private Retrofit retrofit;
    private NetContextLogin(){



        retrofit = new Retrofit.Builder()
                .baseUrl("https://uet-hack.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    public <T> T create(Class<T> classz){
        return retrofit.create(classz);
    }
}
