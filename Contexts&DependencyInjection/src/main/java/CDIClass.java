import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;
import java.io.*;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.sql.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class CDIClass {
    public static void main(String[] args) throws Exception {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        Item item = container.select(Item.class).get();
        item.print();
        item.update();
        item.print();
        container.shutdown();
    }
}

class PathString {
    Actions actions;
    Item item = new Item(actions);

    private String path = item.getPath();

    PathString() throws ClassNotFoundException {
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Produces @Named("getPath")
    public String getPath() {
        return path;
    }
}

class Item{
    private Actions actions;

    public String getPath() {
        return path;
    }

    private String path = "D:\\textdoc.txt";

    @Inject public Item(@Named("txt")Actions actions) throws ClassNotFoundException{
        this.actions = actions;
    }

    public void print() throws Exception {
        actions.print();
    }

    public void update() throws SQLException, IOException {
        actions.update();
    }
}

interface Actions {
    void print() throws SQLException, IOException;
    void create() throws SQLException, IOException;
    void insert() throws SQLException, IOException;
    void delete() throws SQLException;
    void update() throws SQLException, IOException;
}

@Named("db")
class DataBase implements Actions{
    //"jdbc:postgresql://localhost:5432/testdb"
    //postgres
    //masterkey
    private String url = "jdbc:postgresql://localhost:5432/testdb";
    private String user = "postgres";
    private String password = "masterkey";
    private Connection connection = DriverManager.getConnection(url, user, password);
    private Statement statement = connection.createStatement();

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DataBase() throws SQLException {
    }

    @Override
    public void print() throws SQLException {
        String request = "SELECT * FROM testusers";
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

    @Override
    public void create() throws SQLException {
        String tableName = "testusers";
        if(!tableValidation(tableName)){
            String createTable =
                    "CREATE TABLE TESTUSERS(\n" +
                            "   ID INT PRIMARY KEY     NOT NULL,\n" +
                            "   NAME           TEXT    NOT NULL,\n" +
                            "   AGE            INT     NOT NULL\n" +
                            ")";
            statement.executeUpdate(createTable);
        }else System.out.println(String.format("Таблица с таким названием %s уже существует!", tableName));
    }

    @Override
    public void insert() throws SQLException {
        int id = 4;
        if(!requestValidation(id)){
            String insert = String.format("INSERT INTO testusers " +
                    "VALUES(%2d, 'raze', 48)", id);
            statement.executeUpdate(insert);
        }else System.out.println(String.format("Невозможно добавить строку, потому что строка с Id =%2d существует!", id));
    }

    @Override
    public void delete() throws SQLException {
        int id = 10;
        String delete = String.format("DELETE FROM testusers WHERE ID = %2d", id);
        statement.executeUpdate(delete);
    }

    @Override
    public void update() throws SQLException {
        String update = "UPDATE testusers SET NAME='neon' WHERE ID = '10'";
        statement.executeUpdate(update);
    }

    private boolean tableValidation(String tableName) throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet tables = databaseMetaData.getTables(null, null, tableName, null);
        return tables.next();
    }

    private Boolean requestValidation(int id) throws SQLException {
        String requestValidation = String.format("SELECT * FROM testusers WHERE ID = '%2d'", id);
        PreparedStatement preparedStatement = connection.prepareStatement(requestValidation);
        return preparedStatement.execute();
    }
}

@Named("txt")
class TextDocument implements Actions{

    @Inject @Named("getPath")
    private String path;

    public TextDocument() {
    }

    public TextDocument(String path){
        this.path = path;
    }

    @Override
    public void print() throws IOException {
        String bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path))).readLine();
        System.out.println(bufferedReader);
    }

    @Override
    public void create() throws IOException {
        OutputStream outputStream = new FileOutputStream(path);
        outputStream.close();
    }

    @Override
    public void insert() throws IOException {
        try(BufferedWriter out = new BufferedWriter(new FileWriter(path))) {
            out.write("asdasdas");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        new File(path).delete();
    }

    @Override
    public void update(){
        try {
            String bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path))).readLine();
            BufferedWriter out = new BufferedWriter(new FileWriter(path));
            bufferedReader += "nevermore";
            out.write(bufferedReader);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

