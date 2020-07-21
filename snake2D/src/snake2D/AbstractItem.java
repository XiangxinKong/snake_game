package snake2D;

import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
*
* @author Mohammed.Talaat (mtala3t)
* @version 1.0
*/
public class AbstractItem {

	private Ellipse2D.Double coordinate;
	/////
	public static final int sFood=0;
	public static final int sItem=1;
	public static final int sTime=2;

	private Map loadmap = new Map();
	private int[][] mymap;
	public int type;
	///////////////
	/** Creates a new instance of SnakeFood */

	public AbstractItem(int mapnumber/**/, int type) {
		if (mapnumber!=0) mymap = loadmap.mapposition(mapnumber);
		this.type=type;
		generateAbstractItem(mapnumber);
	}

	private void generateAbstractItem(int mapp/**/) {
		Random random = new Random();
		int x, y, i;
		boolean itemMap;
		do {
			x = random.nextInt(39);
			y = random.nextInt(30);
			/////
			itemMap=false;
			if (mapp != 0) {
				for (i=0;i<mymap.length;i++) {
					if (x==mymap[i][0] && y==mymap[i][1]) {
						itemMap = true;
						break;
					}
				}
			}
			/////////////////////////
		} while (itemMap == true || x == 38 || y == 29);
		x = x * 16 + 227;
		y = y * 16 + 127;
		coordinate = new Ellipse2D.Double(x, y, 16, 16);
	}

	public void hide(){
		coordinate = new Ellipse2D.Double(1000, 1000, 16, 16);
	}

	public Ellipse2D.Double getCoordinate() {
		return coordinate;
	}
}
