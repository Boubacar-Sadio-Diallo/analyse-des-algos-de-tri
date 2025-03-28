package model.generateur;

import java.util.List;

/**
 * Interface représentant une stratégie de génération de listes d'entiers.
 * Elle définit les méthodes que tout générateur doit implémenter :
 * - génération d'une liste désordonnée
 * - récupération de la liste désordonnée (sous forme de tableau)
 * - accès à la liste initiale (triée)
 */
public interface StrategieGeneration {
    
    /**
     * Génère une nouvelle liste selon la stratégie implémentée.
     * Cela inclut la création de la liste initiale triée et l'application du désordre.
     *
     * @return la liste générée (potentiellement désordonnée)
     */
    public List<Integer> generateList();

    
    /**
     * Récupère la liste désordonnée sous forme de tableau d'entiers.
     * 
     * @return un tableau contenant les valeurs désordonnées
     */
    int[] getList();
    
    /**
     * Récupère la liste initiale avant application du désordre.
     * 
     * @return une copie de la liste initiale triée
     */
    public List<Integer> getListeInitiale();
}
