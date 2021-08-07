对于一般的Web MVC模式，即将 模型，视图和控制器分开。
首先需要一个总的Servlet(DispatcherServlet)来接受请求，将其包装成GetDispatcher和PostDispatcher

class GetDispatcher {

    Object instance; // Controller实例
    Method method; // Controller方法
    String[] parameterNames; // 方法参数名称
    Class<?>[] parameterClasses; // 方法参数类型

}

对于Controller上的@XXXMapper类型注解中，value存放对应路径

在project启动后，会初始化所有Controller，并且维护一张<String,Object>表，根据路径查找对应的Controller，将它们全部包装成Dispatcher类，使用反射机制将拿到注解中的路径。
而MVC框架封装好的Get和Post会根据映射表寻找对应控制器。



SpringMvc有一个总的DispatcherServlet（类似路由）来接收发来的Req和需要发出的Resp