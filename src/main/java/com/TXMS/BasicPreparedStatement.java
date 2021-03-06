package com.TXMS;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.bo.Employee;

public class BasicPreparedStatement {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterjdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//Prapared statement
	
	public Boolean saveEmployeeByPreparedStatement(final Employee e){  
	    String query="insert into employee values(?,?,?)";  
	    return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
	    public Boolean doInPreparedStatement(PreparedStatement ps)  
	            throws SQLException, DataAccessException {  
	              
	        ps.setInt(1,e.getId());  
	        ps.setString(2,e.getName());  
	        ps.setString(3,e.getRole());  
	              
	        return ps.execute();  
	              
	    }
	    });  
	}
	
	public Boolean updateEmployeeByPreparedStatement(final Employee e){  
	    String query="update employee set name=? where id=?";  
	    return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
	    public Boolean doInPreparedStatement(PreparedStatement ps)  
	            throws SQLException, DataAccessException {  
	              
	        ps.setInt(2,e.getId());  
	        ps.setString(1,e.getName());  
	              
	        return ps.execute();  
	              
	    }
	    });  
	}
	
	public  void saveEmployeeByNamedParameter(Employee e){  
		String query="insert into employee values (:id,:name,:role)";  
		  
		Map<String,Object> map=new HashMap<String,Object>();  
		map.put("id",e.getId());  
		map.put("name",e.getName());  
		map.put("role",e.getRole());  
		  
		namedParameterjdbcTemplate.execute(query,map,new PreparedStatementCallback<Integer>() {  
		    @Override  
		    public Integer doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException {  
		        return ps.executeUpdate();  
		    }  
		});  
	}
	
	public  void updateEmployeeByNamedParameter(Employee e){  
		String query="update employee set name=:name,role=:role where id = :id";  
		  
		Map<String,Object> map=new HashMap<String,Object>();  
		map.put("id",e.getId());  
		map.put("name",e.getName());  
		map.put("role",e.getRole());  
		  
		namedParameterjdbcTemplate.execute(query,map,new PreparedStatementCallback<Integer>() {  
		    @Override  
		    public Integer doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException {  
		        return ps.executeUpdate();  
		    }  
		});  
	}
}
