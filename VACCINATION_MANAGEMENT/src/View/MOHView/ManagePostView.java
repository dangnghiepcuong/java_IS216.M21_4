/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.MOHView;

import Process.Annoucement;
import Process.DefaultValue;
import Process.ImageHelper;
import Process.Organization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author ASUS
 */

public class ManagePostView extends JPanel {

    private JLabel AnnListLabel;
    private JScrollPane ScrollPanePreAnnList;
    private JPanel PreviewPanel;
    private JPanel PreAnnListPanel;
    private JPanel AnnViewPanel;

    private Organization orgUser;

    private DefaultValue dv= new DefaultValue();

    private void initAnnListLabel()
    {
        AnnListLabel = new JLabel("CÁC THÔNG BÁO");
        AnnListLabel.setBounds((PreviewPanel.getWidth()-360)/2, dv.AlignTop_InfoView()+20, 360, 20);
        AnnListLabel.setFont(new Font(dv.fontName(), 1, 20));
        AnnListLabel.setForeground(new Color(dv.FeatureButtonColor()));
        AnnListLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    private JPanel initSmallAnnViewPanel(Annoucement ann)
    {
        JPanel FormPanel = new JPanel();
        FormPanel.setLayout(null);
        FormPanel.setBackground(Color.WHITE);

        JTextArea TitleLabel=new JTextArea(ann.getTitle());
        TitleLabel.setFont(new Font(dv.fontName(), 1, 16));
        TitleLabel.setForeground(new Color(dv.BlackTextColor()));
        TitleLabel.setBounds(0,0,280,50);
        TitleLabel.setLineWrap(true);
        TitleLabel.setWrapStyleWord(true);
        TitleLabel.setEditable(false);
        TitleLabel.setAutoscrolls(true);

        JScrollPane ScrollTitleLabel = new JScrollPane(TitleLabel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ScrollTitleLabel.setBounds(10, 5, 280, 50);
        ScrollTitleLabel.setBorder(null);

        JLabel ORGLabel=new JLabel(ann.getOrg().getName());
        ORGLabel.setFont(new Font(dv.fontName(), 2, 13));
        ORGLabel.setForeground(new Color(dv.BlackTextColor()));
        ORGLabel.setBounds(10, 50, 310, 25);
        ORGLabel.setHorizontalAlignment(JLabel.LEFT);
//        ORGLabel.setBorder(dv.border());

        JLabel PublishDateLabel = new JLabel(String.valueOf(ann.getPublishDate()));
        PublishDateLabel.setPreferredSize(new Dimension(350, 25));
        PublishDateLabel.setFont(new Font(dv.fontName(), 0, 13));
        PublishDateLabel.setForeground(new Color(dv.BlackTextColor()));
        PublishDateLabel.setBounds(10, 75, 310, 25);
        PublishDateLabel.setHorizontalAlignment(JLabel.LEFT);
//        PublishDateLabel.setBorder(dv.border());

        if (ann.getImage() != null)
        {
            ImageIcon imgicon = new ImageIcon(getClass().getResource("/Resources/icon/ImageIcon.png"));
            JLabel imgLabel = new JLabel(imgicon);
            imgLabel.setBounds(320-(imgicon.getIconWidth()+5),55,imgicon.getIconWidth(),imgicon.getIconHeight());
            FormPanel.add(imgLabel);
        }

        ActionListener handleDeletePost = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dv.popupConfirmOption(null,"Xác nhận xóa văn bản/thông báo này?","Xác nhận?") != 0)
                    return;

                String query = "delete from ANNOUNCEMENT" +
                                " where ID = '" + ann.getID() + "'" +
                                " and OrgID = '" + orgUser.getID() +"'";
                try {
                    Connection connection = DriverManager.getConnection(dv.getDB_URL(),dv.getUsername(),dv.getPassword());
                    PreparedStatement st = connection.prepareStatement(query);
                    st.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                ReInitView();
                dv.popupOption(null,"Xóa văn bản/thông báo thành công!", "Thông báo!",0);
            }
        };
        ImageIcon DeleteSymbol = new ImageIcon(getClass().getResource("/Resources/icon/Delete Symbol.png"));
        JButton DeletePostButton = new JButton(DeleteSymbol);
        DeletePostButton.setBounds(320-(DeleteSymbol.getIconWidth()+5),5,DeleteSymbol.getIconWidth(),DeleteSymbol.getIconHeight());
        DeletePostButton.setContentAreaFilled(false);
        DeletePostButton.setBorder(null);
        DeletePostButton.addActionListener(handleDeletePost);

        FormPanel.add(DeletePostButton);
        FormPanel.add(ScrollTitleLabel);
        FormPanel.add(ORGLabel);
        FormPanel.add(PublishDateLabel);
        FormPanel.setPreferredSize(new Dimension(330, 100));

        MouseListener handleViewAnn = new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                initAnnViewPanel(ann);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        };
        TitleLabel.addMouseListener(handleViewAnn);
        FormPanel.addMouseListener(handleViewAnn);

