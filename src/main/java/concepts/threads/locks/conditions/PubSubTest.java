package concepts.threads.locks.conditions;

public class PubSubTest {
    public static void main(String[] args) {
        PubSubService pubSubService = new PubSubService(10);

        new Thread(() -> {
            for(int i=0;i<25;i++) {
                try {
                    System.out.println(pubSubService.consume());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for(int i=0;i<25;i++) {
                try {
                    pubSubService.publish("message"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
