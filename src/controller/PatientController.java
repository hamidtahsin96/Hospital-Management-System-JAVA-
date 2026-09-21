package controller;
import java.lang.*;
import model.*;

public class PatientController
{
    public void insertPatient(Patient p)
    {
        Patient patients[] = this.getAllPatient();

        for(int i = 0; i < patients.length; i++)
        {
            if(patients[i] == null)
            {
                patients[i] = p;
                break;
            }
        }

        this.write(patients);
    }


    public void updatePatient(Patient p)
    {
        Patient patients[] = this.getAllPatient();

        for(int i = 0; i < patients.length; i++)
        {
            if(patients[i] != null)
            {
                if(patients[i].getUserId().equals(p.getUserId()))
                {
                    patients[i] = p;
                    break;
                }
            }
        }

        this.write(patients);
    }


    public void deletePatient(String userId)
    {
        Patient patients[] = this.getAllPatient();

        for(int i = 0; i < patients.length; i++)
        {
            if(patients[i] != null)
            {
                if(patients[i].getUserId().equals(userId))
                {
                    patients[i] = null;
                    break;
                }
            }
        }

        this.write(patients);
    }


    public Patient searchPatient(String userId)
    {
        Patient patients[] = this.getAllPatient();

        for(int i = 0; i < patients.length; i++)
        {
            if(patients[i] != null)
            {
                if(patients[i].getUserId().equals(userId))
                {
                    return patients[i];
                }
            }
        }

        return null;
    }


    public Patient[] getAllPatient()
    {
        String fileName = "Information/patients.txt";

        FileIO fio = new FileIO();
        String values[] = fio.readFile(fileName);

        Patient patients[] = new Patient[100];

        for(int i = 0; i < values.length; i++)
        {
            if(values[i] != null)
            {
                Patient p = new Patient();
                patients[i] = p.formPatient(values[i]);
            }
        }

        return patients;
    }


    public void write(Patient patients[])
    {
        String information[] = new String[100];

        for(int i = 0; i < patients.length; i++)
        {
            if(patients[i] != null)
            {
                information[i] = patients[i].toStringPatient();
            }
        }

        String fileName = "Information/patients.txt";

        FileIO fio = new FileIO();
        fio.writeFile(fileName, information);
    }
}