/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doancuoiky;

/**
 *
 * @author DELL
 */
public class Search {
    private String Province;
    private String District;
    private String Town;
    private String OrgName;

    public Search() {
    }

    public Search(String Province, String District, String Town, String OrgName) {
        this.Province = Province;
        this.District = District;
        this.Town = Town;
        this.OrgName = OrgName;
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

    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String OrgName) {
        this.OrgName = OrgName;
    }
    
    
}
