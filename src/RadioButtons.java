import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Hashtable;

/**
 * A simple Java Swing application that demonstrates a custom JSlider UI
 * to create a modern, game-like difficulty selection bar.
 */
public class RadioButtons {
   static JFrame frame = new JFrame("Difficulty Selection");
  static   JPanel panel = new JPanel();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);
            frame.getContentPane().setBackground(new Color(40, 40, 40));
            frame.setLayout(new GridBagLayout());


         //   panel.setOpaque(false);
            panel.setLayout(new BorderLayout(10, 20));

            JLabel titleLabel = new JLabel("Choose Difficulty");
            titleLabel.setForeground(new Color(220, 220, 220));
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(titleLabel, BorderLayout.NORTH);

            JSlider difficultySlider = new JSlider(JSlider.HORIZONTAL, 0, 2, 0);
            difficultySlider.setOpaque(false);
            difficultySlider.setMajorTickSpacing(1);
            difficultySlider.setPaintTicks(true);
            difficultySlider.setPaintLabels(true);

            // Set the custom UI for the slider
            difficultySlider.setUI(new CustomSliderUI(difficultySlider));

            Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
            labelTable.put(0, new JLabel("Easy"));
            labelTable.put(1, new JLabel("Medium"));
            labelTable.put(2, new JLabel("Hard"));

            Font labelFont = new Font("Arial", Font.BOLD, 18);
            labelTable.get(0).setForeground(new Color(144, 238, 144));
            labelTable.get(0).setFont(labelFont);
            labelTable.get(1).setForeground(new Color(255, 255, 102));
            labelTable.get(1).setFont(labelFont);
            labelTable.get(2).setForeground(new Color(255, 99, 71));
            labelTable.get(2).setFont(labelFont);

            difficultySlider.setLabelTable(labelTable);

            panel.add(difficultySlider, BorderLayout.CENTER);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(20, 20, 20, 20);
            frame.add(panel, gbc);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    /**
     * Custom UI for the JSlider to give it a unique, game-like appearance.
     */
    static class CustomSliderUI extends BasicSliderUI {
        private final int trackHeight = 20; // Vertical thickness
        private final int thumbWidth = 10;
        private final int thumbHeight = 25;

        CustomSliderUI(JSlider slider) {
            super(slider);
        }

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            slider.setOpaque(false);
            slider.setPreferredSize(new Dimension(500, 120));
        }

        @Override
        public void paintTrack(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Calculate track bounds
            int trackY = trackRect.y + (trackRect.height - trackHeight) / 2;
            int arc = 20;

            // Draw the background track (unfilled part)
            g2d.setColor(new Color(80, 80, 80));
            g2d.fill(new RoundRectangle2D.Double(trackRect.x, trackY, trackRect.width, trackHeight, arc, arc));

            // Calculate the filled portion based on the thumb's position
            int thumbX = thumbRect.x + thumbRect.width / 2;

            // Determine the color of the filled portion based on the value
            Color fillColor;
            if (slider.getValue() == 0) {
                fillColor = new Color(255, 99, 71); // Reddish for "Easy"
            } else if (slider.getValue() == 1) {
                fillColor = new Color(255, 255, 102); // Yellowish for "Medium"
            } else {
                fillColor = new Color(144, 238, 144); // Greenish for "Hard"
            }

            // Draw the filled portion
            g2d.setColor(fillColor);
            g2d.fill(new RoundRectangle2D.Double(trackRect.x, trackY, thumbX - trackRect.x, trackHeight, arc, arc));
        }

        @Override
        public void paintThumb(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the thumb as a rounded rectangle
            g2d.setColor(new Color(250, 250, 250)); // Light color for thumb
            g2d.fill(new RoundRectangle2D.Double(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height, 5, 5));
        }

        @Override
        protected Dimension getThumbSize() {
            return new Dimension(thumbWidth, thumbHeight);
        }
    }
}
