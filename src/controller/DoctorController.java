package controller;
import java.lang.*;
import model.*;

public class DoctorController
{
    public void insertDoctor(Doctor d)
    {
        Doctor doctors[] = this.getAllDoctor();

        for(int i = 0; i < doctors.length; i++)
        {
            if(doctors[i] == null)
            {
                doctors[i] = d;
                break;
            }
        }

        this.write(doctors);
    }


    public void updateDoctor(Doctor d)
    {
        Doctor doctors[] = this.getAllDoctor();

        for(int i = 0; i < doctors.length; i++)
        {
            if(doctors[i] != null)
            {
                if(doctors[i].getUserId().equals(d.getUserId()))
                {
                    doctors[i] = d;
                    break;
                }
            }
        }

        this.write(doctors);
    }


    public void deleteDoctor(String userId)
    {
        Doctor doctors[] = this.getAllDoctor();

        for(int i = 0; i < doctors.length; i++)
        {
            if(doctors[i] != null)
            {
                if(doctors[i].getUserId().equals(userId))
                {
                    doctors[i] = null;
                    break;
                }
            }
        }

        this.write(doctors);
    }


    public Doctor searchDoctor(String userId)
    {
        Doctor doctors[] = this.getAllDoctor();

        for(int i = 0; i < doctors.length; i++)
        {
            if(doctors[i] != null)
            {
                if(doctors[i].getUserId().equals(userId))
                {
                    return doctors[i];
                }
            }
        }

        return null;
    }


    public Doctor[] getAllDoctor()
    {
        String fileName = "Information/doctors.txt";

        FileIO fio = new FileIO();
        String values[] = fio.readFile(fileName);

        Doctor doctors[] = new Doctor[100];

        for(int i = 0; i < values.length; i++)
        {
            if(values[i] != null)
            {
                Doctor d = new Doctor();
                doctors[i] = d.formDoctor(values[i]);
            }
        }

        return doctors;
    }


    public void write(Doctor doctors[])
    {
        String information[] = new String[100];

        for(int i = 0; i < doctors.length; i++)
        {
            if(doctors[i] != null)
            {
                information[i] = doctors[i].toStringDoctor();
            }
        }

        String fileName = "Information/doctors.txt";

        FileIO fio = new FileIO();
        fio.writeFile(fileName, information);
    }
}