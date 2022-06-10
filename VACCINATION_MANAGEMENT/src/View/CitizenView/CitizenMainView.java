package View.CitizenView;

import Process.*;
import View.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

public class CitizenMainView extends JFrame implements ActionListener
{
    /*Main GUI*/
    private JLayeredPane MainLayeredPane;
    private JPanel MainPanel;

    private JLayeredPane InfoLayeredPane;
    private JLabel InfoBackground;

    private JLayeredPane FeatureLayeredPane;
    private JButton InfoSettingFButton;
    private JButton RegisterVaccinationFButton;
    private JButton NotificationFButton;
    private JButton FillFormFButton;
    private JButton ManageRegistrationFButton;
    private JButton UpdateInjectionFButton;
    private JButton CertificateFButton;

    /*Data Stored Class*/
    private DefaultValue dv = new DefaultValue();
    private Person personalUser = new Person();

    /*Other Views*/
    private LoginView loginView;
    private UserInformationView userInformationView;
    private RegisterVaccinationView registerVaccinationView;
    private FillFormView fillFormView;
    private ManageRegistrationView manageRegistrationView;
    private UpdateInjectionView updateInjectionView;
    private CertificateView certificateView;
    private AnnouncementView announcementView;

    private JButton LogoutButton;
    private JButton BackButton;
    private JButton BackInfoButton;
    private JButton VaccinationRegButton;

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

    private void initBackInfoButton()
    {
        BackInfoButton = new JButton();
        ImageIcon BackInfoButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Back Button_2.png"));
        BackInfoButton.setIcon(BackInfoButtonIcon);

        BackInfoButton.setBounds(10, 10, BackInfoButtonIcon.getIconWidth(), BackInfoButtonIcon.getIconHeight());
        BackInfoButton.setBorder(null);
        BackInfoButton.setContentAreaFilled(false);

        BackInfoButton.addActionListener(this);
    }

    private void initVaccinationRegButton()
    {
        ImageIcon VaccRegIcon = new ImageIcon(getClass().getResource("/Resources/icon/Vaccination Reg Button.png"));
        VaccinationRegButton = new JButton();
        VaccinationRegButton.setBounds((320-VaccRegIcon.getIconWidth())/2, 600, VaccRegIcon.getIconWidth(), VaccRegIcon.getIconHeight());
        VaccinationRegButton.setBorder(null);
        VaccinationRegButton.setContentAreaFilled(false);
        VaccinationRegButton.setIcon(VaccRegIcon);
        VaccinationRegButton.addActionListener(this);
    }

    private void initLogoutButton()
    {
        LogoutButton = new JButton();
        LogoutButton.setBounds(105, 580, 160, 56);
        LogoutButton.setBorder(null);
        LogoutButton.setContentAreaFilled(false);
        ImageIcon LoginIcon = new ImageIcon(getClass().getResource("/Resources/icon/Personal Logout Button.png"));
        LogoutButton.setIcon(LoginIcon);
        LogoutButton.addActionListener(this);
    }

    private void initInfoBackground()
    {
        ImageIcon InfoLayeredPaneBackground = new ImageIcon(getClass().getResource("/Resources/icon/Personal Info Panel.png"));

        InfoBackground = new JLabel(InfoLayeredPaneBackground);

        InfoBackground.setBounds(0,0, dv.FrameWidth()-dv.FrameHeight() + 8, dv.FrameHeight());

        InfoBackground.setHorizontalAlignment(JLabel.LEFT);
    }

