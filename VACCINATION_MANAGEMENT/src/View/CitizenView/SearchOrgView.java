package View.CitizenView;

import Process.*;

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
public class SearchOrgView extends JPanel implements ActionListener
{
    //Enities used to store the selected out data from database in this view
    private DefaultValue dv = new DefaultValue();
    private Person personalUser = new Person();

    //Org Filter Components
    private JPanel OrgFilterPanel;
    private JLabel ProvinceLabel;
    private JLabel DistrictLabel;
    private JLabel TownLabel;
    private Choice DistrictChoice;
    private Choice TownChoice;
    private Choice ProvinceChoice;
    private JButton OrgFilterButton;

    //Schedule Filter Components
    private JPanel SchedFilterPanel;
    private JLabel SchedFilterLabel;
    private Choice SchedFilterChoice;
    private JButton SchedFilterButton;

    private Organization SelectedOrg;

    private JScrollPane ScrollPaneOrgList;
    private JPanel OrgListPanel;

    private JScrollPane ScrollPaneSchedList;
    private JPanel SchedListPanel;

    private JLayeredPane LayeredPaneArea;


    /*
    *   INITIALIZE A FILTER OF ORGANIZATION
    *   - PANEL:
    *       + LABELS
    *       + CHOICES
    *       + BUTTON: SELECT
    */
    private void initProvinceLabel()
    {
        ProvinceLabel = new JLabel();
        ProvinceLabel.setBounds(0, 0, dv.LabelWidth(), dv.LabelHeight());
        ProvinceLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceLabel.setForeground(new Color(0x666666));
        ProvinceLabel.setText("Tỉnh/thành phố");
        ProvinceLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initProvinceChoice()
    {
        ProvinceChoice = new Choice();
        ProvinceChoice.setBounds(0, 30, dv.FieldWidth(), dv.FieldHeight());
        ProvinceChoice.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        ProvinceChoice.setForeground(new Color(dv.BlackTextColor()));
        ProvinceChoice.setBackground(Color.WHITE);

        ProvinceChoice.add(dv.getProvinceName(personalUser.getProvince()));
        ProvinceChoice.add("");
        for (int i = 1; i <= 64; i++)
            if (i != 20 && dv.getProvinceList()[i] != dv.getProvinceName(personalUser.getProvince()))
                ProvinceChoice.add(dv.getProvinceList()[i]);
    }

    private void initDistrictLabel()
    {
        DistrictLabel = new JLabel();
        DistrictLabel.setBounds(0, 70, dv.LabelWidth(), dv.LabelHeight());
        DistrictLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictLabel.setForeground(new Color(0x666666));
        DistrictLabel.setText("Quận/Huyện");
        DistrictLabel.setSize(dv.LabelWidth(), dv.LabelHeight());
    }

    private void initDistrictChoice()
    {
        DistrictChoice = new Choice();
        DistrictChoice.setBounds(0, 100, dv.FieldWidth(), dv.FieldHeight());
        DistrictChoice.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        DistrictChoice.setForeground(new Color(0x333333));
        DistrictChoice.setBackground(Color.WHITE);

        //set choice
        DistrictChoice.add("");
        DistrictChoice.add("Dầu Tiếng");
        DistrictChoice.add("Thuận An");
        DistrictChoice.add("Dĩ An");
        DistrictChoice.add("Thủ Đức");
        DistrictChoice.add("Thanh Xuân");
    }

    private void initTownLabel()
    {
        TownLabel = new JLabel();
        TownLabel.setBounds(0, 140, dv.LabelWidth(), dv.LabelHeight());
        TownLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownLabel.setForeground(new Color(0x666666));
        TownLabel.setSize(dv.LabelWidth(), dv.LabelHeight());
        TownLabel.setText("Xã/phường/thị trấn");
    }

    private void initTownChoice()
    {
        TownChoice = new Choice();
        TownChoice.setBounds(0, 170, dv.FieldWidth(), dv.FieldHeight());
        TownChoice.setForeground(new Color(0x333333));
        TownChoice.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        TownChoice.setBackground(Color.WHITE);

        TownChoice.add("");
        TownChoice.add("Dầu Tiếng");
        TownChoice.add("Lái Thiêu");
        TownChoice.add("Đông Hòa");
        TownChoice.add("Linh Trung");
        TownChoice.add("Trúc Bạch");
    }

    private void initOrgFilterButton() 
    {
        OrgFilterButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Resources/icon/Search Filter Button.png"));
        OrgFilterButton.setIcon(SearchIcon);

        OrgFilterButton.setBounds(0, 210, dv.FieldWidth(), SearchIcon.getIconHeight());
        OrgFilterButton.setBorder(null);
        OrgFilterButton.setContentAreaFilled(false);

        OrgFilterButton.addActionListener(this);
    }

    private void initOrgFilterPanel()
    {
        initProvinceLabel();
        initProvinceChoice();
        initDistrictLabel();
        initDistrictChoice();
        initTownLabel();
        initTownChoice();
        initOrgFilterButton();

        OrgFilterPanel = new JPanel();
        OrgFilterPanel.setBounds(dv.AlignLeft(), dv.AlignTop(), dv.LabelWidth()+50, 270);
        OrgFilterPanel.setLayout(null);
        OrgFilterPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        OrgFilterPanel.add(ProvinceLabel);
        OrgFilterPanel.add(ProvinceChoice);
        OrgFilterPanel.add(DistrictLabel);
        OrgFilterPanel.add(DistrictChoice);
        OrgFilterPanel.add(TownLabel);
        OrgFilterPanel.add(TownChoice);
        OrgFilterPanel.add(OrgFilterButton);
    }


    /*
    *   INITIALIZE A ORGANIZATION LIST PANEL
    *   - SCROLLPANE:
    *       + PANEL: LIST OF ORGANIZATION
    *           - PANELS: ORGANIZATIONS
    *               + LABELS
    * */
    private JPanel initOrgPanel(Organization Org)
    {
        /*
            INITIALIZED THE SPECIFIED SCHEDULE LIST OF THE SELECTED ORGANIZATION
        */
        MouseListener handleMouseAction = new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                LayeredPaneArea.removeAll();

                JLabel SchedListLabel = new JLabel("DANH SÁCH CÁC LỊCH TIÊM " + Org.getName() + ":");
                SchedListLabel.setBounds(0,0,640,40);
                SchedListLabel.setFont(new Font(dv.fontName(), 1, 20));
                SchedListLabel.setForeground(new Color(dv.FeatureButtonColor()));
                SchedListLabel.setHorizontalAlignment(JLabel.CENTER);

                SelectedOrg = Org;
                initSchedListPanel(Org, -1);
                initScrollPaneSchedList();

                LayeredPaneArea.add(SchedListLabel, Integer.valueOf(3));
                LayeredPaneArea.add(ScrollPaneSchedList, Integer.valueOf(3));
                LayeredPaneArea.repaint(320, 40, 680, 630);

                SchedFilterButton.setEnabled(true);

                ScrollPaneOrgList = null;
                OrgListPanel = null;
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        };

        JPanel OrgPanel = new JPanel();
        OrgPanel.setLayout(null);
        OrgPanel.setPreferredSize(new Dimension(640,120));
        OrgPanel.setBackground(Color.WHITE);

        JLabel OrgName = new JLabel("Đơn vị: " + Org.getName());
        OrgName.setFont(new Font(dv.fontName(), 3, 18));
        OrgName.setForeground(new Color(dv.FeatureButtonColor()));
        OrgName.setBounds(30,1,605,30);
        OrgName.setHorizontalAlignment(JLabel.LEFT);
        //OrgName.setBorder(dv.border());

        JLabel OrgProvince = new JLabel("Tỉnh/TP: " + dv.getProvinceName(Org.getProvince()));
        OrgProvince.setFont(new Font(dv.fontName(), 0, 16));
        OrgProvince.setForeground(new Color(dv.BlackTextColor()));
        OrgProvince.setBounds(30,32,250,25);
        OrgProvince.setHorizontalAlignment(JLabel.LEFT);
        //OrgProvince.setBorder(dv.border());

        JLabel OrgDistrict = new JLabel("Quận/Huyện: " + Org.getDistrict());
        OrgDistrict.setFont(new Font(dv.fontName(), 0, 16));
        OrgDistrict.setForeground(new Color(dv.BlackTextColor()));
        OrgDistrict.setBounds(30, 32+25+2,350,25);
        OrgDistrict.setHorizontalAlignment(JLabel.LEFT);
        //OrgDistrict.setBorder(dv.border());

        JLabel OrgTown  = new JLabel("Xã/phường/thị trấn: " + Org.getTown());
        OrgTown.setFont(new Font(dv.fontName(), 0, 16));
        OrgTown.setForeground(new Color(dv.BlackTextColor()));
        OrgTown.setBounds(30,(32+25+2)+25+2,350,25);
        OrgTown.setHorizontalAlignment(JLabel.LEFT);
        //OrgTown.setBorder(dv.border());

        JLabel OrgStreet  = new JLabel("Đ/c: " + Org.getStreet());
        OrgStreet.setFont(new Font(dv.fontName(), 0, 16));
        OrgStreet.setForeground(new Color(dv.BlackTextColor()));
        OrgStreet.setBounds(285,32,350,25);
        OrgStreet.setHorizontalAlignment(JLabel.LEFT);
        //OrgStreet.setBorder(dv.border());

        JLabel OrgAvaiScheds = new JLabel("Số lịch tiêm hiện có: " + Org.getAvaiScheds());
        OrgAvaiScheds.setFont(new Font(dv.fontName(), 0, 16));
        OrgAvaiScheds.setForeground(new Color(dv.BlackTextColor()));
        OrgAvaiScheds.setBounds(385,(32+25)+2,250,25);
        OrgAvaiScheds.setHorizontalAlignment(JLabel.LEFT);
        //OrgAvaiScheds.setBorder(dv.border());

        OrgPanel.add(OrgName);
        OrgPanel.add(OrgProvince);
        OrgPanel.add(OrgDistrict);
        OrgPanel.add(OrgTown);
        OrgPanel.add(OrgStreet);
        OrgPanel.add(OrgAvaiScheds);

        OrgPanel.addMouseListener(handleMouseAction);

        return OrgPanel;
    }

