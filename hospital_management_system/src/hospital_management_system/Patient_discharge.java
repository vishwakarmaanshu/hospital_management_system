package hospital_management_system;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Patient_discharge extends JFrame {

    Patient_discharge(){

        JPanel panel=new JPanel();
        panel.setBounds(5,5,790,390);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        JLabel label=new JLabel("Check-Out");
        label.setBounds(100,20,150,20);
        label.setFont(new Font("Tahoma",Font.BOLD,20));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JLabel label1=new JLabel("Customer-Id");
        label1.setBounds(30,80,150,20);
        label1.setFont(new Font("Tahoma",Font.BOLD,14));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        Choice choice=new Choice();
        choice.setBounds(200,80,150,25);
        panel.add(choice);
        try{
            conn c=new conn();
            ResultSet resultSet=c.statement.executeQuery("select * from patient_info");
            while (resultSet.next()){
                choice.add(resultSet.getString("number"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel label2=new JLabel("Room_No");
        label2.setBounds(30,130,150,20);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        JLabel Rno=new JLabel();
        Rno.setBounds(200,130,150,20);
        Rno.setFont(new Font("Tahoma",Font.BOLD,14));
        Rno.setForeground(Color.WHITE);
        panel.add(Rno);

        JLabel label3=new JLabel("In Time");
        label3.setBounds(30,180,150,20);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JLabel InTime=new JLabel();
        InTime.setBounds(200,180,250,20);
        InTime.setFont(new Font("Tahoma",Font.BOLD,14));
        InTime.setForeground(Color.WHITE);
        panel.add(InTime);

        JLabel label4=new JLabel("Out Time");
        label4.setBounds(30,230,150,20);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        Date date=new Date();

        JLabel OutTime=new JLabel(""+date);
        OutTime.setBounds(200,230,250,20);
        OutTime.setFont(new Font("Tahoma",Font.BOLD,14));
        OutTime.setForeground(Color.WHITE);
        panel.add(OutTime);

        JButton discharge=new JButton("Discharge");
        discharge.setBounds(30,300,120,30);
        discharge.setBackground(Color.BLACK);
        discharge.setForeground(Color.white);
        panel.add(discharge);
        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c=new conn();
                try{
                    c.statement.executeUpdate("delete from patient_info where number ='"+choice.getSelectedItem()+"'");
                    c.statement.executeUpdate("update roon set availability='available' where room_no='"+Rno.getText()+"'");
                    JOptionPane.showMessageDialog(null,"Done");
                    setVisible(false);
                }catch(Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton check=new JButton("Check");
        check.setBounds(170,300,120,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.white);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c=new conn();
                try{
                    ResultSet resultSet=c.statement.executeQuery("select * from patient_info where number = '"+choice.getSelectedItem()+"'");
                    while (resultSet.next()){
                        Rno.setText(resultSet.getString("room_number"));
                        InTime.setText(resultSet.getString("time"));
                    }
                }catch(Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton back=new JButton("Check");
        back.setBounds(300,300,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(800,400);
        setLayout(null);
        setLocation(250,250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Patient_discharge();
    }
}
