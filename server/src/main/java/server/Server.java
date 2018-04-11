package server;


import common.CommanderInterface;
import common.PlayerInterface;
import common.ServerInterface;
import common.SpaceCommand;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Server extends UnicastRemoteObject implements ServerInterface
{

    private HashMap<String, ConnectedCommander> commanders;
    private HashMap<String, ConnectedPlayer> players;

    public Server() throws RemoteException
    {
        players = new HashMap<>();
        commanders = new HashMap<>();
    }

    @Override
    public void registerPlayer(PlayerInterface connection, String type, String name, String commanderName) throws RemoteException
    {
        System.out.println("Player " + name + type + " has connected.");
        ConnectedCommander connectedCommander = commanders.get(commanderName);
        System.out.println(connectedCommander.name);
        ConnectedPlayer player = new ConnectedPlayer(connection, type, name,connectedCommander);
        players.put(name, player);
        if (connectedCommander != null)
        {
            connectedCommander.getConnection().receivePlayerList(createPlayersList(connectedCommander.getName()));
        } else
        {
            System.out.println("Error, commander is null.");
        }
    }

    @Override
    public void registerCommander(CommanderInterface connection, String name) throws RemoteException
    {
        System.out.println("Commander " + name + " has connected.");
        ConnectedCommander commander = new ConnectedCommander(connection, name);
        commander.getConnection().receiveScore(2137);
        commanders.put(name,commander);
    }

    @Override
    public void removePlayer(String name) throws RemoteException
    {
        System.out.println("Commander " + name + " has connected.");
        ConnectedPlayer player = players.get(name) ;
        ConnectedCommander commander = player.getCommander();
        players.remove(name);
        if (commander != null)
        {
            commander.getConnection().receivePlayerList(createPlayersList(commander.getName()));
        } else
        {
            System.out.println("Error, commander is null.");
        }
    }

    @Override
    public List<String> getListOfCommanders() throws RemoteException
    {
        List<String> commandersNames = new ArrayList<>();
        for (Map.Entry<String, ConnectedCommander> entry : commanders.entrySet())
        {
            commandersNames.add(entry.getValue().getName());
        }
        return commandersNames;
    }

    @Override
    public void removeCommander(String name) throws RemoteException
    {
        System.out.println("Commander " + name + " has connected.");
        commanders.remove(name);
    }

    @Override
    public void broadcastScore(int score) throws RemoteException
    {
        System.out.println("Score changed by " + score);
        //commander.getConnection().receiveScore(score);
    }

    @Override
    public void sendPoints(String commanderName, Integer points) throws RemoteException
    {
        System.out.println("DOWODCY:");
        for (Map.Entry<String, ConnectedPlayer> entry : players.entrySet())
        {
            if(entry.getValue().getCommander().getName().equals(commanderName))
                entry.getValue().getConnection().receivePoints(points);
        }
        //commander.getConnection().receiveScore(score);
    }

    @Override
    public void startRound(Integer integer, String commanderName) throws RemoteException {
        for (Map.Entry<String, ConnectedPlayer> entry : players.entrySet())
        {
            if(entry.getValue().getCommander().getName().equals(commanderName))
                entry.getValue().getConnection().startRound(integer);
        }
    }

    @Override
    public void broadcastCommand(SpaceCommand spaceCommand) throws RemoteException
    {
        System.out.println("Sending " + spaceCommand.getType() + " command.");
        for (Map.Entry<String, ConnectedPlayer> entry : players.entrySet())
        {
            if (entry.getValue().getType().equals(spaceCommand.getType()))
                entry.getValue().getConnection().receiveCommand(spaceCommand);
        }
    }

    @Override
    public void broadcastCommand(String type, String message) throws RemoteException
    {
        System.out.println("Sending " + type + " command." + message);
        for (Map.Entry<String, ConnectedPlayer> entry : players.entrySet())
        {
            if (entry.getValue().getType().equals(type))
                entry.getValue().getConnection().receiveCommand(message);
        }
    }

    @Override
    public void broadcastCommand(String type, String message, String commanderName) throws RemoteException {
        for (Map.Entry<String, ConnectedPlayer> entry : players.entrySet())
        {
            if (entry.getValue().getType().equals(type) &&entry.getValue().getCommander().getName().equals(commanderName))
                entry.getValue().getConnection().receiveCommand(message);
        }
    }

    private List<String> createPlayersList(String commanderName)
    {
        List<String> playerDisplayNames = new ArrayList<>();
        for (Map.Entry<String, ConnectedPlayer> entry : players.entrySet())
        {
            if(entry.getValue().getCommander().getName().equals(commanderName))
                playerDisplayNames.add(entry.getValue().getName() + " " + entry.getValue().getType());
        }
        return playerDisplayNames;
    }
    public HashMap<String, ConnectedCommander> getCommanders() {
        return commanders;
    }

    public HashMap<String, ConnectedPlayer> getPlayers() {
        return players;
    }

}