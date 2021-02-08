package com.VB2020.view;

import com.VB2020.model.Label;

import java.util.Comparator;
import java.util.List;

public class LabelView {
    public static final String dell = "This label was deleted";

    public static void show(){
            System.out.println(ForConsole.BORDER.getMessage());
            String mainMessage = "Choose an action with labels:\n" +
                    " 1. Create\n" +
                    " 2. Edit\n" +
                    " 3. Delete\n" +
                    " 4. Show labels\n" +
                    " 5. Main menu\n" +
                    " 6. Exit";
            System.out.println(mainMessage);
            System.out.println(ForConsole.BORDER.getMessage());

    }

    public static void create(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Enter label name: ");
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void editId(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Enter label id: ");
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void editName(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Enter new label name: ");
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void showLabelsList(List<Label> labels){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Labels list:");
        System.out.println(ForConsole.BORDER.getMessage());
        labels.stream().filter((a) -> !a.getName().equals(LabelView.dell)).sorted(
                Comparator.comparing(Label::getId)).forEach(
                (a) -> System.out.println("Id: " + a.getId() + " | Name: " + a.getName())
        );
    }

}
