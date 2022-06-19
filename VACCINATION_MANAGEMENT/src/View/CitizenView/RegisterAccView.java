package View.CitizenView;

import Process.*;
import View.LoginView;
import org.jdatepicker.impl.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author LeHoangDuyen
 */
public class RegisterAccView extends JFrame implements ActionListener, KeyListener, ItemListener {
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
    private JLabel AddressLabel;
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
    private JComboBox GenderComboBox;
    private JComboBox HomeTownComboBox;
    private JComboBox ProvinceComboBox;
    private JComboBox DistrictComboBox;
    private JComboBox TownComboBox;
    private JDatePickerImpl BirthdayField;
    private JPanel AccInfoPanel;
    private JPanel PersonalInfoPanel;

    private JButton BackButton;

    private void initBackButton()
    {
        BackButton = new JButton();
        ImageIcon BackButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Back Button_2.png"));
        BackButton.setIcon(BackButtonIcon);

        BackButton.setBounds(10, 10, BackButtonIcon.getIconWidth(), BackButtonIcon.getIconHeight());
        BackButton.setBorder(null);
        BackButton.setContentAreaFilled(false);

        BackButton.addActionListener(this);
    }

    private void initAccInfoPanel()
    {
        AccInfoPanel = new JPanel();

        AccInfoPanel.setLayout(null);
        AccInfoPanel.setBounds(0, 0,dv.FrameWidth()-dv.FrameHeight(),dv.FrameHeight() );
        AccInfoPanel.setBackground(new Color(dv.ViewBackgroundColor()));
        AccInfoPanel.setBorder(dv.border());

        JLabel AccInfoLabel = new JLabel("THÔNG TIN TÀI KHOẢN");
        AccInfoLabel.setBounds((AccInfoPanel.getWidth()-300)/2, dv.AlignTop_InfoView(), 300, 30);
        AccInfoLabel.setFont(new Font(dv.fontName(), 1, 20));
        AccInfoLabel.setForeground(new Color(dv.FeatureButtonColor()));
        AccInfoLabel.setHorizontalAlignment(JLabel.CENTER);

        AccInfoPanel.add(AccInfoLabel);

        initBackButton();
        AccInfoPanel.add(BackButton);

        initUsernameLabel();
        AccInfoPanel.add(UsernameLabel);

        initPasswordLabel();
        AccInfoPanel.add(PasswordLabel);

        initRepeatPasswordLabel();
        AccInfoPanel.add(RepeatPasswordLabel);

        initUsernameTextField();
        AccInfoPanel.add(UsernameTextField);

        initPasswordField();
        AccInfoPanel.add(PasswordField);

        initRepeatPasswordField();
        AccInfoPanel.add(RepeatPasswordField);

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
        PersonalInfoLabel.setBounds((PersonalInfoPanel.getWidth()-300)/2, dv.AlignTop_InfoView(), 300, 30);
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

        initGenderComboBox();
        PersonalInfoPanel.add(GenderComboBox);

        initHomeTownLabel();
        PersonalInfoPanel.add(HomeTownLabel);

        initHomeTownComboBox();
        PersonalInfoPanel.add(HomeTownComboBox);

        initAddressLabel();
        PersonalInfoPanel.add(AddressLabel);

        initProvinceLabel();
        PersonalInfoPanel.add(ProvinceLabel);

        initProvinceComboBox();
        PersonalInfoPanel.add(ProvinceComboBox);

        initDistrictLabel();
        PersonalInfoPanel.add(DistrictLabel);

        initDistrictComboBox();
        PersonalInfoPanel.add(DistrictComboBox);

        initTownLabel();
        PersonalInfoPanel.add(TownLabel);

        initTownComboBox();
        PersonalInfoPanel.add(TownComboBox);

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
        UsernameLabel.setBounds(70, 40 +dv.AlignTop_InfoView(), 220, 30);
        UsernameLabel.setText("Số điện thoại");
        UsernameLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        UsernameLabel.setForeground(new Color(0x666666));
    }

