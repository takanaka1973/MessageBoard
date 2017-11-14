package app.web.message_board.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import app.web.message_board.entity.Message;
import app.web.message_board.repository.MessageRepository;
import app.web.message_board.repository.NoDataAffectedException;
import lombok.NonNull;

/**
 * メッセージ関連のリポジトリの実装。
 */
@Repository
public class MessageRepositoryImpl implements MessageRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Message> getAllMessages() throws Exception {
        String sql = "SELECT message_id, title, content, valid_period, created_datetime FROM messages ORDER BY message_id";

        List<Message> messages = jdbcTemplate.query(sql, new RowMapper<Message>() {
            @Override
            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
                Message aMessage = new Message();

                aMessage.setMessageId(rs.getLong("message_id"));
                aMessage.setTitle(rs.getString("title"));
                aMessage.setContent(rs.getString("content"));
                aMessage.setValidPeriodInDays(rs.getInt("valid_period"));
                aMessage.setCreatedDatetime(new Date(rs.getTimestamp("created_datetime").getTime()));

                return aMessage;
            }
        });

        return messages;
    }

    @Override
    public void saveMessage(@NonNull Message message) throws Exception {
        String sql = "INSERT INTO messages (title, content, valid_period, created_datetime)"
                + " VALUES (:title, :content, :valid_period, NOW())";

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("title", message.getTitle());
        parameters.addValue("content", message.getContent());
        parameters.addValue("valid_period", message.getValidPeriodInDays());

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public Message findMessage(long messageId) throws Exception {
        String sql = "SELECT message_id, title, content, valid_period, created_datetime"
                + " FROM messages WHERE message_id = :message_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("message_id", messageId);

        List<Message> messages = jdbcTemplate.query(sql, parameters, new RowMapper<Message>() {
            @Override
            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
                Message aMessage = new Message();

                aMessage.setMessageId(rs.getLong("message_id"));
                aMessage.setTitle(rs.getString("title"));
                aMessage.setContent(rs.getString("content"));
                aMessage.setValidPeriodInDays(rs.getInt("valid_period"));
                aMessage.setCreatedDatetime(new Date(rs.getTimestamp("created_datetime").getTime()));

                return aMessage;
            }
        });

        if (messages.size() == 1) {
            return messages.get(0);
        } else if (messages.size() <= 0) {
            return null;
        } else {
            throw new RuntimeException("multiple rows were retrieved unexpectedly");
        }
    }

    @Override
    public void updateMessage(@NonNull Message message) throws Exception {
        String sql = "UPDATE messages SET"
                + " title = :title, content = :content, valid_period = :valid_period, created_datetime = :created_datetime"
                + " WHERE message_id = :message_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("message_id", message.getMessageId());
        parameters.addValue("title", message.getTitle());
        parameters.addValue("content", message.getContent());
        parameters.addValue("valid_period", message.getValidPeriodInDays());
        parameters.addValue("created_datetime", message.getCreatedDatetime());

        int numRowsUpdated = jdbcTemplate.update(sql, parameters);

        if (numRowsUpdated <= 0) {
            throw new NoDataAffectedException("no message was updated");
        } else if (numRowsUpdated > 1) {
            throw new RuntimeException("multiple rows were updated unexpectedly");
        }
    }

    @Override
    public void deleteMessage(long messageId) throws Exception {
        String sql = "DELETE FROM messages WHERE message_id = :message_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("message_id", messageId);

        int numRowsDeleted = jdbcTemplate.update(sql, parameters);

        if (numRowsDeleted <= 0) {
            throw new NoDataAffectedException("no message was deleted");
        } else if (numRowsDeleted > 1) {
            throw new RuntimeException("multiple rows were deleted unexpectedly");
        }
    }

}
