go        
DECLARE @Tabla varchar(50);        
DECLARE @NroTarea varchar(15); 
DECLARE @ConsultaInsert varchar (5000); 

--------------------
SET @NroTarea = '#';
SET @Tabla = 'usuario';/* Nombre de la tabla a insertar.*/    

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) > 0
        PRINT 'Tabla:'+@Tabla+' - YA EXISTE LA TABLA QUE DESEA CREAR. Tarea:' + @NroTarea; 
    ELSE 
		SET @ConsultaInsert = 'CREATE TABLE [dbo].[usuario](
									[id] [bigint] NOT NULL,
									[activo] [bit] NULL,
									[clave] [varchar](255) NULL,
									[usuario] [varchar](255) NULL,
									[nombre] [varchar](50) NULL,
									[email] [varchar](50) NULL,
									[telefono] [varchar](50) NULL,
									[id_sucursal] [int] NULL,
									[id_cliente] [int] NULL,
									[id_comercio] [int] NULL,
								 PRIMARY KEY CLUSTERED 
								(
									[id] ASC
								)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
								) ON [PRIMARY]';/* Sql Insert.*/
	   
		BEGIN
			EXEC (@ConsultaInsert);
			PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - OK. Tarea:' + @NroTarea;
		END 
 
--------------------
SET @NroTarea = '#';
SET @Tabla = 'rol';/* Nombre de la tabla a insertar.*/    

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) > 0
        PRINT 'Tabla:'+@Tabla+' - YA EXISTE LA TABLA QUE DESEA CREAR. Tarea:' + @NroTarea; 
    ELSE 
		SET @ConsultaInsert = 'CREATE TABLE [dbo].[rol](
									[id] [bigint] NOT NULL,
									[rol] [varchar](255) NULL,
								PRIMARY KEY CLUSTERED 
								(
									[id] ASC
								)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
								) ON [PRIMARY]';/* Sql Insert.*/
	   
		BEGIN
			EXEC (@ConsultaInsert);
			PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - OK. Tarea:' + @NroTarea;
		END 
--------------------
SET @NroTarea = '#';
SET @Tabla = 'roles_usuarios';/* Nombre de la tabla a insertar.*/    

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) > 0
        PRINT 'Tabla:'+@Tabla+' - YA EXISTE LA TABLA  QUE DESEA CREAR. Tarea:' + @NroTarea; 
    ELSE 
		SET @ConsultaInsert = 'CREATE TABLE [dbo].[roles_usuarios](
									[id_usuario] [bigint] NOT NULL,
									[id_rol] [bigint] NOT NULL,
								PRIMARY KEY CLUSTERED 
								(
									[id_usuario] ASC,
									[id_rol] ASC
								)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
								) ON [PRIMARY]';/* Sql Insert.*/
	   
		BEGIN
			EXEC (@ConsultaInsert);
			PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - OK. Tarea:' + @NroTarea;
		END 
--------------------
SET @NroTarea = '#';
SET @Tabla = 'hibernate_sequence';/* Nombre de la tabla a insertar.*/    

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) > 0
        PRINT 'Tabla:'+@Tabla+' - YA EXISTE LA TABLA QUE DESEA CREAR. Tarea:' + @NroTarea; 
    ELSE 
		SET @ConsultaInsert = 'CREATE TABLE [dbo].[hibernate_sequence](
									[next_val] [bigint] NULL
								) ON [PRIMARY]';/* Sql Insert.*/
	   
		BEGIN
			EXEC (@ConsultaInsert);
			PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - OK. Tarea:' + @NroTarea;
		END 
--------------------
SET @NroTarea = '#';
SET @Tabla = 'banner';/* Nombre de la tabla a insertar.*/    

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) > 0
        PRINT 'Tabla:'+@Tabla+' - YA EXISTE LA TABLA QUE DESEA CREAR. Tarea:' + @NroTarea; 
    ELSE 
		SET @ConsultaInsert = 'CREATE TABLE [dbo].[banner](
									[id] [int] NOT NULL,
									[de_pagina] [varchar] (50) NULL,
									[de_titulo] [varchar](50) NULL,
									[de_imagen] [varchar](50) NULL,
									[de_descripcion] [varchar](100) NULL,
									[de_enlace] [varchar](50) NULL,
									[de_descripcion_enlace] [varchar](50) NULL,
									[sn_activo] [varchar] (1) NULL,
								 CONSTRAINT [PK_banner] PRIMARY KEY CLUSTERED 
								(
									[id] ASC
								)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
								) ON [PRIMARY]';/* Sql Insert.*/
	   
		BEGIN
			EXEC (@ConsultaInsert);
			PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - OK. Tarea:' + @NroTarea;
		END 
