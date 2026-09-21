package controller;
import java.lang.*;
import model.*;

public class AdminController
{
    public void insertAdmin(Admin a)
    {
        Admin admins[] = this.getAllAdmin();

        for(int i = 0; i < admins.length; i++)
        {
            if(admins[i] == null)
            {
                admins[i] = a;
                break;
            }
        }

        this.write(admins);
    }

    public void updateAdmin(Admin a)
    {
        Admin admins[] = this.getAllAdmin();

        for(int i = 0; i < admins.length; i++)
        {
            if(admins[i] != null)
            {
                if(admins[i].getUserId().equals(a.getUserId()))
                {
                    admins[i] = a;
                    break;
                }
            }
        }

        this.write(admins);
    }

    public void deleteAdmin(String userId)
    {
        Admin admins[] = this.getAllAdmin();

        for(int i = 0; i < admins.length; i++)
        {
            if(admins[i] != null)
            {
                if(admins[i].getUserId().equals(userId))
                {
                    admins[i] = null;
                    break;
                }
            }
        }

        this.write(admins);
    }

    public Admin searchAdmin(String userId)
    {
        Admin admins[] = this.getAllAdmin();

        for(int i = 0; i < admins.length; i++)
        {
            if(admins[i] != null)
            {
                if(admins[i].getUserId().equals(userId))
                {
                    return admins[i];
                }
            }
        }

        return null;
    }

    public Admin[] getAllAdmin()
    {
        String fileName = "Information/admins.txt";

        FileIO fio = new FileIO();
        String values[] = fio.readFile(fileName);

        Admin admins[] = new Admin[100];

        for(int i = 0; i < values.length; i++)
        {
            if(values[i] != null)
            {
                Admin a = new Admin();
                admins[i] = a.formAdmin(values[i]);
            }
        }

        return admins;
    }

    public void write(Admin admins[])
    {
        String information[] = new String[100];

        for(int i = 0; i < admins.length; i++)
        {
            if(admins[i] != null)
            {
                information[i] = admins[i].toStringAdmin();
            }
        }

        String fileName = "Information/admins.txt";

        FileIO fio = new FileIO();
        fio.writeFile(fileName, information);
    }
}