    private void initOrgListPanel()
    {
        OrgListPanel = new JPanel();
        OrgListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        OrgListPanel.setLayout((new FlowLayout()));

        Organization Org;
        int i = 0;

        //Select out the code of chosen province
        String ProvinceCode = "";
        ProvinceCode = dv.getProvinceCode(ProvinceChoice.getSelectedItem());

        //Select out the specified ORGs
        String query = "select ORG.ID, Name, Province, District, Town, Street, COUNT(SCHED.ID)"
                + " from ORGANIZATION ORG left outer join SCHEDULE SCHED on ORG.ID = SCHED.OrgID";

        if (ProvinceChoice.getSelectedIndex() != 1)
            query = query + " where Province = '" + ProvinceCode + "'";
        else
            query = query + " where Province like '%'";

        if (DistrictChoice.getSelectedIndex() > 0)
            query = query + " and District = '" + DistrictChoice.getSelectedItem() + "'";

        if (TownChoice.getSelectedIndex() > 0)
            query = query + " and ORG.Town = '" + TownChoice.getSelectedItem() + "'";

        query += " and (OnDate > '" + dv.oracleSysdate() + "' or OnDate is null)";
        query += " group by ORG.ID, Name, Province, District, Town, Street";
        query += " order by ID, Province, District, Town";

        System.out.println(query);

        try
        {
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next())
            {
                Org = new Organization();
                Org.setID(rs.getString("ID"));
                Org.setName(rs.getString("Name"));
                Org.setProvince(rs.getString("Province"));
                Org.setDistrict(rs.getString("District"));
                Org.setTown(rs.getString("Town"));
                Org.setStreet(rs.getString("Street"));
                Org.setAvaiScheds(rs.getInt("COUNT(SCHED.ID)"));
                OrgListPanel.add(initOrgPanel(Org));
                i++;
            }
            int nORG = i;
            OrgListPanel.setPreferredSize(new Dimension(660, 120*nORG + nORG*5));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
   }

