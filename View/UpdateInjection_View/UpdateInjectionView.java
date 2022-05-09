/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI_UpdateInjection;

import Data_Processor.Account;
import java.security.cert.Certificate;
import Data_Processor.DefaultValue;
import Data_Processor.Organization;
import Data_Processor.Person;
import Data_Processor.Schedule;
import Data_Processor.RegisteredScheds;
import GUI_ManageVaccination.ManageVaccinationView;
import GUI_SearchOrg.SearchOrgView;


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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import javax.swing.BorderFactory;

/**
 *
 * @author ASUS
 */
public class UpdateInjectionView extends JPanel implements ActionListener{
    
    /*Main GUI*/
    private JLayeredPane MainLayeredPane;
    private JPanel MainPanel;

    private JLayeredPane InfoLayeredPane;
    private JLabel InfoBackground;
    private JLabel NameLabel;
    private JLabel LocationLabel;
    
    private JScrollPane ScrollPaneRegList;
    
    private JLayeredPane InfoInjectionPanel; 
    private JLayeredPane LayerPanel; 
    private JButton UpLoadImageButton;
    private JButton PhotoImageButton;
    private JButton ContinuteButton;
    private JButton ConfirmButton;
    private JButton CancelButton;
    
    private JLayeredPane SchedAttendance;

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
    private Organization organization = new Organization();
    private RegisteredScheds register=new RegisteredScheds();
    private Schedule schedule = new Schedule();

    /*Other Views*/
    private SearchOrgView searchOrgView;
    private ManageVaccinationView manageVaccinationView;
    
