package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote
{
    void confirmConnection(String message) throws RemoteException;
    void receiveCommand(SpaceCommand command) throws RemoteException;
    void receiveCommand(String command) throws RemoteException;
    void receivePoints(Integer points) throws RemoteException;
    void receiveNumberOfPlayers(Integer numberOfPlayers) throws RemoteException;
    void becomeKickout(Boolean kickout) throws RemoteException;
    void startRound(Integer seconds) throws RemoteException;
    void lossConnectionWithServer() throws RemoteException;


}
