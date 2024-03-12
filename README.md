<h1 align="center"> Examen Libre TDP 2024 </h1>
<h2 align="center"> Marcos Constantino - Pacman </h2>
<h3> Herramientas utilizadas:</h3>
<b> Eclipse: </b> No se si voy a seguir usandolo. Casi me cuesta el proyecto <br>
<b> Let's build Pacman: </b> Esta guía (https://www.youtube.com/watch?v=_g2_KQIr2qk&list=PLD5R3cJr8wU2ZNZ-l5MKowc7sXwaV8hFg) fue especialmente util al comienzo aunque rapidamente deje de consultarlo cuando paso a la carga de mapas mediante bitmaps (yo sabía desde el principio que quería hacerlos en base a texto para que sean facilmente personalizables). Pero es muy valiosa la guia al comienzo cuando tenes ideas y nada mas que eso. <br>
<b> StarUML: </b> Utilizado para los siempre cambiantes diagramas de clases. <br>
<b> Copilot (GPT4): </b> Utilizado para cosas poco específicas o que no serán base o fundación de desarrollo futuro, principalmente porque no confio mucho en la misma. Especialmente util cuando me olvidaba de un break en un switch case (pasó más de una vez) y para el manejo de BufferedImage. <br>
<b> StackOverflow: </b> ¡Hoy hice mi primera pregunta de Stackoverflow! Me mataron porque aparentemente no puse suficiente info o la pregunta ya era repetida. Y ya la cerraron y la downvotearon. Que rápido. Podés leerla acá: https://stackoverflow.com/questions/78141000/java-getresourceasstream-throws-nullpointer-even-though-the-path-worked-with-fil <br>
![image](https://github.com/MarcosConstantino2003/Constantino-PACMAN-TDP/assets/117044247/552d41d4-08f8-4919-9eb2-9e32002db83d)

Quise usar algun programa de visualizador de complejidad como <b> Cyvis </b> o <b> Sonarqube </b> (en su versión comunitaria) pero no pude hacer andar ninguno. Son sorpresivamente escasos los programas que cumplen esta función y me hubiera gustado utilizarlos para saber donde refactorizar con precisión.  
<h2 align="center"> El juego </h2>
El juego consta de 3 niveles, con el tercero teniendo 2 portales de cada lado en lugar de uno solo. Los niveles se pueden personalizar reemplazando el carácter deseado en el bloc de notas de algun nivel siguiendo los caracteres asignados a cada tipo de tile: <br>
  - x es <b> Wall </b> <br>
  - c es <b> Cage </b> (donde habitan los fantasmas antes de salir) <br>
  - . es <b> Food </b> <br>
  - y es <b> Superfood </b> <br>
  - p es el <b> Spawnpoint de Pacman </b> <br>
  - l es <b> Warp izquierdo </b> <br>
  - r es <b> Warp derecho. </b> <br>


