# Projet Client-Serveur - Antoine PAYS & Fabien GENIN - LPWEB

Par binôme (du même groupe de TP), vous allez réaliser un petit projet incluant au moins deux resources que vous allez manipuler dans leur cycle de vie (création, mise à jour totale et/ou partielle, suppression, énumération, ...).

Pour ce faire vous allez travailler en Java et utiliser quelques librairies de SpringBoot (la partie mongo pour l'interaction base de données et la partie rest pour l'exposition HTTP).

------------

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
  </ul>
  
 
------------
  
  
## **Les routes utilisées** 
  
Pour nos voitures voici les endpoints disponibles :
| Method | Path                                 | Description                                     |
| ------ | ------------------------------------ | ----------------------------------------------- |
| GET    | /voiture                             | Récupère l'ensemble des voitures                |
| GET    | /voiture/{id}                        | Récupère une voiture spécifique                 |
| GET    | /voiture/countModelByMarque/{marque} | Récupère le nombre de modèle pour une voiture   |
| GET    | /voiture/nbModelPerMarque            | Récupère le nombre de modèle pour chaque marque |
| POST   | /voiture/add_voiture                 | Créé une nouvelle voiture                       |
| PUT    | /voiture/{id}                        | Met à jour une voiture                          |
| DELETE | /voiture/{id}                        | Supprime une voiture existante                  |

  
------------
  
Pour nos concessions voici les endpoints disponibles :

| Method | Path                                 | Description                         |
| ------ | ------------------------------------ | ----------------------------------- |
| GET    | /concession                          | Récupère l'ensemble des concessions |
| GET    | /concession/{id}                     | Récupère une concession spécifique  |
| POST   | /concession/add_concession           | Créé une nouvelle concession        |
| PUT    | /concession/{id}                     | Met à jour une concession           |
| DELETE | /concession/{id}                     | Supprime une concession existante   |

------------

Pour nos équipement auto voici les endpoints disponibles :

| Method | Path                                 | Description                         |
| ------ | ------------------------------------ | ----------------------------------- |
| GET    | /equipement                          | Récupère l'ensemble des équipements |
| GET    | /equipement/{id}                     | Récupère un équipement spécifique   |
| POST   | /equipement/add_equipement           | Créé un nouvel équipement           |
| PUT    | /equipement/{id}                     | Met à jour un équipement            |
| DELETE | /equipement/{id}                     | Supprime un équipement existant     |
  