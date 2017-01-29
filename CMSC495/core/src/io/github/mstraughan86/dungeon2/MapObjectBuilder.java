package io.github.mstraughan86.dungeon2;


import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.utils.Array;

public class MapObjectBuilder {
        private float ppt = 32f;

        Array<Body> buildShapes(Map map, float pixels, World world) {
            ppt = pixels;
            MapObjects objects = map.getLayers().get("collision").getObjects();
            Array<Body> bodies = new Array<Body>();
            for (MapObject object : objects) {
                if (object instanceof TextureMapObject) {
                    continue;
                }
                Shape shape;
                if (object instanceof RectangleMapObject) {
                    shape = getRectangle((RectangleMapObject) object);
                } else if (object instanceof PolylineMapObject) {
                    shape = getPolyline((PolylineMapObject) object);
                }
            }
            return bodies;
        }

        private PolygonShape getRectangle(RectangleMapObject rectangleObject) {
            Rectangle rectangle = rectangleObject.getRectangle();
            PolygonShape polygon = new PolygonShape();
            Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / ppt,
                    (rectangle.y + rectangle.height * 0.5f) / ppt);
            polygon.setAsBox(rectangle.width * 0.5f / ppt,
                    rectangle.height * 0.5f / ppt,
                    size,
                    0.0f);
            return polygon;

        }

        private ChainShape getPolyline(PolylineMapObject polylineObject) {
            float[] vertices = polylineObject.getPolyline().getTransformedVertices();
            Vector2[] worldVertices = new Vector2[vertices.length / 2];

            for (int i = 0; i < vertices.length / 2; ++i) {
                worldVertices[i] = new Vector2();
                worldVertices[i].x = vertices[i * 2] / ppt;
                worldVertices[i].y = vertices[i * 2 + 1] / ppt;
            }
            ChainShape chain = new ChainShape();
            chain.createChain(worldVertices);
            return chain;

        }
    }