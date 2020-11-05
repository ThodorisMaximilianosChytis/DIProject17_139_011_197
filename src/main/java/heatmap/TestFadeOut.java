package heatmap;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

    public class TestFadeOut {

        public static void main(String[] args) {
            new TestFadeOut();
        }

        public TestFadeOut() {
            EventQueue.invokeLater(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (UnsupportedLookAndFeelException e) {
                                e.printStackTrace();
                            }


                            JFrame frame = new JFrame("Testing");
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setLayout(new BorderLayout());
                            frame.add(new TestPane());
                            frame.pack();
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);
                        }
                    });
        }

        public class TestPane extends JPanel {

            private float alpha = 1f;
            private float diff = -0.02f;
            private BufferedImage img;

            public TestPane() {
                try {
                    img = ImageIO.read(new File("Map.png"));
                    Timer timer = new Timer(40, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            alpha += diff;
                            if (alpha < 0) {
                                diff *= -1;
                                alpha = diff;
                            } else if (alpha > 1f) {
                                diff *= -1;
                                alpha = 1f + diff;
                            }
                            repaint();
                        }
                    });
                    timer.setRepeats(true);
                    timer.setCoalesce(true);
                    timer.start();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return img == null ? super.getPreferredSize() : new Dimension(img.getWidth(), img.getHeight());
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (img != null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
                    int x = getWidth() - img.getWidth();
                    int y = getHeight() - img.getHeight();
                    g2d.drawImage(img, x, y, this);
                    g2d.dispose();
                }
            }
        }
    }

