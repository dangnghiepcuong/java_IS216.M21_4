package GUI_Login;

import Data_Processor.*;
import GUI_Main.CitizenMainView;
import GUI_Main.MOHMainView;
import GUI_Main.ORGMainView;
/*
COLOR HEX CODE
màu xám dành cho nền: F2F2F2
màu Peach dành cho button chức năng: FF9292
màu xám hơi đậm dành cho label: 666666
màu đen dành cho text nhập vào: 333333
*/

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
    //private JPanel ViewSymbolPanel;
    private DefaultValue dv = new DefaultValue();
    private JLabel ForgotPasswordLabel;
    private  JLabel RegisterAccountLabel;

    private Account acc = new Account();

    private void initViewSymbol()
    {
        ViewSymbol = new JLabel();
        ImageIcon Virus = new ImageIcon(getClass().getResource("/Data_Processor/icon/Virus.png"));
        ViewSymbol.setBounds(130, 50, 100, 100);
        ViewSymbol.setIcon(Virus);
        ViewSymbol.setHorizontalAlignment(JLabel.CENTER);
    }

    private void initUsernameLabel()
    {
        UsernameLabel = new JLabel();
        UsernameLabel.setBounds(80, 165, dv.LabelWidth(), dv.LabelHeight());
        UsernameLabel.setText("SĐT/Tên tài khoản");
        UsernameLabel.setFont(new Font("SVN-Arial", 3, 20));
        UsernameLabel.setForeground(new Color(0x666666));
        UsernameLabel.setHorizontalAlignment(JLabel.LEFT);
        UsernameLabel.setVerticalAlignment(JLabel.CENTER);
    }

    private void initUsernameTextField()
    {
        UsernameTextField = new JTextField();
        UsernameTextField.setBounds(80, 195, dv.FieldWidth(), dv.FieldHeight());
        UsernameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        UsernameTextField.setFont(new Font("SVN-Arial", Font.PLAIN, 16));
        UsernameTextField.setForeground(new Color(0x333333));
        UsernameTextField.setBackground(Color.WHITE);
    }

    private void initPasswordLabel()
    {
        PasswordLabel = new JLabel();
        PasswordLabel.setBounds(80, 235, dv.LabelWidth(), dv.LabelHeight());
        PasswordLabel.setText("Mật khẩu");
        PasswordLabel.setFont(new Font("SVN-Arial", 3, 20));
        PasswordLabel.setForeground(new Color(0x666666));
        PasswordLabel.setHorizontalTextPosition(JLabel.LEFT);
        PasswordLabel.setVerticalTextPosition(JLabel.CENTER);
    }

    private void initPasswordField()
    {
        PasswordField = new JPasswordField();
        PasswordField.setBounds(80, 265, dv.FieldWidth(), dv.FieldHeight());
        PasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        PasswordField.setFont(new Font("SVN-Arial", Font.PLAIN, 16));
        PasswordField.setForeground(new Color(dv.FieldLabelColor()));
        PasswordField.setBackground(Color.WHITE);
    }

    private void initForgotPasswordLabel()
    {
        ForgotPasswordLabel = new JLabel();
        ForgotPasswordLabel.setBounds(80, 270+dv.FieldHeight(), 120, 25);
        ForgotPasswordLabel.setText("Quên mật khẩu");
        ForgotPasswordLabel.setFont(new Font("SVN-Arial",Font.ITALIC, 12));
        ForgotPasswordLabel.setForeground(new Color(dv.FieldLabelColor()));
        ForgotPasswordLabel.addAncestorListener(this);
        //ForgotPasswordLabel.setBorder(dv.border());
    }

    private void initRegisterAccountLabel()
    {
        RegisterAccountLabel = new JLabel();
        RegisterAccountLabel.setBounds(80+120-1, 270+dv.FieldHeight(), 80, 25);
        RegisterAccountLabel.setText("Đăng ký");
        RegisterAccountLabel.setFont(new Font("SVN-Arial",Font.ITALIC, 12));
        RegisterAccountLabel.setForeground(new Color(dv.FieldLabelColor()));
        RegisterAccountLabel.setHorizontalAlignment(JLabel.RIGHT);
        RegisterAccountLabel.addAncestorListener(this);
        //RegisterAccountLabel.setBorder(dv.border());
    }

    private void initLoginButton()
    {
        LoginButton = new JButton();
        LoginButton.setBounds(105, 380, 150, 49);
        LoginButton.setBorder(null);
        LoginButton.setContentAreaFilled(false);
        ImageIcon LoginIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Login Button.png"));
        LoginButton.setIcon(LoginIcon);
        LoginButton.addActionListener(this);
    }

    private void initFrameComponent()
    {
        //Frame
        //set frame title
        this.setTitle("Đăng nhập");

        //set frame size
        this.setBounds((1600-380)/2, (900-520)/2, 380, 520);
        //this.setSize(1080, 720); --Main View

        //set do not allow frame resizing
        this.setResizable(false);

        //set frame visible on screen
        this.setVisible(true);

        //set frame close on X button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set frame background color
        this.getContentPane().setBackground(new Color(dv.ViewBackgroundColor()));

        //set Frame icon
        this.setIconImage(new ImageIcon(getClass().getResource("/Data_Processor/icon/Virus.png")).getImage());

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


            String query = "select *" +
                    " from ACCOUNT" +
                    " where Username = '" + InputUsername + "'";


            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                PreparedStatement st = connection.prepareStatement(query);

                ResultSet rs = st.executeQuery(query);

                rs.next();
                acc.setUsername(rs.getString("Username"));
                acc.setPassword(rs.getString("Password"));
                acc.setRole(rs.getInt("Role"));

            } catch (SQLException ex) {

                System.out.println("Tài khoản không tồn tại!");

                throw new RuntimeException(ex);
            }

            if (acc.getPassword().equals(InputPassword) == false)
                System.out.println("Mật khẩu không đúng!");
            else
                switch (acc.getRole()) {
                    case 0:
                        this.dispose();
                        MOHMainView mohMainView = new MOHMainView(acc.getUsername());
                        break;
                    case 1:
                        this.dispose();
                        ORGMainView orgMainView = new ORGMainView(acc.getUsername());
                        break;
                    case 2:
                        this.dispose();
                        CitizenMainView citizenMainView = new CitizenMainView(acc.getUsername());
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

    public static void main(String args[])
    {
        LoginView loginView = new LoginView();
    }
}
