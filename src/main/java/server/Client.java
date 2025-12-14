/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.PrintWriter;

/**
 *
 * @author abdou
 */
public class Client {
    String id_user;
    String username;
    PrintWriter pw;
    boolean act;

    public void setAct(boolean act) {
        this.act = act;
    }

    public Client(String id_user, String username, PrintWriter pw) {
        this.id_user = id_user;
        this.username = username;
        this.pw = pw;
        this.act = true;
    }
    
}
