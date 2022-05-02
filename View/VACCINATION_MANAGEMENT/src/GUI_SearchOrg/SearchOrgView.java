/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI_SearchOrg;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;

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
    private JPanel OrgListPanel;
    private JPanel OrgPanel[] = new JPanel[100000];

    private void initProvinceLabel()
    {
        //create new label
        ProvinceLabel = new JLabel();

        //set position and area
        ProvinceLabel.setBounds(10, 10, DefaultLabelWidth(), DefaultLabelHeigth());

        //set label text style
        ProvinceLabel.setFont(new Font("SVN-Arial", 0, DefaultLabelFontSize()));

        //set label text color
        ProvinceLabel.setForeground(new Color(0x666666));

        //set label text
        ProvinceLabel.setText("Tỉnh/thành phố:");

        //set label size
        ProvinceLabel.setSize(DefaultFieldWidth(),DefaultFieldHeigth());
    }

    private void initProvinceChoice()
    {
        //create Username text field
        ProvinceChoice = new Choice();

        //set position
        ProvinceChoice.setBounds(10, 40, DefaultFieldWidth(), DefaultFieldHeigth());

        //set lít font
        ProvinceChoice.setFont(new Font("SVN-Arial", Font.PLAIN, DefaultLabelFontSize()));

        //set lít color
        ProvinceChoice.setForeground(new Color(0x333333));

         //set lít background color
        ProvinceChoice.setBackground(Color.WHITE);

        //set choice
        ProvinceChoice.add("");
        ProvinceChoice.add("Bình Dương");
        ProvinceChoice.add("Hồ Chí Minh");
        ProvinceChoice.add("Hà Nội");
    }

    private void initDistrictLabel()
    {
        //create
        DistrictLabel = new JLabel();

        //set position and area
        DistrictLabel.setBounds(10, 80, DefaultLabelWidth(), DefaultLabelHeigth());

        //set text style
        DistrictLabel.setFont(new Font("SVN-Arial", 0, DefaultLabelFontSize()));

        //set text color
        DistrictLabel.setForeground(new Color(0x666666));

        //set text content
        DistrictLabel.setText("Quận/Huyện:");

        //set size
        DistrictLabel.setSize(DefaultLabelWidth(), DefaultLabelHeigth());
    }

    private void initDistrictChoice()
    {
        //create
        DistrictChoice = new Choice();

        //set position
        DistrictChoice.setBounds(10, 110, DefaultFieldWidth(), DefaultFieldHeigth());

        //set font
        DistrictChoice.setFont(new Font("SVN-Arial", Font.PLAIN, DefaultLabelFontSize()));

        //set text color
        DistrictChoice.setForeground(new Color(0x333333));

        //set background color
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
        //create
        TownLabel = new JLabel();

        //set label position and frame area
        TownLabel.setBounds(10, 150, DefaultLabelWidth(), DefaultLabelHeigth());

        //set label text style
        TownLabel.setFont(new Font("SVN-Arial", 0, DefaultLabelFontSize()));

        //set label text color
        TownLabel.setForeground(new Color(0x666666));

        //set label size
        TownLabel.setSize(DefaultLabelWidth(), DefaultLabelHeigth());

        //set label text
        TownLabel.setText("Xã/phường/thị trấn:");
    }

    private void initTownChoice()
    {
        //create
        TownChoice = new Choice();

        //set position
        TownChoice.setBounds(10, 180, DefaultFieldWidth(), DefaultFieldHeigth());

        //set text color
        TownChoice.setForeground(new Color(0x333333));

        //set font
        TownChoice.setFont(new Font("SVN-Arial", Font.PLAIN, DefaultLabelFontSize()));

        //set background color
        TownChoice.setBackground(Color.WHITE);

        //set choice
        TownChoice.add("");
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
        ImageIcon LoginIcon = new ImageIcon(getClass().getResource("Login Button.png"));

        //set label icon
        SearchOrgButton.setIcon(LoginIcon);

        SearchOrgButton.setBounds(35, 250, 150, 49);
    }


    private void initOrgPanel(int i)
    {
        //Org info
        JLabel OrgName = new JLabel("Ten don vi " + i);


        OrgName.setFont(new Font("SVN-Arial", 0, 16));

        //create OrgPanel Panel
        OrgPanel[i] = new JPanel();

        //set alignment
        //OrgPanel[i].setAlignmentX(JLabel.LEFT);

        OrgPanel[i].setPreferredSize(new Dimension(810, 100));

        //set Alignment
        OrgPanel[i].setAlignmentY(Component.BOTTOM_ALIGNMENT);

        //set Background color
        OrgPanel[i].setBackground(Color.WHITE);

        OrgPanel[i].add(OrgName);
    }

    private void initOrgListPanel()
    {
        OrgListPanel = new JPanel(new GridBagLayout());

        OrgListPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        GridBagConstraints controlGrid = new GridBagConstraints();

        for (int i = 0; i<10; i++)
        {
            initOrgPanel(i);
            controlGrid.insets = new Insets(0, 0, 1, 0);
            controlGrid.gridx = 0;
            controlGrid.gridy = i;
            OrgListPanel.add(OrgPanel[i], controlGrid);
        }
    }

    private void initScrollPaneArea()
    {
        initOrgListPanel();

        //create ScrollPaneArea Panel
        ScrollPaneArea = new JScrollPane(OrgListPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //ScrollPaneArea.add(HorizontalScrollBar);

        //set Bounds
        ScrollPaneArea.setBounds(220, 40, 830, 630);

        //set Border
        //ScrollPaneArea.setBorder(border());

        //set Background color
        //ScrollPaneArea.setBackground(Color.WHITE);
    }

    private void initFrameComponent()
    {      
        //Frame
        //set frame title
        this.setTitle("Tìm kiếm đơn vị tiêm chủng");
        
        //set frame size
        this.setSize(DefaultFrameWidth(), DefaultFrameHeigth());
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

        //init ScrollPaneArea
        initScrollPaneArea();
        this.add(ScrollPaneArea);
    }

    public SearchOrgView()
    {
        initFrameComponent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    private int DefaultLabelFontSize()
    {
        return 20;
    }

    private int DefaultLabelWidth()
    {
        return 200;
    }

    private int DefaultLabelHeigth()
    {
        return 30;
    }

    private int DefaultFieldWidth()
    {
        return 200;
    }

    private int DefaultFieldHeigth()
    {
        return 30;
    }

    private int DefaultFrameWidth()
    {
        return 1080;
    }

    private int DefaultFrameHeigth()
    {
        return 720;
    }

    private Border border()
    {
        return BorderFactory.createLineBorder(Color.BLACK);
    }
}
