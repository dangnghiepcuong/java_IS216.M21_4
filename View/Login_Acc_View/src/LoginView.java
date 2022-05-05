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
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author NghiepCuong
 */
public class LoginView extends JFrame implements ActionListener, AncestorListener {
    private JLabel ViewSymbol;
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JTextField UsernameTextField;
    private JPasswordField PasswordField;
    private JButton LoginButton;
    private JPanel ViewSymbolPanel;
    private DefaultValue dv = new DefaultValue();
    private JLabel ForgotPasswordLabel;
    private  JLabel RegisterAccountLabel;

    private void initViewSymbol()
    {
        ViewSymbol = new JLabel();

        //create an icon
        ImageIcon Virus = new ImageIcon(getClass().getResource("/icon/Virus.png"));

        //set position and area
        ViewSymbol.setBounds(130, 50, 100, 100);

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
        UsernameLabel.setBounds(70, 165, dv.LabelWidth(), dv.LabelHeigth());

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
        UsernameTextField.setBounds(70, 195, dv.FieldWidth(), dv.FieldHeigth());

        //set cursor
        UsernameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        //set field font
        UsernameTextField.setFont(new Font("SVN-Arial", Font.PLAIN, 16));

        //set text field color
        UsernameTextField.setForeground(new Color(0x333333));

        //set field background color
        UsernameTextField.setBackground(Color.WHITE);

    }

    private void initPasswordLabel()
    {
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


        PasswordLabel.setBounds(70, 235, dv.LabelWidth(), dv.LabelHeigth());
    }

    private void initPasswordField()
    {
        //create Username text field
        PasswordField = new JPasswordField();

        //set cursor
        PasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        //set field font
        PasswordField.setFont(new Font("SVN-Arial", Font.PLAIN, 16));

        //set field size
        PasswordField.setSize(240,30);

        //set text field color
        PasswordField.setForeground(new Color(dv.FieldLabelColor()));

        //set field background color
        PasswordField.setBackground(Color.WHITE);

        //set position
        PasswordField.setBounds(70, 265, dv.FieldWidth(), dv.FieldHeigth());

    }

    private void initForgotPasswordLabel()
    {
        ForgotPasswordLabel = new JLabel();

        ForgotPasswordLabel.setText("Quên mật khẩu");

        ForgotPasswordLabel.setFont(new Font("SVN-Arial",Font.ITALIC, 12));

        ForgotPasswordLabel.setSize(150, 25);

        ForgotPasswordLabel.setForeground(new Color(dv.FieldLabelColor()));

        ForgotPasswordLabel.setBounds(70, 270+dv.FieldHeigth(), 100, 25);

       //ForgotPasswordLabel.setBorder(dv.border());

        ForgotPasswordLabel.addAncestorListener(this);
    }

    private void initRegisterAccountLabel()
    {
        RegisterAccountLabel = new JLabel();

        RegisterAccountLabel.setText("Đăng ký");

        RegisterAccountLabel.setFont(new Font("SVN-Arial",Font.ITALIC, 12));

        RegisterAccountLabel.setSize(150, 25);

        RegisterAccountLabel.setForeground(new Color(dv.FieldLabelColor()));

        RegisterAccountLabel.setHorizontalAlignment(JLabel.RIGHT);

        RegisterAccountLabel.setBounds(69+100, 270+dv.FieldHeigth(), 100, 25);

        //RegisterAccountLabel.setBorder(dv.border());

        RegisterAccountLabel.addAncestorListener(this);
    }

    private void initLoginButton()
    {
        //create login button
        LoginButton = new JButton();

        //set position
        LoginButton.setBounds(100, 380, 150, 49);

        //set no border
        LoginButton.setBorder(null);

        LoginButton.setContentAreaFilled(false);

        //create an icon
        ImageIcon LoginIcon = new ImageIcon(getClass().getResource("/icon/Login Button.png"));

        //set label icon
        LoginButton.setIcon(LoginIcon);

        LoginButton.addActionListener(this);
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
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/Virus.png")).getImage());

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

        //init ForgotPasswordLabel
        initForgotPasswordLabel();
        this.add(ForgotPasswordLabel);

        //init RegisterAccountLabel
        initRegisterAccountLabel();
        this.add(RegisterAccountLabel);

        //init LoginButton
        initLoginButton();
        this.add(LoginButton);

        this.repaint();
    }

    public LoginView()
    {
        initFrameComponent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == LoginButton) {
            String InputUsername = UsernameTextField.getText();
            String InputPassword = String.valueOf(PasswordField.getPassword());

            if (InputUsername.equals("") || InputPassword.equals("")) {
                System.out.println("Nhập vào tài khoản/mật khẩu!");
                return;
            }

            String Username = "";
            String Password = "";
            int Role = 0;


            String query = "select *" +
                    " from ACCOUNT" +
                    " where Username = '" + InputUsername + "'";


            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                PreparedStatement st = connection.prepareStatement(query);

                ResultSet rs = st.executeQuery(query);

                rs.next();
                Username = rs.getString("Username");
                Password = rs.getString("Password");
                Role = rs.getInt("Role");

            } catch (SQLException ex) {

                System.out.println("Tài khoản không tồn tại!");

                throw new RuntimeException(ex);
            }

            if (Password.equals(InputPassword) == false)
                System.out.println("Mật khẩu không đúng!");
            else
                switch (Role) {
                    case 0:
                        System.out.println("Giao dien MOH");
                        break;
                    case 1:
                        System.out.println("Giao dien dvtc");
                        break;
                    case 2:
                        System.out.println("Giao dien nguoi dung");
                        break;
                    default:
                        break;
                }
        }
    }

    @Override
    public void ancestorAdded(AncestorEvent event) {

    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {

    }

    @Override
    public void ancestorMoved(AncestorEvent event) {

    }
}
