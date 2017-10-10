/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animations;

/**
 *
 * @author Obare
 */
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.util.Duration;

public class ConfigAnimations extends Transition {
    protected static final Interpolator WEB_EASE = Interpolator.SPLINE(0.25, 0.1, 0.25, 1);
    protected final Node node;
    protected Timeline timeline;
    private boolean oldCache = false;
    private CacheHint oldCacheHint = CacheHint.DEFAULT;
    private final boolean useCache;

 
    public ConfigAnimations(final Node node, final Timeline timeline) {
        this(node, timeline, true);
    }
    

    public ConfigAnimations(final Node node, final Timeline timeline, final boolean useCache) {
        this.node = node;
        this.timeline = timeline;
        this.useCache = useCache;
        statusProperty().addListener(new ChangeListener<Status>() {
            @Override public void changed(ObservableValue<? extends Status> ov, Status t, Status newStatus) {
                switch(newStatus) {
                    case RUNNING:
                        starting();
                        break;
                    default: 
                        stopping();
                        break;
                }
            }
        });
    }
    
    /**
     * Called when the animation is starting
     */
    protected void starting() {
        if (useCache) {
            oldCache = node.isCache();
            oldCacheHint = node.getCacheHint();
            node.setCache(true);
            node.setCacheHint(CacheHint.SPEED);
        }
    }
    
    /**
     * Called when the animation is stopping
     */
    protected void stopping() {
        if (useCache) {
            node.setCache(oldCache);
            node.setCacheHint(oldCacheHint);
        }
    }

    @Override protected void interpolate(double d) {
        timeline.playFrom(Duration.seconds(d));
        timeline.stop();
    }

}