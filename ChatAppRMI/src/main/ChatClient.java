/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Mustaqueem Alam
 */
public interface ChatClient extends Remote {
    void receiveMessage(String message, String sender) throws RemoteException;
}