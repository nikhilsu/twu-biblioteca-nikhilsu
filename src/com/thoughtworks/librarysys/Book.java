package com.thoughtworks.librarysys;

//book has a name author and year of publication and returns them in a required format
public class Book {
    private String name;
    private String author;
    private int yearOfPublication;

    public Book(String name, String author, int yearOfPublication) {
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public String displayDetails() {
        return String.format("%s\t%s\t%s", this.name, this.author, yearOfPublication);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        Book that = (Book) obj;
        if (this.name.equals(that.name))
            return true;

        else
            return false;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        return result;
    }
}
