package model.algo;

/**
 * Contexte d'exécution pour l'application d'une stratégie de tri.
 * Cette classe permet de mesurer le temps d'exécution et de suivre les statistiques
 * (comparaisons, affectations, accès mémoire) d'un algorithme de tri donné.
 */
public class ContexteTri {

    /** La stratégie de tri utilisée (implémente l'interface StrategieTri) */
    public StrategieTri strategy;

    /** Temps d'exécution de l'algorithme de tri (en secondes ou millisecondes) */
    private double tempsExecutuion;

    /** Unité du temps d'exécution (secondes ou millisecondes) */
    private String TimerUnit;

    /**
     * Constructeur du contexte de tri.
     * 
     * @param strategy la stratégie de tri à utiliser
     */
    public ContexteTri(StrategieTri strategy) {
        this.strategy = strategy;
        this.tempsExecutuion = 0;
        this.TimerUnit = "";

    }

   /**
     * Trie le tableau donné en utilisant la stratégie définie.
     * Mesure le temps d'exécution et affiche les statistiques du tri.
     *
     * @param array le tableau à trier
     */
    public void trierListe(int[] array) {
        // Démarrer le chronomètre
        long startTime = System.nanoTime();
        strategy.trier(array); // Utilisation de la stratégie de tri
        // Arrêter le chronomètre
        long endTime = System.nanoTime();
        long elapsedNano = endTime - startTime;
        double elapsedMs = elapsedNano / 1_000_000_000.0;

        // Si le temps dépasse 1 milliseconde, afficher en secondes, sinon en
        // millisecondes
        if (elapsedMs > 1) {
            double elapsedSec = elapsedNano / 1_000_000_000.0;
            tempsExecutuion = elapsedSec;
            TimerUnit = " secondes";
        } else {
            tempsExecutuion = elapsedMs;
            TimerUnit = " millisecondes";
        }

        // Affichage des résultats
        System.out.println("Temps d'exécution : " + tempsExecutuion + TimerUnit);
        System.out.println("Le nombre de comparaisons est : " + strategy.getNbComparison());
        System.out.println("Le nombre d'assignation est : " + strategy.getNbAssignement());
        System.out.println("Le nombre d'acces au donnée est : " + strategy.getNbDataAccess());

    }

    /**
     * Réinitialise le temps d'exécution à 0.
     */
    public void resetExecutionTime() {
        this.tempsExecutuion = 0;
    }

    /**
     * Méthode pour définir une nouvelle stratégie de tri.
     * 
     * @param strategy La stratégie à utiliser.
     */
    public void setStrategy(StrategieTri strategy) {
        this.strategy = strategy;
    }

    /**
     * Récupère la stratégie de tri actuelle.
     * 
     * @return la stratégie de tri en cours
     */
    public StrategieTri getStrategieTri() {
        return strategy;
    }

    /**
     * Récupère le temps d'exécution de la dernière exécution de tri.
     * 
     * @return le temps d'exécution
     */
    public double getExecutionTime() {
        return tempsExecutuion;
    }

    /**
     * Récupère l'unité du temps d'exécution (secondes ou millisecondes).
     * 
     * @return l'unité du temps
     */
    public String getTimerUnit() {
        return TimerUnit;
    }

}