    private void initScrollPaneOrgList()
    {
        ScrollPaneOrgList = new JScrollPane(OrgListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneOrgList.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        ScrollPaneOrgList.setBounds(0, 40, 680, 590); //320-40
    }



    /*
     *   INITIALIZE REGISTER FILTER PANEL
     *   - PANEL:
     *       + LABEL
     *       + CHOICE
     *       + BUTTON: SELECT
     */
    private void initSchedFilterLabel()
    {
        SchedFilterLabel = new JLabel();
        SchedFilterLabel.setBounds(0, 0, dv.LabelWidth()+50, dv.LabelHeight());
        SchedFilterLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        SchedFilterLabel.setForeground(new Color(0x666666));
        SchedFilterLabel.setText("Bộ lọc vaccine Covid-19");
        SchedFilterLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initSchedFilterChoice()
    {
        SchedFilterChoice = new Choice();
        SchedFilterChoice.setBounds(0, 30, dv.FieldWidth(), dv.FieldHeight());
        SchedFilterChoice.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        SchedFilterChoice.setForeground(new Color(dv.BlackTextColor()));

        SchedFilterChoice.add("Tất cả");
        SchedFilterChoice.add("Astra Zeneca");
        SchedFilterChoice.add("Vero Cell (Sinopharm)");
        SchedFilterChoice.add("Sputnik V");
        SchedFilterChoice.add("Corminaty (Pfizer)");
        SchedFilterChoice.add("Moderna");
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
        SchedFilterPanel.setBounds(dv.AlignLeft(), 380, dv.LabelWidth()+50, 125);
        SchedFilterPanel.setLayout(null);
        SchedFilterPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        SchedFilterPanel.add(SchedFilterLabel);
        SchedFilterPanel.add(SchedFilterChoice);
        SchedFilterPanel.add(SchedFilterButton);
    }

    /*
    *   INITIALIZE A SCHEDULE LIST PANEL
    *   - SCROLLPANE:
    *       + PANEL:
    *           - PANELS:
    *               + LABELS
    * */
    private JPanel initSchedPanel(Schedule Sched)
    {
        JPanel SchedPanel = new JPanel();
        SchedPanel.setLayout(null);
        SchedPanel.setPreferredSize(new Dimension(640,120));
        SchedPanel.setBackground(Color.WHITE);

        JLabel OrgName = new JLabel("Tên đơn vị: " + Sched.getOrg().getName());
        OrgName.setFont(new Font(dv.fontName(), 3, 18));
        OrgName.setForeground(new Color(dv.FeatureButtonColor()));
        OrgName.setBounds(30,1,605,30);
        OrgName.setHorizontalAlignment(JLabel.LEFT);
        //OrgName.setBorder(dv.border());

        JLabel OnDate = new JLabel("Lịch tiêm ngày: " + Sched.getOnDate().toString().substring(0,10));
        OnDate.setFont(new Font(dv.fontName(), 0, 16));
        OnDate.setForeground(new Color(dv.BlackTextColor()));
        OnDate.setBounds(30,32,200,25);
        OnDate.setHorizontalAlignment(JLabel.LEFT);
        //OnDate.setBorder(dv.border());

        JLabel VaccineID = new JLabel("Vaccine: " + Sched.getVaccineID());
        VaccineID.setFont(new Font(dv.fontName(), 0, 16));
        VaccineID.setForeground(new Color(dv.BlackTextColor()));
        VaccineID.setBounds(30, 32+25+2,200,25);
        VaccineID.setHorizontalAlignment(JLabel.LEFT);
        //VaccineID.setBorder(dv.border());

        JLabel Serial  = new JLabel("Serial: " + Sched.getSerial());
        Serial.setFont(new Font(dv.fontName(), 0, 16));
        Serial.setForeground(new Color(dv.BlackTextColor()));
        Serial.setBounds(30,(32+25+2)+25+2,200,25);
        Serial.setHorizontalAlignment(JLabel.LEFT);
        //Serial.setBorder(dv.border());

        JLabel DayTime  = new JLabel("Buổi sáng: " + Sched.getDayRegistered() + "/" + Sched.getLimitDay());
        DayTime.setFont(new Font(dv.fontName(), 0, 16));
        DayTime.setForeground(new Color(dv.BlackTextColor()));
        DayTime.setBounds(250,32,150,25);
        DayTime.setHorizontalAlignment(JLabel.LEFT);

        JLabel NoonTime = new JLabel("Buổi trưa: " + Sched.getNoonRegistered() + "/" + Sched.getLimitNoon());
        NoonTime.setFont(new Font(dv.fontName(), 0, 16));
        NoonTime.setForeground(new Color(dv.BlackTextColor()));
        NoonTime.setBounds(250,(32+25)+2,150,25);
        NoonTime.setHorizontalAlignment(JLabel.LEFT);

        JLabel NightTime = new JLabel("Buổi tối: " + Sched.getNightRegistered() + "/" + Sched.getLimitNight());
        NightTime.setFont(new Font(dv.fontName(), 0, 16));
        NightTime.setForeground(new Color(dv.BlackTextColor()));
        NightTime.setBounds(250,(32+25+2)+25+2,150,25);
        NightTime.setHorizontalAlignment(JLabel.LEFT);

        Choice TimeChoice = new Choice();
        TimeChoice.setBounds(470, 32, 80, 30);
        TimeChoice.setFont(new Font(dv.fontName(), 0, 16));
        TimeChoice.setForeground(new Color(dv.BlackTextColor()));
        TimeChoice.add("Sáng");
        TimeChoice.add("Trưa");
        TimeChoice.add("Tối");

        /*
            HANDLE REGISTER A VACCINATION SCHEDULE ACTION
        */
        ActionListener handleSchedRegister = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int answer = dv.popupConfirmOption(null,"Xác nhận đăng ký tiêm chủng?", "Xác nhận!");

                if (answer == JOptionPane.YES_OPTION)
                {
                    String plsql = "{call REG_BEFORE_INSERT_RECORD(?,?,?)}";

                    String plsql2 = "{call REG_INSERT_RECORD(?,?,?,?)}";

                    Connection connection = null;
                    try {
                        connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                        /*
                            CALL PROC TO GET THE RIGHT DOSETYPE OR ASK THE USER IF THIS IS THE 3RD INJECTION
                        */
                        int BoosterAvai = 0;
                        String par_DoseType = "";

                        //CALL PROC
                        CallableStatement cst = connection.prepareCall(plsql);
                        cst.setString("par_PersonalID", personalUser.getID());
                        cst.registerOutParameter("par_BoosterAvai", Types.NUMERIC);
                        cst.registerOutParameter("par_DoseType", Types.VARCHAR);

                        cst.execute();

                        BoosterAvai = cst.getInt("par_BoosterAvai");
                        par_DoseType = cst.getString("par_DoseType");

                        //ASK USER IF THIS IS THE 3RD INJECTION
                        if (BoosterAvai == 1)
                        {
                            answer = dv.popupConfirmOption(null, "Bạn đang đăng ký mũi tiêm thứ 3, " +
                                            "đây là có phải là mũi bổ sung không? (Chọn 'Không' nếu đây là mũi nhắc lại)",
                                    "Chọn loại mũi tiêm đăng ký");

                            if (answer  == 0)
                                par_DoseType = "booster";
                            else if (answer == 1)
                                par_DoseType = "repeat";
                            else
                                return;
                        }

                        /*
                            REGISTER ACTION
                        */
                        CallableStatement cst2 = connection.prepareCall(plsql2);
                        cst2.setString("par_PersonalID", personalUser.getID());
                        cst2.setString("par_SchedID", Sched.getID());
                        cst2.setInt("par_Time", TimeChoice.getSelectedIndex());
                        cst2.setString("par_DoseType", par_DoseType);

                        cst2.execute();
                    } catch (SQLException ex) {
                        dv.popupOption(null,  ex.getMessage(),"Lỗi " + ex.getErrorCode(), 2);
                        ex.printStackTrace();
                        return;
                    }
                }
                else
                    return;

                dv.popupOption(null, "Đăng ký tiêm chủng thành công!", "Thông báo", 0);
            }
        };

        JButton SchedRegisterButton = new JButton();
        ImageIcon SchedRegisterButtonIcon = new ImageIcon(getClass().getResource(
                "/Resources/icon/Sched Register Button.png"));
        SchedRegisterButton.setForeground(new Color(dv.BlackTextColor()));
        SchedRegisterButton.setBounds(470,32*2,
                SchedRegisterButtonIcon.getIconWidth(),SchedRegisterButtonIcon.getIconHeight());
        SchedRegisterButton.setContentAreaFilled(false);
        SchedRegisterButton.setBorder(null);
        SchedRegisterButton.setIcon(SchedRegisterButtonIcon);
        SchedRegisterButton.addActionListener(handleSchedRegister);

        SchedPanel.add(OrgName);
        SchedPanel.add(OnDate);
        SchedPanel.add(VaccineID);
        SchedPanel.add(Serial);
        SchedPanel.add(DayTime);
        SchedPanel.add(NoonTime);
        SchedPanel.add(NightTime);
        SchedPanel.add(TimeChoice);
        SchedPanel.add(SchedRegisterButton);

        return SchedPanel;
    }

