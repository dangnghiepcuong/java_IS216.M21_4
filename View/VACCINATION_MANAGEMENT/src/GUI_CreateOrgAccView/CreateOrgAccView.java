package GUI_CreateOrgAccView;

import Data_Processor.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

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
    private Choice ProvinceFilterChoice;
    private JButton ProvinceFilterButton;

    /*Org List Panel*/
    private JScrollPane ScrollPaneOrgList;
    private JPanel OrgListPanel;
    private JPanel OrgPanel[] = new JPanel[10000];

    /*Create Org Acc Button*/
    private JButton AddNewOrgAccButton;
    private JPanel CreateOrgAccPanel;

    private JLayeredPane LayeredPaneArea;

    /*

*/
    private void initProvinceFilterLabel()
    {
        ProvinceFilterLabel = new JLabel();
        ProvinceFilterLabel.setBounds(0, 0, dv.LabelWidth()+50, dv.LabelHeight());
        ProvinceFilterLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceFilterLabel.setForeground(new Color(0x666666));
        ProvinceFilterLabel.setText("Bộ lọc tỉnh/TP:");
        ProvinceFilterLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initProvinceFilterChoice()
    {
        ProvinceFilterChoice = new Choice();
        ProvinceFilterChoice.setBounds(0, 40, dv.FieldWidth(), dv.FieldHeight());
        ProvinceFilterChoice.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        ProvinceFilterChoice.setForeground(new Color(dv.BlackTextColor()));

        ProvinceFilterChoice.add("");
        for (int i = 1; i<=64; i++)
            ProvinceFilterChoice.add(dv.getProvinceList()[i]);
    }

    private void initProvinceFilterButton()
    {
        ProvinceFilterButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Magnifying Glass Button_1.png"));
        ProvinceFilterButton.setIcon(SearchIcon);

        ProvinceFilterButton.setBounds(0, 110, dv.FieldWidth(), SearchIcon.getIconHeight());
        ProvinceFilterButton.setBorder(null);
        ProvinceFilterButton.setContentAreaFilled(false);

        ProvinceFilterButton.addActionListener(this);
    }

    private void initProvinceFilterPanel()
    {
        initProvinceFilterLabel();
        initProvinceFilterChoice();
        initProvinceFilterButton();

        ProvinceFilterPanel = new JPanel();
        ProvinceFilterPanel.setBounds(dv.AlignLeft(), 80, dv.LabelWidth()+50, 110 + 56);
        ProvinceFilterPanel.setLayout(null);
        ProvinceFilterPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        ProvinceFilterPanel.add(ProvinceFilterLabel);
        ProvinceFilterPanel.add(ProvinceFilterChoice);
        ProvinceFilterPanel.add(ProvinceFilterButton);
    }

    private void initOrgPanel(int i, Organization Org)
    {

        //Org info
        JLabel OrgName = new JLabel("ID: " + Org.getID() + " - Đơn vị: " + Org.getName());
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
    }

    private void initOrgListPanel(String ProvinceCode)
    {
        Organization Org[] = new Organization[10000];
        String query = "";

        int nOrg = 0;
        int i = 0;

        query = "select * from ORGANIZATION ORG" +
                " where ORG.Province = '" + ProvinceCode + "'" +
                " order by ID";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                Org[i] = new Organization();
                Org[i].setID(rs.getString("ID"));
                Org[i].setName(rs.getString("Name"));
                Org[i].setProvince(rs.getString("Province"));
                Org[i].setDistrict(rs.getString("District"));
                Org[i].setTown(rs.getString("Town"));
                Org[i].setNote(rs.getString("Note"));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        nOrg = i;

        OrgListPanel = new JPanel();
        OrgListPanel.setPreferredSize(new Dimension(660, 120*nOrg+nOrg*10));
        OrgListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        OrgListPanel.setLayout(new FlowLayout());

        for (i = 0; i<nOrg; i++)
        {
            initOrgPanel(i, Org[i]);
            OrgListPanel.add(OrgPanel[i]);
        }
    }

    private void initScrollPaneOrgList(String ProvinceCode)
    {
        initOrgListPanel(ProvinceCode);

        ScrollPaneOrgList = new JScrollPane(OrgListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneOrgList.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        ScrollPaneOrgList.setBounds(0, 40, 680, 590); //320 40
    }





    private void initAddNewOrgAccButton()
    {
        ImageIcon AddNewOrgAccButtonIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Create New Org Acc Button.png"));
        AddNewOrgAccButton = new JButton();
        AddNewOrgAccButton.setBounds((320-AddNewOrgAccButtonIcon.getIconWidth())/2, 300, AddNewOrgAccButtonIcon.getIconWidth(), AddNewOrgAccButtonIcon.getIconHeight());
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

        JLabel ProvinceLabel = new JLabel("Tỉnh/thành phố:");
        ProvinceLabel.setPreferredSize(new Dimension(200, 30));
        ProvinceLabel.setFont(new Font(dv.fontName(), 0 ,16));
        ProvinceLabel.setForeground(new Color(dv.BlackTextColor()));

        Choice ProvinceChoice = new Choice();
        ProvinceChoice.setPreferredSize(new Dimension(200, 30));
        ProvinceChoice.setFont(new Font(dv.fontName(), 0, 16));
        ProvinceChoice.setForeground(new Color(dv.BlackTextColor()));

        ProvinceChoice.add("");
        for (int i = 1; i<=64; i++)
            ProvinceChoice.add(dv.getProvinceList()[i]);

        JLabel QuantityLabel = new JLabel("Số lượng tài khoản cần tạo: ");
        QuantityLabel.setPreferredSize(new Dimension(220, 30));
        QuantityLabel.setFont(new Font(dv.fontName(), 0 ,16));
        QuantityLabel.setForeground(new Color(dv.BlackTextColor()));

        JTextField QuantityTextField = new JTextField();
        QuantityTextField.setPreferredSize(new Dimension(200, 30));
        QuantityTextField.setFont(new Font(dv.fontName(), 0 ,16));
        QuantityTextField.setForeground(new Color(dv.BlackTextColor()));

        ActionListener handleCreateOrgAcc = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String InputProvinceCode = dv.getProvinceCode(ProvinceChoice.getSelectedItem());
                String InputQuantity = QuantityTextField.getText();

                if (dv.checkisNumberInputValue(InputQuantity,
                    "Cảnh báo!", "Nhập số cho số lượng!") != -2)
                    return;

                int answer = dv.popupConfirmOption(null,"Xác nhận tạo lịch " + InputQuantity
                        + " tài khoản đơn vị cho tỉnh/thành phố " + ProvinceChoice.getSelectedItem() + "?", "Xác nhận!");

                if (answer == JOptionPane.YES_OPTION)
                {
                    String plsql = "{call ACC_CREATE_ORG(?,?)}";


                    Connection connection = null;
                    try {
                        connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                        CallableStatement cst = connection.prepareCall(plsql);
                        cst.setString("par_Quantity", InputQuantity);
                        cst.setString("par_Province", InputProvinceCode);

                        cst.execute();
                    } catch (SQLException ex)
                    {
                        dv.popupOption(null,  ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                        ex.printStackTrace();
                    }

                    dv.popupOption(null, "Tạo tài khoản đơn vị thành công!", "Thông báo!", 0);
                }
            }
        };

        ImageIcon CreateOrgAccButtonIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Confirm Button.png"));
        JButton CreateOrgAccButton = new JButton();
        CreateOrgAccButton.setPreferredSize(new Dimension(CreateOrgAccButtonIcon.getIconWidth(), CreateOrgAccButtonIcon.getIconHeight()));
        CreateOrgAccButton.setContentAreaFilled(false);
        CreateOrgAccButton.setBorder(null);
        CreateOrgAccButton.setIcon(CreateOrgAccButtonIcon);
        CreateOrgAccButton.addActionListener(handleCreateOrgAcc);

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
        CreateOrgAccPanel.add(ProvinceChoice,c);

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

        //initProvinceFilterPanel
        initProvinceFilterPanel();
        this.add(ProvinceFilterPanel);

        //initAddNewSchedLabel
//        initAddNewSchedLabel();
//        this.add(AddNewSchedLabel);

        //init AddNewOrgAccButton
        initAddNewOrgAccButton();
        this.add(AddNewOrgAccButton);

        //init OrgListPanel
        initOrgListPanel("00");

        //init LayeredPaneArea
        JLabel OrgListLabel = new JLabel("DANH SÁCH ĐƠN VỊ:");
        OrgListLabel.setBounds(0,0,640,40);
        OrgListLabel.setFont(new Font(dv.fontName(), 1, 20));
        OrgListLabel.setForeground(new Color(dv.FeatureButtonColor()));
        OrgListLabel.setHorizontalAlignment(JLabel.CENTER);

        initLayeredPaneArea();

        LayeredPaneArea.add(OrgListLabel, Integer.valueOf(0));
        this.add(LayeredPaneArea);

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());
    }

    public CreateOrgAccView()
    {
        initFrameComponent();
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == ProvinceFilterButton)
        {
            CreateOrgAccPanel = null;
            LayeredPaneArea.removeAll();
            LayeredPaneArea.repaint(320, 40, 680, 630);

            JLabel OrgListLabel = new JLabel("DANH SÁCH ĐƠN VỊ (" + ProvinceFilterChoice.getSelectedItem() + ")");
            OrgListLabel.setBounds(0,0,640,40);
            OrgListLabel.setFont(new Font(dv.fontName(), 1, 20));
            OrgListLabel.setForeground(new Color(dv.FeatureButtonColor()));
            OrgListLabel.setHorizontalAlignment(JLabel.CENTER);

            initScrollPaneOrgList(dv.getProvinceCode(ProvinceFilterChoice.getSelectedItem()));

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