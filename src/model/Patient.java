/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author WELCOME
 */
public class Patient {

    private int patientId;
    private String patientName;
    private int age;
    private String gender;
    private String phone;
    private String address;
    private String disease;

    public Patient(int patientId, String patientName, int age,
                   String gender, String phone, String address,
                   String disease) {

        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.disease = disease;
    }
    public int getPatientId() {
    return patientId;
}

public String getPatientName() {
    return patientName;
}

public int getAge() {
    return age;
}

public String getGender() {
    return gender;
}

public String getPhone() {
    return phone;
}

public String getAddress() {
    return address;
}

public String getDisease() {
    return disease;
}
}