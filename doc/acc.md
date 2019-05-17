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
