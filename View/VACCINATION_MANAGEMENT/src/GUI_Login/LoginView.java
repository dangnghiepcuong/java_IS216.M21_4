package GUI_Login;

import Data_Processor.*;
import GUI_Main.*;
import GUI_RegisterAcc.RegisterAccView;
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
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author NghiepCuong
 */
public class LoginView extends JFrame implements ActionListener, MouseListener {
    private JLabel ViewSymbol;
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JTextField UsernameTextField;
    private JPasswordField PasswordField;
    private JButton LoginButton;
    //private JPanel ViewSymbolPanel;
    private DefaultValue dv = new DefaultValue();
    private JLabel ForgotPasswordLabel;
    private JLabel RegisterAccountLabel;
    private JLabel ExitViewLabel;

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
        UsernameLabel.setFont(new Font(dv.fontName(), 3, 20));
        UsernameLabel.setForeground(new Color(0x666666));
        UsernameLabel.setHorizontalAlignment(JLabel.LEFT);
        UsernameLabel.setVerticalAlignment(JLabel.CENTER);
    }

    private void initUsernameTextField()
    {
        UsernameTextField = new JTextField();
        UsernameTextField.setBounds(80, 195, dv.FieldWidth(), dv.FieldHeight());
        UsernameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        UsernameTextField.setFont(new Font(dv.fontName(), Font.PLAIN, 16));
        UsernameTextField.setForeground(new Color(0x333333));
        UsernameTextField.setBackground(Color.WHITE);
    }

    private void initPasswordLabel()
    {
        PasswordLabel = new JLabel();
        PasswordLabel.setBounds(80, 235, dv.LabelWidth(), dv.LabelHeight());
        PasswordLabel.setText("Mật khẩu");
        PasswordLabel.setFont(new Font(dv.fontName(), 3, 20));
        PasswordLabel.setForeground(new Color(0x666666));
        PasswordLabel.setHorizontalTextPosition(JLabel.LEFT);
        PasswordLabel.setVerticalTextPosition(JLabel.CENTER);
    }

    private void initPasswordField()
    {
        PasswordField = new JPasswordField();
        PasswordField.setBounds(80, 265, dv.FieldWidth(), dv.FieldHeight());
        PasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        PasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, 16));
        PasswordField.setForeground(new Color(dv.FieldLabelColor()));
        PasswordField.setBackground(Color.WHITE);
    }

    private void initForgotPasswordLabel()
    {
        ForgotPasswordLabel = new JLabel();
        ForgotPasswordLabel.setBounds(80, 270+dv.FieldHeight(), 120, 25);
        ForgotPasswordLabel.setText("Quên mật khẩu");
        ForgotPasswordLabel.setFont(new Font(dv.fontName(),Font.ITALIC, 12));
        ForgotPasswordLabel.setForeground(new Color(dv.FieldLabelColor()));
        ForgotPasswordLabel.addMouseListener(this);
        //ForgotPasswordLabel.setBorder(dv.border());
    }

    private void initRegisterAccountLabel()
    {
        RegisterAccountLabel = new JLabel();
        RegisterAccountLabel.setBounds(80+120-1, 270+dv.FieldHeight(), 80, 25);
        RegisterAccountLabel.setText("Đăng ký");
        RegisterAccountLabel.setFont(new Font(dv.fontName(),Font.ITALIC, 12));
        RegisterAccountLabel.setForeground(new Color(dv.FieldLabelColor()));
        RegisterAccountLabel.setHorizontalAlignment(JLabel.RIGHT);
        RegisterAccountLabel.addMouseListener(this);
        //RegisterAccountLabel.setBorder(dv.border());
    }

    private void initExitViewLabel()
    {
        ExitViewLabel = new JLabel();
        ExitViewLabel.setBounds(this.getWidth()-55, this.getHeight()-58, 40, 20);
        ExitViewLabel.setText("Thoát");
        ExitViewLabel.setFont(new Font(dv.fontName(),Font.ITALIC, 12));
        ExitViewLabel.setForeground(new Color(dv.FieldLabelColor()));
        ExitViewLabel.setHorizontalAlignment(JLabel.CENTER);
//        ExitViewLabel.setBorder(dv.border());
        ExitViewLabel.addMouseListener(this);
    }

    private void initLoginButton()
    {
        ImageIcon LoginIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Login Button.png"));
        LoginButton = new JButton();
        LoginButton.setBounds(105, 380, LoginIcon.getIconWidth(), LoginIcon.getIconHeight());
        LoginButton.setBorder(null);
        LoginButton.setContentAreaFilled(false);
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

        //init ExitViewLabel
        initExitViewLabel();
        this.add(ExitViewLabel);

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
                dv.popupOption(this, "Nhập vào tài khoản/mật khẩu!", "Cảnh báo!", 1);
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
                acc.setStatus(rs.getInt("Status"));

            } catch (SQLException ex) {
                dv.popupOption(this, "Tài khoản không tồn tại!", "Lỗi " + ex.getErrorCode(), 2);
                throw new RuntimeException(ex);
            }

            if (acc.getPassword().equals(InputPassword) == false)
            {
                dv.popupOption(this, "Mật khẩu không đúng!", "Cảnh báo!", 1);
                return;
            }

            if (acc.getStatus() == 0)
            {
                dv.popupOption(this, "Tài khoản đã bị khóa!", "Cảnh báo!", 1);
                return;
            }

            if (acc.getStatus() == 2)
            {
                dv.popupOption(this, "Tài khoản đang được đăng nhập!", "Lỗi ", 2);
                return;
            }

            else
            {
                query = "update ACCOUNT ACC set Status = 2 where ACC.Username = '" + acc.getUsername() + "'";

                try {
                    Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                    PreparedStatement st = connection.prepareStatement(query);

                    st.executeQuery(query);
                } catch (SQLException ex) {
                    dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(),2);
                    ex.printStackTrace();
                }


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
    }


    public static void main(String args[])
    {
        LoginView loginView = new LoginView();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (e.getSource() == RegisterAccountLabel)
        {
            this.dispose();
            RegisterAccView registerAccView = new RegisterAccView();
        }

        if (e.getSource() == ExitViewLabel)
        {
            this.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}