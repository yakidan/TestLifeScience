package com.lifescience.business;


public class Book {
    String name;

    public int getAmount() {
        return amount;
    }

    int amount = 1;

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCypher() {
        return cypher;
    }

    String author;

    public void setCypher(String cypher) {
        this.cypher = cypher;
    }

    String cypher;

    Book( String cypher,String name, String author) {
        this.name = name;
        this.author = author;
        this.cypher = cypher;
    }

    public Book( String cypher,String name, String author, int amount) {
        this.name = name;
        this.author = author;
        this.cypher = cypher;
        this.amount = amount;

    }

}
