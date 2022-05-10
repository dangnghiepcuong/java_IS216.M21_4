/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI_UpdateInjection;

import Data_Processor.*;
import GUI_ManageVaccination.ManageVaccinationView;
import GUI_SearchOrg.SearchOrgView;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;

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
    private JLabel image;
    
    private JScrollPane ScrollPaneInjList;
    
    private JPanel InjectionListPanel;
    private JPanel InjectionPanel[] = new JPanel[10];
    private JLayeredPane LayeredPaneArea; 
    private JButton UpLoadImageButton;
    private JButton PhotoImageButton;
    private JButton ContinuteButton;
    private JButton ConfirmButton;
    private JButton CancelButton;
    
    private JLayeredPane SchedAttendance;

    /*Object Class*/
    private DefaultValue dv = new DefaultValue();
    private Account userAccount = new Account();
    private Person personalUser = new Person();

    //Stored Sum_Injection
    private int Total_Injection;
    
    



   private void initInjectionPanel(int i, Injection Inj)
   {
       JLabel NameOrg=new JLabel("Đơn vị tiêm chủng: " + Inj.getOrg().getName());
       NameOrg.setBounds(20, 2, 600, 25);
       NameOrg.setFont(new Font(dv.fontName(),2, 20));

       JLabel InjNOType = new JLabel("Mũi: " + Inj.getInjNo()
               + "Loại: "+dv.getDoseTypeName(Inj.getDoseType()));
       InjNOType.setBounds(20, 25, 400, 25);
       InjNOType.setFont(new Font(dv.fontName(),0, 18));

       JLabel Vaccine = new JLabel("Vaccine: " + Inj.getSched().getVaccineID()+" - "+ Inj.getSched().getSerial());
       Vaccine.setBounds(20, 25*2, 350, 25);
       Vaccine.setFont(new Font(dv.fontName(),0,18));

       JLabel OnDateTime = new JLabel("Lịch tiêm ngày: " + Inj.getSched().getOnDate());
       OnDateTime.setFont(new Font(dv.fontName(),0, 18));
       OnDateTime.setBounds(30,25*3,500,25);

       JLabel Cert = new JLabel("Giấy chứng nhận tiêm chủng vaccine: ");
       Cert.setBounds(50,345,360,25);
       Cert.setFont(new Font(dv.fontName(),0,20));

       UpLoadImageButton = new JButton();
       ImageIcon UploadImage = new ImageIcon(getClass().getResource("/Data_Processor/icon/image-gallery.png"));
       UpLoadImageButton.setIcon(UploadImage);
       UpLoadImageButton.setBounds(dv.AlignLeft()+350, 330, 50, 50);
       UpLoadImageButton.setBorder(null);
       UpLoadImageButton.setContentAreaFilled(false);
       UpLoadImageButton.addActionListener(this);

//        PhotoImageButton = new JButton();
//        ImageIcon PhotoImage = new ImageIcon(getClass().getResource("/Data_Processor/icon/add-photo.png"));
//        PhotoImageButton.setIcon(PhotoImage);
//        PhotoImageButton.setBounds(dv.AlignLeft(), 375, dv.FieldWidth(), UploadImage.getIconHeight());
//        PhotoImageButton.setBorder(null);
//        PhotoImageButton.setContentAreaFilled(false);


//        ContinuteButton = new JButton();
//        ImageIcon ContinuteImage = new ImageIcon(getClass().getResource("/Data_Processor/icon/OK Button.png"));
//        ContinuteButton.setIcon(ContinuteImage);
//        ContinuteButton.setBounds(dv.AlignLeft()+150, 450, dv.FieldWidth(), ContinuteImage.getIconHeight());
//        ContinuteButton.setBorder(null);
//        ContinuteButton.setContentAreaFilled(false);

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

       InjectionPanel[i] = new JPanel();
       InjectionPanel[i].setPreferredSize(new Dimension(640, 200));
       InjectionPanel[i].setLayout(null);
       InjectionPanel[i].setBackground(Color.WHITE);

       InjectionPanel[i].add(NameOrg);
       InjectionPanel[i].add(Vaccine);
       InjectionPanel[i].add(InjNOType);
       InjectionPanel[i].add(OnDateTime);
       InjectionPanel[i].add(Cert);
       InjectionPanel[i].add(Warning);

       //Button
       InjectionPanel[i].add(UpLoadImageButton);
       InjectionPanel[i].add(ConfirmButton);
   }
   
   
   private void initInjectionListPanel()
   {
       Injection Inj[] =new Injection[10];

       int i =0;
       int nInj = 0;
        
        String query = "select *" +
                        "from INJECTION INJ, SCHEDULE SCHED, ORGANIZATION ORG " +
                        "where INJ.PersonalID = " + personalUser.getID() + " and" +
                        "      INJ.Schedid = SCHED.ID and" +
                        "      SCHED.OrgID = ORG.ID";
        try {
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());


            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                Inj[i] = new Injection();
                Inj[i].setInjNo(rs.getInt("InjNO"));
                Inj[i].getOrg().setName(rs.getString("Name"));
                Inj[i].getOrg().setProvince(rs.getString("Province"));
                Inj[i].getOrg().setDistrict(rs.getString("District"));
                Inj[i].getOrg().setTown(rs.getString("Town"));
                Inj[i].getOrg().setStreet(rs.getString("Street"));
                Inj[i].getSched().setOnDate(rs.getString("OnDate").substring(0,10));
                Inj[i].getSched().setVaccineID(rs.getString("VaccineID"));
                Inj[i].setDoseType(rs.getString("DoseType"));
                i++;
            }
            

        } catch (SQLException ex) {
            dv.popupOption(null,ex.getMessage(), String.valueOf(ex.getErrorCode()),2);
            ex.printStackTrace();
        }

       InjectionListPanel = new JPanel();
       InjectionListPanel.setBounds(0,0,660, 590);
       InjectionListPanel.setLayout(new FlowLayout());
       InjectionListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));

        nInj = i;

        for (i = 0; i<nInj; i++)
        {
            initInjectionPanel(i, Inj[i]);
            InjectionListPanel.add(InjectionPanel[i]);
        }

   }
   
   
   private void initScrollPaneInjList()//1
    {
        ScrollPaneInjList = new JScrollPane(InjectionListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        ScrollPaneInjList.setBounds(00,40,680, 590);; //320 40
    }

    public void initLayeredPaneArea()
    {
        JLabel InfoLabel=new JLabel("CẬP NHẬT THÔNG TIN TIÊM CHỦNG");
        InfoLabel.setBounds(0, 0, 680, 40);
        InfoLabel.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        InfoLabel.setHorizontalAlignment(JLabel.CENTER);
        InfoLabel.setForeground(new Color(dv.FeatureButtonColor()));

        LayeredPaneArea = new JLayeredPane();
        LayeredPaneArea.setBounds(320,40,680, 630);
        LayeredPaneArea.setLayout(null);

        LayeredPaneArea.add(InfoLabel);
    }

    public UpdateInjectionView(Person person)
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

        initInjectionListPanel();
        initScrollPaneInjList();

        initLayeredPaneArea();
        LayeredPaneArea.add(ScrollPaneInjList, Integer.valueOf(0));

        this.add(LayeredPaneArea);
        this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
    }


   
    private void ActionUpLoadImage( )
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
        if(chooser.showOpenDialog(this) ==JFileChooser.CANCEL_OPTION)
            return;
        
        File file=chooser.getSelectedFile();
        try
        {
            ImageIcon imageicon=new ImageIcon(file.getPath());
            Image img=ImageHelper.reSize(imageicon.getImage(), 4, 3);
            JLabel image = new JLabel(imageicon); 
            
            image.setBounds(90,140,300,400);
            image.setHorizontalAlignment(JLabel.CENTER);
            InfoLayeredPane.add(image,Integer.valueOf(2));
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
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
        
        if (e.getSource() == UpLoadImageButton)
        {
            ActionUpLoadImage();
        }
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


   
}
