Mattia Ruberto

# Guida Lightweight Directory Access Protocol (LDAP)

Per connetterci al database della scuola abbiamo deciso di utilizzare LDAP Connector perché era la soluzione più facile e sicura da implementare dato che passi le credenziali per entrare nel database alla scuola, gli dici cosa vuoi sapere e fa tutto lui così nessuno accede direttamente sul database per evitare problemi.


![LDAP](../img/ldap/uml.png)


La classe LdapConnector viene utilizzata nel login, infatti quando l'utente si loggerà le credenziali che mette vengono prese e e vengono confrontate con le credenziali del database della scuola dove vede se l'utente è un docente e quindi ha i permessi per accederci o è un allievo e quindi non ha i permessi. LDAP permette di mantenere anche una certa sicurezza essendo che la comunicazione &egrave; criptata.
Nella classe ci saranno i seguenti attributi statici: la porta di default del server, la chiave di autenticazione, e una variabile che rappresenta il contesto iniziale del LDAP. Poi nelle variabili domain viene salvato il dominio del server LDAP, nella variabile port la porta del server se è diversa da quella di dafault, la variabile base rappresenta il livello del server nelle unità organizzative dove deve andare a controllare le credenziali e security rappresenta il tipo di sicurezza che viene utilizzata per connettersi. Nella classe vengono implementati poi tutti i vari get e set per settare o ritornare i valori delle variabili, ci sono tre costruttori, uno a cui viene passato il dominio, la porta, l'unità organizzativa e il tipo di sicurezza, uno a cui non viene passato il tipo di sicurezza e nel terzo viene passato solo il dominio e l'unità organizzativa. Il metodo getEnvironment ritorna l'ambiente hashtable della connessione, getConnectionString ritorna la stringa di connessione, getDN ritorna una stringa con le credenziali e l'unita organizzativa da inviare nel metodo getEnvironment per creare la connessione, e getDirContext ritorna se l'utente ha i permessi o no.

// ------------------------------------------------------------------------------------ Costants

    /**
     * Default LDAP server port.
     * Value: 389.
     */
    public static final int DEFAULT_PORT = 389;

    /**
     * Default security authentication.
     * Value: "simple".
     */
    public static final String DEFAULT_SECURITY_AUTHENTICATION = "simple";

    /**
     * Default initial context factory connection.
     * Value: "com.sun.jndi.ldap.LdapCtxFactory".
     */
    public static final String DEFAULT_INITIAL_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * LDAP server address.
     */
    private String domain;

    /**
     * LDAP server port.
     */
    private int port;

    /**
     * LDAP base ou.
     */
    private String base;

    /**
     * LDAP connection security type.
     */
    private String security;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Set the LDAP server address.
     *
     * @param domain LDAP server address.
     */
    private void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Get the LDAP server address.
     *
     * @return LDAP server address.
     */
    public String getDomain() {
        return this.domain;
    }

    /**
     * Set the LDAP server port.
     *
     * @param port LDAP server port.
     * @throws IOException Port not valid.
     */
    private void setPort(int port) throws IOException {
        // check if the port is in the right range for network logical port
        if (port > 0 && port < 65535) {
            this.port = port;
        } else {
            throw new IOException("The port:" + port + "is not valid. Must be in between 1-65535");
        }
    }

    /**
     * Get the LDAP server port.
     *
     * @return LDAP server port.
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Set the LDAP base ou.
     *
     * @param base LDAP base ou.
     */
    private void setBase(String base) {
        this.base = base;
    }

    /**
     * Get the LDAP base ou.
     *
     * @return LDAP base ou.
     */
    public String getBase() {
        return this.base;
    }

    /**
     * Set the LDAP connection security type.
     *
     * @param security LDAP connection security type.
     */
    private void setSecurity(String security) {
        this.security = security;
    }

    /**
     * Get the LDAP connection security type.
     *
     * @return LDAP connection security type.
     */
    public String getSecurity() {
        return this.security;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the LDAP connector.
     *
     * @param domain   LDAP server address.
     * @param port     LDAP server port.
     * @param base     LDAP base ou.
     * @param security LDAP connection security type.
     * @throws IOException Port not valid.
     */
    public LdapConnector(String domain, int port, String base, String security) throws IOException {
        this.setDomain(domain);
        this.setBase(base);
        this.setPort(port);
        this.setSecurity(security);
    }

    /**
     * Create the LDAP connector.
     *
     * @param domain LDAP server address.
     * @param port   LDAP server port.
     * @param base   LDAP base ou.
     * @throws IOException Port not valid.
     */
    public LdapConnector(String domain, int port, String base) throws IOException {
        this(domain, port, base, DEFAULT_SECURITY_AUTHENTICATION);
    }

    /**
     * Create the LDAP connector.
     *
     * @param domain   LDAP server address.
     * @param base     LDAP base ou.
     * @param security LDAP Security type.
     */
    public LdapConnector(String domain, String base, String security) {
        try {
            this.setDomain(domain);
            this.setBase(base);
            this.setPort(DEFAULT_PORT);
            this.setSecurity(security);
        } catch (IOException ignored) {
            // ignored because the default port is in the range (and it's a constant).
        }
    }


    /**
     * Create the LDAP connector.
     *
     * @param domain LDAP server address.
     * @param base   LDAP base ou.
     */
    public LdapConnector(String domain, String base) {
        this(domain, base, DEFAULT_SECURITY_AUTHENTICATION);
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Get the connection string to the LDAP server.
     * Like "ldap://host.local:389".
     *
     * @return Connection string to the LDAP server.
     */
    private String getConnectionString() {
        return "ldap://" + getDomain() + ":" + getPort();
    }

    /**
     * Get the DN for ldap.
     * Like "CN=username, OU=ldapExample,DC=host,DC=local".
     *
     * @param username Username of the DN.
     * @return DN String.
     */
    private String getDn(String username) {
        return "CN=" + username + "," + getBase();
    }

    /**
     * Get the hashtable environment of the connection.
     *
     * @param username Username of the connection.
     * @param password Password of the connection.
     * @return Hashtable Environment of the connection.
     */
    private Hashtable<String, String> getEnvironment(String username, String password) {
        Hashtable<String, String> environment = new Hashtable<String, String>();

        environment.put(Context.INITIAL_CONTEXT_FACTORY, DEFAULT_INITIAL_CONTEXT_FACTORY);
        environment.put(Context.PROVIDER_URL, getConnectionString());
        environment.put(Context.SECURITY_AUTHENTICATION, getSecurity());
        environment.put(Context.SECURITY_PRINCIPAL, getDn(username));
        environment.put(Context.SECURITY_CREDENTIALS, password);

        return environment;
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Check the user and connect it, get the Dir context.
     * Handle the AuthenticationException, used for the wrong credentials, separately from the
     * NamingException, used for connection errors.
     *
     * @param username Username of the connection.
     * @param password Password of the connection.
     * @return Dir context of the connection.
     * @throws NamingException Error with the connection to the LDAP server. Wrong credentials or
     *                         connection error.
     */
    public DirContext getDirContext(String username, String password) throws NamingException {
        return new InitialDirContext(getEnvironment(username, password));
    }