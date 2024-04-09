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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class Sender extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	 private static JTextArea textArea;
	 static ServerSocket ss;
	private static Socket con;
    DataInputStream input;
    DataOutputStream output;
    private static JTextArea Msgbox;
    
	/**
	 * Launch the application.sw
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sender frame = new Sender();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			ss=new ServerSocket(8089);
			con= ss.accept();
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
				Msgbox.setText(Msgbox.getText() +"\n" + "Client: "+ string);
			} catch (Exception ev) {
				Msgbox.setText(Msgbox.getText()+"\n " + "Network issue");
				
				try {
					Thread.sleep(3000);
				
				 System.exit(0);
				 
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//ev.printStackTrace();
				
				
				
				
				
			}	 
	}

	}

	/**
	 * Create the frame.
	 */
	public Sender() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 648);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 150, 122));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSenderSide = new JLabel("Sender Side");
		lblSenderSide.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSenderSide.setBounds(481, 67, 107, 33);
		contentPane.add(lblSenderSide);
		
		Msgbox = new JTextArea();
		Msgbox.setText("Server-client chat application");
		Msgbox.setBounds(38, 195, 657, 406);
		contentPane.add(Msgbox);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(53, 64, 352, 46);
		contentPane.add(textField);
		
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
					Msgbox.setText(Msgbox.getText()+"\n"+"Server : "+ textField.getText());
				
					
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
		btnNewButton.setBounds(329, 143, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("CHAT BOX");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(285, 0, 152, 46);
		contentPane.add(lblNewLabel_1);
	}

}
