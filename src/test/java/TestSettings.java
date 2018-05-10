import org.junit.Test;
import static org.junit.Assert.assertTrue;
import main.Settings;
public class TestSettings {

    @Test
    public void testingSettings() {
        Settings settings = Settings.instance();
        assertTrue(settings.screenWidth == 1600);
    }
}
