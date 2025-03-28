package experience.automatisation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class Resultats {
    private static final String CHEMIN_FICHIER = "experience/stockage/resultats.csv";
    private static FileWriter writer = null;
    
    private String algo;
    private int taille;
    private double desordre;
    //private String repartition;
    private String typeDesordre;

    public Resultats(String typeDesordre, String algo, int taille, double quantiteDedesordre) throws IOException {
        this.typeDesordre=typeDesordre;
        this.algo = algo;
        this.taille = taille;
        this.desordre = quantiteDedesordre;
        //this.repartition = repartition;

        // Vérifier si le répertoire existe, sinon le créer
        File repertoire = new File("experience/stockage");
        if (!repertoire.exists()) {
            repertoire.mkdirs();
        }
        

        // Vérifier si le fichier existe déjà
        File fichier = new File(CHEMIN_FICHIER);
        boolean fichierExiste = fichier.exists();

        // Initialiser le FileWriter en mode append (ajout)
        if (writer == null) {
            writer = new FileWriter(fichier, true);

            // Si le fichier n'existe pas, ajouter l'en-tête
            if (!fichierExiste) {
                writer.write("typeDesordre,Algorithme,Taille,Désordre,Comparaisons,Temps,Affectations,Accès_aux_données\n");
            }
        }
    }

    public void enregistrerResultats(long nbComparaison, double tempsExecution, long nbreAffectation, long nbAccesMemoire) throws IOException {
        Locale locale = Locale.US;  
        String ligne = String.format(locale, "%s,%s,%d,%.1f,%d,%.5f,%d,%d\n",
                                     typeDesordre,algo, taille, desordre, nbComparaison, tempsExecution, nbreAffectation, nbAccesMemoire);
        writer.write(ligne);
               
    }

    // Fermer le fichier après utilisation (ex. : en fin de programme)
    public static void fermer() throws IOException {
        if (writer != null) {
            writer.close();
            writer = null;  // Remettre à null pour éviter une réouverture incorrecte
        }
    }
}
