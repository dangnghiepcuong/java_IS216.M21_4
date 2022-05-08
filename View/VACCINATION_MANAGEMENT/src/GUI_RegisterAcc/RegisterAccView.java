package GUI_RegisterAcc;

/*
COLOR HEX CODE
màu xám dành cho nền: F2F2F2
màu Peach dành cho button chức năng: FF9292
màu xám hơi đậm dành cho label: 666666
màu đen dành cho text nhập vào: 333333
*/

import Data_Processor.*;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/**
 *
 * @author LeHoangDuyen
 */
public class RegisterAccView extends JFrame implements ActionListener
{
    DefaultValue dv = new DefaultValue();
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JLabel RepeatPasswordLabel;
    private JLabel LastNameLabel;
    private JLabel FirstNameLabel;
    private JLabel IDLabel;
    private JLabel BirthdayLabel;
    private JLabel GenderLabel;
    private JLabel HomeTownLabel;
    private JLabel ProvinceLabel;
    private JLabel DistrictLabel;
    private JLabel TownLabel;
    private JLabel StreetLabel;
    private JLabel EmailLabel;
    private JTextField UsernameTextField;
    private JPasswordField PasswordField;
    private JPasswordField RepeatPasswordField;
    private JTextField LastNameTextField;
    private JTextField FirstNameTextField;
    private JTextField IDTextField;
    private JTextField StreetTextField;
    private JTextField EmailTextField;
    private JButton RegisterAccButton;
    private Choice GenderChoice;
    private Choice HomeTownChoice;
    private Choice ProvinceChoice;
    private Choice DistrictChoice;
    private Choice TownChoice;
    private JDatePickerImpl BirthdayField;
    private JPanel AccInfoPanel;
    private JPanel PersonalInfoPanel;
    private Person personalUser;

    private void initAccInfoPanel()
    {
        AccInfoPanel = new JPanel();

        AccInfoPanel.setLayout(null);
        AccInfoPanel.setBounds(0, 0,dv.FrameWidth()-dv.FrameHeight(),dv.FrameHeight() );
        AccInfoPanel.setBackground(new Color(dv.ViewBackgroundColor()));
        AccInfoPanel.setBorder(dv.border());

        //init UsernameLabel
        initUsernameLabel();
        AccInfoPanel.add(UsernameLabel);

        //init PasswordLabel
        initPasswordLabel();
        AccInfoPanel.add(PasswordLabel);

        //init RepeatPasswordLabel
        initRepeatPasswordLabel();
        AccInfoPanel.add(RepeatPasswordLabel);

        //init UsernameTextField
        initUsernameTextField();
        AccInfoPanel.add(UsernameTextField);

        //init PasswordField;
        initPasswordField();
        AccInfoPanel.add(PasswordField);

        //init RepeatPasswordLabel
        initRepeatPasswordField();
        AccInfoPanel.add(RepeatPasswordField);

        //init RegisterAccButton
        initRegisterAccButton();
        AccInfoPanel.add(RegisterAccButton);

        AccInfoPanel.validate();
    }

    private void initPersonalInfoPanel()
    {
        PersonalInfoPanel = new JPanel();

        PersonalInfoPanel.setLayout(null);
        PersonalInfoPanel.setBounds(dv.FrameWidth()-dv.FrameHeight(),0,dv.FrameHeight(),dv.FrameHeight());
        PersonalInfoPanel.setBackground(new Color(dv.ViewBackgroundColor()));
        PersonalInfoPanel.setBorder(dv.border());

        initLastNameLabel();
        PersonalInfoPanel.add(LastNameLabel);

        initLastNameTextField();
        PersonalInfoPanel.add(LastNameTextField);

        initFirstNameLabel();
        PersonalInfoPanel.add(FirstNameLabel);

        initFirstNameTextField();
        PersonalInfoPanel.add(FirstNameTextField);

        initIDLabel();
        PersonalInfoPanel.add(IDLabel);

        initIDTextField();
        PersonalInfoPanel.add(IDTextField);

        initBirthdayLabel();
        PersonalInfoPanel.add(BirthdayLabel);

        initBirthdayField();
        PersonalInfoPanel.add(BirthdayField);

        initGenderLabel();
        PersonalInfoPanel.add(GenderLabel);

        initGenderChoice();
        PersonalInfoPanel.add(GenderChoice);

        initHomeTownLabel();
        PersonalInfoPanel.add(HomeTownLabel);

        initHomeTownChoice();
        PersonalInfoPanel.add(HomeTownChoice);

        initProvinceLabel();
        PersonalInfoPanel.add(ProvinceLabel);

        initProvinceChoice();
        PersonalInfoPanel.add(ProvinceChoice);

        initDistrictLabel();
        PersonalInfoPanel.add(DistrictLabel);

        initDistrictChoice();
        PersonalInfoPanel.add(DistrictChoice);

        initTownLabel();
        PersonalInfoPanel.add(TownLabel);

        initTownChoice();
        PersonalInfoPanel.add(TownChoice);

        initStreetLabel();
        PersonalInfoPanel.add(StreetLabel);

        initStreetTextField();
        PersonalInfoPanel.add(StreetTextField);

        initEmailLabel();
        PersonalInfoPanel.add(EmailLabel);

        initEmailTextField();
        PersonalInfoPanel.add(EmailTextField);

        PersonalInfoPanel.validate();
    }

