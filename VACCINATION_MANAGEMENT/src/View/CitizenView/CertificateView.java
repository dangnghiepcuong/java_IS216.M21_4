package View.CitizenView;

import Process.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class CertificateView extends JPanel{

    /*Main GUI*/

    private JScrollPane ScrollPaneInjList;

    private JPanel InjectionListPanel;
    private JPanel CertificatePanel;

    /*Object Class*/
    private DefaultValue dv = new DefaultValue();
    private Person personalUser = new Person();
    private Certificate cert = new Certificate();

    /*
    *   INITIALIZE THE LIST OF INJECTIONS OF THE CITIZEN
    *   -LAYEREDPANE:
    *      + SCROLLPANE:
    *           - PANEL: LIST OF INJECTIONS
    *               + PANELS: INJECTIONS
    *                  - LABELS
    * */
   private JPanel initInjectionPanel(Injection Inj)
   {
       JLabel NameOrg = new JLabel("Mũi: " + Inj.getInjNo()
               + " ("+dv.getDoseTypeName(Inj.getDoseType()) +")");
       NameOrg.setBounds(20, 2, 400, 25);
       NameOrg.setFont(new Font(dv.fontName(),1, 20));
       NameOrg.setForeground(new Color(dv.BlackTextColor()));

       JLabel Vaccine = new JLabel("Vaccine: " + Inj.getSched().getVaccineID());
       Vaccine.setBounds(20, 30, 350, 25);
       Vaccine.setFont(new Font(dv.fontName(),2,20));
       Vaccine.setForeground(new Color(dv.BlackTextColor()));

       JLabel InjNOType=new JLabel("Đơn vị tiêm chủng: " + Inj.getOrg().getName());
       InjNOType.setBounds(20, 30*2, 600, 25);
       InjNOType.setFont(new Font(dv.fontName(),0, 18));
       InjNOType.setForeground(new Color(dv.BlackTextColor()));

       JLabel OnDateTime = new JLabel("Lịch tiêm ngày: " + Inj.getSched().getOnDate());
       OnDateTime.setFont(new Font(dv.fontName(),0, 18));
       OnDateTime.setBounds(20,30*3,500,25);
       OnDateTime.setForeground(new Color(dv.BlackTextColor()));

       JPanel InjectionPanel = new JPanel();
       InjectionPanel.setPreferredSize(new Dimension(560, 120));
       InjectionPanel.setLayout(null);
       InjectionPanel.setBackground(Color.WHITE);
       InjectionPanel.setBorder(dv.border());

       InjectionPanel.add(NameOrg);
       InjectionPanel.add(Vaccine);
       InjectionPanel.add(InjNOType);
       InjectionPanel.add(OnDateTime);

       return InjectionPanel;
   }


   private void initInjectionListPanel()
   {
       InjectionListPanel = new JPanel();
       InjectionListPanel.setLayout(new FlowLayout());
       InjectionListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));

       int i =0;
       int nInj = 0;

        String query = "select *" +
                        "from INJECTION INJ, SCHEDULE SCHED, ORGANIZATION ORG " +
                        "where INJ.PersonalID = " + personalUser.getID() + " and" +
                        "      INJ.Schedid = SCHED.ID and" +
                        "      SCHED.OrgID = ORG.ID " +
                        "order by InjNO";
        try {
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                cert.getInjectionList()[i] = new Injection();
                cert.getInjectionList()[i].setInjNo(rs.getInt("InjNO"));
                cert.getInjectionList()[i].getOrg().setName(rs.getString("Name"));
                cert.getInjectionList()[i].getOrg().setProvince(rs.getString("ProvinceName"));
                cert.getInjectionList()[i].getOrg().setDistrict(rs.getString("DistrictName"));
                cert.getInjectionList()[i].getOrg().setTown(rs.getString("TownName"));
                cert.getInjectionList()[i].getOrg().setStreet(rs.getString("Street"));
                cert.getInjectionList()[i].getSched().setOnDate(rs.getString("OnDate").substring(0,10));
                cert.getInjectionList()[i].getSched().setVaccineID(rs.getString("VaccineID"));
                cert.getInjectionList()[i].setDoseType(rs.getString("DoseType"));
                InjectionListPanel.add(initInjectionPanel(cert.getInjectionList()[i]));
                i++;
            }
        } catch (SQLException ex) {
            dv.popupOption(null,ex.getMessage(), String.valueOf(ex.getErrorCode()),2);
            ex.printStackTrace();
            return;
        }

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
            ex.printStackTrace();
            return;
       }

       nInj = i;

       InjectionListPanel.setPreferredSize(new Dimension(580, 120*nInj + nInj*10));
   }


   private void initScrollPaneInjList()
    {
        ScrollPaneInjList = new JScrollPane(InjectionListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneInjList.setBounds(0,160,600, 500);
        ScrollPaneInjList.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        ScrollPaneInjList.setBorder(null);
    }

    public void initCertificatePanel()
    {
        JLabel InfoLabel = new JLabel("CHỨNG NHẬN TIÊM CHỦNG");
        InfoLabel.setBounds(0, 40, 600, 40);
        InfoLabel.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        InfoLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel InfoLabel2 = new JLabel(personalUser.getFullName() + " (" + dv.getGenderName(personalUser.getGender()) + " - "
                + personalUser.getBirthday().substring(0,4) + ")");
        InfoLabel2.setBounds(0, 80, 600, 40);
        InfoLabel2.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        InfoLabel2.setHorizontalAlignment(JLabel.CENTER);

        JLabel InfoLabel3 = new JLabel();
        InfoLabel3.setBounds(0, 120, 600, 40);
        InfoLabel3.setFont(new Font(dv.fontName(),Font.ITALIC, 24));
        InfoLabel3.setHorizontalAlignment(JLabel.CENTER);

        if (cert.getCertType() == 0)
        {
            InfoLabel.setForeground(Color.WHITE);
            InfoLabel2.setForeground(Color.WHITE);
            InfoLabel3.setForeground(Color.WHITE);
            InfoLabel3.setText("Chưa thực hiện tiêm chủng vaccine Covid-19");
        }
        if (cert.getCertType() == 1)
        {
            InfoLabel.setForeground(new Color(dv.BlackTextColor()));
            InfoLabel2.setForeground(new Color(dv.BlackTextColor()));
            InfoLabel3.setForeground(new Color(dv.BlackTextColor()));
            InfoLabel3.setText("Chưa tiêm đủ liều cơ bản vaccine Covid-19");
        }
        if (cert.getCertType() == 2)
        {
            InfoLabel.setForeground(Color.WHITE);
            InfoLabel2.setForeground(Color.WHITE);
            InfoLabel3.setForeground(Color.WHITE);
            InfoLabel3.setText("Đã hoàn thành tiêm chủng vaccine Covid-19");
        }

        CertificatePanel = new JPanel();
        CertificatePanel.setBounds((this.getWidth()-600)/2,0,600, 720);
        CertificatePanel.setLayout(null);
        CertificatePanel.setOpaque(false);

        if (cert.getCertType() == 0)
            this.setBackground(new Color(dv.RedPastel()));
        if (cert.getCertType() == 1)
            this.setBackground(new Color(dv.YellowPastel()));
        if (cert.getCertType() == 2)
            this.setBackground(new Color(dv.GreenPastel()));

        CertificatePanel.add(InfoLabel);
        CertificatePanel.add(InfoLabel2);
        CertificatePanel.add(InfoLabel3);
        CertificatePanel.add(ScrollPaneInjList);
    }

    private void initComponents()
    {
        this.setSize(dv.FrameWidth(), dv.FrameHeight());
        this.setVisible(true);
        //this.setBackground(new Color(dv.ViewBackgroundColor()));
        this.setLayout(null);

        initInjectionListPanel();
        initScrollPaneInjList();

        initCertificatePanel();

        this.add(CertificatePanel);
        this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
    }

    /*CONSTRUCTOR*/

    public CertificateView(Person person)
    {
        personalUser = person;

        String plsql = "{call CERT_UPDATE_RECORD(?)}";

        try {
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            CallableStatement cst;

            cst = connection.prepareCall(plsql);
            cst.setString("par_PersonalID", personalUser.getID());

                cst.execute();
            }
            catch (SQLException ex)
            {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

        initComponents();
    }

}
