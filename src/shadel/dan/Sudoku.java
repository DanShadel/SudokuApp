package shadel.dan;
import java.util.ArrayList;
import java.util.Random;

public class Sudoku {

	public Index board[] = new Index[81];
	public Index playboard[] = new Index[81];
	
	//prints the current board. Will replace with java applet when finished
	static void printBoard(Index[] board) {
		
		int count;
		int row = 0;
		System.out.println();
		System.out.println();
		System.out.println("-------------------------");
		for (int i=0;i<9;i++)
		{
			count = 0;
			System.out.print("| ");
			count += (i*3) + ((row/3)*18);
			for(int j=0;j<9;j++)
			{
				if(board[count].num == 0) {
					System.out.print("  ");
				}
				else {
					System.out.print(board[count].num + " ");
				}
				
				count++;
				if (count%3 == 0) {
					System.out.print("| ");
					count+= 6;
				}

			}
			System.out.print("\n");
			row++;
			if(row%3 == 0) {
				System.out.println("-------------------------");
			}
		}
	
		
	}
	
	//fills box 0, and row/col 0;
	static void step1(Index[] board, Random rand) {
		ArrayList<Integer> temp = new ArrayList<Integer>(9);

		int i;
		int start;
		
		//add 9 numbers to box 0
		for(i = 0; i < 9; i++) {
			
			//get the list of available numbers for that index
			temp = board[i].getAvailable(board);
			board[i].num = temp.get(rand.nextInt(temp.size()));
			
			
		}
		
		//row 0
		start = 9;
		for(i=1;i<7;i++) {
			temp = board[start].getAvailable(board);
			board[start].num = temp.get(rand.nextInt(temp.size()));
			start++;
			if(i%3 == 0) {
				start +=6;
			}
		}
		
		//col 0
		start = 27;
		for(i=1;i<7;i++) {
			temp = board[start].getAvailable(board);
			board[start].num = temp.get(rand.nextInt(temp.size()));
			start += 3;
			if(i%3 == 0) {
				start +=18;
			}
		}

		
	}
	//fills boxes 1,2,3,6
	static void step2(Index[] board, Random rand) {
		ArrayList<Integer> temp = new ArrayList<Integer>(9);

		int i;
		int start = 0;
		
		
		
		//check forced placement indexes
		board[0].step2ForcedPlacements(board, rand);

		
		
		//box 1
		for(i = 12; i < 18; i++) {
			
			if(board[i].num != 0) {
				continue;
			}
			temp = board[i].getAvailable(board);
			if(temp.size() > 0) {
				board[i].num = temp.get(rand.nextInt(temp.size()));
			}
			
		}
		//box 2
		for(i = 21; i < 27; i++) {
			
			
			temp = board[i].getAvailable(board);
			if(temp.size() > 0) {
				board[i].num = temp.get(rand.nextInt(temp.size()));
			}
		}
		
		//box 3
		start = 28;
		for(i=1;i<7;i++)
		{
			if(board[start].num != 0) {
				
			}
			else {
				temp = board[start].getAvailable(board);
				if(temp.size() > 0) {
					board[start].num = temp.get(rand.nextInt(temp.size()));
				}
			}
			start++;
			if (i%2 == 0)
				start++;
		}
		
		
		//box 6
		start = 55;
		for(i=1;i<7;i++)
		{
			temp = board[start].getAvailable(board);
			if(temp.size() > 0) {
				board[start].num = temp.get(rand.nextInt(temp.size()));
			}
			start++;
			if (i%2 == 0)
				start++;
		}
	
	}
		
		
	static void step3(Index[] board, Random rand) {
		ArrayList<Integer> temp = new ArrayList<Integer>(9);
		int i;
		int square;
		for(i=0;i<9;i++) {
			square = board[36].LeastFillable(board);
			temp = board[square].getAvailable(board);
			if (temp.size() != 0) {
				board[square].num = temp.get(rand.nextInt(temp.size()));
			}
		}
	}
	static void step4(Index[] board, Random rand) {
		ArrayList<Integer> temp = new ArrayList<Integer>(9);
		int i;
		int square;
		int newzero=27;
		boolean boxfull;
		int j;
		int counter = 0;
		//27 squares unfilled remaining
		board[0].zeros = 27;
		//place forced placements
		board[45].LeastFillable(board);
		board[63].LeastFillable(board);
		board[72].LeastFillable(board);
		
		printBoard(board);
		System.out.println();
		
		
		while(board[0].zeros > 0)
		{
			counter++;
			if(counter >100) {
				break;
			}
			//box 7
			i=63;
			boxfull=true;
			for(j=0;j<9;j++)
			{
				if (board[i+j].num == 0) {
					boxfull= false;
					i = i+j;
					break;
				}
			}
			if(boxfull == false) {
				temp = board[i].getAvailable(board);
				if (temp.size() != 0) {
					board[i].num = temp.get(rand.nextInt(temp.size()));
					board[0].zeros--;
				}
				while(newzero != board[0].zeros) {
					//place forced placements
					newzero = board[0].zeros;
					board[45].LeastFillable(board);
					board[63].LeastFillable(board);
					board[72].LeastFillable(board);
				}
			}
			
			if(board[0].zeros == 0) {
				break;
			}
			
			
			//box 5
			i=45;
			boxfull=true;
			for(j=0;j<9;j++)
			{
				if (board[i+j].num == 0) {
					boxfull= false;
					i = i+j;
					break;
				}
			}
			if(boxfull == false) {
				temp = board[i].getAvailable(board);
				if (temp.size() != 0) {
					board[i].num = temp.get(rand.nextInt(temp.size()));
					board[0].zeros--;
				}
				while(newzero != board[0].zeros) {
					//place forced placements
					newzero = board[0].zeros;
					board[45].LeastFillable(board);
					board[63].LeastFillable(board);
					board[72].LeastFillable(board);
				}
				
				
				
			}		
			printBoard(board);
		}
		
		System.out.println(board[0].zeros);
	
		
		
		
	}
	
	
	static void step5(Index[] board, Index[] playboard, Random rand, int difficulty) {
		
		int i;
		int temp=-1;
		int index = -1;
		int playing = 0;
		ArrayList<Integer> placed = new ArrayList<Integer>(50);
		ArrayList<Integer> boxes = new ArrayList<Integer>(9);
		
		//populate a list with the unplaced boxes
		for(i=0;i<9;i++) {
			boxes.add(i);
		}
		
		
		//generate a solvable board.
		//start by placing a number in every box except one.
		
		
		//temp is the one box that doesn't get a placement
		temp=rand.nextInt(9);
		for(i=0;i<9;i++) {
			
			if(i==temp) {
				continue;
			}
			
			index = rand.nextInt(9); //select a random local index
			index += i *9; // add box offset
			placed.add(index);
		}
			
			
		//now we need to add all the other starting boxes boxes
		// set the total number of placed boxes per difficulty
		switch(difficulty) {
			case 1://easy
				playing = 50;
				break;
			case 2://medium
				playing = 40;
				break;
				
			case 3://hard
				playing = 30;
				break;
				
			case 4://Expert
				playing = 24;
				break;
		}
		
		//remove the first 8 from the total placed
		playing -= 8;
		
		System.out.println("creating the rest of the placed boxes");
		for(i=0;i<playing;i++) {
			while(placed.contains(index)) {
				index = rand.nextInt(81);
			}
			System.out.println(index);
			placed.add(index);
		}
		
		
		
		
		
		
		
		//copy only the selected indices to the play board
		for(i=0;i<placed.size();i++){
			
			index = placed.get(i);
			playboard[index].num = board[index].num;
			
			
			
		}
	}
	
	static void genBoard(Index[] board, Random rand) {
		
		try {
			step1(board,rand);
			step2(board,rand);
			step3(board,rand);
			step4(board,rand);
			
			
			for(int i=0;i<81;i++)
			{
				if (board[i].num == 0) {
					throw new NullPointerException("Zeroes");
				}
			}
		}
		catch(Exception e) {
			for(int i=0;i<81;i++)
			{
				board[i].num = 0;
			}
			genBoard(board,rand);
		}
	}
	static void makePlayingBoard(Index[] board, Index[] playboard, Random rand, int difficulty){
		
		
	}
	Sudoku(int difficulty) {
		
		//create indexes
		
		int i;
		Random rand = new Random();
		
		
		
		for(i=0;i<81;i++) {
			board[i] = new Index(i);
			playboard[i] = new Index(i);
		}
		
		
		
		// Generate Box 0
		genBoard(board,rand);
		step5(board,playboard,rand,4);
		printBoard(board);
		printBoard(playboard);
		System.out.println("playboard printed?");
		
		
		

		
	}//end main()

}