    private void initUsernameLabel()
    {
        UsernameLabel = new JLabel();
        UsernameLabel.setBounds(70, 80, 240, 30);
        UsernameLabel.setText("Số điện thoại");
        UsernameLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        UsernameLabel.setForeground(new Color(0x666666));
    }

    private void initUsernameTextField()
    {
        UsernameTextField = new JTextField();
        UsernameTextField.setBounds(70, 80+dv.LabelHeight(), 220, 30);
        UsernameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        UsernameTextField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        UsernameTextField.setForeground(new Color(0x333333));
        UsernameTextField.setBackground(Color.WHITE);
    }

    private void initPasswordLabel()
    {
        PasswordLabel = new JLabel();
        PasswordLabel.setBounds(70, 90 + dv.FieldHeight() + dv.LabelHeight(), 270, 30);
        PasswordLabel.setText("Mật khẩu");
        PasswordLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        PasswordLabel.setForeground(new Color(0x666666));
    }

    private void initPasswordField()
    {
        PasswordField = new JPasswordField();
        PasswordField.setBounds(70, 90 + dv.FieldHeight() + 2 * dv.LabelHeight(), 220, 30);
        PasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        PasswordField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        PasswordField.setForeground(new Color(0x333333));
        PasswordField.setBackground(Color.WHITE);
    }

    private void initRepeatPasswordLabel()
    {
        RepeatPasswordLabel = new JLabel();
        RepeatPasswordLabel.setBounds(70, 100 + 3*dv.FieldHeight() + dv.LabelHeight(), 240, 30);
        RepeatPasswordLabel.setText("Nhập lại mật khẩu");
        RepeatPasswordLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        RepeatPasswordLabel.setForeground(new Color(0x666666));
    }

    private void initRepeatPasswordField()
    {
        RepeatPasswordField = new JPasswordField();
        RepeatPasswordField.setBounds(70, 100 + 3*dv.FieldHeight() + 2 * dv.LabelHeight(), 220, 30);
        RepeatPasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        RepeatPasswordField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        RepeatPasswordField.setForeground(new Color(0x333333));
        RepeatPasswordField.setBackground(Color.WHITE);
    }

    private void initRegisterAccButton()
    {
        RegisterAccButton = new JButton();
        RegisterAccButton.setBounds(105, 350, 150, 49);
        RegisterAccButton.setBorder(null);
        RegisterAccButton.setContentAreaFilled(false);

        ImageIcon LoginIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/RegisterAcc.png"));
        RegisterAccButton.setIcon(LoginIcon);

        RegisterAccButton.addActionListener(this);
    }

    private void initLastNameLabel()
    {
        LastNameLabel = new JLabel();
        LastNameLabel.setBounds(70, 80, 240, 30);
        LastNameLabel.setText("Họ và tên đệm");
        LastNameLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        LastNameLabel.setForeground(new Color(0x666666));
    }
    private void initLastNameTextField()
    {
        LastNameTextField = new JTextField();
        LastNameTextField.setBounds(70, 80 + dv.LabelHeight(), 220, 30);
        LastNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        LastNameTextField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        LastNameTextField.setForeground(new Color(0x333333));
        LastNameTextField.setBackground(Color.WHITE);
    }

