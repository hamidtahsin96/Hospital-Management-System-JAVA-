package view;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import controller.*;

public class PPaymentFrame extends JFrame implements ActionListener
{
    private JLabel titleLabel;

    private JButton payBtn;
    private JButton backBtn;

    private JTable paymentTable;
    private JScrollPane paymentTableSP;

    private JPanel panel;

    private User u;

    public PPaymentFrame(User u)
    {
        super("Payment Frame");

        this.u = u;

        this.setSize(750, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.titleLabel = new JLabel("Payment");

        this.titleLabel.setBounds(330, 40, 100, 30);

        this.panel.add(titleLabel);

        this.showPayments();

        this.payBtn = new JButton("Pay Doctor");

        this.payBtn.setBounds(230, 450, 120, 30);

        this.payBtn.addActionListener(this);

        this.panel.add(payBtn);

        this.backBtn = new JButton("Back");

        this.backBtn.setBounds(380, 450, 100, 30);

        this.backBtn.addActionListener(this);

        this.panel.add(backBtn);

        this.add(panel);
    }

    public void showPayments()
    {
        PaymentController pc = new PaymentController();

        Payment payments[] = pc.getAllPayment();

        String paymentInfo[][] = new String[payments.length][3];

        int j = 0;

        for(int i = 0; i < payments.length; i++)
        {
            if(payments[i] != null)
            {
                if(payments[i].getPatient() != null)
                {
                    if(payments[i].getPatient().getUserId().equals(this.u.getUserId()))
                    {
                        paymentInfo[j][0] = payments[i].getPaymentId();

                        paymentInfo[j][1] = payments[i].getDoctor().getUserId();

                        paymentInfo[j][2] = String.valueOf(payments[i].getAmount());

                        j++;
                    }
                }
            }
        }

        String head[] =
                {
                        "Payment ID",
                        "Doctor ID",
                        "Amount"
                };

        this.paymentTable = new JTable(paymentInfo, head);

        this.paymentTableSP = new JScrollPane(paymentTable);

        this.paymentTableSP.setBounds(50, 100, 650, 300);

        this.paymentTable.setEnabled(false);

        this.panel.add(paymentTableSP);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals(payBtn.getText()))
        {
            String paymentIdValue = JOptionPane.showInputDialog(this, "Enter Payment ID:");

            if(paymentIdValue != null && !paymentIdValue.isEmpty())
            {
                PaymentController pc = new PaymentController();

                Payment existing = pc.searchPayment(paymentIdValue);

                if(existing != null)
                {
                    JOptionPane.showMessageDialog(this, "Payment ID Already Exists");
                }
                else
                {
                    String doctorIdValue =
                            JOptionPane.showInputDialog(this, "Enter Doctor ID:");

                    if(doctorIdValue != null && !doctorIdValue.isEmpty())
                    {
                        DoctorController dc = new DoctorController();

                        Doctor doctor = dc.searchDoctor(doctorIdValue);

                        if(doctor != null)
                        {
                            String amountValue = JOptionPane.showInputDialog(this, "Enter Amount:");

                            if(amountValue != null && !amountValue.isEmpty())
                            {
                                try
                                {
                                    double amount = Double.parseDouble(amountValue);

                                    PatientController pc1 = new PatientController();

                                    Patient patient = pc1.searchPatient(this.u.getUserId());

                                    if(patient != null)
                                    {
                                        Payment payment = new Payment(
                                                        paymentIdValue,
                                                        doctor,
                                                        patient,
                                                        amount
                                                );

                                        pc.insertPayment(payment);

                                        JOptionPane.showMessageDialog(this, "Payment Successful");

                                        this.removePaymentTable();

                                        this.showPayments();

                                        this.panel.revalidate();
                                        this.panel.repaint();
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(this, "Patient Not Found");
                                    }
                                }
                                catch(Exception e)
                                {
                                    JOptionPane.showMessageDialog(this, "Please Enter a Valid Amount");
                                }
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "Doctor Not Found");
                        }
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

    public void removePaymentTable()
    {
        this.panel.remove(this.paymentTableSP);
    }
}