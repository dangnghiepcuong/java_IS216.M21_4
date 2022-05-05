package Main;
import GUI_Login.*;
import GUI_RegisterAcc.*;
import GUI_SearchOrg.*;

/**
 *
 * @author NghiepCuong
 */
public class MainClass {
    public static void main(String args[])
    {
        LoginView loginView = new LoginView();

        RegisterAccView registerAccView = new RegisterAccView();

        SearchOrgView searchOrgView = new SearchOrgView();
    }
}
