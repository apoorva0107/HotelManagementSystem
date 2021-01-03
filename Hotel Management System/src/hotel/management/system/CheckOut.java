package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class CheckOut extends JFrame implements ActionListener{
    
    JLabel l1, l2, l3;
    Choice c1;
    JTextField t1;
    JButton b1, b2, b3, b4;
    
    CheckOut(){
        
        l1 = new JLabel("Check Out");
        l1.setBounds(100, 20, 100, 30);
        l1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        l1.setForeground(Color.BLUE);
        add(l1);
        
        l2 =  new JLabel("Customer ID");
        l2.setBounds(30, 80, 100, 30);
        add(l2);
        
        c1 = new Choice();
        try{
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                c1.add(rs.getString("number"));
                
            }
        }
        catch(Exception e){
            
        }
        c1.setBounds(150, 80, 120, 35);
        add(c1);
        
        l3 =  new JLabel("Room Number");
        l3.setBounds(30, 130, 100, 30);
        add(l3);
        
        t1 = new JTextField();
        t1.setBounds(150, 130, 150, 30);
        add(t1);
        
        b1 = new JButton("Check Out");
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        b1.setBounds(30, 200, 120, 30);
        add(b1);
        
        b2 = new JButton("Back");
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        b2.setBounds(170, 200, 120, 30);
        add(b2);
        
        getContentPane().setBackground(Color.white);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        b3 = new JButton(i3);
        b3.setBounds(310, 80, 20, 20);
        b3.addActionListener(this);
        add(b3);
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/sixth.jpg"));
        Image i5 = i4.getImage().getScaledInstance(400, 250,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        b4 = new JButton(i4);
        b4.setBounds(350, 0, 400, 250);
        add(b4);
        
        
        setLayout(null);
        setBounds(500, 200, 800, 300);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            String id = c1.getSelectedItem();
            String room = t1.getText();
            String str = "delete from customer where number = '"+id+"' ";
            String str2 = "update room set available = 'Available' where room = '"+room+"' ";
            conn c = new conn();
            try{
                c.s.executeUpdate(str);
                c.s.executeUpdate(str2);
                JOptionPane.showMessageDialog(null, "Checkout Sucessful");
                new Reception().setVisible(true);
                this.setVisible(false);
            }
            catch(Exception e){
                
            }
        }
        else if(ae.getSource() == b2){
            new Reception().setVisible(true);
            this.setVisible(false);
        }
        else if(ae.getSource() == b3){
            conn c = new conn();
            String id = c1.getSelectedItem();
            try{
                ResultSet rs = c.s.executeQuery("select * from customer where number = '"+id+"'");
                while(rs.next()){
                    t1.setText(rs.getString("room"));
                }
            }
            catch(Exception e){
                
            }

        }
    }
    
    public static void main(String[] args){
        new CheckOut().setVisible(true);
    }
    
}
