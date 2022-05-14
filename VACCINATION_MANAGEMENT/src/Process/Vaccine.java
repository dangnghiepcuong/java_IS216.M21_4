/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

/**
 *
 * @author DELL
 */
public class Vaccine {
    private String ID;
    private String Name;
    private String Technology;
    private String Country;
    private String Note;

    public Vaccine() {
    }

    public Vaccine(String ID, String Name, String Technology, String Country, String Note) {
        this.ID = ID;
        this.Name = Name;
        this.Technology = Technology;
        this.Country = Country;
        this.Note = Note;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getTechnology() {
        return Technology;
    }

    public void setTechnology(String Technology) {
        this.Technology = Technology;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }
    
    
}
