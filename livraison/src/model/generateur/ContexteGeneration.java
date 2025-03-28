package model.generateur;

/**
 * Contexte d'exécution pour la génération de données de test.
 * Cette classe utilise une stratégie de génération de liste via {@link StrategieGeneration}.
 * Elle permet d'encapsuler l'appel à un générateur donné et de récupérer une liste prête à trier.
 */
public class ContexteGeneration {

    /** La stratégie de génération actuellement utilisée */
    protected StrategieGeneration generator;

    /**
     * Constructeur du contexte de génération.
     *
     * @param generator une stratégie de génération (ex: liste croissante, aléatoire...)
     */
    public ContexteGeneration (StrategieGeneration generator){
        this.generator = generator;

    }

    /**
     * Génère une nouvelle liste d'entiers via la stratégie courante.
     * 
     * @return un tableau d'entiers généré selon la stratégie définie
     * @throws IllegalArgumentException si aucun générateur n’est défini
     */
    public int[] generate() {
        if (this.generator !=null) {
            generator.generateList();      // Génère la liste complète
        }
        else{
            throw new IllegalArgumentException("vous n'avez pas de generateur definit ");
        }
        return generator.getList();         // Retourne le tableau désordonné

    }

    /**
     * Définit une nouvelle stratégie de génération.
     * 
     * @param generator la stratégie de génération à utiliser
     */
    public void setGenerator(StrategieGeneration generator) {
        this.generator = generator;
    }

    /**
     * Récupère la stratégie de génération actuelle.
     * 
     * @return la stratégie de génération utilisée
     */
    public StrategieGeneration getGenerator() {
        return generator;
    }
    
}
