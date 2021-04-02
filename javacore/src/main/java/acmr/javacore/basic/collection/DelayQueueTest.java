package acmr.javacore.basic.collection;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    private final DelayQueue<GameAddict> gamerQue = new DelayQueue<>();
    private final boolean stop = false;

    private class GameAddict implements Delayed {
        //都以毫秒为单位
        private final String name;
        private final long gameTime;  //游戏开始时间
        private final long addictTime = 20 * 1000;    //游戏成瘾时间，到了这个时间就需要提醒游戏者了

        public GameAddict(String name, long letsPlayAGame) {
            this.name = name;
            gameTime = letsPlayAGame;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long stopTime = gameTime + addictTime - System.currentTimeMillis();
            return unit.convert(stopTime, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int)(this.gameTime / 1000 - ((GameAddict)o).gameTime / 1000);
        }
    }

    public void letsPlayAGame() {
        new Thread(()-> {
            System.out.println("适度游戏益脑，沉迷游戏伤身");
            while (!stop) {
                try {
                    GameAddict gamer = gamerQue.take();
                    System.out.println(gamer.name + ",适度游戏益脑，沉迷游戏伤身");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()-> {
            int count = 0;
            while (!stop) {
                GameAddict gameAddict = new GameAddict("大兄弟" + count++ + "号", System.currentTimeMillis());
                System.out.println(gameAddict.name + "开启了疯狂游戏时间");
                try {
                    gamerQue.put(gameAddict);
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        DelayQueueTest test = new DelayQueueTest();
        test.letsPlayAGame();
    }
}
