# back-end

L'unione di tutti i moduli del progetto avviene nel back-end, il quale deve collegare il front-end,
cio&eacute; la web-app, il database di domotics, e i micro controllor (nel nostro caso Arduino),
tramite l'ACC.

Il back-end &egrave; scritto in java, e verr&agrave; servito da tomcat, come web server.

## Design

Il back-end, &egrave; stato progettato in pi&ugrave; fasi, modulo per modulo. Il primo modulo ad
essere stato progettato &egrave; stato quello di `ldap` e dell'autenticazione, che si compone delle
classi:

- `LdapConnector`, gestisce le connessioni con il server ldap.
- `Authenticator`, &egrave; un interfaccia creata per poter utilizzare diversi tipi di autenticazione
con facilit&agrave;
- `LdapAuthenticator`, classe per autenticarsi con ldap, implementa `Authenticator`.
- `AuthenticationChecker`, una classe utilizzata per convalidare le sessioni HTTP.
- `LoginServlet`, servlet per eseguire il login della sessione HTTP.
- `LogoutServlet`, servlet per invalidare la sessione HTTP.

![back-end autenticazione &amp; ldap diagramma delle classi](../../design/back-end/authentication.png)