        return FormPanel;
    }

    private void initPreAnnListPanel()
    {
        PreAnnListPanel= new JPanel();
        PreAnnListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        PreAnnListPanel.setLayout(new FlowLayout());

        Annoucement ann;
        int nAnn = 0;
        int i = 0;

        String query= "select *" +
                    " from ANNOUNCEMENT ANN, ORGANIZATION ORG" +
                    " where ANN.OrgID = ORG.ID" +
                    " and ORG.ID = '"+ orgUser.getID() +"'" +
//                    " and (sysdate - PublishDate) <= 90" +
//                    " and PublishDate <= sysdate" +
                    " order by PublishDate desc";
        System.out.println(query);
        Connection connection=null;

        try
        {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                ann = new Annoucement();
                ann.setID(rs.getString("ID"));
                ann.getOrg().setID(rs.getString("OrgID"));
                ann.getOrg().setName(rs.getString("Name"));
                ann.setTitle(rs.getString("Title"));
                ann.setPublishDate(LocalDate.parse(rs.getString("PublishDate").substring(0, 10)));
                ann.setImage(rs.getBytes("Image"));
                ann.setContent(rs.getString("Content"));
                PreAnnListPanel.add(initSmallAnnViewPanel(ann));
                i++;
            }
        }
        catch(SQLException ex)
        {
            dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
            ex.printStackTrace();
            return;
        }

        nAnn = i;
        if (nAnn == 0)
            nAnn = 1;

        PreAnnListPanel.setPreferredSize(new Dimension(340, nAnn*100 + nAnn*5));
        PreAnnListPanel.setBounds(0,0,340,nAnn*100 + nAnn*5);
    }

    private void initScrollPanePreAnnList()// View Announcement new
    {
        ScrollPanePreAnnList =  new JScrollPane(PreAnnListPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ScrollPanePreAnnList.setBounds(0,100,360 ,480);
    }

    private void initPreviewPanel()
    {
        PreviewPanel=new JPanel();
        PreviewPanel.setBounds(0,0,360,720);
        PreviewPanel.setLayout(null);
        PreviewPanel.setOpaque(false);
        PreviewPanel.setBorder(dv.border());

        initAnnListLabel();
        initPreAnnListPanel();
        initScrollPanePreAnnList();

        PreviewPanel.add(AnnListLabel);
        PreviewPanel.add(ScrollPanePreAnnList);

        PreviewPanel.repaint(0,0,360,720);
    }

    public void initAnnViewPanel(Annoucement ann)
    {
        JTextArea Title = new JTextArea(ann.getTitle());
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

        JLabel AnnNumber = new JLabel("Thông báo số: " + ann.getID());
        AnnNumber.setBounds(80, 125, 640, 25);
        AnnNumber.setFont(new Font(dv.fontName(), Font.ITALIC, 18));
        AnnNumber.setForeground(Color.BLACK);

        JLabel Date = new JLabel(dv.toTextDate(String.valueOf(ann.getPublishDate())));
        Date.setBounds(80, 145 + 2, 640, 30);
        Date.setFont(new Font(dv.fontName(), Font.ITALIC, 18));
        Date.setForeground(Color.BLACK);

        JTextArea AnnTextArea = new JTextArea();
        AnnTextArea.setText(ann.getContent());
        if (ann.getContent() == null)
            AnnTextArea.setText("(Không có nội dung!)");
        AnnTextArea.setBounds(0,0,610,1);
        AnnTextArea.setFont(new Font(dv.fontName(), Font.PLAIN, 18));
        AnnTextArea.setForeground(new Color(dv.BlackTextColor()));
        AnnTextArea.setBackground(Color.WHITE);
        AnnTextArea.setAutoscrolls(true);
        AnnTextArea.setWrapStyleWord(true);
        AnnTextArea.setLineWrap(true);
        AnnTextArea.setEditable(false);
        AnnTextArea.setBorder(null);

        JPanel AnnContent = new JPanel();
        BoxLayout boxLayout = new BoxLayout(AnnContent, BoxLayout.Y_AXIS);
        AnnContent.setLayout(boxLayout);
        AnnContent.setBounds(0,0,610,0);
        AnnContent.setBackground(Color.WHITE);
        AnnContent.setOpaque(true);

        AnnContent.add(AnnTextArea);
        if (ann.getImage() != null)
        {
            ImageIcon img = new ImageIcon(ann.getImage());

            JLabel AttachedImage = new JLabel(
                    new ImageIcon(
                            ImageHelper.reSize(
                                    img.getImage(),600,(int)600.0*img.getIconHeight()/img.getIconWidth()
                            )
                    )
            );
            JPanel ImagePanel = new JPanel();
            ImagePanel.setBackground(Color.WHITE);
            ImagePanel.add(AttachedImage);
            AnnContent.add(ImagePanel);
        }
        AnnContent.repaint(0,0,610,0);

        JScrollPane ScrollPaneContent = new JScrollPane(AnnContent,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ScrollPaneContent.setLayout(new ScrollPaneLayout());
        ScrollPaneContent.setBounds(40, 180, 630, 450);
        ScrollPaneContent.setBackground(Color.WHITE);
        ScrollPaneContent.setBorder(null);

        JLabel Publisher = new JLabel("Đơn vị: " + ann.getOrg().getName());
        Publisher.setBounds(80, 640, 560, 30);
        Publisher.setFont(new Font(dv.fontName(), Font.ITALIC, 18));
        Publisher.setForeground(Color.BLACK);
        Publisher.setHorizontalAlignment(JLabel.RIGHT);

        AnnViewPanel = new JPanel();
        AnnViewPanel.setBounds(360, 0, 720, 720);
        AnnViewPanel.setLayout(null);
        AnnViewPanel.setBackground(Color.WHITE);

//        AnnViewPanel.removeAll();
        AnnViewPanel.add(ScrollPaneTitle);
        AnnViewPanel.add(AnnNumber);
        AnnViewPanel.add(Date);
        AnnViewPanel.add(ScrollPaneContent);
        AnnViewPanel.add(Publisher);
//        AnnViewPanel.repaint(360, 0, 720, 720);

        this.removeAll();
        this.add(PreviewPanel);
        this.add(AnnViewPanel);
        this.repaint(0,0, 1080, 720);
    }

    private void ReInitView()
    {
        this.removeAll();

        initPreviewPanel();
        this.add(PreviewPanel);

        this.repaint(0,0,1080, 720);
    }

    /*CONSTRUCTOR*/
    public ManagePostView(Organization org)
    {
        orgUser = org;

        this.setBounds(0,0,1080, 720);
        this.setVisible(true);
        this.setBackground(new Color(dv.ViewBackgroundColor()));
        this.setLayout(null);

        initPreviewPanel();
        this.add(PreviewPanel);

        this.repaint(0,0, 1080, 720);
    }
}
