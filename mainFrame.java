import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JEditorPane;


public class mainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel frame;
	private JTextField gf_settings;
	private JTextField primitive_root_settings;
	private JTextField private_key_settings;
	private JTextField public_key_settings;
	private JTextField random_r;
	private JTextField K_encrypt;
	private JTextField decryption_C1_textField;
	private JTextField decryption_C2_textField;
	private JTextField decryption_1K_textField;
	private JTextField decryption_K_textField;
	private JTextField decryption_character_textField;
	private JLabel gf_error_settings;
	private JLabel root_error_settings;
	private JLabel xA_error_settings;
	private JTextArea message_encrypt;
	private JTextArea c1_c2_encryption;
	private JLabel random_r_error;
	private JLabel decryption_C1_error;
	private JLabel decryption_C2_error;
	private JTextField Full_Decryption_textfield;
	
	ArrayList<Long> arrLong = new ArrayList<>();
	long qGlobal;
	long xaGlobal;
	private JTextField RecoveredKs_Decryption;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
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
	public mainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 650);
		frame = new JPanel();
		frame.setBackground(new Color(102, 51, 0));
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frame);
		frame.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 102, 0));
		panel.setBorder(new TitledBorder(null, "Settings Field", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 167, 549, 143);
		frame.add(panel);
		panel.setLayout(null);
		
		JLabel lblQ = new JLabel("Choose GF():");
		lblQ.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQ.setBounds(6, 19, 128, 17);
		panel.add(lblQ);
		lblQ.setHorizontalAlignment(SwingConstants.TRAILING);
		
		gf_settings = new JTextField();
		gf_settings.setBounds(144, 16, 180, 20);
		panel.add(gf_settings);
		gf_settings.setColumns(10);
		
		JLabel lblPrimitiveRoot = new JLabel("Primitive root:");
		lblPrimitiveRoot.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrimitiveRoot.setBounds(6, 43, 128, 14);
		panel.add(lblPrimitiveRoot);
		lblPrimitiveRoot.setHorizontalAlignment(SwingConstants.TRAILING);
		
		primitive_root_settings = new JTextField();
		primitive_root_settings.setDisabledTextColor(new Color(102, 102, 51));
		primitive_root_settings.setBackground(new Color(204, 204, 0));
		primitive_root_settings.setEnabled(false);
		primitive_root_settings.setEditable(false);
		primitive_root_settings.setBounds(144, 41, 180, 20);
		panel.add(primitive_root_settings);
		primitive_root_settings.setColumns(10);
		
		JLabel lblXa = new JLabel("Your Private Key:");
		lblXa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblXa.setBounds(6, 77, 128, 18);
		panel.add(lblXa);
		lblXa.setHorizontalAlignment(SwingConstants.TRAILING);
		
		private_key_settings = new JTextField();
		private_key_settings.setBounds(144, 79, 180, 20);
		panel.add(private_key_settings);
		private_key_settings.setColumns(10);
		
		JLabel lblYa = new JLabel("Your Public Key:");
		lblYa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYa.setBounds(6, 108, 128, 17);
		panel.add(lblYa);
		lblYa.setHorizontalAlignment(SwingConstants.TRAILING);
		
		public_key_settings = new JTextField();
		public_key_settings.setDisabledTextColor(new Color(102, 102, 51));
		public_key_settings.setBackground(new Color(204, 204, 0));
		public_key_settings.setBounds(144, 109, 180, 20);
		panel.add(public_key_settings);
		public_key_settings.setEnabled(false);
		public_key_settings.setEditable(false);
		public_key_settings.setColumns(10);
		
		JButton validate_button = new JButton("Validate");
		validate_button.setBackground(new Color(204, 153, 102));
		validate_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validateSettings();
			}
		});
		validate_button.setBounds(369, 104, 101, 29);
		panel.add(validate_button);
		
		gf_error_settings = new JLabel("should be a prime number > 255");
		gf_error_settings.setForeground(new Color(204, 0, 0));
		gf_error_settings.setFont(new Font("Tahoma", Font.PLAIN, 10));
		gf_error_settings.setBounds(334, 19, 154, 14);
		panel.add(gf_error_settings);
		gf_error_settings.setVisible(false);
		
		root_error_settings = new JLabel("should be primitive root for GF");
		root_error_settings.setForeground(new Color(204, 0, 0));
		root_error_settings.setFont(new Font("Tahoma", Font.PLAIN, 11));
		root_error_settings.setBounds(334, 44, 154, 17);
		panel.add(root_error_settings);
		root_error_settings.setVisible(false);
		
		xA_error_settings = new JLabel("Error: 1 < key < GF -1");
		xA_error_settings.setForeground(new Color(204, 0, 0));
		xA_error_settings.setFont(new Font("Tahoma", Font.PLAIN, 14));
		xA_error_settings.setBounds(334, 78, 154, 17);
		panel.add(xA_error_settings);
		xA_error_settings.setVisible(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 102, 0));
		panel_1.setBorder(new TitledBorder(null, "Encryption Field", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 317, 549, 283);
		frame.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMessage.setBounds(0, 82, 71, 32);
		panel_1.add(lblMessage);
		lblMessage.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(81, 82, 180, 56);
		panel_1.add(scrollPane);
		scrollPane.setFocusable(false);
		
		message_encrypt = new JTextArea();
		scrollPane.setViewportView(message_encrypt);
		message_encrypt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		message_encrypt.setWrapStyleWord(true);
		message_encrypt.setText((String) null);
		message_encrypt.setLineWrap(true);
		
		JLabel lblRandomK = new JLabel("Random r: ");
		lblRandomK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRandomK.setBounds(-47, 159, 128, 14);
		panel_1.add(lblRandomK);
		lblRandomK.setHorizontalAlignment(SwingConstants.TRAILING);
		
		random_r = new JTextField();
		random_r.setBounds(81, 159, 180, 20);
		panel_1.add(random_r);
		random_r.setColumns(10);
		
		JLabel lblK = new JLabel("K: ");
		lblK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblK.setBounds(284, 85, 69, 14);
		panel_1.add(lblK);
		lblK.setHorizontalAlignment(SwingConstants.TRAILING);
		
		K_encrypt = new JTextField();
		K_encrypt.setDisabledTextColor(new Color(102, 102, 51));
		K_encrypt.setBackground(new Color(204, 204, 0));
		K_encrypt.setBounds(359, 83, 180, 20);
		panel_1.add(K_encrypt);
		K_encrypt.setEditable(false);
		K_encrypt.setEnabled(false);
		K_encrypt.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(359, 113, 180, 85);
		panel_1.add(scrollPane_1);
		scrollPane_1.setFocusable(false);
		
		c1_c2_encryption = new JTextArea();
		scrollPane_1.setViewportView(c1_c2_encryption);
		c1_c2_encryption.setDisabledTextColor(new Color(102, 102, 51));
		c1_c2_encryption.setBackground(new Color(204, 204, 0));
		c1_c2_encryption.setEditable(false);
		c1_c2_encryption.setWrapStyleWord(true);
		c1_c2_encryption.setText((String) null);
		c1_c2_encryption.setLineWrap(true);
		c1_c2_encryption.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblMessage_1 = new JLabel("C1, C2:");
		lblMessage_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMessage_1.setBounds(264, 109, 89, 29);
		panel_1.add(lblMessage_1);
		lblMessage_1.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JButton encrypt_button = new JButton("Encrypt");
		encrypt_button.setBackground(new Color(204, 153, 102));
		encrypt_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateSettings()){
					char[] message = message_encrypt.getText().trim().toCharArray();
					String k = random_r.getText();
					long q = Long.parseLong(gf_settings.getText());
					qGlobal = q;
					long root = Long.parseLong(primitive_root_settings.getText());
					long YA = Long.parseLong(public_key_settings.getText());
					boolean error = false;
					
					if (k.isEmpty()){
						k = String.valueOf(Methods.get_k(q));
						random_r.setText(k);
						random_r_error.setVisible(false);
					}
					else if (isNumeric(k) && Long.parseLong(k) > 0 && Long.parseLong(k) < q){
						random_r_error.setVisible(false);
					}
					else {
						random_r_error.setVisible(true);
						error = true;
						c1_c2_encryption.setText(null);
						K_encrypt.setText(null);
					}
					
					if (!error){
						long K = Methods.powmod(YA, Long.parseLong(k), q);
						K_encrypt.setText(String.valueOf(K));
						
						String pairs = "";
						ArrayList<Long> arrC1C2 = new ArrayList<>();
						for (int i = 0; i < message.length; i++) {
							long C1 = Methods.powmod(root, Long.parseLong(k), q);
							long C2 = (K*((int)message[i]) % q);
							
							arrC1C2.add(C1);
							arrC1C2.add(C2);
							
							pairs += "(" + String.valueOf(C1) + "," + String.valueOf(C2) + ") ";
						}
						arrLong.clear();
						for (int i = 0; i<arrC1C2.size() ;i++) {
							arrLong.add(arrC1C2.get(i));
						}
						c1_c2_encryption.setText(pairs);
					}
					
				} else {
					random_r_error.setText(null);
				}
			}
		});
		encrypt_button.setBounds(253, 215, 101, 34);
		panel_1.add(encrypt_button);
		
		random_r_error = new JLabel("Error: 1 <= r <= GF -1");
		random_r_error.setForeground(new Color(204, 0, 0));
		random_r_error.setFont(new Font("Tahoma", Font.PLAIN, 14));
		random_r_error.setBounds(102, 189, 152, 32);
		panel_1.add(random_r_error);
		
		JLabel lblNewLabel_1 = new JLabel("Encryption");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(222, 20, 143, 39);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("to Encrypt:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 113, 71, 25);
		panel_1.add(lblNewLabel_4);
		random_r_error.setVisible(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 102, 0));
		panel_2.setBorder(new TitledBorder(null, "Decryption Field", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(569, 169, 493, 431);
		frame.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblC = new JLabel("Please enter C1:");
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblC.setBounds(6, 63, 128, 14);
		panel_2.add(lblC);
		lblC.setHorizontalAlignment(SwingConstants.TRAILING);
		
		decryption_C1_textField = new JTextField();
		decryption_C1_textField.setBounds(144, 61, 180, 20);
		panel_2.add(decryption_C1_textField);
		decryption_C1_textField.setColumns(10);
		
		JLabel lblC_1 = new JLabel("Please enter C2: ");
		lblC_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblC_1.setBounds(6, 91, 128, 14);
		panel_2.add(lblC_1);
		lblC_1.setHorizontalAlignment(SwingConstants.TRAILING);
		
		decryption_C2_textField = new JTextField();
		decryption_C2_textField.setBounds(144, 91, 180, 20);
		panel_2.add(decryption_C2_textField);
		decryption_C2_textField.setColumns(10);
		
		decryption_1K_textField = new JTextField();
		decryption_1K_textField.setDisabledTextColor(new Color(102, 102, 51));
		decryption_1K_textField.setBackground(new Color(204, 204, 0));
		decryption_1K_textField.setBounds(144, 167, 180, 20);
		panel_2.add(decryption_1K_textField);
		decryption_1K_textField.setEnabled(false);
		decryption_1K_textField.setEditable(false);
		decryption_1K_textField.setColumns(10);
		
		JLabel lblK_2 = new JLabel("K Inverse:");
		lblK_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblK_2.setBounds(6, 169, 128, 14);
		panel_2.add(lblK_2);
		lblK_2.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel lblK_1 = new JLabel("Recovered K:");
		lblK_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblK_1.setBounds(6, 139, 128, 14);
		panel_2.add(lblK_1);
		lblK_1.setHorizontalAlignment(SwingConstants.TRAILING);
		
		decryption_K_textField = new JTextField();
		decryption_K_textField.setDisabledTextColor(new Color(102, 102, 51));
		decryption_K_textField.setBackground(new Color(204, 204, 0));
		decryption_K_textField.setBounds(144, 137, 180, 20);
		panel_2.add(decryption_K_textField);
		decryption_K_textField.setEnabled(false);
		decryption_K_textField.setEditable(false);
		decryption_K_textField.setColumns(10);
		
		JLabel lblDecryptedCharacter = new JLabel("Character Decryption:");
		lblDecryptedCharacter.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDecryptedCharacter.setBounds(6, 198, 128, 19);
		panel_2.add(lblDecryptedCharacter);
		lblDecryptedCharacter.setHorizontalAlignment(SwingConstants.TRAILING);
		
		decryption_character_textField = new JTextField();
		decryption_character_textField.setDisabledTextColor(new Color(102, 102, 51));
		decryption_character_textField.setBackground(new Color(204, 204, 0));
		decryption_character_textField.setBounds(144, 197, 180, 20);
		panel_2.add(decryption_character_textField);
		decryption_character_textField.setEnabled(false);
		decryption_character_textField.setEditable(false);
		decryption_character_textField.setColumns(10);
		
		Full_Decryption_textfield = new JTextField();
		Full_Decryption_textfield.setEnabled(false);
		Full_Decryption_textfield.setEditable(false);
		Full_Decryption_textfield.setDisabledTextColor(new Color(102, 102, 51));
		Full_Decryption_textfield.setColumns(10);
		Full_Decryption_textfield.setBackground(new Color(204, 204, 0));
		Full_Decryption_textfield.setBounds(144, 383, 180, 20);
		panel_2.add(Full_Decryption_textfield);
		
		
		
		
		
		JButton decrypt_button = new JButton("Decrypt Char");
		decrypt_button.setBackground(new Color(204, 153, 102));
		decrypt_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateSettings()){
					String C1 = decryption_C1_textField.getText();
					String C2 = decryption_C2_textField.getText();
					long q = Long.parseLong(gf_settings.getText());
					long XA = Long.parseLong(private_key_settings.getText());
					
					xaGlobal =XA;
					boolean error = false;
					
					if (C1.isEmpty()){
						decryption_C1_error.setVisible(true);
						error = true;
						decryption_K_textField.setText(null);
						decryption_1K_textField.setText(null);
						decryption_character_textField.setText(null);
					}
					else if (isNumeric(C1) && Long.parseLong(C1) >= 0 && Long.parseLong(C1) < q){
						decryption_C1_error.setVisible(false);
					}
					else {
						decryption_C1_error.setVisible(true);
						error = true;
						decryption_K_textField.setText(null);
						decryption_1K_textField.setText(null);
						decryption_character_textField.setText(null);
					}
					
					if (C2.isEmpty()){
						decryption_C2_error.setVisible(true);
						error = true;
						decryption_K_textField.setText(null);
						decryption_1K_textField.setText(null);
						decryption_character_textField.setText(null);
					}
					else if (isNumeric(C2) && Long.parseLong(C2) >= 0 && Long.parseLong(C2) < q){
						decryption_C2_error.setVisible(false);
					}
					else {
						decryption_C2_error.setVisible(true);
						error = true;
						decryption_K_textField.setText(null);
						decryption_1K_textField.setText(null);
						decryption_character_textField.setText(null);
					}
					
					if (!error){
						long K = Methods.powmod(Long.parseLong(C1), XA, q);
						decryption_K_textField.setText(String.valueOf(K));
						long K1 = Methods.get_K_inv(K, q);
						decryption_1K_textField.setText(String.valueOf(K1));
						char character = (char)((Long.parseLong(C2)*K1) % q);
						decryption_character_textField.setText(String.valueOf(character));
						
						
					}
					
				} else {
					decryption_C1_error.setText(null);
					decryption_C2_error.setText(null);
				}
			} 
		});
		decrypt_button.setBounds(364, 192, 113, 32);
		panel_2.add(decrypt_button);
		
		decryption_C1_error = new JLabel("Error: 0 <= C1 < GF");
		decryption_C1_error.setForeground(new Color(204, 0, 0));
		decryption_C1_error.setFont(new Font("Tahoma", Font.PLAIN, 14));
		decryption_C1_error.setBounds(334, 60, 154, 20);
		panel_2.add(decryption_C1_error);
		decryption_C1_error.setVisible(false);
		
		decryption_C2_error = new JLabel("Error: 0 <= C2 < GF");
		decryption_C2_error.setForeground(new Color(204, 0, 0));
		decryption_C2_error.setFont(new Font("Tahoma", Font.PLAIN, 14));
		decryption_C2_error.setBounds(334, 88, 154, 20);
		panel_2.add(decryption_C2_error);
		
		JLabel lblNewLabel = new JLabel("Decryption");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(183, 10, 113, 41);
		panel_2.add(lblNewLabel);
		
		/*
		Full_Decryption_textfield = new JTextField();
		Full_Decryption_textfield.setEnabled(false);
		Full_Decryption_textfield.setEditable(false);
		Full_Decryption_textfield.setDisabledTextColor(new Color(102, 102, 51));
		Full_Decryption_textfield.setColumns(10);
		Full_Decryption_textfield.setBackground(Color.GREEN);
		Full_Decryption_textfield.setBounds(144, 228, 180, 20);
		panel_2.add(Full_Decryption_textfield);
		*/
		
		JLabel lblNewLabel_3 = new JLabel("Full Decryption:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(34, 380, 100, 22);
		panel_2.add(lblNewLabel_3);
		
		JTextArea textCpairs = new JTextArea();
		textCpairs.setLineWrap(true);
		textCpairs.setBounds(144, 244, 180, 71);
		panel_2.add(textCpairs);
			
		JLabel lblccPairs = new JLabel("(C1,C2) Pairs:");
		lblccPairs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblccPairs.setBounds(34, 273, 100, 20);
		panel_2.add(lblccPairs);
		
		JButton btnDecryptAll_1 = new JButton("Decrypt All");
		btnDecryptAll_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xaGlobal = Long.parseLong(private_key_settings.getText());
				String fullDecr = "";
				String full_recovered_Ks = "";
				String s = textCpairs.getText();
				String[] arrWithoutSpaces = s.split(" ");
				for (int i=0 ; i<arrWithoutSpaces.length; i++ ) {
					String c = arrWithoutSpaces[i];
					String onlycommas = c.substring(1, c.length()-1);
					String[] c1andc2 = onlycommas.split(",");
					
					long kiki = Methods.powmod( Long.parseLong(c1andc2[0]), xaGlobal, qGlobal);
					if (i==0) {full_recovered_Ks += String.valueOf(kiki);}
					long k1kiki = Methods.get_K_inv(kiki, qGlobal);
					char chichi = (char)((Long.parseLong(c1andc2[1])*k1kiki)%qGlobal);
					fullDecr = fullDecr + chichi; 
				}
				
				Full_Decryption_textfield.setText(fullDecr);
				RecoveredKs_Decryption.setText(full_recovered_Ks);
			}
		});
		btnDecryptAll_1.setBackground(new Color(204, 153, 102));
		btnDecryptAll_1.setBounds(364, 377, 106, 32);
		panel_2.add(btnDecryptAll_1);
		
		RecoveredKs_Decryption = new JTextField();
		RecoveredKs_Decryption.setEnabled(false);
		RecoveredKs_Decryption.setEditable(false);
		RecoveredKs_Decryption.setDisabledTextColor(new Color(102, 102, 51));
		RecoveredKs_Decryption.setColumns(10);
		RecoveredKs_Decryption.setBackground(new Color(204, 204, 0));
		RecoveredKs_Decryption.setBounds(144, 352, 180, 20);
		panel_2.add(RecoveredKs_Decryption);
		
		JLabel lblNewLabel_7 = new JLabel("Recovered K:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(34, 349, 100, 20);
		panel_2.add(lblNewLabel_7);
		
		
		JLabel lblNewLabel_2 = new JLabel("EL GAMAL");
		lblNewLabel_2.setForeground(new Color(204, 153, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 80));
		lblNewLabel_2.setBounds(64, 58, 440, 82);
		frame.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("A project by: \r\nMohammad Serhal, \r\nYoussef El Sayed,");
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(569, 39, 426, 90);
		frame.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Hoda Al Labban, Karim Zarzour");
		lblNewLabel_6.setForeground(new Color(0, 0, 0));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setBounds(688, 106, 283, 39);
		frame.add(lblNewLabel_6);
		decryption_C2_error.setVisible(false);
	}
	
	boolean validateSettings(){
		String q = gf_settings.getText().trim();
		String root = null;
		String XA = private_key_settings.getText().trim();
		boolean error = false;
		
		if (q.isEmpty()){
			q = String.valueOf(Methods.get_q());
			gf_settings.setText(q);
			gf_error_settings.setVisible(false);
			root = String.valueOf(Methods.getPrimitiveRootFor_q(Long.parseLong(q)));
			primitive_root_settings.setText(root);
		}
		else if (isNumeric(q) && Methods.checkPrime(Long.parseLong(q)) && Long.parseLong(q) > 255){
			gf_error_settings.setVisible(false);
			root = String.valueOf(Methods.getPrimitiveRootFor_q(Long.parseLong(q)));
			primitive_root_settings.setText(root);
		}
		else {
			gf_error_settings.setVisible(true);
			primitive_root_settings.setText(null);
			xA_error_settings.setVisible(false);
			public_key_settings.setText(null);
			error = true;
		}
		
		if (error)
			xA_error_settings.setVisible(false);
		else if (!error && XA.isEmpty()){
			XA = String.valueOf(Methods.get_X_A(Long.parseLong(q)));
			private_key_settings.setText(XA);
			xA_error_settings.setVisible(false);
			public_key_settings.setText(String.valueOf(Methods.get_Y_A(Long.parseLong(root), Long.parseLong(XA), Long.parseLong(q))));
		}
		else if (!error && isNumeric(XA) && Long.parseLong(XA)>1 && Long.parseLong(XA)<Long.parseLong(q)-1){
			xA_error_settings.setVisible(false);
			public_key_settings.setText(String.valueOf(Methods.get_Y_A(Long.parseLong(root), Long.parseLong(XA), Long.parseLong(q))));
		}
		else {
			xA_error_settings.setVisible(true);
			public_key_settings.setText(null);
		}
		
		
		return !error;
		
	}
	
	public static boolean isNumeric(String str) {
	    if (str == null) {
	        return false;
	    }
	    int sz = str.length();
	    for (int i = 0; i < sz; i++) {
	        if (Character.isDigit(str.charAt(i)) == false) {
	            return false;
	        }
	    }
	    return true;
	}
}