package firstTest;

//class for returning an array of coordinates for the path to follow
class Path {
	public int x;
	public int y;
	public int[][][] last;
	public int distance;
	public int extra;
	
	Path(int x, int y, int[][][] last, int extra){
		this.x=x;
		this.y=y;
		this.last=last;
		this.extra=extra;
		this.distance=last.length+extra;
	}
	
	//method for adding items to array
	public static Path[] append(Path[] all,Path next) {
		Path[] z=new Path[all.length+1];
		for(int i=0; i<all.length;i++) {
			z[i]= all[i];
		}
		z[z.length-1]=next;
		return z;
	}
	public static int[][] append2(int[][] all,int[] next) {
		int[][] z=new int[all.length+1][2];
		for(int i=0; i<all.length;i++) {
			z[i]= all[i];
		}
		z[z.length-1]=next;
		return z;
	}
	
	//full movement method
	public Path[] move(int[][] array) {
		boolean up=true;
		boolean down=true;
		boolean left=true;
		boolean right=true;
		int xmax=array[0].length;
		int ymax=array.length;
		int counter=0;
		
		//Prevent from moving out of array or into non open tiles-> can include players
		//set open tiles to 0
		if(x==0) {
			left=false;
		}
		if(y==0) {
			up=false;
		}
		if(x==(xmax-1)) {
			right=false;
		}
		if(y==(ymax-1)) {
			down=false;
		}
		if(up==true) {
			if(array[y-1][x]==-1) {
				up=false;
			}
		}
		if(down==true) {
			if(array[y+1][x]==-1) {
				down=false;
			}
		}
		if(left==true) {
			if(array[y][x-1]==-1) {
				left=false;
			}
		}
		if(right==true) {
			if(array[y][x+1]==-1) {
				right=false;
			}
		}
		
		//Begin the hard part:
		//check all previously visited tiles to prevent from backtracking
//		System.out.println(up+" "+down+" "+left+" "+right);
		if(up==true) {
			for(int i=0; i<last.length;i++) {
//				System.out.println("X:"+x+" "+last[i][0][0]+" y:"+y+" "+last[i][1][0]);
				if(x==last[i][0][0] && (y-1)==last[i][1][0]) {
					up=false;
				}
			}
		}
		if(right==true) {
			for(int i=0; i<last.length;i++) {
//				System.out.println("X:"+x+" "+last[i][0][0]+" y:"+y+" "+last[i][1][0]);
				if((x+1)==last[i][0][0] && (y)==last[i][1][0]) {
					right=false;
				}
			}
		}
		if(down==true) {
			for(int i=0; i<last.length;i++) {
//				System.out.println("X:"+x+" "+last[i][0][0]+" y:"+y+" "+last[i][1][0]);
				if((x)==last[i][0][0] && (y+1)==last[i][1][0]) {
					down=false;
				}
			}
		}
		if(left==true) {
			for(int i=0; i<last.length;i++) {
//				System.out.println("X:"+x+" "+last[i][0][0]+" y:"+y+" "+last[i][1][0]);
				if((x-1)==last[i][0][0] && (y)==last[i][1][0]) {
					left=false;
				}
			}
		}
		//check to see if the new tile has already been checked
		if(up==true) {
			for(int i=0;i<Pathing.allArrays.length;i++) {
				if(x==Pathing.allArrays[i][0] && (y-1)==Pathing.allArrays[i][1]) {
					up=false;
				}
			}
		}
		if(down==true) {
			for(int i=0;i<Pathing.allArrays.length;i++) {
				if(x==Pathing.allArrays[i][0] && (y+1)==Pathing.allArrays[i][1]) {
					down=false;
				}
			}
		}
		if(left==true) {
			for(int i=0;i<Pathing.allArrays.length;i++) {
				if((x-1)==Pathing.allArrays[i][0] && (y)==Pathing.allArrays[i][1]) {
					left=false;
				}
			}
		}
		if(right==true) {
			for(int i=0;i<Pathing.allArrays.length;i++) {
				if((x+1)==Pathing.allArrays[i][0] && (y)==Pathing.allArrays[i][1]) {
					right=false;
				}
			}
		}
		
//		System.out.println("UP:"+up+" DOWN:"+down+" LEFT:"+left+" RIGHT:"+right);
		
		//check to see if there are multiple paths or only one
		if(up==true) {
			counter+=1;
		}
		if(down==true) {
			counter+=1;
		}
		if(left==true) {
			counter+=1;
		}
		if(right==true) {
			counter+=1;
		}
		//For multiple paths:
		if(counter>0) {
			int[][][] replace1=null;
			int[][][] replace2=null;
			int[][][] replace3=null;
			int[][][] replace4=null;
			int x1=0;
			int x2=0;
			int x3=0;
			int x4=0;
			int y1=0;
			int y2=0;
			int y3=0;
			int y4=0;
			int extra1=0;
			int extra2=0;
			int extra3=0;
			int extra4=0;
			if(up==true) {
				//create new 'last' array for next path by inserting old values and adding 
				//new x and y value 
				replace1 = new int[last.length+1][2][1];
				for(int i=0; i<last.length;i++) {
					replace1[i][0][0]= last[i][0][0];
					replace1[i][1][0]= last[i][1][0];
				}

				replace1[last.length][0][0]=x;
				replace1[last.length][1][0]=y-1;
//				for(int i=0; i<replace1.length;i++) {
//					System.out.println(" "+replace1[i][0][0]+" "+replace1[i][1][0]);
//				}
				x1=x;
				y1=y-1;
				extra1=array[y1][x1];

			}
			if(down==true) {
				replace2 = new int[last.length+1][2][1];
				for(int i=0; i<last.length;i++) {
					replace2[i][0][0]= last[i][0][0];
					replace2[i][1][0]= last[i][1][0];
				}

				replace2[last.length][0][0]=x;
				replace2[last.length][1][0]=y+1;
//				for(int i=0; i<replace2.length;i++) {
//					System.out.println(" "+replace2[i][0][0]+" "+replace2[i][1][0]);
//				}
				x2=x;
				y2=y+1;
				extra2=array[y2][x2];
				
				
			}
			if(left==true) {
				replace3 = new int[last.length+1][2][1];
				for(int i=0; i<last.length;i++) {
					replace3[i][0][0]= last[i][0][0];
					replace3[i][1][0]= last[i][1][0];
				}

				replace3[last.length][0][0]=x-1;
				replace3[last.length][1][0]=y;
//				for(int i=0; i<replace3.length;i++) {
//					System.out.println(" "+replace3[i][0][0]+" "+replace3[i][1][0]);
//				}
				x3=x-1;
				y3=y;
				extra3=array[y3][x3];
				
				
			}
			if(right==true) {
				replace4 = new int[last.length+1][2][1];
				for(int i=0; i<last.length;i++) {
					replace4[i][0][0]= last[i][0][0];
					replace4[i][1][0]= last[i][1][0];
				}

				replace4[last.length][0][0]=x+1;
				replace4[last.length][1][0]=y;
//				for(int i=0; i<replace4.length;i++) {
//					System.out.println(" "+replace4[i][0][0]+" "+replace4[i][1][0]);
//				}
				x4=x+1;
				y4=y;
				extra4=array[y4][x4];
				
				
			}
			
			//create new array of paths to be returned
			Path[] fin= new Path[0];
			
			//add new Paths if they exist then return the array of paths 
			if(replace1!=null) {
				fin=append(fin, new Path(x1,y1,replace1,extra1+extra));
				for(int i=0;i<replace1.length;i++) {
					int[] db=new int[2];
					db[0]=replace1[i][0][0];
					db[1]=replace1[i][1][0];
					Pathing.allArrays=append2(Pathing.allArrays,db);
				}
			}
			
			
			if(replace2!=null) {
				fin=append(fin, new Path(x2,y2,replace2,extra2+extra));
				for(int i=0;i<replace2.length;i++) {
					int[] db=new int[2];
					db[0]=replace2[i][0][0];
					db[1]=replace2[i][1][0];
					Pathing.allArrays=append2(Pathing.allArrays,db);
				}
			}
			if(replace3!=null) {
				fin=append(fin, new Path(x3,y3,replace3,extra3+extra));
				for(int i=0;i<replace3.length;i++) {
					int[] db=new int[2];
					db[0]=replace3[i][0][0];
					db[1]=replace3[i][1][0];
					Pathing.allArrays=append2(Pathing.allArrays,db);
				}
			}
			if(replace4!=null) {
				fin=append(fin,new Path(x4,y4,replace4,extra4+extra));
				for(int i=0;i<replace4.length;i++) {
					int[] db=new int[2];
					db[0]=replace4[i][0][0];
					db[1]=replace4[i][1][0];
					Pathing.allArrays=append2(Pathing.allArrays,db);
				}
			}
			
			return fin;

		}
		//else return null signifying end of path
		return null;
		
	}
	
}


