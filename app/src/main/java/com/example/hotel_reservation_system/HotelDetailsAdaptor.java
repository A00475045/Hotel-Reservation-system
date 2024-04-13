package com.example.hotel_reservation_system;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HotelDetailsAdaptor extends RecyclerView.Adapter<HotelDetailsAdaptor.ViewHolder> {

    private LayoutInflater layoutInflater;
    private static List<Guest> guests;
    int numberOfGuest;

    public HotelDetailsAdaptor(Context context, int numberOfGuest,List<Guest> guests) {
        this.numberOfGuest = numberOfGuest;
        this.layoutInflater = LayoutInflater.from(context);  // Initialize the LayoutInflater here
        this.guests = guests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (parent.getContext() == null) {
            throw new IllegalStateException("Context should not be null when creating view holders");
        }

//        guests

        View view = layoutInflater.inflate(R.layout.hotel_details_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelDetailsAdaptor.ViewHolder holder, int position) {
        // Since guests list is not initialized or used, you should ensure the logic here matches your needs

//        Guest guest = new Guest();
        if (guests != null && position < guests.size()) {
            Guest guest = guests.get(position);
//            // Presumably set texts based on the 'guest' object
//            holder.nameEditText.setText(guest.getName());
//            holder.numberEditText.setText(guest.getPhone_number());
//            holder.ageEditText.setText(String.valueOf(guest.getAge()));
////            holder.radioGroup.check(guest.getGender() == "male" ? 0 : 1);  // Correct this with actual logic to set checked radio button
//        } else {
        {
            // Default or empty state handling
            holder.nameEditText.setText("");
            holder.numberEditText.setText("");
            holder.ageEditText.setText("");
            holder.radioGroup.clearCheck();  // Clear checks if no data
        }

        holder.nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String text = s.toString();
                guests.get(holder.getAdapterPosition()).setName(text);
                System.out.println(guest.getId() +"  " +guest.getName() +"  " +guest.getAge() +"  " +guest.getPhone_number() +"  " +guest.getGuest_of() +"  " +guest.getGender());
            }

            @Override
            public void afterTextChanged(Editable s) {
                guests.get(holder.getAdapterPosition()).setName(s.toString());
            }
        });
        holder.numberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                guests.get(holder.getAdapterPosition()).setPhone_number(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                guests.get(holder.getAdapterPosition()).setPhone_number(s.toString());
            }
        });
        holder.ageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && !s.toString().isEmpty()) {
                    try {
                        int number = Integer.parseInt(s.toString());
                        guests.get(holder.getAdapterPosition()).setAge(number);
                    } catch (Exception e) {
                        Log.e("NumberFormatException", "Invalid input for number");
                        guests.get(holder.getAdapterPosition()).setAge(0); // Set to default or handle error
                    }
                } else {
                    guests.get(holder.getAdapterPosition()).setAge(0); // Default value or handle empty input
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                guests.get(holder.getAdapterPosition()).setAge(Integer.parseInt(s.toString()));
            }
        });
        holder.radioGroup.setOnCheckedChangeListener(null); // Remove previous listeners

        if (guest.getGender().equals("male")) {
            holder.radioGroup.check(R.id.radioButton1);
        } else if (guest.getGender().equals("female")) {
            holder.radioGroup.check(R.id.radioButton2);
        } else if (guest.getGender().equals("others")) {
            holder.radioGroup.check(R.id.radioButton3);
        } else {
            holder.radioGroup.clearCheck();
        }

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                int checkedId = radioButton.getId();
                if (checkedId == R.id.radioButton1) {
                    guests.get(holder.getAdapterPosition()).setGender("male");
                } else if (checkedId == R.id.radioButton2) {
                    guests.get(holder.getAdapterPosition()).setGender("female");
                } else if (checkedId == R.id.radioButton3) {
                    guests.get(holder.getAdapterPosition()).setGender("other");
                }

                System.out.println(guest.getId() +"  " +guest.getName() +"  " +guest.getAge() +"  " +guest.getPhone_number() +"  " +guest.getGuest_of() +"  " +guest.getGender());
            }
        });}
    }

    @Override
    public int getItemCount() {
        return numberOfGuest;  // Return the number of guests to display
    }

    public List<Guest> getGuestList() {
        return guests;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, numberTextView, ageTextView;
        static EditText nameEditText;
        EditText numberEditText;
        EditText ageEditText;
        static RadioGroup radioGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameEditText = itemView.findViewById(R.id.name_edit_text);
            numberEditText = itemView.findViewById(R.id.number_edit_text);
            ageEditText = itemView.findViewById(R.id.age_edit_text);
            radioGroup = itemView.findViewById(R.id.radioGroup);

//            setupListeners();
        }



    }

}
