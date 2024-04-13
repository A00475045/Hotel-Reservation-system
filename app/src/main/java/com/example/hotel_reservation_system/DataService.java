package com.example.hotel_reservation_system;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("all-hotels")
    Call<List<Hotel>> getAllObjects();
    @POST("add-users")
    Call<List<Guest>> addGuests(@Body List<Guest> guests);
    @POST("add-bookings")
    Call<List<Booking>> addBookings(@Body List<Booking> guests);
}
