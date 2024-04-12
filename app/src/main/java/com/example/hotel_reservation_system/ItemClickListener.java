package com.example.hotel_reservation_system;

import android.view.View;

public interface ItemClickListener {
//    public void onItemClick(View view, int position, Hotel hotel);
    public void onItemClick(int position, Hotel hotel, int numberOfGuests);
}
