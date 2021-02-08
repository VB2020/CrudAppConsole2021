package com.VB2020.service;

import com.VB2020.model.Label;
import com.VB2020.view.LabelView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LabelService {
    public static int getMaxId(List<Label> t){
        int maxId;
        if(t.isEmpty()){
            maxId = 0;
        }
        else {
            t.sort(Comparator.comparing(Label::getId));
            maxId = t.get(t.size() - 1).getId();;
        }
        return maxId;
    }

    public static boolean containLabel(List<Label> labelList, Label label){
        AtomicBoolean flag = new AtomicBoolean(false);
        labelList.forEach((a) -> {
            if (a.getId() == label.getId()){
                flag.set(true);
            }
        });
        return flag.get();
    }

      public static List<Label> delLabel(List<Label> l1, List<Label> l2){
        List<Label> res = new ArrayList<>();
        l1.stream().filter((a) -> !containLabel(l2, a)).filter((a) -> !a.getName().equals(LabelView.dell))
        .forEach((a) -> res.add(a));
        return res;
    }

      public static List<Label> notDelLabel(List<Label> l1, List<Label> l2){
        List<Label> res = new ArrayList<>();
        l1.stream().filter((a) -> containLabel(l2, a)).filter((a) -> !a.getName().equals(LabelView.dell))
                .forEach((a) -> res.add(a));
        return res;
    }
}
