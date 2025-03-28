package model.algo;

import model.ModelTri;

/**
 * Classe représentant un thread dédié à l'exécution d'un tri.
 * Elle permet de lancer une stratégie de tri dans un contexte
 * séparé, sans bloquer l'interface utilisateur.
 */
public class ThreadTri implements Runnable{

    /** Contexte de tri contenant la stratégie utilisée et les statistiques */
    protected ContexteTri strategieTri;

    /** Tableau d'entiers à trier */
    protected int[] array ;
   // protected ControlerTri controlerTri;
    
   /**
     * Constructeur du thread de tri.
     * 
     * @param strategieTri le contexte de tri à utiliser (avec stratégie et stats)
     * @param array le tableau d'entiers à trier
     * @param controlerTri (optionnel) référence au contrôleur (non utilisée ici)
     */
    public ThreadTri (ContexteTri strategieTri,int[] array, ModelTri controlerTri){
        this.strategieTri = strategieTri;
        this.array = array;
        //this.controlerTri = controlerTri;
    }

    /**
     * Méthode appelée lors du démarrage du thread.
     * Lance le tri via le contexte fourni.
     */
    @Override
    public void run() {
        this.strategieTri.trierListe(array);
    }
    
}
