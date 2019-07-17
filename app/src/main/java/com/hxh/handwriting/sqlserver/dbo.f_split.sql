USE [GYLTC]
GO
/****** Object:  UserDefinedFunction [dbo].[f_split]    Script Date: 2019/7/17 11:06:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER FUNCTION [dbo].[f_split](@c VARCHAR(2000),@split VARCHAR(2))
returns @t TABLE(col VARCHAR(50))
AS
    BEGIN

      WHILE(charindex(@split,@c)<>0)
        BEGIN
          INSERT @t(col) VALUES (SUBSTRING(@c,1,charindex(@split,@c)-1))
          SET @c = stuff(@c,1,charindex(@split,@c),'')
        END
      INSERT @t(col) VALUES (@c)
      RETURN
    END
------------------这行及下面为添加的注释----------
--表值函数dbo.f_split
--字符串分隔成表，列名为col