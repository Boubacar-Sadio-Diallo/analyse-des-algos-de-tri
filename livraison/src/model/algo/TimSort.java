package model.algo;

/**
 * Implémentation de l'algorithme TimSort (tri hybride utilisé dans Java/Python).
 * Cette classe hérite de {@link AbstractSorter} pour inclure les statistiques
 * d'exécution et les pauses visuelles.
 *
 * L'algorithme combine le tri par insertion et le tri par fusion
 * pour être plus efficace sur des données partiellement triées.
 */
public class TimSort extends AbstractSorter {

    /** Taille minimale d'une "run" (sous-tableau initialement trié) */
    static int MIN_MERGE = 32;

    /** Constructeur par défaut */
    public TimSort() {
    }

    /**
     * Trie le tableau donné avec l’algorithme TimSort.
     * N’applique le tri que si le tableau est non nul et contient plus d’un élément.
     *
     * @param array le tableau à trier
     */
    public void trier(int[] array) {
        if (array == null || array.length <= 1) {
            return; // Rien à trier
        }
        // Appel à la méthode interne avec les limites calculées
        if (sort) {

            timSort(array, array.length);
        }
    }

    /**
     * Calcule la taille minimale d'une run (sous-tableau trié) pour TimSort.
     * Cette taille dépend de la longueur totale du tableau.
     *
     * @param n taille totale du tableau
     * @return la taille minimale d'une run
     */
    public static int minRunLength(int n) {
        assert n >= 0;

        // Devient 1 si des bits à 1 sont décalés
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    /**
     * Méthode principale de tri TimSort (itératif).
     * Divise le tableau en sous-tableaux ("runs") triés par insertion, puis les fusionne.
     *
     * @param arr le tableau à trier
     * @param n taille du tableau
     */
    public void timSort(int[] arr, int n) {
        int minRun = minRunLength(MIN_MERGE);

        // Utilisation de ta fonction insertionSort pour trier les sous-tableaux
        for (int i = 0; i < n; i += minRun) {
            insertionSort(arr, Math.min(i + minRun - 1, n - 1));
        }

        // Commencer la fusion à partir de la taille
        // RUN (ou 32). Cela fusionnera pour former la taille 64,
        // puis 128, 256, et ainsi de suite...
        for (int size = minRun; size < n; size = 2 * size) {

            // Choisir le point de départ
            // du sous-tableau gauche. Nous
            // allons fusionner
            // arr[left..left+size-1]
            // et arr[left+size, left+2size-1]
            // Après chaque fusion, nous
            // augmentons left de 2size.
            for (int left = 0; left < n; left += 2 * size) {

                // Trouver le point de fin du sous-tableau gauche
                // mid+1 est le point de départ du sous-tableau droit.
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1),
                        (n - 1));

                if (mid < right)
                    merge(arr, left, mid, right);
            }
        }
    }

}

// This code has been contributed by 29AjayKumar
