package ru.netology.domain;

import java.util.Comparator;

public class IssueByDateTimeComparator implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getDateTime().compareTo(o2.getDateTime());
    }
}
