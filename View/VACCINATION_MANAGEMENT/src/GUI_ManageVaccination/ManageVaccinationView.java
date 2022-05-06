package GUI_ManageVaccination;

import Data_Processor.DefaultValue;
import Data_Processor.RegisteredScheds;
import Data_Processor.Schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

/**
 *
 * @author NghiepCuong
 */
public class ManageVaccinationView extends JPanel implements ActionListener
{
    private DefaultValue dv = new DefaultValue();
    private RegisteredScheds reg_Scheds[] = new RegisteredScheds[100000];

    private JLabel ProvinceLabel;
    private JLabel DistrictLabel;
    private JLabel TownLabel;
    private Choice DistrictChoice;
    private Choice TownChoice;
    private Choice ProvinceChoice;
    private JButton SearchOrgButton;

    private JScrollPane ScrollPaneOrgList;
    private JPanel OrgListPanel;
    private JPanel OrgPanel[] = new JPanel[100000];

    private JFrame OrgDetailView;
    private JScrollPane ScrollPaneSchedList;
    private JPanel SchedListPanel;
    private JPanel SchedPanel[] = new JPanel[50];

    private JLayeredPane LayeredPaneArea;



    private void initProvinceLabel()
    {
        ProvinceLabel = new JLabel();
        ProvinceLabel.setBounds(dv.AlignLeft(), 40, dv.LabelWidth(), dv.LabelHeight());
        ProvinceLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        ProvinceLabel.setForeground(new Color(0x666666));
        ProvinceLabel.setText("Tỉnh/thành phố:");
        ProvinceLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initProvinceChoice()
    {
        ProvinceChoice = new Choice();
        ProvinceChoice.setBounds(dv.AlignLeft(), 80, dv.FieldWidth(), dv.FieldHeight());
        ProvinceChoice.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        ProvinceChoice.setForeground(new Color(dv.BlackTextColor()));
        ProvinceChoice.setBackground(Color.WHITE);

        ProvinceChoice.add("*");
        ProvinceChoice.add("Bình Dương");
        ProvinceChoice.add("Hồ Chí Minh");
        ProvinceChoice.add("Hà Nội");
    }

    private void initDistrictLabel()
    {
        DistrictLabel = new JLabel();
        DistrictLabel.setBounds(dv.AlignLeft(), 120, dv.LabelWidth(), dv.LabelHeight());
        DistrictLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        DistrictLabel.setForeground(new Color(0x666666));
        DistrictLabel.setText("Quận/Huyện:");
        DistrictLabel.setSize(dv.LabelWidth(), dv.LabelHeight());
    }

    private void initDistrictChoice()
    {
        DistrictChoice = new Choice();
        DistrictChoice.setBounds(dv.AlignLeft(), 150, dv.FieldWidth(), dv.FieldHeight());
        DistrictChoice.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        DistrictChoice.setForeground(new Color(0x333333));
        DistrictChoice.setBackground(Color.WHITE);

        //set choice
        DistrictChoice.add("*");
        DistrictChoice.add("Dầu Tiếng");
        DistrictChoice.add("Thuận An");
        DistrictChoice.add("Dĩ An");
        DistrictChoice.add("Thủ Đức");
        DistrictChoice.add("Thanh Xuân");
    }

    private void initTownLabel()
    {
        TownLabel = new JLabel();
        TownLabel.setBounds(dv.AlignLeft(), 190, dv.LabelWidth(), dv.LabelHeight());
        TownLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        TownLabel.setForeground(new Color(0x666666));
        TownLabel.setSize(dv.LabelWidth(), dv.LabelHeight());
        TownLabel.setText("Xã/phường/thị trấn:");
    }

    private void initTownChoice()
    {
        TownChoice = new Choice();
        TownChoice.setBounds(dv.AlignLeft(), 220, dv.FieldWidth(), dv.FieldHeight());
        TownChoice.setForeground(new Color(0x333333));
        TownChoice.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        TownChoice.setBackground(Color.WHITE);

        TownChoice.add("*");
        TownChoice.add("Dầu Tiếng");
        TownChoice.add("Lái Thiêu");
        TownChoice.add("Đông Hòa");
        TownChoice.add("Linh Trung");
        TownChoice.add("Trúc Bạch");
    }

    private void initSearchOrgButton()
    {
        SearchOrgButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Search.png"));
        SearchOrgButton.setIcon(SearchIcon);

        SearchOrgButton.setBounds(dv.AlignLeft(), 290, dv.FieldWidth(), SearchIcon.getIconHeight());
        SearchOrgButton.setBorder(null);
        SearchOrgButton.setContentAreaFilled(false);

        SearchOrgButton.addActionListener(this);
    }

    private void initOrgPanel(int i)
    {
        //Org info
        JLabel OrgName = new JLabel("Người đăng ký: " + reg_Scheds[i].getPersonalID());
        OrgName.setFont(new Font("SVN-Arial", 3, 18));
        OrgName.setForeground(new Color(dv.FeatureButtonColor()));
        OrgName.setBounds(30,1,605,30);
        OrgName.setHorizontalAlignment(JLabel.LEFT);
        //OrgName.setBorder(dv.border());

        JLabel OrgProvince = new JLabel("Đơn vị tiêm: " + dv.getProvinceName(reg_Scheds[i].getOrg().getName()));
        OrgProvince.setFont(new Font("SVN-Arial", 0, 16));
        OrgProvince.setForeground(new Color(dv.BlackTextColor()));
        OrgProvince.setBounds(30,32,250,25);
        OrgProvince.setHorizontalAlignment(JLabel.LEFT);
        //OrgProvince.setBorder(dv.border());

        JLabel OrgDistrict = new JLabel("Loại mũi tiêm: " + dv.getDoseTypeName(reg_Scheds[i].getDoseType()));
        OrgDistrict.setFont(new Font("SVN-Arial", 0, 16));
        OrgDistrict.setForeground(new Color(dv.BlackTextColor()));
        OrgDistrict.setBounds(30, 32+25+2,350,25);
        OrgDistrict.setHorizontalAlignment(JLabel.LEFT);
        //OrgDistrict.setBorder(dv.border());

        JLabel OrgTown  = new JLabel("Buổi tiêm: " + dv.getTimeName(reg_Scheds[i].getTime()));
        OrgTown.setFont(new Font("SVN-Arial", 0, 16));
        OrgTown.setForeground(new Color(dv.BlackTextColor()));
        OrgTown.setBounds(30,(32+25+2)+25+2,350,25);
        OrgTown.setHorizontalAlignment(JLabel.LEFT);
        //OrgTown.setBorder(dv.border());

        JLabel OrgStreet  = new JLabel("STT: " + reg_Scheds[i].getNO());
        OrgStreet.setFont(new Font("SVN-Arial", 0, 16));
        OrgStreet.setForeground(new Color(dv.BlackTextColor()));
        OrgStreet.setBounds(285,32,350,25);
        OrgStreet.setHorizontalAlignment(JLabel.LEFT);
        //OrgStreet.setBorder(dv.border());

        JLabel OrgAvaiScheds = new JLabel("Trạng thái: " + reg_Scheds[i].getStatus());
        OrgAvaiScheds.setFont(new Font("SVN-Arial", 0, 16));
        OrgAvaiScheds.setForeground(new Color(dv.BlackTextColor()));
        OrgAvaiScheds.setBounds(385,(32+25)+2,250,25);
        OrgAvaiScheds.setHorizontalAlignment(JLabel.LEFT);
        //OrgAvaiScheds.setBorder(dv.border());

        //create OrgPanel Panel
        OrgPanel[i] = new JPanel();
        //set layout
        OrgPanel[i].setLayout(null);
        OrgPanel[i].setPreferredSize(new Dimension(660,120));
        //set Background color
        OrgPanel[i].setBackground(Color.WHITE);

        OrgPanel[i].add(OrgName);
        OrgPanel[i].add(OrgProvince);
        OrgPanel[i].add(OrgDistrict);
        OrgPanel[i].add(OrgTown);
        OrgPanel[i].add(OrgStreet);
        OrgPanel[i].add(OrgAvaiScheds);
        //OrgPanel[i].add(OrgDetailButton[i]);

        MouseListener handleMouseAction = new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("clicked on " + OrgName.getText());
                initScrollPaneSchedList(org[i]);

                LayeredPaneArea.removeAll();
                LayeredPaneArea.add(ScrollPaneOrgList, Integer.valueOf(0));
                LayeredPaneArea.add(ScrollPaneSchedList, Integer.valueOf(1));
                LayeredPaneArea.repaint(320, 40, 680, 630);
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
        };

        //OrgDetailButton[i].addMouseListener(this);
        OrgPanel[i].addMouseListener(handleMouseAction);
    }

