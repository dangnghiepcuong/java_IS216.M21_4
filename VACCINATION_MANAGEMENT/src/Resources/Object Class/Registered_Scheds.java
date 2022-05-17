package doancuoiky;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Registered_Scheds {
    private ArrayList<Schedule> RegList;
    private String PersonalID;
    private Schedule SchedID;
    private String DoseType;
    private int Time;
    private int NO;
    private int Status;
    private byte Image;
    private String Note;

    public Registered_Scheds() {
    }

    public Registered_Scheds(ArrayList<Schedule> RegList, String PersonalID, Schedule SchedID, String DoseType, int Time, int NO, int Status, byte Image, String Note) {
        this.RegList = RegList;
        this.PersonalID = PersonalID;
        this.SchedID = SchedID;
        this.DoseType = DoseType;
        this.Time = Time;
        this.NO = NO;
        this.Status = Status;
        this.Image = Image;
        this.Note = Note;
    }

    public ArrayList<Schedule> getRegList() {
        return RegList;
    }

    public void setRegList(ArrayList<Schedule> RegList) {
        this.RegList = RegList;
    }

    public String getPersonalID() {
        return PersonalID;
    }

    public void setPersonalID(String PersonalID) {
        this.PersonalID = PersonalID;
    }

    public Schedule getSchedID() {
        return SchedID;
    }

    public void setSchedID(Schedule SchedID) {
        this.SchedID = SchedID;
    }

    public String getDoseType() {
        return DoseType;
    }

    public void setDoseType(String DoseType) {
        this.DoseType = DoseType;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int Time) {
        this.Time = Time;
    }

    public int getNO() {
        return NO;
    }

    public void setNO(int NO) {
        this.NO = NO;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public byte getImage() {
        return Image;
    }

    public void setImage(byte Image) {
        this.Image = Image;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }
    
    
}
