/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;


/**
 *
 * @author LeHoangDuyen
 */
public class Person {
    // Thuộc tính mã định danh của người dùng
    private String ID;
    private String LastName;
    private String FirstName;
    private String Birthday;
    private int Gender;
    private String HomeTown;
    private String Province;
    private String District;
    private String Town;
    private String Street;
    private String Phone;
    private String Email;
    private String Guardian;
    private String Note;

    public Person() {
    }

    public Person(String ID, String LastName, String FirstName, String Birthday, int Gender, String HomeTown, String Province, String District, String Town, String Street, String Phone, String Email, String Guardian, String Note) {
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

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }

    public String getHomeTown() {
        return HomeTown;
    }

    public void setHomeTown(String HomeTown) {
        this.HomeTown = HomeTown;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
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
    
    public String getFullName()
    {
        return LastName + " " + FirstName;
    }
}