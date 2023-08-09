package dev.alexisdev.storage.bukkit.item.compound;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bson.Document;
import org.bukkit.enchantments.Enchantment;
import team.unnamed.pixel.storage.codec.ModelReader;
import team.unnamed.pixel.storage.mongo.codec.DocumentCodec;
import team.unnamed.pixel.storage.mongo.codec.DocumentWriter;

public class EnchantmentCompound
        implements DocumentCodec {

    private final String type;
    private final int level;

    private EnchantmentCompound(
            String type,
            int level
    ) {
        this.type = type;
        this.level = level;
    }

    /**
     * `create` creates a new `EnchantmentCompound` object
     *
     * @param type  The type of enchantment.
     * @param level The level of the enchantment.
     * @return A new EnchantmentCompound object.
     */
    public static EnchantmentCompound create(
            String type,
            int level
    ) {
        return new EnchantmentCompound(type, level);
    }

    /**
     * "Reads an EnchantmentCompound from a ModelReader."
     * <p>
     * The first line of the function is the function header. It tells us the function's name, its return type, and its
     * parameters
     *
     * @param reader The ModelReader that is reading the data.
     * @return EnchantmentCompound
     */
    public static EnchantmentCompound read(
            ModelReader<Document> reader
    ) {
        return create(
                reader.readString("type"),
                reader.readInt("level")
        );
    }

    /**
     * It takes a Bukkit enchantment map and returns a set of enchantment compounds
     *
     * @param enchantmentMap The enchantment map to convert.
     * @return A set of EnchantmentCompound objects.
     */
    public static Set<EnchantmentCompound> ofBukkitEnchantmentMap(
            Map<Enchantment, Integer> enchantmentMap
    ) {
        Set<EnchantmentCompound> enchantmentCompoundSet = new HashSet<>();

        for (Map.Entry<Enchantment, Integer> entry : enchantmentMap.entrySet()) {
            enchantmentCompoundSet.add(EnchantmentCompound.create(entry.getKey().getName(), entry.getValue()));
        }

        return enchantmentCompoundSet;
    }

    /**
     * This function returns the type of the current object
     *
     * @return The type of the object.
     */
    public String getType() {
        return type;
    }

    /**
     * This function returns the level of the player.
     *
     * @return The level of the player.
     */
    public int getLevel() {
        return level;
    }

    @Override
    public Document serialize() {
        return DocumentWriter.create()
                .write("type", type)
                .write("level", level)
                .end();
    }
}