public class Pathing {
	public int startX;
	public int startY;
	public int endX;
	public int endY;
	public static int[][] allArrays = new int[0][0];
	public int[][] array;
	public int[][] retArray;

	public int[][][] beginArray = new int[1][2][1];
	
	public void load(int sX, int sY, int eX, int eY, int[][] ar) {
		startX=sX;
		startY=sY;
		endX=eX;
		endY=eY;
		array=ar;

		beginArray[0][0][0]=startX;
		beginArray[0][1][0]=startY;
	}
	
	public void print() {
		if(retArray!=null) {
			for(int i=0;i<retArray.length;i++) {
				for(int j=0;j<retArray[i].length;j++) {
					System.out.print(retArray[i][j]);
				}
				System.out.println();
			}
		}else {
			System.out.println("Failure: run the getPath method first");
			System.out.println("Failure: no path found");
		}
		
	}
	public void display() {
		if(retArray!=null) {
			int[][] display= new int[array.length][array[0].length];
			for(int i=0;i<display.length;i++) {
				for(int j=0;j<display[i].length;j++) {
					display[i][j]=0;
				}
			}
			for(int i=0;i<retArray.length;i++) {
				display[retArray[i][1]][retArray[i][0]]=1;
			}
			for(int i=0;i<display.length;i++) {
				for(int j=0;j<display[i].length;j++) {
					if(display[i][j]==0) {
						System.out.print(" "+" ");
					}else {
						System.out.print(display[i][j]+" ");
					}
					
				}
				System.out.println();
			}
		}else {
			System.out.println("Failure: run the getPath method first");
			System.out.println("Failure: no path found");
		}

		
	}
	
