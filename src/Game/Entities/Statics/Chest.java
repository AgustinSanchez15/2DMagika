package Game.Entities.Statics;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Game.Entities.Creatures.Player;
import Game.Items.Item;
import Game.Tiles.Tile;
import Main.Handler;
import Resources.Images;

public class Chest extends StaticEntity{

	private Rectangle ir = new Rectangle();
	public Boolean EP = false;
	public Boolean isOpen = false;
	public Boolean justPressed = false;
	private int stickCount = 0;														//Edit: Here is the stickCount initiation 

	public Chest(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEHEIGHT, Tile.TILEWIDTH);
		health=10000000;
		bounds.width = Tile.TILEWIDTH;
		bounds.height = Tile.TILEHEIGHT;
		ir.width = bounds.width;
		ir.height = bounds.height;
		int irx=(int)(bounds.x-handler.getGameCamera().getxOffset()+x);
		int iry= (int)(bounds.y-handler.getGameCamera().getyOffset()+y+height/2);
		ir.y=iry;
		ir.x=irx;
	}

	@Override
	public void tick() {

		if(isBeinghurt()){
			setHealth(10000000);
		}

		if(handler.getKeyManager().attbut){
			EP=true;

		}else if(!handler.getKeyManager().attbut){
			EP=false;
		}

	}
	private void giveItem(String itemname, int quantity_needed, Player p) {
		int itemcount = quantity_needed;
		if(itemcount<=0) {
			return;
		}
		if(stickCount < 3) {												//Edit:Included a stickCount and the chest does not take more sticks
			for(Item pitem : p.getInventory().getInventoryItems()) {		//when stickCount has 3 sticks already, also when the player has two or 
				if(pitem.getName().equals(itemname) && EP) {				//more stick, when they open it, it takes all the sticks away until the 
					while(pitem.getCount() > 0) {							//player has no more or stickCount has 3
						pitem.setCount(pitem.getCount()-1);					//Lastly, the chest takes the sticks when the player opens it, not closing it.
						itemcount--;
						stickCount++;
						if(stickCount >= 3) {
							break;
						}
					}
				}
			}
		}
		//		g.drawImage(p.getInventory().inventoryItems.get(0).getTexture(), 25, 24, inventoryItems.get(0).getWidth(), inventoryItems.get(0).getHeight(), null);
		//        g.drawString(String.valueOf(int itemcount, 25+33,25+35);
	}

	@Override
	public void render(Graphics g) {
		if(!isOpen) {
			g.drawImage(Images.chest[0],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
		}else if(isOpen) {
			g.drawImage(Images.chest[1],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
		}
		checkForPlayer(g, handler.getWorld().getEntityManager().getPlayer());

	}
	private void checkForPlayer(Graphics g, Player p) {
		Rectangle pr = p.getCollisionBounds(0,0);

		if(ir.contains(pr) && !EP){
			g.drawImage(Images.E,(int) x+width,(int) y+10,32,32,null);
		}

		if(ir.contains(pr) && EP) {
			if(!justPressed) {
				isOpen = !isOpen;
				justPressed = true;
				if(isOpen) {
					giveItem("Stick",3,handler.getWorld().getEntityManager().getPlayer());	//Edit: method implemented here
				}
			} 
			g.drawImage(Images.EP,(int) x+width,(int) y+10,32,32,null);
		} else {
			justPressed = false;
		}
	}
	@Override
	public void die() {

	}
}