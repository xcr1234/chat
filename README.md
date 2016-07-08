#Servlet Comet聊天室

**基于Comet技术的Java简易聊天室，主要学习Servlet Comet技术的使用。**

##构建项目

从git上下载最新的代码

`git clone https://git.coding.net/xcr_abcd/chat.git`

使用maven install编译它  

`maven install`  

在target目录下编译生成chat.war，拷贝到tomcat中运行。


##核心功能

核心代码见com.oraclewdp.chat.comet.ChatCometServlet

```java

		final AsyncContext ac = request.startAsync(request, response);
        ac.setTimeout(600000L);
        ac.addListener(new AsyncListener() {

            @Override
            public void onComplete(AsyncEvent ae) throws IOException {
                ASYNC_CONTEXT_QUEUE.remove(ac);
            }

            @Override
            public void onTimeout(AsyncEvent ae) throws IOException {
                ASYNC_CONTEXT_QUEUE.remove(ac);
            }

            @Override
            public void onError(AsyncEvent ae) throws IOException {
                ASYNC_CONTEXT_QUEUE.remove(ac);
            }

            @Override
            public void onStartAsync(AsyncEvent ae) throws IOException {

            }
        });
        ASYNC_CONTEXT_QUEUE.add(ac);
```


##jar库引用

commons codec  
fastjson  
sqlite jdbc  

##参考资料

主要代码参考了这篇文章：  

http://www.ibm.com/developerworks/cn/web/wa-lo-comet/
