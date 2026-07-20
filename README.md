# Persönliche Videospielverwaltung

## Bedienanleitung
- (Optional) Falls Sie die bereits erstellten Dummy-Daten nutzen wollen: main-Methode in DummyData ausführen, Anwendung fuktioniert aber auch ohne Dummy-Daten uns die haben die Möglichkeit komplett eigene Daten zu übergeben
1. Main-Methode in networks/Server starten
2. Main-Methode in view/JavaFXApp starten
3. Login mit **Username**: **Max Mustermann** und **Password**: **testpassword1** oder Register mit eigenen fiktiven Daten
4. Sie landen automatisch in der Game-Collection Ansicht. Über die Navbar kann man navigieren. Falls Sie auf Reviews clicken, müssen Sie im Suchfeld nach einem Spieletitel suchen (Destiny 2, Cyberpunk 2077, Vampire Survivors, etc)
5. Logout

## Projektbeschreibung
Mein Projekt ist eine Desktop-Anwendung, mit der man seine persönliche Videospielsammlung verwalten kann. Hier können Nutzer ihre Spiele eintragen, deren Status bearbeiten und einsehen (in Besitz, auf der Wunschliste, verkauft, etc.). Ebenso gibt es die Möglichkeit, Spiele zu bewerten und Sammlungen mit mehreren Inhabern anzulegen. 
  - **Hauptaufgabe**: Zentrale und kompakte Übersicht über die eigenen Spiele und deren Status.
  - **Zielgruppe**: Privatpersonen mit großer und kleiner Spielesammlung.
  - **Motivation**: Strukturierte Übersicht über die eigene Sammlung, Spiele auf der Wishlist und verkaufte/besessene Spiele mit personalisierten Statistiken, sowie keine Gefahr des doppelten Kaufens bei großen Sammlungen.

## Geplante Funktionalitäten
- **Must Have**:
  - Spiele hinzufügen, bearbeiten und entfernen
  - Statuswechsel (Auf der Wunschliste → gekauft/in Besitz → durchgespielt → besessen/verkauft)
  - Persönliche Bewertung mit kurzer Notiz/Kritik
  - Wishlist erweitern/verkleinern 
  - Reviews von anderen Nutzern zu gesuchtem Spiel lesen
- **Should Have**:
  - Suchfunktion (Titel, Platform, Entwickler, etc.)
- **Nice to Have**:
  - Familiensammlung anlegen
  - Statistiken (Wieviele Spiele, Gesamtwert, wieviele duchgespielt, etc.)

## Bereits implementierten Funktionalitäten
### Grundstruktur
- Folgende Klassen implementiert:
  - Game, Platform, Genre, Developer, ReleaseDate, Status, Review, Player, Wishlist und Price
- Getter- und Settermethoden für alle Klassen implementiert
- toString(), equals() und hashCode() implementiert
- Id-Attribute mit final versehen
- Zweiter Konstruktor in Game.java für WishlistHandler.java angelegt
- Zweiten Konstruktor in Review.java für ReviewHandler.java angelegt 

### Funktionen
- Funktionen addGame() und removeGame() für Wishlist.java implementiert
- GameComparator.java mit Sortierung nach Game, Price, Developer und ReleaseDate erstellt
- Interface SearchCollection für spätere Suchfunktion erstellt

### Datenbanken
- SQLite Verbindung nach Anleitungsschritten erstellt und erfolgreich getestet
- MyDatabase.db erstellt
- Handler-Klassen vollständig implementiert
  - Game-Handler
  - Player-Handler
  - Wishlist-Handler
  - Review-Handler
- Hilfsmethode convertToObject() in allen Handler-Klassen implementiert, um Rohdaten aus der Datenbank in ein passendes Objekt zu konvertieren
- 5 Dummy-Daten pro Tabelle in DummyData.java erstellt und erfolgreich getestet

