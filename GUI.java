package firstTest;
import java.awt.*;

import javax.swing.*;
import java.awt.Color.*;
import java.util.Scanner;
import java.awt.event.*;



//make it so on pathing, the array last includes all points covered since they will already be checked by
//another path
//should reduce the total amount of thinking since replicas will not exist
//
//find way for old path to die off after creating new ones to avoid overflow




class block{
	public int x;
	public int y;
	public int width;
	public int mvt;
	public Color color;
	public String name;
	
	public block(int x, int y, int width, Color color, String name, int mvt){
		this.x=x;
		this.y=y;
		this.width=width;
		this.mvt=mvt;
		this.color=color;
		this.name=name;
	}
}

public class GUI extends JFrame implements MouseListener,KeyListener{
	int rows=20;
	int columns=10;
	int test=0;
	boolean clicked=false;
	boolean operate=true;
	int xDis;
	int yDis;
	int x;
	int y;
	
	String action="none";
	block target;
	block[] array=new block[10];
	block[] player = new block[5];
	block[] enemy = new block[5];
	int[][] graph = new int[columns][rows];
	int[][] pathToDisplay=null;
	boolean display=false;
	
	GUI(){
		setSize(rows*40+15,columns*40+39);
		setTitle("Pathfinding");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		addMouseListener(this);
		addKeyListener(this);
		
		
		for(int i=0;i<graph.length;i++) {
			for(int j=0;j<graph[i].length;j++) {
				graph[i][j]=0;
			}
		}

		
	}
	
