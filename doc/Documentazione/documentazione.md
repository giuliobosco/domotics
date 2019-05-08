## 1 Introduzione

### 1.1 Informazioni sul progetto

Questo progetto è stato assegnato dal docente Francesco Mussi il 13.02.2019. Gli alievi del terzo anno della scuola arti e mestieri Trevano Mattia Ruberto, Giulio Bosco e Paolo Gübeli sono i membri del gruppo che si occupano del progetto. La scadenza del progetto è fissata per il 17.05.2019.

### 1.2 Abstract

This project started from the fact that the lighting switches of the school of arts and crafts of Trevano are placed in the classroom in clumsy and uncomfortable places so we decided to implement an application that would allow you to manage lights, curtains and beamer through a website that allows you to adjust the various components of the classroom. In short, it is a domotization of the classrooms that in this way can be controlled remotely.

### 1.3 Scopo

Lo scopo del progetto è quello di domotizzare le aule della scuola arti e mestieri Trevano. Per domotizzazione delle aule si intende la possibilità di poter gestire le luci, il beamer e le tende delle aule tramite un sito web in cui vengono rappresentate le aule con tutti i valori modificabili. A questo sito web ci sarà un sistema di login da cui potranno accedere solo i docenti.

### 1.4 Analisi del dominio

Questa applicazione permette di controllare a distanza luci, tende e beamer delle aule delle scuole. Infatti il suo contesto principale di utilizzo sono proprio le scuole dato che è un sistema molto semplice e a basso prezzo che fa proprio a caso delle scuole. Infatti l'ide è di implementare e testare quest'applicazione inizialmente in una singola aula dopdichè se funziona bene l'idea è di implementarlo in tutte le aule della scuola. Prodotti simili esistono già ma non specifici per le aule di Trevano, inoltre il nostro sistema va ad interagire direttamente con il server della scuola per controlla chi di quelli che fa il login è un docente e quindi chi di essi potrà accedere al pannello di controllo o no. Senza il nostro prodotto gli utenti delle varie aule, docenti e allievi tribulano di continuo con i pulsanti delle luci che funzionano a cas.  

### 1.5 Analisi e specifica dei requisiti

|		   |ID: REQ-01    |	    		                       |
|:---------|:-------------|:-----------------------------------|
|Nome:     |Domotizzazione dell'aula                          ||
|Priorità: |1                                                 ||
|Versione: |1.0                                               ||
|Note:     |                                                  ||
|          |Sotto requisiti                                    |
|001:      |Gestione luci, tende e beamer tramite un sito web ||

|		   |ID: REQ-02    |	    		             |
|:---------|:-------------|:-------------------------|
|Nome:     |Frontend                                ||
|Priorità: |1                                       ||
|Versione: |1.0                                     ||
|Note:     |                                        ||
|          |Sotto requisiti                          |
|001:      |Sito Web                                 |    
|002:      |Modulo gestione dell'aula               ||
|003:      |Modulo gestione delle aule              ||
|004:      |Modulo luci/tende/beamer                ||
|005:      |Modulo login                            ||

|		   |ID: REQ-03    |	    		                      |
|:---------|:-------------|:----------------------------------|
|Nome:     |Backend                                          ||
|Priorità: |1                                                ||
|Versione: |1.0                                              ||
|Note:     |                                                 ||
|          |Sotto requisiti                                   |
|001:      |Server in python                                 ||
|002:      |Server in TomCat                                 ||
|003:      |LDap connector per il login                      ||
|004:      |JDBC con Java e MySQL                            ||
|005:      |Arduino Connection Controller Client             ||
|006:      |Arduino Connection Controller Server             ||

|		   |ID: REQ-04    |	    		                             |
|:---------|:-------------|:-----------------------------------------|
|Nome:     |Guide di utilizzo                                       ||
|Priorità: |2                                                       ||
|Versione: |1.0                                                     ||
|Note:     |                                                        ||
|          |Sotto requisiti                                          |
|001:      |Arduino yun                                             ||
|002:      |Server in Tomcat                                        ||

|		   |ID: REQ-05    |	    		               |
|:---------|:-------------|:---------------------------|
|Nome:     |Simulazione luci                          ||
|Priorità: |1                                         ||
|Versione: |1.0                                       ||
|Note:     |                                          ||
|          |Sotto requisiti                            |
|001:      |Controllo dei led tramite l'arduino       ||

|		   |ID: REQ-06    |	                                      |
|:---------|:-------------|:--------------------------------------|
|Nome:     |Simulazione tende                                    ||
|Priorità: |1                                                    ||
|Versione: |1.0                                                  ||
|Note:     |                                                     ||
|          |Sotto requisiti                                       |
|001:      |Controllo dei motori tramite l'arduino               ||

