import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {



    @Test
    public void LoadTest() throws Exception {


        startSeverForTest();

        for (int i = 0; i < 20 ; i++) {
            startClientForTest();
            Thread.sleep(100);
        }
        Thread.sleep(10000);


    }


    public void startSeverForTest(){
        new Thread(() -> {
            try {
                Server.startServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void startClientForTest(){
        new Thread(() -> {
            try {
                Client.startClientDemo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


}