package Game.Entities.Statics;

import Game.Entities.Creatures.Player;
import Game.GameStates.State;
import Game.Items.Item;
import Main.Handler;
import Resources.Images;
import Worlds.BaseWorld;

import java.awt.*;

/**
 * Created by Elemental on 2/2/2017.
 */


public class Door3 extends StaticEntity {

    private Rectangle ir = new Rectangle();
    public Boolean EP = false;
    private Boolean doorOpened = false;
    private Boolean justPressed = false;

    private BaseWorld world;

    public Door3(Handler handler, float x, float y,BaseWorld world) {
        super(handler, x, y, 64, 100);
        this.world=world;
        health=10000000;
        bounds.x=0;
        bounds.y=0;
        bounds.width = 100;
        bounds.height = 64;

        ir.width = bounds.width;
        ir.height = bounds.height;
        int irx=(int)(bounds.x-handler.getGameCamera().getxOffset()+x);
        int iry= (int)(bounds.y-handler.getGameCamera().getyOffset()+height);
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
    	if(!doorOpened) {
    		g.drawImage(Images.doorClosed,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
    	} else {
    		g.drawImage(Images.doorOpened,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
    	}

        g.setColor(Color.black);
        checkForPlayer(g, handler.getWorld().getEntityManager().getPlayer());
    }

    private void checkForPlayer(Graphics g, Player p) {
        Rectangle pr = p.getCollisionBounds(0,0);

        if(ir.contains(pr) && !EP){
            g.drawImage(Images.E,(int) x+width,(int) y+10,32,32,null);
        }else if(ir.contains(pr) && EP){
            g.drawImage(Images.EP,(int) x+width,(int) y+10,32,32,null);
            if(!justPressed) {
            	if(doorOpened) {
            		handler.getGame().gameState.setState(handler.getGame().winState);
            	}
            	if(!doorOpened) {
            		for(Item pitem : p.getInventory().getInventoryItems()) {
            			if(pitem.getName().equals("Key")) {
            				doorOpened = true;
            			}
            		}
            	}
            	justPressed = true;
            }
        } else {
        	justPressed = false;
        }


    }

    @Override
    public void die() {

    }
}