    private void initInfoLayeredPane()
    {
        InfoLayeredPane = new JLayeredPane();

        InfoLayeredPane.setBounds(0,0,dv.FrameWidth()-dv.FrameHeight() + 8, dv.FrameHeight());

        InfoLayeredPane.setLayout(null);

        initInfoBackground();
        InfoLayeredPane.add(InfoBackground, Integer.valueOf(0));

        JLabel InfoLabel = new JLabel("THÔNG TIN CƠ BẢN");
        InfoLabel.setBounds(0,40,360,35);
        InfoLabel.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        InfoLabel.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon AvatarImage = new ImageIcon(getClass().getResource("/Resources/icon/Avatar.png"));
        JLabel Avatar = new JLabel(AvatarImage);
        Avatar.setBounds((dv.FrameWidth()-dv.FrameHeight()-AvatarImage.getIconWidth())/2,100,
                AvatarImage.getIconWidth(),AvatarImage.getIconHeight());
        Avatar.setHorizontalAlignment(JLabel.CENTER);

        JLabel Name = new JLabel(personalUser.getFullName());
        Name.setBounds(0, 300, 360, 35);
        Name.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        Name.setHorizontalAlignment(JLabel.CENTER);

        JLabel BasicInfo = new JLabel(dv.getGenderName(personalUser.getGender()) + " - "
                + dv.toOracleDateFormat(personalUser.getBirthday()).substring(7,11));
        BasicInfo.setBounds(0, 350, 360, 35);
        BasicInfo.setFont(new Font(dv.fontName(),Font.BOLD, 20));
        BasicInfo.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon LocationImage = new ImageIcon(getClass().getResource("/Resources/icon/Location.png"));
        JLabel Location = new JLabel(personalUser.getProvince());
        Location.setFont(new Font(dv.fontName(),Font.BOLD, 20));
        Location.setIcon(LocationImage);
        Location.setBounds(0,400,360,30);
        Location.setHorizontalAlignment(JLabel.CENTER);

        initLogoutButton();

        InfoLayeredPane.add(InfoLabel, Integer.valueOf(1));
        InfoLayeredPane.add(Avatar, Integer.valueOf(1));
        InfoLayeredPane.add(Name, Integer.valueOf(1));
        InfoLayeredPane.add(BasicInfo, Integer.valueOf(1));
        InfoLayeredPane.add(Location, Integer.valueOf(1));

        InfoLayeredPane.add(LogoutButton, Integer.valueOf(1));

        InfoLayeredPane.repaint(0,0, dv.FrameWidth()-dv.FrameHeight() + 8, dv.FrameHeight());
    }

    private void initInfoSettingFButton()
    {
        InfoSettingFButton = new JButton();
        InfoSettingFButton.setBounds(60, 30, 133, 133);
        InfoSettingFButton.setBorder(null);
        InfoSettingFButton.setContentAreaFilled(false);
        InfoSettingFButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Personal Info Feature Button.png")));
        InfoSettingFButton.addActionListener(this);

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(0, 160, 240, 30);
        ButtonLabel.setText("Thông tin");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(0, 160 +25, 240, 30);
        ButtonLabel2.setText("cá nhân");
        ButtonLabel2.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel2.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel2.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        FeatureLayeredPane.add(ButtonLabel);
        FeatureLayeredPane.add(ButtonLabel2);
    }

    private void initRegisterVaccinationFButton()
    {
        RegisterVaccinationFButton = new JButton();
        RegisterVaccinationFButton.setBounds(240 + 30+15, 30, 133, 133);
        RegisterVaccinationFButton.setBorder(null);
        RegisterVaccinationFButton.setContentAreaFilled(false);
        RegisterVaccinationFButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Register Vaccination Feature Button.png")));
        RegisterVaccinationFButton.addActionListener(this);

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(240, 160, 240, 30);
        ButtonLabel.setText("Đăng ký");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(240, 160 +25, 240, 30);
        ButtonLabel2.setText("tiêm chủng");
        ButtonLabel2.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel2.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel2.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel2_1.setBorder(dv.border());
        FeatureLayeredPane.add(ButtonLabel);
        FeatureLayeredPane.add(ButtonLabel2);
    }

    private void initNotificationFButton()
    {
        NotificationFButton = new JButton();
        NotificationFButton.setBounds(240*2+30, 30, 133,133);
        NotificationFButton.setBorder(null);
        NotificationFButton.setContentAreaFilled(false);
        NotificationFButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Notification Feature Button.png")));
        NotificationFButton.addActionListener(this);

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(240*2 -15, 160, 240-20, 30);
        ButtonLabel.setText("Thông báo");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(240*2 -15, 160 +25, 240-20, 30);
        ButtonLabel2.setText("- tin tức");
        ButtonLabel2.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel2.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel2.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        FeatureLayeredPane.add(ButtonLabel);
        FeatureLayeredPane.add(ButtonLabel2);
    }

