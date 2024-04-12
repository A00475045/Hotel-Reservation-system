package com.example.hotel_reservation_system;

public class Hotel {

    Integer id;
    String hotel_name;
    String address;
    Integer room_types;
    Integer price;
    Integer total_rooms;

    public Hotel(Integer id, String hotel_name, String address, Integer room_types, Integer price, Integer total_rooms) {
        this.id = id;
        this.hotel_name = hotel_name;
        this.address = address;
        this.room_types = room_types;
        this.price = price;
        this.total_rooms = total_rooms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRoom_types() {
        return room_types;
    }

    public void setRoom_types(Integer room_types) {
        this.room_types = room_types;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotal_rooms() {
        return total_rooms;
    }

    public void setTotal_rooms(Integer total_rooms) {
        this.total_rooms = total_rooms;
    }
}
