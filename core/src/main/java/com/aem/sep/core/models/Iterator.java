package com.aem.sep.core.models;


import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Iterator {

    @Inject
    private String loops;

    private List<String> iterations = new ArrayList<>(0);

    private static final int DEFAULT_NUMBER_ITERATIONS = 1;

    private static final Logger LOG = LoggerFactory.getLogger(Iterator.class);

    /**
     * Init().
     */
    @PostConstruct
    protected void init() {
        try {
            List<String> iterations = new ArrayList<>(0);
            int count = StringUtils.isEmpty(this.loops) ? DEFAULT_NUMBER_ITERATIONS : Integer.valueOf(this.loops);

            for (int i = 1; i <= count; i++) {
                iterations.add(String.valueOf(i));
            }

            this.iterations = iterations;
        } catch (Exception e) {
            LOG.error("An exception has occurred.", e);
        }
    }

    /**
     * Get the number of iterations.
     * @return List<String>
     */
    public List<String> getIterations() {
        return this.iterations;
    }
}
