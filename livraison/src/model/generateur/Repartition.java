package model.generateur;

/**
 * Enumération représentant les différentes stratégies
 * de répartition du désordre dans une liste.
 * <p>
 * Utilisée dans les générateurs de listes pour définir
 * quelle portion de la liste sera modifiée (désordonnée).
 */
public enum Repartition {
    /** Désordre appliqué au début de la liste */
    DEBUT,

    /** Désordre appliqué au milieu de la liste */
    MILIEU, 
    
    /** Désordre appliqué à la fin de la liste */
    FIN, 
    
    /** Désordre appliqué de façon aléatoire dans toute la liste */
    ALEATOIRE
}
