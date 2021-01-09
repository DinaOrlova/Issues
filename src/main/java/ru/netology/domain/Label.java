package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Label {
    private int id;
    private String name;
    private String description;
    private Color color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return id == label.id &&
                Objects.equals(name, label.name) &&
                Objects.equals(description, label.description) &&
                Objects.equals(color, label.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, color);
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", color=" + color +
                '}';
    }
}
