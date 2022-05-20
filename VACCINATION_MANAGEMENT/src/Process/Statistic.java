/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import java.time.LocalDate;

/**
 *
 * @author DELL
 */
public class Statistic {
    private String Title;
    private int Data;
    private LocalDate LastUpdate;

    public Statistic() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getData() {
        return Data;
    }

    public void setData(int data) {
        Data = data;
    }

    public LocalDate getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        LastUpdate = lastUpdate;
    }
}
