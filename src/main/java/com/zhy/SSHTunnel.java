package com.zhy;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.zhy.utils.DatabaseConnection;
import com.zhy.utils.UnicodeTest;
import com.zhy.vo.SerialNumber;
import com.zhy.vo.User;

import java.sql.*;
import java.util.*;

public class SSHTunnel {
    private static int localPort = 3310;

    private static String remoteHost = "10.10.152.72";
    private static int remotePort = 3306;
    private static String remotePassword = "fuhuideng#$%";

    public static void connection() {
        String sshUsername = "root";
        String sshPassword = "136915155";
        String sshHost = "123.59.53.17";
        int sshPort = 2333;
        try {
            JSch jSch = new JSch();
            Session session = jSch.getSession(sshUsername, sshHost, sshPort);
            session.setPassword(sshPassword);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            // 打印ssh服务器版本信息
            System.out.println(session.getServerVersion());
            int assinged_port = session.setPortForwardingL(localPort, remoteHost, remotePort);
            System.out.println("localhost:" + assinged_port + "->" + remoteHost + ":" + remotePort);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public static void sql(String sql, List<User> users) {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        List<SerialNumber> list = new ArrayList<SerialNumber>();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            conn = databaseConnection.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                SerialNumber no = new SerialNumber();
                no.setContacterId(rs.getString(1));
                list.add(no);
            }
            sql = AppendSql.appendSql(list,"select mobile, realname, id_img from users_contacter where id in ( ");
            sql1(sql, users);
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void sql1(String sql, List<User> users) {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            conn = databaseConnection.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                for (User user : users) {
                    if (rs.getString(2).contains(user.getUsername())) {
                        ImageDownLoad.downloadPicture(user.getMobile(), rs.getString(3));
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void sqlCard(String sql, List<User> users) {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            conn = databaseConnection.getConnection();

            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                for (User user : users) {
                    if (rs.getString(1).contains(user.getUsername())) {
                        ImageCardDownLoad.downloadPicture(user.getMobile(), rs.getString(2));
                        break;
                    }
                }
            }

            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<User> sqlField(String sql, List<User> users) {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        List<User> vos = new ArrayList<User>();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            conn = databaseConnection.getConnection();

            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                for (User user : users) {
                    if (rs.getString(1).contains(user.getUsername())) {
                        User vo = new User();
                        vo.setUsername(user.getUsername());
                        vo.setField(UnicodeTest.unicode2String(rs.getString(2)).replaceAll("\\\\", ""));
                        vos.add(vo);
                        break;
                    }
                }
            }

//            while (rs.next()) {
//                User vo = new User();
//                vo.setUsername(rs.getString(1));
//                vo.setField(UnicodeTest.unicode2String(rs.getString(2)).replaceAll("\\\\", ""));
//                vos.add(vo);
//            }

            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vos;
    }


    public static void photo(String sql, List<User> users) {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        List<SerialNumber> list = new ArrayList<SerialNumber>();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            conn = databaseConnection.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ImageCardDownLoad.downloadPicture("125", "user/idphoto/siu50z17ice");
            }
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
