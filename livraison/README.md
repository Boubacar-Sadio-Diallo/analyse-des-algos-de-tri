# Analyse des algorithmes de tris

## Auteurs
- **Diallo Boubacar Sadio** : 22211641  
- **Diare Youssouf** : 22008756  
- **EMAM Mohamed El Mamy** : 22019076  
- **OLANGASSICKA ONDOUMBOU Franck Loick** : 22112035  

---

## Description

**Analyse des algorithmes de tris**  Ce projet met en œuvre et analyse les performances des algorithmes de tri en fonction de différents types de désordre, de tailles de données et de répartitions du désordre, tout en exploitant des structures de données optimisées.
---

## Prérequis

- **Java Development Kit (JDK)** : version 11 ou supérieure.
- **Apache Ant** : pour automatiser les tâches de construction.
- **Jupyter NoteBook et Python** pour l'analyse des résultats
- **Matplotlib** pour la visualisation des graphes
- **Pandas** pour la gestion et l'analyse des données
- **NETBEANS** pour avoir les fonctionnalités JUnit afin de lancer les tests unitaires 

---

## Installation et Compilation

1. **Cloner le projet** :
   ```bash
    svn checkout https://redmine-etu.unicaen.fr/svn/diallo-diare-olangassicka-emam
2. : cd livraison
    ant clean     # Nettoie les fichiers générés
    ant compile   # Compile le projet
    ant dist      # Génère le fichier .jar de distribution
    ant run       # Exécute l'interface graphique du projet
    ant javadoc   # Génère la documentation Javadoc
    ant experiment -Dnb=$nombre d'expérimentation selon vos besoins #lance les expérimentations et ouvre les resultats de l'expérimentation sous forme graphque en format html
    java -jar dist/AlgoDeTri-0.1jar # Exécuter l'application sur différentes machines
