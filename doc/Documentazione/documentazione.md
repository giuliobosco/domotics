## 1 Introduzione sul progetto

### 1.1 Informazioni sul progetto

Questo progetto è stato assegnato dal docente Francesco Mussi il 13.02.2019. Gli alievi del terzo anno della scuola arti e mestieri Trevano Mattia Ruberto, Giulio Bosco e Paolo Gübeli sono i membri del gruppo che si occupano del progetto. La scadenza del progetto è fissata per il 17.05.2019.

### 1.2 Abstract

This project started from the fact that the lighting switches of the school of arts and crafts of Trevano are placed in the classroom in clumsy and uncomfortable places so we decided to implement an application that would allow you to manage lights, curtains and beamer through a website that allows you to adjust the various components of the classroom. In short, it is a domotization of the classrooms that in this way can be controlled remotely.

### 1.3 Scopo

Lo scopo del progetto è quello di domotizzare le aule della scuola arti e mestieri Trevano. Per domotizzazione delle aule si intende la possibilità di poter gestire le luci, il beamer e le tende delle aule tramite un sito web in cui vengono rappresentate le aule con tutti i valori modificabili. A questo sito web ci sarà un sistema di login da cui potranno accedere solo i docenti.

### 1.4 Analisi del dominio

Questa applicazione permette di controllare a distanza luci, tende e beamer delle aule delle scuole. Infatti il suo contesto principale di utilizzo sono proprio le scuole dato che è un sistema molto semplice e a basso prezzo che fa proprio a caso delle scuole. Infatti l'ide è di implementare e testare quest'applicazione inizialmente in una singola aula dopdichè se funziona bene l'idea è di implementarlo in tutte le aule della scuola. Prodotti simili esistono già ma non specifici per le aule di Trevano, inoltre il nostro sistema va ad interagire direttamente con il server della scuola per controlla chi di quelli che fa il login è un docente e quindi chi di essi potrà accedere al pannello di controllo o no. Senza il nostro prodotto gli utenti delle varie aule, docenti e allievi tribulano di continuo con i pulsanti delle luci che funzionano a cas.  

### 1.5 Analisi e specifica dei requisiti

|		   |ID: REQ-01    |	    		             |
|:---------|:-------------|:-------------------------|
|Nome:     |Domotizzazione dell'aula                ||
|Priorità: |1                                       ||
|Versione: |1.0                                     ||
|Note:     |                                        ||
|          |Sotto requisiti                          |
|001:      |Scegliere quale arduino utilizzare <br> ||
|002:      |Gestione tende<br>                      ||
|003:      |Gestione beamer<br>                     ||
|004:      |Gestione luci<br>                       ||
|005:      |Front-end<br>                           ||
|006:      |Back-end<br>                            ||
|007:      |Guide di utilizzo<br>                   ||

|		   |ID: REQ-02    |	    		             |
|:---------|:-------------|:-------------------------|
|Nome:     |Front-end                               ||
|Priorità: |1                                       ||
|Versione: |1.0                                     ||
|Note:     |                                        ||
|          |Sotto requisiti                          |
|001:      |001: Sito Web                           ||

|		   |ID: REQ-03    |	    		             |
|:---------|:-------------|:-------------------------|
|Nome:     |Sito Web                                ||
|Priorità: |1                                       ||
|Versione: |1.0                                     ||
|Note:     |                                        ||
|          |Sotto requisiti                          |
|001:      |modulo gestione dell'aula               ||
|002:      |modulo gestione delle aule              ||
|003:      |modulo luci/tende/beamer                ||
|004:      |modulo login                            ||

|		   |ID: REQ-04    |	    		                      |
|:---------|:-------------|:----------------------------------|
|Nome:     |Back-end                                         ||
|Priorità: |1                                                ||
|Versione: |1.0                                              ||
|Note:     |                                                 ||
|          |Sotto requisiti                                   |
|001:      |Server in python                                 ||
|002:      |GServer in TomCat                                ||
|003:      |Client in arduino                                ||
|004:      |LDap connector per il login                      ||
|005:      |JDBC per prendere i valori dal data base con java||
|006:      |Back-end<br>                                     ||
|007:      |Guide di utilizzo<br>                            ||

