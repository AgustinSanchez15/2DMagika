package Worlds;
import java.awt.Graphics;
import java.awt.Rectangle;

import Game.Entities.Creatures.Player;
import Game.Entities.Statics.Chest3;
import Game.Entities.Statics.Door3;
import Game.GameStates.State;
import Game.Items.Item;
import Main.Handler;
import Resources.Images;

/**
 * Created by Elemental on 2/10/2017.
 */
public class World3 extends BaseWorld{
	private Handler handler;
	private Player player;
	private boolean EP = false;
	private boolean justPressed = false;
	protected Rectangle bounds;
	private int count = 0;

	public World3(Handler handler, String path, Player player) {
		super(handler,path,player);
		this.handler = handler;
		this.player=player;

		entityManager.addEntity(new Chest3(handler, 1475, 1270));
		entityManager.addEntity(new Door3(handler, 1450, 0, null));
		
		bounds = new Rectangle(0, 0, width,height);
	}

	public void tick() {
		super.tick();
		if(handler.getKeyManager().attbut){
			EP=true;

		}else if(!handler.getKeyManager().attbut){
			EP=false;
		}
	}

	public void render(Graphics g) {
		super.render(g);
		g.drawImage(Images.narrowVision,(int)(handler.getWorld().entityManager.getPlayer().getX()-handler.getGameCamera().getxOffset()-725),(int)(handler.getWorld().entityManager.getPlayer().getY()-handler.getGameCamera().getyOffset()-getHeight()+(bounds.height/3)-680),1500,1500,null);
		handler.getWorld().entityManager.getPlayer().getInventory().render(g);
		handler.getWorld().entityManager.getPlayer().getSpellGUI().render(g);

		for(Item pitem : handler.getWorld().entityManager.getPlayer().getInventory().getInventoryItems()) {
			if(pitem.getName().equals("Border")) {	
				if(count <= 1) {
					g.drawImage(Images.items[4],(int)(handler.getWorld().entityManager.getPlayer().getX()-handler.getGameCamera().getxOffset())-570,(int)(handler.getWorld().entityManager.getPlayer().getY()-handler.getGameCamera().getyOffset()),600,300,null);
				} else {
					pitem.setCount(pitem.getCount()-1);
				}
				if(EP) {
					if(!justPressed) {
						justPressed = true;
						count++;
					}
				} else {
					justPressed = false;
				}
			}
		}
		if(handler.getKeyManager().getnbutt()) {
			State.setState(handler.getGame().winState);
		}
	}
}