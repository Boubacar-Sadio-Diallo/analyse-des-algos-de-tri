package experience;

import java.io.IOException;

import experience.automatisation.Resultats;
import model.ModelTri;
import model.algo.ContexteTri;
import model.algo.HeapSort;
import model.algo.InsertionSort;
import model.algo.QuickSort;
import model.algo.RadixSort;
import model.algo.TimSort;
import model.algo.TriBulle;
import model.algo.MergeSort;
import model.generateur.ContexteGeneration;
import model.generateur.GeneratorBaseDecroissante;
import model.generateur.GeneratorBaseCroissante;
import model.generateur.Repartition;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            System.out.println("Usage: java -jar tri.jar <typeDesordre> <algorithme> <taille>");
            return;
        }
        String typeDesordre = args[0];
        // typeDesordreTotal = args[1];
        String algo = args[1];
        int taille = Integer.parseInt(args[2]);
        double quantiteDedesordre = Integer.parseInt(args[3]);// designe les taux de desordre partielle à tester
        // int desordreTotal = Integer.parseInt(args[5]);// designe les taux de desordre
        // total à tester
        // String repartitionStr = args[4];

        // Sélection de l'algorithme
        ContexteTri contexteTri;
        switch (algo.toLowerCase()) {
            case "tri_rapide":
                contexteTri = new ContexteTri(new QuickSort());
                break;
            case "tri_a_bulle":
                contexteTri = new ContexteTri(new TriBulle());
                break;
            case "tri_fusion":
                contexteTri = new ContexteTri(new MergeSort());
                break;
            case "tri_insertion":
                contexteTri = new ContexteTri(new InsertionSort());
                break;
            case "tri_par_tas":
                contexteTri = new ContexteTri(new HeapSort());
                break;
            case "tri_par_base":
                contexteTri = new ContexteTri(new RadixSort());
                break;
            case "tri_hybride":
                contexteTri = new ContexteTri(new TimSort());
                break;
            default:
                System.out.println("Erreur : Algorithme inconnu");
                return;
        }

        // Selection du generateur
        ContexteGeneration generator = null;

        switch (typeDesordre) {
            case "partielleDebut":
                generator = new ContexteGeneration(
                        new GeneratorBaseCroissante(taille, quantiteDedesordre / 100, Repartition.DEBUT));
                System.out.println(quantiteDedesordre / 100);
                // System.out.println("##############################################################################");
                break;
            case "partielleFin":
                generator = new ContexteGeneration(
                        new GeneratorBaseCroissante(taille, quantiteDedesordre / 100, Repartition.FIN));
                break;
            case "partielleAlea":
                generator = new ContexteGeneration(
                        new GeneratorBaseCroissante(taille, quantiteDedesordre / 100, Repartition.ALEATOIRE));
                break;
            case "partielleMilieu":
                generator = new ContexteGeneration(
                        new GeneratorBaseDecroissante(taille, quantiteDedesordre / 100, Repartition.MILIEU));
                break;
            case "partielleDebutInverse":
                generator = new ContexteGeneration(
                        new GeneratorBaseDecroissante(taille, quantiteDedesordre / 100, Repartition.DEBUT));
                break;
            case "partielleMilieuInverse":
                generator = new ContexteGeneration(
                        new GeneratorBaseDecroissante(taille, quantiteDedesordre / 100, Repartition.MILIEU));
                break;
            case "partielleFinInverse":
                generator = new ContexteGeneration(
                        new GeneratorBaseDecroissante(taille, quantiteDedesordre / 100, Repartition.FIN));
                break;
            case "partielleAleaInverse":
                generator = new ContexteGeneration(
                        new GeneratorBaseDecroissante(taille, quantiteDedesordre / 100, Repartition.ALEATOIRE));
                break;
            case "totalementDesordoné":
                generator = new ContexteGeneration(
                        new GeneratorBaseCroissante(taille, quantiteDedesordre / 100, Repartition.ALEATOIRE));
                break;
            default:
                System.out.println("vous n'avez pas de desordre definit");
                break;
        }

        // Création des contextes
        // ContexteGeneration contexteGeneration = new ContexteGeneration(generator);
        ModelTri modelTri = new ModelTri(contexteTri, generator);
        Resultats resultats = new Resultats(typeDesordre, algo, taille, quantiteDedesordre);
        // Lancer le tri
        modelTri.startExperimentation();

        long nbComparaison = modelTri.getNbComparison();
        System.out.println(modelTri.getNbComparison());
        double tempsExecution = modelTri.getExecutionTime();
        System.out.println(modelTri.getExecutionTime());
        long nbreAffectation = modelTri.getNbAssignement();
        long nbAccesDonnee = modelTri.getNbDataAccess();
        resultats.enregistrerResultats(nbComparaison, tempsExecution, nbreAffectation, nbAccesDonnee);

        Resultats.fermer();
        System.out.println("Fin du programme");
    }
}
