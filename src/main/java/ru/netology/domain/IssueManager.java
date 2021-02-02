package ru.netology.domain;
import java.util.*;
import java.util.function.Predicate;

public class IssueManager {

    IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue){
        repository.save(issue);
    }

    public void addAll(List<Issue> issues){
        repository.saveAll(issues);
    }

    public List<Issue> findAll(){
       return repository.findAll();
    }

    public List<Issue> findOpen(){
        return repository.findOpen();
    }

    public List<Issue> findClose(){
        return repository.findClose();
    }

    public void removeById(int id){
        repository.removeById(id);
    }

    public void clearAll(){
        repository.clear();
    }

    public void removeAll(List<Issue> issues){
        repository.removeAll(issues);
    }

    public List<Issue> sortByNew(Comparator<Issue> comparator){
        List<Issue> result=repository.findAll();
        result.sort(comparator);
        return result;
    }

    public List<Issue> sortByOld(Comparator<Issue> comparator){
        List<Issue> result=repository.findAll();
        result.sort(comparator);
        Collections.reverse(result);
        return result;
    }

    public void closeIssue(int id){
        for(Issue issue:repository.findAll()){
            if(issue.getId()==id){
                issue.setOpen(false);
            }
        }
    }

    public void openIssue(int id){
        for(Issue issue:repository.findAll()){
            if(issue.getId()==id){
                issue.setOpen(true);
            }
        }
    }

    public List<Issue> sortByAuthor(String author){
        List<Issue> temp = repository.findAll();
        temp.removeIf(issue -> !author.equals(issue.getAuthor()));
        return temp;
    }

    public List<Issue> sortByAssignee(String assignee){
        List<Issue> temp = repository.findAll();
        List<Issue> removed = new ArrayList<>();
        for(Issue issue:temp){
            int countOfAssignee=0;
            for(String assigne: issue.getAssignees()){
                if (assignee.equals(assigne))
                    countOfAssignee++;
            }
            if(countOfAssignee==0)
                removed.add(issue);
        }
        temp.removeAll(removed);
        return temp;
    }

    public List<Issue> sortByLabel(String label){
        List<Issue> temp = repository.findAll();
        List<Issue> removed = new ArrayList<>();
        for(Issue issue:temp){
            int countOfLabel=0;
            for(String labell: issue.getLabels()){
                if (label.equals(labell))
                    countOfLabel++;
            }
            if(countOfLabel==0)
                removed.add(issue);
        }
        temp.removeAll(removed);
        return temp;
    }
}
