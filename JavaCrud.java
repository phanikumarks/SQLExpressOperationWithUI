package Tableoperations;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class JavaCrud {

	private JFrame frame;
	private JTextField txtsname;
	private JTextField txtage;
	private JTextField txtgender;
	private JTextField txtsid;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	 
	public void Connect()
	    {
	        try {
	            con = DriverManager.getConnection("jdbc:sqlserver://0.0.0.0;encrypt=false;"
						+ "databaseName=student_database", "admin", "password");
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	 
	    }
	
	 public void table_load()
	    {
	     try
	     {
	    pst = con.prepareStatement("select * from students");
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	}
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	  }
	    }


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1129, 745);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Registration");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(362, 21, 357, 89);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 117, 496, 364);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student_Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(42, 46, 147, 37);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Age");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(42, 125, 147, 37);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Gender");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(42, 219, 147, 37);
		panel.add(lblNewLabel_1_1_1);
		
		txtsname = new JTextField();
		txtsname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtsname.setBounds(223, 49, 208, 37);
		panel.add(txtsname);
		txtsname.setColumns(10);
		
		txtage = new JTextField();
		txtage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtage.setColumns(10);
		txtage.setBounds(223, 125, 208, 37);
		panel.add(txtage);
		
		txtgender = new JTextField();
		txtgender.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtgender.setColumns(10);
		txtgender.setBounds(223, 219, 208, 37);
		panel.add(txtgender);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String student_name, age, gender;
				
				student_name = txtsname.getText();
				age = txtage.getText();
				gender = txtgender.getText();
				
				try {
					pst = con.prepareStatement("insert into students(student_name,age,gender)values(?,?,?)");
					pst.setString(1, student_name);
					pst.setString(2, age);
					pst.setString(3, gender);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!!!!!");
					table_load();
					          
					txtsname.setText("");
					txtage.setText("");
					txtgender.setText("");
					txtsname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(42, 291, 107, 49);
		panel.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(205, 291, 107, 49);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtsname.setText("");
				txtage.setText("");
				txtgender.setText("");
				txtsname.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClear.setBounds(368, 291, 107, 49);
		panel.add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 506, 490, 73);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Student_id");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(26, 26, 147, 37);
		panel_1.add(lblNewLabel_1_2);
		
		txtsid = new JTextField();
		txtsid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {


try {
          
            String student_id = txtsid.getText();
 
                pst = con.prepareStatement("select student_name,age,gender from students where student_id = ?");
                pst.setString(1, student_id);
                ResultSet rs = pst.executeQuery();
 
            if(rs.next()==true)
            {
              
                String student_name = rs.getString(1);
                String age = rs.getString(2);
                String gender = rs.getString(3);
                
                txtsname.setText(student_name);
                txtage.setText(age);
                txtgender.setText(gender);
                
                
            }  
            else
            {
            	txtsname.setText("");
            	txtage.setText("");
            	txtgender.setText("");
                
            }
            
 
 
        }
catch (SQLException ex) {
          
        }
			}
		});
		txtsid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtsid.setColumns(10);
		txtsid.setBounds(232, 26, 208, 37);
		panel_1.add(txtsid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(516, 120, 490, 357);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String student_name, age, gender,sid;
				
				student_name = txtsname.getText();
				age = txtage.getText();
				gender = txtgender.getText();
				sid = txtsid.getText();
				
				try {
					pst = con.prepareStatement("update students set student_name=?, age=?, gender=? where student_id=?");
					pst.setString(1, student_name);
					pst.setString(2, age);
					pst.setString(3, gender);
					pst.setString(4, sid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated!!!!!");
					table_load();
					          
					txtsname.setText("");
					txtage.setText("");
					txtgender.setText("");
					txtsname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBounds(587, 526, 107, 49);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
String sid;
				
			
				sid = txtsid.getText();
				
				try {
					pst = con.prepareStatement("delete from students where student_id=?");
					
					pst.setString(1, sid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record deleted!!!!!");
					table_load();
					          
					txtsname.setText("");
					txtage.setText("");
					txtgender.setText("");
					txtsname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(763, 526, 107, 49);
		frame.getContentPane().add(btnDelete);
	}
}
