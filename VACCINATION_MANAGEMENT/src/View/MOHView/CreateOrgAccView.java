package View.MOHView;

import Process.DefaultValue;
import Process.Organization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;

/**
 *
 * @author NghiepCuong
 */
public class CreateOrgAccView extends JPanel implements ActionListener
{
    private DefaultValue dv = new DefaultValue();

    /*Province Filter Panel*/
    private JPanel ProvinceFilterPanel;
    private JLabel ProvinceFilterLabel;
    private JComboBox ProvinceComboBox;
    private JButton ProvinceFilterButton;

    /*Org List Panel*/
    private JScrollPane ScrollPaneOrgList;
    private JPanel OrgListPanel;

    /*Create Org Acc Button*/
    private JButton AddNewOrgAccButton;
    private JPanel CreateOrgAccPanel;

    private JLayeredPane LayeredPaneArea;

    /*
    *   INITIALIZE THE ORGANIZATION LIST FILTER PANEL
    *   - PANEL:
    *       + LABELS
    *       + ComboBoxS
    *       + BUTTON: SELECT
    * */
    private void initProvinceFilterLabel()
    {
        ProvinceFilterLabel = new JLabel();
        ProvinceFilterLabel.setBounds(0, 0, dv.LabelWidth()+50, dv.LabelHeight());
        ProvinceFilterLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceFilterLabel.setForeground(new Color(0x666666));
        ProvinceFilterLabel.setText("Bộ lọc tỉnh/TP");
        ProvinceFilterLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initProvinceComboBox()
    {
        ProvinceComboBox = new JComboBox();
        ProvinceComboBox.setBounds(0, 40, dv.FieldWidth(), dv.FieldHeight());
        ProvinceComboBox.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        ProvinceComboBox.setForeground(new Color(dv.BlackTextColor()));
        ProvinceComboBox.setBackground(Color.WHITE);

        try {
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            String query = "select distinct ProvinceCode, ProvinceName from REGION order by ProvinceCode";
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            ProvinceComboBox.addItem("");
            while (rs.next())
            {
                ProvinceComboBox.addItem(rs.getString("ProvinceName"));
            }
        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
            ex.printStackTrace();
            return;
        }
    }

    private void initProvinceFilterButton()
    {
        ProvinceFilterButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Resources/icon/Search Filter Button.png"));
        ProvinceFilterButton.setIcon(SearchIcon);

        ProvinceFilterButton.setBounds(0, 110, dv.FieldWidth(), SearchIcon.getIconHeight());
        ProvinceFilterButton.setBorder(null);
        ProvinceFilterButton.setContentAreaFilled(false);

        ProvinceFilterButton.addActionListener(this);
    }

    private void initProvinceFilterPanel()
    {
        initProvinceFilterLabel();
        initProvinceComboBox();
        initProvinceFilterButton();

        ProvinceFilterPanel = new JPanel();
        ProvinceFilterPanel.setBounds(dv.AlignLeft(), 80, dv.LabelWidth()+50, 110 + 56);
        ProvinceFilterPanel.setLayout(null);
        ProvinceFilterPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        ProvinceFilterPanel.add(ProvinceFilterLabel);
        ProvinceFilterPanel.add(ProvinceComboBox);
        ProvinceFilterPanel.add(ProvinceFilterButton);
    }


    /*
    *   INITIALIZE THE LIST OF ORGANIZATION IN A REGION
    *   - SCROLLPANE:
    *       + PANEL:
    *           - PANELS:
    *               + LABELS
    * */
    private JPanel initOrgPanel(Organization Org)
    {

        //Org info
        JLabel OrgName = new JLabel("ID: " + Org.getID() + " - Đơn vị: " + Org.getName());
        OrgName.setFont(new Font(dv.fontName(), 3, 18));
        OrgName.setForeground(new Color(dv.FeatureButtonColor()));
        OrgName.setBounds(30,1,605,30);
        OrgName.setHorizontalAlignment(JLabel.LEFT);
        //OrgName.setBorder(dv.border());

        JLabel OrgProvince = new JLabel("Tỉnh/TP: " + Org.getProvince());
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

        //create OrgPanel Panel
        JPanel OrgPanel = new JPanel();
        //set layout
        OrgPanel.setLayout(null);
        OrgPanel.setPreferredSize(new Dimension(640,120));
        //set Background color
        OrgPanel.setBackground(Color.WHITE);

        OrgPanel.add(OrgName);
        OrgPanel.add(OrgProvince);
        OrgPanel.add(OrgDistrict);
        OrgPanel.add(OrgTown);
        OrgPanel.add(OrgStreet);
        OrgPanel.add(OrgAvaiScheds);

        return OrgPanel;
    }

    private void initOrgListPanel()
    {
        OrgListPanel = new JPanel();
        OrgListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        OrgListPanel.setLayout((new FlowLayout()));

        Organization Org;
        int i = 0;
        int nORG = 0;

        //Select out the specified ORGs
        String query = "select ORG.ID, Name, ProvinceName, DistrictName, TownName, Street, COUNT(SCHED.ID)"
                + " from ORGANIZATION ORG left outer join SCHEDULE SCHED on ORG.ID = SCHED.OrgID"
                + " where ORG.ID != 'MOH'";

        if (ProvinceComboBox.getSelectedIndex() != 0)
            query = query + " and ProvinceName = '" + ProvinceComboBox.getSelectedItem() + "'";
        else
            return;

        query += " and (OnDate > '" + dv.oracleSysdate() + "' or OnDate is null)";
        query += " group by ORG.ID, Name, ProvinceName, DistrictName, TownName, Street";
        query += " order by TO_NUMBER(SUBSTR(ID,3,LENGTH(ID))), ProvinceName, DistrictName, TownName";

        System.out.println(query);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            /*JLabel NumberOfOrg = new JLabel("Số lượng đơn vị: " + nORG);
            NumberOfOrg.setFont(new Font(dv.fontName(), 2, 16));
            NumberOfOrg.setForeground(new Color(dv.BlackTextColor()));
            NumberOfOrg.setPreferredSize(new Dimension(640,25));
            NumberOfOrg.setHorizontalAlignment(JLabel.LEFT);
            OrgListPanel.add(NumberOfOrg);*/

            while (rs.next()) {
                Org = new Organization();
                Org.setID(rs.getString("ID"));
                Org.setName(rs.getString("Name"));
                Org.setProvince(rs.getString("Provincename"));
                Org.setDistrict(rs.getString("DistrictName"));
                Org.setTown(rs.getString("TownName"));
                Org.setStreet(rs.getString("Street"));
                Org.setAvaiScheds(rs.getInt("COUNT(SCHED.ID)"));
                OrgListPanel.add(initOrgPanel(Org));
                i++;
            }
            nORG = i;
            OrgListPanel.setPreferredSize(new Dimension(660, 120 * nORG + nORG * 5));
        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
            ex.printStackTrace();
            return;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void initScrollPaneOrgList()
    {
        ScrollPaneOrgList = new JScrollPane(OrgListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneOrgList.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        ScrollPaneOrgList.setBounds(0, 40, 680, 590); //320 40
        ScrollPaneOrgList.setBorder(null);
    }




    /*
    *   INITIALIZE THE CREATE ORGANIZATION PANEL
    *   - PANEL:
    *       + LABELS
    *       + ComboBoxS
    *       + BUTTON: CONFIRM CREATION
    * */
    private void initAddNewOrgAccButton()
    {
        ImageIcon AddNewOrgAccButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Create New Org Acc Button.png"));
        AddNewOrgAccButton = new JButton();
        AddNewOrgAccButton.setBounds((320-AddNewOrgAccButtonIcon.getIconWidth())/2, 600, AddNewOrgAccButtonIcon.getIconWidth(), AddNewOrgAccButtonIcon.getIconHeight());
        AddNewOrgAccButton.setBorder(null);
        AddNewOrgAccButton.setContentAreaFilled(false);
        AddNewOrgAccButton.setIcon(AddNewOrgAccButtonIcon);
        AddNewOrgAccButton.addActionListener(this);
    }

    private void initCreateOrgAccPanel()
    {
        JLabel CreateSchedLabel = new JLabel("TẠO TÀI KHOẢN ĐƠN VỊ");
        CreateSchedLabel.setPreferredSize(new Dimension(600, 35));
        CreateSchedLabel.setFont(new Font(dv.fontName(), 1, 20));
        CreateSchedLabel.setForeground(new Color(dv.FeatureButtonColor()));
        CreateSchedLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel ProvinceLabel = new JLabel("Tỉnh/thành phố");
        ProvinceLabel.setPreferredSize(new Dimension(200, 30));
        ProvinceLabel.setFont(new Font(dv.fontName(), 0 ,16));
        ProvinceLabel.setForeground(new Color(dv.BlackTextColor()));

        JComboBox ProvinceComboBox = new JComboBox();
        ProvinceComboBox.setPreferredSize(new Dimension(200, 30));
        ProvinceComboBox.setFont(new Font(dv.fontName(), 0, 16));
        ProvinceComboBox.setForeground(new Color(dv.BlackTextColor()));
        ProvinceComboBox.setBackground(Color.WHITE);

        try {
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            String query = "select distinct ProvinceCode, ProvinceName from REGION order by ProvinceCode";
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            ProvinceComboBox.addItem("");
            while (rs.next())
            {
                ProvinceComboBox.addItem(rs.getString("ProvinceName"));
            }
        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
            ex.printStackTrace();
            return;
        }

        JLabel QuantityLabel = new JLabel("Số lượng tài khoản cần tạo");
        QuantityLabel.setPreferredSize(new Dimension(220, 30));
        QuantityLabel.setFont(new Font(dv.fontName(), 0 ,16));
        QuantityLabel.setForeground(new Color(dv.BlackTextColor()));

        JTextField QuantityTextField = new JTextField();
        QuantityTextField.setPreferredSize(new Dimension(200, 30));
        QuantityTextField.setFont(new Font(dv.fontName(), 0 ,16));
        QuantityTextField.setForeground(new Color(dv.BlackTextColor()));

        ActionListener handleConfirmButton = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String InputProvince = String.valueOf(ProvinceComboBox.getSelectedItem());
                String InputQuantity = QuantityTextField.getText();

                if (dv.checkisNumberInputValue(InputQuantity,
                    "Cảnh báo!", "Nhập số cho số lượng!") != -2)
                    return;

                int answer = dv.popupConfirmOption(null,"Xác nhận tạo" + InputQuantity
                        + " tài khoản đơn vị cho tỉnh/thành phố " + ProvinceComboBox.getSelectedItem() + "?", "Xác nhận!");

                if (answer == JOptionPane.YES_OPTION)
                {
                    String plsql = "{call ACC_CREATE_ORG(?,?)}";


                    Connection connection = null;
                    try {
                        connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                        CallableStatement cst = connection.prepareCall(plsql);
                        cst.setString("par_Quantity", InputQuantity);
                        cst.setString("par_ProvinceName", InputProvince);

                        cst.execute();
                    } catch (SQLException ex)
                    {
                        dv.popupOption(null,  ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                        ex.printStackTrace();
                        return;
                    }

                    dv.popupOption(null, "Tạo tài khoản đơn vị thành công!", "Thông báo!", 0);
                }
            }
        };

        KeyListener handleEnterConfirmation = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    String InputProvince = String.valueOf(ProvinceComboBox.getSelectedItem());
                    String InputQuantity = QuantityTextField.getText();

                    if (dv.checkisNumberInputValue(InputQuantity,
                            "Cảnh báo!", "Nhập số cho số lượng!") != -2)
                        return;

                    int answer = dv.popupConfirmOption(null,"Xác nhận tạo" + InputQuantity
                            + " tài khoản đơn vị cho tỉnh/thành phố " + ProvinceComboBox.getSelectedItem() + "?", "Xác nhận!");

                    if (answer == JOptionPane.YES_OPTION)
                    {
                        String plsql = "{call ACC_CREATE_ORG(?,?)}";


                        Connection connection = null;
                        try {
                            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                            CallableStatement cst = connection.prepareCall(plsql);
                            cst.setString("par_Quantity", InputQuantity);
                            cst.setString("par_ProvinceName", InputProvince);

                            cst.execute();
                        } catch (SQLException ex)
                        {
                            dv.popupOption(null,  ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                            ex.printStackTrace();
                            return;
                        }

                        dv.popupOption(null, "Tạo tài khoản đơn vị thành công!", "Thông báo!", 0);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };
        QuantityTextField.addKeyListener(handleEnterConfirmation);

        ImageIcon CreateOrgAccButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Confirm Button.png"));
        JButton CreateOrgAccButton = new JButton();
        CreateOrgAccButton.setPreferredSize(new Dimension(CreateOrgAccButtonIcon.getIconWidth(), CreateOrgAccButtonIcon.getIconHeight()));
        CreateOrgAccButton.setContentAreaFilled(false);
        CreateOrgAccButton.setBorder(null);
        CreateOrgAccButton.setIcon(CreateOrgAccButtonIcon);
        CreateOrgAccButton.addActionListener(handleConfirmButton);

        CreateOrgAccPanel = new JPanel();
        CreateOrgAccPanel.setBounds(0, 0, 660, 630);
        CreateOrgAccPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        CreateOrgAccPanel.setLayout(new GridBagLayout());
        CreateOrgAccPanel.setBorder(dv.border());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;

        c.insets = new Insets(0,0,15,0);
        c.gridy = 0;
        CreateOrgAccPanel.add(CreateSchedLabel, c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 1;
        CreateOrgAccPanel.add(ProvinceLabel,c);

        c.insets = new Insets(0,0,10,0);
        c.gridy = 2;
        CreateOrgAccPanel.add(ProvinceComboBox,c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 3;
        CreateOrgAccPanel.add(QuantityLabel,c);

        c.insets = new Insets(0,0,40,0);
        c.gridy = 4;
        CreateOrgAccPanel.add(QuantityTextField,c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 13;
        CreateOrgAccPanel.add(CreateOrgAccButton, c);
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

        //initProvinceFilterPanel
        initProvinceFilterPanel();
        this.add(ProvinceFilterPanel);

        //init AddNewOrgAccButton
        initAddNewOrgAccButton();
        this.add(AddNewOrgAccButton);

        //init OrgListPanel
        initOrgListPanel();
        initScrollPaneOrgList();

        //init LayeredPaneArea
        JLabel OrgListLabel = new JLabel("DANH SÁCH ĐƠN VỊ");
        OrgListLabel.setBounds(0,0,640,40);
        OrgListLabel.setFont(new Font(dv.fontName(), 1, 20));
        OrgListLabel.setForeground(new Color(dv.FeatureButtonColor()));
        OrgListLabel.setHorizontalAlignment(JLabel.CENTER);

        initLayeredPaneArea();

        LayeredPaneArea.add(OrgListLabel, Integer.valueOf(0));
        LayeredPaneArea.add(ScrollPaneOrgList, Integer.valueOf(0));
        this.add(LayeredPaneArea);

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());
    }

    /*CONSTRUCTOR*/
    public CreateOrgAccView()
    {
        initComponents();
    }

    /*ACTION PERFORMED*/
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == ProvinceFilterButton)
        {
            CreateOrgAccPanel = null;
            LayeredPaneArea.removeAll();
            LayeredPaneArea.repaint(320, 40, 680, 630);

            JLabel OrgListLabel = new JLabel("DANH SÁCH ĐƠN VỊ (" + ProvinceComboBox.getSelectedItem() + ")");
            OrgListLabel.setBounds(0,0,640,40);
            OrgListLabel.setFont(new Font(dv.fontName(), 1, 20));
            OrgListLabel.setForeground(new Color(dv.FeatureButtonColor()));
            OrgListLabel.setHorizontalAlignment(JLabel.CENTER);

            initOrgListPanel();
            initScrollPaneOrgList();

            LayeredPaneArea.add(OrgListLabel, Integer.valueOf(0));
            LayeredPaneArea.add(ScrollPaneOrgList, Integer.valueOf(0));
            LayeredPaneArea.repaint(320, 40, 680, 630);
        }

        if (e.getSource() == AddNewOrgAccButton)
        {
            OrgListPanel = null;
            ScrollPaneOrgList = null;

            LayeredPaneArea.removeAll();

            initCreateOrgAccPanel();

            LayeredPaneArea.add(CreateOrgAccPanel, Integer.valueOf(0));

            LayeredPaneArea.repaint(320, 40, 680, 630);
        }
    }
}
