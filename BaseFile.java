package firstTest;

public class BaseFile {
	int[][] array = {
			{0,0,0,0,-1,0,0,0,0},
			{0,0,0,0,1,0,00,0,8},
			{0,0,0,0,-1,0,0,0,-1},
			{0,0,0,0,9,-1,1,0,0},
			{0,0,3,0,0,0,0,0,0},
			{0,2,4,0,0,0,0,-1,0},
			{-1,1,0,0,0,-1,0,0,0},
			{0,0,6,-1,0,0,4,0,-1},
			{-1,-1,0,0,-1,3,-1,0,0},
	};
	int startX=0;
	int startY=0;
	int endX=4;
	int endY=4;

	public static void main(String[] args) {
		Pathing one = new Pathing();
		BaseFile test = new BaseFile();
		one.load(test.startX, test.startY, test.endX, test.endY, test.array);
		int[][] path=one.getPath(false);
//		one.print();
//		one.display();
	}

}
