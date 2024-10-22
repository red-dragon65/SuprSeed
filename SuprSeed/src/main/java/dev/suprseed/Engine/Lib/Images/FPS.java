package dev.suprseed.Engine.Lib.Images;

public enum FPS {

    // This number must fit equally within the frame time
    // FPS options: 1, 2, 3, 4, 5, 6, 10, 12, 15, 20, 30, 60

    _1(1),
    _2(2),
    _3(3),
    _4(4),
    _5(5),
    _6(6),
    _10(10),
    _12(12),
    _15(15),
    _20(20),
    _30(30),
    _60(60);

    private final int fps;

    FPS(int fps){
        this.fps = fps;
    }

    public int toInt(){
        return fps;
    }
}
