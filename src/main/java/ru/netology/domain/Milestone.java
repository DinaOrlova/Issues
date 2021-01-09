package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Milestone {
    private int id;
    private String title;
    private LocalDate date;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milestone milestone = (Milestone) o;
        return id == milestone.id &&
                Objects.equals(title, milestone.title) &&
                Objects.equals(date, milestone.date) &&
                Objects.equals(description, milestone.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, description);
    }

    @Override
    public String toString() {
        return "Milestone{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
