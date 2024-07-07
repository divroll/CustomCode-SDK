package com.divroll.sdkapi;

import com.divroll.core.CannotCastValueException;
import com.divroll.core.EntityStoreException;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.security.SecureRandom;

import static org.junit.Assert.*;

public class EntityTest {

    @Test
    public void testCreatingEntityConstructorWithProperties() throws EntityStoreException, CannotCastValueException, IOException {
        // Given: a set of properties for a mobile game character
        Map<String, EntityProperty> properties = new HashMap<>();

        byte[] avatarImage = generateRandomBytes(4096);
        InputStream mockImage = new ByteArrayInputStream(avatarImage);
        properties.put("avatar", new BlobProperty(mockImage));
        properties.put("isActive", new BooleanProperty(true));
        properties.put("inventory", new CollectionProperty<>(Arrays.asList(new StringProperty("sword"), new StringProperty("shield"), new StringProperty("potion"))));
        properties.put("highScore", new DoubleProperty(2510.32));
        properties.put("level", new IntProperty(5L));
        properties.put("name", new StringProperty("Knight"));
        // properties.put("metadata", new MetadataProperty(/* some metadata */));

        // When: an Entity is created with these properties
        Entity gameCharacter = new Entity("GameCharacter", properties);
        gameCharacter.setPublicRead(new BooleanProperty(false));
        gameCharacter.setPublicWrite(new BooleanProperty(false));

        // Then: the Entity should have the properties set correctly
        assertTrue("Avatar should be a byte array", Arrays.equals(avatarImage, convertInputStreamToByteArray(gameCharacter.get("avatar", BlobProperty.class).getValue())));
        assertTrue("isActive should be true", gameCharacter.get("isActive", BooleanProperty.class).getValue());
        assertEquals("Inventory should contain items",
                     Arrays.asList(new StringProperty("sword"), new StringProperty("shield"), new StringProperty("potion")),
                     gameCharacter.get("inventory", CollectionProperty.class).getValue()
        );
        assertEquals("HighScore should be 2510.32", 2510.32, gameCharacter.get("highScore", DoubleProperty.class).getValue(), 0.01);
        assertEquals("Level should be 5", 5, gameCharacter.get("level", IntProperty.class).getValue().intValue());
        assertEquals("Name should be 'Knight'", "Knight", gameCharacter.get("name", StringProperty.class).getValue());
    }

    @Test(expected = CannotCastValueException.class)
    public void testEntityConstructorWithInvalidType() throws CannotCastValueException {
        // Setup
        Map<String, EntityProperty> properties = new HashMap<>();
        properties.put("name", new StringProperty("Knight"));
        
        // Execution
        Entity entity = new Entity("GameCharacter", properties);
        entity.get("name", BooleanProperty.class); // This should throw an exception
    }
    
    @Test
    public void testSettingAProperty() throws CannotCastValueException {
        // Given: a StringProperty with a value of "Archer"
        Entity entity = new Entity("GameCharacter", new HashMap<String, EntityProperty>());
        StringProperty nameProperty = new StringProperty("Archer");

        // When: the property is set on the entity
        entity.set("name", nameProperty);

        // Then: the entity should have the property with the correct value
        StringProperty retrievedProperty = entity.get("name", StringProperty.class);
        assertNotNull("Property should not be null after being set", retrievedProperty);
        assertEquals("Property value should match the set value", "Archer", retrievedProperty.getValue());
    }

    @Test
    public void testGettingAProperty() throws CannotCastValueException {
        // Given: an entity with a BooleanProperty set to true
        Entity entity = new Entity("GameCharacter", new HashMap<String, EntityProperty>());
        BooleanProperty activeProperty = new BooleanProperty(true);
        entity.set("isActive", activeProperty);

        // When: the property is retrieved from the entity
        BooleanProperty retrievedProperty = retrievedProperty = entity.get("isActive", BooleanProperty.class);;

        // Then: the retrieved property should be the same as the one set
        assertNotNull("Retrieved property should not be null", retrievedProperty);
        assertTrue("Retrieved property value should be true", retrievedProperty.getValue());
    }

    private static byte[] generateRandomBytes(int length) {
        byte[] randomBytes = new byte[length];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomBytes);
        return randomBytes;
    }

    private static byte[] convertInputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int bytesRead;
        byte[] data = new byte[1024];

        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }

        buffer.flush();
        return buffer.toByteArray();
    }
}