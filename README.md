# Mode-of-Web-Page-Tamper-resistant
信息安全专业毕业设计
网页防篡改模型

使用：
将bsxt下载到D盘下，便可以直接运行。

本模型包括：

水印检测模块:ScanMode 配置文件：prop1.properties
本模块可以扫描Test文件夹下的文件，并检测他们是否被修改。

文件恢复模块：BackupsMode 
改模块仅以D:\bsxt\Test\新建文本文档.txt 为网页源文件。D:\bsxt\backups\新建文本文档.txt 为其恢复文件。

过滤器模块：FilterMode 用于模拟过滤用户请求。
模型较为简单，仅对字符串中包含z1的消息进行阻断

告警模块：EmailMode 用于在检测模块检测到告警后发送邮件。
使用时需要配置自己的邮箱(禁止使用作者邮箱)。
