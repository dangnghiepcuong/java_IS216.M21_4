/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI_Announcement;

import Data_Processor.DefaultValue;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

/**
 *
 * @author ASUS
 */




public class AnnouncemnetView extends JFrame implements ActionListener {
    
    private JLayeredPane MainLayeredPane;
    private JScrollPane AnnouncementNew;
    private JLayeredPane SearchAnnouncement;
    private JScrollPane Announcement;
    private JPanel MainPane;
    
    private JButton BackButton;
    private JButton NextButton;
    private JButton HomeButton;
    private JButton RefreshButton;
   
    private DefaultValue dv= new DefaultValue();
    
    public AnnouncemnetView()//Constructor
    {
        this.setSize(1080, 720);
        //this.setSize(1080, 720); --Main View
        //set frame visible on screen
        this.setVisible(true);
        //set frame background color
        this.setResizable(false);
        this.setBackground(new Color(dv.ViewBackgroundColor()));
        //set layout
        this.setLayout(null);
        
        initMainLayeredPane();
        initMainPane();
        initAnnouncementNew();
        initSearchAnnouncement();
        initAnnouncement();
        initButtonBack();
        initButtonNext();
        initButtonRefresh();
        
        this.add(MainLayeredPane);
        MainLayeredPane.add(MainPane,Integer.valueOf(0));
        MainPane.add(AnnouncementNew);
        MainPane.add(SearchAnnouncement);
        MainPane.add(Announcement);
        MainPane.add(BackButton);
        MainPane.add(NextButton);
        MainPane.add(RefreshButton);
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void initAnnouncementNew()// View Announcement new
    {
        AnnouncementNew = new JScrollPane(null,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        AnnouncementNew.setBounds(20,150,250 ,520);
        AnnouncementNew.setLayout(null);
        AnnouncementNew.setOpaque(true);
        //AnnouncementNew.setBackground(Color.red);
    }
    
    private void initSearchAnnouncement()//View Search Announcement
    {
        SearchAnnouncement = new JLayeredPane();
        SearchAnnouncement.setBounds(20,90,250 ,50);
        SearchAnnouncement.setLayout(null);
        SearchAnnouncement.setOpaque(true);
        //SearchAnnouncement.setBackground(Color.blue);
        SearchAnnouncement.setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    private void initAnnouncement()// view detailed announcement
    {
        Announcement = new JScrollPane();
        Announcement.setBounds(280,20,770 ,650);
        Announcement.setLayout(null);
        Announcement.setOpaque(true);
        
    }
    
    private void initButtonBack()
    {
        BackButton = new JButton();
        ImageIcon BackButtonIcon= new ImageIcon(getClass().getResource("/Data_Processor/icon/Back Button_2.png"));
        BackButton.setIcon(BackButtonIcon);
        
        BackButton.setBounds(20, 20,BackButtonIcon.getIconWidth(),BackButtonIcon.getIconHeight());
        BackButton.setContentAreaFilled(false);
        BackButton.setBorder(null);
    }
    
    private void initButtonNext()
    {
        NextButton = new JButton();
        ImageIcon NextButtonIcon= new ImageIcon(getClass().getResource("/Data_Processor/icon/NextButton.png"));
        NextButton.setIcon(NextButtonIcon);
        
        NextButton.setBounds(70, 20,NextButtonIcon.getIconWidth(),NextButtonIcon.getIconHeight());
        NextButton.setContentAreaFilled(false);
        NextButton.setBorder(null);
    }
    
    private void initButtonRefresh()
    {
        RefreshButton = new JButton();
        ImageIcon RefreshButtonIcon= new ImageIcon(getClass().getResource("/Data_Processor/icon/Refresh.png"));
        RefreshButton.setIcon(RefreshButtonIcon);
        
        RefreshButton.setBounds(120, 20,RefreshButtonIcon.getIconWidth(),RefreshButtonIcon.getIconHeight());
        RefreshButton.setContentAreaFilled(false);
        RefreshButton.setBorder(null);
    }
    
    private void initButtonHome()
    {}

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
