package controller;
import model.*;

public class AppointmentController
{
    public void insertAppointment(Appointment ap)
    {
        Appointment appointments[] = this.getAllAppointment();

        for(int i = 0; i < appointments.length; i++)
        {
            if(appointments[i] == null)
            {
                appointments[i] = ap;
                break;
            }
        }

        this.write(appointments);
    }

    public void updateAppointment(Appointment ap)
    {
        Appointment appointments[] = this.getAllAppointment();

        for(int i = 0; i < appointments.length; i++)
        {
            if(appointments[i] != null)
            {
                if(appointments[i].getAppointmentId().equals(ap.getAppointmentId()))
                {
                    appointments[i] = ap;
                    break;
                }
            }
        }

        this.write(appointments);
    }

    public void deleteAppointment(String appointmentId)
    {
        Appointment appointments[] = this.getAllAppointment();

        for(int i = 0; i < appointments.length; i++)
        {
            if(appointments[i] != null)
            {
                if(appointments[i].getAppointmentId().equals(appointmentId))
                {
                    appointments[i] = null;
                    break;
                }
            }
        }

        this.write(appointments);
    }

    public Appointment searchAppointment(String appointmentId)
    {
        Appointment appointments[] = this.getAllAppointment();

        for(int i = 0; i < appointments.length; i++)
        {
            if(appointments[i] != null)
            {
                if(appointments[i].getAppointmentId().equals(appointmentId))
                {
                    return appointments[i];
                }
            }
        }

        return null;
    }

    public Appointment[] searchAppointmentByDoctorId(Doctor doctor)
    {
        Appointment appointments[] = this.getAllAppointment();
        Appointment targetAppointments[] = new Appointment[100];

        for(int i = 0; i < appointments.length; i++)
        {
            if(appointments[i] != null)
            {
                if(appointments[i].getDoctor().getUserId().equals(doctor.getUserId()))
                {
                    targetAppointments[i] = appointments[i];
                }
            }
        }

        return targetAppointments;
    }

    public Appointment[] searchAppointmentByPatientId(Patient patient)
    {
        Appointment appointments[] = this.getAllAppointment();
        Appointment targetAppointments[] = new Appointment[100];

        for(int i = 0; i < appointments.length; i++)
        {
            if(appointments[i] != null)
            {
                if(appointments[i].getPatient().getUserId().equals(patient.getUserId()))
                {
                    targetAppointments[i] = appointments[i];
                }
            }
        }

        return targetAppointments;
    }

    public Appointment[] getAllAppointment()
    {
        String fileName = "Information/appointments.txt";

        FileIO fio = new FileIO();
        String values[] = fio.readFile(fileName);

        Appointment appointments[] = new Appointment[100];

        for(int i = 0; i < values.length; i++)
        {
            if(values[i] != null)
            {
                Appointment ap = new Appointment();
                appointments[i] = ap.formAppointment(values[i]);
            }
        }

        return appointments;
    }

    public void write(Appointment appointments[])
    {
        String information[] = new String[100];

        for(int i = 0; i < appointments.length; i++)
        {
            if(appointments[i] != null)
            {
                information[i] = appointments[i].toStringAppointment();
            }
        }

        String fileName = "Information/appointments.txt";

        FileIO fio = new FileIO();
        fio.writeFile(fileName, information);
    }
}