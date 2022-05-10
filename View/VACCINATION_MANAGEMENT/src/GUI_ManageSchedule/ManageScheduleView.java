package GUI_ManageSchedule;

import Data_Processor.*;
import GUI_RegisterAcc.RegisterAccView;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Properties;

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

    /*Create Schedule*/
    private JLabel AddNewSchedLabel;
    private JButton AddNewSchedButton;
    private JPanel CreateSchedPanel;


    private JLayeredPane LayeredPaneArea;

    public Organization getOrgUser() {
        return orgUser;
    }

    public void setOrgUser(Organization orgUser) {
        this.orgUser = orgUser;
    }
    /*

*/
    private void initSchedFilterLabel()
    {
        SchedFilterLabel = new JLabel();
        SchedFilterLabel.setBounds(0, 0, dv.LabelWidth()+50, dv.LabelHeight());
        SchedFilterLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        SchedFilterLabel.setForeground(new Color(0x666666));
        SchedFilterLabel.setText("Bộ lọc lịch tiêm:");
        SchedFilterLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initSchedFilterChoice()
    {
        SchedFilterChoice = new Choice();
        SchedFilterChoice.setBounds(0, 40, dv.FieldWidth(), dv.FieldHeight());
        SchedFilterChoice.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
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
        ActionListener handleRegistion = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                LayeredPaneArea.removeAll();
                SchedListPanel = null;
                ScrollPaneSchedList = null;

                JLabel RegListLabel = new JLabel("DANH SÁCH CÁC LƯỢT ĐĂNG KÝ:");
                RegListLabel.setBounds(0,0,640,40);
                RegListLabel.setFont(new Font(dv.fontName(), 1, 20));
                RegListLabel.setForeground(new Color(dv.FeatureButtonColor()));
                RegListLabel.setHorizontalAlignment(JLabel.CENTER);

                initScrollPaneRegList(Sched);

                LayeredPaneArea.add(RegListLabel, Integer.valueOf(0));
                LayeredPaneArea.add(ScrollPaneRegList, Integer.valueOf(0));
                LayeredPaneArea.repaint(320, 40, 680, 630);

                initRegFilterPanel();
                getParent().add(RegFilterPanel);
            }
        };


        /*JLabel OrgName = new JLabel("Tên đơn vị: " + orgUser.getName());
        OrgName.setFont(new Font(dv.fontName(), 3, 18));
        OrgName.setForeground(new Color(dv.FeatureButtonColor()));
        OrgName.setBounds(30,1,605,30);
        OrgName.setHorizontalAlignment(JLabel.LEFT);*/

        JLabel OnDateVaccine = new JLabel("Lịch tiêm ngày: " + Sched.getOnDate().substring(0, 10)
                + "          Vaccine: " + Sched.getVaccineID() + " - " + Sched.getSerial());
        OnDateVaccine.setFont(new Font(dv.fontName(), 1, 20));
        OnDateVaccine.setForeground(new Color(dv.BlackTextColor()));
        OnDateVaccine.setBounds(30,3,600,30);
        OnDateVaccine.setHorizontalAlignment(JLabel.LEFT);

        JLabel Time = new JLabel("Buổi sáng: " + Sched.getDayRegistered() + "/" + Sched.getLimitDay()
                + "          Buổi trưa: " + Sched.getNoonRegistered() + "/" + Sched.getLimitNoon()
                + "          Buổi tối: " + Sched.getNightRegistered() + "/" + Sched.getLimitNight());
        Time.setFont(new Font(dv.fontName(), 0, 16));
        Time.setForeground(new Color(dv.BlackTextColor()));
        Time.setBounds(30, 40+2,600,25);
        Time.setHorizontalAlignment(JLabel.LEFT);

        JButton SchedRegistionButton = new JButton();
        SchedRegistionButton.setBounds(30, 80, 150+7, 30+6);
        SchedRegistionButton.setBorder(null);
        SchedRegistionButton.setContentAreaFilled(false);
        SchedRegistionButton.setIcon(new ImageIcon(getClass().getResource("/Data_Processor/icon/Sched Registion Button.png")));
        SchedRegistionButton.addActionListener(handleRegistion);

        SchedPanel[i] = new JPanel();
        SchedPanel[i].setLayout(null);
        SchedPanel[i].setPreferredSize(new Dimension(640,120));
        SchedPanel[i].setBackground(Color.WHITE);

