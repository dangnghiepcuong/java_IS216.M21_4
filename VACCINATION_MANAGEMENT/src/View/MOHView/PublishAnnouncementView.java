package View.MOHView;

import Process.DateLabelFormatter;
import Process.DefaultValue;
import Process.ImageHelper;
import Process.Organization;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;

public class PublishAnnouncementView extends JPanel implements ActionListener, KeyListener {

    /*Announcement Content*/
    private JPanel AnnViewPanel;

    /*Announcement Info*/
    private JPanel AnnInfoPanel;
    private JLabel OrgNameLabel;
    private JLabel TitleLabel;
    private JTextField TitleField;
    private JLabel AnnNumberLabel;
    private  JTextField AnnNumberField;
    private JLabel PublishDateLabel;
    private JDatePickerImpl PublishDateField;
    private JLabel ContentLabel;
    private JLabel ContentFileLabel = new JLabel();
    private File ContentFilePath;
    private Scanner ContentFile;
    private JButton UploadContentButton;
    private JLabel ImageLabel;
    private JLabel ImageFileLabel = new JLabel();
    private File ImageFilePath;
    private JLabel AttachedImage;
    private JButton UploadImageButton;
    private  JButton RemoveImageButton;

    private JButton PreviewButton;
    private  JButton PublishButton;

    /*Object Class*/
    private DefaultValue dv = new DefaultValue();
    private Organization orgUser = new Organization();

    private void initOrgNameLabel()
    {
        OrgNameLabel = new JLabel("Đơn vị: " + orgUser.getName());
        OrgNameLabel.setBounds(70, 40 +dv.AlignTop_InfoView(), 240, 30);
        OrgNameLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        OrgNameLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initTitleLabel()
    {
        TitleLabel = new JLabel("Tiêu đề");
        TitleLabel.setBounds(70, 50 + dv.LabelHeight() + dv.AlignTop_InfoView(), 240, 30);
        TitleLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TitleLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initTitleField()
    {
        TitleField = new JTextField();
        TitleField.setBounds(70, 50 + 2*dv.LabelHeight() +dv.AlignTop_InfoView(), 220, 30);
        TitleField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        TitleField.setForeground(new Color(dv.BlackTextColor()));
        TitleField.addKeyListener(this);
    }

    private void initAnnNumberLabel()
    {
        AnnNumberLabel = new JLabel("Số hiệu");
        AnnNumberLabel.setBounds(70, 60  + 3*dv.LabelHeight() +dv.AlignTop_InfoView(), 270, 30);
        AnnNumberLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        AnnNumberLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initAnnNumberField()
    {
        AnnNumberField = new JTextField();
        AnnNumberField.setBounds(70, 60  + 4 * dv.LabelHeight() +dv.AlignTop_InfoView(), 220, 30);
        AnnNumberField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        AnnNumberField.setForeground(new Color(dv.BlackTextColor()));
        AnnNumberField.addKeyListener(this);
    }

    private void initPublishDateLabel()
    {
        PublishDateLabel = new JLabel("Ngày đăng");
        PublishDateLabel.setBounds(70, 70 + 5 * dv.LabelHeight() +dv.AlignTop_InfoView(),220,30);
        PublishDateLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        PublishDateLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initPublishDateField()
    {
        UtilDateModel model=new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        PublishDateField = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        JFormattedTextField textField = PublishDateField.getJFormattedTextField();
        textField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        textField.setBounds(70, 70  + 6 * dv.LabelHeight() +dv.AlignTop_InfoView(), 220, 28);

        PublishDateField.setBounds(70, 70  + 6 * dv.LabelHeight() +dv.AlignTop_InfoView(), 220, 28);
        PublishDateField.setTextEditable(true);
        PublishDateField.setForeground(new Color(dv.BlackTextColor()));
        PublishDateField.setVisible(true);
        PublishDateField.setEnabled(true);
    }

    private void UploadTextFile()
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
                                          return f.getName().toLowerCase().endsWith(".txt");
                                  }
                                  @Override
                                  public String getDescription()
                                  {
                                      return "Text File (*.txt)";
                                  }
                              }
        );
        if(chooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION)
            return;

        ContentFilePath = chooser.getSelectedFile();
        try
        {
            ContentFileLabel.setText(ContentFilePath.getName());
            ContentFileLabel.setBounds(70, 80 + 8 * dv.LabelHeight() +dv.AlignTop_InfoView(),220,30);
            ContentFileLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
            ContentFileLabel.setForeground(new Color(dv.FieldLabelColor()));
            AnnInfoPanel.add(ContentFileLabel);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            dv.popupOption(null, ex.getMessage(), "Lỗi!",2);
        }
    }

