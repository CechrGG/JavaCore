package acmr.javacore.advance.gc;

public class FinializeTest {
    private static FinializeTest finializeTest = new FinializeTest();
    public static FinializeTest getInstance() {
        return finializeTest;
    }

    @Override
    //此方法执行一次后不会再执行，而且极其不靠谱，忘掉吧
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("GC大魔王要吃掉我，谁来救救我");
        finializeTest = this;
    }

    public static void main(String[] args) throws InterruptedException {
//        FinializeTest test = FinializeTest.getInstance();
        finializeTest = null;
        System.out.println("GC大魔王发起第一次攻击");
        System.gc();
        Thread.sleep(1000);
        if(finializeTest == null) {
            System.out.println("一击致命，KO");
        } else {
            System.out.println("哈哈，GC大魔王也不过如此，我还活着呢" + finializeTest);
            finializeTest = null;
            System.out.println("GC大魔王发起第二次攻击");
            Thread.sleep(3000);
            if(finializeTest == null) {
                System.out.println("跑了和尚跑不了庙，乖乖受死吧");
            } else {
                System.out.println("GC大魔王该不会是脑瘫吧" + finializeTest);
            }
        }
    }
}
