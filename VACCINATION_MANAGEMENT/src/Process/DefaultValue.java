package Process;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDateTime;

public class DefaultValue {
    private String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private String username = "vaccination_management_database";
    private String password = "covid19";

    private String DB_URL1 = "jdbc:oracle:thin:@localhost:1521:orcl";
    private String username1 = "vaccination";
    private String password1 = "vaccination";

    public String getDB_URL() {
        return DB_URL;
    }

    public void setDB_URL(String DB_URL) {
        this.DB_URL = DB_URL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDB_URL1() {
        return DB_URL1;
    }

    public void setDB_URL1(String DB_URL1) {
        this.DB_URL1 = DB_URL1;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public int checkStringInputValue(String InputValue, String title, String Message) {
        if (InputValue.equals("")) {
            JOptionPane OptionFrame = new JOptionPane();

            String responses[] = {"OK!"};

            ImageIcon icon = new ImageIcon(getClass().getResource("/Resources/icon/Warning Icon.png"));

            return OptionFrame.showOptionDialog(null, Message, title,
                    JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, icon, responses, 0);
        }

        return -2;
    }

    public int checkisNumberInputValue(String InputValue, String title, String Message) {
        JOptionPane OptionFrame = new JOptionPane();

        String responses[] = {"OK!"};

        try {
            int value = Integer.parseInt(InputValue);
        } catch (NumberFormatException ex) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/Resources/icon/Warning Icon.png"));

            return OptionFrame.showOptionDialog(null, Message, title,
                    JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, icon, responses, 0);
        }

        return -2;
    }

    public int popupConfirmOption(JFrame frame, String Message, String title) {
        JOptionPane OptionFrame = new JOptionPane();

        String responses[] = {"Có", "Không"};

        ImageIcon icon = new ImageIcon(getClass().getResource("/Resources/icon/Question Icon.png"));

        return OptionFrame.showOptionDialog(frame, Message, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, responses, 0);

    }

    public JOptionPane popupOption(JFrame frame, String Message, String title, int Option) {
        JOptionPane OptionFrame = new JOptionPane();

//        String responses[] = {"Có", "Không"};

        String translateMess = Message;
        if (Option == 2 && title.length() >= 5)
            translateMess = translateError(Integer.parseInt(title.substring(title.length()-5,title.length())), Message);

        switch (Option) {
            case 0:
                OptionFrame.showMessageDialog(frame, Message, title, JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon(getClass().getResource("/Resources/icon/Infomation Icon.png")));
                break;
            case 1:
                OptionFrame.showMessageDialog(frame, Message, title, JOptionPane.WARNING_MESSAGE,
                        new ImageIcon(getClass().getResource("/Resources/icon/Warning Icon.png")));
                break;
            case 2:
                OptionFrame.showMessageDialog(frame, translateMess, title, JOptionPane.ERROR_MESSAGE,
                        new ImageIcon(getClass().getResource("/Resources/icon/Error Icon.png")));
                break;
            default:
                return OptionFrame;
        }

        return OptionFrame;
    }

    public String translateError(int ErrorCode, String Message)
    {
        if (ErrorCode == 20000) return "Độ tuổi của bạn nhỏ hơn quy định tiêm chủng!";
        if (ErrorCode == 20001) return "Bạn phải hoàn thành lượt đăng ký tiêm chủng hiện có trước khi thực hiện đăng ký lịch tiêm chủng mới!";
        if (ErrorCode == 20002) return "Bạn đã hoàn thành tất cả các liều tiêm chủng!";
        if (ErrorCode == 20003) return "Không thể đăng ký lịch tiêm này vì không phù hợp với khoảng cách thời gian tiêm chủng được quy định!";
        if (ErrorCode == 20004) return "Không thể đăng ký lịch tiêm này vì không phù hợp với loại vaccine tiêm chủng được quy định!";
        if (ErrorCode == 20005) return "Bạn phải thực hiện khai báo y tế trong vòng 7 ngày trước khi lịch tiêm diễn ra!";
        if (ErrorCode == 20006) return "Bạn đã bị nhiễm Covid-19 trong khoảng thời gian gần đây, xin chờ đến khi bạn hồi phục hoàn toàn!";
        if (ErrorCode == 20007) return "Bạn chưa thực hiện khai báo y tế trong 7 ngày gần đây!";
        if (ErrorCode == 20008) return "Tên không được bỏ trống!";
        if (ErrorCode == 20009) return "Ngày sinh không được là ngày trong tương lai!";
        if (ErrorCode == 20010) return "Số lượt đăng ký không thể là số âm!";
        if (ErrorCode == 20011) return "Số lượt đăng ký đã đạt giới hạn!";
        if (ErrorCode == 20012) return "Tên tài khoản/SĐT đã được sử dụng bởi một người dùng khác!";
        if (ErrorCode == 20013) return "Sai mật khẩu!";
        if (ErrorCode == 20014) return "Tên tài khoản/SĐT không tồn tại!";
        if (ErrorCode == 20015) return "CCCD hoặc SĐT đã được sử dụng bởi một người dùng khác!";
        if (ErrorCode == 20016) return "Mã vaccine hoặc tên vaccine đã được sử dụng để đăng ký thông tin!";
        if (ErrorCode == 20020) return "Không thể cập nhật, số lượt đăng ký đã vượt giới hạn!";
        if (ErrorCode == 20021) return "Số lượng tài khoản phải là số dương!";
        return Message;
    }

    public String popupInputOption(String message, String title)
    {
        return JOptionPane.showInputDialog(null,message,title,1);
    }


    public String fontName()
    {
        return "Open Sans";
    }

    public int LabelFontSize()
    {
        return 20;
    }

    public int LabelWidth()
    {
        return 200;
    }

    public int LabelHeight()
    {
        return 30;
    }

    public int FieldWidth()
    {
        return 200;
    }

    public int FieldHeight()
    {
        return 30;
    }

    public int FrameWidth()
    {
        return 1080;
    }

    public int FrameHeight()
    {
        return 720;
    }

    public int AlignTop()
    {
        return 80;
    }

    public int AlignTop_InfoView() { return 40; }

    public int AlignLeft()
    {
        return 60;
    }

    public int A4Width() { return 595; }

    public int A4Height() { return 842; }
    
    public Border border()
    {
        return BorderFactory.createLineBorder(Color.BLACK);
    }

    /*COLOR HEX CODE
    màu xám dành cho nền: F2F2F2
    màu Peach dành cho button chức năng: FF9292
    màu xám hơi đậm dành cho label: 666666
    màu đen dành cho text nhập vào: 333333*/

    public int ViewBackgroundColor()
    {
        return 0xFFFAFA;
    }

    public int SpecifiedAreaBackgroundColor() {return 0xF3F6FB;}

    public int FeatureButtonColor()
    {
        return 0xFF9292;
    }

    public int FieldLabelColor()
    {
        return 0x666666;
    }

    public int BlackTextColor()
    {
        return 0x333333;
    }

    public int GreenPastel() { return 0x77DD77; };

    public int YellowPastel() { return 0xFDFD96; };

    public int RedPastel() { return 0xFF6961; };


    public DefaultValue()
    {

    }


    public String toOracleDateFormat(String date)
    {
        String Month = date.substring(5,7);

        if (Month.equals("01"))
            Month = "JAN";
        if (Month.equals("02"))
            Month = "FEB";
        if (Month.equals("03"))
            Month = "MAR";
        if (Month.equals("04"))
            Month = "APR";
        if (Month.equals("05"))
            Month = "MAY";
        if (Month.equals("06"))
            Month = "JUN";
        if (Month.equals("07"))
            Month = "JUL";
        if (Month.equals("08"))
            Month = "AUG";
        if (Month.equals("09"))
            Month = "SEP";
        if (Month.equals("10"))
            Month = "OCT";
        if (Month.equals("11"))
            Month = "NOV";
        if (Month.equals("12"))
            Month = "DEC";

        return date.substring(8,10) + "-" + Month + "-" + date.substring(0,4);
    }

    public String oracleSysdate()
    {
        return toOracleDateFormat(LocalDateTime.now().toString().substring(0, 10));
    }

    public String toTextDate(String date)
    {
        return "ngày " + date.substring(8,10) + " tháng " + date.substring(5,7) + " năm " + date.substring(0,4);
    }

    public String todayString()
    {
        return LocalDateTime.now().toString().substring(0, 10);
    }

    public String getDoseTypeName(String DoseType)
    {
        if (DoseType.equals("basic"))
            return "Cơ bản";

        if (DoseType.equals("booster"))
            return "Tăng cường";

        if (DoseType.equals("repeat"))
            return "Nhắc lại";

        return "";
    }

    public String getTimeName(int Time)
    {
        if (Time == 0)
            return "Sáng";

        if (Time == 1)
            return "Trưa";

        if (Time == 2)
            return "Tối";

        return "";
    }

    public String getStatusName(int Status)
    {
        if (Status == 0)
            return "Đăng ký";
        if (Status == 1)
            return "Điểm danh";
        if (Status == 2)
            return "Đã tiêm";
        if (Status == 3)
            return "Đã hủy";

        return "";
    }

    public int getStatusIndex(String Status)
    {
        if (Status == "Đăng ký")
            return 0;
        if (Status == "Điểm danh")
            return 1;
        if (Status == "Đã tiêm")
            return 2;
        if (Status == "Đã hủy")
            return 3;

        return-1;
    }

    public String[] getGenderList()
    {
        String genderList[] = {"Nữ", "Nam", "Khác"};

        return genderList;
    }

    public String getGenderName(int Gender)
    {
        if (Gender == 0)
            return "Nữ";
        if (Gender == 1)
            return "Nam";
        if (Gender == 2)
            return "Khác";

        return "";
    }

    public int getGenderIndex(String Gender)
    {
        if (Gender == "Nữ")
            return 0;
        if (Gender == "Nam")
            return 1;
        if (Gender == "Khác")
            return 2;
        return -1;
    }

    public String getVaccineID(int index)
    {
        switch (index)
        {
            case 0: return "Astra";
            case 1: return "Vero";
            case 2: return "Sputnik";
            case 3: return "Pfizer";
            case 4: return "Moderna";
        }

        return "";
    }

    public int getYear(String date)
    {
        return Integer.parseInt(date.substring(0,4));
    }

    public int getMonth(String date)
    {
        return Integer.parseInt(date.substring(5,7));
    }

    public int getDay(String date)
    {
        return Integer.parseInt(date.substring(8,10));
    }
}
