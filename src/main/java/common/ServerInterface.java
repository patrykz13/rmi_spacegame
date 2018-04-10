package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote
{
    void registerPlayer(PlayerInterface player, String type, String name, String commander) throws RemoteException;
    void registerCommander(CommanderInterface commander, String name) throws RemoteException;
    void removePlayer(String name) throws RemoteException;
    void removeCommander(String name) throws RemoteException;
    void broadcastScore(int score) throws RemoteException;
    void broadcastCommand(SpaceCommand command) throws RemoteException;
}
