import processing.core.PApplet;
import processing.event.KeyEvent;

public class GameOverScreen implements IWorld {
	int score;

	public GameOverScreen(int score) {
		Sounds.mainTheme.stop();
		Sounds.runSound.stop();
		this.score = score;
	}
	
	@Override
	public PApplet draw(PApplet c) {
		c.background(255);
		c.textSize(200);
		c.textFont(SSConstants.font);
		c.text("Game Over!", SSConstants.WIDTH / 2, SSConstants.HEIGHT / 2);
		c.textSize(150);
		c.text("Score: " + score, SSConstants.WIDTH / 2, SSConstants.HEIGHT / 2 + 150);
		return c;
	}

	@Override
	public IWorld update() {
		return this;
	}

	@Override
	public IWorld keyPressed(KeyEvent kev) {
		// TODO Auto-generated method stub
		return this;
	}

}