    private void initOrgListPanel(int nORG)
    {
        OrgListPanel = new JPanel();

        OrgListPanel.setPreferredSize(new Dimension(680, 120*nORG));

        OrgListPanel.setLayout((new FlowLayout()));

        for (int i = 0; i < nORG; i++)
        {
            initOrgPanel(i);
            OrgListPanel.add(OrgPanel[i]);
        }
   }

    private void initScrollPaneOrgList(int nORG)
    {
        initOrgListPanel(nORG);

        //create ScrollPaneOrgList Panel
        ScrollPaneOrgList = new JScrollPane(OrgListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //set Bounds
        ScrollPaneOrgList.setBounds(0, 0, 680, 630); //320-40
    }

    private void initSchedPanel(int i, Schedule sched, RegisteredScheds SelectedOrg)
    {
        //Org info
        JLabel OrgName = new JLabel("Tên đơn vị: " + SelectedOrg.getName());
        OrgName.setFont(new Font("SVN-Arial", 3, 18));
        OrgName.setForeground(new Color(dv.FeatureButtonColor()));
        OrgName.setBounds(30,1,605,30);
        OrgName.setHorizontalAlignment(JLabel.LEFT);
        //OrgName.setBorder(dv.border());

        JLabel OnDate = new JLabel("Lịch tiêm ngày: " + sched.getOnDate().toString().substring(0,10));
        OnDate.setFont(new Font("SVN-Arial", 0, 16));
        OnDate.setForeground(new Color(dv.BlackTextColor()));
        OnDate.setBounds(30,32,200,25);
        OnDate.setHorizontalAlignment(JLabel.LEFT);
        //OnDate.setBorder(dv.border());

        JLabel VaccineID = new JLabel("Vaccine: " + sched.getVaccineID());
        VaccineID.setFont(new Font("SVN-Arial", 0, 16));
        VaccineID.setForeground(new Color(dv.BlackTextColor()));
        VaccineID.setBounds(30, 32+25+2,200,25);
        VaccineID.setHorizontalAlignment(JLabel.LEFT);
        //VaccineID.setBorder(dv.border());

        JLabel Serial  = new JLabel("Serial: " + sched.getSerial());
        Serial.setFont(new Font("SVN-Arial", 0, 16));
        Serial.setForeground(new Color(dv.BlackTextColor()));
        Serial.setBounds(30,(32+25+2)+25+2,200,25);
        Serial.setHorizontalAlignment(JLabel.LEFT);
        //Serial.setBorder(dv.border());

        JRadioButton DayTimeButton  = new JRadioButton("Buổi sáng: " + sched.getDayRegistered() + "/" + sched.getLimitDay());
        DayTimeButton.setFont(new Font("SVN-Arial", 0, 16));
        DayTimeButton.setForeground(new Color(dv.BlackTextColor()));
        DayTimeButton.setBounds(300,32,150,25);
        DayTimeButton.setHorizontalAlignment(JLabel.LEFT);
        DayTimeButton.setContentAreaFilled(false);
        //DayTimeButton.setBorder(dv.border());

        JRadioButton NoonTimeButton = new JRadioButton("Buổi trưa: " + sched.getNoonRegistered() + "/" + sched.getLimitNoon());
        NoonTimeButton.setFont(new Font("SVN-Arial", 0, 16));
        NoonTimeButton.setForeground(new Color(dv.BlackTextColor()));
        NoonTimeButton.setBounds(300,(32+25)+2,150,25);
        NoonTimeButton.setHorizontalAlignment(JLabel.LEFT);
        NoonTimeButton.setContentAreaFilled(false);
        //NoonTimeButton.setBorder(dv.border());

        JRadioButton NightTimeButton = new JRadioButton("Buổi trưa: " + sched.getNightRegistered() + "/" + sched.getLimitNight());
        NightTimeButton.setFont(new Font("SVN-Arial", 0, 16));
        NightTimeButton.setForeground(new Color(dv.BlackTextColor()));
        NightTimeButton.setBounds(300,(32+25+2)+25+2,150,25);
        NightTimeButton.setHorizontalAlignment(JLabel.LEFT);
        NightTimeButton.setContentAreaFilled(false);
        //NightTimeButton.setBorder(dv.border());

        ButtonGroup TimeGroupButton = new ButtonGroup();
        TimeGroupButton.add(DayTimeButton);
        TimeGroupButton.add(NoonTimeButton);
        TimeGroupButton.add(NightTimeButton);

        JButton SchedRegisterButton = new JButton();
        SchedRegisterButton.setForeground(new Color(dv.BlackTextColor()));
        SchedRegisterButton.setBounds(470,32+24,120,38);
        SchedRegisterButton.setContentAreaFilled(false);
        SchedRegisterButton.setBorder(null);
        SchedRegisterButton.setIcon(new ImageIcon(getClass().getResource("/Data_Processor/icon/SchedRegister.png")));

        SchedPanel[i] = new JPanel();

        SchedPanel[i].setLayout(null);
        SchedPanel[i].setPreferredSize(new Dimension(660,120));
        //set Background color
        SchedPanel[i].setBackground(Color.WHITE);

        SchedPanel[i].add(OrgName);
        SchedPanel[i].add(OnDate);
        SchedPanel[i].add(VaccineID);
        SchedPanel[i].add(Serial);
        SchedPanel[i].add(DayTimeButton);
        SchedPanel[i].add(NoonTimeButton);
        SchedPanel[i].add(NightTimeButton);
        SchedPanel[i].add(DayTimeButton);
        SchedPanel[i].add(NoonTimeButton);
        SchedPanel[i].add(NightTimeButton);
        SchedPanel[i].add(SchedRegisterButton);
    }

    private void initSchedListPanel(RegisteredScheds SelectedOrg)
    {

        Schedule sched[] = new Schedule[50];
        String query = "";

        int nSched = 0;
        int i = 0;

        query = "select *" +
                " from SCHEDULE SCHED" +
                " where SCHED.OrgID = '" + SelectedOrg.getID() + "'" +
                " and SCHED.OnDate >= TO_DATE('" + dv.sysdate() + "')";

        System.out.println(query);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                sched[i] = new Schedule();
                sched[i].setID(rs.getString("ID"));
                sched[i].setOrgID(rs.getString("OrgID"));
                sched[i].setOnDate(rs.getString("OnDate"));
                sched[i].setVaccineID(rs.getString("VaccineID"));
                sched[i].setSerial(rs.getString("Serial"));
                sched[i].setLimitDay(rs.getInt("LimitDay"));
                sched[i].setLimitNoon(rs.getInt("LimitNoon"));
                sched[i].setLimitNight(rs.getInt("LimitNight"));
                sched[i].setDayRegistered(rs.getInt("DayRegistered"));
                sched[i].setNoonRegistered(rs.getInt("Noonregistered"));
                sched[i].setNightRegistered(rs.getInt("NightRegistered"));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        nSched = i;

        SchedListPanel = new JPanel();

        SchedListPanel.setPreferredSize(new Dimension(680, 120));

        SchedListPanel.setLayout(new FlowLayout());

        for (i = 0; i<nSched; i++)
        {
            initSchedPanel(i, sched[i], SelectedOrg);
            SchedListPanel.add(SchedPanel[i]);
        }



    }

    private void initScrollPaneSchedList(RegisteredScheds SelectedOrg)
    {
        initSchedListPanel(SelectedOrg);

        //create ScrollPaneOrgList Panel
        ScrollPaneSchedList = new JScrollPane(SchedListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //set Bounds
        ScrollPaneSchedList.setBounds(0, 0, 680, 630); //320 40
    }

    private void initLayeredPaneArea()
    {
        LayeredPaneArea = new JLayeredPane();

        LayeredPaneArea.setLayout(null);

        LayeredPaneArea.setBounds(320, 40, 680, 630);

        LayeredPaneArea.repaint(320, 40, 680, 630);
    }

    private void initFrameComponent()
    {
        //set Frame icon
        //this.setIconImage(new ImageIcon(getClass().getResource("/Data_Processor/icon/Virus.png")).getImage());

        //set frame title
        //this.setTitle("Tìm kiếm đơn vị tiêm chủng");

        //set frame size
        this.setSize(dv.FrameWidth(), dv.FrameHeight());
        //this.setSize(1080, 720); --Main View

        //set do not allow frame resizing
        //this.setResizable(false);

        //set frame visible on screen
        this.setVisible(true);

        //set frame close on X button
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set frame background color
        //this.getContentPane().setBackground(new Color(dv.ViewBackgroundColor()));
        this.setBackground(new Color(dv.ViewBackgroundColor()));

        this.setLayout(null);

        //init ProvinceLabel
        initProvinceLabel();
        this.add(ProvinceLabel);

        //init ProvinceChoice
        initProvinceChoice();
        this.add(ProvinceChoice);

        //init DistrictLabel;
        initDistrictLabel();
        this.add(DistrictLabel);

        //init DistrictChoice
        initDistrictChoice();
        this.add(DistrictChoice);

        //init TownLabel;
        initTownLabel();
        this.add(TownLabel);

        //init TownChoice
        initTownChoice();
        this.add(TownChoice);

        //init SearchOrgButton
        initSearchOrgButton();
        this.add(SearchOrgButton);

        //init LayeredPane
        initLayeredPaneArea();
        this.add(LayeredPaneArea);

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());
    }

    public ManageVaccinationView()
    {
        initFrameComponent();
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        int n = 0;

        //Pressed SearchOrgButton
        if (e.getSource() == SearchOrgButton)
        {
            String query = "";

            //Select out the code of chosen province
            String ProvinceCode = "";

            ProvinceCode = dv.getProvinceCode(ProvinceChoice.getSelectedItem());

            //Select out the specified ORGs
            query = "select ORG.ID, Name, Province, District, Town, Street, COUNT(SCHED.ID)"
                    + " from Registered_Scheds ORG left join SCHEDULE SCHED on ORG.ID = SCHED.OrgID";

            if (ProvinceChoice.getSelectedIndex() > 0)
                query = query + " where ORG.Province = '" + ProvinceCode + "'";
            else
                query = query + " where ORG.Province like '%'";

            if (DistrictChoice.getSelectedIndex() > 0)
                query = query + " and ORG.District = '" + DistrictChoice.getSelectedItem() + "'";
            else
                query = query + " and ORG.District like '%'";

            if (TownChoice.getSelectedIndex() > 0)
                query = query + " and ORG.Town = '" + TownChoice.getSelectedItem() + "'";
            else
                query = query + " and ORG.Town like '%'";

            query += " and OnDate >= '" + dv.sysdate() + "'";
            query += " group by ORG.ID, Name, Province, District, Town, Street";
            query += " order by Province, District, Town";

            System.out.println(query);

            try
            {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                //Statement st = connection.createStatement();

                PreparedStatement st = connection.prepareStatement(query);

                ResultSet rs = st.executeQuery(query);

                int i = 0;
                n = 0;

                while (rs.next())
                {
                    org[i] = new RegisteredScheds();
                    org[i].setID(rs.getString(1));
                    org[i].setName(rs.getString("Name"));
                    org[i].setProvince(rs.getString("Province"));
                    org[i].setDistrict(rs.getString("District"));
                    org[i].setTown(rs.getString("Town"));
                    org[i].setStreet(rs.getString("Street"));
                    org[i].setAvaiScheds(rs.getInt("COUNT(SCHED.ID)"));
                    i++;
                }
                n = i;

                //init ScrollPaneOrgList

                //clear the Layered Area
                LayeredPaneArea.removeAll();

                //init Scroll Pane of Orgs
                initScrollPaneOrgList(n);
                //add Scroll Pane of Orgs to TOP of Layered Area
                LayeredPaneArea.add(ScrollPaneOrgList, Integer.valueOf(1));
                //Delete scroll pane of sched
                ScrollPaneSchedList = null;
            }
            catch (SQLException exception)
            {
                exception.printStackTrace();
            }
        }


    }

}
