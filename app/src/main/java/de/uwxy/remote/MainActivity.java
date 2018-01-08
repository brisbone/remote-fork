package de.uwxy.remote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

    public static final String TAG = "DEBUG: ";
    Button doitButton;
    Button allOffButton;
    Button allOnButton;
    Button volumeUpButton;
    Button volumeDownButton;
    Button channelUpButton;
    Button channelDownButton;
    Button radioOnButton;
    Button radioOffButton;
    Button source3Button;
    Button source2Button;
    Button radioTvButton;
    Button muteButton;
    Button phoenixButton;
    Button arteButton;
    Button tagesschauButton;
    Button dreiSatButton;
    Button menuButton;
    Button backButton;
    Button reserveButton;
    Button reserveZweiButton;
    TextView messageView;
    final String serverUri = "tcp://raspi:1883";
    String clientId = "s4@uwxy.de";
    final String subscriptionTopic = "home/sensors/#";
    final String PUBLISH_TOPIC = "home/lirc/switch";
    final int QOS = 1;
    MqttAndroidClient mqttAndroidClient;
    boolean isConnected = false;
    boolean isMessageEmpty = true;
    boolean isSubscriptionEmpty = true;
    String myAboTopic = "";
    String myPubString;
    String myMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        doitButton = findViewById(R.id.button);
        allOffButton = findViewById(R.id.allOffButton);
        allOnButton =  findViewById(R.id.allOnButton);
        volumeUpButton =  findViewById(R.id.volumeUpButton);
        volumeDownButton =  findViewById(R.id.volumeDownButton);
        channelUpButton =  findViewById(R.id.channelUpButton);
        channelDownButton =  findViewById(R.id.channelDownButton);
        radioOnButton =  findViewById(R.id.radioOnButton);
        radioOffButton =  findViewById(R.id.radioOffButton);
        source3Button =  findViewById(R.id.src3Button);
        source2Button =  findViewById(R.id.src2Button);
        radioTvButton =  findViewById(R.id.radioTvButton);
        muteButton =  findViewById(R.id.muteButton);
        phoenixButton =  findViewById(R.id.phoenixButton);
        arteButton =  findViewById(R.id.arteButton);
        tagesschauButton =  findViewById(R.id.tgsButton);
        dreiSatButton =  findViewById(R.id.dreiSatButton);
        menuButton =  findViewById(R.id.menuButton);
        backButton =  findViewById(R.id.backButton);
        reserveButton =  findViewById(R.id.reserveButton);
        reserveZweiButton =  findViewById(R.id.reserveZweiButton);

        messageView = findViewById(R.id.messageView);
        doitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            myPubString = getString(R.string.doitButton);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        allOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.allOfMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        allOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.allOnMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        volumeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.volumeUpMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        volumeDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.volumeDownMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        channelUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.channelUpMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        channelDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.channelDownMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        radioOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.radioOnMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        radioOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.radioOffMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        radioTvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.radioTvMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        source3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.source3Mqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        source2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.source2Mqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.menuMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        muteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.muteMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.backMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        dreiSatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.dreiSatMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        phoenixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.phoenixMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        tagesschauButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.tagesschauMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        arteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.arteMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.reserveMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });
        reserveZweiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myPubString = getString(R.string.reserveZweiMqtt);
            publishMessage(PUBLISH_TOPIC + "/" + myPubString, myPubString);
            }

        });


        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(true);
        mqttAndroidClient = new MqttAndroidClient(getApplicationContext(), serverUri, clientId);
        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

                if (reconnect) {
                    addToHistory("Reconnected to : " + serverURI);
                    // Because Clean Session is true, we need to re-subscribe
                    subscribeToTopic(myAboTopic);
                } else {
                    addToHistory("Connected to: " + serverURI);
                }
            }

            @Override
            public void connectionLost(Throwable cause) {
                addToHistory("The Connection was lost.");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String incomingMessage = new String(message.getPayload());
                addToHistory("Incoming message: " + topic + ": " + incomingMessage);
                myMessage = topic + ": " + incomingMessage;
                messageView.setText(myMessage);
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
                    isConnected = true;
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    addToHistory("Failed to connect to: " + serverUri);
                    isConnected = false;
                }
            });


        } catch (MqttException ex){
            ex.printStackTrace();
        }

    }



    public void addToHistory (String string){
        //connectionHistory.add(string);
        //serverViewVar.setText(string);
        Log.e(TAG, string);
    }

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

    public void publishMessage(String topic, String messageString){

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
