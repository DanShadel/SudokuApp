package shadel.dan;
import shadel.dan.Sudoku;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

public class Window {

	private JFrame frame;
	private int difficulty = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			Sudoku sudoku = new Sudoku();
			boolean win = false;
			
			public void run() {
				try {
					
					//Window menu = new Window();
					//menu.frame.setVisible(true);
					
					
					Window window = new Window(sudoku);
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
	public Window() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel MenuScreen = new JLabel("SUDOKU");
		MenuScreen.setFont(new Font("Rod", Font.PLAIN, 45));
		MenuScreen.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(MenuScreen, BorderLayout.NORTH);
		
		Box verticalBox = Box.createVerticalBox();
		frame.getContentPane().add(verticalBox, BorderLayout.CENTER);
		
		Component verticalStrut_1 = Box.createVerticalStrut(80);
		verticalBox.add(verticalStrut_1);
		
		JButton btnEasy = new JButton("Easy");
		btnEasy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				difficulty = 1;
			}
		});
		btnEasy.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnEasy.setPreferredSize(new Dimension(40,30));
		verticalBox.add(btnEasy);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);
		
		JButton btnMedium = new JButton("Medium");
		btnMedium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				difficulty = 2;
			}
		});
		btnMedium.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnMedium.setPreferredSize(new Dimension(40,30));
		verticalBox.add(btnMedium);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_2);
		
		JButton btnHard = new JButton("Hard");
		btnHard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				difficulty = 3;
			}
		});
		btnHard.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnHard.setPreferredSize(new Dimension(40,30));
		verticalBox.add(btnHard);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_3);
		
		JButton btnExpert = new JButton("Expert");
		btnExpert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				difficulty = 4;
			}
		});
		btnExpert.setPreferredSize(new Dimension(40,30));
		btnExpert.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(btnExpert);
		
		
		
	 	
		
	}
	public Window(Sudoku sudoku) {
		initialize(sudoku);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize(Sudoku sudoku) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		JLabel grid[] = new JLabel[81];
     
		int dimensions = 40;
		int offset = 450-(dimensions*9);
		offset /= 2;
		for(int i=0;i<81;i++)
		{
			grid[i] = new JLabel(Integer.toString(sudoku.board[i].num));
			grid[i].setHorizontalAlignment(SwingConstants.CENTER);
			grid[i].setBounds((offset + sudoku.board[i].col * dimensions), (offset + sudoku.board[i].row * dimensions), dimensions, dimensions);
			grid[i].setBorder(border);
			frame.getContentPane().add(grid[i]);
		}
		
		border = BorderFactory.createLineBorder(Color.BLUE, 3);
		
		JLabel table = new JLabel("");
		JLabel horizontal = new JLabel("");
		JLabel vertical = new JLabel("");
		
		//outside border
		table.setBounds(offset, offset,(9*dimensions),(9*dimensions));
		table.setBorder(border);
		
		//columns diving boxes
		horizontal.setBounds(offset,offset+(3*dimensions),(9*dimensions),(3*dimensions));
		horizontal.setBorder(border);
		vertical.setBounds(offset+(3*dimensions),offset,(3*dimensions),(9*dimensions));
		vertical.setBorder(border);
		
		frame.getContentPane().add(table);
		frame.getContentPane().add(vertical);
		frame.getContentPane().add(horizontal);
		
	}
}
