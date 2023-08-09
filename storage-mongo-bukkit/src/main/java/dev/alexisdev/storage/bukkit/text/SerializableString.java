package dev.alexisdev.storage.bukkit.text;

import org.bson.Document;
import team.unnamed.pixel.storage.codec.ModelReader;
import team.unnamed.pixel.storage.model.Model;
import team.unnamed.pixel.storage.mongo.codec.DocumentCodec;
import team.unnamed.pixel.storage.mongo.codec.DocumentWriter;

public class SerializableString
        implements Model,
        DocumentCodec {

    private final String value;

    private SerializableString(String value) {
        this.value = value;
    }

    /**
     * This function takes a string and returns a CommandContainer object.
     *
     * @param value The command to be executed.
     * @return A new instance of CommandContainer
     */
    public static SerializableString of(String value) {
        return new SerializableString(value);
    }

    /**
     * "Reads a command container from a model reader."
     * <p>
     * The first line of the function is a JavaDoc comment. It's a good idea to include a JavaDoc comment for every
     * function
     *
     * @param reader The ModelReader to read from.
     * @return A CommandContainer object.
     */
    public static SerializableString read(ModelReader<Document> reader) {
        return of(reader.readString("value"));
    }

    @Override
    public String getId() {
        return value;
    }

    @Override
    public Document serialize() {
        return DocumentWriter.create()
                .write("value", value)
                .end();
    }
}
