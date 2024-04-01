package com.example.hotel_reservation_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HotelListFragment extends Fragment {


    View view;
    TextView headingTextView;
    ProgressBar progressBar;
    List<Hotel> userListResponseData;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_list_fragment,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //heading text view
        headingTextView = view.findViewById(R.id.Welcome_banner);
        progressBar = view.findViewById(R.id.progress_bar);


        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        String numberOfGuests = getArguments().getString("number of guests");

        //Set up the header
        headingTextView.setText("Welcome user, displaying hotel for " + numberOfGuests + " guests staying from " + checkInDate +
                " to " + checkOutDate);


        // Set up the RecyclerView
//        ArrayList<Hotel> Hotel = initHotel();
//        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), Hotel);
//        recyclerView.setAdapter(hotelListAdapter);
        getHotelsListsData();
    }

    public ArrayList<Hotel> initHotel() {
        ArrayList<Hotel> list = new ArrayList<>();

        list.add(new Hotel("Halifax Regional Hotel", "2000$", "true"));
        list.add(new Hotel("Hotel Pearl", "500$", "false"));
        list.add(new Hotel("Hotel Amano", "800$", "true"));
        list.add(new Hotel("San Jones", "250$", "false"));
        list.add(new Hotel("Halifax Regional Hotel", "2000$", "true"));
        list.add(new Hotel("Hotel Pearl", "500$", "false"));
        list.add(new Hotel("Hotel Amano", "800$", "true"));
        list.add(new Hotel("San Jones", "250$", "false"));

        return list;
    }

    private void getHotelsListsData() {
        progressBar.setVisibility(View.VISIBLE);

        userListResponseData = initHotel();
        setupRecyclerView();
//        Api.getClient().getHotelsLists(new Callback<List<Hotel>>() {
//            @Override
//            public void success(List<Hotel> userListResponses, Response response) {
//                // in this method we will get the response from API
//                userListResponseData = userListResponses;
//
//
//                // Set up the RecyclerView
//                setupRecyclerView();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                // if error occurs in network transaction then we can get the error in this method.
//                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
//
//            }
//        });
    }

    private void setupRecyclerView() {
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.Recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), userListResponseData);
        recyclerView.setAdapter(hotelListAdapter);

        //Bind the click listener
//        hotelListAdapter.setClickListener((ItemClickListener) this);
    }


//    @Override
//    public void onClick(View view, int position) {
//        Hotel Hotel = userListResponseData.get(position);
//
//        String hotelName = Hotel.getHotel_name();
//        String price = Hotel.getPrice();
//        String availability = Hotel.getAvailability();
//
//        Bundle bundle = new Bundle();
//        bundle.putString("hotel name", hotelName);
//        bundle.putString("hotel price", price);
//        bundle.putString("hotel availability", availability);
//
//        HotelGuestDetailsFragment hotelGuestDetailsFragment = new HotelGuestDetailsFragment();
//        hotelGuestDetailsFragment.setArguments(bundle);
//
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.remove(HotelsListFragment.this);
//        fragmentTransaction.replace(R.id.main_layout, hotelGuestDetailsFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commitAllowingStateLoss();
//
//    }
}
