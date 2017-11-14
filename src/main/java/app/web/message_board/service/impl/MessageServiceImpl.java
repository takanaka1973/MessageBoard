package app.web.message_board.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.web.message_board.entity.Message;
import app.web.message_board.repository.MessageRepository;
import app.web.message_board.service.MessageService;
import lombok.NonNull;

/**
 * メッセージ関連のサービスの実装。
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAllMessages() throws Exception {
        return messageRepository.getAllMessages();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void saveMessage(@NonNull Message message) throws Exception {
        // NOTE: 本来はここでも'message'の各フィールドの値の検証をすべき
        messageRepository.saveMessage(message);
    }

    @Override
    @Transactional(readOnly = true)
    public Message findMessage(long messageId) throws Exception {
        return messageRepository.findMessage(messageId);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateMessage(@NonNull Message message) throws Exception {
        // NOTE: 本来はここでも'message'の各フィールドの値の検証をすべき
        messageRepository.updateMessage(message);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteMessage(long messageId) throws Exception {
        messageRepository.deleteMessage(messageId);
    }

}
