/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamehub.control;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ClientHandle {

    private BufferedReader bf;
    private PrintWriter pw;

    public ClientHandle(BufferedReader bf, PrintWriter pw) {
        this.bf = bf;
        this.pw = pw;
    }

    public String login(String username, String password) {
        try {
            pw.println("login");
            pw.println(username);
            pw.println(password);
            return bf.readLine();
        } catch (IOException ex) {
            System.getLogger(ClientHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return "-1";
    }

    public String register(String username, String password) {
        try {
            pw.println("register");
            pw.println(username);
            pw.println(password);
            return bf.readLine();
        } catch (IOException ex) {
            System.getLogger(ClientHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return "-1";
    }

    public String create_reserv(String id_user, String date, String post) {
        try {
            pw.println("create_reserv");
            pw.println(id_user);
            pw.println(date);
            pw.println(post);
            return bf.readLine();
        } catch (IOException ex) {
            System.getLogger(ClientHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return "-1";
    }

    public String modify_reserv(String id_user, String odate, String opost, String date, String post) {
        try {
            pw.println("modify_reserv");
            pw.println(id_user);
            pw.println(odate);
            pw.println(opost);
            pw.println(date);
            pw.println(post);
            return bf.readLine();
        } catch (IOException ex) {
            System.getLogger(ClientHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return "-1";
    }

    public String del_reserv(String id_user, String date, String post) {
        try {
            pw.println("del_reserv");
            pw.println(id_user);
            pw.println(date);
            pw.println(post);
            return bf.readLine();
        } catch (IOException ex) {
            System.getLogger(ClientHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return "-1";
    }

    public String get_reserv(String username) {
        try {
            pw.println("get_reserv");
            pw.println(username);
            return bf.readLine();
        } catch (IOException ex) {
            System.getLogger(ClientHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return "-1";
    }
    public String get_reserv_admin() {
        try {
            pw.println("get_reserv_admin");
            return bf.readLine();
        } catch (IOException ex) {
            System.getLogger(ClientHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return "-1";
    }
    public String get_reserv_wek(String username, String date) {
        try {
            pw.println("get_reserv_wek");
            pw.println(username);
            pw.println(date);
            return bf.readLine();
        } catch (IOException ex) {
            System.getLogger(ClientHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return "-1";
    }
    public String get_reserv_mon(String username, String date) {
        try {
            pw.println("get_reserv_mon");
            pw.println(username);
            pw.println(date);
            return bf.readLine();
        } catch (IOException ex) {
            System.getLogger(ClientHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return "-1";
    }
    public String get_reserv_wek_admin( String date) {
        try {
            pw.println("get_reserv_wek_admin");
            pw.println(date);
            return bf.readLine();
        } catch (IOException ex) {
            System.getLogger(ClientHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return "-1";
    }
    public String get_reserv_mon_admin( String date) {
        try {
            pw.println("get_reserv_mon_admin");
            pw.println(date);
            return bf.readLine();
        } catch (IOException ex) {
            System.getLogger(ClientHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return "-1";
    }

    public String get_posts(String date) throws IOException {
        pw.println("get_posts");
        pw.println(date);
        return bf.readLine();
    }

    public String listen() {
        try {
            String request = bf.readLine();
            String answer = bf.readLine();
            return request + ":" + answer;
        } catch (IOException ex) {
            System.getLogger(ClientHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return "-1";
    }
}
