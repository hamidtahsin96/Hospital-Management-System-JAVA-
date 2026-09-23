package model;

public class Nurse extends User
{
    private String shift;
    private String department;

    public Nurse()
    {
        super();
    }

    public Nurse(String userId, String password, String name,
                 String email, String contactNo, int role,
                 String shift, String department)
    {
        super(userId, password, role);

        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.shift = shift;
        this.department = department;
    }

    public void setShift(String shift)
    {
        this.shift = shift;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getShift()
    {
        return this.shift;
    }

    public String getDepartment()
    {
        return this.department;
    }

    public String toStringNurse()
    {
        String str = this.userId + "," +
                this.password + "," +
                this.name + "," +
                this.email + "," +
                this.contactNo + "," +
                this.role + "," +
                this.shift + "," +
                this.department + "\n";

        return str;
    }

    public Nurse formNurse(String str)
    {
        String information[] = str.split(",");

        if(information.length >= 8)
        {
            Nurse n = new Nurse();

            n.setUserId(information[0]);
            n.setPassword(information[1]);
            n.setName(information[2]);
            n.setEmail(information[3]);
            n.setContactNo(information[4]);
            n.setRole(Integer.parseInt(information[5]));
            n.setShift(information[6]);
            n.setDepartment(information[7]);

            return n;
        }
        else
        {
            return null;
        }
    }
}