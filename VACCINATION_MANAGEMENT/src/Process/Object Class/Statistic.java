/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doancuoiky;

/**
 *
 * @author DELL
 */
public class Statistic {
    private String Province;
    private String District;
    private String Town;
    private int Age;
    private int Gender;
    private String DoseType;

    public Statistic() {
    }

    public Statistic(String Province, String District, String Town, int Age, int Gender, String DoseType) {
        this.Province = Province;
        this.District = District;
        this.Town = Town;
        this.Age = Age;
        this.Gender = Gender;
        this.DoseType = DoseType;
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

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }

    public String getDoseType() {
        return DoseType;
    }

    public void setDoseType(String DoseType) {
        this.DoseType = DoseType;
    }
    
    
}
