package View.OrgView;

import Process.*;
import View.CitizenView.RegisterVaccinationView;
import View.LoginView;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MOHMainView extends JFrame implements ActionListener
{
    private JLayeredPane MainLayeredPane;

    private JPanel MainPanel;

    private DefaultValue dv = new DefaultValue();
    private JLayeredPane InfoLayeredPane;
    private JLabel InfoBackground;

    private JLayeredPane FeatureLayeredPane;
    private JButton InfoSettingButton;
    private JButton CreateOrgAccButton;
    private JButton PublishPostButton;
    private JButton SearchButton;
    private JButton StatisticButton;

    private JButton BackButton;
    private JButton BackInfoButton;
    private JButton LogoutButton;

    private Organization mohUser = new Organization();

    private LoginView loginView;
    private OrgInformationView orgInformationView;
    private CreateOrgAccView createOrgAccView;
    private SearchCitizenView searchCitizenView;
    private MOHStatisticView mohStatisticView;

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

        JLabel Name = new JLabel(mohUser.getName());
        Name.setBounds(0, 300, 360, 35);
        Name.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        Name.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon LocationImage = new ImageIcon(getClass().getResource("/Resources/icon/Location.png"));
        JLabel Location = new JLabel(dv.getProvinceName(mohUser.getProvince()));
        Location.setFont(new Font(dv.fontName(),Font.BOLD, 20));
        Location.setIcon(LocationImage);
        Location.setBounds(0,400,360,30);
        Location.setHorizontalAlignment(JLabel.CENTER);

        initLogoutButton();

        InfoLayeredPane.add(InfoLabel, Integer.valueOf(1));
        InfoLayeredPane.add(Avatar, Integer.valueOf(1));
        InfoLayeredPane.add(Name, Integer.valueOf(1));
        InfoLayeredPane.add(Location, Integer.valueOf(1));

        InfoLayeredPane.add(LogoutButton, Integer.valueOf(1));

        InfoLayeredPane.repaint(0,0, dv.FrameWidth()-dv.FrameHeight() + 8, dv.FrameHeight());
    }

    private void initFeatureLayeredPane()
    {
        FeatureLayeredPane = new JLayeredPane();
        FeatureLayeredPane.setBounds(360, 0, dv.FrameWidth() - 360, dv.FrameHeight());
        FeatureLayeredPane.setLayout(null);
        FeatureLayeredPane.setBackground(new Color(dv.ViewBackgroundColor()));

        initInfoSettingButton();
        FeatureLayeredPane.add(InfoSettingButton);

        initCreateOrgAccButton();
        FeatureLayeredPane.add(CreateOrgAccButton);

        initPublishPostButton();
        FeatureLayeredPane.add(PublishPostButton);

        initSearchButton();
        FeatureLayeredPane.add(SearchButton);

        initStatisticButton();
        FeatureLayeredPane.add(StatisticButton);
    }

    private void initInfoBackground()
    {
        ImageIcon InfoLayeredPaneBackground = new ImageIcon(getClass().getResource("/Resources/icon/Org Info Panel.png"));

        InfoBackground = new JLabel(InfoLayeredPaneBackground);

        InfoBackground.setBounds(0,0, dv.FrameWidth()-dv.FrameHeight() + 8, dv.FrameHeight());

        InfoBackground.setHorizontalAlignment(JLabel.LEFT);
    }

    private void initInfoSettingButton()
    {
        InfoSettingButton = new JButton();
        InfoSettingButton.setBounds(60, 30, 133, 133);
        InfoSettingButton.setBorder(null);
        InfoSettingButton.setContentAreaFilled(false);
        InfoSettingButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Org Info Feature Button.png")));
        InfoSettingButton.addActionListener(this);

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(0, 160, 240, 30);
        ButtonLabel.setText("Thông tin");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(0, 160 +25, 240, 30);
        ButtonLabel2.setText("đơn vị");
        ButtonLabel2.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel2.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel2.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        FeatureLayeredPane.add(ButtonLabel);
        FeatureLayeredPane.add(ButtonLabel2);
    }

    private void initCreateOrgAccButton()
    {
        CreateOrgAccButton = new JButton();
        CreateOrgAccButton.setBounds(240 + 30+15, 30, 133, 133);
        CreateOrgAccButton.setBorder(null);
        CreateOrgAccButton.setContentAreaFilled(false);
        CreateOrgAccButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Create Org Acc Feature Button.png")));
        CreateOrgAccButton.addActionListener(this);

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(240, 160, 240, 30);
        ButtonLabel.setText("Tạo tài khoản");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(240, 160 +25, 240, 30);
        ButtonLabel2.setText("đơn vị");
        ButtonLabel2.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel2.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel2.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel2_1.setBorder(dv.border());
        FeatureLayeredPane.add(ButtonLabel);
        FeatureLayeredPane.add(ButtonLabel2);
    }

    private void initPublishPostButton()
    {
        PublishPostButton = new JButton();
        PublishPostButton.setBounds(240*2+30, 30, 133,133);
        PublishPostButton.setBorder(null);
        PublishPostButton.setContentAreaFilled(false);
        PublishPostButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Send Notification Feature Button.png")));

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(240*2 -15, 160, 240-20, 30);
        ButtonLabel.setText("Gửi thông tin");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(240*2 -15, 160 +25, 240-20, 30);
        ButtonLabel2.setText("- thông báo");
        ButtonLabel2.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel2.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel2.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        FeatureLayeredPane.add(ButtonLabel);
        FeatureLayeredPane.add(ButtonLabel2);
    }

    private void initSearchButton()
    {
        SearchButton = new JButton();
        SearchButton.setBounds(60, 240, 133, 133);
        SearchButton.setBorder(null);
        SearchButton.setContentAreaFilled(false);
        SearchButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Search Feature Button.png")));
        SearchButton.addActionListener(this);

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(0, 240+130, 240, 30);
        ButtonLabel.setText("Tra cứu");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(0, 240+130 +25, 240, 30);
        ButtonLabel2.setText("công dân");
        ButtonLabel2.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel2.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel2.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        FeatureLayeredPane.add(ButtonLabel);
        FeatureLayeredPane.add(ButtonLabel2);
    }

    private void initStatisticButton()
    {
        StatisticButton = new JButton();
        StatisticButton.setBounds(240 + 30+15, 240 , 133, 133);
        StatisticButton.setBorder(null);
        StatisticButton.setContentAreaFilled(false);
        StatisticButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Statistic Feature Button.png")));
        StatisticButton.addActionListener(this);

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(240 -10, 240+130, 240, 30);
        ButtonLabel.setText("Thống kê");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(240 -10, 240+130 +25, 240, 30);
        ButtonLabel2.setText("số liệu");
        ButtonLabel2.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel2.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel2.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        FeatureLayeredPane.add(ButtonLabel);
        FeatureLayeredPane.add(ButtonLabel2);
    }

    private void initLogoutButton()
    {
        LogoutButton = new JButton();
        LogoutButton.setBounds(105, 580, 160, 56);
        LogoutButton.setBorder(null);
        LogoutButton.setContentAreaFilled(false);
        ImageIcon LoginIcon = new ImageIcon(getClass().getResource("/Resources/icon/Logout Button.png"));
        LogoutButton.setIcon(LoginIcon);
        LogoutButton.addActionListener(this);
    }

    private void initMainPanel()
    {
        MainPanel = new JPanel();
        MainPanel.setBounds(0,0,dv.FrameWidth(),dv.FrameHeight());
        MainPanel.setLayout(null);
        MainPanel.setBackground(new Color(dv.ViewBackgroundColor()));
    }

    private void initMainLayeredPane()
    {
        MainLayeredPane = new JLayeredPane();
        MainLayeredPane.setBounds(0, 0, dv.FrameWidth(), dv.FrameHeight());
        MainLayeredPane.setLayout(null);
        MainLayeredPane.setBackground(new Color(dv.ViewBackgroundColor()));
    }

    /*CONSTRUCTOR*/
    public MOHMainView(String Username)
    {
        this.setTitle("Quản lý tiêm chủng vaccine Covid-19: Bộ Y tế");
        this.setBounds(260, 90, dv.FrameWidth(), dv.FrameHeight());
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(new Color(dv.ViewBackgroundColor()));
        this.setIconImage(new ImageIcon(getClass().getResource("/Resources/icon/Virus.png")).getImage());
        this.setLayout(null);

        String query = "select * from ORGANIZATION ORG where ORG.ID = '" +  Username + "'";

        try {
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet rs = st.executeQuery(query);

            rs.next();
            mohUser.setID(rs.getString("ID"));
            mohUser.setName(rs.getString("Name"));
            mohUser.setProvince(rs.getString("Province"));
            mohUser.setDistrict(rs.getString("District"));
            mohUser.setTown(rs.getString("Town"));
            mohUser.setStreet(rs.getString("Street"));

        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
            ex.printStackTrace();
            return;
        }

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

    /*ACTION PERFORMED*/
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == BackButton)
        {
            orgInformationView = null;
            createOrgAccView = null;
            mohStatisticView = null;
            MainLayeredPane.removeAll();
            MainLayeredPane.add(MainPanel, Integer.valueOf(0));
            MainLayeredPane.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
        }

        if (e.getSource() == LogoutButton)
        {
            String query = "update ACCOUNT ACC set Status = 1 where ACC.Username = '" + mohUser.getID() + "'";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                PreparedStatement st = connection.prepareStatement(query);

                st.executeUpdate(query);
            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

            loginView = new LoginView();
            this.dispose();
        }

        if (e.getSource() == InfoSettingButton)
        {
            orgInformationView = new OrgInformationView(mohUser);
            MainLayeredPane.removeAll();
            MainLayeredPane.add(orgInformationView, Integer.valueOf(0));

            MainLayeredPane.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());

            //init BackButton
            initBackInfoButton();
            MainLayeredPane.add(BackInfoButton, Integer.valueOf(5));

        }

        if(e.getSource() == BackInfoButton)
        {
            String query = "select * from ORGANIZATION ORG where ORG.ID = '" +  mohUser.getID() + "'";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                PreparedStatement st = connection.prepareStatement(query);

                ResultSet rs = st.executeQuery(query);

                rs.next();
                mohUser.setID(rs.getString("ID"));
                mohUser.setName(rs.getString("Name"));
                mohUser.setProvince(rs.getString("Province"));
                mohUser.setDistrict(rs.getString("District"));
                mohUser.setTown(rs.getString("Town"));
                mohUser.setStreet(rs.getString("Street"));

            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

            orgInformationView = null;
            MainLayeredPane.removeAll();
            initInfoLayeredPane();
            MainPanel.removeAll();
            MainPanel.add(InfoLayeredPane);
            MainPanel.add(FeatureLayeredPane);
            MainLayeredPane.add(MainPanel);
        }

        if (e.getSource() == CreateOrgAccButton)
        {
            orgInformationView = null;
            searchCitizenView = null;
            mohStatisticView = null;

            createOrgAccView = new CreateOrgAccView();
            MainLayeredPane.removeAll();
            MainLayeredPane.add(createOrgAccView, Integer.valueOf(0));
            MainLayeredPane.add(BackButton, Integer.valueOf(1));
            MainLayeredPane.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
        }

        if (e.getSource() == SearchButton)
        {
            orgInformationView = null;
            createOrgAccView = null;
            mohStatisticView = null;

            searchCitizenView = new SearchCitizenView();
            MainLayeredPane.removeAll();
            MainLayeredPane.add(searchCitizenView, Integer.valueOf(1));

            initBackButton();
            MainLayeredPane.add(BackButton, Integer.valueOf(5));
            MainLayeredPane.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
        }

        if (e.getSource() == StatisticButton)
        {
            orgInformationView = null;
            createOrgAccView = null;
            searchCitizenView = null;

            mohStatisticView = new MOHStatisticView(mohUser);
            MainLayeredPane.removeAll();
            MainLayeredPane.add(mohStatisticView, Integer.valueOf(0));

            initBackButton();
            MainLayeredPane.add(BackButton, Integer.valueOf(1));
            MainLayeredPane.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
        }
    }

}