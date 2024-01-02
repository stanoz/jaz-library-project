package com.library.library_client;

import com.library.library_client.contract.ResultsDto;

public class Main {
    public static void main(String[] args) {
        test();
    }
    private static void test(){
        IBooksClientSettings booksClientSettings = new BooksClientSettings();
        IBooksClient booksClient = new BooksClient(booksClientSettings);
        ResultsDto resultsDto = booksClient.getBooks();
        System.out.println("hi");
    }
}
