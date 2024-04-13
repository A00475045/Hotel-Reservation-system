package com.example.hotel_reservation_system;

public class Hotel {

    Integer id;
    String hotelName;
    String address;
    Integer roomTypes;
    Integer price;
    Integer totalRooms;

    public Hotel(Integer id, String hotelName, String address, Integer roomTypes, Integer price, Integer totalRooms) {
        this.id = id;
        this.hotelName = hotelName;
        this.address = address;
        this.roomTypes = roomTypes;
        this.price = price;
        this.totalRooms = totalRooms;


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(Integer roomTypes) {
        this.roomTypes = roomTypes;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(Integer totalRooms) {
        this.totalRooms = totalRooms;
    }
}
