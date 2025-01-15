-- Crear base de datos
CREATE DATABASE IF NOT EXISTS Biblioteca;
USE Biblioteca;

-- Crear tabla Usuario
CREATE TABLE IF NOT EXISTS Usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    contrasenha VARCHAR(100) NOT NULL,
    rol ENUM('Miembro', 'Administrador') NOT NULL DEFAULT 'Miembro'
);

-- Crear tabla Libro con autor incluido
CREATE TABLE IF NOT EXISTS Libro (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    isbn VARCHAR(13) NOT NULL
);

-- Crear tabla Prestamo
CREATE TABLE IF NOT EXISTS Prestamo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fechaInicio DATE NOT NULL,
    fechaFin DATE NOT NULL,
    usuarioId INT NOT NULL,
    libroId INT NOT NULL,
    FOREIGN KEY (usuarioId) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (libroId) REFERENCES Libro(id) ON DELETE CASCADE
);

-- Insertar datos en la tabla Usuario
INSERT INTO Usuario (nombre, email, contrasenha, rol)
VALUES
    ('juan.perez', 'juan.perez@example.com', 'contrasenha123', 'Administrador'),
    ('maria.lopez', 'maria.lopez@example.com', 'contrasenha123', 'Miembro'),
    ('carlos.garcia', 'carlos.garcia@example.com', 'contrasenha123', 'Miembro'),
    ('ana.fernandez', 'ana.fernandez@example.com', 'contrasenha123', 'Miembro'),
    ('luis.martinez', 'luis.martinez@example.com', 'contrasenha123', 'Administrador');

-- Insertar datos en la tabla Libro (incluye autor)
INSERT INTO Libro (titulo, autor, isbn)
VALUES
    ('Cien Años de Soledad', 'Gabriel García Márquez', '9788437604947'),
    ('La Casa de los Espíritus', 'Isabel Allende', '9788497592200'),
    ('La Ciudad y los Perros', 'Mario Vargas Llosa', '9780679733038'),
    ('El Aleph', 'Jorge Luis Borges', '9788420658728'),
    ('Rayuela', 'Julio Cortázar', '9788466332525');

-- Insertar datos en la tabla Prestamo
INSERT INTO Prestamo (fechaInicio, fechaFin, usuarioId, libroId)
VALUES
    ('2023-10-01', '2023-10-15', 1, 1),
    ('2023-09-20', '2023-10-05', 2, 2),
    ('2023-10-05', '2023-10-20', 3, 3),
    ('2023-09-30', '2023-10-15', 4, 4),
    ('2023-10-01', '2023-10-10', 5, 5);
