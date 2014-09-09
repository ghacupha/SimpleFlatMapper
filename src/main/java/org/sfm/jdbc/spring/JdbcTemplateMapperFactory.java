package org.sfm.jdbc.spring;

import java.util.List;

import org.sfm.jdbc.JdbcMapperFactory;
import org.sfm.map.FieldMapperErrorHandler;
import org.sfm.map.MapperBuilderErrorHandler;
import org.sfm.map.MapperBuildingException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public final class JdbcTemplateMapperFactory {
	private final JdbcMapperFactory jdbcMapperFactory = new JdbcMapperFactory();

	public JdbcTemplateMapperFactory fieldMapperErrorHandler(FieldMapperErrorHandler fieldMapperErrorHandler) {
		jdbcMapperFactory.fieldMapperErrorHandler(fieldMapperErrorHandler);
		return this;
	}

	public JdbcTemplateMapperFactory mapperBuilderErrorHandler(
			MapperBuilderErrorHandler mapperBuilderErrorHandler) {
		jdbcMapperFactory.mapperBuilderErrorHandler(mapperBuilderErrorHandler);
		return this;
	}

	public JdbcTemplateMapperFactory useAsm(boolean useAsm) {
		jdbcMapperFactory.useAsm(useAsm);
		return this;
	}
	
	public <T> RowMapper<T> newRowMapper(Class<T> target)
			throws MapperBuildingException {
		return newMapperDelegate(target);
	}

	public <T> MapperDelegate<T> newMapperDelegate(Class<T> target) {
		return new MapperDelegate<>(jdbcMapperFactory.newMapper(target));
	}
	
	public <T> PreparedStatementCallback<List<T>> newPreparedStatementCallback(Class<T> target)
			throws MapperBuildingException {
		return newMapperDelegate(target);
	}
	
	public <T> ResultSetExtractor<List<T>> newResultSetExtractor(Class<T> target)
			throws MapperBuildingException {
		return newMapperDelegate(target);
	}
}