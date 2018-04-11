package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CommanderInterface extends Remote
{
    void receiveScore(int score) throws RemoteException;
    void receiveNumberOfPlayers(int score) throws RemoteException;
    void receivePlayerList(List<String> players) throws RemoteException;
    void receivePlayer(String player) throws RemoteException;
    void lossConnectionWithServer() throws RemoteException;

}
