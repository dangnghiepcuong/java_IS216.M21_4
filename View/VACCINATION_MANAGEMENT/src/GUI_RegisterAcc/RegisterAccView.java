/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template


COLOR HEX CODE
màu xám dành cho nền: F2F2F2
màu Peach dành cho button chức năng: FF9292
màu xám hơi đậm dành cho label: 666666
màu đen dành cho text nhập vào: 333333
*/
package GUI_RegisterAcc;

import GUI_RegisterAcc.*;
import java.awt.Image.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author LeHoangDuyen
 */
public class RegisterAccView extends JFrame implements ActionListener
{
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JLabel RepeatPasswordLabel;

    private JLabel LastNameLabel;

    private JLabel FirstNameLabel;

    private JLabel IDLabel;

    private JLabel BirthdayLabel;
    private JTextField UsernameTextField;
    private JPasswordField PasswordField;
    private JPasswordField RepeatPasswordField;

    private JTextField LastNameTextField;

    private JTextField FirstNameTextField;

    private JTextField IDTextField;

    private JButton RegisterAccButton;

    private JPanel AccInfoPanel;

    private JPanel PersonalInfoPanel;
    
    private void initAccInfoPanel()
    {
        //create
        AccInfoPanel = new JPanel();

        //set position and area
        AccInfoPanel.setBounds(0, 0,DefaultFrameWidth()-DefaultFrameHeigth(),DefaultFrameHeigth() );

        AccInfoPanel.setBorder(border());

        //init UsernameLabel
        initUsernameLabel();
        this.add(UsernameLabel);

        //init PasswordLabel
        initPasswordLabel();
        this.add(PasswordLabel);

        //init RepeatPasswordLabel
        initRepeatPasswordLabel();
        this.add(RepeatPasswordLabel);

        //init UsernameTextField
        initUsernameTextField();
        this.add(UsernameTextField);

        //init PasswordField;
        initPasswordField();
        this.add(PasswordField);

        //init RepeatPasswordLabel
        initRepeatPasswordField();
        this.add(RepeatPasswordField);

        //init RegisterAccButton
        initRegisterAccButton();
        this.add(RegisterAccButton);
    }

    private void initPersonalInfoPanel()
    {
        //create
        PersonalInfoPanel = new JPanel();

        //set position and area
        PersonalInfoPanel.setBounds(360,0,DefaultFrameHeigth(),DefaultFrameHeigth());

        PersonalInfoPanel.setBorder(border());

        initLastNameLabel();
        this.add(LastNameLabel);

        initLastNameTextField();
        this.add(LastNameTextField);

        initFirstNameLabel();
        this.add(FirstNameLabel);

        initFirstNameTextField();
        this.add(FirstNameTextField);

        initIDLabel();
        this.add(IDLabel);

        initIDTextField();
        this.add(IDTextField);
    }

    private void initUsernameLabel()
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        //create new label
        UsernameLabel = new JLabel();

        //set label position and frame area
        UsernameLabel.setBounds(70, 80, 240, 30);
        
        //set label text
        UsernameLabel.setText("Số điện thoại");
        
        //set label text style
        UsernameLabel.setFont(new Font("SVN-Arial", 0, DefaultLabelFontSize()));

//        //set label text color
        UsernameLabel.setForeground(new Color(0x666666));

