package ru.netology.domain;

import java.util.Comparator;

public class ComparatorIssue implements Comparator<Issue> {
    private Issue o1;
    private Issue o2;

    @Override
    public int compare(Issue o1, Issue o2) {
        this.o1 = o1;
        this.o2 = o2;
        return o1.getData() - o2.getData();
    }
}
