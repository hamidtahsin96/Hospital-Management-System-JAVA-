package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class PBookAppointmentFrame extends JFrame implements ActionListener
{
    private JLabel titleLabel;

    private JLabel appointmentIdLabel;
    private JLabel doctorIdLabel;
    private JLabel dateLabel;
    private JLabel timeLabel;

    private JTextField appointmentIdTF;
    private JTextField doctorIdTF;
    private JTextField dateTF;
    private JTextField timeTF;

    private JButton bookBtn;
    private JButton backBtn;

    private JPanel panel;

    private User u;

    public PBookAppointmentFrame(User u)
    {
        super("Book Appointment Frame");

        this.u = u;

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.titleLabel = new JLabel("Book Appointment");

        this.titleLabel.setBounds(320, 40, 160, 30);

        this.panel.add(titleLabel);

        this.appointmentIdLabel = new JLabel("Appointment ID:");

        this.appointmentIdLabel.setBounds(180, 120, 120, 30);

        this.panel.add(appointmentIdLabel);

        this.appointmentIdTF = new JTextField();

        this.appointmentIdTF.setBounds(320, 120, 250, 30);

        this.panel.add(appointmentIdTF);

        this.doctorIdLabel = new JLabel("Doctor ID:");

        this.doctorIdLabel.setBounds(180, 170, 120, 30);

        this.panel.add(doctorIdLabel);

        this.doctorIdTF = new JTextField();

        this.doctorIdTF.setBounds(320, 170, 250, 30);

        this.panel.add(doctorIdTF);

        this.dateLabel = new JLabel("Date:");

        this.dateLabel.setBounds(180, 220, 120, 30);

        this.panel.add(dateLabel);

        this.dateTF = new JTextField();

        this.dateTF.setBounds(320, 220, 250, 30);

        this.panel.add(dateTF);

        this.timeLabel = new JLabel("Time:");

        this.timeLabel.setBounds(180, 270, 120, 30);

        this.panel.add(timeLabel);

        this.timeTF = new JTextField();

        this.timeTF.setBounds(320, 270, 250, 30);

        this.panel.add(timeTF);

        this.bookBtn = new JButton("Book Appointment");

        this.bookBtn.setBounds(220, 350, 180, 30);

        this.bookBtn.addActionListener(this);

        this.panel.add(bookBtn);

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(420, 350, 100, 30);

        this.backBtn.addActionListener(this);

        this.panel.add(backBtn);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(bookBtn.getText()))
        {
            String appointmentIdValue = this.appointmentIdTF.getText();

            String doctorIdValue = this.doctorIdTF.getText();

            String dateValue = this.dateTF.getText();

            String timeValue = this.timeTF.getText();

            if(appointmentIdValue.isEmpty() || doctorIdValue.isEmpty() || dateValue.isEmpty() || timeValue.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please Fill All Fields");
            }
            else
            {
                AppointmentController ac = new AppointmentController();

                Appointment existing = ac.searchAppointment(appointmentIdValue);

                if(existing != null)
                {
                    JOptionPane.showMessageDialog(this, "Appointment ID Already Exists");
                }
                else
                {
                    DoctorController dc = new DoctorController();

                    Doctor doctor = dc.searchDoctor(doctorIdValue);

                    if(doctor != null)
                    {
                        PatientController pc = new PatientController();

                        Patient patient = pc.searchPatient(this.u.getUserId());

                        if(patient != null)
                        {
                            Appointment appointment = new Appointment(
                                            appointmentIdValue,
                                            doctor,
                                            patient,
                                            dateValue,
                                            timeValue,
                                            "Pending"
                                    );

                            ac.insertAppointment(appointment);

                            JOptionPane.showMessageDialog(this, "Appointment Booked Successfully");

                            this.appointmentIdTF.setText("");
                            this.doctorIdTF.setText("");
                            this.dateTF.setText("");
                            this.timeTF.setText("");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "Patient Not Found");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Doctor Not Found");
                    }
                }
            }
        }

        else if(command.equals(backBtn.getText()))
        {
            PatientHomeFrame phf = new PatientHomeFrame(this.u);

            this.setVisible(false);
            phf.setVisible(true);
        }
    }
}