        UsernameLabel.setHorizontalAlignment(JLabel.LEFT);
        UsernameLabel.setVerticalAlignment(JLabel.CENTER);
    }
    
     private void initUsernameTextField()
    {
        //create Username text field
        UsernameTextField = new JTextField();

        //set position
        UsernameTextField.setBounds(70, 80+DefaultLabelHeigth(), 220, 30);
        
        //set cursor
        UsernameTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        
        //set field font
        UsernameTextField.setFont(new Font("SVN-Arial", Font.PLAIN, DefaultLabelFontSize()));

        //set text field color
        UsernameTextField.setForeground(new Color(0x333333));
        
        //set field background color
        UsernameTextField.setBackground(Color.WHITE);


    }
    
    private void initPasswordLabel()
    {
        //create new label
        PasswordLabel = new JLabel();

        //set position and area
        PasswordLabel.setBounds(70, 90 + DefaultFieldHeigth() + DefaultLabelHeigth(), 270, 30);
        
        //set label text
        PasswordLabel.setText("Mật khẩu");
        
        //set label text style
        PasswordLabel.setFont(new Font("SVN-Arial", 0, DefaultLabelFontSize()));

       //set label text color
        PasswordLabel.setForeground(new Color(0x666666));
        
        //set label alignment
        PasswordLabel.setHorizontalTextPosition(JLabel.LEFT);
        PasswordLabel.setVerticalTextPosition(JLabel.CENTER);
    }
    
    private void initPasswordField()
    {
        
        //create Username text field
        PasswordField = new JPasswordField();

        //set position and area
        PasswordField.setBounds(70, 90 + DefaultFieldHeigth() + 2 * DefaultLabelHeigth(), 220, 30);
        
        //set cursor
        PasswordField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        
        //set field font
        PasswordField.setFont(new Font("SVN-Arial", Font.PLAIN, DefaultLabelFontSize()));

        //set text field color
        PasswordField.setForeground(new Color(0x333333));
        
        //set field background color
        PasswordField.setBackground(Color.WHITE);

        PasswordField.setPreferredSize(new java.awt.Dimension(220, 30));
    }

    private void initRepeatPasswordLabel()
    {
        //create new label
        RepeatPasswordLabel = new JLabel();

        //set position and area
        RepeatPasswordLabel.setBounds(70, 100 + 3*DefaultFieldHeigth() + DefaultLabelHeigth(), 240, 30);

        //set label text
        RepeatPasswordLabel.setText("Nhập lại mật khẩu");

        //set label text style
        RepeatPasswordLabel.setFont(new Font("SVN-Arial", 0, DefaultLabelFontSize()));

        //set label text color
        RepeatPasswordLabel.setForeground(new Color(0x666666));

        //set label alignment
        RepeatPasswordLabel.setHorizontalTextPosition(JLabel.LEFT);
        RepeatPasswordLabel.setVerticalTextPosition(JLabel.CENTER);
    }

    private void initRepeatPasswordField()
    {
        //create Username text field
        RepeatPasswordField = new JPasswordField();

        //set position and area
        RepeatPasswordField.setBounds(70, 100 + 3*DefaultFieldHeigth() + 2 * DefaultLabelHeigth(), 220, 30);

        //set cursor
        RepeatPasswordField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        //set field font
        RepeatPasswordField.setFont(new Font("SVN-Arial", Font.PLAIN, DefaultLabelFontSize()));

        //set text field color
        RepeatPasswordField.setForeground(new Color(0x333333));

        //set field background color
        RepeatPasswordField.setBackground(Color.WHITE);

        RepeatPasswordField.setPreferredSize(new java.awt.Dimension(220, 30));
    }
    
    private void initRegisterAccButton()
    {
        //create login button
        RegisterAccButton = new JButton();

        //set position
        RegisterAccButton.setBounds(70, 420, 150, 49);

        //set no border
        RegisterAccButton.setBorder(null);
        
        RegisterAccButton.setContentAreaFilled(false);

        
        //create an icon
        ImageIcon LoginIcon = new ImageIcon(getClass().getResource("Login Button.png"));
        
        //set label icon
        RegisterAccButton.setIcon(LoginIcon);    
    }

    private void initLastNameLabel()
    {
        //create
        LastNameLabel = new JLabel();

        //set position and area
        LastNameLabel.setBounds(360+70, 80, 240, 30);

        //set label text
        LastNameLabel.setText("Họ và tên đệm");

        //set label text style
        LastNameLabel.setFont(new Font("SVN-Arial", 0, DefaultLabelFontSize()));

        //set label text color
        LastNameLabel.setForeground(new Color(0x666666));

        //set label alignment
        LastNameLabel.setHorizontalTextPosition(JLabel.LEFT);
        LastNameLabel.setVerticalTextPosition(JLabel.CENTER);
    }
    private void initLastNameTextField()
    {
        //create
        LastNameTextField = new JTextField();

        //set position and area
        LastNameTextField.setBounds(70+360, 80 + DefaultLabelHeigth(), 220, 30);

        //set cursor
        LastNameTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        //set field font
        LastNameTextField.setFont(new Font("SVN-Arial", Font.PLAIN, DefaultLabelFontSize()));

        //set text field color
        LastNameTextField.setForeground(new Color(0x333333));

        //set field background color
        LastNameTextField.setBackground(Color.WHITE);

        LastNameTextField.setPreferredSize(new java.awt.Dimension(220, 30));
    }

    private void initFirstNameLabel()
    {
        //create
        FirstNameLabel = new JLabel();

        //set position and area
        FirstNameLabel.setBounds(360+70+220+50, 80, 240, 30);

        //set label text
        FirstNameLabel.setText("Tên");

        //set label text style
        FirstNameLabel.setFont(new Font("SVN-Arial", 0, DefaultLabelFontSize()));

        //set label text color
        FirstNameLabel.setForeground(new Color(0x666666));

        //set label alignment
        FirstNameLabel.setHorizontalTextPosition(JLabel.LEFT);
        FirstNameLabel.setVerticalTextPosition(JLabel.CENTER);
    }
    private void initFirstNameTextField()
    {
        //create
        FirstNameTextField = new JTextField();

        //set position and area
        FirstNameTextField.setBounds(70+360+220+50, 80 + DefaultLabelHeigth(), 150, 30);

        //set cursor
        FirstNameTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        //set field font
        FirstNameTextField.setFont(new Font("SVN-Arial", Font.PLAIN, DefaultLabelFontSize()));

        //set text field color
        FirstNameTextField.setForeground(new Color(0x333333));

        //set field background color
        FirstNameTextField.setBackground(Color.WHITE);

        FirstNameTextField.setPreferredSize(new java.awt.Dimension(220, 30));
    }

    private void initIDLabel()
    {
        //create
        IDLabel = new JLabel();

        //set position and area
        IDLabel.setBounds(70+360, 90 + DefaultLabelHeigth()+DefaultFieldHeigth(), 240, 30);

        //set label text
        IDLabel.setText("Mã định danh");

        //set label text style
        IDLabel.setFont(new Font("SVN-Arial", 0, DefaultLabelFontSize()));

        //set label text color
        IDLabel.setForeground(new Color(0x666666));

        //set label alignment
        IDLabel.setHorizontalTextPosition(JLabel.LEFT);
        IDLabel.setVerticalTextPosition(JLabel.CENTER);

    }
    private void initIDTextField()
    {
        //create
        IDTextField = new JTextField();

        //set position and area
        IDTextField.setBounds(70+360, 90 + 2*DefaultLabelHeigth()+DefaultFieldHeigth(), 220, 30);

        //set cursor
        IDTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        //set field font
        IDTextField.setFont(new Font("SVN-Arial", Font.PLAIN, DefaultLabelFontSize()));

        //set text field color
        IDTextField.setForeground(new Color(0x333333));

        //set field background color
        IDTextField.setBackground(Color.WHITE);

        IDTextField.setPreferredSize(new java.awt.Dimension(220, 30));
    }



    private void initFrameComponent()
    {      
        //Frame
        //set frame title
        this.setTitle("Đăng ký");
        
        //set frame size
        this.setSize(DefaultFrameWidth(), DefaultFrameHeigth());
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

        //set account information Panel
        initAccInfoPanel();
        this.add(AccInfoPanel);

        //set personal information Panel
        initPersonalInfoPanel();
        this.add(PersonalInfoPanel);
    }
    
    public RegisterAccView()
    {
        initFrameComponent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private int DefaultLabelFontSize()
    {
        return 18;
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
