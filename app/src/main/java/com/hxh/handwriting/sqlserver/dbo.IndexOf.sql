USE [GYLTC]
GO
/****** Object:  UserDefinedFunction [dbo].[IndexOf]    Script Date: 2019/7/17 11:23:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER FUNCTION [dbo].[IndexOf](@str VARCHAR(500),@value VARCHAR(50),@posIndex INT)
RETURNS int AS
BEGIN
DECLARE @pos int=0	--记录位置
DECLARE @i INT =0	--记录查找的次数
WHILE(@i<@posindex)
BEGIN
SET @i=@i+1
set @pos=CHARINDEX(@value,@str,@pos+1)
IF(@pos=0) RETURN 0
END
RETURN	@pos
END
------------------这行及下面为添加的注释----------
--表值函数dbo.IndexOf
--[value]在[str]中出现第[posIndex]次的索引,没有找到返回为0