package View.MOHView;

import Process.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class SearchCitizenView extends JPanel implements ActionListener, KeyListener {

    /*Search Result*/
    private JScrollPane ScrollPaneInjList;
    private JPanel InjectionListPanel;
    private JPanel CertificatePanel;

    /*Search Info*/
    private JPanel PersonalInfoPanel;
    private JLabel FullNameLabel;
    private JTextField FullNameField;
    private JLabel PhoneNumberLabel;
    private JTextField PhoneNumberField;
    private JLabel PersonalIDLabel;
    private  JTextField PersonalIDField;
    private JLabel BirthdayLabel;
    private JTextField BirthdayField;
    private JLabel GenderLabel;
    private JComboBox GenderComboBox;
    private  JButton SearchButton;

    /*Object Class*/
    private DefaultValue dv = new DefaultValue();
    private Organization orgUser = new Organization();
    private Person personalUser = new Person();
    private Certificate cert = new Certificate();

    private void initFullNameLabel()
    {
        FullNameLabel = new JLabel("Họ và tên");
        FullNameLabel.setBounds(70, 40 +dv.AlignTop_InfoView(), 240, 30);
        FullNameLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        FullNameLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initFullNameField()
    {
        FullNameField = new JTextField();
        FullNameField.setBounds(70, 40+dv.LabelHeight() +dv.AlignTop_InfoView(), 220, 30);
        FullNameField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        FullNameField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        FullNameField.setForeground(new Color(dv.BlackTextColor()));
        FullNameField.addKeyListener(this);
    }

    private void initPhoneNumberLabel()
    {
        PhoneNumberLabel = new JLabel("Số điện thoại");
        PhoneNumberLabel.setBounds(70, 50 + 2*dv.LabelHeight() + dv.AlignTop_InfoView(), 240, 30);
        PhoneNumberLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        PhoneNumberLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initPhoneNumberField()
    {
        PhoneNumberField = new JTextField();
        PhoneNumberField.setBounds(70, 50 + 3*dv.LabelHeight() +dv.AlignTop_InfoView(), 220, 30);
        PhoneNumberField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        PhoneNumberField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        PhoneNumberField.setForeground(new Color(dv.BlackTextColor()));
        PhoneNumberField.addKeyListener(this);
    }

    private void initPersonalIDLabel()
    {
        PersonalIDLabel = new JLabel("CMND/CCCD");
        PersonalIDLabel.setBounds(70, 60  + 4*dv.LabelHeight() +dv.AlignTop_InfoView(), 270, 30);
        PersonalIDLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        PersonalIDLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initPersonalIDField()
    {
        PersonalIDField = new JTextField();
        PersonalIDField.setBounds(70, 60  + 5 * dv.LabelHeight() +dv.AlignTop_InfoView(), 220, 30);
        PersonalIDField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        PersonalIDField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        PersonalIDField.setForeground(new Color(dv.BlackTextColor()));
        PersonalIDField.addKeyListener(this);
    }

    private void initBirthdayLabel()
    {
        BirthdayLabel = new JLabel("Năm sinh");
        BirthdayLabel.setBounds(70, 70 + 6 * dv.LabelHeight() +dv.AlignTop_InfoView(),220,30);
        BirthdayLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        BirthdayLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initBirthdayField()
    {
        BirthdayField = new JTextField();
        BirthdayField.setBounds(70, 70  + 7 * dv.LabelHeight() +dv.AlignTop_InfoView(), 220, 30);
        BirthdayField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        BirthdayField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        BirthdayField.setForeground(new Color(dv.BlackTextColor()));
        BirthdayField.addKeyListener(this);
    }

    private void initGenderLabel()
    {
        GenderLabel = new JLabel("Giới tính");
        GenderLabel.setBounds(70, 80 + 8*dv.LabelHeight() +dv.AlignTop_InfoView(), 200, 30);
        GenderLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        GenderLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initGenderComboBox()
    {
        GenderComboBox = new JComboBox();
        GenderComboBox.setBounds(70, 80 + 9*dv.LabelHeight() +dv.AlignTop_InfoView(), 80, 28);
        GenderComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        GenderComboBox.setForeground(new Color(dv.FieldLabelColor()));
        GenderComboBox.setBackground(Color.WHITE);
        GenderComboBox.addKeyListener(this);

        GenderComboBox.addItem("");
        GenderComboBox.addItem("Nữ");
        GenderComboBox.addItem("Nam");
        GenderComboBox.addItem("Khác");
    }

    private void initSearchButton()
    {
        ImageIcon SearchButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Search Filter Button.png"));
        SearchButton = new JButton();
        SearchButton.setBounds(70, 600, SearchButtonIcon.getIconWidth(), SearchButtonIcon.getIconHeight());
        SearchButton.setBorder(null);
        SearchButton.setContentAreaFilled(false);
        SearchButton.setIcon(SearchButtonIcon);

        SearchButton.addActionListener(this);
        SearchButton.addKeyListener(this);
    }

    private void initPersonalInfoPanel()
    {
        PersonalInfoPanel = new JPanel();

        PersonalInfoPanel.setLayout(null);
        PersonalInfoPanel.setBounds(0,0,dv.FrameWidth()-dv.FrameHeight(),dv.FrameHeight());
        PersonalInfoPanel.setBackground(new Color(dv.ViewBackgroundColor()));
        PersonalInfoPanel.setBorder(dv.border());

        JLabel PersonalInfoLabel = new JLabel("THÔNG TIN CÁ NHÂN");
        PersonalInfoLabel.setBounds((PersonalInfoPanel.getWidth()-300)/2, 40, 300, 30);
        PersonalInfoLabel.setFont(new Font(dv.fontName(), 1, 20));
        PersonalInfoLabel.setForeground(new Color(dv.FeatureButtonColor()));
        PersonalInfoLabel.setHorizontalAlignment(JLabel.CENTER);

        PersonalInfoPanel.add(PersonalInfoLabel);

        initFullNameLabel();
        PersonalInfoPanel.add(FullNameLabel);

        initFullNameField();
        PersonalInfoPanel.add(FullNameField);

        initBirthdayLabel();
        PersonalInfoPanel.add(BirthdayLabel);

        initBirthdayField();
        PersonalInfoPanel.add(BirthdayField);

        initGenderLabel();
        PersonalInfoPanel.add(GenderLabel);

        initGenderComboBox();
        PersonalInfoPanel.add(GenderComboBox);

        initPhoneNumberLabel();
        PersonalInfoPanel.add(PhoneNumberLabel);

        initPhoneNumberField();
        PersonalInfoPanel.add(PhoneNumberField);

        initPersonalIDLabel();
        PersonalInfoPanel.add(PersonalIDLabel);

        initPersonalIDField();
        PersonalInfoPanel.add(PersonalIDField);

        initSearchButton();
        PersonalInfoPanel.add(SearchButton);

        PersonalInfoPanel.validate();
    }

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
//       InjectionPanel.setBorder(dv.border());

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

       String query = "";
       query = "select *" +
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
       nInj = 120*nInj + nInj*10;
       if (nInj < 500)
           nInj = 500;

       InjectionListPanel.setPreferredSize(new Dimension(580, nInj));
//       InjectionListPanel.setBounds(0,0,580,nInj);
   }


   private void initScrollPaneInjList()
    {
        ScrollPaneInjList = new JScrollPane(InjectionListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneInjList.setBounds(60 ,40 + 120,600, 500);
        ScrollPaneInjList.setBorder(null);
    }

    public void initCertificatePanel()
    {
        CertificatePanel = new JPanel();
        CertificatePanel.setBounds(360,0,720, 720);
        CertificatePanel.setLayout(null);

        JLabel InfoLabel = new JLabel("CHỨNG NHẬN TIÊM CHỦNG");
        InfoLabel.setBounds(0 , 40, CertificatePanel.getWidth(), 40);
        InfoLabel.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        InfoLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel InfoLabel2 = new JLabel(personalUser.getFullName() + " (" + dv.getGenderName(personalUser.getGender()) + " - "
                + personalUser.getBirthday().substring(0,4) + ")");
        InfoLabel2.setBounds(0 , 40 + 40, CertificatePanel.getWidth(), 40);
        InfoLabel2.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        InfoLabel2.setHorizontalAlignment(JLabel.CENTER);

        JLabel InfoLabel3 = new JLabel();
        InfoLabel3.setBounds(0 , 40 + 80, CertificatePanel.getWidth(), 40);
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

        if (cert.getCertType() == 0)
            CertificatePanel.setBackground(new Color(dv.RedPastel()));
        if (cert.getCertType() == 1)
            CertificatePanel.setBackground(new Color(dv.YellowPastel()));
        if (cert.getCertType() == 2)
            CertificatePanel.setBackground(new Color(dv.GreenPastel()));

        CertificatePanel.add(InfoLabel);
        CertificatePanel.add(InfoLabel2);
        CertificatePanel.add(InfoLabel3);
        CertificatePanel.add(ScrollPaneInjList);
    }

    private void initComponents()
    {
        this.setSize(dv.FrameWidth(), dv.FrameHeight());
        this.setVisible(true);
        this.setBackground(new Color(dv.ViewBackgroundColor()));
        this.setLayout(null);

        initPersonalInfoPanel();

        this.add(PersonalInfoPanel);
        this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
    }

    /*CONSTRUCTOR*/

    public SearchCitizenView(Organization org)
    {
        orgUser = org;
        initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == SearchButton)
        {
            String InputFullName = FullNameField.getText();
            String InputBirthday = BirthdayField.getText();
            int InputGender = GenderComboBox.getSelectedIndex()-1;
            String InputPhone = PhoneNumberField.getText();
            String InputPersonalID = PersonalIDField.getText();

            if (dv.checkStringInputValue(InputFullName, "Cảnh báo!","Nhập họ và tên!" ) != -2)
                return;
            if (dv.checkisNumberInputValue(InputBirthday, "Cảnh báo!", "Nhập ngày sinh là số!") != -2)
                return;
            if (dv.checkStringInputValue(String.valueOf(GenderComboBox.getSelectedItem()), "Cảnh báo!", "Chọn giới tính!") != -2)
                return;
            if (dv.checkStringInputValue(InputPhone, "Cảnh báo!", "Nhập số điện thoại!") != -2)
                return;
            if (dv.checkStringInputValue(InputPersonalID, "Cảnh báo!", "Nhập CMND/CCCD!") != -2)
                return;

            String query = "";
            if (orgUser.getID().equals("MOH"))
                query = "select LastName, FirstName, Birthday, Gender, Phone, ID" +
                    " from PERSON" +
                    " where (LastName || ' ' || FirstName) = '" + InputFullName + "'" +
                    " and extract(year from Birthday) = '" + InputBirthday + "'" +
                    " and Gender = " + InputGender +
                    " and ID = '" + InputPersonalID + "'" +
                    " and Phone = '" + InputPhone + "'";
            else
                query = "select LastName, FirstName, Birthday, Gender, Phone, ID" +
                        " from PERSON" +
                        " where " +
                        "exists (select PersonalID from REGISTER REG, SCHEDULE SCHED " +
                        "       where SCHED.ID = REG.SchedID" +
                        "       and OrgID = '" + orgUser.getID() + "'" +
                        "       and PersonalID = PERSON.ID)" +
                        " and (LastName || ' ' || FirstName) = '" + InputFullName + "'" +
                        " and extract(year from Birthday) = '" + InputBirthday + "'" +
                        " and Gender = " + InputGender +
                        " and ID = '" + InputPersonalID + "'" +
                        " and Phone = '" + InputPhone + "'";

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                PreparedStatement st = connection.prepareStatement(query);
                ResultSet rs = st.executeQuery(query);

                rs.next();
                personalUser.setLastName(rs.getString("LastName"));
                personalUser.setFirstName(rs.getString("FirstName"));
                personalUser.setBirthday(rs.getString("Birthday"));
                personalUser.setGender(rs.getInt("Gender"));
                personalUser.setPhone(rs.getString("Phone"));
                personalUser.setID(rs.getString("ID"));
            } catch (SQLException ex)
            {
                if (ex.getErrorCode() == 17289)
                {
                    if (CertificatePanel != null)
                    {
                        CertificatePanel.removeAll();
                        this.setBackground(new Color(dv.ViewBackgroundColor()));
                        this.removeAll();
                        this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
                        this.add(PersonalInfoPanel);
                    }
                    dv.popupOption(null, "Không tìm thấy thông tin người dùng!", "Lỗi " + ex.getErrorCode(), 2);
                }
                else
                    dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                return;
            } finally {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            initInjectionListPanel();
            initScrollPaneInjList();
            initCertificatePanel();
            this.add(CertificatePanel);
            InjectionListPanel.setPreferredSize(
                    new Dimension(InjectionListPanel.getWidth(), InjectionListPanel.getHeight()));
            InjectionListPanel.setBounds(0,0,InjectionListPanel.getWidth(), InjectionListPanel.getHeight());
            this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            String InputFullName = FullNameField.getText();
            String InputBirthday = BirthdayField.getText();
            int InputGender = GenderComboBox.getSelectedIndex()-1;
            String InputPhone = PhoneNumberField.getText();
            String InputPersonalID = PersonalIDField.getText();

            if (dv.checkStringInputValue(InputFullName, "Cảnh báo!","Nhập họ và tên!" ) != -2)
                return;
            if (dv.checkisNumberInputValue(InputBirthday, "Cảnh báo!", "Nhập ngày sinh là số!") != -2)
                return;
            if (dv.checkStringInputValue(String.valueOf(GenderComboBox.getSelectedItem()), "Cảnh báo!", "Chọn giới tính!") != -2)
                return;
            if (dv.checkStringInputValue(InputPhone, "Cảnh báo!", "Nhập số điện thoại!") != -2)
                return;
            if (dv.checkStringInputValue(InputPersonalID, "Cảnh báo!", "Nhập CMND/CCCD!") != -2)
                return;

            String query = "";
            if (orgUser.getID().equals("MOH"))
                query = "select LastName, FirstName, Birthday, Gender, Phone, ID" +
                        " from PERSON" +
                        " where (LastName || ' ' || FirstName) = '" + InputFullName + "'" +
                        " and extract(year from Birthday) = '" + InputBirthday + "'" +
                        " and Gender = " + InputGender +
                        " and ID = '" + InputPersonalID + "'" +
                        " and Phone = '" + InputPhone + "'";
            else
                query = "select LastName, FirstName, Birthday, Gender, Phone, ID" +
                        " from PERSON" +
                        " where " +
                        "exists (select PersonalID from REGISTER REG, SCHEDULE SCHED " +
                        "       where SCHED.ID = REG.SchedID" +
                        "       and OrgID = '" + orgUser.getID() + "'" +
                        "       and PersonalID = PERSON.ID)" +
                        " and (LastName || ' ' || FirstName) = '" + InputFullName + "'" +
                        " and extract(year from Birthday) = '" + InputBirthday + "'" +
                        " and Gender = " + InputGender +
                        " and ID = '" + InputPersonalID + "'" +
                        " and Phone = '" + InputPhone + "'";

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                PreparedStatement st = connection.prepareStatement(query);
                ResultSet rs = st.executeQuery(query);

                rs.next();
                personalUser.setLastName(rs.getString("LastName"));
                personalUser.setFirstName(rs.getString("FirstName"));
                personalUser.setBirthday(rs.getString("Birthday"));
                personalUser.setGender(rs.getInt("Gender"));
                personalUser.setPhone(rs.getString("Phone"));
                personalUser.setID(rs.getString("ID"));
            } catch (SQLException ex)
            {
                if (ex.getErrorCode() == 17289)
                {
                    if (CertificatePanel != null)
                    {
                        CertificatePanel.removeAll();
                        this.setBackground(new Color(dv.ViewBackgroundColor()));
                        this.removeAll();
                        this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
                        this.add(PersonalInfoPanel);
                    }
                    dv.popupOption(null, "Không tìm thấy thông tin người dùng!", "Lỗi " + ex.getErrorCode(), 2);
                }
                else
                    dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                return;
            } finally {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            initInjectionListPanel();
            initScrollPaneInjList();
            initCertificatePanel();
            this.add(CertificatePanel);
            this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
