package GUI_ManageVaccination;

import Data_Processor.DefaultValue;
import Data_Processor.Person;
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
    private Person personalUser;

    private JLabel RegSchedFilterLabel;
    private Choice RegSchedFilterChoice;
    private JButton FilterButton;

    private JScrollPane ScrollPaneOrgList;
    private JPanel OrgListPanel;
    private JPanel OrgPanel[] = new JPanel[100000];

    private JFrame OrgDetailView;
    private JScrollPane ScrollPaneRegSchedList;
    private JPanel RegSchedListPanel;
    private JPanel SchedPanel[] = new JPanel[50];

    private JLayeredPane LayeredPaneArea;

    public Person getPersonalUser() {
        return personalUser;
    }

    public void setPersonalUser(Person personalUser) {
        this.personalUser = personalUser;
    }

    private void initRegSchedFilterLabel()
    {
        RegSchedFilterLabel = new JLabel();
        RegSchedFilterLabel.setBounds(dv.AlignLeft(), 40, dv.LabelWidth()+50, dv.LabelHeight());
        RegSchedFilterLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));
        RegSchedFilterLabel.setForeground(new Color(0x666666));
        RegSchedFilterLabel.setText("Bộ lọc:");
        RegSchedFilterLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initRegSchedFilterChoice()
    {
        RegSchedFilterChoice = new Choice();
        RegSchedFilterChoice.setBounds(dv.AlignLeft(), 80, dv.FieldWidth(), dv.FieldHeight());
        RegSchedFilterChoice.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));
        RegSchedFilterChoice.setForeground(new Color(dv.BlackTextColor()));
        RegSchedFilterChoice.setBackground(Color.WHITE);

        RegSchedFilterChoice.add("Tất cả");
        RegSchedFilterChoice.add("Đã đăng ký");
        RegSchedFilterChoice.add("Đã điểm danh");
        RegSchedFilterChoice.add("Đã tiêm");
        RegSchedFilterChoice.add("Đã hủy");
    }

    private void initFilterButton()
    {
        FilterButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Magnifying Glass Button_1.png"));
        FilterButton.setIcon(SearchIcon);

        FilterButton.setBounds(dv.AlignLeft(), 150, dv.FieldWidth(), SearchIcon.getIconHeight());
        FilterButton.setBorder(null);
        FilterButton.setContentAreaFilled(false);

        FilterButton.addActionListener(this);
    }

    private void initRegSchedPanel(int i, RegisteredScheds reg_Sched)
    {
        //Org info
        JLabel OrgName = new JLabel("Tên đơn vị: " + reg_Sched.getOrg().getName());
        OrgName.setFont(new Font("SVN-Arial", 3, 18));
        OrgName.setForeground(new Color(dv.FeatureButtonColor()));
        OrgName.setBounds(30,1,605,30);
        OrgName.setHorizontalAlignment(JLabel.LEFT);
        //OrgName.setBorder(dv.border());

        JLabel Address = new JLabel("Đ/c: " + dv.getProvinceName(reg_Sched.getOrg().getProvince())  + ", "
                + reg_Sched.getOrg().getDistrict() + ", " + reg_Sched.getOrg().getTown() + ", " + reg_Sched.getOrg().getStreet());
        Address.setFont(new Font("SVN-Arial", 2, 16));
        Address.setForeground(new Color(dv.BlackTextColor()));
        Address.setBounds(30,32,600,25);
        Address.setHorizontalAlignment(JLabel.LEFT);
        //OrgName.setBorder(dv.border());

        JLabel OnDateTime = new JLabel("Lịch tiêm ngày: " + reg_Sched.getSched().getOnDate()
                + "          Buổi: " + dv.getTimeName(reg_Sched.getTime())  + "          STT: " + reg_Sched.getNO());
        OnDateTime.setFont(new Font("SVN-Arial", 1, 16));
        OnDateTime.setForeground(new Color(dv.BlackTextColor()));
        OnDateTime.setBounds(30,32+25+2,600,25);
        OnDateTime.setHorizontalAlignment(JLabel.LEFT);
        //OnDate.setBorder(dv.border());

        JLabel Vaccine = new JLabel("Vaccine: " + reg_Sched.getSched().getVaccineID() + " - " + reg_Sched.getSched().getSerial());
        Vaccine.setFont(new Font("SVN-Arial", 0, 16));
        Vaccine.setForeground(new Color(dv.BlackTextColor()));
        Vaccine.setBounds(30, 32+25*2+2,250,25);
        Vaccine.setHorizontalAlignment(JLabel.LEFT);
        //VaccineID.setBorder(dv.border());

        JLabel Status = new JLabel("Tình trạng: " + dv.getStatusName(reg_Sched.getStatus()));
        Status.setFont(new Font("SVN-Arial", 0, 16));
        Status.setForeground(new Color(dv.BlackTextColor()));
        Status.setBounds(270, 32+25*2+2,250,25);
        Status.setHorizontalAlignment(JLabel.LEFT);
        //VaccineID.setBorder(dv.border());

        SchedPanel[i] = new JPanel();

        SchedPanel[i].setLayout(null);
        SchedPanel[i].setPreferredSize(new Dimension(660,120));
        //set Background color
        SchedPanel[i].setBackground(Color.WHITE);

        SchedPanel[i].add(OrgName);
        SchedPanel[i].add(Address);
        SchedPanel[i].add(OnDateTime);
        SchedPanel[i].add(Vaccine);
        SchedPanel[i].add(Status);
    }

    private void initRegSchedListPanel(int StatusFilter)
    {
        RegisteredScheds reg_Scheds[] = new RegisteredScheds[1000];
        String query = "";

        int nRegScheds = 0;
        int i = 0;

        query = "select DoseType, Time, NO, Status, Image, OnDate, VaccineID, Serial, Name, Province, District, Town, Street" +
                " from REGISTER REG, SCHEDULE SCHED, ORGANIZATION ORG" +
                " where '" + personalUser.getID() + "' = REG.PersonalID" +
                " and REG.SchedID = SCHED.ID" +
                " and SCHED.OrgID = ORG.ID";

        if (StatusFilter == 1) //select choice: Da dang ky
            query += " and Status = 0";
        if (StatusFilter == 2) //select choice: Da diem danh
            query += " and Status = 1";
        if (StatusFilter == 3) //select choice: Da tiem
            query += " and Status = 2";
        if (StatusFilter == 4) //select choice: Da huy
            query += " and Status = 3";

        query += " order by Status asc, OnDate desc";

        System.out.println(query);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                reg_Scheds[i] = new RegisteredScheds();
                reg_Scheds[i].setDoseType(rs.getString("DoseType"));
                reg_Scheds[i].setTime(rs.getInt("Time"));
                reg_Scheds[i].setNO(rs.getInt("NO"));
                reg_Scheds[i].setStatus(rs.getInt("Status"));
                reg_Scheds[i].setImage(rs.getByte("Image"));
                reg_Scheds[i].getSched().setOnDate(rs.getString("OnDate").substring(0,10));
                reg_Scheds[i].getSched().setVaccineID(rs.getString("VaccineID"));
                reg_Scheds[i].getSched().setSerial(rs.getString("Serial"));
                reg_Scheds[i].getOrg().setName(rs.getString("Name"));
                reg_Scheds[i].getOrg().setProvince(rs.getString("Province"));
                reg_Scheds[i].getOrg().setDistrict(rs.getString("District"));
                reg_Scheds[i].getOrg().setTown(rs.getString("Town"));
                reg_Scheds[i].getOrg().setStreet(rs.getString("Street"));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        nRegScheds = i;

        RegSchedListPanel = new JPanel();

        RegSchedListPanel.setPreferredSize(new Dimension(640, 120*nRegScheds +nRegScheds*10));

        RegSchedListPanel.setLayout(new FlowLayout());

        for (i = 0; i<nRegScheds; i++)
        {
            initRegSchedPanel(i, reg_Scheds[i]);
            RegSchedListPanel.add(SchedPanel[i]);
        }
    }

    private void initScrollPaneRegSchedList()
    {
        ScrollPaneRegSchedList = new JScrollPane(RegSchedListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //set Bounds
        ScrollPaneRegSchedList.setBounds(0, 0, 680, 630); //320 40
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

        //init RegSchedFilterLabel
        initRegSchedFilterLabel();
        this.add(RegSchedFilterLabel);

        //init RegSchedFilterChoice
        initRegSchedFilterChoice();
        this.add(RegSchedFilterChoice);

        //init FilterButton
        initFilterButton();
        this.add(FilterButton);

        //init RegSchedList
        initRegSchedListPanel(0);

        //init LayeredPaneArea
        initScrollPaneRegSchedList();
        initLayeredPaneArea();
        LayeredPaneArea.add(ScrollPaneRegSchedList);
        this.add(LayeredPaneArea);

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());
    }

    public ManageVaccinationView(Person person)
    {
        personalUser = person;
        initFrameComponent();
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == FilterButton)
        {
            LayeredPaneArea.removeAll();

            initRegSchedListPanel(RegSchedFilterChoice.getSelectedIndex());
            initScrollPaneRegSchedList();

            LayeredPaneArea.add(ScrollPaneRegSchedList);
            LayeredPaneArea.repaint(320, 40, 680, 630);
        }

    }

}
