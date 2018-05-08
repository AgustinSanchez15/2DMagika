package Worlds;
import java.awt.Graphics;

import Game.Entities.Creatures.Boss;
//import Game.Entities.Creatures.Boss;
import Game.Entities.Creatures.Player;
import Game.Entities.Statics.Door;
import Main.Handler;

/**
 * Created by Elemental on 2/10/2017.
 */
public class CaveWorld extends BaseWorld{
    private Handler handler;
    private Player player;
    private BaseWorld World3;

    public CaveWorld(Handler handler, String path, Player player) {
        super(handler,path,player);
        this.handler = handler;
        this.player=player;
        World3 = new World3(handler,"res/Maps/map3.map",player);
        entityManager.addEntity(new Boss(handler,500,500));

        entityManager.addEntity(new Door(handler, 300, 0, World3));
    }
    public void render(Graphics g){
		super.render(g);
		if(handler.getKeyManager().getnbutt()) {
			handler.setWorld(World3);
		}
	}

}