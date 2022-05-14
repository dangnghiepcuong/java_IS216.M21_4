package Process;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDateTime;

public class DefaultValue
{
    private String DB_URL = "jdbc:oracle:thin:@localhost:1521:nghiepcuong";
    private String username = "test_project";
    private String password = "test_project";

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

    public int checkStringInputValue(String InputValue, String title, String Message)
    {
        if (InputValue.equals(""))
        {
            JOptionPane OptionFrame = new JOptionPane();

            String responses[] = {"OK!"};

            ImageIcon icon = new ImageIcon(getClass().getResource("/Resources/icon/Warning Icon.png"));

            return OptionFrame.showOptionDialog(null, Message, title,
                    JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, icon, responses, 0);
        }

        return -2;
    }

    public int checkisNumberInputValue(String InputValue, String title, String Message)
    {
        JOptionPane OptionFrame = new JOptionPane();

        String responses[] = {"OK!"};

        try
        {
            int value = Integer.parseInt(InputValue);
        }
        catch (NumberFormatException ex)
        {
            ImageIcon icon = new ImageIcon(getClass().getResource("/Resources/icon/Warning Icon.png"));

            return OptionFrame.showOptionDialog(null, Message, title,
                    JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, icon, responses, 0);
        }

        return -2;
    }

    public int popupConfirmOption(JFrame frame, String Message, String title)
    {
        JOptionPane OptionFrame = new JOptionPane();

        String responses[] = {"Có", "Không"};

        ImageIcon icon = new ImageIcon(getClass().getResource("/Resources/icon/Question Icon.png"));

        return OptionFrame.showOptionDialog(frame, Message, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, responses, 0);

    }

    public JOptionPane popupOption(JFrame frame, String Message, String title, int Option)
    {
        JOptionPane OptionFrame = new JOptionPane();

        String responses[] = {"Có", "Không"};

        switch (Option)
        {
            case 0:
                OptionFrame.showMessageDialog(frame, Message, title, JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon(getClass().getResource("/Resources/icon/Infomation Icon.png")));
                break;
            case 1:
                OptionFrame.showMessageDialog(frame, Message, title, JOptionPane.WARNING_MESSAGE,
                        new ImageIcon(getClass().getResource("/Resources/icon/Warning Icon.png")));
                break;
            case 2:
                OptionFrame.showMessageDialog(frame, Message, title, JOptionPane.ERROR_MESSAGE,
                        new ImageIcon(getClass().getResource("/Resources/icon/Error Icon.png")));
                break;
            default:
                return OptionFrame;
        }

        return OptionFrame;
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



    public String[] getProvinceList()
    {
        String provinceList[] = new String[65];

        for (int i = 1; i <= 64; i++)
            provinceList[i] = new String();

        provinceList[1] = "Hà Nội";
        provinceList[2] = "Hồ Chí Minh";
        provinceList[3] = "Hải Phòng";
        provinceList[4] = "Đà Nẵng";
        provinceList[5] = "Hà Giang";
        provinceList[6] = "Cao Bằng";
        provinceList[7] = "Lai Châu";
        provinceList[8] = "Lào Cai";
        provinceList[9] = "Tuyên Quang";
        provinceList[10] = "Lạng Sơn";
        provinceList[11] = "Bắc Kạn";
        provinceList[12] = "Thái Nguyên";
        provinceList[13] = "Yên Bái";
        provinceList[14] = "Sơn La";
        provinceList[15] = "Phú Thọ";
        provinceList[16] = "Vĩnh Phúc";
        provinceList[17] = "Quảng Ninh";
        provinceList[18] = "Bắc Giang";
        provinceList[19] = "Bắc Ninh";
        provinceList[20] = "";
        provinceList[21] = "Hải Dương";
        provinceList[22] = "Hưng Yên";
        provinceList[23] = "Hòa Bình";
        provinceList[24] = "Hà Nam";
        provinceList[25] = "Nam Định";
        provinceList[26] = "Thái Bình";
        provinceList[27] = "Ninh Bình";
        provinceList[28] = "Thanh Hóa";
        provinceList[29] = "Nghệ An";
        provinceList[30] = "Hà Tĩnh";
        provinceList[31] = "Quảng Bình";
        provinceList[32] = "Quảng Trị";
        provinceList[33] = "Thừa Thiên - Huế";
        provinceList[34] = "Quảng Nam";
        provinceList[35] = "Quảng Ngãi";
        provinceList[36] = "Kon Tum";
        provinceList[37] = "Bình Định";
        provinceList[38] = "Giai Lai";
        provinceList[39] = "Phú Yên";
        provinceList[40] = "Đắk Lắk";
        provinceList[41] = "Khánh Hòa";
        provinceList[42] = "Lâm Đồng";
        provinceList[43] = "Bình Phước";
        provinceList[44] = "Bình Dương";
        provinceList[45] = "Ninh Thuận";
        provinceList[46] = "Tây Ninh";
        provinceList[47] = "Bình Thuận";
        provinceList[48] = "Đồng Nai";
        provinceList[49] = "Long An";
        provinceList[50] = "Đồng Tháp";
        provinceList[51] = "An Giang";
        provinceList[52] = "Bà Rịa - Vũng Tàu";
        provinceList[53] = "Tiền Giang";
        provinceList[54] = "Kiên Giang";
        provinceList[55] = "Cần Thơ";
        provinceList[56] = "Bến Tre";
        provinceList[57] = "Vĩnh Long";
        provinceList[58] = "Trà Vinh";
        provinceList[59] = "Sóc Trăng";
        provinceList[60] = "Bạc Liêu";
        provinceList[61] = "Cà Mau";
        provinceList[62] = "Điện Biên";
        provinceList[63] = "Đắk Nông";
        provinceList[64] = "Hậu Giang";

        return provinceList;
    }

    public DefaultValue()
    {

    }

    public String getProvinceName(String Code)
    {
        int index = Integer.parseInt(Code);
        return this.getProvinceList()[index];
    }

    public String getProvinceCode(String Name)
    {
        for (int i = 1; i < 10; i++)
            if (this.getProvinceList()[i].equals(Name))
                return "0"+i;

        for (int i = 10; i <= 64; i++)
            if (this.getProvinceList()[i].equals(Name))
                return ""+i;

        return "";
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

    public String toApplicationDate(String date)
    {
        String Month = date.substring(5,8);

        if (Month.equals("JAN"))
            Month = "01";
        if (Month.equals("FEB"))
            Month = "02";
        if (Month.equals("MAR"))
            Month = "03";
        if (Month.equals("APR"))
            Month = "04";
        if (Month.equals("MAY"))
            Month = "05";
        if (Month.equals("JUN"))
            Month = "06";
        if (Month.equals("JUL"))
            Month = "07";
        if (Month.equals("AUG"))
            Month = "08";
        if (Month.equals("SEP"))
            Month = "09";
        if (Month.equals("OCT"))
            Month = "10";
        if (Month.equals("NOV"))
            Month = "11";
        if (Month.equals("DEC"))
            Month = "12";

        return date.substring(6,10) + "-" + Month + "-" + date.substring(0,2) ;
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
            return "Đã đăng ký";
        if (Status == 1)
            return "Đã điểm danh";
        if (Status == 2)
            return "Đã tiêm";
        if (Status == 3)
            return "Đã hủy";

        return "";
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
