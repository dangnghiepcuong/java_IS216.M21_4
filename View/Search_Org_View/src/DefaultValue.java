import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DefaultValue
{
    private String DB_URL = "jdbc:oracle:thin:@localhost:1521:nghiepcuong";
    private String username = "test_project";
    private String password = "test_project";

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

    public int LabelFontSize()
    {
        return 20;
    }

    public int LabelWidth()
    {
        return 200;
    }

    public int LabelHeigth()
    {
        return 30;
    }

    public int FieldWidth()
    {
        return 200;
    }

    public int FieldHeigth()
    {
        return 30;
    }

    public int FrameWidth()
    {
        return 1080;
    }

    public int FrameHeigth()
    {
        return 720;
    }

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
        return 0xF2F2F2;
    }

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

    private String province[] = new String[65];

    public DefaultValue()
    {
        for (int i=1; i<=64; i++)
            province[i] = new String();

        province[1] = "Hà Nội";
        province[2] = "Hồ Chí Minh";
        province[3] = "Hải Phòng";
        province[4] = "Đà Nẵng";
        province[5] = "Hà Giang";
        province[25] = "Nam Định";
        province[39] = "Phú Yên";
        province[43] = "Bình Phước";
        province[44] = "Bình Dương";
        province[45] = "Ninh Thuận";
        province[46] = "Tây Ninh";
        province[47] = "Bình Thuận";
        province[48] = "Đồng Nai";
        province[49] = "Long An";
        province[50] = "Đồng Tháp";
        province[51] = "An Giang";
        province[52] = "Bà Rịa - Vũng Tàu";
        province[53] = "Tiền Giang";
        province[54] = "Kiên Giang";
        province[55] = "Cần Thơ";
        province[56] = "Bến Tre";
        province[57] = "Vĩnh Long";
        province[58] = "Trà Vinh";
        province[59] = "Sóc Trăng";
    }

    public String getProvinceName(String Code)
    {
        int index = Integer.parseInt(Code);
        return province[index];
    }

    public String getProvinceCode(String Name)
    {
        for (int i = 1; i < 10; i++)
            if (province[i].equals(Name))
                return "0"+i+"";

        for (int i = 10; i <= 64; i++)
            if (province[i].equals(Name))
                return ""+i+"";

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

    public String sysdate()
    {
        return toOracleDateFormat(java.time.LocalDateTime.now().toString().substring(0, 10));
    }
}
