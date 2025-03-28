package vue;

import javax.swing.*;

import model.*;

import java.awt.*;

/**
 * Fenêtre principale de l'application de visualisation de tri.
 * <p>
 * Cette classe étend {@link JFrame} et initialise une interface graphique
 * en plein écran contenant une instance de {@link VueTri}.
 * Elle est directement liée au modèle {@link ModelTri}.
 */
public class TriGui extends JFrame  {

    /**
     * Constructeur principal de la fenêtre graphique.
     *
     * @param controlerTri le modèle de tri (incluant la stratégie de tri et de génération)
     */
    public TriGui(ModelTri controlerTri) {
        // Titre de la fenêtre
        this.setTitle("Algo de Tri");

        // Fermer proprement l'application à la fermeture de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Récupérer la taille de l'écran pour une mise en plein écran
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle screenRect = ge.getMaximumWindowBounds();
        int screenWidth = screenRect.width;
        int screenHeight = screenRect.height;
        this.setSize(screenWidth, screenHeight);

        // Disposition générale en BorderLayout
        this.setLayout(new BorderLayout());

        // Ajouter la vue centrale qui dessine le tri
        VueTri vueTri = new VueTri(controlerTri);
        this.add(vueTri, BorderLayout.CENTER);
        // Rendre la fenêtre visible
        this.setVisible(true);

        setLayout(new BorderLayout());

    }
    
    
}
