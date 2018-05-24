import org.junit.Test;
import static org.junit.Assert.assertTrue;
import main.Prop;
public class TestSettings {

    @Test
    public void testingSettings() {
        assertTrue(Prop.getScreenWidth() == 1600);
    }
}
