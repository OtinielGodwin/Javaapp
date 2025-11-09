package ecole.gestion;

import ecole.exception.Etudiantpresent;
import ecole.model.Etudiant;

import java.util.Map;
import java.util.TreeMap;

public class Groupe {

    private String nomGroupe;
    private Map<String, Etudiant> etudiants;

    public Groupe(String nom) {
        this.nomGroupe = nom;
        this.etudiants = new TreeMap<>();
    }

    public void ajouterEtudiant(Etudiant etu) {
        if (etu == null) {
            throw new IllegalArgumentException("L'étudiant ne peut pas être null.");
        }
        String id = etu.getNumeroEtudiant();
        if (this.etudiants.containsKey(id)) {
            throw new Etudiantpresent("L'étudiant (ID: " + id + ") est déjà dans le groupe " + this.nomGroupe);
        } else {
            this.etudiants.put(id, etu);
            System.out.println(etu.getPrenom() + " (ID: " + id + ") ajouté au groupe " + this.nomGroupe);
        }
    }
    public Etudiant supprimerEtudiant(String numeroEtudiant) {
        return this.etudiants.remove(numeroEtudiant);
    }

    public Etudiant supprimerEtudiant(Etudiant etu) {
        if (etu == null) return null;
        return this.etudiants.remove(etu.getNumeroEtudiant());
    }
    public Etudiant[] toArray() {
        return this.etudiants.values().toArray(new Etudiant[0]);
    }

    public Etudiant[] toArray(Etudiant[] a) {
        return this.etudiants.values().toArray(a);
    }

    public Etudiant findEtudiant(String numeroEtudiant) {
        return this.etudiants.get(numeroEtudiant);
    }

    public boolean existeEtudiant(String numeroEtudiant) {
        return this.etudiants.containsKey(numeroEtudiant);
    }
    public Map<String, Etudiant> getMembres() {
        return this.etudiants;
    }

    public void afficherMembres() {
        System.out.println("Membres du groupe '" + this.nomGroupe + "' (triés par N° étudiant):");

        if (etudiants.isEmpty()) {
            System.out.println("  (Ce groupe est vide)");
        } else {
            for (Etudiant e : this.etudiants.values()) {
                System.out.println("  - " + e.toString());
            }
        }
    }

    public String getNomGroupe() {
        return nomGroupe;
    }
}