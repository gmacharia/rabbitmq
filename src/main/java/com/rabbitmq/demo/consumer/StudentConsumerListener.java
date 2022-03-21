package com.rabbitmq.demo.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.demo.dao.StudentDetailsDTO;
import com.rabbitmq.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class StudentConsumerListener implements ChannelAwareMessageListener {

    private final ObjectMapper objectMapper;
    private final StudentService studentService;

    @Override
    //@RabbitListener(queues = "queue name")
    public void onMessage(@NotNull Message message, Channel channel) throws Exception {
        log.info("We have received the following message in the queue ::" + message.getBody().toString());
        try {
            StudentDetailsDTO studentDetailsDTO = objectMapper.readValue(message.getBody(), StudentDetailsDTO.class);
            studentService.processStudentDetails(studentDetailsDTO);
            log.info("Finished processing the request");
        } catch (IOException e) {
            log.info("Exception caught when reading the message from the queue :: ");
        }
    }
}
