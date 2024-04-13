package com.example.hotel_reservation_system;

//import static com.example.hotel_reservation_system.RetrofitClientInstance.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


//import retrofit.Callback;

class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.ViewHolder> {

    private final ItemClickListener clickListener;
    private List<Hotel> Hotel;
    private LayoutInflater layoutInflater;
    int numberOfGuests;
    HotelListAdapter(Context context, List<Hotel> Hotel, ItemClickListener clickListener, int numberOfGuests) {
        this.layoutInflater = LayoutInflater.from(context);
        this.Hotel = Hotel;
        this.clickListener = clickListener;
        this.numberOfGuests = numberOfGuests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotel_list_layout, parent, false);
        return new ViewHolder(view, clickListener, Hotel, numberOfGuests);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListAdapter.ViewHolder holder, int position) {
        System.out.println();
        String hotelName = Hotel.get(position).getHotelName();
        String hotelPrice = String.valueOf(Hotel.get(position).getPrice()) +"$";
        String totalRooms = "Total rooms: "+String.valueOf(Hotel.get(position).getTotalRooms());
        String hotelAddress = String.valueOf(Hotel.get(position).getAddress());

        // set up the text
        holder.hotelName.setText(hotelName);
        holder.totalRooms.setText(totalRooms);
        holder.hotelPrice.setText(hotelPrice);
        holder.hotelAddress.setText(hotelAddress);
    }

    @Override
    public int getItemCount() {
        if (Hotel != null) {
            return Hotel.size();
        } else {
            return 0;
        }
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView hotelName, hotelPrice, totalRooms, hotelAddress;

        public ViewHolder(@NonNull View itemView, ItemClickListener clickListener, List<Hotel> Hotel, int numberOfGuests) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.hotel_name_text_view);
            hotelPrice = itemView.findViewById(R.id.price_text_view);
            totalRooms = itemView.findViewById(R.id.roomType_text_view);
            hotelAddress = itemView.findViewById(R.id.hotel_address_text_view);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            clickListener.onItemClick(position, new Hotel(Hotel.get(position).getId(), Hotel.get(position).getHotelName(),Hotel.get(position).getAddress(), Hotel.get(position).getRoomTypes(), Hotel.get(position).getPrice(),Hotel.get(position).getTotalRooms()), numberOfGuests);
                        }
                    }
                }
            });

        }


    }

}

