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
public class Health {
    private String PersonalID;
    private LocalDate FilledDate;
    private String Healths;
    private String Note;

    public Health() {
    }

    public Health(String PersonalID, LocalDate FilledDate, String Healths, String Note) {
        this.PersonalID = PersonalID;
        this.FilledDate = FilledDate;
        this.Healths = Healths;
        this.Note = Note;
    }

    public String getPersonalID() {
        return PersonalID;
    }

    public void setPersonalID(String PersonalID) {
        this.PersonalID = PersonalID;
    }

    public LocalDate getFilledDate() {
        return FilledDate;
    }

    public void setFilledDate(LocalDate FilledDate) {
        this.FilledDate = FilledDate;
    }

    public String getHealths() {
        return Healths;
    }

    public void setHealths(String Healths) {
        this.Healths = Healths;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }
    
    
}
