package jp4js.utils;

import jp4js.utils.Configuration.Defaults;
import jp4js.utils.spi.json.GsonJsonProvider;
import jp4js.utils.spi.json.JsonProvider;
import jp4js.utils.spi.mapper.GsonMappingProvider;
import jp4js.utils.spi.mapper.MappingProvider;

import java.util.EnumSet;
import java.util.Set;

public final class DefaultsImpl implements Defaults {

  public static final DefaultsImpl INSTANCE = new DefaultsImpl();

  private final MappingProvider mappingProvider = new GsonMappingProvider();

  @Override
  public JsonProvider jsonProvider() {
    return new GsonJsonProvider();
  }

  @Override
  public Set<Option> options() {
    return EnumSet.noneOf(Option.class);
  }

  @Override
  public MappingProvider mappingProvider() {
    return mappingProvider;
  }

  private DefaultsImpl() {
  };

}
