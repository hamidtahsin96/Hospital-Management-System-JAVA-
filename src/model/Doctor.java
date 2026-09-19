package model;
import java.lang.*;

public class Doctor extends User
{
    private String specialist;
    private String educationalInformation;

    public Doctor()
    {
        super();
    }

    public Doctor(String userId, String password, String name, String email, String contactNo, int role, String specialist,String educationalInformation)
    {
        super(userId,password,role);
        this.name= name;
        this.email= email;
        this.contactNo= contactNo;
        this.specialist= specialist;
        this.educationalInformation= educationalInformation;
    }

    public void setSpecialist(String specialist)
    {
        this.specialist= specialist;
    }

    public void setEducationalInformation(String educationalInformation)
    {
        this.educationalInformation= educationalInformation;
    }

    public String getSpecialist()
    {
        return this.specialist;
    }

    public String getEducationalInformation()
    {
        return this.educationalInformation;
    }

    public String toStringDoctor()
    {
        String str= this.userId+","+this.name+","+this.email+","+this.contactNo+","+this.specialist+","+this.educationalInformation+"\n";
        return str;
    }

    public Doctor formDoctor(String str)
    {
        String information[]= str.split(",");

        if(information.length>=3)
        {
            Doctor d= new Doctor();
            d.setUserId(information[0]);
            d.setName(information[1]);
            d.setEmail(information[2]);
            d.setContactNo(information[3]);
            d.setSpecialist(information[4]);
            d.setEducationalInformation(information[5]);
            return d;
        }
        else
        {
            return null;
        }

    }
}