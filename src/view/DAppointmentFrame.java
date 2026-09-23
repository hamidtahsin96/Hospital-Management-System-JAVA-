package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class DAppointmentFrame extends JFrame implements ActionListener
{
    private JLabel titleLabel;

    private JButton acceptBtn;
    private JButton rescheduleBtn;
    private JButton cancelBtn;
    private JButton backBtn;

    private JTable appointmentTable;
    private JScrollPane appointmentTableSP;

    private JPanel panel;

    private User u;

    public DAppointmentFrame(User u)
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

        this.acceptBtn = new JButton("Accept Appointment");

        this.acceptBtn.setBounds(50, 450, 180, 30);
        this.acceptBtn.addActionListener(this);
        this.panel.add(acceptBtn);

        this.rescheduleBtn = new JButton("Reschedule");

        this.rescheduleBtn.setBounds(250, 450, 150, 30);
        this.rescheduleBtn.addActionListener(this);
        this.panel.add(rescheduleBtn);

        this.cancelBtn = new JButton("Cancel Appointment");

        this.cancelBtn.setBounds(420, 450, 180, 30);
        this.cancelBtn.addActionListener(this);
        this.panel.add(cancelBtn);

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(620, 450, 100, 30);
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
                if(appointments[i].getDoctor() != null)
                {
                    if(appointments[i].getDoctor().getUserId().equals(this.u.getUserId()))
                    {
                        appointmentInfo[j][0] = appointments[i].getAppointmentId();

                        appointmentInfo[j][1] = appointments[i].getPatient().getUserId();

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
                        "Patient ID",
                        "Date",
                        "Time",
                        "Status"
                };

        this.appointmentTable = new JTable(appointmentInfo, head);

        this.appointmentTableSP = new JScrollPane(appointmentTable);

        this.appointmentTableSP.setBounds(50, 100, 700, 300
        );

        this.appointmentTable.setEnabled(false);

        this.panel.add(appointmentTableSP);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(acceptBtn.getText()))
        {
            String appointmentIdValue = JOptionPane.showInputDialog(this, "Enter Appointment ID:");

            if(appointmentIdValue != null && !appointmentIdValue.isEmpty())
            {
                AppointmentController ac = new AppointmentController();

                Appointment ap = ac.searchAppointment(appointmentIdValue);

                if(ap != null)
                {
                    if(ap.getDoctor().getUserId().equals(this.u.getUserId()))
                    {
                        ap.setStatus("Accepted");

                        ac.updateAppointment(ap);

                        JOptionPane.showMessageDialog(this, "Appointment Accepted");

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
                else
                {
                    JOptionPane.showMessageDialog(this, "Appointment Not Found");
                }
            }
        }

        else if(command.equals(rescheduleBtn.getText()))
        {
            String appointmentIdValue = JOptionPane.showInputDialog(this, "Enter Appointment ID:");

            if(appointmentIdValue != null && !appointmentIdValue.isEmpty())
            {
                AppointmentController ac = new AppointmentController();

                Appointment ap = ac.searchAppointment(appointmentIdValue);

                if(ap != null)
                {
                    if(ap.getDoctor().getUserId().equals(this.u.getUserId()))
                    {
                        String dateValue = JOptionPane.showInputDialog(this, "Enter New Date:");

                        String timeValue = JOptionPane.showInputDialog(this, "Enter New Time:");

                        if(dateValue != null && timeValue != null && !dateValue.isEmpty() && !timeValue.isEmpty())
                        {
                            ap.setDate(dateValue);
                            ap.setTime(timeValue);

                            ac.updateAppointment(ap);

                            JOptionPane.showMessageDialog(this, "Appointment Rescheduled");

                            this.removeAppointmentTable();

                            this.showAppointments();

                            this.panel.revalidate();
                            this.panel.repaint();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "This Appointment Does Not Belong To You");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Appointment Not Found");
                }
            }
        }

        else if(command.equals(cancelBtn.getText()))
        {
            String appointmentIdValue = JOptionPane.showInputDialog(this, "Enter Appointment ID:");

            if(appointmentIdValue != null && !appointmentIdValue.isEmpty())
            {
                AppointmentController ac = new AppointmentController();

                Appointment ap = ac.searchAppointment(appointmentIdValue);

                if(ap != null)
                {
                    if(ap.getDoctor().getUserId().equals(this.u.getUserId()))
                    {
                        ap.setStatus("Cancelled");

                        ac.updateAppointment(ap);

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
                else
                {
                    JOptionPane.showMessageDialog(this, "Appointment Not Found");
                }
            }
        }

        else if(command.equals(backBtn.getText()))
        {
            DoctorHomeFrame dhf = new DoctorHomeFrame(this.u);

            this.setVisible(false);
            dhf.setVisible(true);
        }
    }

    public void removeAppointmentTable()
    {
        this.panel.remove(this.appointmentTableSP);
    }
}