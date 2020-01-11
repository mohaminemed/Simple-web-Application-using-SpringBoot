package soft.webtest.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import soft.webtest.models.User;

import javax.sql.rowset.JdbcRowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDataAccess {
    @Autowired
    JdbcTemplate jdbcTemplate;
    class  UserRowMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user =new User(
                    resultSet.getInt("id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname")
                    );
            return user;
        }
    }
    public int insert (User user){
        return jdbcTemplate.update("insert into user (id,firstname,lastname)"+"values (?,?,?)",new Object[]{
                user.getId(),user.getFirstname(),user.getLastname()
        });
    }
    public List<User> getAllUsers(){
        return jdbcTemplate.query("select* from user",new UserRowMapper());
    }

    public int delete(int id){
        return jdbcTemplate.update("delete from user where id=?",new Object[] {id});
    }
    public int update(User user){
        return jdbcTemplate.update("update user set firstname=?,lastname=? where id=?",new Object[] {
                user.getFirstname(),user.getLastname(),user.getId()
        });
    }
    public List<User> callUsers(){
        return jdbcTemplate.query("CALL `callUsers`();",new UserRowMapper());
    }
    public String callUserById(int id){
        SimpleJdbcCall call= new SimpleJdbcCall(jdbcTemplate).withProcedureName("callUserById");

        Map<String,Object> objresult= call.execute(new MapSqlParameterSource("idu",id));
        Object array= objresult.entrySet().toArray();
        return objresult.entrySet().toArray()[0].toString();
    }
}
