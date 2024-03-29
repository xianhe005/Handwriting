----------ISNULL使用
select null + 1 --NULL
select ISNULL(null + 1, 1) --1
select ISNULL(null + 1, 2) --2
select ISNULL(0, 2) --0
--ISNULL(Defect,'') = '' 等价于 Defect = '' OR Defect IS NULL
--ISNULL(Defect,''),表示如果Defect不为NULL时返回Defect(Defect为''时也表示不为NULL),否则返回第二个参数(这里表示'')

-----------DECLARE、CONVERT、CAST、DATEADD、ROUND、DATEDIFF
declare @tt VARCHAR(1000) = CONVERT(VARCHAR(100), GETDATE(), 23) + ' ' + CONVERT(VARCHAR(12), '08:30:00.0000000', 8)
select @tt --GETDATE()是当前时间，返回示例仅在当时运行的结果
--result:
--(无列名)
--2019-07-17 08:30:00.000
select cast(@tt as DATETIME) as varchartodatetime
--result:
--varchartodatetime
--2019-07-17 08:30:00.000
select convert(VARCHAR(11), GETDATE(), 120) + CONVERT(VARCHAR(12), '06:00:00.0000000')
--result:
--(无列名)
--2019-07-17 06:00:00.000
select convert(VARCHAR(7), GETDATE(), 120) + '-01'
--result:
--(无列名)
--2019-07-01
select DATEADD(mi, 510, GETDATE()) --510分以后
--result:
--(无列名)
--2019-07-17 19:25:51.500
select DATEADD(mi, 8.5 * 60, GETDATE()) --510分以后
--result:
--(无列名)
--2019-07-17 19:25:51.500
select DATEADD(hh, 8.5, GETDATE()) --8.5小时以后
--result:
--(无列名)
--2019-07-17 18:55:51.500
select DATEADD(hh, 8, GETDATE()) --8小时以后
--result:
--(无列名)
--2019-07-17 18:55:51.500
select convert(TIME, '08:30:00')
--result:
--(无列名)
--08:30:00.0000000
select convert(VARCHAR(100), GETDATE(), 20) AS now
--result:
--now
--2019-07-17 11:02:06
declare @t DATETIME = GETDATE()
select @t
--result:
--(无列名)
--2019-07-17 11:02:52.033
select DATEADD(hh,2,@t)
--result:
--(无列名)
--2019-07-17 13:02:52.033
DECLARE @s BIGINT = DATEDIFF(s, '2019-07-15 11:08:50.960', GETDATE())
select round((@s / CAST(3600 AS FLOAT)), 4)
--result:
--(无列名)
--47.9153
SELECT DATEADD(day,2,'2008-12-29 16:25:46.635')
--result:
--(无列名)
--2008-12-31 16:25:46.637
SELECT GETDATE()
--result:
--(无列名)
--2019-07-17 11:05:10.657
SELECT DATEADD(hh,2,GETDATE())
--result:
--(无列名)
--2019-07-17 13:05:10.657
SELECT DATEADD(hh,-48,GETDATE())
--result:
--(无列名)
--2019-07-15 11:05:10.657
SELECT DATEADD(day,-2,GETDATE())
--result:
--(无列名)
--2019-07-15 11:05:10.657
SELECT DATEDIFF(hh, '2019-07-15 11:08:50.960', GETDATE())
--result:
--(无列名)
--48
SELECT DATEDIFF(s, '2019-07-15 11:08:50.960', GETDATE())
--result:
--(无列名)
--172580

------------FOR XML PATH、split、STUFF
SELECT *
		FROM dbo.f_split('a45e841b-88a6-4027-83a1-dccc39b8de74,178,177', ',') a
--dbo.f_split see file:dbo.f_split.sql
--result:
--col
--a45e841b-88a6-4027-83a1-dccc39b8de74
--178
--177
SELECT ',' + a.col
		FROM dbo.f_split('a45e841b-88a6-4027-83a1-dccc39b8de74,178,177', ',') a
		FOR XML PATH('')
