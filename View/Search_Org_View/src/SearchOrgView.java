/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

/**
 *
 * @author NghiepCuong
 */
public class SearchOrgView extends JFrame implements ActionListener
{
    private JLabel ProvinceLabel;
    private JLabel DistrictLabel;
    private JLabel TownLabel;
    private Choice DistrictChoice;
    private Choice TownChoice;
    private Choice ProvinceChoice;
    private JButton SearchOrgButton;
    private JScrollPane ScrollPaneArea;
    private JPanel OrgPanel[] = new JPanel[10000];
    private DefaultValue dv = new DefaultValue();
    private JPanel OrgListPanel;
    private Organization org[] = new Organization[10000];

    private void initProvinceLabel()
    {
        //create new label
        ProvinceLabel = new JLabel();

        //set position and area
        ProvinceLabel.setBounds(dv.AlignLeft(), 10, dv.LabelWidth(), dv.LabelHeigth());

        //set label text style
        ProvinceLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        //set label text color
        ProvinceLabel.setForeground(new Color(0x666666));

        //set label text
        ProvinceLabel.setText("Tỉnh/thành phố:");

        //set label size
        ProvinceLabel.setSize(dv.FieldWidth(),dv.FieldHeigth());
    }

    private void initProvinceChoice()
    {
        //create Username text field
        ProvinceChoice = new Choice();

        //set position
        ProvinceChoice.setBounds(dv.AlignLeft(), 40, dv.FieldWidth(), dv.FieldHeigth());

        //set lít font
        ProvinceChoice.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));

        //set lít color
        ProvinceChoice.setForeground(new Color(0x333333));

         //set lít background color
        ProvinceChoice.setBackground(Color.WHITE);

        //set choice
        ProvinceChoice.add("*");
        ProvinceChoice.add("Bình Dương");
        ProvinceChoice.add("Hồ Chí Minh");
        ProvinceChoice.add("Hà Nội");
    }

    private void initDistrictLabel()
    {
        //create
        DistrictLabel = new JLabel();

        //set position and area
        DistrictLabel.setBounds(dv.AlignLeft(), 80, dv.LabelWidth(), dv.LabelHeigth());

        //set text style
        DistrictLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        //set text color
        DistrictLabel.setForeground(new Color(0x666666));

        //set text content
        DistrictLabel.setText("Quận/Huyện:");

        //set size
        DistrictLabel.setSize(dv.LabelWidth(), dv.LabelHeigth());
    }

    private void initDistrictChoice()
    {
        //create
        DistrictChoice = new Choice();

        //set position
        DistrictChoice.setBounds(dv.AlignLeft(), 110, dv.FieldWidth(), dv.FieldHeigth());

        //set font
        DistrictChoice.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));

        //set text color
        DistrictChoice.setForeground(new Color(0x333333));

        //set background color
        DistrictChoice.setBackground(Color.WHITE);


        //set choice
        DistrictChoice.add("*");
        DistrictChoice.add("Dầu Tiếng");
        DistrictChoice.add("Thuận An");
        DistrictChoice.add("Dĩ An");
        DistrictChoice.add("Thủ Đức");
        DistrictChoice.add("Thanh Xuân");
    }

    private void initTownLabel()
    {
        //create
        TownLabel = new JLabel();

        //set label position and frame area
        TownLabel.setBounds(dv.AlignLeft(), 150, dv.LabelWidth(), dv.LabelHeigth());

        //set label text style
        TownLabel.setFont(new Font("SVN-Arial", 0, dv.LabelFontSize()));

        //set label text color
        TownLabel.setForeground(new Color(0x666666));

        //set label size
        TownLabel.setSize(dv.LabelWidth(), dv.LabelHeigth());

        //set label text
        TownLabel.setText("Xã/phường/thị trấn:");
    }

    private void initTownChoice()
    {
        //create
        TownChoice = new Choice();

        //set position
        TownChoice.setBounds(dv.AlignLeft(), 180, dv.FieldWidth(), dv.FieldHeigth());

        //set text color
        TownChoice.setForeground(new Color(0x333333));

        //set font
        TownChoice.setFont(new Font("SVN-Arial", Font.PLAIN, dv.LabelFontSize()));

        //set background color
        TownChoice.setBackground(Color.WHITE);

        //set choice
        TownChoice.add("*");
        TownChoice.add("Dầu Tiếng");
        TownChoice.add("Lái Thiêu");
        TownChoice.add("Đông Hòa");
        TownChoice.add("Linh Trung");
        TownChoice.add("Trúc Bạch");
    }

    private void initSearchOrgButton() 
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        //create login button
        SearchOrgButton = new JButton();

        //set no border
        SearchOrgButton.setBorder(null);

        SearchOrgButton.setContentAreaFilled(false);

        //create an icon
        ImageIcon LoginIcon = new ImageIcon(getClass().getResource("icon/Search.png"));

        //set label icon
        SearchOrgButton.setIcon(LoginIcon);

        SearchOrgButton.setBounds(dv.AlignLeft(), 250, dv.FieldWidth(), LoginIcon.getIconHeight());

        SearchOrgButton.addActionListener(this);
    }

    private void initOrgPanel(int i)
    {
        //Org info
        JLabel OrgName = new JLabel("Tên đơn vị: " + org[i].getName());
        OrgName.setFont(new Font("SVN-Arial", 3, 18));
        OrgName.setBounds(30,1,605,30);
        OrgName.setHorizontalAlignment(JLabel.LEFT);
        OrgName.setBorder(dv.border());

        JLabel OrgProvince = new JLabel("Tỉnh/TP: " + org[i].getProvince());
        OrgProvince.setFont(new Font("SVN-Arial", 0, 16));
        OrgProvince.setBounds(30,32,250,25);
        OrgProvince.setHorizontalAlignment(JLabel.LEFT);
        OrgProvince.setBorder(dv.border());

        JLabel OrgDistrict = new JLabel("Quận/Huyện: " + org[i].getDistrict());
        OrgDistrict.setFont(new Font("SVN-Arial", 0, 16));
        OrgDistrict.setBounds(30, 32+25+2,350,25);
        OrgDistrict.setHorizontalAlignment(JLabel.LEFT);
        OrgDistrict.setBorder(dv.border());

        JLabel OrgTown  = new JLabel("Xã/phường/thị trấn: " + org[i].getTown());
        OrgTown.setFont(new Font("SVN-Arial", 0, 16));
        OrgTown.setBounds(30,(32+25+2)+25+2,350,25);
        OrgTown.setHorizontalAlignment(JLabel.LEFT);
        OrgTown.setBorder(dv.border());

        JLabel OrgStreet  = new JLabel("Đ/c: " + org[i].getStreet());
        OrgStreet.setFont(new Font("SVN-Arial", 0, 16));
        OrgStreet.setBounds(285,32,350,25);
        OrgStreet.setHorizontalAlignment(JLabel.LEFT);
        OrgStreet.setBorder(dv.border());

        JLabel Org_avaiScheds = new JLabel("Số lịch tiêm hiện có: ");
        Org_avaiScheds.setFont(new Font("SVN-Arial", 0, 16));
        Org_avaiScheds.setBounds(385,(32+25)+2,250,25);
        Org_avaiScheds.setHorizontalAlignment(JLabel.LEFT);
        Org_avaiScheds.setBorder(dv.border());

        JLabel Org_Scheds = new JLabel("Số lịch tiêm đã tổ chức: ");
        Org_Scheds.setFont(new Font("SVN-Arial", 0, 16));
        Org_Scheds.setBounds(385,((32+25)+2)+25+2,250,25);
        Org_Scheds.setHorizontalAlignment(JLabel.LEFT);
        Org_Scheds.setBorder(dv.border());

        //create OrgPanel Panel
        OrgPanel[i] = new JPanel();

        //set layout
        OrgPanel[i].setLayout(null);

        OrgPanel[i].setPreferredSize(new Dimension(660,120));

        //set Background color
        OrgPanel[i].setBackground(Color.WHITE);

        OrgPanel[i].add(OrgName);
        OrgPanel[i].add(OrgProvince);
        OrgPanel[i].add(OrgDistrict);
        OrgPanel[i].add(OrgTown);
        OrgPanel[i].add(OrgStreet);
        OrgPanel[i].add(Org_avaiScheds);
        OrgPanel[i].add(Org_Scheds);
    }

    private void initOrgListPanel(int nORG)
    {
        OrgListPanel = new JPanel();

        OrgListPanel.setPreferredSize(new Dimension(680, 120*nORG));

        OrgListPanel.setLayout((new FlowLayout()));

        for (int i = 0; i < nORG; i++)
        {
            initOrgPanel(i);
            OrgListPanel.add(OrgPanel[i]);
        }

   }

    private void initScrollPaneArea(int nORG)
    {
        initOrgListPanel(nORG);

        //create ScrollPaneArea Panel
        ScrollPaneArea = new JScrollPane(OrgListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //set Bounds
        ScrollPaneArea.setBounds(320, 40, 680, 630);
    }

    private void initFrameComponent()
    {      
        //Frame
        //set frame title
        this.setTitle("Tìm kiếm đơn vị tiêm chủng");
        
        //set frame size
        this.setSize(dv.FrameWidth(), dv.FrameHeigth());
        //this.setSize(1080, 720); --Main View
        
        //set do not allow frame resizing
        this.setResizable(false);
        
        //set frame visible on screen
        this.setVisible(true);
        
        //set frame close on X button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //set frame background color
        this.getContentPane().setBackground(new Color(0xFCFCFC));
      
        this.setLayout(null);
        
        //init ProvinceLabel
        initProvinceLabel();
        this.add(ProvinceLabel);

        //init ProvinceChoice
        initProvinceChoice();
        this.add(ProvinceChoice);

        //init DistrictLabel;
        initDistrictLabel();
        this.add(DistrictLabel);

        //init DistrictChoice
        initDistrictChoice();
        this.add(DistrictChoice);

        //init TownLabel;
        initTownLabel();
        this.add(TownLabel);

        //init TownChoice
        initTownChoice();
        this.add(TownChoice);

        //init SearchOrgButton
        initSearchOrgButton();
        this.add(SearchOrgButton);


    }

    public SearchOrgView()
    {
        initFrameComponent();
        this.validate();

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Pressed SearchOrgButton
        if (e.getSource() == SearchOrgButton)
        {
            String query = "";

            //Select out the code of chosen province
            String ProvinceCode = "";

            query = "select Code from REGION where REGION.Name = '"
                    + ProvinceChoice.getSelectedItem() + "'";

            try
            {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                //Statement st = connection.createStatement();
                PreparedStatement st = connection.prepareStatement(query);

                ResultSet rs = st.executeQuery(query);
                while(rs.next())
                    ProvinceCode = rs.getString("Code");
                System.out.println(ProvinceCode);
            }
            catch (SQLException exception)
            {
                exception.printStackTrace();
            }


            //Select out the specified ORGs
            query = "select ID, Name, Province, District, Town, Street, COUNT(SCHED.ID)"
                    + " from ORGANIZATION ORG, SCHEDULE SCHED"
                    + " where ORG.ID = SCHED.OrgID";

            if (ProvinceChoice.getSelectedIndex() > 0)
                query = query + " and ORG.Province = '" + ProvinceCode + "' ";
            else
                query = query + " and ORG.Province like '%'  ";

            if (DistrictChoice.getSelectedIndex() > 0)
                query = query + " and ORG.District = '" + DistrictChoice.getSelectedItem() + "' ";
            else
                query = query + " and ORG.District like '%' ";

            if (TownChoice.getSelectedIndex() > 0)
                query = query + " and ORG.Town = '" + TownChoice.getSelectedItem() + "'";
            else
                query = query + " and ORG.Town like '%'";

            query += " group by ORG.ID";

            System.out.println(query);

            try
            {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                //Statement st = connection.createStatement();

                PreparedStatement st = connection.prepareStatement(query);

                ResultSet rs = st.executeQuery(query);

                int i = 0, n= 0;
                while (rs.next())
                {
                    org[i] = new Organization();
                    org[i].setID(rs.getString("ID"));
                    org[i].setName(rs.getString("Name"));
                    org[i].setProvince(rs.getString("Province"));
                    org[i].setDistrict(rs.getString("District"));
                    org[i].setTown(rs.getString("Town"));
                    org[i].setStreet(rs.getString("Street"));
                    i++;
                }
                n = i;

                //init ScrollPaneArea
                initScrollPaneArea(n);
                this.add(ScrollPaneArea);

                this.validate();
            }
            catch (SQLException exception)
            {
                exception.printStackTrace();
            }

        }
    }

}
