package ecole.model;

import java.util.Objects;



public class Etudiant extends Personne {
    /**

     */
    private String numeroEtudiant;
    private Filiere filiere;

    public Etudiant(String nom, String prenom, int age, String numeroEtudiant , Genre legenre, Filiere filiere ) {
        super(nom,prenom,age,legenre);
        this.numeroEtudiant =  numeroEtudiant;
        this.filiere = filiere;
    }
    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }
    @Override
    public String toString() {
        return super.toString() + " - Étudiant n°" + numeroEtudiant;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Etudiant etudiant = (Etudiant) o;
        return Objects.equals(numeroEtudiant, etudiant.numeroEtudiant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroEtudiant);
    }
}
