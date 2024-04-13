package com.example.hotel_reservation_system;

public class Guest {

    Integer id;
    String name;
    String phoneNumber;
    Integer age;
    Integer guestOf;
    String gender;

    public Guest() {
        this.id = null;
        this.name = "";
        this.phoneNumber = null;
        this.age = null;
        this.guestOf = null;
        this.gender = "";
    }

    public Guest(Integer id, String name, String phone_number, Integer age, Integer guest_of, String gender) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phone_number;
        this.age = age;
        this.guestOf = guest_of;
        this.gender = gender;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phoneNumber;
    }

    public void setPhone_number(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGuest_of() {
        return guestOf;
    }

    public void setGuest_of(Integer guest_of) {
        this.guestOf = guest_of;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
