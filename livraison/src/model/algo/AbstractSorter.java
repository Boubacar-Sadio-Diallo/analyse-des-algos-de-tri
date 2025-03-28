package model.algo;

import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import controler.AbstractModeleEcoutable;

/**
 * Classe abstraite représentant un tri avec instrumentation :
 * - compte les affectations, comparaisons, et accès mémoire
 * - permet de visualiser le tri étape par étape
 * - fournit des outils communs à tous les algorithmes de tri
 */

public abstract class AbstractSorter extends AbstractModeleEcoutable implements StrategieTri {

    /** Nombre d'affectations effectuées pendant le tri */
    protected long nbAssignement; 

    /** Nombre de comparaisons effectuées pendant le tri */
    protected long nbComparison; 

    /** Nombre d'accès aux données (lecture/écriture) */
    protected long nbDataAccess;

    /** Temps de pause entre chaque opération (en ms) */
    protected int pauseTime; 

    /** Indique si le tri doit continuer ou non */
    protected boolean sort;

    /** Constructeur par défaut */
    public AbstractSorter() {
        this.nbComparison = 0;
        this.nbAssignement = 0;
        this.nbDataAccess = 0;
        this.sort = true;
    }

    /**
     * Méthode abstraite à implémenter dans chaque algorithme de tri concret
     * @param array le tableau à trier
     */

    @Override
    public abstract void trier(int[] array);

    /** @return le nombre d'accès mémoire comptabilisé */
    @Override
    public long getNbDataAccess() {
        return nbDataAccess;
    }

    /** @return le nombre d'affectations comptabilisé */
    @Override
    public long getNbAssignement() {
        return nbAssignement;
    }

    /** @return le nombre de comparaisons comptabilisé */
    @Override
    public long getNbComparison() {
        return nbComparison;
    }

    /**
     * Réinitialise toutes les statistiques de tri
     */
    @Override
    public void resetStat() {
        this.nbComparison = 0;
        this.nbAssignement = 0;
        this.nbDataAccess = 0;
        fireChange();
    }

    /**
     * Définit la durée de pause entre deux étapes de tri
     * @param time durée en millisecondes
     */

    @Override
    public void setPauseTime(int time) {
        this.pauseTime = time;
        fireChange();
    }

    /**
     * Active ou désactive l'exécution du tri
     * @param b true pour activer, false pour arrêter
     */
    @Override
    public void setSortStatus(boolean b) {
        this.sort = b;
        fireChange();
    }

    /**
     * Incrémente le compteur d'accès mémoire
     * @param nb le nombre d'accès à ajouter
     */

    public void incrementNbDataAccess(int nb) {
        this.nbDataAccess += nb;
    }

    /** Incrémente le compteur de comparaisons */
    public void incrementNbComparison() {
        this.nbComparison++;
    }

    /** Incrémente le compteur d'affectations */
    public void incrementNbAssignement() {
        this.nbAssignement++;
    }

    /**
     * Échange deux éléments d'un tableau si nécessaire
     * @param array le tableau
     * @param i indice du premier élément
     * @param j indice du second élément
     */
    public void swap(int[] array, int i, int j) {
        if (i != j && array[i] != array[j]) { 
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;

        }
    }

    /**
     * Met en pause l'exécution pour visualiser le tri
     */

    public void pauseExecution() {
        try {
            Thread.sleep(pauseTime); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /** @return la durée de pause entre chaque étape */
    public int getPauseTime() {
        return pauseTime;
    }

    /**
     * Notification manuelle pour forcer un rafraîchissement visuel
     */
    public void notifyRealTime() {

        if (!hasListening()) {
            return;
        }

        // Attendre 1 sec avant le fireChange
        try {
            Timer timer = new Timer();
            SwingUtilities.invokeAndWait(() -> timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    fireChange();
                }
            }, 0));
        } catch (InterruptedException | InvocationTargetException exp) {
            exp.printStackTrace();
        }
    }


    /**
     * Implémentation du tri par insertion, utilisée en interne
     * @param array le tableau à trier
     * @param n l'indice max (généralement array.length - 1)
     */
    public void insertionSort(int[] array, int n) {
        for (int i = 1; i <= n; i++) {
            if (sort) {

                int key = array[i];
                incrementNbDataAccess(1); // Lecture de la clé
                int j = i;

                while (j > 0 && array[j - 1] > key) {
                    incrementNbComparison();
                    incrementNbDataAccess(2); // Lecture de array[j-1] et key
                    array[j] = array[j - 1];
                    incrementNbDataAccess(1);// Lecture de array[j - 1]
                    incrementNbAssignement();

                    j = j - 1;
                    pauseExecution();
                }

                if (array[j] != key) { // Ne compter que si la valeur change réellement
                    array[j] = key;
                    incrementNbAssignement(); // Comptabilise uniquement les remplacements de valeurs
                }
                pauseExecution();
            }
        }
    }


    /**
     * Fusionne deux sous-tableaux dans un tri par fusion
     * @param array le tableau principal
     * @param begin début du premier sous-tableau
     * @param middle fin du premier sous-tableau
     * @param end fin du second sous-tableau
     */
    public void merge(int array[], int begin, int middle, int end) {
        int n1 = middle - begin + 1;
        int n2 = end - middle;

        int leftArray[] = new int[n1];
        int rightArray[] = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[begin + i];
            incrementNbAssignement();
            incrementNbDataAccess(2);
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[middle + 1 + j];
            incrementNbAssignement();
            incrementNbDataAccess(2);
        }

        int i = 0, j = 0;
        int k = begin;
        while (i < n1 && j < n2) {
            incrementNbComparison();
            incrementNbDataAccess(2);
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
            incrementNbAssignement();
            incrementNbDataAccess(1);
            pauseExecution();
        }

        while (i < n1) {
            if (sort) {

                array[k] = leftArray[i];
                i++;
                k++;
                incrementNbAssignement();
                incrementNbDataAccess(1);
                pauseExecution();
            }
        }

        while (j < n2) {
            if (sort) {

                array[k] = rightArray[j];
                j++;
                k++;
                incrementNbAssignement();
                incrementNbDataAccess(1);
                pauseExecution();
            }
        }
    }

}
