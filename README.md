# TP2 : Génération aléatoire d'arbres couvrants

**Membre :**
- Ranzi teo

## Description du projet

Ce projet a pour but d'implémenter et de comparer différents algorithmes de génération d'arbres couvrants aléatoires sur des graphes. Nous nous sommes concentrés sur des graphes de type "Grille" pour visualiser les résultats sous forme de labyrinthes.

Quatre algorithmes ont été implémentés :
1.  **RandomWeightMST** (Kruskal/Prim avec poids aléatoires)
2.  **RandomDFS** (Parcours en profondeur aléatoire)
3.  **Aldous-Broder** (Marche aléatoire simple)
4.  **Wilson** (Marche aléatoire avec effacement de boucles - Loop Erased Random Walk)

## Compilation et Exécution

Le projet utilise un `Makefile` pour la compilation et l'exécution.

### Commandes disponibles :

* **Compiler le projet :**
    ```bash
    make compile
    ```

* **Exécuter le programme :**
    ```bash
    make exec
    ```
    *Cette commande lance la génération des arbres sur une grille de 100x100 (Ordre 10 000), calcule les statistiques sur 40 échantillons pour chaque algorithme, et génère les images des labyrinthes (fichiers `.png`).*

* **Nettoyer les fichiers compilés :**
    ```bash
    make clean
    ```

## Analyse des Résultats

Les algorithmes ont été testé sur une grille de taille **100x100** (contenant 10 000 sommets). Voici les moyennes obtenues sur **40 échantillons** :

| Algorithme | Temps moyen | Diamètre moyen | Excentricité moy. | Nombre de feuilles | Nœuds de degré 2 |
| :--- | :---: | :---: | :---: | :---: | :---: |
| **RandomWeightMST** | 1 ms | 604 | 156 | 3053 (~30%) | 4293 |
| **RandomDFS** | 3 ms | 3839 | 1001 | 998 (~10%) | 8022 |
| **Aldous-Broder** | 11 ms | 737 | 186 | 2937 (~29%) | 4483 |
| **Wilson** | 4 ms | 703 | 181 | 2931 (~29%) | 4492 |

### Comparaison des Algorithmes

**1. Uniformité (Wilson, Aldous-Broder, RandomWeightMST)**
On observe que ces trois algorithmes produisent des résultats statistiques très proches :
* Leur **diamètre** tourne autour de 600-750.
* Le nombre de **feuilles** est stable autour de 3000 (soit 30% des sommets).
* L'**excentricité** est similaire (~150-180).

Ces résultats confirment que ces trois algorithmes génèrent des arbres couvrants selon une **distribution uniforme**. Les arbres produits sont "équilibrés", foisonnants et possèdent une structure fractale typique.

**2. Le cas particulier du RandomDFS**
L'algorithme de parcours en profondeur aléatoire (RandomDFS) se distingue radicalement :
* Son **diamètre est immense** (3839 contre ~700 pour les autres).
* Il possède très **peu de feuilles** (~10% contre ~30%).
* La grande majorité des nœuds sont de **degré 2** (80%).

Cela s'explique par la nature de l'algorithme qui cherche à aller "le plus loin possible" avant de revenir en arrière. Il génère des arbres filiformes, ressemblant à de longs serpents tortueux avec peu d'embranchements. Ce n'est **pas** un générateur uniforme.

**3. Performance**
* **RandomWeightMST** et **Wilson** sont très rapides (1ms et 4ms). Wilson est particulièrement efficace sur les grilles car il converge vite une fois que l'arbre commence à remplir l'espace.
* **Aldous-Broder** est le plus lent (11ms), car il souffre du problème du "collectionneur de vignettes" : il met beaucoup de temps à trouver les derniers sommets non visités à la fin de l'exécution.

## Addendum : Relation entre Arbres Couvrants et Labyrinthes

Un arbre couvrant généré sur un graphe de type "grille" correspond exactement à la définition mathématique d'un **labyrinthe parfait**.

* **Connexe :** Toutes les cases (sommets) sont accessibles.
* **Acyclique :** Il n'y a pas de boucles (on ne peut pas tourner en rond).
* **Unicité du chemin :** Il existe un unique chemin entre n'importe quelle paire de cases (par exemple entre l'entrée et la sortie).

Les murs du labyrinthe correspondent aux arêtes du graphe dual qui ne sont pas sélectionnées dans l'arbre couvrant. Nos visualisations (les images `.png` générées) illustrent ce principe : les chemins colorés représentent les arêtes de l'arbre.

## Références et Bibliographie

Pour la réalisation de ce projet, je me suis appuyé sur les ressources suivantes :

### 1. Documents de Cours
* **Sujet du TP2 :** "Génération aléatoire d'arbres couvrants", Algorithmique 2, Licence Informatique, Aix-Marseille Université.
* **Articulation Points :** "Finding Articulation Points", Université de Montréal (mentionné dans le sujet pour les extensions).

### 2. Algorithmes et Structures de Données
* **Union-Find (Weighted Quick Union) :**
    * *Source :* Algorithms, 4th Edition (Sedgewick & Wayne) - Princeton University.
    * *Lien :* [https://algs4.cs.princeton.edu/15uf/](https://algs4.cs.princeton.edu/15uf/)
    * *Usage :* Utilisé pour l'implémentation efficace de la structure Union-Find dans l'algorithme d'Insertion Aléatoire (Kruskal) et la gestion des composantes connexes. (Voir `src/AlgoGraph/UnionFind.java`).

### 3. Articles Scientifiques (Algorithmes Uniformes)
* **Algorithme de Wilson :**
    * *Papier :* Wilson, D. B. (1996). "Generating random spanning trees more quickly than the cover time".
    * *Description :* Décrit la méthode "Loop-Erased Random Walk" (LERW) que j'ai implémenté pour générer des arbres uniformes efficacement.
* **Algorithme d'Aldous-Broder :**
    * *Papier :* Broder, A. (1989). "Generating random spanning trees".
    * *Description :* Décrit l'approche basée sur une marche aléatoire simple couvrant tout le graphe.

### 4. Visualisation et Labyrinthes
* **Visualizing Algorithms (Mike Bostock) :**
    * *Lien :* [https://bost.ocks.org/mike/algorithms/](https://bost.ocks.org/mike/algorithms/)
    * *Usage :* Une référence visuelle excellente pour comprendre comment les différents algorithmes (notamment le DFS aléatoire vs Prim/Wilson) remplissent une grille et génèrent des textures de labyrinthes différentes.