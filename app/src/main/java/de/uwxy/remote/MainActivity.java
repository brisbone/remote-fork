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
    Button button;
    TextView messageViewVar;
    TextView serverViewVar;
    EditText pubEditVar;
    EditText messageEditVar;
    EditText subEditVar;
    final String serverUri = "tcp://raspi:1883";
    String clientId = "s4@uwxy.de";
    final String subscriptionTopic = "home/sensors/#";
    String publishTopic = "home/test/test";
    final int QOS = 1;
    MqttAndroidClient mqttAndroidClient;
    boolean isConnected = false;
    boolean isMessageEmpty = true;
    boolean isSubscriptionEmpty = true;
    String myAboTopic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        messageViewVar = findViewById(R.id.messageView);
        serverViewVar = findViewById(R.id.serverView);
        pubEditVar = findViewById(R.id.pubEdit);
        messageEditVar = findViewById(R.id.messageEdit);
        subEditVar = findViewById(R.id.subEdit);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String myPubString = messageEditVar.getText().toString();

            if(myPubString.equals("") || myPubString == null){
                myPubString = "s4_test";
            }
            String myPublishTopic = pubEditVar.getText().toString();
            if(myPublishTopic.equals("") || myPubString == null){
                myPublishTopic = publishTopic;
            }
            myAboTopic = subEditVar.getText().toString();
            if (!myAboTopic.equals("") && isConnected == true) {
                subscribeToTopic(myAboTopic);
            }
            publishMessage(myPublishTopic, myPubString);
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
                messageViewVar.setText("");
                messageViewVar.setText(topic + ": " + incomingMessage);
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
        serverViewVar.setText(string);
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
