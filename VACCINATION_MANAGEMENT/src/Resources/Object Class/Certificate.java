/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doancuoiky;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Certificate {
    private int CertType;
    private ArrayList<Injection> InjectionList;

    public Certificate() {
    }

    public Certificate(int CertType, ArrayList<Injection> InjectionList) {
        this.CertType = CertType;
        this.InjectionList = InjectionList;
    }

    public int getCertType() {
        return CertType;
    }

    public void setCertType(int CertType) {
        this.CertType = CertType;
    }

    public ArrayList<Injection> getInjectionList() {
        return InjectionList;
    }

    public void setInjectionList(ArrayList<Injection> InjectionList) {
        this.InjectionList = InjectionList;
    }
    
    
}
