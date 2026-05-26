#Persönliche Videospielverwaltung

##Projektbeschreibung
- Mein Projekt ist eine Desktop-Anwendung, mit der man seine persönliche Videospielsammlung verwalten kann. Hier können Nutzer ihre Spiele eintragen, deren Status bearbeiten und einsehen (in Besitz, auf der Wunschliste, verkauft, etc.). Ebenso gibt es die Möglichkeit, Spiele zu bewerten und Sammlungen mit mehreren Inhabern anzulegen. 
  - Hauptaufgabe: Zentrale und kompakte Übersicht über die eigenen Spiele und deren Status.
  - Zielgruppe: Privatpersonen mit großer und kleiner Spielesammlung.
  - Motivation für die Nutzung: Strukturierte Übersicht über die eigene Sammlung, Spiele auf der Wishlist und verkaufte/besessene Spiele mit personalisierten Statistiken, sowie keine Gefahr des doppelten Kaufens bei großen Sammlungen.

##Geplante Funktionalitäten
- Must Have:
  - Spiele hinzufügen, bearbeiten und entfernen
  - Sammlung mit mehreren Personen anlegen (z.B.: Familiensammlung)
  - Statuswechsel (Auf der Wunschliste -> gekauft/in Besitz -> durchgespielt -> besessen/verkauft)
  - Sammlung anzeigen und nach Kategorien filtern (Genre, Platform, Releasedate, etc.)
- Should Have:
  - Persönliche Bewertung mit kurzer Notiz/Kritik
  - Suchfunktion (Titel, Platform, Entwickler, etc.)
- Nice to Have:
  - Statistiken (Wieviele Spiele, Gesamtwert, wieviele duchgespielt, etc.)

##Bereits implementierten Funktionalitäten
- Getter- und Settermethoden  für alle Klassen implementiert
- toString(), equals() und hashCode() implementiert
- Grundstrukturklassen angelegt(Game, Platform, Genre, Developer,ReleaseDate, Status, Review, Player, Wishlist, Price)
- Funktionen addGame() und removeGame() hinzugefügt
- ID-Variablen mit final versehen
- Comparator Methode 
- Package für Suchfunktion erstellt
- Neues Interface für spätere Suchfunktion

##Änderungen seit der letzten Abgabe
- Package-Struktur verbessert
- Folgende Klassen implementiert:
  - Developer, Genre, Platform, Player, Review, Status, Wishlist
- getter-, setter-, hash-, equals- und toString-Methoden für alles Klassen implementiert 
- Datenbankfunktion "TestConnection" erfolgreich getestet
- Mydatabase.db erstellt
- GameCollection-Klasse implementiert mit folgenden Funktionen:
  - saveGam(),deleteGame(), showFullCollection()(ist noch nicht vollstaendig)


##Noch zu implementieren
- Datenbank:
  - weitere Funktionalitaeten
- Netzwerk
- GUI
- Nebenläufigkeit 

