/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template


COLOR HEX CODE
màu xám dành cho nền: F2F2F2
màu Peach dành cho button chức năng: FF9292
màu xám hơi đậm dành cho label: 666666
màu đen dành cho text nhập vào: 333333
*/
package GUI_Login;

import java.awt.Image.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author NghiepCuong
 */
public class LoginView extends JFrame implements ActionListener
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

        //set position and area
        ViewSymbol.setBounds(140, 50, 100, 100);

        //set label icon
        ViewSymbol.setIcon(Virus); 

        //set alignment
        ViewSymbol.setHorizontalAlignment(JLabel.CENTER);
    }
    
    private void initUsernameLabel()
    {
        //create new label
        UsernameLabel = new JLabel();

        //set label position and frame area
        UsernameLabel.setBounds(70, 165, DefaultLabelWidth(), DefaultLabelHeigth());

        //set label text
        UsernameLabel.setText("SĐT/Tên tài khoản");
        
        //set label text style
        UsernameLabel.setFont(new Font("SVN-Arial", 3, 20));

        //set label text color
        UsernameLabel.setForeground(new Color(0x666666));

        //set alignment
        UsernameLabel.setHorizontalAlignment(JLabel.LEFT);
        UsernameLabel.setVerticalAlignment(JLabel.CENTER);

    }
    
    private void initUsernameTextField()
    {
        //create Username text field
        UsernameTextField = new JTextField();

        //set position and area
        UsernameTextField.setBounds(70, 195, DefaultFieldWidth(), DefaultFieldHeigth());

        //set cursor
        UsernameTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        
        //set field font
        UsernameTextField.setFont(new Font("SVN-Arial", Font.PLAIN, 16));

        //set text field color
        UsernameTextField.setForeground(new Color(0x333333));
        
        //set field background color
        UsernameTextField.setBackground(Color.WHITE);
        
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
        
        
        PasswordLabel.setBounds(70, 235, DefaultLabelWidth(), DefaultLabelHeigth());
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
        PasswordField.setBounds(70, 265, DefaultFieldWidth(), DefaultFieldHeigth());
        
        PasswordField.setPreferredSize(new Dimension(220, 30));
    }
    
    private void initLoginButton()
    {
        //create login button
        LoginButton = new JButton();

        //set position
        LoginButton.setBounds(115, 380, 150, 49);

        //set no border
        LoginButton.setBorder(null);
        
        LoginButton.setContentAreaFilled(false);
        
        //create an icon
        ImageIcon LoginIcon = new ImageIcon(getClass().getResource("Login Button.png"));
        
        //set label icon
        LoginButton.setIcon(LoginIcon);    
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

        //set Frame icon
        this.setIconImage(new ImageIcon(getClass().getResource("Virus.png")).getImage());

        //set layout
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

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    private int DefaultLabelFontSize()
    {
        return 20;
    }

    private int DefaultLabelWidth()
    {
        return 200;
    }

    private int DefaultLabelHeigth()
    {
        return 30;
    }

    private int DefaultFieldWidth()
    {
        return 200;
    }

    private int DefaultFieldHeigth()
    {
        return 30;
    }

    private int DefaultFrameWidth()
    {
        return 1080;
    }

    private int DefaultFrameHeigth()
    {
        return 720;
    }

    private Border border()
    {
        return BorderFactory.createLineBorder(Color.BLACK);
    }
}
