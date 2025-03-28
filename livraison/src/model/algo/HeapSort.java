package model.algo;

/**
 * Implémentation de l'algorithme de tri par tas (HeapSort).
 * Cette classe hérite de AbstractSorter pour bénéficier du comptage
 * des opérations et de la pause d'exécution.
 */
public class HeapSort extends AbstractSorter {

    /** Constructeur par défaut */
    public HeapSort() {
    }

    /**
     * Trie un tableau en utilisant l'algorithme HeapSort.
     * Si le tableau est vide ou de taille 1, rien n'est fait.
     *
     * @param array le tableau à trier
     */
    public void trier(int[] array) {
        if (array == null || array.length <= 1) {
            return; // Rien à trier
        }
        heapSort(array);
    }

    /**
     * Méthode principale de tri par tas.
     * - Construit un max-heap
     * - Extrait les éléments un à un en maintenant la propriété du tas
     *
     * @param array le tableau à trier
     */
    public void heapSort(int array[]) {
        int n = array.length;

        // Construire le tas (réorganiser le tableau)
        for (int i = n / 2 - 1; i >= 0; i--) {
            if (sort) {

                heap(array, n, i);
            }
        }

        // Extraire les éléments un par un du tas
        for (int i = n - 1; i > 0; i--) {
            if (sort) {
                // Déplacer la racine (max) à la fin
                int temp = array[0];
                array[0] = array[i];
                array[i] = temp;
                incrementNbDataAccess(2);// pour l'acces aux donnée la lecture =1 ,l'ecriture = 1 et lecture + ecriture
                                         // = 1
                incrementNbAssignement(); // affectation réalisée

                // Réappliquer le tas sur la partie réduite
                heap(array, i, 0);
            }

        }
    }

    /**
     * Fonction pour maintenir la propriété de max-heap à partir d’un noeud donné.
     * Elle compare un noeud avec ses enfants gauche et droite et échange
     * si nécessaire, puis s'appelle récursivement.
     *
     * @param array le tableau représentant le tas
     * @param n la taille actuelle du tas
     * @param i l'indice du noeud courant
     */
    public void heap(int array[], int n, int i) {
        // initialiser largest comme racine
        int largest = i;

        // index gauche = 2*i + 1
        int l = 2 * i + 1;

        // index droit = 2*i + 2
        int r = 2 * i + 2;

        // Si l'enfant gauche est plus grand que la racine
        if (l < n && array[l] > array[largest]) {
            largest = l;
            incrementNbComparison();
            incrementNbDataAccess(2); // comparaison entre array[l] et array[largest]

        }

        // Si l'enfant droit est plus grand que le plus grand jusqu'à présent
        if (r < n && array[r] > array[largest]) {
            largest = r;
            incrementNbComparison();
            incrementNbDataAccess(2);  // comparaison entre array[r] et array[largest]

        }

        // Si largest n'est pas la racine, faire un échange
        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            incrementNbDataAccess(2); // accès aux deux valeurs échangées
            incrementNbAssignement();  // pour l'échange

            // Appliquer récursivement heapify sur le sous-arbre affecté
            heap(array, n, largest);
        }
        pauseExecution();  // Pause pour visualisation si activée
    }

}
