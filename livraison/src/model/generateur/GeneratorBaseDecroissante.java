package model.generateur;

/**
 * Générateur de listes d'entiers triés en ordre décroissant.
 * Hérite de {@link AbstractGenerator} et remplit la liste de taille à 1 jusqu'à 0.
 */
public class GeneratorBaseDecroissante extends AbstractGenerator {

    /**
     * Constructeur du générateur décroissant.
     *
     * @param taille taille de la liste à générer
     * @param quantite proportion d'éléments à désordonner (entre 0.0 et 1.0)
     * @param repartition stratégie de répartition du désordre
     */
    public GeneratorBaseDecroissante(int taille, double quantite, Repartition repartition) {
        super(taille, quantite, repartition);
    }

    /**
     * Remplit la liste avec des entiers décroissants, de taille - 1 à 0.
     * Méthode appelée automatiquement par {@link AbstractGenerator#generateList()}.
     */
    @Override
    protected void remplirListe() {
        for (int i = taille - 1; i >= 0; i--) {
            liste.add(i);
        }
    }

}
