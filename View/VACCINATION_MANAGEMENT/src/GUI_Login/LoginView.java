/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI_Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author NghiepCuong
 */
public class LoginView extends javax.swing.JFrame
{
    private JLabel ViewSymbol;
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JTextField UsernameTextField;
    private JPasswordField PasswordField;
    private JButton LoginButton;
    private JPanel ViewSymbolPanel;
    
    private void initViewSymbol()
    {
        ViewSymbol = new JLabel();
        
        //create an icon
        ImageIcon Virus = new ImageIcon(getClass().getResource("/GUI_Login/Virus.png"));
       
        //set label icon
        ViewSymbol.setIcon(Virus); 
        
        ViewSymbol.setHorizontalAlignment(JLabel.CENTER);
        
        //ViewSymbol.setOpaque(true);
        
        ViewSymbol.setBounds(140, 50, 100, 100);
    }
    
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
        //UsernameLabel.setBorder(null);
        
        //set label position
//        UsernameLabel.setHorizontalTextPosition(JLabel.CENTER);
//        UsernameLabel.setVerticalTextPosition(JLabel.CENTER);
        
        UsernameLabel.setHorizontalAlignment(JLabel.LEFT);
        UsernameLabel.setVerticalAlignment(JLabel.CENTER);
        
        //set label position and frame area
        UsernameLabel.setBounds(70, 165, 240, 30);
    }
    
     private void initUsernameTextField()
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        
        //create Username text field
        UsernameTextField = new JTextField();
        
        //set cursor
        UsernameTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        
        //set field font
        UsernameTextField.setFont(new Font("SVN-Arial", Font.PLAIN, 16));
        
        //set field size
        UsernameTextField.setSize(240,30);
        
        //set text field color
        UsernameTextField.setForeground(new Color(0x333333));
        
        //set field background color
        UsernameTextField.setBackground(Color.WHITE);
        
        //set border
        UsernameTextField.setBorder(border);
        
        //set position
        UsernameTextField.setBounds(70, 195, 220, 30);
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
        
        
        PasswordLabel.setBounds(70, 235, 240, 30);
    }
    
    private void initPasswordField()
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        
        //create Username text field
        PasswordField = new JPasswordField();
        
        //set cursor
        PasswordField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        
        //set field font
        PasswordField.setFont(new Font("SVN-Arial", Font.PLAIN, 16));
        
        //set field size
        PasswordField.setSize(240,30);
        
        //set text field color
        PasswordField.setForeground(new Color(0x333333));
        
        //set field background color
        PasswordField.setBackground(Color.WHITE);
        
        //set border
        PasswordField.setBorder(border);
        
        //set position
        PasswordField.setBounds(70, 265, 220, 30);
        
        PasswordField.setPreferredSize(new java.awt.Dimension(220, 30));
    }
    
    private void initLoginButton()
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        
        //create login button
        LoginButton = new JButton();
        
        //set no border
        LoginButton.setBorder(null);
        
        LoginButton.setContentAreaFilled(false);
        
        //create an icon
        ImageIcon LoginIcon = new ImageIcon(getClass().getResource("/GUI_Login/Login Button.png"));
        
        //set label icon
        LoginButton.setIcon(LoginIcon);    
        
        LoginButton.setBounds(115, 380, 150, 49);
    }
    
    private void initViewSymbolPanel()
    {
        ViewSymbolPanel = new JPanel();
        
        initViewSymbol();
        
        ViewSymbolPanel.add(ViewSymbol);
        
        ViewSymbolPanel.setBounds(140, 50, 100, 100);
        
        ViewSymbolPanel.setLayout(new BorderLayout());
    }
    
    private void initFrameComponent()
    {      
        //Frame
        //set frame title
        this.setTitle("Đăng nhập");
        
        //set frame size
        this.setSize(380, 520);
        //this.setSize(1080, 720); --Main View
        
        //set do not allow frame resizing
        this.setResizable(false);
        
        //set frame visible on screen
        this.setVisible(true);
        
        //set frame close on X button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //set frame background color
        this.getContentPane().setBackground(new Color(0xFCFCFC));
      
        this.setLayout(null);
        
        //init ViewSymbol
        initViewSymbol();
        this.add(ViewSymbol);
        
        //init UsernameLabel
        initUsernameLabel();
        this.add(UsernameLabel);
        
        //init PasswordLabel
        initPasswordLabel();
        this.add(PasswordLabel);
        
        //init UsernameTextField
        initUsernameTextField();
        this.add(UsernameTextField);
        
        //init PasswordField;
        initPasswordField();
        this.add(PasswordField);
        
        //init LoginButton
        initLoginButton();
        this.add(LoginButton);
        
    }
    
    public LoginView()
    {
        initFrameComponent();
    }
    
    
}