    private void initUsernameTextField()
    {
        UsernameTextField = new JTextField();
        UsernameTextField.setBounds(70, 40+dv.LabelHeight() +dv.AlignTop_InfoView(), 220, 30);
        UsernameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        UsernameTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        UsernameTextField.setForeground(new Color(0x333333));
        UsernameTextField.setBackground(Color.WHITE);
    }

    private void initPasswordLabel()
    {
        PasswordLabel = new JLabel();
        PasswordLabel.setBounds(70, 50 + dv.FieldHeight() + dv.LabelHeight() +dv.AlignTop_InfoView(), 270, 30);
        PasswordLabel.setText("Mật khẩu");
        PasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        PasswordLabel.setForeground(new Color(0x666666));
    }

    private void initPasswordField()
    {
        PasswordField = new JPasswordField();
        PasswordField.setBounds(70, 50 + dv.FieldHeight() + 2 * dv.LabelHeight() +dv.AlignTop_InfoView(), 220, 30);
        PasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        PasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        PasswordField.setForeground(new Color(0x333333));
        PasswordField.setBackground(Color.WHITE);
        PasswordField.addKeyListener(this);
    }

    private void initRepeatPasswordLabel()
    {
        RepeatPasswordLabel = new JLabel();
        RepeatPasswordLabel.setBounds(70, 60 + 3*dv.FieldHeight() + dv.LabelHeight() +dv.AlignTop_InfoView(), 240, 30);
        RepeatPasswordLabel.setText("Nhập lại mật khẩu");
        RepeatPasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        RepeatPasswordLabel.setForeground(new Color(0x666666));
    }

    private void initRepeatPasswordField()
    {
        RepeatPasswordField = new JPasswordField();
        RepeatPasswordField.setBounds(70, 60 + 3*dv.FieldHeight() + 2 * dv.LabelHeight() +dv.AlignTop_InfoView(), 220, 30);
        RepeatPasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        RepeatPasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        RepeatPasswordField.setForeground(new Color(0x333333));
        RepeatPasswordField.setBackground(Color.WHITE);
        RepeatPasswordField.addKeyListener(this);
    }

    private void initRegisterAccButton()
    {
        ImageIcon RegisterAccIcon = new ImageIcon(getClass().getResource("/Resources/icon/Register Button.png"));
        RegisterAccButton = new JButton();
        RegisterAccButton.setBounds(105, 600, RegisterAccIcon.getIconWidth(), RegisterAccIcon.getIconHeight());
        RegisterAccButton.setBorder(null);
        RegisterAccButton.setContentAreaFilled(false);
        RegisterAccButton.setIcon(RegisterAccIcon);

        RegisterAccButton.addActionListener(this);
    }

    private void initLastNameLabel()
    {
        LastNameLabel = new JLabel();
        LastNameLabel.setBounds(50, 40  + dv.AlignTop_InfoView(), 240, 30);
        LastNameLabel.setText("Họ và tên đệm");
        LastNameLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        LastNameLabel.setForeground(new Color(0x666666));
    }

    private void initLastNameTextField()
    {
        LastNameTextField = new JTextField();
        LastNameTextField.setBounds(50, 40 + dv.LabelHeight() + dv.AlignTop_InfoView(), 220, 30);
        LastNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        LastNameTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        LastNameTextField.setForeground(new Color(0x333333));
        LastNameTextField.setBackground(Color.WHITE);
    }

    private void initFirstNameLabel()
    {
        FirstNameLabel = new JLabel();
        FirstNameLabel.setBounds(50+220+25, 40 + dv.AlignTop_InfoView(), 240, 30);
        FirstNameLabel.setText("Tên");
        FirstNameLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        FirstNameLabel.setForeground(new Color(0x666666));
    }
    private void initFirstNameTextField()
    {
        FirstNameTextField = new JTextField();
        FirstNameTextField.setBounds(50+220+25, 40 + dv.LabelHeight()  + dv.AlignTop_InfoView(), 150, 30);
        FirstNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        FirstNameTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        FirstNameTextField.setForeground(new Color(0x333333));
        FirstNameTextField.setBackground(Color.WHITE);
    }

