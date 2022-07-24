import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Window extends JPanel {
    public int[][] texture;
    public int seed = 0;
    public static int GLOBAL_OFFSET = 0;
    public static boolean REGEN = false;
    public static int TIME = 0;

    public Random random;

    private JFrame frame;

    public List<Integer> particleX;
    public List<Integer> particleY;

    public int[][] particleTexture;

    public Window() {
        particleTexture = new int[500][500];

        particleX = new ArrayList<>();
        particleY = new ArrayList<>();

        random = new Random(0);

//        for (int i = 0; i < 10000; i++) {
//            if (random.nextInt(2) == 0) {
//                particleX.add(random.nextInt(500));
//                particleY.add(250);
//            } else {
//                particleY.add(random.nextInt(500));
//                particleX.add(250);
//            }
//        }

        for (int i = 0; i < 100000; i++) {
//            particleX.add(random.nextInt(500));
            particleX.add(random.nextInt(100) + 200);
            particleY.add(random.nextInt(100) + 200);
//            particleY.add(random.nextInt(500));
            particleY.add(250);
        }

        frame = new JFrame("Diamond Square Noise Flow Field");

        frame.setSize(500, 500);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addKeyListener(new Keyboard());

        frame.add(this);

        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (REGEN) {
            seed++;

            texture = DSNoise.generate(seed, 50, 50);

            REGEN = false;
        }

//        for (int i = 0; i < 50; i++) {
//            for (int j = 0; j < 50; j++) {
//                g.setColor(new Color(texture[i][j], texture[i][j], texture[i][j]));
//
//                double val = texture[i][j] / 255. * 360;
//
//                double xOffset = Math.cos((val + GLOBAL_OFFSET) / 180 * Math.PI);
//                double yOffset = Math.sin((val + GLOBAL_OFFSET) / 180 * Math.PI);
//
//                int xo = (int) (xOffset * 10);
//                int yo = (int) (yOffset * 10);
//
////                g.fillRect(j * 10, i * 10, 10, 10);
//
////                g.setColor(Color.RED);
//
//                g.drawLine(j * 10, i * 10, j * 10 + xo, i * 10 + yo);
//            }
//        }

        for (int i = 0; i < particleX.size(); i++) {
            double val = texture[particleY.get(i) / 10][particleX.get(i) / 10] / 255. * 360;

            double xOffset = Math.cos((val + GLOBAL_OFFSET) / 180 * Math.PI);
            double yOffset = Math.sin((val + GLOBAL_OFFSET) / 180 * Math.PI);

            int xo = (int) (xOffset * 2);
            int yo = (int) (yOffset * 2);

            particleX.set(i, particleX.get(i) + xo);
            particleY.set(i, particleY.get(i) + yo);

            if (particleX.get(i) < 0) {
                particleX.set(i, 500 + particleX.get(i));
            } else if (particleX.get(i) > 499) {
                particleX.set(i, particleX.get(i) - 500);
            }

            if (particleY.get(i) < 0) {
                particleY.set(i, 500 + particleY.get(i));
            } else if (particleY.get(i) > 499) {
                particleY.set(i, particleY.get(i) - 500);
            }

            particleTexture[particleY.get(i)][particleX.get(i)]++;
        }

        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
//                particleTexture[i][j] --;

                int curr = particleTexture[i][j];
                curr *= 1;

//                int r = Math.max(0, Math.min(255, curr < 255 ? curr : 665 - curr));
//                int gr = Math.max(0, Math.min(255, curr / 2));
                int b = Math.max(0, Math.min(255, curr));

                g.setColor(new Color(0, b, 0));

                g.fillRect(j, i, 1, 1);
            }
        }

//        try {
//            TimeUnit.MILLISECONDS.sleep(16);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//        if(TIME % 1 == 0) GLOBAL_OFFSET ++;

        TIME++;

        if (random.nextInt(5) == 0) {
//            GLOBAL_OFFSET += 5;
        }

        repaint();
    }
}