GO

-- Usuario: admin Password: 1234
INSERT INTO usuario
           (id
           ,activo
           ,clave
           ,usuario)
     VALUES
           (1
           ,1
           ,'$2a$04$sT70v7qFJWnQA69MdOX5OubIv85jS2cihft3lEaY7q4rYlqFdUFo.'
           ,'admin')
GO

-- Usuario: user Password: 1234
INSERT INTO usuario
           (id
           ,activo
           ,clave
           ,usuario)
     VALUES
           (2
           ,1
           ,'$2a$04$sT70v7qFJWnQA69MdOX5OubIv85jS2cihft3lEaY7q4rYlqFdUFo.'
           ,'user')
GO


-- Usuario: vgm Pass: 846
INSERT INTO usuario
           (id
           ,activo
           ,clave
           ,usuario)
     VALUES
           (3
           ,1
           ,'$2a$04$b3d10o3IkSA0cxHAA6R1PubbSa/7m2NdIfzGvzyP36fv0yAZGkYAe'
           ,'vgm')
GO

INSERT INTO [rol]
           ([id]
           ,[rol])
     VALUES
           (1
           ,'ROLE_ADMIN')
GO

INSERT INTO [rol]
           ([id]
           ,[rol])
     VALUES
           (2
           ,'ROLE_USER')
GO

INSERT INTO [roles_usuarios]
           ([id_usuario]
           ,[id_rol])
     VALUES
           (1
           ,1)
GO

INSERT INTO [roles_usuarios]
           ([id_usuario]
           ,[id_rol])
     VALUES
           (1
           ,2)
GO

INSERT INTO [roles_usuarios]
           ([id_usuario]
           ,[id_rol])
     VALUES
           (2
           ,2)
GO
INSERT INTO [roles_usuarios]
           ([id_usuario]
           ,[id_rol])
     VALUES
           (3
           ,1)
GO


--/* INICIO ---  refs #*/				
go
DECLARE @Columna varchar(50);           
DECLARE @Tipo_Columna varchar(50);         
DECLARE @Tabla varchar(50);             
DECLARE @ConsultaAlter varchar (5000);        
DECLARE @NroTarea varchar(15); 
DECLARE @Dato_Nuevo_Columna varchar(50);            
DECLARE @ConsultaUpdate varchar (5000); 

--------------------
SET @NroTarea = '#';
SET @Columna = 'sn_pagina_web';/* Nombre de la columna a insertar.*/
SET @Tipo_Columna = 'varchar(1)Default(''S'')';/* Tipo de la columna a insertar.*/
SET @Tabla = 'linea';/* Nombre de la tabla a modificar.*/    

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) = 0
        PRINT 'Tabla:'+@Tabla+' - NO EXISTE LA TABLA A LA QUE DESEA INSERTAR LA COLUMNA. Tarea:' + @NroTarea; 
    ELSE 
        SET @ConsultaAlter =
            'ALTER TABLE '+@Tabla+' ADD '+@Columna +' '+ @Tipo_Columna; 
        IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
            WHERE c1.column_name = @Columna and    c1.table_name = @Tabla) = 0 
                BEGIN
                    EXEC (@ConsultaAlter);
                    PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - OK. Tarea:' + @NroTarea;
                END
            ELSE 
                PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - YA EXISTE LA COLUMNA EN LA TABLA. Tarea:' + @NroTarea;   
 
--------------------
SET @NroTarea = '#';
SET @Columna = 'sn_pagina_web';/* Nombre de la columna a insertar.*/
SET @Tipo_Columna = 'varchar(1)Default(''S'')';/* Tipo de la columna a insertar.*/
SET @Tabla = 'segmento';/* Nombre de la tabla a modificar.*/    

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) = 0
        PRINT 'Tabla:'+@Tabla+' - NO EXISTE LA TABLA A LA QUE DESEA INSERTAR LA COLUMNA. Tarea:' + @NroTarea; 
    ELSE 
        SET @ConsultaAlter =
            'ALTER TABLE '+@Tabla+' ADD '+@Columna +' '+ @Tipo_Columna; 
        IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
            WHERE c1.column_name = @Columna and    c1.table_name = @Tabla) = 0 
                BEGIN
                    EXEC (@ConsultaAlter);
                    PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - OK. Tarea:' + @NroTarea;
                END
            ELSE 
                PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - YA EXISTE LA COLUMNA EN LA TABLA. Tarea:' + @NroTarea;

