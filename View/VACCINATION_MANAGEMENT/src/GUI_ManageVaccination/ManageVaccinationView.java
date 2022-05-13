package GUI_ManageVaccination;

import Data_Processor.DefaultValue;
import Data_Processor.Person;
import Data_Processor.RegisteredScheds;
import Data_Processor.Schedule;
import GUI_SearchOrg.SearchOrgView;

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

    private JLabel RegFilterLabel;
    private Choice RegFilterChoice;
    private JButton FilterButton;
    private JPanel RegFilterPanel;

    private JScrollPane ScrollPaneRegList;
    private JPanel RegListPanel;

    private JLayeredPane LayeredPaneArea;

    public Person getPersonalUser() {
        return personalUser;
    }

    public void setPersonalUser(Person personalUser) {
        this.personalUser = personalUser;
    }

    /*
    *   INITIALIZE THE REGISTION FILTER PANEL
    *   - LABEL
    *   - CHOCIE
    *   - BUTTON: SELECT
    * */

    private void initRegFilterLabel()
    {
        RegFilterLabel = new JLabel();
        RegFilterLabel.setBounds(0, 0, dv.LabelWidth()+50, dv.LabelHeight());
        RegFilterLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        RegFilterLabel.setForeground(new Color(0x666666));
        RegFilterLabel.setText("Bộ lọc:");
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
        RegFilterChoice.add("Đã đăng ký");
        RegFilterChoice.add("Đã điểm danh");
        RegFilterChoice.add("Đã tiêm");
        RegFilterChoice.add("Đã hủy");
    }

    private void initFilterButton()
    {
        FilterButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Magnifying Glass Button_1.png"));
        FilterButton.setIcon(SearchIcon);

        FilterButton.setBounds(0, 110, dv.FieldWidth(), SearchIcon.getIconHeight());
        FilterButton.setBorder(null);
        FilterButton.setContentAreaFilled(false);

        FilterButton.addActionListener(this);
    }

    private void initRegFilterPanel()
    {
        initRegFilterLabel();
        initRegFilterChoice();
        initFilterButton();

        RegFilterPanel = new JPanel();
        RegFilterPanel.setBounds(dv.AlignLeft(), dv.AlignTop(), dv.LabelWidth()+50, 110 + 56);
        RegFilterPanel.setLayout(null);
        RegFilterPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        RegFilterPanel.add(RegFilterLabel);
        RegFilterPanel.add(RegFilterChoice);
        RegFilterPanel.add(FilterButton);
    }

    /*
     *       INITIALIZE THE LIST OF REGISTIONS OF THE CITIZEN
     *       - SCROLLPANE:
     *           + PANEL: LIST OF REGISTIONS
     *               - PANELS: REGISTIONS
     *                   + LABELS
     * */
    private JPanel initRegPanel(RegisteredScheds Reg)
    {
        //Org info
        JLabel OrgName = new JLabel("Đơn vị: " + Reg.getOrg().getName());
        OrgName.setFont(new Font(dv.fontName(), 3, 18));
        OrgName.setForeground(new Color(dv.FeatureButtonColor()));
        OrgName.setBounds(30,1,605,30);
        OrgName.setHorizontalAlignment(JLabel.LEFT);
        //OrgName.setBorder(dv.border());

        JLabel Address = new JLabel("Đ/c: " + dv.getProvinceName(Reg.getOrg().getProvince())  + ", "
                + Reg.getOrg().getDistrict() + ", " + Reg.getOrg().getTown() + ", " + Reg.getOrg().getStreet());
        Address.setFont(new Font(dv.fontName(), 2, 16));
        Address.setForeground(new Color(dv.BlackTextColor()));
        Address.setBounds(30,32,600,25);
        Address.setHorizontalAlignment(JLabel.LEFT);
        //OrgName.setBorder(dv.border());

        JLabel OnDateTime = new JLabel("Lịch tiêm ngày: " + Reg.getSched().getOnDate()
                + "          Buổi: " + dv.getTimeName(Reg.getTime())  + "          STT: " + Reg.getNO());
        OnDateTime.setFont(new Font(dv.fontName(), 1, 16));
        OnDateTime.setForeground(new Color(dv.BlackTextColor()));
        OnDateTime.setBounds(30,32+25+2,600,25);
        OnDateTime.setHorizontalAlignment(JLabel.LEFT);
        //OnDate.setBorder(dv.border());

        JLabel Vaccine = new JLabel("Vaccine: " + Reg.getSched().getVaccineID() + " - " + Reg.getSched().getSerial());
        Vaccine.setFont(new Font(dv.fontName(), 0, 16));
        Vaccine.setForeground(new Color(dv.BlackTextColor()));
        Vaccine.setBounds(30, 32+25*2+2,250,25);
        Vaccine.setHorizontalAlignment(JLabel.LEFT);
        //VaccineID.setBorder(dv.border());

        JLabel Status = new JLabel("Tình trạng: " + dv.getStatusName(Reg.getStatus()));
        Status.setFont(new Font(dv.fontName(), 0, 16));
        Status.setForeground(new Color(dv.BlackTextColor()));
        Status.setBounds(270, 32+25*2+2,250,25);
        Status.setHorizontalAlignment(JLabel.LEFT);
        //VaccineID.setBorder(dv.border());

        JPanel RegPanel = new JPanel();

        RegPanel.setLayout(null);
        RegPanel.setPreferredSize(new Dimension(640,120));
        //set Background color
        RegPanel.setBackground(Color.WHITE);

        RegPanel.add(OrgName);
        RegPanel.add(Address);
        RegPanel.add(OnDateTime);
        RegPanel.add(Vaccine);
        RegPanel.add(Status);

        return RegPanel;
    }

    private void initRegListPanel(int StatusFilter)
    {
        RegListPanel = new JPanel();
        RegListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        RegListPanel.setLayout(new FlowLayout());

        int i = 0;

        String query = "select DoseType, Time, NO, Status, Image, OnDate, VaccineID, Serial, Name, Province, District, Town, Street" +
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

        query += " order by OnDate desc, Status";

        System.out.println(query);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                RegisteredScheds Reg = new RegisteredScheds();
                Reg.setDoseType(rs.getString("DoseType"));
                Reg.setTime(rs.getInt("Time"));
                Reg.setNO(rs.getInt("NO"));
                Reg.setStatus(rs.getInt("Status"));
                Reg.setImage(rs.getBytes("Image"));
                Reg.getSched().setOnDate(rs.getString("OnDate").substring(0,10));
                Reg.getSched().setVaccineID(rs.getString("VaccineID"));
                Reg.getSched().setSerial(rs.getString("Serial"));
                Reg.getOrg().setName(rs.getString("Name"));
                Reg.getOrg().setProvince(rs.getString("Province"));
                Reg.getOrg().setDistrict(rs.getString("District"));
                Reg.getOrg().setTown(rs.getString("Town"));
                Reg.getOrg().setStreet(rs.getString("Street"));
                RegListPanel.add(initRegPanel(Reg));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int nRegs = i;

        RegListPanel.setPreferredSize(new Dimension(660, 120*nRegs +nRegs*10));
    }

    private void initScrollPaneRegList()
    {
        ScrollPaneRegList = new JScrollPane(RegListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneRegList.setBounds(0, 40, 680, 590); //320 40
        ScrollPaneRegList.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
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

        //init RegFilterPanel
        initRegFilterPanel();
        this.add(RegFilterPanel);

        //init RegList
        JLabel RegListLabel = new JLabel("DANH SÁCH LỊCH TIÊM ĐÃ ĐĂNG KÝ (" + personalUser.getFullName() + "):");
        RegListLabel.setBounds(0,0,640,40);
        RegListLabel.setFont(new Font(dv.fontName(), 1, 20));
        RegListLabel.setForeground(new Color(dv.FeatureButtonColor()));
        RegListLabel.setHorizontalAlignment(JLabel.CENTER);

        initRegListPanel(0);
        initScrollPaneRegList();

        //init LayeredPaneArea
        initLayeredPaneArea();

        LayeredPaneArea.add(RegListLabel, Integer.valueOf(0));
        LayeredPaneArea.add(ScrollPaneRegList, Integer.valueOf(0));
        this.add(LayeredPaneArea);

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());
    }

    /*CONSTRUCTOR*/
    public ManageVaccinationView(Person person)
    {
        personalUser = person;
        initComponents();
    }

    /*ACTION PERFORMED*/
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == FilterButton)
        {
            LayeredPaneArea.removeAll();

            initRegListPanel(RegFilterChoice.getSelectedIndex());
            initScrollPaneRegList();

            LayeredPaneArea.add(ScrollPaneRegList);
            LayeredPaneArea.repaint(320, 40, 680, 630);
        }


    }

}
