package handlers;

import crccalc.CrcCalculator;
import entities.MessageEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IMessageHandler   <T> {

    T handleMessage(MessageEntity message, OutputStream out) throws IOException;




}
