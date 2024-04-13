package com.example.hotel_reservation_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SuccessFragment extends Fragment {

    View view;
    TextView hostName, hotelName, bookingID;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.success_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hostName = view.findViewById(R.id.host_name);
        hotelName = view.findViewById(R.id.hotel_name_text_view);
        bookingID = view.findViewById(R.id.booking_text_view);

        hostName.setText(getArguments().getString("hostName"));
        hotelName.setText(getArguments().getString("hotelName"));
        bookingID.setText(getArguments().getString("BookingID"));

    }
}
