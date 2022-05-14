package Process;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author DELL
 */
public class Schedule {
    private String ID;
    private Organization Org = new Organization();
    private String OnDate;
    private String VaccineID;
    private String Serial;
    private int LimitDay;
    private int LimitNoon;
    private int LimitNight;
    private int DayRegistered;
    private int NoonRegistered;
    private int NightRegistered;
    private String Note;

    public Schedule() {
    }

    public Schedule(String ID, Organization Org, String OnDate, String VaccineID, String Serial, int LimitDay, int LimitNoon, int LimitNight, int DayRegistered, int NoonRegistered, int NightRegistered, String Note) {
        this.ID = ID;
        this.Org = Org;
        this.OnDate = OnDate;
        this.VaccineID = VaccineID;
        this.Serial = Serial;
        this.LimitDay = LimitDay;
        this.LimitNoon = LimitNoon;
        this.LimitNight = LimitNight;
        this.DayRegistered = DayRegistered;
        this.NoonRegistered = NoonRegistered;
        this.NightRegistered = NightRegistered;
        this.Note = Note;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Organization getOrg() {
        return Org;
    }

    public void setOrg(Organization Org) {
        this.Org = Org;
    }

    public String getOnDate() {
        return OnDate;
    }

    public void setOnDate(String OnDate) {
        this.OnDate = OnDate;
    }

    public String getVaccineID() {
        return VaccineID;
    }

    public void setVaccineID(String VaccineID) {
        this.VaccineID = VaccineID;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String Serial) {
        this.Serial = Serial;
    }

    public int getLimitDay() {
        return LimitDay;
    }

    public void setLimitDay(int LimitDay) {
        this.LimitDay = LimitDay;
    }

    public int getLimitNoon() {
        return LimitNoon;
    }

    public void setLimitNoon(int LimitNoon) {
        this.LimitNoon = LimitNoon;
    }

    public int getLimitNight() {
        return LimitNight;
    }

    public void setLimitNight(int LimitNight) {
        this.LimitNight = LimitNight;
    }

    public int getDayRegistered() {
        return DayRegistered;
    }

    public void setDayRegistered(int DayRegistered) {
        this.DayRegistered = DayRegistered;
    }

    public int getNoonRegistered() {
        return NoonRegistered;
    }

    public void setNoonRegistered(int NoonRegistered) {
        this.NoonRegistered = NoonRegistered;
    }

    public int getNightRegistered() {
        return NightRegistered;
    }

    public void setNightRegistered(int NightRegistered) {
        this.NightRegistered = NightRegistered;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }
    
    
}
