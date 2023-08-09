package dev.alexisdev.storage.bukkit.location;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import team.unnamed.pixel.storage.codec.ModelReader;
import team.unnamed.pixel.storage.mongo.codec.DocumentCodec;
import team.unnamed.pixel.storage.mongo.codec.DocumentWriter;

public class SerializableLocation
        implements DocumentCodec {

    private final String worldName;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    private SerializableLocation(
            String worldName,
            double x,
            double y,
            double z,
            float yaw,
            float pitch
    ) {
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    /**
     * "This function creates a new LightLocation object with the given parameters."
     * <p>
     * Now, let's look at the parameters
     *
     * @param x     The x coordinate of the location
     * @param y     The y coordinate of the location.
     * @param z     The z coordinate of the location.
     * @param yaw   The yaw of the location.
     * @param pitch The angle of the location, in degrees, from the horizontal.
     * @return A new LightLocation object with the given parameters.
     */
    public static SerializableLocation of(
            String worldName,
            double x,
            double y,
            double z,
            float yaw,
            float pitch
    ) {
        return new SerializableLocation(worldName, x, y, z, yaw, pitch);
    }

    /**
     * This function takes a Location object and returns a LightLocation object.
     *
     * @param location The location to convert
     * @return A new LightLocation object.
     */
    public static SerializableLocation of(
            Location location
    ) {
        return new SerializableLocation(
                location.getWorld().getName(),
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getYaw(),
                location.getPitch()
        );
    }

    /**
     * "Reads a LightLocation from a ModelReader."
     * <p>
     * The first line of the function is the function signature. It's a public static function called read that takes a
     * ModelReader as a parameter and returns a LightLocation
     *
     * @param reader The ModelReader that is reading the data.
     * @return A LightLocation object
     */
    public static SerializableLocation read(
            ModelReader<Document> reader
    ) {
        return of(
                reader.readString("world_name"),
                reader.readDouble("x"),
                reader.readDouble("y"),
                reader.readDouble("z"),
                reader.readFloat("yaw"),
                reader.readFloat("pitch")
        );
    }

    /**
     * This function returns the name of the world.
     *
     * @return The worldName variable.
     */
    public String getWorldName() {
        return worldName;
    }

    /**
     * This function returns a Bukkit World object from a String.
     *
     * @return The world name.
     */
    public World getBukkitWorld() {
        return Bukkit.getWorld(worldName);
    }

    /**
     * This function returns the value of the x variable.
     *
     * @return The value of the x variable.
     */
    public double getX() {
        return x;
    }

    /**
     * This function returns the y coordinate of the point.
     *
     * @return The y coordinate of the point.
     */
    public double getY() {
        return y;
    }

    /**
     * This function returns the z value of the point.
     *
     * @return The z value of the point.
     */
    public double getZ() {
        return z;
    }

    /**
     * This function returns the yaw of the player.
     *
     * @return The yaw of the player.
     */
    public float getYaw() {
        return yaw;
    }

    /**
     * This function returns the pitch of the sound.
     *
     * @return The pitch of the note.
     */
    public float getPitch() {
        return pitch;
    }

    /**
     * It converts a Location object to a Bukkit Location object
     *
     * @return A new Location object.
     */
    public Location toBukkitLocation() {
        return new Location(
                getBukkitWorld(),
                x,
                y,
                z,
                yaw,
                pitch
        );
    }

    @Override
    public Document serialize() {
        return DocumentWriter.create()
                .write("world_name", getWorldName())
                .write("x", getX())
                .write("y", getY())
                .write("z", getZ())
                .write("yaw", getYaw())
                .write("pitch", getPitch())
                .end();
    }
}