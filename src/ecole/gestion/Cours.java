package ecole.gestion;

import java.util.Map;
import java.util.TreeMap;
import ecole.model.Note;
import ecole.model.Etudiant;
import ecole.model.Professeur;

public class Cours {

    private String nom;
    private Professeur referent;
    private Groupe groupe;
    private Map<String, Note> notes;

    public Cours(String nom, Professeur referent, Groupe groupe) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom du cours ne peut pas être vide.");
        }
        if (referent == null) {
            throw new IllegalArgumentException("Le professeur référent ne peut pas être nul.");
        }
        if (groupe == null) {
            throw new IllegalArgumentException("Le groupe ne peut pas être nul.");
        }

        this.nom = nom;
        this.referent = referent;
        this.groupe = groupe;
        this.notes = new TreeMap<>();
    }

    public String getNom() {
        return nom;
    }

    public Professeur getReferent() {
        return referent;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void ajouterNote(Note note) {
        if (note == null || !note.getCours().equals(this)) {
            throw new IllegalArgumentException("La note n'est pas valide ou ne concerne pas ce cours.");
        }

        String etudiantId = note.getEtudiant().getNumeroEtudiant();

        this.notes.put(etudiantId, note);
        System.out.println("Note ajoutée/mise à jour pour " + note.getEtudiant().getPrenom());
    }

    public Note getNoteParEtudiant(Etudiant etu) {
        if (etu == null) return null;
        return this.notes.get(etu.getNumeroEtudiant());
    }

    public Map<String, Note> getNotes() {
        return this.notes;
    }

    public void setReferent(Professeur referent) {
        if (referent == null) {
            throw new IllegalArgumentException("Le professeur référent ne peut pas être nul.");
        }
        this.referent = referent;
    }

    @Override
    public String toString() {
        return "Cours: " + this.nom +
                " (Enseignant: " + this.referent.getPrenom() + " " + this.referent.getnom() +
                ", Groupe: " + this.groupe.getNomGroupe() + ")";
    }
}