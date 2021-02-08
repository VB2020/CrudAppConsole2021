package com.VB2020.controller;

import com.VB2020.model.Label;
import com.VB2020.model.Post;
import com.VB2020.model.PostStatus;
import com.VB2020.model.Writer;
import com.VB2020.repository.impl.LabelRepositoryImpl;
import com.VB2020.repository.impl.PostRepositoryImpl;
import com.VB2020.repository.impl.WriterRepositoryImpl;
import com.VB2020.service.LabelService;
import com.VB2020.service.PostService;
import com.VB2020.service.WriterService;
import com.VB2020.view.ForConsole;
import com.VB2020.view.LabelView;
import com.VB2020.view.PostView;
import com.VB2020.view.WriterView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WriterController {
    public static void startM() throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        do {
            WriterView.show();
            String resp = sc.next();
            switch (resp) {
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
                    show();
                    break;
                case "5":
                    addListElementsForWriter();
                    break;
                case "6":
                    isExit = true;
                    MainController.startM();
                    break;
                case "7":
                    isExit = true;
                    break;
                default:
                    System.out.println(ForConsole.ERROR_INPUT.getMessage());
                    break;
            }
        } while (!isExit);
        sc.close();
    }

    public static void create() throws Exception {
        Scanner sc = new Scanner(System.in);
        WriterRepositoryImpl wR = new WriterRepositoryImpl();
        PostRepositoryImpl pR = new PostRepositoryImpl();
        boolean isExit = false;
        WriterView.showFirstName();
        String fName = sc.next();
        int demonId = WriterService.getMaxId(wR.getAll());
        Writer newWriter = new Writer(demonId + 1, fName, "");
        WriterView.showLastName();
        String lName = sc.next();
        newWriter.setLastName(lName);
        newWriter.setPostsList(new ArrayList<>());
        try {
            do {
                WriterView.showCreateList();
                int ca = sc.nextInt();
                switch (ca){
                    case 1:
                        try {
                            List<Post> posts = pR.getAll();
                            PostView.showPostsList(PostService.delPosts(posts, newWriter.getPostsList()));
                            PostView.editId();
                            int pId = sc.nextInt();
                            int maxId = PostService.getMaxId(posts);
                            if (pId > 0 && pId <= maxId) {
                                Post post = pR.getById(pId);
                                if (!post.getPostStatus().equals(PostStatus.DELETED) &&
                                        !PostService.containPost(newWriter.getPostsList(), post)) {
                                    newWriter.getPostsList().add(post);
                                }
                                else {
                                    System.out.println("Id not exist !!!!");
                                }
                            }
                            else {
                                System.out.println("Id not exist !!!!");
                            }
                        } catch (NullPointerException e) {
                            System.out.println("Id not exist !!!!");
                        }
                        break;
                    case 2:
                        isExit = true;
                        break;
                }
            }while (!isExit);
        } catch (InputMismatchException nfe) {
            System.out.println("It's not a number !!!");
        }
        //Если последний ID был удален то он остается в списке с пометкой dell
        //в этом случае когда добавляем новый экземпляр списка нам надо удалить
        //экземпляр с пометкой dell, здесь это и делается + часный случай когда
        //в списке пусто до добавления (maxID = 0 )
        if (demonId != 0){
            if (wR.getById(demonId).getLastName().equals(WriterView.dell)){
                wR.save(newWriter);
                wR.getById(demonId);
            }
            else {
                wR.save(newWriter);
            }
        }
        else {
            wR.save(newWriter);
        }
    }

    public static void edit() throws Exception {
        Scanner sc = new Scanner(System.in);
        WriterRepositoryImpl wR = new WriterRepositoryImpl();
        boolean isExit = false;
        List<Writer> writers = wR.getAll();
        WriterView.showWritersList(writers);
        int demonId = WriterService.getMaxId(writers);
        try {
            do {
                WriterView.editId();
                int id = sc.nextInt();
                Writer writer = wR.getById(id);
                if (id > 0 && demonId >= id && !writer.getLastName().equals(WriterView.dell)){
                    WriterView.showFirstName();
                    String fName = sc.next();
                    writer.setFirstName(fName);
                    WriterView.showLastName();
                    String lName = sc.next();
                    writer.setLastName(lName);
                    wR.save(writer);
                    isExit = true;
                }
                else {
                    System.out.println("Id not exist !!!!");
                }
            }
            while (!isExit);
        }
        catch (InputMismatchException nfe) {
            System.out.println("It's not a number !!!");
        }
    }

    public static void delete() throws Exception {
        Scanner sc = new Scanner(System.in);
        WriterRepositoryImpl wR = new WriterRepositoryImpl();
        boolean isExit = false;
        List<Writer> writers = wR.getAll();
        WriterView.showWritersList(writers);
        try {
            do {
                WriterView.editId();
                try{
                    int id = sc.nextInt();
                    int maxId = WriterService.getMaxId(writers);
                    Writer writer = wR.getById(id);
                    if (id > 0 && id <= maxId && !writer.getLastName().equals(LabelView.dell)) {
                        wR.deleteById(id);
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

    public static void show() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        WriterRepositoryImpl wR = new WriterRepositoryImpl();
        PostRepositoryImpl pR = new PostRepositoryImpl();
        LabelRepositoryImpl lR = new LabelRepositoryImpl();
        boolean isExit = false;
        List<Writer> writers = wR.getAll();
        List<Post> posts = pR.getAll();
        List<Label> labels = lR.getAll();
        WriterView.showWritersList(writers);
        int demonId = WriterService.getMaxId(writers);
        try{
            do {
                WriterView.editId();
                int id = sc.nextInt();
                Writer writer = wR.getById(id);
                if (id > 0 && demonId >= id && !writer.getLastName().equals(WriterView.dell)
                && WriterService.containWriter(writers, writer)){
                    if (!writer.getPostsList().isEmpty()) {
                        WriterView.showWriter(writer);
                        //Проверка что посты у писателя содержатся в общем списке постов (не удалены)
                        List<Post> tmpPostList = PostService.notDelPosts(posts, writer.getPostsList());
                        tmpPostList.forEach((a) -> {
                            PostView.showPost(a);
                            LabelView.showLabelsList(LabelService.notDelLabel(labels, a.getPostLabelList()));
                        });
                        isExit = true;
                    }
                    else{
                        WriterView.showWriter(writer);
                        WriterView.listEmpty();
                    }
                }
                else {
                    System.out.println("Id not exist !!!!");
                }
            }
            while (!isExit);
        }
        catch (InputMismatchException nfe) {
            System.out.println("It's not a number !!!");
        }
    }

    public static void addListElementsForWriter() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        WriterRepositoryImpl wR = new WriterRepositoryImpl();
        PostRepositoryImpl pR = new PostRepositoryImpl();
        boolean isExit = false;
        List<Writer> writers = wR.getAll();
        List<Post> posts = pR.getAll();
        WriterView.showWritersList(writers);
        int demonId = WriterService.getMaxId(writers);
        int maxId = PostService.getMaxId(posts);
        try{
            do {
                WriterView.editId();
                int id = sc.nextInt();
                Writer writer = wR.getById(id);
                if (id > 0 && demonId >= id && !writer.getLastName().equals(WriterView.dell)
                        && WriterService.containWriter(writers, writer)) {
                    do {
                        if (!writer.getPostsList().isEmpty()) {
                            WriterView.showWriter(writer);
                            System.out.println(ForConsole.BORDER.getMessage());
                            System.out.println("This writer contains:");
                            PostView.showPostsList(PostService.notDelPosts(posts, writer.getPostsList()));
                            System.out.println(ForConsole.BORDER.getMessage());
                            System.out.println("You can add this posts:");
                            PostView.showPostsList(PostService.delPosts(posts, writer.getPostsList()));
                        } else {
                            WriterView.showWriter(writer);
                            WriterView.listEmpty();
                            System.out.println("You can add this posts:");
                            PostView.showPostsList(posts);
                        }

                        PostView.showCancel();
                        PostView.editId();
                        int postId = sc.nextInt();
                        Post newPost = pR.getById(postId);
                        if (postId > 0 && postId <= maxId && !PostService.containPost(writer.getPostsList(), newPost)) {
                            WriterService.addPost(writer.getPostsList(), newPost);
                        } else if (postId == 0) {
                            wR.save(writer);
                            isExit = true;
                        } else {
                            System.out.println("Id not exist !!!!");
                        }
                    }while (!isExit);
                }
                else {
                    System.out.println("Id not exist !!!!");
                }
            }
            while (!isExit);
        }
        catch (InputMismatchException nfe) {
            System.out.println("It's not a number !!!");
        }
    }
}
