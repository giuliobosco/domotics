# Domotics - TODO

## Come funziona

front-end -> back-end -> Arduino -> EVENTO

## ACC (Arduino Connection Controller)

&Egrave; il sistema che verr&agrave; utilizzato per comunicare fra gli Arduino e il Server Domotics
quindi questo modulo del progetto avra due parti, una sviluppata in Java (ACC-Server) mentre
un'altra parte sar&agrave; sviluppata in Arduino (ACC-Client).

Questo sistema permetter&agrave; di collegare l'Arduino Ethernet (o WiFi) ad una rete, l'ACC
dovr&agrave; preoccuparsi di collegarsi alla rete con il DHCP, questo per permettere il funzionamento
corretto del sistema in ogni caso.  
Dopo aver collegato l'Arduino alla rete ACC dovr&agrave; ricercare un server Domotics
(ACC-Client-Discover), il quale server&agrave; per registrare l'Arduino sul server e poterlo
configurare dal server.

Dopo che l'arduino si &egrave; registrato sul server se questo &egrave; gi&agrave; stato connesso al
server, il server configura l'arduino, il quale poi inizia a lavorare. Altrimenti il server
configura l'arduino, il quale poi inizia a lavorare.

#### Protocollo

Il Protocollo ACC per domotics &egrave; molto semplice, &egrave; basato su un codice univoco
(ACC-Client-ID) perogni Arduino, ed una chiave di comunicazione (ACC-Client-KEY) di comunicazione
scelta dal server. Il codice univoco sar&agrave; salvato in una costante sull'Arduino, e
servir&agrave; per identificare l'Arduino quando si connette alla rete e ricerca il server. Invece
la KEY viene inviata dal server all'Arduino, la quale verr&agrave; utilizzata dal server e
dall'Arduino per token per una comunicazione sicura. Verr&agrave; inviata assieme a tutti i messaggi
che i due si scambieranno.

**ACC-Client-ID:**

```
DMTSID0ABCDEFABCDEF
```

Il codice inizia con `DMTS` che sta per Domotics, poi `ID0`, che sta per ID ed un limitatore,
seguito da 12 caratteri esadecimali un trattino ed infine l'IP dell'arduino.

```
ABCDEFABCDEF
```

**ACC-Client-KEY:**

Ogni KEY sar&agrave; composta di un codice esadecimale di 12 caratteri, casuali, quali vengono
generati sul server semplicemente perch&egrave; ha pi&ugrve; potenza di calcolo.

**Authentication Discover:**  

L'ACC-Client invier&agrave; un messaggio di broad-cast sulla rete simile al seguente sulla porta
`6137`, contenente il ACC-Client-ID e `-REQUEST` alla fine della stringa dell'id. Poi l'Arduino
apre il suo WebServer sulla porta `8080`.

```
DMTSID0ABCDEFABCDEF-192.168.1.34-REQUEST
```

**Authentication Response:**