    //Stored Sum_Injection
    private int Total_Injection;
    
    
   public UpdateInjectionView(String Username)
   {

        //set frame size
        this.setSize(dv.FrameWidth(), dv.FrameHeight());
        //this.setSize(1080, 720); --Main View
        //set frame visible on screen
        this.setVisible(true);
        //set frame background color
        this.setBackground(new Color(dv.ViewBackgroundColor()));
        //set layout
        this.setLayout(null);
        
       
        String query = "select * "
                        + "from PERSON join Register on Person.ID=Register.PersonalID "
                        + "where PERSON.Phone = '" +  Username + "'"
                        + "Order by SchedID DESC";
        
        String query1 = "select count(*) as Total "
                        + "from PERSON join Register on Person.ID=Register.PersonalID "
                        + "where PERSON.Phone = '" +  Username + "' and Status = 2";
                      
              
        try {
            
            
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            
            PreparedStatement st1 = connection.prepareStatement(query1);
            ResultSet rs1 = st1.executeQuery(query1);
            

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

            register.setDoseType(rs.getString("Dosetype"));
            
            rs1.next();
            Total_Injection=rs1.getInt("Total");
          

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        initMainLayeredPane();
        initMainPanel();
        initInfoLayerPanel();   
        initInfoInjectionPanel(Username);
        initLayerPanel();
        
        initScrollPaneRegList();
        
        this.add(MainLayeredPane);
        this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
        MainLayeredPane.add(MainPanel,Integer.valueOf(0));   
        MainPanel.add(InfoLayeredPane);
        //MainPanel.add(InfoInjectionPanel);
        MainPanel.add(LayerPanel);
        
        MainPanel.add(ScrollPaneRegList);
        
                
        
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
        InfoLayeredPane.setBounds(0,0,450, dv.FrameHeight());
        InfoLayeredPane.setLayout(null);
        InfoLayeredPane.setOpaque(true);
        //InfoLayeredPane.setBackground(Color.red);
               
   }
   
   
   public void initLayerPanel()
   {
        LayerPanel = new JLayeredPane();
        LayerPanel.setBounds(450,0,712, 140);
        LayerPanel.setLayout(null);
        LayerPanel.setOpaque(true);
        //LayerPanel.setBackground(Color.blue);
        
        JLabel InfoLabel=new JLabel("CẬP NHẬT THÔNG TIN TIÊM CHỦNG");
        InfoLabel.setBounds(0, 20, 630, 100);
        InfoLabel.setFont(new Font("SVN-Arial",Font.BOLD, 24));
        InfoLabel.setHorizontalAlignment(JLabel.CENTER);
        InfoLabel.setForeground(new Color(dv.FeatureButtonColor()));
        //InfoLabel.setBackground(Color.blue);
        
        LayerPanel.add(InfoLabel);
   }
   
   
   private void initInfoInjectionPanel(String Username)
   {
        InfoInjectionPanel = new JLayeredPane();
        InfoInjectionPanel.setBounds(400,100,600, dv.FrameHeight()-200);
        InfoInjectionPanel.setLayout(null);
        InfoInjectionPanel.setOpaque(true);
        InfoInjectionPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        InfoInjectionPanel.setBackground(new Color(dv.ViewBackgroundColor()));
        
        
        //InfoInjectionPanel.setBorder(border);
        
        String query = "select *" +
                        "from Register, Schedule, Organization, PERSON\n" +
                        "where Person.ID = Register.PersonalID and\n" +
                        "      register.schedid=Schedule.id and\n" +
                        "      Schedule.OrgID=Organization.ID and\n" +
                        "      PERSON.Phone = '" +  Username + "' and Status = 0";
        try {
            
            
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            
            
            rs.next();
            organization.setName(rs.getString("Name"));
            organization.setID(rs.getString("ID"));
            schedule.setVaccineID(rs.getString("VaccineID"));
            schedule.setSerial(rs.getString("Serial"));
            
            register.getOrg().setProvince(rs.getString("Province"));
            register.getOrg().setDistrict(rs.getString("District"));
            register.getOrg().setTown(rs.getString("Town"));
            register.getOrg().setStreet(rs.getString("Street"));
            register.getSched().setOnDate(rs.getString("OnDate").substring(0,10));           
            register.setStatus(rs.getInt("Status"));
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        


        JLabel Name=new JLabel("Họ tên: "+personalUser.getFullName());
        Name.setBounds(50,30,360,35);
        Name.setFont(new Font("SVN-Arial",Font.PLAIN,20));
        Name.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel sID=new JLabel("CMND/CCCD: "+personalUser.getID());
        sID.setBounds(50,65,360,35);
        sID.setFont(new Font("SVN-Arial",Font.PLAIN,20));
        sID.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel SDT=new JLabel("SĐT: "+personalUser.getPhone());
        SDT.setBounds(50,100,360,35);
        SDT.setFont(new Font("SVN-Arial",Font.PLAIN,20));
        SDT.setHorizontalAlignment(JLabel.LEFT);

        JLabel NameOrg=new JLabel("Tên đơn vị: " + organization.getName());
        NameOrg.setBounds(50, 135, 712, 35);
        NameOrg.setFont(new Font("SVN-Arial",Font.PLAIN, 20));
        NameOrg.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel IDOrg=new JLabel("Mã đơn vị: " + organization.getID());
        IDOrg.setBounds(50, 170, 712, 35);
        IDOrg.setFont(new Font("SVN-Arial",Font.PLAIN, 20));
        IDOrg.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel Vaccine=new JLabel("Vaccine: " + schedule.getVaccineID()+" - "+schedule.getSerial());
        Vaccine.setBounds(50, 205, 356, 35);
        Vaccine.setFont(new Font("SVN-Arial",Font.PLAIN, 20));
        Vaccine.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel doseType=new JLabel("Loại: "+dv.getDoseTypeName(register.getDoseType()));
        doseType.setBounds(406, 205, 356, 35);
        doseType.setFont(new Font("SVN-Arial",Font.PLAIN,20));
        doseType.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel Address = new JLabel("Đ/c: " + dv.getProvinceName(register.getOrg().getProvince())  + ", "
                + register.getOrg().getDistrict() + ", " + register.getOrg().getTown() + ", " + register.getOrg().getStreet());  
        Address.setBounds(50,240,712,35);
        Address.setFont(new Font("SVN-Arial",Font.PLAIN, 20));
        Address.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel OnDateTime = new JLabel("Lịch tiêm ngày: " + register.getSched().getOnDate()
                + "          Buổi: " + dv.getTimeName(register.getTime())  + "          STT: " + register.getNO());
        OnDateTime.setFont(new Font("SVN-Arial",Font.PLAIN, 20));
        OnDateTime.setBounds(50,275,712,35);
        OnDateTime.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel Status = new JLabel("Tình trạng: "+ dv.getStatusName(register.getStatus()));
        Status.setFont(new Font("SVN-Arial",Font.PLAIN, 20));
        Status.setBounds(50,310,712,35);
        Status.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel Type=new JLabel("Giấy chứng nhận tiêm chủng vaccine: ");
        Type.setBounds(50,345,360,35);
        Type.setFont(new Font("SVN-Arial",Font.PLAIN,20));
        Type.setHorizontalAlignment(JLabel.LEFT);
        
        
        UpLoadImageButton = new JButton();
        ImageIcon UploadImage = new ImageIcon(getClass().getResource("/Data_Processor/icon/image-gallery.png"));
        UpLoadImageButton.setIcon(UploadImage);
        UpLoadImageButton.setBounds(dv.AlignLeft()+350, 330, 50, 50);
        UpLoadImageButton.setBorder(null);
        UpLoadImageButton.setContentAreaFilled(false);
        
        PhotoImageButton = new JButton();
        ImageIcon PhotoImage = new ImageIcon(getClass().getResource("/Data_Processor/icon/add-photo.png"));
        PhotoImageButton.setIcon(PhotoImage);
        PhotoImageButton.setBounds(dv.AlignLeft(), 375, dv.FieldWidth(), UploadImage.getIconHeight());
        PhotoImageButton.setBorder(null);
        PhotoImageButton.setContentAreaFilled(false);
        
        
        ContinuteButton = new JButton();
        ImageIcon ContinuteImage = new ImageIcon(getClass().getResource("/Data_Processor/icon/OK Button.png"));
        ContinuteButton.setIcon(ContinuteImage);
        ContinuteButton.setBounds(dv.AlignLeft()+150, 450, dv.FieldWidth(), ContinuteImage.getIconHeight());
        ContinuteButton.setBorder(null);
        ContinuteButton.setContentAreaFilled(false);
        
        ConfirmButton = new JButton();
        ImageIcon ConfirmImage = new ImageIcon(getClass().getResource("/Data_Processor/icon/Confirm Button.png"));
        ConfirmButton.setIcon(ConfirmImage);
        ConfirmButton.setBounds(dv.AlignLeft()+145, 450, dv.FieldWidth(), ConfirmImage.getIconHeight());
        ConfirmButton.setBorder(null);
        ConfirmButton.setContentAreaFilled(false);
        ConfirmButton.addActionListener(this);
        
        JLabel Warning=new JLabel("<html>Khai báo thông tin sai là vi phạm pháp luật Việt Nam và có thể xử lý hình sự.");
        //Warning.setText("");
        Warning.setBounds(50,380,440,60);
        Warning.setFont(new Font("SVN-Arial",Font.ITALIC,18));
        Warning.setHorizontalAlignment(JLabel.CENTER);
        Warning.setForeground(Color.red);
        
        
        
        //Text
        InfoInjectionPanel.add(Name,Integer.valueOf(2));
        InfoInjectionPanel.add(sID,Integer.valueOf(2));
        InfoInjectionPanel.add(SDT,Integer.valueOf(2));        
        InfoInjectionPanel.add(NameOrg,Integer.valueOf(2));
        InfoInjectionPanel.add(IDOrg,Integer.valueOf(2));
        InfoInjectionPanel.add(Vaccine,Integer.valueOf(2));
        InfoInjectionPanel.add(doseType,Integer.valueOf(2));
        InfoInjectionPanel.add(OnDateTime,Integer.valueOf(2));
        InfoInjectionPanel.add(Address,Integer.valueOf(2));
        InfoInjectionPanel.add(Status,Integer.valueOf(2));       
        InfoInjectionPanel.add(Type,Integer.valueOf(1));
        InfoInjectionPanel.add(Warning,Integer.valueOf(1));
          
        //Button
        InfoInjectionPanel.add(UpLoadImageButton,Integer.valueOf(1));    
        InfoInjectionPanel.add(ConfirmButton,Integer.valueOf(2));
       
        
        
       
   }
   
   
   private void initScrollPaneRegList()//1
    {
        ScrollPaneRegList = new JScrollPane(InfoInjectionPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //set Bounds
        ScrollPaneRegList.setBounds(450,140,600, dv.FrameHeight()-215);; //320 40
        //ScrollPaneRegList.setBackground(Color.yellow);
    }
   
   
   

   
   
   
  

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == ConfirmButton)
        {
            if ( dv.popupConfirmOption(null, "Xác nhận cập nhật giấy chứng nhận mũi tiêm?", "Xác nhận?") == 0)
                dv.popupOption(null, "Cập nhật thành công!", "Thông báo!", 0);
            else
                return;
        }
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
   
}