--result:
--XML_F52E2B61-18A1-11d1-B105-00805F49916B
--,a45e841b-88a6-4027-83a1-dccc39b8de74,178,177
SELECT STUFF((
		SELECT ',' + a.col
		FROM dbo.f_split('a45e841b-88a6-4027-83a1-dccc39b8de74,178,177', ',') a
			--JOIN dbo.t_User b ON a.col = b.ID
		FOR XML PATH('')
	), 1, 1, '') AS COL
--result:
--COL
--a45e841b-88a6-4027-83a1-dccc39b8de74,178,177
SELECT '' + a.col
		FROM dbo.f_split('a45e841b-88a6-4027-83a1-dccc39b8de74,178,177', ',') a
--result
--(无列名)
--a45e841b-88a6-4027-83a1-dccc39b8de74
--178
--177
SELECT '' + a.col
		FROM dbo.f_split('a45e841b-88a6-4027-83a1-dccc39b8de74,178,177', ',') a
		FOR XML PATH('TAG')
--result
--XML_F52E2B61-18A1-11d1-B105-00805F49916B
--<TAG>a45e841b-88a6-4027-83a1-dccc39b8de74</TAG><TAG>178</TAG><TAG>177</TAG>
SELECT a.col
		FROM dbo.f_split('a45e841b-88a6-4027-83a1-dccc39b8de74,178,177', ',') a
--result
--col
--a45e841b-88a6-4027-83a1-dccc39b8de74
--178
--177
SELECT a.col
		FROM dbo.f_split('a45e841b-88a6-4027-83a1-dccc39b8de74,178,177', ',') a
		FOR XML PATH('')
--result
--XML_F52E2B61-18A1-11d1-B105-00805F49916B
--<col>a45e841b-88a6-4027-83a1-dccc39b8de74</col><col>178</col><col>177</col>
SELECT a.col
		FROM dbo.f_split('a45e841b-88a6-4027-83a1-dccc39b8de74,178,177', ',') a
		FOR XML PATH('TAG')
--result
--XML_F52E2B61-18A1-11d1-B105-00805F49916B
--<TAG><col>a45e841b-88a6-4027-83a1-dccc39b8de74</col></TAG><TAG><col>178</col></TAG><TAG><col>177</col></TAG>

---------------REPLACE、IndexOf
SELECT REPLACE('SW_胎侧:81_光检杂质, ', '_', ':')
--result:
--(无列名)
--SW:胎侧:81:光检杂质,
SELECT dbo.IndexOf('SW_胎侧:81_光检杂质, ', '_', 1)
--dbo.IndexOf see file: dbo.IndexOf.sql
--result:
--(无列名)
--3
SELECT dbo.IndexOf('SW_胎侧:81_光检杂质, ', '_', 2)
--result:
--(无列名)
--9
SELECT dbo.IndexOf('SW_胎侧:81_光检杂质, ', '_', 3)
--result:
--(无列名)
--0

------------BEGIN、END、SUBSTRING综合应用、列名重命名
begin
	declare @str VARCHAR(100) = 'SW_胎侧:81_光检杂质,'

	select replace(@str, '_', ':') AS '@str2'

	select dbo.IndexOf(replace(@str, '_', ':'), ':', 1) + 1 AS "dbo.IndexOf(REPLACE(@str, '_', ':'), ':', 1) + 1"

	select dbo.IndexOf(replace(@str, '_', ':'), ':', 2) AS "dbo.IndexOf(REPLACE(@str, '_', ':'), ':', 2)"

	select dbo.IndexOf(replace(@str, '_', ':'), ':', 1) AS "dbo.IndexOf(REPLACE(@str, '_', ':'), ':', 1)"

	select SUBSTRING('SW:胎侧:81:光检杂质,', 4, 6 - 3 - 1)

	select SUBSTRING(replace(@str, '_', ':'), dbo.IndexOf(REPLACE(@str, '_', ':'), ':', 1) + 1, dbo.IndexOf(REPLACE(@str, '_', ':'), ':', 2) - dbo.IndexOf(REPLACE(@str, '_', ':'), ':', 1) - 1)
