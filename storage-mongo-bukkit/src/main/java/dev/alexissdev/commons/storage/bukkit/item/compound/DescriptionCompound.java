package dev.alexissdev.commons.storage.bukkit.item.compound;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import team.unnamed.pixel.storage.codec.ModelReader;
import team.unnamed.pixel.storage.mongo.codec.DocumentCodec;
import team.unnamed.pixel.storage.mongo.codec.DocumentWriter;

public class DescriptionCompound
        implements DocumentCodec {

    private final String value;

    private DescriptionCompound(String value) {
        this.value = value;
    }

    /**
     * "Read a DescriptionCompound from a ModelReader."
     * <p>
     * The first line of the function is a JavaDoc comment. It's a good idea to include a JavaDoc comment for every
     * function you write
     *
     * @param reader The ModelReader to read from.
     * @return A new DescriptionCompound object.
     */
    public static DescriptionCompound read(ModelReader<Document> reader) {
        return create(reader.readString("value"));
    }

    /**
     * > Creates a new instance of the DescriptionCompound class
     *
     * @param value The value of the description.
     * @return A new instance of the DescriptionCompound class.
     */
    public static DescriptionCompound create(String value) {
        return new DescriptionCompound(value);
    }

    /**
     * > This function takes a list of strings and returns a set of description compounds
     *
     * @param stringList A list of strings that will be used to create the DescriptionCompound objects.
     * @return A set of DescriptionCompound objects.
     */
    public static Set<DescriptionCompound> ofStringList(List<String> stringList) {
        if (stringList == null) {
            return Collections.emptySet();
        }

        Set<DescriptionCompound> descriptionCompoundSet = new HashSet<>();
        for (String string : stringList) {
            descriptionCompoundSet.add(create(string));
        }

        return descriptionCompoundSet;
    }

    /**
     * This function returns the value of the variable value.
     *
     * @return The value of the variable value.
     */
    public String getValue() {
        return value;
    }

    @Override
    public Document serialize() {
        return DocumentWriter.create()
                .write("value", value)
                .end();
    }
}
