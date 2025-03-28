package model.algo;

import java.util.*;

/**
 * Implémentation de l'algorithme de tri Radix Sort (tri par base).
 * Hérite de {@link AbstractSorter} pour intégrer le comptage des opérations,
 * les pauses, et la visualisation temps réel.
 */
public class RadixSort extends AbstractSorter {

    /** Constructeur par défaut */
    public RadixSort() {
    }

    /**
     * Trie un tableau d'entiers en utilisant le tri Radix Sort.
     * Si le tableau est null ou trop petit, aucun tri n'est effectué.
     *
     * @param array le tableau à trier
     */
    public void trier(int[] array) {
        if (array == null || array.length <= 1) {
            return; // Rien à trier
        }
        // Appel à la méthode interne avec les limites calculées
        if (sort) {

            radixsort(array, array.length);
        }
    }

    /**
     * Recherche la valeur maximale dans le tableau.
     *
     * @param array le tableau
     * @param n la taille du tableau
     * @return la plus grande valeur du tableau
     */
    public int getMax(int array[], int n) {
        int mx = array[0];
        incrementNbDataAccess(1);
        for (int i = 1; i < n; i++)
            if (sort) {
                incrementNbDataAccess(1);
                incrementNbComparison();
                if (array[i] > mx) {
                    mx = array[i];
                    incrementNbDataAccess(1);
                }
            }
        return mx;
    }

    /**
     * Trie le tableau selon les chiffres de l'ordre donné (exposant).
     * Utilise une variante du tri par comptage.
     *
     * @param array le tableau à trier
     * @param n la taille du tableau
     * @param exp la puissance de 10 correspondant au chiffre traité
     */
    public void countSort(int array[], int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10]; // compteurs pour les chiffres 0 à 9
        Arrays.fill(count, 0);

        // Stocker le nombre d'occurrences dans count[]
        for (i = 0; i < n; i++)
            count[(array[i] / exp) % 10]++;
        incrementNbDataAccess(1);

        // Calculer les positions cumulées
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Construire le tableau trié temporaire (output)
        for (i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            incrementNbAssignement();
            count[(array[i] / exp) % 10]--;
            incrementNbDataAccess(3);// accès à array[i], count[], output[]
        }

        // Copier le tableau output dans arr[], de sorte que arr[] contienne désormais
        // les nombres triés selon le chiffre actuel
        for (i = 0; i < n; i++) {
            if (sort) {

                array[i] = output[i];
                incrementNbDataAccess(2); // lecture + écriture
                pauseExecution();
            }
        }

    }

    /**
     * Méthode principale du tri Radix.
     * Applique un tri stable (count sort) pour chaque chiffre significatif.
     *
     * @param array le tableau à trier
     * @param n la taille du tableau
     */
    public void radixsort(int array[], int n) {
        // Trouver le nombre maximum pour connaître le nombre de chiffre
        int m = getMax(array, n);

        // Effectuer un tri par comptage pour chaque chiffre. Notez que
        // au lieu de passer le numéro du chiffre, exp est passé.
        // exp est 10^i où i est le numéro du chiffre actuel.

        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(array, n, exp);

        }

    }

}
