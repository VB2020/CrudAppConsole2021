package com.VB2020.repository.impl;

import com.VB2020.IoUtils.IoUtils;
import com.VB2020.model.Post;
import com.VB2020.model.PostStatus;
import com.VB2020.repository.PostRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class PostRepositoryImpl implements PostRepository {
    File fileName = new File("./src/com/VB2020/resource/Posts.txt");

    @Override
    public Post getById(Integer id) throws FileNotFoundException {
        List<Post> posts = getAllInternal();
        return posts.stream().filter(any_post -> any_post.getId() == id).findFirst().orElse(new Post());
    }

    @Override
    public List<Post> getAll() throws FileNotFoundException {
        if (Objects.isNull(IoUtils.readFromFile(fileName))){
            return new ArrayList<>();
        }
        else{
            return IoUtils.readFromFile(fileName);
        }
    }

    @Override
    public void save(Post post) throws FileNotFoundException {
        List<Post> posts = getAllInternal();
        AtomicBoolean flag = new AtomicBoolean(false);
        try{
            posts.forEach((any_post) -> {
                if (any_post.getId() == post.getId()) {
                    any_post.setId(post.getId());
                    any_post.setContent(post.getContent());
                    any_post.setUpdated(new Date().toString());
                    any_post.setPostLabelList(post.getPostLabelList());
                    any_post.setPostStatus(PostStatus.ACTIVE);
                    flag.set(true);
                }
            });
            if (!flag.get()){
                posts.add(post);
            }
            IoUtils.writeToFile(posts, fileName);
        }
        catch (Exception er){
            System.out.println("Id not exist");
        }

    }

    @Override
    public void deleteById(Integer id) throws Exception {
        List<Post> posts = getAllInternal();
        posts.forEach((any_post) ->
        {
            if (any_post.getId() == id) {
                any_post.setUpdated(new Date().toString());
                any_post.setPostStatus(PostStatus.DELETED);
            }
        });
        IoUtils.writeToFile(posts, fileName);
    }

    private List<Post> getAllInternal() throws FileNotFoundException {
        if (Objects.isNull(IoUtils.readFromFile(fileName))){
            return new ArrayList<>();
        }
        else{
            return IoUtils.readFromFile(fileName);
        }
    }


}

