//package com.example.project;
//
//import org.eclipse.paho.android.service.MqttAndroidClient;
//import org.eclipse.paho.client.mqttv3.IMqttActionListener;
//import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
//import org.eclipse.paho.client.mqttv3.IMqttToken;
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//
//public class MqttActivities {
//
//    public void Initialize(){
//        String clientId = MqttClient.generateClientId();
//        MqttAndroidClient client = new MqttAndroidClient(application, "BROKER SERVER", clientId);
//    }
//
//    public void Connect(MqttAndroidClient client){
//        client.connect().setActionCallback(new IMqttActionListener()){
//            @Override
//            public void onSuccess(IMqttToken asyncActionToken){
//                //we are connected
//            }
//            @Override
//            public void onFailure(IMqttToken asyncActionToken, Throwable exception){
//                //sth went wrong
//            }
//        }
//    }
//
//    public void Disconnect(MqttAndroidClient client){
//        client.disconnect().setActionCallback(new IMqttActionListener(){
//            @Override
//            public void onSuccess(IMqttToken asyncActionToken){
//                //we are connected
//            }
//            @Override
//            public void onFailure(IMqttToken asyncActionToken, Throwable exception){
//                //sth went wrong
//            }
//        });
//    }
//
//    public void Publish(MqttAndroidClient client){
//        MqttMessage message = new MqttMessage(payload.toString().getBytes());
//        client.publish(topic,message);
//    }
//
//    public void Subscribe(MqttAndroidClient client, String topic, int qos){
//        IMqttToken subToken = client.subscribe(topic, qos, iMqttMessageListener);
//        IMqttMessageListener iMqttMessageListener = new IMqttMessageListener() {
//            @Override
//            public void messageArrived(String topic, MqttMessage message) throws Exception{
//            }
//        };
//    }
//
//    public void Unsubscribed(MqttAndroidClient client, String topic){
//        IMqttToken unsubToken = client.unsubscribe(topic);
//        @Override
//        public void onSuccess(IMqttToken asyncActionToken){
//            //we are connected
//        }
//        @Override
//        public void onFailure(IMqttToken asyncActionToken, Throwable exception){
//            //sth went wrong
//        }
//    }
//
//}