## Arrivati 1 mese dalla fine del progetto ci siamo accorti che non c'era abbastanza tempo per l'implementazione fisica dei moduli così abbiamo deciso di metterli in priorità 3 e di cercare di concentrarci sul resto.

|		   |ID: REQ-07    |	    		             |
|:---------|:-------------|:-------------------------|
|Nome:     |Modulo luci                             ||
|Priorità: |3                                       ||
|Versione: |1.0                                     ||
|Note:     |                                        ||
|          |Sotto requisiti                          |
|001:      |Implementazione modulo luci delle tende ||

|		   |ID: REQ-08    |      		               |
|:---------|:-------------|:---------------------------|
|Nome:     |Modulo tende                              ||
|Priorità: |3                                         ||
|Versione: |1.0                                       ||
|Note:     |                                          ||
|          |Sotto requisiti                            |
|001:      |Implementazione modulo fisico delle tende ||

|		   |ID: REQ-09    |	    		             |
|:---------|:-------------|:-------------------------|
|Nome:     |Modulo beamer                           ||
|Priorità: |3                                       ||
|Versione: |1.0                                     ||
|Note:     |                                        ||
|          |Sotto requisiti                          |
|001:      |Implementazione modulo fisico del beamer||

###  1.6 Pianificazione

![Gantt](img/Gantt.png)

Il progetto ci è stato commissionato il 13 Febbraio 2019 e programmiamo di completarlo il 17 Maggio 2019.
Questo è il gantt che rappresenta la nostra pianificazione iniziale. È strutturato in modo tale che la documentazione viene portata a pari passo con tutto il progetto, poi dal 15 Febbraio al 29 Marzo arriva la progettazione dove viene fatta la stesura del gantt, viene fatta l'analisi del dominio, dei requisiti, e si inizia a fare la progettazione di come sarà l'intera rete, come sarà il design del frontend, il design del backend e come sarà lo schema dell'arduino. Dopo la progettazione incomincia la parte di implementazione che va dal 13 Febbraio al 8 Maggio infatti essendo un gruppo da tre quando qualcuno finirà la progettazione qualcuno potrà già iniziare con l'implementazione dei vari moduli. Nel gantt viene mostrato come inizieremo ad implementare prima il frontend, ci occuperemo dell'arduino ed infine ci concentreremo sull'implementazione del backend. Dopo l'implementazione ci sarà l'integrazione di tutti i moduli dal frontend che dovrà comunicare con il backend e con l'arduino. Dopodiché verranno effettuati i test ed infine viene fatta la presentazione.

### 1.7 Analisi dei mezzi

#### 1.7.1 Software
Software utilizzati:<br>
WebStorm<br>
Clion<br>
IntelliJ

#### 1.7.2 Hardware
Hardware utilizzati:<br>
Asus Republic Of Gamers<br>
Mac Book Pro<br>
HP obera<br>
Arduino Yun<br>

## 2 Progettazione

### 2.1 Architettura progetto

![fullStructure](img/full_system_architetture.png)

Questo è lo schema che rappresenta il nostro progetto.
Il nostro progetto è strutturato nel seguente modo:
Nella parte fisica ci saranno i vari componenti dell'aula, come le luci, il beamer, le tende e i vari sensori che verranno utilizzati dal sistema Automatic mode.
Automatic mode è un sistema a parte indipendente che si occupa di gestire tende, luci e beamer in modo autonomo tramite i sensori.
L'arduino viene controllato tramite l'Arduino Connection Controller Server e Client che gli permettono di comunicare con il sito web, da cui si può accedere e tramite il login, una volta che si ci logga il sito porta l'utente alla dashboard iniziale dove è possibile vedere tutte le aule presenti e selezionare quella che si vuole gestire.
Il sito web è caricato sul web server in Tomcat mentre domotics server si occupa di auto configurare gli arduino presenti sulla rete, di trovarli e di memorizzare porta, indirizzo e chiave che poi salverà sul nostro data base dove salviamo le seguenti informazioni.
LDAP connector invece gestisce il login, infatti ogni volta che verrà effettuato lui andrà a confrontare le credenziali con quelle del server della scuola per controllare che l'utente che sta cercando di accedere al pannello di controllo sia un account di un docente.

### 2.2 Design dell'interfaccia di login

![Login](img/sito/Login.JPG)

Questo è l'architettura iniziale del login del sito web, l'interfaccia è molto semplice infatti ci sar&agrave; un semplice form di login che richieder&agrave; nome untente e password.

### 2.3 Design della dashboard del sito web

![Login](img/sito/Portal.JPG)

