Paolo Guebeli

# Guida di funzionamento e implementazione del frontend

## Design

Per il frontend abbiamo scelto una paletta di colori di base per tutti i siti.

![Colori](../img/sito/ColoriFE.PNG)

La pagina di login contiene un semplice form composto da un campo testo, un campo password e un pulsante di submit.

![Login](../img/sito/Login.JPG)

La pagina principale &egrave; composta da due parti, la dashboard e il corpo principale contenente le aule.
La dashboard contiene le pagine del sito (al momento solo una) e la gestione del account.
Invece il corpo della pagina permette di gestire le aule, vedere la tempereatura e spegnere/accendere le luci.

![Portal](../img/sito/Portal.JPG)

## Implementazione

### Stile

Per lo stile delle pagine usiamo bootstrap (https://getbootstrap.com) e fontawesome (https://fontawesome.com).
Abbiamo usato Fontawesome per aggiungere delle icone al sito, abbiamo usato Fontawesome al posto delle immagini perché le icone che contiene sono dei caratteri che quindi sono più leggeri e più facili da usare delle immagini.
Ecco un esempio di view implementata usando bootstrap e fontawesome.

```html
<div class="navbar">
	<i class="fa fa-bars fa-2x"></i>
</div>
<div class="sidebar">
	<div class="col-md-12">

		<h2>portal<i class="pull-right fa fa-times"></i></h2>
		<ul class="a-white">
			<li><a href="#!/rooms">rooms</a></li>
		</ul>
	</div>
	<div class="bottom col-md-12 a-white">
    <a href="#!/settings"><i class="fa fa-cog fa-2x"></i></a>
    <a href="/Logout"><i class="fa fa-sign-out pull-right fa-2x"></i></a>
  </div>
</div>
<script src="assets/js/scripts/sidebar.js"></script>
```
Fontawesome usa il tag <i> per inserire le icone. Per definire l'icona bisogna inserire nel attributo class: fa che definisce l'uso di fontawesome, fa-[nome icona] che definisce quale icona si vuole usare, e se si vuole la grandezza dell'icona

```html
<i class="fa fa-cog fa-2x">
```

### Views

Per gestire le pagine vengono usate delle views che vengono caricate da app.js nel body della pagina Index.html.


app.jscontiene una funzione usa uno switch che a seconda di quello che viene passato nella stringa http carica la pagina corretta.
```js
app.config(function ($routeProvider) {
```

Se non viene passato niente viene caricato l'index.

```js
// index
$routeProvider.when('/', {
  templateUrl: 'views/index.html'
});
```

Se viene passato un valore esistente viene caricata la pagina apposita.

```js
// login
$routeProvider.when('/login', {
  templateUrl: 'views/login.html'
});

// rooms
$routeProvider.when('/rooms', {
  templateUrl: 'views/rooms.html'
});

// settings
$routeProvider.when('/settings', {
  templateUrl: 'views/settings.html'
});
```

Se viene passato qualcosa che non esiste vien caricato l'index.

```
// else
$routeProvider.otherwise({
  redirectTo: '/'
});
}).run(function ($rootScope, $route) {
$rootScope.$route = $route;
});
```
