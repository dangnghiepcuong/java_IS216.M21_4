package View.OrgView;


import Process.Account;
import Process.DefaultValue;
import Process.Organization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author LeHoangDuyen
 */
public class OrgInformationView extends JPanel implements ActionListener, KeyListener, ItemListener {
    DefaultValue dv = new DefaultValue();
    private JLabel IDLabel;
    private JLabel PasswordLabel;
    private JLabel ChangePasswordLabel;
    private JLabel NewPasswordLabel;
    private JLabel RepeatNewPasswordLabel;
    private JLabel NameLabel;
    private JLabel ProvinceLabel;
    private JLabel DistrictLabel;
    private JLabel TownLabel;
    private JLabel StreetLabel;
    private JTextField IDTextField;
    private JTextField NameTextField;
    private JPasswordField PasswordField;
    private JPasswordField NewPasswordField;
    private JPasswordField RepeatNewPasswordField;
    private JTextField StreetTextField;
    private JButton UpdateAccButton;
    private JComboBox ProvinceComboBox;
    private JComboBox DistrictComboBox;
    private JComboBox TownComboBox;
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

        initChangePasswordLabel();
        AccInfoPanel.add(ChangePasswordLabel);

        initNewPasswordLabel();
        AccInfoPanel.add(NewPasswordLabel);

        initNewPasswordField();
        AccInfoPanel.add(NewPasswordField);

        initRepeatNewPasswordLabel();
        AccInfoPanel.add(RepeatNewPasswordLabel);

        initRepeatNewPasswordField();
        AccInfoPanel.add(RepeatNewPasswordField);

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

        initProvinceComboBox();
        OrgInfoPanel.add(ProvinceComboBox);

        initDistrictLabel();
        OrgInfoPanel.add(DistrictLabel);

        initDistrictComboBox();
        OrgInfoPanel.add(DistrictComboBox);

        initTownLabel();
        OrgInfoPanel.add(TownLabel);

