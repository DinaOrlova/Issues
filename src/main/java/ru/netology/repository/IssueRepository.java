package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public void save(Issue issue) {
        issues.add(issue);
    }

    public List<Issue> findAll() {
        return issues;
    }

    public Issue getById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public boolean remove(Issue issue) {
        return issues.remove(issue);
    }

    public void addAll(Collection<Issue> issues) {
        this.issues.addAll(issues);
    }

    public void removeAll(Collection<Issue> issues) {
        this.issues.removeAll(issues);
    }
}
