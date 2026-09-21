package model;
import java.lang.*;

public class DoctorPatient
{
    private String doctorPatientId;
    private Doctor doctor;
    private Patient patient;

    public DoctorPatient()
    {

    }

    public DoctorPatient(String doctorPatientId, Doctor doctor, Patient patient)
    {
        this.doctorPatientId = doctorPatientId;
        this.doctor = doctor;
        this.patient = patient;
    }

    public void setDoctorPatientId(String doctorPatientId)
    {
        this.doctorPatientId = doctorPatientId;
    }

    public void setDoctor(Doctor doctor)
    {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public String getDoctorPatientId()
    {
        return this.doctorPatientId;
    }

    public Doctor getDoctor()
    {
        return this.doctor;
    }

    public Patient getPatient()
    {
        return this.patient;
    }

    public String toStringDoctorPatient()
    {
        String str = this.doctorPatientId + "," +
                this.doctor.getUserId() + "," +
                this.patient.getUserId() + "\n";

        return str;
    }

    public DoctorPatient formDoctorPatient(String str)
    {
        String information[] = str.split(",");

        if(information.length >= 3)
        {
            DoctorPatient dp = new DoctorPatient();

            dp.setDoctorPatientId(information[0]);

            Doctor doctor = new Doctor();
            doctor.setUserId(information[1]);
            dp.setDoctor(doctor);

            Patient patient = new Patient();
            patient.setUserId(information[2]);
            dp.setPatient(patient);

            return dp;
        }
        else
        {
            return null;
        }
    }
}