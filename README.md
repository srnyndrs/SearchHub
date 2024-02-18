# <img src="images/logo.png" style="float: left; padding-right:10px;" height="32px" width="32px"> SearchHub

### The main goal of this application is to search github repositories through the GitHub Search API.

## Technologies
- JetPack Compose
- Retrofit
- Dagger Hilt
- Coil

## Home Screen
On the main page, there is a search bar accompanied by a button to initiate the search. If the request yields any results, a list becomes visible. Clicking on the individual repositories allows you to view more details about them.

<img src="images/home.png" height="40%" width="40%">

## Search Feature
While typing in the search bar, a set of qualifiers will appear to assist you in refining your search. You can select any of them to automatically insert the corresponding query.

<img src="images/search.png" height="40%" width="40%">

## Details Screen
On the details page of a repository, you can view the title and description, as well as information about the user who created it and other relevant details. Additionally, you have the option to navigate to the GitHub page of the repository by clicking the arrow next to the title. The same can be done for the user's page by clicking the corresponding arrow.

<img src="images/details.png" height="40%" width="40%">

