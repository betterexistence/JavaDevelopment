import java.sql.*;

public class JavaDBConnectivity {
    public static void main(String[] args) throws SQLException {
        JDBCClass jdbcClass = new JDBCClass();
        jdbcClass.createTable();
        //jdbcClass.insertRow();
        jdbcClass.printTable();
    }
}

class JDBCClass {
    private Connection connection;
    private Statement statement;

    public JDBCClass() throws SQLException {
        this.connection = getNewConnection();
        this.statement = connection.createStatement();
    }

    public void updateRow() throws SQLException {
        String update = "UPDATE shop SET NAME='neon' WHERE ID = '10'";
        statement.executeUpdate(update);
    }

    public void insertRow() throws SQLException {
        int id = 1;
        if(requestValidation(id)){
            String insert = String.format("INSERT INTO shop " +
                    "VALUES(%2d, 'butter')", id);
            statement.executeUpdate(insert);
        }else System.out.println(String.format("Невозможно добавить строку, потому что строка с Id =%2d существует!", id));
    }

    public void deleteRow() throws SQLException {
        int id = 10;
        String delete = String.format("DELETE FROM testusers WHERE ID = %2d", id);
        statement.executeUpdate(delete);
    }

    public void printTable() throws SQLException {
        String request = "SELECT * FROM shop";
        ResultSet resultSet = statement.executeQuery(request);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnNumber = resultSetMetaData.getColumnCount();
        for(int columnNameIndex = 1; columnNameIndex <= columnNumber; columnNameIndex++){
            System.out.print(resultSetMetaData.getColumnName(columnNameIndex) + "\t");
        }
        System.out.println();
        while (resultSet.next()){
            for(int i = 1; i <= columnNumber; i++){
                System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
        }
    }

    public void createTable() throws SQLException {
        String tableName = "shop";
        if(!tableValidation(tableName)){
            String createTable =
                    "CREATE TABLE shop(\n" +
                            "   ID INT PRIMARY KEY     NOT NULL,\n" +
                            "   ITEM           TEXT    NOT NULL\n" +
                            ")";
            statement.executeUpdate(createTable);
        }else System.out.println(String.format("Таблица с таким названием %s уже существует!", tableName));
    }

    private boolean tableValidation(String tableName) throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet tables = databaseMetaData.getTables(null, null, tableName, null);
        return tables.next();
    }

    private Boolean requestValidation(int id) throws SQLException {
        String requestValidation = String.format("SELECT * FROM shop WHERE ID = '%2d'", id);
        PreparedStatement preparedStatement = connection.prepareStatement(requestValidation);
        return preparedStatement.execute();
    }

    private Connection getNewConnection() throws SQLException{
        String url = "jdbc:postgresql://localhost:5432/shop";
        String user = "postgres";
        String password = "masterkey";
        return DriverManager.getConnection(url, user, password);
    }
}
