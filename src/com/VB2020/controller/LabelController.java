package com.VB2020.controller;

import com.VB2020.ioutils.LabelIO;
import com.VB2020.model.Label;
import com.VB2020.repository.impl.LabelRepositoryImpl;
import com.VB2020.view.ForConsole;
import com.VB2020.view.LabelView;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LabelController {

    public static void startM() throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        try {
            do {
                LabelView.show();
                String response = sc.next();
                switch (response) {
                    case "1":
                        create();
                        break;
                    case "2":
                        edit();
                        break;
                    case "3":
                        delete();
                        break;
                    case "4":
                        showAll();
                        break;
                    case "5":
                        isExit = true;
                        MainController.startM();
                        break;
                    case "6":
                        isExit = true;
                        break;
                    default:
                        System.out.println(ForConsole.ERROR_INPUT.getMessage());
                        break;
                }
            } while (!isExit);
            sc.close();
        }
        catch (NumberFormatException nfe){
            System.out.println("It's not a number !!!");
        }
    }

    public static void create() throws Exception {
        Scanner sc = new Scanner(System.in);
        LabelRepositoryImpl lR = new LabelRepositoryImpl();
        LabelView.create();
        String name = sc.next();
        int demonId = LabelIO.getMaxId(lR.getAll());
        if (demonId != 0) {
            if (lR.getById(demonId).getName().equals(LabelView.deleted)) {
                lR.save(new Label(demonId + 1, name));
                lR.deleteById(demonId);
            }
            else {
                lR.save(new Label(demonId + 1, name));
            }
        }
        else {
            lR.save(new Label(demonId + 1, name));
        }
    }

    public static void edit() throws Exception {
        Scanner sc = new Scanner(System.in);
        LabelRepositoryImpl lR = new LabelRepositoryImpl();
        boolean isExit = false;
        List<Label> labels = lR.getAll();
        LabelView.showLabelsList(labels);
        try {
            do {
                LabelView.editId();
                try {
                    int id = sc.nextInt();
                    int maxId = LabelIO.getMaxId(labels);
                    if (id > 0 && id <= maxId) {
                        Label label = lR.getById(id);
                        if (!label.getName().equals(LabelView.deleted)) {
                            LabelView.editName();
                            String name = sc.next();
                            label.setName(name);
                            lR.save(label);
                            isExit = true;
                        }
                        else {
                            System.out.println("Id not exist !!!!");
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("Id not exist !!!!");
                }
            }while (!isExit);
        } catch (InputMismatchException nfe) {
            System.out.println("It's not a number !!!");
        }
    }

    public static void delete() throws Exception {
        Scanner sc = new Scanner(System.in);
        LabelRepositoryImpl lR = new LabelRepositoryImpl();
        boolean isExit = false;
        List<Label> labels = lR.getAll();
        LabelView.showLabelsList(labels);
        try {
            do {
                LabelView.editId();
                try{
                    int id = sc.nextInt();
                    int maxId = LabelIO.getMaxId(labels);
                    Label label = lR.getById(id);
                    if (id > 0 && id <= maxId && !label.getName().equals(LabelView.deleted)) {
                        lR.deleteById(id);
                        isExit = true;
                    }
                    else {
                        System.out.println("Id not exist !!!!");
                    }
            } catch (NullPointerException e) {
                System.out.println("Id not exist !!!!");
            }
            }while (!isExit);
        }
        catch (InputMismatchException nfe){
            System.out.println("It's not a number !!!");
        }
    }

    public static void showAll() throws FileNotFoundException {
        LabelRepositoryImpl lR = new LabelRepositoryImpl();
        LabelView.showLabelsList(lR.getAll());
    }

}
