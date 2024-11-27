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

Il y a tout d'abord trois bibliothèques :
<ul>
	<li>JRE System Library : celle du jdk 21 (la version d'Eclipse et du JRE qu'on utilise est celle-ci).
	<li>JUnit 5 : pour faire des tests JUnit (que nous avons rajouté).
	<li>Referenced Libraries (une bibliothèque qui était déjà dans le projet mapd-pne-code quand nous l'avons récupéré sur Moodle).
</ul>
Il y a aussi :
<ul>
	<li>Le rapport de relecture de nos camarades, complété par nos décisions finales. (en pdf)
	<li>La grille d'autoévaluation des bonnes pratiques. (en pdf)
	<li>Ce fichier README.md 
	<li>Un image PNG qui contient le diagramme de classe de rétro-ingénierie de notre implantation finale (notre façon d'implémenter un PetriNet).
</ul>
Il y a ensuite plusieurs packages qui étaient déjà présent, dont le package src que nous avons modifié. Nous avons rajouté : 
<ul>
	<li>Dans src.org.pneditor.petrinet.models , un package causeretjasmin qui contient notre implémentation finale d'un PetriNet.
	<li>Dans src.org.pneditor.petrinet.adapters , un package causeretjasmin qui contient notre adaptateur.
	<li>Dans src , un package unitTest dans lequel nous avons fait des tests JUnit pour notre implémentation finale.
</ul>

###Lancement des tests :

Pour les tests, il suffit de :
<ol>
	<li>Lancer les tests JUnit d'un ou de tout les fichiers du package unitTest, en cliquant sur Coverage As JUnit Test pour avoir la couverture des tests. Nos tests couvre 100% de notre implémentation finale. 
</ol>

###Lancement du code (faire fonctionner le PNE avec notre modèle) :

Pour cela, il faut : 
<ol>
	<li>Exécuter le main dans src.org.pneditor.editor
	<li>Dans la fenêtre qui s'ouvre, cliquer sur "Change model" dans la barre du haut puis sur causeretjasmin.
	<li>Vous pouvez ensuite construire un PetriNet, enlever des places, des arcs, des transistions, changer des arcs(clique dessus, clique droit, puis le set en tant que inhibiteurs, videur ou changer son poids). 
</ol>

###Lien entre le code actuel et la conception initiale (diagramme de classe UML) : 

Notre code ne représente pas exactement le diagramme de classe que nous avions construit initialement.
Le diagramme de classe final est celui obtenu par retro-ingénierie avec ObjectAid. 
Il y a trois différences majeures :
 - nous avons remplacé la classe Arc par une classe Abstraite et rajouté un interface IPetriNet
 - nous avons changé les méthodes d'ajout d'arcs, de places et de transition dans la classe PetriNet
 - nous avons rajouté des méthodes dans nos classes pour faciliter la rédaction des modifications dans PetriNet, notamment dans :
 	-Arc : ajout de getters.
 	-Transition : ajout de getters et ajout de méthodes pour connaitre s'il y a un arc entre une place et une transition, et avoir l'arc le cas échéant, mais aussi ajout d'une méthode pour enlever un arc d'une transition.

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

Remarque : Mise à jour du 13/11 (dernière modification) : nous avons modifié les méthodes remPlace() et remTransition de la classe PetriNet.

