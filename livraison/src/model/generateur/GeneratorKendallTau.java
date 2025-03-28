package model.generateur;

import java.util.*;

/**
 * Générateur de permutations basé sur la distance de Kendall-Tau.
 * Cette classe permet de créer une permutation avec un niveau de désordre
 * contrôlé en fonction d'une distance cible entre la permutation triée
 * et la permutation générée.
 *
 * La distance Kendall-Tau mesure le nombre d'inversions entre deux permutations.
 */
public class GeneratorKendallTau extends AbstractGenerator {

    /**
     * Constructeur du générateur Kendall-Tau.
     *
     * @param taille la taille de la liste à générer
     * @param quantite proportion du désordre souhaité (entre 0.0 et 1.0)
     * @param repartition stratégie de répartition (non utilisée ici, mais conservée pour compatibilité)
     */
    public GeneratorKendallTau(int taille, double quantite, Repartition repartition) {
        super(taille, quantite, repartition);
    }

    /**
     * Remplit la liste de manière croissante, de 0 à taille - 1.
     */
    @Override
    protected void remplirListe() {
        for (int i = 0; i < taille; i++) {
            liste.add(i);
        }

    }

    /**
     * Calcule la distance de Kendall-Tau entre deux permutations.
     * Cela correspond au nombre d'inversions nécessaires pour passer
     * d'une permutation à l'autre.
     *
     * @param permActuelle permutation initiale
     * @param permSuivante permutation cible
     * @return la distance Kendall-Tau entre les deux
     */
    public static int calculeDistanceDesordre(List<Integer> permActuelle, List<Integer> permSuivante) {
        if (permActuelle.size() != permSuivante.size()) {
            throw new IllegalArgumentException("Les deux permutations doivent avoir la même taille.");
        }
        if (!contiennentMemeElements(permActuelle, permSuivante)) {
            throw new IllegalArgumentException("Les permutations doivent contenir les mêmes éléments.");
        }

        int distanceKendallTau = 0;
        int n = permActuelle.size();

        // Crée une table de correspondance des positions dans la permutation cible
        Map<Integer, Integer> positionMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            positionMap.put(permSuivante.get(i), i);

        }

        // Compte le nombre d'inversions
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (positionMap.get(permActuelle.get(i)) > positionMap.get(permActuelle.get(j))) {
                    distanceKendallTau++;
                }
            }
        }

        return distanceKendallTau;
    }

    /**
     * Vérifie si deux listes contiennent les mêmes éléments (peu importe l'ordre).
     *
     * @param list1 première liste
     * @param list2 deuxième liste
     * @return true si elles contiennent les mêmes éléments
     */
    private static boolean contiennentMemeElements(List<Integer> list1, List<Integer> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

    /**
     * Génère une permutation ayant une distance Kendall-Tau proche de la cible définie
     * par le pourcentage {@code quantite}. Elle tente de converger vers ce niveau de désordre
     * par des échanges aléatoires validés uniquement si la distance se rapproche de la cible.
     *
     * @return la liste désordonnée générée
     */
    @Override
    public List<Integer> generateurDesordre() {
        if (liste.isEmpty()) {
            throw new IllegalStateException("La liste est vide. Veuillez générer une liste d'abord.");
        }

        int kendallTauCible = (int) Math.round((taille * (taille - 1) / 2) * quantite);
        int kendallTauActuel = calculeDistanceDesordre(listeInitiale, liste);
        Random random = new Random();

        int maxIterations = 10 * taille;
        int iterations = 0;

        System.out.println("🎯 Distance Kendall-Tau cible : " + kendallTauCible);
        System.out.println("📌 Distance Kendall-Tau actuelle avant swaps : " + kendallTauActuel);

        while (Math.abs(kendallTauActuel - kendallTauCible) > 1 && iterations < maxIterations) {
            int index1 = random.nextInt(taille);
            int index2;
            do {
                index2 = random.nextInt(taille);
            } while (index1 == index2);

            // Swap et recalcul
            Collections.swap(liste, index1, index2);
            int newKendallTau = calculeDistanceDesordre(listeInitiale, liste);

            // Vérifier si le swap rapproche de la cible
            if (Math.abs(newKendallTau - kendallTauCible) < Math.abs(kendallTauActuel - kendallTauCible)) {
                kendallTauActuel = newKendallTau;
            } else {
                // Annuler le swap s'il ne réduit pas Kendall-Tau
                Collections.swap(liste, index1, index2);
            }

            iterations++;
        }

        System.out.println("✅ Distance Kendall-Tau finale : " + kendallTauActuel);
        return liste;
    }

}
