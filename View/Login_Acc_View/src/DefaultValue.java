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
}
