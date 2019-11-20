package model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Stations {
    private int id;
    private String nom;
    private String location;

    public Stations(int id, String nom, String location) {
        this.id = id;
        this.nom = nom;
        this.location = location;
    }

    public Stations(String nom, String location) {
        this.nom = nom;
        this.location = location;
    }

    public Stations() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stations stations = (Stations) o;
        return id == stations.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Stations{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
