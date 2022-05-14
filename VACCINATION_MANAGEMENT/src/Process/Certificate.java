/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

/**
 *
 * @author DELL
 */
public class Certificate {
    private Person person;
    private int CertType;
    private int Dose;
    private Injection injectionList[] = new Injection[10];

    public Certificate() {
    }

    public Certificate(Person person, int certType, int dose, Injection[] injectionList) {
        this.person = person;
        CertType = certType;
        Dose = dose;
        this.injectionList = injectionList;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getCertType() {
        return CertType;
    }

    public void setCertType(int CertType) {
        this.CertType = CertType;
    }

    public Injection[] getInjectionList() {
        return injectionList;
    }

    public void setInjectionList(Injection[] injectionList) {
        this.injectionList = injectionList;
    }

    public int getDose() {
        return Dose;
    }

    public void setDose(int dose) {
        Dose = dose;
    }
}
