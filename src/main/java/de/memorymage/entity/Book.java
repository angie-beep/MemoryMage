package de.memorymage.entity;

import java.util.ArrayList;

public class Book {

    String title;
    ArrayList<Page> pages;
    int numberOfPages;
    String color;

    public Book(String title) {
        this.title = title;
        this.numberOfPages = 0;
        pages = new ArrayList<>();
    }

    public void addPage(Page page) {
        numberOfPages++;
        pages.add(page);
        page.pageNumber = numberOfPages;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }

    public void getRight(){
        pages.get(pages.size()-1).isCorrect = true;
    }

    public void getWrong(){
        pages.get(pages.size()-1).isCorrect = false;
    }

    public void removePage(Page page) {
        pages.remove(page);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void increaseNumberOfPages(){
        this.numberOfPages++;
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }
}
