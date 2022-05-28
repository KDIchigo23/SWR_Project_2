CREATE DATABASE [ok2]
GO

/****** Object:  Table [dbo].[Account]    Script Date: 04/18/2022 21:31:42 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Account](
	[userID] [varchar](20) NOT NULL,
	[password] [int] NOT NULL,
	[fullName] [nvarchar](50) NOT NULL,
	[roleID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

USE [ok2]
GO

/****** Object:  Table [dbo].[Book]    Script Date: 04/18/2022 21:32:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Book](
	[bookId] [varchar](10) NOT NULL,
	[description] [nvarchar](250) NOT NULL,
	[price] [float] NULL,
	[bookName] [nvarchar](250) NOT NULL,
	[author] [nvarchar](250) NOT NULL,
	[yearOfProduction] [int] NULL,
	[quantity] [int] NULL,
	[bookImgURL] [varchar](50) NOT NULL,
	[categoryID] [nvarchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[bookId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[Book]  WITH CHECK ADD FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([categoryID])
GO

USE [ok2]
GO

/****** Object:  Table [dbo].[Category]    Script Date: 04/18/2022 21:33:11 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Category](
	[categoryID] [nvarchar](10) NOT NULL,
	[categoryName] [varchar](250) NULL,
PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

USE [ok2]
GO

/****** Object:  Table [dbo].[Payment]    Script Date: 04/18/2022 21:33:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Payment](
	[paymentId] [int] IDENTITY(1,1) NOT NULL,
	[userID] [varchar](20) NOT NULL,
	[dateCreate] [date] NOT NULL,
	[totalPayment] [float] NOT NULL,
	[address] [nvarchar](50) NOT NULL,
	[phoneNumber] [varchar](20) NOT NULL,
	[status] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[paymentId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[Payment]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[Account] ([userID])
GO


USE [ok2]
GO

/****** Object:  Table [dbo].[PaymentDetail]    Script Date: 04/18/2022 21:34:17 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[PaymentDetail](
	[paymentId] [int] NOT NULL,
	[bookId] [varchar](10) NOT NULL,
	[quantity] [int] NOT NULL,
	[subTotal] [float] NOT NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[PaymentDetail]  WITH CHECK ADD FOREIGN KEY([bookId])
REFERENCES [dbo].[Book] ([bookId])
GO

ALTER TABLE [dbo].[PaymentDetail]  WITH CHECK ADD FOREIGN KEY([paymentId])
REFERENCES [dbo].[Payment] ([paymentId])
GO







