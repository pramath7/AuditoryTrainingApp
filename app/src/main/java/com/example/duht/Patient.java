package com.example.duht;

public class Patient{
    public String fullNmae;
    public  String age;
    public  String phoneNumber;
    public  String email;
    public  String password;
    public  String gender;

    public Patient(){}

    public Patient(String fullNmae, String age, String phoneNumber, String email, String password, String gender) {
        this.fullNmae = fullNmae;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }
}
