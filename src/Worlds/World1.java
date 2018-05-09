package Worlds;

import java.awt.Graphics;

import Game.Entities.EntityBase;
import Game.Entities.Creatures.Boss;
import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SkelyEnemy;
import Game.Entities.Statics.*;
import Game.Tiles.Tile;
import Main.Handler;
import Resources.Images;

/**
 * Created by Elemental on 1/2/2017.
 */
public class World1 extends BaseWorld{

	private Handler handler;
	private BaseWorld caveWorld;
	

	public World1(Handler handler, String path, Player player){
		super(handler,path,player);
		this.handler = handler;
		caveWorld = new CaveWorld(handler,"res/Maps/caveMap.map",player);
		Chest chest = new Chest(handler, 240, 150);
		Door door =new Door(handler, 400, 0,caveWorld);
		entityManager.addEntity(new Tree(handler, 100, 250));
		entityManager.addEntity(new Rock(handler, 100, 450));
		entityManager.addEntity(new Tree(handler, 633, 376));
		entityManager.addEntity(new Rock(handler, 684, 1370));
		entityManager.addEntity(new Tree(handler, 765, 888));
		entityManager.addEntity(new Rock(handler, 88, 1345));
		entityManager.addEntity(new Tree(handler, 77, 700));
		entityManager.addEntity(new Rock(handler, 700, 83));
		entityManager.addEntity(new SkelyEnemy(handler, 1150, 600));
		entityManager.addEntity(new SkelyEnemy(handler, 950, 800));
		entityManager.addEntity(new SkelyEnemy(handler, 1050, 700));
		entityManager.addEntity(new SkelyEnemy(handler, 1250, 500));
		entityManager.addEntity(new Bush(handler, 387, 500));
		entityManager.addEntity(new Bush(handler, 1079, 891));
		entityManager.addEntity(new Bush(handler, 275, 620));
		entityManager.addEntity(new Bush(handler, 415, 142));
		entityManager.addEntity(chest);
		entityManager.addEntity(new Bush(handler, 215, 242));
		entityManager.addEntity(new Boss(handler, 230, 372));
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);

	
		entityManager.addEntity(door);	


	}

	public void render(Graphics g){
		super.render(g);
		if(handler.getKeyManager().getnbutt()) {
			handler.setWorld(caveWorld);
		}
		
	}
}
