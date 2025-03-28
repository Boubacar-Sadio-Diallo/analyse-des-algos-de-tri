package model.generateur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe abstraite de génération de listes d'entiers.
 * Permet de créer une liste initiale et d'en générer une version désordonnée
 * selon une répartition choisie (début, milieu, fin, aléatoire).
 * 
 * Cette classe implémente le pattern Strategy pour permettre
 * des implémentations différentes via {@link StrategieGeneration}.
 */
public abstract class AbstractGenerator implements StrategieGeneration {

    /** Taille de la liste à générer */
    protected int taille;

    /** Proportion de la liste à désordonner (entre 0.0 et 1.0) */
    protected double quantite;

    /** Liste générée (désordonnée) */
    protected List<Integer> liste; 

    /** Copie de la liste initiale triée */
    protected List<Integer> listeInitiale;

    /** Type de répartition du désordre à appliquer */
    protected Repartition repartition;

    /**
     * Constructeur du générateur.
     * 
     * @param taille taille de la liste
     * @param quantite pourcentage d'éléments à désordonner (entre 0.0 et 1.0)
     * @param repartition stratégie de répartition du désordre
     * @throws IllegalArgumentException si quantite est hors bornes
     */
    public AbstractGenerator(int taille, double quantite, Repartition repartition) {
        if (quantite < 0.0 || quantite > 1.0) {
            throw new IllegalArgumentException("Le degré de répartition doit être entre 0.0 et 1.0");
        }
        this.taille = taille;
        this.quantite = quantite;
        this.repartition = repartition;
        this.liste = new ArrayList<>();
        this.listeInitiale = new ArrayList<>();

        generateList();
    }

    /**
     * Méthode à implémenter dans les sous-classes pour définir la logique
     * de remplissage initiale de la liste.
     */
    protected abstract void remplirListe();

    /**
     * Génère une nouvelle liste en appelant {@link #remplirListe()}.
     * Remplit également {@code listeInitiale} avec une copie triée de la liste.
     * 
     * @return la liste générée
     */
    public List<Integer> generateList() {
        liste.clear();
        listeInitiale.clear();

        remplirListe();

        // Faire une copie de la liste initiale pour éviter les modifications futures
        listeInitiale = new ArrayList<>(liste);

        return this.liste;
    }

    /**
     * Génère une version désordonnée de la liste initiale selon la stratégie choisie.
     * 
     * @return la liste désordonnée
     * @throws IllegalStateException si la liste n'a pas encore été générée
     */
    public List<Integer> generateurDesordre() {
        if (liste.isEmpty()) {
            throw new IllegalStateException("La liste est vide. Veuillez générer une liste d'abord.");
        }

        int nbreEnDesordre = (int) Math.round(taille * quantite);
        nbreEnDesordre = Math.min(nbreEnDesordre, liste.size());

        List<Integer> indices = new ArrayList<>();

        switch (repartition) {
            case DEBUT:
                for (int i = 0; i < nbreEnDesordre; i++) {
                    indices.add(i);
                }
                break;

            case MILIEU:
                int start = (taille - nbreEnDesordre) / 2;
                for (int i = start; i < start + nbreEnDesordre; i++) {
                    indices.add(i);
                }
                break;

            case FIN:
                for (int i = taille - nbreEnDesordre; i < taille; i++) {
                    indices.add(i);
                }
                break;

            case ALEATOIRE:
                for (int i = 0; i < taille; i++) {
                    indices.add(i);
                }
                Collections.shuffle(indices);
                indices = indices.subList(0, nbreEnDesordre);
                break;

            default:
                throw new IllegalArgumentException("Répartition inconnue");
        }

        // Appliquer le désordre
        permuterListe(liste, indices);

        return liste;
    }

    /**
     * Réalise un mélange partiel des éléments de la liste à des positions données.
     *
     * @param liste la liste d'origine
     * @param indices indices à mélanger dans la liste
     */
    private static void permuterListe(List<Integer> liste, List<Integer> indices) {
        List<Integer> selectedElements = new ArrayList<>();
        for (int index : indices) {
            selectedElements.add(liste.get(index));
        }

        // Mélanger les éléments sélectionnés
        Collections.shuffle(selectedElements);

        // Réassigner les éléments mélangés dans la liste
        for (int i = 0; i < indices.size(); i++) {
            liste.set(indices.get(i), selectedElements.get(i));
        }
    }

    /**
     * @return une copie de la liste initiale triée
     */
    public List<Integer> getListeInitiale() {
        return new ArrayList<>(listeInitiale); 
    }

    /**
     * @return la liste désordonnée générée, convertie en tableau d'entiers
     */
    public int[] getList() {
        List<Integer> list = generateurDesordre();
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /** @return la taille de la liste */
    public int getTaille() {
        return this.taille;
    }

    /** @return le pourcentage de désordre à appliquer */
    public double getQuantite() {
        return this.quantite;
    }

    /** @return la stratégie de répartition actuelle */
    public Repartition getRepartition() {
        return this.repartition;
    }

    /**
     * Définit une nouvelle taille pour les listes à générer.
     * @param taille la nouvelle taille
     */
    public void setTaille(int taille) {
        this.taille = taille;
    }

    /**
     * Définit une nouvelle taille pour les listes à générer.
     * @param taille la nouvelle taille
     */
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    /**
     * Change la stratégie de répartition du désordre.
     * @param repartition la nouvelle stratégie
     */
    public void setRepartition(Repartition repartition) {
        this.repartition = repartition;
    }
}