Questo &egrave; il design del sito web dopo che si ha fatto il login e che le credenziali sono state convalidate e confermate. Il sito porta in questa pagina dove si posssono visualizzare le varie aule con la possibilità di vedere la temperatura e accendere/spengenere le luci.


### 2.4 Architettura Data Base

![DataBase](img/db/DbDiagram.png)

Questo è il design del database, il database è formato da sette tabelle. La tabella room rappresenta le aule di cui viene salvato il loro nome, per ogni aula viene associato un arduino di cui viene memorizzato l'id, l'indirizzo ip, la password e la chiave per il client. Per ogni arduino gestisce i bottoni delle luci di cui vengono memorizzati i suoi pin e le luci. Gestisce anche le luci, il beamer, le tende e i sensori di cui vengono memorizzati i loro pin.

### 2.5 Architettura LDAP connector

![LDAP](img/ldap/uml.png)

La classe LdapConnector viene utilizzata nel login, infatti quando l'utente si loggerà le credenziali che mette vengono prese e questa classe va a confrontarle con le credenziali del database della scuola dove vede se l'utente è un docente e quindi ha i permessi per accederci o è un allievo e quindi non ha i permessi. LDAP permette di mantenere anche una certa sicurezza essendo che va a leggere i dati i modo criptato dato che fa tutto lui.
Nella classe ci saranno i seguenti attributi statici: la porta di default del server, la chiave di autentificazione, e una variabile che rappresenta il contesto iniziale del LDAP. Poi nelle variabili domain viene salvato il dominio del server LDAP, nella variabile port la porta del server se è diversa da quella di dafault, la variabile base rappresenta il livello del server nelle unità organizzative dove andare a controllare le credenziali e security rappresenta il tipo di sicurezza che viene utilizzata per connettersi. Nella classe vengono implementati poi tutti i vari get e set per settare o ritornare i valori delle variabili, ci saranno tre costruttori, uno in cui gli viene passato il dominio, la porta, l'unità organizzativa e il tipo di sicurezza, uno in cui non gli viene passato il tipo di sicurezza e nel terzo gli viene passato solo il dominio e l'unità organizzativa. Il metodo getEnvironment ritorna l'ambiente hashtable della connessione, getConnectionString ritorna la stringa di connessione, getDN ritorna una stringa con le credenziali e l'unita organizzativa da inviare nel metodo getEnvironment per creare la connessione e getDirContext che ritorna se l'utente ha i permessi o no.

### 2.6 Architettura Arduino Yun

![SchemaArduino](img/arduino/SchemaArduino.PNG)

Questo è il design dello schema dell'arduino, come si può notare l'arduino YUN e questo particolare schema rappresenta una simulazione di ciò che poi si dovrà implementare fisicamente, infatti i motori delle tende sono stati sostituiti con dei servi che rappresenteranno poi i motori che verranno utilizzati per muovere le tende. Le luci invece vengono simulate con dei led, sempre collegate a dei relays dato che fisicamente per attaccarsi alle luci bisogna collegarsi ai ralays che si trovanno nelle aule collegati alle luci. Gli interruttori delle luci delle aule vengono anch'essi simulate con dei bottoni in pull-down. Nello schema c'è anche il sensore di temperatura.

### 2.7 Arduino Connection Controller Client

![Login](img/ACC_Client/ACC_Client_UML.png)

Queste classi permettono all'arduino di connettersi al server dall'arduino e di ricavare le informazioni necessarie per il server per conescere lo stato dell'arduino.

### 2.8 Arduino Connection Controller Server

### 2.1.3 Architettura Domotics Server

### 2.1.4 Architettura Web App (Tomcat)

## 3 Implementazione

### 3.1 Login del sito web

### 3.2 Dashboard del sito web

### 3.3 Data Base

Per realizzare il data base abbiamo utilizzato MySQL.
Prima di tutto bisogna creare il database tramite il seguente comando.
```SQL
/* create the database */
CREATE DATABASE domotics;
```
In questo pezzo di codice creiamo la tabella che rappresenta l'aula che avrà un singolo attributo che rappresenta il nome dell'aula.
```SQL
/* create the room table */
CREATE TABLE domotics.room (
    name VARCHAR(255) PRIMARY KEY
);
```
Dopo aver creato l'aula creiamo la tabella che rappresenta l'arduino e dovra avere al suo interno l'id del client, l'indirizzo ip, la password del root, la chiave del client e l'aula i cui è posizionato.
```SQL
/* create the arduino table */
CREATE TABLE domotics.arduino (
	client_id VARCHAR (255) PRIMARY KEY,
	ip VARCHAR (255),
    root_password VARCHAR (255),
	client_key VARCHAR (255),
	room VARCHAR (255),

	FOREIGN KEY (room) REFERENCES domotics.room(name)
);
```
Adesso bisogna creare le tabelle che rappresentano i moduli che l'arduino andrà a controllare.
I moduli dovranno avere come attributi il numero del pin, arduino da cui vengono controllati e il loro stato.
```SQL
/* create the light table */
CREATE TABLE domotics.light (
    pin VARCHAR (255),
	arduino VARCHAR (255),
	status INT (1),
	PRIMARY KEY (pin, arduino),
	FOREIGN KEY (arduino) REFERENCES domotics.arduino(client_id)
);
```

