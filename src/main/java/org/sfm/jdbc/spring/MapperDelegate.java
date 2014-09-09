package org.sfm.jdbc.spring;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.sfm.jdbc.JdbcMapper;
import org.sfm.utils.ListHandler;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public final class MapperDelegate<T> implements RowMapper<T>, PreparedStatementCallback<List<T>>, ResultSetExtractor<List<T>> {
	private final JdbcMapper<T> mapper;
	
	public MapperDelegate(JdbcMapper<T> mapper) {
		this.mapper = mapper;
	}

	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		return mapper.map(rs);
	}

	@Override
	public List<T> doInPreparedStatement(PreparedStatement ps) throws SQLException,
			DataAccessException {
		ResultSet rs = ps.executeQuery();
		try {
			return extractData(rs);
		} finally {
			rs.close();
		}
	}

	@Override
	public List<T> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		return mapper.forEach(rs, new ListHandler<T>()).getList();
	}
}