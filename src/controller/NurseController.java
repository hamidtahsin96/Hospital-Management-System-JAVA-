package controller;
import model.*;

public class NurseController
{
    public void insertNurse(Nurse n)
    {
        Nurse nurses[] = this.getAllNurse();

        for(int i = 0; i < nurses.length; i++)
        {
            if(nurses[i] == null)
            {
                nurses[i] = n;
                break;
            }
        }

        this.write(nurses);
    }

    public void updateNurse(Nurse n)
    {
        Nurse nurses[] = this.getAllNurse();

        for(int i = 0; i < nurses.length; i++)
        {
            if(nurses[i] != null)
            {
                if(nurses[i].getUserId().equals(n.getUserId()))
                {
                    nurses[i] = n;
                    break;
                }
            }
        }

        this.write(nurses);
    }

    public void deleteNurse(String userId)
    {
        Nurse nurses[] = this.getAllNurse();

        for(int i = 0; i < nurses.length; i++)
        {
            if(nurses[i] != null)
            {
                if(nurses[i].getUserId().equals(userId))
                {
                    nurses[i] = null;
                    break;
                }
            }
        }

        this.write(nurses);
    }

    public Nurse searchNurse(String userId)
    {
        Nurse nurses[] = this.getAllNurse();

        for(int i = 0; i < nurses.length; i++)
        {
            if(nurses[i] != null)
            {
                if(nurses[i].getUserId().equals(userId))
                {
                    return nurses[i];
                }
            }
        }

        return null;
    }

    public Nurse[] getAllNurse()
    {
        String fileName = "Information/nurses.txt";

        FileIO fio = new FileIO();
        String values[] = fio.readFile(fileName);

        Nurse nurses[] = new Nurse[100];

        for(int i = 0; i < values.length; i++)
        {
            if(values[i] != null)
            {
                Nurse n = new Nurse();
                nurses[i] = n.formNurse(values[i]);
            }
        }

        return nurses;
    }

    public void write(Nurse nurses[])
    {
        String information[] = new String[100];

        for(int i = 0; i < nurses.length; i++)
        {
            if(nurses[i] != null)
            {
                information[i] = nurses[i].toStringNurse();
            }
        }

        String fileName = "Information/nurses.txt";

        FileIO fio = new FileIO();
        fio.writeFile(fileName, information);
    }
}