--------------------
SET @NroTarea = '#';
SET @Columna = 'sn_pagina_web';/* Nombre de la columna a insertar.*/
SET @Tipo_Columna = 'varchar(1)Default(''S'')';/* Tipo de la columna a insertar.*/
SET @Tabla = 'proveedores';/* Nombre de la tabla a modificar.*/    

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) = 0
        PRINT 'Tabla:'+@Tabla+' - NO EXISTE LA TABLA A LA QUE DESEA INSERTAR LA COLUMNA. Tarea:' + @NroTarea; 
    ELSE 
        SET @ConsultaAlter =
            'ALTER TABLE '+@Tabla+' ADD '+@Columna +' '+ @Tipo_Columna; 
        IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
            WHERE c1.column_name = @Columna and    c1.table_name = @Tabla) = 0 
                BEGIN
                    EXEC (@ConsultaAlter);
                    PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - OK. Tarea:' + @NroTarea;
                END
            ELSE 
                PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - YA EXISTE LA COLUMNA EN LA TABLA. Tarea:' + @NroTarea;
				
--------------------
SET @NroTarea = '#';
SET @Columna = 'sn_pagina_web';/* Nombre de la columna a insertar.*/
SET @Tipo_Columna = 'varchar(1)Default(''S'')';/* Tipo de la columna a insertar.*/
SET @Tabla = 'articulos';/* Nombre de la tabla a modificar.*/    

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) = 0
        PRINT 'Tabla:'+@Tabla+' - NO EXISTE LA TABLA A LA QUE DESEA INSERTAR LA COLUMNA. Tarea:' + @NroTarea; 
    ELSE 
        SET @ConsultaAlter =
            'ALTER TABLE '+@Tabla+' ADD '+@Columna +' '+ @Tipo_Columna; 
        IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
            WHERE c1.column_name = @Columna and    c1.table_name = @Tabla) = 0 
                BEGIN
                    EXEC (@ConsultaAlter);
                    PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - OK. Tarea:' + @NroTarea;
                END
            ELSE 
                PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - YA EXISTE LA COLUMNA EN LA TABLA. Tarea:' + @NroTarea;				
--------------------
SET @NroTarea = '#';
SET @Columna = 'ti_web_destacados';/* Nombre de la columna a insertar.*/
SET @Tipo_Columna = 'varchar(4)';/* Tipo de la columna a insertar.*/
SET @Tabla = 'articulos';/* Nombre de la tabla a modificar.*/    

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) = 0
        PRINT 'Tabla:'+@Tabla+' - NO EXISTE LA TABLA A LA QUE DESEA INSERTAR LA COLUMNA. Tarea:' + @NroTarea; 
    ELSE 
        SET @ConsultaAlter =
            'ALTER TABLE '+@Tabla+' ADD '+@Columna +' '+ @Tipo_Columna; 
        IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
            WHERE c1.column_name = @Columna and    c1.table_name = @Tabla) = 0 
                BEGIN
                    EXEC (@ConsultaAlter);
                    PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - OK. Tarea:' + @NroTarea;
                END
            ELSE 
                PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - YA EXISTE LA COLUMNA EN LA TABLA. Tarea:' + @NroTarea;				
	
--/* FIN ---  refs #*/

/* INICIO  ---  Tareas #48763- [PaginaWeb] 1. Entorno - Parámetros - Generales - Página Web: Crear parámetro para visualizar o no configuraciones de Página Web.*/        
DECLARE @NroTarea varchar(15); 
DECLARE @Columna varchar(50);           
DECLARE @Tipo_Columna varchar(50);         
DECLARE @Tabla varchar(50);             
DECLARE @ConsultaAlter varchar (5000);
DECLARE @Dato_Nuevo_Columna varchar(50);            
DECLARE @ConsultaUpdate varchar (500); 


