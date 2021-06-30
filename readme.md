# Topologie Generator

#### Zweck
Dieses Dokument erläutert die Nutzung des Topologie Generators. 

#### Installation
Die Software liegt in zwei Teilen vor. Der erste Teil ist die Oberfläche, welche mithilfe des PHP MyGenerators 
importiert werden muss. Den zweiten Teil stellt das Java Programm dar, welches für die Erstellung der Grafiken
benötigt wird. Um eine vollständige Funktionsfähigkeit zu gewährleisten müssen beide Teile installiert und gestartet sein,
sonst kann es zu Fehlern kommen.

##### Entpacken

Zunächst muss die ZIP-Datei entpackt werden. Danach kann der Ordner mithilfe einer Entwicklungsumgebung geöffnet werden.
Alle Abhängigkeiten werden automatisch mithilfe von Maven verwaltet. Die einzige Ausnahme bildet die PlantUML.jar Datei, 
welche sich im Ordner "lib" befindet. Diese muss manuell über die Projektstruktur als externe JAR eingefügt werden.
über File -> Project Structure manuell die Library hinzugefügt werden.


