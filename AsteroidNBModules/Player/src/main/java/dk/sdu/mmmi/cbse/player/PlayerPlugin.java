/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class)}
)

/**
 *
 * @author Agger
 */
public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    @Override
    public void start(GameData gd, World world) {
        // Add entities to the world
        player = createPlayerShip(gd);
        world.addEntity(player);
    }

    @Override
    public void stop(GameData gd, World world) {
        // Remove entities
        world.removeEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {
        Entity playerShip = new Player();
        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = (800 / 2);
        float y = (600 / 2);
        float radians = 3.1415f / 2;
        
        playerShip.setRadius(4);
//        playerShip.add(new LifePart(3));
        playerShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        playerShip.add(new PositionPart(x, y, radians));

        return playerShip;
    }

}
