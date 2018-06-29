package com.example.janusz.finalapp_v1.SQLiteNotes;

/**
 * Created by janusz on 11.04.2018.
 */

public class Notes {


    private int id;
    private String note;
    private String date;
    String txtfilePath;
    String jpgFilePath;
    private byte[] image;


    public Notes(int id, String name, String phone) {
        this.id = id;
        this.note = name;
        this.date = phone;

    }
    public Notes(String name, String phone, String txtFilePath, String jpgFilePath) {
        this.note = name;
        this.date = phone;
        this.txtfilePath = txtFilePath;
        this.jpgFilePath = jpgFilePath;
    }
    public Notes(int id, String name, String phone, byte[] image) {
                            this.id = id;
                            this.note = name;
                            this.date = phone;
                            this.image = image;
                        }
    public byte[] getImage() {
                            return image;
                        }

    public void setImage(byte[] image) {
                            this.image = image;
                        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}