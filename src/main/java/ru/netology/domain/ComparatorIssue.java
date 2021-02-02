package ru.netology.domain;

import java.util.Comparator;

public class ComparatorIssue implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getData() - o2.getData();
    }
}
