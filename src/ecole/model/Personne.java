package ecole.model;
import ecole.exception.AgeInvalidException;
import ecole.exception.NomInvalidException;
import java.util.Objects;
public class Personne {
    protected String nom;
    protected String prenom;
    protected int age;
    protected Genre genre;
    protected static int nbPersonnes;


public Personne(String leNom, String lePrenom, int lAge, Genre leGenre) {
        if (leNom == null || leNom.isEmpty()) {
            throw new NomInvalidException("Le nom ne peut pas être nul ou vide.");
        }
        for (char c : leNom.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("Le nom ne peut pas contenir de chiffres. Reçu : " + lePrenom);
            }
        }
        if (lePrenom == null || lePrenom.isEmpty()){
            throw new NomInvalidException("Le prenom ne peut pas être nul ou vide.");
        }
        for (char c : lePrenom.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("Le prénom ne peut pas contenir de chiffres. Reçu : " + lePrenom);
            }
        }
        if (lAge < 0) {
            throw new AgeInvalidException("L'âge renseigné ne doit pas être négatif. Valeur reçue : " + lAge);
        }
        this.nom = leNom;
        this.prenom = lePrenom;
        this.age = lAge;
        this.genre = leGenre;
        nbPersonnes++;
    }
    Personne(String lePrenom) {
        nom = "Snow";
        prenom = lePrenom;
        age = 0;
        genre = Genre.AUTRE;
        nbPersonnes = nbPersonnes + 1;
    }
    public String getnom() {
        return nom;
    }
    public void setnom (String nouveaunom) {
        if (nouveaunom == null || nouveaunom.isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être nul ou vide.");
        }
        for (char c : nouveaunom.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("Le nom ne peut pas contenir de chiffres.");
            }
        }
        this.nom = nouveaunom;
    }

    public String getPrenom() {
        return this.prenom;
    }
    public void setPrenom(String nouveauPrenom) {
        if (nouveauPrenom == null || nouveauPrenom.isEmpty()) {
            throw new IllegalArgumentException("Le prénom ne peut pas être nul ou vide.");
        }
        for (char c : nouveauPrenom.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("Le prénom ne peut pas contenir de chiffres.");
            }
        }
        this.prenom = nouveauPrenom;
    }
    void afficheAge() {
        System.out.println(age);
    }

    @Override
    public String toString() {
        return "Je me nomme " + prenom + nom + "et j'ai " + age + " ans";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Personne personne)) return false;
        return age == personne.age && Objects.equals(nom, personne.nom) && Objects.equals(prenom, personne.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, age, genre);
    }
}

