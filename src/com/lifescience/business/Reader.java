package com.lifescience.business;

import java.util.ArrayList;


public class Reader {
    static int ID = 0;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    String name = "";
    int id;
    ArrayList<Book> takenBooks = new ArrayList<>();

    Reader() {
        id = ID++;
    }

    Reader(String name) {
        id = ID++;
        this.name = name;
    }

    Book addBook(Book book) {
        takenBooks.add(book);
        return book;
    }

    ArrayList<Book> getTakenBooks() {
        return this.takenBooks;
    }

    void printTakenBooks() {
        for (Book book :
                takenBooks) {
            System.out.println("Cypher= " + book.getCypher() + " Name = " + book.getName()
                    + " Author = " + book.getAuthor() + " Amount = " + book.getAmount());
        }
    }
}
