package model.generateur;

/**
 * Générateur de listes d'entiers triés en ordre croissant.
 * Hérite de {@link AbstractGenerator} et remplit simplement la liste
 * avec des entiers de 0 à taille - 1.
 */
public class GeneratorBaseCroissante extends AbstractGenerator {

    /**
     * Constructeur du générateur croissant.
     * 
     * @param taille taille de la liste à générer
     * @param quantite proportion des éléments à désordonner (entre 0.0 et 1.0)
     * @param repartition stratégie de répartition du désordre (début, milieu, etc.)
     */
    public GeneratorBaseCroissante(int taille, double quantite, Repartition repartition) {
        super(taille, quantite, repartition);
    }

    /**
     * Remplit la liste avec des entiers croissants de 0 à taille - 1.
     * Méthode appelée automatiquement par {@link AbstractGenerator#generateList()}.
     */
    @Override
    protected void remplirListe() {
        for (int i = 0; i < taille; i++) {
            liste.add(i);
        }
    }

}
