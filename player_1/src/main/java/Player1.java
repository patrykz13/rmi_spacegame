import common.PlayerInterface;
import common.ServerInterface;
import common.SpaceCommand;
import controller.Player1Controller;
import controller.PlayerController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Player1 extends UnicastRemoteObject implements PlayerInterface
{
    private ServerInterface server;
    private Player1Controller controller;

    public Player1(String name, Player1Controller controller, String type) throws RemoteException
    {
        try
        {
            String url = "rmi://localhost/sserver";
            server = (ServerInterface) Naming.lookup(url);
            server.registerPlayer(this, type, name);
            this.controller = controller;
        } catch (RemoteException ex)
        {
            System.out.println("Server RemoteException.");
            System.out.println(ex.getMessage());
        } catch (NotBoundException ex)
        {
            System.out.println("Server NotBoundException.");
            System.out.println(ex.getMessage());
        } catch (MalformedURLException ex)
        {
            System.out.println("Server MalformedURLException.");
            System.out.println(ex.getMessage());
        }
    }

    public ServerInterface getServer()
    {
        return server;
    }

    //<editor-fold desc="rmi">

    @Override
    public void confirmConnection(String s) throws RemoteException
    {

    }

    @Override
    public void receiveCommand(SpaceCommand spaceCommand) throws RemoteException
    {
        System.out.println("Received command " + spaceCommand.getType() + " " + spaceCommand.getParameters() + ".");
        //String text = spaceCommand.getType().name() + " " + spaceCommand.getParameters().toString();
        //controller.textField.setText(text);
        //controller.CurrentCommand = spaceCommand;
    }

    //</editor-fold>

}