	public int[][] getPath(boolean outputs) {
		allArrays= new int[0][2];
		Path one = new Path(startX,startY,beginArray,0);
		Path[] first=one.move(array);
		Path[] total = new Path[0];
		if(first==null) {
			if(outputs) {
				System.out.println("No path found");
			}
			
			return null;
		}
		for(int i=0;i<first.length;i++) {
			total=Path.append(total,first[i]);
		}
		Path[] totalN = total;
		int count=0;
		Path winner=null;
		Path[] winners= new Path[0];
		int itter=-1;
		int lowest=0;
		for(int i=0;i<array.length;i++) {
			for(int j=0;j<array[i].length;j++) {
				if(array[i][j]!=-1) {
					lowest+=Math.abs(array[i][j]);
				}
				
			}
		}
		lowest*=array.length*array[0].length;
//		System.out.println(lowest);
		while(count<10) {
			itter+=1;
//			System.out.println(itter+" itter");
			if(winners.length!=0) {
				for(int i=0;i<winners.length;i++) {
					if(lowest>winners[i].distance) {
						lowest=winners[i].distance;
					}
				}
				if(lowest<=itter) {
					count=20;
					if(outputs) {
						System.out.println("No other possible shorter paths");
					}
					
				}
			}
			Path[] totalT=new Path[0];
			for(int i=0;i<totalN.length;i++) {
				Path[] out;
				if(winners.length>0 && totalN[i].distance<=lowest && (totalN[i].distance+Math.abs(totalN[i].x-endX)+Math.abs(totalN[i].y-endY))<=lowest) {
					out=totalN[i].move(array);
				}else if(winners.length==0){
					out=totalN[i].move(array);
				}else {
					out=null;
				}
				
				if(out!=null) {
					for(int j=0;j<out.length;j++) {
						if(out[j].x==endX && out[j].y==endY) {
//							count=20;
							if(outputs) {
								System.out.println("Path found");
							}
							
							winner=out[j];
							if(winner.distance<lowest) {
								lowest=winner.distance;
								winners=Path.append(winners, winner);
							}
//							if(winners.length>0) {
//								System.out.println(winners[0].last.length+" "+winner.distance);
//							}
//							System.out.println("lowest: "+lowest);
							if(winners.length>0 && winner.distance<lowest) {
								winners=Path.append(winners, winner);
							}else if(winners.length==0) {
								winners=Path.append(winners, winner);
							}
							
//							if(winners.length>= 30) {
//								count=20;
//							}
//							System.out.println("dis:"+winners[0].distance+" it: "+itter);
							
							
						}
						totalT=Path.append(totalT,out[j]);
					}
				}
				
			}
			if(totalT.length==0 && winners.length>0) {
				count=20;
				if(outputs) {
					System.out.println("No other paths left to measure");
				}
				
			}else if(totalT.length==0) {
				if(outputs) {
					System.out.println("No path found");
				}
				
				return null;
			}
			totalN=totalT;
			
		}
		
	//return a 2d array of points
	int[][] ans = new int[winner.last.length][2];
	for(int i=0;i<winner.last.length;i++) {
		for(int j=0;j<winner.last[i].length;j++) {
			ans[i][j]=winner.last[i][j][0];
		}
	}
//	System.out.println("Hello");
//	for(int i=0; i<winners.length;i++) {
//		for(int j=0;j<winners[i].last.length;j++) {
//			for(int k=0;k<winners[i].last[j].length;k++) {
//				System.out.println(winners[i].last[j][k][0]);
//			}
//		}
//	}

//	for(int u=0;u<winners.length;u++) {
//		System.out.println("");
//		System.out.println("");
//		System.out.println("");
//		System.out.println("");
//		if(winners[u]!=null) {
//			System.out.println("dis: "+winners[u].distance);
//			int[][] display= new int[array.length][array[0].length];
//			for(int i=0;i<display.length;i++) {
//				for(int j=0;j<display[i].length;j++) {
//					display[i][j]=0;
//				}
//			}
//			for(int i=0;i<winners[u].last.length;i++) {
////				System.out.print(winners[u].last[i][0][0]+"hello ");
////				System.out.println(winners[u].last[i][1][0]+"hello");
//				display[winners[u].last[i][1][0]][winners[u].last[i][0][0]]=1;
//			}
//			for(int i=0;i<display.length;i++) {
//				for(int j=0;j<display[i].length;j++) {
//					if(display[i][j]==0) {
//						System.out.print(" "+" ");
//					}else {
//						System.out.print(display[i][j]+" ");
//					}
//					
//				}
//				System.out.println();
//			}
//		}
//	}
	//This number should be changed to adapt for larger distances
	int dis=100000;
	for(int i=0;i<winners.length;i++) {
		if(dis>winners[i].distance) {
			dis=winners[i].distance;
		}
	}
	if(outputs) {
		System.out.println("distance: "+ dis);
	}
	
	retArray=ans;
	return ans;
	
	
	
	
	}
	
	

}
