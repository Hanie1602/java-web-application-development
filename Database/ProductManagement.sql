USE [master]
GO
/****** Object:  Database [ProductManagement]   *****/
CREATE DATABASE [ProductManagement]
GO

USE [ProductManagement]
GO

CREATE TABLE [dbo].[tblUsers](
	[userID] [nvarchar](50) NOT NULL,
	[fullName] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[roleID] [nvarchar](50) NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'admin', N'Toi la admin', N'1', N'AD', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'Hoadnt', N'Minh Thu', N'1', N'AD', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'SE170969', N'Nguyen Gia Tin', N'123', N'US', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'SE170443', N'LE MINH KHOA', N'1', N'US', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'A', N'BANH THI NO', N'1', N'US', 1)

CREATE TABLE [dbo].[tblProduct](
	[id] [char](5) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[description] [nvarchar](500) NOT NULL,
	[price] [float] NOT NULL,
	[size] [nvarchar](50) NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_tblFashion] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblProduct] ([id], [name], [description], [price], [size], [status]) VALUES (N'F-001', N'Ao dai', N'Ao dai co dien', 200,4, 1)
INSERT [dbo].[tblProduct] ([id], [name], [description], [price], [size], [status]) VALUES (N'F-002', N'Ao so mi nam', N'Ao so mi cong so', 300,5, 0)
