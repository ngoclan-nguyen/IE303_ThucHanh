import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class ShopGUI extends JFrame {
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    private static final int DETAIL_PANEL_WIDTH = 350;
    private static final int MAIN_IMAGE_SIZE = 300;
    private static final int CARD_IMAGE_SIZE = 140;

    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color PRODUCT_CARD_COLOR = new Color(248, 249, 250);
    private static final Color PRODUCT_CARD_HOVER_COLOR = new Color(235, 235, 235);
    private static final Color PRODUCT_CARD_BORDER_COLOR = new Color(220, 220, 220);

    private ImageLabel mainImageLabel;
    private JLabel productTitleLabel;
    private JLabel productPriceLabel;
    private JLabel productBrandLabel;
    private JLabel productDescriptionLabel;

    private List<Product> products;

    public ShopGUI() {
        configureWindow();

        loadProductsFromDatabase();

        add(createProductDetailPanel(), BorderLayout.WEST);
        add(createProductListScrollPane(), BorderLayout.CENTER);

        showFirstProductIfAvailable();
    }

    private void configureWindow() {
        setTitle("Product Store");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 0));
        getContentPane().setBackground(BACKGROUND_COLOR);
    }

    private void loadProductsFromDatabase() {
        products = new ArrayList<>();

        try {
            ProductDAO productDAO = new ProductDAO();
            products = productDAO.getAllProducts();
        } catch (SQLException exception) {
            showDatabaseError(exception);
        }
    }

    private JPanel createProductDetailPanel() {
        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.setPreferredSize(new Dimension(DETAIL_PANEL_WIDTH, 0));
        detailPanel.setBackground(BACKGROUND_COLOR);
        detailPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        mainImageLabel = new ImageLabel();
        mainImageLabel.setPreferredSize(new Dimension(MAIN_IMAGE_SIZE, MAIN_IMAGE_SIZE));

        productTitleLabel = createLabel("Tên Sản Phẩm", Font.BOLD, 22, Color.BLACK);
        productPriceLabel = createLabel("$0.00", Font.BOLD, 20, Color.BLACK);
        productBrandLabel = createLabel("Thương hiệu", Font.PLAIN, 14, Color.GRAY);
        productDescriptionLabel = createLabel("Mô tả...", Font.PLAIN, 14, Color.DARK_GRAY);

        detailPanel.add(mainImageLabel);
        detailPanel.add(Box.createVerticalStrut(20));
        detailPanel.add(productTitleLabel);
        detailPanel.add(Box.createVerticalStrut(10));
        detailPanel.add(productPriceLabel);
        detailPanel.add(Box.createVerticalStrut(5));
        detailPanel.add(productBrandLabel);
        detailPanel.add(Box.createVerticalStrut(15));
        detailPanel.add(productDescriptionLabel);

        return detailPanel;
    }

    private JScrollPane createProductListScrollPane() {
        JPanel productGridPanel = createProductGridPanel();

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(BACKGROUND_COLOR);
        wrapperPanel.add(productGridPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(wrapperPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return scrollPane;
    }

    private JPanel createProductGridPanel() {
        JPanel productGridPanel = new JPanel(new GridLayout(0, 4, 15, 15));
        productGridPanel.setBackground(BACKGROUND_COLOR);
        productGridPanel.setBorder(new EmptyBorder(30, 0, 30, 30));

        for (Product product : products) {
            productGridPanel.add(createProductCard(product));
        }

        return productGridPanel;
    }

    private JPanel createProductCard(Product product) {
        JPanel productCard = new JPanel(new BorderLayout(0, 10));
        productCard.setBackground(PRODUCT_CARD_COLOR);
        productCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
        productCard.setBorder(createProductCardBorder());

        productCard.add(createProductCardHeader(product), BorderLayout.NORTH);
        productCard.add(createProductImageLabel(product), BorderLayout.CENTER);
        productCard.add(createProductCardFooter(product), BorderLayout.SOUTH);

        productCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                showProductDetail(product);
            }

            @Override
            public void mouseEntered(MouseEvent event) {
                productCard.setBackground(PRODUCT_CARD_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                productCard.setBackground(PRODUCT_CARD_COLOR);
            }
        });

        return productCard;
    }

    private JPanel createProductCardHeader(Product product) {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        JLabel titleLabel = createLabel(product.getTitle(), Font.BOLD, 15, Color.BLACK);
        JLabel descriptionLabel = createLabel(
                shortenText(product.getDescription(), 25),
                Font.PLAIN,
                12,
                Color.GRAY
        );

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(descriptionLabel);

        return headerPanel;
    }

    private JLabel createProductImageLabel(Product product) {
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setIcon(resizeImage(product.getImagePath(), CARD_IMAGE_SIZE, CARD_IMAGE_SIZE));

        return imageLabel;
    }

    private JPanel createProductCardFooter(Product product) {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setOpaque(false);

        JLabel brandLabel = createLabel(product.getBrand(), Font.PLAIN, 12, Color.GRAY);
        JLabel priceLabel = createLabel(product.getPrice(), Font.BOLD, 16, Color.BLACK);

        footerPanel.add(brandLabel, BorderLayout.WEST);
        footerPanel.add(priceLabel, BorderLayout.EAST);

        return footerPanel;
    }

    private javax.swing.border.Border createProductCardBorder() {
        javax.swing.border.Border lineBorder =
                BorderFactory.createLineBorder(PRODUCT_CARD_BORDER_COLOR, 1, true);
        javax.swing.border.Border padding = new EmptyBorder(15, 15, 15, 15);

        return BorderFactory.createCompoundBorder(lineBorder, padding);
    }

    private JLabel createLabel(String text, int fontStyle, int fontSize, Color textColor) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", fontStyle, fontSize));
        label.setForeground(textColor);

        return label;
    }

    private void showFirstProductIfAvailable() {
        if (!products.isEmpty()) {
            showProductDetail(products.get(0));
        }
    }

    private void showProductDetail(Product product) {
        productTitleLabel.setText(product.getTitle());
        productPriceLabel.setText(product.getPrice());
        productBrandLabel.setText(product.getBrand());
        productDescriptionLabel.setText("<html>" + product.getDescription() + "</html>");
        mainImageLabel.setImageWithFade(
                resizeImage(product.getImagePath(), MAIN_IMAGE_SIZE, MAIN_IMAGE_SIZE)
        );
    }

    private ImageIcon resizeImage(String imagePath, int width, int height) {
        Image image = new ImageIcon(imagePath)
                .getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(image);
    }

    private String shortenText(String text, int maxLength) {
        if (text == null) {
            return "";
        }

        if (text.length() <= maxLength) {
            return text;
        }

        return text.substring(0, maxLength) + "...";
    }

    private void showDatabaseError(SQLException exception) {
        exception.printStackTrace();

        JOptionPane.showMessageDialog(
                this,
                "Không thể tải danh sách sản phẩm từ CSDL:\n" + exception.getMessage(),
                "Lỗi CSDL",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShopGUI().setVisible(true));
    }
}
