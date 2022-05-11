package GUI_FillFormView;

import Data_Processor.*;
import GUI_ManageSchedule.ManageScheduleView;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;

/**
 *
 * @author NghiepCuong
 */
public class FillFormView extends JPanel implements ActionListener
{
    private DefaultValue dv = new DefaultValue();
    private Person personalUser = new Person();

    /*Schedule List*/
    private JPanel FormFilterPanel;
    private JLabel FormFilterLabel;
    private Choice FormFilterChoice;
    private JButton FormFilterButton;

    private JScrollPane ScrollPaneFormList;
    private JPanel FormListPanel;
    private JPanel FormPanel[] = new JPanel[50];

    /*Create Schedule*/
    private JLabel FillFormLabel;
    private JButton FillFormButton;
    private JPanel CreateFormPanel;

    private JLayeredPane LayeredPaneArea;


    public Person getPersonalUser() {
        return personalUser;
    }

    public void setPersonalUser(Person personalUser) {
        this.personalUser = personalUser;
    }

    /*

*/
    private void initFormFilterLabel()
    {
        FormFilterLabel = new JLabel();
        FormFilterLabel.setBounds(0, 0, dv.LabelWidth()+50, dv.LabelHeight());
        FormFilterLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        FormFilterLabel.setForeground(new Color(0x666666));
        FormFilterLabel.setText("Tờ khai trong vòng (ngày):");
        FormFilterLabel.setSize(dv.FieldWidth(),dv.FieldHeight());
    }

    private void initFormFilterChoice()
    {
        FormFilterChoice = new Choice();
        FormFilterChoice.setBounds(0, 40, dv.FieldWidth(), dv.FieldHeight());
        FormFilterChoice.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        FormFilterChoice.setForeground(new Color(dv.BlackTextColor()));

        FormFilterChoice.add("7");
        FormFilterChoice.add("14");
        FormFilterChoice.add("30");
    }

    private void initFormFilterButton()
    {
        FormFilterButton = new JButton();
        ImageIcon SearchIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Magnifying Glass Button_1.png"));
        FormFilterButton.setIcon(SearchIcon);

        FormFilterButton.setBounds(0, 110, dv.FieldWidth(), SearchIcon.getIconHeight());
        FormFilterButton.setBorder(null);
        FormFilterButton.setContentAreaFilled(false);

        FormFilterButton.addActionListener(this);
    }

    private void initFormFilterPanel()
    {
        initFormFilterLabel();
        initFormFilterChoice();
        initFormFilterButton();

        FormFilterPanel = new JPanel();
        FormFilterPanel.setBounds(dv.AlignLeft(), 80, dv.LabelWidth()+50, 110 + 56);
        FormFilterPanel.setLayout(null);
        FormFilterPanel.setBackground(new Color(dv.ViewBackgroundColor()));

        FormFilterPanel.add(FormFilterLabel);
        FormFilterPanel.add(FormFilterChoice);
        FormFilterPanel.add(FormFilterButton);
    }

