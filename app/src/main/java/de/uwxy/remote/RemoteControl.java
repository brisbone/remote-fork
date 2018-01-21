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


public class RemoteControl implements Lirc {


    private String AMP = "yamaha";
    private String TV = "toshiba";
    private String RECEIVER = "humax";
    //private String PRE_STRING = "SEND_ONCE";
    private boolean cec = true;
    private Context context;

    RemoteControl(Context context){
        this.context = context;
    }

    @Override
    public void volumeDown(){
       //readXml = new ReadXml(context);
       //Log.e(TV,"volumeUp reached");
            send(AMP, context.getString(R.string.yamVolumeDown));

        //Log.e(TV,"sendString passed: ");

    }

    @Override
    public void powerOff() {
            send(AMP, context.getString(R.string.yamPowerOff));
            //send(RECEIVER, count(2, context.getString(R.string.humPower)));
    }

    @Override
    public void powerOn() {
            send(AMP, context.getString(R.string.yamPowerOn));
            //send(RECEIVER, count(2, context.getString(R.string.humPower)));
    }

    @Override
    public void mute() {
        send(AMP, context.getString(R.string.yamMute));
    }

    @Override
    public void dreiSat() {
        send(RECEIVER, context.getString(R.string.hum1));
        send(RECEIVER, context.getString(R.string.hum3));
    }

    @Override
    public void arte() {
        send(RECEIVER, context.getString(R.string.hum1));
        send(RECEIVER, context.getString(R.string.hum5));
    }

    @Override
    public void phönix() {
       send(RECEIVER, context.getString(R.string.hum1));
       send(RECEIVER, context.getString(R.string.hum6));
    }

    @Override
    public void tagesschau24() {
        send(RECEIVER, context.getString(R.string.hum2));
        send(RECEIVER, context.getString(R.string.hum0));
    }

    @Override
    public void dasErste() {
        send(RECEIVER, context.getString(R.string.hum1));
    }

    @Override
    public void zdf() {
        send(RECEIVER, context.getString(R.string.hum2));
    }

    @Override
    public void zdfInfo() {
        send(RECEIVER, context.getString(R.string.hum1));
        send(RECEIVER, context.getString(R.string.hum8));
    }

    @Override
    public void ardAlpha() {
        send(RECEIVER, context.getString(R.string.hum1));
        send(RECEIVER, context.getString(R.string.hum4));
    }

    @Override
    public void one() {
        send(RECEIVER, context.getString(R.string.hum1));
        send(RECEIVER, context.getString(R.string.hum9));
    }

    @Override
    public void zdfNeo() {
        send(RECEIVER, context.getString(R.string.hum1));
        send(RECEIVER, context.getString(R.string.hum7));
    }

    @Override
    public void hessenDrei() {
        send(RECEIVER, context.getString(R.string.hum3));
        send(RECEIVER, context.getString(R.string.hum5));
    }

    @Override
    public void rockAntenne() {
        send(RECEIVER, context.getString(R.string.hum5));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum1));
    }

    @Override
    public void sunshineLive() {
        send(RECEIVER, context.getString(R.string.hum5));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum5));
    }

    @Override
    public void hrDrei() {
        send(RECEIVER, context.getString(R.string.hum5));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum4));
    }

    @Override
    public void antenneBayern() {
        send(RECEIVER, context.getString(R.string.hum5));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum3));
    }

    @Override
    public void hrZwei() {
        send(RECEIVER, context.getString(R.string.hum5));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum1));
        send(RECEIVER, context.getString(R.string.hum5));
    }

    @Override
    public void hrEins() {
        send(RECEIVER, context.getString(R.string.hum5));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum7));
    }

    @Override
    public void mdrJump() {
        send(RECEIVER, context.getString(R.string.hum5));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum2));
    }

    @Override
    public void bayernDrei() {
        send(RECEIVER, context.getString(R.string.hum5));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum6));
    }

    @Override
    public void youFm() {
        send(RECEIVER, context.getString(R.string.hum5));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum8));
    }

    @Override
    public void mdrSputnik() {
        send(RECEIVER, context.getString(R.string.hum5));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum9));
    }

    @Override
    public void brKlassik() {
        send(RECEIVER, context.getString(R.string.hum5));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum1));
        send(RECEIVER, context.getString(R.string.hum2));
    }

    @Override
    public void brAktuell() {
        send(RECEIVER, context.getString(R.string.hum5));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum1));
        send(RECEIVER, context.getString(R.string.hum3));
    }

    @Override
    public void hrVier() {
        send(RECEIVER, context.getString(R.string.hum5));
        send(RECEIVER, context.getString(R.string.hum0));
        send(RECEIVER, context.getString(R.string.hum1));
        send(RECEIVER, context.getString(R.string.hum6));
    }

    @Override
    public void guideUp() {
        send(RECEIVER, context.getString(R.string.humUp));
    }

    @Override
    public void guideDown() {
        send(RECEIVER, context.getString(R.string.humDown));
    }

    @Override
    public void guideLeft() {
        send(RECEIVER, context.getString(R.string.humLeft));
    }

    @Override
    public void guideRight() {
        send(RECEIVER, context.getString(R.string.humRight));
    }

    @Override
    public void source3() {

    }

    @Override
    public void source2() {

    }

    @Override
    public void doIt() {
        send(RECEIVER, context.getString(R.string.humOk));
    }

    @Override
    public void channelUp() {
        send(RECEIVER, context.getString(R.string.humChannelUp));
    }

    @Override
    public void channelDown() {
        send(RECEIVER, context.getString(R.string.humChannelDown));
    }



    @Override
    public void volumeUp(){
        send(AMP, context.getString(R.string.yamVolumeUp));
    }


    private void send(String deviceSring, String commandString){
        String[] sendString = {PRE_STRING, deviceSring, commandString};
        //Log.e(AMP, sendString[0] + sendString[1] + sendString[2]);
        new SocketToLircConnection().execute(sendString);
    }

}