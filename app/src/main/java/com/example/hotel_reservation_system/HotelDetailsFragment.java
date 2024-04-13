package com.example.hotel_reservation_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelDetailsFragment extends Fragment {

    View view;
    TextView messageTextView;
    Button cnfBokkingBtn;

    ProgressBar progressBar;
    private HotelDetailsAdaptor hotelDetailsAdaptor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_details_fragment,container,false);
        return view;
    }
//
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Guest> guests = new ArrayList<>();

        messageTextView = view.findViewById(R.id.message_text_view);
        messageTextView.setText("You are about to book Rooms in " + getArguments().getString("hotel-name") + " for " + getArguments().getString("numberOfGuests") + " guests, from " + getArguments().getString("checkIn") + " to " + getArguments().getString("checkOut"));
        progressBar = view.findViewById(R.id.progress_bar);

        RecyclerView recyclerView = view.findViewById(R.id.Recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        for (int i = 0; i < Integer.parseInt(getArguments().getString("numberOfGuests")); i++) {
            guests.add(new Guest());
        }
        hotelDetailsAdaptor = new HotelDetailsAdaptor(getActivity(), Integer.parseInt(getArguments().getString("numberOfGuests")), guests);
        recyclerView.setAdapter(hotelDetailsAdaptor);


        cnfBokkingBtn = view.findViewById(R.id.booking_button);
        cnfBokkingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Guest> updatedGuests = hotelDetailsAdaptor.getGuestList();
                // Now you can use the updatedGuests list to save or process data
//                saveGuestData(updatedGuests);
                for (int i = 0; i < updatedGuests.size(); i++) {

                    if (updatedGuests.get(i).getName().isEmpty() || updatedGuests.get(i).getAge() == null || updatedGuests.get(i).getPhone_number() == null || updatedGuests.get(i).getPhone_number().toString().length() < 10 || updatedGuests.get(i).getGender().isEmpty()) {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("All fields Necessary")
                                .setMessage("Please make sure all the fields are filled properly, phone Number is atleast 10 digits!")
                                .setPositiveButton("OK", (dialog, which) -> {

                                }).show();
                        return;
                    }

                    System.out.println(updatedGuests.get(i).getId() + "  " + updatedGuests.get(i).getName() + "  " + updatedGuests.get(i).getAge() + "  " + updatedGuests.get(i).getPhone_number() + "  " + updatedGuests.get(i).getGuest_of() + "  " + updatedGuests.get(i).getGender());
                }
                List<Guest> gg = new ArrayList<>();
                gg.add(updatedGuests.get(0));
//                List<Guest> gg = new ArrayList<>();

//********************************************************************************************
                DataService service = RetrofitClientInstance.getRetrofitInstance().create(DataService.class);
                Call<List<Guest>> call = service.addGuests(gg);
                progressBar.setVisibility(View.VISIBLE);

                call.enqueue(new Callback<List<Guest>>() {

                    @Override
                    public void onResponse(Call<List<Guest>> call, Response<List<Guest>> response) {

                        if (response.isSuccessful()) {


                            List<Guest> savedGuests = response.body();
                            gg.remove(0);
                            gg.add(0, savedGuests.get(0));
//                            List<Guest> gg = new ArrayList<>();
//                            gg.add(savedGuests);
                            for (int i = 1; i < updatedGuests.size(); i++) {

                                System.out.println("---> " + savedGuests.get(0).getId());
                                updatedGuests.get(i).setGuest_of(savedGuests.get(0).getId());
                            }
                            updatedGuests.remove(0);
//                            ----------------------------------
//                            DataService service = RetrofitClientInstance.getRetrofitInstance().create(DataService.class);


                            Call<List<Guest>> call1 = service.addGuests(updatedGuests);

                            if (updatedGuests.size() > 0) {
                                call1.enqueue(new Callback<List<Guest>>() {

                                    @Override
                                    public void onResponse(Call<List<Guest>> call, Response<List<Guest>> response) {
                                        System.out.println("res: " + call);
                                        if (response.isSuccessful()) {
                                            System.out.println("in success");
                                            // Success! Handle the response data
                                            List<Guest> data = response.body();
                                            data.add(0, savedGuests.get(0));

//                                        ================================
                                            List<Booking> bookings = new ArrayList<>();
                                            for (int i = 0; i < data.size(); i++) {
                                                gg.add(data.get(i));

//                                            System.out.println("Mubarak ho!!");
//
//                                            System.out.println(data.get(i).getId() +"  " +data.get(i).getName() +"  " +data.get(i).getAge() +"  " +data.get(i).getPhone_number() +"  " +data.get(i).getGuest_of() +"  " + data.get(i).getGender());
                                            }
//                                        ================================

                                            for (int i = 0; i < gg.size(); i++) {
                                                bookings.add(new Booking(gg.get(i).getId(), Integer.parseInt(getArguments().getString("hotel-id")), getArguments().getString("checkIn"), getArguments().getString("checkOut")));
                                            }

                                            Call<List<Booking>> call2 = service.addBookings(bookings);

//                                    ==============
                                            call2.enqueue(new Callback<List<Booking>>() {

                                                @Override
                                                public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                                                    System.out.println("res: " + call);
                                                    if (response.isSuccessful()) {
                                                        System.out.println("in success");
                                                        // Success! Handle the response data
                                                        List<Booking> data = response.body();
                                                        progressBar.setVisibility(View.GONE);

//                                                    sssssssssssssssssssssssssssssssssssssssss
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("hostName", gg.get(0).getName());
                                                        bundle.putString("bookingID", data.get(0).getId().toString());
                                                        bundle.putString("hotelName", getArguments().getString("hotel-name"));
//                                                    bundle.putString("name", name);


                                                        // set Fragment class Arguments
                                                        SuccessFragment successFragment = new SuccessFragment();
                                                        successFragment.setArguments(bundle);


                                                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();

                                                        fragmentTransaction.replace(R.id.frame_layout, successFragment);
                                                        fragmentTransaction.addToBackStack(null);
                                                        fragmentTransaction.commit();


                                                    } else {
                                                        System.out.println(bookings);
                                                        progressBar.setVisibility(View.GONE);
                                                        new AlertDialog.Builder(getActivity())
                                                                .setTitle("Server Error/Bad request1")
                                                                .setMessage("There was a Server Error/ Bad Request error in Booking your hotel")
                                                                .setPositiveButton("OK", (dialog, which) -> {

                                                                    Bundle bundle = new Bundle();
                                                                    bundle.putString("hostName", gg.get(0).getName());
                                                                    bundle.putString("bookingID", data.get(0).getId().toString());
                                                                    bundle.putString("hotelName", getArguments().getString("hotel-name"));
//                                                    bundle.putString("name", name);


                                                                    // set Fragment class Arguments
                                                                    SuccessFragment successFragment = new SuccessFragment();
                                                                    successFragment.setArguments(bundle);


                                                                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();

                                                                    fragmentTransaction.replace(R.id.frame_layout, successFragment);
                                                                    fragmentTransaction.addToBackStack(null);
                                                                    fragmentTransaction.commit();

                                                                }).show();

                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<List<Booking>> call, Throwable t) {
                                                    progressBar.setVisibility(View.GONE);
                                                    new AlertDialog.Builder(getActivity())
                                                            .setTitle("Network Error2")
                                                            .setMessage("There was an Error in Booking your hotel")
                                                            .setPositiveButton("OK", (dialog, which) -> {
                                                                Bundle bundle = new Bundle();
                                                                bundle.putString("hostName", gg.get(0).getName());
                                                                bundle.putString("bookingID", data.get(0).getId().toString());
                                                                bundle.putString("hotelName", getArguments().getString("hotel-name"));
//                                                    bundle.putString("name", name);


                                                                // set Fragment class Arguments
                                                                SuccessFragment successFragment = new SuccessFragment();
                                                                successFragment.setArguments(bundle);


                                                                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();

                                                                fragmentTransaction.replace(R.id.frame_layout, successFragment);
                                                                fragmentTransaction.addToBackStack(null);
                                                                fragmentTransaction.commit();
                                                            }).show();
                                                }
                                            });
//                                    ==============

                                        } else {
                                            progressBar.setVisibility(View.GONE);
                                            new AlertDialog.Builder(getActivity())
                                                    .setTitle("Server Error/Bad request1")
                                                    .setMessage("There was a Server Error/ Bad Request error in Booking your hotel")
                                                    .setPositiveButton("OK", (dialog, which) -> {
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("hostName", gg.get(0).getName());
                                                        bundle.putString("bookingID", "23");
                                                        bundle.putString("hotelName", getArguments().getString("hotel-name"));
//                                                    bundle.putString("name", name);


                                                        // set Fragment class Arguments
                                                        SuccessFragment successFragment = new SuccessFragment();
                                                        successFragment.setArguments(bundle);


                                                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();

                                                        fragmentTransaction.replace(R.id.frame_layout, successFragment);
                                                        fragmentTransaction.addToBackStack(null);
                                                        fragmentTransaction.commit();
                                                    }).show();

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<Guest>> call, Throwable t) {
                                        progressBar.setVisibility(View.GONE);
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle("Network Error2")
                                                .setMessage("There was an Error in Booking your hotel")
                                                .setPositiveButton("OK", (dialog, which) -> {
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("hostName", gg.get(0).getName());
                                                    bundle.putString("bookingID", "23");
                                                    bundle.putString("hotelName", getArguments().getString("hotel-name"));
//                                                    bundle.putString("name", name);


                                                    // set Fragment class Arguments
                                                    SuccessFragment successFragment = new SuccessFragment();
                                                    successFragment.setArguments(bundle);


                                                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();

                                                    fragmentTransaction.replace(R.id.frame_layout, successFragment);
                                                    fragmentTransaction.addToBackStack(null);
                                                    fragmentTransaction.commit();
                                                }).show();
                                    }
                                });
                            } else {

//                                Call to the Booking api
                                List<Booking> bookings = new ArrayList<>();
                                bookings.add(new Booking(gg.get(0).getId(), Integer.parseInt(getArguments().getString("hotel-id")), getArguments().getString("checkIn"), getArguments().getString("checkOut")));

                                Call<List<Booking>> call2 = service.addBookings(bookings);
                                call2.enqueue(new Callback<List<Booking>>() {

                                    @Override
                                    public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                                        System.out.println("res: " + call);
                                        progressBar.setVisibility(View.GONE);
                                        if (response.isSuccessful()) {
                                            System.out.println("in success");
                                            // Success! Handle the response data
                                            List<Booking> data = response.body();

//                                                    sssssssssssssssssssssssssssssssssssssssss
                                            Bundle bundle = new Bundle();
                                            bundle.putString("hostName", gg.get(0).getName());
                                            bundle.putString("bookingID", data.get(0).getId().toString());
                                            bundle.putString("hotelName", getArguments().getString("hotel-name"));
//                                                    bundle.putString("name", name);


                                            // set Fragment class Arguments
                                            SuccessFragment successFragment = new SuccessFragment();
                                            successFragment.setArguments(bundle);


                                            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();

                                            fragmentTransaction.replace(R.id.frame_layout, successFragment);
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();

                                        } else {
                                            progressBar.setVisibility(View.GONE);

                                            new AlertDialog.Builder(getActivity())
                                                    .setTitle("Server Error/Bad request1")
                                                    .setMessage("There was a Server Error/ Bad Request error in Booking your hotel")
                                                    .setPositiveButton("OK", (dialog, which) -> {
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("hostName", gg.get(0).getName());
                                                        bundle.putString("bookingID", "23");
                                                        bundle.putString("hotelName", getArguments().getString("hotel-name"));
//                                                    bundle.putString("name", name);


                                                        // set Fragment class Arguments
                                                        SuccessFragment successFragment = new SuccessFragment();
                                                        successFragment.setArguments(bundle);


                                                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();

                                                        fragmentTransaction.replace(R.id.frame_layout, successFragment);
                                                        fragmentTransaction.addToBackStack(null);
                                                        fragmentTransaction.commit();
                                                    }).show();

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<Booking>> call, Throwable t) {
                                        progressBar.setVisibility(View.GONE);
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle("Network Error2")
                                                .setMessage("There was an Error in Booking your hotel")
                                                .setPositiveButton("OK", (dialog, which) -> {
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("hostName", gg.get(0).getName());
                                                    bundle.putString("bookingID", "23");
                                                    bundle.putString("hotelName", getArguments().getString("hotel-name"));
//                                                    bundle.putString("name", name);


                                                    // set Fragment class Arguments
                                                    SuccessFragment successFragment = new SuccessFragment();
                                                    successFragment.setArguments(bundle);


                                                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();

                                                    fragmentTransaction.replace(R.id.frame_layout, successFragment);
                                                    fragmentTransaction.addToBackStack(null);
                                                    fragmentTransaction.commit();
                                                }).show();
                                    }
                                });
                            }

//                            ----------------------------------
                        } else {
                            progressBar.setVisibility(View.GONE);

                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Server Error/Bad request3")
                                    .setMessage("There was a Server Error/ Bad Request error in Booking your hotel")
                                    .setPositiveButton("OK", (dialog, which) -> {

                                    }).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Guest>> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);

                        System.out.println("in error" + t.getMessage());
                        System.out.println("in error" + call);
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Network Error4")
                                .setMessage("There was an Error in Booking your hotel")
                                .setPositiveButton("OK", (dialog, which) -> {

                                }).show();
//
                    }
                });

//*********************************

            }
//


        });
    }
}
