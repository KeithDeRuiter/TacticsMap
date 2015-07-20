/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.model.track;

import java.util.UUID;

/**
 * An object that is being tracked.
 * @author Keith
 */
public class Track {
    /** The UUID of this track. */
    private final UUID id;
    
    /** The location of the object. */
    private Position position;
    
    /** The hostility of the object, i.e. friendly or enemy. */
    private HostilityLevel hostility;
    
    /** The name of the object. */
    private String name;
    
    /** The identifier string of this track. */
    private String identifier;

    /**
     * Creates a new instance of {@Track} with a random {@link UUID} and the given data.
     * @param position The position of the Track.
     * @param hostility The hostility of the Track.
     * @param identifier The identifier 
     * @param name The name of the Track.
     */
    public Track(Position position, HostilityLevel hostility, String identifier, String name) {
        this(UUID.randomUUID(), position, hostility, identifier, name);
    }

    /**
     * Creates a new instance of {@Track} with the specified {@link UUID} and the given data.
     * Used for specific instances where using a specified existing ID may be necessary.
     * @param id The UUID of the Track.
     * @param position The position of the Track.
     * @param hostility The hostility of the Track.
     * @param identifier The identifier string fo rthis track.
     * @param name The name of the Track.
     */
    public Track(UUID id, Position position, HostilityLevel hostility, String identifier, String name) {
        this.id = id;
        this.position = position;
        this.hostility = hostility;
        this.identifier = identifier;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public HostilityLevel getHostility() {
        return hostility;
    }

    public void setHostility(HostilityLevel hostility) {
        this.hostility = hostility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
