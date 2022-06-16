package View.CitizenView;

import Process.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author NghiepCuong
 */
public class ManageRegistrationView extends JPanel implements ActionListener
{
    private DefaultValue dv = new DefaultValue();
    private Person personalUser;

    private JLabel RegFilterLabel;
    private JComboBox RegFilterComboBox;
    private JButton FilterButton;
    private JPanel RegFilterPanel;

    private JScrollPane ScrollPaneRegList;
    private JPanel RegListPanel;

    private JLayeredPane LayeredPaneArea;

    /*
    *   INITIALIZE THE REGISTRATION FILTER PANEL
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
        RegFilterLabel.setText("Bộ lọc trạng thái đăng ký");
        RegFilterLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initRegFilterComboBox()
    {
        RegFilterComboBox = new JComboBox();
        RegFilterComboBox.setBounds(0, 40, dv.FieldWidth(), dv.FieldHeight());
        RegFilterComboBox.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        RegFilterComboBox.setForeground(new Color(dv.BlackTextColor()));
        RegFilterComboBox.setBackground(Color.WHITE);

        RegFilterComboBox.addItem("Tất cả");
        RegFilterComboBox.addItem("Đã đăng ký");
        RegFilterComboBox.addItem("Đã điểm danh");
        RegFilterComboBox.addItem("Đã tiêm");
        RegFilterComboBox.addItem("Đã hủy");
    }

    private void initFilterButton()
    {
        FilterButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Resources/icon/Search Filter Button.png"));
        FilterButton.setIcon(SearchIcon);

        FilterButton.setBounds(0, 80, dv.FieldWidth(), SearchIcon.getIconHeight());
        FilterButton.setBorder(null);
        FilterButton.setContentAreaFilled(false);

        FilterButton.addActionListener(this);
    }

    private void initRegFilterPanel()
    {
        initRegFilterLabel();
        initRegFilterComboBox();
        initFilterButton();

        RegFilterPanel = new JPanel();
        RegFilterPanel.setBounds(dv.AlignLeft(), dv.AlignTop(), dv.LabelWidth()+50, 110 + 56);
        RegFilterPanel.setLayout(null);
        RegFilterPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        RegFilterPanel.add(RegFilterLabel);
        RegFilterPanel.add(RegFilterComboBox);
        RegFilterPanel.add(FilterButton);
    }

    /*
     *       INITIALIZE THE LIST OF REGISTRATIONS OF THE CITIZEN
     *       - SCROLLPANE:
     *           + PANEL: LIST OF REGISTRATIONS
     *               - PANELS: REGISTRATIONS
     *                   + LABELS
     * */
    private JPanel initRegPanel(Register Reg)
    {
        JPanel RegPanel = new JPanel();

        RegPanel.setLayout(null);
        RegPanel.setPreferredSize(new Dimension(640, 120));
        RegPanel.setBackground(Color.WHITE);

        JLabel OrgName = new JLabel("Đơn vị: " + Reg.getOrg().getName());
        OrgName.setFont(new Font(dv.fontName(), 3, 18));
        OrgName.setForeground(new Color(dv.FeatureButtonColor()));
        OrgName.setBounds(30, 1, 605, 30);
        OrgName.setHorizontalAlignment(JLabel.LEFT);
        //OrgName.setBorder(dv.border());

        JLabel Address = new JLabel("Đ/c: " + Reg.getOrg().getProvince() + ", "
                + Reg.getOrg().getDistrict() + ", " + Reg.getOrg().getTown() + ", " + Reg.getOrg().getStreet());
        Address.setFont(new Font(dv.fontName(), 2, 16));
        Address.setForeground(new Color(dv.BlackTextColor()));
        Address.setBounds(30, 32, 600, 25);
        Address.setHorizontalAlignment(JLabel.LEFT);
        //OrgName.setBorder(dv.border());

        JLabel OnDateTime = new JLabel("Lịch tiêm ngày: " + Reg.getSched().getOnDate()
                + "          Buổi: " + dv.getTimeName(Reg.getTime()) + "          STT: " + Reg.getNO());
        OnDateTime.setFont(new Font(dv.fontName(), 1, 16));
        OnDateTime.setForeground(new Color(dv.BlackTextColor()));
        OnDateTime.setBounds(30, 32 + 25 + 2, 500, 25);
        OnDateTime.setHorizontalAlignment(JLabel.LEFT);
        //OnDate.setBorder(dv.border());

        JLabel Vaccine = new JLabel("Vaccine: " + Reg.getSched().getVaccineID() + " - " + Reg.getSched().getSerial());
        Vaccine.setFont(new Font(dv.fontName(), 0, 16));
        Vaccine.setForeground(new Color(dv.BlackTextColor()));
        Vaccine.setBounds(30, 32 + 25 * 2 + 2, 250, 25);
        Vaccine.setHorizontalAlignment(JLabel.LEFT);
        //VaccineID.setBorder(dv.border());

        JLabel Status = new JLabel("Tình trạng: " + dv.getStatusName(Reg.getStatus()));
        Status.setFont(new Font(dv.fontName(), 0, 16));
        Status.setForeground(new Color(dv.BlackTextColor()));
        Status.setBounds(270, 32 + 25 * 2 + 2, 250, 25);
        Status.setHorizontalAlignment(JLabel.LEFT);
        //VaccineID.setBorder(dv.border());

        RegPanel.add(OrgName);
        RegPanel.add(Address);
        RegPanel.add(OnDateTime);
        RegPanel.add(Vaccine);
        RegPanel.add(Status);

        JButton UpdateStatusButton = new JButton();
        ActionListener handleUpdate = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (dv.popupConfirmOption(null, "Xác nhận cập nhật trạng thái lượt đăng ký?", "Xác nhận?") != 0)
                    return;

                String plsql = "{call REG_UPDATE_STATUS(?, ?, ?)}";

                try {
                    Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                    CallableStatement cst = connection.prepareCall(plsql);

                    cst.setString("par_PersonalID", personalUser.getID());
                    cst.setString("par_SchedID", Reg.getSched().getID());
                    cst.setInt("par_Status", 3);

                    cst.execute();
                } catch (SQLException ex) {
                    dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                    ex.printStackTrace();
                    return;
                }
                dv.popupOption(null, "Hủy lịch đăng ký tiêm thành công!", "Thông báo!", 0);
                Reg.setStatus(3);
                Status.setText("Tình trạng: " + dv.getStatusName(Reg.getStatus()));
                UpdateStatusButton.setEnabled(false);
                RegPanel.repaint();
            }
        };
        ImageIcon UpdateStatusButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Cancel Reg Button.png"));
        UpdateStatusButton.setForeground(new Color(dv.BlackTextColor()));
        UpdateStatusButton.setBounds(500, 32 * 2 + 5, UpdateStatusButtonIcon.getIconWidth(), UpdateStatusButtonIcon.getIconHeight());
        UpdateStatusButton.setContentAreaFilled(false);
        UpdateStatusButton.setBorder(null);
        UpdateStatusButton.setIcon(UpdateStatusButtonIcon);
        UpdateStatusButton.addActionListener(handleUpdate);
        if (Reg.getStatus() > 1)
            UpdateStatusButton.setEnabled(false);

        RegPanel.add(UpdateStatusButton);


        return RegPanel;
    }

    private void initRegListPanel(int StatusFilter)
    {
        RegListPanel = new JPanel();
        RegListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        RegListPanel.setLayout(new FlowLayout());

        int i = 0;

        String query = "select DoseType, Time, NO, Status, Image, SCHED.ID, OnDate, VaccineID, Serial, Name, ProvinceName, DistrictName, TownName, Street" +
                " from REGISTER REG, SCHEDULE SCHED, ORGANIZATION ORG" +
                " where '" + personalUser.getID() + "' = REG.PersonalID" +
                " and REG.SchedID = SCHED.ID" +
                " and SCHED.OrgID = ORG.ID";

        if (StatusFilter == 1) //select ComboBox: Da dang ky
            query += " and Status = 0";
        if (StatusFilter == 2) //select ComboBox: Da diem danh
            query += " and Status = 1";
        if (StatusFilter == 3) //select ComboBox: Da tiem
            query += " and Status = 2";
        if (StatusFilter == 4) //select ComboBox: Da huy
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
                Register Reg = new Register();
                Reg.setDoseType(rs.getString("DoseType"));
                Reg.setTime(rs.getInt("Time"));
                Reg.setNO(rs.getInt("NO"));
                Reg.setStatus(rs.getInt("Status"));
                Reg.setImage(rs.getBytes("Image"));
                Reg.getSched().setID(rs.getString("ID"));
                Reg.getSched().setOnDate(rs.getString("OnDate").substring(0,10));
                Reg.getSched().setVaccineID(rs.getString("VaccineID"));
                Reg.getSched().setSerial(rs.getString("Serial"));
                Reg.getOrg().setName(rs.getString("Name"));
                Reg.getOrg().setProvince(rs.getString("ProvinceName"));
                Reg.getOrg().setDistrict(rs.getString("DistrictName"));
                Reg.getOrg().setTown(rs.getString("TownName"));
                Reg.getOrg().setStreet(rs.getString("Street"));
                RegListPanel.add(initRegPanel(Reg));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        int nRegs = i;

        RegListPanel.setPreferredSize(new Dimension(660, 120*nRegs +nRegs*5));
    }

    private void initScrollPaneRegList()
    {
        ScrollPaneRegList = new JScrollPane(RegListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneRegList.setBounds(0, 40, 680, 590); //320 40
        ScrollPaneRegList.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        ScrollPaneRegList.setBorder(null);
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
        JLabel RegListLabel = new JLabel("DANH SÁCH LỊCH TIÊM ĐÃ ĐĂNG KÝ (" + personalUser.getFullName() + ")");
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
    public ManageRegistrationView(Person person)
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

            initRegListPanel(RegFilterComboBox.getSelectedIndex());
            initScrollPaneRegList();

            LayeredPaneArea.add(ScrollPaneRegList);
            LayeredPaneArea.repaint(320, 40, 680, 630);
        }


    }

}
