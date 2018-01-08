package com.example.pc2.ccctoken;

/**
 * Created by pc2 on 1/8/2018 AD.
 */

public class Message {
    private String title, message;

    public Message(){}

    public Message(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
