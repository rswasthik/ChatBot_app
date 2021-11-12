package com.example.chatbot;

public class chatmodal {
    private String message;
    private String sender;

    public chatmodal(String message) {
        this.message = message;
    }

    public chatmodal(String please_revert_your_question, String bot_key) {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