    private void initFormPanel(int i, Health Heal)
    {
        JLabel Target = new JLabel("Đối tượng: " + personalUser.getFullName()
                + " (ID: " + getPersonalUser().getID() + ")");
        Target.setFont(new Font(dv.fontName(), 1, 20));
        Target.setForeground(new Color(dv.BlackTextColor()));
        Target.setBounds(30,2,600,30);
        Target.setHorizontalAlignment(JLabel.LEFT);


        String temp = dv.toApplicationDate(String.valueOf(Heal.getFilledDate()));

        JLabel FilledDate = new JLabel("Ngày thực hiện khai báo: " + Heal.getFilledDate());
        FilledDate.setFont(new Font(dv.fontName(), 1, 18));
        FilledDate.setForeground(new Color(dv.BlackTextColor()));
        FilledDate.setBounds(30,30,600,25);
        FilledDate.setHorizontalAlignment(JLabel.LEFT);

        FormPanel[i] = new JPanel();
        FormPanel[i].setLayout(null);
        FormPanel[i].setBackground(Color.WHITE);
        FormPanel[i].add(Target);
        FormPanel[i].add(FilledDate);

        if (Heal.getHealths().equals("0000"))
        {
            JLabel NormalHealth = new JLabel("Sức khỏe bình thường - Đạt điều kiện sức khỏe tiêm chủng");
            NormalHealth.setFont(new Font(dv.fontName(), 0, 20));
            NormalHealth.setForeground(new Color(dv.GreenPastel()));
            NormalHealth.setBounds(30,55,600,30);
            NormalHealth.setHorizontalAlignment(JLabel.LEFT);

            FormPanel[i].setPreferredSize(new Dimension(640,100));
            FormPanel[i].add(NormalHealth);
        }
        else
        {
            JLabel FirstAns = new JLabel();
            FirstAns.setBounds(30,60,600,30);
            FirstAns.setHorizontalAlignment(JLabel.LEFT);
            if (Heal.getHealths().substring(0,1).equals("1"))
            {
                FirstAns.setText("Có một trong các dấu hiệu sốt, ho, khó thở, viêm phổi, đau họng, mệt mỏi trong vòng 14 ngày qua");
                FirstAns.setFont(new Font(dv.fontName(), 1, 16));
                FirstAns.setForeground(new Color(dv.BlackTextColor()));
            }
            else
            {
                FirstAns.setText("Không có dấu hiệu sốt, ho, khó thở, viêm phổi, đau họng, mệt mỏi trong vòng 14 ngày qua");
                FirstAns.setFont(new Font(dv.fontName(), 0, 16));
                FirstAns.setForeground(new Color(dv.BlackTextColor()));
            }

            JLabel SecondAns = new JLabel();
            SecondAns.setBounds(30,90,600,30);
            SecondAns.setHorizontalAlignment(JLabel.LEFT);
            if (Heal.getHealths().substring(1,2).equals("1"))
            {
                SecondAns.setText("Có tiếp xúc với Người bệnh hoặc nghi ngờ, mắc bệnh COVID-19 trong vòng 14 ngày qua");
                SecondAns.setFont(new Font(dv.fontName(), 1, 16));
                SecondAns.setForeground(new Color(dv.BlackTextColor()));
            }
            else
            {
                SecondAns.setText("Không tiếp xúc với Người bệnh hoặc nghi ngờ, mắc bệnh COVID-19 trong vòng 14 ngày qua");
                SecondAns.setFont(new Font(dv.fontName(), 0, 16));
                SecondAns.setForeground(new Color(dv.BlackTextColor()));
            }


            JLabel ThirdAns = new JLabel();
            ThirdAns.setBounds(30,120,600,30);
            ThirdAns.setHorizontalAlignment(JLabel.LEFT);
            if (Heal.getHealths().substring(2,3).equals("1"))
            {
                ThirdAns.setText("Là đối tượng đang dương tính với Covid-19");
                ThirdAns.setFont(new Font(dv.fontName(), 1, 16));
                ThirdAns.setForeground(new Color(dv.RedPastel()));
            }
            else
            {
                ThirdAns.setText("Không là đối tượng đang dương tính với Covid-19");
                ThirdAns.setFont(new Font(dv.fontName(), 0, 16));
                ThirdAns.setForeground(new Color(dv.BlackTextColor()));
            }

            JLabel FourthAns = new JLabel();
            FourthAns.setBounds(30,150,600,30);
            FourthAns.setHorizontalAlignment(JLabel.LEFT);
            if (Heal.getHealths().substring(3,4).equals("1"))
            {
                FourthAns.setText("Là đối tượng trì hoãn tiêm chủng hoặc chống chỉ định với tiêm chủng vaccine Covid-19");
                FourthAns.setFont(new Font(dv.fontName(), 1, 16));
                FourthAns.setForeground(new Color(dv.RedPastel()));
            }
            else
            {
                FourthAns.setText("Không là đối tượng trì hoãn tiêm chủng hoặc chống chỉ định với tiêm chủng vaccine Covid-19");
                FourthAns.setFont(new Font(dv.fontName(), 0, 16));
                FourthAns.setForeground(new Color(dv.BlackTextColor()));
            }

            FormPanel[i].setPreferredSize(new Dimension(640,180));
            FormPanel[i].add(FirstAns);
            FormPanel[i].add(SecondAns);
            FormPanel[i].add(ThirdAns);
            FormPanel[i].add(FourthAns);
        }
    }