//        SchedPanel[i].add(OrgName);
        SchedPanel[i].add(OnDateVaccine);
        SchedPanel[i].add(Time);
        SchedPanel[i].add(SchedRegistionButton);

        LocalDate SchedOnDate = LocalDate.of(dv.getYear(Sched.getOnDate()), dv.getMonth(Sched.getOnDate()), dv.getDay(Sched.getOnDate()));

        LocalDate sysdate = LocalDate.from(java.time.LocalDateTime.now());

        if (SchedOnDate.isAfter(sysdate))
        {
            ActionListener handleUpdate = new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {

                }
            };
            JButton UpdateSchedButton = new JButton();
            UpdateSchedButton.setBounds(30 + 160 + 30, 80, 150+7, 30+6);
            UpdateSchedButton.setBorder(null);
            UpdateSchedButton.setContentAreaFilled(false);
            UpdateSchedButton.setIcon(new ImageIcon(getClass().getResource("/Data_Processor/icon/Update Sched Button.png")));
            UpdateSchedButton.addActionListener(handleUpdate);

            ActionListener handleCancel = new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {

                }
            };
            JButton CancelSchedButton = new JButton();
            CancelSchedButton.setBounds( 160 + 30 + 200, 80, 150+7, 30+6);
            CancelSchedButton.setBorder(null);
            CancelSchedButton.setContentAreaFilled(false);
            CancelSchedButton.setIcon(new ImageIcon(getClass().getResource("/Data_Processor/icon/Cancel Sched Button.png")));
            CancelSchedButton.addActionListener(handleCancel);

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
        ScrollPaneSchedList.setBounds(0, 40, 680, 590); //320 40
    }





    private void initRegFilterLabel()
    {
        RegFilterLabel = new JLabel();
        RegFilterLabel.setBounds(0, 0, dv.LabelWidth()+50, dv.LabelHeight());
        RegFilterLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        RegFilterLabel.setForeground(new Color(0x666666));
        RegFilterLabel.setText("Bộ lọc lịch tiêm:");
        RegFilterLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initRegFilterChoice()
    {
        RegFilterChoice = new Choice();
        RegFilterChoice.setBounds(0, 40, dv.FieldWidth(), dv.FieldHeight());
        RegFilterChoice.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
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
        CitizenName.setFont(new Font(dv.fontName(), 3, 18));
        CitizenName.setForeground(new Color(dv.FeatureButtonColor()));
        CitizenName.setBounds(30,1,605,30);
        CitizenName.setHorizontalAlignment(JLabel.LEFT);

        JLabel Phone = new JLabel("SĐT: " + Reg.getCitizen().getPhone());
        Phone.setFont(new Font(dv.fontName(), 1, 16));
        Phone.setForeground(new Color(dv.BlackTextColor()));
        Phone.setBounds(30,32,200,25);
        Phone.setHorizontalAlignment(JLabel.LEFT);

        JLabel OnDateVaccine = new JLabel("Lịch tiêm ngày: " + Reg.getSched().getOnDate().substring(0, 10)
                + "  -  Vaccine: " + Reg.getSched().getVaccineID() + " - " + Reg.getSched().getSerial());
        OnDateVaccine.setFont(new Font(dv.fontName(), 1, 16));
        OnDateVaccine.setForeground(new Color(dv.BlackTextColor()));
        OnDateVaccine.setBounds(30,32+25+2,400,25);
        OnDateVaccine.setHorizontalAlignment(JLabel.LEFT);

        JLabel TimeNOStatus = new JLabel("Buổi: " + dv.getTimeName(Reg.getTime())
                + "          STT: " + Reg.getNO() + "          Tình trạng: " + dv.getStatusName(Reg.getStatus()));
        TimeNOStatus.setFont(new Font(dv.fontName(), 0, 16));
        TimeNOStatus.setForeground(new Color(dv.BlackTextColor()));
        TimeNOStatus.setBounds(30, 32+25*2+2,380,25);
        TimeNOStatus.setHorizontalAlignment(JLabel.LEFT);

        RegPanel[i] = new JPanel();
        RegPanel[i].setLayout(null);
        RegPanel[i].setPreferredSize(new Dimension(640,120));
        RegPanel[i].setBackground(Color.WHITE);

        RegPanel[i].add(CitizenName);
        RegPanel[i].add(Phone);
        RegPanel[i].add(OnDateVaccine);
        RegPanel[i].add(TimeNOStatus);

        if (Reg.getStatus() < 2)
        {
            Choice StatusChoice = new Choice();
            StatusChoice.setBounds(500, 32+2, 120, 30);
            StatusChoice.setFont(new Font(dv.fontName(), 0, 16));
            StatusChoice.setForeground(new Color(dv.BlackTextColor()));
            StatusChoice.setBackground(Color.WHITE);
            StatusChoice.add("Điểm danh");
            StatusChoice.add("Đã tiêm");
            StatusChoice.add("Hủy");

            ActionListener handleUpdate = new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if ( dv.popupConfirmOption(null,"Xác nhận cập nhật trạng thái lượt đăng ký?", "Xác nhận?") != 0)
                        return;

                    String plsql = "{call REG_UPDATE_STATUS(?, ?, ?)}";

                    try {
                        Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                        CallableStatement cst = connection.prepareCall(plsql);

                        cst.setString("par_PersonalID", Reg.getCitizen().getID());
                        cst.setString("par_SchedID", Reg.getSched().getID());
                        cst.setInt("par_Status", StatusChoice.getSelectedIndex()+1);

                        cst.execute();

                    }
                    catch (SQLException ex)
                    {
                        dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(),2);
                    }

                    Reg.setStatus(StatusChoice.getSelectedIndex()+1);
                    TimeNOStatus.setText("Buổi: " + dv.getTimeName(Reg.getTime())
                            + "          STT: " + Reg.getNO() + "          Tình trạng: " + dv.getStatusName(Reg.getStatus()));

                    LayeredPaneArea.repaint(320, 40, 680, 630);
                }
            };

            JButton UpdateStatusButton = new JButton();
            ImageIcon UpdateStatusButtonIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Update Status Button.png"));
            UpdateStatusButton.setForeground(new Color(dv.BlackTextColor()));
            UpdateStatusButton.setBounds(500,32*2+5,UpdateStatusButtonIcon.getIconWidth(),UpdateStatusButtonIcon.getIconHeight());
            UpdateStatusButton.setContentAreaFilled(false);
            UpdateStatusButton.setBorder(null);
            UpdateStatusButton.setIcon(UpdateStatusButtonIcon);
            UpdateStatusButton.addActionListener(handleUpdate);

            RegPanel[i].add(StatusChoice);
            RegPanel[i].add(UpdateStatusButton);
        }
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
        ScrollPaneRegList.setBounds(0, 40, 680, 590); //320 40
    }





    private void initOnDateLabel()
    {

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
    
    private void initAddNewSchedLabel()
    {
        AddNewSchedLabel = new JLabel("Thêm lịch tiêm mới");
        AddNewSchedLabel.setBounds(70, 260, 200, 30);
        AddNewSchedLabel.setFont(new Font(dv.fontName(), 1, 18));
        AddNewSchedLabel.setForeground(new Color(dv.BlackTextColor()));
    }

    private void initAddNewSchedButton()
    {
        ImageIcon AddNewButton = new ImageIcon(getClass().getResource("/Data_Processor/icon/Add New Button.png"));
        AddNewSchedButton = new JButton();
        AddNewSchedButton.setBounds(125, 300, AddNewButton.getIconWidth(), AddNewButton.getIconHeight());
        AddNewSchedButton.setBorder(null);
        AddNewSchedButton.setContentAreaFilled(false);
        AddNewSchedButton.setIcon(AddNewButton);
        AddNewSchedButton.addActionListener(this);
    }

    private void initCreateSchedPanel()
    {
        JLabel CreateSchedLabel = new JLabel("TẠO LỊCH TIÊM CHỦNG (" + orgUser.getName() + ")");
        CreateSchedLabel.setPreferredSize(new Dimension(600, 35));
        CreateSchedLabel.setFont(new Font(dv.fontName(), 1, 20));
        CreateSchedLabel.setForeground(new Color(dv.FeatureButtonColor()));
        CreateSchedLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel OnDateLabel = new JLabel("Ngày thực hiện lịch tiêm: ");
        OnDateLabel.setPreferredSize(new Dimension(200, 30));
        OnDateLabel.setFont(new Font(dv.fontName(), 0, 16));
        OnDateLabel.setForeground(new Color(dv.BlackTextColor()));

        UtilDateModel model=new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl OnDateField = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        OnDateField.setPreferredSize(new Dimension(150, 30));

        JFormattedTextField textField = OnDateField.getJFormattedTextField();
        textField.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        textField.setPreferredSize(new Dimension(150, 30));
        textField.setBackground(Color.WHITE);

        OnDateField.setForeground(new Color(dv.BlackTextColor()));
        OnDateField.setVisible(true);
        OnDateField.setEnabled(true);

        JLabel VaccineLabel = new JLabel("Loại vaccine sử dụng:");
        VaccineLabel.setPreferredSize(new Dimension(200, 30));
        VaccineLabel.setFont(new Font(dv.fontName(), 0 ,16));
        VaccineLabel.setForeground(new Color(dv.BlackTextColor()));

        Vaccine vacc[] = new Vaccine[10];

        int i = 0;
        int nVacc = 0;

        String query = "select * from VACCINE";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                vacc[i] = new Vaccine();
                vacc[i].setID(rs.getString("ID"));
                vacc[i].setName(rs.getString("Name"));
                vacc[i].setTechnology(rs.getString("Technology"));
                vacc[i].setCountry(rs.getString("Country"));
                vacc[i].setNote(rs.getString("Note"));
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Choice VaccineChoice = new Choice();
        VaccineChoice.setPreferredSize(new Dimension(200, 30));
        VaccineChoice.setFont(new Font(dv.fontName(), 0, 16));
        VaccineChoice.setForeground(new Color(dv.BlackTextColor()));
        VaccineChoice.setBackground(Color.WHITE);

        nVacc = i;

        VaccineChoice.add("");
        for (i = 0; i<nVacc; i++)
        {
            VaccineChoice.add(vacc[i].getID());
        }

        JLabel SerialLabel = new JLabel("Series vaccine:");
        SerialLabel.setPreferredSize(new Dimension(200, 30));
        SerialLabel.setFont(new Font(dv.fontName(), 0 ,16));
        SerialLabel.setForeground(new Color(dv.BlackTextColor()));

        JTextField SerialTextField = new JTextField();
        SerialTextField.setPreferredSize(new Dimension(200, 30));
        SerialTextField.setFont(new Font(dv.fontName(), 0 ,16));
        SerialTextField.setForeground(new Color(dv.BlackTextColor()));

        JLabel LimitDayLabel = new JLabel("Giới hạn số lượt đăng ký buổi sáng:");
        LimitDayLabel.setPreferredSize(new Dimension(250, 30));
        LimitDayLabel.setFont(new Font(dv.fontName(), 0 ,16));
        LimitDayLabel.setForeground(new Color(dv.BlackTextColor()));

        JTextField LimitDayTextField = new JTextField();
        LimitDayTextField.setPreferredSize(new Dimension(200, 30));
        LimitDayTextField.setFont(new Font(dv.fontName(), 0 ,16));
        LimitDayTextField.setForeground(new Color(dv.BlackTextColor()));

        JLabel LimitNoonLabel = new JLabel("Giới hạn số lượt đăng ký buổi trưa:");
        LimitNoonLabel.setPreferredSize(new Dimension(250, 30));
        LimitNoonLabel.setFont(new Font(dv.fontName(), 0 ,16));
        LimitNoonLabel.setForeground(new Color(dv.BlackTextColor()));

        JTextField LimitNoonTextField = new JTextField();
        LimitNoonTextField.setPreferredSize(new Dimension(200, 30));
        LimitNoonTextField.setFont(new Font(dv.fontName(), 0 ,16));
        LimitNoonTextField.setForeground(new Color(dv.BlackTextColor()));

        JLabel LimitNightLabel = new JLabel("Giới hạn số lượt đăng ký buổi tối:");
        LimitNightLabel.setPreferredSize(new Dimension(250, 30));
        LimitNightLabel.setFont(new Font(dv.fontName(), 0 ,16));
        LimitNightLabel.setForeground(new Color(dv.BlackTextColor()));

        JTextField LimitNightTextField = new JTextField();
        LimitNightTextField.setPreferredSize(new Dimension(200, 30));
        LimitNightTextField.setFont(new Font(dv.fontName(), 0 ,16));
        LimitNightTextField.setForeground(new Color(dv.BlackTextColor()));

        ActionListener handleCreateSched = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String InputOnDate = OnDateField.getJFormattedTextField().getText();
                String InputVaccineID = VaccineChoice.getSelectedItem();
                String InputSerial = SerialTextField.getText();

                if (dv.checkStringInputValue(InputOnDate,
                    "Cảnh báo!", "Nhập ngày lịch tiêm!") != -2)
                    return;
                if (dv.checkStringInputValue(InputVaccineID,
                    "Cảnh báo!", "Chọn loại vaccine!") != -2)
                    return;
                if(dv.checkStringInputValue(InputSerial,
                    "Cảnh báo!", "Nhập số lô vaccine!") != -2)
                    return;

                int InputLimitDay = 0;
                try {
                    InputLimitDay = Integer.parseInt(LimitDayTextField.getText());
                } catch (NumberFormatException ex) {
                    InputLimitDay = 0;
                }

                int InputLimitNoon = 0;
                try {

                    InputLimitNoon = Integer.parseInt(LimitNoonTextField.getText());
                } catch (NumberFormatException ex) {
                    InputLimitNoon = 0;
                }

                int InputLimitNight = 0;
                try {

                    InputLimitNight = Integer.parseInt(LimitNightTextField.getText());
                } catch (NumberFormatException ex) {
                    InputLimitNight = 0;
                }

                int answer = dv.popupConfirmOption(null,"Xác nhận tạo lịch tiêm chủng?", "Xác nhận!");

                if (answer == JOptionPane.YES_OPTION)
                {
                    String plsql = "{call SCHED_INSERT_RECORD(?,?,?,?,?,?,?,?)}";


                    Connection connection = null;
                    try {
                        connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                        CallableStatement cst = connection.prepareCall(plsql);
                        cst.setString("par_OrgID", orgUser.getID());
                        cst.setString("par_OnDate", dv.toOracleDateFormat(InputOnDate));
                        cst.setString("par_VaccineID", InputVaccineID);
                        cst.setString("par_Serial", InputSerial);
                        cst.setInt("par_LimitDay", InputLimitDay);
                        cst.setInt("par_LimitNoon", InputLimitNoon);
                        cst.setInt("par_LimitNight", InputLimitNight);
                        cst.setString("par_Note", "");

                        cst.execute();
                    } catch (SQLException ex)
                    {
                        dv.popupOption(null,  ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                        ex.printStackTrace();
                    }

                    dv.popupOption(null, "Tạo lịch tiêm chủng thành công!", "Thông báo!", 0);
                }
            }
        };

        ImageIcon CreateSchedButtonIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Confirm Button.png"));
        JButton CreateSchedButton = new JButton();
        CreateSchedButton.setPreferredSize(new Dimension(CreateSchedButtonIcon.getIconWidth(), CreateSchedButtonIcon.getIconHeight()));
        CreateSchedButton.setContentAreaFilled(false);
        CreateSchedButton.setBorder(null);
        CreateSchedButton.setIcon(CreateSchedButtonIcon);
        CreateSchedButton.addActionListener(handleCreateSched);

        CreateSchedPanel = new JPanel();
        CreateSchedPanel.setBounds(0, 0, 660, 630);
        CreateSchedPanel.setLayout(new GridBagLayout());
        CreateSchedPanel.setBorder(dv.border());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;

        c.insets = new Insets(0,0,20,0);
        c.gridy = 0;
        CreateSchedPanel.add(CreateSchedLabel, c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 1;
        CreateSchedPanel.add(OnDateLabel,c);

        c.insets = new Insets(0,0,15,0);
        c.gridy = 2;
        CreateSchedPanel.add(OnDateField,c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 3;
        CreateSchedPanel.add(VaccineLabel,c);

        c.insets = new Insets(0,0,15,0);
        c.gridy = 4;
        CreateSchedPanel.add(VaccineChoice,c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 5;
        CreateSchedPanel.add(SerialLabel,c);

        c.insets = new Insets(0,0,15,0);
        c.gridy = 6;
        CreateSchedPanel.add(SerialTextField,c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 7;
        CreateSchedPanel.add(LimitDayLabel,c);

        c.insets = new Insets(0,0,15,0);
        c.gridy = 8;
        CreateSchedPanel.add(LimitDayTextField,c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 9;
        CreateSchedPanel.add(LimitNoonLabel,c);

        c.insets = new Insets(0,0,15,0);
        c.gridy = 10;
        CreateSchedPanel.add(LimitNoonTextField,c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 11;
        CreateSchedPanel.add(LimitNightLabel,c);

        c.insets = new Insets(0,0,15,0);
        c.gridy = 12;
        CreateSchedPanel.add(LimitNightTextField,c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 13;
        CreateSchedPanel.add(CreateSchedButton, c);
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

        //initAddNewSchedLabel
        initAddNewSchedLabel();
        this.add(AddNewSchedLabel);

        //init AddNewSchedButton
        initAddNewSchedButton();
        this.add(AddNewSchedButton);

        //init SchedListPanel
        initSchedListPanel(0);

        //init LayeredPaneArea
        JLabel SchedListLabel = new JLabel("DANH SÁCH LỊCH TIÊM (" + orgUser.getName() + "):");
        SchedListLabel.setBounds(0,0,640,40);
        SchedListLabel.setFont(new Font(dv.fontName(), 1, 20));
        SchedListLabel.setForeground(new Color(dv.FeatureButtonColor()));
        SchedListLabel.setHorizontalAlignment(JLabel.CENTER);

        initScrollPaneSchedList(0);

        initLayeredPaneArea();

        LayeredPaneArea.add(SchedListLabel, Integer.valueOf(0));
        LayeredPaneArea.add(ScrollPaneSchedList, Integer.valueOf(0));
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
            CreateSchedPanel = null;
            LayeredPaneArea.removeAll();
            LayeredPaneArea.repaint(320, 40, 680, 630);

            JLabel SchedListLabel = new JLabel("DANH SÁCH LỊCH TIÊM:");
            SchedListLabel.setBounds(0,0,640,40);
            SchedListLabel.setFont(new Font(dv.fontName(), 1, 20));
            SchedListLabel.setForeground(new Color(dv.FeatureButtonColor()));
            SchedListLabel.setHorizontalAlignment(JLabel.CENTER);

            initScrollPaneSchedList(SchedFilterChoice.getSelectedIndex());

            LayeredPaneArea.add(SchedListLabel, Integer.valueOf(0));
            LayeredPaneArea.add(ScrollPaneSchedList, Integer.valueOf(0));
            LayeredPaneArea.repaint(320, 40, 680, 630);

            RegListPanel = null;
            ScrollPaneRegList = null;
        }

        if (e.getSource() == AddNewSchedButton)
        {
            SchedListPanel = null;
            ScrollPaneSchedList = null;

            RegListPanel = null;
            ScrollPaneRegList = null;

            LayeredPaneArea.removeAll();

            initCreateSchedPanel();

            LayeredPaneArea.add(CreateSchedPanel, Integer.valueOf(0));

            LayeredPaneArea.repaint(320, 40, 680, 630);
        }

    }

}
