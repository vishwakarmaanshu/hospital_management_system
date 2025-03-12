package hospital_management_system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Employee_info extends JFrame {
    Employee_info(){

        JPanel panel=new JPanel();
        panel.setBounds(5,5,990,590);
        panel.setBackground(new Color(109,164,170));
        panel.setLayout(null);
        add(panel);

        JTable table=new JTable();
        table.setBounds(10,34,980,450);
        table.setBackground(new Color(109,164,170));
        table.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(table);

        try{
            conn c=new conn();
            String q="select * from emp_info";
            ResultSet resultSet=c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel label1=new JLabel("Name");
        label1.setBounds(41,11,70,20);
        label1.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label1);

        JLabel label2=new JLabel("Age");
        label2.setBounds(165,11,70,20);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label2);

        JLabel label3=new JLabel("Phone_no");
        label3.setBounds(350,11,150,20);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label3);

        JLabel label4=new JLabel("Salary");
        label4.setBounds(550,11,150,20);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label4);

        JLabel label5=new JLabel("Email_Id");
        label5.setBounds(720,11,150,20);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label5);

        JLabel label6=new JLabel("Aadhar_no");
        label6.setBounds(830,11,150,20);
        label6.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label6);

        JButton b1=new JButton("Back");
        b1.setBounds(350,500,120,30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        panel.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(1000,600);
        setLayout(null);
        setLocation(250,80);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee_info();
    }
}
