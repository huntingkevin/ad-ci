package com.ximalaya.ad.ci.cases;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author ruhong
 **/

public class HelloTest {

    @Test
    public void testHelloWorld() {
        System.out.println("Hello World");
        Assert.assertEquals("Hello World", "Hello World");
    }

}
