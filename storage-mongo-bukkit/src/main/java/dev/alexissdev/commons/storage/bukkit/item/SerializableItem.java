package dev.alexissdev.commons.storage.bukkit.item;

import dev.alexissdev.commons.storage.bukkit.item.compound.DescriptionCompound;
import dev.alexissdev.commons.storage.bukkit.item.compound.EnchantmentCompound;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import team.unnamed.pixel.storage.codec.ModelReader;
import team.unnamed.pixel.storage.mongo.codec.DocumentCodec;
import team.unnamed.pixel.storage.mongo.codec.DocumentWriter;

public class SerializableItem
        implements DocumentCodec {

    protected final Set<EnchantmentCompound> enchantmentCompoundSet;
    protected String materialName;
    protected Short code;
    protected Integer amount;
    protected Integer slot;
    protected String displayName;
    protected Set<DescriptionCompound> descriptionCompoundSet;

    protected SerializableItem(
            String materialName,
            Short code,
            Integer amount,
            Integer slot,
            String displayName,
            Set<DescriptionCompound> descriptionCompoundSet,
            Set<EnchantmentCompound> enchantmentCompoundSet
    ) {
        this.materialName = materialName;
        this.code = code;
        this.amount = amount;
        this.slot = slot;
        this.displayName = displayName;
        this.descriptionCompoundSet = descriptionCompoundSet;
        this.enchantmentCompoundSet = enchantmentCompoundSet;
    }

    /**
     * It creates a new SerializableItem object with the given parameters
     *
     * @param materialName           The name of the material.
     * @param code                   The data value of the item.
     * @param amount                 The amount of the item.
     * @param displayName            The name of the item.
     * @param descriptionCompoundSet A set of description compound's that will be displayed in the item's lore.
     * @param enchantmentCompoundSet A set of enchantment IDs to enchantment levels.
     * @return A new SerializableItem object.
     */
    public static SerializableItem create(
            String materialName,
            Short code,
            Integer amount,
            Integer slot,
            String displayName,
            Set<DescriptionCompound> descriptionCompoundSet,
            Set<EnchantmentCompound> enchantmentCompoundSet
    ) {
        return new SerializableItem(
                materialName,
                code,
                amount,
                slot,
                displayName,
                descriptionCompoundSet,
                enchantmentCompoundSet
        );
    }

    /**
     * It takes an ItemStack, and returns a SerializableItem
     *
     * @param itemStack The ItemStack you want to serialize.
     * @return A SerializableItem object.
     */
    public static SerializableItem of(ItemStack itemStack, int slot) {
        return create(
                itemStack.getType().name(),
                itemStack.getDurability(),
                itemStack.getAmount(),
                slot,
                itemStack.getItemMeta().getDisplayName(),
                DescriptionCompound.ofStringList(
                        itemStack.getItemMeta().getLore()
                ),
                EnchantmentCompound.ofBukkitEnchantmentMap(
                        itemStack.getEnchantments()
                )
        );
    }

    /**
     * It reads the data from the model and creates a new instance of the class
     *
     * @param reader The ModelReader object that is used to read the data from the file.
     * @return A SerializableItem object.
     */
    public static SerializableItem read(
            ModelReader<Document> reader
    ) {
        return create(
                reader.readString("material_name"),
                Short.valueOf(reader.readString("code")),
                reader.readInt("amount"),
                reader.readInt("slot"),
                reader.readString("display_name"),
                reader.readChildren("description", DescriptionCompound::read),
                reader.readChildren("enchantment_compound_set", EnchantmentCompound::read)
        );
    }

    /**
     * This function returns the name of the material
     *
     * @return The material name.
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * This function sets the material name
     *
     * @param materialName The name of the material to be created.
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    /**
     * > This function returns the code of the enum
     *
     * @return The code of the enum
     */
    public Short getCode() {
        return code;
    }

    /**
     * > This function sets the value of the code variable to the value of the code parameter
     *
     * @param code of item.
     */
    public void setCode(Short code) {
        this.code = code;
    }

    /**
     * > This function returns the amount of money in the account
     *
     * @return The amount of the item.
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This function sets the amount of the object to the amount passed in.
     *
     * @param amount The amount of the items.
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * > This function returns the slot number of the card
     *
     * @return The slot number of the card.
     */
    public Integer getSlot() {
        return slot;
    }

    /**
     * This function sets the slot of the card
     *
     * @param slot The slot number of the item in the inventory.
     */
    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    /**
     * This function returns the display name of the user
     *
     * @return The displayName variable is being returned.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * This function sets the display name of the user
     *
     * @param displayName The name of the field that will be displayed in the form.
     */
    public void setDisplayName(
            String displayName
    ) {
        this.displayName = displayName;
    }

    /**
     * Return a copy of the description list.
     *
     * @return A copy of the description list.
     */
    public Set<DescriptionCompound> getDescription() {
        return new HashSet<>(descriptionCompoundSet);
    }

    /**
     * This function sets the description of the object to the description passed in
     *
     * @param descriptionCompoundSet A list of compounds of description
     */
    public void setDescription(
            Set<DescriptionCompound> descriptionCompoundSet
    ) {
        this.descriptionCompoundSet = descriptionCompoundSet;
    }

    /**
     * Add all the strings in the strings array to the description list.
     */
    public void addDescription(
            String... strings
    ) {
        for (String string : strings) {
            descriptionCompoundSet.add(
                    DescriptionCompound.create(string)
            );
        }
    }

    /**
     * It returns a copy of the enchantments set
     *
     * @return A new HashSet of the enchantmentCompoundSet
     */
    public Set<EnchantmentCompound> getEnchantments() {
        return new HashSet<>(enchantmentCompoundSet);
    }

    /**
     * It adds an enchantment to the enchantment map
     *
     * @param enchantment The enchantment to add.
     * @param level       The level of the enchantment.
     */
    public void addEnchantment(
            Enchantment enchantment,
            int level
    ) {
        enchantmentCompoundSet
                .add(EnchantmentCompound.create(enchantment.getName(), level));
    }

    /**
     * It takes the data from the config file and turns it into an ItemStack
     *
     * @return An ItemStack
     */
    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(Material.matchMaterial(materialName), amount, code);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);

        List<String> lore = new ArrayList<>();

        for (DescriptionCompound descriptionCompound : descriptionCompoundSet) {
            lore.add(descriptionCompound.getValue());
        }

        itemMeta.setLore(lore);


        for (EnchantmentCompound enchantmentCompound : enchantmentCompoundSet) {
            Enchantment type = Enchantment.getByName(enchantmentCompound.getType());
            if (type != null) {
                itemMeta.addEnchant(type, enchantmentCompound.getLevel(), true);
            }
        }

        item.setItemMeta(itemMeta);

        return item;
    }

    @Override
    public Document serialize() {
        return DocumentWriter.create()
                .write("material_name", materialName)
                .write("code", code)
                .write("amount", amount)
                .write("slot", slot)
                .write("display_name", displayName)
                .write("description", descriptionCompoundSet)
                .write("enchantment_compound_set", enchantmentCompoundSet)
                .end();
    }
}
