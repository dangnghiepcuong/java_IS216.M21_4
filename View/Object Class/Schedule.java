/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doancuoiky;

import java.time.LocalDate;

/**
 *
 * @author DELL
 */
public class Schedule {
    private String ID;
    private String OrgID;
    private LocalDate OnDate;
    private String VaccineID;
    private String Serial;
    private int LimitDay;
    private int LimitNoon;
    private int LimitNight;
    private int DayRegistered;
    private int Noonregistered;
    private int NightRegistered;
    private String Note;

    public Schedule() {
    }

    public Schedule(String ID, String OrgID, LocalDate OnDate, String VaccineID, String Serial, int LimitDay, int LimitNoon, int LimitNight, int DayRegistered, int Noonregistered, int NightRegistered, String Note) {
        this.ID = ID;
        this.OrgID = OrgID;
        this.OnDate = OnDate;
        this.VaccineID = VaccineID;
        this.Serial = Serial;
        this.LimitDay = LimitDay;
        this.LimitNoon = LimitNoon;
        this.LimitNight = LimitNight;
        this.DayRegistered = DayRegistered;
        this.Noonregistered = Noonregistered;
        this.NightRegistered = NightRegistered;
        this.Note = Note;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String OrgID) {
        this.OrgID = OrgID;
    }

    public LocalDate getOnDate() {
        return OnDate;
    }

    public void setOnDate(LocalDate OnDate) {
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

    public int getNoonregistered() {
        return Noonregistered;
    }

    public void setNoonregistered(int Noonregistered) {
        this.Noonregistered = Noonregistered;
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
