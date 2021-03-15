# Java IO 详解

## Serializable 序列化
处理对象流的一种机制
* 序列化：把Java对象转换为可保持或传输的字节序列
* 反序列化：把Java对象的字节序列恢复为Java对象
> 作用：序列化传递和保存对象，保证对象的完整性和可传递性，反序列化重建对象，从而实现进程间或网络上的对象传输。
 
> 常见场景：
> * 对象信息持久化
> * 进程间传递对象
> * 网络传输对象
> * RMI传输对象

> 实现方式：实现Serializable或者Externalizable接口(继承Serializable)     
> Serializable接口没有任何方法，相当于只是一个标识        
> Externalizable接口有两个方法，readExternal()和writeExternal(),可实现对象输入输出流的自定义
> > *注意关键字transient和static修复的属性不会被序列化, 但是transient关键字的可以通过重写readExternal和writeExternal来实现序列化*