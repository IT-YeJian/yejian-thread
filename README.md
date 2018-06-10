#### 静态方法里为什么new内部类的实例对象
     我们知道内部类可以访问外部的成员变量,但是要等外部对象创建完后
     成员变量才会分配空间,然而在main方法里可以不用创建那个外部对象
     就矛盾了.
####获取线程结束的通知
* 1 ThreadDeathRequest
* 2 使用 Google Guava 提供的 ListeningExecutorService 启动线程会返回一个 ListenableFuture<?>，然后实现它的执行完的回调接口，就可以监听线程完成的状态，如下代码：     
    ListenableFuture<XX> future = null;
    Futures.addCallback(future,
         new FutureCallback<XX> {
           public void onSuccess(XX result) {//线程执行完的回调接口
             xx(result);
           }
           public void onFailure(Throwable t) {//线程执行失败的回调接口
             reportError(t);
           }
         });     
         
        
   
         