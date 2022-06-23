# Projet Client-Serveur - Antoine PAYS & Fabien GENIN - LPWEB

Par binôme (du même groupe de TP), vous allez réaliser un petit projet incluant au moins deux resources que vous allez manipuler dans leur cycle de vie (création, mise à jour totale et/ou partielle, suppression, énumération, ...).

Pour ce faire vous allez travailler en Java et utiliser quelques librairies de SpringBoot (la partie mongo pour l'interaction base de données et la partie rest pour l'exposition HTTP).

## **Explications du MCD :**

### Nous avons 3 entités :

  <ul>
  <li>Concession qui représente une concession de voitures.</li>
  <li> Voiture qui représente une voiture.</li>
  <li> Equipement Auto qui représente un Equipement Auto.</li>
  </ul>
  
### Liaison des entités :
  <ul>
  <li>Concession qui contient une liste de voitures</li>
  <li>Voiture qui contient une liste d'équipement auto</li>
  
 
------------
  
  
### Les routes utilisées 
  
  Pour voir la liste des voitures : "http://localhost:8080/voiture"

  Pour voir une seule voiture : "http://localhost:8080/voiture/idvoiture"
  
  Pour voir la liste des concessions : "http://localhost:8080/concession"
  
  Pour voir une seule concession : "http://localhost:8080/concession/idconcession"

  Pour voir la liste des equipements : "http://localhost:8080/equipement"
  
  Pour voir un seul equipement : "http://localhost:8080/equipement/idequipement"
  
  
  Il est possible de savoir le nombre de modele d'une marque précise : "http://localhost:8080/voiture/countModelByMarque/nomdelamarque" 
  (Exemple : http://localhost:8080/voiture/countModelByMarque/renault")
  
  Pour connaitre le nombre de modele de toutes les marques de voitures : "http://localhost:8080/voiture/nbModelPerMarque"
