package Game.Entities.Statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Game.Entities.Creatures.Player;
import Game.Tiles.Tile;
import Main.Handler;
import Resources.Images;

public class Chest extends StaticEntity{
	
	private Rectangle ir = new Rectangle();
    public Boolean EP = false;
    
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

	@Override
	public void render(Graphics g) {
		g.drawImage(Images.chest[0],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
		
        checkForPlayer(g, handler.getWorld().getEntityManager().getPlayer());
	}
	private void checkForPlayer(Graphics g, Player p) {
        Rectangle pr = p.getCollisionBounds(0,0);

        if(ir.contains(pr) && !EP){
            g.drawImage(Images.E,(int) x+width,(int) y+10,32,32,null);
        }else if(ir.contains(pr) && EP){
            g.drawImage(Images.EP,(int) x+width,(int) y+10,32,32,null);
           
        }


    }
	@Override
	public void die() {
		
		
	}

}
