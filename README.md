# Somos Tres

Proyecto Spring Boot / Thymeleaf del emprendimiento Somos Tres.

## Cambios realizados
- Se amplió `CatalogoController` para atender `/catalogo`, `/catalogo/` y `/catalogo/listado`.
- Se completó la vista del catálogo con encabezado, accesos por categoría y secciones de Camisas, Tops y bodies, y Pantalones.
- Se agregó ordenamiento en pantalla por destacados, nombre y precio.
- Se agregaron enlaces de consulta por WhatsApp en cada producto.
- Se creó `ContactoController` y la vista `templates/contacto/contacto.html`.
- Se actualizó el menú para que Categorías lleve a las secciones del catálogo y Contacto abra su nueva página.
- Se permitió el acceso público a `/contacto` en `SecurityConfig`.
- Se ampliaron los estilos en `static/css/estilos.css`.
- Se agregó la imagen de referencia `static/img/contactoSomos3.jpeg`.

## Ejecutar
Abrir la carpeta `Somos3` como proyecto Maven en NetBeans o IntelliJ y ejecutar `Somos3Application`. La aplicación utiliza el puerto 8080 y la configuración de MySQL existente en `src/main/resources/application.properties`.

## Nota sobre el contenido
Los productos y precios que aparecen en el catálogo son los que ya estaban escritos en el proyecto original. Las áreas de imagen de los productos siguen siendo marcadores de posición porque el ZIP original no incluía fotografías individuales de cada prenda. El botón de cada producto inicia una consulta por WhatsApp al número proporcionado.
