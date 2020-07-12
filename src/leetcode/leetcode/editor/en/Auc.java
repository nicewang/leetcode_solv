package leetcode.leetcode.editor.en;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Auc {

    public static void main(String[] args) {
        Auc auc = new Auc();
        int[] label = { 1, 1, 0, 0, 1, 0, 1, 0 };
        double[] score = { 0.9, 0.8, 0.3, 0.1, 0.4, 0.9, 0.66, 0.7 };
        System.out.println(auc.aucCal1(score, label));
        System.out.println(auc.aucCal2(score, label));
    }

    private double aucCal1(double[] score, int[] label) {
        double auc = 0.0;
        List<Pair<Integer, Double>> list = new ArrayList<>();
        int pos_count = 0, neg_count = 0;
        for (int i = 0; i < label.length; i++) {
            list.add(new Pair<>(label[i], score[i]));
            if (label[i] == 1) {
                pos_count++;
            } else {
                neg_count++;
            }
        }
        Collections.sort(list, Comparator.comparingDouble(Pair::getValue));
        Collections.reverse(list);
        boolean tpr_started = false;
        int tpr = 0;
        for (Pair<Integer, Double> pair : list) {
            int lab = pair.getKey();
            if (lab == 0) {
                if (tpr_started) {
                    auc += tpr;
                }
            } else {
                if (!tpr_started) {
                    tpr_started = true;
                }
                tpr++;
            }
        }
        auc /= (pos_count * neg_count);
        return auc;
    }

    private double aucCal2(double[] score, int[] label) {
        double auc = 0.0;
        List<Integer> pos = new ArrayList<>(), neg = new ArrayList<>();
        for (int i = 0; i < label.length; i++) {
            if (label[i] == 1) {
                pos.add(i);
            } else {
                neg.add(i);
            }
        }
        for (int i : pos) {
            for (int j : neg) {
                if (score[i] > score[j]) {
                    auc += 1.0;
                }
                //else if (score[i] == score[j]) {
                //    auc += 0.5;
                //}
            }
        }
        auc /= (pos.size() * neg.size());
        return auc;
    }

}
