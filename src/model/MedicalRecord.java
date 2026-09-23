package model;

public class MedicalRecord
{
    private String medicalRecordId;
    private Doctor doctor;
    private Patient patient;
    private String details;

    public MedicalRecord()
    {

    }

    public MedicalRecord(String medicalRecordId, Doctor doctor, Patient patient, String details)
    {
        this.medicalRecordId = medicalRecordId;
        this.doctor = doctor;
        this.patient = patient;
        this.details = details;
    }

    public void setMedicalRecordId(String medicalRecordId)
    {
        this.medicalRecordId = medicalRecordId;
    }

    public void setDoctor(Doctor doctor)
    {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getMedicalRecordId()
    {
        return this.medicalRecordId;
    }

    public Doctor getDoctor()
    {
        return this.doctor;
    }

    public Patient getPatient()
    {
        return this.patient;
    }

    public String getDetails()
    {
        return this.details;
    }

    public String toStringMedicalRecord()
    {
        String str = this.medicalRecordId + "," +
                this.doctor.getUserId() + "," +
                this.patient.getUserId() + "," +
                this.details + "\n";

        return str;
    }

    public MedicalRecord formMedicalRecord(String str)
    {
        String information[] = str.split(",");

        if(information.length >= 4)
        {
            MedicalRecord mr = new MedicalRecord();

            mr.setMedicalRecordId(information[0]);

            Doctor doctor = new Doctor();
            doctor.setUserId(information[1]);
            mr.setDoctor(doctor);

            Patient patient = new Patient();
            patient.setUserId(information[2]);
            mr.setPatient(patient);

            mr.setDetails(information[3]);

            return mr;
        }
        else
        {
            return null;
        }
    }
}