package shadel.dan;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

public class Index {

	int num;
	int box;
	int col;
	int row;
	int id;
	int zeros;
	ArrayList<Integer> possible;
	
	Index(int position){//constructor, in loop for(i=0;i<81;i++)
		
		num = 0; //all positions start blank
		id = position; //set positional ID
		box = id / 9; //identify what box the index belongs to
		
		//now we need to find local position offsets
		/*		------------
		 *     | 0	 1	 2	|
		 *     | 3	 4	 5	|
		 *     | 6	 7	 8	|
		 *      ------------
		 */
		
		int temp = id % 9;
		
		
		//update rows and cols based on local position
		switch(temp) {
			case 0:
				row += 0;
				col += 0;
				break;
			case 1:
				row += 0;
				col += 1;
				break;
			case 2:
				row += 0;
				col += 2;
				break;
			case 3:
				row += 1;
				col += 0;
				break;
			case 4:
				row += 1;
				col += 1;
				break;
			case 5:
				row += 1;
				col += 2;
				break;
			case 6:
				row += 2;
				col += 0;
				break;
			case 7:
				row += 2;
				col += 1;
				break;
			case 8:
				row += 2;
				col += 2;
				break;
				
		}
		//finally update rows and cols based on which box the index belongs to
		switch(box) {
			case 0:
				row += 0;
				col += 0;
				break;
			case 1:
				row += 0;
				col += 3;
				break;
			case 2:
				row += 0;
				col += 6;
				break;
			case 3:
				row += 3;
				col += 0;
				break;
			case 4:
				row += 3;
				col += 3;
				break;
			case 5:
				row += 3;
				col += 6;
				break;
			case 6:
				row += 6;
				col += 0;
				break;
			case 7:
				row += 6;
				col += 3;
				break;
			case 8:
				row += 6;
				col += 6;
				break;
			
	
		}//end switch(box)
		
		
	}//end Index(int position)

	//prints what box each index belongs to
	public void printBoxes() {
		System.out.println(id + " is in box " + box + ".    r,c :" + row + ", " + col);
		return;
		
	}
	
	
	//returns an arraylist of possible numbers that can be placed in an index.
	public ArrayList<Integer> getAvailable(Index[] board) {
		
		ArrayList<Integer> available = new ArrayList<Integer>(9);
		int i;
		int start;
		//each index starts with all numbers available
		for(i=1;i<10;i++) {
			
			available.add(i);
		}
		
		//to determine availability, you must check the box, the row, and the column
		
		
		//box
		//check what indexes belong to the box
		start = box*9; // top left index of each box
		for(i=start;i<start+9;i++)
		{
			if (board[i].num == 0)
			{
				
			}
			else if(available.contains(board[i].num)) {
				available.remove((new Integer(board[i].num)));

			}
			
		}
		
		//row
		//indexes in the same row are ordered such as 3,4,5, 12,13,14, 21,22,23
	
		start= ((box/3) * 27) + ((row%3)* 3); 

		for(i=1;i<10;i++) {//still 9 elements
			
			if (board[start].num == 0)
			{
				
			}
			else if(available.contains(board[start].num)) {
				available.remove((new Integer(board[start].num)));

			}
			
			start++;
			if(i%3 ==0) {
				start +=6;
			}
		}
		
		//column
		start = (box%3)*9 + (col%3);//the top row of boxes

		for(i=1;i<10;i++) {
			
			if (board[start].num == 0)
			{
				
			}
			else if(available.contains(board[start].num)) {
				available.remove((new Integer(board[start].num)));
				
			}
			
			start += 3;
			if(i%3 ==0) {
				start +=18;
			}
		}
		//System.out.println("index " + id + " finished. row col "+ row + " " + col);
		return available;
	}
	
	
	public void step2ForcedPlacements(Index[] board, Random rand) {
		
		int[] b0r2 = new int[3];
		int[] b0c2 = new int[3];
		int[] b2r0 = new int[3];
		int[] b6c0 = new int[3];
		int i;
		int j;
		int temp;
		//isolate arrays for opposite rows in corners
		for(i=0;i<3;i++) {
			b0r2[i] = board[i+6].num;
			b0c2[i] = board[((i+1)*3)-1].num;
			b2r0[i] = board[i+18].num;
			b6c0[i] = board[(i*3)+54].num;
		}
		
		//compare for middle section of B1
		for(i=0;i<3;i++) {
			for(j=0;j<3;j++) {
				//number must be placed middle row of B1
				if(b0r2[i] == b2r0[j])
				{
					temp = rand.nextInt(3);
					while(board[temp+12].num != 0)//if that position is taken
					{
						temp = rand.nextInt(3);
					}
					board[temp+12].num=b0r2[i];
						
				}
			}
		}
		
		//middle col of b3
		for(i=0;i<3;i++) {
			for(j=0;j<3;j++) {
				//number must be placed middle row of B1
				if(b0c2[i] == b6c0[j])
				{
					temp = rand.nextInt(3);
					temp *= 3;
					while(board[temp+28].num != 0)//if that position is taken
					{
						temp = rand.nextInt(3);
						temp *= 3;
					}
					board[temp+28].num=b0c2[i];	
				}
			}
		}
	
	}
	
	public int LeastFillable(Index[] board) {
		int[] sizes = new int[9];
		int min=1000;
		int square = 0;
		for(int i = 0;i<9;i++) {
			if(board[i+id].num != 0) {
				sizes[i] = 100;
			}
			else {
				sizes[i] = board[i+id].getAvailable(board).size();
			}
		}
		
		for(int i =0;i<9;i++) {
			if(sizes[i] == 1){
				board[i+id].num=board[i+id].getAvailable(board).get(0);
				board[0].zeros--;
				sizes[i] = 100;
				i = 0;
				continue;
			}
			if(sizes[i]<min) {
				square = i;
				min = sizes[i];
				
				
				
			}
		}
		System.out.println(min + (id+square));
		return square +=id;
	}
}//end class
