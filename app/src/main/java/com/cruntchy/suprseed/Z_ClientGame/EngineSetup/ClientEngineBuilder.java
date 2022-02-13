package com.cruntchy.suprseed.Z_ClientGame.EngineSetup;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

import com.cruntchy.suprseed.MainView.GameProcessor.Loop.GameView;
import com.cruntchy.suprseed.MainView.GameViewBuilder.BaseEngineBuilder;
import com.cruntchy.suprseed.MainView.GameViewBuilder.DefaultEngineBuilder;
import com.cruntchy.suprseed.MainView.GameViewBuilder.DefaultWindowConfig;
import com.cruntchy.suprseed.MainView.GameViewBuilder.WindowConfigurator;


public class ClientEngineBuilder extends BaseEngineBuilder {

    // Constructor
    public ClientEngineBuilder(Context context, Resources res, SharedPreferences gameData){
        super(context, res, gameData);

    }


    @Override
    public GameView getView() {

        // TODO: Client adds their custom code here for how they want their view to built out
        //  If the client does not want to manually pick modules, they can use the 'DefaultBuilder'

        DefaultEngineBuilder builder = new DefaultEngineBuilder(super.context, super.res, super.gameData);

        return builder.getView();
    }

    @Override
    public void setWindowConfig(AppCompatActivity mainActivity) {

        WindowConfigurator defaultWindowConfig = new DefaultWindowConfig(false, false);

        defaultWindowConfig.applyWindowSettings(mainActivity);
    }



    /*
    // Example: pass in custom module
    @Override
    public GameView getView() {

        // Here, the client can create a partially default game view

        // Create the default module bindings
        DefaultBuilder builder = new DefaultBuilder(super.context, super.res, super.gameData);

        // Users initializes their custom module
        TouchHandler myCustomHandler = new CustomTouchHandler();

        GameView clientView = new ClientView(myCustomHandler, builder.getDefaultSensor());

        return clientView;
    }
     */



    /*
    // Example: Pass in custom module to a custom game view
    @Override
    public GameView getView() {

        // Here, the user can pass in requirements for the engine, as well as
        // extend the functionality of their code

        // Create the default module bindings

        // Users initializes their custom module
        TouchHandler myCustomHandler = new CustomTouchHandler();

        // This is how it looks to use the default constructor
        // Requirements: TouchHandler, DeviceSensor
        //GameView clientView = new ClientView(myCustomHandler, builder.getDefaultSensor());

        // Here, the user can pass in requirements for the parent, as well as pass in data
        // for their custom view
        // Requirements: TouchHandler, DeviceSensor, ControllerInput
        CustomView clientView = new CustomView(myCustomHandler, builder.getDefaultSensor(), myCustomHandler);

        return clientView;
    }
     */
}
