package org.medmota.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.medmota.demo.utils.Calculator;

public class AppTest {
	@BeforeAll
	static void setup() {
		System.out.println("@BeforeAll executed");
	}

	@BeforeEach
	void setupThis() {
		System.out.println("@BeforeEach executed");
	}

	@Tag("DEV")
	@Test
	void testCalcOne() {
		System.out.println("===AZERT===TEST ONE EXECUTED=======");
		Assertions.assertEquals(4, Calculator.add(2, 2));
	}

	@Tag("PROD")
	@Disabled
	@Test
	void testCalcTwo() {
		System.out.println("==QWERTY====TEST TWO EXECUTED=======");
		Assertions.assertEquals(6, Calculator.add(2, 4));
	}
	
	@Test
	void testFail() {
		Assertions.assertNotEquals(4, Calculator.add(2, 2), "Calculator.add(2, 2) test failed");	
	}
	
	@Test
    void testOnDev()
    {
        System.setProperty("ENV", "DEV");
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")), AppTest::message);
    }

    @Test
    void testOnProd()
    {
        System.setProperty("ENV", "PROD");
        Assumptions.assumeFalse("DEV".equals(System.getProperty("ENV")));
    }

    private static String message () {
        return "TEST Execution Failed :: ";
    }

	@AfterEach
	void tearThis() {
		System.out.println("@AfterEach executed");
	}

	@AfterAll
	static void tear() {
		System.setProperty("ENV", "PROD");
		System.out.println("@AfterAll executed");
	}
}
