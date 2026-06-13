import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductDAO chịu trách nhiệm truy vấn dữ liệu sản phẩm từ CSDL.
 * DAO = Data Access Object, tức là lớp chuyên làm việc với database.
 */
public class ProductDAO {
    private static final String SELECT_ALL_PRODUCTS_SQL =
            "SELECT title, price, brand, description, image_path FROM products";

    public List<Product> getAllProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();

        try (Connection databaseConnection = DatabaseConnection.getConnection();
             Statement statement = databaseConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_PRODUCTS_SQL)) {

            while (resultSet.next()) {
                Product product = mapResultSetToProduct(resultSet);
                productList.add(product);
            }
        }

        return productList;
    }

    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getString("title"),
                resultSet.getString("price"),
                resultSet.getString("brand"),
                resultSet.getString("description"),
                resultSet.getString("image_path")
        );
    }
}
