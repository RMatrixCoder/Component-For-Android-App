package project.helper;

import android.content.res.AssetManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import project.core.G;

public class HelperIO {
  public static void closeStream(InputStream stream){
    try {
      stream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void closeStream(OutputStream stream){
    try {
      stream.flush();
      stream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void closeChannel(FileChannel channel){
    try {
      channel.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void copyFile(String inputFileName,String outputFileName){

    InputStream inputStream = null;
    OutputStream outputStream = null;
    try {
      inputStream = new FileInputStream(inputFileName);
      outputStream = new FileOutputStream(outputFileName);

      copyFile(inputStream,outputStream);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }finally {
      closeStream(inputStream);
      closeStream(outputStream);
    }
  }

  public static void copyFileFromAssets(String assetFileName,String outputFileName){
    AssetManager assetManager = G.context.getAssets();
    InputStream inputStream;
    try {
      inputStream = assetManager.open(assetFileName);
      copyFile(inputStream,outputFileName);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void copyFile(String inputFileName,OutputStream outputStream){

    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream(inputFileName);

      copyFile(inputStream,outputStream);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }finally {
      closeStream(inputStream);
      closeStream(outputStream);
    }
  }
  public static void copyFile(InputStream inputStream,String outputFileName){

    OutputStream outputStream = null;
    try {
      outputStream = new FileOutputStream(outputFileName);

      copyFile(inputStream,outputStream);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }finally {
      closeStream(inputStream);
      closeStream(outputStream);
    }
  }
//  public static void copyFile(FileInputStream inputStream,FileOutputStream outputStream){
//
//    FileChannel inputChannel = null;
//    FileChannel outputChannel = null;
//    try {
//      inputChannel = inputStream.getChannel();
//      outputChannel = outputStream.getChannel();
//
//      outputChannel.transferFrom(inputChannel,0,inputChannel.size());
//
//    } catch (IOException e) {
//      e.printStackTrace();
//      closeChannel(inputChannel);
//      closeChannel(outputChannel);
//    }
//  }

  public static void copyFile(InputStream inputStream,OutputStream outputStream){
    byte[] buffer = new byte[8 * 1024];
    int lenRead = 0;

    try {
      while ((lenRead = inputStream.read(buffer)) != -1){
        outputStream.write(buffer,0,lenRead);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      closeStream(inputStream);
      closeStream(outputStream);
    }
  }
}