    private void initFormListPanel(int Within)
    {
        Health heals[] = new Health[100000];
        int nheals = 0;
        int i = 0;

        String nDaysAgo = dv.toOracleDateFormat(String.valueOf(LocalDate.parse(dv.todayString()).minusDays(Within)));

        String query = "select * from HEALTH HEAL" +
                " where HEAL.PersonalID = '" + personalUser.getID() + "'" +
                " and FilledDate >= '" + nDaysAgo + "'" +
                " order by FilledDate desc, ID desc";

        System.out.println(query);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                heals[i] = new Health();
                heals[i].setID(rs.getInt("ID"));
                heals[i].getPerson().setID(rs.getString("PersonalID"));
                heals[i].setFilledDate(LocalDate.parse(rs.getString("FilledDate").substring(0,10)));
                heals[i].setHealths(rs.getString("Healths"));
                i++;
            }
        } catch (SQLException ex) {
            dv.popupOption(null,ex.getMessage(), String.valueOf(ex.getErrorCode()),2);
            ex.printStackTrace();
            return;
        }

        nheals = i;

        FormListPanel = new JPanel();
        FormListPanel.setPreferredSize(new Dimension(660, 180*nheals+nheals*10));
        FormListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        FormListPanel.setLayout(new FlowLayout());

        for (i = 0; i<nheals; i++)
        {
            initFormPanel(i, heals[i]);
            FormListPanel.add(FormPanel[i]);
        }
    }

    private void initScrollPaneFormList(int Within)
    {
        initFormListPanel(Within);

        ScrollPaneFormList = new JScrollPane(FormListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneFormList.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        ScrollPaneFormList.setBounds(0, 40, 680, 590); //320 40
    }


    private void initFillFormButton()
    {
        ImageIcon FillFormButtonIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Fill Form Button.png"));
        FillFormButton = new JButton();
        FillFormButton.setBounds((320-FillFormButtonIcon.getIconWidth())/2, 300, FillFormButtonIcon.getIconWidth(), FillFormButtonIcon.getIconHeight());
        FillFormButton.setBorder(null);
        FillFormButton.setContentAreaFilled(false);
        FillFormButton.setIcon(FillFormButtonIcon);
        FillFormButton.addActionListener(this);
    }

    private void initCreateFormPanel()
    {
        JLabel CreateHealLabel = new JLabel("KHAI BÁO Y TẾ");
        CreateHealLabel.setPreferredSize(new Dimension(600, 35));
        CreateHealLabel.setFont(new Font(dv.fontName(), 1, 20));
        CreateHealLabel.setForeground(new Color(dv.FeatureButtonColor()));
        CreateHealLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel FilledDateLabel = new JLabel("Ngày thực hiện khai báo: ");
        FilledDateLabel.setPreferredSize(new Dimension(200, 30));
        FilledDateLabel.setFont(new Font(dv.fontName(), 0, 16));
        FilledDateLabel.setForeground(new Color(dv.BlackTextColor()));

        UtilDateModel model=new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl FilledDateField = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        FilledDateField.setPreferredSize(new Dimension(150, 30));

        JFormattedTextField textField = FilledDateField.getJFormattedTextField();
        textField.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        textField.setPreferredSize(new Dimension(150, 30));

        FilledDateField.setForeground(new Color(dv.BlackTextColor()));
        FilledDateField.setVisible(true);
        FilledDateField.setEnabled(true);

        JLabel FirstQuesLabel = new JLabel("<html>     Trong vòng 14 ngày qua, Anh/Chị có thấy xuất hiện ít nhất 1 trong các dấu hiệu: " +
                "sốt, ho, khó thở, viêm phổi, đau họng, mệt mỏi không?:");
        FirstQuesLabel.setPreferredSize(new Dimension(600, 50));
        FirstQuesLabel.setFont(new Font(dv.fontName(), 0 ,16));
        FirstQuesLabel.setForeground(new Color(dv.BlackTextColor()));

        Choice FirstQuesChoice = new Choice();
        FirstQuesChoice.setPreferredSize(new Dimension(80, 30));
        FirstQuesChoice.setFont(new Font(dv.fontName(), 0 ,16));
        FirstQuesChoice.setForeground(new Color(dv.BlackTextColor()));
        FirstQuesChoice.add("Không");
        FirstQuesChoice.add("Có");

        JLabel SecondQuesLabel = new JLabel("<html>     Trong vòng 14 ngày qua, Anh/Chị có tiếp xúc với Người bệnh hoặc nghi ngờ," +
                " mắc bệnh COVID-19 không?");
        SecondQuesLabel.setPreferredSize(new Dimension(600, 50));
        SecondQuesLabel.setFont(new Font(dv.fontName(), 0 ,16));
        SecondQuesLabel.setForeground(new Color(dv.BlackTextColor()));

        Choice SecondQuesChoice = new Choice();
        SecondQuesChoice.setPreferredSize(new Dimension(80, 30));
        SecondQuesChoice.setFont(new Font(dv.fontName(), 0 ,16));
        SecondQuesChoice.setForeground(new Color(dv.BlackTextColor()));
        SecondQuesChoice.add("Không");
        SecondQuesChoice.add("Có");

        JLabel ThirdQuesLabel = new JLabel("<html>     Anh/Chị có đang dương tính với Covid-19 không?");
        ThirdQuesLabel.setPreferredSize(new Dimension(600, 50));
        ThirdQuesLabel.setFont(new Font(dv.fontName(), 0 ,16));
        ThirdQuesLabel.setForeground(new Color(dv.BlackTextColor()));

        Choice ThirdQuesChoice = new Choice();
        ThirdQuesChoice.setPreferredSize(new Dimension(80, 30));
        ThirdQuesChoice.setFont(new Font(dv.fontName(), 0 ,16));
        ThirdQuesChoice.setForeground(new Color(dv.BlackTextColor()));
        ThirdQuesChoice.add("Không");
        ThirdQuesChoice.add("Có");

        JLabel FourthQuesLabel = new JLabel("<html>    Anh/Chị có đang là đối tượng trì hoãn tiêm chủng vaccine Covid-19 hoặc " +
                "đang là đối tượng chống chỉ định với tiêm chủng Covid-19 không?");
        FourthQuesLabel.setPreferredSize(new Dimension(600, 50));
        FourthQuesLabel.setFont(new Font(dv.fontName(), 0 ,16));
        FourthQuesLabel.setForeground(new Color(dv.BlackTextColor()));

        Choice FourthQuesChoice = new Choice();
        FourthQuesChoice.setPreferredSize(new Dimension(80, 30));
        FourthQuesChoice.setFont(new Font(dv.fontName(), 0 ,16));
        FourthQuesChoice.setForeground(new Color(dv.BlackTextColor()));
        FourthQuesChoice.add("Không");
        FourthQuesChoice.add("Có");


        ActionListener handleCreateHeal = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String InputFirstQues = String.valueOf(FirstQuesChoice.getSelectedIndex());
                String InputSecondQues = String.valueOf(SecondQuesChoice.getSelectedIndex());
                String InputThirdQues = String.valueOf(ThirdQuesChoice.getSelectedIndex());
                String InputFourthQues = String.valueOf(FourthQuesChoice.getSelectedIndex());

                int answer = dv.popupConfirmOption(null,"Xác nhận khai báo?", "Xác nhận!");

                if (answer == JOptionPane.YES_OPTION)
                {
                    String plsql = "{call HEAL_INSERT_RECORD(?,?,?,?)}";

                    Connection connection = null;
                    try {
                        connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                        CallableStatement cst = connection.prepareCall(plsql);
                        cst.setString("par_PersonalID", personalUser.getID());
                        cst.setString("par_FilledDate", dv.oracleSysdate());
                        cst.setString("par_Healths", InputFirstQues + InputSecondQues + InputThirdQues + InputFourthQues);
                        cst.setString("par_Note", "");

                        cst.execute();
                    } catch (SQLException ex)
                    {
                        dv.popupOption(null,  ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                        ex.printStackTrace();
                        return;
                    }

                    dv.popupOption(null, "Khai báo thành công!", "Thông báo!", 0);
                }
            }
        };

        ImageIcon CreateHealButtonIcon = new ImageIcon(getClass().getResource("/Data_Processor/icon/Confirm Button.png"));
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

        c.insets = new Insets(0,0,20,0);
        c.gridy = 0;
        CreateFormPanel.add(CreateHealLabel, c);

        c.insets = new Insets(0,0,20,0);
        c.gridy = 1;
        CreateFormPanel.add(FilledDateLabel,c);

        c.insets = new Insets(0,0,20,0);
        c.gridy = 2;
        CreateFormPanel.add(FilledDateField, c);

        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 3;
        CreateFormPanel.add(FirstQuesLabel,c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,0,15,0);
        c.gridy = 4;
        CreateFormPanel.add(FirstQuesChoice,c);

        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 5;
        CreateFormPanel.add(SecondQuesLabel,c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,0,15,0);
        c.gridy = 6;
        CreateFormPanel.add(SecondQuesChoice,c);

        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 7;
        CreateFormPanel.add(ThirdQuesLabel,c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,0,15,0);
        c.gridy = 8;
        CreateFormPanel.add(ThirdQuesChoice,c);

        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 9;
        CreateFormPanel.add(FourthQuesLabel,c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,0,30,0);
        c.gridy = 10;
        CreateFormPanel.add(FourthQuesChoice,c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 11;
        CreateFormPanel.add(CreateHealButton, c);
    }

    private void initLayeredPaneArea()
    {
        LayeredPaneArea = new JLayeredPane();
        LayeredPaneArea.setLayout(null);
        LayeredPaneArea.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        LayeredPaneArea.setBounds(320, 40, 680, 630);
    }

    private void initFrameComponent()
    {
        //set Frame icon
        //this.setIconImage(new ImageIcon(getClass().getResource("/Data_Processor/icon/Virus.png")).getImage());

        //set frame title
        //this.setTitle("Tìm kiếm đơn vị tiêm chủng");

        //set frame size
        this.setSize(dv.FrameWidth(), dv.FrameHeight());
        //this.setSize(1080, 720); --Main View

        //set do not allow frame resizing
        //this.setResizable(false);

        //set frame visible on screen
        this.setVisible(true);

        //set frame close on X button
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set frame background color
        //this.getContentPane().setBackground(new Color(dv.ViewBackgroundColor()));
        this.setBackground(new Color(dv.ViewBackgroundColor()));

        this.setLayout(null);

        //initFormFilterPanel
        initFormFilterPanel();
        this.add(FormFilterPanel);

        //initFillFormLabel
//        initFillFormLabel();
//        this.add(FillFormLabel);

        //init FillFormButton
        initFillFormButton();
        this.add(FillFormButton);

        //init FormListPanel



        JLabel HealListLabel = new JLabel("DANH SÁCH TỜ KHAI Y TẾ (" + personalUser.getFullName() + "):");
        HealListLabel.setBounds(0,0,640,40);
        HealListLabel.setFont(new Font(dv.fontName(), 1, 20));
        HealListLabel.setForeground(new Color(dv.FeatureButtonColor()));
        HealListLabel.setHorizontalAlignment(JLabel.CENTER);

        initScrollPaneFormList(7);

        //init LayeredPaneArea
        initLayeredPaneArea();

        LayeredPaneArea.add(HealListLabel, Integer.valueOf(0));
        LayeredPaneArea.add(ScrollPaneFormList, Integer.valueOf(0));
        this.add(LayeredPaneArea);

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());
    }

    public FillFormView(Person person)
    {
        personalUser = person;
        initFrameComponent();
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == FormFilterButton)
        {
            CreateFormPanel = null;
            LayeredPaneArea.removeAll();
            LayeredPaneArea.repaint(320, 40, 680, 630);

            JLabel HealListLabel = new JLabel("DANH SÁCH TỜ KHAI Y TẾ (" + personalUser.getFullName() + "):");
            HealListLabel.setBounds(0,0,640,40);
            HealListLabel.setFont(new Font(dv.fontName(), 1, 20));
            HealListLabel.setForeground(new Color(dv.FeatureButtonColor()));
            HealListLabel.setHorizontalAlignment(JLabel.CENTER);

            initScrollPaneFormList(Integer.valueOf(FormFilterChoice.getSelectedItem()));

            LayeredPaneArea.add(HealListLabel, Integer.valueOf(0));
            LayeredPaneArea.add(ScrollPaneFormList, Integer.valueOf(0));
            LayeredPaneArea.repaint(320, 40, 680, 630);
        }

        if (e.getSource() == FillFormButton)
        {
            FormListPanel = null;
            ScrollPaneFormList = null;

            LayeredPaneArea.removeAll();

            initCreateFormPanel();

            LayeredPaneArea.add(CreateFormPanel, Integer.valueOf(0));

            LayeredPaneArea.repaint(320, 40, 680, 630);
        }

    }

}
