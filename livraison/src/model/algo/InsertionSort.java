package model.algo;

/**
 * Implémentation de l'algorithme de tri par insertion.
 * Hérite de {@link AbstractSorter} pour profiter du comptage des opérations
 * et des outils de visualisation.
 */
public class InsertionSort extends AbstractSorter {

    /** Constructeur par défaut */
    public InsertionSort() {
    }

    /**
     * Trie un tableau d'entiers en utilisant l'algorithme d'insertion.
     * Cette méthode lance une exception si le tableau est nul ou de taille < 2.
     * Si le tri est désactivé (`sort == false`), rien n'est fait.
     * 
     * @param array le tableau à trier
     * @throws IllegalStateException si le tableau est null ou trop petit
     */
    public void trier(int[] array) {
        if (array == null || array.length < 2) {
            throw new IllegalStateException("Le tableau est vide ou contient un seul élément");
        }
        if (sort) {
            // Utilise la méthode utilitaire héritée de AbstractSorter
            insertionSort(array, array.length - 1);
        } else {
            return;
        }

    }

}
