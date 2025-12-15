/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatroom.models;

import java.io.Serializable;
import java.io.PrintWriter;
import java.io.BufferedReader;
/**
 *
 * @author abdou
 */
public class User {
    private String username;
    public static BufferedReader bf;
    public static PrintWriter pw;
    public User (String username ,PrintWriter pw,BufferedReader bf){
       this.username=username;
       this.bf=bf;
       this.pw=pw;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}
