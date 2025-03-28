package model.algo;

/**
 * Implémentation de l'algorithme de tri rapide (QuickSort).
 * Hérite de {@link AbstractSorter} pour intégrer le comptage des opérations,
 * la pause entre les étapes, et l'affichage temps réel.
 */
public class QuickSort extends AbstractSorter {

    /** Constructeur par défaut */
    public QuickSort() {
    }

    /**
     * Trie un tableau d'entiers en utilisant l'algorithme QuickSort.
     * 
     * @param array le tableau à trier
     * @throws IllegalStateException si le tableau est null ou contient moins de 2 éléments
     */
    public void trier(int[] array) {
        if (array == null || array.length < 2) {
            throw new IllegalStateException("Le tableau ne contient qu'un seul élément ou est vide"); // Rien à trier
        }

        // Appel à la méthode interne avec les limites calculées
        quickSortRecursive(array, 0, array.length - 1);

    }

    /**
     * Méthode récursive principale de QuickSort.
     * 
     * @param array le tableau à trier
     * @param low indice de début de la portion à trier
     * @param high indice de fin de la portion à trier
     */
    public void quickSortRecursive(int[] array, int low, int high) {
        if (sort) {
            if (low < high) {
                // Trouver l'indice du pivot après partition
                int pivotIndex = partition(array, low, high);

                // Trier les sous-tableaux gauche et droit
                quickSortRecursive(array, low, pivotIndex - 1);
                quickSortRecursive(array, pivotIndex + 1, high);
            }
        } else {
            return;
        }
    }

    /**
     * Partitionne le tableau autour d’un pivot (dernier élément).
     * Tous les éléments inférieurs ou égaux au pivot sont placés à gauche,
     * les autres à droite. Le pivot est placé à sa position finale.
     * 
     * @param array le tableau à partitionner
     * @param low indice de début
     * @param high indice de fin (pivot choisi ici)
     * @return la position finale du pivot
     */
    private int partition(int[] array, int low, int high) {

        incrementNbDataAccess(1); // lecture du pivot
        // Utiliser le dernier élément comme pivot
        int pivot = array[high];
        int i = low - 1; // Indice pour les éléments plus petits que le pivot

        for (int j = low; j < high; j++) {
            if (sort) {

                // Si l'élément courant est plus petit ou égal au pivot
                incrementNbComparison();
                incrementNbDataAccess(1); // acces à array[j]
                if (array[j] <= pivot) {
                    i++;
                    // Échanger array[i] et array[j]
                    swap(array, i, j);
                    pauseExecution();
                    notifyRealTime();

                }
            }
        }

        // Placer le pivot à sa position correcte
        swap(array, i + 1, high);
        incrementNbAssignement();
        incrementNbDataAccess(2);
        notifyRealTime();

        return i + 1; // Retourner l'indice du pivo
    }

}
