package View.CitizenView;


import Process.DefaultValue;
import Process.ImageHelper;
import Process.Person;
import Process.Register;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class UpdateInjectionView extends JPanel implements ActionListener{
    
    /*Main GUI*/
    private JLayeredPane MainLayeredPane;
    private JPanel MainPanel;

    private JPanel ImagePanel;
    private JLayeredPane InjectionInfoPanel; 
    private JButton UpLoadImageButton;
    private  JButton RemoveImageButton;
    private JButton ConfirmButton;
    
    /*Data Stored Class*/
    private DefaultValue dv = new DefaultValue();
    private Person personalUser = new Person();
    private Register Reg=new Register();

    private File file;
    private FileInputStream input=null;
   
   private void initMainPanel()
   {
       MainPanel=new JPanel();
       MainPanel.setBounds(0,0,dv.FrameWidth(),dv.FrameHeight());
       MainPanel.setBackground(new Color(dv.ViewBackgroundColor()));
       MainPanel.setLayout(null);
   }
   
   private void initImagePanel()
   {
        ImagePanel = new JPanel();
        ImagePanel.setBounds(40,100,380, dv.FrameHeight() - 200);
        ImagePanel.setLayout(null);
        ImagePanel.setOpaque(true);
        
        //Load taken Image from database
       if(Reg.getImage() != null)
       {
           ImageIcon TakenImage = new ImageIcon(Reg.getImage());
           Image ResizedImg = ImageHelper.reSize(TakenImage.getImage(),
                   380, dv.FrameHeight() - 200);
           JLabel ImagePlace = new JLabel(new ImageIcon(ResizedImg));
           ImagePlace.setBounds(0, 0, 380, dv.FrameHeight() - 200);
           ImagePlace.setHorizontalAlignment(JLabel.LEFT);
           ImagePanel.add(ImagePlace);
       }
   }
   
   
   private void initInjectionInfoPanel(Person personalUser) //throws IOException
   {
       InjectionInfoPanel = new JLayeredPane();
       InjectionInfoPanel.setBounds(425, 100, 600, dv.FrameHeight() - 200);
       InjectionInfoPanel.setLayout(null);
       InjectionInfoPanel.setOpaque(true);
       InjectionInfoPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

       InjectionInfoPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));

       //InjectionInfoPanel.setBorder(border);

       String query = "select SCHED.ID, VaccineID, OnDate, NO, Serial, Name, ProvinceName, DistrictName, TownName, Street, DoseType, Status, Image " +
               "from REGISTER REG, SCHEDULE SCHED, ORGANIZATION ORG " +
               "where REG.PersonalID = '" + personalUser.getID() + "' " +
               "and REG.SchedID = SCHED.ID " +
               "and SCHED.OrgID = ORG.ID " +
               "and Status = 1 ";
       try {
           Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

           PreparedStatement st = connection.prepareStatement(query);
           ResultSet rs = st.executeQuery(query);

           rs.next();
           Reg.getSched().setID(rs.getString("ID"));
           Reg.getSched().setVaccineID(rs.getString("VaccineID"));
           Reg.getSched().setOnDate(rs.getString("OnDate").substring(0, 10));
           Reg.getSched().setSerial(rs.getString("Serial"));
           Reg.setNO(rs.getInt("NO"));
           Reg.getOrg().setName(rs.getString("Name"));
           Reg.getOrg().setProvince(rs.getString("ProvinceName"));
           Reg.getOrg().setDistrict(rs.getString("DistrictName"));
           Reg.getOrg().setTown(rs.getString("TownName"));
           Reg.getOrg().setStreet(rs.getString("Street"));
           Reg.setDoseType(rs.getString("DoseType"));
           Reg.setStatus(rs.getInt("Status"));
           Reg.setImage(rs.getBytes("Image"));

       } catch (SQLException ex) {
           if (ex.getErrorCode() == 17289)
               dv.popupOption(null, "Bạn không có mũi tiêm nào chưa được cập nhật!",
                       "Thông báo!", 0);
           else {
               dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
               ex.printStackTrace();
               return;
           }
       }

       JLabel Name = new JLabel("Họ tên: " + personalUser.getFullName());
       Name.setBounds(50, 20, 360, 35);
       Name.setFont(new Font(dv.fontName(), Font.PLAIN, 20));
       Name.setHorizontalAlignment(JLabel.LEFT);

       JLabel sID = new JLabel("CMND/CCCD: " + personalUser.getID());
       sID.setBounds(50, 55, 360, 35);
       sID.setFont(new Font(dv.fontName(), Font.PLAIN, 20));
       sID.setHorizontalAlignment(JLabel.LEFT);

       JLabel SDT = new JLabel("SĐT: " + personalUser.getPhone());
       SDT.setBounds(50, 90, 360, 35);
       SDT.setFont(new Font(dv.fontName(), Font.PLAIN, 20));
       SDT.setHorizontalAlignment(JLabel.LEFT);

       JLabel NameOrg = new JLabel("Tên đơn vị: " + Reg.getOrg().getName());
       NameOrg.setBounds(50, 125, 712, 35);
       NameOrg.setFont(new Font(dv.fontName(), Font.PLAIN, 20));
       NameOrg.setHorizontalAlignment(JLabel.LEFT);

       JLabel IDOrg = new JLabel("Mã lịch tiêm: " + Reg.getSched().getID());
       IDOrg.setBounds(50, 160, 712, 35);
       IDOrg.setFont(new Font(dv.fontName(), Font.PLAIN, 20));
       IDOrg.setHorizontalAlignment(JLabel.LEFT);

       JLabel Vaccine = new JLabel("Vaccine: " + Reg.getSched().getVaccineID() + " - " + Reg.getSched().getSerial());
       Vaccine.setBounds(50, 195, 356, 35);
       Vaccine.setFont(new Font(dv.fontName(), Font.PLAIN, 20));
       Vaccine.setHorizontalAlignment(JLabel.LEFT);

       JLabel doseType = new JLabel("Loại: " + dv.getDoseTypeName(Reg.getDoseType()));
       doseType.setBounds(406, 195, 356, 35);
       doseType.setFont(new Font(dv.fontName(), Font.PLAIN, 20));
       doseType.setHorizontalAlignment(JLabel.LEFT);

       JLabel Address = new JLabel("Đ/c: " + Reg.getOrg().getProvince() + ", "
               + Reg.getOrg().getDistrict() + ", " + Reg.getOrg().getTown() + ", " + Reg.getOrg().getStreet());
       Address.setBounds(50, 230, 712, 35);
       Address.setFont(new Font(dv.fontName(), Font.PLAIN, 20));
       Address.setHorizontalAlignment(JLabel.LEFT);

       JLabel OnDateTime = new JLabel("Lịch tiêm ngày: " + Reg.getSched().getOnDate()
               + "          Buổi: " + dv.getTimeName(Reg.getTime()) + "          STT: " + Reg.getNO());
       OnDateTime.setFont(new Font(dv.fontName(), Font.PLAIN, 20));
       OnDateTime.setBounds(50, 265, 712, 35);
       OnDateTime.setHorizontalAlignment(JLabel.LEFT);

       JLabel Status = new JLabel("Tình trạng: " + dv.getStatusName(Reg.getStatus()));
       Status.setFont(new Font(dv.fontName(), Font.PLAIN, 20));
       Status.setBounds(50, 300, 712, 35);
       Status.setHorizontalAlignment(JLabel.LEFT);

       JLabel Type = new JLabel("Giấy chứng nhận tiêm chủng vaccine: ");
       Type.setBounds(50, 335, 360, 35);
       Type.setFont(new Font(dv.fontName(), Font.PLAIN, 20));
       Type.setHorizontalAlignment(JLabel.LEFT);


       UpLoadImageButton = new JButton();
       ImageIcon UploadImage = new ImageIcon(getClass().getResource("/Resources/icon/ImageIcon.png"));
       UpLoadImageButton.setIcon(UploadImage);
       UpLoadImageButton.setBounds(dv.AlignLeft() + 350, 330, UploadImage.getIconWidth(), UploadImage.getIconHeight());
