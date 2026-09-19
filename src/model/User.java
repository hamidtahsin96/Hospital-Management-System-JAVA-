package model;

public class User
{
    protected String userId;
    protected String password;
    protected String name;
    protected String email;
    protected String contactNo;
    protected int role;

    public static final int ADMIN = 1;
    public static final int DOCTOR = 2;
    public static final int PATIENT = 3;
    public static final int NURSE = 4;
    public static final int RECEPTIONIST = 5;

    public User()
    {

    }

    public User(String userId, String password, int role)
    {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

    public User(String userId, String password, String name, String email, String contactNo, int role)
    {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.role = role;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setContactNo(String contactNo)
    {
        this.contactNo = contactNo;
    }

    public void setRole(int role)
    {
        this.role = role;
    }

    public String getUserId()
    {
        return this.userId;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getName()
    {
        return this.name;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getContactNo()
    {
        return this.contactNo;
    }

    public int getRole()
    {
        return this.role;
    }

    public String toStringUser()
    {
        String str = this.userId + "," + this.password + "," + this.name + "," +
                this.email + "," + this.contactNo + "," + this.role + "\n";

        return str;
    }

    public User formUser(String str)
    {
        String information[] = str.split(",");

        if(information.length >= 6)
        {
            User u = new User();

            u.setUserId(information[0]);
            u.setPassword(information[1]);
            u.setName(information[2]);
            u.setEmail(information[3]);
            u.setContactNo(information[4]);
            u.setRole(Integer.parseInt(information[5]));

            return u;
        }
        else
        {
            return null;
        }
    }
}