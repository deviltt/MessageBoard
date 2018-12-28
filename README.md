# MessageBoard

1.创建一个数据库操作的工作类（创建、释放数据库连接的工具类），返回connection连接，并且提供释放ResultSet,Statement,connection资源的静态方法

2.消息列表页
    
    jsp分页及消息列表展现
    
    Servlet、Service、DAO实现
    service是对从数据查询到的数据进行一个处理，然后直接返回给Servlet使用
    
    Java后端分页处理
    
    调用格局
        
        ServletInit -> MessageService()
        
        MessageService Constructor -> MessageDao()
