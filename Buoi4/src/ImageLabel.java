import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * ImageLabel là JLabel có thêm hiệu ứng mờ dần khi đổi ảnh.
 */
public class ImageLabel extends JLabel {
    private float opacity = 1f;
    private Timer fadeTimer;

    public void setImageWithFade(ImageIcon imageIcon) {
        opacity = 0f;
        stopCurrentAnimationIfNeeded();

        setIcon(imageIcon);
        startFadeInAnimation();
    }

    private void stopCurrentAnimationIfNeeded() {
        if (fadeTimer != null && fadeTimer.isRunning()) {
            fadeTimer.stop();
        }
    }

    private void startFadeInAnimation() {
        fadeTimer = new Timer(20, event -> {
            opacity += 0.05f;

            if (opacity >= 1f) {
                opacity = 1f;
                fadeTimer.stop();
            }

            repaint();
        });

        fadeTimer.start();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics.create();

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        super.paintComponent(graphics2D);

        graphics2D.dispose();
    }
}
