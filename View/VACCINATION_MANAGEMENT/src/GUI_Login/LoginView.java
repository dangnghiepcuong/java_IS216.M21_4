/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI_Login;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author NghiepCuong
 */
public class LoginView extends javax.swing.JFrame
{
    public LoginView()
    {
        JFrame LoginFrame = new JFrame("Đăng nhập");
        LoginFrame.setSize(420,540);
        LoginFrame.setVisible(true);
        LoginFrame.setBackground(Color.PINK);
        LoginFrame.setForeground(Color.pink);
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //LoginFrame.setLayout();
        
    }
    
    public static void main(String args[])
    {
        new LoginView();
    }
}
