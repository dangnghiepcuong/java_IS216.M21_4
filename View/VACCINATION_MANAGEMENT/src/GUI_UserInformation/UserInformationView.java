package GUI_UserInformation;

/*
COLOR HEX CODE
màu xám dành cho nền: F2F2F2
màu Peach dành cho button chức năng: FF9292
màu xám hơi đậm dành cho label: 666666
màu đen dành cho text nhập vào: 333333
*/

import Data_Processor.DefaultValue;
import Data_Processor.Person;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
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
        //create
        AccInfoPanel = new JPanel();

        //set layout
        AccInfoPanel.setLayout(null);

        //set position and area
        AccInfoPanel.setBounds(0, 0,dv.FrameWidth()-dv.FrameHeight(),dv.FrameHeight() );
        AccInfoPanel.setPreferredSize(new Dimension(dv.FrameWidth()-dv.FrameHeight(),dv.FrameHeight()));
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
        //create
        PersonalInfoPanel = new JPanel();

        //set layout
        PersonalInfoPanel.setLayout(null);
        PersonalInfoPanel.setPreferredSize(new Dimension(dv.FrameHeight(),dv.FrameHeight()));

        //set position and area
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
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        //create new label
        UsernameLabel = new JLabel();

        //set label position and frame area
        UsernameLabel.setBounds(70, 80, 240, 30);
        UsernameLabel.setPreferredSize(new Dimension(240,30));

        //set label text
        UsernameLabel.setText("Số điện thoại");

        //set label text style
        UsernameLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        //set label text color
        UsernameLabel.setForeground(new Color(0x666666));

        UsernameLabel.setHorizontalAlignment(JLabel.LEFT);
        UsernameLabel.setVerticalAlignment(JLabel.CENTER);
    }

    private void initUsernameTextField()
    {
        //create Username text field
        UsernameTextField = new JTextField();

        //set position
        UsernameTextField.setBounds(70, 80+dv.LabelHeight(), 220, 30);

        //set cursor
        UsernameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        //set field font
        UsernameTextField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));

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
        PasswordLabel.setBounds(70, 90 + dv.FieldHeight() + dv.LabelHeight(), 270, 30);

        //set label text
        PasswordLabel.setText("Mật khẩu");

        //set label text style
        PasswordLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

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
        PasswordField.setBounds(70, 90 + dv.FieldHeight() + 2 * dv.LabelHeight(), 220, 30);

        //set cursor
        PasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        //set field font
        PasswordField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));

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
        RepeatPasswordLabel.setBounds(70, 100 + 3*dv.FieldHeight() + dv.LabelHeight(), 240, 30);

        //set label text
        RepeatPasswordLabel.setText("Nhập lại mật khẩu");

        //set label text style
        RepeatPasswordLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

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
        RepeatPasswordField.setBounds(70, 100 + 3*dv.FieldHeight() + 2 * dv.LabelHeight(), 220, 30);

        //set cursor
        RepeatPasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        //set field font
        RepeatPasswordField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));

        //set text field color
        RepeatPasswordField.setForeground(new Color(0x333333));

        //set field background color
        RepeatPasswordField.setBackground(Color.WHITE);

        RepeatPasswordField.setPreferredSize(new Dimension(220, 30));
    }

    private void initRegisterAccButton()
    {
        //create login button
        RegisterAccButton = new JButton();

        //set position
        RegisterAccButton.setBounds(105, 350, 150, 49);

        //set no border
        RegisterAccButton.setBorder(null);

        RegisterAccButton.setContentAreaFilled(false);


        //create an icon
        ImageIcon LoginIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/RegisterAcc.png"));

        //set label icon
        RegisterAccButton.setIcon(LoginIcon);

        RegisterAccButton.addActionListener(this);
    }

    private void initLastNameLabel()
    {
        //create
        LastNameLabel = new JLabel();

        //set position and area
        LastNameLabel.setBounds(70, 80, 240, 30);

        //set label text
        LastNameLabel.setText("Họ và tên đệm");

        //set label text style
        LastNameLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

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

        LastNameTextField.setText("");

        //set position and area
        LastNameTextField.setBounds(70, 80 + dv.LabelHeight(), 220, 30);

        //set cursor
        LastNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        //set field font
        LastNameTextField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));

        //set text field color
        LastNameTextField.setForeground(new Color(0x333333));

        //set field background color
        LastNameTextField.setBackground(Color.WHITE);

        LastNameTextField.setPreferredSize(new Dimension(220, 30));
    }

    private void initFirstNameLabel()
    {
        //create
        FirstNameLabel = new JLabel();

        //set position and area
        FirstNameLabel.setBounds(70+220+25, 80, 240, 30);

        //set label text
        FirstNameLabel.setText("Tên");

        //set label text style
        FirstNameLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

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
        FirstNameTextField.setBounds(70+220+25, 80 + dv.LabelHeight(), 150, 30);

        //set cursor
        FirstNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        //set field font
        FirstNameTextField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));

        //set text field color
        FirstNameTextField.setForeground(new Color(0x333333));

        //set field background color
        FirstNameTextField.setBackground(Color.WHITE);

        FirstNameTextField.setPreferredSize(new Dimension(220, 30));
    }

    private void initIDLabel()
    {
        //create
        IDLabel = new JLabel();

        //set position and area
        IDLabel.setBounds(70, 90 + dv.LabelHeight()+dv.FieldHeight(), 240, 30);

        //set label text
        IDLabel.setText("Mã định danh");

        //set label text style
        IDLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

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
        IDTextField.setBounds(70, 90 + 2*dv.LabelHeight()+dv.FieldHeight(), 220, 30);

        //set cursor
        IDTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        //set field font
        IDTextField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));

        //set text field color
        IDTextField.setForeground(new Color(0x333333));

        //set field background color
        IDTextField.setBackground(Color.WHITE);

        IDTextField.setPreferredSize(new Dimension(220, 30));
    }

    private void initBirthdayLabel()
    {
        //create
        BirthdayLabel = new JLabel();

        BirthdayLabel.setBounds(70, 100 + 2*dv.LabelHeight()+2*dv.FieldHeight(),220,30);

        //set label text
        BirthdayLabel.setText("Ngày tháng năm sinh");

        //set label text style
        BirthdayLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        //set label text color
        BirthdayLabel.setForeground(new Color(0x666666));

        //set label alignment
        BirthdayLabel.setHorizontalTextPosition(JLabel.LEFT);
        BirthdayLabel.setVerticalTextPosition(JLabel.CENTER);
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
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        //create new label
        GenderLabel = new JLabel();

        //set label position and frame area
        GenderLabel.setBounds(70 + 25 + 220, 100 + 2*dv.LabelHeight()+2*dv.FieldHeight(), 240, 30);
        GenderLabel.setPreferredSize(new Dimension(240,30));

        //set label text
        GenderLabel.setText("Giới tính");

        //set label text style
        GenderLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        //set label text color
        GenderLabel.setForeground(new Color(0x666666));

        GenderLabel.setHorizontalAlignment(JLabel.LEFT);
        GenderLabel.setVerticalAlignment(JLabel.CENTER);
    }
    private void initGenderChoice()
    {
        GenderChoice = new Choice();

        GenderChoice.setBounds(70 + 25 + 220, 100 + 3*dv.LabelHeight()+2*dv.FieldHeight(), 80, 30);

        GenderChoice.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        GenderChoice.setForeground(new Color(0x666666));

        GenderChoice.setBackground(Color.WHITE);

        GenderChoice.add("Nữ");
        GenderChoice.add("Nam");
        GenderChoice.add("Khác");
    }

    private void initHomeTownLabel()
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        //create new label
        HomeTownLabel = new JLabel();

        //set label position and frame area
        HomeTownLabel.setBounds(70, 110 + 3*dv.LabelHeight()+3*dv.FieldHeight(), 240, 30);
        HomeTownLabel.setPreferredSize(new Dimension(240,30));

        //set label text
        HomeTownLabel.setText("Quê quán");

        //set label text style
        HomeTownLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        //set label text color
        HomeTownLabel.setForeground(new Color(0x666666));

        HomeTownLabel.setHorizontalAlignment(JLabel.LEFT);
        HomeTownLabel.setVerticalAlignment(JLabel.CENTER);
    }

    private void initHomeTownChoice()
    {
        HomeTownChoice = new Choice();

        HomeTownChoice.setBounds(70, 110 + 4*dv.LabelHeight()+3*dv.FieldHeight(), 170, 30);

        HomeTownChoice.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        HomeTownChoice.setForeground(new Color(0x666666));

        HomeTownChoice.setBackground(Color.WHITE);

        HomeTownChoice.add("Hồ Chí Minh");
        HomeTownChoice.add("Bình Dương");
        HomeTownChoice.add("Hà Nội");
    }

    private void initProvinceLabel()
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        //create new label
        ProvinceLabel = new JLabel();

        //set label position and frame area
        ProvinceLabel.setBounds(70, 120 + 4*dv.LabelHeight()+4*dv.FieldHeight(), 240, 30);
        ProvinceLabel.setPreferredSize(new Dimension(240,30));

        //set label text
        ProvinceLabel.setText("Tỉnh/thành phố cư trú");

        //set label text style
        ProvinceLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        //set label text color
        ProvinceLabel.setForeground(new Color(0x666666));

        ProvinceLabel.setHorizontalAlignment(JLabel.LEFT);
        ProvinceLabel.setVerticalAlignment(JLabel.CENTER);
    }

    private void initProvinceChoice()
    {
        ProvinceChoice = new Choice();

        ProvinceChoice.setBounds(70, 120 + 5*dv.LabelHeight()+4*dv.FieldHeight(), 170, 30);

        ProvinceChoice.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        ProvinceChoice.setForeground(new Color(0x666666));

        ProvinceChoice.setBackground(Color.WHITE);

        ProvinceChoice.add("Hồ Chí Minh");
        ProvinceChoice.add("Đồng Nai");
        ProvinceChoice.add("Bình Dương");
        ProvinceChoice.add("Hà Nội");
    }

    private void initDistrictLabel()
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        //create new label
        DistrictLabel = new JLabel();

        //set label position and frame area
        DistrictLabel.setBounds(70 + 25 +170, 120 + 4*dv.LabelHeight()+4*dv.FieldHeight(), 240, 30);
        DistrictLabel.setPreferredSize(new Dimension(240,30));

        //set label text
        DistrictLabel.setText("Quận/huyện cư trú");

        //set label text style
        DistrictLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        //set label text color
        DistrictLabel.setForeground(new Color(0x666666));

        DistrictLabel.setHorizontalAlignment(JLabel.LEFT);
        DistrictLabel.setVerticalAlignment(JLabel.CENTER);
    }

    private void initDistrictChoice()
    {
        DistrictChoice = new Choice();

        DistrictChoice.setBounds(70+25+170, 120 + 5*dv.LabelHeight()+4*dv.FieldHeight(), 170, 30);

        DistrictChoice.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        DistrictChoice.setForeground(new Color(0x666666));

        DistrictChoice.setBackground(Color.WHITE);

        DistrictChoice.add("Thủ Đức");
        DistrictChoice.add("Biên Hòa");
        DistrictChoice.add("Cẩm Mỹ");
        DistrictChoice.add("Thủ Dầu Một");
    }

    private void initTownLabel()
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        //create new label
        TownLabel = new JLabel();

        //set label position and frame area
        TownLabel.setBounds(70 + 50 +2*170, 120 + 4*dv.LabelHeight()+4*dv.FieldHeight(), 240, 30);
        TownLabel.setPreferredSize(new Dimension(240,30));

        //set label text
        TownLabel.setText("Phường/xã cư trú");

        //set label text style
        TownLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        //set label text color
        TownLabel.setForeground(new Color(0x666666));

        TownLabel.setHorizontalAlignment(JLabel.LEFT);
        TownLabel.setVerticalAlignment(JLabel.CENTER);
    }

    private void initTownChoice()
    {
        TownChoice = new Choice();

        TownChoice.setBounds(70+50+2*170, 120 + 5*dv.LabelHeight()+4*dv.FieldHeight(), 170, 30);

        TownChoice.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        TownChoice.setForeground(new Color(0x666666));

        TownChoice.setBackground(Color.WHITE);

        TownChoice.add("Linh Trung");
        TownChoice.add("Dầu Tiếng");
        TownChoice.add("Lái Thiêu");
        TownChoice.add("Tân Hòa");
        TownChoice.add("Sông Ray");
    }

    private void initStreetLabel()
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        //create new label
        StreetLabel = new JLabel();

        //set label position and frame area
        StreetLabel.setBounds(70, 130 + 5*dv.LabelHeight()+5*dv.FieldHeight(), 240, 30);
        StreetLabel.setPreferredSize(new Dimension(240,30));

        //set label text
        StreetLabel.setText("Số nhà, tên đường, khu phố/ấp");

        //set label text style
        StreetLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        //set label text color
        StreetLabel.setForeground(new Color(0x666666));

        StreetLabel.setHorizontalAlignment(JLabel.LEFT);
        StreetLabel.setVerticalAlignment(JLabel.CENTER);
    }

    private void initStreetTextField()
    {
        //create
        StreetTextField = new JTextField();

        //set position and area
        StreetTextField.setBounds(70, 130 + 6*dv.LabelHeight()+5*dv.FieldHeight(), 300, 30);

        //set cursor
        StreetTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        //set field font
        StreetTextField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));

        //set text field color
        StreetTextField.setForeground(new Color(0x333333));

        //set field background color
        StreetTextField.setBackground(Color.WHITE);

        StreetTextField.setPreferredSize(new Dimension(220, 30));
    }

    private void initEmailLabel()
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        //create new label
        EmailLabel = new JLabel();

        //set label position and frame area
        EmailLabel.setBounds(70, 140 + 6*dv.LabelHeight()+6*dv.FieldHeight(), 240, 30);
        EmailLabel.setPreferredSize(new Dimension(240,30));

        //set label text
        EmailLabel.setText("Email");

        //set label text style
        EmailLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        //set label text color
        EmailLabel.setForeground(new Color(0x666666));

        EmailLabel.setHorizontalAlignment(JLabel.LEFT);
        EmailLabel.setVerticalAlignment(JLabel.CENTER);
    }

    private void initEmailTextField()
    {
        //create
        EmailTextField = new JTextField();

        //set position and area
        EmailTextField.setBounds(70, 140 + 7*dv.LabelHeight()+6*dv.FieldHeight(), 220, 30);

        //set cursor
        EmailTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        //set field font
        EmailTextField.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));

        //set text field color
        EmailTextField.setForeground(new Color(0x333333));

        //set field background color
        EmailTextField.setBackground(Color.WHITE);

        EmailTextField.setPreferredSize(new Dimension(220, 30));
    }
    private void initFrameComponent()
    {
        //Frame
        //set frame title
        //this.setTitle("Đăng ký");

        //set frame size
        this.setSize(dv.FrameWidth(), dv.FrameHeight());
        //this.setSize(1080, 720); --Main View

        //set do not allow frame resizing
        //this.setResizable(false);

        //set frame visible on screen
        //this.setVisible(true);

        //set frame close on X button
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set frame background color
        this.setBackground(new Color(dv.ViewBackgroundColor()));

        //set Frame icon
        //this.setIconImage(new ImageIcon(getClass().getResource("/Data_Processor/icon/Virus.png")).getImage());

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


    public UserInformationView()
    {
        initFrameComponent();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == RegisterAccButton) {

            JFormattedTextField textField = BirthdayField.getJFormattedTextField();

            String InputID = IDTextField.getText();
            String InputUsername = UsernameTextField.getText();
            String InputPassword = String.valueOf(PasswordField.getPassword());
            String InputRepeatPassword = String.valueOf(RepeatPasswordField.getPassword());
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

            if (InputPassword.equals(InputRepeatPassword) == false) {
                System.out.println("Mật khẩu không trùng khớp!");
                return;
            }

            String query1 = "exec ACC_INSERT_RECORD('" + InputUsername + "', '" + InputPassword + "', 2, 1);";

            String query2 = "exec PERSON_INSERT_RECORD('" + InputID + "', '" + InputLastName + "', '" +InputFirstName
                    + "', '" + InputBirthday + "', " + InputGender + ", '" + InputHomeTown + "', '" + InputProvince
                    + "', '" + InputDistrict + "', '" + InputTown + "', '" + InputStreet + "', '" + InputUsername
                    + "', '"  +InputEmail +  "');";

            CallableStatement cs = null;
            CallableStatement cs1 = null;
            Connection connection1 = null;
            Connection connection2 = null;
            /*try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    connection1 = DriverManager.getConnection(dv.getDB_URL1(), dv.getUsername1(), dv.getPassword1());
                    connection1.setAutoCommit((false));

                    String sql = "{call ACC_INSERT_RECORD(?,?,?,?)}";
                    cs = connection1.prepareCall(sql);
                    cs.setString(1, InputUsername);
                    cs.setString(2, InputPassword);
                    cs.setInt(3, 2);
                    cs.setInt(4,1);
                    cs.executeQuery();

                    connection1.rollback();

                } catch (Exception ex) {
                    System.out.println("Tài khoản đã tồn tại!");
                    ex.printStackTrace();
            }*/
            try {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                connection2 = DriverManager.getConnection(dv.getDB_URL1(), dv.getUsername1(), dv.getPassword1());
                //connection2.setAutoCommit((false));

                String sql1 = "{call PERSON_INSERT_RECORD(?,?,?,?,?,?,?,?,?,?,?,?)}";
                cs1 = connection2.prepareCall(sql1);
                cs1.setString(1, InputID);
                cs1.setString(2, InputLastName);
                cs1.setString(3, InputFirstName);
                cs1.setString(4,InputBirthday);
                cs1.setInt(5, InputGender);
                cs1.setString(6,InputHomeTown);
                cs1.setString(7,InputProvince);
                cs1.setString(8,InputDistrict);
                cs1.setString(9,InputTown);
                cs1.setString(10,InputStreet);
                cs1.setString(11,InputUsername);
                cs1.setString(12,InputEmail);
                cs1.executeQuery();

                //connection2.rollback();

            } catch (Exception ex) {
                System.out.println("Không thành công!");
                ex.printStackTrace();
            }
        }
    }
}
