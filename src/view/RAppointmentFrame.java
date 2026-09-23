package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class RAppointmentFrame extends JFrame implements ActionListener
{
    private JLabel appointmentIdLabel;
    private JLabel doctorIdLabel;
    private JLabel patientIdLabel;
    private JLabel dateLabel;
    private JLabel timeLabel;
    private JLabel statusLabel;

    private JTextField appointmentIdTF;
    private JTextField doctorIdTF;
    private JTextField patientIdTF;
    private JTextField dateTF;
    private JTextField timeTF;
    private JTextField statusTF;

    private JButton bookBtn;
    private JButton searchBtn;
    private JButton updateBtn;
    private JButton cancelBtn;
    private JButton resetBtn;
    private JButton backBtn;

    private JPanel panel;

    private User u;

    public RAppointmentFrame(User u)
    {
        super("Appointment");

        this.u = u;

        this.setSize(650, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.appointmentIdLabel = new JLabel("Appointment ID:");
        this.appointmentIdLabel.setBounds(70, 40, 120, 30);
        this.panel.add(appointmentIdLabel);

        this.appointmentIdTF = new JTextField();
        this.appointmentIdTF.setBounds(200, 40, 220, 30);
        this.panel.add(appointmentIdTF);

        this.doctorIdLabel = new JLabel("Doctor ID:");
        this.doctorIdLabel.setBounds(70, 90, 120, 30);
        this.panel.add(doctorIdLabel);

        this.doctorIdTF = new JTextField();
        this.doctorIdTF.setBounds(200, 90, 220, 30);
        this.panel.add(doctorIdTF);

        this.patientIdLabel = new JLabel("Patient ID:");
        this.patientIdLabel.setBounds(70, 140, 120, 30);
        this.panel.add(patientIdLabel);

        this.patientIdTF = new JTextField();
        this.patientIdTF.setBounds(200, 140, 220, 30);
        this.panel.add(patientIdTF);

        this.dateLabel = new JLabel("Date:");
        this.dateLabel.setBounds(70, 190, 120, 30);
        this.panel.add(dateLabel);

        this.dateTF = new JTextField();
        this.dateTF.setBounds(200, 190, 220, 30);
        this.panel.add(dateTF);

        this.timeLabel = new JLabel("Time:");
        this.timeLabel.setBounds(70, 240, 120, 30);
        this.panel.add(timeLabel);

        this.timeTF = new JTextField();
        this.timeTF.setBounds(200, 240, 220, 30);
        this.panel.add(timeTF);

        this.statusLabel = new JLabel("Status:");
        this.statusLabel.setBounds(70, 290, 120, 30);
        this.panel.add(statusLabel);

        this.statusTF = new JTextField();
        this.statusTF.setBounds(200, 290, 220, 30);
        this.panel.add(statusTF);

        this.bookBtn = new JButton("Book Appointment");
        this.bookBtn.setBounds(60, 360, 150, 30);
        this.bookBtn.addActionListener(this);
        this.panel.add(bookBtn);

        this.searchBtn = new JButton("Search");
        this.searchBtn.setBounds(230, 360, 100, 30);
        this.searchBtn.addActionListener(this);
        this.panel.add(searchBtn);

        this.updateBtn = new JButton("Update");
        this.updateBtn.setBounds(350, 360, 100, 30);
        this.updateBtn.addActionListener(this);
        this.panel.add(updateBtn);

        this.cancelBtn = new JButton("Cancel");
        this.cancelBtn.setBounds(470, 360, 100, 30);
        this.cancelBtn.addActionListener(this);
        this.panel.add(cancelBtn);

        this.resetBtn = new JButton("Reset");
        this.resetBtn.setBounds(180, 430, 100, 30);
        this.resetBtn.addActionListener(this);
        this.panel.add(resetBtn);

        this.backBtn = new JButton("Back");
        this.backBtn.setBounds(320, 430, 100, 30);
        this.backBtn.addActionListener(this);
        this.panel.add(backBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(bookBtn.getText()))
        {
            if(appointmentIdTF.getText().isEmpty() ||
                    doctorIdTF.getText().isEmpty() ||
                    patientIdTF.getText().isEmpty() ||
                    dateTF.getText().isEmpty() ||
                    timeTF.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please Fill All Fields");
            }
            else
            {
                DoctorController dc = new DoctorController();

                PatientController pc = new PatientController();

                Doctor doctor = dc.searchDoctor(doctorIdTF.getText());

                Patient patient = pc.searchPatient(patientIdTF.getText());

                AppointmentController ac = new AppointmentController();

                Appointment oldAppointment = ac.searchAppointment(appointmentIdTF.getText());

                if(doctor == null)
                {
                    JOptionPane.showMessageDialog(this, "Doctor Does Not Exist");
                }
                else if(patient == null)
                {
                    JOptionPane.showMessageDialog(this, "Patient Does Not Exist");
                }
                else if(oldAppointment != null)
                {
                    JOptionPane.showMessageDialog(this, "Appointment ID Already Exists");
                }
                else
                {
                    Appointment appointment = new Appointment(
                                    appointmentIdTF.getText(),
                                    doctor,
                                    patient,
                                    dateTF.getText(),
                                    timeTF.getText(),
                                    "Pending"
                            );

                    ac.insertAppointment(appointment);

                    JOptionPane.showMessageDialog(this, "Appointment Booked Successfully");

                    this.resetFields();
                }
            }
        }

        else if(command.equals(searchBtn.getText()))
        {
            if(appointmentIdTF.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please Enter Appointment ID");
            }
            else
            {
                AppointmentController ac = new AppointmentController();

                Appointment appointment = ac.searchAppointment(appointmentIdTF.getText());

                if(appointment != null)
                {
                    this.doctorIdTF.setText(appointment.getDoctor().getUserId());

                    this.patientIdTF.setText(appointment.getPatient().getUserId());

                    this.dateTF.setText(appointment.getDate());

                    this.timeTF.setText(appointment.getTime());

                    this.statusTF.setText(appointment.getStatus());
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Appointment Does Not Exist");
                }
            }
        }

        else if(command.equals(updateBtn.getText()))
        {
            if(appointmentIdTF.getText().isEmpty() ||
                    doctorIdTF.getText().isEmpty() ||
                    patientIdTF.getText().isEmpty() ||
                    dateTF.getText().isEmpty() ||
                    timeTF.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please Fill All Fields");
            }
            else
            {
                AppointmentController ac = new AppointmentController();

                Appointment appointment = ac.searchAppointment(appointmentIdTF.getText());

                DoctorController dc = new DoctorController();

                PatientController pc = new PatientController();

                Doctor doctor = dc.searchDoctor(doctorIdTF.getText());

                Patient patient = pc.searchPatient(patientIdTF.getText());

                if(appointment == null)
                {
                    JOptionPane.showMessageDialog(this, "Appointment Does Not Exist");
                }
                else if(doctor == null)
                {
                    JOptionPane.showMessageDialog(this, "Doctor Does Not Exist");
                }
                else if(patient == null)
                {
                    JOptionPane.showMessageDialog(this, "Patient Does Not Exist");
                }
                else
                {
                    appointment.setDoctor(doctor);

                    appointment.setPatient(patient);

                    appointment.setDate(dateTF.getText());

                    appointment.setTime(timeTF.getText());

                    ac.updateAppointment(appointment);

                    JOptionPane.showMessageDialog(this, "Appointment Updated Successfully");
                }
            }
        }

        else if(command.equals(cancelBtn.getText()))
        {
            if(appointmentIdTF.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please Enter Appointment ID");
            }
            else
            {
                AppointmentController ac = new AppointmentController();

                Appointment appointment = ac.searchAppointment(appointmentIdTF.getText());

                if(appointment != null)
                {
                    appointment.setStatus("Cancelled");

                    ac.updateAppointment(appointment);

                    JOptionPane.showMessageDialog(this, "Appointment Cancelled Successfully");

                    this.resetFields();
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Appointment Does Not Exist");
                }
            }
        }

        else if(command.equals(resetBtn.getText()))
        {
            this.resetFields();
        }

        else if(command.equals(backBtn.getText()))
        {
            ReceptionistHomeFrame rhf = new ReceptionistHomeFrame(this.u);

            this.setVisible(false);
            rhf.setVisible(true);
        }
    }

    public void resetFields()
    {
        this.appointmentIdTF.setText("");
        this.doctorIdTF.setText("");
        this.patientIdTF.setText("");
        this.dateTF.setText("");
        this.timeTF.setText("");
        this.statusTF.setText("");
    }
}