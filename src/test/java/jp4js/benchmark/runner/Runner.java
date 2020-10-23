package jp4js.benchmark.runner;

public abstract class Runner {
    private long begin;
    private long end;
    private int tests;

    public void begin() {
        this.begin = System.currentTimeMillis();
    }

    public void end() {
        this.end = System.currentTimeMillis();
    }

    public void test() {
        this.tests ++;
        doTest();
    }

    public long average() {
        return (end - begin) / tests;
    }

    public abstract void doTest();
}
