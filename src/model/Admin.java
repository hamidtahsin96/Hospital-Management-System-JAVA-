package model;
import java.lang.*;

public class Admin extends User
{
    private String adminType;

    public Admin()
    {
        super();
    }

    public Admin(String userId, String password, String name, String email, String contactNo, int role, String adminType)
    {
        super(userId,password,role);
        this.name= name;
        this.email= email;
        this.contactNo= contactNo;
        this.adminType= adminType;
    }

    public void setAdminType(String adminType)
    {
        this.adminType= adminType;
    }

    public String getAdminType()
    {
        return this.adminType;
    }

    public String toStringAdmin()
    {
        String str= this.userId+","+this.name+","+this.email+","+this.contactNo+","+this.adminType+"\n";
        return str;
    }

    public Admin formAdmin(String str)
    {
        String information[]= str.split(",");

        if(information.length>=3)
        {
            Admin a= new Admin();
            a.setUserId(information[0]);
            a.setName(information[1]);
            a.setEmail(information[2]);
            a.setContactNo(information[3]);
            a.setAdminType(information[4]);
            return a;
        }
        else
        {
            return null;
        }

    }
}