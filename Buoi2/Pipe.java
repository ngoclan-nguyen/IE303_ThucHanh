import java.awt.Image;
import java.awt.Rectangle;

public class Pipe {
    private int x;
    private int y;
    private int width = 52;
    private int height;
    private Image img;
    private boolean passed = false;
    private boolean isTopPipe;

    public Pipe(int x, int y, int height, Image img, boolean isTopPipe) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.img = img;
        this.isTopPipe = isTopPipe;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Image getImg() { return img; }
    public boolean isPassed() { return passed; }
    public boolean isTopPipe() { return isTopPipe; }

    public void setX(int x) { this.x = x; }
    public void setPassed(boolean passed) { this.passed = passed; }
}