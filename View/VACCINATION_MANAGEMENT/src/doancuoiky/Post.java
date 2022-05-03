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
public class Post {
    private String ID;
    private String OrgID;
    private String Title;
    private String Content;
    private LocalDate PublishDate;
    private byte Image;
    private String Note;

    public Post() {
    }

    public Post(String ID, String OrgID, String Title, String Content, LocalDate PublishDate, byte Image, String Note) {
        this.ID = ID;
        this.OrgID = OrgID;
        this.Title = Title;
        this.Content = Content;
        this.PublishDate = PublishDate;
        this.Image = Image;
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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public LocalDate getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(LocalDate PublishDate) {
        this.PublishDate = PublishDate;
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
