
/////Gui design(unimplemented)
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Client01 extends JFrame {

	private JPanel contentPane;
	static private JTextField msg_text;
	static private JTextArea msg_area; 
	static private JTextArea online_text;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client01 frame = new Client01();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client01() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msg_area = new JTextArea();
		msg_area.setBounds(10, 11, 298, 193);
		contentPane.add(msg_area);
		
		online_text = new JTextArea();
		online_text.setBounds(318, 11, 106, 193);
		contentPane.add(online_text);
		
		msg_text = new JTextField();
		msg_text.setBounds(10, 215, 298, 35);
		contentPane.add(msg_text);
		msg_text.setColumns(10);
		
		JButton msg_send = new JButton("SEND");
		msg_send.setBounds(318, 215, 106, 35);
		contentPane.add(msg_send);
	}

}
