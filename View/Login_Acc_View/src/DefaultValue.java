import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DefaultValue
{
    private String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private String username = "vaccination";
    private String password = "vaccination";

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
}
