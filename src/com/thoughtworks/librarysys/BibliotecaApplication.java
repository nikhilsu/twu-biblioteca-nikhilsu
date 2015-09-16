package com.thoughtworks.librarysys;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//Biblioteca Application constructs and assembles the dependencies of the controller, initiates and runs it.
public class BibliotecaApplication {
    private Scanner scanner;
    private PrintStream printStream;
    private ConsoleView consoleView;
    private LibraryObserver libraryObserver;
    private Books books;
    private Movies movies;
    private MainMenu mainMenu;
    private InputParser inputParser;
    private WelcomeUser welcomeUser;
    private Controller controller;
    private Library library;
    private HashMap<String, MenuOptions> mapper;

    private ArrayList<Book> manufactureListOfBooks() {
        ArrayList<Book> listOfBooks = new ArrayList<>();
        Book bookOne = new Book("Kite Runner", "Khaled Hossieni", 2003);
        Book bookTwo = new Book("Inferno", "Dan Brown", 2012);
        Book bookThree = new Book("Gone Girl", "Gillian Flynn", 2014);
        listOfBooks.add(bookOne);
        listOfBooks.add(bookTwo);
        listOfBooks.add(bookThree);
        return listOfBooks;
    }

    private ArrayList<String> manufactureListOfMenuItems() {
        ArrayList<String> listOfMenuItems = new ArrayList<>();
        listOfMenuItems.add("1. List Books");
        listOfMenuItems.add("2. Checkout Book");
        listOfMenuItems.add("3. Return Book");
        listOfMenuItems.add("4. Quit");
        listOfMenuItems.add("5. List Movies");
        listOfMenuItems.add("6. Checkout Movie");
        return listOfMenuItems;
    }

    private WelcomeUser manufactureWelcomeUser() {
        return new WelcomeUser("Welcome To Biblioteca Application");
    }

    public void createDependencies() {
        this.scanner = new Scanner(System.in);
        this.printStream = new PrintStream(System.out);
        this.consoleView = new ConsoleView(scanner, printStream);
        this.libraryObserver = new LibraryObserver(consoleView);
        ArrayList<Book> listOfBooks = manufactureListOfBooks();
        ArrayList<Book> listOfCheckedOutBooks = new ArrayList<>();
        this.books = new Books(listOfBooks, listOfCheckedOutBooks, libraryObserver);
        ArrayList<Movie> listOfMovies = manufactureListOfMovies();
        this.movies = new Movies(listOfMovies, libraryObserver);
        this.library = new Library(books, movies);
        mapper = manufactureHashMapForParser();
        this.inputParser = new InputParser(library, consoleView, mapper);
        ArrayList<String> listOfMenuItems = manufactureListOfMenuItems();
        this.mainMenu = new MainMenu(listOfMenuItems, consoleView);
        this.welcomeUser = manufactureWelcomeUser();
    }

    private HashMap<String, MenuOptions> manufactureHashMapForParser() {
        HashMap<String, MenuOptions> mapper = new HashMap<>();
        mapper.put("1", new ListBooksMenuItem(library, consoleView));
        mapper.put("2", new CheckoutBookMenuItem(library, consoleView));
        mapper.put("3", new ReturnBookMenuItem(library, consoleView));
        mapper.put("4", new QuitMenuItem());
        mapper.put("5", new ListMovieMenuItem(library, consoleView));
        mapper.put("6", new CheckoutMovieMenuItem(library, consoleView));
        return mapper;
    }

    private ArrayList<Movie> manufactureListOfMovies() {
        ArrayList<Movie> listOfMovies = new ArrayList<>();
        Movie movieOne = new Movie("Matrix", 1999, "Andy Wachowski", 9);
        Movie movieTwo = new Movie("Gone Girl", 2014, "David Fincher", 8);
        listOfMovies.add(movieOne);
        listOfMovies.add(movieTwo);
        return listOfMovies;
    }

    public void initialiseApplication() {
        this.consoleView.printOnConsole(welcomeUser.toString());
        this.controller = new Controller(mainMenu, inputParser, consoleView);
    }

    public void start() {
        while (true)
            this.controller.run();
    }

    public static void main(String[] args) {
        BibliotecaApplication bibliotecaApplication = new BibliotecaApplication();
        bibliotecaApplication.createDependencies();
        bibliotecaApplication.initialiseApplication();
        bibliotecaApplication.start();
    }
}
