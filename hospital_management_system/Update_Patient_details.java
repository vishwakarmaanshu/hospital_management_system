package hospital_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_Patient_details extends JFrame {
    Update_Patient_details(){

        JPanel panel=new JPanel();
        panel.setBounds(5,5,940,490);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image i1=imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1=new ImageIcon(i1);
        JLabel label=new JLabel(imageIcon1);
        label.setBounds(500,60,300,300);
        panel.add(label);

        JLabel label1=new JLabel("Update Patient Details");
        label1.setBounds(124,11,260,25);
        label1.setFont(new Font("Tahoma",Font.BOLD,20));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2=new JLabel("Name :");
        label2.setBounds(25,88,100,14);
        label2.setFont(new Font("Tahoma",Font.PLAIN,14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        Choice choice=new Choice();
        choice.setBounds(248,85,140,25);
        panel.add(choice);

        try{
            conn c=new conn();
            ResultSet resultSet=c.statement.executeQuery("select * from patient_info");
            while (resultSet.next()){
                choice.add(resultSet.getString("Name"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel label3=new JLabel("Room_no :");
        label3.setBounds(25,129,100,14);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JTextField textFieldR=new JTextField();
        textFieldR.setBounds(248,129,140,20);
        panel.add(textFieldR);

        JLabel label4=new JLabel("In-Time :");
        label4.setBounds(25,174,100,14);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JTextField textFieldInTime=new JTextField();
        textFieldInTime.setBounds(248,174,140,20);
        panel.add(textFieldInTime);

        JLabel label5=new JLabel("Amount Paid (Rs) :");
        label5.setBounds(25,216,150,14);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        JTextField textFieldAmount=new JTextField();
        textFieldAmount.setBounds(248,216,140,20);
        panel.add(textFieldAmount);

        JLabel label6=new JLabel("Pending Amount :");
        label6.setBounds(25,261,150,14);
        label6.setFont(new Font("Tahoma",Font.BOLD,14));
        label6.setForeground(Color.WHITE);
        panel.add(label6);

        JTextField textFieldPending=new JTextField();
        textFieldPending.setBounds(248,261,140,20);
        panel.add(textFieldPending);

        JButton b1=new JButton("Check");
        b1.setBounds(281,378,89,23);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        panel.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=choice.getSelectedItem();
                String q="select * from patient_info where name='"+id+"'";
                try{
                    conn c=new conn();
                    ResultSet resultSet=c.statement.executeQuery(q);
                    while (resultSet.next()){
                        textFieldR.setText(resultSet.getString("room_number"));
                        textFieldInTime.setText(resultSet.getString("time"));
                        textFieldAmount.setText(resultSet.getString("deposit"));
                    }
                    ResultSet resultSet1=c.statement.executeQuery("select * from room where room_no='"+textFieldR.getText()+"'");
                    while (resultSet1.next()){
                        String price=resultSet1.getString("price");
                        int amountPaid=Integer.parseInt(price)-Integer.parseInt(textFieldAmount.getText());
                        textFieldPending.setText(""+amountPaid);
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton update=new JButton("Update");
        update.setBounds(56,378,89,23);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        panel.add(update);
       update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    conn c=new conn();
                    String q=choice.getSelectedItem();
                    String room=textFieldR.getText();
                    String time=textFieldInTime.getText();
                    String amount=textFieldAmount.getText();
                    c.statement.executeUpdate("update patient_info set room_number='"+room+"',time='"+time+"',deposit='"+amount+"' where new='"+q+"'");
                    JOptionPane.showMessageDialog(null,"Update Successfully");
                    setVisible(false);
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton back=new JButton("Back");
        back.setBounds(168,378,89,23);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(950,500);
        setLayout(null);
        setLocation(350,180);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Update_Patient_details();
    }
}
