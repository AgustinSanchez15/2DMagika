package Game.Entities.Statics;

import Game.Entities.Creatures.Player;
import Game.GameStates.State;
import Main.Handler;
import Resources.Images;
import Worlds.BaseWorld;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Created by Elemental on 2/2/2017.
 */


public class Door extends StaticEntity {
	public boolean accomplished = false;
	private Rectangle ir = new Rectangle();
	public Boolean EP = false;

	private BaseWorld world;
	private File audioFile;
    private AudioInputStream audioStream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip audioClip;

	public Door(Handler handler, float x, float y,BaseWorld world) {
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

		if(handler.getGame().getDoorVisible()) {
			g.drawImage(Images.door,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
			audioClip.start();
		}

		g.setColor(Color.black);
		checkForPlayer(g, handler.getWorld().getEntityManager().getPlayer());
	}

	private void checkForPlayer(Graphics g, Player p) {
		Rectangle pr = p.getCollisionBounds(0,0);

		if(ir.contains(pr) && !EP){
			if(handler.getGame().getDoorVisible()) {
				g.drawImage(Images.E,(int) x+width,(int) y+10,32,32,null);
			}
		}else if(ir.contains(pr) && EP){
			if(handler.getGame().getDoorVisible()) {
				g.drawImage(Images.EP,(int) x+width,(int) y+10,32,32,null);
				g.drawImage(Images.loading,0,0,800,600,null);
				handler.setWorld(world);
				handler.getGame().setDoorVisible(false);
			}
		}


	}

	@Override
	public void die() {

	}
	
}