    private void initFillFormFButton()
    {
        FillFormFButton = new JButton();
        FillFormFButton.setBounds(60, 240, 133, 133);
        FillFormFButton.setBorder(null);
        FillFormFButton.setContentAreaFilled(false);
        FillFormFButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Fill Form Feature Button.png")));
        FillFormFButton.addActionListener(this);

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(0, 240+130, 240, 30);
        ButtonLabel.setText("Khai báo y tế");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());
        FeatureLayeredPane.add(ButtonLabel);
    }

    private void initManageRegistrationFButton()
    {
        ManageRegistrationFButton = new JButton();
        ManageRegistrationFButton.setBounds(240 + 30+15, 240 , 133, 133);
        ManageRegistrationFButton.setBorder(null);
        ManageRegistrationFButton.setContentAreaFilled(false);
        ManageRegistrationFButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Manage Registration Feature Button.png")));
        ManageRegistrationFButton.addActionListener(this);

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(240 -10, 240+130, 240, 30);
        ButtonLabel.setText("Quản lý");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(240 -10, 240+130 +25, 240, 30);
        ButtonLabel2.setText("lịch đăng ký");
        ButtonLabel2.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel2.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel2.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        FeatureLayeredPane.add(ButtonLabel);
        FeatureLayeredPane.add(ButtonLabel2);
    }

    private void initUpdateInjectionFButton()
    {
        UpdateInjectionFButton = new JButton();
        UpdateInjectionFButton.setBounds(240*2+30, 240, 133, 133);
        UpdateInjectionFButton.setBorder(null);
        UpdateInjectionFButton.setContentAreaFilled(false);
        UpdateInjectionFButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Update Injection Feature Button.png")));
        UpdateInjectionFButton.addActionListener(this);

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(240*2 -15, 240+130, 240 -20, 30);
        ButtonLabel.setText("Cập nhật");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(240*2 -15, 240+130 +25, 240 -20, 30);
        ButtonLabel2.setText("mũi tiêm");
        ButtonLabel2.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel2.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel2.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        FeatureLayeredPane.add(ButtonLabel);
        FeatureLayeredPane.add(ButtonLabel2);
    }

    private void initCertificateFButton()
    {
        CertificateFButton = new JButton();
        CertificateFButton.setBounds(60, 240*2-30, 133, 133);
        CertificateFButton.setBorder(null);
        CertificateFButton.setContentAreaFilled(false);
        CertificateFButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Certificate Feature Button.png")));
        CertificateFButton.addActionListener(this);

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(10, 240*2-30 +130, 240, 30);
        ButtonLabel.setText("Chứng nhận");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(10, 240*2-30 +130 +25, 240, 30);
        ButtonLabel2.setText("tiêm chủng");
        ButtonLabel2.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel2.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel2.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        FeatureLayeredPane.add(ButtonLabel);
        FeatureLayeredPane.add(ButtonLabel2);
    }

    private void initFeatureLayeredPane()
    {
        FeatureLayeredPane = new JLayeredPane();
        FeatureLayeredPane.setBounds(360, 0, dv.FrameWidth() - 360, dv.FrameHeight());
        FeatureLayeredPane.setLayout(null);

        initInfoSettingFButton();
        FeatureLayeredPane.add(InfoSettingFButton);

        initRegisterVaccinationFButton();
        FeatureLayeredPane.add(RegisterVaccinationFButton);

        initNotificationFButton();
        FeatureLayeredPane.add(NotificationFButton);

        initFillFormFButton();
        FeatureLayeredPane.add(FillFormFButton);

        initManageRegistrationFButton();
        FeatureLayeredPane.add(ManageRegistrationFButton);

        initUpdateInjectionFButton();
        FeatureLayeredPane.add(UpdateInjectionFButton);

        initCertificateFButton();
        FeatureLayeredPane.add(CertificateFButton);
    }


    private void initMainPanel()
    {
        MainPanel = new JPanel();
        MainPanel.setBounds(0,0,dv.FrameWidth(),dv.FrameHeight());
        MainPanel.setBackground(new Color(dv.ViewBackgroundColor()));
        MainPanel.setLayout(null);
    }

    private void initMainLayeredPane()
    {
        MainLayeredPane = new JLayeredPane();
        MainLayeredPane.setBounds(0, 0, dv.FrameWidth(), dv.FrameHeight());
        MainLayeredPane.setBackground(new Color(dv.ViewBackgroundColor()));
        MainLayeredPane.setLayout(null);
    }

    public CitizenMainView(String Username)
    {
        this.setTitle("Công dân - " + Username);
        this.setBounds(260, 90, dv.FrameWidth(), dv.FrameHeight());
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(getClass().getResource("/Resources/icon/Virus.png")).getImage());
        this.setLayout(null);

        String query = "select * from PERSON where PERSON.Phone = '" +  Username + "'";

        try {
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet rs = st.executeQuery(query);

            rs.next();
            personalUser.setID(rs.getString("ID"));
            personalUser.setFirstName(rs.getString("FirstName"));
            personalUser.setLastName(rs.getString("LastName"));
            personalUser.setBirthday(rs.getString("Birthday"));
            personalUser.setGender(rs.getInt("Gender"));
            personalUser.setHomeTown(rs.getString("HomeTown"));
            personalUser.setProvince(rs.getString("ProvinceName"));
            personalUser.setDistrict(rs.getString("DistrictName"));
            personalUser.setTown(rs.getString("TownName"));
            personalUser.setStreet(rs.getString("Street"));
            personalUser.setPhone(rs.getString("Phone"));
            personalUser.setEmail(rs.getString("Email"));
            personalUser.setGuardian(rs.getString("Guardian"));
        } catch (SQLException ex) {
            dv.popupOption(null,ex.getMessage(), String.valueOf(ex.getErrorCode()),2);
            ex.printStackTrace();
            return;
        }

        //init BackButton
        initBackButton();

        initMainLayeredPane();
        initMainPanel();

        initInfoLayeredPane();
        MainPanel.add(InfoLayeredPane);

        initFeatureLayeredPane();
        MainPanel.add(FeatureLayeredPane);

        MainLayeredPane.add(MainPanel, Integer.valueOf(0));

        this.add(MainLayeredPane);

        this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
    }

    private void nullFeatureView()
    {
        userInformationView = null;
        registerVaccinationView = null;
        announcementView = null;
        fillFormView = null;
        manageRegistrationView = null;
        updateInjectionView = null;
        certificateView = null;
    }

    /*ACTION PERFORMED*/
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == LogoutButton)
        {
            String query = "update ACCOUNT ACC set Status = 1 where ACC.Username = '" + personalUser.getPhone() + "'";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                PreparedStatement st = connection.prepareStatement(query);

                st.executeUpdate(query);
            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(),"Lỗi " + ex.getErrorCode(),2);
                ex.printStackTrace();
                return;
            }

            loginView = new LoginView();
            this.dispose();
        }

        if(e.getSource() == BackButton)
        {
            nullFeatureView();

            MainLayeredPane.removeAll();
            MainLayeredPane.add(MainPanel, Integer.valueOf(0));
            MainLayeredPane.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
        }

        if(e.getSource() == BackInfoButton)
        {
            personalUser.setPhone(userInformationView.getPersonalUser().getPhone());

            String query = "select * from PERSON where PERSON.Phone = '" +  personalUser.getPhone() + "'";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                PreparedStatement st = connection.prepareStatement(query);

                ResultSet rs = st.executeQuery(query);

                rs.next();
                personalUser.setID(rs.getString("ID"));
                personalUser.setFirstName(rs.getString("FirstName"));
                personalUser.setLastName(rs.getString("LastName"));
                personalUser.setBirthday(rs.getString("Birthday"));
                personalUser.setGender(rs.getInt("Gender"));
                personalUser.setHomeTown(rs.getString("HomeTown"));
                personalUser.setProvince(rs.getString("ProvinceName"));
                personalUser.setDistrict(rs.getString("DistrictName"));
                personalUser.setTown(rs.getString("TownName"));
                personalUser.setStreet(rs.getString("Street"));
                personalUser.setPhone(rs.getString("Phone"));
                personalUser.setEmail(rs.getString("Email"));
                personalUser.setGuardian(rs.getString("Guardian"));
            } catch (SQLException ex) {
                dv.popupOption(null,ex.getMessage(), String.valueOf(ex.getErrorCode()),2);
                ex.printStackTrace();
                return;
            }

            userInformationView = null;
            MainLayeredPane.removeAll();
            initInfoLayeredPane();
            MainPanel.removeAll();
            MainPanel.add(InfoLayeredPane);
            MainPanel.add(FeatureLayeredPane);
            MainLayeredPane.add(MainPanel);
        }

        if (e.getSource() == InfoSettingFButton)
        {
            nullFeatureView();

            userInformationView = new UserInformationView(personalUser);
            initBackInfoButton();
            MainLayeredPane.removeAll();
            MainLayeredPane.add(userInformationView, Integer.valueOf(0));
            MainLayeredPane.add(BackInfoButton, Integer.valueOf(5));
        }

        if (e.getSource() == RegisterVaccinationFButton)
        {
            nullFeatureView();

            registerVaccinationView = new RegisterVaccinationView(personalUser);
            MainLayeredPane.removeAll();
            MainLayeredPane.add(registerVaccinationView, Integer.valueOf(1));
            MainLayeredPane.add(BackButton, Integer.valueOf(5));
        }

        if (e.getSource() == FillFormFButton)
        {
            nullFeatureView();

            fillFormView = new FillFormView(personalUser);
            MainLayeredPane.removeAll();
            MainLayeredPane.add(fillFormView, Integer.valueOf(0));
            MainLayeredPane.add(BackButton, Integer.valueOf(5));
        }

        if(e.getSource() == ManageRegistrationFButton)
        {
            nullFeatureView();

            manageRegistrationView = new ManageRegistrationView(personalUser);
            initVaccinationRegButton();
            MainLayeredPane.removeAll();
            MainLayeredPane.add(manageRegistrationView, Integer.valueOf(0));
            MainLayeredPane.add(VaccinationRegButton, Integer.valueOf(1));
            MainLayeredPane.add(BackButton, Integer.valueOf(5));
        }

        if (e.getSource() == VaccinationRegButton)
        {
            nullFeatureView();

            registerVaccinationView = new RegisterVaccinationView(personalUser);
            MainLayeredPane.removeAll();
            MainLayeredPane.add(registerVaccinationView, Integer.valueOf(1));
            MainLayeredPane.add(BackButton, Integer.valueOf(5));
        }

        if (e.getSource() == UpdateInjectionFButton)
        {
            nullFeatureView();

            try {
                updateInjectionView = new UpdateInjectionView(personalUser);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            MainLayeredPane.removeAll();
            MainLayeredPane.add(updateInjectionView, Integer.valueOf(0));
            MainLayeredPane.add(BackButton, Integer.valueOf(5));
        }

        if (e.getSource() == CertificateFButton)
        {
            nullFeatureView();

            certificateView = new CertificateView(personalUser);
            MainLayeredPane.removeAll();
            MainLayeredPane.add(certificateView, Integer.valueOf(0));
            MainLayeredPane.add(BackButton, Integer.valueOf(5));
        }
        
        if(e.getSource()==NotificationFButton)
        {
            nullFeatureView();

            announcementView=new AnnouncementView(personalUser);
            MainLayeredPane.removeAll();
            MainLayeredPane.add(announcementView, Integer.valueOf(0));
            MainLayeredPane.add(BackButton, Integer.valueOf(5));
        }
    }

}
