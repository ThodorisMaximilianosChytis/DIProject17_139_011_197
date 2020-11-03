package heatmap;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ImageGrid extends JLabel {

//    private static final int CELLS = 10;
//    private static final int

    BufferedImage img;

    public ImageGrid() {
        try {
            img = ImageIO.read(getClass().getClassLoader().getResource("Map.png"));
            setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(ImageGrid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
//            Image background = Toolkit.getDefaultToolkit().createImage(map)
//            g.drawImage(img,0,0,null);
            int cellHeight = (int) (getHeight() / 4.0);
            int cellWidth = (int) (getWidth() / 10.0);
            for (int y = 0; y < getHeight(); y += cellHeight) {
                for (int x = 0; x < getWidth(); x += cellWidth) {
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, cellWidth, cellHeight);
                    System.out.println(cellWidth);
                    System.out.println(cellHeight);
//                    g.setColor(Color.CYAN);
//                    g.fillRect(x, y, cellWidth, cellHeight);

                }
            }
        }
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                JPanel wrapperPanel = new JPanel(new GridBagLayout());
                wrapperPanel.add(new ImageGrid());
//                wrapperPanel.setBackground(new ImageGrid());
//                wrapperPanel.setOpaque(true);
//                wrapperPanel.setBackground(new Color(0,0,0,0));
                frame.add(wrapperPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
