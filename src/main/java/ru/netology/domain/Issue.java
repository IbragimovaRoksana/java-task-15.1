package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    private int id;
    private String title;
    private String textIssue;
    private String author;
    private String[] assignees = new String[0];
    private String[] labels = new String[0];
//    private Label labels = new Label();
    private String project;
    private String milestone;
    private int data;//сколько дней с начала года
    private boolean open;


    public Issue(int id, String title, String textIssue, String author, int data, boolean open) {
        this.id = id;
        this.title = title;
        this.textIssue = textIssue;
        this.author = author;
        this.data = data;
        this.open = open;
    }

}
