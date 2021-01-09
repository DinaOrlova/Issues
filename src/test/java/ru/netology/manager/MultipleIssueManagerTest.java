package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.*;
import ru.netology.domain.Label;
import ru.netology.repository.IssueRepository;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MultipleIssueManagerTest {
    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);

    private LocalDate Date1 = LocalDate.of(2018, 4, 24);
    private LocalDate Date2 = LocalDate.of(2018, 10, 10);
    private Instant date1 = Instant.ofEpochSecond(1573395930);
    private Instant date2 = Instant.ofEpochSecond(1578483463);
    private Instant date3 = Instant.ofEpochSecond(1587248063);
    private Instant date4 = Instant.ofEpochSecond(1601106835);
    private Instant date5 = Instant.ofEpochSecond(1605346123);

    private Label bug = new Label(1,"bug", "Something isn't working", Color.red);
    private Label helpWanted = new Label(2, "help wanted", "Extra attention is needed", Color.green);
    private Label question = new Label(3, "question", "Further information is requested", Color.blue);

    private Milestone first = new Milestone(1, "first", Date1, "description");
    private Milestone second = new Milestone(2, "second", Date2, "description");

    private Comment N1 = new Comment(1, "Dima", date1, "text", true);
    private Comment N2 = new Comment(2, "Katya", date2, "text", true);

    private Set<String> assignees1 = Set.of("Dima", "Kolya", "Petya");
    private Set<String> assignees2 = Set.of("Ivan");
    private Set<String> assignees3 = Set.of();
    private Set<Label> labels1 = Set.of(bug);
    private Set<Label> labels2 = Set.of(question, helpWanted);
    private Set<Label> labels3 = Set.of();
    private Set<Milestone> milestones1 = Set.of();
    private Set<Milestone> milestones2 = Set.of(first, second);
    private List<Comment> comments1 = List.of();
    private List<Comment> comments2 = List.of(N1, N2);
    private List<Comment> comments3 = List.of(N1);

    private Issue issue1 = new Issue(1, "Ivan", "title", "text", date1, assignees1, labels1, milestones2, comments1, true, true, true, true);
    private Issue issue2 = new Issue(2, "Tonya", "title", "text", date2, assignees3, labels3, milestones1, comments2, true, true, true, true);
    private Issue issue3 = new Issue(3, "Ivan", "title", "text", date3, assignees1, labels2, milestones2, comments2, true, false, false, true);
    private Issue issue4 = new Issue(4, "Vasya", "title", "text", date4, assignees2, labels1, milestones1, comments3, true, true, true, true);
    private Issue issue5 = new Issue(5, "Tonya", "title", "text", date5, assignees3, labels3, milestones1, comments1, true, false, false, true);
    private List<Issue> issues = List.of(issue1, issue2, issue3, issue4, issue5);

    @BeforeEach
    public void setUp() {
        repository.addAll(issues);
    }

    @Test
    void shouldShowOpen() {
        List<Issue> expected = List.of(issue4, issue2, issue1);
        List<Issue> actual = manager.showOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowClose() {
        List<Issue> expected = List.of(issue5, issue3);
        List<Issue> actual = manager.showClose();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthor() {
        List<Issue> issues = manager.showOpen();
        List<Issue> expected = List.of(issue2);
        List<Issue> actual = manager.filterByAuthor(issues, "Tonya");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAuthor() {
        List<Issue> issues = manager.showOpen();
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByAuthor(issues, "Dima");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabel() {
        List<Issue> issues = manager.showOpen();
        List<Issue> expected = List.of(issue4, issue1);
        List<Issue> actual = manager.filterByLabel(issues, bug);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByLabel() {
        List<Issue> issues = manager.showClose();
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByLabel(issues, bug);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssignee() {
        List<Issue> issues = manager.showClose();
        List<Issue> expected = List.of(issue3);
        List<Issue> actual = manager.filterByAssignee(issues, "Kolya");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAssignee() {
        List<Issue> issues = manager.showClose();
        List<Issue> expected = List.of();
        List<Issue> actual = manager.filterByAssignee(issues, "Ivan");
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowNewest() {
        List<Issue> issues = manager.showOpen();
        IssueByDateTimeComparator dateTimeComparator = new IssueByDateTimeComparator();
        List<Issue> expected = List.of(issue4, issue2, issue1);
        List<Issue> actual = manager.showNewest(issues, dateTimeComparator);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowOldest() {
        List<Issue> issues = manager.showOpen();
        IssueByDateTimeComparator dateTimeComparator = new IssueByDateTimeComparator();
        List<Issue> expected = List.of(issue1, issue2, issue4);
        List<Issue> actual = manager.showOldest(issues, dateTimeComparator);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowMostCommented() {
        List<Issue> issues = manager.showOpen();
        IssueByCommentsComparator commentsComparator = new IssueByCommentsComparator();
        List<Issue> expected = List.of(issue2, issue4, issue1);
        List<Issue> actual = manager.showMostCommented(issues, commentsComparator);
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowLeastCommented() {
        List<Issue> issues = manager.showOpen();
        IssueByCommentsComparator commentsComparator = new IssueByCommentsComparator();
        List<Issue> expected = List.of(issue1, issue4, issue2);
        List<Issue> actual = manager.showLeastCommented(issues, commentsComparator);
        assertEquals(expected, actual);
    }

    @Test
    void shouldCloseIssue() {
        int idToClose = 2;
        assertTrue(manager.closeIssue(idToClose));
    }

    @Test
    void shouldNotCloseIssue() {
        int idToClose = 3;
        assertFalse(manager.closeIssue(idToClose));
    }

    @Test
    void shouldNotCloseNonexistentIssue() {
        int idToClose = 9;
        assertFalse(manager.closeIssue(idToClose));
    }

    @Test
    void shouldOpenIssue() {
        int idToOpen = 5;
        assertTrue(manager.openIssue(idToOpen));
    }

    @Test
    void shouldNotOpenIssue() {
        int idToOpen = 4;
        assertFalse(manager.openIssue(idToOpen));
    }

    @Test
    void shouldNotOpenNonexistentIssue() {
        int idToOpen = 7;
        assertFalse(manager.openIssue(idToOpen));
    }
}