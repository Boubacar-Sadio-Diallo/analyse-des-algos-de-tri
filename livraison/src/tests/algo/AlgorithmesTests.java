package tests.algo;

import model.algo.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlgorithmesTests {

    // QuickSort Test
    @Test
    public boolean testQuickSort() {
        System.out.println("[Test] [QuickSort] lancé");
        int[] array = { 5, 4, 3, 2, 1 }; // Exemple de tableau
        QuickSort instance = new QuickSort();
        instance.trier(array);
        boolean result = array[0] == 1 && array[1] == 2 && array[2] == 3 && array[3] == 4 && array[4] == 5;
        System.out.println(result ? "[Test] [QuickSort] passé" : "[Test] [QuickSort] raté");
        assertTrue(result);
        return result;
    }

    // RadixSort Test
    @Test
    public boolean testRadixSort() {
        System.out.println("[Test] [RadixSort] lancé");
        int[] array = { 5, 4, 3, 2, 1 }; // Exemple de tableau
        RadixSort instance = new RadixSort();
        instance.trier(array);
        boolean result = array[0] == 1 && array[1] == 2 && array[2] == 3 && array[3] == 4 && array[4] == 5;
        System.out.println(result ? "[Test] [RadixSort] passé" : "[Test] [RadixSort] raté");
        assertTrue(result);
        return result;
    }

    // TimSort Test
    @Test
    public boolean testTimSort() {
        System.out.println("[Test] [TimSort] lancé");
        int[] array = { 5, 4, 3, 2, 1 }; // Exemple de tableau
        TimSort instance = new TimSort();
        instance.trier(array);
        boolean result = array[0] == 1 && array[1] == 2 && array[2] == 3 && array[3] == 4 && array[4] == 5;
        System.out.println(result ? "[Test] [TimSort] passé" : "[Test] [TimSort] raté");
        assertTrue(result);
        return result;
    }

    // TriBulle Test
    @Test
    public boolean testTriBulle() {
        System.out.println("[Test] [TriBulle] lancé");
        int[] array = { 5, 4, 3, 2, 1 }; // Exemple de tableau
        TriBulle instance = new TriBulle();
        instance.trier(array);
        boolean result = array[0] == 1 && array[1] == 2 && array[2] == 3 && array[3] == 4 && array[4] == 5;
        System.out.println(result ? "[Test] [TriBulle] passé" : "[Test] [TriBulle] raté");
        assertTrue(result);
        return result;
    }

    // HeapSort Test
    @Test
    public boolean testHeapSort() {
        System.out.println("[Test] [HeapSort] lancé");
        int[] array = { 5, 4, 3, 2, 1 }; // Exemple de tableau
        HeapSort instance = new HeapSort();
        instance.heapSort(array);
        boolean result = array[0] == 1 && array[1] == 2 && array[2] == 3 && array[3] == 4 && array[4] == 5;
        System.out.println(result ? "[Test] [HeapSort] passé" : "[Test] [HeapSort] raté");
        assertTrue(result);
        return result;
    }

    // InsertionSort Test
    @Test
    public boolean testInsertionSort() {
        System.out.println("[Test] [InsertionSort] lancé");
        int[] array = { 5, 4, 3, 2, 1 }; // Exemple de tableau
        InsertionSort instance = new InsertionSort();
        instance.trier(array);
        boolean result = array[0] == 1 && array[1] == 2 && array[2] == 3 && array[3] == 4 && array[4] == 5;
        System.out.println(result ? "[Test] [InsertionSort] passé" : "[Test] [InsertionSort] raté");
        assertTrue(result);
        return result;
    }

    // MergeSort Test
    @Test
    public boolean testMergeSort() {
        System.out.println("[Test] [MergeSort] lancé");
        int[] array = { 5, 4, 3, 2, 1 }; // Exemple de tableau
        MergeSort instance = new MergeSort();
        instance.trier(array);
        boolean result = array[0] == 1 && array[1] == 2 && array[2] == 3 && array[3] == 4 && array[4] == 5;
        System.out.println(result ? "[Test] [MergeSort] passé" : "[Test] [MergeSort] raté");
        assertTrue(result);
        return result;

    }

    // Exécution des tests
    public boolean runTests() {
        System.out.println("Initialisation des tests pour tous les algorithmes de tri");

        // Exécution des tests pour chaque algorithme
        boolean ok = true;

        // Exécution des tests pour chaque algorithme
        ok = ok && testQuickSort();
        ok = ok && testRadixSort();
        ok = ok && testTimSort();
        ok = ok && testTriBulle();
        ok = ok && testHeapSort();
        ok = ok && testInsertionSort();
        ok = ok && testMergeSort();

        // Résultat global
        System.out.println(ok ? "Tous les tests OK" : "Au moins un test KO");

        return ok;
    }

    // Méthode main pour exécuter les tests
    public static void main(String[] args) {
        // Création d'un objet de test
        AlgorithmesTests tests = new AlgorithmesTests();

        // Lancer les tests et afficher le résultat
        boolean allTestsPassed = tests.runTests();
        System.out.println(allTestsPassed ? "Tous les tests ont réussi !" : "Au moins un test a échoué.");
    }
}
