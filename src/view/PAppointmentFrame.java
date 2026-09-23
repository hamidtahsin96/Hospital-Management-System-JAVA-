package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class PAppointmentFrame extends JFrame implements ActionListener
{
    private JLabel titleLabel;

    private JButton cancelBtn;
    private JButton backBtn;

    private JTable appointmentTable;
    private JScrollPane appointmentTableSP;

    private JPanel panel;

    private User u;

    public PAppointmentFrame(User u)
    {
        super("Appointment List Frame");

        this.u = u;

        this.setSize(800, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.titleLabel = new JLabel("My Appointments");

        this.titleLabel.setBounds(330, 40, 150, 30);

        this.panel.add(titleLabel);

        this.showAppointments();

        this.cancelBtn = new JButton("Cancel Appointment");

        this.cancelBtn.setBounds(200, 450, 180, 30);

        this.cancelBtn.addActionListener(this);

        this.panel.add(cancelBtn);

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(420, 450, 100, 30);

        this.backBtn.addActionListener(this);

        this.panel.add(backBtn);

        this.add(panel);
    }

    public void showAppointments()
    {
        AppointmentController ac = new AppointmentController();

        Appointment appointments[] = ac.getAllAppointment();

        String appointmentInfo[][] = new String[appointments.length][5];

        int j = 0;

        for(int i = 0; i < appointments.length; i++)
        {
            if(appointments[i] != null)
            {
                if(appointments[i].getPatient() != null)
                {
                    if(appointments[i].getPatient().getUserId().equals(this.u.getUserId()))
                    {
                        appointmentInfo[j][0] = appointments[i].getAppointmentId();

                        appointmentInfo[j][1] = appointments[i].getDoctor().getUserId();

                        appointmentInfo[j][2] = appointments[i].getDate();

                        appointmentInfo[j][3] = appointments[i].getTime();

                        appointmentInfo[j][4] = appointments[i].getStatus();

                        j++;
                    }
                }
            }
        }

        String head[] =
                {
                        "Appointment ID",
                        "Doctor ID",
                        "Date",
                        "Time",
                        "Status"
                };

        this.appointmentTable = new JTable(appointmentInfo, head);

        this.appointmentTableSP = new JScrollPane(appointmentTable);

        this.appointmentTableSP.setBounds(50, 100, 700, 300);

        this.appointmentTable.setEnabled(false);

        this.panel.add(appointmentTableSP);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(cancelBtn.getText()))
        {
            String appointmentIdValue = JOptionPane.showInputDialog(this, "Enter Appointment ID:");

            if(appointmentIdValue != null && !appointmentIdValue.isEmpty())
            {
                AppointmentController ac = new AppointmentController();

                Appointment appointment = ac.searchAppointment(appointmentIdValue);

                if(appointment != null)
                {
                    if(appointment.getPatient() != null)
                    {
                        if(appointment.getPatient().getUserId().equals(this.u.getUserId()))
                        {
                            appointment.setStatus("Cancelled");

                            ac.updateAppointment(appointment);

                            JOptionPane.showMessageDialog(this, "Appointment Cancelled");

                            this.removeAppointmentTable();

                            this.showAppointments();

                            this.panel.revalidate();
                            this.panel.repaint();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "This Appointment Does Not Belong To You");
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Appointment Not Found");
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

    public void removeAppointmentTable()
    {
        this.panel.remove(this.appointmentTableSP
        );
    }
}