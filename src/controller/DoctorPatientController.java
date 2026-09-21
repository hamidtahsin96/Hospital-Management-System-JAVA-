package controller;
import java.lang.*;
import model.*;

public class DoctorPatientController
{
    public void insertDoctorPatient(DoctorPatient dp)
    {
        DoctorPatient doctorPatients[] = this.getAllDoctorPatient();

        for(int i = 0; i < doctorPatients.length; i++)
        {
            if(doctorPatients[i] == null)
            {
                doctorPatients[i] = dp;
                break;
            }
        }

        this.write(doctorPatients);
    }


    public void updateDoctorPatient(DoctorPatient dp)
    {
        DoctorPatient doctorPatients[] = this.getAllDoctorPatient();

        for(int i = 0; i < doctorPatients.length; i++)
        {
            if(doctorPatients[i] != null)
            {
                if(doctorPatients[i].getDoctorPatientId().equals(dp.getDoctorPatientId()))
                {
                    doctorPatients[i] = dp;
                    break;
                }
            }
        }

        this.write(doctorPatients);
    }


    public void deleteDoctorPatient(String doctorPatientId)
    {
        DoctorPatient doctorPatients[] = this.getAllDoctorPatient();

        for(int i = 0; i < doctorPatients.length; i++)
        {
            if(doctorPatients[i] != null)
            {
                if(doctorPatients[i].getDoctorPatientId().equals(doctorPatientId))
                {
                    doctorPatients[i] = null;
                    break;
                }
            }
        }

        this.write(doctorPatients);
    }


    public DoctorPatient searchDoctorPatient(String doctorPatientId)
    {
        DoctorPatient doctorPatients[] = this.getAllDoctorPatient();

        for(int i = 0; i < doctorPatients.length; i++)
        {
            if(doctorPatients[i] != null)
            {
                if(doctorPatients[i].getDoctorPatientId().equals(doctorPatientId))
                {
                    return doctorPatients[i];
                }
            }
        }

        return null;
    }


    public DoctorPatient[] searchDoctorPatientByDoctorId(Doctor doctor)
    {
        DoctorPatient doctorPatients[] = this.getAllDoctorPatient();
        DoctorPatient targetDoctorPatients[] = new DoctorPatient[100];

        for(int i = 0; i < doctorPatients.length; i++)
        {
            if(doctorPatients[i] != null)
            {
                if(doctorPatients[i].getDoctor().getUserId().equals(doctor.getUserId()))
                {
                    targetDoctorPatients[i] = doctorPatients[i];
                }
            }
        }

        return targetDoctorPatients;
    }


    public DoctorPatient[] searchDoctorPatientByPatientId(Patient patient)
    {
        DoctorPatient doctorPatients[] = this.getAllDoctorPatient();
        DoctorPatient targetDoctorPatients[] = new DoctorPatient[100];

        for(int i = 0; i < doctorPatients.length; i++)
        {
            if(doctorPatients[i] != null)
            {
                if(doctorPatients[i].getPatient().getUserId().equals(patient.getUserId()))
                {
                    targetDoctorPatients[i] = doctorPatients[i];
                }
            }
        }

        return targetDoctorPatients;
    }


    public DoctorPatient[] getAllDoctorPatient()
    {
        String fileName = "Information/doctorPatients.txt";

        FileIO fio = new FileIO();
        String values[] = fio.readFile(fileName);

        DoctorPatient doctorPatients[] = new DoctorPatient[100];

        for(int i = 0; i < values.length; i++)
        {
            if(values[i] != null)
            {
                DoctorPatient dp = new DoctorPatient();
                doctorPatients[i] = dp.formDoctorPatient(values[i]);
            }
        }

        return doctorPatients;
    }


    public void write(DoctorPatient doctorPatients[])
    {
        String information[] = new String[100];

        for(int i = 0; i < doctorPatients.length; i++)
        {
            if(doctorPatients[i] != null)
            {
                information[i] = doctorPatients[i].toStringDoctorPatient();
            }
        }

        String fileName = "Information/doctorPatients.txt";

        FileIO fio = new FileIO();
        fio.writeFile(fileName, information);
    }
}