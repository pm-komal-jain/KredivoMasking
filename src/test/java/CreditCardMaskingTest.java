import org.junit.Assert;
import org.junit.Test;

public class CreditCardMaskingTest {
    CreditCardMasking mask = new CreditCardMasking();

    @Test
    public void shouldAcceptNumbers(){
        Assert.assertEquals("Acceptable Credit Card #",mask.setCreditCardNumber("1234567890123"));
    }

    @Test
    public void shouldNotAcceptChar(){
        try {
            mask.setCreditCardNumber("1a2S123456789");
        }
        catch (Exception e) {
            boolean response = e.getLocalizedMessage().contains("Only digits are accepted as Credit Card Number found - 1a2S123456789");
            Assert.assertTrue(response);
        }
    }

    @Test
    public void shouldNotAcceptSpecialChar(){
        try {
            mask.setCreditCardNumber("$1234567890123");
        }
        catch (Exception e) {
            boolean response = e.getLocalizedMessage().contains("Only digits are accepted as Credit Card Number found - $1234567890123");
            Assert.assertTrue(response);
        }
    }

    @Test
    public void lengthShouldBeMoreEqual13(){
        try {
            mask.setCreditCardNumber("123456");
        }
        catch (Exception e) {
            boolean response = e.getLocalizedMessage().contains("Credit card length should be between 13-16 numbers long, actually found - 6");
            Assert.assertTrue(response);
        }
    }

    @Test
    public void lengthShouldbeLessEqual16(){
        try {
            mask.setCreditCardNumber("12345678901234567");
        }
        catch (Exception e) {
            boolean response = e.getLocalizedMessage().contains("Credit card length should be between 13-16 numbers long, actually found - 17");
            Assert.assertTrue(response);
        }
    }

    @Test
    public void shouldMask13DigitNumber(){
        mask.setCreditCardNumber("1234567890123");
        mask.setMaskingChar('X');
        Assert.assertTrue(mask.maskCreditCardNumber().toString().contains("XXXX-XXXXX-0123"));
    }

    @Test
    public void shouldMask16DigitNumber(){
        mask.setCreditCardNumber("1234567890123456");
        mask.setMaskingChar('*');
        Assert.assertTrue(mask.maskCreditCardNumber().toString().contains("****-********-3456"));
    }
}
