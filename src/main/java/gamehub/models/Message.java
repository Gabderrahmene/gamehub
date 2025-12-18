/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamehub.models;

/**
 *
 * @author abdou
 */
public class Message {

    private String id;
    private String username;
    private String content;
    private String date;

    public Message(String id, String username, String content, String date) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

}
