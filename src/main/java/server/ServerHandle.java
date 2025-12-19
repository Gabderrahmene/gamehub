/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author abdou
 */
public class ServerHandle {

    public String login_verif(String username, String password) {
        try (Connection conn = DriverManager.getConnection(System.getenv("game_hubBaseUrl"), "root", null)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM client WHERE username = '" + username + "'")) {
                    if (!rs.first()) {

                        return "-1";
                    }
                    rs.first();
                    if (password.equals(rs.getString("password"))) {
                        if(rs.getString("vip").equals("1")){
                          return "admin";  
                        }else{
                            return rs.getString("id");
                        } 
                    } else {
                        return "-1";
                    }
                } catch (SQLException ex) {
                    System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            } catch (SQLException ex) {
                System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } catch (SQLException ex) {
            System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return "-1";
    }

    public String register_verif(String username, String password) {
        try (Connection conn = DriverManager.getConnection(System.getenv("game_hubBaseUrl"), "root", null)) {
            try (Statement stmt = conn.createStatement()) {
                try {
                    stmt.executeUpdate("INSERT INTO client (username, password) VALUES ('" + username + "','" + password + "')");
                    return "0";
                } catch (SQLException ex) {
                    System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    return "-1";
                }
            } catch (SQLException ex) {
                System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                return "-1";
            }
        } catch (SQLException ex) {
            System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return "-1";
        }

    }

    public String get_reserv(String username) {
        try (Connection conn = DriverManager.getConnection(System.getenv("game_hubBaseUrl"), "root", null)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT r.date, p.post_name FROM reserv r INNER JOIN client c ON r.id_client = c.id INNER JOIN post p ON r.id_post = p.id_post WHERE c.id = '" + username + "';")) {
                    String res = "";
                    while (rs.next()) {
                        res += rs.getString("post_name") + "," + rs.getString("date") + "/";
                    }

                    return res;
                } catch (SQLException ex) {
                    System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    return "-1";
                }
            } catch (SQLException ex) {
                System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                return "-1";
            }
        } catch (SQLException ex) {
            System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return "-1";
        }
    }

    public String get_reserv_wek(String username, String date) {
        try (Connection conn = DriverManager.getConnection(System.getenv("game_hubBaseUrl"), "root", null)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT p.post_name, r.date FROM reserv r INNER JOIN client c ON r.id_client = c.id INNER JOIN post p ON r.id_post = p.id_post WHERE c.id = '" + username + "' AND r.date BETWEEN '" + date + " 00:00:00' AND DATE_ADD('" + date + " 23:59:59', INTERVAL 6 DAY) ORDER BY r.date ASC")) {
                    String res = "";
                    while (rs.next()) {
                        System.out.print(rs.getString("post_name") + "," + rs.getString("date") + "/");
                        res += rs.getString("post_name") + "," + rs.getString("date") + "/";
                    }
                    System.out.print(res);
                    return res;
                } catch (SQLException ex) {
                    System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    return "-1";
                }
            } catch (SQLException ex) {
                System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                return "-1";
            }
        } catch (SQLException ex) {
            System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return "-1";
        }
    }

    public String get_reserv_mon(String username, String date) {
        try (Connection conn = DriverManager.getConnection(System.getenv("game_hubBaseUrl"), "root", null)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT p.post_name, r.date FROM reserv r INNER JOIN client c ON r.id_client = c.id INNER JOIN post p ON r.id_post = p.id_post WHERE c.id = '" + username + "' AND r.date BETWEEN '" + date + " 00:00:00' AND DATE_ADD('" + date + " 23:59:59', INTERVAL 42 DAY) ORDER BY r.date ASC")) {
                    String res = "";
                    while (rs.next()) {
                        System.out.print(rs.getString("post_name") + "," + rs.getString("date") + "/");
                        res += rs.getString("post_name") + "," + rs.getString("date") + "/";
                    }
                    System.out.print(res);
                    return res;
                } catch (SQLException ex) {
                    System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    return "-1";
                }
            } catch (SQLException ex) {
                System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                return "-1";
            }
        } catch (SQLException ex) {
            System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return "-1";
        }
    }

    public String get_posts(String date) {
        try (Connection conn = DriverManager.getConnection(System.getenv("game_hubBaseUrl"), "root", null)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT P.id_post,P.post_name FROM post P LEFT JOIN reserv R ON P.id_post =R.id_post AND R.date ='" + date + "' WHERE R.id_post is null;")) {
                    String res = "";
                    while (rs.next()) {
                        res += rs.getString("id_post") + "." + rs.getString("post_name") + ",";
                    }
                    return res;
                } catch (SQLException ex) {
                    System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    return "-1";
                }
            } catch (SQLException ex) {
                System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                return "-1";
            }
        } catch (SQLException ex) {
            System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return "-1";
        }

    }

    public String create_reserv(String id_user, String date, String post) {
        try (Connection conn = DriverManager.getConnection(System.getenv("game_hubBaseUrl"), "root", null)) {
            try (Statement stmt = conn.createStatement()) {
                try {
                    stmt.executeUpdate("INSERT INTO reserv(id_client,date,id_post) VALUES ('" + id_user + "','" + date + "','" + post + "')");
                    return "0";
                } catch (SQLException ex) {
                    System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    return "-1";
                }
            } catch (SQLException ex) {
                System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                return "-1";
            }
        } catch (SQLException ex) {
            System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return "-1";
        }

    }

    public String modify_reserv(String id_user, String odate, String opost, String date, String post) {
        String cr = create_reserv(id_user, date, post);
        if (!cr.equals("-1")) {
            del_reserv(id_user, odate, opost);
        } else {
            return "-1";
        }
        return "1";
    }

    public String del_reserv(String id_user, String date, String post) {
        try (Connection conn = DriverManager.getConnection(System.getenv("game_hubBaseUrl"), "root", null)) {
            // create a Statement
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("DELETE r FROM reserv r INNER JOIN post p ON r.id_post = p.id_post WHERE r.id_client = '" + id_user + "' AND p.post_name = '" + post + "' AND r.date = '" + date + "';")) {
                    return "1";
                } catch (SQLException ex) {
                    System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    return "-1";
                }
            } catch (SQLException ex) {
                System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                return "-1";
            }
        } catch (SQLException ex) {
            System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return "-1";
        }
    }
    
    
    
    
    
    //admin:
    
    public String get_reserv_wek_admin(String date) {
        try (Connection conn = DriverManager.getConnection(System.getenv("game_hubBaseUrl"), "root", null)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT p.post_name, r.date,c.username,c.id FROM reserv r INNER JOIN client c ON r.id_client = c.id INNER JOIN post p ON r.id_post = p.id_post  AND r.date BETWEEN '" + date + " 00:00:00' AND DATE_ADD('" + date + " 23:59:59', INTERVAL 6 DAY) ORDER BY r.date ASC")) {
                    String res = "";
                    while (rs.next()) {
                        System.out.print(rs.getString("post_name") + "," + rs.getString("date") + "/");
                        res +=  rs.getString("post_name") + "," + rs.getString("date")+"," +rs.getString("c.username") + "," +rs.getString("c.id")+ "/";
                    }
                    System.out.print(res);
                    return res;
                } catch (SQLException ex) {
                    System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    return "-1";
                }
            } catch (SQLException ex) {
                System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                return "-1";
            }
        } catch (SQLException ex) {
            System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return "-1";
        }
    }

    public String get_reserv_mon_admin(String date) {
        try (Connection conn = DriverManager.getConnection(System.getenv("game_hubBaseUrl"), "root", null)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT p.post_name, c.username , r.date FROM reserv r INNER JOIN client c ON r.id_client = c.id INNER JOIN post p ON r.id_post = p.id_post  AND r.date BETWEEN '" + date + " 00:00:00' AND DATE_ADD('" + date + " 23:59:59', INTERVAL 42 DAY) ORDER BY r.date ASC")) {
                    String res = "";
                    while (rs.next()) {
                        System.out.print(rs.getString("post_name") + "," + rs.getString("date") + "/");
                        res += rs.getString("post_name") + "," + rs.getString("date") +","+ rs.getString("username")+"/";
                    }
                    System.out.print(res);
                    return res;
                } catch (SQLException ex) {
                    System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    return "-1";
                }
            } catch (SQLException ex) {
                System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                return "-1";
            }
        } catch (SQLException ex) {
            System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return "-1";
        }
    }
     public String get_reserv_admin() {
        try (Connection conn = DriverManager.getConnection(System.getenv("game_hubBaseUrl"), "root", null)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT r.date, p.post_name ,c.id ,c.username FROM reserv r INNER JOIN client c ON r.id_client = c.id INNER JOIN post p ON r.id_post = p.id_post;")) {
                    String res = "";
                    while (rs.next()) {
                        res += rs.getString("post_name") + "," + rs.getString("date") + ","+rs.getString("c.username") + "," +rs.getString("c.id") +"/";
                    }

                    return res;
                } catch (SQLException ex) {
                    System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    return "-1";
                }
            } catch (SQLException ex) {
                System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                return "-1";
            }
        } catch (SQLException ex) {
            System.getLogger(ServerHandle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return "-1";
        }
    }
    
}
