package com.library.library_updater.books.updater;

public interface IUpdateBooks {
    void updateByResultPages(int numberOfPages);
    void updateByPagesRange(int pageFrom, int pageTo);
}
