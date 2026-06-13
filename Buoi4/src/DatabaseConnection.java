import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DatabaseConnection chịu trách nhiệm:
 * 1. Nạp SQLite JDBC Driver.
 * 2. Tìm thư mục gốc của project.
 * 3. Kết nối tới file db/products.db.
 * 4. Tạo bảng và thêm dữ liệu mẫu nếu CSDL chưa có dữ liệu.
 */
public class DatabaseConnection {
    private static final String SQLITE_DRIVER_CLASS = "org.sqlite.JDBC";
    private static final String DATABASE_FILE_PATH = "db/products.db";
    private static final String DATABASE_SCRIPT_FILE = "database.sql";
    private static final String SQLITE_URL_PREFIX = "jdbc:sqlite:";

    public static Connection getConnection() throws SQLException {
        loadSqliteDriver();

        File projectFolder = findProjectFolder();
        File databaseFile = new File(projectFolder, DATABASE_FILE_PATH);
        createParentFolderIfNeeded(databaseFile);

        Connection connection = DriverManager.getConnection(SQLITE_URL_PREFIX + databaseFile.getPath());
        initializeDatabaseIfNeeded(connection, projectFolder);

        return connection;
    }

    private static void loadSqliteDriver() throws SQLException {
        try {
            Class.forName(SQLITE_DRIVER_CLASS);
        } catch (ClassNotFoundException exception) {
            throw new SQLException(
                    "Khong tim thay SQLite JDBC Driver. Hay kiem tra file .jar trong thu muc lib.",
                    exception
            );
        }
    }

    private static File findProjectFolder() throws SQLException {
        File currentFolder = new File(System.getProperty("user.dir"));

        while (currentFolder != null) {
            File databaseScript = new File(currentFolder, DATABASE_SCRIPT_FILE);

            if (databaseScript.isFile()) {
                return currentFolder;
            }

            currentFolder = currentFolder.getParentFile();
        }

        throw new SQLException("Khong tim thay file database.sql trong project.");
    }

    private static void createParentFolderIfNeeded(File databaseFile) {
        File databaseFolder = databaseFile.getParentFile();

        if (databaseFolder != null && !databaseFolder.exists()) {
            databaseFolder.mkdirs();
        }
    }

    private static void initializeDatabaseIfNeeded(Connection connection, File projectFolder)
            throws SQLException {
        if (productsTableHasData(connection)) {
            return;
        }

        Path databaseScriptPath = new File(projectFolder, DATABASE_SCRIPT_FILE).toPath();
        executeSqlScript(connection, databaseScriptPath);
    }

    private static boolean productsTableHasData(Connection connection) {
        String countProductsSql = "SELECT COUNT(*) FROM products";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(countProductsSql)) {

            return resultSet.next() && resultSet.getInt(1) > 0;

        } catch (SQLException exception) {
            // Nếu bảng products chưa tồn tại thì xem như CSDL chưa được khởi tạo.
            return false;
        }
    }

    private static void executeSqlScript(Connection connection, Path scriptPath) throws SQLException {
        try {
            String scriptContent = new String(Files.readAllBytes(scriptPath), StandardCharsets.UTF_8);
            String[] sqlCommands = scriptContent.split(";");

            try (Statement statement = connection.createStatement()) {
                for (String sqlCommand : sqlCommands) {
                    String trimmedCommand = sqlCommand.trim();

                    if (!trimmedCommand.isEmpty()) {
                        statement.executeUpdate(trimmedCommand);
                    }
                }
            }
        } catch (IOException exception) {
            throw new SQLException("Khong doc duoc file database.sql.", exception);
        }
    }
}
