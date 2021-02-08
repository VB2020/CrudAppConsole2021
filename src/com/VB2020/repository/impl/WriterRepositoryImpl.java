package com.VB2020.repository.impl;

import com.VB2020.ioutils.IoUtils;
import com.VB2020.model.Writer;
import com.VB2020.repository.WriterRepository;
import com.VB2020.view.WriterView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class WriterRepositoryImpl implements WriterRepository {
    File fileName = new File("./src/com/VB2020/resource/Writers.txt");

    @Override
    public Writer getById(Integer id) throws FileNotFoundException {
        List<Writer> writers = getAll();
        return writers.stream().filter(a -> a.getId() == id).findFirst().orElse(new Writer());
    }

    @Override
    public List<Writer> getAll() throws FileNotFoundException {
        if (IoUtils.ReadFromFile(fileName) == null){
            return new ArrayList<>();
        }
        else{
            return IoUtils.ReadFromFile(fileName);
        }
    }

    @Override
    public void save(Writer writer) throws FileNotFoundException {
        List<Writer> writers = getAll();
        AtomicBoolean flag = new AtomicBoolean(false);
        try{
            writers.forEach((a) -> {
                if (a.getId() == writer.getId()) {
                    a.setId(writer.getId());
                    a.setFirstName(writer.getFirstName());
                    a.setLastName(writer.getLastName());
                    a.setPostsList(writer.getPostsList());
                    flag.set(true);
                }
            });
            if (!flag.get()){
                writers.add(writer);
            }
            IoUtils.WriteToFile(writers, fileName);
        }
        catch (Exception er){
            System.out.println("Id not exist");
        }

    }

    @Override
    public void deleteById(Integer id) throws Exception {
        List<Writer> writers = getAll();
        writers.forEach((a) ->
        {
            if (a.getId() == id) {
                a.setLastName(WriterView.dell);
            }
        });
        IoUtils.WriteToFile(writers, fileName);
    }
}
