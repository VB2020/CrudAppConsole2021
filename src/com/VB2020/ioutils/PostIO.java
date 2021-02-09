package com.VB2020.ioutils;

import com.VB2020.model.Post;
import com.VB2020.model.PostStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PostIO {
    public static int getMaxId(List<Post> t){
        int maxId;
        if(t.isEmpty()){
            maxId = 0;
        }
        else {
            t.sort(Comparator.comparing(Post::getId));
            maxId = t.get(t.size() - 1).getId();;
        }
        return maxId;
    }

    public static boolean containPost(List<Post> postList, Post post){
        AtomicBoolean flag = new AtomicBoolean(false);
        postList.forEach((a) -> {
            if (a.getId() == post.getId()){
                flag.set(true);
            }
        });
        return flag.get();
    }

    public static List<Post> delPosts(List<Post> l1, List<Post> l2){
        List<Post> res = new ArrayList<>();
        l1.stream().filter((a) -> !containPost(l2, a)).filter((a) -> !a.getPostStatus().equals(PostStatus.DELETED))
                .forEach((a) -> res.add(a));
        return res;
    }

    public static List<Post> notDelPosts(List<Post> l1, List<Post> l2){
        List<Post> res = new ArrayList<>();
        l1.stream().filter((a) -> containPost(l2, a)).filter((a) -> !a.getPostStatus().equals(PostStatus.DELETED))
                .forEach((a) -> res.add(a));
        return res;
    }
}
