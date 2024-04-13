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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import retrofit.Callback;
//import retrofit.RetrofitError;
//import retrofit.client.Response;

public class HotelListFragment extends Fragment implements ItemClickListener {


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
        String name = getArguments().getString("name");
        System.out.println(checkInDate + " to " + checkOutDate);
        //Set up the header
        headingTextView.setText("Welcome "+ name +", \nhotel for " + numberOfGuests + " guest/s staying from " + checkInDate +
                " to " + checkOutDate);



        getHotelsListsData();
    }

    public ArrayList<Hotel> initHotel() {
        ArrayList<Hotel> list = new ArrayList<>();

        list.add(new Hotel(1, "NAME1", "6533 Cork Street", 1, 150, 20));
        list.add(new Hotel(2, "NAME2", "6534 Cork Street", 2, 220, 20));
        list.add(new Hotel(3, "NAME3", "6535 Cork Street", 1, 120, 20));
        list.add(new Hotel(4, "NAME4", "6536 Cork Street", 3, 330, 20));
        list.add(new Hotel(5, "NAME5", "6537 Cork Street", 2, 230, 20));
        list.add(new Hotel(6, "NAME6", "6538 Cork Street", 1, 120, 20));
        list.add(new Hotel(7, "NAME7", "6539 Cork Street", 1, 110, 20));


        return list;
    }

    private void getHotelsListsData() {
        System.out.println("in getHotelsListsData");
        progressBar.setVisibility(View.VISIBLE);


        //=================
        DataService service = RetrofitClientInstance.getRetrofitInstance().create(DataService.class);
        Call<List<Hotel>> call = service.getAllObjects();

        call.enqueue(new Callback<List<Hotel>>() {

            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                System.out.println("res: " + call);
                if (response.isSuccessful()) {
                    System.out.println("in success");
                    // Success! Handle the response data
                    List<Hotel> data = response.body();
                    System.out.println("Log: " + data);
                    userListResponseData = data;
                    setupRecyclerView();
                } else {
                    // Handle the error
                    userListResponseData = initHotel();
                    setupRecyclerView();
                }
            }
            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                System.out.println("in error"+ t.getMessage());
                System.out.println("in error"+ call);
                userListResponseData = initHotel();
                setupRecyclerView();
                // Network error or request was aborted
            }
        });
//================
    }

    private void setupRecyclerView() {
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.Recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), userListResponseData, this, Integer.parseInt(getArguments().getString("number of guests")));
        recyclerView.setAdapter(hotelListAdapter);

        //Bind the click listener
//        hotelListAdapter.setClickListener((ItemClickListener) this);
    }

    @Override
    public void onItemClick(int position, Hotel hotel, int numberOfGuests) {
        System.out.println("hello there -->>> "+ hotel.getId());

        Bundle bundle = new Bundle();
        bundle.putString("numberOfGuests", String.valueOf(( numberOfGuests)));
        bundle.putString("hotel-id", String.valueOf(hotel.getId()));
        bundle.putString("hotel-name", String.valueOf(hotel.getHotelName() ));
        bundle.putString("hotel-address", String.valueOf(hotel.getAddress() ));
        bundle.putString("room_types", String.valueOf(hotel.getRoomTypes() ));
        bundle.putString("price", String.valueOf(hotel.getPrice() ));
        bundle.putString("checkIn", getArguments().getString("check in date"));
        bundle.putString("checkOut", getArguments().getString("check out date"));
        bundle.putString("total_rooms", String.valueOf(hotel.getTotalRooms() ));


        // set Fragment class Arguments
        HotelDetailsFragment hotelDetailsFragment = new HotelDetailsFragment();
        hotelDetailsFragment.setArguments(bundle);


        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frame_layout, hotelDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

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
