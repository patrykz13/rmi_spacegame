package server;


import common.CommanderInterface;

public class ConnectedCommander {
    private String name;
    private CommanderInterface connection;
    private Integer numberOfPlayers;

    public ConnectedCommander(CommanderInterface connection, String name) {
        this.connection = connection;
        this.name = name;
        this.numberOfPlayers = 0;
    }

    public CommanderInterface getConnection() {
        return connection;
    }

    public void setConnection(CommanderInterface connection) {
        this.connection = connection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void incrementNumberOfPlayers() {
        numberOfPlayers++;
    }

    public void decrementNumberOfPlayers() {
        numberOfPlayers--;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

}
