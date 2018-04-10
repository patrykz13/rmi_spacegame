package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote
{
    void confirmConnection(String message) throws RemoteException;
    void receiveCommand(SpaceCommand command) throws RemoteException;
    void receiveCommand(String command) throws RemoteException;

}
