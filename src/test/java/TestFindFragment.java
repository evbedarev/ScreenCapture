import find_fragments.FindFragmentFiles;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class TestFindFragment {
    private static final String ROOT_DIR = "/home/mj/Projects/ScreenCapture/src/main/resources/forTests/";
    private static final String WILDCARD = "frag*";
    private static final String NEW_FRAGMENT_FILE = "fragment123.png";

    @Before
    public void createFiles() {
        createTestFile(ROOT_DIR + NEW_FRAGMENT_FILE);
    }

    @After
    public void deleteFiles() {
        File file = new File(ROOT_DIR + NEW_FRAGMENT_FILE);
        file.delete();
    }

    @Test
    public void testFndFiles() throws IOException {
        FindFragmentFiles fndFragments = new FindFragmentFiles(WILDCARD, ROOT_DIR);
        List<File> files = fndFragments.findFiles();
        assertTrue(files.size() == 2);
        File newFile = new File(NEW_FRAGMENT_FILE);
    }

    private void createTestFile(String filePath) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath), "utf-8"))) {
            writer.write("test");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
