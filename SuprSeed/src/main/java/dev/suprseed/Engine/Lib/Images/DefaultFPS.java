package dev.suprseed.Engine.Lib.Images;

public enum DefaultFPS {

    // This number must fit equally within the render system frame time
    // FPS options: 1, 2, 3, 5, 6, 10, 15, 30
    /*
    Note: the target frame time in the render system can be changed in order to allow more
    fps options. Ie, in order to allow a 20fps option, the render system target frame time should
    be changed from 30 to 60 in order to avoid multiplicity conversions where the given 20fps
    would get changed to 30fps.
     */

    _1(1),
    _2(2),
    _3(3),
    _5(5),
    _6(6),
    _10(10),
    _15(15),
    _30(30);

    private final int fps;

    DefaultFPS(int fps) {
        this.fps = fps;
    }

    public int toInt() {
        return fps;
    }
}