    private void initSchedListPanel(Organization Org, int SelectedVaccine)
    {
        /*
                SELECT OUT THE INFO OF THE ORGANIZATION'S SPECIFIED SCHEDULES
        */
        SchedListPanel = new JPanel();
        SchedListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        SchedListPanel.setLayout(new FlowLayout());

        String query = "";

        int nSched = 0;
        int i = 0;

        query = "select *" +
                " from SCHEDULE SCHED" +
                " where OrgID = '" + Org.getID() + "'" +
                " and OnDate >= TO_DATE('" + dv.oracleSysdate() + "')";
        if (SelectedVaccine != -1)
            query += " and VaccineID = '" + dv.getVaccineID(SchedFilterChoice.getSelectedIndex()-1) + "'";
        query += " order by OnDate";

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
//                Sched.getOrg().setID(rs.getString("OrgID"));
                Sched.setOnDate(rs.getString("OnDate"));
                Sched.setVaccineID(rs.getString("VaccineID"));
                Sched.setSerial(rs.getString("Serial"));
                Sched.setLimitDay(rs.getInt("LimitDay"));
                Sched.setLimitNoon(rs.getInt("LimitNoon"));
                Sched.setLimitNight(rs.getInt("LimitNight"));
                Sched.setDayRegistered(rs.getInt("DayRegistered"));
                Sched.setNoonRegistered(rs.getInt("Noonregistered"));
                Sched.setNightRegistered(rs.getInt("NightRegistered"));
                Sched.setOrg(Org);
                SchedListPanel.add(initSchedPanel(Sched));
                i++;
            }
        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
            ex.printStackTrace();
            return;
        }

        nSched = i;

        SchedListPanel.setPreferredSize(new Dimension( 660, 120*nSched + nSched*10));
    }

    private void initScrollPaneSchedList()
    {
        ScrollPaneSchedList = new JScrollPane(SchedListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneSchedList.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        ScrollPaneSchedList.setBounds(0, 40, 680, 590);
    }

    private void initLayeredPaneArea()
    {
        LayeredPaneArea = new JLayeredPane();
        LayeredPaneArea.setLayout(null);
        LayeredPaneArea.setBounds(320, 40, 680, 630);
        LayeredPaneArea.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        LayeredPaneArea.repaint(320, 40, 680, 630);
    }

    private void initComponents()
    {
        this.setSize(dv.FrameWidth(), dv.FrameHeight());
        this.setVisible(true);
        this.setBackground(new Color(dv.ViewBackgroundColor()));
        this.setLayout(null);
        
        //init OrgFilterPanel
        initOrgFilterPanel();
        this.add(OrgFilterPanel);

        //init SchedFilterPanel
        initSchedFilterPanel();
        this.add(SchedFilterPanel);
        SchedFilterButton.setEnabled(false);

        //init LayeredPane
        initLayeredPaneArea();
        this.add(LayeredPaneArea);

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());
    }

    /*CONSTRUCTOR*/
    public SearchOrgView(Person person)
    {
        personalUser = person;
        initComponents();
        this.validate();
    }

    /*ACTION PERFORMED*/
    @Override
    public void actionPerformed(ActionEvent e)
    {
        int n = 0;

        /*
            HANDLE ON SEARCH ORG BUTTON CLICKING
        */
        if (e.getSource() == OrgFilterButton) {
            /*
                SELECT OUT THE INFO OF THE ORGANIZATION'S SPECIFIED SCHEDULES
            */
            LayeredPaneArea.removeAll();

            JLabel OrgListLabel = new JLabel("DANH SÁCH CÁC ĐƠN VỊ TIÊM CHỦNG ("
                    + ProvinceChoice.getSelectedItem() + "-" + DistrictChoice.getSelectedItem() + "-" + TownChoice.getSelectedItem() + "):");
            OrgListLabel.setBounds(0, 0, 640, 40);
            OrgListLabel.setFont(new Font(dv.fontName(), 1, 20));
            OrgListLabel.setForeground(new Color(dv.FeatureButtonColor()));
            OrgListLabel.setHorizontalAlignment(JLabel.CENTER);

            initOrgListPanel();
            initScrollPaneOrgList();

            LayeredPaneArea.add(OrgListLabel, Integer.valueOf(0));

            LayeredPaneArea.add(ScrollPaneOrgList, Integer.valueOf(0));

            SchedListPanel = null;
            ScrollPaneSchedList = null;
            SchedFilterButton.setEnabled(false);
        }

        if (e.getSource() == SchedFilterButton)
        {
            JLabel SchedListLabel = new JLabel("DANH SÁCH CÁC LỊCH TIÊM " + SelectedOrg.getName() + ":");
            SchedListLabel.setBounds(0,0,640,40);
            SchedListLabel.setFont(new Font(dv.fontName(), 1, 20));
            SchedListLabel.setForeground(new Color(dv.FeatureButtonColor()));
            SchedListLabel.setHorizontalAlignment(JLabel.CENTER);

            initSchedListPanel(SelectedOrg, SchedFilterChoice.getSelectedIndex()-1);
            initScrollPaneSchedList();

            LayeredPaneArea.removeAll();

            LayeredPaneArea.add(SchedListLabel);
            LayeredPaneArea.add(ScrollPaneSchedList);
        }
    }

}
