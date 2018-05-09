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

public class Chest3 extends StaticEntity{

	private Rectangle ir = new Rectangle();
	public Boolean EP = false;
	public Boolean alreadyOpened = false;
	public Boolean showedMessage = false;
	public Boolean justPressed = false;
	public int count = 0;
	//Res.music
    private File audioFile;
    private AudioInputStream audioStream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip audioClip;

	public Chest3(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEHEIGHT, Tile.TILEWIDTH);
		health=10000000;
		bounds.width = Tile.TILEWIDTH;
		bounds.height = Tile.TILEHEIGHT;
		ir.width = bounds.width;
		ir.height = bounds.height;
		int irx= (int)(bounds.x-handler.getGameCamera().getxOffset()+x);
		int iry= (int)(bounds.y-handler.getGameCamera().getyOffset()+y+height/2);
		ir.y=iry;
		ir.x=irx;
		
		try {
            audioFile = new File("res/music/key.wav");
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            format = audioStream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
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
		if(!alreadyOpened) {
			g.drawImage(Images.chest[0],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
		}else if(alreadyOpened) {
			g.drawImage(Images.chest[1],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
			if(count == 0) {
				handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(Item.border);
			}
			if(EP) {
				if(!justPressed) {
					count++;
					justPressed = true;
				}
			} else {
				justPressed = false;
			}
		}

		checkForPlayer(g, handler.getWorld().getEntityManager().getPlayer());

	}
	private void checkForPlayer(Graphics g, Player p) {
		Rectangle pr = p.getCollisionBounds(0,0);

		if(ir.contains(pr) && !EP && !alreadyOpened){
			g.drawImage(Images.E,(int) x+width,(int) y+10,32,32,null);
		}

		if(ir.contains(pr) && EP && !alreadyOpened) {
			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(Item.keyItem);
			g.drawImage(Images.EP,(int) x+width,(int) y+10,32,32,null);
			alreadyOpened = true;
			audioClip.start();
		} 
	}

	@Override
	public void die() {

	}
}