    private void initFirstNameLabel()
    {
        FirstNameLabel = new JLabel();
        FirstNameLabel.setBounds(70+220+25, 80, 240, 30);
        FirstNameLabel.setText("Tên");
        FirstNameLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        FirstNameLabel.setForeground(new Color(0x666666));
    }
    private void initFirstNameTextField()
    {
        FirstNameTextField = new JTextField();
        FirstNameTextField.setBounds(70+220+25, 80 + dv.LabelHeight(), 150, 30);
        FirstNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        FirstNameTextField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        FirstNameTextField.setForeground(new Color(0x333333));
        FirstNameTextField.setBackground(Color.WHITE);
    }

    private void initIDLabel()
    {
        IDLabel = new JLabel();
        IDLabel.setBounds(70, 90 + dv.LabelHeight()+dv.FieldHeight(), 240, 30);
        IDLabel.setText("Mã định danh");
        IDLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        IDLabel.setForeground(new Color(0x666666));

    }
    private void initIDTextField()
    {
        IDTextField = new JTextField();
        IDTextField.setBounds(70, 90 + 2*dv.LabelHeight()+dv.FieldHeight(), 220, 30);
        IDTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        IDTextField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        IDTextField.setForeground(new Color(0x333333));
        IDTextField.setBackground(Color.WHITE);
    }

    private void initBirthdayLabel()
    {
        BirthdayLabel = new JLabel();
        BirthdayLabel.setBounds(70, 100 + 2*dv.LabelHeight()+2*dv.FieldHeight(),220,30);
        BirthdayLabel.setText("Ngày tháng năm sinh");
        BirthdayLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        BirthdayLabel.setForeground(new Color(0x666666));
    }

    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
    private void initBirthdayField()
    {
        UtilDateModel model=new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        BirthdayField = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        BirthdayField.setBounds(70, 100 + 3*dv.LabelHeight()+2*dv.FieldHeight(),170,30);

        JFormattedTextField textField = BirthdayField.getJFormattedTextField();
        textField.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        textField.setBounds(70, 100 + 3*dv.LabelHeight()+2*dv.FieldHeight(),170,30);
        textField.setBackground(Color.WHITE);

        BirthdayField.setForeground(new Color(dv.BlackTextColor()));
        BirthdayField.setVisible(true);
        BirthdayField.setEnabled(true);
    }
    private void initGenderLabel()
    {
        GenderLabel = new JLabel();
        GenderLabel.setBounds(70 + 25 + 220, 100 + 2*dv.LabelHeight()+2*dv.FieldHeight(), 240, 30);
        GenderLabel.setText("Giới tính");
        GenderLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        GenderLabel.setForeground(new Color(0x666666));
    }
    private void initGenderChoice()
    {
        GenderChoice = new Choice();
        GenderChoice.setBounds(70 + 25 + 220, 100 + 3*dv.LabelHeight()+2*dv.FieldHeight(), 80, 30);
        GenderChoice.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        GenderChoice.setForeground(new Color(0x666666));
        GenderChoice.setBackground(Color.WHITE);

        GenderChoice.add("");
        GenderChoice.add("Nữ");
        GenderChoice.add("Nam");
        GenderChoice.add("Khác");
    }

    private void initHomeTownLabel()
    {
        HomeTownLabel = new JLabel();
        HomeTownLabel.setBounds(70, 110 + 3*dv.LabelHeight()+3*dv.FieldHeight(), 240, 30);
        HomeTownLabel.setText("Quê quán");
        HomeTownLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        HomeTownLabel.setForeground(new Color(0x666666));
    }

    private void initHomeTownChoice()
    {
        HomeTownChoice = new Choice();
        HomeTownChoice.setBounds(70, 110 + 4*dv.LabelHeight()+3*dv.FieldHeight(), 170, 30);
        HomeTownChoice.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        HomeTownChoice.setForeground(new Color(0x666666));
        HomeTownChoice.setBackground(Color.WHITE);

        HomeTownChoice.add("");
        HomeTownChoice.add("Bình Dương");
        HomeTownChoice.add("Hồ Chí Minh");
        HomeTownChoice.add("Hà Nội");
        HomeTownChoice.add("Phú Yên");
        HomeTownChoice.add("Đồng Nai");
        HomeTownChoice.add("Bình Định");
    }

