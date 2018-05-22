import org.junit.Test;
import static org.junit.Assert.assertTrue;
public class TestSettings {

    @Test
    public void testingSettings() {
        assertTrue(main.Prop.getScreenWidth() == 1600);
    }
}