## Änderungen seit der letzten Abgabe
### **Disclaimer**
In manchen Klassen werden Logiken und Methoden benutzt, auf die Ich ohne externe Hilfe wie StackOverflow, Reddit und weitere Foren nicht gekommen wäre, wie zum Beispiel LocalDate.MIN oder LocalDate.NOW um Nullcheck sinnvoll zu implementieren. Es wurde **KEINE** KI verwendet und keine Codeblöcke aus Foren Copy-Pasted. 
### Checkliste von Blatt 2
  - [x] README.md aktualisiert und als PDS-Datei auf Moodle hochgeladen. 
  - [x] Vollständiger Projektstand auf dem Main-Branch gepusht.
  - [x] Projektstand eigenständig auf der GitLab-Website überprüft.
  - [x] Alle Tutor*innen sowie Fatih Ozgan haben weiterhin Zugriff auf das Repository.
  - [x] JavaFX-GUI ist lauffähig.
  - [x] Die wichtigsten Projektfunktionen sind über die GUI bedienbar.
  - [x] Client, Server, Datenbank und GUI sind sauber getrennt.
  - [x] Projektstruktur folgt nachvollziehbar dem MVC-Prinzip.
  - [x] Die GUI kommuniziert nicht direkt mit der Datenbank.
  - [x] Datenbankzugriffe erfolgen weiterhin ausschließlich über den Server. 
  - [x] Commands, Statusmeldungen und DTOs aus Blatt 3 werden sinnvoll verwendet oder erweitert.
  - [x] Die GUI bleibt bei Serveranfragen bedienbar und friert nicht dauerhaft ein.
  - [x] Fehler- und Statusmeldungen werden verständlich in der GUI angezeigt.
  - [x] Kritische Bereiche sind weiterhin geeignet synchronisiert
  - [x] Race Conditions wurden bedacht und relevante Stellen in der README.md dokumentiert.
  - [x] Zeitslot für die GUI-Vorführung gebucht.
  - [x] Zentrale Klassen, Datenfluss, MVC-Struktur und eigene Implementierungsentscheidungen können im Codegespräch erklärt werden.


### Verbesserungen und Überarbeitungen
- weitere packages hinzugefügt(tests, security, dto-packages)
- in Player.java password Attribut, get- und setPassword hinzugefügt
- in PlayerHandler.java password TEXT Spalte in createTable() hinzugefügt
- in Developer.java, Genre.java, Platform.java und Game.java jeweils implements Serializable hinzugefügt
- in GameHandler.java, WishlistHandler.java, ReviewHandler.java und PlayerHandler.java schreibende Methoden mit **synchronized** versehen
  - hierbei habe ich nur "schreibende" methoden mit synchronized versehen, da nur diese aktiv auf die Datenbank zugreifen und Daten verändern können
- in DummyData werden password jetzt korrekt gehasht gespeichert
### Neue Klassen
- Commands.java als ENUM mit allen Client Anfragen
- StatusCommand.java als ENUM mit allen Server Antworten
- Server.java um Server auf Port 8080 zu starten
- ClientHandler.java Client in einzelnen Thread zu verarbeiten
- GameClient.java stellt Anfragen an Server
- DummyClients.java startet zwei GameClients testweise gleichzeitig
### Neue Funktionen
- in **GameClient.java**: login(), registration(), getGames(), addGames(), deleteGames(), getWishlist(), addToWishlist(), updateWishlist(), addReview(), start()
- in **LoginHandler**: hashPW(), registration(), login()
### Datenbankstruktur
- Folgende Tabellen wurden erstellt:
  1. games mit Primary Key gameId → Speichert alle übergebenen Spielinformationen
  2. players mit Primary Key playerId → speichert das Nutzerprofil
  3. wishlist mit Primary Key playerId und gameId → ordnet jedem Nutzer eine Wishlist zu und speichert sie
  4. reviews mit Primary Key reviewId → Speichert Nutzerreviews


## Umsetzung von Feedback zu Blatt 2
- "Zweitkonstruktoren in Game sind inkonsistent in Parameterreihenfolge" wurde gefixt
- "DatabaseConnection.connect() gibt bei Fehler null zurück" → habe **throws SQLException** hinzugefügt
- "Datumskonvertierung sollte auf null/Formatfehler geprüft werden" → habe Nullcheck in der convertToObject()-Methode in GameHandler.java implementiert, sowie Nullcheck im Zweitkonstruktor von Review. LocalDate.MIN und LocalDate.NOW() dienen als Absicherung, damit trotzdem ein Wert übergeben wird


## Race Condition Dokumentation
Ausgangssituation ist, dass zwei Clients gleichzeitig auf dieselbe Ressource zugreifen und versuchen, ein Spiel mit gameId 1234 zu löschen.
Ohne Synchronisation würden beide Threads gleichzeitig auf die Datenbank zugreifen, was zu Datenbankfehlern führt.
Mit Synchronisation kann hier GameHandler sicherstellen, dass immer nur genau ein Thread gleichzeitig auf die Datenbank zugreifen kann. Der zweite Thread würde in unserem Fall dann warten bis der erste fertig ist.
Ich habe mich für einen TCP Socket entschieden, da beim Übertragen der Login-Daten die Zuverlässigkeit sehr wichtig ist. Ebenso kann Datenverlust fatal sein und die Reihenfolge der gesendeten Pakete muss garantiert sein.  


