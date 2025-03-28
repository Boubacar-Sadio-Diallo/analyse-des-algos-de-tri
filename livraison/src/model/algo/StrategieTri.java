package model.algo;

/**
 * Interface définissant les méthodes essentielles pour toute stratégie de tri.
 * Elle permet d'assurer un comportement uniforme pour l'instrumentation :
 * - Statistiques de tri (comparaisons, affectations, accès mémoire)
 * - Contrôle de l'exécution (pause, statut)
 * - Méthode de tri principale
 */
public interface StrategieTri {

    /**
     * Trie le tableau donné selon l'algorithme implémenté.
     * 
     * @param array le tableau à trier
     */
    public void trier(int[] array); 

    /**
     * @return le nombre de comparaisons effectuées pendant le tri
     */
    public long getNbComparison();

    /**
     * @return le nombre d'affectations effectuées pendant le tri
     */
    public long getNbAssignement();

    /**
     * @return le nombre d'accès mémoire (lecture/écriture) pendant le tri
     */
    public long getNbDataAccess();

    /**
     * Réinitialise les statistiques de tri (comparaisons, affectations, accès)
     */
    public void resetStat();

    /**
     * Définit le temps de pause (en millisecondes) entre deux opérations de tri.
     * 
     * @param time durée en ms
     */
    public void setPauseTime(int time);

    /**
     * @return la durée de pause actuelle entre deux opérations
     */
    public int getPauseTime();

    /**
     * Active ou désactive l'exécution du tri.
     * 
     * @param b true pour exécuter le tri, false pour l'interrompre
     */
    public void setSortStatus(boolean b);
}
