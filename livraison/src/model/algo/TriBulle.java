package model.algo;


/**
 * Implémentation de l'algorithme de tri à bulles (Bubble Sort).
 * Cette classe hérite de {@link AbstractSorter} pour bénéficier des outils
 * de comptage d'opérations et de visualisation pas à pas.
 */
public class TriBulle extends AbstractSorter {

    /** Constructeur par défaut */
    public TriBulle(){}
    
    /**
     * Trie un tableau d'entiers en utilisant l'algorithme du tri à bulles.
     * À chaque passage, les plus grandes valeurs remontent en fin de tableau.
     *
     * @param array le tableau à trier
     */
    public void trier(int[] array) {   
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (sort) {
                    incrementNbDataAccess(2); // le nombre de lecture d'une valeur  
                    incrementNbComparison();
                    if (array[j] > array[j + 1]) {
                        // Échanger les éléments 
                        swap(array,j, j + 1);
                        
                        incrementNbDataAccess(2); // accès pour l'échange
                        incrementNbAssignement(); // une affectation comptée
                        pauseExecution(); // Pause pour visualisation

                    }
                }
            }
        }
    }

    
}

