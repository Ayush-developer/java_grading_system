import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.*;
import javax.swing.event.MenuListener;
import java.sql.*;
import java.util.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class firstGUI {
    public static void main(String[] args){
        Abc obj = new Abc();



    }
}



class faculty extends JFrame{

    JLabel t,kyu;
    int marks;

    JTextField facroll,facmarks;

    JButton sub;




    public faculty(){

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400,400);
        t = new JLabel("Enter Roll no");
        facroll = new JTextField(10);
        kyu = new JLabel("Enter Marks");
        facmarks = new JTextField(10);
        sub = new JButton("Submit");


        add(t);
        add(facroll);
        add(kyu);
        add(facmarks);
        add(sub);

        sub.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent es){
                String rolls = facroll.getText();
                String marks = facmarks.getText();
                System.out.println(rolls+marks);


                try{

                    String query = "insert into marks values("+marks+","+rolls+")";
                    System.out.println(query);


                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/demo?useSSL=false&allowPublicKeyRetrieval=true","root","hackerman");
                    Statement st = con.createStatement();
                    int rs = st.executeUpdate(query);
                    System.out.println(rs);




                    st.close();
                    con.close();
                }
                catch (Exception e){
                    System.out.println(e);
                }


            }
        });







        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}








class mainpage extends JFrame{

    JLabel t,kyu;
    int marks;



    public mainpage(String roll){

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400,400);
        t = new JLabel("The roll no is "+roll);





        try{

            String query = "select idmarks from marks where rollno ="+roll;
            System.out.println(query);


            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo?useSSL=false&allowPublicKeyRetrieval=true","root","hackerman");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()){
                marks = rs.getInt(1);
                System.out.println(marks);
            }




            st.close();
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }

        kyu = new JLabel("Your Marks are "+marks);

        add(t);
        add(kyu);





        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class signup extends JFrame {



    JLabel t1,t2,t6,l,x,y;
    JFrame frame,frames;

    JPanel p;
    JTextField t3;
    JPasswordField t7;

    JButton btn,btnsign;
    JMenuBar menuBar;
    JMenu menu, menu2;

    public signup(){



        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400,400);
        setJMenuBar(menuBar);
        frame= new JFrame("JFrame Example");

        t1 = new JLabel("Username");
//        t2 = new JLabel("Password");
        t6 =new JLabel("Password");
        t7 = new JPasswordField(10);
        t3 = new JTextField(10);
//        t5 = new JPasswordField(10);
        btn = new JButton("Submit");
        btnsign = new JButton("Sign Up");
        menuBar = new JMenuBar();
        menu = new JMenu("Login");
        menu2 = new JMenu("Registration");
        menuBar.add(menu);
        menuBar.add(menu2);






        add(menuBar);
        add(t1);
        add(t3);
//        add(t2);

//        add(t5);
        add(t6);
        add(t7);
        add(btn);
        setJMenuBar(menuBar);


        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ep){
                String EmpID = t7.getText();
                String name = t3.getText();
//                String departments = t5.getText();
                int age = 25;
                String departments = "technology";

                try{

                    String query = "insert into demotable values('"+EmpID+"','"+name+"',"+age+",'"+departments+"')";
                    System.out.println(query);


                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/demo?useSSL=false&allowPublicKeyRetrieval=true","root","hackerman");
                    Statement st = con.createStatement();
                    int rs = st.executeUpdate(query);
                    System.out.println(rs+"rows affected");

                    st.close();
                    con.close();
                }
                catch (Exception e){
                    System.out.println(e);
                }
                setVisible(false); //you can't see me!
                dispose();


            }
        });




        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

class Abc extends JFrame implements ActionListener{


    JLabel x,y;

    JTextField use;
    JPasswordField paws;

    JButton login,signs;




    public Abc(){

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400,400);

        x = new JLabel("Username");
        use = new JTextField(10);
        y = new JLabel("Password");
        paws = new JPasswordField(10);
        login = new JButton("Login");
        signs = new JButton("Register");

        add(x);
        add(use);
        add(y);
        add(paws);
        add(login);
        add(signs);



        signs.addActionListener(this);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ep) {
                String EmpID = use.getText();
                String name = paws.getText();

                try{

                    String query = "select * from demotable where EmpID ="+name;
                    System.out.println(query);


                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/demo?useSSL=false&allowPublicKeyRetrieval=true","root","hackerman");
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    if (rs.next()){
                        System.out.println("User Authenticated");
                        setVisible(false); //you can't see me!
                        dispose();
                        int rol= Integer.parseInt(name);
                        if (rol<200){
                            mainpage c = new mainpage(name);
                        }
                        else{
                            faculty fac = new faculty();
                        }

                    }
                    else{
                        System.out.println("No User found");
                    }

                    st.close();
                    con.close();
                }
                catch (Exception e){
                    System.out.println(e);
                }

            }
        });


    }

    public void actionPerformed(ActionEvent ae){


        signup a = new signup();
        a.setLayout(new FlowLayout());
        a.setVisible(true);
        a.setSize(400,400);


        }




}
