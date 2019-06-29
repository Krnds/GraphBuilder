# D'un fichier CSV à un graphique SVG

À partir d'un simple fichier CSV (2 colonnes uniquement prises en compte), ce programme construit deux graphiques
avec les données du CSV. 

- LineChart : un graphique de points
- BarChart : un graphique à barres  

Certaines caractéristiques des graphiques peuvent être modifiées : 
- couleur
- contour
- largeur des barres
- hauteur et largeur


### Utilisation

Mettre votre fichier d'entrée dans le dossier GraphBuilder/csv
Lancer la classe Presentation.java
Indiquez les caractéristiques souhaitées, puis pour visualiser le SVG, il faudra aller l'ouvrir manuellement à cet endroit : 
- Pour un graphe de points : CSV-GraphBuilder/linechart.svg
- Pour un graphe de barres : CSV-GraphBuilder/barchart.svg

### Flow du programme

## Parser (Lecture et analyse du CSV) : 
1. Ouvre un fichier CSV, vérifie qu'il existe bien à l'endroit demandé : Package utils, classe CSVReader
2. Créé un objet CSVParser qui va lire ligne à ligne le CSV, séparer les données et les renvoyer dans un objet Graph

## Model (modèle de données) :
3. Cet objet Graph est lui-même composé de l'objet DataPoint qui sert d'encapsulation : un DataPoint représente un point du graphique constitué d'une String (Nom de la donnée ou légende) et d'une valeur Int du point.
4. Graph utilise donc les données du CSV indirectement grâce à DataPoint pour créér des listes de valeurs (légendes et valeurs numériques) qui pourront être utilisées pour créer un SVG. 

- Sous-classes de Chart :
5. La classe fille de Graph (appelé comme ceci car non "visuel") est LineChart (utilisation du mot "chart" car c'est un graphique, donc visuel), elle hérite de tous les champs de Graph (getLabels et getValues qui serviront à construire le SVG) mais aussi de tous les champs que l'utilisateur peut modifier. 
6. La classe fille de LineChart (qui descend donc elle-même de Graph) est BarChart car BarChart a les mêmes caractéristiques que LineChart sauf la largeur des barres qui est ajoutée. 

## ChartMarker (construction du SVG) :
7. BarChartSVGMaker qui construit le digramme en barres à partir de l'objet BarChart
8. LineChartSVGMaker qui construit le digramme de points à partir de l'objet LineChart

## Presentation (interface utilisateur) :
9. Demande le nom du fichier CSV, vérifie que le fichier est correct (nom et emplacement) et 
10. Créé le fichier SVG correspondant (Linechart.svg ou Barchart.svg dans le dosser CSV-GraphBuilder)

### Bugs

- Ne contrôle pas le flux d'entrée de l'utilisateur (quand il indique une valeur anormale)
- Pas de tests unitaires
- Problèmes avec la largeur et la hauteur du graphe choisie : toutes les données ne sont pas correctement représentées
- Ne fonctionne pas si le fichier CSV comprend des guillemets ou des points-virgules.


author : Karine Dias