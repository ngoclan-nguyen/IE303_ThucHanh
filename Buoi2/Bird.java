import java.awt.Rectangle;

public class Bird {
    private int x = 60;
    private int y;
    private int width = 34;
    private int height = 24;
    private double velocityY = 0;
    private double gravity = 0.5;
    private double jumpStrength = -8.0;

    public Bird(int startY) {
        this.y = startY;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public double getVelocityY() { return velocityY; }
    public double getGravity() { return gravity; }
    public double getJumpStrength() { return jumpStrength; }

    public void setY(int y) { this.y = y; }
    public void setVelocityY(double velocityY) { this.velocityY = velocityY; }
}