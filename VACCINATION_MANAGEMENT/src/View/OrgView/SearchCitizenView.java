package View.OrgView;

import Process.Certificate;
import Process.DefaultValue;
import Process.Injection;
import Process.Person;
import View.CitizenView.UserInformationView;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/**
 *
 * @author ASUS
 */
public class SearchCitizenView extends JPanel implements ActionListener, KeyListener {

    /*Search Result*/
    private JScrollPane ScrollPaneInjList;
    private JPanel InjectionListPanel;
    private JLayeredPane LayeredPaneArea;

    /*Search Info*/
    private JPanel PersonalInfoPanel;
    private JLabel FullNameLabel;
    private JTextField FullNameField;
    private JLabel PhoneNumberLabel;
    private JTextField PhoneNumberField;
    private JLabel PersonalIDLabel;
    private  JTextField PersonalIDField;
    private JLabel BirthdayLabel;
    private JDatePickerImpl BirthdayField;
    private JLabel GenderLabel;
    private Choice GenderChoice;
    private  JButton SearchButton;

    /*Object Class*/
    private DefaultValue dv = new DefaultValue();
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
        BirthdayLabel = new JLabel("Ngày sinh");
        BirthdayLabel.setBounds(70, 70 + 6 * dv.LabelHeight() +dv.AlignTop_InfoView(),220,30);
        BirthdayLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        BirthdayLabel.setForeground(new Color(dv.FieldLabelColor()));
    }


    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }

    private void initBirthdayField()
    {
        UtilDateModel model=new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        BirthdayField = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        BirthdayField.setBounds(70, 70 + 7*dv.LabelHeight() +dv.AlignTop_InfoView(),170,30);

        JFormattedTextField textField = BirthdayField.getJFormattedTextField();

        textField.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));

        textField.setBounds(0, 100 + 7*dv.LabelHeight() +dv.AlignTop_InfoView(),170,30);

        BirthdayField.setTextEditable(true);
        BirthdayField.setForeground(new Color(dv.BlackTextColor()));
        BirthdayField.setVisible(true);
        BirthdayField.setEnabled(true);
    }

    private void initGenderLabel()
    {
        GenderLabel = new JLabel("Giới tính");
        GenderLabel.setBounds(70, 80 + 8*dv.LabelHeight() +dv.AlignTop_InfoView(), 200, 30);
        GenderLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        GenderLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initGenderChoice()
    {
        GenderChoice = new Choice();
        GenderChoice.setBounds(70, 80 + 9*dv.LabelHeight() +dv.AlignTop_InfoView(), 80, 28);
        GenderChoice.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        GenderChoice.setForeground(new Color(dv.FieldLabelColor()));

        GenderChoice.add("");
        GenderChoice.add("Nữ");
        GenderChoice.add("Nam");
        GenderChoice.add("Khác");
    }

    private void initSearchButton()
    {
        ImageIcon RegisterButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Search Filter Button.png"));
        SearchButton = new JButton();
        SearchButton.setBounds(70, 600, RegisterButtonIcon.getIconWidth(), RegisterButtonIcon.getIconHeight());
        SearchButton.setBorder(null);
        SearchButton.setContentAreaFilled(false);
        SearchButton.setIcon(RegisterButtonIcon);

        SearchButton.addActionListener(this);
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

        initGenderChoice();
        PersonalInfoPanel.add(GenderChoice);

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

        PersonalInfoPanel.repaint(0,0,dv.FrameWidth()-dv.FrameHeight(),dv.FrameHeight());
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
                cert.getInjectionList()[i].getOrg().setProvince(rs.getString("Province"));
                cert.getInjectionList()[i].getOrg().setDistrict(rs.getString("District"));
                cert.getInjectionList()[i].getOrg().setTown(rs.getString("Town"));
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
        ScrollPaneInjList.setBounds(0,120,600, 550);; //320 40
        ScrollPaneInjList.repaint(0,120,600, 550);
    }

    public void initLayeredPaneArea()
    {
        JLabel InfoLabel = new JLabel("CHỨNG NHẬN TIÊM CHỦNG");
        InfoLabel.setBounds(0, 0, 600, 40);
        InfoLabel.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        InfoLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel InfoLabel2 = new JLabel(personalUser.getFullName() + " (" + dv.getGenderName(personalUser.getGender()) + " - "
                + personalUser.getBirthday().substring(0,4) + ")");
        InfoLabel2.setBounds(0, 40, 600, 40);
        InfoLabel2.setFont(new Font(dv.fontName(),Font.BOLD, 24));
        InfoLabel2.setHorizontalAlignment(JLabel.CENTER);

        JLabel InfoLabel3 = new JLabel();
        InfoLabel3.setBounds(0, 80, 600, 40);
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
            this.setBackground(new Color(dv.RedPastel()));
        if (cert.getCertType() == 1)
            this.setBackground(new Color(dv.YellowPastel()));
        if (cert.getCertType() == 2)
            this.setBackground(new Color(dv.GreenPastel()));

        LayeredPaneArea = new JLayeredPane();
        LayeredPaneArea.setBounds(360+(720-600)/2,(this.getHeight()-630)/2,600, 630);
        LayeredPaneArea.setLayout(null);

        LayeredPaneArea.add(InfoLabel);
        LayeredPaneArea.add(InfoLabel2);
        LayeredPaneArea.add(InfoLabel3);
    }

    private void initComponents()
    {
        this.setSize(dv.FrameWidth(), dv.FrameHeight());
        this.setVisible(true);
        //this.setBackground(new Color(dv.ViewBackgroundColor()));
        this.setLayout(null);

        initPersonalInfoPanel();

        this.add(PersonalInfoPanel);
        this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
    }

    /*CONSTRUCTOR*/

    public SearchCitizenView()
    {
        initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == SearchButton)
        {
            JFormattedTextField textField = BirthdayField.getJFormattedTextField();

            String InputFullName = FullNameField.getText();
            String InputBirthday = textField.getText();
            int InputGender = GenderChoice.getSelectedIndex()-1;
            String InputPhone = PhoneNumberField.getText();
            String InputPersonalID = PersonalIDField.getText();

            if (dv.checkStringInputValue(InputFullName, "Cảnh báo!","Nhập họ và tên!" ) != -2)
                return;
            if (dv.checkStringInputValue(InputBirthday, "Nhập ngày sinh!", "Cảnh báo!") != -2)
                return;
            if (dv.checkStringInputValue(GenderChoice.getSelectedItem(), "Cảnh báo!", "Chọn giới tính!") != -2)
                return;
            if (dv.checkStringInputValue(InputPhone, "Cảnh báo!", "Nhập số điện thoại!") != -2)
                return;
            if (dv.checkStringInputValue(InputPersonalID, "Cảnh báo!", "Nhập CMND/CCCD!") != -2)
                return;

            String query = "select LastName, FirstName, Birthday, Gender, Phone, ID" +
                            " from PERSON" +
                            " where (LastName || ' ' || FirstName) = '" + InputFullName + "'" +
                            " and Birthday = '" + dv.toOracleDateFormat(InputBirthday) + "'" +
                            " and Gender = " + InputGender +
                            " and ID = '" + InputPersonalID + "'" +
                            " and Phone = '" + InputPhone + "'";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
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
                    dv.popupOption(null, "Không tìm thấy thông tin người dùng!", "Lỗi " + ex.getErrorCode(), 2);
                else
                    dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                return;
            }

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

            initInjectionListPanel();
            initScrollPaneInjList();

            initLayeredPaneArea();
            LayeredPaneArea.add(ScrollPaneInjList, Integer.valueOf(0));

            this.removeAll();

            this.add(LayeredPaneArea);
            this.add(PersonalInfoPanel);
            this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight()-1);
            this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
