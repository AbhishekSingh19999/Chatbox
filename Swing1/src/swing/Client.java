package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.InterruptedByTimeoutException;
import java.awt.event.ActionEvent;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
   // private static JTextArea textArea;
    
    private static Socket con;
    DataInputStream input;
    DataOutputStream output;
    private static JTextArea Msgbox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws InterruptedByTimeoutException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		        
		try {
			con= new Socket("127.0.0.1", 8089);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true)
		{
			try {
				DataInputStream input = new DataInputStream(con.getInputStream());
				String string=input.readUTF();
				Msgbox.setText(Msgbox.getText() +"\n" + "Server: "+ string);
			} catch (Exception ev) {
				Msgbox.setText(Msgbox.getText()+"\n " + "Network issue");
				
				try {
					Thread.sleep(3000);
				
				
				 System.exit(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				
				
				
				//ev.printStackTrace();
				
				
				
				}	
				
			}	 
	}

	//private static void While(boolean b) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 656);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 150, 122));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Client Side");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(512, 77, 95, 33);
		contentPane.add(lblNewLabel);
		
		Msgbox = new JTextArea();
		Msgbox.setText("server-client chat application");
		Msgbox.setBounds(24, 203, 657, 406);
		contentPane.add(Msgbox);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		textField.setBounds(41, 74, 352, 46);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("SEND");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Write some text first" );
				}
				else
				{
					Msgbox.setText(Msgbox.getText()+"\n"+"Client : "+ textField.getText());
				
					
					try {
						DataOutputStream output = new DataOutputStream(con.getOutputStream());
					    output.writeUTF(textField.getText());
					    output.flush();
					}
					catch(IOException e1) {
						
						Msgbox.setText(Msgbox.getText() +"\n "+" Network issue" );
						try {
							Thread.sleep(3000);
							System.exit(0);
						}catch(InterruptedException e2) {
							e2.printStackTrace();
							}
					}
						
					textField.setText(" ");
					}
					
					
				
				
				
				
				
				}
			 
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(322, 149, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("CHAT BOX");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(273, 10, 152, 46);
		contentPane.add(lblNewLabel_1);
	}
}
