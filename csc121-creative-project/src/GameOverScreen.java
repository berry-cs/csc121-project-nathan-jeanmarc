import processing.core.PApplet;
import processing.event.KeyEvent;

public class GameOverScreen implements IWorld {
	int score;

	public GameOverScreen(int score) {
		this.score = score;
	}
	
	@Override
	public PApplet draw(PApplet c) {
		c.camera(SSConstants.WIDTH/2f, SSConstants.HEIGHT/2f, (SSConstants.HEIGHT/2f) / c.tan(c.PI*30.0f / 180.0f), SSConstants.WIDTH/2f, SSConstants.HEIGHT/2f, 0f, 0f, 1f, 0f);
		c.background(25, 30, 35);
		
		c.fill(60, 35, 15); 
		c.rect(SSConstants.WIDTH / 2, 125, 1000, 225, 75);
		c.rect(SSConstants.WIDTH / 2, SSConstants.HEIGHT / 2 + 35, SSConstants.WIDTH, 275);
		c.rect(SSConstants.WIDTH / 2, SSConstants.HEIGHT, 1000, 225, 75);
		
		c.textFont(SSConstants.font);
		c.fill(115, 30, 145); //255, 130, 15
		c.textSize(200);
		c.textAlign(3);
		c.text("Game Over!", SSConstants.WIDTH / 2, 200);
		c.textSize(125);
		c.text("Score: " + score, SSConstants.WIDTH / 2, SSConstants.HEIGHT / 2 + 20);
		c.textSize(100);
		c.text("HighScore: " + "---", SSConstants.WIDTH / 2, SSConstants.HEIGHT / 2 + 140);
		c.textSize(75);
		c.text("Press Any Key to Play Again!", SSConstants.WIDTH / 2, SSConstants.HEIGHT - 25);
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
