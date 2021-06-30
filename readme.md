# Topologie Generator

#### Zweck
Dieses Dokument erläutert die Nutzung des Topologie Generators. 

#### Installation
Die Software liegt in zwei Teilen vor. Der erste Teil ist die Oberfläche, welche mithilfe des PHP MyGenerators 
importiert werden muss. Den zweiten Teil stellt das Java Programm dar, welches für die Erstellung der Grafiken
benötigt wird. Um eine vollständige Funktionsfähigkeit zu gewährleisten müssen beide Teile installiert und gestartet sein,
sonst kann es zu Fehlern kommen.

#### Entpacken

Zunächst muss die ZIP-Datei mit dem Namen "Topologie" entpackt werden. Danach kann der Ordner mithilfe einer Entwicklungsumgebung geöffnet werden.
Alle Abhängigkeiten werden automatisch mithilfe von Maven verwaltet. Die einzige Ausnahme bildet die PlantUML.jar Datei, 
welche sich im Ordner "lib" befindet. Diese muss manuell über die Projektstruktur als externe JAR eingefügt werden.
über File -> Project Structure manuell die Library hinzugefügt werden.

In einer zweiten ZIP-Datei mit Namen "Topologie_PHP" befindet sich ein PHP-Generator Project, welches nachdem es ausgepackt wurde simple
in PHP Generator importiert werden kann. Im Ordner xampp/htdocs/topologie muss die start.html noch eingebunden werden.

#### Datenbank anlegen
Bevor die Anwendung nun gestartet werden kann, muss sichergestellt werden, dass in XAMPP Apache und MySQL gestartet sind.
Zudem muss eine leere Datenbank mit dem Namen "topologie" erstellt werden. Für den Fall die Datenbank nach eigenem 
Gusto zu benennen ist dies auch möglich. Allerdings muss dafür in der Datei "application.properties" im
Java Programm der Name der sql Datenbank auf den selbstgewählten geändert werden.

In der Projektstruktur ist eine Grundlegende Datenbank (topologie.sql) eingebunden, welche genutzt werden kann, um sich einen ersten Eindruck der Anwendung zu machen. Diese kann in der Datenbank unter dem Punkt Importieren (PhpMyGenerator) eingebunden werden.

#### Vorbereitung PHP-Generator
Die "Topologie_PHP" Datei sorgt dafür, die Daten einfach in der Datenbank persistieren zu können. Die Webansicht
kann geöffnet werden, in dem man im Browser den Pfad _**localhost/topologie/start.html**_ angibt. 

#### Programm starten
Wenn Sie alle bisherigen Punkten gefolgt sind, kann man nun die Java Anwendung starten in dem man die "TopologieSpringBootApplication"
ausführt. In der Commandozeile dürfen hierbei keine Fehler auftreten, sonst stimmt etwas mit der Konfiguration nicht.

In der Anwendung sollte nun über den Reiter PHP Generator die Datenverwaltung und über den Reiter Grafik anzeigen der Output
zur Verfügung stehen.

##Troubleshooting
Wenn die Anwendung nicht startet, kann dies mehrere Ursachen haben. Zum Teil gestaltet sich die Fehlersuche schwierig, da die Fehlermeldungen
nicht immer aussagekräftig sind. In der Regel lassen sich die Fehler aber etwas untergliedern:

<ul>
<li>Thymeleaf Fehler: Treten in der Regel auf, wenn beim erstellen des Contents eine Verbindung falsch angelegt ist.
Beispielsweise durch eine Communication, welche zu einer Software gehört, die es nicht gibt. Oft steht in diesem Zusammenhang:
No Content available.
Thymeleaf Fehler betreffen immer das Frontend</li>
<li>Fehler im Reiter "PHP Generator" in der Webansicht: Steht hier no Content so könnte es sein, dass der Pfad in der Start.html
Seite nicht mit dem Pfad des eigenen Xampp Ordners übereinstimmt. Die Pfade sollten überprüft werden.</li>

<li>Fehler beim Starten der Java Anwendung: Die Fehler hier sind in der Regel auf die Datenbank zurückzuführen, sofern an den Dateien nichts geändert wurde.
Eventuell ist eine Column nicht erstellt worden, welche vom Spring Framework jedoch erwartet wird. Die genaue Fehlerbezeichnung kann
in der Console ausgelesen werden.</li>
<li> Probleme beim Hinzufügen von Software Objekten / Kommunicationsobjekten: Hier scheint es sich um einen Bug im
PHP MyGenerator zu handeln. Dieser schlüsselt aus uns unbekannten Gründen nicht den Namen der Software, sondern den Namen des Operation Systems auf.
Daher ist eine Zuordnung zur Software nicht möglich. Eventuell lässt sich dies einfach beheben, durch ein Upgrade auf PHP Generator Pro (Für unschlagbare 249$). Daher wird empfohlen hierfür auf die SQL-Insert-Funktion des PhpMyAdmin zurückzugreifen. Diese ist verfügbar unter localhost/phpmyadmin.</li>
<li>Autoinkrementierung der einzelnen Tabellen: Die Autoinkrementierung kann aus ebenso unerfindlichen Gründen nicht nachträglich eingestellt werden für die Primary Keys.
im Falle einer Neufüllung der Datenbank wird empfohlen die Tabellen zunächst auf AI zu überprüfen und erst danach zu befüllen. Andernfalls hat man mit vielen Constraints und Abhängigkeiten zu kämpfen.</li>
<li>Xampp nicht gestartet: Führt dazu, dass die Datenbank nicht gefunden werden kann. Java Anwendung und PHP-Seite werfen einen Fehler.</li>
</ul>

##Schlussworte
Bei diesem Programm handelt es sich um einen Prototypen, daher sind Fehler/ Makel ein wichtiger Bestandteil. Es wurde versucht diese auf ein Minimum zu begrenzen, jedoch sind zum Teil
die verwendeten Tools selbst nicht makellos (siehe PHP Generator->AI, Objektreferenzierung / PlantUML -> Objekterstellung). Diese konnten mit unseren Ressouren im zeitlichen Rahmen des Projektes nicht gelöst werden. 
Es soll bei einer eventuellen Erweiterung dazu dienen, auf Grundfunktionalitäten aufbauen zu können. 


Diese Anwendung wurde in Kooperation mit vielen technischen Ausfällen und Rückschlägen erstellt. Nichts desto trotz haben wir es am Ende zu einer lauffähigen Anwendung bringen können, welche großes Potenzial auf dem Markt hat. Wir sind stolz auf unser Endprodukt. Genauere Informationen zum Anwendungsfall sind der offiziellen Projektdokumentation zu entnehmen.
Das Copyright dieser Anwendung liegt beim Topologieteam bestehend aus Daria Hof, Semine Imeri, Laura Kremers, Viola Winter, Jacob Drouin und Tanja Heinrich.