    private void initProvinceLabel()
    {
        ProvinceLabel = new JLabel();
        ProvinceLabel.setBounds(70, 120 + 4*dv.LabelHeight()+4*dv.FieldHeight(), 240, 30);
        ProvinceLabel.setText("Tỉnh/thành phố cư trú");
        ProvinceLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        ProvinceLabel.setForeground(new Color(0x666666));
    }

    private void initProvinceChoice()
    {
        ProvinceChoice = new Choice();
        ProvinceChoice.setBounds(70, 120 + 5*dv.LabelHeight()+4*dv.FieldHeight(), 170, 30);
        ProvinceChoice.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        ProvinceChoice.setForeground(new Color(0x666666));
        ProvinceChoice.setBackground(Color.WHITE);

        ProvinceChoice.add("");
        ProvinceChoice.add("Bình Dương");
        ProvinceChoice.add("Hồ Chí Minh");
        ProvinceChoice.add("Đồng Nai");
        ProvinceChoice.add("Bình Định");
        ProvinceChoice.add("Phú Yên");
        ProvinceChoice.add("Hà Nội");
    }

    private void initDistrictLabel()
    {
        DistrictLabel = new JLabel();
        DistrictLabel.setBounds(70 + 25 +170, 120 + 4*dv.LabelHeight()+4*dv.FieldHeight(), 240, 30);
        DistrictLabel.setText("Quận/huyện cư trú");
        DistrictLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        DistrictLabel.setForeground(new Color(0x666666));
    }

    private void initDistrictChoice()
    {
        DistrictChoice = new Choice();
        DistrictChoice.setBounds(70+25+170, 120 + 5*dv.LabelHeight()+4*dv.FieldHeight(), 170, 30);
        DistrictChoice.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        DistrictChoice.setForeground(new Color(0x666666));
        DistrictChoice.setBackground(Color.WHITE);

        DistrictChoice.add("");
        DistrictChoice.add("Dầu Tiếng");
        DistrictChoice.add("Thuận An");
        DistrictChoice.add("Thủ Đức");
        DistrictChoice.add("Biên Hòa");
        DistrictChoice.add("Cẩm Mỹ");
        DistrictChoice.add("Thủ Dầu Một");
    }

    private void initTownLabel()
    {
        TownLabel = new JLabel();
        TownLabel.setBounds(70 + 50 +2*170, 120 + 4*dv.LabelHeight()+4*dv.FieldHeight(), 350, 30);
        TownLabel.setText("Xã/phường/thị trấn cư trú");
        TownLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        TownLabel.setForeground(new Color(0x666666));
    }

    private void initTownChoice()
    {
        TownChoice = new Choice();
        TownChoice.setBounds(70+50+2*170, 120 + 5*dv.LabelHeight()+4*dv.FieldHeight(), 170, 30);
        TownChoice.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        TownChoice.setForeground(new Color(0x666666));
        TownChoice.setBackground(Color.WHITE);

        TownChoice.add("");
        TownChoice.add("Dầu Tiếng");
        TownChoice.add("Lái Thiêu");
        TownChoice.add("Linh Trung");
        TownChoice.add("Tân Hòa");
        TownChoice.add("Sông Ray");
    }

    private void initStreetLabel()
    {
        StreetLabel = new JLabel();
        StreetLabel.setBounds(70, 130 + 5*dv.LabelHeight()+5*dv.FieldHeight(), 300, 30);
        StreetLabel.setText("Số nhà, tên đường, khu phố/ấp");
        StreetLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        StreetLabel.setForeground(new Color(0x666666));
    }

    private void initStreetTextField()
    {
        StreetTextField = new JTextField();
        StreetTextField.setBounds(70, 130 + 6*dv.LabelHeight()+5*dv.FieldHeight(), 560, 30);
        StreetTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        StreetTextField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        StreetTextField.setForeground(new Color(0x333333));
        StreetTextField.setBackground(Color.WHITE);
    }

    private void initEmailLabel()
    {
        EmailLabel = new JLabel();
        EmailLabel.setBounds(70, 140 + 6*dv.LabelHeight()+6*dv.FieldHeight(), 240, 30);
        EmailLabel.setText("Email");
        EmailLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        EmailLabel.setForeground(new Color(0x666666));
    }

