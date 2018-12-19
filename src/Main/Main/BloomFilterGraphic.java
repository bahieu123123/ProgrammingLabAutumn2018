package Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BloomFilterGraphic extends JPanel {
    int m = 0;
    int key;
    BloomFilter bloomFilter2;
    List<Integer> checkList = new ArrayList<>();

    BloomFilterGraphic(int m, int k) {
        this.m = m;
        this.key = k;
        bloomFilter2 = new BloomFilter(m, key);
    }

    @Override
    public void paintComponent(Graphics g) {
        int x = 10;
        int y = 210;
        for (int i = 0; i < bloomFilter2.set.length; i++) {
            g.setColor(new Color(0xB7E2DB));
            g.fillRect(x, y - 10, 20, 20);
            g.setColor(Color.black);
            g.drawRect(x, y - 10, 20, 20);
            g.setFont(new Font("1", 1, 10));
            if (bloomFilter2.set[i] == 0) g.drawString("0", x + 5, y);
            if (bloomFilter2.set[i] == 1) {
                if (checkList.contains(i)) {
                    g.setColor(new Color(0xF08B2B));
                    g.fillRect(x, y - 10, 20, 20);
                    g.setColor(Color.black);
                    g.drawRect(x, y - 10, 20, 20);
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(new Color(0xEFF01A));
                    g.fillRect(x, y - 10, 20, 20);
                    g.setColor(Color.black);
                    g.drawRect(x, y - 10, 20, 20);
                    g.setColor(Color.BLACK);
                }
                g.drawString("1", x + 5, y);
            }
            g.drawString(String.valueOf(i), x + 5, y + 20);
            x += 20;
            if (x > 950) {
                x = 0;
                y += 100;
            }
        }
        checkList.clear();
    }

    public void setDialogs(JFrame frame) {
        JPanel dialog = new JPanel();
        dialog.setBackground(new Color(0xF9E37D));
        JPanel option1Panel = new JPanel();
        option1Panel.setBorder(new LineBorder(new Color(0x000000)));
        option1Panel.setPreferredSize(new Dimension(210, 90));
        option1Panel.setBackground(new Color(0x4F89F9));
        option1Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("Capacity");
        JTextField addMField = new JTextField(9);
        JLabel label0 = new JLabel("Key size");
        JTextField addKField = new JTextField(9);
        JButton option1Button = new JButton("Confirm");
        option1Button.addActionListener(e -> {
            m = Integer.parseInt(addMField.getText());
            key = Integer.parseInt(addKField.getText());
            bloomFilter2 = new BloomFilter(m, key);
            frame.repaint();
        });
        option1Panel.add(label);
        option1Panel.add(addMField);
        option1Panel.add(label0);
        option1Panel.add(addKField);
        option1Panel.add(option1Button);
        dialog.add(option1Panel);


        JPanel addPanel = new JPanel();
        addPanel.setBorder(new LineBorder(new Color(0x000000)));
        addPanel.setPreferredSize(new Dimension(210, 90));
        addPanel.setBackground(new Color(0x4F89F9));
        addPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel label1 = new JLabel("Element");
        JTextField addField = new JTextField(9);
        JButton addButton = new JButton("Add");
        JButton checkButton = new JButton("Check");
        addButton.addActionListener(e -> {
            bloomFilter2.addElement(Integer.parseInt(addField.getText()));
            frame.repaint();
        });
        checkButton.addActionListener(e -> {
            boolean check = bloomFilter2.check(Integer.parseInt(addField.getText()));
            if (check == false) {
                JOptionPane.showMessageDialog(frame, "No element");
            } else {
                int[] arr = bloomFilter2.getSetArray(Integer.parseInt(addField.getText()));
                for (int i : arr) {
                    checkList.add(i);
                }
            }
            frame.repaint();
        });
        addPanel.add(label1);
        addPanel.add(addField);
        addPanel.add(addButton);
        addPanel.add(checkButton);
        dialog.add(addPanel);
        JPanel makeEmptyPanel = new JPanel();
        makeEmptyPanel.setBorder(new LineBorder(new Color(0x000000)));
        makeEmptyPanel.setPreferredSize(new Dimension(210, 90));
        makeEmptyPanel.setBackground(new Color(0x4F89F9));
        makeEmptyPanel.setLayout(new GridBagLayout());
        JButton makeEmptyButton = new JButton("Make empty");
        makeEmptyButton.addActionListener(e -> {
            bloomFilter2.makeEmpty();
            frame.repaint();
        });
        makeEmptyPanel.add(makeEmptyButton);
        dialog.add(makeEmptyPanel);
        frame.add(dialog, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bloom Filter");
        BloomFilterGraphic ui = new BloomFilterGraphic(0, 0);
        frame.add(ui, BorderLayout.CENTER);
        ui.setDialogs(frame);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
