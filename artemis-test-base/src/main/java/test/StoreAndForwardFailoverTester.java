package test;

import java.io.File;
import static java.util.Arrays.asList;
import javax.jms.ConnectionFactory;
import org.apache.activemq.artemis.utils.FileUtil;
import org.junit.Before;
import org.junit.Test;
import store.forward.StoreAndForwardConnectionFactory;

public class StoreAndForwardFailoverTester extends FailoverTester {

    @Before
    public void setUp() throws Exception {
        //Ensure SandF is clear
        FileUtil.deleteDirectory(new File(STORE_LOCATION_PATH));
        super.setUp();
    }

    @Test
    public void testStoreAndForward() throws Exception {
        final ConnectionFactory producerConnectionFactory = createConnectionFactoryStoreAndForward(USERNAME, PASSWORD);
        testMe(producerConnectionFactory);
    }

    @Test
    public void testStoreAndForwardFailover() throws Exception {
        final ConnectionFactory producerConnectionFactory = createConnectionFactoryStoreAndForward(USERNAME, PASSWORD);
        testMeFailover(producerConnectionFactory);
    }

    private ConnectionFactory createConnectionFactoryStoreAndForward(String username, String password) throws Exception {
        StoreAndForwardConnectionFactory storeAndForwardConnectionFactory = new StoreAndForwardConnectionFactory(this.createCoreConnectionFactory(username, password), STORE_LOCATION_PATH, asList(DESTINATION_ADDRESS));
        return storeAndForwardConnectionFactory;
    }

}
