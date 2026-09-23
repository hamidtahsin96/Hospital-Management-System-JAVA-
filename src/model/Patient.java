package model;

public class Patient extends User
{
    private String gender;
    private int age;
    private String address;

    public Patient()
    {
        super();
    }

    public Patient(String userId, String password, String name, String email, String contactNo,int role, String gender, int age, String address)
    {
        super(userId,password,role);
        this.name= name;
        this.email= email;
        this.contactNo= contactNo;
        this.gender= gender;
        this.age= age;
        this.address= address;
    }

    public void setGender(String gender)
    {
        this.gender= gender;
    }

    public void setAge(int age)
    {
        this.age= age;
    }

    public void setAddress(String address)
    {
        this.address= address;
    }

    public String getGender()
    {
        return this.gender;
    }

    public int getAge()
    {
        return this.age;
    }

    public String getAddress()
    {
        return this.address;
    }

    public String toStringPatient()
    {
        String str= this.userId+","+this.name+","+this.email+","+this.contactNo+","+this.gender+","+this.age+","+this.address+"\n";
        return str;
    }

    public Patient formPatient(String str)
    {
        String information[]= str.split(",");

        if(information.length>=3)
        {
            Patient p= new Patient();
            p.setUserId(information[0]);
            p.setName(information[1]);
            p.setEmail(information[2]);
            p.setContactNo(information[3]);
            p.setGender(information[4]);
            p.setAge(Integer.parseInt(information[5]));
            p.setAddress(information[6]);
            return p;
        }
        else
        {
            return null;
        }

    }
}