    private void initIDLabel()
    {
        IDLabel = new JLabel();
        IDLabel.setBounds(50, 50 + dv.LabelHeight()+dv.FieldHeight()  + dv.AlignTop_InfoView(), 240, 30);
        IDLabel.setText("Mã định danh");
        IDLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        IDLabel.setForeground(new Color(0x666666));
    }
    private void initIDTextField()
    {
        IDTextField = new JTextField();
        IDTextField.setBounds(50, 50 + 2*dv.LabelHeight()+dv.FieldHeight()  + dv.AlignTop_InfoView(), 220, 30);
        IDTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        IDTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        IDTextField.setForeground(new Color(0x333333));
        IDTextField.setBackground(Color.WHITE);
    }

    private void initBirthdayLabel()
    {
        BirthdayLabel = new JLabel();
        BirthdayLabel.setBounds(50, 60 + 2*dv.LabelHeight()+2*dv.FieldHeight()  + dv.AlignTop_InfoView(),220,30);
        BirthdayLabel.setText("Ngày tháng năm sinh");
        BirthdayLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        BirthdayLabel.setForeground(new Color(0x666666));
    }

    private void initBirthdayField()
    {
        UtilDateModel model=new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        BirthdayField = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        BirthdayField.setBounds(50, 60 + 3*dv.LabelHeight()+2*dv.FieldHeight()  + dv.AlignTop_InfoView(),170,27);

        JFormattedTextField textField = BirthdayField.getJFormattedTextField();
        textField.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        textField.setBounds(50, 60 + 3*dv.LabelHeight()+2*dv.FieldHeight()  + dv.AlignTop_InfoView(),170,35);
        textField.setBackground(Color.WHITE);

        BirthdayField.setForeground(new Color(dv.BlackTextColor()));
        BirthdayField.setVisible(true);
        BirthdayField.setEnabled(true);
    }

