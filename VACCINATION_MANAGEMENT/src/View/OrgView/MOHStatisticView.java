package View.OrgView;

import Process.*;
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
import java.time.LocalDate;
import java.util.Properties;

/**
 *
 * @author NghiepCuong
 */
public class MOHStatisticView extends JPanel implements ActionListener, KeyListener {
    private DefaultValue dv = new DefaultValue();
    private Account acc = new Account();
    private Organization mohUser = new Organization();

    /*Confirm Statistic*/
    private JPanel ConfirmStatisticPanel;
    private JLabel WithinDaysLabel;
    private JTextField WithinDaysField;
    private JLabel ConfirmPasswordLabel;
    private JTextField PasswordField;
    private JButton ConfirmStatisticButton;

    /*Statistic List*/
    private JScrollPane ScrollPaneStatisticList;
    private JPanel StatisticListPanel;
    
    private JButton ExportReportButton;

    private JLayeredPane LayeredPaneArea;

    /*
     *   INITIALIZE CONFIRM STATISTIC PANEL
     *   - PANEL:
     *       + LABEL
     *       + PASSWORDFILED
     *       + BUTTON: CONFIRM
     * */
    private void initWithinDaysLabel() {
        WithinDaysLabel = new JLabel();
        WithinDaysLabel.setBounds(0, 0, dv.LabelWidth() + 50, dv.LabelHeight());
        WithinDaysLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        WithinDaysLabel.setForeground(new Color(0x666666));
        WithinDaysLabel.setText("Số ngày thống kê");
        WithinDaysLabel.setSize(dv.FieldWidth(), dv.FieldHeight());
    }

    private void initWithinDaysField() {
        WithinDaysField = new JTextField();
        WithinDaysField.setBounds(0, 30, dv.FieldWidth(), dv.FieldHeight());
        WithinDaysField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        WithinDaysField.setForeground(new Color(dv.BlackTextColor()));
        WithinDaysField.addKeyListener(this);
        WithinDaysField.addKeyListener(this);
    }

    private void initConfirmPasswordLabel() {
        ConfirmPasswordLabel = new JLabel();
        ConfirmPasswordLabel.setBounds(0, 80, dv.LabelWidth() + 50, dv.LabelHeight());
        ConfirmPasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ConfirmPasswordLabel.setForeground(new Color(0x666666));
        ConfirmPasswordLabel.setText("Xác nhận mật khẩu");
        ConfirmPasswordLabel.setSize(dv.FieldWidth(), dv.FieldHeight());
    }

    private void initPasswordField() {
        PasswordField = new JPasswordField();
        PasswordField.setBounds(0, 110, dv.FieldWidth(), dv.FieldHeight());
        PasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        PasswordField.setForeground(new Color(dv.BlackTextColor()));
        PasswordField.addKeyListener(this);
        PasswordField.addKeyListener(this);
    }

    private void initConfirmStatisticButton() {
        ConfirmStatisticButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Resources/icon/Confirm Button.png"));
        ConfirmStatisticButton.setIcon(SearchIcon);

        ConfirmStatisticButton.setBounds(0, 150, dv.FieldWidth(), SearchIcon.getIconHeight());
        ConfirmStatisticButton.setBorder(null);
        ConfirmStatisticButton.setContentAreaFilled(false);

        ConfirmStatisticButton.addActionListener(this);
    }

    private void initConfirmStatisticPanel() {
        initWithinDaysLabel();
        initWithinDaysField();
        initConfirmPasswordLabel();
        initPasswordField();
        initConfirmStatisticButton();

        ConfirmStatisticPanel = new JPanel();
        ConfirmStatisticPanel.setBounds(dv.AlignLeft(), 80, dv.LabelWidth() + 50, 250);
        ConfirmStatisticPanel.setLayout(null);
        ConfirmStatisticPanel.setBackground(new Color(dv.ViewBackgroundColor()));
//        ConfirmStatisticPanel.setBorder(dv.border());

        ConfirmStatisticPanel.add(WithinDaysLabel);
        ConfirmStatisticPanel.add(WithinDaysField);
        ConfirmStatisticPanel.add(ConfirmPasswordLabel);
        ConfirmStatisticPanel.add(PasswordField);
        ConfirmStatisticPanel.add(ConfirmStatisticButton);
    }


