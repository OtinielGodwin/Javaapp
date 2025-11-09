package ecole.model;

import ecole.gestion.Cours;

public class Note {

    private Etudiant etudiant;
    private Cours cours;
    private double valeur;

    public Note(Etudiant etudiant, Cours cours, double valeur) {
        if (etudiant == null) {
            throw new IllegalArgumentException("L'étudiant ne peut pas être nul.");
        }
        if (cours == null) {
            throw new IllegalArgumentException("Le cours ne peut pas être nul.");
        }

        if (valeur < 0 || valeur > 20) {
            throw new IllegalArgumentException("La note doit être comprise entre 0 et 20.");
        }

        this.etudiant = etudiant;
        this.cours = cours;
        this.valeur = valeur;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Cours getCours() {
        return cours;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        if (valeur < 0 || valeur > 20) {
            throw new IllegalArgumentException("La note doit être comprise entre 0 et 20.");
        }
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "Note: " + etudiant.getPrenom() + " " + etudiant.getnom() +
                " | Cours: " + cours.getNom() +
                " | Note: " + valeur + "/20";
    }
}
