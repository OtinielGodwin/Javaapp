<p align="center">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" alt="Java" width="80" height="80"/>
</p>

# Projet de Gestion (Java)

... (le reste de ton README) ...
# 💎 Java App

Il met en œuvre les principes de la programmation orientée objet pour créer un mini-système de gestion d'école, jusqu'à l'interface graphique (Swing).

## 🚀 Fonctionnalités

Le projet est construit autour d'une architecture complète qui sépare la logique et l'affichage.

### Logique Métier (Le "Moteur")
* **Modélisation POO :** Utilisation de l'héritage (`Personne` ➔ `Etudiant`, `Professeur`) pour modéliser les utilisateurs du système.
* **Gestion des Données :** Utilisation de `Map` pour une gestion efficace des listes d'étudiants (dans `Groupe`) et des notes (dans `Cours`), permettant une recherche instantanée par ID.
* **Exceptions Personnalisées :** Création et utilisation d'exceptions métier (ex: `AgeInvalideException`, `SalaireInvalideException`, `EtudiantDejaPresentException`) pour garantir la validité des données dès leur création (principe de "fail-fast").

### Interface Graphique (Le "Tableau de Bord")
Une interface graphique complète construite avec **Java Swing**, séparée en trois portails distincts :

1.  **Interface Admin :**
    * Système d'onglets (`JTabbedPane`).
    * Crée des `Etudiant` et des `Professeur` via des formulaires.
    * Capture les exceptions (ex: âge négatif, nom avec un chiffre) et les affiche proprement à l'utilisateur via des `JOptionPane`.

2.  **Interface Professeur :**
    * Système d'onglets.
    * **Onglet 1 :** Permet de créer un `Cours` et de l'assigner à un `Groupe` existant (via un `JComboBox`).
    * **Onglet 2 :** Permet de sélectionner un cours, d'afficher la liste de ses étudiants (`JList`), et d'enregistrer des `Note`s.

3.  **Interface Étudiant :**
    * Un portail de **connexion** (`LoginEtudiant`) qui vérifie l'ID et le nom de l'étudiant.
    * Si la connexion est réussie, une seconde fenêtre (`EtudiantInterface`) s'ouvre et affiche un bulletin de notes (`JTextArea`) en allant chercher toutes les notes de l'étudiant dans les différents cours.

## 📁 Structure du Projet

Le projet est organisé en packages pour une séparation claire des responsabilités (SoC) :
* **`ecole.model`** : Contient les classes de données (entités) comme `Personne`, `Etudiant`, `Note`, `Filiere`, `Genre`.
* **`ecole.gestion`** : Contient les classes de logique (gestionnaires) comme `Groupe` et `Cours`.
* **`ecole.exceptions`** : Contient toutes les classes d'exceptions personnalisées.
* **`ecole.ui`** : Contient l'interface graphique, elle-même sous-divisée en :
    * `ecole.ui.admin`
    * `ecole.ui.prof`
    * `ecole.ui.etudiant`

## ⚙️ Comment Lancer

Le projet contient trois points d'entrée (`main`) pour tester les différentes interfaces. Pour lancer un portail :

1.  **Portail Admin :** Exécuter `AdminInterface.java` (dans `ecole.ui.admin`)
2.  **Portail Professeur :** Exécuter `ProfInterface.java` (dans `ecole.ui.prof`)
3.  **Portail Étudiant :** Exécuter `LoginEtudiant.java` (dans `ecole.ui.etudiant`)
