package ru.netology.domain;

import java.util.Comparator;

public class IssueByCommentsComparator implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getComments().size() - o2.getComments().size();
    }
}
