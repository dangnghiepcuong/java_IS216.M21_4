package View.OrgView;


import Process.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author LeHoangDuyen
 */
public class OrgInformationView extends JPanel implements ActionListener
{
    DefaultValue dv = new DefaultValue();
    private JLabel IDLabel;
    private JLabel PasswordLabel;
    private JLabel NameLabel;
    private JLabel ProvinceLabel;
    private JLabel DistrictLabel;
    private JLabel TownLabel;
    private JLabel StreetLabel;
    private JTextField IDTextField;
    private JTextField NameTextField;
    private JPasswordField PasswordField;
    private JTextField StreetTextField;
    private JButton UpdateAccButton;
    private Choice ProvinceChoice;
    private Choice DistrictChoice;
    private Choice TownChoice;
    private JPanel AccInfoPanel;
    private JPanel OrgInfoPanel;

    private Organization orgUser = new Organization();
    private Account acc = new Account();

    public Organization getOrgUser() {
        return orgUser;
    }

    private void initAccInfoPanel()
    {
        AccInfoPanel = new JPanel();

        AccInfoPanel.setLayout(null);
        AccInfoPanel.setBounds(0, 0,dv.FrameWidth()-dv.FrameHeight(),dv.FrameHeight() );
        AccInfoPanel.setBackground(new Color(dv.ViewBackgroundColor()));
        AccInfoPanel.setBorder(dv.border());

        JLabel AccInfoLabel = new JLabel("THÔNG TIN TÀI KHOẢN");
        AccInfoLabel.setBounds((AccInfoPanel.getWidth()-300)/2, 80, 300, 30);
        AccInfoLabel.setFont(new Font(dv.fontName(), 1, 20));
        AccInfoLabel.setForeground(new Color(dv.FeatureButtonColor()));
        AccInfoLabel.setHorizontalAlignment(JLabel.CENTER);

        AccInfoPanel.add(AccInfoLabel);

        initIDLabel();
        AccInfoPanel.add(IDLabel);

        initPasswordLabel();
        AccInfoPanel.add(PasswordLabel);

        initIDTextField();
        AccInfoPanel.add(IDTextField);

        initPasswordField();
        AccInfoPanel.add(PasswordField);

        initUpdateAccButton();
        AccInfoPanel.add(UpdateAccButton);

        AccInfoPanel.validate();
    }

    private void initOrgInfoPanel()
    {
        OrgInfoPanel = new JPanel();

        OrgInfoPanel.setLayout(null);
        OrgInfoPanel.setBounds(dv.FrameWidth()-dv.FrameHeight(),0,dv.FrameHeight()+30,dv.FrameHeight());
        OrgInfoPanel.setBackground(new Color(dv.ViewBackgroundColor()));
        OrgInfoPanel.setBorder(dv.border());

        JLabel PersonalInfoLabel = new JLabel("THÔNG TIN ĐƠN VỊ TIÊM CHỦNG");
        PersonalInfoLabel.setBounds((OrgInfoPanel.getWidth()-500)/2, 80, 500, 30);
        PersonalInfoLabel.setFont(new Font(dv.fontName(), 1, 20));
        PersonalInfoLabel.setForeground(new Color(dv.FeatureButtonColor()));
        PersonalInfoLabel.setHorizontalAlignment(JLabel.CENTER);

        OrgInfoPanel.add(PersonalInfoLabel);

        initNameLabel();
        OrgInfoPanel.add(NameLabel);

        initNameTextField();
        OrgInfoPanel.add(NameTextField);

        initProvinceLabel();
        OrgInfoPanel.add(ProvinceLabel);

        initProvinceChoice();
        OrgInfoPanel.add(ProvinceChoice);

        initDistrictLabel();
        OrgInfoPanel.add(DistrictLabel);

        initDistrictChoice();
        OrgInfoPanel.add(DistrictChoice);

        initTownLabel();
        OrgInfoPanel.add(TownLabel);

        initTownChoice();
        OrgInfoPanel.add(TownChoice);

        initStreetLabel();
        OrgInfoPanel.add(StreetLabel);

        initStreetTextField();
        OrgInfoPanel.add(StreetTextField);

        OrgInfoPanel.validate();
    }

