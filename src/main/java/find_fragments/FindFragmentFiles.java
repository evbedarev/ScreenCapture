package find_fragments;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FindFragmentFiles implements FindFragments{
    private static FindFragmentFiles instance;
    private String wildCard;
    private String rootDir;

    private FindFragmentFiles() {
    }

    public static FindFragmentFiles getInstance() {
        if (instance == null) {
            instance = new FindFragmentFiles();
        }
        return instance;
    }

    /**
     * convert files to images.
     * @return List<BufferedImage> - list of images
     * @throws IOException
     */
    public List<BufferedImage> fragments(String wildCard, String rootDir)
            throws IOException {
        this.wildCard = wildCard;
        this.rootDir = rootDir;

        List<BufferedImage> images = new ArrayList<>();
        List<File> filesList = findFiles();
        for (File file: filesList) {
            images.add(ImageIO.read(file));
        }
        return images;
    }

    /**
     * Find files by wildcard and return List of Files
     * @return List<Files> - list of files
     * @throws IOException
     */
    public List<File> findFiles() throws IOException {
        List<File> fragFiles = new ArrayList<>();
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(
                Paths.get(rootDir), wildCard))
        {
            dirStream.forEach(path -> fragFiles.add(path.toFile()));
        }
        return fragFiles;
    }
}
