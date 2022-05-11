package GUI_FillForm;

import Data_Processor.DefaultValue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FillFormView extends JFrame implements ActionListener{
    private JLabel ViewSymbol;
    private DefaultValue dv = new DefaultValue();

    private JButton FilledFormButton;

    private JButton PlusFillFormButton;

    private JLabel FormFilterLabel;


    private JLabel FillFormLabel;


    private JButton FillFormButton;

    private JLabel ListLabel;

    private JLabel QuestionLabel;
    private JLabel Question1Label;

    private JLabel Question2Label;

    private JLabel Question2_1Label;

    private JLabel Question2_2Label;

    private JLabel Question2_3Label;

    private JRadioButton Q1Radio;

    private JRadioButton Q2_1Radio;

    private JRadioButton Q2_2Radio;

    private JRadioButton Q2_3Radio;

    private JScrollPane ScrollPaneFilForm;

    private JPanel FillFormPanel;

    private JPanel FillMySelfPanel;

    private JPanel FillOtherPanel;
    private JButton ForMySelf;

    private JButton ForOther;

    private JLabel RelationshipLabel;

    private JTextField RelationshipTextField;

    private JButton SendFormButton;


    private void initViewSymbol()
    {
        ViewSymbol = new JLabel();
        ImageIcon Virus = new ImageIcon(getClass().getResource("/Data_Processor/icon/Virus.png"));
        ViewSymbol.setBounds(130, 50, 100, 100);
        ViewSymbol.setIcon(Virus);
        ViewSymbol.setHorizontalAlignment(JLabel.CENTER);
    }

    //Nút thêm bản khai báo y tế
    private void initPlusFillFormButton(){
        PlusFillFormButton = new JButton();
        PlusFillFormButton.setBorder(null);
        PlusFillFormButton.setContentAreaFilled(false);
        ImageIcon plus = new ImageIcon(getClass().getResource("/Data_Processor/icon/Plus Button.png"));
        PlusFillFormButton.setIcon(plus);
        PlusFillFormButton.setBounds(107,170,156,156);
        PlusFillFormButton.addActionListener(this);
    }

    //label khai báo y tế
    private void initFillFormLabel(){
        FillFormLabel = new JLabel();
        FillFormLabel.setText("KHAI BÁO Y TẾ");
        FillFormLabel.setBounds(510,40,390,170);
        FillFormLabel.setForeground(new Color(0x666666));
        FillFormLabel.setFont(new Font("SVN-Arial",Font.BOLD,20));
        FillFormLabel.setHorizontalAlignment(JLabel.LEFT);
        FillFormLabel.setVerticalAlignment(JLabel.CENTER);
    }

    private void initForMySelf(){
        ForMySelf = new JButton();
        ForMySelf.setText("CHO BẢN THÂN");
        ForMySelf.setFont(new Font("SVN-Arial",Font.BOLD,20));
        ForMySelf.setForeground(new Color(dv.BlackTextColor()));
        ForMySelf.setBounds(510,240,330,200);
        ForMySelf.addActionListener(this);
        ForMySelf.setBorder(null);
    }

    private void initForOther(){
        ForOther = new JButton();
        ForOther.setText("CHO NGƯỜI KHÁC");
        ForOther.setFont(new Font("SVN-Arial",Font.BOLD,20));
        ForOther.setForeground(new Color(dv.BlackTextColor()));
        ForOther.setBounds(690,240,330,200);
        ForOther.addActionListener(this);
        ForOther.setBorder(null);
    }


    private void initFillFormPanel(){
        FillFormPanel = new JPanel(new GridLayout(1,2));
        FillFormPanel.add(FillFormLabel);
        FillFormPanel.add(ForMySelf);
        FillFormPanel.add(ForOther);
        FillFormPanel.setBounds(330,1,749,718);
        FillFormPanel.setBorder(null);
    }

    private void initFillMySelfPanel(){
        FillMySelfPanel = new JPanel();

        JLabel mySelf = new JLabel();
        mySelf.setText("KHAI BÁO Y TẾ CHO BẢN THÂN");
        mySelf.setFont(new Font("SVN-Arial",Font.BOLD,20));
        mySelf.setForeground(new Color(0x666666));
        mySelf.setHorizontalAlignment(JLabel.CENTER);
        mySelf.setVerticalAlignment(JLabel.CENTER);
        mySelf.setSize(720,30);

        FillMySelfPanel.add(Question1Label);
        FillMySelfPanel.add(Question2Label);
        FillMySelfPanel.add(SendFormButton);

        FillMySelfPanel.setBounds(330,1,149,718);

    }

    private void initFillOtherPanel(){
        FillOtherPanel = new JPanel();

        JLabel other = new JLabel();
        other.setText("KHAI BÁO Y TẾ CHO NGƯỜI KHÁC");
        other.setFont(new Font("SVN-Arial",Font.BOLD,20));
        other.setForeground(new Color(0x666666));
        other.setHorizontalAlignment(JLabel.CENTER);
        other.setVerticalAlignment(JLabel.CENTER);
        other.setSize(720,30);

        FillOtherPanel.add(Question1Label);
        FillOtherPanel.add(Question2Label);
        FillOtherPanel.add(SendFormButton);
        FillOtherPanel.add(RelationshipLabel);
        FillOtherPanel.setBounds(330,1,149,718);
    }

    private void initRelationshipLabel(){
        RelationshipLabel = new JLabel();
        RelationshipLabel.setText("Mối quan hệ với người được khai báo hộ: ");
        RelationshipLabel.setForeground(new Color(0x666666));
        RelationshipLabel.setVerticalTextPosition(JLabel.CENTER);
        RelationshipLabel.setBounds(340,591,700,40);
        RelationshipLabel.setFont(new Font("SVN-Aral",Font.PLAIN,20));
        RelationshipLabel.add(RelationshipTextField);
    }

    private void initRelationshipTextField(){
        RelationshipTextField = new JTextField();
        RelationshipTextField.setBounds(330, 606, dv.FieldWidth()+300, dv.FieldHeight()+50);
        RelationshipTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        RelationshipTextField.setFont(new Font("SVN-Arial", Font.PLAIN, 16));
        RelationshipTextField.setForeground(new Color(0x333333));
        RelationshipTextField.setBackground(Color.WHITE);
    }

    private void initQuestion1Label(){
        Question1Label = new JLabel();
        Question1Label.setText("Trong vòng 14 ngày qua, Anh/Chị có thấy xuất hiện ít nhất một trong các dấu hiệu: sốt,ho,khó thở,đau người,mệt mỏi,ớn lạnh,giảm hoặc mất vị giác hoặc khứu giác không?");
        Question1Label.setBounds(340,51,700,115);
        Question1Label.setFont(new Font("SVN-Arial",Font.BOLD,20));
        Question1Label.setForeground(new Color(dv.FieldLabelColor()));
        Question1Label.setHorizontalTextPosition(JLabel.LEFT);
        Question1Label.setVerticalTextPosition(JLabel.CENTER);
        Question1Label.add(Q1Radio);
    }

    private void initQuestion2Label(){
        Question2Label = new JLabel();
        Question2Label.setText("Trong vòng 14 ngày qua, Anh/Chị có tiếp xúc với:");
        Question2Label.setBounds(340,226,700,40);
        Question2Label.setFont(new Font("SVN-Arial",Font.BOLD,20));
        Question2Label.setForeground(new Color(dv.FieldLabelColor()));
        Question2Label.setHorizontalTextPosition(JLabel.LEFT);
        Question2Label.setVerticalTextPosition(JLabel.CENTER);
        Question2Label.add(Question2_1Label);
        Question2Label.add(Question2_2Label);
        Question2Label.add(Question2_3Label);
    }

    private void initQuestion2_1Label(){
        Question2_1Label = new JLabel();
        Question2_1Label.setText("Người bệnh hoặc nghi ngờ mắc Covid-19");
        Question2_1Label.setBounds(340,246,700,40);
        Question2_1Label.setFont(new Font("SVN-Arial",Font.PLAIN,20));
        Question2_1Label.setForeground(new Color(dv.FieldLabelColor()));
        Question2_1Label.setHorizontalTextPosition(JLabel.LEFT);
        Question2_1Label.setVerticalTextPosition(JLabel.CENTER);
        Question2_1Label.add(Q2_1Radio);
    }

    private void initQuestion2_2Label(){
        Question2_2Label = new JLabel();
        Question2_2Label.setText("Người về từ nước có bệnh Covid-19");
        Question2_2Label.setBounds(340,306,700,40);
        Question2_2Label.setFont(new Font("SVN-Arial",Font.PLAIN,20));
        Question2_2Label.setForeground(new Color(dv.FieldLabelColor()));
        Question2_2Label.setHorizontalTextPosition(JLabel.LEFT);
        Question2_2Label.setVerticalTextPosition(JLabel.CENTER);
        Question2_2Label.add(Q2_2Radio);
    }

    private void initQuestion2_3Label(){
        Question2_3Label = new JLabel();
        Question2_3Label.setText("Người bệnh có biểu hiện sốt,ho,khó thở,viêm phổi");
        Question2_3Label.setBounds(340,366,700,40);
        Question2_3Label.setFont(new Font("SVN-Arial",Font.PLAIN,20));
        Question2_3Label.setForeground(new Color(dv.FieldLabelColor()));
        Question2_3Label.setHorizontalTextPosition(JLabel.LEFT);
        Question2_3Label.setVerticalTextPosition(JLabel.CENTER);
        Question2_3Label.add(Q2_3Radio);
    }

    private void initQ1Radio(){
        JRadioButton NoButton;
        JRadioButton YesButton;

        NoButton = new JRadioButton("Không");
        NoButton.setFont(new Font("SVN-Arial",Font.BOLD,20));
        NoButton.setForeground(new Color(dv.BlackTextColor()));
        NoButton.setHorizontalAlignment(JLabel.LEFT);
        NoButton.setContentAreaFilled(false);
        NoButton.setBounds(340,181,30,30);

        YesButton = new JRadioButton("Có");
        YesButton.setFont(new Font("SVN-Arial",Font.BOLD,20));
        YesButton.setForeground(new Color(dv.BlackTextColor()));
        YesButton.setHorizontalAlignment(JLabel.LEFT);
        YesButton.setContentAreaFilled(false);
        YesButton.setBounds(400,181,20,30);

        ButtonGroup YesNoButtonGroup = new ButtonGroup();

        YesNoButtonGroup.add(NoButton);
        YesNoButtonGroup.add(YesButton);

        NoButton.addActionListener(this);
        YesButton.addActionListener(this);

    }

    private void initQ2_1Radio(){
        JRadioButton NoButton;
        JRadioButton YesButton;

        NoButton = new JRadioButton("Không");
        NoButton.setFont(new Font("SVN-Arial",Font.BOLD,20));
        NoButton.setForeground(new Color(dv.BlackTextColor()));
        NoButton.setHorizontalAlignment(JLabel.LEFT);
        NoButton.setContentAreaFilled(false);
        NoButton.setBounds(340,341,30,30);

        YesButton = new JRadioButton("Có");
        YesButton.setFont(new Font("SVN-Arial",Font.BOLD,20));
        YesButton.setForeground(new Color(dv.BlackTextColor()));
        YesButton.setHorizontalAlignment(JLabel.LEFT);
        YesButton.setContentAreaFilled(false);
        YesButton.setBounds(400,341,20,30);

        ButtonGroup YesNoButtonGroup = new ButtonGroup();

        YesNoButtonGroup.add(NoButton);
        YesNoButtonGroup.add(YesButton);

        NoButton.addActionListener(this);
        YesButton.addActionListener(this);

    }
    private void initQ2_2Radio(){
        JRadioButton NoButton;
        JRadioButton YesButton;

        NoButton = new JRadioButton("Không");
        NoButton.setFont(new Font("SVN-Arial",Font.BOLD,20));
        NoButton.setForeground(new Color(dv.BlackTextColor()));
        NoButton.setHorizontalAlignment(JLabel.LEFT);
        NoButton.setContentAreaFilled(false);
        NoButton.setBounds(340,441,30,30);

        YesButton = new JRadioButton("Có");
        YesButton.setFont(new Font("SVN-Arial",Font.BOLD,20));
        YesButton.setForeground(new Color(dv.BlackTextColor()));
        YesButton.setHorizontalAlignment(JLabel.LEFT);
        YesButton.setContentAreaFilled(false);
        YesButton.setBounds(400,441,20,30);

        ButtonGroup YesNoButtonGroup = new ButtonGroup();

        YesNoButtonGroup.add(NoButton);
        YesNoButtonGroup.add(YesButton);

        NoButton.addActionListener(this);
        YesButton.addActionListener(this);

    }

    private void initQ2_3Radio(){
        JRadioButton NoButton;
        JRadioButton YesButton;

        NoButton = new JRadioButton("Không");
        NoButton.setFont(new Font("SVN-Arial",Font.BOLD,20));
        NoButton.setForeground(new Color(dv.BlackTextColor()));
        NoButton.setHorizontalAlignment(JLabel.LEFT);
        NoButton.setContentAreaFilled(false);
        NoButton.setBounds(340,541,30,30);

        YesButton = new JRadioButton("Có");
        YesButton.setFont(new Font("SVN-Arial",Font.BOLD,20));
        YesButton.setForeground(new Color(dv.BlackTextColor()));
        YesButton.setHorizontalAlignment(JLabel.LEFT);
        YesButton.setContentAreaFilled(false);
        YesButton.setBounds(400,541,20,30);

        ButtonGroup YesNoButtonGroup = new ButtonGroup();

        YesNoButtonGroup.add(NoButton);
        YesNoButtonGroup.add(YesButton);

        NoButton.addActionListener(this);
        YesButton.addActionListener(this);

    }

    private void initSendFormButton(){
        SendFormButton = new JButton();
        SendFormButton.setBorder(null);
        SendFormButton.setBounds(550,650,200,40);
        SendFormButton.setContentAreaFilled(false);
        SendFormButton.setText("GỬI TỜ KHAI");
        ImageIcon send = new ImageIcon(getClass().getResource("/Data_Processor/icon/Login Button.png"));
        SendFormButton.setIcon(send);
        SendFormButton.addActionListener(this);
        SendFormButton.addActionListener(this);
    }

    public FillFormView(){
        initFillFormFrameComponent();

    }

    private void initFillFormFrameComponent(){
        this.setTitle("Khai báo y tế");

        this.setVisible(true);

        this.setResizable(false);

        this.setBackground(new Color(dv.ViewBackgroundColor()));

        this.setSize(1080,720);

        this.setLayout(null);

        this.add(ViewSymbol);
        initViewSymbol();

        this.add(PlusFillFormButton);
        initPlusFillFormButton();

        this.add(FillFormLabel);
        initFillFormLabel();

        this.add(ForMySelf);
        initForMySelf();

        this.add(ForOther);
        initForOther();

        this.add(FillFormPanel);
        initFillFormPanel();

        this.add(FillMySelfPanel);
        initFillMySelfPanel();

        this.add(FillOtherPanel);
        initFillOtherPanel();

        this.add(RelationshipLabel);
        initRelationshipLabel();

        this.add(RelationshipTextField);
        initRelationshipTextField();

        this.add(Question1Label);
        initQuestion1Label();

        this.add(Question2Label);
        initQuestion2Label();

        this.add(Question2_1Label);
        initQuestion2_1Label();

        this.add(Question2_2Label);
        initQuestion2_2Label();

        this.add(Question2_3Label);
        initQuestion2_3Label();

        this.add(Q1Radio);
        initQ1Radio();

        this.add(Q2_1Radio);
        initQ2_1Radio();

        this.add(Q2_2Radio);
        initQ2_2Radio();

        this.add(Q2_3Radio);
        initQ2_3Radio();

        this.add(SendFormButton);
        initSendFormButton();

        this.repaint(0,0,dv.FrameWidth(),dv.FrameHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == PlusFillFormButton)
        {

        }

    }

}
