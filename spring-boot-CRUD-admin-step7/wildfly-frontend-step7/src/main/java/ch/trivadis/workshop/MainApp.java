package ch.trivadis.workshop;

import javax.enterprise.inject.spi.Extension;
import org.jacpfx.discovery.extension.K8SExtension;
import ch.trivadis.workshop.api.JaxRsActivator;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.undertow.WARArchive;

/**
 * Created by Andy Moncsek on 02.06.17.
 */
public class MainApp {
  public static void main(String[] args) throws Exception {
    Swarm swarm = new Swarm();
    WARArchive deployment = ShrinkWrap.create(WARArchive.class);
    deployment.
        addAllDependencies().
        addClass(JaxRsActivator.class).
        addAsServiceProvider(Extension.class, K8SExtension.class).
        addPackages(true,"ch.trivadis.workshop");
    deployment.staticContent();
    swarm.start();
    swarm.deploy(deployment);
  }
}
