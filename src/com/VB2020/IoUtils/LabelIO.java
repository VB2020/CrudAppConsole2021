package com.VB2020.IoUtils;

import com.VB2020.model.Label;
import com.VB2020.view.LabelView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LabelIO {
    public static int getMaxId(List<Label> labels){
        int maxId;
        if(labels.isEmpty()){
            maxId = 0;
        }
        else {
            labels.sort(Comparator.comparing(Label::getId));
            maxId = labels.get(labels.size() - 1).getId();;
        }
        return maxId;
    }

    public static boolean containLabel(List<Label> labels, Label label){
        AtomicBoolean flag = new AtomicBoolean(false);
        labels.forEach((any_label) -> {
            if (any_label.getId() == label.getId()){
                flag.set(true);
            }
        });
        return flag.get();
    }

      public static List<Label> delLabel(List<Label> l1, List<Label> l2){
        List<Label> res = new ArrayList<>();
        l1.stream().filter((a) -> !containLabel(l2, a)).filter((a) -> !a.getName().equals(LabelView.deleted))
        .forEach((a) -> res.add(a));
        return res;
    }

      public static List<Label> notDelLabel(List<Label> l1, List<Label> l2){
        List<Label> res = new ArrayList<>();
        l1.stream().filter((a) -> containLabel(l2, a)).filter((a) -> !a.getName().equals(LabelView.deleted))
                .forEach((a) -> res.add(a));
        return res;
    }
}
