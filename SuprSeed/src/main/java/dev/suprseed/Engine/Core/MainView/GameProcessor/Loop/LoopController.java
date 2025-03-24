package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

public class LoopController implements LoopControllable {

    private boolean softPause = false;
    private boolean hardPause = false;

    public LoopController() {

    }

    @Override
    public boolean isHardPause() {
        return hardPause;
    }

    @Override
    public void setHardPause(boolean hardPause) {
        this.hardPause = hardPause;
    }

    @Override
    public boolean isSoftPause() {
        return softPause;
    }

    @Override
    public void setSoftPause(boolean softPause) {
        this.softPause = softPause;
    }

    @Override
    public void toggleSoftPause() {
        this.softPause = !softPause;
    }
}