L'ACC-Server risponde al messagio discover con una richiesta HTTP sulla porta `8080` e inviando il
messaggio che ha ricevuto come richiesta, ci aggiunge un trattino e la ACC-Client-KEY, che deve
essere generata se l'Arduino non &egrave; mai stato collegato al server Domotics, altrimenti deve
essere presa quella gia presente sul database (e deve essere aggiornato l'IP nel caso sia diverso).
Ed infine deve aggiungere il suo indirizzo IP e la stringa `-ACCEPTED`.

```
DMTSID0ABCDEFABCDEF-192.168.1.34-REQUEST-ABCDEFABCDEF-192.168.1.2-ACCEPTED
```

L'indirizzo IP e la KEY dovranno essere salvati in delle variabili sull Arduino.

**Comunicazione ACC-Client -> ACC-Server:**

La comunicazione fra i due elementi ACC deve avvenire tramite HTTP, utilizzando il metodo GET, la
risposta dovr&agrave; essere un file JSON.

Per esempio quando viene cliccato un bottone l'ACC-Client, invia una richiesta al server simile alla
seguente:

```
http://192.168.1.2:8080/acc?key=ABCDEFABCDEF&type=send&pin=13&value=1
```

Tutte le richieste referenti all'ACC dovranno essere fatte verso l'IP del server domotics sulla
porta `8080`, alla pagina `acc`, e inviando la KEY di comunicazione dell'Arduino (AC-Client-KEY) con
il parametro `key` a valore della KEY.

Per le richieste di invio di dati bisogna inserire il parametro `type` a `send`, questo per
segnalare al che &egrave; una richiesta HTTP di invio dati, poi settare il parametro `pin` al numero
del pin (nel caso sia un pin analogico inserire un `a` prima del numero) ed infine settare il
parametro `value` al valore del pin.

Il server ritorner&agrave; una risposta simile alla seguente.

```JSON
{
    "response": "OK",
    "message": ""
}
```

Il parametro `response` della risposta del server, segnala se la richiesta &egrave; stata ricevuta
correttamente, altrimenti ritorna `FAILED` con un messaggio d'errore.

**Comunicazione ACC-Server -> ACC-Client:**

La comunicazione fra i due elementi avviene anche qui tramite http, le richieste dovranno essere
effettuate con il metodo GET e le risposte saranno dei file JSON.

Le richieste potranno essere di due tipi:

- `send`, che serve per inviare dei dati all'arduino, per esempio il valore che deve assumere un pin
- `get`, che serve per richiedere dei dati all'Arduino, per esempio richiedere il valore di un pin

Esempio di richiesta _send_:

```
http://192.168.1.34:18086/acc?key=ABCDEFABCDEF&type=send&pin=4&value=1
```

risposta:

```JSON
{
    "pin":4,
    "value":1
}
```

Esempio di richiesta _get_:

```
http://192.168.1.34:18086/acc?key=ABCDEFABCDEF&type=get&pin=a0
```

risposta:

```JSON
{
    "pin":"a0",
    "value":"123"
}
```

**ACC-AutoConfiguation:**

Siccome si vuole avere un sistema di comunicazione autonomo. Il quale deve essere in grado di
richiedere al server la sua configuazione, la quale deve essere richiesta dal arduino al server
tramite la ACC-Authentication-Request.

Se il server gi&agrave; conosce l'Arduino (ACC-Client-ID), gli invia la chiave, e nel caso sia
cambiato l'IP del server lo aggiona sul database (questo per permettere un buon funzionamento con
DHCP). Altrimenti viene aggiunto al DB l'arduino con il suo ID e viene generata con il
`ACC-Client-KEY-Generator` una KEY.

**ACC-Client-KEY-Generator:**

Deve generare casualmente una chiava di 12 caratteri esadecimali (`01234567890ABCDEF`), controllare
che non ve ne siano gia di uguali nel database (nel caso ri generarne un'altra).

## Database

## Back-end

### LDAP

Richiede ad LDAP, se l'utente &egrave; corretto, dopo di che esegue il login e salva le credenziali
nei cookies. Quali permettono poi di aprire le pagine successive.

login.html -> LoginServlet

### Moduli

- modulo aula
- modulo luce
- modulo tende
- modulo beamer
- modulo planimetria (in pi&ugrave;)
- modulo meteo (in pi&ugrave;)

## Front-end

### Moduli

- modulo aula
- modulo luce
- modulo tende
- modulo beamer
- modulo planimetria (in pi&ugrave;)
- modulo meteo (in pi&ugrave;)

## Controllo luci

### Auto

Le luci regolano l'intensità tramite i dati ricevuti dai sensori di luce posti nell'aula.
Se la luce esterna è molto intensa si spengono.
Dopo un certo orario le luci si spengono in automatico.

## Controllo tende

### Auto

Quando la luce esterna è molto intensa chiude le tende e accende le luci ad un'intensità di default.
Dopo un certo orario le tende si aprono in automatico.

## Controllo beamer

### Auto

Quando si accende il beamer le tende si chiudono e e luci si spengono.
Il beamer si spegne dopo un certo orario.
