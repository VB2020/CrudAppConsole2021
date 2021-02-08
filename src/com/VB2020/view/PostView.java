package com.VB2020.view;

import com.VB2020.model.Post;
import com.VB2020.model.PostStatus;

import java.util.List;

public class PostView {
    public static final String dell = "This post was deleted";
    public static void show(){
        System.out.println(ForConsole.BORDER.getMessage());
        String mainMessage = "Choose an action with posts:\n" +
                " 1. Create\n" +
                " 2. Edit content\n" +
                " 3. Delete\n" +
                " 4. Show post by id\n" +
                " 5. Create or edit labels list for post by ID\n" +
                " 6. Main menu\n" +
                " 7. Exit";
        System.out.println(mainMessage);
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void showAddLabel(){
        System.out.println(ForConsole.BORDER.getMessage());
        String mainMessage = "Add label in labels list for post ?\n" +
                " 1. Yes\n" +
                " 2. No" ;
        System.out.println(mainMessage);
        System.out.println(ForConsole.BORDER.getMessage());

    }

    public static void create(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Enter post content: ");
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void idNotEx(){
        System.out.println("This Id not exist");
    }

    public static void editId(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Enter post id: ");
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void editName(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Enter new post content: ");
        System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void showCancel(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Press '0' for exit");
        //System.out.println(ForConsole.BORDER.getMessage());
    }

    public static void showPostsList(List<Post> posts){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Posts list:");
        System.out.println(ForConsole.BORDER.getMessage());
        posts.stream().filter((a) -> !a.getPostStatus().equals(PostStatus.DELETED)).forEach(
                (a) -> System.out.println("Id: " + a.getId() + " | Content: " + a.getContent() +
                        " | Created: " + a.getCreated() + " | Updated: " + a.getUpdated())
        );
    }

    public static void showPost(Post a){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Post:");
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Id: " + a.getId() + " | Content: " + a.getContent() +
                " | Created: " + a.getCreated() + " | Updated: " + a.getUpdated());
    }

    public static void listEmpty(){
        System.out.println(ForConsole.BORDER.getMessage());
        System.out.println("Labels list for this post is empty");
    }
}
