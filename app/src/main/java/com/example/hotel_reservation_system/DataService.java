package com.example.hotel_reservation_system;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("all-hotels")
    Call<List<Hotel>> getAllObjects();
}
