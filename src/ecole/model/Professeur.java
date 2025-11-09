package ecole.model;

import java.util.Objects;
import ecole.exception.SalaireInvalidException;
public class Professeur extends Personne {

    private String matiere;
    private double salaire;

    public Professeur(String nom, String prenom, int age, Genre genre,
                      String matiere, double salaire)
    {
        super(nom, prenom, age, genre);
        if (salaire < 0) {
            throw new SalaireInvalidException("Le salaire ne peut pas être négatif. Valeur reçue : " + salaire);
        }
        this.matiere = matiere;
        this.salaire = salaire;
    }


    public String getMatiere() {
        return matiere;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public void setSalaire(double salaire) {
        if (salaire < 0) {
            throw new SalaireInvalidException("Le salaire ne peut pas être négatif. Valeur reçue : " + salaire);
        }
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return super.toString() + " | Enseigne: " + this.matiere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        if (!super.equals(o)) return false;

        Professeur that = (Professeur) o;
        return Double.compare(that.salaire, salaire) == 0 &&
                Objects.equals(matiere, that.matiere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), matiere, salaire);
    }
}