|		   |ID: REQ-05    |	    		                             |
|:---------|:-------------|:-----------------------------------------|
|Nome:     |Guide di utilizzo                                       ||
|Priorità: |1                                                       ||
|Versione: |1.0                                                     ||
|Note:     |                                                        ||
|          |Sotto requisiti                                          |
|001:      |Guida di utilizzo dell'arduino yun                      ||
|002:      |Guida di utilizzo e implementazione del server in Tomcat||

|		   |ID: REQ-06    |	    		                                       |
|:---------|:-------------|:---------------------------------------------------|
|Nome:     |Simulazione controllo delle luci                                  ||
|Priorità: |1                                                                 ||
|Versione: |1.0                                                               ||
|Note:     |                                                                  ||
|          |Sotto requisiti                                                    |
|001:      |Simulazione del controllo delle luci con l'arduino tramite dei led||

|		   |ID: REQ-07    |	    		                                           |
|:---------|:-------------|:-------------------------------------------------------|
|Nome:     |Simulazione controllo tende                                           ||
|Priorità: |1                                                                     ||
|Versione: |1.0                                                                   ||
|Note:     |                                                                      ||
|          |Sotto requisiti                                                        |
|001:      |Simulazione del controllo delle luci con l'arduino tramite dei motori ||
|002:      |Decidere quali motori utilizzare                                      ||

|		   |ID: REQ-08    |	    		             |
|:---------|:-------------|:-------------------------|
|Nome:     |Implementazione modulo fisico del beamer||
|Priorità: |1                                       ||
|Versione: |1.0                                     ||
|Note:     |                                        ||
|          |Sotto requisiti                          |
|001:      |Controllare le tende tramite l'arduino  ||

|		   |ID: REQ-09    |	    		             |
|:---------|:-------------|:-------------------------|
|Nome:     |Implementazione modulo fisico delle luci||
|Priorità: |1                                       ||
|Versione: |1.0                                     ||
|Note:     |                                        ||
|          |Sotto requisiti                          |
|001:      |Controllare le luci attraverso l'arduino||

|		   |ID: REQ-010   |	    		                               |
|:---------|:-------------|:-------------------------------------------|
|Nome:     |Implementazione modulo fisico delle tende                 ||
|Priorità: |1                                                         ||
|Versione: |1.0                                                       ||
|Note:     |                                                          ||
|          |Sotto requisiti                                            |
|001:      |Controllare le tende attraverso l'arduino con dei motori  ||

###  1.6 Pianificazione

### 1.7 Analisi dei mezzi

#### 1.7.1 Software
Software utilizzati:
*WebStorm
*Clion
*IntelliJ

#### 1.7.2 Hardware
Hardware utilizzati:
*Asus Republic Of Gamers
*Mac Book Pro
*Obera
*HP OMEN

## 2.0 Progettazione

### 2.1 Design dell'architettura

#### 2.1.1 Architettura progetto

![fullStructure](img/full_system_architetture.png)

Questo è lo schema che rappresenta il nostro progetto.
Il nostro progetto è strutturato nel seguente modo:
Nella parte fisica ci saranno i vari componenti dell'aula, come le luci, il beamer, le tende e i vari sensori che verranno utilizzati dal sistema Automatic mode.
Automatic mode è un sistema a parte indipendente che si occupa di gestire tende, luci e beamer in modo autonomo tramite i sensori.
L'arduino viene controllato tramite l'Arduino Connection Controller Server e Client che gli permettono di comunicare con il sito web, da cui si può accedere e tramite il login, una volta che si ci logga il sito porta l'utente alla dashboard iniziale dove è possibile vedere tutte le aule presenti e selezionare quella che si vuole gestire.
Il sito web è caricato sul web server in Tomcat mentre domotics server si occupa di auto configurare gli arduino presenti sulla rete, di trovarli e di memorizzare porta, indirizzo e chiave che poi salverà sul nostro data base dove salviamo le seguenti informazioni.
LDAP connector invece gestisce il login, infatti ogni volta che verrà effettuato lui andrà a confrontare le credenziali con quelle del server della scuola per controllare che l'utente che sta cercando di accedere al pannello di controllo sia un account di un docente.

#### 2.1.2 Architettura Data Base



#### 2.1.3 Architettura LDAP connector

#### 2.1.3 Architettura Domotics Server

#### 2.1.4 Architettura Web App (Tomcat)