    private void initGenderLabel()
    {
        GenderLabel = new JLabel();
        GenderLabel.setBounds(50 + 25 + 220, 60 + 2*dv.LabelHeight()+2*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        GenderLabel.setText("Giới tính");
        GenderLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        GenderLabel.setForeground(new Color(0x666666));
    }
    private void initGenderComboBox()
    {
        GenderComboBox = new JComboBox();
        GenderComboBox.setBounds(50 + 25 + 220, 60 + 3*dv.LabelHeight()+2*dv.FieldHeight() + dv.AlignTop_InfoView(), 80, 30);
        GenderComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        GenderComboBox.setForeground(new Color(0x666666));
        GenderComboBox.setBackground(Color.WHITE);

        GenderComboBox.addItem("");
        GenderComboBox.addItem("Nữ");
        GenderComboBox.addItem("Nam");
        GenderComboBox.addItem("Khác");
    }

    private void initHomeTownLabel()
    {
        HomeTownLabel = new JLabel();
        HomeTownLabel.setBounds(50, 70 + 3*dv.LabelHeight()+3*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        HomeTownLabel.setText("Quê quán");
        HomeTownLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        HomeTownLabel.setForeground(new Color(0x666666));
    }

    private void initHomeTownComboBox()
    {
        HomeTownComboBox = new JComboBox();
        HomeTownComboBox.setBounds(50, 70 + 4*dv.LabelHeight()+3*dv.FieldHeight() + dv.AlignTop_InfoView(), 170, 30);
        HomeTownComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        HomeTownComboBox.setForeground(new Color(0x666666));
        HomeTownComboBox.setBackground(Color.WHITE);

        HomeTownComboBox.addItemListener(this);
    }

    private void initAddressLabel()
    {
        AddressLabel = new JLabel();
        AddressLabel.setBounds(50, 90 + 4*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        AddressLabel.setText("Địa chỉ thường trú:");
        AddressLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        AddressLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initProvinceLabel()
    {
        ProvinceLabel = new JLabel();
        ProvinceLabel.setBounds(50, 90 + 5*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        ProvinceLabel.setText("Tỉnh/thành phố");
        ProvinceLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceLabel.setForeground(new Color(0x666666));
    }

    private void initProvinceComboBox()
    {
        ProvinceComboBox = new JComboBox();
        ProvinceComboBox.setBounds(50, 90 + 6*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 170, 30);
        ProvinceComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceComboBox.setForeground(new Color(0x666666));
        ProvinceComboBox.setBackground(Color.WHITE);

        try {
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            String query = "select distinct ProvinceCode, ProvinceName from REGION order by ProvinceCode";
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            HomeTownComboBox.addItem("");
            ProvinceComboBox.addItem("");
            while (rs.next())
            {
                HomeTownComboBox.addItem(rs.getString("ProvinceName"));
                ProvinceComboBox.addItem(rs.getString("ProvinceName"));
            }
        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
            ex.printStackTrace();
            return;
        }

        ProvinceComboBox.addItemListener(this);
    }

    private void initDistrictLabel()
    {
        DistrictLabel = new JLabel();
        DistrictLabel.setBounds(50 + 25 +170, 90 + 5*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        DistrictLabel.setText("Quận/huyện");
        DistrictLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictLabel.setForeground(new Color(0x666666));
    }

    private void initDistrictComboBox()
    {
        DistrictComboBox = new JComboBox();
        DistrictComboBox.setBounds(50+25+170, 90 + 6*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 170, 30);
        DistrictComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictComboBox.setForeground(new Color(0x666666));
        DistrictComboBox.setBackground(Color.WHITE);
        DistrictComboBox.addItemListener(this);
    }

    private void initTownLabel()
    {
        TownLabel = new JLabel();
        TownLabel.setBounds(50 + 50 +2*170, 90 + 5*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 350, 30);
        TownLabel.setText("Xã/phường/thị trấn");
        TownLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownLabel.setForeground(new Color(0x666666));
    }

    private void initTownComboBox()
    {
        TownComboBox = new JComboBox();
        TownComboBox.setBounds(50+50+2*170, 90 + 6*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 170, 30);
        TownComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownComboBox.setForeground(new Color(0x666666));
        TownComboBox.setBackground(Color.WHITE);
    }

    private void initStreetLabel()
    {
        StreetLabel = new JLabel();
        StreetLabel.setBounds(50, 110 + 6*dv.LabelHeight()+5*dv.FieldHeight() + dv.AlignTop_InfoView(), 300, 30);
        StreetLabel.setText("Số nhà, tên đường, khu phố/ấp");
        StreetLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        StreetLabel.setForeground(new Color(0x666666));
    }

    private void initStreetTextField()
    {
        StreetTextField = new JTextField();
        StreetTextField.setBounds(50, 110 + 6*dv.LabelHeight()+6*dv.FieldHeight() + dv.AlignTop_InfoView(), 560, 30);
        StreetTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        StreetTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        StreetTextField.setForeground(new Color(0x333333));
        StreetTextField.setBackground(Color.WHITE);
    }

    private void initEmailLabel()
    {
        EmailLabel = new JLabel();
        EmailLabel.setBounds(50, 120 + 7*dv.LabelHeight()+6*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        EmailLabel.setText("Email");
        EmailLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        EmailLabel.setForeground(new Color(0x666666));
    }

    private void initEmailTextField()
    {
        EmailTextField = new JTextField();
        EmailTextField.setBounds(50, 120 + 8*dv.LabelHeight()+6*dv.FieldHeight() + dv.AlignTop_InfoView(), 220, 30);
        EmailTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        EmailTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        EmailTextField.setForeground(new Color(0x333333));
        EmailTextField.setBackground(Color.WHITE);
    }

    private void initComponents()
    {
        this.setTitle("Đăng ký tài khoản cá nhân");
        this.setBounds((1600-dv.FrameWidth())/2, (900-dv.FrameHeight())/2, dv.FrameWidth(), dv.FrameHeight());
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(new Color(dv.ViewBackgroundColor()));
        this.setIconImage(new ImageIcon(getClass().getResource("/Resources/icon/Virus.png")).getImage());
        this.setLayout(null);

        //set account information Panel
        initAccInfoPanel();
        this.add(AccInfoPanel);

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());

        //set personal information Panel
        initPersonalInfoPanel();
        this.add(PersonalInfoPanel);
    }

    /*CONSTRUCTOR*/
    public RegisterAccView()
    {
        initComponents();
    }

    /*ACTION PERFORMED*/
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == BackButton)
        {
            this.dispose();
            LoginView loginView = new LoginView();
        }

        if (e.getSource() == RegisterAccButton)
        {
            JFormattedTextField textField = BirthdayField.getJFormattedTextField();

            String InputUsername = UsernameTextField.getText();
            String InputPassword = String.valueOf(PasswordField.getPassword());
            String InputRepeatPassword = String.valueOf(RepeatPasswordField.getPassword());
            String InputID = IDTextField.getText();
            String InputLastName = LastNameTextField.getText();
            String InputFirstName = FirstNameTextField.getText();
            String InputBirthday = textField.getText();
            int InputGender = GenderComboBox.getSelectedIndex();
            String InputHomeTown = String.valueOf(HomeTownComboBox.getSelectedItem());
            String InputProvince = String.valueOf(ProvinceComboBox.getSelectedItem());
            String InputDistrict = String.valueOf(DistrictComboBox.getSelectedItem());
            String InputTown = String.valueOf(TownComboBox.getSelectedItem());
            String InputStreet = StreetTextField.getText();
            String InputEmail = EmailTextField.getText();

            if (dv.checkStringInputValue(InputUsername, "Cảnh báo!", "Nhập tên tài khoản!") != -2)
                return;
            if (dv.checkStringInputValue(InputPassword, "Cảnh báo!", "Nhập mật khẩu!") != -2)
                return;
            if (dv.checkStringInputValue(InputRepeatPassword, "Cảnh báo!", "Nhập lại mật khẩu!") != -2)
                return;
            if (dv.checkStringInputValue(InputID, "Cảnh báo!", "Nhập mã định danh cá nhân!") != -2)
                return;
            if (dv.checkStringInputValue(InputFirstName, "Cảnh báo!", "Nhập tên công dân!") != -2)
                return;
            if (dv.checkStringInputValue(InputBirthday, "Cảnh báo!", "Nhập ngày sinh!") != -2)
                return;
            if (dv.checkStringInputValue(InputProvince, "Cảnh báo!", "Nhập tỉnh!") != -2)
                return;

            if (InputGender == 0)
                InputGender = 3;

            if (InputPassword.equals(InputRepeatPassword) == false) {
                System.out.println("Mật khẩu không trùng khớp!");
                return;
            }

            InputBirthday = dv.toOracleDateFormat(InputBirthday);
            InputGender -= 1;


            String plsql = "{call ACC_INSERT_RECORD(?,?,?,?,?)}";

            String plsql2 = "{call PERSON_INSERT_RECORD(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                connection.setAutoCommit(false);
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
                cst.setString("par_ProvinceName", InputProvince);
                cst.setString("par_DistrictName", InputDistrict);
                cst.setString("par_TownName", InputTown);
                cst.setString("par_Street", InputStreet);
                cst.setString("par_Phone", InputUsername);
                cst.setString("par_Email", InputEmail);
                cst.setString("par_Guardian", "");
                cst.setString("par_Note", "");

                cst.execute();

            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                ex.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
                return;
            } finally {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            dv.popupOption(null, "Đăng ký thành công!", "Thông báo!", 0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            JFormattedTextField textField = BirthdayField.getJFormattedTextField();

            String InputUsername = UsernameTextField.getText();
            String InputPassword = String.valueOf(PasswordField.getPassword());
            String InputRepeatPassword = String.valueOf(RepeatPasswordField.getPassword());
            String InputID = IDTextField.getText();
            String InputLastName = LastNameTextField.getText();
            String InputFirstName = FirstNameTextField.getText();
            String InputBirthday = textField.getText();
            int InputGender = GenderComboBox.getSelectedIndex();
            String InputHomeTown = String.valueOf(HomeTownComboBox.getSelectedItem());
            String InputProvince = String.valueOf(ProvinceComboBox.getSelectedItem());
            String InputDistrict = String.valueOf(DistrictComboBox.getSelectedItem());
            String InputTown = String.valueOf(TownComboBox.getSelectedItem());
            String InputStreet = StreetTextField.getText();
            String InputEmail = EmailTextField.getText();

            if (dv.checkStringInputValue(InputUsername, "Cảnh báo!", "Nhập tên tài khoản!") != -2)
                return;
            if (dv.checkStringInputValue(InputPassword, "Cảnh báo!", "Nhập mật khẩu!") != -2)
                return;
            if (dv.checkStringInputValue(InputRepeatPassword, "Cảnh báo!", "Nhập lại mật khẩu!") != -2)
                return;
            if (dv.checkStringInputValue(InputID, "Cảnh báo!", "Nhập mã định danh cá nhân!") != -2)
                return;
            if (dv.checkStringInputValue(InputFirstName, "Cảnh báo!", "Nhập tên công dân!") != -2)
                return;
            if (dv.checkStringInputValue(InputBirthday, "Cảnh báo!", "Nhập ngày sinh!") != -2)
                return;
            if (dv.checkStringInputValue(InputProvince, "Cảnh báo!", "Nhập tỉnh!") != -2)
                return;

            if (InputGender == 0)
                InputGender = 3;

            if (InputPassword.equals(InputRepeatPassword) == false) {
                System.out.println("Mật khẩu không trùng khớp!");
                return;
            }

            InputBirthday = dv.toOracleDateFormat(InputBirthday);
            InputGender -= 1;


            String plsql = "{call ACC_INSERT_RECORD(?,?,?,?,?)}";

            String plsql2 = "{call PERSON_INSERT_RECORD(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                connection.setAutoCommit(false);
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
                cst.setString("par_ProvinceName", InputProvince);
                cst.setString("par_DistrictName", InputDistrict);
                cst.setString("par_TownName", InputTown);
                cst.setString("par_Street", InputStreet);
                cst.setString("par_Phone", InputUsername);
                cst.setString("par_Email", InputEmail);
                cst.setString("par_Guardian", "");
                cst.setString("par_Note", "");

                cst.execute();

            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                ex.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
                return;
            } finally {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            dv.popupOption(null, "Đăng ký thành công!", "Thông báo!", 0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if (e.getSource() == ProvinceComboBox)
        {
            try {
                DistrictComboBox.removeAllItems();
                TownComboBox.removeAllItems();

                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                String query = "select distinct DistrictCode, DistrictName from REGION " +
                        "where ProvinceName = '" + ProvinceComboBox.getSelectedItem() + "' " +
                        "order by DistrictCode";
                PreparedStatement st = connection.prepareStatement(query);
                ResultSet rs = st.executeQuery();

                DistrictComboBox.addItem("");
                while (rs.next())
                    DistrictComboBox.addItem(rs.getString("DistrictName"));
            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                ex.printStackTrace();
                return;
            }

            DistrictComboBox.addItemListener(this);
        }

        if (e.getSource() == DistrictComboBox)
        {
            try {
                TownComboBox.removeAllItems();

                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                String query = "select distinct TownCode, TownName from REGION " +
                        "where ProvinceName = '" + ProvinceComboBox.getSelectedItem() + "' " +
                        "and DistrictName = '" + DistrictComboBox.getSelectedItem() + "' " +
                        "order by TownCode";
                PreparedStatement st = connection.prepareStatement(query);
                ResultSet rs = st.executeQuery();

                TownComboBox.addItem("");
                while (rs.next())
                    TownComboBox.addItem(rs.getString("TownName"));
            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                ex.printStackTrace();
                return;
            }
        }
    }
}
