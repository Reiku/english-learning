package util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 2669520868916689663L;
	private Image image;
	private int imgWidth;
	private int imgHeight;

    public void setBackground(byte[] data, int maxHeight) {
        try {
        	BufferedImage bi = ImageIO.read(new ByteArrayInputStream(data));
			this.image = bi;
			this.imgWidth = bi.getWidth();
			this.imgHeight = bi.getHeight();
			
			this.setPreferredSize(new Dimension(maxHeight * imgWidth / imgHeight, maxHeight));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
    }

}