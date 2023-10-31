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
		c.textSize(100);
		c.textFont(SSConstants.font);
		c.text("Game over!", c.width / 2, c.height / 2);
		c.textSize(75);
		c.text("Score: " + score, c.width / 2, c.height / 2 + 85);
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
