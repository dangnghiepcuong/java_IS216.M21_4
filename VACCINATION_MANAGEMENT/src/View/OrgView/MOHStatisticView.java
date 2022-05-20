package View.OrgView;

import Process.DateLabelFormatter;
import Process.DefaultValue;
import Process.Health;
import Process.Person;
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
    private Person personalUser = new Person();

    /*Confirm Statistic*/
    private JPanel ConfirmStatisticPanel;
    private JLabel ConfirmPasswordLabel;
    private JTextField PasswordField;
    private JButton ConfirmStatisticButton;

    /*Statistic List*/
    private JScrollPane ScrollPaneStatisticList;
    private JPanel StatisticListPanel;
    
    private JButton ExportReportButton;

    private JLayeredPane LayeredPaneArea;

    /*
     *   INITIALIZE THE FILTER OF FILLED FORMS OF THE CITIZEN
     *   - PANEL:
     *       + LABEL
     *       + CHOICE
     *       + BUTTON: SELECT
     * */
    private void initConfirmPasswordLabel() {
        ConfirmPasswordLabel = new JLabel();
        ConfirmPasswordLabel.setBounds(0, 0, dv.LabelWidth() + 50, dv.LabelHeight());
        ConfirmPasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ConfirmPasswordLabel.setForeground(new Color(0x666666));
        ConfirmPasswordLabel.setText("Tờ khai trong vòng");
        ConfirmPasswordLabel.setSize(dv.FieldWidth(), dv.FieldHeight());
    }

    private void initPasswordField() {
        PasswordField = new JPasswordField();
        PasswordField.setBounds(0, 30, dv.FieldWidth(), dv.FieldHeight());
        PasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        PasswordField.setForeground(new Color(dv.BlackTextColor()));
        PasswordField.addKeyListener(this);
    }

    private void initConfirmStatisticButton() {
        ConfirmStatisticButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Resources/icon/Search Filter Button.png"));
        ConfirmStatisticButton.setIcon(SearchIcon);

        ConfirmStatisticButton.setBounds(0, 70, dv.FieldWidth(), SearchIcon.getIconHeight());
        ConfirmStatisticButton.setBorder(null);
        ConfirmStatisticButton.setContentAreaFilled(false);

        ConfirmStatisticButton.addActionListener(this);
    }

    private void initConfirmStatisticPanel() {
        initConfirmPasswordLabel();
        initPasswordField();
        initConfirmStatisticButton();

        ConfirmStatisticPanel = new JPanel();
        ConfirmStatisticPanel.setBounds(dv.AlignLeft(), 80, dv.LabelWidth() + 50, 125);
        ConfirmStatisticPanel.setLayout(null);
        ConfirmStatisticPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        ConfirmStatisticPanel.add(ConfirmPasswordLabel);
        ConfirmStatisticPanel.add(PasswordField);
        ConfirmStatisticPanel.add(ConfirmStatisticButton);
    }


    /*
     *   INITIALIZE THE LIST OF FILLED FORMS OF THE CITIZEN
     *   - SCROLLPANE:
     *       + PANEL: LIST OF FORMS
     *           - PANELS: FORMS
     *               + LABELS
     * */
    private JPanel initFormPanel(Health Heal) {
        JPanel FormPanel = new JPanel();
        FormPanel.setLayout(null);
        FormPanel.setBackground(Color.WHITE);

        JLabel Target = new JLabel("Đối tượng: " + personalUser.getFullName()
                + " (ID: " + personalUser.getID() + ")");
        Target.setFont(new Font(dv.fontName(), 1, 16));
        Target.setForeground(new Color(dv.BlackTextColor()));
        Target.setBounds(30, 1, 600, 25);
        Target.setHorizontalAlignment(JLabel.LEFT);

        JLabel FilledDate = new JLabel("Ngày thực hiện khai báo: " + Heal.getFilledDate());
        FilledDate.setFont(new Font(dv.fontName(), 1, 16));
        FilledDate.setForeground(new Color(dv.BlackTextColor()));
        FilledDate.setBounds(30, 25, 600, 25);
        FilledDate.setHorizontalAlignment(JLabel.LEFT);

        FormPanel.add(Target);
        FormPanel.add(FilledDate);

        if (Heal.getHealths().equals("0000")) {
            JLabel NormalHealth = new JLabel("Sức khỏe bình thường - Đạt điều kiện sức khỏe tiêm chủng");
            NormalHealth.setFont(new Font(dv.fontName(), 1, 13));
            NormalHealth.setForeground(new Color(dv.GreenPastel()));
            NormalHealth.setBounds(30, 50, 600, 25);
            NormalHealth.setHorizontalAlignment(JLabel.LEFT);

            FormPanel.setPreferredSize(new Dimension(640, 80));
            FormPanel.add(NormalHealth);
        } else {
            JLabel FirstAns = new JLabel();
            FirstAns.setBounds(30, 50, 600, 25);
            FirstAns.setHorizontalAlignment(JLabel.LEFT);
            if (Heal.getHealths().substring(0, 1).equals("1")) {
                FirstAns.setText("<html>1. Có một trong các dấu hiệu sốt, ho, khó thở, viêm phổi, đau họng, mệt mỏi trong vòng 14 ngày qua");
                FirstAns.setFont(new Font(dv.fontName(), 0, 13));
                FirstAns.setForeground(new Color(dv.BlackTextColor()));
            } else {
                FirstAns.setText("<html>Không có dấu hiệu sốt, ho, khó thở, viêm phổi, đau họng, mệt mỏi trong vòng 14 ngày qua");
                FirstAns.setFont(new Font(dv.fontName(), 0, 13));
                FirstAns.setForeground(new Color(dv.GreenPastel()));
            }

            JLabel SecondAns = new JLabel();
            SecondAns.setBounds(30, 75, 600, 25);
            SecondAns.setHorizontalAlignment(JLabel.LEFT);
            if (Heal.getHealths().substring(1, 2).equals("1")) {
                SecondAns.setText("<html>2. Có tiếp xúc với Người bệnh hoặc nghi ngờ, mắc bệnh COVID-19 trong vòng 14 ngày qua");
                SecondAns.setFont(new Font(dv.fontName(), 0, 13));
                SecondAns.setForeground(new Color(dv.BlackTextColor()));
            } else {
                SecondAns.setText("<html>Không tiếp xúc với Người bệnh hoặc nghi ngờ, mắc bệnh COVID-19 trong vòng 14 ngày qua");
                SecondAns.setFont(new Font(dv.fontName(), 0, 13));
                SecondAns.setForeground(new Color(dv.GreenPastel()));
            }

            JLabel ThirdAns = new JLabel();
            ThirdAns.setBounds(30, 100, 600, 25);
            ThirdAns.setHorizontalAlignment(JLabel.LEFT);
            if (Heal.getHealths().substring(2, 3).equals("1")) {
                ThirdAns.setText("<html>3. Là đối tượng đang dương tính với Covid-19");
                ThirdAns.setFont(new Font(dv.fontName(), 1, 13));
                ThirdAns.setForeground(new Color(dv.RedPastel()));
            } else {
                ThirdAns.setText("<html>4. Không là đối tượng đang dương tính với Covid-19");
                ThirdAns.setFont(new Font(dv.fontName(), 0, 13));
                ThirdAns.setForeground(new Color(dv.GreenPastel()));
            }

            JLabel FourthAns = new JLabel();
            FourthAns.setBounds(30, 125, 600, 25);
            FourthAns.setHorizontalAlignment(JLabel.LEFT);
            if (Heal.getHealths().substring(3, 4).equals("1")) {
                FourthAns.setText("<html>Là đối tượng trì hoãn tiêm chủng hoặc chống chỉ định với tiêm chủng vaccine Covid-19");
                FourthAns.setFont(new Font(dv.fontName(), 1, 13));
                FourthAns.setForeground(new Color(dv.RedPastel()));
            } else {
                FourthAns.setText("<html>Không là đối tượng trì hoãn tiêm chủng hoặc chống chỉ định với tiêm chủng vaccine Covid-19");
                FourthAns.setFont(new Font(dv.fontName(), 0, 13));
                FourthAns.setForeground(new Color(dv.GreenPastel()));
            }

            FormPanel.setPreferredSize(new Dimension(640, 150));
            FormPanel.add(FirstAns);
            FormPanel.add(SecondAns);
            FormPanel.add(ThirdAns);
            FormPanel.add(FourthAns);
        }
        return FormPanel;
    }

    private void initStatisticListPanel() {
        StatisticListPanel = new JPanel();
        StatisticListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        StatisticListPanel.setLayout(new FlowLayout());

        JPanel Stat = new JPanel();

        String query = "select * from STATISTIC";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Stat = new Statistic();
                Stat.setID(rs.getInt("ID"));
                Stat.getPerson().setID(rs.getString("PersonalID"));
                Stat.setFilledDate(LocalDate.parse(rs.getString("FilledDate").substring(0, 10)));
                Stat.setStatths(rs.getString("Statths"));
                StatisticListPanel.add(initFormPanel(Stat));
                if (Stat.getHealths().equals("0000"))
                    listHeight += 80;
                else
                    listHeight += 150;
                i++;
            }
        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
            ex.printStackTrace();
            return;
        }
        StatisticListPanel.setPreferredSize(new Dimension(660, listHeight + i * 10));
    }

    private void initScrollPaneStatisticList() {
        ScrollPaneStatisticList = new JScrollPane(StatisticListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneStatisticList.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
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
        ImageIcon ExportReportButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Fill Form Button.png"));
        ExportReportButton = new JButton();
        ExportReportButton.setBounds((320 - ExportReportButtonIcon.getIconWidth()) / 2, 600, ExportReportButtonIcon.getIconWidth(), ExportReportButtonIcon.getIconHeight());
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
        JLabel HealListLabel = new JLabel("DANH SÁCH TỜ KHAI Y TẾ (" + personalUser.getFullName() + ")");
        HealListLabel.setBounds(0, 0, 640, 40);
        HealListLabel.setFont(new Font(dv.fontName(), 1, 20));
        HealListLabel.setForeground(new Color(dv.FeatureButtonColor()));
        HealListLabel.setHorizontalAlignment(JLabel.CENTER);

        initStatisticListPanel(7);
        initScrollPaneStatisticList();

        //init LayeredPaneArea
        initLayeredPaneArea();

        LayeredPaneArea.add(HealListLabel, Integer.valueOf(0));
        LayeredPaneArea.add(ScrollPaneStatisticList, Integer.valueOf(0));
        this.add(LayeredPaneArea);

        this.repaint(0, 0, dv.FrameWidth(), dv.FrameHeight());
    }

    /*CONSTRUCTOR*/
    public MOHStatisticView(Person person) {
        personalUser = person;
        initComponents();
    }

    /*ACTION PERFORMED*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ConfirmStatisticButton) {
            LayeredPaneArea.removeAll();
            LayeredPaneArea.repaint(320, 40, 680, 630);

            JLabel HealListLabel = new JLabel("DANH SÁCH TỜ KHAI Y TẾ (" + personalUser.getFullName() + ")");
            HealListLabel.setBounds(0, 0, 640, 40);
            HealListLabel.setFont(new Font(dv.fontName(), 1, 20));
            HealListLabel.setForeground(new Color(dv.FeatureButtonColor()));
            HealListLabel.setHorizontalAlignment(JLabel.CENTER);

            initStatisticListPanel();
            initScrollPaneStatisticList();

            LayeredPaneArea.add(HealListLabel, Integer.valueOf(0));
            LayeredPaneArea.add(ScrollPaneStatisticList, Integer.valueOf(0));
            LayeredPaneArea.repaint(320, 40, 680, 630);
        }

        if (e.getSource() == ExportReportButton) {
            StatisticListPanel = null;
            ScrollPaneStatisticList = null;

            LayeredPaneArea.removeAll();

            initCreateFormPanel();

            LayeredPaneArea.add(CreateFormPanel, Integer.valueOf(0));

            LayeredPaneArea.repaint(320, 40, 680, 630);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
