package ecole.gestion;

import java.util.Map;
import java.util.TreeMap;
import ecole.model.Note;
import ecole.model.Etudiant;
import ecole.model.Professeur;
/**
 * Représente un cours au sein de l'école.
 * Cette classe fait le lien entre un professeur, un groupe d'étudiants,
 * et gère le carnet de notes (via une Map) pour ce cours.
 */
public class Cours {
    /* Attributs privés pour stocker les informations du cours */
    private final String nom;
    private Professeur referent;
    private final Groupe groupe;
    /**
     * Le carnet de notes.
     * Utilise une Map où :
     * La Clé (String) est le numéro d'étudiant.
     * La Valeur (Note) est l'objet Note complet.
     * On utilise TreeMap pour que les notes soient automatiquement triées par ID étudiant.
     */

    private final Map<String, Note> notes;
    /**
     * Construit un nouvel objet Cours.
     * Initialise le carnet de notes comme une TreeMap vide.
     *
     * @param nom      Le nom du cours. Ne peut pas être nul ou vide.
     * @param referent Le professeur responsable du cours. Ne peut pas être nul.
     * @param groupe   Le groupe d'étudiants qui suit ce cours. Ne peut pas être nul.
     * @throws IllegalArgumentException Si l'un des paramètres est nul ou invalide.
     */

    public Cours(String nom, Professeur referent, Groupe groupe) {
        //Exception
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom du cours ne peut pas être vide.");
        }
        if (referent == null) {
            throw new IllegalArgumentException("Le professeur référent ne peut pas être nul.");
        }
        if (groupe == null) {
            throw new IllegalArgumentException("Le groupe ne peut pas être nul.");
        }

        // Attribut
        this.nom = nom;
        this.referent = referent;
        this.groupe = groupe;
        //Initialisation de la map
        this.notes = new TreeMap<>();
    }
    /**
     * Récupère le nom du cours.
     * @return Le nom du cours.
     */
    public String getNom() {
        return nom;
    }
    /**
     * Récupère le professeur référent du cours.
     * @return L'objet('referent') Professeur.
     */

    public Professeur getReferent() {
        return referent;
    }
    /**
     * Récupère le groupe d'étudiants associé à ce cours.
     * @return L'objet Groupe.
     */

    public Groupe getGroupe() {
        return groupe;
    }
    /**
     * Ajoute ou met à jour une note dans le carnet de notes du cours.
     * Utilise le numéro d'étudiant comme clé dans la Map.
     *
     * @param note L'objet Note à ajouter (il contient l'étudiant et la valeur).
     * @throws IllegalArgumentException Si la note est nulle ou ne correspond pas à ce cours.
     */
    public void ajouterNote(Note note) {
        // Validation
        if (note == null || !note.getCours().equals(this)) {
            throw new IllegalArgumentException("La note n'est pas valide ou ne concerne pas ce cours.");
        }
        // On extrait l'ID de l'étudiant pour l'utiliser comme clé
        String etudiantId = note.getEtudiant().getNumeroEtudiant();
        // .put() ajoute la note. Si une note existe déjà pour cet ID,
        // elle est automatiquement écrasée (mise à jour),
        this.notes.put(etudiantId, note);
        System.out.println("Note ajoutée/mise à jour pour " + note.getEtudiant().getPrenom());
    }

    /**
     * Récupère la note d'un étudiant spécifique pour ce cours.
     * C'est une recherche rapide grâce à la Map.
     *
     * @param etu L'étudiant dont on cherche la note.
     * @return L'objet Note, ou null si cet étudiant n'a pas de note dans ce cours.
     */

    public Note getNoteParEtudiant(Etudiant etu) {
        if (etu == null) return null;
        return this.notes.get(etu.getNumeroEtudiant());
    }

    /**
     * Récupère la Map complète de toutes les notes du cours.
     *
     * @return Une Map (ID Étudiant ~~~ Objet Note) des notes.
     */

    public Map<String, Note> getNotes() {
        return this.notes;
    }

    /**
     * Permet de changer le professeur référent du cours.
     *
     * @param referent Le nouvel objet Professeur.
     * @throws IllegalArgumentException Si le professeur est nul.
     */

    public void setReferent(Professeur referent) {
        if (referent == null) {
            throw new IllegalArgumentException("Le professeur référent ne peut pas être nul.");
        }
        this.referent = referent;
    }
    /**
     * Fournit une représentation textuelle claire de l'objet Cours.
     * @return Une chaîne de caractères décrivant le cours.
     */

    @Override
    public String toString() {
        return "Cours: " + this.nom +
                " (Enseignant: " + this.referent.getPrenom() + " " + this.referent.getnom() +
                ", Groupe: " + this.groupe.getNomGroupe() + ")";
    }
}