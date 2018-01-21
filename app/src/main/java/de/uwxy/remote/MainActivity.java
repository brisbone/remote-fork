/*
 * Copyright (C) 2018 Ulrich Wiegand, brisbone@gmx.de
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 */

package de.uwxy.remote;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = "MAIN_ACTIVITY_DEBUG: ";
    private final String serverUri = "tcp://raspi:1883";
    private final String PUBLISH_TOPIC = "home/lirc/switch";
    private MqttAndroidClient mqttAndroidClient;
    private String myPubString;
    private final String ON_STRING = "ON";
    private String myMessage;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        Button xButton = findViewById(R.id.xbutton);
        Button allOffButton = findViewById(R.id.allOffButton);
        Button allOnButton = findViewById(R.id.allOnButton);
        Button volumeUpButton = findViewById(R.id.volumeUpButton);
        Button volumeDownButton = findViewById(R.id.volumeDownButton);
        Button channelUpButton = findViewById(R.id.channelUpButton);
        Button channelDownButton = findViewById(R.id.channelDownButton);
        Button radioOnButton = findViewById(R.id.radioOnButton);
        Button radioOffButton = findViewById(R.id.radioOffButton);
        Button guideButton = findViewById(R.id.guideButton);
        Button upButton = findViewById(R.id.upButton);
        Button radioTvButton = findViewById(R.id.radioTvButton);
        Button muteButton = findViewById(R.id.muteButton);
        Button source3Button = findViewById(R.id.source3Button);
        Button rightButton = findViewById(R.id.rightButton);
        Button leftButton = findViewById(R.id.leftButton);
        Button doitButton = findViewById(R.id.doitButton);
        Button backButton = findViewById(R.id.backButton);
        Button downButton = findViewById(R.id.downButton);
        Button source2Button = findViewById(R.id.source2Button);

        
        doitButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  new RemoteControl(context).volumeDown();
              }
          });
        allOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPubString = getString(R.string.allOfMqtt);
                publishMessage(PUBLISH_TOPIC + "/" + myPubString, ON_STRING);
                new RemoteControl(context).powerOff();
             }

        });
        allOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPubString = getString(R.string.allOnMqtt);
                publishMessage(PUBLISH_TOPIC + "/" + myPubString, ON_STRING);
                new RemoteControl(context).powerOn();
            }

        });
        volumeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RemoteControl(context).volumeUp();
            }

        });
        volumeDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RemoteControl(context).volumeDown();
            }

        });
        channelUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RemoteControl(context).channelUp();
            }

        });
        channelDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RemoteControl(context).channelDown();
            }

        });
        radioOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.radioOnMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, ON_STRING);
            }

        });
        radioOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.radioOffMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, ON_STRING);
            }

        });
        radioTvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.radioTvMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, ON_STRING);
            }

        });
        source3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.source3Mqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, ON_STRING);
            }

        });
        source2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.source2Mqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, ON_STRING);
            }

        });
        guideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.menuMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, ON_STRING);
            }

        });
        muteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RemoteControl(context).mute();
            }

        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.backMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, ON_STRING);
            }

        });
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new RemoteControl(context).guideLeft();
            }

        });

        doitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new RemoteControl(context).doIt();
            //myPubString = getString(R.string.tagesschauMqtt);
            //publishMessage(PUBLISH_TOPIC + "/" + myPubString, ON_STRING);
            }

        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RemoteControl(context).guideRight();
            }

        });
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RemoteControl(context).guideDown();
            }

        });

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RemoteControl(context).guideUp();
            }

        });

        source2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPubString = getString(R.string.source2Mqtt);
                publishMessage(PUBLISH_TOPIC + "/" + myPubString, ON_STRING);
            }

        });

        source3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPubString = getString(R.string.source3Mqtt);
                publishMessage(PUBLISH_TOPIC + "/" + myPubString, ON_STRING);
            }

        });

        final Spinner radioStations = findViewById(R.id.radioStations);
        ArrayAdapter<String> radioStationsArrayAdapter = new ArrayAdapter<>(this
                , R.layout.spinner_item
                , getResources().getStringArray(R.array.radio_stations));
        radioStationsArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        radioStations.setAdapter(radioStationsArrayAdapter);
        radioStations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                    switch (position) {
                    case 0: break;
                    case 1: new RemoteControl(context).rockAntenne(); break;
                    case 2: new RemoteControl(context).sunshineLive(); break;
                    case 3: new RemoteControl(context).hrDrei();
                    case 4: new RemoteControl(context).antenneBayern(); break;
                    case 5: new RemoteControl(context).hrZwei(); break;
                    case 6: new RemoteControl(context).hrEins(); break;
                    case 7: new RemoteControl(context).mdrJump(); break;
                    case 8: new RemoteControl(context).bayernDrei(); break;
                    case 9: new RemoteControl(context).youFm(); break;
                    case 10: new RemoteControl(context).mdrSputnik(); break;
                    case 11: new RemoteControl(context).brKlassik(); break;
                    case 12: new RemoteControl(context).brAktuell(); break;
                    case 13: new RemoteControl(context).hrVier(); break;
                }
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
            });

        final Spinner tvStations = findViewById(R.id.tvStations);
        ArrayAdapter<String> tvStationsArrayAdapter = new ArrayAdapter<>(this
                , R.layout.spinner_item
                , getResources().getStringArray(R.array.tv_stations));
        tvStationsArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        tvStations.setAdapter(tvStationsArrayAdapter);
        tvStations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                switch (position) {
                    case 0: break;
                    case 1: new RemoteControl(context).dasErste(); break;
                    case 2: new RemoteControl(context).zdf(); break;
                    case 3: new RemoteControl(context).dreiSat(); break;
                    case 4: new RemoteControl(context).arte(); break;
                    case 5: new RemoteControl(context).phönix(); break;
                    case 6: new RemoteControl(context).tagesschau24(); break;
                    case 7: new RemoteControl(context).zdfInfo(); break;
                    case 8: new RemoteControl(context).ardAlpha(); break;
                    case 9: new RemoteControl(context).one(); break;
                    case 10: new RemoteControl(context).zdfNeo(); break;
                    case 11: new RemoteControl(context).hessenDrei(); break;
                }
        }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
            });


        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(true);
        String clientId = "s4@uwxy.de";
        mqttAndroidClient = new MqttAndroidClient(getApplicationContext(), serverUri, clientId);
        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

                if (reconnect) {
                    addToHistory("Reconnected to : " + serverURI);
                    // Because Clean Session is true, we need to re-subscribe
                    //subscribeToTopic(myAboTopic);
                } else {
                    addToHistory("Connected to: " + serverURI);
                }
            }

            @Override
            public void connectionLost(Throwable cause) {
                addToHistory("The SocketToLircConnection was lost.");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String incomingMessage = new String(message.getPayload());
                addToHistory("Incoming message: " + topic + ": " + incomingMessage);
                myMessage = topic + ": " + incomingMessage;
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

        try {
            addToHistory("Connecting to " + serverUri);
            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                    disconnectedBufferOptions.setBufferEnabled(true);
                    disconnectedBufferOptions.setBufferSize(100);
                    disconnectedBufferOptions.setPersistBuffer(false);
                    disconnectedBufferOptions.setDeleteOldestMessages(false);
                    mqttAndroidClient.setBufferOpts(disconnectedBufferOptions);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    addToHistory("Failed to connect to: " + serverUri);
                }
            });


        } catch (MqttException ex){
            ex.printStackTrace();
        }

    }



    private void addToHistory(String string){
        //connectionHistory.add(string);
        //serverViewVar.setText(string);
        //Log.e(TAG, string);
    }
/*
    private void subscribeToTopic(String topic) {
        try {
            mqttAndroidClient.subscribe(topic, QOS, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.w("Mqtt","Subscribed: " + myAboTopic);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.w("Mqtt", "Subscribed fail!");
                }
            });

        } catch (MqttException ex) {
            System.err.println("Exceptionst subscribing");
            ex.printStackTrace();
        }
    }
*/
    private void publishMessage(String topic, String messageString){

        try {
            MqttMessage message = new MqttMessage();
            message.setPayload(messageString.getBytes());
            mqttAndroidClient.publish(topic, message);
            addToHistory("Message Published: " + topic + " " + message);
            if(!mqttAndroidClient.isConnected()){
                addToHistory(mqttAndroidClient.getBufferedMessageCount() + " messages in buffer.");
            }
        } catch (MqttException e) {
            System.err.println("Error Publishing: " + e.getMessage());
            e.printStackTrace();
        }
    }
    


}