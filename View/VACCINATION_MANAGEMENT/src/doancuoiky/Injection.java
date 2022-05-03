/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doancuoiky;

/**
 *
 * @author DELL
 */
public class Injection {
    private String PersonalID;
    private int InjNo;
    private String SchedID;
    private String DoseType;
    private String Note;

    public Injection() {
    }

    public Injection(String PersonalID, int InjNo, String SchedID, String DoseType, String Note) {
        this.PersonalID = PersonalID;
        this.InjNo = InjNo;
        this.SchedID = SchedID;
        this.DoseType = DoseType;
        this.Note = Note;
    }

    public String getPersonalID() {
        return PersonalID;
    }

    public void setPersonalID(String PersonalID) {
        this.PersonalID = PersonalID;
    }

    public int getInjNo() {
        return InjNo;
    }

    public void setInjNo(int InjNo) {
        this.InjNo = InjNo;
    }

    public String getSchedID() {
        return SchedID;
    }

    public void setSchedID(String SchedID) {
        this.SchedID = SchedID;
    }

    public String getDoseType() {
        return DoseType;
    }

    public void setDoseType(String DoseType) {
        this.DoseType = DoseType;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }
    
    
    
}
