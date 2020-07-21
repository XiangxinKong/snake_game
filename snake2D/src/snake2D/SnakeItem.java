package snake2D;

import java.awt.geom.Ellipse2D;
import java.util.Random;

public class SnakeItem {

	private Ellipse2D.Double item;
	/////
	private Map loadmap = new Map();
	private int[][] mymap;
	///////////////
	/** Creates a new instance of SnakeItem */
	public SnakeItem(int mapnumber/**/) {
		if (mapnumber!=0) mymap = loadmap.mapposition(mapnumber);
		generateItem(mapnumber);
	}

	public void generateItem(int mapp/**/) {

		Random random = new Random();
		int x, y, i;
		boolean Itemonmap;
		do {
			x = (int) (random.nextInt(39));
			y = (int) (random.nextInt(30));
			/////
			Itemonmap=false;
			if (mapp != 0) {
				for (i=0;i<mymap.length;i++) {
					if (x==mymap[i][0] && y==mymap[i][1]) {
						Itemonmap = true;
						break;
					}
				}
			}
			/////////////////////////
		} /*while (x == 0 || y == 0 || x == 38 || y == 29);*/
		/////(¸Ä)
		while (Itemonmap == true || x == 38 || y == 29);
		//////////

		x = x * 16 + 227;
		y = y * 16 + 127;

		item = new Ellipse2D.Double(x, y, 16, 16);
	}

	public Ellipse2D.Double getItem() {
		return item;
	}
	
}


