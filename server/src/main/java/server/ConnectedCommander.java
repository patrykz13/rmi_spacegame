package server;


import common.CommanderInterface;

public class ConnectedCommander
{
    private CommanderInterface connection;
    public String name;
    private Integer numberOfPlayers = 0;

    public ConnectedCommander(CommanderInterface connection, String name)
    {
        this.connection = connection;
        this.name = name;
    }

    public CommanderInterface getConnection()
    {
        return connection;
    }

    public void setConnection(CommanderInterface connection)
    {
        this.connection = connection;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void decrementNumberOfPlayers() {

    }
}
