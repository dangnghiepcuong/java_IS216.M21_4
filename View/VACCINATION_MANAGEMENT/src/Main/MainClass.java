package Main;
import GUI_Login.*;
import GUI_Main.MOHMainView;
import GUI_RegisterAcc.*;
import GUI_SearchOrg.*;
import GUI_Main.*;

/**
 *
 * @author NghiepCuong
 */
public class MainClass
{
    private static MOHMainView mohMainView;
    private static CitizenMainView citizenMainView;

    public static void main(String args[])
    {
        citizenMainView = new CitizenMainView();
    }
}
