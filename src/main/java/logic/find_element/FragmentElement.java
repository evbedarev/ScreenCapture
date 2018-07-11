//package logic.find_element;
//
//import find_fragments.FindFragmentFiles;
//import find_fragments.FindFragments;
//import find_image.FindImageHard;
//import key_and_mouse.Keys;
//import key_and_mouse.Mouse;
//import logic.Capture;
//import logic.kill_monster.KillMonster;
//import main.Prop;
//import main.Settings;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.Optional;
//
//public class FragmentElement implements KillMonster {
//    String rootDir = Prop.ROOT_DIR + "objects\\warp\\";
//    String wildcard = "fragm*";
//
//    private final Capture capture;
//    private final Mouse mouse;
//    private final Keys keys;
//    private final FindImageHard findImageHard;
//
//    public FragmentElement() throws AWTException {
//        capture = Capture.instance();
//        mouse = new Mouse();
//        keys = new Keys();
//        findImageHard = new FindImageHard();
//    }
//
//    /**
//     *
//     * @return boolean;
//     * @throws IOException
//     * @throws AWTException
//     * @throws InterruptedException
//     */
//
//
//
//    @Override
//    public boolean kill() throws Exception {
//        BufferedImage screenShot = capture.takeScreenShot();
//        return findAndKill(screenShot);
//    }
//
//    @Override
//    public boolean findAndKill(BufferedImage screenShot) throws
//            IOException,
//            AWTException,
//            InterruptedException
//    {
//
//        //It's bad, later change. Need to load in constructor.
//        FindFragments fragmentFiles = new FindFragmentFiles(
//                wildcard,
//                rootDir);
//
//        System.out.println(fragmentFiles.fragments().size());
//
//        for (BufferedImage fragment: fragmentFiles.fragments()) {
//            Optional<int[]> xy = findImageHard.findImageExcludeArea(screenShot, fragment);
//            if (xy.isPresent()) {
//                int x = xy.get()[0];
//                int y = xy.get()[1];
//                mouse.mouseClick(x , y);
//                return true;
//            }
//        }
//        return false;
//    }
//}
