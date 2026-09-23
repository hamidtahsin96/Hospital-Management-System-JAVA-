package model;

public class Appointment
{
    private String appointmentId;
    private Doctor doctor;
    private Patient patient;
    private String date;
    private String time;
    private String status;

    public Appointment()
    {

    }

    public Appointment(String appointmentId, Doctor doctor, Patient patient,
                       String date, String time, String status)
    {
        this.appointmentId = appointmentId;
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public void setAppointmentId(String appointmentId)
    {
        this.appointmentId = appointmentId;
    }

    public void setDoctor(Doctor doctor)
    {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getAppointmentId()
    {
        return this.appointmentId;
    }

    public Doctor getDoctor()
    {
        return this.doctor;
    }

    public Patient getPatient()
    {
        return this.patient;
    }

    public String getDate()
    {
        return this.date;
    }

    public String getTime()
    {
        return this.time;
    }

    public String getStatus()
    {
        return this.status;
    }

    public String toStringAppointment()
    {
        String str = this.appointmentId + "," +
                this.doctor.getUserId() + "," +
                this.patient.getUserId() + "," +
                this.date + "," +
                this.time + "," +
                this.status + "\n";

        return str;
    }

    public Appointment formAppointment(String str)
    {
        String information[] = str.split(",");

        if(information.length >= 6)
        {
            Appointment ap = new Appointment();

            ap.setAppointmentId(information[0]);

            Doctor doctor = new Doctor();
            doctor.setUserId(information[1]);
            ap.setDoctor(doctor);

            Patient patient = new Patient();
            patient.setUserId(information[2]);
            ap.setPatient(patient);

            ap.setDate(information[3]);
            ap.setTime(information[4]);
            ap.setStatus(information[5]);

            return ap;
        }
        else
        {
            return null;
        }
    }
}