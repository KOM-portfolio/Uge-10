/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class)}
)

/**
 *
 * @author Agger
 */
public class EnemyPlugin implements IGamePluginService {
    private String enemyID;
    
    @Override
    public void start(GameData gd, World world) {
        // Add entities to the world
        Entity enemy = createEnemyShip(gd);
        enemyID = world.addEntity(enemy);
    }
    
    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new Enemy();

        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = 800 / 3;
        float y = 600 / 3;
        float radians = 3.1415f / 2;
        //enemyShip.add(new LifePart(3));
        enemyShip.setRadius(4);
        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new PositionPart(x, y, radians));
        
        return enemyShip;
    }

    @Override
    public void stop(GameData gd, World world) {
        // Remove entities
        world.removeEntity(enemyID);
    }
    
}
