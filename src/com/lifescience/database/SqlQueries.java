package com.lifescience.database;

public class SqlQueries {

    public static final String BOOK_TABLE_SQL_QUERY = "SELECT * FROM book";
    public static final String READER_TABLE_SQL_QUERY = "SELECT * FROM reader";
    public static final String LIBRARIAN_TABLE_SQL_QUERY = "SELECT * FROM librarian";

    public static final String BOOKS_FOR_READER_TABLE_SQL_QUERY =
            "select tb.reader reader_id, b.cypher cypher, b.name book_name,\n" +
                    "       b.author author, tb.amount amount\n" +
                    "    from reader as r\n" +
                    "    join taken_books tb on r.id = tb.reader\n" +
                    "    join book b on tb.cypher = b.cypher";

    public static final String INSERT_READER_SQL_QUERY = "insert into reader values ";
    public static final String INSERT_BOOK_SQL_QUERY = "insert into book values ";
    public static final String UPDATE_CYPHER_OF_BOOK_SQL_QUERY = "update book set cypher=";
    public static final String DELETE_BOOK_SQL_QUERY = "delete from book where cypher=";
}
