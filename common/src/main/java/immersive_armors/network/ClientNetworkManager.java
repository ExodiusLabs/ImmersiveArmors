package immersive_armors.network;

import immersive_armors.Main;
import immersive_armors.network.s2c.SettingsMessage;

public class ClientNetworkManager implements NetworkManager {
    @Override
    public void handleSettingsMessage(SettingsMessage message) {
        Main.ENABLE_EFFECTS = message.enableEffects;
    }
}
