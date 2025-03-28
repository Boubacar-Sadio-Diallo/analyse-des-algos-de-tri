package tests.algo;

import model.algo.AbstractSorter;
import org.junit.Before;

public class AbstractSorterTest {

    private AbstractSorter sorter;

    // Constructeur pour garantir l'initialisation de sorter
    public AbstractSorterTest() {
        sorter = new AbstractSorterImpl(); // Initialisation ici
    }

    public static void main(String[] args) {
        AbstractSorterTest test = new AbstractSorterTest();
        test.runTests();
    }

    public boolean runTests() {
        System.out.println("Initialisation des tests pour la classe AbstractSorter");

        boolean ok = true;
        ok = ok && testGetNbDataAccess();
        ok = ok && testGetNbAssignement();
        ok = ok && testGetNbComparison();
        ok = ok && testResetStat();
        ok = ok && testSetPauseTime();
        ok = ok && testSetSortStatus();
        ok = ok && testIncrementNbDataAccess();
        ok = ok && testIncrementNbComparison();
        ok = ok && testIncrementNbAssignement();
        ok = ok && testSwap();
        ok = ok && testPauseExecution();
        ok = ok && testGetPauseTime();
        ok = ok && testNotifyRealTime();

        System.out.println(ok ? "All tests OK" : "Au moins un test KO");

        return ok;
    }

    @Before
    public void setUp() {
        sorter = new AbstractSorterImpl();
    }

    public boolean testGetNbDataAccess() {
        System.out.println("[Test] [getNbDataAccess] lancé");
        boolean result = sorter.getNbDataAccess() == 0;
        System.out.println(result ? "[Test] [getNbDataAccess] passé" : "[Test] [getNbDataAccess] raté");
        return result;
    }

    public boolean testGetNbAssignement() {
        System.out.println("[Test] [getNbAssignement] lancé");
        boolean result = sorter.getNbAssignement() == 0;
        System.out.println(result ? "[Test] [getNbAssignement] passé" : "[Test] [getNbAssignement] raté");
        return result;
    }

    public boolean testGetNbComparison() {
        System.out.println("[Test] [getNbComparison] lancé");
        boolean result = sorter.getNbComparison() == 0;
        System.out.println(result ? "[Test] [getNbComparison] passé" : "[Test] [getNbComparison] raté");
        return result;
    }

    public boolean testResetStat() {
        System.out.println("[Test] [resetStat] lancé");
        sorter.incrementNbAssignement();
        sorter.incrementNbComparison();
        sorter.resetStat();
        boolean result = sorter.getNbAssignement() == 0 && sorter.getNbComparison() == 0;
        System.out.println(result ? "[Test] [resetStat] passé" : "[Test] [resetStat] raté");
        return result;
    }

    public boolean testSetPauseTime() {
        System.out.println("[Test] [setPauseTime] lancé");
        sorter.setPauseTime(100);
        boolean result = sorter.getPauseTime() == 100;
        System.out.println(result ? "[Test] [setPauseTime] passé" : "[Test] [setPauseTime] raté");
        return result;
    }

    public boolean testSetSortStatus() {
        System.out.println("[Test] [setSortStatus] lancé");
        sorter.setSortStatus(false);
        boolean result = !sorter.sort;
        System.out.println(result ? "[Test] [setSortStatus] passé" : "[Test] [setSortStatus] raté");
        return result;
    }

    public boolean testIncrementNbDataAccess() {
        System.out.println("[Test] [incrementNbDataAccess] lancé");
        sorter.incrementNbDataAccess(5);
        boolean result = sorter.getNbDataAccess() == 5;
        System.out.println(result ? "[Test] [incrementNbDataAccess] passé" : "[Test] [incrementNbDataAccess] raté");
        return result;
    }

    public boolean testIncrementNbComparison() {
        System.out.println("[Test] [incrementNbComparison] lancé");
        sorter.incrementNbComparison();
        boolean result = sorter.getNbComparison() == 1;
        System.out.println(result ? "[Test] [incrementNbComparison] passé" : "[Test] [incrementNbComparison] raté");
        return result;
    }

    public boolean testIncrementNbAssignement() {
        System.out.println("[Test] [incrementNbAssignement] lancé");
        sorter.incrementNbAssignement();
        boolean result = sorter.getNbAssignement() == 1;
        System.out.println(result ? "[Test] [incrementNbAssignement] passé" : "[Test] [incrementNbAssignement] raté");
        return result;
    }

    public boolean testSwap() {
        System.out.println("[Test] [swap] lancé");
        int[] array = { 1, 2, 3 };
        sorter.swap(array, 0, 2);
        boolean result = array[0] == 3 && array[2] == 1;
        System.out.println(result ? "[Test] [swap] passé" : "[Test] [swap] raté");
        return result;
    }

    public boolean testPauseExecution() {
        System.out.println("[Test] [pauseExecution] lancé");
        long start = System.currentTimeMillis();
        sorter.setPauseTime(100);
        sorter.pauseExecution();
        long end = System.currentTimeMillis();
        boolean result = (end - start) >= 100;
        System.out.println(result ? "[Test] [pauseExecution] passé" : "[Test] [pauseExecution] raté");
        return result;
    }

    public boolean testGetPauseTime() {
        System.out.println("[Test] [getPauseTime] lancé");
        sorter.setPauseTime(200);
        boolean result = sorter.getPauseTime() == 200;
        System.out.println(result ? "[Test] [getPauseTime] passé" : "[Test] [getPauseTime] raté");
        return result;
    }

    public boolean testNotifyRealTime() {
        System.out.println("[Test] [notifyRealTime] lancé");
        sorter.notifyRealTime();
        System.out.println("[Test] [notifyRealTime] passé");
        return true;
    }

    // Implémentation concrète de AbstractSorter pour les tests
    class AbstractSorterImpl extends AbstractSorter {
        @Override
        public void trier(int[] array) {
            // Implémentation minimale pour le test (peut être vide)
        }
    }
}
