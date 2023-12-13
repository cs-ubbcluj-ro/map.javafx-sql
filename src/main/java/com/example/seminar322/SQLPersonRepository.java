package com.example.seminar322;

import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class SQLPersonRepository extends MemoryRepository<Person> {

    private String dbLocation = "jdbc:sqlite:";

    private Connection conn;

    public SQLPersonRepository(String dbLocation) {
        this.dbLocation += dbLocation;
        openConnection();
        createSchema();
        loadData();
    }

    private void openConnection() {
        try {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(dbLocation);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            // FIXME not very nice :)
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS persons(id int, name varchar(200));");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void loadData() {
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from persons"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Person p = new Person(rs.getInt("id"), rs.getString("name"));
                    this.data.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex); // FIXME Sa lucram cu RepositoryException
        }
    }

    @Override
    public void add(Person o) throws RepositoryException {
        super.add(o);

        try {
            // try-with-resources
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO persons VALUES (?, ?)")) {
                // PreparedStatement impotriva SQL injection
                statement.setInt(1, o.getId());
                statement.setString(2, o.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        super.remove(id);
        // TODO db stuff
    }
}
