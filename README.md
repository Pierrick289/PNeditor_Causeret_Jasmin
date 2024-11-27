PNEditor (Petri Net editor)
========

The original PNE can be downloaded from [www.pneditor.org](http://www.pneditor.org/)

This instance is the result of a student project by Joris Thaveau for teaching purpose.

It is a simplified Petri net editor that allows the editing of many PetriNet models.

To use:

1. Run org.pneditor.editor.Main as a Java application
2. Select the model used (the menu scans the org.pneditor.petrinet.adapters folder to build a list of available models and adapters). initial and imta are available. Places and transitions are displayed in different ways.
3. Edit the PetriNet and fire transitions.

You may experiment some unexpected exceptions. Especially if you mix models.

The pedagogical approach consists in:

1. Develop your own PetriNet model in an independent project/environment - with no GUI, just the ''business'' view
2. Pack it as a jar, and let it be visible in the path
3. Develop an Adapter in the org.pneditor.petrinet.adapters folder of PNE to make your model editable

The adapter may be simple or complex depending on the "distance" between your model and the one expected by PNE.

Code license: [GNU GPL v3](http://www.gnu.org/licenses/gpl.html)

Requirements: Java SE 8+

<hr>

#Fil rouge Petri Net - Implémentation


*Ce document est écrit en Français.*

*Ce document décrit l'agencement du code, son contenu, la méthodologie de lancement des tests, ainsi que quelques remarques pour faire le lien entre la conception initiale et le code actuel.*

###Agencement et contenu

Il y a tout d'abord deux bibliothèques :
<ul>
	<li>JRE System Library : celle du jdk 21 (la version d'Eclipse et du JRE qu'on utilise est celle-ci).
	<li>JUnit 5 : pour faire des tests JUnit.
</ul>
Il y a aussi :
<ul>
	<li>Le rapport de relecture de nos camarades, complété par nos décisions finales.
	<li>La grille d'autoévaluation des bonnes pratiques.
</ul>
Il y a ensuite le package src, dans lequel se trouve : 
<ul>
	<li>Ce fichier texte README.
	<li>Le package fil_rouge dans le lequel il y a notre implémentation du PetriNet.
	<li>Le package unitTest dans lequel nous avons fait des tests JUnit.
	<li>Le package test dans lequel nous avons fait quelques tests avec des méthodes main, pour nous aider dans la compréhension et rédaction de notre code.
</ul>

###Lancement du code et des tests :

Il suffit de :
<ol>
	<li>Lancer la méthode main d'un des fichiers de package test, en cliquant sur Run As Java Application sur le fichier.
	<li>Lancer les tests JUnit d'un ou de tout les fichiers du package unitTest, en cliquant sur Coverage As JUnit Test (pour avoir la couverture des tests).
</ol>

###Lien entre le code actuel et la conception initiale (diagramme de classe UML) : 

Notre code ne représente pas exactement le diagramme de classe que nous avions construit initialement.
Il y a trois différences majeures :
 - nous avons remplacé la classe Arc par une classe Abstraite et rajouté un interface IPetriNet
 - nous avons changé les méthodes d'ajout d'arcs, de places et de transition dans la classe PetriNet
 - nous avons rajouté des méthodes dans nos classes pour faciliter la rédaction des modifications dans PetriNet, notamment dans :
 	-Arc : ajout de getters.
 	-Transition : ajout de méthodes pour connaitre s'il y a un arc entre une place et une transition, et avoir l'arc le cas échéant, mais aussi ajout d'une méthode pour enlever un arc d'une transition.

Concernant les nouvelles méthodes d'ajout d'éléments dans PetriNet : nous avons fait le choix de renvoyer les éléments après leurs ajout,
ce qui est plus simple pour construire le PetriNet.
De plus, les méthodes d'ajout d'arcs prennent maintenant 3 paramètres : le poids de l'arc, la place et la transition qui sont connectées à l'arc.
Nous avons fait ce choix pour éviter un problème de taille : 
Par exemple, considérons le cas d'une place et d'une transition, reliées par deux arcs différent.
La place contient un jeton, et les deux arcs ont un poids de 1. Avec notre implémentation, chacun des arcs sera "triggerable", donc la transition aussi.
Lorsque l'on va lancer la transition, chaque arc va retirer un jetons à la place, et la place finira avec un nombre de jetons de -1.
De même, il risque d'y avoir des problèmes s'il y a deux arcs de types différents sortant d'une même place et entrants dans une même transition.
Pour éviter cela, nous ajoutons la transition en paramètre des méthodes d'ajout d'arcs. Ainsi, quand on voudra ajouter un arc, on vérifie d'abord
qu'il n'y a pas déjà un arc entre la place et la transition donner en paramètre.
Si non, on ajoutera simplemment l'arc.
Si oui, on remplacera l'ancien arc par le nouveau. 
De cette façon, il ne peut pas y avoir deux arcs qui relient une place et une transition. 

MAJ du 13/11:
Nous avons rajouté des getters à la classe Transition, nous avons modifié les méthodes remPlace() et remTransition de la classe PetriNet
et nous avons ajouté un diagramme UML que nous avons obtenu en testant ObjectAid. 