    /*
     *   INITIALIZE THE LIST OF FILLED FORMS OF THE CITIZEN
     *   - SCROLLPANE:
     *       + PANEL: LIST OF STATISTIC
     *           - PANELS: STATISTICS
     *               + LABELS
     * */

    private void initStatisticListPanel(int Days)
    {
        StatisticListPanel = new JPanel();
        StatisticListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        StatisticListPanel.setLayout(new FlowLayout());
        StatisticListPanel.setPreferredSize(new Dimension(660,1250));

        String query = "select * from STATISTIC";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            Statistic Stat = new Statistic();
            rs.next();
            Stat.setTitle(rs.getString("Title"));
            Stat.setData(rs.getInt("Data"));
            if (rs.getString("LastUpdate") != null)
                Stat.setLastUpdate(LocalDate.parse(rs.getString("LastUpdate").substring(0, 10)));

            JLabel LastUpdateLabel = new JLabel("Cập nhật ngày: " + Stat.getLastUpdate());
            LastUpdateLabel.setFont(new Font(dv.fontName(), 2, 16));
            LastUpdateLabel.setForeground(new Color(dv.BlackTextColor()));
            LastUpdateLabel.setPreferredSize(new Dimension(640, 25));
            LastUpdateLabel.setHorizontalAlignment(JLabel.LEFT);

            /*Injection Doses Statistic*/
            /*

            JLabel BasicDoseLabel = new JLabel("Số mũi cơ bản hoàn thành: " + Stat.getData());
            BasicDoseLabel.setFont(new Font(dv.fontName(), 0, 16));
            BasicDoseLabel.setForeground(new Color(dv.BlackTextColor()));
            BasicDoseLabel.setBounds(30, 10+  30, 600, 25);
            BasicDoseLabel.setHorizontalAlignment(JLabel.LEFT);

            rs.next();
            Stat.setTitle(rs.getString("Title"));
            Stat.setData(rs.getInt("Data"));
            JLabel BoosterDoseLabel = new JLabel("Số mũi tăng cường hoàn thành: " + Stat.getData());
            BoosterDoseLabel.setFont(new Font(dv.fontName(), 0, 16));
            BoosterDoseLabel.setForeground(new Color(dv.BlackTextColor()));
            BoosterDoseLabel.setBounds(30, 10+  60, 600, 25);
            BoosterDoseLabel.setHorizontalAlignment(JLabel.LEFT);

            rs.next();
            Stat.setTitle(rs.getString("Title"));
            Stat.setData(rs.getInt("Data"));
            JLabel RepeatDoseLabel = new JLabel("Số mũi nhắc lại hoàn thành: " + Stat.getData());
            RepeatDoseLabel.setFont(new Font(dv.fontName(), 0, 16));
            RepeatDoseLabel.setForeground(new Color(dv.BlackTextColor()));
            RepeatDoseLabel.setBounds(30, 10+  90, 600, 25);
            RepeatDoseLabel.setHorizontalAlignment(JLabel.LEFT);

            InjDoses.add(InjDosesLabel);
            InjDoses.add(BasicDoseLabel);
            InjDoses.add(BoosterDoseLabel);
            InjDoses.add(RepeatDoseLabel);

            *//*Target Statistic*//*

            rs.next();
            Stat.setTitle(rs.getString("Title"));
            Stat.setData(rs.getInt("Data"));
            JLabel TargetChildrenLabel = new JLabel("Số trẻ em hoàn thành liều cơ bản (dưới 12 tuổi): "
                    + Stat.getData());
            TargetChildrenLabel.setFont(new Font(dv.fontName(), 0, 16));
            TargetChildrenLabel.setForeground(new Color(dv.BlackTextColor()));
            TargetChildrenLabel.setBounds(30, 10+  30, 600, 25);
            TargetChildrenLabel.setHorizontalAlignment(JLabel.LEFT);

            rs.next();
            Stat.setTitle(rs.getString("Title"));
            Stat.setData(rs.getInt("Data"));
            JLabel TargetTeenagerLabel = new JLabel("Số thanh thiếu niên hoàn thành liều cơ bản (từ 13 đến 22 tuổi): "
                    + Stat.getData());
            TargetTeenagerLabel.setFont(new Font(dv.fontName(), 0, 16));
            TargetTeenagerLabel.setForeground(new Color(dv.BlackTextColor()));
            TargetTeenagerLabel.setBounds(30, 10+  60, 600, 25);
            TargetTeenagerLabel.setHorizontalAlignment(JLabel.LEFT);

            rs.next();
            Stat.setTitle(rs.getString("Title"));
            Stat.setData(rs.getInt("Data"));
            JLabel TargetAdultLabel = new JLabel("Số người lớn hoàn thành liều cơ bản (trên 22 đến dưới 50 tuổi): "
                    + Stat.getData());
            TargetAdultLabel.setFont(new Font(dv.fontName(), 0, 16));
            TargetAdultLabel.setForeground(new Color(dv.BlackTextColor()));
            TargetAdultLabel.setBounds(30, 10+  90, 600, 25);
            TargetAdultLabel.setHorizontalAlignment(JLabel.LEFT);

            rs.next();
            Stat.setTitle(rs.getString("Title"));
            Stat.setData(rs.getInt("Data"));
            JLabel TargetOldPeopleLabel = new JLabel("Số người cao tuổi hoàn thành liều cơ bản (trên 50 tuổi): "
                    + Stat.getData());
            TargetOldPeopleLabel.setFont(new Font(dv.fontName(), 0, 16));
            TargetOldPeopleLabel.setForeground(new Color(dv.BlackTextColor()));
            TargetOldPeopleLabel.setBounds(30, 10+  120, 600, 25);
            TargetOldPeopleLabel.setHorizontalAlignment(JLabel.LEFT);

            Target.add(TargetLabel);
            Target.add(TargetChildrenLabel);
            Target.add(TargetTeenagerLabel);
            Target.add(TargetAdultLabel);
            Target.add(TargetOldPeopleLabel);


            *//*Affected by Covid-19 Stattistic*//*


            rs.next();
            Stat.setTitle(rs.getString("Title"));
            Stat.setData(rs.getInt("Data"));
            JLabel AffectedChildrenLabel = new JLabel("Số trẻ em nhiễm Covid-19 (dưới 12 tuổi): " + Stat.getData());
            AffectedChildrenLabel.setFont(new Font(dv.fontName(), 0, 16));
            AffectedChildrenLabel.setForeground(new Color(dv.BlackTextColor()));
            AffectedChildrenLabel.setBounds(30, 10+  30, 600, 25);
            AffectedChildrenLabel.setHorizontalAlignment(JLabel.LEFT);

            rs.next();
            Stat.setTitle(rs.getString("Title"));
            Stat.setData(rs.getInt("Data"));
            JLabel AffectedTeenagerLabel = new JLabel("Số thanh thiếu niên nhiễm Covid-19 (từ 13 đến 22 tuổi): " + Stat.getData());
            AffectedTeenagerLabel.setFont(new Font(dv.fontName(), 0, 16));
            AffectedTeenagerLabel.setForeground(new Color(dv.BlackTextColor()));
            AffectedTeenagerLabel.setBounds(30, 10+  60, 600, 25);
            AffectedTeenagerLabel.setHorizontalAlignment(JLabel.LEFT);

            rs.next();
            Stat.setTitle(rs.getString("Title"));
            Stat.setData(rs.getInt("Data"));
            JLabel AffectedAdultLabel = new JLabel("Số người lớn nhiễm Covid-19 (trên 22 đến dưới 50 tuổi): " + Stat.getData());
            AffectedAdultLabel.setFont(new Font(dv.fontName(), 0, 16));
            AffectedAdultLabel.setForeground(new Color(dv.BlackTextColor()));
            AffectedAdultLabel.setBounds(30, 10+  90, 600, 25);
            AffectedAdultLabel.setHorizontalAlignment(JLabel.LEFT);

            rs.next();
            Stat.setTitle(rs.getString("Title"));
            Stat.setData(rs.getInt("Data"));
            JLabel AffectedOldPeopleLabel = new JLabel("Số người cao tuổi nhiễm Covid-19 (trên 50 tuổi): " + Stat.getData());
            AffectedOldPeopleLabel.setFont(new Font(dv.fontName(), 0, 16));
            AffectedOldPeopleLabel.setForeground(new Color(dv.BlackTextColor()));
            AffectedOldPeopleLabel.setBounds(30, 10+  120, 600, 25);
            AffectedOldPeopleLabel.setHorizontalAlignment(JLabel.LEFT);

            Affected.add(AffectedLabel);
            Affected.add(AffectedChildrenLabel);
            Affected.add(AffectedTeenagerLabel);
            Affected.add(AffectedAdultLabel);
            Affected.add(AffectedOldPeopleLabel);*/

            JPanel InjDosesPanel = new JPanel();
            InjDosesPanel.setLayout(null);
            InjDosesPanel.setBackground(Color.WHITE);
            InjDosesPanel.setPreferredSize(new Dimension(640, 400));
            InjDosesPanel.setLayout(new FlowLayout());

            JLabel InjDosesLabel = new JLabel("BIỂU ĐỒ THỐNG KÊ SỐ MŨI TIÊM");
            InjDosesLabel.setFont(new Font(dv.fontName(), 1, 18));
            InjDosesLabel.setForeground(new Color(dv.FeatureButtonColor()));
            InjDosesLabel.setBounds(30, 10+  1, 600, 25);
            InjDosesLabel.setHorizontalAlignment(JLabel.CENTER);

            BarChart InjDosesChart = new BarChart("","Số mũi", "Loại mũi");
            InjDosesChart.addDataSetValue(Stat.getData(), "Số mũi", "Cơ bản");
            rs.next();
            InjDosesChart.addDataSetValue(Stat.getData(), "Số mũi", "Tăng cường");
            rs.next();
            InjDosesChart.addDataSetValue(Stat.getData(), "Số mũi", "Nhắc lại");

            InjDosesPanel.add(InjDosesLabel);
            InjDosesPanel.add(InjDosesChart.ChartPanel(620,350));


            JPanel TargetPanel = new JPanel();
            TargetPanel.setLayout(null);
            TargetPanel.setBackground(Color.WHITE);
            TargetPanel.setPreferredSize(new Dimension(640, 400));
            TargetPanel.setLayout(new FlowLayout());

            JLabel TargetLabel = new JLabel("THỐNG KÊ SỐ ĐỐI TƯỢNG HOÀN THÀNH LIỀU CƠ BẢN");
            TargetLabel.setFont(new Font(dv.fontName(), 1, 18));
            TargetLabel.setForeground(new Color(dv.FeatureButtonColor()));
            TargetLabel.setBounds(30, 10+  1, 600, 25);
            TargetLabel.setHorizontalAlignment(JLabel.LEFT);

            BarChart VaccinationTargetChart  = new BarChart("","Số đối tượng", "Đối tượng");
            rs.next();
            VaccinationTargetChart.addDataSetValue(Stat.getData(),"Số đối tượng", "Trẻ em");
            rs.next();
            VaccinationTargetChart.addDataSetValue(Stat.getData(),"Số đối tượng", "Thanh-thiếu niên");
            rs.next();
            VaccinationTargetChart.addDataSetValue(Stat.getData(),"Số đối tượng", "Người lớn");
            rs.next();
            VaccinationTargetChart.addDataSetValue(Stat.getData(),"Số đối tượng", "Người cao tuổi");

            TargetPanel.add(TargetLabel);
            TargetPanel.add(VaccinationTargetChart.ChartPanel(620,350));



            JPanel AffectedPanel = new JPanel();
            AffectedPanel.setLayout(null);
            AffectedPanel.setBackground(Color.WHITE);
            AffectedPanel.setPreferredSize(new Dimension(640, 400));
            AffectedPanel.setLayout(new FlowLayout());

            JLabel AffectedLabel = new JLabel("THỐNG KÊ SỐ CA NHIỄM COVID-19 TRONG VÒNG " + Days + " NGÀY QUA");
            AffectedLabel.setFont(new Font(dv.fontName(), 1, 18));
            AffectedLabel.setForeground(new Color(dv.FeatureButtonColor()));
            AffectedLabel.setBounds(30, 10+  1, 600, 25);
            AffectedLabel.setHorizontalAlignment(JLabel.LEFT);

            BarChart AffectedTarget = new BarChart("", "Số ca nhiễm", "Đối tượng");
            rs.next();
            AffectedTarget.addDataSetValue(Stat.getData(),"Số ca nhiễm", "Trẻ em");
            rs.next();
            AffectedTarget.addDataSetValue(Stat.getData(),"Số ca nhiễm", "Thanh-thiếu niên");
            rs.next();
            AffectedTarget.addDataSetValue(Stat.getData(),"Số ca nhiễm", "Người lớn");
            rs.next();
            AffectedTarget.addDataSetValue(Stat.getData(),"Số ca nhiễm", "Người cao tuổi");

            AffectedPanel.add(AffectedLabel);
            AffectedPanel.add(AffectedTarget.ChartPanel(620,350));

            StatisticListPanel.add(LastUpdateLabel);
            StatisticListPanel.add(InjDosesPanel);
            StatisticListPanel.add(TargetPanel);
            StatisticListPanel.add(AffectedPanel);



        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
            ex.printStackTrace();
            return;
        }
    }

    private void initScrollPaneStatisticList() {
        ScrollPaneStatisticList = new JScrollPane(StatisticListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneStatisticList.setBounds(0, 40, 680, 590); //320 40
    }


    /*
     *   INITIALIZE A FILL FORM PANEL
     *   - BUTTON: FILL FORM
     *   - PANEL:
     *       + LABELS
     *       + CHOICES
     *       + BUTTON: CONFIRM
     * */
    private void initExportReportButton() {
        ImageIcon ExportReportButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Export Report Button.png"));
        ExportReportButton = new JButton();
        ExportReportButton.setBounds((320 - ExportReportButtonIcon.getIconWidth()) / 2, 600,
                ExportReportButtonIcon.getIconWidth(), ExportReportButtonIcon.getIconHeight());
        ExportReportButton.setBorder(null);
        ExportReportButton.setContentAreaFilled(false);
        ExportReportButton.setIcon(ExportReportButtonIcon);
        ExportReportButton.addActionListener(this);
    }

    private void initLayeredPaneArea() {
        LayeredPaneArea = new JLayeredPane();
        LayeredPaneArea.setLayout(null);
        LayeredPaneArea.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        LayeredPaneArea.setBounds(320, 40, 680, 630);
    }

    private void initComponents() {
        this.setSize(dv.FrameWidth(), dv.FrameHeight());
        this.setVisible(true);
        this.setBackground(new Color(dv.ViewBackgroundColor()));
        this.setLayout(null);

        //initConfirmStatisticPanel
        initConfirmStatisticPanel();
        this.add(ConfirmStatisticPanel);

        //init ExportReportButton
        initExportReportButton();
        this.add(ExportReportButton);

        //init StatisticListPanel
        JLabel StatisticListLabel = new JLabel("BẢNG THỐNG KÊ SỐ LIỆU TIÊM CHỦNG");
        StatisticListLabel.setBounds(0, 0, 640, 40);
        StatisticListLabel.setFont(new Font(dv.fontName(), 1, 20));
        StatisticListLabel.setForeground(new Color(dv.FeatureButtonColor()));
        StatisticListLabel.setHorizontalAlignment(JLabel.CENTER);

        initStatisticListPanel(7);
        initScrollPaneStatisticList();

        //init LayeredPaneArea
        initLayeredPaneArea();

        LayeredPaneArea.add(StatisticListLabel, Integer.valueOf(0));
        LayeredPaneArea.add(ScrollPaneStatisticList, Integer.valueOf(0));
        this.add(LayeredPaneArea);

        this.repaint(0, 0, dv.FrameWidth(), dv.FrameHeight());
    }

    /*CONSTRUCTOR*/
    public MOHStatisticView(Organization moh)
    {
        mohUser = moh;
        initComponents();
    }

    /*ACTION PERFORMED*/
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == ConfirmStatisticButton)
        {
            String InputPassword = PasswordField.getText();

            if ( dv.checkStringInputValue(InputPassword, "Cảnh báo!","Xác nhận mật khẩu để cập nhật thông tin!") != -2 )
                return;

            String query = "select *" +
                    " from ACCOUNT" +
                    " where Username = '" + mohUser.getID() + "'";
            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                PreparedStatement st = connection.prepareStatement(query);
                ResultSet rs = st.executeQuery(query);

                rs.next();
                acc.setUsername(rs.getString("Username"));
                acc.setPassword(rs.getString("Password"));
                acc.setRole(rs.getInt("Role"));
                acc.setStatus(rs.getInt("Status"));
                if (acc.getPassword().equals(InputPassword) == false)
                {
                    dv.popupOption(null, "Mật khẩu không đúng!", "Lỗi!", 2);
                    return;
                }
            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

            String InputWithinDays = WithinDaysField.getText();

            if (dv.checkisNumberInputValue(InputWithinDays, "Lỗi!", "Số ngày thống kê phải là số!") != -2)
                return;

            String plsql = "{call STAT_STATISTIC_ALL(?)}";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                CallableStatement cst = connection.prepareCall(plsql);
                cst.setInt("par_Days", Integer.parseInt(InputWithinDays));
                cst.execute();
            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                ex.printStackTrace();
                return;
            }

            LayeredPaneArea.removeAll();
            LayeredPaneArea.repaint(320, 40, 680, 630);

            JLabel StatisticListLabel = new JLabel("BẢNG THỐNG KÊ SỐ LIỆU TIÊM CHỦNG");
            StatisticListLabel.setBounds(0, 0, 640, 40);
            StatisticListLabel.setFont(new Font(dv.fontName(), 1, 20));
            StatisticListLabel.setForeground(new Color(dv.FeatureButtonColor()));
            StatisticListLabel.setHorizontalAlignment(JLabel.CENTER);

            initStatisticListPanel(Integer.parseInt(InputWithinDays));
            initScrollPaneStatisticList();

            LayeredPaneArea.add(StatisticListLabel, Integer.valueOf(0));
            LayeredPaneArea.add(ScrollPaneStatisticList, Integer.valueOf(0));
            LayeredPaneArea.repaint(320, 40, 680, 630);
        }