### 3.4 Lightweight Directory Access Protocol (LDAP)
Per controllare se l'utente che sta provando a fare il login sia veramente un docente e quindi con i permessi per accedere si usa LDAP, che va sul data base della scuola in questo caso e controlla se le credenziali corrispondono a un docente o no.

Per fare questo serve server una stringa di connessione che dice a LDAP dove andare a connettersi. Che deve contenere il domimio e la porta del server LDAP.
```java
    private String getConnectionString() {
        return "ldap://" + getDomain() + ":" + getPort();
    }
```
Questo metodo vine utilizzato per creare e poi ritorna DN, è simile a un percorso assoluto solo che invece scendere l'albero da sinistra a destra scendono.
Ecco un esempio di DN:
CN=john.doe,OU=People,DC=example,DC=com
CN è il nome utente, OU è l'unita organizzativa a cui deve puntare(che possono essere più di una) mentre il primo DC rappresenta le componenti del dominio.
```java
    private String getDn(String username) {
        return "CN=" + username + "," + getBase();
    }
```
Il metodo sottostante si occupa di creare una Hashtable che contiene tutti i parametri che poi verranno inviati.
I parametri che andranno inviati sono:
DEFAULT_INITIAL_CONTEXT_FACTORY, Connessione iniziale predefinita del contesto iniziale di fabbrica.
getConnectionString(), stringa di connessione.
getDn(username), percorso a cui deve puntare.
password, password con cui si è tentato di accedere e che deve essere controllata.
```java
    private Hashtable<String, String> getEnvironment(String username, String password) {
        Hashtable<String, String> environment = new Hashtable<String, String>();

        environment.put(Context.INITIAL_CONTEXT_FACTORY, DEFAULT_INITIAL_CONTEXT_FACTORY);
        environment.put(Context.PROVIDER_URL, getConnectionString());
        environment.put(Context.SECURITY_AUTHENTICATION, getSecurity());
        environment.put(Context.SECURITY_PRINCIPAL, getDn(username));
        environment.put(Context.SECURITY_CREDENTIALS, password);

        return environment;
    }
```
Quest'ultimo metodo invece utilizza l'Hashtable per collegarsi e controllare se l'utente è presente all'interno dell'unità organizzativa a cui gli è detto di andare a contrallare.
Se l'autentificazione è valida continua altrimenti richiama un eccezzione.
```java
public DirContext getDirContext(String username, String password) throws NamingException {
        return new InitialDirContext(getEnvironment(username, password));
    }
```
### 3.5 Implementazione Java DataBase Connectivity (JDBC)
Java DataBase Connectivity viene utilizzato per connettersi tramite java al database e ricavarne le informazione che si vogliono sapere.
Le parti fondamentali per capire il funzionamento di JDBC sono queste:
Il seguente metodo che crea la stringa di connessione che permette di connettersi al database, la stringa è composta da una parte iniziale che rappresenta dove deve andare a puntare, in questo caso mysql, poi bisogna passargli l'host, la porta e il nome del database, in più per evitare problemi con il driver è consigliabile anche aggiungere una stringa che rappresenta l'orario della propria zona geografica.

Class.forName("com.mysql.cj.jdbc.Driver"), specifica a JDBC quali driver utilizzare.

Poi viene creata la Connection con il metodo getConnection che prende la connectionString, l'username e la password ed infine ritorna il tutto.
```java
protected Connection getDbConnection(String username, String password, String host, int port, String database) throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://" + host + ":" + port + "/" + database + "?";
        connectionString += TIMEZONE_UTC;

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionString, username, password);
    }
```
```java
public void createStatement() throws SQLException {
        if (this.statement != null && !this.statement.isClosed()) {
            this.closeStatement();
        }

        this.statement = this.connection.createStatement();
    }
```
```java
public void closeStatement() throws SQLException {
        if (!this.statement.isClosed()) {
            this.statement.close();
        }
    }
```
### Arduino Connection Controller Client
### Implementazione Arduino Connection Controller Server
