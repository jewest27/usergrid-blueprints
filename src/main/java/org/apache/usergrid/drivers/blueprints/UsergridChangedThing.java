package org.apache.usergrid.drivers.blueprints;

import org.apache.usergrid.java.client.Client;

/**
 * Created by ApigeeCorporation on 6/30/15.
 */
public interface UsergridChangedThing {
  void onChanged(Client client);
}
