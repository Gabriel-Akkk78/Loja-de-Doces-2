import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LojadeDoces2 extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JPanel[] candyPanels;
    private JLabel[] candyLabels;
    private JLabel[] priceLabels;
    private JTextField[] quantityFields;
    private JButton orderButton;
    private double[] prices = {3.50, 2.75, 1.00}; // Preços dos doces

    public LojadeDoces2() {
        setTitle("Loja de Doces");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        titleLabel = new JLabel("Loja de Doces");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = 2;
        gbcTitle.fill = GridBagConstraints.HORIZONTAL;
        add(titleLabel, gbcTitle);

        candyPanels = new JPanel[3];
        candyLabels = new JLabel[3];
        priceLabels = new JLabel[3];
        quantityFields = new JTextField[3];

        // Adicionando os doces
        String[] candyImagePaths = {"Cupcake.png", "Cookie.png", "Pudim.png"};
        for (int i = 0; i < 3; i++) {
            candyPanels[i] = new JPanel(new BorderLayout());
            try {
                BufferedImage originalImage = ImageIO.read(getClass().getResource(candyImagePaths[i]));
                BufferedImage resizedImage = resizeImage(originalImage, 100, 100); // Tamanho desejado
                ImageIcon candyIcon = new ImageIcon(resizedImage);
                candyLabels[i] = new JLabel(candyIcon);
                candyPanels[i].add(candyLabels[i], BorderLayout.CENTER);

                priceLabels[i] = new JLabel("R$" + prices[i]);
                priceLabels[i].setHorizontalAlignment(JLabel.RIGHT); // Alinhamento à direita
                candyPanels[i].add(priceLabels[i], BorderLayout.LINE_END);

                GridBagConstraints gbcCandy = new GridBagConstraints();
                gbcCandy.gridx = 0;
                gbcCandy.gridy = i + 1;
                gbcCandy.fill = GridBagConstraints.HORIZONTAL;
                gbcCandy.insets = new Insets(5, 5, 5, 5); // Espaçamento
                add(candyPanels[i], gbcCandy);

                quantityFields[i] = new JTextField(5);
                GridBagConstraints gbcQuantity = new GridBagConstraints();
                gbcQuantity.gridx = 1;
                gbcQuantity.gridy = i + 1;
                gbcQuantity.insets = new Insets(5, 5, 5, 5); // Espaçamento
                add(quantityFields[i], gbcQuantity);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        orderButton = new JButton("Pedir");
        orderButton.addActionListener(this);
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 4;
        gbcButton.gridwidth = 2;
        gbcButton.fill = GridBagConstraints.HORIZONTAL;
        gbcButton.insets = new Insets(10, 0, 0, 0); // Espaçamento acima
        add(orderButton, gbcButton);

        pack();
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true);
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return resizedImage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == orderButton) {
            double total = 0;
            for (int i = 0; i < 3; i++) {
                int quantity = Integer.parseInt(quantityFields[i].getText());
                total += prices[i] * quantity;
            }
            JOptionPane.showMessageDialog(this, "Total da compra: $" + total);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LojadeDoces2::new);
    }
}