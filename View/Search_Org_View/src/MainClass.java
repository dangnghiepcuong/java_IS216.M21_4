/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Driver;//import GUI_SearchOrg.*;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author NghiepCuong
 */
public class MainClass {
    public static void main(String args[])
    {
        String DB_URL = "jdbc:oracle:thin:@localhost:1521:nghiepcuong";
        String username = "test_project";
        String password = "test_project";

        try {
            Connection connection = DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Connected Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed!");
        }

        SearchOrgView searchOrgGUI = new SearchOrgView();
    }
}
