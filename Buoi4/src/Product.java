public class Product {
    private final String title;
    private final String price;
    private final String brand;
    private final String description;
    private final String imagePath;

    public Product(String title, String price, String brand, String description, String imagePath) {
        this.title = title;
        this.price = price;
        this.brand = brand;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}
