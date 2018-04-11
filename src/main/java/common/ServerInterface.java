package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote
{
    void registerPlayer(PlayerInterface player, String type, String name, String commander) throws RemoteException;
    void registerCommander(CommanderInterface commander, String name) throws RemoteException;
    void removePlayer(String name) throws RemoteException;
    void removeCommander(String name) throws RemoteException;
    void broadcastScore(int score) throws RemoteException;
    void broadcastCommand(SpaceCommand command) throws RemoteException;
    void broadcastCommand(String type, String message) throws RemoteException;
    void broadcastCommand(String type, String message, String commanderName) throws RemoteException;
    void sendPoints(String commanderName, Integer points) throws RemoteException;


    List<String> getListOfCommanders() throws RemoteException;

}
