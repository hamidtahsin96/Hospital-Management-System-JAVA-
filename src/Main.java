import java.lang.*;
import model.*;
import controller.*;
import view.*;

public class Main
{
    public static void main(String [] args)
    {
        /*LoginFrame lf= new LoginFrame();
        lf.setVisible(true);*/

        Admin admin = new Admin(
                "A001",
                "1234",
                "Hamid Tahsin",
                "hamid@gmail.com",
                "01736762661",
                User.ADMIN,
                "System Admin"
        );

        AdminHomeFrame ahf = new AdminHomeFrame(admin);
        ahf.setVisible(true);
    }
}