/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI_Login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author NghiepCuong
 */
public class LoginView extends javax.swing.JFrame
{
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    
    private void initUsernameLabel()
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        
        //Label: Username
        //create new label
        UsernameLabel = new JLabel();
        
        //set label text
        UsernameLabel.setText("SĐT/Tên tài khoản");
        
        //set label text style
        UsernameLabel.setFont(new Font("SVN-Arial", 3, 20));
        
        //UsernameLabel.setLabelFor();
        //UsernameLabel.setAutoscrolls(false);
        
        //set label size
        UsernameLabel.setSize(240,30);
        
//        //set label text color
        UsernameLabel.setForeground(new Color(0x666666));
//        
//        //create an icon
//        ImageIcon Virus = new ImageIcon("/GUI_Login/Virus.png");
//        
//        //set label icon
//        UsernameLabel.setIcon(Virus);    

        //set label border
        UsernameLabel.setBorder(border);
        
        //set label position
        UsernameLabel.setHorizontalTextPosition(JLabel.LEFT);
        UsernameLabel.setVerticalTextPosition(JLabel.CENTER);
        
//        UsernameLabel.setHorizontalAlignment(JLabel.CENTER);
//        UsernameLabel.setVerticalAlignment(JLabel.CENTER);
        
        
        UsernameLabel.setBounds(70, 160, 240, 30);
    }
    
    private void initPasswordLabel()
    {
        //Label: Username
        //create new label
        PasswordLabel = new JLabel();
        
        //set label text
        PasswordLabel.setText("Mật khẩu");
        
        //set label text style
        PasswordLabel.setFont(new Font("SVN-Arial", 3, 20));
        
        //PasswordLabel.setLabelFor();
        //PasswordLabel.setAutoscrolls(false);
        
        //set label size
        PasswordLabel.setSize(240,30);
        
//      //set label text color
        PasswordLabel.setForeground(new Color(0x666666));
        
        //set label position
        PasswordLabel.setHorizontalTextPosition(JLabel.LEFT);
        PasswordLabel.setVerticalTextPosition(JLabel.CENTER);
        
//      PasswordLabel.setHorizontalAlignment(JLabel.CENTER);
//      PasswordLabel.setVerticalAlignment(JLabel.CENTER);
        
        
        PasswordLabel.setBounds(70, 230, 240, 30);
    }
    
    private void initFrameComponent()
    {      
        //Frame
        //set frame title
        this.setTitle("Đăng nhập");
        
        //set frame size
        this.setSize(380, 560);
        
        //set do not allow frame resizing
        this.setResizable(false);
        
        //set frame visible on screen
        this.setVisible(true);
        
        //set frame close on X button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //set frame background color
        this.getContentPane().setBackground(new Color(0xFCFCFC));
      
        this.setLayout(null);
        
        //init UsernameLabel
        initUsernameLabel();
        this.add(UsernameLabel);
        
        //init PasswordLabel
        initPasswordLabel();
        this.add(PasswordLabel);
    }
    
    public LoginView()
    {
        initFrameComponent();
    }
    
    
}
