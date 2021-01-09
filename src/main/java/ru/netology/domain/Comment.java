package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int id;
    private String author;
    private Instant date;
    private String text;
    private boolean canReply;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                canReply == comment.canReply &&
                Objects.equals(author, comment.author) &&
                Objects.equals(date, comment.date) &&
                Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, date, text, canReply);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", canReply=" + canReply +
                '}';
    }
}