end
--result:
--@str2
--SW:胎侧:81:光检杂质,
------
--dbo.IndexOf(REPLACE(@str, '_', ':'), ':', 1) + 1
--4
------
--dbo.IndexOf(REPLACE(@str, '_', ':'), ':', 2)
--6
------
--dbo.IndexOf(REPLACE(@str, '_', ':'), ':', 1)
--3
------
--(无列名)
--胎侧
------
--(无列名)
--胎侧

-----------------CHARINDEX
select CHARINDEX('(', '1(', 0)
--result:
--(无列名)
--2
select CHARINDEX('(', '1((', 0)
--result:
--(无列名)
--2
select CHARINDEX('(', '1((', -1)
--result:
--(无列名)
--2
select CHARINDEX('(', '1((')
--result:
--(无列名)
--2
select CHARINDEX('(', '1', 0)
--result:
--(无列名)
--0
select CHARINDEX('(', '1((', 2)
--result:
--(无列名)
--2
select CHARINDEX('(', '1((', 3)
--result:
--(无列名)
--3
select CHARINDEX('(', '1((', 4)
--result:
--(无列名)
--0

-------------CASE WHEN ELSE END
declare @IsQuanlified INT
set @IsQuanlified = 4
select case
            when @IsQuanlified = 0 THEN '适中'
            WHEN @IsQuanlified = 1 THEN '偏轻'
            WHEN @IsQuanlified = 2 THEN '偏重'
            ELSE '其他'
        end AS Quanlifie
--result:
--Quanlifie
--其他

----------两结果集是否一样判断
SELECT ID, Defect
FROM (
	SELECT TOP 1000 ID, Defect
	FROM t_InspectionRecord
	UNION ALL
	SELECT TOP 1000 ID, Defect
	FROM t_InspectionRecord
) AS t
GROUP BY ID, Defect
HAVING COUNT(*) < 2 --如果结果集为NULL(无数据)，就可以判断该结果集是一致的。。。

--------------模糊查询'_'需转义处理
LIKE '%\_%' ESCAPE '\'

--------------REVERSE,SQL中的类似JAVA中LastIndexOf,截取最后一次出现字符后面的字符
begin
	declare @str VARCHAR(100)
	set @str = 'SW:胎侧:81:光检杂质,'
	select REVERSE(@str)
	select CHARINDEX(':',REVERSE(@str)) - 1
	select SUBSTRING(REVERSE(@str),1,CHARINDEX(':',REVERSE(@str)) - 1)
	select REVERSE(SUBSTRING(REVERSE(@str),1,CHARINDEX(':',REVERSE(@str)) - 1))
end
--result
--(无列名)
--,质杂检光:18:侧胎:WS
------
--(无列名)
--5
------
--(无列名)
--,质杂检光
-------
--(无列名)
--光检杂质,

