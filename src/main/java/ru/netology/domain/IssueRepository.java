package ru.netology.domain;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public void save(Issue issue) {
        issues.add(issue);
    }

    public List<Issue> findAll() {
        return issues;
    }

    public List<Issue> findOpen(){
        List<Issue> temp = issues;
        temp.removeIf(issue -> issue.isOpen()==false);
        return temp;
    }

    public List<Issue> findClose(){
        List<Issue> temp = issues;
        temp.removeIf(issue -> issue.isOpen()==true);
        return temp;
    }

    public void saveAll(List<Issue> tmp){
        issues.addAll(tmp);
    }

    public void removeById (int id){
        issues.removeIf(issue -> issue.getId()==id);
    }

    public void clear(){
        issues.clear();
    }

    public void removeAll(List<Issue> tmp){
        issues.removeAll(tmp);
    }
}
