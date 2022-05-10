package GUI_UserInformation;

/*
COLOR HEX CODE
màu xám dành cho nền: F2F2F2
màu Peach dành cho button chức năng: FF9292
màu xám hơi đậm dành cho label: 666666
màu đen dành cho text nhập vào: 333333
*/

import Data_Processor.*;
import GUI_Login.LoginView;
import GUI_RegisterAcc.RegisterAccView;
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
public class UserInformationView extends JPanel implements ActionListener
{
    DefaultValue dv = new DefaultValue();
    private JLabel UsernameLabel;
    private JLabel OldPasswordLabel;
    private JLabel NewPasswordLabel;
    private JLabel RepeatNewPasswordLabel;
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
    private JPasswordField OldPasswordField;
    private JPasswordField NewPasswordField;
    private JPasswordField RepeatNewPasswordField;
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

    public Person getPersonalUser() {
        return personalUser;
    }

    private Person personalUser;

    private void initAccInfoPanel()
    {
        AccInfoPanel = new JPanel();

        AccInfoPanel.setLayout(null);
        AccInfoPanel.setBounds(0, 0,dv.FrameWidth()-dv.FrameHeight(),dv.FrameHeight() );
        AccInfoPanel.setBackground(new Color(dv.ViewBackgroundColor()));
        AccInfoPanel.setBorder(dv.border());

        JLabel AccInfoLabel = new JLabel("THÔNG TIN TÀI KHOẢN");
        AccInfoLabel.setBounds((AccInfoPanel.getWidth()-300)/2, 40, 300, 30);
        AccInfoLabel.setFont(new Font(dv.fontName(), 1, 20));
        AccInfoLabel.setForeground(new Color(dv.FeatureButtonColor()));
        AccInfoLabel.setHorizontalAlignment(JLabel.CENTER);

        AccInfoPanel.add(AccInfoLabel);

        //init UsernameLabel
        initUsernameLabel();
        AccInfoPanel.add(UsernameLabel);

        //init OldPasswordLabel
        initOldPasswordLabel();
        AccInfoPanel.add(OldPasswordLabel);

        //init NewPasswordLabel
        initNewPasswordLabel();
        AccInfoPanel.add(NewPasswordLabel);

        //init UsernameTextField
        initUsernameTextField();
        AccInfoPanel.add(UsernameTextField);

        //init OldPasswordField;
        initOldPasswordField();
        AccInfoPanel.add(OldPasswordField);

        //init NewPasswordField
        initNewPasswordField();
        AccInfoPanel.add(NewPasswordField);

        //init RepeatNewPasswordLabel;
        initRepeatNewPasswordLabel();
        this.add(RepeatNewPasswordLabel);

        //initRepeatNewPasswordField
        initRepeatNewPasswordField();
        this.add(RepeatNewPasswordField);

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

        JLabel PersonalInfoLabel = new JLabel("THÔNG TIN CÁ NHÂN");
        PersonalInfoLabel.setBounds((PersonalInfoPanel.getWidth()-300)/2, 40, 300, 30);
        PersonalInfoLabel.setFont(new Font(dv.fontName(), 1, 20));
        PersonalInfoLabel.setForeground(new Color(dv.FeatureButtonColor()));
        PersonalInfoLabel.setHorizontalAlignment(JLabel.CENTER);

        PersonalInfoPanel.add(PersonalInfoLabel);

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
        UsernameLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        UsernameLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initUsernameTextField()
    {
        UsernameTextField = new JTextField(personalUser.getPhone());
        UsernameTextField.setBounds(70, 80+dv.LabelHeight(), 220, 30);
        UsernameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        UsernameTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        UsernameTextField.setForeground(new Color(dv.BlackTextColor()));
    }

    private void initOldPasswordLabel()
    {
        OldPasswordLabel = new JLabel();
        OldPasswordLabel.setBounds(70, 90 + dv.FieldHeight() + dv.LabelHeight(), 270, 30);
        OldPasswordLabel.setText("Nhập mật khẩu cũ");
        OldPasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        OldPasswordLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initOldPasswordField()
    {
        OldPasswordField = new JPasswordField();
        OldPasswordField.setBounds(70, 90 + dv.FieldHeight() + 2 * dv.LabelHeight(), 220, 30);
        OldPasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        OldPasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        OldPasswordField.setForeground(new Color(dv.BlackTextColor()));
    }

    private void initNewPasswordLabel()
    {
        NewPasswordLabel = new JLabel();
        NewPasswordLabel.setBounds(70, 100 + 3*dv.FieldHeight() + dv.LabelHeight(), 240, 30);
        NewPasswordLabel.setText("Nhập mật khẩu mới");
        NewPasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        NewPasswordLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initNewPasswordField()
    {
        NewPasswordField = new JPasswordField();
        NewPasswordField.setBounds(70, 100 + 3*dv.FieldHeight() + 2 * dv.LabelHeight(), 220, 30);
        NewPasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        NewPasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        NewPasswordField.setForeground(new Color(dv.BlackTextColor()));
    }
    
    private void initRepeatNewPasswordLabel()
    {
        RepeatNewPasswordLabel = new JLabel();
        RepeatNewPasswordLabel.setBounds(70, 110 + 5*dv.FieldHeight() + dv.LabelHeight(), 240, 30);
        RepeatNewPasswordLabel.setText("Nhập lại mật khẩu mới");
        RepeatNewPasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        RepeatNewPasswordLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initRepeatNewPasswordField()
    {
        RepeatNewPasswordField = new JPasswordField();
        RepeatNewPasswordField.setBounds(70, 110 + 4*dv.FieldHeight() + 3 * dv.LabelHeight(), 220, 30);
        RepeatNewPasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        RepeatNewPasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        RepeatNewPasswordField.setForeground(new Color(dv.BlackTextColor()));
    }

    private void initRegisterAccButton()
    {

        ImageIcon RegisterButtonIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Update Button.png"));
        RegisterAccButton = new JButton();
        RegisterAccButton.setBounds(105, 450, RegisterButtonIcon.getIconWidth(), RegisterButtonIcon.getIconHeight());
        RegisterAccButton.setBorder(null);
        RegisterAccButton.setContentAreaFilled(false);
        RegisterAccButton.setIcon(RegisterButtonIcon);

        RegisterAccButton.addActionListener(this);
    }

    private void initLastNameLabel()
    {
        LastNameLabel = new JLabel();
        LastNameLabel.setBounds(50, 80, 240, 30);
        LastNameLabel.setText("Họ và tên đệm");
        LastNameLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        LastNameLabel.setForeground(new Color(dv.FieldLabelColor()));
    }
    private void initLastNameTextField()
    {
        LastNameTextField = new JTextField(personalUser.getLastName());
        LastNameTextField.setBounds(50, 80 + dv.LabelHeight(), 220, 30);
        LastNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        LastNameTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        LastNameTextField.setForeground(new Color(dv.BlackTextColor()));
    }

    private void initFirstNameLabel()
    {
        FirstNameLabel = new JLabel();
        FirstNameLabel.setBounds(50+220+25, 80, 240, 30);
        FirstNameLabel.setText("Tên");
        FirstNameLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        FirstNameLabel.setForeground(new Color(dv.FieldLabelColor()));
    }
    private void initFirstNameTextField()
    {
        FirstNameTextField = new JTextField(personalUser.getFirstName());
        FirstNameTextField.setBounds(50+220+25, 80 + dv.LabelHeight(), 150, 30);
        FirstNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        FirstNameTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        FirstNameTextField.setForeground(new Color(dv.BlackTextColor()));
    }

    private void initIDLabel()
    {
        IDLabel = new JLabel();
        IDLabel.setBounds(50, 90 + dv.LabelHeight()+dv.FieldHeight(), 240, 30);
        IDLabel.setText("Mã định danh");
        IDLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        IDLabel.setForeground(new Color(dv.FieldLabelColor()));

    }
    private void initIDTextField()
    {
        IDTextField = new JTextField(personalUser.getID());
        IDTextField.setEditable(false);
        IDTextField.setBounds(50, 90 + 2*dv.LabelHeight()+dv.FieldHeight(), 220, 30);
        IDTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        IDTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        IDTextField.setForeground(new Color(dv.BlackTextColor()));
    }

    private void initBirthdayLabel()
    {
        BirthdayLabel = new JLabel();
        BirthdayLabel.setBounds(50, 100 + 2*dv.LabelHeight()+2*dv.FieldHeight(),220,30);
        BirthdayLabel.setText("Ngày tháng năm sinh");
        BirthdayLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        BirthdayLabel.setForeground(new Color(dv.FieldLabelColor()));
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

        BirthdayField.setBounds(50, 100 + 3*dv.LabelHeight()+2*dv.FieldHeight(),170,40);

        JFormattedTextField textField = BirthdayField.getJFormattedTextField();
        textField.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        textField.setBounds(50, 100 + 3*dv.LabelHeight()+2*dv.FieldHeight(),170,40);

        BirthdayField.setTextEditable(true);
        BirthdayField.setName("BirthdayField");
        BirthdayField.setForeground(new Color(dv.BlackTextColor()));
        BirthdayField.setVisible(true);
        BirthdayField.setEnabled(true);
    }

    private void initGenderLabel()
    {
        GenderLabel = new JLabel();
        GenderLabel.setBounds(50 + 25 + 220, 100 + 2*dv.LabelHeight()+2*dv.FieldHeight(), 240, 30);
        GenderLabel.setText("Giới tính");
        GenderLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        GenderLabel.setForeground(new Color(dv.FieldLabelColor()));
    }
    private void initGenderChoice()
    {
        GenderChoice = new Choice();
        GenderChoice.setBounds(50 + 25 + 220, 100 + 3*dv.LabelHeight()+2*dv.FieldHeight(), 80, 28);
        GenderChoice.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        GenderChoice.setForeground(new Color(dv.FieldLabelColor()));

        GenderChoice.add(dv.getGenderName(personalUser.getGender()));
        GenderChoice.add("Nữ");
        GenderChoice.add("Nam");
        GenderChoice.add("Khác");

        GenderChoice.setName(dv.getGenderName(personalUser.getGender()));
    }

    private void initHomeTownLabel()
    {
        HomeTownLabel = new JLabel();
        HomeTownLabel.setBounds(50, 110 + 3*dv.LabelHeight()+3*dv.FieldHeight(), 240, 28);
        HomeTownLabel.setText("Quê quán");
        HomeTownLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        HomeTownLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initHomeTownChoice()
    {
        HomeTownChoice = new Choice();
        HomeTownChoice.setBounds(50, 110 + 4*dv.LabelHeight()+3*dv.FieldHeight(), 170, 30);
        HomeTownChoice.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        HomeTownChoice.setForeground(new Color(dv.FieldLabelColor()));

        HomeTownChoice.add(dv.getProvinceName(personalUser.getProvince()));

        for (int i = 1; i <= 64; i++)
            if (dv.getProvinceList()[i] != dv.getProvinceName(personalUser.getProvince()) && i != 20)
                HomeTownChoice.add(dv.getProvinceList()[i]);
    }

    private void initProvinceLabel()
    {
        ProvinceLabel = new JLabel();
        ProvinceLabel.setBounds(50, 120 + 4*dv.LabelHeight()+4*dv.FieldHeight(), 240, 30);
        ProvinceLabel.setText("Tỉnh/thành phố cư trú");
        ProvinceLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initProvinceChoice()
    {
        ProvinceChoice = new Choice();
        ProvinceChoice.setBounds(50, 120 + 5*dv.LabelHeight()+4*dv.FieldHeight(), 170, 30);
        ProvinceChoice.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceChoice.setForeground(new Color(dv.FieldLabelColor()));

        ProvinceChoice.add(dv.getProvinceName(personalUser.getProvince()));

        for (int i = 1; i <= 64; i++)
            if (dv.getProvinceList()[i] != dv.getProvinceName(personalUser.getProvince()) && i != 20)
                ProvinceChoice.add(dv.getProvinceList()[i]);
    }

    private void initDistrictLabel()
    {
        DistrictLabel = new JLabel();
        DistrictLabel.setBounds(50 + 25 +170, 120 + 4*dv.LabelHeight()+4*dv.FieldHeight(), 240, 30);
        DistrictLabel.setText("Quận/huyện cư trú");
        DistrictLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initDistrictChoice()
    {
        DistrictChoice = new Choice();
        DistrictChoice.setBounds(50+25+170, 120 + 5*dv.LabelHeight()+4*dv.FieldHeight(), 170, 30);
        DistrictChoice.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictChoice.setForeground(new Color(dv.FieldLabelColor()));

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
        TownLabel.setBounds(50 + 50 +2*170, 120 + 4*dv.LabelHeight()+4*dv.FieldHeight(), 350, 30);
        TownLabel.setText("Xã/phường/thị trấn cư trú");
        TownLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initTownChoice()
    {
        TownChoice = new Choice();
        TownChoice.setBounds(50+50+2*170, 120 + 5*dv.LabelHeight()+4*dv.FieldHeight(), 170, 30);
        TownChoice.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownChoice.setForeground(new Color(dv.FieldLabelColor()));

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
        StreetLabel.setBounds(50, 130 + 5*dv.LabelHeight()+5*dv.FieldHeight(), 300, 30);
        StreetLabel.setText("Số nhà, tên đường, khu phố/ấp");
        StreetLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        StreetLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initStreetTextField()
    {
        StreetTextField = new JTextField();
        StreetTextField.setBounds(50, 130 + 6*dv.LabelHeight()+5*dv.FieldHeight(), 560, 30);
        StreetTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        StreetTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        StreetTextField.setForeground(new Color(dv.BlackTextColor()));
    }

    private void initEmailLabel()
    {
        EmailLabel = new JLabel();
        EmailLabel.setBounds(50, 140 + 6*dv.LabelHeight()+6*dv.FieldHeight(), 240, 30);
        EmailLabel.setText("Email");
        EmailLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        EmailLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initEmailTextField()
    {
        EmailTextField = new JTextField();
        EmailTextField.setBounds(50, 140 + 7*dv.LabelHeight()+6*dv.FieldHeight(), 220, 30);
        EmailTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        EmailTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        EmailTextField.setForeground(new Color(dv.BlackTextColor()));
    }
    private void initFrameComponent()
    {
        //Frame
        //set frame title
//        this.setTitle("Đăng ký tài khoản cá nhân");

        //set frame size
        this.setBounds(0, 0, dv.FrameWidth(), dv.FrameHeight());
        //this.setSize(1080, 720); --Main View

        //set do not allow frame resizing
//        this.setResizable(false);

        //set frame visible on screen
        this.setVisible(true);

        //set frame close on X button
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set frame background color
        this.setBackground(new Color(dv.ViewBackgroundColor()));

        //set Frame icon
//        this.setIconImage(new ImageIcon(getClass().getResource("/Data_Processor/icon/Virus.png")).getImage());

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

    public UserInformationView(Person perUser)
    {
        personalUser = perUser;

        initFrameComponent();

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == RegisterAccButton) {

            JFormattedTextField textField = BirthdayField.getJFormattedTextField();


            String InputUsername = UsernameTextField.getText();
            String InputPassword = String.valueOf(OldPasswordField.getPassword());
            String InputRepeatPassword = String.valueOf(NewPasswordField.getPassword());
            String InputID = IDTextField.getText();
            String InputLastName = LastNameTextField.getText();
            String InputFirstName = FirstNameTextField.getText();
            String InputBirthday = textField.getText();
            int InputGender = GenderChoice.getSelectedIndex();
            String InputHomeTown = HomeTownChoice.getSelectedItem();
            String InputProvince = ProvinceChoice.getSelectedItem();
            String InputDistrict = DistrictChoice.getSelectedItem();
            String InputTown = TownChoice.getSelectedItem();
            String InputStreet = StreetTextField.getText();
            String InputEmail = EmailTextField.getText();

            if ( dv.checkStringInputValue(InputUsername, "Cảnh báo!", "Nhập tên tài khoản!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputPassword, "Cảnh báo!","Nhập mật khẩu!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputRepeatPassword, "Cảnh báo!","Nhập lại mật khẩu!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputID, "Cảnh báo!","Nhập mã định danh cá nhân!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputFirstName, "Cảnh báo!","Nhập tên công dân!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputBirthday, "Cảnh báo!","Nhập ngày sinh!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputProvince, "Cảnh báo!", "Nhập tỉnh cư trú!") != -2 )
                return;
            else
                InputProvince = dv.getProvinceCode(InputProvince);
            if (InputGender == 0)
                InputGender = 3;

            if (InputPassword.equals(InputRepeatPassword) == false)
            {
                System.out.println("Mật khẩu không trùng khớp!");
                return;
            }

            InputBirthday = dv.toOracleDateFormat(InputBirthday);
            InputGender -= 1;


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

                cst = connection.prepareCall(plsql2);
                cst.setString("par_ID", InputID);
                cst.setString("par_LastName", InputLastName);
                cst.setString("par_FirstName", InputFirstName);
                cst.setString("par_Birthday", InputBirthday);
                cst.setInt("par_Gender", Integer.valueOf(InputGender));
                cst.setString("par_Hometown", InputHomeTown);
                cst.setString("par_Province", InputProvince);
                cst.setString("par_District", InputDistrict);
                cst.setString("par_Town", InputTown);
                cst.setString("par_Street", InputStreet);
                cst.setString("par_Phone", InputUsername);
                cst.setString("par_Email", InputEmail);
                cst.setString("par_Guardian", "");
                cst.setString("par_Note", "");

                cst.execute();

            }
            catch (SQLException ex) {
                System.out.println("Không thành công!");

                dv.popupOption(null, "Không thành công", "Lỗi!", 2);

                throw new RuntimeException(ex);
            }

            dv.popupOption(null, "Đăng ký thành công!", "Thông báo!", 0);
        }
    }



}
