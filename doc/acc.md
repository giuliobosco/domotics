# ACC (Arduino Connection Controller)

&Egrave; un protocollo che abbiamo ideato per comunicare con facilita con l'Arduino, dopo averlo
mentre lo stavamo progettando ci siamo accorti che questo protocollo pu&ograve; essere esteso per
qualunque micro controllore che possa essere connesso ad una rete LAN, siccome &egrave; basato sul
protocollo HTTP, per poterlo utilizzare basta un server HTTP personalizzato ed un client HTTP per
eseguire le richieste al server ACC.  
Il server HTTP e il client, sono entrambi sia sul server ACC che sul client ACC (Microcontrollore).  
Per semplicit&agrave; il server verr&agrave; chiamato `ACC-Server` mentre il lato client verr&agrave;
denominato `ACC-Client`.

Questo protocollo, come un modulo a se stante, dal progetto `domotics`, quindi potrà venir
utilizzato anche da altri progetti.  
L'idea del funzionamento di questo modulo, &egrave; poter inviare dei valori da settare sui pin,
oppure richiedere lo stato dei pin. Questo protocollo deve funzionare in maniera "sicura" se vi
&egrave; presente un ACC-Server, oppure in maniera autonoma se il suo server.

La modalit&agrave; `sicura` utilizza una chiave per scambiare i valori fra l'ACC-Client e
l'ACC-server, la chiave &egrave; una stringa esadecimale di 12 caratteri. Quando la modali&agrave;
sicura &egrave; abilitata, per richiedere i valori al microcontrollore o settare dei valori sui pin,
mentre nella modalit&agrave; senza l'ACC-Server, chiunque conosce l'indirizzo IP del server ed il
funzionamento del protocollo pu&ograve; inviare comandi o richiedere valori all'ACC-Client.

## ACC-Server (Arduino Connection Controller - Server)

L'ACC-Server, &egrave; composto di un server HTTP ed un elemento per creare le richieste HTTP. Il
server HTTP, ha bisogno di una pagina, la quale deve essere in grado di interpretare due richiste:
- autoconf: Questa richiesta richiede tramite il suo ID, la quale ritorna la chiave di comunicazione.
- set: Questa richiesta deve contenere, la chiave di comunicazione, il pin ed il valore, questa
serve per aggiornare l'ACC-Server nel caso in cui un pin (per esempio bottone), cambia stato.

### ACC-Server - autoconf

La richiesta deve essere:

```
http://<serverAddress>:<serverPort>/acc?autoconf&id=<ACC-Client-ID>
```

E la risposta sar&agrave;

```
{"id":"<ACC-Client-ID>", "key":"<ACC-Client-KEY>", "server_address":"<serverAddress>:<serverPort>"}
```

Tutte le risposte saranno inviate in formato JSON, questo per facilitare il l'interpretazione da
parte del client.

<small>ACC-Client-ID e ACC-Client-KEY sono spiegati nel capitolo successivo</small>

### ACC-Server - set

Quando cambia lo stato di un pin digitale di input sull'arduino, (per esempio la pressione di un
bottone) questo deve notificarlo al server, per permettere al server di eseguire le guiste
operazioni, per esempio modificare lo stato di altri pin.

La richiesta deve essere:

```
http://<serverAddress>:<serverPort>/acc?key=<ACC-Client-KEY>&pin=<changedPin>&set=<pinStatus>
```

La risposta sar&agrave;:

```
{"status":"OK","message":"<valoreSettato>"}
```

