#!/bin/sh

# Définir le chemin du fichier .jar
JAR_FILE="/app/app.jar"

# Définir le chemin où nous extrairons les fichiers du .jar
EXTRACT_DIR="/tmp/extracted_jar"

# Définir les chemins des fichiers
APP_PROPS="$EXTRACT_DIR/BOOT-INF/classes/application.properties"
EXTERNAL_PROPS="${CONFIG_FILE}"

# Créer un répertoire temporaire pour extraire le jar
mkdir -p $EXTRACT_DIR

# Extraire le contenu du JAR
unzip -o $JAR_FILE -d $EXTRACT_DIR

# Vérifier si les fichiers existent
if [ ! -f "$APP_PROPS" ] || [ ! -f "$EXTERNAL_PROPS" ]; then
    echo "Erreur: Un ou les deux fichiers de propriétés n'existent pas."
    echo "APP_PROPS: $APP_PROPS"
    echo "EXTERNAL_PROPS: $EXTERNAL_PROPS"
    exit 1
fi

# Lire les propriétés de EXTERNAL_PROPS
while IFS='=' read -r key value; do
    # Ignorer les lignes vides ou commentées
    if [ -n "$key" ] && [ -n "$value" ] && ! echo "$key" | grep -q '^#'; then
        # Échapper les caractères spéciaux dans la valeur
        escaped_value=$(echo "$value" | sed 's/[\/&]/\\&/g')
        # Remplacer la valeur dans application.properties
        sed -i "s/\${$key}/$escaped_value/g" "$APP_PROPS"
    fi
done < "$EXTERNAL_PROPS"

echo "Remplacement des propriétés terminé."
echo "Contenu final de application.properties :"
cat "$APP_PROPS"

# Recréer le .jar avec le fichier application.properties modifié
cd $EXTRACT_DIR
jar -uf $JAR_FILE BOOT-INF/classes/application.properties

# Nettoyer le répertoire temporaire
rm -rf $EXTRACT_DIR