    private void initIDLabel()
    {
        IDLabel = new JLabel();
        IDLabel.setBounds(70, 80+40 +20, 240, 30);
        IDLabel.setText("Mã đơn vị tiêm chủng");
        IDLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        IDLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initIDTextField()
    {
        IDTextField = new JTextField(orgUser.getID());
        IDTextField.setBounds(70, 80+dv.LabelHeight()+40+20, 220, 30);
        IDTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        IDTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        IDTextField.setForeground(new Color(dv.BlackTextColor()));
        IDTextField.setText(orgUser.getID());
        IDTextField.setEditable(false);
    }

    private void initPasswordLabel()
    {
        PasswordLabel = new JLabel();
        PasswordLabel.setBounds(70, 90 + dv.FieldHeight() + dv.LabelHeight()+40+20+10, 270, 30);
        PasswordLabel.setText("Xác nhận mật khẩu");
        PasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        PasswordLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initPasswordField()
    {
        PasswordField = new JPasswordField();
        PasswordField.setBounds(70, 90 + dv.FieldHeight() + 2 * dv.LabelHeight()+40+20+10, 220, 30);
        PasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        PasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        PasswordField.setForeground(new Color(dv.BlackTextColor()));
    }
    private void initUpdateAccButton()
    {

        ImageIcon RegisterButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Update Button.png"));
        UpdateAccButton = new JButton();
        UpdateAccButton.setBounds(105, 600, RegisterButtonIcon.getIconWidth(), RegisterButtonIcon.getIconHeight());
        UpdateAccButton.setBorder(null);
        UpdateAccButton.setContentAreaFilled(false);
        UpdateAccButton.setIcon(RegisterButtonIcon);
        UpdateAccButton.addActionListener(this);
    }

    private void initNameLabel()
    {
        NameLabel = new JLabel();
        NameLabel.setBounds(50, 80+40+20, 240, 30);
        NameLabel.setText("Tên đơn vị tiêm chủng");
        NameLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        NameLabel.setForeground(new Color(dv.FieldLabelColor()));
    }
    private void initNameTextField()
    {
        NameTextField = new JTextField(orgUser.getName());
        NameTextField.setBounds(50, 80 + dv.LabelHeight()+40+20, 350, 30);
        NameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        NameTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        NameTextField.setForeground(new Color(dv.BlackTextColor()));
        NameTextField.setText(orgUser.getName());
    }

    private void initProvinceLabel()
    {
        ProvinceLabel = new JLabel();
        ProvinceLabel.setBounds(50, 90 + dv.LabelHeight()+dv.FieldHeight()+40+20+10, 240, 30);
        ProvinceLabel.setText("Tỉnh/thành phố");
        ProvinceLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initProvinceChoice()
    {
        ProvinceChoice = new Choice();
        ProvinceChoice.setBounds(50, 90 + 2*dv.LabelHeight()+dv.FieldHeight()+40+20+10, 170, 30);
        ProvinceChoice.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceChoice.setForeground(new Color(dv.FieldLabelColor()));
        ProvinceChoice.setEnabled(false);

        ProvinceChoice.add(dv.getProvinceName(orgUser.getProvince()));

        for (int i = 1; i <= 64; i++)
            if (dv.getProvinceList()[i] != dv.getProvinceName(orgUser.getProvince()) && i != 20)
                ProvinceChoice.add(dv.getProvinceList()[i]);
    }

    private void initDistrictLabel()
    {
        DistrictLabel = new JLabel();
        DistrictLabel.setBounds(50, 100 + 2*dv.LabelHeight()+2*dv.FieldHeight()+40+20+20, 240, 30);
        DistrictLabel.setText("Quận/huyện");
        DistrictLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initDistrictChoice()
    {
        DistrictChoice = new Choice();
        DistrictChoice.setBounds(50, 100 + 3*dv.LabelHeight()+2*dv.FieldHeight()+40+20+20, 170, 30);
        DistrictChoice.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictChoice.setForeground(new Color(dv.FieldLabelColor()));

        DistrictChoice.add(orgUser.getDistrict());

        DistrictChoice.add("");
        DistrictChoice.add("Dầu Tiếng");
        DistrictChoice.add("Thuận An");
        DistrictChoice.add("Thủ Đức");
        DistrictChoice.add("Biên Hòa");
        DistrictChoice.add("Cẩm Mỹ");
        DistrictChoice.add("Thủ Dầu Một");
    }

    private void initTownLabel()
    {
        TownLabel = new JLabel();
        TownLabel.setBounds(50, 110 + 3*dv.LabelHeight()+3*dv.FieldHeight()+40+20+30, 350, 30);
        TownLabel.setText("Xã/phường/thị trấn");
        TownLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initTownChoice()
    {
        TownChoice = new Choice();
        TownChoice.setBounds(50, 110 + 4*dv.LabelHeight()+3*dv.FieldHeight()+40+20+30, 170, 30);
        TownChoice.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownChoice.setForeground(new Color(dv.FieldLabelColor()));

        TownChoice.add(orgUser.getTown());

        TownChoice.add("");
        TownChoice.add("Dầu Tiếng");
        TownChoice.add("Lái Thiêu");
        TownChoice.add("Linh Trung");
        TownChoice.add("Tân Hòa");
        TownChoice.add("Sông Ray");
    }

    private void initStreetLabel()
    {
        StreetLabel = new JLabel();
        StreetLabel.setBounds(50, 120 + 4*dv.LabelHeight()+4*dv.FieldHeight()+40+20+40, 300, 30);
        StreetLabel.setText("Số nhà, tên đường, khu phố/ấp");
        StreetLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        StreetLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initStreetTextField()
    {
        StreetTextField = new JTextField(orgUser.getStreet());
        StreetTextField.setBounds(50, 120 + 5*dv.LabelHeight()+4*dv.FieldHeight()+40+20+40, 560, 30);
        StreetTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        StreetTextField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        StreetTextField.setForeground(new Color(dv.BlackTextColor()));
    }

    private void initComponents()
    {
        this.setBounds(0, 0, dv.FrameWidth(), dv.FrameHeight());
        this.setVisible(true);
        this.setBackground(new Color(dv.ViewBackgroundColor()));
        this.setLayout(null);

        //set account information Panel
        initAccInfoPanel();
        this.add(AccInfoPanel);

        this.repaint(0,0, dv.FrameWidth(), dv.FrameHeight());

        //set personal information Panel
        initOrgInfoPanel();
        this.add(OrgInfoPanel);

    }

    public OrgInformationView(Organization org)
    {
        orgUser = org;
        initComponents();

    }

    /*ACTION PERFORMED*/
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == UpdateAccButton) {

            String InputID = IDTextField.getText();
            String InputPassword = String.valueOf(PasswordField.getPassword());
            String InputName = NameTextField.getText();
            String InputDistrict = DistrictChoice.getSelectedItem();
            String InputTown = TownChoice.getSelectedItem();
            String InputStreet = StreetTextField.getText();

            if ( dv.checkStringInputValue(InputID, "Cảnh báo!", "Nhập tên tài khoản!") != -2 )
                return;
            if ( dv.checkStringInputValue(InputPassword, "Cảnh báo!","Nhập mật khẩu!") != -2 )
                return;

            String query = "select *" +
                    " from ACCOUNT" +
                    " where Username = '" + InputID + "'";
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
                    dv.popupOption(null, "Mật khẩu không đúng!", "Cảnh báo!", 1);
                    return;
                }

            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }
            
            String plsql = "{call ORG_UPDATE_RECORD(?, ?, ?, ?, ?, ?)}";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                CallableStatement cst;

                cst = connection.prepareCall(plsql);
                cst.setString("par_ID", InputID);
                cst.setString("par_Name", InputName);
                cst.setString("par_District", InputDistrict);
                cst.setString("par_Town", InputTown);
                cst.setString("par_Street", InputStreet);
                cst.setString("par_Note", "");

                cst.execute();
            }
            catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

            dv.popupOption(null, "Cập nhật thành công!", "Thông báo!", 0);
        }
    }
}
