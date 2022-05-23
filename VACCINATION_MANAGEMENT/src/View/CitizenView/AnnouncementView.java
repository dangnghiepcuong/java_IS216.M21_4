/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.OrgView;

import Process.DefaultValue;
import Process.Annoucement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author ASUS
 */




public class AnnouncemnetView extends JPanel implements ActionListener {
    
    private JLayeredPane MainLayeredPane;
    private JScrollPane AnnouncementNew;
    private JTextField SearchAnnouncement;
    private JScrollPane Announcement;
    private JPanel MainPane;
    private JPanel FormListPanel;
    private JLayeredPane LayeredPaneArea; 
    
    private JLabel TittleAnnNew;
    
    private JButton SearchButton;
//    private JButton NextButton;
//    private JButton HomeButton;
//    private JButton RefreshButton;
//   
    private DefaultValue dv= new DefaultValue();
    
    public AnnouncemnetView()//Constructor
    {
        this.setSize(1080, 720);
        //this.setSize(1080, 720); --Main View
        //set frame visible on screen
        this.setVisible(true);
        //set frame background color
        //this.setResizable(false);
        this.setBackground(new Color(dv.ViewBackgroundColor()));
        //set layout
        this.setLayout(null);
        
        initMainLayeredPane();
        initMainPane();
        
        initSearchAnnouncement();
        initAnnouncement();
        initSearchButton();
        
        initFormListPanel();
       
        initAnnouncementNew();
        
        this.add(MainLayeredPane);
        MainLayeredPane.add(MainPane,Integer.valueOf(0));
        MainPane.add(AnnouncementNew);
        MainPane.add(SearchAnnouncement);
        MainPane.add(Announcement);
        MainPane.add(SearchButton);
    }
    
    private void initMainLayeredPane() 
    {
        MainLayeredPane=new JLayeredPane();
        MainLayeredPane.setBounds(0,0,1080,720);
        MainLayeredPane.setLayout(null);
        MainLayeredPane.setOpaque(true);
    }
    
    private void initMainPane()
    {
        MainPane=new JPanel();
        MainPane.setBounds(0,0,1080,720);
        MainPane.setLayout(null);
        MainPane.setOpaque(true);
        //this.setDefaultCloseOperation(JPanel.EXIT_ON_CLOSE);
    }
    
    
    private void initSearchButton()
    {
        SearchButton=new JButton();
        ImageIcon SearchButtonIcon=new ImageIcon(getClass().getResource("/Resources/icon/Search Filter Button.png"));
        SearchButton.setIcon(SearchButtonIcon);
        SearchButton.setBounds(70, 110,SearchButtonIcon.getIconWidth() , SearchButtonIcon.getIconHeight());
      
        SearchButton.setContentAreaFilled(false);
        SearchButton.setBorder(null);
        SearchButton.addActionListener(this);
    }
    
    private void initTittleAnnNew()
    {
        TittleAnnNew = new JLabel("GIỚI THIỆU");
        TittleAnnNew.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TittleAnnNew.setForeground(new Color(0x666666));
    }
    
    private void initSearchAnnouncement()//View Search Announcement
    {
        SearchAnnouncement = new JTextField();
        SearchAnnouncement.setBounds(20,60,250 ,40);
        SearchAnnouncement.setLayout(null);
        SearchAnnouncement.setOpaque(true);
        //SearchAnnouncement.setBackground(Color.blue);
        SearchAnnouncement.setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    private JPanel initFormPanel(Annoucement ann)
    {
        JPanel FormPanel = new JPanel();
        FormPanel.setLayout(null);
        FormPanel.setBackground(Color.WHITE);
        
        JLabel TitelLabel=new JLabel(ann.getTitle()+" (ID: "+ann.getID()+")");
        TitelLabel.setFont(new Font(dv.fontName(), 1, 16));
        TitelLabel.setForeground(new Color(dv.BlackTextColor()));
        TitelLabel.setBounds(10, 1, 600, 25);
        TitelLabel.setHorizontalAlignment(JLabel.LEFT);
        
        
        JLabel ORGLabel=new JLabel("Đơn vị: " + ann.getOrg().getName() + " (ID: "+ ann.getOrgID()+")");
        ORGLabel.setFont(new Font(dv.fontName(), 1, 16));
        ORGLabel.setForeground(new Color(dv.BlackTextColor()));
        ORGLabel.setBounds(10, 26, 600, 25);
        ORGLabel.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel PublishDateLabel = new JLabel("Ngày đăng: " + ann.getPublishDate());
        PublishDateLabel.setPreferredSize(new Dimension(200, 30));
        PublishDateLabel.setFont(new Font(dv.fontName(), 0, 16));
        PublishDateLabel.setForeground(new Color(dv.BlackTextColor()));
        PublishDateLabel.setBounds(10, 51, 600, 25);
        PublishDateLabel.setHorizontalAlignment(JLabel.LEFT);

        

        
        FormPanel.add(TitelLabel);
        FormPanel.add(ORGLabel);
        FormPanel.add(PublishDateLabel);
        
        FormPanel.setPreferredSize(new Dimension(350, 100));
        return FormPanel;
    }
    
    private void initFormListPanel()
    {
        FormListPanel= new JPanel();
        FormListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        FormListPanel.setLayout(new FlowLayout());
        
        Annoucement ann;
        int listAnn = 0;
        int i = 0;
        
        String query= "Select Announcement.ID, ORGID, Name, Title, PublishDate "
                    + "From Announcement join Organization on Announcement.OrgID= Organization.ID "
                    + "Order by PublishDate DESC";
        
        Connection connection=null;
        
        try
        {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());           
            
            
            
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                ann=new Annoucement();                
                ann.setID(rs.getString("ID"));
                ann.setOrgID(rs.getString("OrgID"));
                System.out.print("a");
                ann.getOrg().setName(rs.getString("Name"));
                System.out.print("b");
                ann.setTitle(rs.getString("Title"));         
                ann.setPublishDate(LocalDate.parse(rs.getString("PublishDate").substring(0, 10)));
                FormListPanel.add(initFormPanel(ann));
                listAnn += 100;
                i++;
            }
           
        }
        catch(SQLException ex)
        {
            dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
            ex.printStackTrace();
        
            return;
        }
       
        FormListPanel.setPreferredSize(new Dimension(350, listAnn + i * 10));
        
    }
    
    private void initAnnouncementNew()// View Announcement new
    {
        AnnouncementNew =  new JScrollPane(FormListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS , JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      
        AnnouncementNew.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        AnnouncementNew.setBounds(20,170,250 ,500);
    }
    
    
    
    private void initAnnouncement()// view detailed announcement
    {
        Announcement = new JScrollPane(null,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Announcement.setBounds(280,20,770 ,650);
        Announcement.setLayout(null);
        Announcement.setOpaque(true);
        
    }
    
    private void initLayeredPaneArea() // Panel chứa scroll DS tờ khai
    {
        LayeredPaneArea = new JLayeredPane();
        LayeredPaneArea.setLayout(null);
        LayeredPaneArea.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        LayeredPaneArea.setBounds(320, 40, 680, 630);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
