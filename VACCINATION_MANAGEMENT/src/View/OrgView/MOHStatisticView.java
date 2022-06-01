package View.OrgView;

import Process.*;
import com.lowagie.text.DocumentException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;

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
    private JLabel StatisticListLabel;
    private JScrollPane ScrollPaneStatisticList;
    private JPanel StatisticListPanel;
    private JLabel LastUpdateLabel;
    private JPanel InjDosesPanel;
    private JPanel TargetPanel;
    private JPanel AffectedPanel;
    private JLabel InjDosesLabel;
    private JLabel TargetLabel;
    private JLabel AffectedLabel;
    private BarChart InjDosesChart;
    private BarChart VaccinationTargetChart;
    private BarChart AffectedTargetChart;

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

            LastUpdateLabel = new JLabel("Cập nhật ngày: " + Stat.getLastUpdate());
            LastUpdateLabel.setFont(new Font(dv.fontName(), 2, 16));
            LastUpdateLabel.setForeground(new Color(dv.BlackTextColor()));
            LastUpdateLabel.setPreferredSize(new Dimension(640, 25));
            LastUpdateLabel.setHorizontalAlignment(JLabel.LEFT);

            InjDosesPanel = new JPanel();
            InjDosesPanel.setBackground(Color.WHITE);
            InjDosesPanel.setPreferredSize(new Dimension(580, 350));
            InjDosesPanel.setLayout(new FlowLayout());

            InjDosesLabel = new JLabel("BIỂU ĐỒ THỐNG KÊ SỐ MŨI TIÊM");
            InjDosesLabel.setFont(new Font(dv.fontName(), 1, 18));
            InjDosesLabel.setForeground(new Color(dv.FeatureButtonColor()));
            InjDosesLabel.setBounds(30, 10+  1, 580, 25);
            InjDosesLabel.setHorizontalAlignment(JLabel.CENTER);

            InjDosesChart = new BarChart("","Số mũi", "Loại mũi");
            InjDosesChart.addDataSetValue(Stat.getData(), "Số mũi", "Cơ bản");
            rs.next();
            InjDosesChart.addDataSetValue(Stat.getData(), "Số mũi", "Tăng cường");
            rs.next();
            InjDosesChart.addDataSetValue(Stat.getData(), "Số mũi", "Nhắc lại");

            InjDosesPanel.add(InjDosesLabel);
            InjDosesPanel.add(InjDosesChart.getChartPanel(550, 320));


            TargetPanel = new JPanel();
            TargetPanel.setBackground(Color.WHITE);
            TargetPanel.setPreferredSize(new Dimension(580, 350));
            TargetPanel.setLayout(new FlowLayout());

            TargetLabel = new JLabel("THỐNG KÊ SỐ ĐỐI TƯỢNG HOÀN THÀNH LIỀU CƠ BẢN");
            TargetLabel.setFont(new Font(dv.fontName(), 1, 18));
            TargetLabel.setForeground(new Color(dv.FeatureButtonColor()));
            TargetLabel.setBounds(30, 10+  1, 580, 25);
            TargetLabel.setHorizontalAlignment(JLabel.CENTER);

            VaccinationTargetChart  = new BarChart("","Số đối tượng", "Đối tượng");
            rs.next();
            VaccinationTargetChart.addDataSetValue(Stat.getData(),"Số đối tượng", "Trẻ em");
            rs.next();
            VaccinationTargetChart.addDataSetValue(Stat.getData(),"Số đối tượng", "Thanh-thiếu niên");
            rs.next();
            VaccinationTargetChart.addDataSetValue(Stat.getData(),"Số đối tượng", "Người lớn");
            rs.next();
            VaccinationTargetChart.addDataSetValue(Stat.getData(),"Số đối tượng", "Người cao tuổi");

            TargetPanel.add(TargetLabel);
            TargetPanel.add(VaccinationTargetChart.getChartPanel(550, 320));


            AffectedPanel = new JPanel();
            AffectedPanel.setBackground(Color.WHITE);
            AffectedPanel.setPreferredSize(new Dimension(580, 350));
            AffectedPanel.setLayout(new FlowLayout());

            AffectedLabel = new JLabel("THỐNG KÊ SỐ CA NHIỄM COVID-19 TRONG VÒNG " + Days + " NGÀY QUA");
            AffectedLabel.setFont(new Font(dv.fontName(), 1, 18));
            AffectedLabel.setForeground(new Color(dv.FeatureButtonColor()));
            AffectedLabel.setBounds(30, 10+  1, 580, 25);
            AffectedLabel.setHorizontalAlignment(JLabel.CENTER);

            AffectedTargetChart = new BarChart("", "Số ca nhiễm", "Đối tượng");
            rs.next();
            AffectedTargetChart.addDataSetValue(Stat.getData(),"Số ca nhiễm", "Trẻ em");
            rs.next();
            AffectedTargetChart.addDataSetValue(Stat.getData(),"Số ca nhiễm", "Thanh-thiếu niên");
            rs.next();
            AffectedTargetChart.addDataSetValue(Stat.getData(),"Số ca nhiễm", "Người lớn");
            rs.next();
            AffectedTargetChart.addDataSetValue(Stat.getData(),"Số ca nhiễm", "Người cao tuổi");

            AffectedPanel.add(AffectedLabel);
            AffectedPanel.add(AffectedTargetChart.getChartPanel(550, 300));

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
        ScrollPaneStatisticList.setBorder(null);
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
        StatisticListLabel = new JLabel("BẢNG THỐNG KÊ SỐ LIỆU TIÊM CHỦNG");
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

        if (e.getSource() == ExportReportButton)
        {
//            JOptionPane InputOption = new JOptionPane(null, 0, 0, null);
//            String fileName = InputOption.showInputDialog(null, "Tên tệp");

            PrintedPanel statisticPaper = new PrintedPanel();
//            statisticPaper.setWidth(550);
//            statisticPaper.setHeight(800);

            statisticPaper.addLabel(new JLabel(new ImageIcon(getClass().getResource("/Resources/icon/MOH-logo.png"))),
                    0, 1, 1, 2.5F, 2.5F);

            statisticPaper.addLabel(new JLabel("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM"),
                    PrintedPanel.NORMAL, 8, 3, 11.5F, 0.6F);

            statisticPaper.addLabel(new JLabel("Độc lập - Tự do - Hạnh phúc"),
                    0, 10.5F, 4F, 6.5F, 0.6F);

            statisticPaper.addLabel(LastUpdateLabel, 2, 2,5,6,0.5F);
            JLabel StatisticPaperLabel = new JLabel("BÁO CÁO THỐNG KÊ SỐ LIỆU TIÊM CHỦNG");
            StatisticPaperLabel.setBounds(0, 0, 640, 40);
            StatisticPaperLabel.setFont(new Font(dv.fontName(), 1, 20));
            StatisticPaperLabel.setForeground(new Color(dv.BlackTextColor()));
            StatisticPaperLabel.setHorizontalAlignment(JLabel.CENTER);
            statisticPaper.addLabel(StatisticPaperLabel, 1, 2,6,17,1);

            JPanel chartPanel = new JPanel();
            chartPanel.setBounds(0,0,550,320);
            chartPanel.setLayout(new FlowLayout());
            chartPanel.add(InjDosesChart.getChartPanel2(550,320));

            statisticPaper.addLabel(InjDosesLabel, 1,2,8,17,0.8F);
            statisticPaper.addPanel(chartPanel,2,9,17,9);

            /*JPanel chartPanel2 = new JPanel();
            chartPanel2.setBounds(0,0,550+1,320);
            chartPanel2.setLayout(new FlowLayout());
            chartPanel2.add(InjDosesChart.getChartPanel2(550,320));*/
            chartPanel = new JPanel();
            chartPanel.setBounds(0,0,550+1,320);
            chartPanel.setLayout(new FlowLayout());
            chartPanel.add(VaccinationTargetChart.getChartPanel2(550,320));

            statisticPaper.addLabel(TargetLabel, 1, 2, 19,17, 0.8F);
            statisticPaper.addPanel(chartPanel,2,20,17,9);

            StatisticListPanel.removeAll();
            StatisticListPanel.setLayout(null);
            StatisticListPanel.setBounds(0,0,dv.A4Width(), dv.A4Height());
            StatisticListPanel.add(statisticPaper.getPrintedPanel());

            this.repaint(0,0,dv.FrameWidth(),dv.FrameHeight());

            ExportPDF pdfFile = new ExportPDF();

            try {
                pdfFile.chooseDirectory();
                pdfFile.addMetaData("MOHStatistic", "", "Vaccination Statistic", "MOH", "MOH");
                pdfFile.getPdfwriter();
                pdfFile.openPDF();

                pdfFile.addObject(statisticPaper.getPrintedPanel());
//                pdfFile.addObject(StatisticListPanel);

                pdfFile.closePDF();
            } catch (DocumentException | FileNotFoundException ex) {
                ex.printStackTrace();
                return;
            }


        }

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
