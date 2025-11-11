package ecole.gestion;

// Import de l'exception personnalisée pour gérer les doublons
import ecole.exception.Etudiantpresent;
// Import du modèle Etudiant, car cette classe gère une collection d'Étudiants
import ecole.model.Etudiant;

// Imports des outils de collection Java
import java.util.Map;
import java.util.TreeMap;

/**
 * Représente un groupe d'étudiants.
 * Cette classe gère la liste des étudiants membres en utilisant une Map
 * pour un accès et une recherche rapides par numéro d'étudiant.
 */
public class Groupe {

    /** Le nom du groupe */
    private final String nomGroupe;

    /**
     * Contient les étudiants du groupe.
     * Une Map est utilisée pour une performance optimale.
     * - Clé (String) : Le numéro d'étudiant (ID unique).
     * - Valeur (Etudiant) : L'objet Etudiant associé.
     * TreeMap est choisi pour maintenir la Map triée par clé (numéro d'étudiant).
     */
    private final Map<String, Etudiant> etudiants;

    /**
     * Construit un nouveau Groupe.
     *
     * @param nom Le nom a assigné à ce groupe.
     */
    public Groupe(String nom) {
        this.nomGroupe = nom;
        // Initialise la Map pour éviter les NullPointerExceptions
        this.etudiants = new TreeMap<>();
    }

    /**
     * Ajoute un étudiant au groupe.
     * Vérifie si l'étudiant est nul ou s'il existe déjà (basé sur l'ID).
     *
     * @param etu L'objet Etudiant à ajouter.
     * @throws IllegalArgumentException Si l'objet Etudiant fourni est nul.
     * @throws Etudiantpresent Si un étudiant avec le même ID est déjà dans le groupe.
     */
    public void ajouterEtudiant(Etudiant etu) {
        if (etu == null) {
            throw new IllegalArgumentException("L'étudiant ne peut pas être null.");
        }
        String id = etu.getNumeroEtudiant();

        // Utilise containsKey pour une vérification rapide des doublons
        if (this.etudiants.containsKey(id)) {
            throw new Etudiantpresent("L'étudiant (ID: " + id + ") est déjà dans le groupe " + this.nomGroupe);
        } else {
            // Ajoute l'étudiant à la Map
            this.etudiants.put(id, etu);
            System.out.println(etu.getPrenom() + " (ID: " + id + ") ajouté au groupe " + this.nomGroupe);
        }
    }

    /**
     * Supprime un étudiant de la Map en utilisant son numéro d'étudiant.
     *
     * @param numeroEtudiant L'ID (clé) de l'étudiant à retirer.
     * @return L'objet Etudiant qui a été supprimé, ou null si aucun étudiant n'a été trouvé.
     */
    public Etudiant supprimerEtudiant(String numeroEtudiant) {
        return this.etudiants.remove(numeroEtudiant);
    }

    /**
     * Méthode surchargée pour supprimer un étudiant en passant l'objet Etudiant.
     *
     * @param etu L'objet Etudiant à retirer.
     * @return L'objet Etudiant supprimé, ou null si l'objet 'etu' était nul.
     */
    public Etudiant supprimerEtudiant(Etudiant etu) {
        if (etu == null) return null;
        return this.etudiants.remove(etu.getNumeroEtudiant());
    }

    /**
     * Renvoie un tableau (Array) contenant tous les étudiants du groupe.
     *
     * @return Un tableau de type Etudiant[] contenant toutes les valeurs de la Map.
     */
    public Etudiant[] toArray() {
        // .values() renvoie la collection de tous les étudiants (les valeurs de la Map)
        return this.etudiants.values().toArray(new Etudiant[0]);
    }

    /**
     * Remplit un tableau fourni avec tous les étudiants du groupe.
     * Si le tableau 'a' est trop petit, un nouveau tableau sera créé.
     *
     * @param a Le tableau dans lequel stocker les étudiants.
     * @return Un tableau de type Etudiant[] contenant les étudiants.
     */
    public Etudiant[] toArray(Etudiant[] a) {
        return this.etudiants.values().toArray(a);
    }

    /**
     * Trouve un étudiant par son numéro d'étudiant (ID).
     * L'utilisation de la Map rend cette opération très rapide.
     *
     * @param numeroEtudiant L'ID (clé) à rechercher.
     * @return L'objet Etudiant correspondant, ou null s'il n'est pas trouvé.
     */
    public Etudiant findEtudiant(String numeroEtudiant) {
        return this.etudiants.get(numeroEtudiant);
    }

    /**
     * Vérifie si un étudiant (basé sur son ID) est présent dans le groupe.
     *
     * @param numeroEtudiant L'ID (clé) à vérifier.
     * @return true si l'étudiant existe, false sinon.
     */
    public boolean existeEtudiant(String numeroEtudiant) {
        return this.etudiants.containsKey(numeroEtudiant);
    }

    /**
     * Renvoie la Map complète des membres du groupe.
     * Utile pour l'interface graphique afin d'itérer sur la liste des étudiants.
     *
     * @return La Map (String -> Etudiant) des membres.
     */
    public Map<String, Etudiant> getMembres() {
        return this.etudiants;
    }

    /**
     * Affiche tous les membres du groupe dans la console.
     * Principalement utilisé pour le débogage.
     * Les étudiants sont affichés par ordre d'ID grâce à TreeMap.
     */
    public void afficherMembres() {
        System.out.println("Membres du groupe '" + this.nomGroupe + "' (triés par N° étudiant):");

        if (etudiants.isEmpty()) {
            System.out.println("  (Ce groupe est vide)");
        } else {
            // Itère sur la collection des valeurs (les objets Etudiant)
            for (Etudiant e : this.etudiants.values()) {
                System.out.println("  - " + e.toString());
            }
        }
    }

    /**
     * Renvoie le nom du groupe.
     *
     * @return Le nom (String) du groupe.
     */
    public String getNomGroupe() {
        return nomGroupe;
    }
}