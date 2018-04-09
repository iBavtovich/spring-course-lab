package lab.five;

import lab.five.aop.AopLog;
import lab.five.model.Bar;
import lab.five.model.Customer;
import lab.five.model.CustomerBrokenException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:lab5aop.xml")
public class AopAspectJExceptionTest {

	@Autowired
	private Bar bar;
    
	@Autowired
    private Customer customer;

    @BeforeEach
    public void setUp() throws Exception {
        
        customer.setBroke(true);
    }

    @Test
    public void testAfterThrowingAdvice() {
 
    	Assertions.assertThrows(CustomerBrokenException.class, () -> bar.sellSquishee(customer), "Customer is broken" );
        assertTrue("Customer is not broken ", AopLog.getStringValue().contains("Hmmm..."));
        System.out.println(AopLog.getStringValue());
    }
}