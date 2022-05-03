/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template


COLOR HEX CODE
màu xám dành cho nền: F2F2F2
màu Peach dành cho button chức năng: FF9292
màu xám hơi đậm dành cho label: 666666
màu đen dành cho text nhập vào: 333333
*/

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author LeHoangDuyen
 */
public class RegisterAccView extends JFrame implements ActionListener
{
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JLabel RepeatPasswordLabel;
    private JTextField UsernameTextField;
    private JPasswordField PasswordField;
    private JPasswordField RepeatPasswordField;
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


    }

    private void initUsernameLabel()
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        //create new label
        UsernameLabel = new JLabel();

        //set label position and frame area
        UsernameLabel.setBounds(40, 80, 240, 30);
        
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
        UsernameTextField.setBounds(40, 80+DefaultLabelHeigth(), 220, 30);
        
        //set cursor
        UsernameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
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
        PasswordLabel.setBounds(40, 90 + DefaultFieldHeigth() + DefaultLabelHeigth(), 240, 30);
        
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
        PasswordField.setBounds(40, 90 + DefaultFieldHeigth() + 2 * DefaultLabelHeigth(), 220, 30);
        
        //set cursor
        PasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        //set field font
        PasswordField.setFont(new Font("SVN-Arial", Font.PLAIN, DefaultLabelFontSize()));

        //set text field color
        PasswordField.setForeground(new Color(0x333333));
        
        //set field background color
        PasswordField.setBackground(Color.WHITE);

        PasswordField.setPreferredSize(new Dimension(220, 30));
    }

    private void initRepeatPasswordLabel()
    {
        //create new label
        RepeatPasswordLabel = new JLabel();

        //set position and area
        RepeatPasswordLabel.setBounds(40, 100 + 3*DefaultFieldHeigth() + DefaultLabelHeigth(), 240, 30);

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
        RepeatPasswordField.setBounds(40, 100 + 3*DefaultFieldHeigth() + 2 * DefaultLabelHeigth(), 220, 30);

        //set cursor
        RepeatPasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        //set field font
        RepeatPasswordField.setFont(new Font("SVN-Arial", Font.PLAIN, DefaultLabelFontSize()));

        //set text field color
        RepeatPasswordField.setForeground(new Color(0x333333));

        //set field background color
        RepeatPasswordField.setBackground(Color.WHITE);

        RepeatPasswordField.setPreferredSize(new Dimension(220, 30));
    }
    
    private void initRegisterAccButton()
    {
        //create an icon
        ImageIcon LoginIcon = new ImageIcon(getClass().getResource("/icon/Login Button.png"));

        //create login button
        RegisterAccButton = new JButton();

        //set position
        RegisterAccButton.setBounds(70, 380, LoginIcon.getIconWidth(), LoginIcon.getIconHeight());

        //set no border
        RegisterAccButton.setBorder(null);
        
        RegisterAccButton.setContentAreaFilled(false);

        //set label icon
        RegisterAccButton.setIcon(LoginIcon);    
    }
    
    private void initFrameComponent()
    {      
        //Frame
        //set frame title
        this.setTitle("Đăng ký tài khoản mới");
        
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
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/Virus.png")).getImage());

        //set layout
        this.setLayout(null);

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
