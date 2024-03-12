<h1 align="center"> Examen Libre TDP 2024 </h1>
<h2 align="center"> Marcos Constantino - Pacman </h2>
<h2 align="center"> Herramientas utilizadas </h2>
<b> Eclipse: </b> No se si voy a seguir usandolo. Casi me cuesta el proyecto (seguro fue culpa mía... pero igual 😠)<br>
<b> Let's build Pacman: </b> <a href="https://www.youtube.com/watch?v=_g2_KQIr2qk&list=PLD5R3cJr8wU2ZNZ-l5MKowc7sXwaV8hF)">Esta guía</a>
 fue especialmente util al comienzo aunque rapidamente deje de consultarlo cuando paso a la carga de mapas mediante bitmaps (yo sabía desde el principio que quería hacerlos en base a texto para que sean facilmente personalizables). Pero es muy valiosa la guia al comienzo cuando tenes ideas y nada mas que eso. <br>
<b> StarUML: </b> Utilizado para los siempre cambiantes diagramas de clases. <br>
<b> Copilot (GPT4): </b> Utilizado para cosas poco específicas o que no serán base o fundación de desarrollo futuro, principalmente porque no confio mucho en la misma. Especialmente util cuando me olvidaba de un break en un switch case (pasó más de una vez) y para el manejo de BufferedImage. <br>
<b> StackOverflow: </b> ¡Hoy hice mi primera pregunta de Stackoverflow! Me mataron porque aparentemente no puse suficiente info o la pregunta ya era repetida. Y ya la cerraron y la downvotearon. Que rápido. Podés leerla acá: https://stackoverflow.com/questions/78141000/java-getresourceasstream-throws-nullpointer-even-though-the-path-worked-with-fil <br>
<figure>
  <img src="https://github.com/MarcosConstantino2003/Constantino-PACMAN-TDP/assets/117044247/552d41d4-08f8-4919-9eb2-9e32002db83d" alt="so" style="width:100%">
  <figcaption>Perdonenme.</figcaption>
</figure>
<br>

Quise usar algun programa de visualizador de complejidad como <b> Cyvis </b> o <b> Sonarqube </b> (en su versión comunitaria) pero no pude hacer andar ninguno. Son sorpresivamente escasos los programas que cumplen esta función y me hubiera gustado utilizarlos para saber donde refactorizar con precisión.  
<h2 align="center"> El juego </h2>
El juego consta de 3 niveles (de cuestionable diseño, no soy diseñador de niveles 😅), con el tercero teniendo 2 portales de cada lado en lugar de uno solo. Los niveles se pueden personalizar reemplazando el carácter deseado en el bloc de notas de algun nivel siguiendo los caracteres asignados a cada tipo de tile: <br>
  - x es <b> Wall </b> <br>
  - c es <b> Cage </b> (donde habitan los fantasmas antes de salir) <br>
  - . es <b> Food </b> <br>
  - y es <b> Superfood </b> <br>
  - p es el <b> Spawnpoint de Pacman </b> <br>
  - l es <b> Warp izquierdo </b> <br>
  - r es <b> Warp derecho. </b> <br>
Por ejemplo, mientras desarrollaba queria testear la pantalla de victoria. Gracias a esta forma de leer los niveles pude en unos segundos hacer un nivel linea en el que se gana muy rapidamente. <br>
<img src="https://github.com/MarcosConstantino2003/Constantino-PACMAN-TDP/assets/117044247/b769c939-6439-41fc-8215-b7b2f31044b9" alt="so" style="width:100%">
Hasta pensé en hacer un editor de niveles dentro del juego (me comí la peli). Otra ocasion tal vez.<br> <br>
Por otro lado el juego cuenta con algunos controles y tambien un par de trucos. Los vas a encontrar en la sección correspondiente del menú. <br>
<h2 align="center"> Minutas </h2>
Al principio crei que era una tarea realmente dificil para mi, capaz que demasiado, hacer esto; sobretodo considerando que vengo de estrucuras donde no se hace nada ni a una porción de esta escala. Pero apenas empecé me enganché rápido y le meti durante varias horas durante muchos dias seguidos casi sin sentir fatiga, probablemente porque lo encontraba entretenido y sentia una sensación de recompensa al ver progreso en el juego. Es un proyecto que me desafió en básicamente cada etapa y por eso, y porque hace dos horas perdi todo lo de hoy y casi me muero, si bien NO pude arreglar las malditas animaciones (le prometo que lo intenté como nunca, solo me falto probar a hacer que en los gifs, el sprite mismo se mueva, dentro del gif...no gracias), estoy en general contento por como quedó. Tengo un par de fotos de como fue evolucionando el desarrollo que no creo que ni afecte la nota ni le importe a usted pero quería subirlas acá por si algún día cuando capaz me vaya mejor pueda volver acá y sentir algo de nostalgia. O no. No sé. <br>
<img src="https://github.com/MarcosConstantino2003/Constantino-PACMAN-TDP/assets/117044247/aa6edb2e-bd70-469e-b225-78dcafeed318" alt="so" style="width:100%">
Al primer día, hice un cuadrado amarillo que se mueve (yay!) y un Scanner que leia el bloc de notas. <br>
<img src="https://github.com/MarcosConstantino2003/Constantino-PACMAN-TDP/assets/117044247/a34a2bbf-c16d-4b80-8cb7-fe439e6e6bb2" alt="so" style="width:100%">
Costó pero eventualmente pude dibujar el mapa! <br>
<img src="https://github.com/MarcosConstantino2003/Constantino-PACMAN-TDP/assets/117044247/97a3030a-9ea5-41cc-b8fe-5fca86dce840" alt="so" style="width:100%">
Las colisiones funcionando por primera vez 😄 <br>
<img src="https://github.com/MarcosConstantino2003/Constantino-PACMAN-TDP/assets/117044247/c0e325af-d47e-4009-9cf2-28b80b141f5b" alt="so" style="width:100%">
El mapa pegó glowup (aunque Pacman estaba solo... y no podía comer nada) <br> <br>


<h4 align="center"> Marcos Constantino, 2024, TDP, UNS. </h4>
