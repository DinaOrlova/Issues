package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue implements Comparable<Issue> {
    private int id;
    private String author;
    private String title;
    private String text;
    private Instant dateTime;
    private Set<String> assignees;
    private Set<Label> labels;
    private Set<Milestone> milestones;
    private List<Comment> comments;
    private boolean canComment;
    private boolean open;
    private boolean canEdit;
    private boolean canDelete;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id &&
                canComment == issue.canComment &&
                open == issue.open &&
                canEdit == issue.canEdit &&
                canDelete == issue.canDelete &&
                Objects.equals(author, issue.author) &&
                Objects.equals(title, issue.title) &&
                Objects.equals(text, issue.text) &&
                Objects.equals(dateTime, issue.dateTime) &&
                Objects.equals(assignees, issue.assignees) &&
                Objects.equals(labels, issue.labels) &&
                Objects.equals(milestones, issue.milestones) &&
                Objects.equals(comments, issue.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, text, dateTime, assignees, labels, milestones, comments, canComment, open, canEdit, canDelete);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                ", assignees=" + assignees +
                ", labels=" + labels +
                ", milestones=" + milestones +
                ", comments=" + comments +
                ", canComment=" + canComment +
                ", open=" + open +
                ", canEdit=" + canEdit +
                ", canDelete=" + canDelete +
                '}';
    }

    @Override
    public int compareTo(Issue o) {
        return getDateTime().compareTo(o.getDateTime());
    }
}
