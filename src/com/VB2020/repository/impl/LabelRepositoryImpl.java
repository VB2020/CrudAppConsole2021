package com.VB2020.repository.impl;

import com.VB2020.IoUtils.IoUtils;
import com.VB2020.IoUtils.LabelIO;
import com.VB2020.model.Label;
import com.VB2020.repository.LabelRepository;
import com.VB2020.view.LabelView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LabelRepositoryImpl implements LabelRepository {
    File fileName = new File("./src/com/VB2020/resource/Labels.txt");

    @Override
    public Label getById(Integer id) throws FileNotFoundException {
        List<Label> labels = getAllInternal();
        return labels.stream().filter(any_label -> any_label.getId() == id).findFirst().orElse(new Label());
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
        try {
            labels.add(label);
            IoUtils.writeToFile(labels, fileName);
        }
        catch (Exception er){
            System.out.println("Id not exist");
        }

    }

    @Override
    public void deleteById(Integer id) throws Exception {
        List<Label> labels = getAllInternal();
        if (LabelIO.getMaxId(labels) == id) {
            labels.forEach((any_label) ->
            {
                if (any_label.getId() == id) {
                    any_label.setName(LabelView.deleted);
                }
            });
        } else {
            labels.removeIf((any_label) -> any_label.getId() == id);
        }
        IoUtils.writeToFile(labels, fileName);
    }

    @Override
    public void update(Label label) throws FileNotFoundException {
        List<Label> labels = getAllInternal();
         try{
            labels.forEach((any_label) -> {
                if (any_label.getId() == label.getId()) {
                    any_label.setId(label.getId());
                    any_label.setName(label.getName());
                }
            });
                labels.add(label);
            IoUtils.writeToFile(labels, fileName);
        }
        catch (Exception er){
            System.out.println("Id not exist");
        }

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
