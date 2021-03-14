package com.lifescience.business;

import com.lifescience.database.JDBCPostgreSQL;

import java.util.ArrayList;

public class Librarian {
    static long ID = 0;
    long id;

    Library library;
    String name;

    public Librarian(Library library) {
        this.library = library;
        id = ID++;
    }

    public Librarian(Library library, String name) {
        this.library = library;
        this.name = name;
        id = ID++;
    }

    public void getArrayBooksByReader(int readerId) {
        ArrayList<Book> books = library.getArrayBooksByReader(readerId);
    }

    public String getAuthorBook(String cipher) {
        return library.getBook(cipher).getAuthor();
    }

    public String getNameBook(String cipher) {
        return library.getBook(cipher).getName();
    }

    public void addReaderInLibrary(String name) {
        library.addReaderInLibrary(name);
    }

   public void addBookInLibrary(String name, String author, String cipher, int amount) {
        library.addBookInLibrary(new Book(name, author, cipher, amount));
    }

    public void addBookInLibrary(Book book) {
        library.addBookInLibrary(book);
    }

    public void writeOffBookFromLibrary(String cipher) {
        library.writeOffBookFromLibrary(cipher);
    }

    public void updateCypherOfBookInLibrary(String lastCypher, String newCypher) {
        library.updateCypherOfBookInLibrary(lastCypher,newCypher);
    }
}
