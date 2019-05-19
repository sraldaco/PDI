# PD1 2019-2 - Filtros

### Alejandro Vargas Aldaco
- Profesor: Manuel Cristóbal López Michelone
- Ayudante: Yessica Martínez Reyes
- Ayud. Lab.: César Hernández Solís

##### Practica 1:

* RGB
* BRILLO
* ALTO CONTRASTE
* INVERSO
* MOSAICO
* ESCALA DE GRISES

##### Práctica 2:

* Blur
* Motion Blur
* Encontrar Bordes
* Sharpen
* Emboss
* Mediano

##### Práctica 3:

* Letras
  - Entrada de texto
  - Con archivo .txt
  - Escala de grises
  - A color 
* Domino
* Playin Cards

##### Práctica 4:

* Agregar marca de agua
* Remover marca de agua (Sólo casos específicos de la clase)

##### Práctica 5:

* Óleo
* Sepia

##### Práctica 6

* Imágenes recursivas 

##### Práctica 7

* Dither
  - Aleatorio
  - Ordenado
  - Disperso
  - Difusión de error

##### Práctica 8
* Semitonos
  - Matriz espiral
  
  ## Proyecto Final
  ### FotoMosaico
  
  Crea fotomosaicos a patir de una imagen. Por ejemplo (Da click en la imagen para verla a detalle).
  
  <img alt="Batman photomosaic" style="max-width:100%;" src="https://raw.githubusercontent.com/sraldaco/PDI/master/Filtros/src/main/webapp/resources/output/Batman-Wallpaper-low-quality.jpg" >
 

# Software!
##### Para ejecución
  - JavaEE 7 (Requerido!)
  - Maven 3 (Requerido!)
  - NetBeans ** (Versión completa que contiene JavaEE)
  - GlasFish ** (Integrado en Netbeans)
  - Tomcat 8+ *
##### Tecnologías utilizadas
  - JavaEE 7
  - JSF 2.2
  - Bootstrap 4.1
  - jQuery 3.3.1

# Ejecución

##### Usando NetBeans

1. Abrir el proyecto desde el menú "Abrir proyecto".
2. Seleccionar el proyecto.
3. Dar clic derecho y seleccionar la opción "Limpiar y construir"
4. Ejecutar el proyecto

##### Usando la terminal
&nbsp;
```sh
$ git clone https://github.com/sraldaco/PDI.git
$ cd ./PDI/Filtros/
$ mvn clean install
$ mvn tomcat7:run
```

Una vez iniciado el plugin de tomcat7, entrar a la url...
```sh
http://localhost:8080/Filtros/faces/filtros/contrast.xhtml
```

O en su caso...
```sh
http://127.0.0.1:8080/Filtros/faces/filtros/contrast.xhtml
```
> Es importante **verificar** que el **puerto 8080** esté **libre** antes de la ejecución.
También puede variar el puerto según la configuración del usuario.

##### Tomcat 8
Para usar con **tomcat 8**: Revise el manual de configuración para poder hacer el deploy y lanzar la aplicación.


** Los Filtros se encuentran en: Filtros⁩ ▸ ⁨src⁩ ▸ ⁨main⁩ ▸ ⁨java⁩ ▸ ⁨com⁩ ▸ ⁨filters⁩ **
