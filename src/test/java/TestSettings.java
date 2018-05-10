import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestSettings {

    @Test
    public void testingSettings() {
        main.Settings settings = main.Settings.instance();
        assertTrue(settings.screenWidth == 1600);
    }
}
