package model;

import controler.AbstractModeleEcoutable;
import controler.EcouteurModele;
import model.algo.ContexteTri;
import model.algo.ThreadTri;
import model.generateur.ContexteGeneration;

/**
 * Modèle principal de l'application de tri (pattern MVC).
 * Il gère à la fois la génération des données et l'exécution de la stratégie de tri.
 * Il notifie les observateurs des changements via {@link AbstractModeleEcoutable}.
 */
public class ModelTri extends AbstractModeleEcoutable implements EcouteurModele{

    /** Contexte de tri contenant l'algorithme, les statistiques et le temps d'exécution */
    protected  ContexteTri contexteTri;

    /** Contexte de génération de la liste de données à trier */
    protected ContexteGeneration contexteGeneration;

    /** Tableau courant de données (désordonné) */
    protected int[] array ;
    

    /**
     * Constructeur du modèle.
     *
     * @param contexteTri stratégie de tri à utiliser
     * @param contexteGeneration stratégie de génération des données
     */
    public ModelTri(ContexteTri contexteTri,ContexteGeneration contexteGeneration) {
        this.contexteTri = contexteTri;
        this.contexteGeneration = contexteGeneration;
        this.array = contexteGeneration.generate();
        
        
    }
    
    /**
     * Démarre le tri dans un thread séparé.
     * Réinitialise les statistiques et exécute la stratégie de tri de manière asynchrone.
     */
    public void startSort() {
        contexteTri.getStrategieTri().resetStat();
        contexteTri.resetExecutionTime();
        continuSort();
        ThreadTri threadT = new ThreadTri(contexteTri, array, this);
        new Thread(threadT).start();
        
    }

    /**
     * Lance une expérimentation synchrone (sans thread) sur l'algorithme de tri.
     * Affiche le temps d'exécution après le tri.
     */
    public void startExperimentation(){
        contexteTri.trierListe(array);
        System.out.println(contexteTri.getExecutionTime());
    }

    /**
     * @return le tableau actuel de données
     */
    public int[] getArray() {
        return array;
        
    }

    /**
     * Réinitialise les statistiques et le temps d’exécution.
     * Notifie les observateurs.
     */
    public void resetData(){
        getContexteTri().getStrategieTri().resetStat();
        getContexteTri().resetExecutionTime();
        fireChange();
    }

    /**
     * @return le contexte de génération actuellement utilisé
     */
    public ContexteGeneration getContexteGeneration() {
        return contexteGeneration;
    }

    /**
     * @return le contexte de tri actuellement utilisé
     */
    public ContexteTri getContexteTri() {
        return contexteTri;
        
    }

    /**
     * Définit un nouveau tableau de données et notifie les observateurs.
     * 
     * @param array nouveau tableau à afficher ou trier
     */
    public void setArray(int[] array) {
        this.array = array;
        fireChange();
    }

    /**
     * Vide le tableau et notifie les observateurs.
     */
    public void clearArray(){
        int []vide  = {};
        this.array = vide;
        fireChange();
    }

    /**
     * Définit un nouveau générateur et notifie les observateurs.
     * 
     * @param contexteGeneration nouveau contexte de génération
     */
    public void setContexteGeneration(ContexteGeneration contexteGeneration) {
        this.contexteGeneration = contexteGeneration;
        fireChange();
    }

    /**
     * Définit un nouveau contexte de tri et notifie les observateurs.
     * 
     * @param contexteTri nouveau contexte de tri
     */
    public void setContexteTri(ContexteTri contexteTri) {
        this.contexteTri = contexteTri;
        fireChange();
    }

    /**
     * @return le temps d’exécution du tri actuel (en secondes ou millisecondes)
     */
    public double getExecutionTime(){
        return contexteTri.getExecutionTime();
    }

    /**
     * @return le nombre d'affectations réalisées pendant le tri
     */
    public long getNbAssignement() {
        return contexteTri.getStrategieTri().getNbAssignement();
    }

    /**
     * @return le nombre de comparaisons réalisées pendant le tri
     */
    public long getNbComparison() {
        return contexteTri.getStrategieTri().getNbComparison();
    }

    /**
     * @return le nombre d'accès aux données pendant le tri
     */
    public long getNbDataAccess(){
        return contexteTri.getStrategieTri().getNbDataAccess();
    }

    /**
     * Définit le temps de pause entre les étapes du tri (pour visualisation).
     * 
     * @param time durée en millisecondes
     */
    public void setPauseTime(int time){
        contexteTri.getStrategieTri().setPauseTime(time);
        fireChange();
    }

    /**
     * Stoppe l'exécution du tri (ex. bouton "Stop").
     */
    public void stopSort(){
        this.contexteTri.getStrategieTri().setSortStatus(false);
        fireChange();
    }

    /**
     * Active l'exécution du tri (ex. bouton "Continuer").
     */
    public void continuSort(){
        this.contexteTri.getStrategieTri().setSortStatus(true);
        fireChange();
    }
    
    /**
     * Méthode déclenchée par un modèle écouté (MVC).
     * Notifie les observateurs de ce modèle.
     *
     * @param source la source du changement
     */
    @Override
    public void somethingHasChanged(Object source) {
        this.fireChange();

    }
}
