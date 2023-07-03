package cs3500.pa04;

import cs3500.pa03.controller.BattleController;
import cs3500.pa03.model.BattleModel;
import cs3500.pa03.model.Comp;
import cs3500.pa04.client.ProxyController;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import javax.naming.ldap.Control;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - provide host and port, no command line for manual play
   */
  public static void main(String[] args) throws ConnectException {
    Appendable output = System.out;
    Readable input = new InputStreamReader(System.in);
    if (args.length == 0) {
      BattleModel model = new BattleModel();
      BattleController controller = new BattleController(input, output, model);
      controller.run();
    } else {
      try {
        Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
        ProxyController
            controller = new ProxyController(socket, new Comp(new BattleModel(), "owenhong0"));
        controller.run();
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("please provide valid host and port");
        throw new ConnectException();
      }
    }
  }
}