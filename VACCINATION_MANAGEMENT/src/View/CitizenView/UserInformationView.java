package View.CitizenView;


import Process.Account;
import Process.DateLabelFormatter;
import Process.DefaultValue;
import Process.Person;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author LeHoangDuyen
 */
public class UserInformationView extends JPanel implements ActionListener, KeyListener, ItemListener {
    DefaultValue dv = new DefaultValue();
    private JLabel UsernameLabel;
    private JLabel OldPasswordLabel;
    private JLabel ChangePasswordLabel;
    private JLabel NewPasswordLabel;
    private JLabel RepeatNewPasswordLabel;
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
    private JPasswordField OldPasswordField;
    private JPasswordField NewPasswordField;
    private JPasswordField RepeatNewPasswordField;
    private JTextField LastNameTextField;
    private JTextField FirstNameTextField;
    private JTextField IDTextField;
    private JTextField StreetTextField;
    private JTextField EmailTextField;
    private JButton UpdateAccButton;
    private JComboBox GenderComboBox;
    private JComboBox HomeTownComboBox;
    private JComboBox ProvinceComboBox = new JComboBox();
    private JComboBox DistrictComboBox = new JComboBox();
    private JComboBox TownComboBox = new JComboBox();
    private JDatePickerImpl BirthdayField;
    private JPanel AccInfoPanel;
    private JPanel PersonalInfoPanel;

    public Person getPersonalUser() {
        return personalUser;
    }

    private Person personalUser;
    private Account acc = new Account();

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

        initUsernameLabel();
        AccInfoPanel.add(UsernameLabel);

        initChangePasswordLabel();
        AccInfoPanel.add(ChangePasswordLabel);

        initOldPasswordLabel();
        AccInfoPanel.add(OldPasswordLabel);

        initNewPasswordLabel();
        AccInfoPanel.add(NewPasswordLabel);

        initUsernameTextField();
        AccInfoPanel.add(UsernameTextField);

        initOldPasswordField();
        AccInfoPanel.add(OldPasswordField);

        initNewPasswordField();
        AccInfoPanel.add(NewPasswordField);

        initRepeatNewPasswordLabel();
        this.add(RepeatNewPasswordLabel);

        initRepeatNewPasswordField();
        this.add(RepeatNewPasswordField);

        initUpdateAccButton();
        AccInfoPanel.add(UpdateAccButton);

