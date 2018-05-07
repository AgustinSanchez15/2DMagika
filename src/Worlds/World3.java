package Worlds;
import java.awt.Graphics;
import java.awt.Rectangle;

import Game.Entities.Creatures.Player;
import Game.Entities.Statics.Chest3;
import Main.Handler;
import Resources.Images;

/**
 * Created by Elemental on 2/10/2017.
 */
public class World3 extends BaseWorld{
    private Handler handler;
    private Player player;
    protected Rectangle bounds;

    public World3(Handler handler, String path, Player player) {
        super(handler,path,player);
        this.handler = handler;
        this.player=player;
        
        entityManager.addEntity(new Chest3(handler, 1473, 50));
        bounds = new Rectangle(0, 0, width,height);
    }

    public void render(Graphics g) {
    	handler.getWorld().entityManager.getPlayer().render(g);
    	super.render(g);
    	g.drawImage(Images.narrowVision,(int)(handler.getWorld().entityManager.getPlayer().getX()-handler.getGameCamera().getxOffset()-725),(int)(handler.getWorld().entityManager.getPlayer().getY()-handler.getGameCamera().getyOffset()-getHeight()+(bounds.height/3)-680),1500,1500,null);
    	handler.getWorld().entityManager.getPlayer().getInventory().render(g);
    	handler.getWorld().entityManager.getPlayer().getSpellGUI().render(g);
    }
}