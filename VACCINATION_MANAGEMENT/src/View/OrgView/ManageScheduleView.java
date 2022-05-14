package View.OrgView;

import Process.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
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

    /*Registion List*/
    private JPanel RegFilterPanel;
    private JLabel RegFilterLabel;
    private Choice RegFilterChoice;
    private JButton RegFilterButton;
    private Schedule SelectedSched;

    private JScrollPane ScrollPaneRegList;
    private JPanel RegListPanel;

    /*Update Sched*/
    private JPanel UpdateSchedPanel;

    /*Create Schedule*/
    private JButton CreateNewSchedButton;
    private JPanel CreateSchedPanel;


    private JLayeredPane LayeredPaneArea;

    public Organization getOrgUser() {
        return orgUser;
    }

    public void setOrgUser(Organization orgUser) {
        this.orgUser = orgUser;
    }


    /*
    *   INITIALIZE THE SCHEDULE FILTER PANEL:
    *   + LABEL
    *   + CHOICE
    *   + BUTTON: SELECT
    */
    private void initSchedFilterLabel()
    {
        SchedFilterLabel = new JLabel();
        SchedFilterLabel.setBounds(0, 0, dv.LabelWidth()+50, dv.LabelHeight());
        SchedFilterLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        SchedFilterLabel.setForeground(new Color(0x666666));
        SchedFilterLabel.setText("Bộ lọc lịch tiêm");
        SchedFilterLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initSchedFilterChoice()
    {
        SchedFilterChoice = new Choice();
        SchedFilterChoice.setBounds(0, 30, dv.FieldWidth(), dv.FieldHeight());
        SchedFilterChoice.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        SchedFilterChoice.setForeground(new Color(dv.BlackTextColor()));

        SchedFilterChoice.add("Tất cả");
        SchedFilterChoice.add("Đã lên lịch");
        SchedFilterChoice.add("Đã thực hiện");
    }

    private void initSchedFilterButton()
    {
        SchedFilterButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Resources/icon/Search Filter Button.png"));
        SchedFilterButton.setIcon(SearchIcon);

        SchedFilterButton.setBounds(0, 70, dv.FieldWidth(), SearchIcon.getIconHeight());
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
        SchedFilterPanel.setBounds(dv.AlignLeft(), 80, dv.LabelWidth()+50, 125);
        SchedFilterPanel.setLayout(null);
        SchedFilterPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        SchedFilterPanel.add(SchedFilterLabel);
        SchedFilterPanel.add(SchedFilterChoice);
        SchedFilterPanel.add(SchedFilterButton);
    }



    /*
    *   INITIALIZE SCHEDULE LIST OF THE ORGANIZATION:
    *   - A SCROLLPANE:
    *       + A PANEL: LIST OF SCHEDULES
    *           - PANELS: SCHEDULES
    *               + LABELS
    *               + BUTTONS: WATCH LIST OF REGISTION
    */
    private JPanel initSchedPanel(Schedule Sched)
    {
        JPanel SchedPanel = new JPanel();
        SchedPanel.setLayout(null);
        SchedPanel.setPreferredSize(new Dimension(640,120));
        SchedPanel.setBackground(Color.WHITE);

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

        ActionListener handleRegistionsButton = new ActionListener()
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

                SelectedSched = Sched;
                initRegListPanel(Sched, -1);
                initScrollPaneRegList();

                LayeredPaneArea.add(RegListLabel, Integer.valueOf(0));
                LayeredPaneArea.add(ScrollPaneRegList, Integer.valueOf(0));
                LayeredPaneArea.repaint(320, 40, 680, 630);

                RegFilterButton.setEnabled(true);
            }
        };

        JButton SchedRegistionButton = new JButton();
        SchedRegistionButton.setBounds(30, 80, 150+7, 30+6);
        SchedRegistionButton.setBorder(null);
        SchedRegistionButton.setContentAreaFilled(false);
        SchedRegistionButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Sched Registion Button.png")));
        SchedRegistionButton.addActionListener(handleRegistionsButton);

        SchedPanel.add(OnDateVaccine);
        SchedPanel.add(Time);
        SchedPanel.add(SchedRegistionButton);

        LocalDate SchedOnDate = LocalDate.parse(Sched.getOnDate().substring(0, 10));

        LocalDate sysdate = LocalDate.parse(dv.todayString());

        JButton UpdateSchedButton = new JButton();
        JButton CancelSchedButton = new JButton();

        if (SchedOnDate.isAfter(sysdate))
        {
            ActionListener handleUpdateCancelSched = new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (e.getSource() == UpdateSchedButton)
                    {
                        LayeredPaneArea.removeAll();
                        SchedListPanel = null;
                        ScrollPaneSchedList = null;

                        UpdateSchedPanel = new JPanel();
                        UpdateSchedPanel.setBounds(0,0,680,630);
                        UpdateSchedPanel.setLayout(new GridBagLayout());
                        UpdateSchedPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
                        UpdateSchedPanel.setBorder(dv.border());

                        JLabel UpdateSchedLabel = new JLabel("CẬP NHẬT LỊCH TIÊM");
                        UpdateSchedLabel.setPreferredSize(new Dimension(640,40));
                        UpdateSchedLabel.setFont(new Font(dv.fontName(), 1, 20));
                        UpdateSchedLabel.setForeground(new Color(dv.FeatureButtonColor()));
                        UpdateSchedLabel.setHorizontalAlignment(JLabel.CENTER);

                        JLabel LimitDayLabel = new JLabel("Giới hạn số lượt đăng ký buổi sáng:");
                        LimitDayLabel.setPreferredSize(new Dimension(270, 30));
                        LimitDayLabel.setFont(new Font(dv.fontName(), 0 ,16));
                        LimitDayLabel.setForeground(new Color(dv.BlackTextColor()));

                        JTextField LimitDayTextField = new JTextField();
                        LimitDayTextField.setPreferredSize(new Dimension(200, 30));
                        LimitDayTextField.setFont(new Font(dv.fontName(), 0 ,16));
                        LimitDayTextField.setForeground(new Color(dv.BlackTextColor()));

                        JLabel LimitNoonLabel = new JLabel("Giới hạn số lượt đăng ký buổi trưa:");
                        LimitNoonLabel.setPreferredSize(new Dimension(270, 30));
                        LimitNoonLabel.setFont(new Font(dv.fontName(), 0 ,16));
                        LimitNoonLabel.setForeground(new Color(dv.BlackTextColor()));

                        JTextField LimitNoonTextField = new JTextField();
                        LimitNoonTextField.setPreferredSize(new Dimension(200, 30));
                        LimitNoonTextField.setFont(new Font(dv.fontName(), 0 ,16));
                        LimitNoonTextField.setForeground(new Color(dv.BlackTextColor()));

                        JLabel LimitNightLabel = new JLabel("Giới hạn số lượt đăng ký buổi tối:");
                        LimitNightLabel.setPreferredSize(new Dimension(270, 30));
                        LimitNightLabel.setFont(new Font(dv.fontName(), 0 ,16));
                        LimitNightLabel.setForeground(new Color(dv.BlackTextColor()));

                        JTextField LimitNightTextField = new JTextField();
                        LimitNightTextField.setPreferredSize(new Dimension(200, 30));
                        LimitNightTextField.setFont(new Font(dv.fontName(), 0 ,16));
                        LimitNightTextField.setForeground(new Color(dv.BlackTextColor()));

                        ActionListener handleConfirmButton = new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                String InputLimitDay = LimitDayTextField.getText();
                                String InputLimitNoon = LimitNoonTextField.getText();
                                String InputLimitNight = LimitNightTextField.getText();

                                if (InputLimitDay.equals("") == false)
                                {
                                    if(dv.checkisNumberInputValue(InputLimitDay,
                                            "Lỗi!", "Giới hạn phải là số!") != -2)
                                        return;
                                }
                                else
                                    InputLimitDay = String.valueOf(Sched.getLimitDay());


                                if (InputLimitNoon.equals("") == false)
                                {
                                    if(dv.checkisNumberInputValue(InputLimitNoon,
                                            "Lỗi!", "Giới hạn phải là số!") != -2)
                                        return;
                                }
                                else
                                    InputLimitNoon = String.valueOf(Sched.getLimitNoon());


                                if (InputLimitNight.equals("") == false)
                                {
                                    if(dv.checkisNumberInputValue(InputLimitNight,
                                            "Lỗi!", "Giới hạn phải là số!") != -2)
                                        return;
                                }
                                else
                                    InputLimitNight = String.valueOf(Sched.getLimitNight());

                                if (dv.popupConfirmOption(null,"Xác nhận cập nhật lịch tiêm?", "Xác nhận?") != 0)
                                    return;

                                String plsql = "{call SCHED_UPDATE_RECORD(?, ?, ?, ?)}";

                                try {
                                    Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                                    CallableStatement cst = connection.prepareCall(plsql);

                                    cst.setString("par_ID", Sched.getID());
                                    cst.setInt("par_LimitDay", Integer.parseInt(InputLimitDay));
                                    cst.setInt("par_LimitNoon", Integer.parseInt(InputLimitNoon));
                                    cst.setInt("par_LimitNight", Integer.parseInt(InputLimitNight));

                                    cst.execute();
                                }
                                catch (SQLException ex)
                                {
                                    dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                                    ex.printStackTrace();
                                    return;
                                }
                                dv.popupOption(null, "Cập nhật thành công!", "Thông báo!", 0);

                                Sched.setLimitDay(Integer.parseInt(InputLimitDay));
                                Sched.setLimitNoon(Integer.parseInt(InputLimitNoon));
                                Sched.setLimitNight(Integer.parseInt(InputLimitNight));
                                Time.setText("Buổi sáng: " + Sched.getDayRegistered() + "/" + Sched.getLimitDay()
                                        + "          Buổi trưa: " + Sched.getNoonRegistered() + "/" + Sched.getLimitNoon()
                                        + "          Buổi tối: " + Sched.getNightRegistered() + "/" + Sched.getLimitNight());

                                UpdateSchedPanel.repaint(0,0,680,630);

//                                LayeredPaneArea.removeAll();
//                                LayeredPaneArea.add(UpdateSchedPanel, Integer.valueOf(0));
//                                LayeredPaneArea.repaint(320, 40, 680, 630);
                            }
                        };

                        JButton UpdateSchedButton = new JButton();
                        ImageIcon UpdateSchedButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Confirm Button.png"));
                        UpdateSchedButton.setForeground(new Color(dv.BlackTextColor()));
                        UpdateSchedButton.setPreferredSize(new Dimension(UpdateSchedButtonIcon.getIconWidth(),UpdateSchedButtonIcon.getIconHeight()));
                        UpdateSchedButton.setContentAreaFilled(false);
                        UpdateSchedButton.setBorder(null);
                        UpdateSchedButton.setIcon(UpdateSchedButtonIcon);
                        UpdateSchedButton.addActionListener(handleConfirmButton);

                        GridBagConstraints c = new GridBagConstraints();
                        c.gridx = 0;

                        c.insets = new Insets(0,0,10,0);
                        c.gridy = 0;
                        UpdateSchedPanel.add(UpdateSchedLabel,c);

                        c.insets = new Insets(0,0,5,0);
                        c.gridy = 1;
                        UpdateSchedPanel.add(OnDateVaccine,c);

                        c.insets = new Insets(0,0,30,0);
                        c.gridy = 2;
                        UpdateSchedPanel.add(Time,c);

                        c.insets = new Insets(0,0,0,0);
                        c.gridy = 3;
                        UpdateSchedPanel.add(LimitDayLabel,c);

                        c.insets = new Insets(0,0,20,0);
                        c.gridy = 4;
                        UpdateSchedPanel.add(LimitDayTextField,c);

                        c.insets = new Insets(0,0,0,0);
                        c.gridy = 5;
                        UpdateSchedPanel.add(LimitNoonLabel,c);

                        c.insets = new Insets(0,0,20,0);
                        c.gridy = 6;
                        UpdateSchedPanel.add(LimitNoonTextField,c);

                        c.insets = new Insets(0,0,0,0);
                        c.gridy = 7;
                        UpdateSchedPanel.add(LimitNightLabel,c);

                        c.insets = new Insets(0,0,30,0);
                        c.gridy = 8;
                        UpdateSchedPanel.add(LimitNightTextField,c);

                        c.gridy = 9;
                        UpdateSchedPanel.add(UpdateSchedButton, c);

                        LayeredPaneArea.add(UpdateSchedPanel, Integer.valueOf(0));

                        LayeredPaneArea.repaint(320, 40, 680, 630);
                    }
                }
            };

            UpdateSchedButton.setBounds(30 + 160 + 30, 80, 150+7, 30+6);
            UpdateSchedButton.setBorder(null);
            UpdateSchedButton.setContentAreaFilled(false);
            UpdateSchedButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Update Sched Button.png")));
            UpdateSchedButton.addActionListener(handleUpdateCancelSched);

            CancelSchedButton.setBounds( 160 + 30 + 200, 80, 150+7, 30+6);
            CancelSchedButton.setBorder(null);
            CancelSchedButton.setContentAreaFilled(false);
            CancelSchedButton.setIcon(new ImageIcon(getClass().getResource("/Resources/icon/Cancel Sched Button.png")));
            CancelSchedButton.addActionListener(handleUpdateCancelSched);

            SchedPanel.add(UpdateSchedButton);
            SchedPanel.add(CancelSchedButton);
        }

        return SchedPanel;
    }

    private void initSchedListPanel(int OnDateFilter)
    {
        SchedListPanel = new JPanel();
        SchedListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        SchedListPanel.setLayout(new FlowLayout());

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
                Schedule Sched = new Schedule();
                Sched.setID(rs.getString("ID"));
                Sched.setOnDate(rs.getString("OnDate"));
                Sched.setVaccineID(rs.getString("VaccineID"));
                Sched.setSerial(rs.getString("Serial"));
                Sched.setLimitDay(rs.getInt("LimitDay"));
                Sched.setLimitNoon(rs.getInt("LimitNoon"));
                Sched.setLimitNight(rs.getInt("LimitNight"));
                Sched.setDayRegistered(rs.getInt("DayRegistered"));
                Sched.setNoonRegistered(rs.getInt("NoonRegistered"));
                Sched.setNightRegistered(rs.getInt("NightRegistered"));
                SchedListPanel.add(initSchedPanel(Sched));
                i++;
            }
        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
            ex.printStackTrace();
            return;
        }

        nScheds = i;

        SchedListPanel.setPreferredSize(new Dimension(660, 120*nScheds+nScheds*10));
    }

    private void initScrollPaneSchedList(int OnDateFilter)
    {
        initSchedListPanel(OnDateFilter);

        ScrollPaneSchedList = new JScrollPane(SchedListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneSchedList.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        ScrollPaneSchedList.setBounds(0, 40, 680, 590); //320 40
    }



    /*
    *   INITIALIZE REGISTER FILTER PANEL
    *   - PANEL:
    *       + LABEL
    *       + CHOICE
    *       + BUTTON: SELECT
    */

    private void initRegFilterLabel()
    {
        RegFilterLabel = new JLabel();
        RegFilterLabel.setBounds(0, 0, dv.LabelWidth()+50, dv.LabelHeight());
        RegFilterLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        RegFilterLabel.setForeground(new Color(0x666666));
        RegFilterLabel.setText("Bộ lọc lượt đăng ký");
        RegFilterLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initRegFilterChoice()
    {
        RegFilterChoice = new Choice();
        RegFilterChoice.setBounds(0, 30, dv.FieldWidth(), dv.FieldHeight());
        RegFilterChoice.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        RegFilterChoice.setForeground(new Color(dv.BlackTextColor()));

        RegFilterChoice.add("Tất cả");
        RegFilterChoice.add("Đã đăng ký");
        RegFilterChoice.add("Đã điểm danh");
        RegFilterChoice.add("Đã tiêm");
        RegFilterChoice.add("Đã hủy");
    }

    private void initRegFilterButton()
    {
        RegFilterButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Resources/icon/Search Filter Button.png"));
        RegFilterButton.setIcon(SearchIcon);

        RegFilterButton.setBounds(0, 70, dv.FieldWidth(), SearchIcon.getIconHeight());
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
        RegFilterPanel.setBounds(dv.AlignLeft(), 240, dv.LabelWidth()+50, 125);
        RegFilterPanel.setLayout(null);
        RegFilterPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        RegFilterPanel.add(RegFilterLabel);
        RegFilterPanel.add(RegFilterChoice);
        RegFilterPanel.add(RegFilterButton);
    }

    /*
    *       INITIALIZE THE LIST OF REGISTIONS OF THE SELECTED SCHEDULE
    *       - SCROLLPANE:
    *           + PANEL: LIST OF REGISTIONS
    *               - PANELS: REGISTIONS
    *                   + CHOICE: STATUS OF REGISTION
    *                   + BUTTON: UPDATE STATUS
    * */

    private JPanel initRegPanel(RegisteredScheds Reg)
    {
        JPanel RegPanel = new JPanel();
        RegPanel.setLayout(null);
        RegPanel.setPreferredSize(new Dimension(640,120));
        RegPanel.setBackground(Color.WHITE);

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

        RegPanel.add(CitizenName);
        RegPanel.add(Phone);
        RegPanel.add(OnDateVaccine);
        RegPanel.add(TimeNOStatus);

        if (Reg.getStatus() < 2)
        {
            Choice StatusChoice = new Choice();
            StatusChoice.setBounds(500, 32+2, 120, 30);
            StatusChoice.setFont(new Font(dv.fontName(), 0, 16));
            StatusChoice.setForeground(new Color(dv.BlackTextColor()));

            if (Reg.getStatus() == 0)
            {
                StatusChoice.add("Điểm danh");
                StatusChoice.add("Hủy");
            }

            if (Reg.getStatus() == 1)
            {
                StatusChoice.add("Đã tiêm");
                StatusChoice.add("Hủy");
            }

            ActionListener handleUpdateRegistions = new ActionListener()
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
                        dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                        ex.printStackTrace();
                        return;
                    }

                    //repaint Status on Registion Panel
                    Reg.setStatus(StatusChoice.getSelectedIndex()+1);
                    TimeNOStatus.setText("Buổi: " + dv.getTimeName(Reg.getTime())
                            + "          STT: " + Reg.getNO() + "          Tình trạng: " + dv.getStatusName(Reg.getStatus()));

                    RegPanel.repaint();
                }
            };

            JButton UpdateStatusButton = new JButton();
            ImageIcon UpdateStatusButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Update Status Button.png"));
            UpdateStatusButton.setForeground(new Color(dv.BlackTextColor()));
            UpdateStatusButton.setBounds(500,32*2+5,UpdateStatusButtonIcon.getIconWidth(),UpdateStatusButtonIcon.getIconHeight());
            UpdateStatusButton.setContentAreaFilled(false);
            UpdateStatusButton.setBorder(null);
            UpdateStatusButton.setIcon(UpdateStatusButtonIcon);
            UpdateStatusButton.addActionListener(handleUpdateRegistions);

            RegPanel.add(StatusChoice);
            RegPanel.add(UpdateStatusButton);
        }

        return RegPanel;
    }

    private void initRegListPanel(Schedule Sched, int Status)
    {
        RegListPanel = new JPanel();
        RegListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        RegListPanel.setLayout(new FlowLayout());

        String query = "";

        int nReg = 0;
        int i = 0;

        query = "select PersonalID, LastName, FirstName, Birthday, Gender, Phone, Time, NO, DoseType, Status, Image" +
                " from REGISTER REG, PERSON" +
                " where REG.PersonalID = PERSON.ID" +
                " and REG.SchedID = '" +  Sched.getID() + "'";
        if (Status != -1)
            query += " and Status = " + Status;
        query += " order by Time, NO";

        System.out.println(query);

        Connection connection = null;
        try {

            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                //PersonalID, LastName, FirstName, Birthday, Gender, Phone, Time, NO, DoseType, Status, Image
                RegisteredScheds Reg = new RegisteredScheds();
                Reg.setSched(Sched);
                Reg.getCitizen().setID(rs.getString("PersonalID"));
                Reg.getCitizen().setLastName(rs.getString("LastName"));
                Reg.getCitizen().setFirstName(rs.getString("FirstName"));
                Reg.getCitizen().setBirthday(rs.getString("Birthday"));
                Reg.getCitizen().setGender(rs.getInt("Gender"));
                Reg.getCitizen().setPhone(rs.getString("Phone"));
                Reg.setTime(rs.getInt("Time"));
                Reg.setNO(rs.getInt("NO"));
                Reg.setDoseType(rs.getString("DoseType"));
                Reg.setStatus(rs.getInt("Status"));
                Reg.setImage(rs.getBytes("Image"));
                RegListPanel.add(initRegPanel(Reg));
                i++;
            }
        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
            ex.printStackTrace();
            return;
        }

        nReg = i;

        RegListPanel.setPreferredSize(new Dimension(660, 150*nReg+nReg*10));
    }

    private void initScrollPaneRegList()
    {
        ScrollPaneRegList = new JScrollPane(RegListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneRegList.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        ScrollPaneRegList.setBounds(0, 40, 680, 590); //320 40
    }



    /*
    *   INITIALIZE THE CREATE SCHEDULE PANEL
    *   - BUTTON: CREATE A NEW SCHEDULE
    *   - PANEL:
    *       + LABELS
    *       + CHOICES
    *       + BUTTON: CONFIRM CREATION
    * */

    private void initCreateNewSchedButton()
    {
        ImageIcon CreateNewSchedButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Add New Sched Button.png"));
        CreateNewSchedButton = new JButton();
        CreateNewSchedButton.setBounds((320-CreateNewSchedButtonIcon.getIconWidth())/2, 600, CreateNewSchedButtonIcon.getIconWidth(), CreateNewSchedButtonIcon.getIconHeight());
        CreateNewSchedButton.setBorder(null);
        CreateNewSchedButton.setContentAreaFilled(false);
        CreateNewSchedButton.setIcon(CreateNewSchedButtonIcon);
        CreateNewSchedButton.addActionListener(this);
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

        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
            ex.printStackTrace();
            return;
        }

        Choice VaccineChoice = new Choice();
        VaccineChoice.setPreferredSize(new Dimension(200, 30));
        VaccineChoice.setFont(new Font(dv.fontName(), 0, 16));
        VaccineChoice.setForeground(new Color(dv.BlackTextColor()));

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
        LimitDayLabel.setPreferredSize(new Dimension(270, 30));
        LimitDayLabel.setFont(new Font(dv.fontName(), 0 ,16));
        LimitDayLabel.setForeground(new Color(dv.BlackTextColor()));

        JTextField LimitDayTextField = new JTextField();
        LimitDayTextField.setPreferredSize(new Dimension(200, 30));
        LimitDayTextField.setFont(new Font(dv.fontName(), 0 ,16));
        LimitDayTextField.setForeground(new Color(dv.BlackTextColor()));

        JLabel LimitNoonLabel = new JLabel("Giới hạn số lượt đăng ký buổi trưa:");
        LimitNoonLabel.setPreferredSize(new Dimension(270, 30));
        LimitNoonLabel.setFont(new Font(dv.fontName(), 0 ,16));
        LimitNoonLabel.setForeground(new Color(dv.BlackTextColor()));

        JTextField LimitNoonTextField = new JTextField();
        LimitNoonTextField.setPreferredSize(new Dimension(200, 30));
        LimitNoonTextField.setFont(new Font(dv.fontName(), 0 ,16));
        LimitNoonTextField.setForeground(new Color(dv.BlackTextColor()));

        JLabel LimitNightLabel = new JLabel("Giới hạn số lượt đăng ký buổi tối:");
        LimitNightLabel.setPreferredSize(new Dimension(270, 30));
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
                        dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                        ex.printStackTrace();
                        return;
                    }

                    dv.popupOption(null, "Tạo lịch tiêm chủng thành công!", "Thông báo!", 0);
                }
            }
        };

        ImageIcon CreateSchedButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Confirm Button.png"));
        JButton CreateSchedButton = new JButton();
        CreateSchedButton.setPreferredSize(new Dimension(CreateSchedButtonIcon.getIconWidth(), CreateSchedButtonIcon.getIconHeight()));
        CreateSchedButton.setContentAreaFilled(false);
        CreateSchedButton.setBorder(null);
        CreateSchedButton.setIcon(CreateSchedButtonIcon);
        CreateSchedButton.addActionListener(handleCreateSched);

        CreateSchedPanel = new JPanel();
        CreateSchedPanel.setBounds(0, 0, 660, 630);
        CreateSchedPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        CreateSchedPanel.setLayout(new GridBagLayout());
        CreateSchedPanel.setBorder(dv.border());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;

        c.insets = new Insets(0,0,15,0);
        c.gridy = 0;
        CreateSchedPanel.add(CreateSchedLabel, c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 1;
        CreateSchedPanel.add(OnDateLabel,c);

        c.insets = new Insets(0,0,10,0);
        c.gridy = 2;
        CreateSchedPanel.add(OnDateField,c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 3;
        CreateSchedPanel.add(VaccineLabel,c);

        c.insets = new Insets(0,0,10,0);
        c.gridy = 4;
        CreateSchedPanel.add(VaccineChoice,c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 5;
        CreateSchedPanel.add(SerialLabel,c);

        c.insets = new Insets(0,0,10,0);
        c.gridy = 6;
        CreateSchedPanel.add(SerialTextField,c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 7;
        CreateSchedPanel.add(LimitDayLabel,c);

        c.insets = new Insets(0,0,10,0);
        c.gridy = 8;
        CreateSchedPanel.add(LimitDayTextField,c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 9;
        CreateSchedPanel.add(LimitNoonLabel,c);

        c.insets = new Insets(0,0,10,0);
        c.gridy = 10;
        CreateSchedPanel.add(LimitNoonTextField,c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 11;
        CreateSchedPanel.add(LimitNightLabel,c);

        c.insets = new Insets(0,0,10,0);
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
        LayeredPaneArea.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        LayeredPaneArea.setBounds(320, 40, 680, 630);
//        LayeredPaneArea.repaint(320, 80, 680, 630);
    }

    private void initComponents()
    {
        this.setSize(dv.FrameWidth(), dv.FrameHeight());
        this.setVisible(true);
        this.setBackground(new Color(dv.ViewBackgroundColor()));
        this.setLayout(null);

        //initSchedFilterPanel
        initSchedFilterPanel();
        this.add(SchedFilterPanel);

        //initRegFilterPanel
        initRegFilterPanel();
        RegFilterButton.setEnabled(false);
        this.add(RegFilterPanel);

        //init CreateNewSchedButton
        initCreateNewSchedButton();
        this.add(CreateNewSchedButton);

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

    /*CONSTRUCTOR*/
    public ManageScheduleView(Organization org)
    {
        orgUser = org;
        initComponents();
    }

    /*ACTION PERFORMED*/
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == SchedFilterButton)
        {
//            CreateSchedPanel = null;
            LayeredPaneArea.removeAll();
            LayeredPaneArea.repaint(320, 40, 680, 630);

            JLabel SchedListLabel = new JLabel("DANH SÁCH LỊCH TIÊM (" + orgUser.getName() + "):");
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
            RegFilterButton.setEnabled(false);
        }

        if (e.getSource() == RegFilterButton)
        {
            LayeredPaneArea.removeAll();

            JLabel RegListLabel = new JLabel("DANH SÁCH CÁC LƯỢT ĐĂNG KÝ:");
            RegListLabel.setBounds(0,0,640,40);
            RegListLabel.setFont(new Font(dv.fontName(), 1, 20));
            RegListLabel.setForeground(new Color(dv.FeatureButtonColor()));
            RegListLabel.setHorizontalAlignment(JLabel.CENTER);

            initRegListPanel(SelectedSched, RegFilterChoice.getSelectedIndex()-1);
            initScrollPaneRegList();

            LayeredPaneArea.add(RegListLabel);
            LayeredPaneArea.add(ScrollPaneRegList);
        }

        if (e.getSource() == CreateNewSchedButton)
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
