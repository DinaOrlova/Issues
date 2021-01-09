package ru.netology.repository;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Comment;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.domain.Milestone;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    private IssueRepository repository = new IssueRepository();

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

    private Issue issue1 = new Issue(1, "Ivan", "title", "text", date1, assignees1, labels1, milestones2, comments1, true, true, true, true);
    private Issue issue2 = new Issue(2, "Tonya", "title", "text", date2, assignees3, labels3, milestones1, comments2, true, true, true, true);
    private Issue issue3 = new Issue(3, "Ivan", "title", "text", date3, assignees1, labels2, milestones2, comments2, true, false, false, true);
    private Issue issue4 = new Issue(4, "Vasya", "title", "text", date4, assignees2, labels1, milestones1, comments1, true, true, true, true);
    private Issue issue5 = new Issue(5, "Tonya", "title", "text", date5, assignees3, labels3, milestones1, comments1, true, false, false, true);
    private List<Issue> issues = List.of(issue1, issue2, issue3, issue4, issue5);

    @Nested
    public class Empty {

        @Test
        void shouldSave() {
            repository.save(issue3);
            List<Issue> expected = List.of(issue3);
            List<Issue> actual = repository.findAll();

            assertEquals(expected, actual);
        }

        @Test
        void shouldFindAll() {
            List<Issue> expected = List.of();
            List<Issue> actual = repository.findAll();

            assertEquals(expected, actual);
        }

        @Test
        void shouldNotGetById() {
            Issue expected = null;
            Issue actual = repository.getById(2);

            assertEquals(expected, actual);
        }

        @Test
        void shouldNotRemove() {
            assertFalse(repository.remove(issue4));
        }

        @Test
        void shouldAddAll() {
            repository.addAll(issues);
            List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5);
            List<Issue> actual = repository.findAll();

            assertEquals(expected, actual);
        }

        @Test
        void shouldNotRemoveAll() {
            repository.removeAll(issues);
            List<Issue> expected = List.of();
            List<Issue> actual = repository.findAll();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleIssue {

        @Test
        void shouldSave() {
            List<Issue> newIssues = new ArrayList<>();
            newIssues.add(issue1);
            repository.addAll(newIssues);

            repository.save(issue3);
            List<Issue> expected = List.of(issue1, issue3);
            List<Issue> actual = repository.findAll();

            assertEquals(expected, actual);
        }

        @Test
        void shouldFindAll() {
            repository.save(issue4);

            List<Issue> expected = List.of(issue4);
            List<Issue> actual = repository.findAll();

            assertEquals(expected, actual);
        }

        @Test
        void shouldGetById() {
            repository.save(issue2);

            Issue expected = issue2;
            Issue actual = repository.getById(2);

            assertEquals(expected, actual);
        }

        @Test
        void shouldNotGetById() {
            repository.save(issue5);

            Issue expected = null;
            Issue actual = repository.getById(2);

            assertEquals(expected, actual);
        }

        @Test
        void shouldRemove() {
            repository.save(issue1);

            assertTrue(repository.remove(issue1));
        }

        @Test
        void shouldNotRemove() {
            repository.save(issue4);

            assertFalse(repository.remove(issue3));
        }

        @Test
        void shouldAddAll() {
            repository.save(issue1);

            List<Issue> newIssues = new ArrayList<>();
            newIssues.add(issue2);
            newIssues.add(issue3);

            repository.addAll(newIssues);
            List<Issue> expected = List.of(issue1, issue2, issue3);
            List<Issue> actual = repository.findAll();

            assertEquals(expected, actual);
        }

        @Test
        void shouldRemoveAll() {
            repository.save(issue5);

            repository.removeAll(issues);
            List<Issue> expected = List.of();
            List<Issue> actual = repository.findAll();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class MultipleIssues {

        @Test
        void shouldSave() {
            List<Issue> newIssues = new ArrayList<>();
            newIssues.add(issue1);
            newIssues.add(issue2);
            newIssues.add(issue3);
            repository.addAll(newIssues);

            repository.save(issue4);
            List<Issue> expected = List.of(issue1, issue2, issue3, issue4);
            List<Issue> actual = repository.findAll();

            assertEquals(expected, actual);
        }

        @Test
        void shouldFindAll() {
            repository.addAll(issues);

            List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5);
            List<Issue> actual = repository.findAll();

            assertEquals(expected, actual);
        }

        @Test
        void shouldGetById() {
            repository.addAll(issues);

            Issue expected = issue2;
            Issue actual = repository.getById(2);

            assertEquals(expected, actual);
        }

        @Test
        void shouldNotGetById() {
            repository.addAll(issues);

            Issue expected = null;
            Issue actual = repository.getById(8);

            assertEquals(expected, actual);
        }

        @Test
        void shouldRemove() {
            repository.addAll(issues);

            assertTrue(repository.remove(issue3));
        }

        @Test
        void shouldNotRemove() {
            repository.save(issue1);
            repository.save(issue2);
            repository.save(issue3);

            assertFalse(repository.remove(issue5));
        }

        @Test
        void shouldAddAll() {
            repository.save(issue1);
            repository.save(issue2);

            List<Issue> newIssues = new ArrayList<>();
            newIssues.add(issue3);
            newIssues.add(issue4);

            repository.addAll(newIssues);
            List<Issue> expected = List.of(issue1, issue2, issue3, issue4);
            List<Issue> actual = repository.findAll();

            assertEquals(expected, actual);
        }

        @Test
        void shouldRemoveAll() {
            repository.addAll(issues);

            repository.removeAll(issues);
            List<Issue> expected = List.of();
            List<Issue> actual = repository.findAll();

            assertEquals(expected, actual);
        }
    }
}