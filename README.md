# Gestión biblioteca
Esta aplicación simula un sistema de gestión de libros y préstamos de una biblioteca, así como de los usuarios pertenecientes a ella. Toda la información es persistida en una base de datos local.
## Cómo usarla
### Usuarios
Dependiendo del tipo de gestión a realizar, se deberá tener un usuario de tipo "miembro" o "administrador". Los usuarios "miembro" solo pueden administrar sus préstamos, mientras que los usuarios "administrador" pueden administrar los de todos los usuarios y también los libros.
Para ello, habrá que seleccionar la opción relacionada con el tipo de usuario deseado.
#### Inicio de sesión
Tras seleccionar el tipo de usuario deseado, se deberán rellenar los campos con las credenciales del usuario del que se disponga, siendo estas el nombre de usuario y la contraseña. 
En el caso de que sean correctas, se accede al menú correspondiente.
Si no se dispone de un usuario, se debe seleccionar la opción Registro, localizada en la parte inferior. Esta opción creará un usuario del mismo tipo que el usuario con el que se haya querido iniciar sesión (p. ej., si antes se quiso iniciar sesión con un administrador pero no se dispone de una cuenta y se presiona el botón Registrarse, tras rellenar los datos correspondientes, se creará un usuario de tipo administrador)
#### Registro
En el caso de querer registrarse, es necesario seleccionar esta opción a través de un menú de inicio de sesión. El tipo de usuario creado vendrá definido por el formulario del que se provenga (p. ej., si se desea crear un usuario "miembro", se ha de seleccionar el botón "Iniciar sesión como miembro de la biblioteca", y después, en el siguiente menú, "Registrarse").
Para registrarse son necesarios 3 campos:
- Nombre de usuario (debe ser de al menos 6 caracteres, y contener solo caracteres alfanuméricos)
- Correo electrónico (debe ser un correo electrónico con un formato válido)
- Contraseña (debe tener al menos 6 caracteres)
En caso de que alguno de dichos campos no cumpla la condición, aparecerá un mensaje debajo del campo correspondiente, con los requisitos de este.
Si el usuario se ha registrado correctamente, aparecerá un aviso en la parte inferior.
Después de registrarse, puede iniciar sesión presionando el botón "Iniciar sesión", o volver al menú principal presionando la tecla Esc.
#### Recuperación de contraseña
Para la recuperación de contraseña, es necesario introducir el usuario deseado y una nueva contraseña con la que iniciar sesión.
Una vez rellenados dichos datos, la contraseña habrá sido cambiada en el sistema. Después de ello, puede iniciar sesión presionando el botón "Iniciar sesión", o volver al menú principal presionando la tecla Esc.
#### Menú de usuarios
En este menú, aparece un botón que permite el acceso a la gestión de los préstamos del usuario. Al seleccionarlo, lleva al menú de gestión de préstamos.
#### Menú de administradores
En este menú, aparecen dos botones. El localizado más a la izquierda, permite el acceso a la gestión de los libros que existan en la biblioteca. Al seleccionarlo, lleva al menú de gestión de libros. Y por último, el localizado más a la izquierda, que  permite el acceso a la gestión de los préstamos cualquier usuario de la biblioteca. Al seleccionarlo, lleva al menú de gestión de préstamos.
#### Menú de gestión de libros
Este menú permite la gestión de los libros que existen en la biblioteca. Pueden añadirse libros nuevos, y editar o borrar los ya existentes.
Tras entrar en el menú, en caso de que exista algún libro, aparecerá en una lista localizada en la parte central. Si no, puede añadirse presionando el botón localizado en la parte superior derecha, que dice "Añadir libro".
Para editar un libro, puede seleccionarse el libro con el ratón y o bien hacer doble click sobre él, o seleccionar el botón "Editar libro".
En caso de querer borrar un libro, debe seleccionarse el libro con el ratón y después presionar el botón "Borrar libro".
#### Menú de gestión de préstamos
Este menú permite la gestión de los préstamos que existen en la biblioteca en caso de que se sea administrador, o de los préstamos del propio usuario si se inició sesión con un usuario "miembro". Pueden añadirse préstamos nuevos, y editar o borrar los ya existentes.
Tras entrar en el menú, en caso de que exista algún préstamo, aparecerá en una lista localizada en la parte central. Si no, puede añadirse presionando el botón localizado en la parte superior derecha, que dice "Añadir préstamo".
Para editar un préstamo, puede seleccionarse el libro con el ratón y o bien hacer doble click sobre él, o seleccionar el botón "Editar préstamo".
En caso de querer borrar un préstamo, debe seleccionarse el préstamo con el ratón y después presionar el botón "Borrar préstamo".
### Cambio de idioma
En cada pantalla de la aplicación, hay un selector de idioma, en la parte superior derecha.
Tras seleccionarlo, aparece un desplegable. Ahí se selecciona el idioma deseado, haciendo que la aplicación cambie de idioma.
### Atajos
Existen diversos atajos, pudiendo controlar la aplicación solamente con el teclado:
#### Atajos generales:
- ESC: Vuelve al menú anterior. Si está en el menú de bienvenida, sale del programa.
- Tab: Pone el foco en los campos de texto de la aplicación, empezando por el localizado arriba del todo y siguiendo hacia abajo hasta llegar al último.
#### Atajos del menú de bienvenida
- crtl + u: Redirige al inicio de sesión del usuario de tipo "miembro".
- crtl + g: Redirige al inicio de sesión del usuario de tipo "administrador".
#### Atajos de los menús de inicio de sesión
- crtl + f: Redirige al fomulario de recuperación de contraseña.
- crtl + r: Redirige al formulario de registro.
- Enter: Iniciar sesión.
#### Atajos del menú de registro
- ctrl + l: Iniciar sesión.
- Enter: Registrarse.
#### Atajos del menú de recuperación de contraseña
- ctrl + l: Login.
- Enter: Recuperar contraseña.
#### Atajos del menú de usuario
- Enter: Entrar al menú de gestión de préstamos.
#### Atajos del menú de administrador
- crtl + l: Entrar al menú de gestión de libros.
- ctrl + p : Entrar al menú de gestión de préstamos.
#### Atajos del menú de gestión de préstamos
- crtl + a: Añadir un préstamo.
- ctrl + e / doble click en el préstamo: Editar un préstamo.
- ctrl + b: Borrar un préstamo.
#### Atajos del menú de gestión de libros
- crtl + a: Añadir un libro.
- ctrl + e / doble click en el libro: Editar un libro.
- ctrl + b: Borrar un libro.
