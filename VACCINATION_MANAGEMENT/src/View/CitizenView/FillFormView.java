package View.CitizenView;

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
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

/**
 *
 * @author NghiepCuong
 */
public class FillFormView extends JPanel implements ActionListener{
    private DefaultValue dv = new DefaultValue();
    private Person personalUser = new Person();

    /*Form List*/
    private JPanel FormFilterPanel;
    private JLabel FormFilterLabel;
    private JComboBox FormFilterComboBox;
    private JButton FormFilterButton;

    private JScrollPane ScrollPaneFormList;
    private JPanel FormListPanel;

    /*Create Form*/
    private JButton FillFormButton;
    private JPanel CreateFormPanel;

    private JLayeredPane LayeredPaneArea;

    /*
     *   INITIALIZE THE FILTER OF FILLED FORMS OF THE CITIZEN
     *   - PANEL:
     *       + LABEL
     *       + ComboBox
     *       + BUTTON: SELECT
     * */
    private void initFormFilterLabel() {
        FormFilterLabel = new JLabel();
        FormFilterLabel.setBounds(0, 0, dv.LabelWidth() + 50, dv.LabelHeight());
        FormFilterLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        FormFilterLabel.setForeground(new Color(0x666666));
        FormFilterLabel.setText("Tờ khai trong vòng");
        FormFilterLabel.setSize(dv.FieldWidth(), dv.FieldHeight());
    }

    private void initFormFilterComboBox() {
        FormFilterComboBox = new JComboBox();
        FormFilterComboBox.setBounds(0, 30, dv.FieldWidth(), dv.FieldHeight());
        FormFilterComboBox.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        FormFilterComboBox.setForeground(new Color(dv.BlackTextColor()));
        FormFilterComboBox.setBackground(Color.WHITE);

        FormFilterComboBox.addItem("7 ngày");
        FormFilterComboBox.addItem("14 ngày");
        FormFilterComboBox.addItem("28 ngày");
    }

    private void initFormFilterButton() {
        FormFilterButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Resources/icon/Search Filter Button.png"));
        FormFilterButton.setIcon(SearchIcon);

        FormFilterButton.setBounds(0, 70, dv.FieldWidth(), SearchIcon.getIconHeight());
        FormFilterButton.setBorder(null);
        FormFilterButton.setContentAreaFilled(false);

        FormFilterButton.addActionListener(this);
    }

    private void initFormFilterPanel() {
        initFormFilterLabel();
        initFormFilterComboBox();
        initFormFilterButton();

        FormFilterPanel = new JPanel();
        FormFilterPanel.setBounds(dv.AlignLeft(), 80, dv.LabelWidth() + 50, 125);
        FormFilterPanel.setLayout(null);
        FormFilterPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        FormFilterPanel.add(FormFilterLabel);
        FormFilterPanel.add(FormFilterComboBox);
        FormFilterPanel.add(FormFilterButton);
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
                FirstAns.setText("<html>Có một trong các dấu hiệu sốt, ho, khó thở, viêm phổi, đau họng, mệt mỏi trong vòng 14 ngày qua");
                FirstAns.setFont(new Font(dv.fontName(), 0, 13));
                FirstAns.setForeground(new Color(dv.BlackTextColor()));
            } else {
                FirstAns.setText("<html>1. Không có dấu hiệu sốt, ho, khó thở, viêm phổi, đau họng, mệt mỏi trong vòng 14 ngày qua");
                FirstAns.setFont(new Font(dv.fontName(), 0, 13));
                FirstAns.setForeground(new Color(dv.GreenPastel()));
            }

