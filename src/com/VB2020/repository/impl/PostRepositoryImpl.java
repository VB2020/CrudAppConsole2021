package com.VB2020.repository.impl;

import com.VB2020.ioutils.IoUtils;
import com.VB2020.model.Post;
import com.VB2020.model.PostStatus;
import com.VB2020.repository.PostRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PostRepositoryImpl implements PostRepository {
    File fileName = new File("./src/com/VB2020/resource/Posts.txt");

    @Override
    public Post getById(Integer id) throws FileNotFoundException {
        List<Post> posts = getAll();
        return posts.stream().filter(a -> a.getId() == id).findFirst().orElse(new Post());
    }

    @Override
    public List<Post> getAll() throws FileNotFoundException {
        if (IoUtils.ReadFromFile(fileName) == null){
            return new ArrayList<>();
        }
        else{
            return IoUtils.ReadFromFile(fileName);
        }
    }

    @Override
    public void save(Post post) throws FileNotFoundException {
        List<Post> posts = getAll();
        AtomicBoolean flag = new AtomicBoolean(false);
        try{
            posts.forEach((a) -> {
                if (a.getId() == post.getId()) {
                    a.setId(post.getId());
                    a.setContent(post.getContent());
                    a.setUpdated(new Date().toString());
                    a.setPostLabelList(post.getPostLabelList());
                    a.setPostStatus(PostStatus.ACTIVE);
                    flag.set(true);
                }
            });
            if (!flag.get()){
                posts.add(post);
            }
            IoUtils.WriteToFile(posts, fileName);
        }
        catch (Exception er){
            System.out.println("Id not exist");
        }

    }

    @Override
    public void deleteById(Integer id) throws Exception {
        List<Post> posts = getAll();
        posts.forEach((a) ->
        {
            if (a.getId() == id) {
                a.setUpdated(new Date().toString());
                a.setPostStatus(PostStatus.DELETED);
            }
        });
        IoUtils.WriteToFile(posts, fileName);
    }
}

