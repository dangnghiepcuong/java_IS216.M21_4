/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI_UpdateInjection;

import Data_Processor.Account;
import Data_Processor.Certificate;
import Data_Processor.DefaultValue;
import Data_Processor.Person;
import Data_Processor.RegisteredScheds;
import GUI_ManageVaccination.ManageVaccinationView;
import GUI_SearchOrg.SearchOrgView;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class UpdateInjectionView extends JFrame{
    
    /*Main GUI*/
    private JLayeredPane MainLayeredPane;
    private JPanel MainPanel;

    private JLayeredPane InfoLayeredPane;
    private JLabel InfoBackground;
    private JLabel NameLabel;
    private JLabel LocationLabel;

    private JLayeredPane FeatureLayeredPane;
    private JButton InfoSettingButton;
    private JButton SearchButton;
    private JButton NotificationButton;
    private JButton FillFormButton;
    private JButton ManageVaccinationButton;
    private JButton UpdateInjectionButton;
    private JButton CertificateButton;

    private JButton BackButton;
    private JButton LogoutButton;

    /*Data Stored Class*/
    private DefaultValue dv = new DefaultValue();
    private Account userAccount = new Account();
    private Person personalUser = new Person();
    private RegisteredScheds registeredScheds =new RegisteredScheds();
    private Certificate certificate=new Certificate();

    /*Other Views*/
    private SearchOrgView searchOrgView;
    private ManageVaccinationView manageVaccinationView;
    
   public UpdateInjectionView()
   {
       this.setTitle("Quản lý tiêm chủng vaccine Covid-19: Công dân");

        //set frame size
        this.setSize(dv.FrameWidth(), dv.FrameHeight());
        //this.setSize(1080, 720); --Main View

        //set do not allow frame resizing
        this.setResizable(false);

        //set frame visible on screen
        this.setVisible(true);

        //set frame close on X button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set frame background color
        this.setBackground(new Color(dv.ViewBackgroundColor()));

        //set Frame icon
        this.setIconImage(new ImageIcon(getClass().getResource("/Data_Processor/icon/Virus.png")).getImage());

        //set layout
        this.setLayout(null);
        
        userAccount.setUsername("20521890");
        userAccount.setRole(0);

        //String query = "select * from PERSON where PERSON.ID = '" +  userAccount.getUsername() + "'";
        //String query = "select * from PERSON where PERSON.ID = '" +  userAccount.getUsername() + "'";
        String query = "select * from PERSON join Certificate on Person.ID= Certificate.PersonalID where PERSON.ID = '" +  userAccount.getUsername() + "'";

        try {
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet rs = st.executeQuery(query);

            rs.next();
            personalUser.setID(rs.getString("ID"));
            personalUser.setFirstName(rs.getString("FirstName"));
            personalUser.setLastName(rs.getString("LastName"));
            personalUser.setBirthday(rs.getString("Birthday"));
            personalUser.setGender(rs.getInt("Gender"));
            personalUser.setHomeTown(rs.getString("HomeTown"));
            personalUser.setProvince(rs.getString("Province"));
            personalUser.setDistrict(rs.getString("District"));
            personalUser.setTown(rs.getString("Town"));
            personalUser.setStreet(rs.getString("Street"));
            personalUser.setPhone(rs.getString("Phone"));
            personalUser.setEmail(rs.getString("Email"));
            personalUser.setGuardian(rs.getString("Guardian"));
            certificate.setDoses(rs.getInt("Doses"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        initMainLayeredPane();
        initMainPanel();
        
        initInfoLayerPanel();     
        this.add(MainLayeredPane);
        this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
        MainLayeredPane.add(MainPanel,Integer.valueOf(0));   
        MainPanel.add(InfoLayeredPane);
            

        
        
        
   }
   
   private void initMainLayeredPane()
   {
       MainLayeredPane=new JLayeredPane();
       MainLayeredPane.setBounds(0,0,1080,720);
       MainLayeredPane.setLayout(null);
       MainLayeredPane.setOpaque(true);
   }
   
   private void initMainPanel()
   {
       MainPanel=new JPanel();
       MainPanel.setBounds(0,0,dv.FrameWidth(),dv.FrameHeight());
       MainPanel.setLayout(null);
       MainPanel.setOpaque(true);
   }
   
   private void initInfoLayerPanel()
   {
        InfoLayeredPane = new JLayeredPane();
        InfoLayeredPane.setBounds(0,0,dv.FrameWidth()-dv.FrameHeight() + 8, dv.FrameHeight());
        InfoLayeredPane.setLayout(null);
        InfoLayeredPane.setOpaque(true);
        
        initInfoBackground();
        InfoLayeredPane.add(InfoBackground,Integer.valueOf(0));
        
        JLabel InfoLabel=new JLabel("THÔNG TIN TIÊM CHỦNG");
        InfoLabel.setBounds(0, 40, 365, 30);
        InfoLabel.setFont(new Font("SVN-Arial",Font.BOLD, 24));
        InfoLabel.setHorizontalAlignment(JLabel.CENTER);
        //InfoLabel.autoSize(false);
        
        ImageIcon AvatarImage=new ImageIcon(getClass().getResource("/Data_Processor/icon/Avatar.png"));
        JLabel Avatar=new JLabel(AvatarImage);
        Avatar.setBounds(90,100,190,190);
        Avatar.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel Name=new JLabel(personalUser.getFullName());
        Name.setBounds(0,300,360,35);
        Name.setFont(new Font("SVN-Arial",Font.BOLD,24));
        Name.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel sID=new JLabel("CMND/CCCD: "+personalUser.getID());
        sID.setBounds(0,350,360,35);
        sID.setFont(new Font("SVN-Arial",Font.BOLD,20));
        sID.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel doseType=new JLabel("Loại mũi: "+certificate.getDoses());//   dv.getGenderName(personalUser.getGender()));
        doseType.setBounds(0,400,360,35);
        doseType.setFont(new Font("SVN-Arial",Font.BOLD,20));
        doseType.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel AddressInfo = new JLabel(personalUser.getTown()+"-"+personalUser.getDistrict()+"-"+personalUser.getHomeTown());
        AddressInfo.setBounds(0, 450, 360, 35);
        AddressInfo.setFont(new Font("SVN-Arial",Font.BOLD, 20));
        AddressInfo.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon LocationImage = new ImageIcon(getClass().getResource("/Data_Processor/icon/Location.png"));
        JLabel Location = new JLabel(dv.getProvinceName(personalUser.getProvince()));
        Location.setFont(new Font("SVN-Arial",Font.BOLD, 20));
        Location.setIcon(LocationImage);
        Location.setBounds(0,460,360,30);
        Location.setHorizontalAlignment(JLabel.CENTER);
        
        
        InfoLayeredPane.add(InfoLabel,Integer.valueOf(1));
        InfoLayeredPane.add(Avatar,Integer.valueOf(1));
        InfoLayeredPane.add(Name,Integer.valueOf(1));
        InfoLayeredPane.add(sID,Integer.valueOf(1));
        InfoLayeredPane.add(doseType,Integer.valueOf(1));
        InfoLayeredPane.add(AddressInfo,Integer.valueOf(1));
        InfoLayeredPane.add(Location,Integer.valueOf(1));
   }
   
   private void initInfoBackground()
    {
        ImageIcon InfoLayeredPaneBackground = new ImageIcon(getClass().getResource("/Data_Processor/icon/Personal Info Panel.png"));
        InfoBackground = new JLabel(InfoLayeredPaneBackground);
        InfoBackground.setBounds(0,0, dv.FrameWidth()-dv.FrameHeight() + 8, dv.FrameHeight());
        InfoBackground.setHorizontalAlignment(JLabel.LEFT);
    }
}