            JLabel SecondAns = new JLabel();
            SecondAns.setBounds(30, 75, 600, 25);
            SecondAns.setHorizontalAlignment(JLabel.LEFT);
            if (Heal.getHealths().substring(1, 2).equals("1")) {
                SecondAns.setText("<html>Có tiếp xúc với Người bệnh hoặc nghi ngờ, mắc bệnh COVID-19 trong vòng 14 ngày qua");
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
                ThirdAns.setText("<html>Là đối tượng đang dương tính với Covid-19");
                ThirdAns.setFont(new Font(dv.fontName(), 1, 13));
                ThirdAns.setForeground(new Color(dv.RedPastel()));
            } else {
                ThirdAns.setText("<html>Không là đối tượng đang dương tính với Covid-19");
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

    private void initFormListPanel(int Within) {
        FormListPanel = new JPanel();
        FormListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        FormListPanel.setLayout(new FlowLayout());
        Health Heal;
        int listHeight = 0;
        int i = 0;

        String nDaysAgo = dv.toOracleDateFormat(String.valueOf(LocalDate.parse(dv.todayString()).minusDays(Within)));

        String query = "select * from HEALTH HEAL" +
                " where HEAL.PersonalID = '" + personalUser.getID() + "'" +
                " and FilledDate >= '" + nDaysAgo + "'" +
                " order by FilledDate desc, ID desc";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Heal = new Health();
                Heal.setID(rs.getInt("ID"));
                Heal.getPerson().setID(rs.getString("PersonalID"));
                Heal.setFilledDate(LocalDate.parse(rs.getString("FilledDate").substring(0, 10)));
                Heal.setHealths(rs.getString("Healths"));
                FormListPanel.add(initFormPanel(Heal));
                if (Heal.getHealths().equals("0000"))
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
        FormListPanel.setPreferredSize(new Dimension(660, listHeight + i * 10));
    }

    private void initScrollPaneFormList() {
        ScrollPaneFormList = new JScrollPane(FormListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneFormList.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        ScrollPaneFormList.setBounds(0, 40, 680, 590); //320 40
        ScrollPaneFormList.setBorder(null);
    }


    /*
     *   INITIALIZE A FILL FORM PANEL
     *   - BUTTON: FILL FORM
     *   - PANEL:
     *       + LABELS
     *       + ComboBoxS
     *       + BUTTON: CONFIRM
     * */
    private void initFillFormButton() {
        ImageIcon FillFormButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Fill Form Button.png"));
        FillFormButton = new JButton();
        FillFormButton.setBounds((320 - FillFormButtonIcon.getIconWidth()) / 2, 600, FillFormButtonIcon.getIconWidth(), FillFormButtonIcon.getIconHeight());
        FillFormButton.setBorder(null);
        FillFormButton.setContentAreaFilled(false);
        FillFormButton.setIcon(FillFormButtonIcon);
        FillFormButton.addActionListener(this);
    }

    private void initCreateFormPanel() {
        JLabel CreateHealLabel = new JLabel("KHAI BÁO Y TẾ");
        CreateHealLabel.setPreferredSize(new Dimension(600, 35));
        CreateHealLabel.setFont(new Font(dv.fontName(), 1, 20));
        CreateHealLabel.setForeground(new Color(dv.FeatureButtonColor()));
        CreateHealLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel FilledDateLabel = new JLabel("Ngày thực hiện khai báo: ");
        FilledDateLabel.setPreferredSize(new Dimension(200, 30));
        FilledDateLabel.setFont(new Font(dv.fontName(), 0, 16));
        FilledDateLabel.setForeground(new Color(dv.BlackTextColor()));

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl FilledDateField = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        FilledDateField.setPreferredSize(new Dimension(150, 30));

        FilledDateField.setForeground(new Color(dv.BlackTextColor()));
        FilledDateField.setVisible(true);
        FilledDateField.setEnabled(true);

        JLabel FirstQuesLabel = new JLabel("<html>Trong vòng 14 ngày qua, Anh/Chị có thấy xuất hiện ít nhất 1 trong các dấu hiệu: " +
                "sốt, ho, khó thở, viêm phổi, đau họng, mệt mỏi không?:");
        FirstQuesLabel.setPreferredSize(new Dimension(600, 50));
        FirstQuesLabel.setFont(new Font(dv.fontName(), 0, 16));
        FirstQuesLabel.setForeground(new Color(dv.BlackTextColor()));

        JComboBox FirstQuesComboBox = new JComboBox();
        FirstQuesComboBox.setPreferredSize(new Dimension(80, 30));
        FirstQuesComboBox.setFont(new Font(dv.fontName(), 0, 16));
        FirstQuesComboBox.setForeground(new Color(dv.BlackTextColor()));
        FirstQuesComboBox.setBackground(Color.WHITE);
        FirstQuesComboBox.addItem("Không");
        FirstQuesComboBox.addItem("Có");

        JLabel SecondQuesLabel = new JLabel("<html>Trong vòng 14 ngày qua, Anh/Chị có tiếp xúc với Người bệnh hoặc nghi ngờ," +
                " mắc bệnh COVID-19 không?");
        SecondQuesLabel.setPreferredSize(new Dimension(600, 50));
        SecondQuesLabel.setFont(new Font(dv.fontName(), 0, 16));
        SecondQuesLabel.setForeground(new Color(dv.BlackTextColor()));

        JComboBox SecondQuesComboBox = new JComboBox();
        SecondQuesComboBox.setPreferredSize(new Dimension(80, 30));
        SecondQuesComboBox.setFont(new Font(dv.fontName(), 0, 16));
        SecondQuesComboBox.setForeground(new Color(dv.BlackTextColor()));
        SecondQuesComboBox.setBackground(Color.WHITE);
        SecondQuesComboBox.addItem("Không");
        SecondQuesComboBox.addItem("Có");

        JLabel ThirdQuesLabel = new JLabel("<html>Anh/Chị có đang dương tính với Covid-19 không?");
        ThirdQuesLabel.setPreferredSize(new Dimension(600, 50));
        ThirdQuesLabel.setFont(new Font(dv.fontName(), 0, 16));
        ThirdQuesLabel.setForeground(new Color(dv.BlackTextColor()));

        JComboBox ThirdQuesComboBox = new JComboBox();
        ThirdQuesComboBox.setPreferredSize(new Dimension(80, 30));
        ThirdQuesComboBox.setFont(new Font(dv.fontName(), 0, 16));
        ThirdQuesComboBox.setForeground(new Color(dv.BlackTextColor()));
        ThirdQuesComboBox.setBackground(Color.WHITE);
        ThirdQuesComboBox.addItem("Không");
        ThirdQuesComboBox.addItem("Có");

        JLabel FourthQuesLabel = new JLabel("<html>Anh/Chị có đang là đối tượng trì hoãn tiêm chủng vaccine Covid-19 hoặc " +
                "đang là đối tượng chống chỉ định với tiêm chủng Covid-19 không?");
        FourthQuesLabel.setPreferredSize(new Dimension(600, 50));
        FourthQuesLabel.setFont(new Font(dv.fontName(), 0, 16));
        FourthQuesLabel.setForeground(new Color(dv.BlackTextColor()));

        JComboBox FourthQuesComboBox = new JComboBox();
        FourthQuesComboBox.setPreferredSize(new Dimension(80, 30));
        FourthQuesComboBox.setFont(new Font(dv.fontName(), 0, 16));
        FourthQuesComboBox.setForeground(new Color(dv.BlackTextColor()));
        FourthQuesComboBox.setBackground(Color.WHITE);
        FourthQuesComboBox.addItem("Không");
        FourthQuesComboBox.addItem("Có");


        ActionListener handleCreateHeal = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate FilledDate = LocalDate.parse(FilledDateField.getJFormattedTextField().getText());
                LocalDate sysdate = LocalDate.parse(dv.todayString());

                if (FilledDate.isAfter(sysdate))
                {
                    dv.popupOption(null,"Không thể khai báo y tế cho tương lai", "Lỗi!", 2);
                    return;
                }

                String InputFirstQues = String.valueOf(FirstQuesComboBox.getSelectedIndex());
                String InputSecondQues = String.valueOf(SecondQuesComboBox.getSelectedIndex());
                String InputThirdQues = String.valueOf(ThirdQuesComboBox.getSelectedIndex());
                String InputFourthQues = String.valueOf(FourthQuesComboBox.getSelectedIndex());

                int answer = dv.popupConfirmOption(null, "Xác nhận khai báo?", "Xác nhận!");

                if (answer == JOptionPane.YES_OPTION) {
                    String plsql = "{call HEAL_INSERT_RECORD(?,?,?,?)}";

                    Connection connection = null;
                    try {
                        connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                        CallableStatement cst = connection.prepareCall(plsql);
                        cst.setString("par_PersonalID", personalUser.getID());
                        cst.setString("par_FilledDate", dv.toOracleDateFormat(FilledDateField.getJFormattedTextField().getText()));
                        cst.setString("par_Healths", InputFirstQues + InputSecondQues + InputThirdQues + InputFourthQues);
                        cst.setString("par_Note", "");

                        cst.execute();
                    } catch (SQLException ex) {
                        dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                        ex.printStackTrace();
                        return;
                    }

                    dv.popupOption(null, "Khai báo thành công!", "Thông báo!", 0);
                }
            }
        };

        ImageIcon CreateHealButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Confirm Button.png"));
        JButton CreateHealButton = new JButton();
        CreateHealButton.setPreferredSize(new Dimension(CreateHealButtonIcon.getIconWidth(), CreateHealButtonIcon.getIconHeight()));
        CreateHealButton.setContentAreaFilled(false);
        CreateHealButton.setBorder(null);
        CreateHealButton.setIcon(CreateHealButtonIcon);
        CreateHealButton.addActionListener(handleCreateHeal);

        CreateFormPanel = new JPanel();
        CreateFormPanel.setBounds(0, 0, 660, 630);
        CreateFormPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        CreateFormPanel.setLayout(new GridBagLayout());
        CreateFormPanel.setBorder(dv.border());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;

        c.insets = new Insets(0, 0, 20, 0);
        c.gridy = 0;
        CreateFormPanel.add(CreateHealLabel, c);

        c.insets = new Insets(0, 0, 20, 0);
        c.gridy = 1;
        CreateFormPanel.add(FilledDateLabel, c);

        c.insets = new Insets(0, 0, 20, 0);
        c.gridy = 2;
        CreateFormPanel.add(FilledDateField, c);

        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 3;
        CreateFormPanel.add(FirstQuesLabel, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 15, 0);
        c.gridy = 4;
        CreateFormPanel.add(FirstQuesComboBox, c);

        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 5;
        CreateFormPanel.add(SecondQuesLabel, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 15, 0);
        c.gridy = 6;
        CreateFormPanel.add(SecondQuesComboBox, c);

        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 7;
        CreateFormPanel.add(ThirdQuesLabel, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 15, 0);
        c.gridy = 8;
        CreateFormPanel.add(ThirdQuesComboBox, c);

        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 9;
        CreateFormPanel.add(FourthQuesLabel, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 30, 0);
        c.gridy = 10;
        CreateFormPanel.add(FourthQuesComboBox, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 11;
        CreateFormPanel.add(CreateHealButton, c);
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

        //initFormFilterPanel
        initFormFilterPanel();
        this.add(FormFilterPanel);

        //init FillFormButton
        initFillFormButton();
        this.add(FillFormButton);

        //init FormListPanel
        JLabel HealListLabel = new JLabel("DANH SÁCH TỜ KHAI Y TẾ (" + personalUser.getFullName() + ")");
        HealListLabel.setBounds(0, 0, 640, 40);
        HealListLabel.setFont(new Font(dv.fontName(), 1, 20));
        HealListLabel.setForeground(new Color(dv.FeatureButtonColor()));
        HealListLabel.setHorizontalAlignment(JLabel.CENTER);

        initFormListPanel(7);
        initScrollPaneFormList();

        //init LayeredPaneArea
        initLayeredPaneArea();

        LayeredPaneArea.add(HealListLabel, Integer.valueOf(0));
        LayeredPaneArea.add(ScrollPaneFormList, Integer.valueOf(0));
        this.add(LayeredPaneArea);

        this.repaint(0, 0, dv.FrameWidth(), dv.FrameHeight());
    }

    /*CONSTRUCTOR*/
    public FillFormView(Person person) {
        personalUser = person;
        initComponents();
    }

    /*ACTION PERFORMED*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == FormFilterButton) {
            CreateFormPanel = null;
            LayeredPaneArea.removeAll();
            LayeredPaneArea.repaint(320, 40, 680, 630);

            JLabel HealListLabel = new JLabel("DANH SÁCH TỜ KHAI Y TẾ (" + personalUser.getFullName() + ")");
            HealListLabel.setBounds(0, 0, 640, 40);
            HealListLabel.setFont(new Font(dv.fontName(), 1, 20));
            HealListLabel.setForeground(new Color(dv.FeatureButtonColor()));
            HealListLabel.setHorizontalAlignment(JLabel.CENTER);

            initFormListPanel((FormFilterComboBox.getSelectedIndex() + 1) * 7);
            initScrollPaneFormList();

            LayeredPaneArea.add(HealListLabel, Integer.valueOf(0));
            LayeredPaneArea.add(ScrollPaneFormList, Integer.valueOf(0));
            LayeredPaneArea.repaint(320, 40, 680, 630);
        }

        if (e.getSource() == FillFormButton) {
            FormListPanel = null;
            ScrollPaneFormList = null;

            LayeredPaneArea.removeAll();

            initCreateFormPanel();

            LayeredPaneArea.add(CreateFormPanel, Integer.valueOf(0));

            LayeredPaneArea.repaint(320, 40, 680, 630);
        }

    }
}
