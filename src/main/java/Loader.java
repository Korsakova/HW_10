public class Loader implements Worker {
    static {
        System.out.println("Start now");
    }

    @Override
    public void doWork() {
        System.out.println("Finish again");
    }

}