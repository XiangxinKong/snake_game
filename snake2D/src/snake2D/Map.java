package snake2D;

import java.awt.geom.Rectangle2D;

public class Map {

	private int[][] simplemap1 = new int[50][2];
	private int[][] simplemap2 = new int[56][2];
	private Rectangle2D.Double[] map1 = new Rectangle2D.Double[50];
	private Rectangle2D.Double[] map2 = new Rectangle2D.Double[56];

	public Map(){
		for (int i=0;i<15;i++) {
			map1[i] = new Rectangle2D.Double(227+16*i,351,16,16);
			map1[i+15] = new Rectangle2D.Double(819-16*i,351,16,16);
			simplemap1[i][0] = i; simplemap1[i][1] = 14;
			simplemap1[i+15][0] = 37-i; simplemap1[i+15][1] = 14;
		}
		for (int i=0;i<10;i++) {
			map1[i+30] = new Rectangle2D.Double(515,127+16*i,16,16);
			map1[i+40] = new Rectangle2D.Double(515,575-16*i,16,16);
			simplemap1[i+30][0] = 18; simplemap1[i+30][1] = i;
			simplemap1[i+40][0] = 18; simplemap1[i+40][1] = 28-i;
		}
		
		for (int i=0;i<5;i++) {
			map2[i] = new Rectangle2D.Double(227+i*16,351,16,16);
			map2[i+5] = new Rectangle2D.Double(819-i*16,351,16,16);
			simplemap2[i][0] = i; simplemap2[i][1] = 14;
			simplemap2[i+5][0] = 37-i; simplemap2[i+5][1] = 14;
		}
		for (int i=0;i<23;i++) {
			map2[i+10] = new Rectangle2D.Double(483,127+16*i,16,16);
			map2[i+33] = new Rectangle2D.Double(547,575-16*i,16,16);
			simplemap2[i+10][0] = 16; simplemap2[i+10][1] = i;
			simplemap2[i+33][0] = 20; simplemap2[i+33][1] = 28-i;
		}
	}

	public Rectangle2D.Double[] choseMap(int x) {
		if (x == 1) return map1;
		else if (x == 2) return map2;
		else return null;
	}
	
	public int[][] mapposition(int x) {
		if (x == 1) return simplemap1;
		else if (x == 2) return simplemap2;
		else return null;
	}
}