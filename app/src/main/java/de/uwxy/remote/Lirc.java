package de.uwxy.remote;

/**
 * Created by Uli Wiegand on 10.01.18.
 */

public interface Lirc {


    String PRE_STRING = "SEND_ONCE";
    String sendStartPrefix = "SEND_START";
    String sendStopPrefix = "SEND_STOP";
    int duration = 1;

    void volumeUp();
    void volumeDown();
    void powerOff() throws InterruptedException;
    void powerOn() throws InterruptedException;
    void mute();
    void channelUp();
    void channelDown();
    void dreiSat();
    void arte();
    void phönix();
    void tagesschau24();
    void dasErste();
    void zdf();
    void zdfInfo();
    void ardAlpha();
    void one();
    void zdfNeo();
    void hessenDrei();
    void rockAntenne();
    void sunshineLive();
    void hrDrei();
    void antenneBayern();
    void hrZwei();
    void hrEins();
    void mdrJump();
    void bayernDrei();
    void youFm();
    void mdrSputnik();
    void brKlassik();
    void brAktuell();
    void hrVier();
    void guideUp();
    void guideDown();
    void guideLeft();
    void guideRight();
    void source3();
    void source2();
    void doIt();

}