        initTownComboBox();
        OrgInfoPanel.add(TownComboBox);

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
        PasswordField.addKeyListener(this);
    }

    private void initChangePasswordLabel()
    {
        ChangePasswordLabel = new JLabel("Đổi mật khẩu:");
        ChangePasswordLabel.setBounds(70, 120 + 2*dv.FieldHeight() + 3 * dv.LabelHeight() +dv.AlignTop_InfoView(), 240, 30);
        ChangePasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ChangePasswordLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initNewPasswordLabel()
    {
        NewPasswordLabel = new JLabel("Nhập mật khẩu mới");
        NewPasswordLabel.setBounds(70, 120 + 3*dv.LabelHeight()+3*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        NewPasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        NewPasswordLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initNewPasswordField()
    {
        NewPasswordField = new JPasswordField();
        NewPasswordField.setBounds(70, 120 + 4*dv.LabelHeight()+3*dv.FieldHeight() + dv.AlignTop_InfoView(), 220, 30);
        NewPasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        NewPasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        NewPasswordField.setForeground(new Color(dv.BlackTextColor()));
        NewPasswordField.addKeyListener(this);
    }

    private void initRepeatNewPasswordLabel()
    {
        RepeatNewPasswordLabel = new JLabel("Nhập lại mật khẩu mới");
        RepeatNewPasswordLabel.setBounds(70, 130 + 4*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 240, 30);
        RepeatNewPasswordLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        RepeatNewPasswordLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initRepeatNewPasswordField()
    {
        RepeatNewPasswordField = new JPasswordField();
        RepeatNewPasswordField.setBounds(70, 130 + 5*dv.LabelHeight()+4*dv.FieldHeight() + dv.AlignTop_InfoView(), 220, 30);
        RepeatNewPasswordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        RepeatNewPasswordField.setFont(new Font(dv.fontName(), Font.PLAIN, dv.LabelFontSize()));
        RepeatNewPasswordField.setForeground(new Color(dv.BlackTextColor()));
        RepeatNewPasswordField.addKeyListener(this);
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

    private void initProvinceComboBox()
    {
        ProvinceComboBox = new JComboBox();
        ProvinceComboBox.setBounds(50, 90 + 2*dv.LabelHeight()+dv.FieldHeight()+40+20+10, 170, 30);
        ProvinceComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        ProvinceComboBox.setForeground(new Color(dv.FieldLabelColor()));
        ProvinceComboBox.setEnabled(false);
        ProvinceComboBox.setBackground(Color.WHITE);

        ProvinceComboBox.addItem(orgUser.getProvince());
    }

    private void initDistrictLabel()
    {
        DistrictLabel = new JLabel();
        DistrictLabel.setBounds(50, 100 + 2*dv.LabelHeight()+2*dv.FieldHeight()+40+20+20, 240, 30);
        DistrictLabel.setText("Quận/huyện");
        DistrictLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initDistrictComboBox()
    {
        DistrictComboBox = new JComboBox();
        DistrictComboBox.setBounds(50, 100 + 3*dv.LabelHeight()+2*dv.FieldHeight()+40+20+20, 170, 30);
        DistrictComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        DistrictComboBox.setForeground(new Color(dv.FieldLabelColor()));
        DistrictComboBox.setBackground(Color.WHITE);

        DistrictComboBox.addItem(orgUser.getDistrict());

        try {
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            String query = "select distinct DistrictCode, DistrictName from REGION " +
                    "where ProvinceName = '" + orgUser.getProvince() + "' " +
                    "order by DistrictCode";
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            DistrictComboBox.addItem("");
            while (rs.next())
            {
                if (rs.getString("DistrictName").equals(orgUser.getDistrict()) == false)
                    DistrictComboBox.addItem(rs.getString("DistrictName"));
            }
        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
            ex.printStackTrace();
            return;
        }

        DistrictComboBox.addItemListener(this);
    }

    private void initTownLabel()
    {
        TownLabel = new JLabel();
        TownLabel.setBounds(50, 110 + 3*dv.LabelHeight()+3*dv.FieldHeight()+40+20+30, 350, 30);
        TownLabel.setText("Xã/phường/thị trấn");
        TownLabel.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownLabel.setForeground(new Color(dv.FieldLabelColor()));
    }

    private void initTownComboBox()
    {
        TownComboBox = new JComboBox();
        TownComboBox.setBounds(50, 110 + 4*dv.LabelHeight()+3*dv.FieldHeight()+40+20+30, 170, 30);
        TownComboBox.setFont(new Font(dv.fontName(), 0, dv.LabelFontSize()));
        TownComboBox.setForeground(new Color(dv.FieldLabelColor()));
        TownComboBox.setBackground(Color.WHITE);

        TownComboBox.addItem(orgUser.getTown());
        try {
            Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

            String query = "select distinct TownCode, TownName from REGION " +
                    "where ProvinceName = '" + orgUser.getProvince() + "' " +
                    "order by TownCode";
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            DistrictComboBox.addItem("");
            while (rs.next())
            {
                if (rs.getString("TownName").equals(orgUser.getTown()) == false)
                    DistrictComboBox.addItem(rs.getString("TownName"));
            }
        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
            ex.printStackTrace();
            return;
        }
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
        if (orgUser.getDistrict() == null)
            orgUser.setDistrict("");
        if (orgUser.getTown() == null)
            orgUser.setTown("");
        initComponents();
    }

    /*ACTION PERFORMED*/
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == UpdateAccButton)
        {
            String InputID = IDTextField.getText();
            String InputPassword = String.valueOf(PasswordField.getPassword());
            String InputName = NameTextField.getText();
            String InputDistrict = String.valueOf(DistrictComboBox.getSelectedItem());
            String InputTown = String.valueOf(TownComboBox.getSelectedItem());
            String InputStreet = StreetTextField.getText();
            String InputNewPassword = String.valueOf(NewPasswordField.getPassword());
            String InputRepeatNewPassword = String.valueOf(RepeatNewPasswordField.getPassword());

            if ( dv.checkStringInputValue(InputName, "Cảnh báo!", "Nhập tên đơn vị!") != -2 )
                return;

            if ( dv.checkStringInputValue(InputPassword, "Cảnh báo!","Xác nhận mật khẩu để cập nhật thông tin!") != -2 )
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
                    dv.popupOption(null, "Mật khẩu không đúng!", "Lỗi!", 2);
                    return;
                }
            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

            if (InputRepeatNewPassword.equals(InputNewPassword) == false)
            {
                dv.popupOption(null,"Mật khẩu mới không trùng khớp với ô nhập lại mật khẩu mới!", "Lỗi!", 2);
                JOptionPane OptionFrame = new JOptionPane();
                return;
            }

            String plsql = "{call ORG_UPDATE_RECORD(?, ?, ?, ?, ?, ?)}";

            String plsql2 = "{call ACC_UPDATE_PASSWORD(?,?,?)}";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                CallableStatement cst;

                if (InputNewPassword.equals("") == false) {
                    cst = connection.prepareCall(plsql2);
                    cst.setString(1, orgUser.getID());
                    cst.setString(2, InputPassword);
                    cst.setString(3, InputNewPassword);

                    cst.execute();
                }

                CallableStatement cst2;

                cst2 = connection.prepareCall(plsql);
                cst2.setString("par_ID", InputID);
                cst2.setString("par_Name", InputName);
                cst2.setString("par_DistrictName", InputDistrict);
                cst2.setString("par_TownName", InputTown);
                cst2.setString("par_Street", InputStreet);
                cst2.setString("par_Note", "");

                cst2.execute();
            }
            catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

            dv.popupOption(null, "Cập nhật thành công!", "Thông báo!", 0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            String InputID = IDTextField.getText();
            String InputPassword = String.valueOf(PasswordField.getPassword());
            String InputName = NameTextField.getText();
            String InputDistrict = String.valueOf(DistrictComboBox.getSelectedItem());
            String InputTown = String.valueOf(TownComboBox.getSelectedItem());
            String InputStreet = StreetTextField.getText();
            String InputNewPassword = String.valueOf(NewPasswordField.getPassword());
            String InputRepeatNewPassword = String.valueOf(RepeatNewPasswordField.getPassword());

            if ( dv.checkStringInputValue(InputName, "Cảnh báo!", "Nhập tên đơn vị!") != -2 )
                return;

            if ( dv.checkStringInputValue(InputPassword, "Cảnh báo!","Xác nhận mật khẩu để cập nhật thông tin!") != -2 )
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
                    dv.popupOption(null, "Mật khẩu không đúng!", "Lỗi!", 2);
                    return;
                }
            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

            if (InputRepeatNewPassword.equals(InputNewPassword) == false)
            {
                dv.popupOption(null,"Mật khẩu mới không trùng khớp với ô nhập lại mật khẩu mới!", "Lỗi!", 2);
                JOptionPane OptionFrame = new JOptionPane();
                return;
            }

            String plsql = "{call ORG_UPDATE_RECORD(?, ?, ?, ?, ?, ?)}";

            String plsql2 = "{call ACC_UPDATE_PASSWORD(?,?,?)}";

            try {
                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                CallableStatement cst;

                if (InputNewPassword.equals("") == false) {
                    cst = connection.prepareCall(plsql2);
                    cst.setString(1, orgUser.getID());
                    cst.setString(2, InputPassword);
                    cst.setString(3, InputNewPassword);

                    cst.execute();
                }

                CallableStatement cst2;

                cst2 = connection.prepareCall(plsql);
                cst2.setString("par_ID", InputID);
                cst2.setString("par_Name", InputName);
                cst2.setString("par_DistrictName", InputDistrict);
                cst2.setString("par_TownName", InputTown);
                cst2.setString("par_Street", InputStreet);
                cst2.setString("par_Note", "");

                cst2.execute();
            }
            catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                ex.printStackTrace();
                return;
            }

            dv.popupOption(null, "Cập nhật thành công!", "Thông báo!", 0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == DistrictComboBox)
        {
            try {
                TownComboBox.removeAllItems();

                Connection connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                String query = "select distinct TownCode, TownName from REGION " +
                        "where ProvinceName = '" + ProvinceComboBox.getSelectedItem() + "' " +
                        "and DistrictName = '" + DistrictComboBox.getSelectedItem() + "' " +
                        "order by TownCode";
                PreparedStatement st = connection.prepareStatement(query);
                ResultSet rs = st.executeQuery();

                TownComboBox.addItem("");
                while (rs.next())
                    TownComboBox.addItem(rs.getString("TownName"));
            } catch (SQLException ex) {
                dv.popupOption(null, ex.getMessage(), "Lỗi " + ex.getErrorCode(), 2);
                ex.printStackTrace();
                return;
            }
        }
    }
}
