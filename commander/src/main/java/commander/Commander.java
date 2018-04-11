package commander;

import common.CommanderInterface;
import common.ServerInterface;
import controller.CommanderController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Commander extends UnicastRemoteObject implements CommanderInterface
{

    private ServerInterface server;
    private CommanderController controller;

    public Commander(String name, CommanderController controller) throws RemoteException
    {
        try
        {
            String url = "rmi://localhost/sserver";
            server = (ServerInterface) Naming.lookup(url);
            server.registerCommander(this, name);
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
    public void receiveScore(int score) throws RemoteException
    {
        System.out.println("receiveScore called; value = " + score);
        //controller.updateScore(score);
    }

    @Override
    public void receivePlayerList(List<String> list) throws RemoteException
    {
        System.out.println("receivePlayerList called.");
        controller.updatePlayerList(list);
    }

    @Override
    public void receivePlayer(String name) throws RemoteException
    {

    }



    //</editor-fold>
}