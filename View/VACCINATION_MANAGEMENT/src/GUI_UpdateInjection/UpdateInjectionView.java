package GUI_UpdateInjection;


import Data_Processor.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author ASUS
 */
public class UpdateInjectionView extends JPanel implements ActionListener{
    
    /*Main GUI*/
    private JLayeredPane MainLayeredPane;
    private JPanel MainPanel;

    private JLayeredPane InfoLayeredPane;
    
    private JScrollPane ScrollPaneRegList;
    
    private JLayeredPane InfoInjectionPanel; 
    private JLayeredPane LayerPanel; 
    private JButton UpLoadImageButton;
    private JButton ConfirmButton;
    
    /*Data Stored Class*/
    private DefaultValue dv = new DefaultValue();
    private Person personalUser = new Person();
    private RegisteredScheds Reg=new RegisteredScheds();

    //Stored Sum_Injection
    private int Total_Injection;
    private byte[] ImageInjection;
    
    Connection connection;
    private Image img;
    private File file;
    private FileInputStream input=null;

    
   public UpdateInjectionView(Person person) throws IOException
   {
       personalUser = person;

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
                        + "where PERSON.Phone = '" +  personalUser.getPhone() + "'"
                        + "Order by SchedID DESC";
        
        String query1 = "select count(*) as Total "
                        + "from PERSON join Register on Person.ID=Register.PersonalID "
                        + "where PERSON.Phone = '" +  personalUser.getPhone() + "' and Status = 1";
                      
              
        try {
            
            
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            
            PreparedStatement st1 = connection.prepareStatement(query1);
            ResultSet rs1 = st1.executeQuery(query1);
            

            rs.next();

            Reg.setDoseType(rs.getString("Dosetype"));
            
            rs1.next();
            Total_Injection=rs1.getInt("Total");
          

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        initMainLayeredPane();
//        initMainPanel();
          
        initInfoInjectionPanel(personalUser);
        initLayerPanel();
        initScrollPaneRegList();
        initInfoLayerPanel(); 
        
        this.add(MainLayeredPane);
        this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
        MainLayeredPane.add(MainPanel,Integer.valueOf(0));
        MainPanel.add(InfoLayeredPane);
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
        

        //Load Image
            if(Reg.getImage() != null)
            {
         
                ImageIcon imgIcon=new ImageIcon(Reg.getImage());
                
                Image Img= ImageHelper.reSize(imgIcon.getImage(),379,505);
                
                JLabel Image=new JLabel(new ImageIcon(Img));
                
                Image.setBounds(60,140,379,505);
                Image.setHorizontalAlignment(JLabel.LEFT);
                InfoLayeredPane.add(Image,Integer.valueOf(2));

            }
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
        InfoLabel.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        InfoLabel.setHorizontalAlignment(JLabel.CENTER);
        InfoLabel.setForeground(new Color(dv.FeatureButtonColor()));
        //InfoLabel.setBackground(Color.blue);

        LayerPanel.add(InfoLabel);
   }
   
   
   private void initInfoInjectionPanel(Person personalUser) throws IOException
   {
        InfoInjectionPanel = new JLayeredPane();
        InfoInjectionPanel.setBounds(400,100,600, dv.FrameHeight()-200);
        InfoInjectionPanel.setLayout(null);
        InfoInjectionPanel.setOpaque(true);
        InfoInjectionPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        InfoInjectionPanel.setBackground(new Color(dv.ViewBackgroundColor()));
        
        
        //InfoInjectionPanel.setBorder(border);
        
        String query = "select * " +
                        "from REGISTER REG, SCHEDULE SCHED, ORGANIZATION ORG " +
                        "where REG.PersonalID = '" + personalUser.getID() + "' and" +
                        "      REG.SchedID = SCHED.ID and " +
                        "      SCHED.OrgID = ORG.ID and " +
                        "      Status = 1";
        try {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            
            
            rs.next();
            Reg.getSched().setID(rs.getString("ID"));
            Reg.getSched().setVaccineID(rs.getString("VaccineID"));

            Reg.getSched().setSerial("Serial");
            Reg.getOrg().setName("Name");
            Reg.getOrg().setProvince(rs.getString("Province"));
            Reg.getOrg().setDistrict(rs.getString("District"));
            Reg.getOrg().setTown(rs.getString("Town"));
            Reg.getOrg().setStreet(rs.getString("Street"));
            Reg.getSched().setOnDate(rs.getString("OnDate").substring(0,10));
            Reg.setStatus(rs.getInt("Status"));
            Reg.setImage(rs.getBytes("Image"));

        } catch (SQLException e) {
            dv.popupOption(null,"Bạn không có mũi tiêm nào chưa được cập nhật!", "Thông báo", 0);
            e.printStackTrace();
            return;
        }

        JLabel Name=new JLabel("Họ tên: "+personalUser.getFullName());
        Name.setBounds(50,30,360,35);
        Name.setFont(new Font(dv.fontName(),Font.PLAIN,20));
        Name.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel sID=new JLabel("CMND/CCCD: "+personalUser.getID());
        sID.setBounds(50,65,360,35);
        sID.setFont(new Font(dv.fontName(),Font.PLAIN,20));
        sID.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel SDT=new JLabel("SĐT: "+personalUser.getPhone());
        SDT.setBounds(50,100,360,35);
        SDT.setFont(new Font(dv.fontName(),Font.PLAIN,20));
        SDT.setHorizontalAlignment(JLabel.LEFT);

        JLabel NameOrg=new JLabel("Tên đơn vị: " + Reg.getOrg().getName());
        NameOrg.setBounds(50, 135, 712, 35);
        NameOrg.setFont(new Font(dv.fontName(),Font.PLAIN, 20));
        NameOrg.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel IDOrg=new JLabel("Mã lịch tiêm: " + Reg.getSched().getID());
        IDOrg.setBounds(50, 170, 712, 35);
        IDOrg.setFont(new Font(dv.fontName(),Font.PLAIN, 20));
        IDOrg.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel Vaccine=new JLabel("Vaccine: " + Reg.getSched().getVaccineID()+" - "+ Reg.getSched().getSerial());
        Vaccine.setBounds(50, 205, 356, 35);
        Vaccine.setFont(new Font(dv.fontName(),Font.PLAIN, 20));
        Vaccine.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel doseType=new JLabel("Loại: "+dv.getDoseTypeName(Reg.getDoseType()));
        doseType.setBounds(406, 205, 356, 35);
        doseType.setFont(new Font(dv.fontName(),Font.PLAIN,20));
        doseType.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel Address = new JLabel("Đ/c: " + dv.getProvinceName(Reg.getOrg().getProvince())  + ", "
                + Reg.getOrg().getDistrict() + ", " + Reg.getOrg().getTown() + ", " + Reg.getOrg().getStreet());
        Address.setBounds(50,240,712,35);
        Address.setFont(new Font(dv.fontName(),Font.PLAIN, 20));
        Address.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel OnDateTime = new JLabel("Lịch tiêm ngày: " + Reg.getSched().getOnDate()
                + "          Buổi: " + dv.getTimeName(Reg.getTime())  + "          STT: " + Reg.getNO());
        OnDateTime.setFont(new Font(dv.fontName(),Font.PLAIN, 20));
        OnDateTime.setBounds(50,275,712,35);
        OnDateTime.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel Status = new JLabel("Tình trạng: "+ dv.getStatusName(Reg.getStatus()));
        Status.setFont(new Font(dv.fontName(),Font.PLAIN, 20));
        Status.setBounds(50,310,712,35);
        Status.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel Type=new JLabel("Giấy chứng nhận tiêm chủng vaccine: ");
        Type.setBounds(50,345,360,35);
        Type.setFont(new Font(dv.fontName(),Font.PLAIN,20));
        Type.setHorizontalAlignment(JLabel.LEFT);
        
        
        UpLoadImageButton = new JButton();
        ImageIcon UploadImage = new ImageIcon(getClass().getResource("/Data_Processor/icon/image-gallery.png"));
        UpLoadImageButton.setIcon(UploadImage);
        UpLoadImageButton.setBounds(dv.AlignLeft()+350, 330, 50, 50);
        UpLoadImageButton.setBorder(null);
        UpLoadImageButton.setContentAreaFilled(false);
        UpLoadImageButton.addActionListener(this);
        
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
        Warning.setFont(new Font(dv.fontName(),Font.ITALIC,18));
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
        
    }
   
   
    private void ActionUpLoadImage()
    {
        JFileChooser chooser=new JFileChooser();
        chooser.setFileFilter(new FileFilter()
        {
                    @Override
                    public boolean accept(File f)
                    {
                        if(f.isDirectory())
                            return true;
                        else
                            return f.getName().toLowerCase().endsWith(".jpg");
                    }   

                    @Override
                    public String getDescription()
                    {
                        return "Image File (*.jpg)";
                    }                   
                }
        );
        if(chooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION)
            return;
       
        
        file=chooser.getSelectedFile();
        try
        {
            ImageIcon imageicon=new ImageIcon(file.getPath());
            img= ImageHelper.reSize(imageicon.getImage(), 379, 505);
            JLabel image = new JLabel(new ImageIcon(img));  
            image.setBounds(60,140,379,505);
            image.setHorizontalAlignment(JLabel.LEFT);
            InfoLayeredPane.add(image,Integer.valueOf(2));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("ERROR1");
            
        }
    }

   
   
   
  

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == UpLoadImageButton)
        {
            //InfoLayeredPane=null;
            InfoLayeredPane.removeAll();
            ActionUpLoadImage();
            System.out.println("16");
        }
        
        
        if (e.getSource() == ConfirmButton)
        {
            System.out.println("1");
            if ( dv.popupConfirmOption(null, "Xác nhận cập nhật giấy chứng nhận mũi tiêm?", "Xác nhận?") == 0)
            {
                try {
                    connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                    String query ="update REGISTER REG " +
                                       "set Image=? " +
                                       "where PersonalID= '" + personalUser.getID() + "'" +
                                        " and SchedID = '"+ Reg.getSched().getID() +"'";
                    PreparedStatement st = connection.prepareStatement(query);

                    input = new FileInputStream(file);

                    st.setBinaryStream(1,input);

                    st.executeUpdate();
                    st.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
                catch (IOException ex) {
                    Logger.getLogger(UpdateInjectionView.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Error");
                }
                dv.popupOption(null, "Cập nhật thành công!", "Thông báo!", 0);
            }
            else
                return;
        }
        
        
        
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
   
}
