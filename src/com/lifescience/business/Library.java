package com.lifescience.business;

import com.lifescience.database.JDBCPostgreSQL;
import com.lifescience.database.SqlQueries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Library {
    int id;
    static int ID;
    HashMap<String, Book> books = new HashMap<>();
    HashMap<Integer, Reader> readers = new HashMap<>();
    HashMap<Integer, Librarian> librarians = new HashMap<>();
    JDBCPostgreSQL jdbcPostgreSQL;



    public Library() {
        this.id = ID++;
    }

    public void connectDataBase() {
        jdbcPostgreSQL = new JDBCPostgreSQL();
        jdbcPostgreSQL.connect();
    }

    public void readData() {
        jdbcPostgreSQL.connect();
        readBooksFromDataBase();
        jdbcPostgreSQL.disconnect();
        jdbcPostgreSQL.connect();
        readLibrariansFromDataBase();
        jdbcPostgreSQL.disconnect();
        jdbcPostgreSQL.connect();
        readReadersFromDataBase();
        jdbcPostgreSQL.disconnect();
        jdbcPostgreSQL.connect();
        readBooksForReadersFromDataBase();
        jdbcPostgreSQL.disconnect();
    }

    public void readBooksFromDataBase() {
        ResultSet rs = jdbcPostgreSQL.query(SqlQueries.BOOK_TABLE_SQL_QUERY);
        try {
            while (rs.next()) {
                String name = rs.getString("name");
                String author = rs.getString("author");
                String cypher = rs.getString("cypher");
                int amount = rs.getInt("amount");
                books.put(cypher, new Book(name, author, cypher, amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readReadersFromDataBase() {
        ResultSet resultSetReader = jdbcPostgreSQL.query(SqlQueries.READER_TABLE_SQL_QUERY);

        try {
            while (resultSetReader.next()) {
                int id = resultSetReader.getInt("id");
                String name = resultSetReader.getString("name");
                readers.put(id, new Reader(name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readBooksForReadersFromDataBase() {
        ResultSet resultSetBooksForReader = jdbcPostgreSQL.query(SqlQueries.BOOKS_FOR_READER_TABLE_SQL_QUERY);
        try {
            while (resultSetBooksForReader.next()) {
                int reader_id = resultSetBooksForReader.getInt("reader_id");
                String cypher = resultSetBooksForReader.getString("cypher");
                String name = resultSetBooksForReader.getString("book_name");
                String author = resultSetBooksForReader.getString("author");
                int amount = resultSetBooksForReader.getInt("amount");
                Reader reader = readers.get(reader_id);
                reader.addBook(new Book(name, author, cypher, amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readLibrariansFromDataBase() {
        ResultSet rs = jdbcPostgreSQL.query(SqlQueries.LIBRARIAN_TABLE_SQL_QUERY );
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                librarians.put(id, new Librarian(this, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printBooks() {
        for (Map.Entry<String, Book> entryBook : books.entrySet()) {
            String cypher = entryBook.getKey();
            Book book = entryBook.getValue();
            System.out.println("cypher= " + cypher + " Name = " + book.getName()
                    + " Author = " + book.getAuthor() + " Amount = " + book.getAmount());
        }
    }

    public void printReaders() {
        for (Map.Entry<Integer, Reader> entryReader : readers.entrySet()) {
            int id = entryReader.getKey();
            Reader reader = entryReader.getValue();
            System.out.println("Reader Name: " + reader.getName() + "\n");
            reader.printTakenBooks();
            System.out.println();
        }
    }

    ArrayList<Book> getArrayBooksByReader(int readerId) {
        ArrayList<Book> books = readers.get(readerId).getTakenBooks();
        return books;
    }

    Book getBook(String cypher) {
        return books.get(cypher);
    }

    public void addReaderInLibrary(String name) {
        Reader reader = new Reader(name);
        readers.put(reader.getId(), reader);
        insertReaderInDataBase(reader);
    }

    public void insertReaderInDataBase(Reader reader) {
        String query = SqlQueries.INSERT_READER_SQL_QUERY + "(" + reader.getId() +
                ",'" + reader.getName() + "')";
        jdbcPostgreSQL.connect();
        jdbcPostgreSQL.execute(query);
        jdbcPostgreSQL.disconnect();
    }

    public void addBookInLibrary(Book book) {
        books.put(book.getCypher(), book);
        insertBookInDataBase(book);
    }

    public void insertBookInDataBase(Book book) {
        String query = SqlQueries.INSERT_BOOK_SQL_QUERY + "('" + book.getCypher() +
                "','" + book.getName() + "','" + book.getAuthor() + "'," + book.getAmount() + ")";
        jdbcPostgreSQL.connect();
        jdbcPostgreSQL.execute(query);
        jdbcPostgreSQL.disconnect();
    }

    public void writeOffBookFromLibrary(String cypher) {
        books.remove(cypher);
        deleleBookInDataBase(cypher);
    }

    public void deleleBookInDataBase(String cypher) {
        String query = SqlQueries.DELETE_BOOK_SQL_QUERY + "'" + cypher + "'";
        jdbcPostgreSQL.connect();
        jdbcPostgreSQL.execute(query);
        jdbcPostgreSQL.disconnect();
    }

    public void updateCypherOfBookInLibrary(String lastCypher, String newCypher) {
        Book book = books.get(lastCypher);
        book.setCypher(newCypher);
        books.remove(lastCypher);
        books.put(newCypher, book);
        updateCypherOfBookInDataBase(lastCypher, newCypher);
    }

    public void updateCypherOfBookInDataBase(String lastCypher, String newCypher) {
        String query = SqlQueries.UPDATE_CYPHER_OF_BOOK_SQL_QUERY + "'" + newCypher + "'" + " where cypher=" +
                "'" + lastCypher + "'";
        jdbcPostgreSQL.connect();
        jdbcPostgreSQL.execute(query);
        jdbcPostgreSQL.disconnect();
    }
}
