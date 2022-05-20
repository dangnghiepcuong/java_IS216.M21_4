/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doancuoiky;

import java.time.LocalDate;

/**
 *
 * @author LeHoangDuyen
 */
public class Person {
    // Thuộc tính mã định danh của người dùng
    private String ID;
    private String LastName;
    private String FirstName;
    private LocalDate Birthday;
    private Number Gender;
    private String HomeTown;
    private Number Province;
    private String District;
    private String Town;
    private String Street;
    private String Phone;
    private String Email;
    private String Guardian;
    private String Note;

    public Person() {
    }

    public Person(String ID, String LastName, String FirstName, LocalDate Birthday, Number Gender, String HomeTown, Number Province, String District, String Town, String Street, String Phone, String Email, String Guardian, String Note) {
        this.ID = ID;
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.Birthday = Birthday;
        this.Gender = Gender;
        this.HomeTown = HomeTown;
        this.Province = Province;
        this.District = District;
        this.Town = Town;
        this.Street = Street;
        this.Phone = Phone;
        this.Email = Email;
        this.Guardian = Guardian;
        this.Note = Note;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public LocalDate getBirthday() {
        return Birthday;
    }

    public void setBirthday(LocalDate Birthday) {
        this.Birthday = Birthday;
    }

    public Number getGender() {
        return Gender;
    }

    public void setGender(Number Gender) {
        this.Gender = Gender;
    }

    public String getHomeTown() {
        return HomeTown;
    }

    public void setHomeTown(String HomeTown) {
        this.HomeTown = HomeTown;
    }

    public Number getProvince() {
        return Province;
    }

    public void setProvince(Number Province) {
        this.Province = Province;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    public String getTown() {
        return Town;
    }

    public void setTown(String Town) {
        this.Town = Town;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getGuardian() {
        return Guardian;
    }

    public void setGuardian(String Guardian) {
        this.Guardian = Guardian;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }
    
    
}