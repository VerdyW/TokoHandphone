import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame implements ActionListener{

	private JTextField txtUser;
	private JPasswordField txtPass;
	private JPanel panelForm = new JPanel();
	private JPanel panelForm2 = new JPanel();
	private JPanel panelButton = new JPanel(new FlowLayout());
	private JPanel panelTitle = new JPanel(new FlowLayout());
	
	private JLabel lblLogin, lblUsername, lblPassword;
	private JButton buttonLogin, buttonRegister;
	private JCheckBox cbShowPass;
	

	public Main() {
		setTitle("Login");
		
		lblLogin = new JLabel("Login");
		panelTitle.add(lblLogin);
		
		panelForm.setLayout(new GridLayout (3,2,0,4));
		panelForm.setBorder(new EmptyBorder(0,20,0,20));
		panelForm2.setLayout(new BorderLayout());
		
		lblUsername = new JLabel("Username: ");
		txtUser = new JTextField();
		panelForm.add(lblUsername);
		panelForm.add(txtUser);
		
		lblPassword = new JLabel("Password: ");
		txtPass = new JPasswordField();
		panelForm.add(lblPassword);
		panelForm.add(txtPass);
		panelForm2.add(panelForm, BorderLayout.CENTER);
		
		cbShowPass = new JCheckBox("Show password");
		cbShowPass.setHorizontalAlignment(JCheckBox.CENTER);
		cbShowPass.addActionListener(this);
		panelForm2.add(cbShowPass, BorderLayout.SOUTH);
	
		
		buttonLogin = new JButton("Login");
		panelButton.add(buttonLogin);
		buttonLogin.addActionListener(this);
		
		add(panelTitle, BorderLayout.NORTH);
		add(panelForm2, BorderLayout.CENTER);
		add(panelButton, BorderLayout.SOUTH);
		
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String []args) {
		new Main();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == cbShowPass) {
			
			if (cbShowPass.isSelected()) {
			txtPass.setEchoChar((char)0);
				
			}
			else {
				txtPass.setEchoChar('*');
			}
		}
	
		if(e.getSource() == buttonLogin) {
			String username = txtUser.getText().toString();
			String password = txtPass.getText().toString();
			
			if (!username.equals("Verdy")) {
				JOptionPane.showMessageDialog(this, "Username salah", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (!password.equals("Wahyudi213")) {
				JOptionPane.showMessageDialog(this, "Password salah", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			JOptionPane.showMessageDialog(this, "Login Berhasil");
			System.exit(0);
			
		}
	
	}
}
