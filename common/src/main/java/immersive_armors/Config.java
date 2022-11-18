package immersive_armors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("CanBeFinal")
public final class Config implements Serializable {
    @Serial
    private static final long serialVersionUID = 9132405079466337851L;

    public static final Logger LOGGER = LogManager.getLogger();
    private static final Config INSTANCE = loadOrCreate();

    public static Config getInstance() {
        return INSTANCE;
    }

    public static final int VERSION = 1;

    public boolean hideSecondLayerUnderArmor = true;
    public boolean enableEffects = true;
    public boolean enableEnchantmentGlint = true;

    @SuppressWarnings("unused")
    public String _documentation = "https://github.com/Luke100000/ImmersiveArmors/blob/HEAD/config.md";
    public Map<String, Float> overwriteValues = new HashMap<>();

    public int hudClockXOffhand = -91 - 20 - 29;
    public int hudClockX = -91 - 20;
    public int hudClockY = -20;
    public int hudCompassXOffhand = 91 + 4 + 29;
    public int hudCompassX = 91 + 4;
    public int hudCompassY = -20;

    public float lootChance = 0.025f;
    public float mobEntityUseImmersiveArmorChance = 0.5f;

    public HashMap<String, Boolean> enabledArmors = new HashMap<>();

    {
        enabledArmors.put("bone", true);
        enabledArmors.put("wither", true);
        enabledArmors.put("warrior", true);
        enabledArmors.put("heavy", true);
        enabledArmors.put("robe", true);
        enabledArmors.put("slime", true);
        enabledArmors.put("divine", true);
        enabledArmors.put("prismarine", true);
        enabledArmors.put("wooden", true);
        enabledArmors.put("steampunk", true);
    }

    public int version = 0;

    public static File getConfigFile() {
        return new File("./config/" + Main.MOD_ID + ".json");
    }

    public void save() {
        try (FileWriter writer = new FileWriter(getConfigFile())) {
            version = VERSION;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config loadOrCreate() {
        if (getConfigFile().exists()) {
            try (FileReader reader = new FileReader(getConfigFile())) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Config config = gson.fromJson(reader, Config.class);
                if (config.version != VERSION) {
                    config = new Config();
                }
                config.save();
                return config;
            } catch (Exception e) {
                LOGGER.error("Failed to load Immersive Armors config! Default config is used for now. Delete the file to reset.");
                e.printStackTrace();
                return new Config();
            }
        } else {
            Config config = new Config();
            config.save();
            return config;
        }
    }
}