--------------------
SET @NroTarea = '#48763';
SET @Columna = 'sn_pagina_web';--Nombre de la columna a insertar.    
SET @Tipo_Columna = 'varchar(1)';--Tipo de la columna a insertar.
SET @Tabla = 'parametros';--Nombre de la tabla a modificar.

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) = 0
        PRINT 'Tabla:'+@Tabla+' - NO EXISTE LA TABLA A LA QUE DESEA INSERTAR LA COLUMNA. Tarea:' + @NroTarea; 
    ELSE 
        SET @ConsultaAlter =
            'ALTER TABLE '+@Tabla+' ADD '+@Columna +' '+ @Tipo_Columna; 
        IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
            WHERE c1.column_name = @Columna and    c1.table_name = @Tabla) = 0 
                BEGIN
                    EXEC (@ConsultaAlter);
                    PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - OK. Tarea:' + @NroTarea;
                END
            ELSE 
                PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - YA EXISTE LA COLUMNA EN LA TABLA. Tarea:' + @NroTarea; 
 
--------------------   
SET @Dato_Nuevo_Columna =  char(39) + 'N' + CHAR(39) ;    /* Dato nuevo que va a contener la columna.*/       

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) = 0
        PRINT 'Tabla:'+@Tabla+' - NO EXISTE LA TABLA A LA QUE DESEA INSERTAR LA COLUMNA. Tarea:' + @NroTarea; 
    ELSE 
        SET @ConsultaUpdate = 'UPDATE ' + @Tabla + ' SET ' + @Columna + ' = ' + @Dato_Nuevo_Columna + ' WHERE ' + @columna + ' is null' ; 
        IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
            WHERE c1.column_name = @Columna and    c1.table_name = @Tabla) > 0 
                BEGIN
                    EXEC (@ConsultaUpdate);
                    PRINT 'Update Tabla:' + @Tabla + ', Columna:' + @Columna + ' - OK. Tarea:' + @NroTarea;
                END
            ELSE 
                PRINT 'Update Tabla:' + @Tabla + ', Columna:' + @Columna + ' - NO EXISTE LA COLUMNA EN LA TABLA. Tarea:' + @NroTarea; 
SET @NroTarea = '#48763';
SET @Columna = 'ti_busqueda_img_articulo';--Nombre de la columna a insertar.    
SET @Tipo_Columna = 'varchar(2)';--Tipo de la columna a insertar.
SET @Tabla = 'parametros';--Nombre de la tabla a modificar.

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) = 0
        PRINT 'Tabla:'+@Tabla+' - NO EXISTE LA TABLA A LA QUE DESEA INSERTAR LA COLUMNA. Tarea:' + @NroTarea; 
    ELSE 
        SET @ConsultaAlter =
            'ALTER TABLE '+@Tabla+' ADD '+@Columna +' '+ @Tipo_Columna; 
        IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
            WHERE c1.column_name = @Columna and    c1.table_name = @Tabla) = 0 
                BEGIN
                    EXEC (@ConsultaAlter);
                    PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - OK. Tarea:' + @NroTarea;
                END
            ELSE 
                PRINT 'Tabla:'+@Tabla+', Columna:'+@Columna+' - YA EXISTE LA COLUMNA EN LA TABLA. Tarea:' + @NroTarea; 
 
--------------------   
SET @Dato_Nuevo_Columna =  char(39) + 'CB' + CHAR(39) ;    /* Dato nuevo que va a contener la columna.*/       

IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
    WHERE c1.table_name = @Tabla) = 0
        PRINT 'Tabla:'+@Tabla+' - NO EXISTE LA TABLA A LA QUE DESEA INSERTAR LA COLUMNA. Tarea:' + @NroTarea; 
    ELSE 
        SET @ConsultaUpdate = 'UPDATE ' + @Tabla + ' SET ' + @Columna + ' = ' + @Dato_Nuevo_Columna + ' WHERE ' + @columna + ' is null' ; 
        IF (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS AS c1 
            WHERE c1.column_name = @Columna and    c1.table_name = @Tabla) > 0 
                BEGIN
                    EXEC (@ConsultaUpdate);
                    PRINT 'Update Tabla:' + @Tabla + ', Columna:' + @Columna + ' - OK. Tarea:' + @NroTarea;
                END
            ELSE 
                PRINT 'Update Tabla:' + @Tabla + ', Columna:' + @Columna + ' - NO EXISTE LA COLUMNA EN LA TABLA. Tarea:' + @NroTarea;     
/* FIN  ---  Tareas #48763- [PaginaWeb] 1. Entorno - Parámetros - Generales - Página Web: Crear parámetro para visualizar o no configuraciones de Página Web.*/  