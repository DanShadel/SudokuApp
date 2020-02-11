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

public class Window {

	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			Sudoku sudoku = new Sudoku();
			
			
			public void run() {
				try {
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
