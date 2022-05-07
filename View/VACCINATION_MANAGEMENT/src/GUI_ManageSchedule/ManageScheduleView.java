package GUI_ManageSchedule;

import Data_Processor.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author NghiepCuong
 */
public class ManageScheduleView extends JPanel implements ActionListener
{
    private DefaultValue dv = new DefaultValue();
    private Organization orgUser = new Organization();

    /*Schedule List*/
    private JPanel SchedFilterPanel;
    private JLabel SchedFilterLabel;
    private Choice SchedFilterChoice;
    private JButton SchedFilterButton;

    private JScrollPane ScrollPaneSchedList;
    private JPanel SchedListPanel;
    private JPanel SchedPanel[] = new JPanel[50];

    /*Registion List*/
    private JPanel RegFilterPanel;
    private JLabel RegFilterLabel;
    private Choice RegFilterChoice;
    private JButton RegFilterButton;

    private JScrollPane ScrollPaneRegList;
    private JPanel RegListPanel;
    private JPanel RegPanel[] = new JPanel[3000];

    private JLayeredPane LayeredPaneArea;

    public Organization getOrgUser() {
        return orgUser;
    }

    public void setOrgUser(Organization orgUser) {
        this.orgUser = orgUser;
    }

    private void initSchedFilterLabel()
    {
        SchedFilterLabel = new JLabel();
        SchedFilterLabel.setBounds(0, 0, dv.LabelWidth()+50, dv.LabelHeight());
        SchedFilterLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        SchedFilterLabel.setForeground(new Color(0x666666));
        SchedFilterLabel.setText("Bộ lọc lịch tiêm:");
        SchedFilterLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initSchedFilterChoice()
    {
        SchedFilterChoice = new Choice();
        SchedFilterChoice.setBounds(0, 40, dv.FieldWidth(), dv.FieldHeight());
        SchedFilterChoice.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        SchedFilterChoice.setForeground(new Color(dv.BlackTextColor()));
        SchedFilterChoice.setBackground(Color.WHITE);

        SchedFilterChoice.add("Tất cả");
        SchedFilterChoice.add("Đã lên lịch");
        SchedFilterChoice.add("Đã thực hiện");
    }

    private void initSchedFilterButton()
    {
        SchedFilterButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Magnifying Glass Button_1.png"));
        SchedFilterButton.setIcon(SearchIcon);

        SchedFilterButton.setBounds(0, 110, dv.FieldWidth(), SearchIcon.getIconHeight());
        SchedFilterButton.setBorder(null);
        SchedFilterButton.setContentAreaFilled(false);

        SchedFilterButton.addActionListener(this);
    }

    private void initSchedFilterPanel()
    {
        initSchedFilterLabel();
        initSchedFilterChoice();
        initSchedFilterButton();

        SchedFilterPanel = new JPanel();
        SchedFilterPanel.setBounds(dv.AlignLeft(), 40, dv.LabelWidth()+50, 110 + 56);
        SchedFilterPanel.setLayout(null);
        SchedFilterPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        SchedFilterPanel.add(SchedFilterLabel);
        SchedFilterPanel.add(SchedFilterChoice);
        SchedFilterPanel.add(SchedFilterButton);
    }

    private void initSchedPanel(int i, Schedule Sched)
    {
        MouseListener handleMouseAction = new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                LayeredPaneArea.removeAll();
                SchedListPanel = null;
                ScrollPaneSchedList = null;
                LayeredPaneArea.repaint(320, 40, 680, 630);

                initScrollPaneRegList(Sched);

//                LayeredPaneArea.add(ScrollPaneSchedList, Integer.valueOf(0));
//                LayeredPaneArea.repaint(320, 40, 680, 630);

                LayeredPaneArea.add(ScrollPaneRegList, Integer.valueOf(1));
                LayeredPaneArea.repaint(320, 40, 680, 630);

                initRegFilterPanel();;
                getParent().add(RegFilterPanel);
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

        //OnDate, VaccineID, Serial, LimitDay, LimitNoon,LimitNight, DayRegistered, NoonRegistered, NightRegistered
        JLabel OrgName = new JLabel("Tên đơn vị: " + orgUser.getName());
        OrgName.setFont(new Font("SVN-Arial", 3, 18));
        OrgName.setForeground(new Color(dv.FeatureButtonColor()));
        OrgName.setBounds(30,1,605,30);
        OrgName.setHorizontalAlignment(JLabel.LEFT);

        JLabel OnDateVaccine = new JLabel("Lịch tiêm ngày: " + Sched.getOnDate().substring(0, 10)
                + "          Vaccine: " + Sched.getVaccineID() + " - " + Sched.getSerial());
        OnDateVaccine.setFont(new Font("SVN-Arial", 1, 16));
        OnDateVaccine.setForeground(new Color(dv.BlackTextColor()));
        OnDateVaccine.setBounds(30,32,600,25);
        OnDateVaccine.setHorizontalAlignment(JLabel.LEFT);

        JLabel Time = new JLabel("Buổi sáng: " + Sched.getDayRegistered() + "/" + Sched.getLimitDay()
                + "          Buổi trưa: " + Sched.getNoonRegistered() + "/" + Sched.getLimitNoon()
                + "          Buổi tối: " + Sched.getNightRegistered() + "/" + Sched.getLimitNight());
        Time.setFont(new Font("SVN-Arial", 0, 16));
        Time.setForeground(new Color(dv.BlackTextColor()));
        Time.setBounds(30, 32+25+2,600,25);
        Time.setHorizontalAlignment(JLabel.LEFT);

        JButton SchedRegistionButton = new JButton();
        SchedRegistionButton.setBounds(30, 32+25*2+2, 150+7, 30+6);
        SchedRegistionButton.setBorder(null);
        SchedRegistionButton.setContentAreaFilled(false);
        SchedRegistionButton.setIcon(new ImageIcon(getClass().getResource("/Data_Processor/icon/Sched Registion Button.png")));
        SchedRegistionButton.addMouseListener(handleMouseAction);

        SchedPanel[i] = new JPanel();
        SchedPanel[i].setLayout(null);
        SchedPanel[i].setPreferredSize(new Dimension(640,120));
        //set Background color
        SchedPanel[i].setBackground(Color.WHITE);

        SchedPanel[i].add(OrgName);
        SchedPanel[i].add(OnDateVaccine);
        SchedPanel[i].add(Time);
        SchedPanel[i].add(SchedRegistionButton);

        LocalDate SchedOnDate = LocalDate.of(dv.getYear(Sched.getOnDate()), dv.getMonth(Sched.getOnDate()), dv.getDay(Sched.getOnDate()));

        LocalDate sysdate = LocalDate.from(java.time.LocalDateTime.now());

        System.out.println(SchedOnDate + " " + sysdate);

        if (SchedOnDate.isAfter(sysdate))
        {
            JButton UpdateSchedButton = new JButton();
            UpdateSchedButton.setBounds(30 + 160 + 30, 32+25*2+2, 150+7, 30+6);
            UpdateSchedButton.setBorder(null);
            UpdateSchedButton.setContentAreaFilled(false);
            UpdateSchedButton.setIcon(new ImageIcon(getClass().getResource("/Data_Processor/icon/Update Sched Button.png")));

            JButton CancelSchedButton = new JButton();
            CancelSchedButton.setBounds( 160 + 30 + 200, 32+25*2+2, 150+7, 30+6);
            CancelSchedButton.setBorder(null);
            CancelSchedButton.setContentAreaFilled(false);
            CancelSchedButton.setIcon(new ImageIcon(getClass().getResource("/Data_Processor/icon/Cancel Sched Button.png")));

            SchedPanel[i].add(UpdateSchedButton);
            SchedPanel[i].add(CancelSchedButton);
        }
    }

    private void initSchedListPanel(int OnDateFilter)
    {
        Schedule Scheds[] = new Schedule[1000];
        String query = "";

        int nScheds = 0;
        int i = 0;

        query = "select ID, OnDate, VaccineID, Serial, LimitDay, LimitNoon,LimitNight, DayRegistered, NoonRegistered, NightRegistered" +
                " from SCHEDULE SCHED" +
                " where SCHED.OrgID = '" + orgUser.getID() + "'";

        if (OnDateFilter == 1) //select choice: Da len lich
            query += " and OnDate >= SYSDATE";
        if (OnDateFilter == 2) //select choice: Da thuc hien
            query += " and OnDate < SYSDATE";

        query += " order by OnDate desc";

        System.out.println(query);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                Scheds[i] = new Schedule();
                Scheds[i].setID(rs.getString("ID"));
                Scheds[i].setOnDate(rs.getString("OnDate"));
                Scheds[i].setVaccineID(rs.getString("VaccineID"));
                Scheds[i].setSerial(rs.getString("Serial"));
                Scheds[i].setLimitDay(rs.getInt("LimitDay"));
                Scheds[i].setLimitNoon(rs.getInt("LimitNoon"));
                Scheds[i].setLimitNight(rs.getInt("LimitNight"));
                Scheds[i].setDayRegistered(rs.getInt("DayRegistered"));
                Scheds[i].setNoonRegistered(rs.getInt("NoonRegistered"));
                Scheds[i].setNightRegistered(rs.getInt("NightRegistered"));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        nScheds = i;

        SchedListPanel = new JPanel();

        SchedListPanel.setPreferredSize(new Dimension(660, 120*nScheds+nScheds*10));

        SchedListPanel.setLayout(new FlowLayout());

        for (i = 0; i<nScheds; i++)
        {
            initSchedPanel(i, Scheds[i]);
            SchedListPanel.add(SchedPanel[i]);
        }
    }

    private void initScrollPaneSchedList(int OnDateFilter)
    {
        initSchedListPanel(OnDateFilter);

        ScrollPaneSchedList = new JScrollPane(SchedListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //set Bounds
        ScrollPaneSchedList.setBounds(0, 0, 680, 630); //320 40
    }




    private void initRegFilterLabel()
    {
        RegFilterLabel = new JLabel();
        RegFilterLabel.setBounds(0, 0, dv.LabelWidth()+50, dv.LabelHeight());
        RegFilterLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        RegFilterLabel.setForeground(new Color(0x666666));
        RegFilterLabel.setText("Bộ lọc lịch tiêm:");
        RegFilterLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initRegFilterChoice()
    {
        RegFilterChoice = new Choice();
        RegFilterChoice.setBounds(0, 40, dv.FieldWidth(), dv.FieldHeight());
        RegFilterChoice.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        RegFilterChoice.setForeground(new Color(dv.BlackTextColor()));
        RegFilterChoice.setBackground(Color.WHITE);

        RegFilterChoice.add("Tất cả");
        RegFilterChoice.add("Đã lên lịch");
        RegFilterChoice.add("Đã thực hiện");
    }

    private void initRegFilterButton()
    {
        RegFilterButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Magnifying Glass Button_1.png"));
        RegFilterButton.setIcon(SearchIcon);

        RegFilterButton.setBounds(0, 110, dv.FieldWidth(), SearchIcon.getIconHeight());
        RegFilterButton.setBorder(null);
        RegFilterButton.setContentAreaFilled(false);

        RegFilterButton.addActionListener(this);
    }

    private void initRegFilterPanel()
    {
        initRegFilterLabel();
        initRegFilterChoice();
        initRegFilterButton();

        RegFilterPanel = new JPanel();
        RegFilterPanel.setBounds(dv.AlignLeft(), 300, dv.LabelWidth()+50, 110 + 56);
        RegFilterPanel.setLayout(null);
        RegFilterPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        RegFilterPanel.add(RegFilterLabel);
        RegFilterPanel.add(RegFilterChoice);
        RegFilterPanel.add(RegFilterButton);
    }

    private void initRegPanel(int i, RegisteredScheds Reg)
    {
        //PersonalID, LastName, FirstName, Birthday, Gender, Phone, Time, NO, DoseType, Status, Image
        JLabel CitizenName = new JLabel("Đối tượng: " + Reg.getCitizen().getFullName() + " - "
                + dv.getGenderName(Reg.getCitizen().getGender())  + " - " + dv.getYear(Reg.getCitizen().getBirthday())
                + " (ID: *" + Reg.getCitizen().getID().substring(Reg.getCitizen().getID().length() - 4, Reg.getCitizen().getID().length()) + ")");
        CitizenName.setFont(new Font("SVN-Arial", 3, 18));
        CitizenName.setForeground(new Color(dv.FeatureButtonColor()));
        CitizenName.setBounds(30,1,605,30);
        CitizenName.setHorizontalAlignment(JLabel.LEFT);

        JLabel Phone = new JLabel("SĐT: " + Reg.getCitizen().getPhone());
        Phone.setFont(new Font("SVN-Arial", 1, 16));
        Phone.setForeground(new Color(dv.BlackTextColor()));
        Phone.setBounds(30,32,600,25);
        Phone.setHorizontalAlignment(JLabel.LEFT);

        JLabel OnDateVaccine = new JLabel("Lịch tiêm ngày: " + Reg.getSched().getOnDate().substring(0, 10)
                + "          Vaccine: " + Reg.getSched().getVaccineID() + " - " + Reg.getSched().getSerial());
        OnDateVaccine.setFont(new Font("SVN-Arial", 1, 16));
        OnDateVaccine.setForeground(new Color(dv.BlackTextColor()));
        OnDateVaccine.setBounds(30,32+25+2,600,25);
        OnDateVaccine.setHorizontalAlignment(JLabel.LEFT);

        JLabel TimeNOStatus = new JLabel("Buổi: " + dv.getTimeName(Reg.getTime())
                + "          STT: " + Reg.getNO() + "          Tình trạng: " + dv.getStatusName(Reg.getStatus()));
        TimeNOStatus.setFont(new Font("SVN-Arial", 0, 16));
        TimeNOStatus.setForeground(new Color(dv.BlackTextColor()));
        TimeNOStatus.setBounds(30, 32+25*2+2,600,25);
        TimeNOStatus.setHorizontalAlignment(JLabel.LEFT);

        RegPanel[i] = new JPanel();
        RegPanel[i].setLayout(null);
        RegPanel[i].setPreferredSize(new Dimension(640,120));
        RegPanel[i].setBackground(Color.WHITE);

        RegPanel[i].add(CitizenName);
        RegPanel[i].add(Phone);
        RegPanel[i].add(OnDateVaccine);
        RegPanel[i].add(TimeNOStatus);
    }

    private void initRegListPanel(Schedule Sched)
    {
        RegisteredScheds Reg[] = new RegisteredScheds[1000];
        String query = "";

        int nReg = 0;
        int i = 0;

        query = "select PersonalID, LastName, FirstName, Birthday, Gender, Phone, Time, NO, DoseType, Status, Image" +
                " from REGISTER REG, PERSON" +
                " where REG.PersonalID = PERSON.ID" +
                " and REG.SchedID = '" +  Sched.getID() + "'" +
                " order by Time, NO";

        System.out.println(query);

        Connection connection = null;
        try {

            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                //PersonalID, LastName, FirstName, Birthday, Gender, Phone, Time, NO, DoseType, Status, Image
                Reg[i] = new RegisteredScheds();
                Reg[i].setSched(Sched);
                Reg[i].getCitizen().setID(rs.getString("PersonalID"));
                Reg[i].getCitizen().setLastName(rs.getString("LastName"));
                Reg[i].getCitizen().setFirstName(rs.getString("FirstName"));
                Reg[i].getCitizen().setBirthday(rs.getString("Birthday"));
                Reg[i].getCitizen().setGender(rs.getInt("Gender"));
                Reg[i].getCitizen().setPhone(rs.getString("Phone"));
                Reg[i].setTime(rs.getInt("Time"));
                Reg[i].setNO(rs.getInt("NO"));
                Reg[i].setDoseType(rs.getString("DoseType"));
                Reg[i].setStatus(rs.getInt("Status"));
                Reg[i].setImage(rs.getByte("Image"));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        nReg = i;

        RegListPanel = new JPanel();

        RegListPanel.setPreferredSize(new Dimension(660, 150*nReg+nReg*10));

        RegListPanel.setLayout(new FlowLayout());

        for (i = 0; i<nReg; i++)
        {
            initRegPanel(i, Reg[i]);
            RegListPanel.add(RegPanel[i]);
        }
    }

    private void initScrollPaneRegList(Schedule Sched)
    {
        initRegListPanel(Sched);

        ScrollPaneRegList = new JScrollPane(RegListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //set Bounds
        ScrollPaneRegList.setBounds(0, 0, 680, 630); //320 40
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

        //initSchedFilterPanel
        initSchedFilterPanel();
        this.add(SchedFilterPanel);

        //init RegSchedList
        initSchedListPanel(0);

        //init LayeredPaneArea
        initScrollPaneSchedList(0);
        initLayeredPaneArea();
        LayeredPaneArea.add(ScrollPaneSchedList);
        this.add(LayeredPaneArea);

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());
    }

    public ManageScheduleView(Organization org)
    {
        orgUser = org;
        initFrameComponent();
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == SchedFilterButton)
        {
            LayeredPaneArea.removeAll();

            initScrollPaneSchedList(SchedFilterChoice.getSelectedIndex());

            LayeredPaneArea.add(ScrollPaneSchedList, Integer.valueOf(0));
            LayeredPaneArea.repaint(320, 40, 680, 630);

            RegListPanel = null;
            ScrollPaneRegList = null;
        }

    }

}
