package com.VB2020.repository.impl;

import com.VB2020.IoUtils.IoUtils;
import com.VB2020.model.Writer;
import com.VB2020.repository.WriterRepository;
import com.VB2020.view.WriterView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class WriterRepositoryImpl implements WriterRepository {
    File fileName = new File("./src/com/VB2020/resource/Writers.txt");

    @Override
    public Writer getById(Integer id) throws FileNotFoundException {
        List<Writer> writers = getAllInternal();
        return writers.stream().filter(any_writer -> any_writer.getId() == id).findFirst().orElse(new Writer());
    }

    @Override
    public List<Writer> getAll() throws FileNotFoundException {
        if (Objects.isNull(IoUtils.readFromFile(fileName))){
            return new ArrayList<>();
        }
        else{
            return IoUtils.readFromFile(fileName);
        }
    }

    @Override
    public void save(Writer writer) throws FileNotFoundException {
        List<Writer> writers = getAllInternal();
        AtomicBoolean flag = new AtomicBoolean(false);
        try{
            writers.forEach((any_writer) -> {
                if (any_writer.getId() == writer.getId()) {
                    any_writer.setId(writer.getId());
                    any_writer.setFirstName(writer.getFirstName());
                    any_writer.setLastName(writer.getLastName());
                    any_writer.setPostsList(writer.getPostsList());
                    flag.set(true);
                }
            });
            if (!flag.get()){
                writers.add(writer);
            }
            IoUtils.writeToFile(writers, fileName);
        }
        catch (Exception er){
            System.out.println("Id not exist");
        }

    }

    @Override
    public void deleteById(Integer id) throws Exception {
        List<Writer> writers = getAllInternal();
        writers.forEach((any_writer) ->
        {
            if (any_writer.getId() == id) {
                any_writer.setLastName(WriterView.deleted);
            }
        });
        IoUtils.writeToFile(writers, fileName);
    }

    public List<Writer> getAllInternal() throws FileNotFoundException {
        if (Objects.isNull(IoUtils.readFromFile(fileName))){
            return new ArrayList<>();
        }
        else{
            return IoUtils.readFromFile(fileName);
        }
    }
}
