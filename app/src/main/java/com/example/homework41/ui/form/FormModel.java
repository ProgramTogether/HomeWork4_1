package com.example.homework41.ui.form;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FormModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "text")
    private String text;

    public FormModel(String text) {
        this.text = text;
    }


    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "FormModel{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
