# mk_android

1. AndroidPlusin 项目
android 插件化demo
插件化主要使用到技术:
    1. classloader 
    2. 代理, 插桩
    3. 反射
    4.   

2. AndroidSkin 项目
app换肤demo
主要用到技术:
    1. 反射
    2. 切面编程到思想:
    3. 观察者模式: 在所有需要换肤到activity 需要添加观察者Observer, 

3. AndroidRouter 项目
android 组件化开发
主要用到技术:
    1. 反射
    2. 注解, 注解处理器
    3. 使用livedata 数据传递
    4. 

4. java代码设计模式
    1. filter, 过滤器, 发送request 过滤顺序: A->B, 返回response 过滤顺序是B->A , 主要是递归的思想 
    2. proxy
        a. 静态代理
        b. 动态代理, JDK 动态代理, 缺点,被代理的类必须实现一个接口, 通过asm修改字节码文件
        c. cglib, java 自带的动态代理, 通过asm修改字节码文件