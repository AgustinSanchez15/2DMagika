package Game.Entities.Statics;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
	private int stickcount = 0;	
	private int bonecount = 0;
	private boolean getStick = true;
	private boolean getBone = true;


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
		//giveItem("Stick",3,handler.getWorld().getEntityManager().getPlayer());
		//giveItem("Bone",3,handler.getWorld().getEntityManager().getPlayer());

		if(stickcount >= 3 && bonecount >= 3) {
			handler.getGame().setDoorVisible(true);
		}
	}
	private void giveItem(String itemname, int quantity_needed, Player p) {
		//Rectangle pr = p.getCollisionBounds(0,0);
		for(Item pitem : p.getInventory().getInventoryItems()) {		
			if(itemname.equals("Stick") && getStick) {
				if(pitem.getName().equals(itemname)) {
					while(pitem.getCount() > 0) {
						stickcount++;														
						pitem.setCount(pitem.getCount()-1);
						if(stickcount >= quantity_needed) {
							getStick = false;
							break;
						}
					}
				}

			}else if(itemname.equals("Bone") && getBone) {
				if(itemname.equals("Bone") && getBone) {
					if(pitem.getName().equals(itemname)) {
						while(pitem.getCount() > 0) {
							bonecount++;														
							pitem.setCount(pitem.getCount()-1);
							if(bonecount >= quantity_needed) {
								getBone = false;
								break;
							}
						}
					}
				}

			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(!isOpen) {
			g.drawImage(Images.chest[0],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
		}else if(isOpen) {
			g.drawImage(Images.chest[1],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
		}
		checkForPlayer(g, handler.getWorld().getEntityManager().getPlayer());
		if(stickcount>=3 && bonecount>=3) {
			isOpen = false;
		}

	}
	private void checkForPlayer(Graphics g, Player p) {
		Rectangle pr = p.getCollisionBounds(0,0);

		if(ir.contains(pr) && !EP && !isOpen){
			g.drawImage(Images.E,(int) x+width,(int) y+10,32,32,null);
		}

		if(ir.contains(pr) && EP) {

			isOpen = true;

			if(isOpen) {
				giveItem("Stick",3,handler.getWorld().getEntityManager().getPlayer());
				giveItem("Bone",3,handler.getWorld().getEntityManager().getPlayer());//Edit: method implemented here
			}


		} 
		if(ir.contains(pr) && isOpen) {
			g.drawImage(Images.items[1],(int) x+width,(int) y+10,32,32,null);
			g.drawString(String.valueOf(stickcount) + "/" +String.valueOf(3), (int) x+width,(int)y+10);
			g.drawImage(Images.items[2],(int) x+width+width/2,(int) y+10,32,32,null);
			g.drawString(String.valueOf(bonecount) + "/" +String.valueOf(3), (int) x+width+width/2,(int)y+10);
		}

	}
	@Override
	public void die() {

	}
}