package Data_Processor;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class RegisteredScheds {
    private String PersonalID;
    private Schedule Sched = new Schedule();
    private Organization Org = new Organization();
    private String DoseType;
    private int Time;
    private int NO;
    private int Status;
    private byte Image;
    private String Note;

    public RegisteredScheds() {
    }

    public RegisteredScheds(String PersonalID, Schedule Sched, Organization Org, String DoseType, int Time, int NO, int Status, byte Image, String Note) {
        this.PersonalID = PersonalID;
        this.Sched = Sched;
        this.Org = Org;
        this.DoseType = DoseType;
        this.Time = Time;
        this.NO = NO;
        this.Status = Status;
        this.Image = Image;
        this.Note = Note;
    }

    public String getPersonalID() {
        return PersonalID;
    }

    public void setPersonalID(String PersonalID) {
        this.PersonalID = PersonalID;
    }

    public Schedule getSched() {
        return Sched;
    }

    public void setSched(Schedule Sched) {
        this.Sched = Sched;
    }

    public Organization getOrg() {
        return Org;
    }

    public void setOrg(Organization org) {
        Org = org;
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