    private void initContentLabel()
    {
        ContentLabel = new JLabel("Tải lên file.txt nội dung");
        ContentLabel.setBounds(70, 80 + 7 * dv.LabelHeight() +dv.AlignTop_InfoView(),220,30);
        ContentLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ContentLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initUploadContentButton()
    {
        ImageIcon UploadContentButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Form.png"));
        UploadContentButton = new JButton();
        UploadContentButton.setBounds(70 + 220, 80 + 7 * dv.LabelHeight() +dv.AlignTop_InfoView(),
                UploadContentButtonIcon.getIconWidth(), UploadContentButtonIcon.getIconHeight());
//        UploadContentButton.setBorder(null);
        UploadContentButton.setContentAreaFilled(false);
        UploadContentButton.setIcon(UploadContentButtonIcon);

        UploadContentButton.addActionListener(this);
        UploadContentButton.addKeyListener(this);
    }

    private void UploadImageFile()
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
                                          return f.getName().toLowerCase().endsWith(".png");
                                  }

                                  @Override
                                  public String getDescription()
                                  {
                                      return "Image File (*.png)";
                                  }
                              }
        );
        if(chooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION)
            return;

        ImageFilePath = chooser.getSelectedFile();
        try
        {
            ImageIcon TakenImage = new ImageIcon(ImageFilePath.getPath());
            Image ResizedImg = ImageHelper.reSize(TakenImage.getImage(),
                    600,(int) (600.0/TakenImage.getIconWidth() * TakenImage.getIconHeight()));
            AttachedImage = new JLabel(new ImageIcon(ResizedImg));

            ImageFileLabel.setText(ImageFilePath.getName());
            ImageFileLabel.setBounds(70, 90 + 10 * dv.LabelHeight() +dv.AlignTop_InfoView(),220,30);
            ImageFileLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
            ImageFileLabel.setForeground(new Color(dv.FieldLabelColor()));
            AnnInfoPanel.add(ImageFileLabel);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            dv.popupOption(null, ex.getMessage(), "Lỗi!",2);
        }

        RemoveImageButton.setEnabled(true);
    }

    private void initImageLabel()
    {
        ImageLabel = new JLabel("Tải lên file.jpg hình ảnh");
        ImageLabel.setBounds(70, 90 + 9 * dv.LabelHeight() +dv.AlignTop_InfoView(),220,30);
        ImageLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ImageLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initUploadImageButton()
    {
        ImageIcon UploadImageButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/ImageIcon.png"));
        UploadImageButton = new JButton();
        UploadImageButton.setBounds(70 + 220, 90 + 9 * dv.LabelHeight() +dv.AlignTop_InfoView(),
                UploadImageButtonIcon.getIconWidth(), UploadImageButtonIcon.getIconHeight());
//        UploadImageButton.setBorder(null);
        UploadImageButton.setContentAreaFilled(false);
        UploadImageButton.setIcon(UploadImageButtonIcon);
        UploadImageButton.addActionListener(this);
        UploadImageButton.addKeyListener(this);
    }

    private void initRemoveImageButton()
    {
        RemoveImageButton = new JButton();
        ImageIcon RemoveImageIcon = new ImageIcon(getClass().getResource("/Resources/icon/Remove Pic Button.png"));
        RemoveImageButton.setIcon(RemoveImageIcon);
        RemoveImageButton.setBounds((360-RemoveImageIcon.getIconWidth())/2, 100 + 11 * dv.LabelHeight() +dv.AlignTop_InfoView(),
                RemoveImageIcon.getIconWidth(), RemoveImageIcon.getIconHeight());
        RemoveImageButton.setBorder(null);
        RemoveImageButton.setContentAreaFilled(false);
        RemoveImageButton.addActionListener(this);
        RemoveImageButton.setEnabled(false);
    }

    private void initPreviewButton()
    {
        ImageIcon PreviewButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Preview Button.png"));
        PreviewButton = new JButton();
        PreviewButton.setBounds((360-PreviewButtonIcon.getIconWidth())/2, 150  + 11 * dv.LabelHeight() +dv.AlignTop_InfoView(), PreviewButtonIcon.getIconWidth(), PreviewButtonIcon.getIconHeight());
        PreviewButton.setBorder(null);
        PreviewButton.setContentAreaFilled(false);
        PreviewButton.setIcon(PreviewButtonIcon);

        PreviewButton.addActionListener(this);
        PreviewButton.addKeyListener(this);
    }

    private void initPublishButton()
    {
        ImageIcon PublishButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Publish Ann Button.png"));
        PublishButton = new JButton();
        PublishButton.setBounds((360-PublishButtonIcon.getIconWidth())/2, 600, PublishButtonIcon.getIconWidth(), PublishButtonIcon.getIconHeight());
        PublishButton.setBorder(null);
        PublishButton.setContentAreaFilled(false);
        PublishButton.setIcon(PublishButtonIcon);
        PublishButton.setEnabled(false);

        PublishButton.addActionListener(this);
        PublishButton.addKeyListener(this);
    }

    private void initAnnInfoPanel()
    {
        AnnInfoPanel = new JPanel();

        AnnInfoPanel.setLayout(null);
        AnnInfoPanel.setBounds(0,0,dv.FrameWidth()-dv.FrameHeight(),dv.FrameHeight());
        AnnInfoPanel.setBackground(new Color(dv.ViewBackgroundColor()));
        AnnInfoPanel.setBorder(dv.border());

        JLabel AnnoucementInfoLabel = new JLabel("THIẾT LẬP THÔNG BÁO");
        AnnoucementInfoLabel.setBounds((AnnInfoPanel.getWidth()-300)/2, 40, 300, 30);
        AnnoucementInfoLabel.setFont(new Font(dv.fontName(), 1, 20));
        AnnoucementInfoLabel.setForeground(new Color(dv.FeatureButtonColor()));
        AnnoucementInfoLabel.setHorizontalAlignment(JLabel.CENTER);

        AnnInfoPanel.add(AnnoucementInfoLabel);

        initOrgNameLabel();
        AnnInfoPanel.add(OrgNameLabel);

        initTitleLabel();
        AnnInfoPanel.add(TitleLabel);

        initTitleField();
        AnnInfoPanel.add(TitleField);

        initAnnNumberLabel();
        AnnInfoPanel.add(AnnNumberLabel);

        initAnnNumberField();
        AnnInfoPanel.add(AnnNumberField);

        initPublishDateLabel();
        AnnInfoPanel.add(PublishDateLabel);

        initPublishDateField();
        AnnInfoPanel.add(PublishDateField);

        initContentLabel();
        AnnInfoPanel.add(ContentLabel);

        initImageLabel();
        AnnInfoPanel.add(ImageLabel);

        initUploadContentButton();
        AnnInfoPanel.add(UploadContentButton);

        initUploadImageButton();
        AnnInfoPanel.add(UploadImageButton);

        initRemoveImageButton();
        AnnInfoPanel.add(RemoveImageButton);

        initPreviewButton();
        AnnInfoPanel.add(PreviewButton);

        initPublishButton();
        AnnInfoPanel.add(PublishButton);

        AnnInfoPanel.validate();
    }

    /*
    *   INITIALIZE THE LIST OF INJECTIONS OF THE CITIZEN
    *   -LAYEREDPANE:
    *      + SCROLLPANE:
    *           - PANEL: LIST OF INJECTIONS
    *               + PANELS: INJECTIONS
    *                  - LABELS
    * */


    public void initAnnViewPanel() {
        JTextArea Title = new JTextArea(TitleField.getText());
        Title.setBounds(0, 0, 630, 80);
        Title.setFont(new Font(dv.fontName(), Font.BOLD, 26));
        Title.setForeground(new Color(dv.FeatureButtonColor()));
        Title.setBackground(Color.WHITE);
        Title.setWrapStyleWord(true);
        Title.setLineWrap(true);
        Title.setEditable(false);
        Title.setAutoscrolls(true);

        JScrollPane ScrollPaneTitle = new JScrollPane(Title,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneTitle.setBounds(40, 40, 630, 80);
        ScrollPaneTitle.setBorder(null);
        ScrollPaneTitle.setBackground(Color.WHITE);

        JLabel AnnNumber = new JLabel("Thông báo số: " + AnnNumberField.getText());
        AnnNumber.setBounds(80, 125, 640, 25);
        AnnNumber.setFont(new Font(dv.fontName(), Font.ITALIC, 18));
        AnnNumber.setForeground(Color.BLACK);

        JLabel Date = new JLabel("Ngày: " + PublishDateField.getJFormattedTextField().getText());
        Date.setBounds(80, 145 + 2, 640, 30);
        Date.setFont(new Font(dv.fontName(), Font.ITALIC, 18));
        Date.setForeground(Color.BLACK);

        if (ContentFilePath != null)
            try {
                ContentFile = new Scanner(new File(ContentFilePath.getPath()));
            } catch (FileNotFoundException ex) {
                dv.popupOption(null, "Không tìm thấy file nội dung!", "Lỗi!", 2);
                ex.printStackTrace();
            }

        String tempContent = "";
        if (ContentFile != null)
            while (ContentFile.hasNextLine())
                tempContent += ContentFile.nextLine() + '\n';
        else
            tempContent = "(Không có nội dung)";

        byte[] bytesString = tempContent.getBytes();
        String encodeString = new String(bytesString, StandardCharsets.UTF_8);

        JTextArea AnnTextArea = new JTextArea();
        AnnTextArea.setText(tempContent);
        AnnTextArea.setBounds(0, 0, 1, 1);
        AnnTextArea.setFont(new Font(dv.fontName(), Font.PLAIN, 18));
        AnnTextArea.setForeground(new Color(dv.BlackTextColor()));
        AnnTextArea.setBackground(Color.WHITE);
        AnnTextArea.setAutoscrolls(false);
        AnnTextArea.setWrapStyleWord(true);
        AnnTextArea.setLineWrap(true);
        AnnTextArea.setEditable(false);
//        AnnTextArea.setBorder(dv.border());

        JPanel AnnContent = new JPanel();
        BoxLayout boxLayout = new BoxLayout(AnnContent, BoxLayout.Y_AXIS);
        AnnContent.setLayout(boxLayout);
        AnnContent.setBackground(Color.WHITE);
        AnnContent.setBounds(0, 0, 610, 1);

        AnnContent.add(AnnTextArea);
        if (AttachedImage != null) {
            JPanel ImagePanel = new JPanel();
            ImagePanel.setBackground(Color.WHITE);
            ImagePanel.add(AttachedImage);
            AnnContent.add(ImagePanel);
        }

        JScrollPane ScrollPaneContent = new JScrollPane(AnnContent,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ScrollPaneContent.setLayout(new ScrollPaneLayout());
        ScrollPaneContent.setBounds(40, 180, 630, 450);
        ScrollPaneContent.setBackground(Color.WHITE);
        ScrollPaneContent.setBorder(null);

        JLabel Publisher = new JLabel("Đơn vị: " + orgUser.getName());
        Publisher.setBounds(40, 640, 630, 30);
        Publisher.setFont(new Font(dv.fontName(), Font.ITALIC, 18));
        Publisher.setForeground(Color.BLACK);
        Publisher.setHorizontalAlignment(JLabel.RIGHT);
//        Publisher.setBorder(dv.border());

        AnnViewPanel = new JPanel();
        AnnViewPanel.setBounds(360, 0, 720, 720);
        AnnViewPanel.setLayout(null);
        AnnViewPanel.setBackground(Color.WHITE);

        AnnViewPanel.add(ScrollPaneTitle);
        AnnViewPanel.add(AnnNumber);
        AnnViewPanel.add(Date);
        AnnViewPanel.add(ScrollPaneContent);
        AnnViewPanel.add(Publisher);

        this.removeAll();
        this.add(AnnInfoPanel);
        this.add(AnnViewPanel);
        this.repaint(0, 0, dv.FrameWidth(), dv.FrameHeight());
    }

    private void initComponents()
    {
        this.setSize(dv.FrameWidth(), dv.FrameHeight());
        this.setVisible(true);
        this.setBackground(new Color(dv.ViewBackgroundColor()));
        this.setLayout(null);

        initAnnInfoPanel();

        this.add(AnnInfoPanel);
        this.repaint(0,0,dv.FrameWidth(), dv.FrameHeight());
    }

    /*CONSTRUCTOR*/
    public PublishAnnouncementView(Organization org)
    {
        orgUser = org;
        initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == UploadContentButton)
        {
            UploadTextFile();
            AnnInfoPanel.repaint(0,0,dv.FrameWidth()-dv.FrameHeight(),dv.FrameHeight());
        }

        if (e.getSource() == PreviewButton)
        {
            String InputTitle = TitleField.getText();
            String InputAnnNumber = AnnNumberField.getText();
            String InputPublishDate = PublishDateField.getJFormattedTextField().getText();

            if (dv.checkStringInputValue(InputTitle, "Cảnh báo!", "Nhập tiêu đề thông báo") != -2)
                return;
            if (dv.checkStringInputValue(InputAnnNumber, "Cảnh báo!", "Nhập số hiệu thông báo!") != -2)
                return;
            if (dv.checkStringInputValue(InputPublishDate, "Cảnh báo!", "Nhập ngày đăng thông báo!") != -2)
                return;

            LocalDate PublishDate = LocalDate.parse(InputPublishDate);
            LocalDate sysdate = LocalDate.parse(dv.todayString());

            if (PublishDate.isBefore(sysdate))
            {
                dv.popupOption(null, "Ngày đăng phải lớn hơn hoặc bằng ngày hiện tại!", "Lỗi!", 2);
                return;
            }

                initAnnViewPanel();

            PublishButton.setEnabled(true);
        }

        if (e.getSource() == UploadImageButton)
        {
            UploadImageFile();
            AnnInfoPanel.repaint(0,0,dv.FrameWidth()-dv.FrameHeight(),dv.FrameHeight());
        }

        if (e.getSource() == RemoveImageButton)
        {
            RemoveImageButton.setEnabled(false);
            ImageFilePath = null;
            AttachedImage = null;
            ImageFileLabel.setText("");
        }

        if (e.getSource() == PublishButton)
        {
            if (dv.popupConfirmOption(null,"Xác nhận đăng thông báo?", "Xác nhận?") != 0 )
                return;

            PublishButton.setEnabled(false);

            String InputTitle = TitleField.getText();
            String InputAnnNumber = AnnNumberField.getText();
            String InputPublishDate = dv.toOracleDateFormat(PublishDateField.getJFormattedTextField().getText());

            String plsql = "{call ANN_INSERT_RECORD(?, ?, ?, ?, ?, ?)}";

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
                CallableStatement cst = connection.prepareCall(plsql);

                FileReader clobFile = null;
                if (ContentFilePath != null)
                    clobFile = new FileReader(ContentFilePath.getPath());

                FileInputStream blobFile = null;
                if (ImageFilePath != null)
                    blobFile = new FileInputStream(ImageFilePath);

                cst.setString("par_OrgID", orgUser.getID());
                cst.setString("par_Title", InputTitle);
                cst.setString("par_ID", InputAnnNumber);
                cst.setClob("par_Content", clobFile);
                cst.setBinaryStream("par_Image",blobFile);
                cst.setString("par_PublishDate", InputPublishDate);
                cst.execute();
            } catch (SQLException ex)
            {
                if (ex.getErrorCode() == 1)
                    dv.popupOption(null,"Số hiệu bài viết đã được sử dụng!", "Lỗi!", 2);
                else
                    dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                return;
            } catch (FileNotFoundException ex) {
                dv.popupOption(null,"Không tìm thấy file nội dung!", "Lỗi!", 2);
                ex.printStackTrace();
                return;
            } finally {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            LocalDate PublishDate = LocalDate.parse(PublishDateField.getJFormattedTextField().getText());
            LocalDate sysdate = LocalDate.parse(dv.todayString());
            if (PublishDate.isAfter(sysdate))
                dv.popupOption(null,"Đã lên lịch bài viết!", "Thông báo!", 0);
            else
                dv.popupOption(null,"Đã đăng bài viết!", "Thông báo!", 0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
