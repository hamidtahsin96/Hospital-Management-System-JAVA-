package model;

public class Receptionist extends User
{
    private String shift;
    private String deskNumber;

    public Receptionist()
    {
        super();
    }

    public Receptionist(String userId, String password, String name, String email,
                        String contactNo, int role, String shift, String deskNumber)
    {
        super(userId, password, name, email, contactNo, role);

        this.shift = shift;
        this.deskNumber = deskNumber;
    }

    public void setShift(String shift)
    {
        this.shift = shift;
    }

    public void setDeskNumber(String deskNumber)
    {
        this.deskNumber = deskNumber;
    }

    public String getShift()
    {
        return this.shift;
    }

    public String getDeskNumber()
    {
        return this.deskNumber;
    }

    public String toStringReceptionist()
    {
        String str = this.userId + "," + this.name + "," + this.email + "," +
                this.contactNo + "," + this.shift + "," +
                this.deskNumber + "\n";

        return str;
    }

    public Receptionist formReceptionist(String str)
    {
        String information[] = str.split(",");

        if(information.length >= 6)
        {
            Receptionist r = new Receptionist();

            r.setUserId(information[0]);
            r.setName(information[1]);
            r.setEmail(information[2]);
            r.setContactNo(information[3]);
            r.setShift(information[4]);
            r.setDeskNumber(information[5]);

            return r;
        }
        else
        {
            return null;
        }
    }
}