/*        if (e.getSource() == ExportReportButton) {
            StatisticListPanel = null;
            ScrollPaneStatisticList = null;

            LayeredPaneArea.removeAll();

            initCreateFormPanel();

            LayeredPaneArea.add(CreateFormPanel, Integer.valueOf(0));

            LayeredPaneArea.repaint(320, 40, 680, 630);
        }*/

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            String InputPassword = PasswordField.getText();

            if ( dv.checkStringInputValue(InputPassword, "Cảnh báo!","Xác nhận mật khẩu để cập nhật thông tin!") != -2 )
                return;

            String query = "select *" +
                    " from ACCOUNT" +
                    " where Username = '" + mohUser.getID() + "'";
            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                PreparedStatement st = connection.prepareStatement(query);
                ResultSet rs = st.executeQuery(query);

                rs.next();
                acc.setUsername(rs.getString("Username"));
                acc.setPassword(rs.getString("Password"));
                acc.setRole(rs.getInt("Role"));
                acc.setStatus(rs.getInt("Status"));
                if (acc.getPassword().equals(InputPassword) == false)
                {
                    dv.popupOption(null, "Mật khẩu không đúng!", "Lỗi!", 2);
                    return;
                }
            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

            String InputWithinDays = WithinDaysField.getText();

            if (dv.checkisNumberInputValue(InputWithinDays, "Lỗi!", "Số ngày thống kê phải là số!") != -2)
                return;

            String plsql = "{call STAT_ALL(?)}";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                CallableStatement cst = connection.prepareCall(plsql);
                cst.setInt("par_Days", Integer.parseInt(InputWithinDays));

            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                ex.printStackTrace();
                return;
            }

            LayeredPaneArea.removeAll();
            LayeredPaneArea.repaint(320, 40, 680, 630);

            JLabel StatisticListLabel = new JLabel("BẢNG THỐNG KÊ SỐ LIỆU TIÊM CHỦNG");
            StatisticListLabel.setBounds(0, 0, 640, 40);
            StatisticListLabel.setFont(new Font(dv.fontName(), 1, 20));
            StatisticListLabel.setForeground(new Color(dv.FeatureButtonColor()));
            StatisticListLabel.setHorizontalAlignment(JLabel.CENTER);

            initStatisticListPanel(Integer.parseInt(InputWithinDays));
            initScrollPaneStatisticList();

            LayeredPaneArea.add(StatisticListLabel, Integer.valueOf(0));
            LayeredPaneArea.add(ScrollPaneStatisticList, Integer.valueOf(0));
            LayeredPaneArea.repaint(320, 40, 680, 630);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
