# TP8 suite

### 1. Expression régulière

**Etapes :**

1. ``t[^aue][^ue][^rue][^eu][^aue][^ue]``
2. ``tr[^iu]v[^ie]al``

### 2. Algorithme de construction d'expression régulière

1. Générer mot aléat
3. Parcourir le résultat de ``évaluer()``:
  - Si le caractère est ``x`` : mettre la lettre dans un string des lettres interdites.
2. Parcourir le résultat de ``évaluer()``:
  - Si le caractère est ``o`` : mettre la lettre correspondante dans un string.
  - Si le caractère est ``-`` : mettre la lettre correspondante entre ``[^`` et ``]`` avec le string des lettres interdites et ajouter au string précédent.
  - Si le caractère est ``x`` : mettre le string des lettres interdites entre ``[^`` et ``]`` et ajouter au string précédent.
