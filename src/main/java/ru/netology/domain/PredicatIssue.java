package ru.netology.domain;

import java.util.List;
import java.util.function.Predicate;

public class PredicatIssue implements Predicate<String> {
    String comparable;

    public PredicatIssue(String comparable) {
        this.comparable = comparable;
    }

    @Override
    public boolean test(String issue) {
        return issue.equals(comparable);
    }

}
