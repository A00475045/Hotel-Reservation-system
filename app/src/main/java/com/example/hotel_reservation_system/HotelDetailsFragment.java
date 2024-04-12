package com.example.hotel_reservation_system;

import static android.os.Build.VERSION_CODES.N;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HotelDetailsFragment extends Fragment {

    View view;
    TextView messageTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_details_layout,container,false);
        return view;
    }
//
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        messageTextView = view.findViewById(R.id.message_text_view);
        messageTextView.setText("You are about to book Rooms in "+ getArguments().getString("hotel-name") + " for " + getArguments().getString("numberOfGuests") + " guests, from " + getArguments().getString("checkIn") +" to "+ getArguments().getString("checkOut"));

    }

//        titleTextView = view.findViewById(R.id.title_text_view);
//        titleTextView.setText(R.string.title_text_view);
//        checkInTextView = view.findViewById(R.id.CheckIn_Text_View);
//        checkInTextView.setText(R.string.CheckIn_Text_View);
//        checkOutTextView = view.findViewById(R.id.CheckOut_Text_View);
//        checkOutTextView.setText(R.string.CheckOut_Text_View);
//        numOfGTextView = view.findViewById(R.id.Number_of_Guest);
//        numOfGTextView.setText(R.string.Number_of_Guest);
//        nameOfGTextView = view.findViewById(R.id.name_text_view);
//        nameOfGTextView.setText(R.string.name_text_view);
//
//
//        EditText editTextGuestsCount = view.findViewById(R.id.guests_count_edit_text);
//        EditText editTextGuestName = view.findViewById(R.id.name_edit_text);
//        DatePicker datePickerCheckIn = view.findViewById(R.id.CheckIn_date_picker);
//        DatePicker datePickerCheckOut = view.findViewById(R.id.CheckOut_date_picker);
//        Button confirmButton = view.findViewById(R.id.confirm_my_search_button);
//        Button searchButton = view.findViewById(R.id.search_button);
//        Button retrieveButton = view.findViewById(R.id.retrieve_button);
//        Button resetButton = view.findViewById(R.id.clear_button);
//
//
//
//        resetButton.setOnClickListener(v ->{
//
//
//
//            EditText editTextGuestsCount1 = getView().findViewById(R.id.guests_count_edit_text);
//            editTextGuestsCount1.setText("");
//            EditText editTextGuestName1 = getView().findViewById(R.id.name_edit_text);
//            editTextGuestName1.setText("");
//            Calendar calendar = Calendar.getInstance(); // Gets the current date and time
//            int year = calendar.get(Calendar.YEAR); // Current year
//            int month = calendar.get(Calendar.MONTH); // Current month
//            int day = calendar.get(Calendar.DAY_OF_MONTH); // Current day of the month
//            datePickerCheckIn.updateDate(year, month, day);
//            datePickerCheckOut.updateDate(year, month, day);
//
//
//        });
//
//        searchButton.setOnClickListener(v -> {
//
//            if(editTextGuestsCount.getText().toString().isEmpty()  || editTextGuestName.getText().toString().isEmpty() ){
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("Error")
//                        .setMessage("Please fill all the details (guest-Count and Host-name)")
//                        .setPositiveButton("OK", (dialog, which) -> {
//
//                        }).show();
//                return;
//            }
//            if(!editTextGuestName.getText().toString().matches("^[a-zA-Z ]+$")){
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("Error")
//                        .setMessage("Host name can only have a-z,A-Z, and space ")
//                        .setPositiveButton("OK", (dialog, which) -> {
//
//                        }).show();
//                return;
//            }
//
//            int checkInYear = datePickerCheckIn.getYear();
//            int checkInMonth = datePickerCheckIn.getMonth();
//            int checkInDay = datePickerCheckIn.getDayOfMonth();
//            Calendar calendar1 = Calendar.getInstance();
//            calendar1.set(checkInYear, checkInMonth, checkInDay);
//            calendar1.set(Calendar.HOUR_OF_DAY, 0); // Normalize to the start of the day
//            calendar1.set(Calendar.MINUTE, 0);
//            calendar1.set(Calendar.SECOND, 0);
//            calendar1.set(Calendar.MILLISECOND, 0);
//
//            int checkOutYear = datePickerCheckOut.getYear();
//            int checkOutMonth = datePickerCheckOut.getMonth();
//            int checkOutDay = datePickerCheckOut.getDayOfMonth();
//            Calendar calendar2 = Calendar.getInstance();
//            calendar2.set(checkOutYear, checkOutMonth, checkOutDay);
//            calendar2.set(Calendar.HOUR_OF_DAY, 0); // Normalize to the start of the day
//            calendar2.set(Calendar.MINUTE, 0);
//            calendar2.set(Calendar.SECOND, 0);
//            calendar2.set(Calendar.MILLISECOND, 0);
//            // Check if checkin is before current date
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY, 0); // Normalize to the start of the day
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//            calendar.set(Calendar.MILLISECOND, 0);
//            if(calendar1.before(calendar)){
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("Error")
//                        .setMessage("CheckIn date can't be before current date!!")
//                        .setPositiveButton("OK", (dialog, which) -> {
//
//                        })
//                        .show();
//                return;
//            }
//            // Check if checkout is before current checkin date
//
//            if(calendar2.before(calendar1)){
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("Error")
//                        .setMessage("CheckOut date can't be before the checkin date!!")
//                        .setPositiveButton("OK", (dialog, which) -> {
//
//                        })
//                        .show();
//                return;
//            }
//
//
//
//
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            String checkInDate = simpleDateFormat.format(calendar1.getTime());
////            checkInDate = getDateFromCalendar(datePickerCheckIn);
//
//
//
//            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
//            String checkOutDate = simpleDateFormat.format(calendar2.getTime());
//
//
////            checkOutDate = getDateFromCalendar(datePickerCheckOut);
//            //Get input of guests count
//            String numberOfGuests = editTextGuestsCount.getText().toString();
//            String name = editTextGuestName.getText().toString();
//
//            Bundle bundle = new Bundle();
//            bundle.putString("check in date", checkInDate);
//            bundle.putString("check out date", checkOutDate);
//            bundle.putString("number of guests", numberOfGuests);
//            bundle.putString("name", name);
//
//
//            // set Fragment class Arguments
//            HotelListFragment hotelsListFragment = new HotelListFragment();
//            hotelsListFragment.setArguments(bundle);
//
//
//            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
//
//            fragmentTransaction.replace(R.id.frame_layout, hotelsListFragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//        });
//
//        confirmButton.setOnClickListener(v -> {
//
//            if(editTextGuestsCount.getText().toString().isEmpty()  || editTextGuestName.getText().toString().isEmpty() ){
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("Error")
//                        .setMessage("Please fill all the details (guest-Count and Host-name)")
//                        .setPositiveButton("OK", (dialog, which) -> {
//
//                        })
//                        .setNegativeButton("Cancel", (dialog, which) -> {
//                            Toast.makeText(getActivity(), "Details Required for next step!", Toast.LENGTH_SHORT).show();
//                        })
//                        .show();
//                return;
//            }
//            if(!editTextGuestName.getText().toString().matches("^[a-zA-Z ]+$")){
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("Error")
//                        .setMessage("Host name can only have a-z,A-Z, and space ")
//                        .setPositiveButton("OK", (dialog, which) -> {
//
//                        }).show();
//                return;
//            }
//
//            String guestsCount = editTextGuestsCount.getText().toString();
//            String guestName = editTextGuestName.getText().toString();
//            int checkInYear = datePickerCheckIn.getYear();
//            int checkInMonth = datePickerCheckIn.getMonth();
//            int checkInDay = datePickerCheckIn.getDayOfMonth();
//            String checkInDate = checkInDay + "/" + (checkInMonth + 1) + "/" + checkInYear;
//
//            int checkOutYear = datePickerCheckOut.getYear();
//            int checkOutMonth = datePickerCheckOut.getMonth();
//            int checkOutDay = datePickerCheckOut.getDayOfMonth();
//            String checkOutDate = checkOutDay + "/" + (checkOutMonth + 1) + "/" + checkOutYear;
//
//
//            String message = "Name: " + guestName + "\nGuests: " + guestsCount + "\nCheck-In Date: " + checkInDate + "\nCheck-Out Date: " + checkOutDate;
//
//
//            new AlertDialog.Builder(getActivity())
//                    .setTitle("Reservation Details")
//                    .setMessage(message)
//                    .setPositiveButton("OK", (dialog, which) -> {
//                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//
//                        editor.putString("guestsCount", guestsCount);
//                        editor.putString("guestName", guestName);
//                        editor.apply();
//
//
//                        Toast.makeText(getActivity(), "Details Saved!", Toast.LENGTH_SHORT).show();
//                    })
//                    .setNegativeButton("Cancel", (dialog, which) -> {
//
//                    })
//                    .show();
//        });
//
//
//
//
//
//        retrieveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE);
//                String guestsCount = sharedPreferences.getString("guestsCount", "1"); // Default to 1 if not found
//                String guestName = sharedPreferences.getString("guestName", ""); // Default to 1 if not found
//                EditText editTextGuestsCount = getView().findViewById(R.id.guests_count_edit_text);
//                editTextGuestsCount.setText(guestsCount);
//                EditText editTextGuestName = getView().findViewById(R.id.name_edit_text);
//                editTextGuestName.setText(guestName);
//            }
//        });
////
////        searchButton = view.findViewById(R.id.search_button);
////        searchButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////
////            }
////        });
////        mainLayout = view.findViewById(R.id.main_layout);
//
//    }
//


}
