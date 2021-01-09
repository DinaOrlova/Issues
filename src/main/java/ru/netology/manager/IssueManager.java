package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

@NoArgsConstructor
@AllArgsConstructor
public class IssueManager {
    private IssueRepository repository;

    public void add(Issue issue) { repository.save(issue); }

    public List<Issue> showOpen() {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.isOpen() == true) {
                result.add(issue);
            }
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }

    public List<Issue> showClose() {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.isOpen() == false) {
                result.add(issue);
            }
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }

//    public List<Issue> filterByAuthor(List<Issue> issues, String author) {
//        return issues.stream().filter(p -> p.getAuthor() == author).collect(Collectors.toList());
//    }

    public List<Issue> filterByAuthor(List<Issue> issues, String author) {
        List<Issue> result = new ArrayList<>();
        Predicate<Issue> isAuthor = issue -> issue.getAuthor() == author;
        for (Issue issue : issues) {
            if (isAuthor.test(issue)) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> filterByLabel(List<Issue> issues, Label label) {
        List<Issue> result = new ArrayList<>();
        Predicate<Issue> isLabel = issue -> issue.getLabels().contains(label);
        for (Issue issue : issues) {
            if (isLabel.test(issue)) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> filterByAssignee(List<Issue> issues, String assignee) {
        List<Issue> result = new ArrayList<>();
        Predicate<Issue> isLabel = issue -> issue.getAssignees().contains(assignee);
        for (Issue issue : issues) {
            if (isLabel.test(issue)) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> showNewest(List<Issue> issues, Comparator<Issue> comparator) {
        Collections.sort(issues, comparator.reversed());
        return issues;
    }

    public List<Issue> showOldest(List<Issue> issues, Comparator<Issue> comparator) {
        Collections.sort(issues, comparator);
        return issues;
    }

    public List<Issue> showMostCommented(List<Issue> issues, Comparator<Issue> comparator) {
        Collections.sort(issues, comparator.reversed());
        return issues;
    }

    public List<Issue> showLeastCommented(List<Issue> issues, Comparator<Issue> comparator) {
        Collections.sort(issues, comparator);
        return issues;
    }

    public boolean closeIssue(int id) {
        for (Issue issue : repository.findAll()) {
           if (issue.getId() == id && issue.isOpen() == true) {
               issue.setOpen(false);
               return true;
           }
        }
        return false;
    }

    public boolean openIssue(int id) {
        for (Issue issue : repository.findAll()) {
            if (issue.getId() == id && issue.isOpen() == false) {
                issue.setOpen(true);
                return true;
            }
        }
        return false;
    }
}
