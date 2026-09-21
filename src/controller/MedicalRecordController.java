package controller;
import java.lang.*;
import model.*;

public class MedicalRecordController
{
    public void insertMedicalRecord(MedicalRecord mr)
    {
        MedicalRecord medicalRecords[] = this.getAllMedicalRecord();

        for(int i = 0; i < medicalRecords.length; i++)
        {
            if(medicalRecords[i] == null)
            {
                medicalRecords[i] = mr;
                break;
            }
        }

        this.write(medicalRecords);
    }


    public void updateMedicalRecord(MedicalRecord mr)
    {
        MedicalRecord medicalRecords[] = this.getAllMedicalRecord();

        for(int i = 0; i < medicalRecords.length; i++)
        {
            if(medicalRecords[i] != null)
            {
                if(medicalRecords[i].getMedicalRecordId().equals(mr.getMedicalRecordId()))
                {
                    medicalRecords[i] = mr;
                    break;
                }
            }
        }

        this.write(medicalRecords);
    }


    public void deleteMedicalRecord(String medicalRecordId)
    {
        MedicalRecord medicalRecords[] = this.getAllMedicalRecord();

        for(int i = 0; i < medicalRecords.length; i++)
        {
            if(medicalRecords[i] != null)
            {
                if(medicalRecords[i].getMedicalRecordId().equals(medicalRecordId))
                {
                    medicalRecords[i] = null;
                    break;
                }
            }
        }

        this.write(medicalRecords);
    }


    public MedicalRecord searchMedicalRecord(String medicalRecordId)
    {
        MedicalRecord medicalRecords[] = this.getAllMedicalRecord();

        for(int i = 0; i < medicalRecords.length; i++)
        {
            if(medicalRecords[i] != null)
            {
                if(medicalRecords[i].getMedicalRecordId().equals(medicalRecordId))
                {
                    return medicalRecords[i];
                }
            }
        }

        return null;
    }


    public MedicalRecord[] searchMedicalRecordByPatientId(Patient patient)
    {
        MedicalRecord medicalRecords[] = this.getAllMedicalRecord();
        MedicalRecord targetMedicalRecords[] = new MedicalRecord[100];

        for(int i = 0; i < medicalRecords.length; i++)
        {
            if(medicalRecords[i] != null)
            {
                if(medicalRecords[i].getPatient().getUserId().equals(patient.getUserId()))
                {
                    targetMedicalRecords[i] = medicalRecords[i];
                }
            }
        }

        return targetMedicalRecords;
    }


    public MedicalRecord[] getAllMedicalRecord()
    {
        String fileName = "Information/medicalRecords.txt";

        FileIO fio = new FileIO();
        String values[] = fio.readFile(fileName);

        MedicalRecord medicalRecords[] = new MedicalRecord[100];

        for(int i = 0; i < values.length; i++)
        {
            if(values[i] != null)
            {
                MedicalRecord mr = new MedicalRecord();
                medicalRecords[i] = mr.formMedicalRecord(values[i]);
            }
        }

        return medicalRecords;
    }


    public void write(MedicalRecord medicalRecords[])
    {
        String information[] = new String[100];

        for(int i = 0; i < medicalRecords.length; i++)
        {
            if(medicalRecords[i] != null)
            {
                information[i] = medicalRecords[i].toStringMedicalRecord();
            }
        }

        String fileName = "Information/medicalRecords.txt";

        FileIO fio = new FileIO();
        fio.writeFile(fileName, information);
    }
}