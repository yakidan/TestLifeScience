package com.lifescience.client;

import com.lifescience.business.Book;
import com.lifescience.business.Librarian;
import com.lifescience.business.Library;

public class Main {
    //Тестирование
    public static void main(String[] args) {
        Library library = new Library();
        library.connectDataBase();
        library.readData();
        Librarian librarian=new Librarian(library);
       librarian.addBookInLibrary(new Book("0218a34c-63de-4fbd-8e2a-4242a882f8d3","Евгений Онегин","Пушкин",5));
        librarian.updateCypherOfBookInLibrary("0218a34c-63de-4fbd-8e2a-4242a882f8d3","0218a34c-63de-4fbd-8e2a-4242a882f8d5");
       librarian.writeOffBookFromLibrary("0218a34c-63de-4fbd-8e2a-4242a882f8d5");

    }
}
