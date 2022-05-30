package View.OrgView;

import Process.*;
import View.CitizenView.RegisterVaccinationView;
import View.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ORGMainView extends JFrame implements ActionListener
{
    private JLayeredPane MainLayeredPane;

    private JPanel MainPanel;

    private DefaultValue dv = new DefaultValue();
    private JLayeredPane InfoLayeredPane;
    private JLabel InfoBackground;
    private JLabel NameLabel;
    private JLabel LocationLabel;

    private JLayeredPane FeatureLayeredPane;
    private JButton InfoSettingButton;
    private JButton ManageSchedButton;
    private JButton PublishPostButton;
    private JButton SearchButton;
    private JButton StatisticButton;



    private Organization orgUser = new Organization();

    private LoginView loginView;
    private OrgInformationView orgInformationView;
    private RegisterVaccinationView registerVaccinationView;
    private ManageScheduleView manageScheduleView;

    private JButton LogoutButton;
    private JButton BackButton;
    private JButton BackInfoButton;

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

        InfoLayeredPane.setOpaque(true);

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

        JLabel Name = new JLabel("<html>" + orgUser.getName());
        Name.setBounds(50, 300, 280, 150);
        Name.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        Name.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon LocationImage = new ImageIcon(getClass().getResource("/Resources/icon/Location.png"));
        JLabel Location = new JLabel(orgUser.getProvince());
        Location.setFont(new Font(dv.fontName(),Font.BOLD, 20));
        Location.setIcon(LocationImage);
        Location.setBounds(0,450,360,30);
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

        initManageSchedButton();
        FeatureLayeredPane.add(ManageSchedButton);

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

    private void initManageSchedButton()
    {
        ManageSchedButton = new JButton();
        ManageSchedButton.setBounds(240 + 30+15, 30, 133, 133);
        ManageSchedButton.setBorder(null);
        ManageSchedButton.setContentAreaFilled(false);
        ManageSchedButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Manage Schedule Feature Button.png")));
        ManageSchedButton.addActionListener(this);

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(240, 160, 240, 30);
        ButtonLabel.setText("Quản lý");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(240, 160 +25, 240, 30);
        ButtonLabel2.setText("lịch tiêm chủng");
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
        PublishPostButton.addActionListener(this);

        JLabel ButtonLabel = new JLabel();
        ButtonLabel.setBounds(240*2 -15, 160, 240-20, 30);
        ButtonLabel.setText("Gửi thông báo");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(240*2 -15, 160 +25, 240-20, 30);
        ButtonLabel2.setText("địa phương");
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
        ButtonLabel.setText("Tìm kiếm");
        ButtonLabel.setFont(new Font(dv.fontName(), 1, 20));
        ButtonLabel.setForeground(new Color(dv.FieldLabelColor()));
        ButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        //ButtonLabel.setBorder(dv.border());

        JLabel ButtonLabel2 = new JLabel();
        ButtonLabel2.setBounds(0, 240+130 +25, 240, 30);
        ButtonLabel2.setText("thông tin");
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

    public ORGMainView(String Username)
    {
        this.setTitle("Quản lý tiêm chủng vaccine Covid-19: Đơn vị tiêm chủng");
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
            orgUser.setID(rs.getString("ID"));
            orgUser.setName(rs.getString("Name"));
            orgUser.setProvince(rs.getString("ProvinceName"));
            orgUser.setDistrict(rs.getString("DistrictName"));
            orgUser.setTown(rs.getString("TownName"));
            orgUser.setStreet(rs.getString("Street"));

        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
            ex.printStackTrace();
            return;
        }

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
            registerVaccinationView = null;
            manageScheduleView = null;

            MainLayeredPane.removeAll();
            MainLayeredPane.add(MainPanel, Integer.valueOf(1));
            MainLayeredPane.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
        }

        if (e.getSource() == LogoutButton)
        {
            String query = "update ACCOUNT ACC set Status = 1 where ACC.Username = '" + orgUser.getID() + "'";

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
            orgInformationView = new OrgInformationView(orgUser);
            MainLayeredPane.removeAll();
            MainLayeredPane.add(orgInformationView, Integer.valueOf(0));

            MainLayeredPane.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());

            //init BackButton
            initBackInfoButton();
            MainLayeredPane.add(BackInfoButton, Integer.valueOf(5));
        }

        if(e.getSource() == BackInfoButton)
        {
            String query = "select * from ORGANIZATION ORG where ORG.ID = '" +  orgUser.getID() + "'";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                PreparedStatement st = connection.prepareStatement(query);

                ResultSet rs = st.executeQuery(query);

                rs.next();
                orgUser.setID(rs.getString("ID"));
                orgUser.setName(rs.getString("Name"));
                orgUser.setProvince(rs.getString("ProvinceName"));
                orgUser.setDistrict(rs.getString("DistrictName"));
                orgUser.setTown(rs.getString("TownName"));
                orgUser.setStreet(rs.getString("Street"));

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

        /*if (e.getSource() == SearchButton)
        {
            this.setTitle("Tìm kiếm");
            searchOrgView = new SearchOrgView();
            MainLayeredPane.removeAll();
            MainLayeredPane.add(searchOrgView, Integer.valueOf(1));
            MainLayeredPane.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());

            //init BackButton
            initBackButton();
            MainLayeredPane.add(BackButton, Integer.valueOf(5));
        }*/

        if (e.getSource() == ManageSchedButton)
        {
            manageScheduleView = new ManageScheduleView(orgUser);
            MainLayeredPane.removeAll();
            MainLayeredPane.add(manageScheduleView, Integer.valueOf(0));

            MainLayeredPane.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());

            //init BackButton
            initBackButton();
            MainLayeredPane.add(BackButton, Integer.valueOf(5));
        }
    }

}