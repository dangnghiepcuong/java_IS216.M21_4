package GUI_UserInformation;


import Data_Processor.*;
import org.jdatepicker.impl.*;

import javax.swing.*;
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

        initGenderChoice();
        PersonalInfoPanel.add(GenderChoice);

        initHomeTownLabel();
        PersonalInfoPanel.add(HomeTownLabel);

        initHomeTownChoice();
        PersonalInfoPanel.add(HomeTownChoice);

        initAddressLabel();
        PersonalInfoPanel.add(AddressLabel);

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
        UsernameTextField.setText(personalUser.getPhone());
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

    private void initUpdateAccButton()
    {

        ImageIcon RegisterButtonIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Update Button.png"));
        UpdateAccButton = new JButton();
        UpdateAccButton.setBounds(105, 450, RegisterButtonIcon.getIconWidth(), RegisterButtonIcon.getIconHeight());
        UpdateAccButton.setBorder(null);
        UpdateAccButton.setContentAreaFilled(false);
        UpdateAccButton.setIcon(RegisterButtonIcon);

        UpdateAccButton.addActionListener(this);
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
        LastNameTextField.setText(personalUser.getLastName());
        LastNameTextField.setEditable(false);
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
        FirstNameTextField.setText(personalUser.getFirstName());
        FirstNameTextField.setEditable(false);
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
        IDTextField.setText(personalUser.getID());
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

        textField.setText(dv.toApplicationDate(personalUser.getBirthday().substring(0,10)));

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
        for (int i = 0; i < dv.getGenderList().length; i++)
            if (dv.getGenderList()[i].equals(dv.getGenderName(personalUser.getGender())) == false)
                GenderChoice.add(dv.getGenderList()[i]);

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

    private void initAddressLabel()
    {
        AddressLabel = new JLabel();
        AddressLabel.setBounds(50, 135 + 4*dv.LabelHeight()+4*dv.FieldHeight(), 240, 28);
        AddressLabel.setText("Địa chỉ thường trú:");
        AddressLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        AddressLabel.setForeground(new Color(dv.FieldLabelColor()));
        //AddressLabel.setForeground(new Color(dv.FeatureButtonColor()));
    }

    private void initProvinceLabel()
    {
        ProvinceLabel = new JLabel();
        ProvinceLabel.setBounds(50, 135 + 5*dv.LabelHeight()+4*dv.FieldHeight(), 240, 30);
        ProvinceLabel.setText("Tỉnh/thành phố");
        ProvinceLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initProvinceChoice()
    {
        ProvinceChoice = new Choice();
        ProvinceChoice.setBounds(50, 135 + 6*dv.LabelHeight()+4*dv.FieldHeight(), 170, 30);
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
        DistrictLabel.setBounds(50 + 25 +170, 135 + 5*dv.LabelHeight()+4*dv.FieldHeight(), 240, 30);
        DistrictLabel.setText("Quận/huyện");
        DistrictLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initDistrictChoice()
    {
        DistrictChoice = new Choice();
        DistrictChoice.setBounds(50+25+170, 135 + 6*dv.LabelHeight()+4*dv.FieldHeight(), 170, 30);
        DistrictChoice.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictChoice.setForeground(new Color(dv.FieldLabelColor()));

        DistrictChoice.add(personalUser.getDistrict());

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
        TownLabel.setBounds(50 + 50 +2*170, 135 + 5*dv.LabelHeight()+4*dv.FieldHeight(), 350, 30);
        TownLabel.setText("Xã/phường/thị trấn");
        TownLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initTownChoice()
    {
        TownChoice = new Choice();
        TownChoice.setBounds(50+50+2*170, 135 + 6*dv.LabelHeight()+4*dv.FieldHeight(), 170, 30);
        TownChoice.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownChoice.setForeground(new Color(dv.FieldLabelColor()));

        TownChoice.add(personalUser.getTown());

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
        StreetLabel.setBounds(50, 145 + 6*dv.LabelHeight()+5*dv.FieldHeight(), 300, 30);
        StreetLabel.setText("Số nhà, tên đường, khu phố/ấp");
        StreetLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        StreetLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initStreetTextField()
    {
        StreetTextField = new JTextField(personalUser.getStreet());
        StreetTextField.setBounds(50, 145 + 7*dv.LabelHeight()+5*dv.FieldHeight(), 560, 30);
        StreetTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        StreetTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        StreetTextField.setForeground(new Color(dv.BlackTextColor()));
    }

    private void initEmailLabel()
    {
        EmailLabel = new JLabel();
        EmailLabel.setBounds(50, 155 + 7*dv.LabelHeight()+6*dv.FieldHeight(), 240, 30);
        EmailLabel.setText("Email");
        EmailLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        EmailLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initEmailTextField()
    {
        EmailTextField = new JTextField(personalUser.getEmail());
        EmailTextField.setBounds(50, 155 + 8*dv.LabelHeight()+6*dv.FieldHeight(), 350, 30);
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

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());

        //set personal information Panel
        initPersonalInfoPanel();
        this.add(PersonalInfoPanel);
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
            String InputPassword = String.valueOf(OldPasswordField.getPassword());
            String InputNewPassword = String.valueOf(NewPasswordField.getPassword());
            String InputRepeatNewPassword = String.valueOf(RepeatNewPasswordField.getPassword());
            String InputID = IDTextField.getText();
            String InputBirthday = textField.getText();
            int InputGender = GenderChoice.getSelectedIndex();
            String InputHomeTown =HomeTownChoice.getSelectedItem();
            String InputProvince = ProvinceChoice.getSelectedItem();
            String InputDistrict = DistrictChoice.getSelectedItem();
            String InputTown = TownChoice.getSelectedItem();
            String InputStreet = StreetTextField.getText();
            String InputEmail = EmailTextField.getText();

            if ( dv.checkStringInputValue(InputUsername, "Cảnh báo!", "Nhập tên tài khoản!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputPassword, "Cảnh báo!","Nhập mật khẩu!") != -2 )
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
                    dv.popupOption(null, "Mật khẩu không đúng!", "Cảnh báo!", 1);
                    return;
                }

            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                throw new RuntimeException(ex);
            }

            if ( dv.checkStringInputValue(InputID, "Cảnh báo!","Nhập mã định danh cá nhân!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputBirthday, "Cảnh báo!","Nhập ngày sinh!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputProvince, "Cảnh báo!", "Nhập tỉnh cư trú!") != -2 )
                return;
            else
                InputProvince = dv.getProvinceCode(InputProvince);
            if (InputGender == 0)
                InputGender = 3;

            if (InputRepeatNewPassword.equals(InputNewPassword) == false)
            {
                JOptionPane OptionFrame = new JOptionPane();

                String responses[] = {"OK!"};

                ImageIcon icon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Warning Icon.png"));

                OptionFrame.showOptionDialog(null, "Mật khẩu mới không trùng khớp!", "Cảnh báo",
                        JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, icon, responses, 0);
                return;
            }

            InputBirthday = dv.toOracleDateFormat(InputBirthday);
            InputGender -= 1;

            String plsql = "{call PERSON_UPDATE_RECORD(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            String plsql2 = "{call ACC_UPDATE_PASSWORD(?,?,?)}";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                CallableStatement cst;

                if (InputNewPassword.equals("") == false) {
                    System.out.println(InputNewPassword + personalUser.getPhone());

                    cst = connection.prepareCall(plsql2);
                    cst.setString(1, personalUser.getPhone());
                    cst.setString(2, InputPassword);
                    cst.setString(3, InputNewPassword);

                    cst.execute();
                }

                cst = connection.prepareCall(plsql);
                cst.setString("par_ID", InputID);
                cst.setString("par_Birthday", InputBirthday);
                cst.setInt("par_Gender", InputGender);
                cst.setString("par_HomeTown", InputHomeTown);
                cst.setString("par_Province", InputProvince);
                cst.setString("par_District", InputDistrict);
                cst.setString("par_Town", InputTown);
                cst.setString("par_Street", InputStreet);
                cst.setString("par_Phone", InputUsername);
                cst.setString("par_Email", InputEmail);

                cst.execute();
            }
            catch (SQLException ex) {

                    dv.popupOption(null, "Không thành công", "Lỗi!", 2);

                    throw new RuntimeException(ex);
            }

            personalUser.setPhone(InputUsername);
            dv.popupOption(null, "Cập nhật thành công!", "Thông báo!", 0);
        }
    }
}