        AccInfoPanel.validate();
    }

    private void initPersonalInfoPanel()
    {
        PersonalInfoPanel = new JPanel();

        PersonalInfoPanel.setLayout(null);
        PersonalInfoPanel.setBounds(dv.FrameWidth()-dv.FrameHeight(),0,dv.FrameHeight()+30,dv.FrameHeight());
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
        UsernameLabel = new JLabel("Số điện thoại");
        UsernameLabel.setBounds(70, 40 +dv.AlignTop_InfoView(), 240, 30);
        UsernameLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        UsernameLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initUsernameTextField()
    {
        UsernameTextField = new JTextField(personalUser.getPhone());
        UsernameTextField.setBounds(70, 40+dv.LabelHeight() +dv.AlignTop_InfoView(), 220, 30);
        UsernameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        UsernameTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        UsernameTextField.setForeground(new Color(dv.BlackTextColor()));
        UsernameTextField.setText(personalUser.getPhone());
    }

    private void initOldPasswordLabel()
    {
        OldPasswordLabel = new JLabel("Xác nhận mật khẩu");
        OldPasswordLabel.setBounds(70, 50 + dv.FieldHeight() + dv.LabelHeight() +dv.AlignTop_InfoView(), 270, 30);
        OldPasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        OldPasswordLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initOldPasswordField()
    {
        OldPasswordField = new JPasswordField();
        OldPasswordField.setBounds(70, 50 + dv.FieldHeight() + 2 * dv.LabelHeight() +dv.AlignTop_InfoView(), 220, 30);
        OldPasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        OldPasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        OldPasswordField.setForeground(new Color(dv.BlackTextColor()));
        OldPasswordField.addKeyListener(this);
    }

    private void initChangePasswordLabel()
    {
        ChangePasswordLabel = new JLabel("Đổi mật khẩu:");
        ChangePasswordLabel.setBounds(70, 70 + 2*dv.FieldHeight() + 3 * dv.LabelHeight() +dv.AlignTop_InfoView(), 240, 30);
        ChangePasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ChangePasswordLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initNewPasswordLabel()
    {
        NewPasswordLabel = new JLabel("Nhập mật khẩu mới");
        NewPasswordLabel.setBounds(70, 70 + 3*dv.LabelHeight()+3*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        NewPasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        NewPasswordLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initNewPasswordField()
    {
        NewPasswordField = new JPasswordField();
        NewPasswordField.setBounds(70, 70 + 4*dv.LabelHeight()+3*dv.FieldHeight() + dv.AlignTop_InfoView(), 220, 30);
        NewPasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        NewPasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        NewPasswordField.setForeground(new Color(dv.BlackTextColor()));
        NewPasswordField.addKeyListener(this);
    }
    
    private void initRepeatNewPasswordLabel()
    {
        RepeatNewPasswordLabel = new JLabel("Nhập lại mật khẩu mới");
        RepeatNewPasswordLabel.setBounds(70, 80 + 4*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        RepeatNewPasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        RepeatNewPasswordLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initRepeatNewPasswordField()
    {
        RepeatNewPasswordField = new JPasswordField();
        RepeatNewPasswordField.setBounds(70, 80 + 5*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 220, 30);
        RepeatNewPasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        RepeatNewPasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        RepeatNewPasswordField.setForeground(new Color(dv.BlackTextColor()));
        RepeatNewPasswordField.addKeyListener(this);
    }

    private void initUpdateAccButton()
    {

        ImageIcon RegisterButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Update Button.png"));
        UpdateAccButton = new JButton();
        UpdateAccButton.setBounds(105, 600, RegisterButtonIcon.getIconWidth(), RegisterButtonIcon.getIconHeight());
        UpdateAccButton.setBorder(null);
        UpdateAccButton.setContentAreaFilled(false);
        UpdateAccButton.setIcon(RegisterButtonIcon);

        UpdateAccButton.addActionListener(this);
    }

    private void initLastNameLabel()
    {
        LastNameLabel = new JLabel("Họ và tên đệm");
        LastNameLabel.setBounds(50, 40  + dv.AlignTop_InfoView(), 240, 30);
        LastNameLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        LastNameLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initLastNameTextField()
    {
        LastNameTextField = new JTextField(personalUser.getLastName());
        LastNameTextField.setBounds(50, 40 + dv.LabelHeight() + dv.AlignTop_InfoView(), 220, 30);
        LastNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        LastNameTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        LastNameTextField.setForeground(new Color(dv.BlackTextColor()));
        LastNameTextField.setText(personalUser.getLastName());
    }

    private void initFirstNameLabel()
    {
        FirstNameLabel = new JLabel("Tên");
        FirstNameLabel.setBounds(50+220+25, 40 + dv.AlignTop_InfoView(), 240, 30);
        FirstNameLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        FirstNameLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initFirstNameTextField()
    {
        FirstNameTextField = new JTextField(personalUser.getFirstName());
        FirstNameTextField.setBounds(50+220+25, 40 + dv.LabelHeight()  + dv.AlignTop_InfoView(), 150, 30);
        FirstNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        FirstNameTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        FirstNameTextField.setForeground(new Color(dv.BlackTextColor()));
        FirstNameTextField.setText(personalUser.getFirstName());
    }

    private void initIDLabel()
    {
        IDLabel = new JLabel("Mã định danh");
        IDLabel.setBounds(50, 50 + dv.LabelHeight()+dv.FieldHeight()  + dv.AlignTop_InfoView(), 240, 30);
        IDLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        IDLabel.setForeground(new Color(dv.FieldLabelColor()));

    }
    private void initIDTextField()
    {
        IDTextField = new JTextField(personalUser.getID());
        IDTextField.setBounds(50, 50 + 2*dv.LabelHeight()+dv.FieldHeight()  + dv.AlignTop_InfoView(), 220, 30);
        IDTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        IDTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        IDTextField.setForeground(new Color(dv.BlackTextColor()));
        IDTextField.setText(personalUser.getID());
    }

    private void initBirthdayLabel()
    {
        BirthdayLabel = new JLabel("Ngày tháng năm sinh");
        BirthdayLabel.setBounds(50, 60 + 2*dv.LabelHeight()+2*dv.FieldHeight()  + dv.AlignTop_InfoView(),220,30);
        BirthdayLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        BirthdayLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initBirthdayField()
    {
        UtilDateModel model=new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        BirthdayField = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        BirthdayField.setBounds(50, 60 + 3*dv.LabelHeight()+2*dv.FieldHeight()  + dv.AlignTop_InfoView(),170,27);
        JFormattedTextField textField = BirthdayField.getJFormattedTextField();
        textField.setText(personalUser.getBirthday().substring(0,10));
        textField.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        textField.setBounds(50, 60 + 3*dv.LabelHeight()+2*dv.FieldHeight(),170,30);

        BirthdayField.setTextEditable(true);
        BirthdayField.setForeground(new Color(dv.BlackTextColor()));
        BirthdayField.setVisible(true);
        BirthdayField.setEnabled(true);
    }

    private void initGenderLabel()
    {
        GenderLabel = new JLabel("Giới tính");
        GenderLabel.setBounds(50 + 25 + 220, 60 + 2*dv.LabelHeight()+2*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        GenderLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        GenderLabel.setForeground(new Color(dv.FieldLabelColor()));
    }
    
    private void initGenderComboBox()
    {
        GenderComboBox = new JComboBox();
        GenderComboBox.setBounds(50 + 25 + 220, 60 + 3*dv.LabelHeight()+2*dv.FieldHeight() + dv.AlignTop_InfoView(), 80, 28);
        GenderComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        GenderComboBox.setForeground(new Color(dv.FieldLabelColor()));
        GenderComboBox.setBackground(Color.WHITE);

        GenderComboBox.addItem(dv.getGenderName(personalUser.getGender()));
        for (int i = 0; i < dv.getGenderList().length; i++)
            if (dv.getGenderList()[i].equals(dv.getGenderName(personalUser.getGender())) == false)
                GenderComboBox.addItem(dv.getGenderList()[i]);

    }

    private void initHomeTownLabel()
    {
        HomeTownLabel = new JLabel("Quê quán");
        HomeTownLabel.setBounds(50, 70 + 3*dv.LabelHeight()+3*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 28);
        HomeTownLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        HomeTownLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initHomeTownComboBox()
    {
        HomeTownComboBox = new JComboBox();
        HomeTownComboBox.setBounds(50, 70 + 4*dv.LabelHeight()+3*dv.FieldHeight() + dv.AlignTop_InfoView(), 170, 30);
        HomeTownComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        HomeTownComboBox.setForeground(new Color(dv.FieldLabelColor()));
        HomeTownComboBox.setBackground(Color.WHITE);
        HomeTownComboBox.addItem(personalUser.getHomeTown());
        HomeTownComboBox.addItemListener(this);
    }

    private void initAddressLabel()
    {
        AddressLabel = new JLabel("Địa chỉ thường trú:");
        AddressLabel.setBounds(50, 90 + 4*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 28);
        AddressLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        AddressLabel.setForeground(new Color(dv.FieldLabelColor()));
        //AddressLabel.setForeground(new Color(dv.FeatureButtonColor()));
    }

    private void initProvinceLabel()
    {
        ProvinceLabel = new JLabel("Tỉnh/thành phố");
        ProvinceLabel.setBounds(50, 90 + 5*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        ProvinceLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initProvinceComboBox()
    {
        ProvinceComboBox = new JComboBox();
        ProvinceComboBox.setBounds(50, 90 + 6*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 170, 30);
        ProvinceComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceComboBox.setForeground(new Color(dv.FieldLabelColor()));
        ProvinceComboBox.setBackground(Color.WHITE);

        ProvinceComboBox.addItem(personalUser.getProvince());

        try {
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            String query = "select distinct ProvinceCode, ProvinceName from REGION order by ProvinceCode";
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            HomeTownComboBox.addItem("");
            ProvinceComboBox.addItem("");
            while (rs.next())
            {
                if (rs.getString("ProvinceName").equals(personalUser.getHomeTown()) == false)
                    HomeTownComboBox.addItem(rs.getString("ProvinceName"));
                if (rs.getString("ProvinceName").equals(personalUser.getProvince()) == false)
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
        DistrictLabel = new JLabel("Quận/huyện");
        DistrictLabel.setBounds(50 + 25 +170, 90 + 5*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        DistrictLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initDistrictComboBox()
    {
        DistrictComboBox = new JComboBox();
        DistrictComboBox.setBounds(50+25+170, 90 + 6*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 170, 30);
        DistrictComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictComboBox.setForeground(new Color(dv.FieldLabelColor()));
        DistrictComboBox.setBackground(Color.WHITE);

        DistrictComboBox.addItem(personalUser.getDistrict());
    }

    private void initTownLabel()
    {
        TownLabel = new JLabel("Xã/phường/thị trấn");
        TownLabel.setBounds(50 + 50 +2*170, 90 + 5*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 350, 30);
        TownLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initTownComboBox()
    {
        TownComboBox = new JComboBox();
        TownComboBox.setBounds(50+50+2*170, 90 + 6*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 170, 30);
        TownComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownComboBox.setForeground(new Color(dv.FieldLabelColor()));
        TownComboBox.setBackground(Color.WHITE);

        TownComboBox.addItem(personalUser.getTown());
    }

    private void initStreetLabel()
    {
        StreetLabel = new JLabel("Số nhà, tên đường, khu phố/ấp");
        StreetLabel.setBounds(50, 110 + 6*dv.LabelHeight()+5*dv.FieldHeight() + dv.AlignTop_InfoView(), 300, 30);
        StreetLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        StreetLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initStreetTextField()
    {
        StreetTextField = new JTextField(personalUser.getStreet());
        StreetTextField.setBounds(50, 110 + 6*dv.LabelHeight()+6*dv.FieldHeight() + dv.AlignTop_InfoView(), 560, 30);
        StreetTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        StreetTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        StreetTextField.setForeground(new Color(dv.BlackTextColor()));
    }

    private void initEmailLabel()
    {
        EmailLabel = new JLabel("Email");
        EmailLabel.setBounds(50, 130 + 6*dv.LabelHeight()+7*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        EmailLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        EmailLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initEmailTextField()
    {
        EmailTextField = new JTextField(personalUser.getEmail());
        EmailTextField.setBounds(50, 130 + 7*dv.LabelHeight()+7*dv.FieldHeight() + dv.AlignTop_InfoView(), 350, 30);
        EmailTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        EmailTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        EmailTextField.setForeground(new Color(dv.BlackTextColor()));
    }

    private void initComponents()
    {
        this.setBounds(0, 0, dv.FrameWidth(), dv.FrameHeight());
        this.setVisible(true);
        this.setBackground(new Color(dv.ViewBackgroundColor()));
        this.setLayout(null);

        //set account information Panel
        initAccInfoPanel();
        this.add(AccInfoPanel);

        //set personal information Panel
        initPersonalInfoPanel();
        this.add(PersonalInfoPanel);

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());
    }

    /*CONSTRUCTOR*/
    public UserInformationView(Person perUser)
    {
        personalUser = perUser;
        initComponents();
    }

    /*ACTION PERFORMED*/
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == UpdateAccButton)
        {
            JFormattedTextField textField = BirthdayField.getJFormattedTextField();

            String InputUsername = UsernameTextField.getText();
            String OldPhone = personalUser.getPhone();
            String InputLastName = LastNameTextField.getText();
            String InputFirstName = FirstNameTextField.getText();
            String InputPassword = String.valueOf(OldPasswordField.getPassword());
            String InputNewPassword = String.valueOf(NewPasswordField.getPassword());
            String InputRepeatNewPassword = String.valueOf(RepeatNewPasswordField.getPassword());
            String InputID = IDTextField.getText();
            String OldID = personalUser.getID();
            String InputBirthday = textField.getText();
            int InputGender = dv.getGenderIndex(String.valueOf(GenderComboBox.getSelectedItem()));
            String InputHomeTown = String.valueOf(HomeTownComboBox.getSelectedItem());
            String InputProvince = String.valueOf(ProvinceComboBox.getSelectedItem());
            String InputDistrict = String.valueOf(DistrictComboBox.getSelectedItem());
            String InputTown = String.valueOf(TownComboBox.getSelectedItem());
            String InputStreet = StreetTextField.getText();
            String InputEmail = EmailTextField.getText();

            if ( dv.checkStringInputValue(InputPassword, "Cảnh báo!","Xác nhận mật khẩu để cập nhật thông tin!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputFirstName, "Cảnh báo!", "Nhập tên!") != -2)
                return;
            if ( dv.checkStringInputValue(InputUsername, "Cảnh báo!", "Nhập số điện thoại!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputID, "Cảnh báo!","Nhập mã định danh cá nhân!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputBirthday, "Cảnh báo!","Nhập ngày sinh!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputProvince, "Cảnh báo!", "Nhập tỉnh cư trú!") != -2 )
                return;

            String query = "select *" +
                    " from ACCOUNT" +
                    " where Username = '" + personalUser.getPhone() + "'";
            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                PreparedStatement st = connection.prepareStatement(query);

                ResultSet rs = st.executeQuery(query);

                rs.next();
                acc.setUsername(rs.getString("Username"));
                acc.setPassword(rs.getString("Password"));
                acc.setRole(rs.getInt("Role"));
                acc.setStatus(rs.getInt("Status"));
                if (acc.getPassword().equals(InputPassword) == false)
                {
                    dv.popupOption(null, "Mật khẩu không đúng!", "Lỗi!", 2);
                    return;
                }

            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

            if (InputRepeatNewPassword.equals(InputNewPassword) == false)
            {
                dv.popupOption(null,"Mật khẩu mới không trùng khớp với ô nhập lại mật khẩu mới!", "Lỗi!", 2);
                return;
            }

            InputBirthday = dv.toOracleDateFormat(InputBirthday);


            String plsql = "{call PERSON_UPDATE_RECORD(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            String plsql2 = "{call ACC_UPDATE_PASSWORD(?,?,?)}";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                CallableStatement cst;

                if (InputNewPassword.equals("") == false) {
                    cst = connection.prepareCall(plsql2);
                    cst.setString(1, personalUser.getPhone());
                    cst.setString(2, InputPassword);
                    cst.setString(3, InputNewPassword);

                    cst.execute();
                }

                cst = connection.prepareCall(plsql);
                cst.setString("par_OldID", OldID);
                cst.setString("par_ID", InputID);
                cst.setString("par_LastName", InputLastName);
                cst.setString("par_FirstName", InputFirstName);
                cst.setString("par_Birthday", InputBirthday);
                cst.setInt("par_Gender", InputGender);
                cst.setString("par_HomeTown", InputHomeTown);
                cst.setString("par_ProvinceName", InputProvince);
                cst.setString("par_DistrictName", InputDistrict);
                cst.setString("par_TownName", InputTown);
                cst.setString("par_Street", InputStreet);
                cst.setString("par_Phone", InputUsername);
                cst.setString("par_OldPhone", OldPhone);
                cst.setString("par_Email", InputEmail);
                cst.setString("par_Note", "");

                cst.execute();
            }
            catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

            personalUser.setPhone(InputUsername);
            dv.popupOption(null, "Cập nhật thành công!", "Thông báo!", 0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            JFormattedTextField textField = BirthdayField.getJFormattedTextField();

            String InputUsername = UsernameTextField.getText();
            String OldPhone = personalUser.getPhone();
            String InputLastName = LastNameTextField.getText();
            String InputFirstName = FirstNameTextField.getText();
            String InputPassword = String.valueOf(OldPasswordField.getPassword());
            String InputNewPassword = String.valueOf(NewPasswordField.getPassword());
            String InputRepeatNewPassword = String.valueOf(RepeatNewPasswordField.getPassword());
            String InputID = IDTextField.getText();
            String OldID = personalUser.getID();
            String InputBirthday = textField.getText();
            int InputGender = dv.getGenderIndex(String.valueOf(GenderComboBox.getSelectedItem()));
            String InputHomeTown = String.valueOf(HomeTownComboBox.getSelectedItem());
            String InputProvince = String.valueOf(ProvinceComboBox.getSelectedItem());
            String InputDistrict = String.valueOf(DistrictComboBox.getSelectedItem());
            String InputTown = String.valueOf(TownComboBox.getSelectedItem());
            String InputStreet = StreetTextField.getText();
            String InputEmail = EmailTextField.getText();

            if ( dv.checkStringInputValue(InputPassword, "Cảnh báo!","Xác nhận mật khẩu để cập nhật thông tin!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputFirstName, "Cảnh báo!", "Nhập tên!") != -2)
                return;
            if ( dv.checkStringInputValue(InputUsername, "Cảnh báo!", "Nhập số điện thoại!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputID, "Cảnh báo!","Nhập mã định danh cá nhân!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputBirthday, "Cảnh báo!","Nhập ngày sinh!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputProvince, "Cảnh báo!", "Nhập tỉnh cư trú!") != -2 )
                return;

            String query = "select *" +
                    " from ACCOUNT" +
                    " where Username = '" + personalUser.getPhone() + "'";
            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                PreparedStatement st = connection.prepareStatement(query);

                ResultSet rs = st.executeQuery(query);

                rs.next();
                acc.setUsername(rs.getString("Username"));
                acc.setPassword(rs.getString("Password"));
                acc.setRole(rs.getInt("Role"));
                acc.setStatus(rs.getInt("Status"));
                if (acc.getPassword().equals(InputPassword) == false)
                {
                    dv.popupOption(null, "Mật khẩu không đúng!", "Lỗi!", 2);
                    return;
                }

            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

            if (InputRepeatNewPassword.equals(InputNewPassword) == false)
            {
                dv.popupOption(null,"Mật khẩu mới không trùng khớp với ô nhập lại mật khẩu mới!", "Lỗi!", 2);
                return;
            }

            InputBirthday = dv.toOracleDateFormat(InputBirthday);


            String plsql = "{call PERSON_UPDATE_RECORD(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            String plsql2 = "{call ACC_UPDATE_PASSWORD(?,?,?)}";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                CallableStatement cst;

                if (InputNewPassword.equals("") == false) {
                    cst = connection.prepareCall(plsql2);
                    cst.setString(1, personalUser.getPhone());
                    cst.setString(2, InputPassword);
                    cst.setString(3, InputNewPassword);

                    cst.execute();
                }

                cst = connection.prepareCall(plsql);
                cst.setString("par_OldID", OldID);
                cst.setString("par_ID", InputID);
                cst.setString("par_LastName", InputLastName);
                cst.setString("par_FirstName", InputFirstName);
                cst.setString("par_Birthday", InputBirthday);
                cst.setInt("par_Gender", InputGender);
                cst.setString("par_HomeTown", InputHomeTown);
                cst.setString("par_ProvinceName", InputProvince);
                cst.setString("par_DistrictName", InputDistrict);
                cst.setString("par_TownName", InputTown);
                cst.setString("par_Street", InputStreet);
                cst.setString("par_Phone", InputUsername);
                cst.setString("par_OldPhone", OldPhone);
                cst.setString("par_Email", InputEmail);
                cst.setString("par_Note", "");

                cst.execute();
            }
            catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

            personalUser.setPhone(InputUsername);
            dv.popupOption(null, "Cập nhật thành công!", "Thông báo!", 0);
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
