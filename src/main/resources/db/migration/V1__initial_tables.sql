CREATE TABLE Proveedor (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    contacto VARCHAR(255),
    telefono VARCHAR(50),
    email VARCHAR(255),
    direccion TEXT
);

CREATE TABLE MateriaPrima (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    unidad_medida VARCHAR(50),
    stock_actual INT DEFAULT 0,
    proveedor_id BIGINT,
    FOREIGN KEY (proveedor_id) REFERENCES Proveedor(id) ON DELETE SET NULL
);

CREATE TABLE OrdenCompra (
    id BIGSERIAL PRIMARY KEY,
    proveedor_id BIGINT NOT NULL,
    fecha DATE NOT NULL,
    estado VARCHAR(50) DEFAULT 'Pendiente',
    total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (proveedor_id) REFERENCES Proveedor(id) ON DELETE CASCADE
);

CREATE TABLE EntradaMateriaPrima (
    id BIGSERIAL PRIMARY KEY,
    materia_prima_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    fecha DATE NOT NULL,
    orden_compra_id BIGINT,
    FOREIGN KEY (materia_prima_id) REFERENCES MateriaPrima(id) ON DELETE CASCADE,
    FOREIGN KEY (orden_compra_id) REFERENCES OrdenCompra(id) ON DELETE SET NULL
);

CREATE TABLE SalidaMateriaPrima (
    id BIGSERIAL PRIMARY KEY,
    materia_prima_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    fecha DATE NOT NULL,
    destino VARCHAR(255),
    FOREIGN KEY (materia_prima_id) REFERENCES MateriaPrima(id) ON DELETE CASCADE
);

CREATE TABLE Distribucion (
    id BIGSERIAL PRIMARY KEY,
    salida_materia_prima_id BIGINT NOT NULL,
    destino VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (salida_materia_prima_id) REFERENCES SalidaMateriaPrima(id) ON DELETE CASCADE
);

CREATE TABLE Reportes (
    id BIGSERIAL PRIMARY KEY,
    tipo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha_generacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE alerta_reabastecimiento (
     id BIGSERIAL PRIMARY KEY,
     area_solicitante VARCHAR(255) NOT NULL,
     descripcion TEXT NOT NULL,
     fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     categoria VARCHAR(100),
     estado VARCHAR(50),
     usuario VARCHAR(100),
     criticidad VARCHAR(50),
     orden_compra_id BIGINT,
     FOREIGN KEY (orden_compra_id) REFERENCES OrdenCompra(id)
);