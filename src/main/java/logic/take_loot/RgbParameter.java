package logic.take_loot;

public class RgbParameter {
    private int mainRgb;
    private int[] subImageSize;
    private int[] ancillaryRgb;

    public RgbParameter(int mainRgb, int[] subImageSize, int[] ancillaryRgb) {
        this.mainRgb = mainRgb;
        this.subImageSize = subImageSize;
        this.ancillaryRgb = ancillaryRgb;
    }

    public void setMainRgb(int mainRgb) {
        this.mainRgb = mainRgb;
    }

    public void setSubImageSize(int[] subImageSize) {
        this.subImageSize = subImageSize;
    }

    public void setAncillaryRgb(int[] ancillaryRgb) {
        this.ancillaryRgb = ancillaryRgb;
    }

    public int getMainRgb() {

        return mainRgb;
    }

    public int[] getSubImageSize() {
        return subImageSize;
    }

    public int[] getAncillaryRgb() {
        return ancillaryRgb;
    }
}