	public void keyTyped(KeyEvent e) {
//		System.out.println(e.getKeyChar());
		if(e.getKeyChar()=='1') {
			action="add";
		}else if(e.getKeyChar()=='2') {
			action="sub";
		}else if(e.getKeyChar()==' ') {
			action="none";
			
		}else if(e.getKeyChar()=='t') {
			if(display==true) {
				display=false;
			}else {
				display=true;
			}
		repaint();
		}
	}
	public void keyPressed(KeyEvent e) {
		
	}
	public void keyReleased(KeyEvent e) {
		
	}
	public void mouseClicked(MouseEvent e) {	
	}
	public void mouseEntered(MouseEvent e) {}  
    public void mouseExited(MouseEvent e) {}  
    public void mousePressed(MouseEvent e) {
//    	clicked=true;
//    	System.out.println(action);
    	if(action=="add") {
			int X=((getMousePosition().x-7)/40)*40;
			int Y=((getMousePosition().y-31)/40)*40;
			block im = new block(X+7,Y+31,40,Color.yellow,"One",2);
			array=append(array,im);
			
		}else if(action=="sub") {
			for(int i=0;i<array.length;i++) {
				block block =array[i];
				x=getMousePosition().x;
				y=getMousePosition().y;
				if(block!=null&&block.x<x && (block.x+block.width)>x && block.y<y &&(block.y+block.width)>y) {
					if(block.name!="do") {
						array[i]=null;
					}
					
					
				}
			}
			
		}else if(action=="none") {
			int x=getMousePosition().x;
			int y=getMousePosition().y;
	    	for(block block:array) {
				
				if(block!=null && clicked==false) {
					if(block.x<x && (block.x+block.width)>x && block.y<y &&(block.y+block.width)>y) {
						target=block;
						xDis=x-block.x;
						yDis=y-block.y;
						break;
						
					}
				}
			}
	    	
		}
//    	System.out.println("how");
    	clicked=true;
    }  
    public void mouseReleased(MouseEvent e) {
    	clicked=false;
    	target=null;
    }  
	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, 5000, 5000);
		for(block block: array) {
			if(block!=null) {
				g.setColor(block.color);
				g.fillRect(block.x, block.y, block.width, block.width);
//				System.out.println(block.x+" "+block.y+" "+block.name);
			}
			
		}
		for(int xD=0;xD<rows;) {
			for(int yD=0;yD<columns;) {
				g.setColor(Color.black);
				g.drawRect(xD*40+7, yD*40+31, 40, 40);
				yD+=1;
			}
			xD+=1;
		}
		if(display==true && pathToDisplay!=null) {
//			clicked=false;
			for(int i=1;i<pathToDisplay.length-1;i++) {
				for(int j=0;j<pathToDisplay[i].length;j++) {
					g.setColor(Color.blue);
					g.fillRect(X(pathToDisplay[i][0]), Y(pathToDisplay[i][1]), 40, 40);
				}
			}
		}
		

	}
	public static int X(int x) {
		//input the box location
		return x*40+7;
		
	}
	public static int Y(int y) {
		return y*40+31;
	}
	public static block[] append(block[] blocks, block block) {
		block[] newBlocks = new block[blocks.length+1];
		for(int i=0;i<blocks.length;i++) {
			newBlocks[i]=blocks[i];
		}
		newBlocks[newBlocks.length-1]=block;
		return newBlocks;
	}
	
	public static void main(String[] args) throws InterruptedException {
		GUI a = new GUI();
		
		block oneP = new block(X(2),Y(4),40,Color.green,"do",2);
		block twoP = new block(X(1),Y(5),40,Color.red,"do",2);
//		block threeP = new block(X(2),Y(8),40,Color.blue,"Three",2);
//		block fourP = new block(X(6),Y(2),40,Color.blue,"Four",2);
//		
//		block oneE = new block(X(19),Y(2),40,Color.red,"One",2);
//		block twoE = new block(X(17),Y(4),40,Color.red,"Two",2);
//		block threeE = new block(X(17),Y(6),40,Color.red,"Three",2);
//		block fourE = new block(X(19),Y(8),40,Color.red,"Four",2);
		a.array[0]=oneP;
		a.array[1]=twoP;
//		a.array[2]=threeP;
//		a.array[3]=fourP;
//		a.array[4]=oneE;
//		a.array[5]=twoE;
//		a.array[6]=threeE;
//		a.array[7]=fourE;
//		a.graph[4][2]=1;
		for(int i=0;i<a.graph.length;i++) {
			for(int j=0;j<a.graph[i].length;j++) {
//				System.out.print(a.graph[i][j]);
			}
//			System.out.println();
		}
		
//		a.addMouseListener(null);
//		Thread thing = new Thread();
		
		
		while(a.operate) {
			
//			Thread.sleep(50);
			
//			Scanner sc = new Scanner(System.in);
//			System.out.println("Enter x,y,name");
//			System.out.println(a.getMousePosition());
//			String ans=sc.nextLine();
			String ans="20,20,one";
			String[] ans2=ans.split(",",3);
			for(String sting:ans2) {
//				System.out.println(sting);
			}
			if(a.clicked==true) {
				Pathing pather = new Pathing();
//				System.out.println((array[0].x-7)/40+" "+(array[0].y-31)/40+" "+(array[1].x-7)/40+" "+(array[1].y-31)/40);
				pather.load((a.array[0].x-7)/40,(a.array[0].y-31)/40,(a.array[1].x-7)/40,(a.array[1].y-31)/40,a.graph);
				int[][] out = pather.getPath(false);
//				pather.display();
				a.pathToDisplay=out;
				
//				pather=null;
				a.repaint();
				int x=0;
				int y=0;
				try {
					x=a.getMousePosition().x;
					a.x=x;
				}catch(Exception e) {
					x=a.x;
				}
				try {
					y=a.getMousePosition().y;
					a.y=y;
				}catch(Exception e) {
					y=a.y;
				}
//				x=a.x;
//				y=a.y;
				
				
//				block block=a.array[1];
//				for(block block:a.array) {
//				for(block block:a.array) {
//				System.out.println((x/40)*40+7+ " "+ (y/40)*40+31);
//				System.out.println();
				
				if(true) {
					block block=a.target;
					boolean allow=true;
					if(block!=null) {
//						block.x=x-block.width/2;
//						block.y=y-block.width/2;
						int upperX=(((x-7)/40)*40)+40;
						int lowerX=((x-7)/40)*40;
//						System.out.println("X: "+x+ " L: "+lowerX+ " U: "+ upperX);
						
						int upperY=(((y-31)/40)*40)+40;
						int lowerY=((y-31)/40)*40;
//						System.out.println("Y: "+y+ " L: "+lowerY+ " U: "+ upperY);
						
						for(block block2:a.array) {
							if(block2!=null && block2!=block) {
								if(block2.x==lowerX+7 && block2.y==lowerY+31) {
									allow=false;
								}
							}
						}
						if(allow) {
							block.x=lowerX+7;
							block.y=lowerY+31;
						}
						
//						if(x<upperX && y>lowerX) {
//							System.out.println("true");
//							block.x=lowerX+7;
//						}
//						if((x/40)*40 +7)
//						System.out.println(x+ " "+ block.x+ " "+a.xDis);
//						block.x=x-a.xDis;
//						block.y=y-a.yDis;
					}
				}
//				System.out.println(a.getMousePosition());
			}
//			for(block block:a.array) {
//				if(block!=null) {
//					if(block.name.toLowerCase().equals(ans2[2].toLowerCase())) {
//						block.x=Integer.valueOf(ans2[0]);
//						block.y=Integer.valueOf(ans2[1]);
//					}
//				}
//			}
			for(block block:a.array) {
				if(block!=null && (block.x-7)%40!=0) {
//					System.out.println("X: "+block.x+ " Modulus: "+(block.x-7)%40+ " Divide: "+ block.x/40);
//					block.x=((block.x)/40) *40 +7;
//					block.y=((block.y)/40) *40 +31;
				}
			}
			for(block block:a.array) {
				if(block!=null) {
					if(block.x>(a.rows*40+7)) {
						System.out.println("too far");
					}
				}
			}
			if(a.clicked==true) {
				a.repaint();
				for(int i=0;i<a.graph.length;i++) {
					for(int j=0;j<a.graph[i].length;j++) {
						a.graph[i][j]=0;
					}
				}
				for(block block:a.array) {
					if(block!=null) {
						int x=(block.x-7)/40;
						int y=(block.y-31)/40;
						a.graph[y][x]=-1;
						if(block.name.equals("do")) {
							a.graph[y][x]=1;
						}
//						System.out.println("X: "+block.x+ " Modulus: "+(block.x-7)%40+ " Divide: "+ block.x/40);
//						block.x=((block.x)/40) *40 +7;
//						block.y=((block.y)/40) *40 +31;
					}
				}
				for(int i=0;i<a.graph.length;i++) {
					for(int j=0;j<a.graph[i].length;j++) {
//						System.out.print(a.graph[i][j]);
					}
//					System.out.println();
				}
//				for(int i=0;i<a.array.length;i++) {
//					int x=(a.array[i].x-7)/40;
//					int y=(a.array[i].y-31)/40;
//					a.graph[y][x]=1;
//				}
			}
//			a.repaint();
			Thread.sleep(40);
//			System.out.println("\n");
//			sc.close();
//			sc=null;
//			a.operate=false;
			
		}
	}

}
