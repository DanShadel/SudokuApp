package shadel.dan;

public class Box {

	int[] counts;
	int id;
	
	Box(int box){
		counts = new int[9];
		id = box;
		int i = 0;
		for(i=0;i<9;i++) {
			counts[i] = 0;
		}
	}
}
