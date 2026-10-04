# TP APO : Encapsulation et graphes d'objets

## Introduction

Ce TP a pour objectif de vous faire découvrir les bases de la **programmation orientée objet** (POO) à travers un exemple concret : la représentation d'entiers relatifs avec leur décomposition en facteurs premiers.

Vous connaissez déjà la programmation (variables, boucles, fonctions). En POO, on organise le code autour d'**objets** : des entités qui regroupent des **données** (appelées *attributs*) et des **comportements** (appelés *méthodes*). Une **classe** est le moule qui décrit comment fabriquer ces objets.

Un concept central de la POO est l'**encapsulation** : un objet est responsable de la cohérence de son propre état interne, et on contrôle soigneusement ce que le monde extérieur peut voir ou modifier. Ce TP va vous faire toucher du doigt pourquoi c'est important et quels pièges vous attendent si vous l'ignorez.

### Environnement

Le projet est un projet **Maven** avec **Java 25**. Les tests sont écrits avec **JUnit 5** et se trouvent dans `src/test/`. Pour compiler et lancer les tests :

```
mvn test
```

Tous les tests ne passerons pas, ainsi vous devriez avoir beaucoup de logs indiquant l'insalaltion des bibliothèques de codes relatives à ce TP, terminés par une series de logs indiquant l'échec de tests unitaire, pas de panique, c'est normal puisque vous n'avez pas encore complété le TP (un des buts sera de faire passer ces tests, ainsi que d'en écrire quelques uns vous même).

Dans la suite de ce TP, vous travaillerez principalement dans le fichier `src/main/java/uga/l3m/fraction/EntierRelatif.java`.

---

## Partie 1 : La classe `EntierRelatif`

### Contexte mathématique

Un entier relatif est un entier qui peut être positif, négatif ou nul. Tout entier relatif non nul admet une **décomposition unique en produit de facteurs premiers**, appelée décomposition canonique. Par exemple :

- 12 = 2 × 2 × 3
- −18 = −1 × 2 × 3 × 3
- 7 est lui-même premier : 7 = 7

Nous représenterons cette décomposition comme une liste d'entiers triée par ordre croissant. Le signe est encodé par un facteur `−1` en tête de liste si l'entier est négatif.

**Cas particuliers :**

| Valeur | Liste des facteurs |
|-------:|-------------------|
| 0      | `[]` (liste vide) |
| 1      | `[1]`             |
| −1     | `[-1]`            |
| 12     | `[2, 2, 3]`       |
| −18    | `[-1, 2, 3, 3]`   |

---

### Exercice 1 : Une première classe d'objets

Ouvrez le fichier `EntierRelatif.java`. Vous y trouverez une classe partiellement écrite.

**1.a.** Identifiez les **attributs** de la classe et leur type. Quelle est leur visibilité (`public` ou `private`) ? Pourquoi choisit-on `private` plutôt que `public` pour les attributs ?

**1.b.** La classe possède deux constructeurs. Expliquez le rôle de chacun. Que fait exactement le constructeur `EntierRelatif(EntierRelatif e)` ?

**1.c.** Un **graphe d'objets** est un dessin qui représente les objets présents en mémoire à un instant donné, leurs attributs et les liens (flèches) entre eux. Dessinez sur papier le graphe d'objets correspondant à l'exécution du code suivant, juste après la deuxième ligne :

```java
EntierRelatif a = new EntierRelatif(12);
EntierRelatif b = new EntierRelatif(a);
```

À ce stade, l'attribut `facteursPremiers` n'est pas calculé (`null`). Représentez-le explicitement dans votre dessin.

---

### Exercice 2 : Cohérence de l'état interne

Un objet doit à tout moment être dans un état **cohérent** : les attributs `valeur` et `facteursPremiers` doivent toujours se correspondre. Cette responsabilité appartient à la classe elle-même, pas au code qui l'utilise.

**2.a.** Complétez la méthode `setValeur(Integer value)` pour qu'elle mette à jour `valeur`. Pour l'instant, réinitialisez simplement `facteursPremiers` à `null`, vous l'implémenterez à l'exercice suivant.

**2.b.** Complétez le constructeur `EntierRelatif(Integer valeur)` pour qu'il initialise les deux attributs de façon cohérente.

---

### Exercice 3 : Décomposition en facteurs premiers

Vous allez écrire une méthode **privée** `calculerFacteursPremiers()` qui calcule et stocke la décomposition de `valeur` dans `facteursPremiers`.

Voici l'algorithme à implémenter :

1. Si `valeur == 0`, affecter une liste vide à `facteursPremiers` et s'arrêter.
2. Si `valeur < 0`, ajouter `−1` à la liste. Travailler ensuite sur `n = |valeur|`.
3. Poser `d = 2`. Tant que `d * d <= n` :
   - si `d` divise `n`, ajouter `d` à la liste et remplacer `n` par `n / d` ;
   - sinon, incrémenter `d` de 1.
4. À la fin de la boucle, si `n > 1`, ajouter `n` à la liste.
5. Cas particulier : si `|valeur| == 1`, affecter `[1]` à la liste (ou `[-1]` si la valeur est négative).

***Indications***: 
- _la méthode doit allouer une collection `List<Integer>` et l'affecter à `facteursPremiers`, mais il n'est pas possible de construire une instance de `List<Integer>` (pour des raisons que vous comprendrez plus tard), il vous faut construire une instance d'`ArrayList<Integer>` que vous affecterez simplement à l'attribut `facteursPremiers`._
- _la seule action faite par cet algorithme sur la séquence de facteur consiste à ajouter un facteur à la fin (pour garantir la contrainte d'ordre des facteurs). Cet ajout se fait simplement en utilisant la méthode `add(Integer f)` définie sur les instances de classe `ArrayList<Integer>`._

**3.a.** Pourquoi cette méthode doit-elle être `private` et non `public` ? Quel principe cela illustre-t-il ? Y a-t-il d'autres méthodes dans votre classe qui pourraient rester privées ?

**3.b.** Implémentez `calculerFacteursPremiers()`. Mettez à jour `setValeur` et le constructeur pour l'appeler dès qu'une nouvelle valeur est affectée.

**3.c.** Vérifiez votre implémentation avec les cas du tableau ci-dessus. Vous pouvez écrire un petit `main` dans `App.java` pour afficher les résultats, ou écrire des tests JUnit dans `src/test/java/uga/l3m/EntierRelatifTest.java`.

---

### Exercice 4 : Le graphe d'objets revisité

**4.a.** Dessinez le graphe d'objets correspondant à l'exécution de :

```java
EntierRelatif a = new EntierRelatif(12);
```

Faites apparaître : l'objet `a`, son attribut `valeur` (qui vaut `12`), et son attribut `facteursPremiers` qui est une **référence** vers un objet `List` contenant les entiers `[2, 2, 3]`.

**4.b.** Dessinez maintenant le graphe d'objets après l'ajout de :

```java
EntierRelatif b = new EntierRelatif(a);
```

Regardez le constructeur de copie actuel. Combien d'objets `List` y a-t-il en mémoire ? Est-ce que `a` et `b` partagent la même liste ou en possèdent-ils chacun une ? Que se passerait-il si on modifiait la liste de `a` ?

---

### Exercice 5 : Le piège du getter

Regardez la méthode `getFacteursPremiers()` telle qu'elle est actuellement écrite. :

```java
public class EntierRelatif {
    ...
    public List<Integer> getFacteursPremiers() {
        return facteursPremiers;
    }
    ...
}
```

**5.a.** Que retourne-t-elle exactement ? S'agit-il d'une valeur copiée ou d'une référence vers l'objet interne ?

**5.b.** Expliquez pourquoi le code suivant est problématique :

```java
EntierRelatif a = new EntierRelatif(12);
List<Integer> liste = a.getFacteursPremiers();
liste.clear();
System.out.println(a.getValeur());           // Toujours 12 ?
System.out.println(a.getFacteursPremiers()); // Que contient la liste ?
```

En quoi le principe d'encapsulation est violé ?

**5.c.** Corrigez `getFacteursPremiers()` pour protéger l'état interne. Trois approches sont possibles :

**Approche 1 : copie indépendante**
```java
return new ArrayList<>(facteursPremiers);
```
Un nouvel objet `ArrayList` est créé en mémoire, contenant les mêmes éléments. L'appelant reçoit une liste **totalement indépendante** : la modifier n'affecte pas l'objet `EntierRelatif`.

**Approche 2 : vue non modifiable (wrapper)**
```java
return Collections.unmodifiableList(facteursPremiers);
// ou en Java 10+ :
return List.copyOf(facteursPremiers); // copie immuable, voir ci-dessous
```
[`Collections.unmodifiableList`](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#unmodifiableList-java.util.List-) ne copie **pas** les données. Il crée un objet *wrapper* (enveloppe) qui garde une **référence vers la liste originale** et bloque toute tentative de modification (`add`, `remove`, etc.) en lançant `UnsupportedOperationException`. En conséquence, si la liste des facteurs premiers change, alors le wrapper va refléter ces changements. Dessinez le graphe d'objets pour comprendre ce qui se passe. Cette approche viole-t-elle l'encapsulation ?

**Approche 3 : copie immuable (Java 10+)**
```java
return List.copyOf(facteursPremiers);
```
[`List.copyOf`](https://docs.oracle.com/en/java/docs/api/java.base/java/util/List.html#copyOf(java.util.Collection)) fait une **vraie copie** des éléments dans une nouvelle liste immuable (ni `add`, ni `remove`, ni même `set`). C'est l'approche la plus stricte : l'appelant obtient une liste figée qui ne reflète pas les modifications ultérieures de l'objet.

### Exercice 6 : Le piège du setter

Regardez la méthode `setFacteursPremiers(List<Integer> facteursPremiers)`.

**6.a.** Expliquez pourquoi le code suivant est problématique :

```java
List<Integer> maListe = new ArrayList<>(List.of(2, 3, 5));
EntierRelatif a = new EntierRelatif(1);
a.setFacteursPremiers(maListe);
maListe.add(7); // modification depuis l'extérieur
System.out.println(a.getValeur());            // Que vaut a maintenant ?
System.out.println(a.getFacteursPremiers()); // Cohérent ?
```

Dessinez le graphe d'objets avant et après `maListe.add(7)` pour visualiser le problème.

**6.b.** Corrigez `setFacteursPremiers` pour qu'il :
1. fasse une **copie défensive** de la liste reçue (afin de ne pas garder de référence vers la liste de l'appelant) ;
2. recalcule `valeur` à partir des facteurs en faisant le produit de tous les éléments de la liste.

**6.c.** Faut-il valider que la liste passée est bien une décomposition valide (facteurs premiers, triés, au plus un `−1` en tête) ? Quels sont les avantages et inconvénients d'une telle validation ?

---

### Exercice 7 : Le piège du constructeur qui copie un autre `EntierRelatif`

Revenez sur le constructeur `EntierRelatif(EntierRelatif e)`.

**7.a.** En vous appuyant sur le graphe d'objets de l'exercice 4.b, caractérisez ce que fait ce constructeur.

**7.b.** Écrivez un exemple de code (quelques lignes) qui démontre que deux objets `a` et `b` (où `b` est créé par `new EntierRelatif(a)`) partagent la même liste interne et que modifier l'un modifie l'autre.

**7.c.** Corrigez ce constructeur pour respecter le principe d'encapsulation.

---

### Exercice 8 : Évaluation paresseuse

Jusqu'ici, la décomposition en facteurs premiers est calculée dès la construction de l'objet, même si on n'a jamais besoin de la consulter. Sur un grand nombre d'objets, ce calcul peut représenter un coût inutile.

L'**évaluation paresseuse** consiste à reporter un calcul au moment où le résultat est effectivement demandé, et à le mettre en cache pour ne pas le recalculer.

**8.a.** Ajoutez un attribut booléen privé `facteursPremiersCalcules`, initialisé à `false`. Modifiez `getFacteursPremiers()` pour qu'il déclenche le calcul uniquement lors du premier appel, puis retourne directement le résultat mis en cache lors des appels suivants.

**8.b.** Que doit-il se passer si on appelle `setValeur` après avoir déjà calculé les facteurs ? Mettez à jour la logique en conséquence.

**8.c.** Dessinez le graphe d'objets juste après `new EntierRelatif(12)` avec l'évaluation paresseuse activée. Comparez-le avec le graphe de l'exercice 4.a. Quelle différence observez-vous ?

**8.d.** Quels sont les avantages et les inconvénients de l'évaluation paresseuse ? Dans quel cas vaut-il mieux calculer immédiatement ?

---

### Exercice 9 : Représentations textuelles et évaluation paresseuse symétrique

**9.a. `setFacteursPremiers` avec évaluation paresseuse**

À l'exercice 6, vous avez implémenté `setFacteursPremiers` de façon à recalculer immédiatement `valeur` à partir du produit des facteurs. À l'exercice 8, vous avez rendu le calcul des facteurs *paresseux* côté `getFacteursPremiers`.

Appliquez le même principe dans l'autre sens : lorsque `setFacteursPremiers` est appelé, **ne calculez pas immédiatement `valeur`**. Stockez la nouvelle liste de facteurs (copie défensive), invalidez la valeur courante, et ne calculez le produit que lors du prochain appel à `getValeur()`.

- Ajoutez un attribut booléen privé `valeurCalculee`.
- Modifiez `getValeur()` en conséquence.
- Modifiez `setFacteursPremiers` en conséquence.
- Modifiez `setValeur` en conséquence.
- Corrigez si nécessaire les autres méthodes de la classe `EntierRelatif` en conséquence (constructeurs, accesseurs, copie, etc.).

Vérifiez votre implémentation en ajoutant des tests ou du code dans un `main`.

**9.b. `asStringDecimale()`**

Implémentez une méthode publique `String asStringDecimale()` qui retourne une représentation décimale de l'entier, avec le signe `−` si l'entier est négatif. Exemples :

| `valeur` | résultat attendu |
|---------:|-----------------|
| 12       | `"12"`          |
| -27      | `"-27"`         |
| 0        | `"0"`           |
| 1        | `"1"`           |

*Indication :* `Integer` possède déjà une méthode de conversion vers `String`, inutile de réinventer la roue.

**9.c. `asStringFacteursPremiers()`**

Implémentez une méthode publique `String asStringFacteursPremiers()` qui retourne une représentation de la décomposition en facteurs premiers, en séparant les facteurs par le caractère `x`. Exemples :

| `valeur` | résultat attendu   |
|---------:|--------------------|
| 12       | `"2 x 2 x 3"`      |
| -27      | `"-1 x 3 x 3 x 3"` |
| 7        | `"7"`              |
| 1        | `"1"`              |
| 0        | `""`               |


## Partie 2 : La classe `Rationnel`

La classe `Rationnel` représente un nombre rationnel $\frac{p}{q}$ comme la composition de deux `EntierRelatif` : un numérateur et un dénominateur.

Ouvrez le fichier `src/main/java/uga/l3m/fraction/Rationnel.java`. Vous y trouverez une implémentation partielle. Dans cette partie, vous allez la corriger et la compléter en appliquant les principes vus en Partie 1.

---

### Exercice 10 : Corriger la classe `Rationnel`

La classe `Rationnel` souffre de plusieurs problèmes d'encapsulation similaires à ceux rencontrés en Partie 1. Identifiez-les et corrigez-les en vous appuyant sur ce que vous avez appris.

Les points à revoir concernent notamment : les constructeurs, les accesseurs, la méthode `copier()`, et les méthodes qui modifient l'état de l'objet. Veillez à ce que les invariants du rationnel (numérateur et dénominateur toujours indépendants de l'extérieur) soient respectés.

Avant d'implémenter `simplifier()` et `multiplierPar()`, il faut choisir la bonne structure de données pour manipuler les listes de facteurs. Discutez de l'usage des listes chainées (`LinkedList`) versus des listes à accès direct (`ArrayList`) dans ce contexte.

#### Trace d'exécution attendue pour `simplifier()`

La simplification s'appuie sur la décomposition en facteurs premiers. Illustrons avec $\frac{12}{18}$ :

- Facteurs du numérateur : `[2, 2, 3]`
- Facteurs du dénominateur : `[2, 3, 3]`

On parcourt les facteurs du numérateur un par un. Pour chaque facteur, s'il apparaît également dans la liste du dénominateur, on le retire des deux listes :

| Facteur examiné | Présent au dénominateur ? | Numérateur restant | Dénominateur restant |
|---|---|---|---|
| `2` | oui | `[2, 3]` | `[3, 3]` |
| `2` | non | `[2, 3]` | `[3, 3]` |
| `3` | oui | `[2]` | `[3]` |

Résultat : numérateur = 2, dénominateur = 3, soit $\frac{2}{3}$.

#### Trace d'exécution attendue pour `multiplierPar()`

Multiplier deux fractions revient à concaténer les listes de facteurs :

$$\frac{2}{3} \times \frac{5}{7} = \frac{2 \times 5}{3 \times 7}$$

Illustrons avec $\frac{2}{3} \times \frac{5}{7}$ :

**Étape 1 : Regroupement des facteurs**

On concatène les listes de facteurs du numérateur d'une part, et du dénominateur d'autre part :

| | Numérateur | Dénominateur |
|---|---|---|
| Fraction 1 | `[2]` | `[3]` |
| Fraction 2 | `[5]` | `[7]` |
| Après concaténation | `[2, 5]` | `[3, 7]` |

**Étape 2 : Simplification**

On parcourt les facteurs du numérateur un par un. Pour chaque facteur, s'il apparaît dans la liste du dénominateur, on le retire des deux listes :

| Facteur examiné | Présent au dénominateur ? | Numérateur restant | Dénominateur restant |
|---|---|---|---|
| `2` | non | `[2, 5]` | `[3, 7]` |
| `5` | non | `[2, 5]` | `[3, 7]` |

Aucun facteur commun : la fraction est déjà irréductible.

Résultat : numérateur = `[2, 5]` → 10, dénominateur = `[3, 7]` → 21, soit $\frac{10}{21}$.

---

Illustrons maintenant un cas avec simplification, $\frac{2}{3} \times \frac{3}{5}$ :

**Étape 1 : Regroupement des facteurs**

| | Numérateur | Dénominateur |
|---|---|---|
| Fraction 1 | `[2]` | `[3]` |
| Fraction 2 | `[3]` | `[5]` |
| Après concaténation | `[2, 3]` | `[3, 5]` |

**Étape 2 : Simplification**

| Facteur examiné | Présent au dénominateur ? | Numérateur restant | Dénominateur restant |
|---|---|---|---|
| `2` | non | `[2, 3]` | `[3, 5]` |
| `3` | oui | `[2]` | `[5]` |

Résultat : numérateur = `[2]` → 2, dénominateur = `[5]` → 5, soit $\frac{2}{5}$.

---

### Exercice 11 : Représentations textuelles de `Rationnel`

Implémentez les deux méthodes déjà déclarées dans la classe :

- `asStringDecimale()` : retourne une chaîne de la forme `"p/q"`, par exemple `"-3/10"`.
- `asStringFacteursPremiers()` : retourne une représentation des facteurs, par exemple `"-1 x 3 / 2 x 5"` pour la fraction $\frac{-3}{10}$.

Réutilisez les méthodes correspondantes de `EntierRelatif`, aucune duplication de code ne doit être nécessaire.
