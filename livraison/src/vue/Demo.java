package vue;

import model.ModelTri;
import model.algo.*;
import model.generateur.*;

/**
 * Classe de démonstration principale.
 * Initialise un modèle de tri avec une stratégie de tri et une stratégie de génération,
 * puis lance l'interface graphique {@link TriGui}.
 */
public class Demo {

    /**
     * Point d'entrée du programme.
     * Initialise un tri avec l'algorithme {@link QuickSort} et une liste
     * croissante partiellement désordonnée, puis lance la vue.
     *
     * @param args arguments de la ligne de commande (non utilisés ici)
     */
    public static void main(String[] args) {

        // Création du modèle avec :
        // - QuickSort comme algorithme de tri
        // - une liste de 300 éléments avec 70% de désordre aléatoire
        ModelTri controler = new ModelTri(new ContexteTri(new QuickSort()),
                new ContexteGeneration(new GeneratorBaseCroissante(300, 0.7, Repartition.ALEATOIRE)));
        
        // Lancement de l'interface graphique avec le modèle
        new TriGui(controler);
    }
}
