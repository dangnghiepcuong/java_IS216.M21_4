/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import java.time.LocalDate;

/**
 *
 * @author LeHoangDuyen
 */
public class Health {
    private int ID;
    private Person person = new Person();
    private LocalDate FilledDate;
    private String Healths;
    private String Note;

    public Health() {
    }

    public Health(int ID, Person person, LocalDate filledDate, String healths, String note) {
        this.ID = ID;
        this.person = person;
        FilledDate = filledDate;
        Healths = healths;
        Note = note;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