## Alles zu Blatt 4

### **DISCLAIMER**
Bei der Erstellung der grafischen Benutzeroberfläche habe Ich mich bezüglich der Logik und manchen "Konventionen" bei Youtube-Guides zu JavaFX sowie Internetforen (Reddit, StackOverflow) inspirieren lassen. Dasselbe gilt für die Farben in Hexadezimalschreibweise. **Es wurde keine KI bei der Erstellung verwendet und nichts CopyPasted!** Wie bereits bei der Abgabe von Blatt 3 erwähnt, habe ich auch beim Bearbeiten von Blatt 4 manche Funktionen verwendet, auf die Ich ohne externe Hilfe wie StackOverflow, Reddit, etc nicht alleine gekommen wäre. Auch hierbei wurde **keine KI verwendet und nichts aus Foren CopyPasted**.
Falls sich irgendwo im Code noch komische System.out.println("....")-Zeilen befinden sollten entschuldige Ich mich. Sie dienten nur zum Debuggen und Ich habe versucht alle wieder zu entfernen.

## GUI Dokumentation
Für das Erstellen der grafischen Benutzeroberfläche wurde JavaFX verwendet und sie folgt dem MVC-Prinzip.

### Aufbau und Struktur
- Model-Paket
  - CLientModel.java ist dafür da, den aktuellen Zustand des Clients zu speichern (eingeloggt: Ja/Nein, etc …)
- View-Paket
  - Im View-Pakt passiert das meiste grafische, was der Anwender sieht. Hier werden die unterschiedlichen Daten angezeigt in Form von Spielesammlung, Spiele auf der Wishlist und von allen Nutzern die geschriebenen Reviews zu einem gesuchten Spiel.
  - JavaFXApp.java ist hierbei der Einstiegspunkt für die GUI. Sie startet die Anwendung und man landet in der Login/Register Ansicht. Hier hat der Nutzer die Auswahl zwischen dem Login und Register Button, je nachdem ob er bereits einen Account besitzt oder nicht. Nach erfolgreichem Login/registrieren kommt man sofort auf die "Startseite" der Anwendung, die persönliche "GameCollection". Hier hat man nun mehrere Möglichkeiten.
    - Möglichkeit 1: Add Game → Der Anwender wird zu einem Formular weitergeleitet, bei dem er alle wichtigen Details eines Spiels hinzufügen kann.
    - Möglichkeit 2: Über die Navbar oben links in eine andere Szene zu wechseln → Wishlist oder Reviews
    - Möglichkeit 3: Delete-Button → entfernt ausgewähltes Spiel
    - Möglichkeit 4: Logout → Szene wechselt zum Login/Register-Fenster 
  - In der Wishlist-Szene hat man die Möglichkeit seine aktuelle Wishlist sich anzuschauen, ein neues Spiel zur Wishlist hinzuzufügen oder ein Spiel von der Wishlist zu entfernen.
  - In der Review-Szene sieht man vorerst nichts. Hier muss man explizit nach einem Spieletitel suchen, um eine Review angezeigt zu bekommen. Wenn der Anwender auf "Create Review" clicken sollten, wird ein Formular geöffnet, wo man einen Spieletitel angeben kann inklusive Bewertung und dann in einem kleinen Textfeld eine Review schreiben kann.
- Controller-Paket
  - GameController reagiert auf die Nutzereingaben und kommuniziert mit GameClient und aktualisiert die View

### Synchronisation und kritische Bereiche
- Mehrere clients können erfolgreich gleichzeitig mit dem Server arbeiten. Ebenso ist die Datenbankoperation abgesichert und jeder User kann nur seine eigenen Spiele und Reviews bearbeiten oder löschen. Die Datenbankzugriffe erfolgen auch weiterhin ausschließlich über den Server und es kommt zu keiner Race Condition.  
- Synchronisation: Die schreibenden datenbankoperationen in den Klassen GameHandler, WishlistHandler und ReviewHandler werden synchronisiert und wurden jeweils mit **synchronized** versehen. Die Ressourcen müssen geschützt werden, damit es beim gleichzeitigen Zugriff von Threads auf dieselben Datenbanktabellen zu keinen Überschreibungen kommen kann. Dadurch, dass die Datenbank Handler-Klassen mit **synchronized** versehen worden sind, kann immer nur ein Thread gleichzeitig eine Datenbankoperation ausführen und Race Conditions werden vermieden.

### Error-Handling und Statusanzeigen
- Fast jede View-Klasse hat eine Methode **showStatus**, um dem Benutzer anzuzeigen, ob die getätigte Aktion erfolgreich war oder fehlgeschlagen ist.