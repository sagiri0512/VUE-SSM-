# VUE和SSM超市销售
前端使用VUE 后端SSM 数据库SQL 的项目

后端使用的idea的maven项目 使用Tomcat代理的8888端口
前端使用VUE CLI创建的VUE3项目 端口3000 使用npm run dev命令运行

关于数据库插入
如果firstjob.sql文件不能成功插入，是因为其中含有外键，而firstjob.sql中新建表的顺序不同，我的表中含有数据，会找不到对应的外键数据。
遇到这个情况，请下载sql.zip。
