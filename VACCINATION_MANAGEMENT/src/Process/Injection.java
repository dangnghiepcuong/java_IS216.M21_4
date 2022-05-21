/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

/**
 *
 * @author DELL
 */
public class Injection {
    private Person person = new Person();
    private int InjNo;
    private Schedule Sched = new Schedule();
    private Organization Org = new Organization();
    private Register Reg = new Register();
    private String DoseType;
    private String Note;

    public Injection() {
    }

    public Injection(Person person, int InjNo, String SchedID, String DoseType, String Note) {
        this.person = person;
        this.InjNo = InjNo;
        this.Sched = Sched;
        this.DoseType = DoseType;
        this.Note = Note;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getInjNo() {
        return InjNo;
    }

    public void setInjNo(int InjNo) {
        this.InjNo = InjNo;
    }

    public Schedule getSched() {
        return Sched;
    }

    public void setSched(Schedule Sched) {
        this.Sched = Sched;
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

    public Organization getOrg() {
        return Org;
    }

    public void setOrg(Organization org) {
        Org = org;
    }

    public Register getReg() {
        return Reg;
    }

    public void setReg(Register reg) {
        Reg = reg;
    }
}
