package model.generateur;

//import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

// import java.util.*;

/**
 * Classe principale de test pour les générateurs de listes.
 * Cette démonstration utilise {@link GeneratorBaseDecroissante}
 * avec 50% de désordre appliqué au début de la liste.
 */
public class Main {

    /**
     * Point d'entrée du programme.
     * Affiche la liste initiale triée, puis la version désordonnée.
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        // Créer un générateur avec 10 éléments et 50% de désordre
        GeneratorBaseDecroissante generateur = new GeneratorBaseDecroissante(10, 0.5, Repartition.DEBUT);
        ContexteGeneration stratGeneration = new ContexteGeneration(generateur);

        // Afficher la liste initiale
        List<Integer> listeInitiale = stratGeneration.getGenerator().getListeInitiale();
        System.out.println("Liste initiale : " + listeInitiale);

        // Afficher la liste après désordre
        int[] listeApresDesordre = generateur.getList();
        System.out.println("Liste après désordre : " + Arrays.toString(listeApresDesordre));

    }
}