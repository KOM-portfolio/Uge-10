/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)
    ,
    @ServiceProvider(service = IGamePluginService.class)}
)

/**
 *
 * @author chris
 */
public class AsteroidSystem implements IGamePluginService, IEntityProcessingService {

    @Override
    public void start(GameData gameData, World world) {
        System.out.println("Installing Asteroid Plugin");
        Entity asteroid = createAsteroid();
        world.addEntity(asteroid);
    }

    @Override
    public void stop(GameData gameData, World world) {
        System.out.println("Uninstalling Asteroid Plugin");
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);

            int numPoints = 12;
            
            movingPart.setUp(true);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            setShape(asteroid, numPoints);
        }
    }

    private Entity createAsteroid() {
        Entity asteroid = new Asteroid();
        float radians = (float) Math.random() * 2 * 3.1415f;
        float speed = (float) Math.random() * 10f + 20f;

        asteroid.setRadius(20);
        asteroid.add(new MovingPart(0, speed, speed, 0));
        asteroid.add(new PositionPart(30, 30, radians));

        return asteroid;
    }

    private void setShape(Entity asteroid, int numPoints) {
        PositionPart position = asteroid.getPart(PositionPart.class);
        float[] shapex = new float[numPoints];
        float[] shapey = new float[numPoints];
        float radians = position.getRadians();
        float x = position.getX();
        float y = position.getY();
        float radius = asteroid.getRadius();

        float angle = 0;

        for (int i = 0; i < numPoints; i++) {
            shapex[i] = x + (float) Math.cos(angle + radians) * radius;
            shapey[i] = y + (float) Math.sin(angle + radians) * radius;
            angle += 2 * 3.1415f / numPoints;
        }

        asteroid.setShapeX(shapex);
        asteroid.setShapeY(shapey);
    }

}
