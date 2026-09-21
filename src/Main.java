import model.*;
import controller.*;

public class Main
{
    public static void main(String[] args)
    {
        Admin admin = new Admin(
                "A001",
                "1234",
                "Hamid Tahsin",
                "hamid@gmail.com",
                "01736762661",
                User.ADMIN,
                "System Admin"
        );

        UserController uc = new UserController();
        AdminController ac = new AdminController();

        User user = new User(
                admin.getUserId(),
                admin.getPassword(),
                admin.getName(),
                admin.getEmail(),
                admin.getContactNo(),
                admin.getRole()
        );

        uc.insertUser(user);

        if(admin.getRole() == User.ADMIN)
        {
            ac.insertAdmin(admin);
        }

        System.out.println("Admin created successfully.");
    }
}