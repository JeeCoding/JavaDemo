package example_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 1.创建了一个线程池，扔了5个线程，接下来要执行6个任务，扔进去线程池里面就启一个线程帮你执行一个，
 * 因为这里最多就起5个线程，接下来扔第6个任务的时候，不好意思，它排队了，排在线程池所维护的一个任务队列里面，
 * 任务队列大多数使用的都是BlockingQueue，这是线程池的概念；
 * 2.有什么好处？好处在于如果这个任务执行完了，这个线程不会消失，它执行完任务空闲下来了，
 * 如果有新的任务来的时候，直接交给这个线程来运行就行了，不需要新启动线程；
 * 从这个概念上讲，如果你的任务和线程池线程数量控制的比较好的情况下，你不需要启动新的线程就能执行很多很多的任务，效率会比较高，并发性好；
 * 3.线程池里面维护了很多线程，等着你往里扔任务，而扔任务的时候它可以维护着一个任务列表，还没有被执行的任务列表，
 * 同样的它还维护着另外一个队列，complete tasks，结束的任务队列，任务执行结束扔到这个队列里，所以，一个线程池维护着两个队列；
 */
public class ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5); //execute submit
        for (int i = 0; i < 6; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        //关闭线程池，shutdown是正常的关闭，它会等所有的任务都执行完才会关闭掉；
        // 还有一个是shutdownNow，二话不说直接就给关了，不管线程有没有执行完；
        service.shutdown();

        // service.isTerminated(): 代表的是这里所有执行的任务是不是都执行完了。
        // isShutdown()为true，注意它关了但并不代表它执行完了，只是代表正在关闭的过程之中(注意打印Shutting down)
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);

        TimeUnit.SECONDS.sleep(5);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
    }
}