--------------循环拼接、EXEC
begin
	declare @w VARCHAR(1000) = '';
	declare @min INT = 10;
	declare @max INT = 52;
	declare @num INT = @min;
	while @num <= @max
	begin
		if(@num =15)
		begin
			set @num = @num + 1;
			CONTINUE;
		end
		set @w = @w + '''' + CONVERT(VARCHAR, @num) + ''''; --' 转义
		if(@num < @max) set @w = @w + ', ';
		set @num = @num + 1;
	end
	select @w;

	select * from t_DefectItem where CODE in ( @w ) --这个写法有问题，怎么解决？用下面的EXEC解决

	declare @sql NVARCHAR(1000)
	set @sql = 'SELECT * FROM t_DefectItem WHERE CODE IN ( ' + @w + ' )'
	select @sql AS [SQL]
	EXEC(@sql)
	select * from t_DefectItem where CODE in ('10', '11', '12', '13', '14', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', '40', '41', '42', '43', '44', '45', '46', '47', '48', '49', '50', '51', '52')
end
--result
--(无列名)
--'10', '11', '12', '13', '14', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', '40', '41', '42', '43', '44', '45', '46', '47', '48', '49', '50', '51', '52'
-------
--NULL
-------
--SQL
--SELECT * FROM t_DefectItem WHERE CODE IN ( '10', '11', '12', '13', '14', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', '40', '41', '42', '43', '44', '45', '46', '47', '48', '49', '50', '51', '52' )
-------
--Code	Name	Type	InspectionType
--10	缺胶	2	1
--11	裂口	2	1
--12	接头开	2	1
--13	露线	2	1
--14	气泡	2	1
--16	重皮	2	1
--17	实鼓	2	1
--18	鼓包	2	1
--19	脱空	2	1
--20	圆角	2	1
--21	趾口胶片超厚	2	1
--22	趾口窄	2	1
--23	趾口上抽	2	1
--24	条码缺陷	1	1
--25	豁口	2	1
--26	胎里不平	2	1
--27	出台	2	1
--28	模具胶边或错缝	2	1
--29	窝气	2	1
--30	欠硫	2	1
--31	变形	2	1
--32	异常胎	2	1
--33	胶囊疤	2	1
--34	胶囊曲	2	1
--35	胶囊漏	2	1
--36	模具出错	2	1
--37	机械损伤	2	1
--38	进错胎胚	2	1
--39	熟胶皮	2	1
--40	杂质	2	1
--41	外检其他	2	1
--42	粘胶囊	2	1
--43	轮胎表面不洁	2	1
--44	标识不全或错误	2	1
--45	局部多胶	2	1
--46	缺少刀槽花纹	2	1
--47	胶囊老化缺陷	2	1
--48	钢丝刺出	2	1
--49	红卡胎	2	1
--50	试验胎	2	1
--51	小辫子	2	1
--52	DOT周期牌错误	2	1
------
--结果同上

-----------ALTER TABLE ADD COL:表新增列
alter table t_DefectItem add InspectionType TINYINT

-----------ROW_NUMBER OVER (ORDER BY )、BETWEEN AND(主要用于分页)、COUNT、CEILING
select a.col, row_number() over (order by a.col desc) as [rank]
		from dbo.f_split('16,178,144,177', ',') a
--result
--col	rank
--178	1
--177	2
--16	3
--144	4
select * from
	(select a.col, row_number() over (order by a.col desc) as [rank]
		from dbo.f_split('16,178,144,177', ',') a ) AS b
	WHERE b.rank BETWEEN 2 AND 4
--result
--col	rank
--177	2
--16	3
--144	4
select a.col, row_number() over (order by a.col desc) as [rank], count(1) over() as totalCount
		from dbo.f_split('16,178,144,177', ',') a
--result
--col	rank	totalCount
--178	1	4
--177	2	4
--16	3	4
--144	4	4
select cast(CEILING(100 / 3.0) as int) as totalPage --总页数

--分页举例，使用count(1) over()获取总记录数量
begin
	declare @t TABLE(name VARCHAR(MAX), age INT)
	insert @t VALUES('a0', 20)
	insert @t VALUES('a1', 10)
	insert @t VALUES('a2', 30)
	insert @t VALUES('a3', 50)
	insert @t VALUES('a4', 40)

	select * from @t

	select *, row_number() over(order by age) as [rank], count(1) over() as totalCount from @t

	declare @curPage INT = 2 --当前页
	declare @pageCount INT = 3 --每页数量
	select * ,
	cast(CEILING(t.totalCount / cast(@pageCount as FLOAT)) AS INT) AS totalPage --总页数
	--,CASE WHEN @curPage * @pageCount > t.totalCount THEN t.totalCount ELSE @curPage * @pageCount END AS test
	from (
		select *, row_number() over(order by age) as [rank], count(1) over() as totalCount --总条数
		from @t
	) as t
	where t.[rank] BETWEEN ((@curPage - 1) * @pageCount + 1) AND
		case when @curPage * @pageCount > t.totalCount then t.totalCount else @curPage * @pageCount end
end

-----------定义数据表、不等于[x]条件的写法(ISNULL)
begin
	declare @t TABLE(col VARCHAR(50))
	insert @t(col) VALUES ('a')
	insert @t(col) VALUES ('b')
	insert @t(col) VALUES (null)
	insert @t(col) VALUES ('c')
	select * from @t
	select * from @t AS b where b.col is null
	select * from @t AS b where b.col <> 'a' --不等于'a'的错误写法
	select * from @t AS b where ISNULL(b.col, '') <> 'a' --不等于'a'的正确写法,只要ISNULL第二个参数不等于'a'即可
end
--result
--col
--a
--b
--NULL
--c
-------
--col
--NULL
-------
--col
--b
--c
-------
--col
--b
--NULL
--c
------------
begin
	declare @t2 TABLE(col BIGINT)
	insert @t2(col) VALUES (1)
	insert @t2(col) VALUES (3)
	insert @t2(col) VALUES (null)
	insert @t2(col) VALUES (2)
	select * from @t2
	select * from @t2 AS b where b.col is null
	select * from @t2 AS b where b.col <> 3 --不等于3的错误写法
	select * from @t2 AS b where ISNULL(b.col, 0) <> 3 --不等于3的正确写法,只要ISNULL第二个参数不等于3即可
end
--result
--col
--1
--3
--NULL
--2
--------
--col
--NULL
--------
--col
--1
--2
--------
--col
--1
--NULL
--2

------------------NOT与ISNULL组合的正确用法
select * from dbo.f_split('a,b,c,d', ',') a
--result
--col
--a
--b
--c
--d
select * from (select * from dbo.f_split('a,b,c,d', ',')) a WHERE a.col = 'a' OR a.col = 'c'
--result
--col
--a
--c
select * from (select * from dbo.f_split('a,b,c,d', ',')) a WHERE NOT (a.col = 'a' OR a.col = 'c')
--result
--col
--b
--d

begin
	declare @t3 TABLE(col VARCHAR(50))
	insert @t3(col) VALUES ('a')
	insert @t3(col) VALUES ('b')
	insert @t3(col) VALUES (null)
	insert @t3(col) VALUES ('c')
	insert @t3(col) VALUES ('d')
	select * from @t3
	select * from @t3 AS b where ISNULL(b.col, '') <> 'a' and ISNULL(b.col, '') <> 'c'
	select * from @t3 AS b where not (ISNULL(b.col, '') <> 'a' and ISNULL(b.col, '') <> 'c')
	select * from @t3 AS b where not not (ISNULL(b.col, '') <> 'a' and ISNULL(b.col, '') <> 'c')
	select * from @t3 AS b where not b.col = 'a' --这里取反NULL被丢失了,有问题
	select * from @t3 AS b where not ISNULL(b.col, '') = 'a' --这样处理才对
end
--result
--col
--a
--b
--NULL
--c
--d
---------
--col
--b
--NULL
--d
---------
--col
--a
--c
---------
--col
--b
--NULL
--d
---------
--col
--b
--c
--d
---------
--col
--b
--NULL
--c
--d

--------------------IDENTITY(1,1) PRIMARY KEY、ROW_NUMBER() OVER (PARTITION BY [x] ORDER BY(分组排序生成分组内行号)
begin
	declare @t4 TABLE(ID INT IDENTITY(1,1) PRIMARY KEY, name VARCHAR(50), [date] DATETIME)
	insert @t4 (name, [date]) VALUES ('c', CAST('2019-01-01 10:00' AS DATETIME))
	insert @t4 (name, [date]) VALUES ('b', CAST('2019-01-01 12:00' AS DATETIME))
	insert @t4 (name, [date]) VALUES ('a', CAST('2019-01-01 11:00' AS DATETIME))
	insert @t4 (name, [date]) VALUES ('c', CAST('2019-01-01 13:10' AS DATETIME))
	insert @t4 (name, [date]) VALUES ('b', CAST('2019-01-01 09:00' AS DATETIME))
	select * from @t4

	select b.ID, row_number() over (partition by b.name order by [date] DESC) as num, b.name, b.[date]
		from @t4 AS b

	select * from (select b.ID, row_number() over (partition by b.name order by [date] DESC) as num, b.name, b.[date]
		FROM @t4 as b) AS c
	where c.num = 1
	order by name desc
end
--result
--ID	name	date
--1	c	2019-01-01 10:00:00.000
--2	b	2019-01-01 12:00:00.000
--3	a	2019-01-01 11:00:00.000
--4	c	2019-01-01 13:10:00.000
--5	b	2019-01-01 09:00:00.000
------------
--ID	num	name	date
--3	1	a	2019-01-01 11:00:00.000
--2	1	b	2019-01-01 12:00:00.000
--5	2	b	2019-01-01 09:00:00.000
--4	1	c	2019-01-01 13:10:00.000
--1	2	c	2019-01-01 10:00:00.000
------------
--ID	num	name	date
--4	1	c	2019-01-01 13:10:00.000
--2	1	b	2019-01-01 12:00:00.000
--3	1	a	2019-01-01 11:00:00.000

-----------------子查询 ORDER BY
begin
	declare @t4 TABLE(ID INT IDENTITY(1,1) PRIMARY KEY, name VARCHAR(50), [date] DATETIME)
	insert @t4 (name, [date]) VALUES ('c', CAST('2019-01-01 10:00' AS DATETIME))
	insert @t4 (name, [date]) VALUES ('b', CAST('2019-01-01 12:00' AS DATETIME))
	insert @t4 (name, [date]) VALUES ('a', CAST('2019-01-01 11:00' AS DATETIME))
	insert @t4 (name, [date]) VALUES ('c', CAST('2019-01-01 13:10' AS DATETIME))
	insert @t4 (name, [date]) VALUES ('b', CAST('2019-01-01 09:00' AS DATETIME))
	select * from @t4

	declare @t5 TABLE(ID INT IDENTITY(1,1) PRIMARY KEY, TName VARCHAR(50), [date] DATETIME)
	insert @t5 (TName, [date]) VALUES ('a', CAST('2019-01-01 11:00' AS DATETIME))
	insert @t5 (TName, [date]) VALUES ('b', CAST('2019-01-01 12:00' AS DATETIME))
	insert @t5 (TName, [date]) VALUES ('c', CAST('2019-01-01 13:00' AS DATETIME))
	insert @t5 (TName, [date]) VALUES ('d', CAST('2019-01-01 14:00' AS DATETIME))
	insert @t5 (TName, [date]) VALUES ('e', CAST('2019-01-01 15:00' AS DATETIME))
	select * from @t5

	select TOP(5) b.name from @t4 AS b order by b.name

	--TOP(N)的形式
	select TOP(select count(*) from @t4) b.name FROM @t4 AS b order by b.name

	--下面报如下错误
	--除非另外还指定了 TOP 或 FOR XML，否则，ORDER BY 子句在视图、内联函数、派生表、子查询和公用表表达式中无效。
	--SELECT * FROM @t5 AS a WHERE a.TName IN (
	--	SELECT b.name FROM @t4 AS b ORDER BY b.name
	--)

	--TOP(N)的形式来解决上面ORDER BY子查询问题
	select * from @t5 AS a where a.TName in (
		select TOP(select count(*) from @t4) b.name FROM @t4 AS b order by b.name
	)
end
--result
--ID	name	date
--1	c	2019-01-01 10:00:00.000
--2	b	2019-01-01 12:00:00.000
--3	a	2019-01-01 11:00:00.000
--4	c	2019-01-01 13:10:00.000
--5	b	2019-01-01 09:00:00.000
--------
--ID	TName	date
--1	a	2019-01-01 11:00:00.000
--2	b	2019-01-01 12:00:00.000
--3	c	2019-01-01 13:00:00.000
--4	d	2019-01-01 14:00:00.000
--5	e	2019-01-01 15:00:00.000
--------
--name
--a
--b
--b
--c
--c
--------
--name
--a
--b
--b
--c
--c
--------
--ID	TName	date
--1	a	2019-01-01 11:00:00.000
--2	b	2019-01-01 12:00:00.000
--3	c	2019-01-01 13:00:00.000

-----------------------查询表结构信息
select * from information_schema.columns where table_name='t_Shift'

-----------------------FUNCTION
USE [GYLTC]
GO
/****** Object:  UserDefinedFunction [dbo].[f_split_GetDefect]    Script Date: 2019/7/25 14:24:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
------------------------------
--函数：拆分检测缺陷类型
--创建人XXX
--创建时间：2019-04-22


alter function [dbo].[f_split_GetDefect]
    (
      @SourceSql NVARCHAR(MAX)
    )
RETURNS table
as

     return          select   ( select TOP 1
                    col
          from      f_split(t.col, ' ')
        ) as DefectLocation  ,
        ( select TOP 1
                    col
          FROM      (select * from f_split(t.col, ' ') c where c.col!='' ) as d
          WHERE     col NOT IN ( select TOP 1
                                        col
                                 FROM   f_split(t.col, ' ')  )
        ) AS DefectCause
FROM    ( select    *
          from      f_split(@SourceSql, ',')
        ) AS t
WHERE   t.col != ''
----------------------------上面为表值函数

----------------------------下面为分析
--f_split(@SourceSql, ',')分解：
--a et v
--b d
--c f
declare @SourceSql NVARCHAR(MAX) = 'a et v,b d,c f'
select * from f_split(@SourceSql, ',')
select   ( select TOP 1
                    col
          from      f_split(t.col, ' ')
        ) as DefectLocation  ,
        ( select TOP 1
                    col
          from      (select * from f_split(t.col, ' ') c where c.col!='' ) as d
          WHERE     col NOT IN ( select TOP 1
                                        col
                                 from   f_split(t.col, ' ')  )
        ) AS DefectCause,
		(select TOP 1 col from   f_split(t.col, ' ')) AS TEST1,
		t.col AS TEST2
FROM    ( select    *
          from      f_split(@SourceSql, ',')
        ) AS t
WHERE   t.col != ''
--最终结果
--DefectLocation	DefectCause	TEST1	TEST2
--a	et	a	a et v
--b	d	b	b d
--c	f	c	c f

--------------------SUM GROUP BY(例子：统计教师星期几的上课节数)
begin
	declare @t TABLE(tn INT, wn INT)
	insert @t (tn, wn) VALUES (1, 2)
	insert @t (tn, wn) VALUES (1, 3)
	insert @t (tn, wn) VALUES (2, 1)
	insert @t (tn, wn) VALUES (3, 2)
	insert @t (tn, wn) VALUES (1, 2)

	select * from @t

	select t.tn '教师号',t.wn '星期号', ('有') '是否有课' from (select * from @t) t

	select t.tn,
		case when t.wn=1 then 1 else 0 end as 星期一,
		case when t.wn=2 then 1 else 0 end as 星期二,
		case when t.wn=3 then 1 else 0 end as 星期三
		from @t t

	select s.tn as 教师号 ,sum(s.星期一) as 星期一,sum(s.星期二) as 星期二,sum(s.星期三) as 星期三
		from (select t.tn,
			case when t.wn=1 then 1 else 0 end as 星期一,
			case when t.wn=2 then 1 else 0 end as 星期二,
			case when t.wn=3 then 1 else 0 end as 星期三
			from @t t
		) s
	group by s.tn

	select k.教师号,
		case when k.星期一>0 then cast(k.星期一 as varchar) else '' end as 星期一 ,
		case when k.星期二>0 then cast(k.星期二 as varchar) else '' end as 星期二 ,
		case when k.星期三>0 then cast(k.星期三 as varchar) else '' end as 星期三
	from (
		select s.tn as 教师号 ,sum(s.星期一) as 星期一,sum(s.星期二) as 星期二,sum(s.星期三) as 星期三
			from (select t.tn,
				case when t.wn=1 then 1 else 0 end as 星期一,
				case when t.wn=2 then 1 else 0 end as 星期二,
				case when t.wn=3 then 1 else 0 end as 星期三
				from @t t
			) s
		group by s.tn
	) k order by k.教师号
end
--result
--tn	wn
--1	    2
--1	    3
--2	    1
--3	    2
--1	    2
----------
--教师号	星期号	是否有课
--1	    2	    有
--1	    3	    有
--2	    1	    有
--3	    2	    有
--1	    2	    有
----------
--tn	星期一	星期二	星期三
--1	    0	    1	    0
--1	    0	    0	    1
--2	    1	    0	    0
--3	    0	    1	    0
--1	    0	    1	    0
----------
--教师号	星期一	星期二	星期三
--1	    0	    2	    1
--2	    1	    0	    0
--3	    0	    1	    0
----------
--教师号	星期一	星期二	星期三
--1	    	    2	    1
--2	    1
--3		        1

--------------------查询ID是否在形如:('1,3,4,7~11')这样的范围的数据
--1.先建一表值函数
--函数说明：从字符串@c(其中各部分用@split分隔,如果某部分是范围,则此部分又由@split2分隔)中查找@target是否包含在其中
--返回：1:表示在该范围内,0:表示不在该范围内
--举例：[dbo].[contains2]('3~5,11,13~16,18', ',', '~', '5') 返回:1
--举例：[dbo].[contains2]('3~5,11,13~16,18', ',', '~', '11') 返回:1
--举例：[dbo].[contains2]('3~5,11,13~16,18', ',', '~', '17') 返回:0
USE [MyTestDB] -- use DB
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create function [dbo].[contains2](@c VARCHAR(MAX),@split VARCHAR(2),@split2 VARCHAR(2),@target VARCHAR(MAX))
returns INT
as
	begin
		declare @r INT = 0
		declare @splitStr VARCHAR(MAX)
		declare @min INT
		declare @max INT
		if CHARINDEX(@split2, @c) = 0 AND CHARINDEX(@target, @c) = 0
			begin
				set @r = 0
				return @r
			END
		ELSE
			begin
				if @c = @target
					begin
						set @r = 1
						return @r
					END
				ELSE
					begin
						while @c <> ''
							begin
								set @splitStr = SUBSTRING(@c, 1, case when CHARINDEX(@split, @c) = 0 THEN LEN(@c)
									else CHARINDEX(@split, @c)- LEN(@split) end)
								--SELECT @c AS [@c]
								--SELECT @splitStr AS [@splitStr]
								if @splitStr = @target
									begin
										set @r = 1
										set @c = ''
										return @r
									END
								ELSE if CHARINDEX(@split2, @splitStr) <> 0
									begin
										set @min = CAST(SUBSTRING(@splitStr, 1, CHARINDEX(@split2, @splitStr) - LEN(@split2)) AS INT)
										set @max = CAST(SUBSTRING(@splitStr, CHARINDEX(@split2, @splitStr) + LEN(@split2), LEN(@splitStr)) AS INT)
										--SELECT @min AS [@min]
										--SELECT @max AS [@max]
										if @min <= CAST(@target AS INT) AND @max >= CAST(@target AS INT)
											begin
												set @r = 1
												set @c = ''
												return @r
											END
										ELSE
											begin
												if CHARINDEX(@split,@c)<> 0
													set @c = STUFF(@c,1,CHARINDEX(@split,@c),'')
												ELSE
													set @c = ''
											END
									END
								ELSE
									begin
										if CHARINDEX(@split,@c)<> 0
											set @c = STUFF(@c,1,CHARINDEX(@split,@c),'')
										ELSE
											set @c = ''
									END
							END
					END
			END
		return @r
	END
GO
--2.使用
declare @v VARCHAR(MAX) = '21' --要查找的id

declare @t TABLE(name VARCHAR(MAX), ids VARCHAR(MAX))
insert @t VALUES('a0', '1,2,6~9')
insert @t VALUES('a1', '3~5,11,13~16,18')
insert @t VALUES('a2', '20~22,24,25,30')
select * from @t AS t where [dbo].[contains2](t.ids, ',', '~', @v) = 1
--result
--name	ids
--a2	20~22,24,25,30
