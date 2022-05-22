package ConnectDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

import Process.*;

public class querySchedRegistrations
{
    private DefaultValue dv = new DefaultValue();

    private JPanel initRegPanel(Register Reg)
    {
        JPanel RegPanel = new JPanel();
        RegPanel.setLayout(null);
        RegPanel.setPreferredSize(new Dimension(640,120));
        RegPanel.setBackground(Color.WHITE);

        //PersonalID, LastName, FirstName, Birthday, Gender, Phone, Time, NO, DoseType, Status, Image
        JLabel CitizenName = new JLabel("Đối tượng: " + Reg.getCitizen().getFullName() + " - "
                + dv.getGenderName(Reg.getCitizen().getGender())  + " - " + dv.getYear(Reg.getCitizen().getBirthday())
                + " (ID: *" + Reg.getCitizen().getID().substring(Reg.getCitizen().getID().length() - 4, Reg.getCitizen().getID().length()) + ")");
        CitizenName.setFont(new Font(dv.fontName(), 3, 18));
        CitizenName.setForeground(new Color(dv.FeatureButtonColor()));
        CitizenName.setBounds(30,1,605,30);
        CitizenName.setHorizontalAlignment(JLabel.LEFT);

        JLabel Phone = new JLabel("SĐT: " + Reg.getCitizen().getPhone());
        Phone.setFont(new Font(dv.fontName(), 1, 16));
        Phone.setForeground(new Color(dv.BlackTextColor()));
        Phone.setBounds(30,32,200,25);
        Phone.setHorizontalAlignment(JLabel.LEFT);

        JLabel OnDateVaccine = new JLabel("Lịch tiêm ngày: " + Reg.getSched().getOnDate().substring(0, 10)
                + "  -  Vaccine: " + Reg.getSched().getVaccineID() + " - " + Reg.getSched().getSerial());
        OnDateVaccine.setFont(new Font(dv.fontName(), 1, 16));
        OnDateVaccine.setForeground(new Color(dv.BlackTextColor()));
        OnDateVaccine.setBounds(30,32+25+2,400,25);
        OnDateVaccine.setHorizontalAlignment(JLabel.LEFT);

        JLabel TimeNOStatus = new JLabel("Buổi: " + dv.getTimeName(Reg.getTime())
                + "          STT: " + Reg.getNO() + "          Tình trạng: " + dv.getStatusName(Reg.getStatus()));
        TimeNOStatus.setFont(new Font(dv.fontName(), 0, 16));
        TimeNOStatus.setForeground(new Color(dv.BlackTextColor()));
        TimeNOStatus.setBounds(30, 32+25*2+2,380,25);
        TimeNOStatus.setHorizontalAlignment(JLabel.LEFT);

        RegPanel.add(CitizenName);
        RegPanel.add(Phone);
        RegPanel.add(OnDateVaccine);
        RegPanel.add(TimeNOStatus);

        if (Reg.getStatus() < 2)
        {
            Choice StatusChoice = new Choice();
            StatusChoice.setBounds(500, 32+2, 120, 30);
            StatusChoice.setFont(new Font(dv.fontName(), 0, 16));
            StatusChoice.setForeground(new Color(dv.BlackTextColor()));

            if (Reg.getStatus() == 0)
            {
                StatusChoice.add("Điểm danh");
                StatusChoice.add("Đã hủy");
            }

            if (Reg.getStatus() == 1)
            {
                StatusChoice.add("Đã tiêm");
                StatusChoice.add("Đã hủy");
            }

            JButton UpdateStatusButton = new JButton();
            ActionListener handleUpdateRegistrations = new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    LocalDate SchedOnDate = LocalDate.parse(Reg.getSched().getOnDate().substring(0, 10));

                    LocalDate sysdate = LocalDate.parse(dv.todayString());

                    if (sysdate.isBefore(SchedOnDate))
                    {
                        dv.popupOption(null, "Chưa thể cập nhật trạng thái cho lượt đăng ký này!", "Lỗi!", 2);
                        return;
                    }

                    if ( dv.popupConfirmOption(null,"Xác nhận cập nhật trạng thái lượt đăng ký?", "Xác nhận?") != 0)
                        return;

                    String plsql = "{call REG_UPDATE_STATUS(?, ?, ?)}";
                    Connection connection = null;
                    try {
                        connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());

                        CallableStatement cst = connection.prepareCall(plsql);
                        int temp = dv.getStatusIndex(StatusChoice.getSelectedItem());
                        cst.setString("par_PersonalID", Reg.getCitizen().getID());
                        cst.setString("par_SchedID", Reg.getSched().getID());
                        cst.setInt("par_Status", dv.getStatusIndex(StatusChoice.getSelectedItem()));

                        cst.execute();
                    }
                    catch (SQLException ex)
                    {
                        dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
                        ex.printStackTrace();
                        return;
                    } finally {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }

                    //repaint Status on Registration Panel
                    Reg.setStatus(dv.getStatusIndex(StatusChoice.getSelectedItem()));
                    TimeNOStatus.setText("Buổi: " + dv.getTimeName(Reg.getTime())
                            + "          STT: " + Reg.getNO() + "          Tình trạng: " + dv.getStatusName(Reg.getStatus()));

                    if (StatusChoice.getSelectedItem() == "Điểm danh")
                    {
                        StatusChoice.removeAll();
                        StatusChoice.add("Đã tiêm");
                        StatusChoice.add("Đã hủy");
                    }
                    else
                    {
                        StatusChoice.removeAll();
                        UpdateStatusButton.setEnabled(false);
                    }
                    RegPanel.repaint();
                }
            };
            ImageIcon UpdateStatusButtonIcon = new ImageIcon(getClass().getResource("/Resources/icon/Update Status Button.png"));
            UpdateStatusButton.setForeground(new Color(dv.BlackTextColor()));
            UpdateStatusButton.setBounds(500,32*2+5,UpdateStatusButtonIcon.getIconWidth(),UpdateStatusButtonIcon.getIconHeight());
            UpdateStatusButton.setContentAreaFilled(false);
            UpdateStatusButton.setBorder(null);
            UpdateStatusButton.setIcon(UpdateStatusButtonIcon);
            UpdateStatusButton.addActionListener(handleUpdateRegistrations);

            RegPanel.add(StatusChoice);
            RegPanel.add(UpdateStatusButton);
        }

        return RegPanel;
    }

    public JPanel SchedRegListPanel(Schedule Sched, int Status)
    {
        JPanel RegListPanel = new JPanel();
        RegListPanel.setBackground(new Color(dv.SpecifiedAreaBackgroundColor()));
        RegListPanel.setLayout(new FlowLayout());

        String query = "";

        int nReg = 0;
        int i = 0;

        query = "select PersonalID, LastName, FirstName, Birthday, Gender, Phone, Time, NO, DoseType, Status, Image" +
                " from REGISTER REG, PERSON" +
                " where REG.PersonalID = PERSON.ID" +
                " and REG.SchedID = '" +  Sched.getID() + "'";
        if (Status != -1)
            query += " and Status = " + Status;
        query += " order by Status, Time, NO";

        System.out.println(query);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dv.getDB_URL(), dv.getUsername(), dv.getPassword());
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                //PersonalID, LastName, FirstName, Birthday, Gender, Phone, Time, NO, DoseType, Status, Image
                Register Reg = new Register();
                Reg.setSched(Sched);
                Reg.getCitizen().setID(rs.getString("PersonalID"));
                Reg.getCitizen().setLastName(rs.getString("LastName"));
                Reg.getCitizen().setFirstName(rs.getString("FirstName"));
                Reg.getCitizen().setBirthday(rs.getString("Birthday"));
                Reg.getCitizen().setGender(rs.getInt("Gender"));
                Reg.getCitizen().setPhone(rs.getString("Phone"));
                Reg.setTime(rs.getInt("Time"));
                Reg.setNO(rs.getInt("NO"));
                Reg.setDoseType(rs.getString("DoseType"));
                Reg.setStatus(rs.getInt("Status"));
                Reg.setImage(rs.getBytes("Image"));
                RegListPanel.add(initRegPanel(Reg));
                i++;
            }
        } catch (SQLException ex) {
            dv.popupOption(null, ex.getMessage(), String.valueOf(ex.getErrorCode()), 2);
            ex.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return RegListPanel;
    }
}
