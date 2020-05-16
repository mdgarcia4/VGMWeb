CREATE TABLE [dbo].[usuario](
	[id] [bigint] NOT NULL,
	[activo] [bit] NULL,
	[clave] [varchar](255) NULL,
	[usuario] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[rol](
	[id] [bigint] NOT NULL,
	[rol] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[roles_usuarios](
	[id_usuario] [bigint] NOT NULL,
	[id_rol] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_usuario] ASC,
	[id_rol] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[roles_usuarios]  WITH CHECK ADD  CONSTRAINT [FKhray3l5w1m82hia6uyyka7cj8] FOREIGN KEY([id_rol])
REFERENCES [dbo].[rol] ([id])
GO

ALTER TABLE [dbo].[roles_usuarios] CHECK CONSTRAINT [FKhray3l5w1m82hia6uyyka7cj8]
GO

ALTER TABLE [dbo].[roles_usuarios]  WITH CHECK ADD  CONSTRAINT [FKsl9jx85ff1byy0x5q8ddwn0hu] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[usuario] ([id])
GO

ALTER TABLE [dbo].[roles_usuarios] CHECK CONSTRAINT [FKsl9jx85ff1byy0x5q8ddwn0hu]
GO

CREATE TABLE [dbo].[hibernate_sequence](
	[next_val] [bigint] NULL
) ON [PRIMARY]

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