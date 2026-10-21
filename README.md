# Gestión de grafos

Proyecto Java con grafos de matriz de adyacencia, compatible con Apache NetBeans y Visual Studio Code. Requiere Java 17 o superior.

## Abrir en Apache NetBeans

Abra la carpeta que contiene `pom.xml` como proyecto Maven. NetBeans reconocerá automáticamente las fuentes y la clase principal `com.proyecto.grafo.Main`.

## Ejecutar en Visual Studio Code

Abra la carpeta en VS Code con el Extension Pack for Java. Ejecute `Main.java` desde el editor o use el terminal:

```text
javac -encoding UTF-8 -d target\classes src\main\java\com\proyecto\grafo\*.java
java -cp target\classes com.proyecto.grafo.Main
```

Si Maven está instalado, también puede ejecutar:

```text
mvn clean package
java -cp target\classes com.proyecto.grafo.Main
```

## Uso

El menú principal permite crear una instancia de grafo dirigido o no dirigido. Cada instancia comienza con cuatro vértices numerados desde `0`. El menú del grafo incluye operaciones para agregar y eliminar vértices/aristas, modificar conexiones, imprimir la matriz, listar conexiones y consultar una arista. Al eliminar un vértice, los índices posteriores se renumeran.
