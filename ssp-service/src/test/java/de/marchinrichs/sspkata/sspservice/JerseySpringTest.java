package de.marchinrichs.sspkata.sspservice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/** Run JerseyTest with custom spring context with mocks Mimics Spring @WebMvcTest with @MockBean */
public abstract class JerseySpringTest extends JerseyTest {

    @Override
    protected ResourceConfig configure() {
        MockitoAnnotations.openMocks(this);
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        set(TestProperties.CONTAINER_PORT, "0");
        final ResourceConfig resourceConfig =
                new ResourceConfig()
                        .property("contextConfig", createSpringContext(getBeanMap()))
                        .property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, "WARNING")
                        .register(getResourceClass());
        return serverConfig(resourceConfig);
    }

    /**
     * Gives the test class opportunity to further customize the configuration. Like registering a
     * MultiPartFeature if required.
     *
     * @param config
     * @return
     */
    protected ResourceConfig serverConfig(final ResourceConfig config) {
        return config;
    }

    /**
     * Supplies all the bean objects required to be loaded in the application context for the Resource class
     * under test
     *
     * @return
     */
    protected List<Object> getBeans() {
        return Collections.emptyList();
    }

    /**
     * Supplies all the bean objects with name qualifier required to be loaded in the application context for the Resource class
     * under test
     *
     * @return
     */
    protected Map<String, Object> getQualifiedBeans() {
        return Collections.emptyMap();
    }

    private Map<String, Object> getBeanMap() {
        final Map<String, Object> result = new HashMap<>();
        CollectionUtils.emptyIfNull(getBeans())
                .forEach(obj -> result.put(StringUtils.uncapitalize(obj.getClass().getSimpleName()), obj));
        result.putAll(MapUtils.emptyIfNull(getQualifiedBeans()));
        return result;
    }

    /**
     * Resource class under test
     *
     * @return
     */
    protected abstract Class<?> getResourceClass();

    /**
     * Creates & returns a Spring GenericApplicationContext from the given beans with qualified names
     *
     * @param beans
     * @return
     */
    public static ApplicationContext createSpringContext(Map<String, Object> beans) {
        final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        MapUtils.emptyIfNull(beans).forEach((k, obj) -> beanFactory.registerSingleton(k, obj));
        final GenericApplicationContext context = new GenericApplicationContext(beanFactory);
        context.refresh();
        return context;
    }
}