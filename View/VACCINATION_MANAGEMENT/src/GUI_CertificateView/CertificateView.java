/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI_CertificateView;

import Data_Processor.*;
import GUI_UpdateInjection.ImageHelper;

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
public class CertificateView extends JPanel implements ActionListener{

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
    private Person personalUser = new Person();
    private Certificate cert = new Certificate();

    //Stored Sum_Injection
    private int Total_Injection;





   private void initInjectionPanel(int i, Injection Inj)
   {
       JLabel NameOrg=new JLabel("Đơn vị tiêm chủng: " + Inj.getOrg().getName());
       NameOrg.setBounds(20, 2, 600, 25);
       NameOrg.setFont(new Font(dv.fontName(),2, 20));
       NameOrg.setForeground(Color.WHITE);

       JLabel InjNOType = new JLabel("Mũi: " + Inj.getInjNo()
               + "          Loại: "+dv.getDoseTypeName(Inj.getDoseType()));
       InjNOType.setBounds(20, 25, 400, 25);
       InjNOType.setFont(new Font(dv.fontName(),0, 18));
       InjNOType.setForeground(Color.WHITE);

       JLabel Vaccine = new JLabel("Vaccine: " + Inj.getSched().getVaccineID()+" - "+ Inj.getSched().getSerial());
       Vaccine.setBounds(20, 25*2, 350, 25);
       Vaccine.setFont(new Font(dv.fontName(),0,18));
       Vaccine.setForeground(Color.WHITE);

       JLabel OnDateTime = new JLabel("Lịch tiêm ngày: " + Inj.getSched().getOnDate());
       OnDateTime.setFont(new Font(dv.fontName(),0, 18));
       OnDateTime.setBounds(20,25*3,500,25);
       OnDateTime.setForeground(Color.WHITE);

       InjectionPanel[i] = new JPanel();
       InjectionPanel[i].setPreferredSize(new Dimension(560, 100));
       InjectionPanel[i].setLayout(null);
       InjectionPanel[i].setBackground(null);
       InjectionPanel[i].setBorder(dv.border());

       InjectionPanel[i].add(NameOrg);
       InjectionPanel[i].add(Vaccine);
       InjectionPanel[i].add(InjNOType);
       InjectionPanel[i].add(OnDateTime);
   }


   private void initInjectionListPanel()
   {
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
                cert.getInjectionList()[i] = new Injection();
                cert.getInjectionList()[i].setInjNo(rs.getInt("InjNO"));
                cert.getInjectionList()[i].getOrg().setName(rs.getString("Name"));
                cert.getInjectionList()[i].getOrg().setProvince(rs.getString("Province"));
                cert.getInjectionList()[i].getOrg().setDistrict(rs.getString("District"));
                cert.getInjectionList()[i].getOrg().setTown(rs.getString("Town"));
                cert.getInjectionList()[i].getOrg().setStreet(rs.getString("Street"));
                cert.getInjectionList()[i].getSched().setOnDate(rs.getString("OnDate").substring(0,10));
                cert.getInjectionList()[i].getSched().setVaccineID(rs.getString("VaccineID"));
                cert.getInjectionList()[i].setDoseType(rs.getString("DoseType"));
                i++;
            }


        } catch (SQLException ex) {
            dv.popupOption(null,ex.getMessage(), String.valueOf(ex.getErrorCode()),2);
            ex.printStackTrace();
        }

       InjectionListPanel = new JPanel();
       InjectionListPanel.setBounds(0,0,580, 590);
       InjectionListPanel.setLayout(new FlowLayout());

       int CertificateType = 0;

       query = "select * from CERTIFICATE CERT where PersonalID = '" + personalUser.getID() + "'";
       try {
           Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
           PreparedStatement st = connection.prepareStatement(query);
           ResultSet rs = st.executeQuery(query);

           rs.next();
           cert.setDose(rs.getInt("Dose"));
           cert.setCertType(rs.getInt("CertType"));
       }
       catch (SQLException ex)
       {
            dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
       }

       if (cert.getCertType() == 0)
           InjectionListPanel.setBackground(new Color(0xFF6961));
       if (cert.getCertType() == 1)
           InjectionListPanel.setBackground(new Color(0xFDFD96));
       if (cert.getCertType() == 2)
           InjectionListPanel.setBackground(new Color(0x77DD77));


        nInj = i;

        for (i = 0; i<nInj; i++)
        {
            initInjectionPanel(i, cert.getInjectionList()[i]);
            InjectionListPanel.add(InjectionPanel[i]);
        }

   }


   private void initScrollPaneInjList()//1
    {
        ScrollPaneInjList = new JScrollPane(InjectionListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        ScrollPaneInjList.setBounds(0,80,600, 510);; //320 40
    }

    public void initLayeredPaneArea()
    {
        JLabel InfoLabel = new JLabel("CHỨNG NHẬN TIÊM CHỦNG");
        InfoLabel.setBounds(0, 0, 600, 40);
        InfoLabel.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        InfoLabel.setHorizontalAlignment(JLabel.CENTER);
        InfoLabel.setForeground(new Color(dv.FeatureButtonColor()));

        JLabel InfoLabel2 = new JLabel(personalUser.getFullName() + " (" + dv.getGenderName(personalUser.getGender()) + " - "
                + personalUser.getBirthday().substring(0,4) + ")");
        InfoLabel2.setBounds(0, 40, 600, 40);
        InfoLabel2.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        InfoLabel2.setHorizontalAlignment(JLabel.CENTER);
        InfoLabel2.setForeground(new Color(dv.FeatureButtonColor()));

        LayeredPaneArea = new JLayeredPane();
        LayeredPaneArea.setBounds((this.getWidth()-600)/2,(this.getHeight()-630)/2,600, 630);
        LayeredPaneArea.setLayout(null);

        LayeredPaneArea.add(InfoLabel);
        LayeredPaneArea.add(InfoLabel2);
    }

    public CertificateView(Person person)
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
            Image img= ImageHelper.reSize(imageicon.getImage(), 4, 3);
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
