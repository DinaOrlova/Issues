package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.*;
import ru.netology.domain.Label;
import ru.netology.repository.IssueRepository;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EmptyIssueManagerTest {
    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);

    private LocalDate Date1 = LocalDate.of(2018, 4, 24);
    private Instant date1 = Instant.ofEpochSecond(1573395930);

    private Label bug = new Label(1,"bug", "Something isn't working", Color.red);
    private Milestone first = new Milestone(1, "first", Date1, "description");
    private Comment N1 = new Comment(1, "Dima", date1, "text", true);

    private Set<String> assignees1 = Set.of("Dima", "Kolya", "Petya");
    private Set<Label> labels1 = Set.of(bug);
    private Set<Milestone> milestones1 = Set.of(first);
    private List<Comment> comments1 = java.util.List.of(N1);

    private Issue issue1 = new Issue(1, "Ivan", "title", "text", date1, assignees1, labels1, milestones1, comments1, true, true, true, true);

    @Test
    void shouldAdd() {
        manager.add(issue1);
        List<Issue> expected = List.of(issue1);
        List<Issue> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowOpen() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.showOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowClose() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.showClose();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthor() {
        List<Issue> issues = manager.showOpen();
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByAuthor(issues, "Ivan");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabel() {
        List<Issue> issues = manager.showClose();
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByLabel(issues, bug);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssignee() {
        List<Issue> issues = manager.showClose();
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByAssignee(issues, "Ivan");
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowNewest() {
        List<Issue> issues = new ArrayList<>();
        repository.addAll(issues);
        IssueByDateTimeComparator dateTimeComparator = new IssueByDateTimeComparator();
        List<Issue> expected = List.of();
        List<Issue> actual = manager.showNewest(issues, dateTimeComparator);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowOldest() {
        List<Issue> issues = new ArrayList<>();
        repository.addAll(issues);
        IssueByDateTimeComparator dateTimeComparator = new IssueByDateTimeComparator();
        List<Issue> expected = List.of();
        List<Issue> actual = manager.showOldest(issues, dateTimeComparator);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowMostCommented() {
        List<Issue> issues = new ArrayList<>();
        repository.addAll(issues);
        IssueByCommentsComparator commentsComparator = new IssueByCommentsComparator();
        List<Issue> expected = List.of();
        List<Issue> actual = manager.showMostCommented(issues, commentsComparator);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowLeastCommented() {
        List<Issue> issues = new ArrayList<>();
        repository.addAll(issues);
        IssueByCommentsComparator commentsComparator = new IssueByCommentsComparator();
        List<Issue> expected = List.of();
        List<Issue> actual = manager.showLeastCommented(issues, commentsComparator);
        assertEquals(expected, actual);
    }

    @Test
    void shouldCloseIssue() {
        int idToClose = 2;
        assertFalse(manager.closeIssue(idToClose));
    }

    @Test
    void shouldOpenIssue() {
        int idToOpen = 5;
        assertFalse(manager.openIssue(idToOpen));
    }
}