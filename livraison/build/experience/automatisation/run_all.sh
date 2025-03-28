#!/bin/bash

# Vérification du nombre d'arguments
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <nombre_experimentations>"
    exit 1
fi

# Nombre d'expérimentations (passé en argument)
nbExperiments=$1

# Charger la configuration
source experience/automatisation/config.sh

# Vérification que les tableaux sont bien remplis
echo "Algorithmes: ${algorithmes[@]}"
echo "Tailles: ${tailleTableau[@]}"
echo "Quantité de désordre: ${quantiteDesordre[@]}"
echo "type de désordre: ${typeDeDesordre[@]}"
echo "Nombre d'expérimentations: $nbExperiments"

#echo "Répartitions de désordre: ${repartitionsDesordre[@]}"
#echo "Génerateurs: ${generateurs[@]}"

# Répertoire où placer les fichiers compilés
BUILD_DIR="../build"

# Compilation du projet si nécessaire
echo "Compilation du projet..."
javac -d "$BUILD_DIR" model/algo/*.java model/generateur/*.java model/ModelTri.java experience/Main.java experience/automatisation/Resultats.java

# Vérification si la compilation a réussi
if [ $? -ne 0 ]; then
    echo "Erreur lors de la compilation. Vérifiez votre code."
    exit 1
fi


for taille in "${tailleTableau[@]}"; do
    for algo in "${algorithmes[@]}"; do
        for desordre in "${quantiteDesordre[@]}"; do
                for typeDeDesordre in "${typeDeDesordre[@]}"; do
                    for ((i=1; i<=nbExperiments; i++)); do
                        echo "Exécution $i/$nbExperiments : Algo: $algo, Taille: $taille, Désordre: $typeDeDesordre, Quantité: $desordre"
                        java -cp "$BUILD_DIR" experience.Main "$typeDeDesordre" "$algo" "$taille" "$desordre" 
                    done
                done
        done
    done
done
