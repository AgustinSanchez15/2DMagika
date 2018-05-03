//package Game.Entities.Statics;
//
//import java.awt.Graphics;
//
//import Game.Items.Item;
//import Main.Handler;
//import Resources.Images;
//
//public class Bush extends Tree{
//
//	public Bush(Handler handler, float x, float y) {
//		super(handler, x, y);
//		health = 8;
//	}
//	@Override
//    public void render(Graphics g) {
//        renderLife(g);
//        g.drawImage(Images.blocks[15],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
//
//    }
//	@Override
//    public void die() {
//        handler.getWorld().getItemManager().addItem(Item.stickItem.createNew((int)x + bounds.x,(int)y + bounds.y,1));
//    }
//}
