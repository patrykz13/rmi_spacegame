package server;

import common.PlayerInterface;

public class ConnectedPlayer
{
    private PlayerInterface connection;
    ConnectedCommander commander;
    private String type;
    private String name;

    public ConnectedPlayer(PlayerInterface connection, String type, String name, ConnectedCommander commander)
    {
        this.connection = connection;
        this.type = type;
        this.name = name;
    }

    public PlayerInterface getConnection()
    {
        return connection;
    }

    public void setConnection(PlayerInterface connection)
    {
        this.connection = connection;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public ConnectedCommander getCommander() {
        return commander;
    }

    public void setCommander(ConnectedCommander commander) {
        this.commander = commander;
    }

}
