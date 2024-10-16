package test;

import org.junit.*;
import org.junit.runner.JUnitCore;


public class TestVacio {
    private EscenarioVacio escenarioVacio = new EscenarioVacio();

    public TestVacio() {
    }

    public static void main(String[] args) {
        String[] args2 = { TestVacio.class.getName() };
        JUnitCore.main(args2);
    }

    @Before
    public void setUp() throws Exception {
        escenarioVacio.setUp();
    }

    @After
    public void tearDown() throws Exception {
        escenarioVacio.tearDown();
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }


    @Test
    public void testeaAlgoNehuen(){
    		//ahhhhh
    	//asdas
    	///ekis de
    }

}
