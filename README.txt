Nuestra tarea está compuesta de 4 carpetas en cual cada una representa un Stage de avance de
la tarea, es decir que la ultima Stage corresponde a lo pedido por el profesor(dibujar USM 
y 2 drones).

Para compilar cualquiera de estas Stage es necesario estar ubicado en la carpeta de la Stage
que se desea compilar utilizado el comando cd, luego una vez dentro de la carpeta se deberia
poder visualizar los archivos java con ls(Linux) o dir(Windows).

Para compilar se compila el main (StageTest.java) : Ejemplo de compilar Stage3:

$javac Stage3Test.java

Luego de esto se deberian generar los archivos .class que son los que se ejecutara por la 
maquina virtual java.

Para ejecutar una Stage1 deber usar el siguiente comando:

$java Stage1Test 

Para ejecutar Stage 2 ,3 y 4

$java StageNTest entrada.csv     , con N como el numero de la Stage.

Esto puesto que a partir de la Stage 2 los programas requieren de un archivo de entrada que
viene simulando las posiciones de un Joystick en un control remoto.Estos valores de la 
posicion van de -1 a 1 y son 4 joysticks. 

La Stage1 genera un archivo de salida con las posiciones del joystick durante el tiempo, esto
puesto que el archivo de entrada correspondiente a la posicion de los joysctik solo muestra
la posicion del joystcick cuando se genera un cambio de este y el de salida muestra la posicion
de este joystick en el tiempo.

La Stage2 imprime por pantalla las posiciones del dron en el tiempo con respecto a las 
posiciones del joystick, para esto es necesaria las posiciones del joysctik del tiempo que
tenemos de la stage1 y en esta etapa imprime las posiciones usando la formula de tiempo y
velocidad, la que multiplicada da la distancia recorrida en ese tiempo.Las velocidades del
dron vienen dadas por la posicon de los joystick.

En stage3 se incluye un dron el cual pude ser usado por le teclado, para usarlo se tiene que
apreta espacio y enter.

Por otro lado en la Stage4 se generaran dos archivos.csv nuevos los cuales cada uno indica la 
posicion en los eje x, y, z de los drones