package controller;
import model.*;

public class ReceptionistController
{
    public void insertReceptionist(Receptionist r)
    {
        Receptionist receptionists[] = this.getAllReceptionist();

        for(int i = 0; i < receptionists.length; i++)
        {
            if(receptionists[i] == null)
            {
                receptionists[i] = r;
                break;
            }
        }

        this.write(receptionists);
    }

    public void updateReceptionist(Receptionist r)
    {
        Receptionist receptionists[] = this.getAllReceptionist();

        for(int i = 0; i < receptionists.length; i++)
        {
            if(receptionists[i] != null)
            {
                if(receptionists[i].getUserId().equals(r.getUserId()))
                {
                    receptionists[i] = r;
                    break;
                }
            }
        }

        this.write(receptionists);
    }

    public void deleteReceptionist(String userId)
    {
        Receptionist receptionists[] = this.getAllReceptionist();

        for(int i = 0; i < receptionists.length; i++)
        {
            if(receptionists[i] != null)
            {
                if(receptionists[i].getUserId().equals(userId))
                {
                    receptionists[i] = null;
                    break;
                }
            }
        }

        this.write(receptionists);
    }

    public Receptionist searchReceptionist(String userId)
    {
        Receptionist receptionists[] = this.getAllReceptionist();

        for(int i = 0; i < receptionists.length; i++)
        {
            if(receptionists[i] != null)
            {
                if(receptionists[i].getUserId().equals(userId))
                {
                    return receptionists[i];
                }
            }
        }

        return null;
    }

    public Receptionist[] getAllReceptionist()
    {
        String fileName = "Information/receptionists.txt";

        FileIO fio = new FileIO();
        String values[] = fio.readFile(fileName);

        Receptionist receptionists[] = new Receptionist[100];

        for(int i = 0; i < values.length; i++)
        {
            if(values[i] != null)
            {
                Receptionist r = new Receptionist();
                receptionists[i] = r.formReceptionist(values[i]);
            }
        }

        return receptionists;
    }

    public void write(Receptionist receptionists[])
    {
        String information[] = new String[100];

        for(int i = 0; i < receptionists.length; i++)
        {
            if(receptionists[i] != null)
            {
                information[i] = receptionists[i].toStringReceptionist();
            }
        }

        String fileName = "Information/receptionists.txt";

        FileIO fio = new FileIO();
        fio.writeFile(fileName, information);
    }
}