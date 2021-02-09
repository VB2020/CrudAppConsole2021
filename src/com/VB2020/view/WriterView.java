package com.VB2020.view;

import com.VB2020.model.Writer;

import java.util.List;

public class WriterView {
    public static final String deleted = "This writer was deleted";

    public static void show(){
        System.out.println(ForConsole.BORDER.getMessage());
        String mainMessage = "Choose an action with writer:\n" +
                " 1. Create\n" +
                " 2. Edit name\n" +
                " 3. Delete\n" +
                " 4. Show writer by id\n" +
                " 5. Create or edit posts list for writer by ID\n" +
                " 6. Main menu\n" +
                " 7. Exit";
        System.out.println(mainMessage);
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void showFirstName(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Enter first name: ");
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void showLastName(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Enter last name: ");
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void showCreateList(){
        System.out.println(ForConsole.BORDER.getMessage());
        String mainMessage = "Add post in posts list for writer ?\n" +
                " 1. Yes\n" +
                " 2. No" ;
        System.out.println(mainMessage);
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void editId(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Enter writer id: ");
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void showWritersList(List<Writer> writers){
        System.out.println(ForConsole.BORDER.getMessage());
        writers.stream().filter((any_writer) -> !any_writer.getLastName().equals(WriterView.deleted)).forEach(
                (a) -> System.out.println("Id: " + a.getId() + " | Firstname: " + a.getFirstName() +
                        " | Lastname: " + a.getLastName() )
        );
    }

    public static void showWriter(Writer a){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Writer:");
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Id: " + a.getId() + " | Firstname: " + a.getFirstName() +
                        " | Lastname: " + a.getLastName());
    }

    public static void listEmpty(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Posts list for this writer is empty");
    }






}
