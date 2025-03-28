package model.generateur;

import java.util.*;

/**
 * Générateur de listes d'entiers basé sur un certain nombre de swaps (échanges).
 * Cette classe permet d'introduire un niveau de désordre contrôlé
 * dans une liste initialement triée, en réalisant des échanges d'éléments.
 * 
 * Le désordre est appliqué dans une partie spécifique de la liste selon
 * la répartition : début, milieu, fin ou aléatoire.
 */
public class GeneratorSwaps extends AbstractGenerator {

    /**
     * Constructeur du générateur par swaps.
     * 
     * @param taille taille de la liste
     * @param quantite proportion de swaps à appliquer (entre 0.0 et 1.0)
     * @param repartition stratégie de répartition des swaps
     */
    public GeneratorSwaps(int taille, double quantite, Repartition repartition) {
        super(taille, quantite, repartition);
    }

    /**
     * Remplit la liste avec des entiers croissants de 0 à taille - 1.
     */
    @Override
    protected void remplirListe() {
        for (int i = 0; i < taille; i++) {
            liste.add(i);
        }
    }

    /**
     * Retourne un entier aléatoire dans l'intervalle [min, max[.
     * 
     * @param min valeur minimale (incluse)
     * @param max valeur maximale (exclue)
     * @return un entier aléatoire
     */
    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * Désordonne la liste en effectuant un certain nombre d'échanges d'éléments (swaps),
     * dans la zone définie par la répartition (début, milieu, fin, aléatoire).
     *
     * @return la liste désordonnée
     * @throws IllegalStateException si la liste n'a pas encore été générée
     */
    @Override
    public List<Integer> generateurDesordre() {

        if (liste.isEmpty()) {
            throw new IllegalStateException("La liste est vide. Veuillez générer une liste d'abord.");
        }

        int nbreDeSwaps = (int) Math.round(taille * quantite);
        nbreDeSwaps = Math.min(nbreDeSwaps, liste.size());

        int milieu = (int) Math.round(taille / 2);
        Random random = new Random();

        List<Integer> sousListe;

        switch (repartition) {
            case DEBUT:
                sousListe = liste.subList(0, milieu);
                for (int i = 0; i < nbreDeSwaps; i++) {
                    int index1 = random.nextInt(sousListe.size());
                    int index2;
                    do {
                        index2 = random.nextInt(sousListe.size());
                    } while (index1 == index2);
                    Collections.swap(sousListe, index1, index2);
                }
                break;

            case MILIEU:
                int debut = (taille - milieu) / 2;
                int fin = debut + milieu;
                sousListe = liste.subList(debut, fin);
                for (int i = 0; i < nbreDeSwaps; i++) {
                    int index1 = random.nextInt(sousListe.size());
                    int index2;
                    do {
                        index2 = random.nextInt(sousListe.size());
                    } while (index1 == index2);
                    Collections.swap(sousListe, index1, index2);
                }
                break;

            case FIN:
                sousListe = liste.subList(milieu, taille);
                for (int i = 0; i < nbreDeSwaps; i++) {
                    int index1 = random.nextInt(sousListe.size());
                    int index2;
                    do {
                        index2 = random.nextInt(sousListe.size());
                    } while (index1 == index2);
                    Collections.swap(sousListe, index1, index2);
                }
                break;

            case ALEATOIRE:
                for (int i = 0; i < nbreDeSwaps; i++) {
                    int index1 = random.nextInt(taille);
                    int index2;
                    do {
                        index2 = random.nextInt(taille);
                    } while (index1 == index2);
                    Collections.swap(liste, index1, index2);
                    System.out.println("swaps entre" + index1 + "et" + index2);
                }
                break;

            default:
                throw new IllegalArgumentException("Répartition inconnue");
        }

        return liste;

    }

}
