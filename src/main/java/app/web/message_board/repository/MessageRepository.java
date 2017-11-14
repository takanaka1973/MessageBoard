package app.web.message_board.repository;

import java.util.List;
import app.web.message_board.entity.Message;

/**
 * メッセージ関連のリポジトリ。
 */
public interface MessageRepository {

    /**
     * 全てのメッセージを取得する。
     */
    List<Message> getAllMessages() throws Exception;

    /**
     * メッセージを保存する。
     */
    void saveMessage(Message message) throws Exception;

    /**
     * 指定されたIDのメッセージを取得する。
     * 該当するメッセージが存在しない場合はnullを返す。
     */
    Message findMessage(long messageId) throws Exception;

    /**
     * メッセージを更新する。
     */
    void updateMessage(Message message) throws Exception;

    /**
     * メッセージを削除する。
     */
    void deleteMessage(long messageId) throws Exception;

}
