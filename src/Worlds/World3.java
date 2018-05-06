package Worlds;
import Game.Entities.Creatures.Boss;
//import Game.Entities.Creatures.Boss;
import Game.Entities.Creatures.Player;
import Main.Handler;

/**
 * Created by Elemental on 2/10/2017.
 */
public class World3 extends BaseWorld{
    private Handler handler;
    private Player player;

    public World3(Handler handler, String path, Player player) {
        super(handler,path,player);
        this.handler = handler;
        this.player=player;

        
    }


}