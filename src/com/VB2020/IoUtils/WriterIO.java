package com.VB2020.IoUtils;

import com.VB2020.model.Post;
import com.VB2020.model.Writer;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class WriterIO {
    public static int getMaxId(List<Writer> writers){
        int maxId;
        if(writers.isEmpty()){
            maxId = 0;
        }
        else {
            writers.sort(Comparator.comparing(Writer::getId));
            maxId = writers.get(writers.size() - 1).getId();;
        }
        return maxId;
    }

    public static boolean containWriter(List<Writer> writers, Writer writer){
        AtomicBoolean flag = new AtomicBoolean(false);
        writers.forEach((any_writer) -> {
            if (any_writer.getId() == writer.getId()){
                flag.set(true);
            }
        });
        return flag.get();
    }

    public static void addPost(List<Post> posts, Post post){
        Post newPost = new Post(post.getId(), post.getContent(), post.getPostStatus());
        posts.add(newPost);
    }
}
