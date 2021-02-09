package com.VB2020.repository.impl;

import com.VB2020.ioutils.IoUtils;
import com.VB2020.model.Label;
import com.VB2020.repository.LabelRepository;
import com.VB2020.service.LabelService;
import com.VB2020.view.LabelView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class LabelRepositoryImpl implements LabelRepository {
    File fileName = new File("./src/com/VB2020/resource/Labels.txt");

    @Override
    public Label getById(Integer id) throws FileNotFoundException {
        List<Label> labels = getAllInternal();
        return labels.stream().filter(a -> a.getId() == id).findFirst().orElse(new Label());
    }

    @Override
    public List<Label> getAll() throws FileNotFoundException {
        if (Objects.isNull(IoUtils.readFromFile(fileName))){
            return new ArrayList<>();
        }
        else{
            return IoUtils.readFromFile(fileName);
        }
    }

    @Override
    public void save(Label label) throws FileNotFoundException {
        List<Label> labels = getAllInternal();
        AtomicBoolean flag = new AtomicBoolean(false);
        try{
            labels.forEach((a) -> {
                if (a.getId() == label.getId()) {
                    a.setId(label.getId());
                    a.setName(label.getName());
                    flag.set(true);
                }
            });
            if (!flag.get()){
                labels.add(label);
            }
            IoUtils.writeToFile(labels, fileName);
        }
        catch (Exception er){
            System.out.println("Id not exist");
        }

    }

    @Override
    public void deleteById(Integer id) throws Exception {
        List<Label> labels = getAllInternal();
        if (LabelService.getMaxId(labels) == id) {
            labels.forEach((a) ->
            {
                if (a.getId() == id) {
                    a.setName(LabelView.dell);
                }
            });
        } else {
            labels.removeIf((a) -> a.getId() == id);
        }
        IoUtils.writeToFile(labels, fileName);
    }

    private List<Label> getAllInternal() throws FileNotFoundException {
        if (Objects.isNull(IoUtils.readFromFile(fileName))){
            return new ArrayList<>();
        }
        else{
            return IoUtils.readFromFile(fileName);
        }
    }
}
