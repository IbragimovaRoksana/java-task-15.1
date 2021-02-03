package ru.netology.domain;

import java.util.*;
import java.util.function.Predicate;

public class IssueManager {

    IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    public void addAll(List<Issue> issues) {
        repository.saveAll(issues);
    }

    public List<Issue> findAll() {
        return repository.findAll();
    }

    public List<Issue> findOpen() {
        return repository.findOpen();
    }

    public List<Issue> findClose() {
        return repository.findClose();
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public void clearAll() {
        repository.clear();
    }

    public void removeAll(List<Issue> issues) {
        repository.removeAll(issues);
    }

    public List<Issue> sortAscending() {
        List<Issue> result = repository.findAll();
        Comparator<Issue> comparator = new ComparatorIssue();
        result.sort(comparator);
        return result;
    }

    public List<Issue> sortDescending() {
        List<Issue> result = repository.findAll();
        Comparator<Issue> comparator = new ComparatorIssue();
        result.sort(comparator);
        Collections.reverse(result);
        return result;
    }

    public void closeIssue(int id) {
        for (Issue issue : repository.findAll()) {
            if (issue.getId() == id) {
                issue.setOpen(false);
            }
        }
    }

    public void openIssue(int id) {
        for (Issue issue : repository.findAll()) {
            if (issue.getId() == id) {
                issue.setOpen(true);
            }
        }
    }

    public List<Issue> sortByAuthor(String author) {
        List<Issue> temp = repository.findAll();
        List<Issue> result = new ArrayList<>();
        PredicatIssue link = new PredicatIssue(author);
        for (Issue issue : temp)
            if (link.test(issue.getAuthor()))
                result.add(issue);
        return result;
    }

    public List<Issue> sortByAssignee(String assignee) {
        List<Issue> temp = repository.findAll();
        List<Issue> result = new ArrayList<>();
        PredicatIssue link = new PredicatIssue(assignee);
        for (Issue issue : temp) {
            if (repository.filterBy(link, issue.getAssignees()))
                result.add(issue);
        }
        return result;
    }

    public List<Issue> sortByLabel(String label) {
        List<Issue> temp = repository.findAll();
        List<Issue> result = new ArrayList<>();
        PredicatIssue link = new PredicatIssue(label);
        for (Issue issue : temp) {
            if (repository.filterBy(link, issue.getLabels()))
                result.add(issue);
        }
        return result;
    }

}
