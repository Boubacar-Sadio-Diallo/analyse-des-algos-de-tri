package model.algo;

/**
 * Implémentation de l'algorithme de tri par fusion (MergeSort).
 * Cette classe hérite de {@link AbstractSorter} et utilise la méthode utilitaire
 * {@code merge(...)} fournie par la classe parente.
 */
public class MergeSort extends AbstractSorter {

    /** Constructeur par défaut */
    public MergeSort() {
    }

    /**
     * Trie un tableau d'entiers en utilisant l'algorithme de tri par fusion.
     * Si le tableau est null ou contient un seul élément, aucun tri n'est effectué.
     * 
     * @param array le tableau à trier
     */
    public void trier(int[] array) {
        if (array == null || array.length <= 1) {
            return; // Rien à trier
        }
        mergeSort(array, 0, array.length - 1);
    }

    /**
     * Méthode récursive principale de MergeSort.
     * Divise le tableau en deux, trie chaque moitié, puis fusionne.
     * 
     * @param array le tableau à trier
     * @param begin indice de début
     * @param end indice de fin
     */
    public void mergeSort(int array[], int begin, int end) {
        if (sort) {

            if (begin < end) {
                int middle = begin + (end - begin) / 2;

                mergeSort(array, begin, middle);
                mergeSort(array, middle + 1, end);
                // Fusion des deux sous-tableaux
                merge(array, begin, middle, end);
            }
        } else {
            return;
        }
    }

}
