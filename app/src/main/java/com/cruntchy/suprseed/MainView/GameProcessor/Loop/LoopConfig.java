package com.cruntchy.suprseed.MainView.GameProcessor.Loop;

public class LoopConfig implements RunnableConfig<GameView> {

    private RefreshTypes refreshSpeed;
    private LogicRates logicRate;

    private boolean softPause;
    private boolean hardPause;


    // Constructor
    public LoopConfig(RefreshTypes refreshSpeed, LogicRates logicRate){

        this.refreshSpeed = refreshSpeed;
        this.logicRate = logicRate;

    }




    @Override
    public void initLoop(GameView gameView){

        initRefreshSpeed();
        initLogicSpeed();
    }

    private void initRefreshSpeed(){

    }

    private void initLogicSpeed(){

    }





    @Override
    public void run(GameView gameView){

        // TODO: Run loop based on refresh speed and logic rate!

        if(hardPause){

            // Stop logic and drawing

        } else{

            if(softPause){

                // Stop logic but allow drawing

            }else{

                // Run client logic code
                gameView.logicLoop();
            }

            // Run clients drawing code
            gameView.drawingLoop();


            // Clear out old contents of screen
            gameView.invalidate();
        }


        /*
        // Simplified form of above code
        if(!hardPause){

            if(!softPause){

                gameView.logicLoop();;
            }

            gameView.drawingLoop();
        }
        */
    }


    @Override
    public boolean isHardPause(){
        return hardPause;
    }

    @Override
    public boolean isSoftPause(){
        return softPause;
    }

    @Override
    public void setHardPause(boolean pause){

        hardPause = pause;
    }

    @Override
    public void setSoftPause(boolean pause){
        softPause = pause;
    }

    @Override
    public void toggleSoftPause(){
        softPause = !softPause;
    }

}


/*

// This is how in game pausing can be used by the client

pausedButton.touched(){

    RunnableConfig.toggleLoop();

}


gameView.focusChanged(){

    RunnableConfig.pauseLoop();
}

gameView.hasFocus(){

    RunnableConfig.resumeLoop();
}

 */