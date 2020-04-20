/*
 * Copyright 2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp4js.utils;

import jp4js.utils.DefaultsImpl;
import jp4js.utils.spi.json.JsonProvider;
import jp4js.utils.spi.mapper.MappingProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import static jp4js.utils.Utils.notNull;
import static java.util.Arrays.asList;

/**
 * Immutable configuration object
 */
public class Configuration {

    private static Defaults DEFAULTS = null;

    /**
     * Set Default configuration
     * @param defaults default configuration settings
     */
    public static synchronized void setDefaults(Defaults defaults){
        DEFAULTS = defaults;
    }

    private static Defaults getEffectiveDefaults(){
        if (DEFAULTS == null) {
          return DefaultsImpl.INSTANCE;
        } else {
          return DEFAULTS;
        }
    }

    private final JsonProvider jsonProvider;
    private final MappingProvider mappingProvider;
    private final Set<Option> options;

    private Configuration(JsonProvider jsonProvider, MappingProvider mappingProvider, EnumSet<Option> options) {
        notNull(jsonProvider, "jsonProvider can not be null");
        notNull(mappingProvider, "mappingProvider can not be null");
        notNull(options, "setOptions can not be null");
        this.jsonProvider = jsonProvider;
        this.mappingProvider = mappingProvider;
        this.options = Collections.unmodifiableSet(options);
    }

    /**
     * Creates a new Configuration based on the given {@link jp4js.utils.spi.json.JsonProvider}
     * @param newJsonProvider json provider to use in new configuration
     * @return a new configuration
     */
    public Configuration jsonProvider(JsonProvider newJsonProvider) {
        return Configuration.builder().jsonProvider(newJsonProvider).mappingProvider(mappingProvider).options(options).build();
    }

    /**
     * Returns {@link jp4js.utils.spi.json.JsonProvider} used by this configuration
     * @return jsonProvider used
     */
    public JsonProvider jsonProvider() {
        return jsonProvider;
    }

    /**
     * Creates a new Configuration based on the given {@link jp4js.utils.spi.mapper.MappingProvider}
     * @param newMappingProvider mapping provider to use in new configuration
     * @return a new configuration
     */
    public Configuration mappingProvider(MappingProvider newMappingProvider) {
        return Configuration.builder().jsonProvider(jsonProvider).mappingProvider(newMappingProvider).options(options).build();
    }

    /**
     * Returns {@link jp4js.utils.spi.mapper.MappingProvider} used by this configuration
     * @return mappingProvider used
     */
    public MappingProvider mappingProvider() {
        return mappingProvider;
    }

    /**
     * Creates a new configuration by adding the new options to the options used in this configuration.
     * @param options options to add
     * @return a new configuration
     */
    public Configuration addOptions(Option... options) {
        EnumSet<Option> opts = EnumSet.noneOf(Option.class);
        opts.addAll(this.options);
        opts.addAll(asList(options));
        return Configuration.builder().jsonProvider(jsonProvider).mappingProvider(mappingProvider).options(opts).build();
    }

    /**
     * Creates a new configuration with the provided options. Options in this configuration are discarded.
     * @param options
     * @return
     */
    public Configuration setOptions(Option... options) {
        return Configuration.builder().jsonProvider(jsonProvider).mappingProvider(mappingProvider).options(options).build();
    }

    /**
     * Returns the options used by this configuration
     * @return
     */
    public Set<Option> getOptions() {
        return options;
    }

    /**
     * Check if this configuration contains the given option
     * @param option option to check
     * @return true if configurations contains option
     */
    public boolean containsOption(Option option){
        return options.contains(option);
    }

    /**
     * Creates a new configuration based on default values
     * @return a new configuration based on defaults
     */
    public static Configuration defaultConfiguration() {
        Defaults defaults = getEffectiveDefaults();
        return Configuration.builder().jsonProvider(defaults.jsonProvider()).options(defaults.options()).build();
    }

    /**
     * Returns a new ConfigurationBuilder
     * @return a builder
     */
    public static ConfigurationBuilder builder() {
        return new ConfigurationBuilder();
    }

    /**
     * Configuration builder
     */
    public static class ConfigurationBuilder {

        private JsonProvider jsonProvider;
        private MappingProvider mappingProvider;
        private EnumSet<Option> options = EnumSet.noneOf(Option.class);

        public ConfigurationBuilder jsonProvider(JsonProvider provider) {
            this.jsonProvider = provider;
            return this;
        }

        public ConfigurationBuilder mappingProvider(MappingProvider provider) {
            this.mappingProvider = provider;
            return this;
        }

        public ConfigurationBuilder options(Option... flags) {
            if(flags.length > 0) {
                this.options.addAll(asList(flags));
            }
            return this;
        }

        public ConfigurationBuilder options(Set<Option> options) {
            this.options.addAll(options);
            return this;
        }

        public Configuration build() {
            if (jsonProvider == null || mappingProvider == null) {
                final Defaults defaults = getEffectiveDefaults();
                if (jsonProvider == null) {
                    jsonProvider = defaults.jsonProvider();
                }
                if (mappingProvider == null){
                    mappingProvider = defaults.mappingProvider();
                }
            }
            return new Configuration(jsonProvider, mappingProvider, options);
        }
    }

    public interface Defaults {
        /**
         * Returns the default {@link jp4js.utils.spi.json.JsonProvider}
         * @return default json provider
         */
        JsonProvider jsonProvider();

        /**
         * Returns default setOptions
         * @return setOptions
         */
        Set<Option> options();

        /**
         * Returns the default {@link jp4js.utils.spi.mapper.MappingProvider}
         *
         * @return default mapping provider
         */
        MappingProvider mappingProvider();

    }
}
