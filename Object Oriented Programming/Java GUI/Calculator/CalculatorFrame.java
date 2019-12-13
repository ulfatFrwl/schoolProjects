package thecalc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.awt.event.ActionEvent;
import common.TheCalc;

public class CalculatorFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textScreen;
	
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton buttonAC;
	private JButton buttonleftparenth;
	private JButton buttonrightparenth;
	private JButton buttonDivide;
	private JButton buttonX;
	private JButton buttonMinus;
	private JButton buttonPlus;
	private JButton buttonPostfix;
	private JButton button0;
	private JButton buttonEqual;
	private TheCalc EQ;
	private boolean PFpressed;
	private boolean EQpressed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorFrame frame = new CalculatorFrame();
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
	public CalculatorFrame() {
		setTitle("Postfix Calculator");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CalculatorFrame.class.getResource("/resources/calculator_16.png")));
		setResizable(false);
		initComponent();
		createEvents();
		
		
	}

	private void createEvents() 
	{
		buttonAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textScreen.setText(null);
				
			}
		});
		
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "0");
			}
		});
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "1");
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "2");
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "3");
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "4");
			}
		});
		
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "5");
			}
		});
		
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "6");
			}
		});
		
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "7");
			}
		});
		
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "8");
			}
		});
		
		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "9");
			}
		});
		
		buttonDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "/");
			}
		});
		
		buttonMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "-");
			}
		});
		
		buttonX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "*");
			}
		});
		
		buttonPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "+");
			}
		});
		
		buttonleftparenth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + "(");
			}
		});
		
		buttonrightparenth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp = textScreen.getText();
				textScreen.setText(temp + ")");
			}
		});
		
		buttonPostfix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(EQpressed)
				{
					textScreen.setText(EQ.postfix);
				}
				else 
				{
					EQ = new TheCalc(textScreen.getText());
					textScreen.setText(EQ.postfix);
					PFpressed = true;
				}
			}
		});
		
		buttonEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(PFpressed)
				{
					textScreen.setText(EQ.result);
				}
				
				else
				{
					EQ = new TheCalc(textScreen.getText());
					textScreen.setText(EQ.result);
					EQpressed = true;
				}
			}
		});
		
	}

	private void initComponent() 
	{
		PFpressed = false;
		EQpressed = false;
		
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 293, 477);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textScreen = new JTextField();
		textScreen.setText("");
		textScreen.setBackground(new Color(255, 255, 255));
		textScreen.setForeground(new Color(255, 165, 0));
		textScreen.setBorder(null);
		textScreen.setEditable(false);
		textScreen.setDisabledTextColor(SystemColor.windowBorder);
		textScreen.setHorizontalAlignment(SwingConstants.RIGHT);
		textScreen.setFont(new Font("Segoe UI Light", Font.PLAIN, 54));
		textScreen.setColumns(10);
		
		button9 = new JButton("9");
		button9.setForeground(new Color(255, 255, 255));
		button9.setBorder(null);
		button9.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		button9.setBackground(new Color(211, 211, 211));
		
		button8 = new JButton("8");
		button8.setForeground(new Color(255, 255, 255));
		button8.setBorder(null);
		button8.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		button8.setBackground(new Color(211, 211, 211));
		
		button1 = new JButton("1");
		button1.setForeground(new Color(255, 255, 255));
		button1.setBorder(null);
		button1.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		button1.setBackground(new Color(211, 211, 211));
		
		button2 = new JButton("2");
		button2.setForeground(new Color(255, 255, 255));
		button2.setBorder(null);
		button2.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		button2.setBackground(new Color(211, 211, 211));
		
		button3 = new JButton("3");
		button3.setForeground(new Color(255, 255, 255));
		button3.setBorder(null);
		button3.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		button3.setBackground(new Color(211, 211, 211));
		
		button4 = new JButton("4");
		button4.setForeground(new Color(255, 255, 255));
		button4.setBorder(null);
		button4.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		button4.setBackground(new Color(211, 211, 211));
		
		button5 = new JButton("5");
		button5.setForeground(new Color(255, 255, 255));
		button5.setBorder(null);
		button5.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		button5.setBackground(new Color(211, 211, 211));
		
		button6 = new JButton("6");
		button6.setForeground(new Color(255, 255, 255));
		button6.setBorder(null);
		button6.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		button6.setBackground(new Color(211, 211, 211));
		
		button7 = new JButton("7");
		button7.setForeground(new Color(255, 255, 255));
		button7.setBorder(null);
		button7.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		button7.setBackground(new Color(211, 211, 211));
		
		buttonAC = new JButton("AC");
		
		buttonAC.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		buttonAC.setOpaque(false);
		buttonAC.setForeground(new Color(47, 79, 79));
		buttonAC.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		buttonAC.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		buttonAC.setBackground(new Color(255, 255, 255));
		
		buttonleftparenth = new JButton("(");
		buttonleftparenth.setForeground(new Color(0, 0, 0));
		buttonleftparenth.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		buttonleftparenth.setBorder(null);
		buttonleftparenth.setBackground(new Color(220, 220, 220));
		
		buttonrightparenth = new JButton(")");
		buttonrightparenth.setForeground(new Color(0, 0, 0));
		buttonrightparenth.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		buttonrightparenth.setBorder(null);
		buttonrightparenth.setBackground(new Color(220, 220, 220));
		
		buttonDivide = new JButton("/");
		buttonDivide.setForeground(new Color(47, 79, 79));
		buttonDivide.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		buttonDivide.setBorder(null);
		buttonDivide.setBackground(new Color(220, 220, 220));
		
		buttonX = new JButton("x");
		buttonX.setForeground(new Color(47, 79, 79));
		buttonX.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		buttonX.setBorder(null);
		buttonX.setBackground(new Color(220, 220, 220));
		
		buttonMinus = new JButton("-");
		buttonMinus.setForeground(new Color(47, 79, 79));
		buttonMinus.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		buttonMinus.setBorder(null);
		buttonMinus.setBackground(new Color(220, 220, 220));
		
		buttonPlus = new JButton("+");
		buttonPlus.setForeground(new Color(47, 79, 79));
		buttonPlus.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		buttonPlus.setBorder(null);
		buttonPlus.setBackground(new Color(220, 220, 220));
		
		buttonPostfix = new JButton("postfix");
		buttonPostfix.setForeground(new Color(47, 79, 79));
		buttonPostfix.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		buttonPostfix.setBorder(null);
		buttonPostfix.setBackground(new Color(220, 220, 220));
		
		button0 = new JButton("0");
		button0.setForeground(Color.WHITE);
		button0.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		button0.setBorder(null);
		button0.setBackground(new Color(211, 211, 211));
		
		buttonEqual = new JButton("=");
		buttonEqual.setForeground(new Color(47, 79, 79));
		buttonEqual.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		buttonEqual.setBorder(null);
		buttonEqual.setBackground(new Color(220, 220, 220));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textScreen, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button7, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button8, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button4, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button5, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(button9, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(button3, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(button6, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(buttonX, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonMinus, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonPlus, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(buttonAC, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonleftparenth, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonrightparenth, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonDivide, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(button0, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonPostfix, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonEqual, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(textScreen, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonDivide, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonAC, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonleftparenth, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonrightparenth, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button7, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(button8, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(button9, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonX, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button4, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(button5, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(button6, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonMinus, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(button2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(button3, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonPlus, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button0, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonPostfix, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonEqual, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