//       UpLoadImageButton.setBorder(null);
       UpLoadImageButton.setContentAreaFilled(false);
       UpLoadImageButton.addActionListener(this);

       RemoveImageButton = new JButton();
       ImageIcon RemoveImageIcon = new ImageIcon(getClass().getResource("/Resources/icon/Remove Pic Button.png"));
       RemoveImageButton.setIcon(RemoveImageIcon);
       RemoveImageButton.setBounds(dv.AlignLeft() + 280 + RemoveImageIcon.getIconWidth() + 5, 330,
               RemoveImageIcon.getIconWidth(), RemoveImageIcon.getIconHeight());
       RemoveImageButton.setBorder(null);
       RemoveImageButton.setContentAreaFilled(false);
       RemoveImageButton.addActionListener(this);

       ConfirmButton = new JButton();
       ImageIcon ConfirmImage = new ImageIcon(getClass().getResource("/Resources/icon/Confirm Button.png"));
       ConfirmButton.setIcon(ConfirmImage);
       ConfirmButton.setBounds(dv.AlignLeft() + 145, 440, ConfirmImage.getIconWidth(), ConfirmImage.getIconHeight());
       ConfirmButton.setBorder(null);
       ConfirmButton.setContentAreaFilled(false);
       ConfirmButton.addActionListener(this);

       JLabel Warning = new JLabel("<html>Khai báo thông tin sai là vi phạm pháp luật Việt Nam và có thể xử lý hình sự.");
       Warning.setBounds(50, 380, 440, 60);
       Warning.setFont(new Font(dv.fontName(), Font.ITALIC, 18));
       Warning.setHorizontalAlignment(JLabel.CENTER);
       Warning.setForeground(Color.red);

       InjectionInfoPanel.add(Name, Integer.valueOf(2));
       InjectionInfoPanel.add(sID, Integer.valueOf(2));
       InjectionInfoPanel.add(SDT, Integer.valueOf(2));
       InjectionInfoPanel.add(NameOrg, Integer.valueOf(2));
       InjectionInfoPanel.add(IDOrg, Integer.valueOf(2));
       InjectionInfoPanel.add(Vaccine, Integer.valueOf(2));
       InjectionInfoPanel.add(doseType, Integer.valueOf(2));
       InjectionInfoPanel.add(OnDateTime, Integer.valueOf(2));
       InjectionInfoPanel.add(Address, Integer.valueOf(2));
       InjectionInfoPanel.add(Status, Integer.valueOf(2));
       InjectionInfoPanel.add(Type, Integer.valueOf(1));
       InjectionInfoPanel.add(Warning, Integer.valueOf(1));

       InjectionInfoPanel.add(UpLoadImageButton, Integer.valueOf(1));
       InjectionInfoPanel.add(RemoveImageButton, Integer.valueOf(1));
       InjectionInfoPanel.add(ConfirmButton, Integer.valueOf(2));
   }


    /*CONSTRUCTOR*/
    public UpdateInjectionView(Person person) throws IOException
    {
        personalUser = person;
        this.setSize(dv.FrameWidth(), dv.FrameHeight());
        this.setLayout(null);

        JLabel InjectionInfoLabel = new JLabel("CẬP NHẬT THÔNG TIN TIÊM CHỦNG");
        InjectionInfoLabel.setBounds((dv.FrameWidth()-630)/2, 40, 630, 40);
        InjectionInfoLabel.setFont(new Font(dv.fontName(), Font.BOLD, 24));
        InjectionInfoLabel.setHorizontalAlignment(JLabel.CENTER);
        InjectionInfoLabel.setForeground(new Color(dv.FeatureButtonColor()));

        initInjectionInfoPanel(personalUser);
        initImagePanel();

        initMainPanel();
        MainPanel.add(InjectionInfoLabel);
        MainPanel.add(ImagePanel);
        MainPanel.add(InjectionInfoPanel);
        this.add(MainPanel);
        this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
    }
   
   /*ACTION PERFORMED*/
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
            ImageIcon TakenImage = new ImageIcon(file.getPath());
            Image ResizedImg = ImageHelper.reSize(TakenImage.getImage(), 380, dv.FrameHeight() - 200);
            JLabel ImagePlace = new JLabel(new ImageIcon(ResizedImg));
            ImagePlace.setBounds(0, 0, 380, dv.FrameHeight() - 200);
            ImagePlace.setHorizontalAlignment(JLabel.LEFT);
            ImagePanel.add(ImagePlace);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            dv.popupOption(null, ex.getMessage(), "Lỗi!",2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == UpLoadImageButton)
        {
            ImagePanel.removeAll();
            ActionUpLoadImage();
            ImagePanel.repaint();
        }

        if (e.getSource() == RemoveImageButton)
        {
            if (dv.popupConfirmOption(null,"Xác nhận gỡ ảnh?", "Xác nhận") == 0);
            {
                String query = "update REGISTER set Image = null where PersonalID = '" + personalUser.getID() + "'";

                try {
                    Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                    PreparedStatement st = connection.prepareStatement(query);

                    st.executeUpdate();
                } catch (SQLException ex) {
                    dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                    ex.printStackTrace();
                    return;
                }
                dv.popupOption(null, "Gỡ ảnh thành công!", "Thông báo!", 0);
                Reg.setImage(null);
                ImagePanel.removeAll();
                ImagePanel.repaint();
            }
        }

        if (e.getSource() == ConfirmButton)
        {
            if ( dv.popupConfirmOption(null, "Xác nhận cập nhật giấy chứng nhận mũi tiêm?", "Xác nhận?") == 0)
            {
                try {
                    Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                    String query ="update REGISTER REG " +
                                       "set Image=? " +
                                       "where PersonalID= '" + personalUser.getID() + "'" +
                                        " and SchedID = '"+ Reg.getSched().getID() +"'";
                    PreparedStatement st = connection.prepareStatement(query);
                    input = new FileInputStream(file);
                    st.setBinaryStream(1,input);
                    st.executeUpdate();
                }
                catch (SQLException ex) {
                    dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                    ex.printStackTrace();
                    return;
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
    }
}
