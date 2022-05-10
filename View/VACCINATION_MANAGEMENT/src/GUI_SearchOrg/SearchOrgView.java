package GUI_SearchOrg;

import Data_Processor.*;
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
    private Organization org[] = new Organization[100000];
    private Person personalUser = new Person();

    //Components used in this view
    private JPanel OrgFilterPanel;
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


/*
                                INIT A FILTER OF ORGANIZATION
                                ADD IS TO THE SEARCHORGVIEW PANEL
*/
    private void initProvinceLabel()
    {
        ProvinceLabel = new JLabel();
        ProvinceLabel.setBounds(0, 0, dv.LabelWidth(), dv.LabelHeight());
        ProvinceLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceLabel.setForeground(new Color(0x666666));
        ProvinceLabel.setText("Tỉnh/thành phố:");
        ProvinceLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initProvinceChoice()
    {
        ProvinceChoice = new Choice();
        ProvinceChoice.setBounds(0, 40, dv.FieldWidth(), dv.FieldHeight());
        ProvinceChoice.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        ProvinceChoice.setForeground(new Color(dv.BlackTextColor()));
        ProvinceChoice.setBackground(Color.WHITE);

        ProvinceChoice.add("");
        for (int i = 1; i <= 64; i++)
            if (i != 20)
                ProvinceChoice.add(dv.getProvinceList()[i]);
    }

    private void initDistrictLabel()
    {
        DistrictLabel = new JLabel();
        DistrictLabel.setBounds(0, 80, dv.LabelWidth(), dv.LabelHeight());
        DistrictLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictLabel.setForeground(new Color(0x666666));
        DistrictLabel.setText("Quận/Huyện:");
        DistrictLabel.setSize(dv.LabelWidth(), dv.LabelHeight());
    }

    private void initDistrictChoice()
    {
        DistrictChoice = new Choice();
        DistrictChoice.setBounds(0, 110, dv.FieldWidth(), dv.FieldHeight());
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
        TownLabel.setBounds(0, 150, dv.LabelWidth(), dv.LabelHeight());
        TownLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownLabel.setForeground(new Color(0x666666));
        TownLabel.setSize(dv.LabelWidth(), dv.LabelHeight());
        TownLabel.setText("Xã/phường/thị trấn:");
    }

    private void initTownChoice()
    {
        TownChoice = new Choice();
        TownChoice.setBounds(0, 180, dv.FieldWidth(), dv.FieldHeight());
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

    private void initSearchOrgButton() 
    {
        SearchOrgButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Magnifying Glass Button_1.png"));
        SearchOrgButton.setIcon(SearchIcon);

        SearchOrgButton.setBounds(0, 250, dv.FieldWidth(), SearchIcon.getIconHeight());
        SearchOrgButton.setBorder(null);
        SearchOrgButton.setContentAreaFilled(false);

        SearchOrgButton.addActionListener(this);
    }

    private void initOrgFilterPanel()
    {
        initProvinceLabel();
        initProvinceChoice();
        initDistrictLabel();
        initDistrictChoice();
        initTownLabel();
        initTownChoice();
        initSearchOrgButton();

        OrgFilterPanel = new JPanel();
        OrgFilterPanel.setBounds(dv.AlignLeft(), dv.AlignTop(), dv.LabelWidth()+50, 350);
        OrgFilterPanel.setLayout(null);
        OrgFilterPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        OrgFilterPanel.add(ProvinceLabel);
        OrgFilterPanel.add(ProvinceChoice);
        OrgFilterPanel.add(DistrictLabel);
        OrgFilterPanel.add(DistrictChoice);
        OrgFilterPanel.add(TownLabel);
        OrgFilterPanel.add(TownChoice);
        OrgFilterPanel.add(SearchOrgButton);
    }


/*
                                INIT A ORGANIZATION LIST PANEL
                                ADD IS TO A SCROLLPANE
                                ADD THE SCROLLPANE TO THE LAYEREDPANE
*/
    private void initOrgPanel(int i)
    {
        //Org info
        JLabel OrgName = new JLabel("Tên đơn vị: " + org[i].getName());
        OrgName.setFont(new Font(dv.fontName(), 3, 18));
        OrgName.setForeground(new Color(dv.FeatureButtonColor()));
        OrgName.setBounds(30,1,605,30);
        OrgName.setHorizontalAlignment(JLabel.LEFT);
        //OrgName.setBorder(dv.border());

        JLabel OrgProvince = new JLabel("Tỉnh/TP: " + dv.getProvinceName(org[i].getProvince()));
        OrgProvince.setFont(new Font(dv.fontName(), 0, 16));
        OrgProvince.setForeground(new Color(dv.BlackTextColor()));
        OrgProvince.setBounds(30,32,250,25);
        OrgProvince.setHorizontalAlignment(JLabel.LEFT);
        //OrgProvince.setBorder(dv.border());

        JLabel OrgDistrict = new JLabel("Quận/Huyện: " + org[i].getDistrict());
        OrgDistrict.setFont(new Font(dv.fontName(), 0, 16));
        OrgDistrict.setForeground(new Color(dv.BlackTextColor()));
        OrgDistrict.setBounds(30, 32+25+2,350,25);
        OrgDistrict.setHorizontalAlignment(JLabel.LEFT);
        //OrgDistrict.setBorder(dv.border());

        JLabel OrgTown  = new JLabel("Xã/phường/thị trấn: " + org[i].getTown());
        OrgTown.setFont(new Font(dv.fontName(), 0, 16));
        OrgTown.setForeground(new Color(dv.BlackTextColor()));
        OrgTown.setBounds(30,(32+25+2)+25+2,350,25);
        OrgTown.setHorizontalAlignment(JLabel.LEFT);
        //OrgTown.setBorder(dv.border());

        JLabel OrgStreet  = new JLabel("Đ/c: " + org[i].getStreet());
        OrgStreet.setFont(new Font(dv.fontName(), 0, 16));
        OrgStreet.setForeground(new Color(dv.BlackTextColor()));
        OrgStreet.setBounds(285,32,350,25);
        OrgStreet.setHorizontalAlignment(JLabel.LEFT);
        //OrgStreet.setBorder(dv.border());

        JLabel OrgAvaiScheds = new JLabel("Số lịch tiêm hiện có: " + org[i].getAvaiScheds());
        OrgAvaiScheds.setFont(new Font(dv.fontName(), 0, 16));
        OrgAvaiScheds.setForeground(new Color(dv.BlackTextColor()));
        OrgAvaiScheds.setBounds(385,(32+25)+2,250,25);
        OrgAvaiScheds.setHorizontalAlignment(JLabel.LEFT);
        //OrgAvaiScheds.setBorder(dv.border());


        //create OrgPanel Panel
        OrgPanel[i] = new JPanel();
        //set layout
        OrgPanel[i].setLayout(null);
        OrgPanel[i].setPreferredSize(new Dimension(640,120));
        //set Background color
        OrgPanel[i].setBackground(Color.WHITE);

        OrgPanel[i].add(OrgName);
        OrgPanel[i].add(OrgProvince);
        OrgPanel[i].add(OrgDistrict);
        OrgPanel[i].add(OrgTown);
        OrgPanel[i].add(OrgStreet);
        OrgPanel[i].add(OrgAvaiScheds);
        //OrgPanel[i].add(OrgDetailButton[i]);

        /*
            INITIALIZED THE SPECIFIED SCHEDULE LIST OF THE SELECTED ORGANIZATION
        */
        MouseListener handleMouseAction = new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                LayeredPaneArea.removeAll();

                JLabel SchedListLabel = new JLabel("DANH SÁCH CÁC LỊCH TIÊM " + org[i].getName() + ":");
                SchedListLabel.setBounds(0,0,640,40);
                SchedListLabel.setFont(new Font(dv.fontName(), 1, 20));
                SchedListLabel.setForeground(new Color(dv.FeatureButtonColor()));
                SchedListLabel.setHorizontalAlignment(JLabel.CENTER);

                initScrollPaneSchedList(org[i]);

                LayeredPaneArea.add(SchedListLabel, Integer.valueOf(3));
                LayeredPaneArea.add(ScrollPaneSchedList, Integer.valueOf(3));
                LayeredPaneArea.repaint(320, 40, 680, 630);

                ScrollPaneOrgList = null;
                OrgListPanel = null;
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

        OrgPanel[i].addMouseListener(handleMouseAction);
    }

    private void initOrgListPanel(int nORG)
    {
        OrgListPanel = new JPanel();

        OrgListPanel.setPreferredSize(new Dimension(660, 120*nORG));

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
        ScrollPaneOrgList.setBounds(0, 40, 680, 590); //320-40
    }



/*
                                INIT A SCHEDULE LIST PANEL
                                ADD IS TO A SCROLLPANE
                                ADD THE SCROLLPANE TO THE LAYEREDPANE
*/
    private void initSchedPanel(int i, Schedule sched, Organization SelectedOrg)
    {
        //Org info
        JLabel OrgName = new JLabel("Tên đơn vị: " + SelectedOrg.getName());
        OrgName.setFont(new Font(dv.fontName(), 3, 18));
        OrgName.setForeground(new Color(dv.FeatureButtonColor()));
        OrgName.setBounds(30,1,605,30);
        OrgName.setHorizontalAlignment(JLabel.LEFT);
        //OrgName.setBorder(dv.border());

        JLabel OnDate = new JLabel("Lịch tiêm ngày: " + sched.getOnDate().toString().substring(0,10));
        OnDate.setFont(new Font(dv.fontName(), 0, 16));
        OnDate.setForeground(new Color(dv.BlackTextColor()));
        OnDate.setBounds(30,32,200,25);
        OnDate.setHorizontalAlignment(JLabel.LEFT);
        //OnDate.setBorder(dv.border());

        JLabel VaccineID = new JLabel("Vaccine: " + sched.getVaccineID());
        VaccineID.setFont(new Font(dv.fontName(), 0, 16));
        VaccineID.setForeground(new Color(dv.BlackTextColor()));
        VaccineID.setBounds(30, 32+25+2,200,25);
        VaccineID.setHorizontalAlignment(JLabel.LEFT);
        //VaccineID.setBorder(dv.border());

        JLabel Serial  = new JLabel("Serial: " + sched.getSerial());
        Serial.setFont(new Font(dv.fontName(), 0, 16));
        Serial.setForeground(new Color(dv.BlackTextColor()));
        Serial.setBounds(30,(32+25+2)+25+2,200,25);
        Serial.setHorizontalAlignment(JLabel.LEFT);
        //Serial.setBorder(dv.border());

        JLabel DayTime  = new JLabel("Buổi sáng: " + sched.getDayRegistered() + "/" + sched.getLimitDay());
        DayTime.setFont(new Font(dv.fontName(), 0, 16));
        DayTime.setForeground(new Color(dv.BlackTextColor()));
        DayTime.setBounds(250,32,150,25);
        DayTime.setHorizontalAlignment(JLabel.LEFT);

        JLabel NoonTime = new JLabel("Buổi trưa: " + sched.getNoonRegistered() + "/" + sched.getLimitNoon());
        NoonTime.setFont(new Font(dv.fontName(), 0, 16));
        NoonTime.setForeground(new Color(dv.BlackTextColor()));
        NoonTime.setBounds(250,(32+25)+2,150,25);
        NoonTime.setHorizontalAlignment(JLabel.LEFT);

        JLabel NightTime = new JLabel("Buổi tối: " + sched.getNightRegistered() + "/" + sched.getLimitNight());
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
                        cst.setInt("par_BoosterAvai", BoosterAvai);
                        cst.setString("par_DoseType", par_DoseType);

                        //ASK USER IF THIS IS THE 3RD INJECTION
                        if (BoosterAvai == 1)
                        {
                            answer = dv.popupConfirmOption(null, "Bạn đang đăng ký mũi tiêm thứ 3, đây là mũi bổ sung hay nhắc lại?",
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
                        cst2.setString("par_SchedID", sched.getID());
                        cst2.setInt("par_Time", TimeChoice.getSelectedIndex());
                        cst2.setString("par_DoseType", par_DoseType);

                        cst2.execute();
                    } catch (SQLException ex) {
                        dv.popupOption(null,  ex.getMessage(),"Lỗi " + ex.getErrorCode(), 2);
                        ex.printStackTrace();
                    }
                }

            }
        };

        JButton SchedRegisterButton = new JButton();
        ImageIcon SchedRegisterButtonIcon = new ImageIcon(getClass().getResource(
                "/Data_Processor/icon/Sched Register Button.png"));
        SchedRegisterButton.setForeground(new Color(dv.BlackTextColor()));
        SchedRegisterButton.setBounds(470,32*2,
                SchedRegisterButtonIcon.getIconWidth(),SchedRegisterButtonIcon.getIconHeight());
        SchedRegisterButton.setContentAreaFilled(false);
        SchedRegisterButton.setBorder(null);
        SchedRegisterButton.setIcon(SchedRegisterButtonIcon);
        SchedRegisterButton.addActionListener(handleSchedRegister);

        SchedPanel[i] = new JPanel();

        SchedPanel[i].setLayout(null);
        SchedPanel[i].setPreferredSize(new Dimension(640,120));
        SchedPanel[i].setBackground(Color.WHITE);

        SchedPanel[i].add(OrgName);
        SchedPanel[i].add(OnDate);
        SchedPanel[i].add(VaccineID);
        SchedPanel[i].add(Serial);
        SchedPanel[i].add(DayTime);
        SchedPanel[i].add(NoonTime);
        SchedPanel[i].add(NightTime);
        SchedPanel[i].add(TimeChoice);
        SchedPanel[i].add(SchedRegisterButton);
    }

    private void initSchedListPanel(Organization SelectedOrg)
    {
        /*
                SELECT OUT THE INFO OF THE ORGANIZATION'S SPECIFIED SCHEDULES
        */
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
                sched[i].getOrg().setID(rs.getString("OrgID"));
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

        SchedListPanel.setPreferredSize(new Dimension( 660, 120*nSched + nSched*10));

        SchedListPanel.setLayout(new FlowLayout());

        for (i = 0; i<nSched; i++)
        {
            initSchedPanel(i, sched[i], SelectedOrg);
            SchedListPanel.add(SchedPanel[i]);
        }

    }

    private void initScrollPaneSchedList(Organization SelectedOrg)
    {
        initSchedListPanel(SelectedOrg);

        ScrollPaneSchedList = new JScrollPane(SchedListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneSchedList.setBounds(0, 40, 680, 590);
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
        
        //init OrgFilterPanel
        initOrgFilterPanel();
        this.add(OrgFilterPanel);

        //init LayeredPane
        initLayeredPaneArea();
        this.add(LayeredPaneArea);

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());
    }

    public SearchOrgView(Person person)
    {
        personalUser = person;
        initFrameComponent();
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        int n = 0;

        /*
            HANDLE ON SEARCH ORG BUTTON CLICKING
        */
        if (e.getSource() == SearchOrgButton)
        {
            /*
                SELECT OUT THE INFO OF THE ORGANIZATION'S SPECIFIED SCHEDULES
            */
            String query = "";
            //Select out the code of chosen province
            String ProvinceCode = "";

            ProvinceCode = dv.getProvinceCode(ProvinceChoice.getSelectedItem());

            //Select out the specified ORGs
            query = "select ORG.ID, Name, Province, District, Town, Street, COUNT(SCHED.ID)"
                    + " from ORGANIZATION ORG left join SCHEDULE SCHED on ORG.ID = SCHED.OrgID";

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

            //query += " and OnDate >= '" + dv.sysdate() + "'";
            query += " group by ORG.ID, Name, Province, District, Town, Street";
            query += " order by Province, District, Town";

            System.out.println(query);

            try
            {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                PreparedStatement st = connection.prepareStatement(query);

                ResultSet rs = st.executeQuery(query);

                int i = 0;
                n = 0;

                while (rs.next())
                {
                    org[i] = new Organization();
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

                //clear the Layered Area
                LayeredPaneArea.removeAll();

                //init OrgListLabel
                JLabel OrgListLabel = new JLabel("DANH SÁCH CÁC ĐƠN VỊ TIÊM CHỦNG ("
                        + ProvinceChoice.getSelectedItem() + "-" + DistrictChoice.getSelectedItem() + "-" + TownChoice.getSelectedItem()+"):");
                OrgListLabel.setBounds(0,0,640,40);
                OrgListLabel.setFont(new Font(dv.fontName(), 1, 20));
                OrgListLabel.setForeground(new Color(dv.FeatureButtonColor()));
                OrgListLabel.setHorizontalAlignment(JLabel.CENTER);

                //init Scroll Pane of Orgs
                initScrollPaneOrgList(n);

                //add Label
                LayeredPaneArea.add(OrgListLabel, Integer.valueOf(0));

                //add Scroll Pane of Orgs to TOP of Layered Area
                LayeredPaneArea.add(ScrollPaneOrgList, Integer.valueOf(0));
                //Delete scroll pane of sched
                SchedListPanel = null;
                ScrollPaneSchedList = null;
            }
            catch (SQLException exception)
            {
                exception.printStackTrace();
            }
        }


    }

}
