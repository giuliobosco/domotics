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

## Arduino controller (Python)

## Database

## Back-end

### LDAP

JSON based

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

## Controllo luci

## Controllo tende

## Controllo beamer