    private void initEmailTextField()
    {
        EmailTextField = new JTextField();
        EmailTextField.setBounds(70, 140 + 7*dv.LabelHeight()+6*dv.FieldHeight(), 220, 30);
        EmailTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        EmailTextField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        EmailTextField.setForeground(new Color(0x333333));
        EmailTextField.setBackground(Color.WHITE);
    }
    private void initFrameComponent()
    {
        //Frame
        //set frame title
        this.setTitle("Đăng ký tài khoản cá nhân");

        //set frame size
        this.setBounds((1600-dv.FrameWidth())/2, (900-dv.FrameHeight())/2, dv.FrameWidth(), dv.FrameHeight());
        //this.setSize(1080, 720); --Main View

        //set do not allow frame resizing
        this.setResizable(false);

        //set frame visible on screen
        this.setVisible(true);

        //set frame close on X button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set frame background color
        this.setBackground(new Color(dv.ViewBackgroundColor()));

        //set Frame icon
        this.setIconImage(new ImageIcon(getClass().getResource("/Data_Processor/icon/Virus.png")).getImage());

        //set layout
        this.setLayout(null);

        //set account information Panel
        initAccInfoPanel();
        this.add(AccInfoPanel);

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());

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

        if (e.getSource() == RegisterAccButton) {

            JFormattedTextField textField = BirthdayField.getJFormattedTextField();


            String InputUsername = UsernameTextField.getText();
            String InputPassword = String.valueOf(PasswordField.getPassword());
            String InputRepeatPassword = String.valueOf(RepeatPasswordField.getPassword());
            String InputID = IDTextField.getText();
            String InputLastName = LastNameTextField.getText();
            String InputFirstName = FirstNameTextField.getText();
            String InputBirthday = dv.toOracleDateFormat(textField.getText());
            int InputGender = GenderChoice.getSelectedIndex();
            String InputHomeTown = HomeTownChoice.getSelectedItem();
            String InputProvince = dv.getProvinceCode(ProvinceChoice.getSelectedItem());
            String InputDistrict = DistrictChoice.getSelectedItem();
            String InputTown = TownChoice.getSelectedItem();
            String InputStreet = StreetTextField.getText();
            String InputEmail = EmailTextField.getText();

            if (InputPassword.equals(InputRepeatPassword) == false)
            {
                System.out.println("Mật khẩu không trùng khớp!");
                return;
            }

            if (InputGender == 0)
            {
                System.out.println("Thiết lập thông tin giới tính!");
                return;
            }
            else
                InputGender -= 1;

            if (InputHomeTown.equals(""))
            {
                System.out.println("Thiết lập thông tin quê quán!");
                return;
            }

            if (InputProvince.equals(""))
            {
                System.out.println("Thiết lập thông tin  Tỉnh/thành phố cư trú!");
                return;
            }

            if (InputDistrict.equals(""))
            {
                System.out.println("Thiết lập thông tin  Quận/huyện cư trú!");
                return;
            }
            if (InputTown.equals(""))
            {
                System.out.println("Thiết lập thông tin Xã/phường/thị trấn phố cư trú!");
                return;
            }

            String plsql = "{call ACC_INSERT_RECORD(?,?,?,?,?)}";


            String plsql2 = "{call PERSON_INSERT_RECORD(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                CallableStatement cst = connection.prepareCall(plsql);
                cst.setString(1, InputUsername);
                cst.setString(2, InputPassword);
                cst.setInt(3, Integer.valueOf(2));
                cst.setInt(4, Integer.valueOf(1));
                cst.setString(5, "");

                cst.execute();

                /*cst = connection.prepareCall(plsql2);
                cst.setString("ID", InputID);
                cst.setString("LastName", InputLastName);
                cst.setString("FirstName", InputFirstName);
                cst.setString("Birthday", InputBirthday);
                cst.setInt("Gender", Integer.valueOf(InputGender));
                cst.setString("Hometown", InputHomeTown);
                cst.setString("Province", InputProvince);
                cst.setString("District", InputDistrict);
                cst.setString("Town", InputTown);
                cst.setString("Street", InputStreet);
                cst.setString("Phone", InputUsername);
                cst.setString("Email", InputEmail);
                cst.setString("Guardian", "");
                cst.setString("Note", "");

                cst.execute();*/

            }
            catch (SQLException ex) {
                System.out.println("Không thành công!");
                throw new RuntimeException(ex);

            }

            System.out.println("Đăng ký thành công!");

        }
    }



}
